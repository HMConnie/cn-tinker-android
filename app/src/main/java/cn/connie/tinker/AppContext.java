package cn.connie.tinker;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;
import android.text.TextUtils;

import com.dx168.patchsdk.PatchManager;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.tinker.loader.TinkerLoader;
import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.zhy.autolayout.config.AutoLayoutConifg;

import cn.connie.tinker.base.NewsLifecycleHandler;
import cn.connie.tinker.compontent.AppComponent;
import cn.connie.tinker.compontent.AppModule;
import cn.connie.tinker.compontent.DaggerAppComponent;
import cn.connie.tinker.exception.DealUnKnowException;
import cn.connie.tinker.tinker.PatchManagerRegisterListener;
import cn.connie.tinker.tinker.QueryAndPatchPatchManager;
import cn.connie.tinker.tinker.SampleApplicationLike;
import cn.connie.tinker.utils.AppUtil;
import cn.connie.tinker.utils.Constants;
import cn.connie.tinker.utils.ResPathCenter;

public class AppContext extends TinkerApplication implements DealUnKnowException.ExceptionCallBack {

    private static volatile boolean isInit = false;

    private AppComponent appComponent;
    private static AppContext mInstance;
    private NewsLifecycleHandler mNewsLifecycleHandler;
    private RefWatcher mRefWatcher;


    public static AppContext getInstance() {
        return mInstance;
    }

    public NewsLifecycleHandler getNewsLifecycleHandler() {
        return mNewsLifecycleHandler;
    }

    public RefWatcher getRefWatcher() {
        return mRefWatcher;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public AppContext() {
        super(ShareConstants.TINKER_ENABLE_ALL, SampleApplicationLike.class.getName(), TinkerLoader.class.getName(), false);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        String processName = AppUtil.getAppName(this);
        // 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
        if (!TextUtils.equals(processName, getPackageName()) || isInit) {
            return;
        }
        init();
    }

    /**
     * 分割Dex文件，解决方法限制，解决方法数是有限制的差不多64000
     *
     * @param base
     */
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private void init() {
        isInit = true;
        mInstance = this;

        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        appComponent.inject(this);


        mNewsLifecycleHandler = appComponent.provideLifecycleHandler();
        mRefWatcher = appComponent.provideRefWatcher();

        registerActivityLifecycleCallbacks(mNewsLifecycleHandler);

        if (!BuildConfig.DEBUG) {
            DealUnKnowException.getInstance().init(this, ResPathCenter.getInstance().getLogPath(), this);
        }
        /*** AutoLayout 适配布局***/
        AutoLayoutConifg.getInstance().useDeviceSize();

        String umAppKey = AppUtil.getMetaData(Constants.UMENG_APPKEY);
        String umChannel = AppUtil.getMetaData(Constants.UMENG_CHANNEL);

        /** 友盟统计 **/
        UMConfigure.setLogEnabled(BuildConfig.DEBUG);
        UMConfigure.init(this, umAppKey, umChannel, UMConfigure.DEVICE_TYPE_PHONE, null);
        MobclickAgent.setSessionContinueMillis(1000); // 设置session的间隔时间
        MobclickAgent.setCatchUncaughtExceptions(BuildConfig.DEBUG); //release时,关闭错误统计、自己捕获错误信息

        /**tinker热修复功能***/
        String appId = AppUtil.getMetaData(Constants.PATCH_APP_ID);
        String appSecret = AppUtil.getMetaData(Constants.PATCH_APP_SECRET);
        PatchManager.getInstance().init(this, Constants.BASE_PATCH_URL, appId, appSecret, new QueryAndPatchPatchManager());
        PatchManager.getInstance().register(new PatchManagerRegisterListener());
        PatchManager.getInstance().setChannel(umChannel);
        PatchManager.getInstance().setTag(umChannel);
    }

    @Override
    public void success(String path, String errorInfo) {
        MobclickAgent.reportError(this, errorInfo);
    }

    @Override
    public void failed(String error) {
        MobclickAgent.reportError(this, error);
    }

    @Override
    public void happenedException() {
        /**** 解决Activity重叠问题 ***/
        mNewsLifecycleHandler.finishAllActivity();
        Intent intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent restart = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager am = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC, System.currentTimeMillis() + 2000, restart);
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}

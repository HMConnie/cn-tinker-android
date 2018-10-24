package cn.connie.tinker.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dx168.patchsdk.PatchManager;
import com.umeng.analytics.MobclickAgent;

//import javax.inject.Inject;

import cn.connie.tinker.R;
import cn.connie.tinker.base.BaseInjectActivity;
import cn.connie.tinker.cache.UserCache;
import cn.connie.tinker.compontent.AppComponent;
import cn.connie.tinker.compontent.DaggerMainActivityComponent;
import cn.connie.tinker.event.DefaultEvent;
import cn.connie.tinker.network.exceptions.HttpTimeException;
import cn.connie.tinker.network.exceptions.RetryWhenNetworkException;
import cn.connie.tinker.network.model.base.BaseParam;
import cn.connie.tinker.network.model.req.user.QuickLoginParam;
import cn.connie.tinker.network.model.resp.user.UserInfoResult;
import cn.connie.tinker.network.model.resp.user.UserResult;
import cn.connie.tinker.network.retrofit.NetWorkSubscriber;
import cn.connie.tinker.network.retrofit.ServiceGenerator;
import cn.connie.tinker.network.services.UserServices;
import cn.connie.tinker.utils.Constants;
import cn.connie.tinker.utils.DeviceUtil;
import cn.connie.tinker.utils.LogUtil;
import cn.connie.tinker.utils.RxBus;
import cn.connie.tinker.utils.ToastUtil;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseInjectActivity {

//    @Inject
//    ServiceGenerator serviceGenerator;

    @Override
    public void inject(AppComponent appComponent) {
        DaggerMainActivityComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uniquePsuedoID = DeviceUtil.getUniquePsuedoID();
                LogUtil.e("uniquePsuedoID:" + uniquePsuedoID);
                ToastUtil.show(MainActivity.this, uniquePsuedoID);
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
//        loading();

        PatchManager.getInstance().queryAndPatch(true);
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toActivity(LoginActivity.class);
            }
        });
    }

//    private void loading() {
//        showLoadingDialog(false);
//        QuickLoginParam loginVerifyParam = new QuickLoginParam("18311380063", "1111111");
//        serviceGenerator.createService(UserServices.class)
//                .quickLogin(loginVerifyParam.getHeaders(), loginVerifyParam.getBodyParams())
//                .flatMap(new Func1<UserResult, Observable<UserInfoResult>>() {
//                    @Override
//                    public Observable<UserInfoResult> call(UserResult userResult) {
//                        UserCache.cacheLoginUser(userResult);
//                        return ServiceGenerator.getInstance().createService(UserServices.class).userInfo(new BaseParam().getHeaders());
//                    }
//                })
//                .compose(this.<UserInfoResult>bindToLifecycle())
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .retryWhen(new RetryWhenNetworkException())
//                .subscribe(new NetWorkSubscriber<UserInfoResult>() {
//                    @Override
//                    protected void onError(HttpTimeException ex) {
//                        dismissLoadingDialog();
//                        ToastUtil.show(MainActivity.this, ex.getMessage());
//                    }
//
//
//                    @Override
//                    public void onNext(final UserInfoResult result) {
//                        UserCache.cacheLoginUserInfo(result);
//                        UserCache.setLoginStatus(true);
//                        MobclickAgent.onProfileSignIn(result.data.id);//友盟账号统计
//                        RxBus.getInstance().send(new DefaultEvent(Constants.EventCode.LOGIN_SUCCESS));
//                        finish();
//                    }
//                });
//    }
}

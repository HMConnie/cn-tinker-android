package cn.connie.tinker.base;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.Iterator;
import java.util.List;

import cn.connie.tinker.AppContext;
import cn.connie.tinker.BuildConfig;
import cn.connie.tinker.R;
import cn.connie.tinker.activitys.LoginActivity;
import cn.connie.tinker.cache.UserCache;
import cn.connie.tinker.event.DefaultEvent;
import cn.connie.tinker.utils.AppUtil;
import cn.connie.tinker.utils.Constants;
import cn.connie.tinker.utils.NotificationUtil;
import cn.connie.tinker.utils.PermissionUtil;
import cn.connie.tinker.utils.RxBus;
import cn.connie.tinker.utils.WeakHandler;
import cn.connie.tinker.view.LoadingDialog;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import rx.functions.Action1;

/**
 * 继承RxFragmentActivity，防止Rxjava内存泄露
 */

public abstract class BaseActivity extends RxAppCompatActivity implements EasyPermissions.PermissionCallbacks {

    private static final String[] PERMS = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
    private static final String LAYOUT_LINEARLAYOUT = "LinearLayout";
    private static final String LAYOUT_FRAMELAYOUT = "FrameLayout";
    private static final String LAYOUT_RELATIVELAYOUT = "RelativeLayout";
    private static final int RC_INIT_PERMISSION = 401;
    private static final int DIALOG_PEMISSION_REQ = 1001; // 权限提示对话框
    private static final int DIALOG_LOADING_REQ = 1002; // 加载对话框
    private static final int DIALOG_ENABLE_NOFITY_REQ = 1004; // 通知栏开启提示对话框
    private static final int DIALOG_ENABLE_UNKNOWN_APP = 1005; // 跳转到未知来源界面打开
    private static final int DIALOG_INSTALL_FAILED = 1006; // 应用安装失败
    private static final int DIALOG_REQUEST_EXPIRE = 1007; // 系统时间跟服务器的时间不一致

    private boolean mIsPermissionOtherApp = false; // 是否从跳转了其他app
    private boolean mIsPermissionDialogShow = false; // 是否弹出了权限对话框
    private WeakHandler mWeakHandler;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initEvents();
    }

    private void initEvents() {
        RxBus.with(this).onNext(new Action1<Object>() {
            @Override
            public void call(Object events) {
                if (events instanceof DefaultEvent) {
                    DefaultEvent defaultEvent = (DefaultEvent) events;
                    if (defaultEvent.event == Constants.EventCode.TOKEN_EXPIRE) {
                        if (BaseActivity.this.getClass() != LoginActivity.class && UserCache.loginStatus()) { //防止重复调用
                            UserCache.clear();
                            AppContext.getInstance().getNewsLifecycleHandler().finish2MainActivity();
                            toActivity(LoginActivity.class);
                        }
                    } else if (defaultEvent.event == Constants.EventCode.REQUEST_EXPIRATION) {
                        showRequestExpire();
                    }
                    BaseActivity.this.onEventMainThread(defaultEvent);
                }
            }
        }).create();
    }

    /**
     * 移除所有的Fragment
     */
    protected void removeAllFragments() {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null && fragments.size() > 0) {
            FragmentTransaction fts = fragmentManager.beginTransaction();
            Iterator<Fragment> iterator = fragments.iterator();
            while (iterator.hasNext()) {
                Fragment fragment = iterator.next();
                if (fragment != null) {
                    fts.remove(fragment);
                }
            }
            fts.commitAllowingStateLoss();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mIsPermissionOtherApp && (!mIsPermissionDialogShow)) {
            requiredInitPermissions();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppUtil.fixInputMethodManagerLeak(this);
        if (BuildConfig.DEBUG && AppContext.getInstance().getRefWatcher() != null) {
            AppContext.getInstance().getRefWatcher().watch(this);
        }
    }


    public void toActivity(Class<?> toClsActivity) {
        toActivity(toClsActivity, null);
    }

    public void toActivity(Class<?> toClsActivity, Bundle bundle) {
        Intent intent = new Intent(this, toClsActivity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void toActivityForResult(Class<?> toClsActivity, int requestCode) {
        toActivityForResult(toClsActivity, null, requestCode);
    }

    public void toActivityForResult(Class<?> toClsActivity, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, toClsActivity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 检测通知栏是否开启
     */
    public void checkNotifyEnable() {
        if (!NotificationUtil.isNotificationEnabled(this)) {
            showDialog(DIALOG_ENABLE_NOFITY_REQ);
        }
    }

    /**
     * 显示加载对话框
     */
    public void showLoadingDialog() {
        showLoadingDialog(true);
    }

    public void showLoadingDialog(boolean isCancelable) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constants.BUNDLE_STR_NEW_KEY, isCancelable);
        showDialog(DIALOG_LOADING_REQ, bundle);
    }


    /**
     * dismiss加载对话框
     */

    public void dismissLoadingDialog() {
        removeDialog(DIALOG_LOADING_REQ);
    }

    /**
     * 未知来源开关对话框
     */
    public void showUnknowInstallAppDialog() {
        showDialog(DIALOG_ENABLE_UNKNOWN_APP);
    }

    /**
     * 安装失败对话框
     */
    public void showInstallFailedAppDialog() {
        showDialog(DIALOG_INSTALL_FAILED);
    }

    /**
     * 获取WeakHandler
     */
    public WeakHandler weakHandler() {
        if (mWeakHandler == null) {
            mWeakHandler = new WeakHandler();
        }
        return mWeakHandler;
    }

    /**
     * 服务器返回请求超时
     */
    public void showRequestExpire() {
        showDialog(DIALOG_REQUEST_EXPIRE);
    }

    public void runOnUiThreadDelay(Runnable runnable, long delayMillis) {
        weakHandler().postDelayed(runnable, delayMillis);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_PEMISSION_REQ) {
            MaterialDialog d = new MaterialDialog.Builder(this)
                    .title(getString(R.string.str_granted_title))
                    .content(getString(R.string.str_granted_init))
                    .negativeColor(Color.GRAY)
                    .cancelable(false)
                    .contentColor(getResources().getColor(R.color.color_dialog_content))
                    .negativeColor(getResources().getColor(R.color.color_dialog_cancle))
                    .positiveColor(getResources().getColor(R.color.color_dialog_positive))
                    .positiveText("去设置")
                    .negativeText("取消")
                    .build();

            d.getActionButton(DialogAction.POSITIVE).setTextSize(16);
            d.getActionButton(DialogAction.NEGATIVE).setTextSize(16);
            d.getContentView().setTextSize(18);
            return d;
        } else if (id == DIALOG_LOADING_REQ) {
            return new LoadingDialog(this);
        } else if (id == DIALOG_ENABLE_NOFITY_REQ) {
            MaterialDialog d = new MaterialDialog.Builder(this)
                    .cancelable(false)
                    .title(R.string.str_notifty_title)
                    .content(R.string.str_notifty_message)
                    .contentColor(getResources().getColor(R.color.color_dialog_content))
                    .negativeColor(getResources().getColor(R.color.color_dialog_cancle))
                    .positiveColor(getResources().getColor(R.color.color_dialog_positive))
                    .positiveText(R.string.str_dialog_notify_positive)
                    .negativeText(R.string.str_dialog_negative)
                    .onNegative(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            dialog.dismiss();
                        }
                    })
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            dialog.dismiss();
                            AppUtil.toDetailSettings(dialog.getContext());
                        }
                    })
                    .build();

            d.getActionButton(DialogAction.POSITIVE).setTextSize(16);
            d.getActionButton(DialogAction.NEGATIVE).setTextSize(16);
            d.getContentView().setTextSize(18);
            return d;
        } else if (id == DIALOG_ENABLE_UNKNOWN_APP) {
            MaterialDialog d = new MaterialDialog.Builder(this)
                    .cancelable(false)
                    .title(R.string.str_unknown_app_title)
                    .content(R.string.str_unknown_app_message)
                    .contentColor(getResources().getColor(R.color.color_dialog_content))
                    .negativeColor(getResources().getColor(R.color.color_dialog_cancle))
                    .positiveColor(getResources().getColor(R.color.color_dialog_positive))
                    .positiveText(R.string.str_dialog_notify_positive)
                    .negativeText(R.string.str_dialog_negative)
                    .onNegative(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            dialog.dismiss();
                        }
                    })
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            dialog.dismiss();
                            Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    })
                    .build();

            d.getActionButton(DialogAction.POSITIVE).setTextSize(16);
            d.getActionButton(DialogAction.NEGATIVE).setTextSize(16);
            d.getContentView().setTextSize(18);
            return d;
        } else if (id == DIALOG_INSTALL_FAILED) {
            MaterialDialog d = new MaterialDialog.Builder(this)
                    .cancelable(false)
                    .title(R.string.str_install_error_title)
                    .content(R.string.str_install_error_message)
                    .contentColor(getResources().getColor(R.color.color_dialog_content))
                    .negativeColor(getResources().getColor(R.color.color_dialog_cancle))
                    .positiveColor(getResources().getColor(R.color.color_dialog_positive))
                    .positiveText(R.string.str_dialog_notify_positive)
                    .negativeText(R.string.str_dialog_negative)
                    .onNegative(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            dialog.dismiss();
                        }
                    })
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            dialog.dismiss();
                            Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    })
                    .build();

            d.getActionButton(DialogAction.POSITIVE).setTextSize(16);
            d.getActionButton(DialogAction.NEGATIVE).setTextSize(16);
            d.getContentView().setTextSize(18);
            return d;
        } else if (id == DIALOG_REQUEST_EXPIRE) {
            MaterialDialog d = new MaterialDialog.Builder(this)
                    .cancelable(false)
                    .title(R.string.str_time_error_title)
                    .content(R.string.str_time_error_message)
                    .contentColor(getResources().getColor(R.color.color_dialog_content))
                    .negativeColor(getResources().getColor(R.color.color_dialog_cancle))
                    .positiveColor(getResources().getColor(R.color.color_dialog_positive))
                    .positiveText(R.string.str_dialog_notify_positive)
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            dialog.dismiss();
                            AppUtil.toDateSettings(dialog.getContext());
                        }
                    })
                    .build();

            d.getActionButton(DialogAction.POSITIVE).setTextSize(16);
            d.getContentView().setTextSize(18);
            return d;
        }
        return super.onCreateDialog(id);
    }


    @Override
    protected void onPrepareDialog(int id, final Dialog dialog, final Bundle args) {
        if (id == DIALOG_PEMISSION_REQ && dialog instanceof MaterialDialog) {
            MaterialDialog materialDialog = (MaterialDialog) dialog;
            materialDialog.getBuilder().onNegative(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    dialog.dismiss();
                    BaseActivity.this.mIsPermissionDialogShow = false;
                    onPermissionCallbackFailed();
                }
            }).onPositive(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    dialog.dismiss();
                    Intent intent = PermissionUtil.getAppDetailSettingIntent(BaseActivity.this);
                    startActivity(intent);

                    mIsPermissionDialogShow = false;
                    mIsPermissionOtherApp = true;
                }
            });
            mIsPermissionDialogShow = true;
        }
        if (id == DIALOG_LOADING_REQ && dialog instanceof LoadingDialog) {
            LoadingDialog loadingDialog = (LoadingDialog) dialog;
            boolean isCancelable = args.getBoolean(Constants.BUNDLE_STR_NEW_KEY, true);
            loadingDialog.setCancelable(isCancelable);
        }
        super.onPrepareDialog(id, dialog, args);
    }

    protected void onEventMainThread(DefaultEvent events) {
    }

    /**
     * 权限获取成功
     */
    protected void onPermissionCallbackSuccess() {
    }

    /**
     * 权限获取失败
     */
    protected void onPermissionCallbackFailed() {
    }

    /**
     * 是否有权限
     *
     * @return
     */
    public boolean hasPermissions() {
        return EasyPermissions.hasPermissions(this, PERMS);
    }


    @AfterPermissionGranted(RC_INIT_PERMISSION)
    public void requiredInitPermissions() {
        if (EasyPermissions.hasPermissions(this, PERMS)) {
            onPermissionCallbackSuccess();
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.str_granted_init), RC_INIT_PERMISSION, PERMS);
        }
    }

    /**
     * 授予的权限回调
     *
     * @param requestCode
     * @param perms
     */
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (requestCode == RC_INIT_PERMISSION) {
            if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
                if (perms.size() == PERMS.length) {
                    onPermissionCallbackSuccess();
                } else {
                    showDialog(DIALOG_PEMISSION_REQ);
                }
            } else {
                onPermissionCallbackFailed();
            }
        }
    }


    /**
     * 拒绝的权限回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == RC_INIT_PERMISSION) {
            if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
                if (perms.size() == 0) {
                    onPermissionCallbackSuccess();
                } else {
                    showDialog(DIALOG_PEMISSION_REQ);
                }
            } else {
                onPermissionCallbackFailed();
            }
        }

    }


    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = null;
        if (name.equals(LAYOUT_FRAMELAYOUT)) {
            view = new AutoFrameLayout(context, attrs);
        }

        if (name.equals(LAYOUT_LINEARLAYOUT)) {
            view = new AutoLinearLayout(context, attrs);
        }

        if (name.equals(LAYOUT_RELATIVELAYOUT)) {
            view = new AutoRelativeLayout(context, attrs);
        }

        if (view != null) return view;

        return super.onCreateView(name, context, attrs);
    }

}

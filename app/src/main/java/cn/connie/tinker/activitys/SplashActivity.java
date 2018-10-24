package cn.connie.tinker.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import cn.connie.tinker.AppContext;
import cn.connie.tinker.R;
import cn.connie.tinker.base.BaseActivity;
import cn.connie.tinker.utils.Constants;

/**
 * Created by hinge on 17/10/17.
 */

public class SplashActivity extends BaseActivity implements View.OnClickListener {


    private RelativeLayout mRlBg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        if (hasPermissions()) { // 判断是否有权限
            toMainActivity();
        } else {
            requiredInitPermissions();
        }
    }


    private void initView() {
        mRlBg = (RelativeLayout) findViewById(R.id.rl_bg);
        mRlBg.setOnClickListener(this);
    }

    @Override
    protected void onPermissionCallbackFailed() {
        super.onPermissionCallbackFailed();
        AppContext.getInstance().getNewsLifecycleHandler().appExit(); // 权限获取失败直接退出app
    }

    @Override
    protected void onPermissionCallbackSuccess() {
        super.onPermissionCallbackSuccess();
        toMainActivity();
    }

    private void toMainActivity() {
        runOnUiThreadDelay(new Runnable() {
            @Override
            public void run() {
                jumpMainActivity();
            }
        }, Constants.LAUNCHER_DELAY);

    }


    private void jumpMainActivity() {
        toActivity(MainActivity.class);
        finish();
        overridePendingTransition(R.anim.anim_splash_enter, R.anim.anim_splash_quit);
    }


    @Override
    public void onClick(View v) {
    }


}

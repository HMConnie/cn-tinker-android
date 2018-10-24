package cn.connie.tinker.base;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;

import cn.connie.tinker.event.DefaultEvent;
import cn.connie.tinker.utils.Constants;
import cn.connie.tinker.utils.PermissionUtil;
import cn.connie.tinker.utils.RxBus;


/**
 * Created by hinge on 17/4/12.
 */

public class PermissionShadowActivity extends Activity {


    private static final int REQUEST_CODE = 100;//请求吗
    private String mPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            handleIntent(getIntent());
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        mPermission = intent.getStringExtra("permission");
        if (TextUtils.equals(Manifest.permission.ACCESS_FINE_LOCATION, mPermission)) {//如果当前传输过来是定位
            startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), REQUEST_CODE);
        } else if (TextUtils.equals(Manifest.permission.CAMERA, mPermission)) {
            startActivityForResult(PermissionUtil.getAppDetailSettingIntent(this), REQUEST_CODE);
        } else {
            finish(false);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        boolean isOpen = false;
        if (requestCode == REQUEST_CODE) {
            if (TextUtils.equals(Manifest.permission.ACCESS_FINE_LOCATION, mPermission)) {//如果当前传输过来是定位
                isOpen = PermissionUtil.isOPenGPS(this);
            } else if (TextUtils.equals(Manifest.permission.CAMERA, mPermission)) {
                isOpen = PermissionUtil.isCameraCanUse();
            }
            finish(isOpen);
        }

    }

    private void finish(boolean isOpen) {
        RxBus.getInstance().send(new DefaultEvent(Constants.EventCode.PERMISSION_INFO, isOpen));
        finish();
    }


}

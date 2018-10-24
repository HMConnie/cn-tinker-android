package cn.connie.tinker.tinker;

import com.dx168.patchsdk.Listener;

import cn.connie.tinker.utils.LogUtil;

/**
 * Created by hinge on 2018/10/17.
 */

public class PatchManagerRegisterListener implements Listener {
    @Override
    public void onQuerySuccess(String response) {
        LogUtil.i("onQuerySuccess response=" + response);
    }

    @Override
    public void onQueryFailure(Throwable e) {
        LogUtil.i("onQueryFailure e=" + e);
    }

    @Override
    public void onDownloadSuccess(String path) {
        LogUtil.i("onDownloadSuccess path=" + path);
    }

    @Override
    public void onDownloadFailure(Throwable e) {
        LogUtil.i("onDownloadFailure e=" + e);
    }

    @Override
    public void onPatchSuccess() {
        LogUtil.i("onPatchSuccess");
    }

    @Override
    public void onPatchFailure(String error) {
        LogUtil.i("onPatchFailure=" + error);
    }

    @Override
    public void onLoadSuccess() {
        LogUtil.i("onLoadSuccess");
    }

    @Override
    public void onLoadFailure(String error) {
        LogUtil.i("onLoadFailure=" + error);
    }
}

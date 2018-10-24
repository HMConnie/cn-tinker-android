package cn.connie.tinker.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.indicators.LineSpinFadeLoaderIndicator;
import com.zhy.autolayout.utils.AutoUtils;

import cn.connie.tinker.R;


public class LoadingDialog extends Dialog {


    private AVLoadingIndicatorView mAVLoadingIndicatorView;

    public LoadingDialog(Context context) {
        super(context, R.style.LodingViewDialog);
        View rootView = View.inflate(context, R.layout.dialog_loading, null);
        AutoUtils.auto(rootView);
        setContentView(rootView);
        mAVLoadingIndicatorView = rootView.findViewById(R.id.avIndicatorView);
        mAVLoadingIndicatorView.setIndicator(new LineSpinFadeLoaderIndicator());  //release版本不生效解决方式
    }

    @Override
    public void dismiss() {
        super.dismiss();
        mAVLoadingIndicatorView.hide();
    }

    @Override
    public void show() {
        super.show();
        mAVLoadingIndicatorView.show();
    }

}

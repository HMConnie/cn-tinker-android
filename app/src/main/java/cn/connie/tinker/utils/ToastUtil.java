package cn.connie.tinker.utils;

import android.content.Context;
import android.text.TextUtils;

import es.dmoral.toasty.Toasty;


/**
 *
 */
public final class ToastUtil {

    /**
     * 当前工具类的总开关
     */
    private final static boolean isAllowShow = true;


    private ToastUtil() {

    }


    /**
     * 功能:开关(isAllowShow开启后，此方法功能可用)
     */
    public static void show(Context context, String msg) {
        if (!isAllowShow || TextUtils.isEmpty(msg) || context == null) {
            return;
        }

        // 解决小米手机显示不了Toast的bug
        Toasty.normal(context, msg).show();
    }



}

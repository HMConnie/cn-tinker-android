package cn.connie.tinker.network.exceptions;


import android.text.TextUtils;


import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

import cn.connie.tinker.BuildConfig;
import cn.connie.tinker.network.model.base.NetWorkErrorResult;
import cn.connie.tinker.utils.EMUtil;
import cn.connie.tinker.utils.GsonUtil;
import cn.connie.tinker.utils.HttpNetUtil;
import okhttp3.Request;


/**
 * 自定义错误信息，统一处理返回处理
 * Created by WZG on 2016/7/16.
 */
public class HttpTimeException extends RuntimeException {
    private static final String MESSAGE_NO_NET_ERROR = "网络断开，请检查网络";
    private static final String MESSAGE_NET_WEAK_ERROR = "网络不给力";
    private static final String MESSAGE_KNOWN_NET_ERROR = "服务器忙坏了，等会儿再试吧";
    private static final String MESSAGE_TIMEOUT_ERROR = "网络超时了";
    private static final String MESSAGE_ERROR = "程序内部出错了";
    private int mCode;
    private String mMessage;
    private String mReason;
    private Request mOriginRequest;
    private boolean isConnectNet;// 是否网络中断 ,返回true表示断网了

    public Request getOriginRequest() {
        return mOriginRequest;
    }

    /***
     * 解析网络超时异常
     */
    private static String parseError(Throwable throwable) {
        if ((throwable instanceof ConnectException
                || throwable instanceof SocketTimeoutException
                || throwable instanceof TimeoutException
                || throwable instanceof SocketException)) {
            return BuildConfig.DEBUG ? MESSAGE_TIMEOUT_ERROR + "/" + throwable.getMessage() : MESSAGE_NET_WEAK_ERROR;
        } else {
            // 上报给友盟统计
            EMUtil.reportUMError(throwable);
            return BuildConfig.DEBUG ? MESSAGE_ERROR + "/" + throwable.getMessage() : MESSAGE_KNOWN_NET_ERROR;
        }
    }

    public HttpTimeException(Throwable throwable) {
        this(throwable, 0);
    }

    public HttpTimeException(Throwable throwable, int code) {
        this(code, parseError(throwable), null);
    }


    public HttpTimeException(int code, String result, Request original) {
        this.mCode = code;
        this.isConnectNet = HttpNetUtil.isConnected();
        this.mOriginRequest = original;
        if (!isConnectNet) {
            mMessage = MESSAGE_NO_NET_ERROR;
        } else {
            if (original != null) {
                NetWorkErrorResult netWorkErrorResult = GsonUtil.fromJsontoBean(result, NetWorkErrorResult.class);
                if (netWorkErrorResult != null) {
                    mReason = netWorkErrorResult.msg;
                    mMessage = netWorkErrorResult.msgText;
                } else {
                    // 上报给友盟统计
                    EMUtil.reportUMError(code, result, original);
                    mMessage = MESSAGE_KNOWN_NET_ERROR;
                }

            } else {
                mMessage = TextUtils.isEmpty(result) ? MESSAGE_KNOWN_NET_ERROR : result;
            }

        }

    }


    public int getCode() {
        return mCode;
    }

    public String getMessage() {
        return mMessage;
    }

    public String getReason() {
        return mReason;
    }

    public boolean isConnectNet() {
        return isConnectNet;
    }
}



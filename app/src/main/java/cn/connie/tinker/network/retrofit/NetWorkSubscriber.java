package cn.connie.tinker.network.retrofit;

import android.net.ParseException;

import com.google.gson.JsonParseException;
import cn.connie.tinker.event.DefaultEvent;
import cn.connie.tinker.network.exceptions.HttpTimeException;
import cn.connie.tinker.network.model.base.RetryRequest;
import cn.connie.tinker.utils.Constants;
import cn.connie.tinker.utils.RxBus;

import org.json.JSONException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Request;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by hinge on 17/4/10.
 */

public abstract class NetWorkSubscriber<T> extends Subscriber<T> {


    /**
     * 处理网络请求错误
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        Throwable throwable = e;
        //获取最根源的异常
        while (throwable.getCause() != null) {
            e = throwable;
            throwable = throwable.getCause();
        }

        if (e instanceof HttpException) {             //HTTP错误
            HttpException httpException = (HttpException) e;
            onError(new HttpTimeException(e, httpException.code()));
        } else {
            if (e instanceof HttpTimeException) {    //服务器返回的错误
                HttpTimeException exception = (HttpTimeException) e;
                if (exception.getCode() == 401) {
                    Request request = ((HttpTimeException) e).getOriginRequest();
                    Type genType = getClass().getGenericSuperclass();
                    Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
                    Class clazz = (Class) params[0];
                    RetryRequest retryRequest = new RetryRequest(request, this, clazz, new RetryRequest.RetryUrl(request.url().toString(), request.method()));
                    RxBus.getInstance().send(new DefaultEvent(Constants.EventCode.TOKEN_EXPIRE, retryRequest));
                } else if (Constants.NetWorkErrorReason.RequestExpirationException.equals(exception.getReason())) {
                    RxBus.getInstance().send(new DefaultEvent(Constants.EventCode.REQUEST_EXPIRATION));
                }
                onError(exception);
            } else if (e instanceof JsonParseException
                    || e instanceof JSONException
                    || e instanceof ParseException) {// json解析错误
                onError(new HttpTimeException(e));
            } else {
                onError(new HttpTimeException(e));  //未知错误
            }
        }
    }


    /**
     * 错误回调
     */
    protected abstract void onError(HttpTimeException ex);


    @Override
    public void onCompleted() {

    }

}
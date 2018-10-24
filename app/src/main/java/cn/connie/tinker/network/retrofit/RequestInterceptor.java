package cn.connie.tinker.network.retrofit;


import cn.connie.tinker.BuildConfig;
import cn.connie.tinker.network.exceptions.HttpTimeException;
import cn.connie.tinker.utils.LogUtil;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * Created by hinge on 17/4/6.
 */

public class RequestInterceptor implements Interceptor {

    private static final Charset UTF8 = Charset.forName("UTF-8");
    private final String X_HTTP_METHOD_OVERRIDE = "x-http-method-override"; // 请求方法是PATCH，必须加上请求头
    private final String METHOD_PATCH = "PATCH"; // PATCH方法
    private final String ACCEPT_ENCODING = "Accept-Encoding"; // 请求头接收的gzip压缩

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();

        /***去除gzip压缩格式**/
        builder.removeHeader(ACCEPT_ENCODING);

        if (request.method().equals(METHOD_PATCH)) {
            builder.header(X_HTTP_METHOD_OVERRIDE, METHOD_PATCH);
        }

        Request newRequest = builder.build();
        Response response = chain.proceed(newRequest);
        if (!response.isSuccessful()) {
            String result = response.body().string();
            if (BuildConfig.DEBUG) {
                LogUtil.e("url = " + request.url().toString() + "\nresult = " + result);
            }
            throw new HttpTimeException(response.code(), result, newRequest); //服务器请求错误吗和错误信息抛出
        } else {
            if (BuildConfig.DEBUG) {
                ResponseBody responseBody = response.body();
                BufferedSource source = responseBody.source();
                source.request(Long.MAX_VALUE);
                Buffer buffer = source.buffer();

                Charset charset = UTF8;
                MediaType contentType = responseBody.contentType();
                if (contentType != null) {
                    charset = contentType.charset(UTF8);
                }
                long contentLength = responseBody.contentLength();
                if (contentLength != 0) {
                    String respResult = buffer.clone().readString(charset);
                    LogUtil.i(respResult);
                }
            }


        }
        return response;
    }


}

package cn.connie.tinker.network.model.base;


import okhttp3.Request;
import rx.Subscriber;

/**
 * Created by yangkang on 2017/5/6.
 */

public class RetryRequest<T> {

    public RetryUrl retryUrl;
    public Request request;
    public Subscriber subscriber;
    public Class<T> result;


    public RetryRequest(Request request, Subscriber subscriber, Class<T> result, RetryUrl retryUrl) {
        this.request = request;
        this.subscriber = subscriber;
        this.result = result;
        this.retryUrl = retryUrl;
    }


    public static class RetryUrl {
        public String url;
        public String method;

        public RetryUrl(String url, String method) {
            this.url = url;
            this.method = method;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj != null && obj instanceof RetryUrl) {
                RetryUrl retryUrl = (RetryUrl) obj;
                return url.equals(retryUrl.url) && method.equals(retryUrl.method);
            }
            return super.equals(obj);
        }
    }
}

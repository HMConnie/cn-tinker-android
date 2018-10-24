package cn.connie.tinker.network.retrofit;


import cn.connie.tinker.utils.Constants;
import cn.connie.tinker.utils.SDCard;

import java.util.concurrent.TimeUnit;

import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求生成器
 */

public class ServiceGenerator {

    private static final int HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 10 * 1024 * 1024;//最大缓存10M


    private static ServiceGenerator mInstance;

    private RequestInterceptor requestInterceptor;//网络请求拦截器
    private CacheInterceptor cacheInterceptor; //缓存拦截器
    private Cache cache;//网络缓存
    private Retrofit retrofit;

    public RequestInterceptor getRequestInterceptor() {
        return requestInterceptor;
    }

    public CacheInterceptor getCacheInterceptor() {
        return cacheInterceptor;
    }

    public Cache getCache() {
        return cache;
    }

    private ServiceGenerator() {
        requestInterceptor = new RequestInterceptor();//网络请求拦截器
        cacheInterceptor = new CacheInterceptor(); //缓存拦截器
        cache = new Cache(SDCard.getDiskCachePath(), HTTP_RESPONSE_DISK_CACHE_MAX_SIZE);//缓存路径和文件大小

        /**创建okhttp，拼接log拦截器、cache拦截器、请求头拦截器**/
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .addInterceptor(cacheInterceptor) //设置缓存拦截器
                .cache(cache)//设置缓存
                .addNetworkInterceptor(requestInterceptor)
                .connectTimeout(Constants.NET_TIMEOUT, TimeUnit.SECONDS) // 设置连接超时时间
                .readTimeout(Constants.NET_TIMEOUT, TimeUnit.SECONDS) // 设置读取超时时间
                .retryOnConnectionFailure(true) //失败重连
                .connectionPool(new ConnectionPool(5, 1, TimeUnit.MINUTES));//keepAliveDuration时间，让每次连接5秒后就关闭。

        OkHttpClient client = RetrofitUrlManager.getInstance().with(clientBuilder).build();

        // 添加多个域名供选择
        RetrofitUrlManager.getInstance().putDomain(Constants.BASE_URL_KEY, Constants.BASE_URL);
        RetrofitUrlManager.getInstance().putDomain(Constants.BASE_UPLOAD_KEY, Constants.BASE_UPLOAD_URL);
        RetrofitUrlManager.getInstance().putDomain(Constants.BASE_WEBVIEW_KEY, Constants.BASE_WEBVIEW_URL);

        /**创建retrofit，加入gson转化器、rxjava适配器**/
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();


    }

    public static ServiceGenerator getInstance() {
        if (null == mInstance) {
            synchronized (ServiceGenerator.class) {
                if (null == mInstance) {
                    mInstance = new ServiceGenerator();
                }
            }
        }
        return mInstance;
    }


    /**
     * @param clazz 接口
     * @param <T>
     * @return
     */
    public <T> T createService(Class<T> clazz) {
        return retrofit.create(clazz);
    }

}

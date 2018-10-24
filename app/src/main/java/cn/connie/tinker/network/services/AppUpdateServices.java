package cn.connie.tinker.network.services;

import cn.connie.tinker.network.model.resp.appupdate.AppUpdateResult;
import cn.connie.tinker.utils.Constants;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import rx.Observable;

/**
 * Created by hinge on 2018/8/9.
 */

public interface AppUpdateServices {
    /**
     * 获取app最新版本信息
     */
    @GET("/misc/app/version")
    @Headers({"Domain-Name: " + Constants.BASE_UPLOAD_KEY})
    Observable<AppUpdateResult> getNewVersion(@HeaderMap Map<String, String> headers);
}

package cn.connie.tinker.network.services;

import cn.connie.tinker.network.model.resp.advertisement.BannerResult;

import java.util.Map;

import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by hinge on 2018/8/6.
 */

public interface AdvertisementServices {
    /**
     * 首页banner
     *
     * @param headers
     * @return
     */
    @POST("/information/newsAdvertisement.do")
    Observable<BannerResult> banner(@HeaderMap Map<String, String> headers);

}

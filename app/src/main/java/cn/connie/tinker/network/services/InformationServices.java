package cn.connie.tinker.network.services;

import cn.connie.tinker.network.model.resp.information.HotSearchResult;
import cn.connie.tinker.network.model.resp.information.InformationDetailResult;
import cn.connie.tinker.network.model.resp.information.InformationListResult;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by hinge on 2018/8/6.
 */

public interface InformationServices {


    /**
     * 资讯列表
     *
     * @param headers
     * @return
     */
    @POST("/information/informationList.do")
    @FormUrlEncoded
    Observable<InformationListResult> getInformationList(@HeaderMap Map<String, String> headers, @FieldMap Map<String, String> map);


    /**
     * 资讯详情
     *
     * @param headers
     * @return
     */
    @POST("/information/{id}.do")
    Observable<InformationDetailResult> informationDetail(@HeaderMap Map<String, String> headers, @Path("id") String id);

    /**
     * 资讯搜索
     *
     * @param headers
     * @return
     */
    @POST("/information/informationSearch.do")
    @FormUrlEncoded
    Observable<InformationListResult> informationSearch(@HeaderMap Map<String, String> headers, @FieldMap Map<String, String> map);


    /**
     * 收藏资讯
     *
     * @param headers
     * @return
     */
    @POST("/information/browseInformation.do")
    @FormUrlEncoded
    Observable<Void> browseInformation(@HeaderMap Map<String, String> headers, @FieldMap Map<String, String> map);

    /**
     * 取消收藏
     *
     * @param headers
     * @return
     */
    @POST("/information/cancelBrowseInformation.do")
    @FormUrlEncoded
    Observable<Void> cancelBrowseInformation(@HeaderMap Map<String, String> headers, @FieldMap Map<String, String> map);

    /**
     * 搜索推荐
     *
     * @param headers
     * @return
     */
    @POST("/information/recommend.do")
    Observable<HotSearchResult> hotSearch(@HeaderMap Map<String, String> headers);

    /**
     * 用户收藏
     *
     * @param headers
     * @return
     */
    @POST("/information/userCollection.do")
    @FormUrlEncoded
    Observable<InformationListResult> userCollection(@HeaderMap Map<String, String> headers, @FieldMap Map<String, String> map);
}

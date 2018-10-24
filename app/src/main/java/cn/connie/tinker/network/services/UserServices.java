package cn.connie.tinker.network.services;

import cn.connie.tinker.network.model.resp.user.InMailResult;
import cn.connie.tinker.network.model.resp.user.InMailTypeResult;
import cn.connie.tinker.network.model.resp.user.UserInfoResult;
import cn.connie.tinker.network.model.resp.user.UserResult;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by hinge on 2018/7/31.
 */

public interface UserServices {
    /**
     * 发送短信验证码
     */
    @POST("/user/sendSMS.do")
    @FormUrlEncoded
    Observable<Void> sendSMS(@HeaderMap Map<String, String> headMap, @FieldMap Map<String, String> map);

    /**
     * 快捷登录
     *
     * @param headers
     * @param body
     * @return
     */
    @POST("/user/login.do")
    @FormUrlEncoded
    Observable<UserResult> quickLogin(@HeaderMap Map<String, String> headers, @FieldMap Map<String, String> body);

    /**
     * 用户信息
     *
     * @param headers
     * @return
     */
    @POST("/user/userInfo.do")
    Observable<UserInfoResult> userInfo(@HeaderMap Map<String, String> headers);

    /**
     * 修改用户信息
     *
     * @param headers
     * @return
     */
    @POST("/user/modifyInfo.do")
    @FormUrlEncoded
    Observable<Void> modifyInfo(@HeaderMap Map<String, String> headers, @FieldMap Map<String, String> body);

    /**
     * 刷新token
     *
     * @param headers
     * @param body
     * @return
     */
    @POST("/user/token.do")
    @FormUrlEncoded
    Observable<UserResult> refreshToken(@HeaderMap Map<String, String> headers, @FieldMap Map<String, String> body);

    /**
     * 退出登录
     *
     * @param headers
     * @return
     */
    @POST("/user/logout.do")
    Observable<Void> loginOut(@HeaderMap Map<String, String> headers);

    /**
     * 用户反馈
     *
     * @param headers
     * @return
     */
    @POST("/user/feedBack.do")
    @FormUrlEncoded
    Observable<Void> feedBack(@HeaderMap Map<String, String> headers, @FieldMap Map<String, String> body);

    /**
     * 站内信列表
     *
     * @param headers
     * @return
     */
    @POST("/user/userInMail.do")
    @FormUrlEncoded
    Observable<InMailResult> userInMail(@HeaderMap Map<String, String> headers, @FieldMap Map<String, String> body);

    /**
     * 我的消息列表
     *
     * @param headers
     * @return
     */
    @POST("/user/userInMailType.do")
    @FormUrlEncoded
    Observable<InMailTypeResult> userInMailType(@HeaderMap Map<String, String> headers, @FieldMap Map<String, String> body);


}

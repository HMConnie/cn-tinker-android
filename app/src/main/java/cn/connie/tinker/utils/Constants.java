package cn.connie.tinker.utils;


import cn.connie.tinker.BuildConfig;

/**
 * Created by hinge on 17/3/27.
 */

public interface Constants {


    /*** 用户的接口base地址*/
    String BASE_URL = BuildConfig.DEBUG ? "http://testcoinknow.dui1dui.com" : "http://coinknowapi.eshoue.cn";
    String BASE_UPLOAD_URL = BuildConfig.DEBUG ? "http://testckupload.dui1dui.com" : "http://ckupload.eshoue.cn";
    String BASE_WEBVIEW_URL = BuildConfig.DEBUG ? "http://testckwebview.dui1dui.com" : "http://ckwebview.eshoue.cn";
    String BASE_PATCH_URL = BuildConfig.DEBUG ? "http://172.16.10.178:9011/" : "http://172.16.10.178:9011/";

    /***图片服务器的base地址**/
//    String BASE_IMAGE_URL = BuildConfig.DEBUG ? "http://testthumbor.benbenzone.com" : "http://thumbor.benbenzone.com";

    String BASE_URL_KEY = "BASE_URL_KEY";
    String BASE_UPLOAD_KEY = "BASE_UPLOAD_KEY";
    String BASE_WEBVIEW_KEY = "BASE_WEBVIEW_KEY";

    String FILE_UPLOAD_URL = BASE_UPLOAD_URL + "/misc/upload/img";
    /*** BaseParam 过滤的属性名**/
    String SERIALVERSION_UID = "serialVersionUID";
    String CHANGE = "$change";
    String HEAD_INFOS = "headerInfos";
    String BENBEN_SP_FILE = "BENBEN_SP_FILE";

    int NET_TIMEOUT = 10;


    String CLIENT_TYPE = "CUSTOMER_ANDROID";
    String APPID = "59a2bcab4b6b11e7a4a5000b2f82eca7";


    String BUNDLE_OBJ = "BUNDLE_OBJ";
    String BUNDLE_STR_KEY = "BUNDLE_STR_KEY";
    String BUNDLE_STR_NEW_KEY = "BUNDLE_STR_NEW_KEY";
    String ELLIPSIZETEXT_WHOLE = "…【查看全文】";
    String END_TEXT = "【查看全文】";
    String SHARE_DEFAULT_TITLE = "币知";
    long LAUNCHER_DELAY = 2000;


    /***RxBus常量事件标识**/
    interface EventCode {
        int REQUEST_EXPIRATION = 0x999;//请求超时
        int TOKEN_EXPIRE = 0x1000; //token过期
        int LOGIN_SUCCESS = 0x1002;
        int PERMISSION_INFO = 0x1003;
        int NETWORK_CHANGE_EVENT = 0x1004;
        int LOGIN_OUTSIDE = 0x1005;
        int RECEVIER_MESSAGE = 0x1006;
        int MODIFY_USER_INFO = 0x1007;
        int COLLECT_NUM_CHANGE = 0x1008;
        int SELF_BIT_CHANGE = 0x1009;
    }

    interface SpName {
        String CHANNEL_ID = "channelId";
        String GUIDE_VERSION = "GUIDE_VERSION";
        String LOGIN_STATUS = "LOGIN_STATUS";
        String LOGIN_USER_EXPIREDIN = "LOGIN_USER_EXPIREDIN";
        String LOGIN_USER = "LOGIN_USER";
        String LOGIN_USER_INFO = "LOGIN_USER_INFO";
        String USER_GUIDE = "USER_GUIDE";
        String PUSH_STATE = "PUSH_STATE";
        String RAD_RAISE_GREEN_STATE = "RAD_RAISE_GREEN_STATE";
    }


    interface NetWorkErrorReason {
        String RefreshTokenIsInvalidException = "Token is not exist";
        String RequestExpirationException = "Request expiration";
    }

    /**
     * 友盟appkey和channel
     */
    String UMENG_APPKEY = "UMENG_APPKEY";
    String UMENG_CHANNEL = "UMENG_CHANNEL";
    String PATCH_APP_ID = "PATCH_APP_ID";
    String PATCH_APP_SECRET = "PATCH_APP_SECRET";
    String PATCH_APP_TAG = "PATCH_APP_TAG";

    String QQ_APP_ID = "QQAppId";
    String QQ_SECRET = "QQSecret";
    String WECHAT_APP_ID = "WeChatAppId";
    String WECHAT_SECRET = "WeChatSecret";
    String WEIBO_APP_ID = "WeiBoAppId";
    String WEIBO_SECRET = "WeiBoSecret";
    String WEIBO_REDIRECT_URL = "WeiBoRedirectUrl";


}

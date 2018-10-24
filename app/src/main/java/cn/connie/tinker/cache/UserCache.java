package cn.connie.tinker.cache;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import cn.connie.tinker.AppContext;
import cn.connie.tinker.network.model.resp.user.UserInfoResult;
import cn.connie.tinker.network.model.resp.user.UserResult;
import cn.connie.tinker.utils.AppUtil;
import cn.connie.tinker.utils.Constants;
import cn.connie.tinker.utils.GsonUtil;

/**
 * Created by hinge on 17/10/17.
 */

public class UserCache {


    private volatile static long mExpiredInTime;

    private UserCache() {

    }

    /**
     * 清除缓存数据
     */
    public static synchronized void clear() {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        edit.remove(Constants.SpName.LOGIN_STATUS);
        edit.remove(Constants.SpName.LOGIN_USER_EXPIREDIN);
        edit.remove(Constants.SpName.LOGIN_USER);
        edit.remove(Constants.SpName.LOGIN_USER_INFO);
        edit.commit();
    }

    private static SharedPreferences getSharedPreferences() {
        return AppContext.getInstance().getSharedPreferences(Constants.BENBEN_SP_FILE, Context.MODE_PRIVATE);
    }

    public static synchronized void setUserGuide(boolean userGuide) {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        String currentVersion = AppUtil.getVersionName(AppContext.getInstance());
        edit.putBoolean(Constants.SpName.USER_GUIDE, userGuide);
        edit.putString(Constants.SpName.GUIDE_VERSION, currentVersion);
        edit.commit();
    }

    public static boolean userGuide() {
        String guideVersion = getGuideVersion();
        if (TextUtils.isEmpty(guideVersion)) {
            return false;
        } else {
            int compare = AppUtil.compareVersion(AppContext.getInstance(), guideVersion);
            return compare > 0 ? false : getSharedPreferences().getBoolean(Constants.SpName.USER_GUIDE, false);
        }
    }

    /**
     * 获取推送开关状态
     */
    public static boolean getPushState() {
        return getSharedPreferences().getBoolean(Constants.SpName.PUSH_STATE, true);
    }


    /**
     * 获取数据库的版本号
     *
     * @return
     */
    public static String getGuideVersion() {
        return getSharedPreferences().getString(Constants.SpName.GUIDE_VERSION, "");
    }


    public static String getToken() {
        UserResult loginUser = getLoginUser();
        return loginUser != null && loginUser.data != null ? loginUser.data.accessToken : "";
    }

    /**
     * 保存登录的状态
     */
    public static synchronized void setLoginStatus(boolean loginStatus) {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        edit.putBoolean(Constants.SpName.LOGIN_STATUS, loginStatus);
        edit.commit();
    }

    public static boolean loginStatus() {
        return getSharedPreferences().getBoolean(Constants.SpName.LOGIN_STATUS, false);
    }


    public static String getUserId() {
        UserResult loginUser = getLoginUser();
        return loginUser != null && loginUser.data != null ? loginUser.data.userId : null;
    }

    public static UserResult getLoginUser() {
        String json = getSharedPreferences().getString(Constants.SpName.LOGIN_USER, "");
        return !TextUtils.isEmpty(json) ? GsonUtil.fromJsontoBean(json, UserResult.class) : null;
    }

    public static UserInfoResult getLoginUserInfo() {
        String json = getSharedPreferences().getString(Constants.SpName.LOGIN_USER_INFO, "");
        return !TextUtils.isEmpty(json) ? GsonUtil.fromJsontoBean(json, UserInfoResult.class) : null;
    }

    /**
     * /**
     * 保存登录信息
     *
     * @param user
     */
    public static synchronized void cacheLoginUser(UserResult user) {
        if (user == null) return; // 非空判断


        /**过期时间为6天，避免出现客户端和服务端时间过期不一致**/
        mExpiredInTime = System.currentTimeMillis() + user.data.expiredIn * 1000 - 24 * 60 * 60 * 1000;

        SharedPreferences.Editor edit = getSharedPreferences().edit();
        edit.putString(Constants.SpName.LOGIN_USER, GsonUtil.toJsonFromBean(user));//保存用户信息
        edit.putLong(Constants.SpName.LOGIN_USER_EXPIREDIN, mExpiredInTime);//过期时间
        edit.commit();
    }

    public static synchronized void cacheLoginUserInfo(UserInfoResult user) {
        if (user == null) return; // 非空判断


        SharedPreferences.Editor edit = getSharedPreferences().edit();
        edit.putString(Constants.SpName.LOGIN_USER_INFO, GsonUtil.toJsonFromBean(user));//保存用户信息
        edit.commit();
    }

    /**
     * 判断token是否已经过期
     *
     * @return
     */
    public static boolean isExpiredIn() {
        if (mExpiredInTime > 0) {
            return System.currentTimeMillis() - mExpiredInTime > 0;
        } else {
            mExpiredInTime = getSharedPreferences().getLong(Constants.SpName.LOGIN_USER_EXPIREDIN, 0);
            return System.currentTimeMillis() - mExpiredInTime > 0;
        }

    }

}

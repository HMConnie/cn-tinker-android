package cn.connie.tinker.network.retrofit;


import cn.connie.tinker.base.BaseActivity;
import cn.connie.tinker.cache.UserCache;
import cn.connie.tinker.event.DefaultEvent;
import cn.connie.tinker.network.exceptions.HttpTimeException;
import cn.connie.tinker.network.model.base.BaseParam;
import cn.connie.tinker.network.model.req.user.RefreshTokenParam;
import cn.connie.tinker.network.model.resp.user.UserInfoResult;
import cn.connie.tinker.network.model.resp.user.UserResult;
import cn.connie.tinker.network.services.UserServices;
import cn.connie.tinker.utils.Constants;
import cn.connie.tinker.utils.RxBus;
import com.trello.rxlifecycle.android.ActivityEvent;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * 重新登录操作
 */

public final class RefreshToken {


    private RefreshToken() {

    }

    private static boolean mIsExecuteReq = false;// 是否在执行请求

    /**
     * 1,进入前台界面，判断过期时间  大于6天
     * 2,BaseActivity  执行刷新token
     *
     * @param activity
     */

    public static void requestRefreshToken(final BaseActivity activity) {
        if (mIsExecuteReq || activity == null || activity.isFinishing()) return;

        mIsExecuteReq = true;
        if (UserCache.getLoginUser() != null) {
            RefreshTokenParam refreshTokenParam = new RefreshTokenParam(UserCache.getLoginUser().data.refreshToken);
            ServiceGenerator.getInstance().createService(UserServices.class)
                    .refreshToken(refreshTokenParam.getHeaders(), refreshTokenParam.getBodyParams())
                    .flatMap(new Func1<UserResult, Observable<UserInfoResult>>() {
                        @Override
                        public Observable<UserInfoResult> call(UserResult result) {
                            UserResult loginUser = UserCache.getLoginUser();
                            if (result != null && result.data != null && loginUser != null && loginUser.data != null) {
                                loginUser.data.accessToken = result.data.accessToken;
                                loginUser.data.refreshToken = result.data.refreshToken;
                                loginUser.data.expiredIn = result.data.expiredIn;
                                UserCache.cacheLoginUser(loginUser);
                            }
                            return ServiceGenerator.getInstance().createService(UserServices.class).userInfo(new BaseParam().getHeaders());
                        }
                    })
                    .compose(activity.<UserInfoResult>bindUntilEvent(ActivityEvent.DESTROY))
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new NetWorkSubscriber<UserInfoResult>() {
                        @Override
                        protected void onError(HttpTimeException ex) {
                            mIsExecuteReq = false;
                            /**当前是无效的token**/
                            if (Constants.NetWorkErrorReason.RefreshTokenIsInvalidException.equals(ex.getReason())) {
                                RxBus.getInstance().send(new DefaultEvent(Constants.EventCode.TOKEN_EXPIRE));
                            }

                        }

                        @Override
                        public void onNext(UserInfoResult result) {
                            mIsExecuteReq = false;
                            UserCache.cacheLoginUserInfo(result);
                        }
                    });
        }
    }


}

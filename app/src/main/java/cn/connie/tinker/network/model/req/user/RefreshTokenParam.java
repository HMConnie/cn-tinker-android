package cn.connie.tinker.network.model.req.user;

import cn.connie.tinker.network.model.base.BaseParam;

/**
 * Created by hinge on 2018/8/6.
 */

public class RefreshTokenParam extends BaseParam {
    public String refreshToken;

    public RefreshTokenParam(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}

package cn.connie.tinker.network.model.req.user;

import cn.connie.tinker.network.model.base.BaseParam;

/**
 * Created by hinge on 2018/8/6.
 */

public class QuickLoginParam extends BaseParam {
    public String mobile;
    public String code;

    public QuickLoginParam(String mobile, String code) {
        this.mobile = mobile;
        this.code = code;
    }
}

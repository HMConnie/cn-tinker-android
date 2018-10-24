package cn.connie.tinker.network.model.req.user;

import cn.connie.tinker.network.model.base.BaseParam;

/**
 * Created by hinge on 2018/8/24.
 */

public class InMailTypeParam extends BaseParam {
    public String type;

    public InMailTypeParam() {
        this.type = InMailType.SYSTEM.ordinal() + "";
    }

}

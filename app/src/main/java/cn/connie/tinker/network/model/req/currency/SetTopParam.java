package cn.connie.tinker.network.model.req.currency;

import cn.connie.tinker.network.model.base.BaseParam;

/**
 * Created by hinge on 2018/8/28.
 */

public class SetTopParam extends BaseParam {
    public String id;
    public int selfSelectType;

    public SetTopParam(String id, int selfSelectType) {
        this.id = id;
        this.selfSelectType = selfSelectType;
    }
}

package cn.connie.tinker.network.model.req.currency;

import cn.connie.tinker.network.model.base.BaseParam;

/**
 * Created by hinge on 2018/8/28.
 */

public class SelfSelectionSelectParam extends BaseParam {

    public int sortRule;
    public int sortType;
    public int selfSelectType;

    public SelfSelectionSelectParam(int sortRule, int sortType, int selfSelectType) {
        this.sortRule = sortRule;
        this.sortType = sortType;
        this.selfSelectType = selfSelectType;
    }
}

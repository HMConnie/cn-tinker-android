package cn.connie.tinker.network.model.req.currency;

import cn.connie.tinker.network.model.base.BaseParam;

/**
 * Created by hinge on 2018/8/29.
 */

public class SearchCurrencyOrMoreParam extends BaseParam {
    public int pageNo;
    public int pageSize;
    public int sortType;
    public int sortRule;
    public int selfSelectType;
    public String content;

    public SearchCurrencyOrMoreParam(int pageNo, int pageSize, int sortType, int sortRule, String selfSelectType, String content) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.sortType = sortType;
        this.sortRule = sortRule;
        this.selfSelectType = Integer.valueOf(selfSelectType);
        this.content = content;
    }
}

package cn.connie.tinker.network.model.req.currency;

import cn.connie.tinker.network.model.base.BaseParam;

/**
 * Created by hinge on 2018/8/27.
 */

public class CurrencyQuotationMarketParam extends BaseParam {

    public int pageSize;
    public int pageNo;
    public int sortType;
    public int sortRule;

    public CurrencyQuotationMarketParam(int pageSize, int pageNo, int sortType, int sortRule) {
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.sortType = sortType;
        this.sortRule = sortRule;
    }
}

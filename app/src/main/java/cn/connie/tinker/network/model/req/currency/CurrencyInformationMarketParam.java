package cn.connie.tinker.network.model.req.currency;

import cn.connie.tinker.network.model.base.BaseParam;

/**
 * Created by hinge on 2018/8/28.
 */

public class CurrencyInformationMarketParam extends BaseParam {

    public int pageNo;
    public int pageSize;
    public int sortType;
    public int sortRule;
    public String buyCurrency;
    public String sellCurrency;

    public CurrencyInformationMarketParam(int pageNo, int pageSize, int sortType, int sortRule, String buyCurrency, String sellCurrency) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.sortType = sortType;
        this.sortRule = sortRule;
        this.buyCurrency = buyCurrency;
        this.sellCurrency = sellCurrency;
    }
}

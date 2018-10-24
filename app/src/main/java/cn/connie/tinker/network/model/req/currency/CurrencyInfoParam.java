package cn.connie.tinker.network.model.req.currency;

import cn.connie.tinker.network.model.base.BaseParam;

/**
 * Created by hinge on 2018/8/28.
 */

public class CurrencyInfoParam extends BaseParam {

    public String sellCurrency;

    public CurrencyInfoParam(String sellCurrency) {
        this.sellCurrency = sellCurrency;
    }
}

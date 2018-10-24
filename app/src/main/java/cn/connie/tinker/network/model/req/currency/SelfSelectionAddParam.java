package cn.connie.tinker.network.model.req.currency;

import cn.connie.tinker.network.model.base.BaseParam;

/**
 * Created by hinge on 2018/8/28.
 */

public class SelfSelectionAddParam extends BaseParam {
    public int selfSelectType;//类型: 0:货币 1:交易对
    public String currencyOrTransactionPair;//货币/交易对的id

    public SelfSelectionAddParam(int selfSelectType, String currencyOrTransactionPair) {
        this.selfSelectType = selfSelectType;
        this.currencyOrTransactionPair = currencyOrTransactionPair;
    }
}

package cn.connie.tinker.network.model.req.currency;

import cn.connie.tinker.network.model.base.BaseParam;

/**
 * Created by hinge on 2018/8/27.
 */

public class CurrencyNavigationBarParam extends BaseParam {
    public int pageSize;
    public int pageNo;

    public CurrencyNavigationBarParam(int pageSize, int pageNo) {
        this.pageSize = pageSize;
        this.pageNo = pageNo;
    }
}

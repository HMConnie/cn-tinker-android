package cn.connie.tinker.network.model.req.user;

import cn.connie.tinker.network.model.base.BaseParam;

/**
 * Created by hinge on 2018/8/24.
 */

public class InMailParam extends BaseParam {
    public int pageNo;
    public int pageSize;
    public String type;


    public InMailParam(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.type = InMailType.SYSTEM.ordinal() + "";
    }
}

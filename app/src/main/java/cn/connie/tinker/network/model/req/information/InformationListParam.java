package cn.connie.tinker.network.model.req.information;


import cn.connie.tinker.network.model.base.BaseParam;

/**
 * Created by hinge on 2018/8/6.
 */

public class InformationListParam extends BaseParam {
    public int pageNo;
    public int pageSize;
    public String type;

    public InformationListParam(int pageNo, int pageSize, String type) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.type = type;
    }
}

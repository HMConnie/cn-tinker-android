package cn.connie.tinker.network.model.req.information;

import cn.connie.tinker.network.model.base.BaseParam;

/**
 * Created by hinge on 2018/8/6.
 */

public class InformationSearchParam extends BaseParam {
    public int pageNo;
    public int pageSize;
    public String key;
    public String type;

    public InformationSearchParam(int pageNo, int pageSize, String key, String type) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.key = key;
        this.type = type;
    }
}

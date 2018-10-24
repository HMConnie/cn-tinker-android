package cn.connie.tinker.network.model.req.information;


import cn.connie.tinker.network.model.base.BaseParam;

/**
 * Created by hinge on 2018/8/22.
 */

public class InformationDetailParam extends BaseParam {
    public String informationId;

    public InformationDetailParam(String informationId) {
        this.informationId = informationId;
    }
}

package cn.connie.tinker.network.model.req.user;

import cn.connie.tinker.network.model.base.BaseParam;

/**
 * Created by hinge on 2018/8/22.
 */

public class ModifyInfoParam extends BaseParam {
    public String headPortrait;
    public String nickName;

    public ModifyInfoParam(String headPortrait, String nickName) {
        this.headPortrait = headPortrait;
        this.nickName = nickName;
    }
}

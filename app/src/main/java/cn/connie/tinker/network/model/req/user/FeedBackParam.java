package cn.connie.tinker.network.model.req.user;

import cn.connie.tinker.network.model.base.BaseParam;

/**
 * Created by hinge on 2018/8/24.
 */

public class FeedBackParam extends BaseParam {
    public String content;

    public FeedBackParam(String content) {
        this.content = content;
    }
}

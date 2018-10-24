package cn.connie.tinker.network.model.req.user;

import cn.connie.tinker.network.model.base.BaseParam;

/**
 * Created by Q on 2017/11/9.
 * 发送短信接口参数
 * mobile：手机号
 * type：类型
 * LOGIN 登录
 * REGISTER 注册
 * MODIFY_PASSWORD 修改登录密码
 * BIND_QQ 绑定QQ
 */

public class SendSmsParam extends BaseParam {
    public String mobile;
    public String type;

    public SendSmsParam(String mobile, String type) {
        this.mobile = mobile;
        this.type = type;
    }
}

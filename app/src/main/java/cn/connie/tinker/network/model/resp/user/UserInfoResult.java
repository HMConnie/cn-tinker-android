package cn.connie.tinker.network.model.resp.user;

import java.io.Serializable;

/**
 * Created by Q on 2017/11/9.
 * 获取用户信息接口返回值
 */

public class UserInfoResult implements Serializable {

    /**
     * data : {"remark":"ee33f0761b79420f8b412e0d79ec32aa","headPortrait":"http://testcdn.dui1dui.com/benben/V1/images/default_head.jpg","mobile":"18600444789","nickName":"木凸祭","sex":"MALE","birthday":"2017-12-01","qqNumber":"122046728","state":"NORMAL","password":"25f9e794323b453885f5181f1b624d0b","certificationState":"UN_CERTIFICATION"}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * remark : ee33f0761b79420f8b412e0d79ec32aa
         * headPortrait : http://testcdn.dui1dui.com/benben/V1/images/default_head.jpg
         * mobile : 18600444789
         * nickName : 木凸祭
         * sex : MALE
         * birthday : 2017-12-01
         * qqNumber : 122046728
         * state : NORMAL
         * password : 25f9e794323b453885f5181f1b624d0b
         * certificationState : UN_CERTIFICATION
         */

        public String id;
        public String headPortrait;
        public String mobile;
        public String nickName;
        public String sex;
        public String birthday;
        public String qqNumber;
        public String state;
        public String password;
        public int collectionCount;
        public String certificationState;
    }
}

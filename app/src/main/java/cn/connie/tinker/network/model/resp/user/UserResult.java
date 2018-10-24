package cn.connie.tinker.network.model.resp.user;

import java.io.Serializable;

/**
 * Created by Q on 2017/11/9.
 * 注册（保存用户信息）接口返回值
 */

public class UserResult implements Serializable {

    /**
     * data : {"expiredIn":604800,"accessToken":"31ea601130434c57bb9b3e9c3ad69dbd","userId":"426bde1d07a84946936cf6d43d478bc8","refreshToken":"757c8c8b0e0e4e3e876a2ee2c97d7875"}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * expiredIn : 604800
         * accessToken : 31ea601130434c57bb9b3e9c3ad69dbd
         * userId : 426bde1d07a84946936cf6d43d478bc8
         * refreshToken : 757c8c8b0e0e4e3e876a2ee2c97d7875
         * password:25f9e794323b453885f5181f1b624d0b
         */

        public int expiredIn;
        public String accessToken;
        public String userId;
        public String refreshToken;
        public String password;
    }
}

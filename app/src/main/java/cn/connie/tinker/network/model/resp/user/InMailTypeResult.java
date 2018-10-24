package cn.connie.tinker.network.model.resp.user;

import java.util.List;

/**
 * Created by hinge on 2018/8/24.
 */

public class InMailTypeResult {

    public List<DataBean> data;


    public static class DataBean {

        public String id;
        public String receiverUserId;
        public String sendUserId;
        public String title;
        public String content;
        public String sendTime;
        public String readTime;
        public String state;
        public String type;
        public int unReadCount;
    }
}

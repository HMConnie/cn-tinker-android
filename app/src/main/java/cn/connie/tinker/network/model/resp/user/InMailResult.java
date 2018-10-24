package cn.connie.tinker.network.model.resp.user;

import java.util.List;

/**
 * Created by hinge on 2018/8/24.
 */

public class InMailResult {


    /**
     * data : {"recordCnt":3,"pageNo":1,"pageSize":10,"list":[{"id":"111","receiverUserId":"sddvc","sendUserId":"sddvc","content":7,"content":"sdfwsfvsfsdsdcfv","sendTime":"2017-10-27 18:53:04","readTime":"","state":0,"type":0}]}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * recordCnt : 3
         * pageNo : 1
         * pageSize : 10
         * list : [{"id":"111","receiverUserId":"sddvc","sendUserId":"sddvc","content":7,"content":"sdfwsfvsfsdsdcfv","sendTime":"2017-10-27 18:53:04","readTime":"","state":0,"type":0}]
         */

        public int recordCnt;
        public int pageNo;
        public int pageSize;
        public List<ListBean> list;

        public static class ListBean {
            /**
             * id : 111
             * receiverUserId : sddvc
             * sendUserId : sddvc
             * content : 7
             * content : sdfwsfvsfsdsdcfv
             * sendTime : 2017-10-27 18:53:04
             * readTime :
             * state : 0
             * type : 0
             */

            public String id;
            public String receiverUserId;
            public String sendUserId;
            public String title;
            public String content;
            public String sendTime;
            public String readTime;
            public String state;
            public String type;
        }
    }
}

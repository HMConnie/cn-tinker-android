package cn.connie.tinker.network.model.resp.information;

import java.util.List;

/**
 * Created by hinge on 2018/7/28.
 */

public class InformationListResult {


    public DataBean data;

    public static class DataBean {
        /**
         * recordCnt : 11
         * pageNo : 1
         * pageSize : 10
         */

        public int recordCnt;
        public int pageNo;
        public int pageSize;
        public List<ListBean> list;

        public static class ListBean {
            /**
             * browseCount : 0
             * collectionCount : 0
             * content :""
             * createTime : 2017-12-08 17:16:43
             * createUser : 测试作者
             * remark : 3
             * image : http://testcdn.dui1dui.com/benben/images/7b92614c22ec5852000be7605d7c6c51.JPEG
             * informationType : NEWS
             * source : 测试标签
             * state : NORMAL
             * title : 测试标题
             */

            public int browseCount;
            public int collectionCount;
            public String content;
            public String createTime;
            public String createUser;
            public String id;
            public String image;
            public String informationType;
            public String source;
            public String state;
            public String title;
            public boolean collection;//是否收藏
            public boolean isExpand;
            public String time;
        }
    }
}

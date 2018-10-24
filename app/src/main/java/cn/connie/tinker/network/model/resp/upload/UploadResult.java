package cn.connie.tinker.network.model.resp.upload;

/**
 * Created by hinge on 17/11/7.
 */

public class UploadResult {


    /**
     * data : {"remark":1223323232123,"createTime":"2017-01-01 15:20:30","url":"http://static.dui1dui.com/1705/0e5j32ys4gp.jpg"}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * remark : 1223323232123
         * createTime : 2017-01-01 15:20:30
         * url : http://static.dui1dui.com/1705/0e5j32ys4gp.jpg
         */

        public String id;
        public String createTime;
        public String url;
    }
}

package cn.connie.tinker.network.model.resp.appupdate;

/**
 * Created by hinge on 17/11/7.
 */

public class AppUpdateResult {


    /**
     * data : {"content":"123aaabbb ","url":"http://","versionName":"v1.0.0","versionCode":"1"}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * /**
         * content : 123aaabbb
         * url : http://
         * versionName : v1.0.0
         * versionCode : 1
         */

        public String content;
        public String url;
        public String versionName;
        public String versionCode;
        public boolean forceUpdate;//是否强行更新
    }
}

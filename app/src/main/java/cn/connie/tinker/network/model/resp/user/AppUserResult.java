package cn.connie.tinker.network.model.resp.user;

/**
 * Created by hinge on 2018/8/12.
 */

public class AppUserResult {


    /**
     * data : {"appUser":{"userAlreadyCashProfit":0,"userTotalAssets":202.68,"userUnpaidProfit":2.56}}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * appUser : {"userAlreadyCashProfit":0,"userTotalAssets":202.68,"userUnpaidProfit":2.56}
         */

        public AppUserBean appUser;

        public static class AppUserBean {
            /**
             * userAlreadyCashProfit : 0
             * userTotalAssets : 202.68
             * userUnpaidProfit : 2.56
             */

            public double userAlreadyCashProfit;
            public double userTotalAssets;
            public double userUnpaidProfit;
        }
    }
}

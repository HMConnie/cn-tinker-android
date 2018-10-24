package cn.connie.tinker.network.model.resp.currency;

import java.util.List;

/**
 * Created by hinge on 2018/8/27.
 */

public class CurrencyNavigationBarResult {


    /**
     * data : {"list":[{"circulation":886211988,"circulationMarketValue":56451256,"cnName":"艾特币","enName":"e-dinar-coin","enShortName":"EDR","transactionPairId":"3cd23de7580b4da58428428b914de525","marketValue":999999999999999,"state":0},{"circulation":311733766968808,"circulationMarketValue":5382717,"cnName":"","enName":"808coin","enShortName":"808","transactionPairId":"96f42914e1bf4e5f8e9262e65d56eb5a","marketValue":311733766968808,"state":0}],"pageCnt":1290,"pageSize":2,"recordCnt":2579}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * list : [{"circulation":886211988,"circulationMarketValue":56451256,"cnName":"艾特币","enName":"e-dinar-coin","enShortName":"EDR","transactionPairId":"3cd23de7580b4da58428428b914de525","marketValue":999999999999999,"state":0},{"circulation":311733766968808,"circulationMarketValue":5382717,"cnName":"","enName":"808coin","enShortName":"808","transactionPairId":"96f42914e1bf4e5f8e9262e65d56eb5a","marketValue":311733766968808,"state":0}]
         * pageCnt : 1290
         * pageSize : 2
         * recordCnt : 2579
         */

        public int pageCnt;
        public int pageSize;
        public int recordCnt;
        public List<ListBean> list;

        public static class ListBean {
            /**
             * circulation : 886211988
             * circulationMarketValue : 56451256
             * cnName : 艾特币
             * enName : e-dinar-coin
             * enShortName : EDR
             * transactionPairId : 3cd23de7580b4da58428428b914de525
             * marketValue : 999999999999999
             * state : 0
             */

            public String circulation;
            public String circulationMarketValue;
            public String cnName;
            public String enName;
            public String enShortName;
            public String id;
            public String marketValue;
            public int state;
        }
    }
}

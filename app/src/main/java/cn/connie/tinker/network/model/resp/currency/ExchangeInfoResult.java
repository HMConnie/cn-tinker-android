package cn.connie.tinker.network.model.resp.currency;

import java.util.List;

/**
 * Created by hinge on 2018/8/29.
 */

public class ExchangeInfoResult {


    /**
     * data : {"list":[{"cnName":"测试交易所中文名1","currencyUnit":"EDR","enName":"测试交易所英文名1","priceChangePercent":"+2.35","rMB":200,"sellCurrencyEnName":"EDR","transactionPairMoney":0.235,"transactionPairType":1,"volume":669}],"pageCnt":1,"pageSize":5,"recordCnt":1}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * list : [{"cnName":"测试交易所中文名1","currencyUnit":"EDR","enName":"测试交易所英文名1","priceChangePercent":"+2.35","rMB":200,"sellCurrencyEnName":"EDR","transactionPairMoney":0.235,"transactionPairType":1,"volume":669}]
         * pageCnt : 1
         * pageSize : 5
         * recordCnt : 1
         */

        public int pageCnt;
        public int pageSize;
        public int recordCnt;
        public List<ListBean> list;

        public static class ListBean {
            /**
             * cnName : 测试交易所中文名1
             * currencyUnit : EDR
             * enName : 测试交易所英文名1
             * priceChangePercent : +2.35
             * rMB : 200
             * sellCurrencyEnName : EDR
             * transactionPairMoney : 0.235
             * transactionPairType : 1
             * volume : 669
             */

            public String cnName;
            public String currencyUnit;
            public String enName;
            public String priceChangePercent;
            public String rMB;
            public String sellCurrencyEnName;
            public String transactionPairMoney;
            public int transactionPairType;
            public String volume;
        }
    }
}

package cn.connie.tinker.network.model.resp.currency;

import java.util.List;

/**
 * Created by hinge on 2018/8/27.
 */

public class CurrencyQuotationMarketResult {


    /**
     * data : {"list":[{"cnName":"","dollar":5636789.12,"enShortName":"EDR","marketValue":1500000000,"marketValueDollar":10222200000,"priceChangePercent":"-2.39","rMB":5.55999888436E9}],"pageCnt":1,"pageSize":5,"recordCnt":2}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * list : [{"cnName":"","dollar":5636789.12,"enShortName":"EDR","marketValue":1500000000,"marketValueDollar":10222200000,"priceChangePercent":"-2.39","rMB":5.55999888436E9}]
         * pageCnt : 1
         * pageSize : 5
         * recordCnt : 2
         */

        public int pageCnt;
        public int pageSize;
        public int recordCnt;
        public List<ListBean> list;

        public static class ListBean {
            /**
             * cnName :
             * dollar : 5636789.12
             * enShortName : EDR
             * marketValue : 1500000000
             * marketValueDollar : 10222200000
             * priceChangePercent : -2.39
             * rMB : 5.55999888436E9
             */

            public String cnName;
            public String dollar;
            public String enShortName;
            public String marketValue;
            public String marketValueDollar;
            public String priceChangePercent;
            public String rMB;
            public String currencyId;
            public String currencyEnName;
        }
    }
}

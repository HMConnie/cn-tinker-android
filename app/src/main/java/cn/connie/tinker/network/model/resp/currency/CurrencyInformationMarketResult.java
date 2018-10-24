package cn.connie.tinker.network.model.resp.currency;

import java.util.List;

/**
 * Created by hinge on 2018/8/28.
 */

public class CurrencyInformationMarketResult {


    /**
     * data : {"list":[{"b":false,"buyCurrencyEnName":"808 ","buyCurrencyId":"96f42914e1bf4e5f8e9262e65d56eb5a","cnName":"测试交易所中文名1","enName":"测试交易所英文名1","priceChangePercent":"+2.35","rmb":200,"sellCurrencyEnName":"EDR","sellCurrencyId":"3cd23de7580b4da58428428b914de525","transactionPairMoney":0.235}],"pageCnt":1,"pageSize":5,"recordCnt":1}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * list : [{"b":false,"buyCurrencyEnName":"808 ","buyCurrencyId":"96f42914e1bf4e5f8e9262e65d56eb5a","cnName":"测试交易所中文名1","enName":"测试交易所英文名1","priceChangePercent":"+2.35","rmb":200,"sellCurrencyEnName":"EDR","sellCurrencyId":"3cd23de7580b4da58428428b914de525","transactionPairMoney":0.235}]
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
             * b : false
             * buyCurrencyEnName : 808
             * buyCurrencyId : 96f42914e1bf4e5f8e9262e65d56eb5a
             * cnName : 测试交易所中文名1
             * enName : 测试交易所英文名1
             * priceChangePercent : +2.35
             * rmb : 200
             * sellCurrencyEnName : EDR
             * sellCurrencyId : 3cd23de7580b4da58428428b914de525
             * transactionPairMoney : 0.235
             */

            public boolean b;
            public String buyCurrencyEnName;
            public String buyCurrencyId;
            public String cnName;
            public String enName;
            public String currencyUnit;
            public String priceChangePercent;
            public String rmb;
            public String currencyId;
            public String sellCurrencyEnName;
            public String sellCurrencyId;
            public String transactionPairMoney;
        }
    }
}

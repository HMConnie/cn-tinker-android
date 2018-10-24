package cn.connie.tinker.network.model.resp.currency;

import java.util.List;

/**
 * Created by hinge on 2018/8/29.
 */

public class SearchCurrencyOrMoreResult {


    /**
     * data : {"list":[{"buyCurrencyEnName":"808 ","buyCurrencyId":"96f42914e1bf4e5f8e9262e65d56eb5a","currencyUnit":"EDR","enName":"测试交易所英文名1","exchangecnName":"测试交易所中文名1","priceChangePercent":"+2.35","rMB":200,"select":false,"selfSelectType":0,"sellCurrencyEnName":"EDR","sellCurrencyId":"3cd23de7580b4da58428428b914de525","transactionPairId":"1","transactionPairMoney":0.235,"selfSelectionId":0,"transactionPairType":1}],"pageCnt":1,"pageSize":5,"recordCnt":1}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * list : [{"buyCurrencyEnName":"808 ","buyCurrencyId":"96f42914e1bf4e5f8e9262e65d56eb5a","currencyUnit":"EDR","enName":"测试交易所英文名1","exchangecnName":"测试交易所中文名1","priceChangePercent":"+2.35","rMB":200,"select":false,"selfSelectType":0,"sellCurrencyEnName":"EDR","sellCurrencyId":"3cd23de7580b4da58428428b914de525","transactionPairId":"1","transactionPairMoney":0.235,"selfSelectionId":0,"transactionPairType":1}]
         * pageCnt : 1
         * pageSize : 5
         * recordCnt : 1
         */

        public int pageCnt;
        public int pageSize;
        public int recordCnt;
        public List<ListBean> list;

        public static class ListBean {
            public String cnName;
            public String currencyId;
            public String dollar;
            public String enShortName;
            public String currencyEnName;
            public String buyCurrencyEnName;
            public String buyCurrencyId;
            public String currencyUnit;
            public String enName;
            public String exchangecnName;
            public String priceChangePercent;
            public String rMB;
            public boolean select;
            public int selfSelectType;
            public String sellCurrencyEnName;
            public String sellCurrencyId;
            public String transactionPairId;
            public String transactionPairMoney;
            public String selfSelectionId;
            public int transactionPairType;

        }
    }
}

package cn.connie.tinker.network.model.resp.currency;

import java.math.BigDecimal;

/**
 * Created by hinge on 2018/8/28.
 */

public class CurrencyInfoResult {


    /**
     * data : {"circulation":886211988,"circulationMarketValue":56451256,"cnName":"艾特币","currencyDesc":"这是测试描述记得删除","dollar":5636789.12,"enName":"e-dinar-coin","enShortName":"EDR","highPrice":100.12,"id":"3cd23de7580b4da58428428b914de525","issue":100,"lowPrice":200.16,"marketValue":999999999999999,"marketValueRanking":1,"officialWebsite":"http://www.baicu.com","priceChange":"2.57","priceChangePercent":"-2.39","releaseTime":"2018-08-28 20:11:12","state":0,"transactionProbability":2.3,"rmb":5.55999888436E9,"volume":2333}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * circulation : 886211988
         * circulationMarketValue : 56451256
         * cnName : 艾特币
         * currencyDesc : 这是测试描述记得删除
         * dollar : 5636789.12
         * enName : e-dinar-coin
         * enShortName : EDR
         * highPrice : 100.12
         * id : 3cd23de7580b4da58428428b914de525
         * issue : 100
         * lowPrice : 200.16
         * marketValue : 999999999999999
         * marketValueRanking : 1
         * officialWebsite : http://www.baicu.com
         * priceChange : 2.57
         * priceChangePercent : -2.39
         * releaseTime : 2018-08-28 20:11:12
         * state : 0
         * transactionProbability : 2.3
         * rmb : 5.55999888436E9
         * volume : 2333.0
         */

        public String circulation;
        public String circulationMarketValue;
        public String cnName;
        public String currencyDesc;
        public String dollar;
        public String enName;
        public String enShortName;
        public String highPrice;
        public String id;
        public String issue;
        public String lowPrice;
        public String marketValue;
        public int marketValueRanking;
        public String officialWebsite;
        public String priceChange;
        public String priceChangePercent;
        public String releaseTime;
        public int state;
        public String transactionProbability;
        public String rmb;
        public String volume;
        public boolean isb;
        public String exchangeNumber;
    }
}

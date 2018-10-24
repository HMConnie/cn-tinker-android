package cn.connie.tinker.network.model.resp.currency;

import java.util.List;

/**
 * Created by hinge on 2018/8/28.
 */

public class CurrencyTrendInfoResult {


    /**
     * data : {"volume":[{"number":100000,"recordTime":1535513739332,"recordType":1,"id":"123123123","currencyId":"11111","timeLatitude":1}],"topVolume":{"number":100000,"recordTime":1535513739332,"recordType":1,"id":"123123123","currencyId":"11111","timeLatitude":1},"topPrice":{"number":100000,"recordTime":1535513739332,"recordType":1,"id":"123123123","currencyId":"11111","timeLatitude":1},"lowPrice":{"number":100000,"recordTime":1535513739332,"recordType":1,"id":"123123123","currencyId":"11111","timeLatitude":1},"price":[{"number":100000,"recordTime":1535513739332,"recordType":1,"id":"123123123","currencyId":"11111","timeLatitude":1}],"lowVolume":{"number":100000,"recordTime":1535513739332,"recordType":1,"id":"123123123","currencyId":"11111","timeLatitude":1},"topTime":1535513742601,"lowTime":1535513742097}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * volume : [{"number":100000,"recordTime":1535513739332,"recordType":1,"id":"123123123","currencyId":"11111","timeLatitude":1}]
         * topVolume : {"number":100000,"recordTime":1535513739332,"recordType":1,"id":"123123123","currencyId":"11111","timeLatitude":1}
         * topPrice : {"number":100000,"recordTime":1535513739332,"recordType":1,"id":"123123123","currencyId":"11111","timeLatitude":1}
         * lowPrice : {"number":100000,"recordTime":1535513739332,"recordType":1,"id":"123123123","currencyId":"11111","timeLatitude":1}
         * price : [{"number":100000,"recordTime":1535513739332,"recordType":1,"id":"123123123","currencyId":"11111","timeLatitude":1}]
         * lowVolume : {"number":100000,"recordTime":1535513739332,"recordType":1,"id":"123123123","currencyId":"11111","timeLatitude":1}
         * topTime : 1535513742601
         * lowTime : 1535513742097
         */

        public float topVolume;
        public float topPrice;
        public float lowPrice;
        public float lowVolume;
        public long topTime;
        public long lowTime;
        public List<VolumeBean> volume;
        public List<PriceBean> price;


        public static class VolumeBean {
            /**
             * number : 100000
             * recordTime : 1535513739332
             * recordType : 1
             * id : 123123123
             * currencyId : 11111
             * timeLatitude : 1
             */

            public float number;
            public String recordTime;
            public long recordTimeStamp;
            public int recordType;
            public String id;
            public String currencyId;
            public int timeLatitude;
        }

        public static class PriceBean {
            /**
             * number : 100000
             * recordTime : 1535513739332
             * recordType : 1
             * id : 123123123
             * currencyId : 11111
             * timeLatitude : 1
             */

            public float number;
            public String recordTime;
            public long recordTimeStamp;
            public int recordType;
            public String id;
            public String currencyId;
            public int timeLatitude;
        }
    }
}

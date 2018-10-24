package cn.connie.tinker.network.model.resp.advertisement;

import java.util.List;

/**
 * Created by hinge on 2018/8/6.
 */

public class BannerResult {

    public List<DataBean> data;

    public static class DataBean {

        public String content;
        public String createTime;
        public String id;
        public String position;
        public String remark;
    }
}

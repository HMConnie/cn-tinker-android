package cn.connie.tinker.network.model.resp.information;

import java.util.List;

/**
 * Created by hinge on 2018/8/23.
 */

public class HotSearchResult {

    public List<DataBean> data;

    public static class DataBean {
        public String id;
        public String content;
    }
}

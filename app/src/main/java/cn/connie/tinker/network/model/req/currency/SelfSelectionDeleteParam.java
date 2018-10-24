package cn.connie.tinker.network.model.req.currency;

import cn.connie.tinker.network.model.base.BaseParam;

import java.util.List;

/**
 * Created by hinge on 2018/8/28.
 */

public class SelfSelectionDeleteParam extends BaseParam {

    public String ids;

    public SelfSelectionDeleteParam(List<String> iDs) {
        if (iDs.size() == 1) {
            ids = iDs.get(0);
        } else {
            for (int i = 0; i < iDs.size(); i++) {
                if (i == iDs.size() - 1) {
                    ids = ids + iDs.get(i);
                } else {
                    ids = ids + iDs.get(i) + ",";
                }
            }
        }
    }

    public SelfSelectionDeleteParam(String ids) {
        this.ids = ids;
    }
}

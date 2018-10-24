package cn.connie.tinker.network.model.base;

import android.text.TextUtils;

import com.sgcai.sign.Sign;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import cn.connie.tinker.AppContext;
import cn.connie.tinker.cache.UserCache;
import cn.connie.tinker.utils.AppUtil;
import cn.connie.tinker.utils.Constants;
import cn.connie.tinker.utils.GsonUtil;

/**
 * Created by hinge on 17/4/7.
 */

public class BaseParam implements Serializable {


    public BaseParam() {
    }

    /**
     * 传入可以为空的属性值
     *
     * @return
     */
    public Map<String, String> getBodyParams() {
        Map<String, String> params = new HashMap<>();
        try {
            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String name = field.getName();
                if (!TextUtils.isEmpty(name)) {
                    //过滤掉头信息属性
                    if (Constants.HEAD_INFOS.equals(name) || Constants.SERIALVERSION_UID.equals(name) || Constants.CHANGE.equals(name)) {
                        continue;
                    }
                    Object obj = field.get(this);
                    String value = obj != null ? String.valueOf(obj) : "";
                    params.put(name, value);
                }


            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return params;
    }

    /**
     * .header("merchantNo", "" + merchantNo)
     * .header("timestamp", "" + timeStamp)
     * .header("sign", sign)
     */
    public Map<String, String> getHeaders() {
        final Map<String, String> signMap = new HashMap<>();//sign加密所需的参数
        final Map<String, String> headers = new HashMap<>();//http请求所需的请求头

        long timeStamp = System.currentTimeMillis();
        String versionName = AppUtil.getVersionName(AppContext.getInstance());
        signMap.put("timestamp", timeStamp + ""); // 拼接参与sign时间戳
        signMap.put("channelId", AppUtil.getChannelId() + ""); // 拼接参与sign下载渠道ID

        headers.put("timestamp", timeStamp + ""); // 拼接时间戳头信息
        headers.put("channelId", AppUtil.getChannelId() + ""); // 分配给客户端的下载渠道ID
        headers.put("clientType", Constants.CLIENT_TYPE + ""); // 拼接客户端类型
        headers.put("appId", Constants.APPID); // 拼接APP_ID
        headers.put("ver", versionName); // app的版本名

        String token = UserCache.getToken();
        if (!TextUtils.isEmpty(token)) { // 拼接Token
            headers.put("Authorization", "common " + token);
        }

        Map<String, String> buildParams = getBodyParams();
        if (buildParams != null) {
            signMap.putAll(buildParams);//添加参数体，参与生成sign
        }

        String sign = Sign.getSign(GsonUtil.toJsonFromMap(signMap));//生成sign
        headers.put("sign", sign);//将sign拼接到请求头中

        return headers;
    }
}

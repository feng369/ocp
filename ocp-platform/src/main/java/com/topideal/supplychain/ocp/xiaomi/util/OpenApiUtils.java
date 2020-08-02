package com.topideal.supplychain.ocp.xiaomi.util;

import com.topideal.supplychain.ocp.xiaomi.dto.PartnerInfo;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class OpenApiUtils {

    private PartnerInfo partner;

    private final String urlTest = "http://openapi.test.youpin.mi.com";
    private final String urlOnline = "https://shopapi.io.mi.com";
/*
    private final String urlTest = "http://openapi.test.youpin.mi.com";
    private final String urlOnline = "https://shopapi.io.mi.com";
*/

    private final Map<String, String> apiDict = new HashMap<String, String>() {
        {
            put("orderstatus", "/openapi/shop/orderstatus");
            put("orderlist", "/openapi/shop/orderlist");
            put("proplist", "/openapi/shop/proplist");
        }
    };

    public void SetShop(PartnerInfo partner) {
        this.partner = partner;
    }

    public String api(String method, Map<String, String> data, String env) {
        if (!env.equals("online")) {
            env = "test";
        }

        try {
            String res = fetch(method, data, env);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> parseParam(Map<String, String> param) throws Exception {
        String dataValue = AesUtils.encrypt(partner.aesKey, urlEncoded(param));

        String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);

        Map<String, String> signParam = new TreeMap<>(new MapKeyComparator());
        signParam.put("data", dataValue);
        signParam.put("timestamp", timeStamp);
        signParam.put("partner_id", partner.partnerId);
        String sign = DigestUtils.md5Hex((urlEncoded(signParam) + partner.key).getBytes());

        signParam.put("sign", sign);
        return signParam;
    }

    private class MapKeyComparator implements Comparator<String> {

        @Override
        public int compare(String str1, String str2) {
            return str1.compareTo(str2);
        }
    }


    private String fetch(String method, Map<String, String> data, String env) throws Exception {

        Map<String, String> request;
        try {
            request = parseParam(data);
        } catch (Exception e) {
            throw new Exception(e.toString() + "参数解析错误");
        }

        String url = env.equals("online") ? urlOnline : urlTest;

        System.out.printf("\nurl: %s", url);
        System.out.printf("\nrequest: %s", request);
//        String content = HttpUtils.postUrlEncoded(url + apiDict.get(method), null, request);

//        return content;
        return null;

    }

    private String urlEncoded(Map<String, String> param) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : param.entrySet()) {
            builder.append("&");
            builder.append(entry.getKey());
            builder.append("=");
            builder.append(entry.getValue());
        }
        return builder.toString().substring(1);
    }

}
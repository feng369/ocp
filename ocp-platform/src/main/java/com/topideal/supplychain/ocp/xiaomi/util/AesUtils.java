package com.topideal.supplychain.ocp.xiaomi.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

// for JDK 1.7
//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;

/**
 * AES加解密工具
 * <p>
 * 密钥(密码)长度只能为16, 24, 32 位长度
 * <p>
 * 加密过程: 加密-Base64编码
 * 解密过程: Base64解码-解密
 */
public class AesUtils {

    // 加密模式
    private static final String TRANS_FORMATION = "AES/ECB/PKCS5Padding";
    // 加密算法
    private static final String ALGORITHM = "AES";
    // 字符编解码
    private static final String CHARSET_NAME = "UTF-8";

    // for JDK 1.7
//    private static final BASE64Decoder decoder = new BASE64Decoder();
//    private static final BASE64Encoder encoder = new BASE64Encoder();


    private AesUtils() {
    }


    /**
     * AES 加密
     *
     * @param value 原始字符串
     * @return base64编码后的加密字符串
     * @throws Exception 编码异常
     */
    public static String encrypt(String password, String value) throws Exception {
        SecretKeySpec aesKey = new SecretKeySpec(password.getBytes(Charset.forName(CHARSET_NAME)), ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANS_FORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
        byte[] encrypted = cipher.doFinal(value.getBytes(Charset.forName(CHARSET_NAME)));

        // for JDK 1.7
        // return encoder.encode(encrypted);

        return Base64.getEncoder().encodeToString(encrypted);

    }

    /**
     * AES 解密
     *
     * @param value base64编码后的加密字符串
     * @return base64解码后的原始字符串
     * @throws Exception 解码异常
     */
    public static String decrypt(String password, String value) throws Exception {
        byte[] decode = Base64.getDecoder().decode(value.getBytes(StandardCharsets.UTF_8));

        // for JDK 1.7
        // byte[] decode = decoder.decodeBuffer(value);

        SecretKeySpec aesKey = new SecretKeySpec(password.getBytes(Charset.forName(CHARSET_NAME)), ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANS_FORMATION);
        cipher.init(Cipher.DECRYPT_MODE, aesKey);
        return new String(cipher.doFinal(decode));
    }

    public static void main(String[] args) {
        String partner_id= "616";
        String aeskey= "92e7b21bee1d3589ec01dfc72f1b6a64" ;
        String  key= "547980b69180299bba56f1de477d9873";

        String testStr = "{\"orderId\":\"4191005964004759\",\"expressSn\":104534,\"expressName\":\"中通快递\",\"bizCode\":\"zhongtong\",\"userId\":123456,\"itemList\":[{\"skuId\":3000054,\"count\":2,\"pid\":19558}],\"partnerId\":65}";
//        AesUtils.encrypt(aeskey,urlEncoded())
    }

    private static String urlEncoded(Map<String, String> param) {
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

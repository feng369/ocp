package com.topideal.supplychain.ocp.vip.dto;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.ContentType;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;

public class Configure {

    /**
     * 请求响应格式
     */
    private String format;
    /**
     * AppKey
     */
    private String appKey;
    /**
     * 服务名
     */
    private String service;
    /**
     * 方法名称
     */
    private String method;
    /**
     * 时间戳（误差不能相差十分钟）
     */
    private String timestamp;
    /**
     * 版本号
     */
    private String version;
    /**
     * AppSecrect
     */
    private String appSecrect;
    /**
     * 请求URL
     */
    private String url;
    /**
     * 业务参数
     */
    private String businessData;

    public static Configure build(){
        return new Configure();
    }

    public String getFormat() {
        return format;
    }

    public Configure setFormat(String format) {
        this.format = format;
        return this;
    }

    public String getAppKey() {
        return appKey;
    }

    public Configure setAppKey(String appKey) {
        this.appKey = appKey;
        return this;
    }


    public String getService() {
        return service;
    }

    public Configure setService(String service) {
        this.service = service;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public Configure setMethod(String method) {
        this.method = method;
        return this;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public Configure setTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public Configure setVersion(String version) {
        this.version = version;
        return this;
    }

    public String getAppSecrect() {
        return appSecrect;
    }

    public Configure setAppSecrect(String appSecrect) {
        this.appSecrect = appSecrect;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Configure setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getBusinessData() {
        return businessData;
    }

    public Configure setBusinessData(String businessData) {
        this.businessData = businessData;
        return this;
    }

    public static class Constant{
        public static final String FORMAT = "format";

        public static final String APP_KEY = "appKey";

        public static final String SERVICE_NAME = "service";

        public static final String METHOD_NAME = "method";

        public static final String TIMESTAMP = "timestamp";

        public static final String VERSION = "version";
    }

    public static class VipUtil {
        public final static String AJAX_HEADER = "x-requested-with";
        public final static String XMLHTTPREQUEST = "XMLHttpRequest";
        private static String CHARACTER_CODING = "UTF-8";
        private static String CONTENT_TYPE_XML = "application/xml";
        private static String CONTENT_TYPE_JSON = "application/json";
        public static final String CHARSET_UTF8 = "UTF-8";
        public static final String KEY_MAC = "HmacMD5";
        private static final String HEXSTR = "0123456789ABCDEF";



        /**
         * hmac-md5签名
         *
         * @param data
         * @param secret
         * @return
         * @throws IOException
         * @throws NoSuchAlgorithmException
         * @throws InvalidKeyException
         */
        public static byte[] encryptHMAC(String data, String secret)
                throws IOException, NoSuchAlgorithmException, InvalidKeyException {
            byte[] bytes = null;
            SecretKey secretKey = new SecretKeySpec(secret.getBytes(CHARSET_UTF8),
                    KEY_MAC);
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            bytes = mac.doFinal(data.getBytes(CHARSET_UTF8));
            return bytes;
        }

        /**
         * 将byte转化为十六进制字符串
         *
         * @param bytes
         * @return
         */
        public static String byte2hex(byte[] bytes) {
            StringBuilder sign = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sign.append(String.valueOf(HEXSTR.charAt((bytes[i] & 0xF0) >> 4))); // 字节高4位
                sign.append(String.valueOf(HEXSTR.charAt(bytes[i] & 0x0F))); // 字节低4位
            }
            return sign.toString().toUpperCase();
        }

        /**
         * 将Map转化为排序后的组字符串
         *
         * @param params
         * @return
         */
        public static String convertToSortStr(Map<String, String> params) {
            if (params == null || params.isEmpty()) {
                return null;
            }

            String[] keys = params.keySet().toArray(new String[0]);
            Arrays.sort(keys);

            StringBuilder query = new StringBuilder();

            for (String key : keys) {
                String value = params.get(key);
                if (isNotEmpty(key, value)) {
                    query.append(key).append(value);
                }
            }

            return query.toString();
        }

        public static boolean isNotEmpty(String... values) {
            boolean result = true;
            if (values == null || values.length == 0) {
                result = false;
            } else {
                for (String value : values) {
                    result &= !StringUtils.isEmpty(value);
                }
            }
            return result;
        }

        public static String getQueryString(Map<String, String> params) throws UnsupportedEncodingException {
            StringBuilder query = new StringBuilder();
            boolean hasParam = false;
            String[] keys = params.keySet().toArray(new String[0]);
            Arrays.sort(keys);
            for (String key : keys) {
                String value = params.get(key);
                if (isNotEmpty(key, value)) {
                    if (hasParam) {
                        query.append("&");
                    } else {
                        hasParam = true;
                    }
                    query.append(key).append("=").append(URLEncoder.encode(value, CHARACTER_CODING));
                }
            }

            return query.toString();
        }

        /**
         * 不能用BufferedReader的ReadLine和read来读，因为不方便处理换行和编码处理。只能一个一个读，才能保证正确性。
         *
         * @param is
         * @return
         */
        public static String stream2Str(InputStream is) throws Exception {
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int i = -1;
                while ((i = is.read()) != -1) {
                    baos.write(i);
                }
                is.close();
                return baos.toString();
            } catch (Exception e) {
                throw e;
            }
        }

        public static ContentType getContentType(String format) {
            ContentType contentType = ContentType.create(CONTENT_TYPE_XML, CHARACTER_CODING);
            if ("JSON".equalsIgnoreCase(format)) {
                contentType = ContentType.create(CONTENT_TYPE_JSON, CHARACTER_CODING);
            }
            return contentType;
        }

        /**
         * 解密方法
         */
        public static String decodeStr(String decodeStr) {

            Base64 base64 = new Base64();
            try {
                return new String(base64.decodeBase64(decodeStr),"UTF-8");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        /**
         * 加密方法
         */
        public static String encodeStr(String encodeStr) {
            Base64 base64 = new Base64();
            byte[] b = encodeStr.getBytes();
            b = base64.encode(b);
            String s = new String(b);
            return s;
        }
    }


}

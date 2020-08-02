package com.topideal.supplychain.ocp.hipac.dto;

/**
 * 海拍客平台参数配置
 *
 * @author xuxiaoyan
 * @date 2019-12-17 16:46
 */
public class HipacArgsDto {

    //调用者身份
    private String appKey;

    //密钥
    private String secret;

    private String token;

    //调用格式
    private String format;

    //版本
    private String version;

    /**
     * 加签方法
     */
    private String signMethod;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSignMethod() {
        return signMethod;
    }

    public void setSignMethod(String signMethod) {
        this.signMethod = signMethod;
    }
}

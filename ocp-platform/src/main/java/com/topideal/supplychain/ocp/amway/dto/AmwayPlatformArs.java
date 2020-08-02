package com.topideal.supplychain.ocp.amway.dto;

/**
 * 安利接单配置-平台参数
 *
 * @author xuxiaoyan
 * @date 2020-07-08 13:55
 */
public class AmwayPlatformArs {

    //调用者身份
    private String appKey;

    //密钥
    private String secret;

    private String token;


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

}

package com.topideal.supplychain.ocp.jd.dto;

import java.io.Serializable;

/**
 * @ClassName JdConfig
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2020/3/5 17:38
 * @Version 1.0
 **/
public class JdConfig implements Serializable {

    private String appSecret;
    private String appKey;
    private String accessToken;
    private String customsId;
    private String serviceId;

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getCustomsId() {
        return customsId;
    }

    public void setCustomsId(String customsId) {
        this.customsId = customsId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }
}

package com.topideal.supplychain.ocp.pdd.model;

/**
 * @author klw
 * @date 2019/12/12 13:59
 * @description: 店铺扩展参数实体
 */
public class PddStoreArg {

    private String clientId;

    private String clientSecret;

    private String accessToken;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}

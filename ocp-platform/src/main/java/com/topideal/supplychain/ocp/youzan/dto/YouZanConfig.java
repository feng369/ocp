package com.topideal.supplychain.ocp.youzan.dto;

import com.topideal.supplychain.ocp.enums.BusiModeEnum;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 有赞的配置
 */
public class YouZanConfig implements Serializable {

    //过期时间
    private Date expiratioTime;
    //client_id
    private String clientId;
    //secret
    private String secret;
    //token
    private String token;
    private String accessToken;

    //关区和业务类型
    private List<CustomBusiness> customBusinessList;

    private String key;

    public static class CustomBusiness{
        //关区
        private String customsCode;
        //检区
        private String ciqCode;
        //业务类型编码
        private String businessMode;

        public String getCustomsCode() {
            return customsCode;
        }

        public void setCustomsCode(String customsCode) {
            this.customsCode = customsCode;
        }

        public String getCiqCode() {
            return ciqCode;
        }

        public void setCiqCode(String ciqCode) {
            this.ciqCode = ciqCode;
        }

        public String getBusinessMode() {
            return businessMode;
        }

        public void setBusinessMode(String businessMode) {
            this.businessMode = businessMode;
        }
    }

    public List<CustomBusiness> getCustomBusinessList() {
        return customBusinessList;
    }

    public void setCustomBusinessList(
            List<CustomBusiness> customBusinessList) {
        this.customBusinessList = customBusinessList;
    }

    public Date getExpiratioTime() {
        return expiratioTime;
    }

    public void setExpiratioTime(Date expiratioTime) {
        this.expiratioTime = expiratioTime;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}

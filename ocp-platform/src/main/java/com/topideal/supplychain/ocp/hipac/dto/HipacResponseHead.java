package com.topideal.supplychain.ocp.hipac.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author xuxiaoyan
 * @date 2019-12-18 18:35
 */
@XmlRootElement(name = "Head")
public class HipacResponseHead {

    private String version;

    private String service;

    private String sendID;

    private String appKey;

    private String retCode;

    private String sign;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getSendID() {
        return sendID;
    }

    public void setSendID(String sendID) {
        this.sendID = sendID;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
}

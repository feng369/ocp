package com.topideal.supplychain.ocp.amway.dto;

public class AmwayRequestArgsVo {
    //安利接口
    private String appSecret;
    //安利接口平台ebpCode
    private String ebpCode;
    //安利接口企业ebcCode
    private String ebcCode;
    //安利接口报文对象名
    private String amwayMessageName;
    //安利--卓志报文对象名
    private String amwayzzMessageName;
    //安利-配送商 AMWAY_DISPATCHPROVIDER
    private String amwayDispatchProvider;

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getEbpCode() {
        return ebpCode;
    }

    public void setEbpCode(String ebpCode) {
        this.ebpCode = ebpCode;
    }

    public String getEbcCode() {
        return ebcCode;
    }

    public void setEbcCode(String ebcCode) {
        this.ebcCode = ebcCode;
    }

    public String getAmwayMessageName() {
        return amwayMessageName;
    }

    public void setAmwayMessageName(String amwayMessageName) {
        this.amwayMessageName = amwayMessageName;
    }

    public String getAmwayzzMessageName() {
        return amwayzzMessageName;
    }

    public void setAmwayzzMessageName(String amwayzzMessageName) {
        this.amwayzzMessageName = amwayzzMessageName;
    }

    public String getAmwayDispatchProvider() {
        return amwayDispatchProvider;
    }

    public void setAmwayDispatchProvider(String amwayDispatchProvider) {
        this.amwayDispatchProvider = amwayDispatchProvider;
    }
}

package com.topideal.supplychain.ocp.master.model;

import com.topideal.supplychain.common.model.BaseEntity;

/**
 * @author klw
 * @date 2019/11/18 11:41
 * @description: 店铺信息实体类
 */
public class Store extends BaseEntity {
    /**店铺编码*/
    private String code;
    /**业务类型*/
    private String busiType;
    /**平台编码*/
    private String platformCode;
    /**商家编码*/
    private String merchantCode;
    /**海外仓编码*/
    /*private String overseaHouseCode;
    *//**店铺id*//*
    private String appId;

    private String appKey;*/
    /**店铺参数*/
    private String arguments;
    /**esd参数*/
    private String esdArguments;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType == null ? null : busiType.trim();
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode == null ? null : platformCode.trim();
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode == null ? null : merchantCode.trim();
    }

    /*public String getOverseaHouseCode() {
        return overseaHouseCode;
    }

    public void setOverseaHouseCode(String overseaHouseCode) {
        this.overseaHouseCode = overseaHouseCode;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }*/

    public String getArguments() {
        return arguments;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments == null ? null : arguments.trim();
    }

    public String getEsdArguments() {
        return esdArguments;
    }

    public void setEsdArguments(String esdArguments) {
        this.esdArguments = esdArguments;
    }
}
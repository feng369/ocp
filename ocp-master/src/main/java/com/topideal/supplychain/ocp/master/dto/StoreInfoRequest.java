package com.topideal.supplychain.ocp.master.dto;

public class StoreInfoRequest {
    private String dno; //店铺编码

    private String storehouse_id; //海外仓编码

    private String app_id; //卓志速运达分配给第三方应用ID

    private String app_key; //第三方应用注册私钥

    private String shopId;

    public String getDno() {
        return dno;
    }

    public void setDno(String dno) {
        this.dno = dno == null ? null : dno.trim();
    }

    public String getStorehouse_id() {
        return storehouse_id;
    }

    public void setStorehouse_id(String storehouse_id) {
        this.storehouse_id = storehouse_id;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getApp_key() {
        return app_key;
    }

    public void setApp_key(String app_key) {
        this.app_key = app_key;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
}
package com.topideal.supplychain.ocp.xiaomi.dto;
/**
 * @author wanzhaozhang
 * @date 2019/12/27
 * @description 小米平台对接参数
 **/
public class PartnerInfo {
    /**
     有品分配的商家ID 取自店铺信息-店铺编码
     */
    public String partnerId;
    /**
     * 有品分配的商家名字 取自店铺信息-店铺名称/code 没有用到
     */
    public String name;
    /**
     * 签名密钥[有品提供] 取自店铺信息-appkey
     */
    public String key;
    /**
     *  AES加密密钥[有品提供] 取自店铺信息-店铺参数
     */
    public String aesKey;

    public PartnerInfo() {
    }

    public PartnerInfo(String partnerId,  String key, String aesKey) {
        this.partnerId = partnerId;
        this.name = partnerId;
        this.key = key;
        this.aesKey = aesKey;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAesKey() {
        return aesKey;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }
}
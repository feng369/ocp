package com.topideal.supplychain.ocp.master.dto;

/**
 * @author wanzhaozhang
 * @date 2019/11/22
 * @description
 **/
public class SyncStoreDto {
    //电商平台
   private String ebpCode;
   private String ebpName;
   //电商企业
   private String ebcCode;
   private String ebcName;
   //店铺
   private String shopCode;
   private String shopName;
   //海外仓编码
   private String storehouseId;
   private String appKey;
   private String appSecret;
   private String token;
   //app_id，esd专用
   private String appId;
   //20：启用；30：停用
   private String status;

   public String getEbpCode() {
      return ebpCode;
   }

   public void setEbpCode(String ebpCode) {
      this.ebpCode = ebpCode;
   }

   public String getEbpName() {
      return ebpName;
   }

   public void setEbpName(String ebpName) {
      this.ebpName = ebpName;
   }

   public String getEbcCode() {
      return ebcCode;
   }

   public void setEbcCode(String ebcCode) {
      this.ebcCode = ebcCode;
   }

   public String getEbcName() {
      return ebcName;
   }

   public void setEbcName(String ebcName) {
      this.ebcName = ebcName;
   }

   public String getShopCode() {
      return shopCode;
   }

   public void setShopCode(String shopCode) {
      this.shopCode = shopCode;
   }

   public String getShopName() {
      return shopName;
   }

   public void setShopName(String shopName) {
      this.shopName = shopName;
   }

   public String getStorehouseId() {
      return storehouseId;
   }

   public void setStorehouseId(String storehouseId) {
      this.storehouseId = storehouseId;
   }

   public String getAppKey() {
      return appKey;
   }

   public void setAppKey(String appKey) {
      this.appKey = appKey;
   }

   public String getAppSecret() {
      return appSecret;
   }

   public void setAppSecret(String appSecret) {
      this.appSecret = appSecret;
   }

   public String getToken() {
      return token;
   }

   public void setToken(String token) {
      this.token = token;
   }

   public String getAppId() {
      return appId;
   }

   public void setAppId(String appId) {
      this.appId = appId;
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }
}

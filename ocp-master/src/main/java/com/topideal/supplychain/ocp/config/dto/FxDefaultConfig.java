package com.topideal.supplychain.ocp.config.dto;

import java.io.Serializable;

/**
 * 接单及转单的默认配置订单数据
 */
public class FxDefaultConfig implements Serializable {

    private String merchantCode; //平台电商企业编码

    private String platformCode; //平台电商平台编码

    private String itemMerchantCode; //明细电商企业编码

    private String tpl; //平台物流企业编码

    private String logisticsCode;//物流商id

    private String logisticsName;//物流企业名称

    private String fromEplat;//第一仓标识

    private String orderType;

    private String orderSource;

    //private String storeCode;

    private String warehouseCode;

    private String ciqCode;

    public FxDefaultConfig(){ }

    private FxDefaultConfig(Builder builder) {
        setMerchantCode(builder.merchantCode);
        setPlatformCode(builder.platformCode);
        setItemMerchantCode(builder.itemMerchantCode);
        setTpl(builder.tpl);
        setLogisticsCode(builder.logisticsCode);
        setLogisticsName(builder.logisticsName);
        setFromEplat(builder.fromEplat);
        setOrderType(builder.orderType);
        setOrderSource(builder.orderSource);
        //setStoreCode(builder.storeCode);
        setWarehouseCode(builder.warehouseCode);
        setCiqCode(builder.ciqCode);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getCiqCode() {
        return ciqCode;
    }

    public void setCiqCode(String ciqCode) {
        this.ciqCode = ciqCode;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    public String getTpl() {
        return tpl;
    }

    public void setTpl(String tpl) {
        this.tpl = tpl;
    }

    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode;
    }

    public String getLogisticsName() {
        return logisticsName;
    }

    public void setLogisticsName(String logisticsName) {
        this.logisticsName = logisticsName;
    }

    public String getFromEplat() {
        return fromEplat;
    }

    public void setFromEplat(String fromEplat) {
        this.fromEplat = fromEplat;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public void setItemMerchantCode(String itemMerchantCode) {
        this.itemMerchantCode = itemMerchantCode;
    }

    public String getItemMerchantCode() {
        return itemMerchantCode;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    /*public String getStoreCode() {
        return storeCode;
    }*/

    /*public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }*/

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public static final class Builder {

        private String merchantCode;
        private String platformCode;
        private String itemMerchantCode; //明细电商企业编码
        private String tpl;
        private String logisticsCode;
        private String logisticsName;
        private String fromEplat;
        private String orderType;
        private String orderSource;
        //private String storeCode;
        private String warehouseCode;
        private String ciqCode;

        private Builder() {
        }

        public Builder merchantCode(String merchantCode) {
            this.merchantCode = merchantCode;
            return this;
        }

        public Builder itemMerchantCode(String itemMerchantCode) {
            this.itemMerchantCode = itemMerchantCode;
            return this;
        }

        public Builder platformCode(String platformCode) {
            this.platformCode = platformCode;
            return this;
        }

        public Builder tpl(String tpl) {
            this.tpl = tpl;
            return this;
        }

        public Builder logisticsCode(String logisticsCode) {
            this.logisticsCode = logisticsCode;
            return this;
        }

        public Builder logisticsName(String logisticsName) {
            this.logisticsName = logisticsName;
            return this;
        }

        public Builder fromEplat(String fromEplat) {
            this.fromEplat = fromEplat;
            return this;
        }

        public Builder orderType(String orderType) {
            this.orderType = orderType;
            return this;
        }

        public Builder orderSource(String orderSource) {
            this.orderSource = orderSource;
            return this;
        }

        /*public Builder storeCode(String storeCode) {
            this.storeCode = storeCode;
            return this;
        }*/

        public Builder warehouseCode(String warehouseCode) {
            this.warehouseCode = warehouseCode;
            return this;
        }

        public Builder ciqCode(String ciqCode){
            this.ciqCode = ciqCode;
            return this;
        }



        public FxDefaultConfig build() {
            return new FxDefaultConfig(this);
        }

    }
}

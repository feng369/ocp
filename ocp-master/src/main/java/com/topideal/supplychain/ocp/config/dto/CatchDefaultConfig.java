package com.topideal.supplychain.ocp.config.dto;

import java.io.Serializable;

/**
 * 接单及转单的默认配置订单数据
 */
public class CatchDefaultConfig implements Serializable {

    //private String merchantCode; //平台电商企业编码

    //平台电商平台编码
    private String platformCode;

    /*private String storeCode;//店铺编码*/

    private String customsCode;//海关编码

    private String ciqCode;//国检编码

    private String busiMode;//业务模式

    private String tpl; //平台物流企业编码

    private String logisticsCode;//物流商id

    private String logisticsName;//物流企业名称

    /*private String fromEplat;//第一仓标识*/

    private String customsType;//海关类型

    public CatchDefaultConfig() {
    }

    private CatchDefaultConfig(Builder builder) {
        /*setMerchantCode(builder.merchantCode);
        setPlatformCode(builder.platformCode);
        setStoreCode(builder.storeCode);*/
        setCustomsCode(builder.customsCode);
        setCiqCode(builder.ciqCode);
        setBusiMode(builder.busiMode);
        setTpl(builder.tpl);
        setLogisticsCode(builder.logisticsCode);
        setLogisticsName(builder.logisticsName);
        /*setFromEplat(builder.fromEplat);*/
        setCustomsType(builder.customsType);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }


    /*public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }


    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }*/

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

    public String getBusiMode() {
        return busiMode;
    }

    public void setBusiMode(String busiMode) {
        this.busiMode = busiMode;
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

    /*public String getFromEplat() {
        return fromEplat;
    }

    public void setFromEplat(String fromEplat) {
        this.fromEplat = fromEplat;
    }*/

    public String getCustomsType() {
        return customsType;
    }

    public void setCustomsType(String customsType) {
        this.customsType = customsType;
    }

    public static final class Builder {

        /*private String merchantCode;
        private String platformCode;
        private String storeCode;*/
        private String customsCode;
        private String ciqCode;
        private String busiMode;
        private String tpl;
        private String logisticsCode;
        private String logisticsName;
        //private String fromEplat;
        private String customsType;//海关类型


        private Builder() {
        }

        /*public Builder merchantCode(String merchantCode) {
            this.merchantCode = merchantCode;
            return this;
        }*/

        public Builder customsType(String customsType) {
            this.customsType = customsType;
            return this;
        }

        /*public Builder platformCode(String platformCode) {
            this.platformCode = platformCode;
            return this;
        }

        public Builder storeCode(String storeCode) {
            this.storeCode = storeCode;
            return this;
        }*/

        public Builder customsCode(String customsCode) {
            this.customsCode = customsCode;
            return this;
        }

        public Builder ciqCode(String ciqCode) {
            this.ciqCode = ciqCode;
            return this;
        }

        public Builder busiMode(String busiMode) {
            this.busiMode = busiMode;
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

        /*public Builder fromEplat(String fromEplat) {
            this.fromEplat = fromEplat;
            return this;
        }*/



        public CatchDefaultConfig build() {
            return new CatchDefaultConfig(this);
        }
    }
}

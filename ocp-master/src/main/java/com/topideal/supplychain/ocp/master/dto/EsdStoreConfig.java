package com.topideal.supplychain.ocp.master.dto;

import java.io.Serializable;

/**
 * @ClassName EsdStoreConfig
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2020/5/25 11:46
 * @Version 1.0
 **/
public class EsdStoreConfig implements Serializable {

    private String dno;

    private String appId;

    private String appKey;

    private String overseaHouseCode;

    public EsdStoreConfig() {

    }

    private EsdStoreConfig(Builder builder) {
        dno = builder.dno;
        appId = builder.appId;
        appKey = builder.appKey;
        overseaHouseCode = builder.overseaHouseCode;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public String getDno() {
        return dno;
    }

    public String getAppId() {
        return appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public String getOverseaHouseCode() {
        return overseaHouseCode;
    }

    public void setDno(String dno) {
        this.dno = dno;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public void setOverseaHouseCode(String overseaHouseCode) {
        this.overseaHouseCode = overseaHouseCode;
    }

    public static final class Builder {

        private String dno;
        private String appId;
        private String appKey;
        private String overseaHouseCode;

        private Builder() {
        }

        public Builder dno(String dno) {
            this.dno = dno;
            return this;
        }

        public Builder appId(String appId) {
            this.appId = appId;
            return this;
        }

        public Builder appKey(String appKey) {
            this.appKey = appKey;
            return this;
        }

        public Builder overseaHouseCode(String overseaHouseCode) {
            this.overseaHouseCode = overseaHouseCode;
            return this;
        }

        public EsdStoreConfig build() {
            return new EsdStoreConfig(this);
        }
    }
}

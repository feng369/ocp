package com.topideal.supplychain.ocp.global.dto;

import java.util.Date;

/**
 * <p>标题: 全球仓的参数</p>
 * <p>模块: ocp-parent</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.global.dto</p>
 * <p>作者: LeoZhang</p>
 * <p>创建日期: 2019/12/12 17:42</p>
 *
 * @version 1.0
 */
public class GlobalParam {

    private int pageNo;//页码
    private Long startTime;//抓单开始时间
    private Long endTime;//抓单截至时间
    private Integer pageSize;//页数
    private String storeCode;//店铺编码
    private String appId;//appId
    private String secretKey;//密钥

    private GlobalParam(Builder builder) {
        setPageNo(builder.pageNo);
        setStartTime(builder.startTime);
        setEndTime(builder.endTime);
        setPageSize(builder.pageSize);
        setStoreCode(builder.storeCode);
        setAppId(builder.appId);
        setSecretKey(builder.secretKey);
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public static final class Builder {
        private int pageNo;
        private Long startTime;
        private Long endTime;
        private Integer pageSize;
        private String storeCode;
        private String appId;
        private String secretKey;

        public Builder() {
        }

        public static Builder newBuilder(){
            return new Builder();
        }

        public Builder pageNo(int val) {
            pageNo = val;
            return this;
        }

        public Builder startTime(Long val) {
            startTime = val;
            return this;
        }

        public Builder endTime(Long val) {
            endTime = val;
            return this;
        }

        public Builder pageSize(Integer val) {
            pageSize = val;
            return this;
        }

        public Builder storeCode(String val) {
            storeCode = val;
            return this;
        }

        public Builder appId(String val) {
            appId = val;
            return this;
        }

        public Builder secretKey(String val) {
            secretKey = val;
            return this;
        }

        public GlobalParam build() {
            return new GlobalParam(this);
        }
    }
}

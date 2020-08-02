package com.topideal.supplychain.ocp.jd.dto;

import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName JdRequest
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2020/3/16 14:00
 * @Version 1.0
 **/
public class JdRequest implements Serializable {

    private boolean isDlz ;//是否是独立站多渠道

    private boolean isYx;//是否是云霄购

    private String orderId;//订单号

    private String platformId;

    private String method;//接口请求方法

    private int page;//分页数

    private boolean isBatch;//是否批量抓单

    private Date beginDate;

    private Date endDate;

    private CatchOrderConfig catchOrderConfig;//抓单配置

    private JdRequest(Builder builder) {
        isDlz = builder.isDlz;
        isYx = builder.isYx;
        orderId = builder.orderId;
        platformId = builder.platformId;
        method = builder.method;
        page = builder.page;
        isBatch = builder.isBatch;
        catchOrderConfig = builder.catchOrderConfig;
        endDate = builder.endDate;
        beginDate = builder.beginDate;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public boolean isDlz() {
        return isDlz;
    }

    public boolean isYx() {
        return isYx;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getPlatformId() {
        return platformId;
    }

    public String getMethod() {
        return method;
    }

    public boolean isBatch() {
        return isBatch;
    }

    public CatchOrderConfig getCatchOrderConfig() {
        return catchOrderConfig;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {

        private boolean isDlz= false;
        private boolean isYx= false;
        private String orderId;
        private String platformId;
        private String method;
        private int page = 1;
        private boolean isBatch = true;
        private CatchOrderConfig catchOrderConfig;
        private Date beginDate;
        private Date endDate;

        private Builder() {
        }

        public Builder isDlz(boolean isDlz) {
            this.isDlz = isDlz;
            return this;
        }

        public Builder isYx(boolean isYx) {
            this.isYx = isYx;
            return this;
        }

        public Builder orderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder platformId(String platformId) {
            this.platformId = platformId;
            return this;
        }

        public Builder method(String method) {
            this.method = method;
            return this;
        }

        public Builder page(int page) {
            this.page = page;
            return this;
        }

        public Builder isBatch(boolean isBatch) {
            this.isBatch = isBatch;
            return this;
        }

        public Builder beginDate(Date beginDate) {
            this.beginDate = beginDate;
            return this;
        }

        public Builder endDate(Date endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder catchOrderConfig(CatchOrderConfig catchOrderConfig) {
            this.catchOrderConfig = catchOrderConfig;
            return this;
        }

        public JdRequest build() {
            return new JdRequest(this);
        }
    }
}

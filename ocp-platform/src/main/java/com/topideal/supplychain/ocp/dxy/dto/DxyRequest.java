package com.topideal.supplychain.ocp.dxy.dto;

import java.io.Serializable;

/**
 * @ClassName DxyRequest
 * @TODO 丁香医生请求参数
 * @Author zhangzhihao
 * @DATE 2020/6/30 15:35
 * @Version 1.0
 **/
public class DxyRequest implements Serializable {

    private int pageNo;

    private int pageSize;

    private String startTime;

    private String endTime;

    private DxyRequest(Builder builder) {
        pageNo = builder.pageNo;
        pageSize = builder.pageSize;
        startTime = builder.startTime;
        endTime = builder.endTime;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }


    public static final class Builder {

        private int pageNo = 1;
        private int pageSize;
        private String startTime;
        private String endTime;

        private Builder() {
        }

        public Builder pageNo(int pageNo) {
            this.pageNo = pageNo;
            return this;
        }

        public Builder pageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public Builder startTime(String startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder endTime(String endTime) {
            this.endTime = endTime;
            return this;
        }

        public DxyRequest build() {
            return new DxyRequest(this);
        }
    }
}

package com.topideal.supplychain.ocp.op.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;

/**
 * @ClassName 扩展字段
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2019/12/9 15:34
 * @Version 1.0
 **/
@JsonInclude(Include.NON_NULL)
public class OpCommonFieldDto implements Serializable {

    private String transaction;
    private String tid;
    private Integer isfx;
    private String fx_kdt_id;
    private String fx_order_no;
    private String platformNo;//平台物流商编码

    private OpCommonFieldDto(Builder builder) {
        transaction = builder.transaction;
        tid = builder.tid;
        isfx = builder.isfx;
        fx_kdt_id = builder.fx_kdt_id;
        fx_order_no = builder.fx_order_no;
        platformNo = builder.platformNo;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {

        private String transaction;
        private String tid;
        private Integer isfx;
        private String fx_kdt_id;
        private String fx_order_no;
        private String platformNo;

        private Builder() {
        }

        public Builder transaction(String transaction) {
            this.transaction = transaction;
            return this;
        }

        public Builder tid(String tid) {
            this.tid = tid;
            return this;
        }

        public Builder isfx(Integer isfx) {
            this.isfx = isfx;
            return this;
        }

        public Builder fx_kdt_id(String fx_kdt_id) {
            this.fx_kdt_id = fx_kdt_id;
            return this;
        }

        public Builder fx_order_no(String fx_order_no) {
            this.fx_order_no = fx_order_no;
            return this;
        }

        public Builder platformNo(String platformNo) {
            this.platformNo = platformNo;
            return this;
        }

        public OpCommonFieldDto build() {
            return new OpCommonFieldDto(this);
        }
    }

    public String getTransaction() {
        return transaction;
    }

    public String getTid() {
        return tid;
    }

    public Integer getIsfx() {
        return isfx;
    }

    public String getFx_kdt_id() {
        return fx_kdt_id;
    }

    public String getFx_order_no() {
        return fx_order_no;
    }

    public String getPlatformNo() {
        return platformNo;
    }
}

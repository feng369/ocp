package com.topideal.supplychain.ocp.esd.dto;

import java.io.Serializable;

public class EsdBuyer implements Serializable {

    private String name; //姓名

    private String phone; //联系电话

    private String mobile; //订购人注册号

    private String id_type; //证件类型 1：身份证；2：其它

    private String identity_no; //证件号码

    private EsdBuyer(Builder builder) {
        name = builder.name;
        phone = builder.phone;
        mobile = builder.mobile;
        id_type = builder.id_type;
        identity_no = builder.identity_no;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {

        private String name;
        private String phone;
        private String mobile;
        private String id_type;
        private String identity_no;

        private Builder() {
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder mobile(String mobile) {
            this.mobile = mobile;
            return this;
        }

        public Builder id_type(String id_type) {
            this.id_type = id_type;
            return this;
        }

        public Builder identity_no(String identity_no) {
            this.identity_no = identity_no;
            return this;
        }

        public EsdBuyer build() {
            return new EsdBuyer(this);
        }
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getMobile() {
        return mobile;
    }

    public String getId_type() {
        return id_type;
    }

    public String getIdentity_no() {
        return identity_no;
    }
}
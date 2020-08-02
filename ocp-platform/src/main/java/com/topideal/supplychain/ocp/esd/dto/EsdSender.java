package com.topideal.supplychain.ocp.esd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class EsdSender implements Serializable {

    private String name; //姓名

    private String phone; //电话

    private String mobile; //手机

    private String country; //国家

    private String province; //省

    private String city; //市

    private String county; //区

    private String address; //详细地址

    private String zip_code; //邮编

    private String street;//发件人街道

    private String send_num;//发件人门牌号;

    private EsdSender(Builder builder) {
        name = builder.name;
        phone = builder.phone;
        mobile = builder.mobile;
        country = builder.country;
        province = builder.province;
        city = builder.city;
        county = builder.county;
        address = builder.address;
        zip_code = builder.zip_code;
        street = builder.street;
        send_num = builder.send_num;
    }

    public static Builder newBuilder() {
        return new Builder();
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

    public String getCountry() {
        return country;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getCounty() {
        return county;
    }

    public String getAddress() {
        return address;
    }

    public String getZip_code() {
        return zip_code;
    }

    public String getStreet() {
        return street;
    }

    public String getSend_num() {
        return send_num;
    }

    public static final class Builder {

        private String name;
        private String phone;
        private String mobile;
        private String country;
        private String province;
        private String city;
        private String county;
        private String address;
        private String zip_code;
        private String street;
        private String send_num;

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

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder province(String province) {
            this.province = province;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder county(String county) {
            this.county = county;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder zip_code(String zip_code) {
            this.zip_code = zip_code;
            return this;
        }

        public Builder street(String street) {
            this.street = street;
            return this;
        }

        public Builder send_num(String send_num) {
            this.send_num = send_num;
            return this;
        }

        public EsdSender build() {
            return new EsdSender(this);
        }
    }
}
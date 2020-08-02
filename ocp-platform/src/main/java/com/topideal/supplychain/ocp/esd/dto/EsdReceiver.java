package com.topideal.supplychain.ocp.esd.dto;

import java.io.Serializable;

public class EsdReceiver implements Serializable {

    private String name; //姓名

    private String phone; //电话

    private String mobile; //手机

    private String identity_no; //身份证号码

    private String identity_no_front; //身份证正面url，pdf，图片

    private String identity_no_back; //身份证反面url，pdf，图片

    private String country; //国家，默认中国

    private String province; //省

    private String city; //市

    private String county; //区

    private String address; //详细地址

    private String zip_code; //邮编

    private String identity_type;//证件类型，1:身份证；2:护照，默认1

    private String province_code;//收件人省份/州编码

    private String street;//收件人所在街道
    private String address2;//收件人详细地址2
    private String address3;//收件人详细地址3
    private String houseno;//收件人门牌号
    private String email;//收件人邮箱

    private EsdReceiver(Builder builder) {
        name = builder.name;
        phone = builder.phone;
        mobile = builder.mobile;
        identity_no = builder.identity_no;
        identity_no_front = builder.identity_no_front;
        identity_no_back = builder.identity_no_back;
        country = builder.country;
        province = builder.province;
        city = builder.city;
        county = builder.county;
        address = builder.address;
        zip_code = builder.zip_code;
        identity_type = builder.identity_type;
        province_code = builder.province_code;
        street = builder.street;
        address2 = builder.address2;
        address3 = builder.address3;
        houseno = builder.houseno;
        email = builder.email;
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

    public String getIdentity_no() {
        return identity_no;
    }

    public String getIdentity_no_front() {
        return identity_no_front;
    }

    public String getIdentity_no_back() {
        return identity_no_back;
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

    public String getIdentity_type() {
        return identity_type;
    }

    public String getProvince_code() {
        return province_code;
    }

    public String getStreet() {
        return street;
    }

    public String getAddress2() {
        return address2;
    }

    public String getAddress3() {
        return address3;
    }

    public String getHouseno() {
        return houseno;
    }

    public String getEmail() {
        return email;
    }

    public static final class Builder {

        private String name;
        private String phone;
        private String mobile;
        private String identity_no;
        private String identity_no_front;
        private String identity_no_back;
        private String country;
        private String province;
        private String city;
        private String county;
        private String address;
        private String zip_code;
        private String identity_type;
        private String province_code;
        private String street;
        private String address2;
        private String address3;
        private String houseno;
        private String email;

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

        public Builder identity_no(String identity_no) {
            this.identity_no = identity_no;
            return this;
        }

        public Builder identity_no_front(String identity_no_front) {
            this.identity_no_front = identity_no_front;
            return this;
        }

        public Builder identity_no_back(String identity_no_back) {
            this.identity_no_back = identity_no_back;
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

        public Builder identity_type(String identity_type) {
            this.identity_type = identity_type;
            return this;
        }

        public Builder province_code(String province_code) {
            this.province_code = province_code;
            return this;
        }

        public Builder street(String street) {
            this.street = street;
            return this;
        }

        public Builder address2(String address2) {
            this.address2 = address2;
            return this;
        }

        public Builder address3(String address3) {
            this.address3 = address3;
            return this;
        }

        public Builder houseno(String houseno) {
            this.houseno = houseno;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public EsdReceiver build() {
            return new EsdReceiver(this);
        }
    }
}
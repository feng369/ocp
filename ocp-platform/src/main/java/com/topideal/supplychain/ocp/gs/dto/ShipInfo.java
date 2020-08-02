package com.topideal.supplychain.ocp.gs.dto;

import java.io.Serializable;

public class ShipInfo implements Serializable {
        /**
         * 收货方姓名
         */
        private String shipToName;
        /**
         * 收货方手机号
         */
        private String shipToMobile;
        /**
         * 收货方地址邮编
         */
        private String shipToZip;
        /**
         * 收货方地址省份,直辖市省市相同
         */
        private String shipToProvince;
        /**
         * 收货方地址市,直辖市省市相同
         */
        private String shipToCity;
        /**
         * 收货方地址行政区,市/县级行政区
         */
        private String shipToDistrict;
        /**
         * 收货方地址镇,市级行政区对应街道/县级行政区对应城镇收货城市编码
         */
        private String shipToTown;
        /**
         * 收货方详细收货地址,街道、小区、门牌号收货地区
         */
        private String shipToAddress;
        /**
         * 订购人信息
         */
        private Certification certification;

        public String getShipToName() {
            return shipToName;
        }

        public void setShipToName(String shipToName) {
            this.shipToName = shipToName;
        }

        public String getShipToMobile() {
            return shipToMobile;
        }

        public void setShipToMobile(String shipToMobile) {
            this.shipToMobile = shipToMobile;
        }

        public String getShipToZip() {
            return shipToZip;
        }

        public void setShipToZip(String shipToZip) {
            this.shipToZip = shipToZip;
        }

        public String getShipToProvince() {
            return shipToProvince;
        }

        public void setShipToProvince(String shipToProvince) {
            this.shipToProvince = shipToProvince;
        }

        public String getShipToCity() {
            return shipToCity;
        }

        public void setShipToCity(String shipToCity) {
            this.shipToCity = shipToCity;
        }

        public String getShipToDistrict() {
            return shipToDistrict;
        }

        public void setShipToDistrict(String shipToDistrict) {
            this.shipToDistrict = shipToDistrict;
        }

        public String getShipToTown() {
            return shipToTown;
        }

        public void setShipToTown(String shipToTown) {
            this.shipToTown = shipToTown;
        }

        public String getShipToAddress() {
            return shipToAddress;
        }

        public void setShipToAddress(String shipToAddress) {
            this.shipToAddress = shipToAddress;
        }

        public Certification getCertification() {
            return certification;
        }

        public void setCertification(Certification certification) {
            this.certification = certification;
        }
    }




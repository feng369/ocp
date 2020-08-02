package com.topideal.supplychain.ocp.gs.dto;

import java.io.Serializable;

public class Certification implements Serializable {
        /**
         * 身份证号码
         */
        private String idCardNO;
        /**
         * 身份证姓名
         */
        private String idCardName;
        /**
         * 身份证正面照片(图片url)
         */
        private String positiveUrl;
        /**
         * 身份证反面照片(图片url)
         */
        private String otherSideUrl;

        public String getIdCardNO() {
            return idCardNO;
        }

        public void setIdCardNO(String idCardNO) {
            this.idCardNO = idCardNO;
        }

        public String getIdCardName() {
            return idCardName;
        }

        public void setIdCardName(String idCardName) {
            this.idCardName = idCardName;
        }

        public String getPositiveUrl() {
            return positiveUrl;
        }

        public void setPositiveUrl(String positiveUrl) {
            this.positiveUrl = positiveUrl;
        }

        public String getOtherSideUrl() {
            return otherSideUrl;
        }

        public void setOtherSideUrl(String otherSideUrl) {
            this.otherSideUrl = otherSideUrl;
        }
    }
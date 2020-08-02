package com.topideal.supplychain.ocp.gs.dto;

import java.io.Serializable;

public class LogisticsInfo implements Serializable {
        /**
         * 物流公司
         */
        private String logisticsChannel;
        /**
         * 物流编号
         */
        private String logisticsNumber;
        /**
         * 包裹状态
         */
        private String status;
        /**
         * 包裹状态描述
         */
        private String statusDesc;

        public String getLogisticsChannel() {
            return logisticsChannel;
        }

        public void setLogisticsChannel(String logisticsChannel) {
            this.logisticsChannel = logisticsChannel;
        }

        public String getLogisticsNumber() {
            return logisticsNumber;
        }

        public void setLogisticsNumber(String logisticsNumber) {
            this.logisticsNumber = logisticsNumber;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatusDesc() {
            return statusDesc;
        }

        public void setStatusDesc(String statusDesc) {
            this.statusDesc = statusDesc;
        }
    }
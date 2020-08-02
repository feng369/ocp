package com.topideal.supplychain.ocp.gs.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Date;

/**
 * <p>标题: TODO</p>
 * <p>功能: TODO</p>
 * <p>模块: ocp2</p>
 * <p>版权: Copyright © 2020 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.gs.dto</p>
 * <p>作者: topIdeal</p>
 * <p>创建日期: 2020-01-08 11:50</p>
 *
 * @version 1.0
 */
public class GsGrabReqDto {

    private String partner;
    private Date timestamp;
    private GsGrabArgs params;

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public GsGrabArgs getParams() {
        return params;
    }

    public void setParams(GsGrabArgs params) {
        this.params = params;
    }

    @JsonInclude(Include.NON_NULL)
    public static class GsGrabArgs {
        private String consignCode;
        private Date startTime;
        private Date endTime;
        private Date updateTimeStart;
        private Date updateTimeEnd;
        private String status;
        private Boolean bondedArea;
        private Integer page;
        private Integer pageSize;

        public String getConsignCode() {
            return consignCode;
        }

        public void setConsignCode(String consignCode) {
            this.consignCode = consignCode;
        }

        public Date getStartTime() {
            return startTime;
        }

        public void setStartTime(Date startTime) {
            this.startTime = startTime;
        }

        public Date getEndTime() {
            return endTime;
        }

        public void setEndTime(Date endTime) {
            this.endTime = endTime;
        }

        public Date getUpdateTimeStart() {
            return updateTimeStart;
        }

        public void setUpdateTimeStart(Date updateTimeStart) {
            this.updateTimeStart = updateTimeStart;
        }

        public Date getUpdateTimeEnd() {
            return updateTimeEnd;
        }

        public void setUpdateTimeEnd(Date updateTimeEnd) {
            this.updateTimeEnd = updateTimeEnd;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Boolean getBondedArea() {
            return bondedArea;
        }

        public void setBondedArea(Boolean bondedArea) {
            this.bondedArea = bondedArea;
        }

        public Integer getPage() {
            return page;
        }

        public void setPage(Integer page) {
            this.page = page;
        }

        public Integer getPageSize() {
            return pageSize;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }
    }
}

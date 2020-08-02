package com.topideal.supplychain.ocp.daling.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 * 达令家抓取详情data
 * @author xuxiaoyan
 * @date 2019-12-13 10:58
 */
public class DalingGrabInfoData implements Serializable{

    private static final long serialVersionUID = -7782212949922293954L;

    @JsonProperty("order_no")
    private String orderNo;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}

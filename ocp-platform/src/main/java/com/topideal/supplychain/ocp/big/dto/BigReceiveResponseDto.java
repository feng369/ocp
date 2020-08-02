package com.topideal.supplychain.ocp.big.dto;

import java.io.Serializable;

/**
 * 标题：大订单接单响应DTO
 * 模块：ocp-parent
 * 版权: Copyright © 2019 topideal
 * 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.big.dto
 * 作者：songping
 * 创建日期：2019/12/26 17:37
 *
 * @version 1.0
 */
public class BigReceiveResponseDto implements Serializable {

    private String	    orderId;
    private Integer		status;
    private String	    notes;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

package com.topideal.supplychain.ocp.order.model;

import com.topideal.supplychain.common.model.BaseEntity;
/**
 * @author klw
 * @date 2019/12/11 16:01
 * @description: 拼多多抓单的卡密实体类
 */
public class OrderPddCard extends BaseEntity {

    private Long orderId;

    private String maskPassword;

    private String cardNo;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getMaskPassword() {
        return maskPassword;
    }

    public void setMaskPassword(String maskPassword) {
        this.maskPassword = maskPassword == null ? null : maskPassword.trim();
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }
}
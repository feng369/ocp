package com.topideal.supplychain.ocp.order.model;

import com.topideal.supplychain.common.model.BaseEntity;

import java.util.Date;
/**
 * @author klw
 * @date 2019/12/11 16:01
 * @description: 拼多多抓单子货品实体类
 */
public class OrderPddWare extends BaseEntity {

    private Long orderId;

    private String wareSn;

    private Long wareQuantity;

    private String wareName;

    private Long wareId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getWareSn() {
        return wareSn;
    }

    public void setWareSn(String wareSn) {
        this.wareSn = wareSn == null ? null : wareSn.trim();
    }

    public Long getWareQuantity() {
        return wareQuantity;
    }

    public void setWareQuantity(Long wareQuantity) {
        this.wareQuantity = wareQuantity;
    }

    public String getWareName() {
        return wareName;
    }

    public void setWareName(String wareName) {
        this.wareName = wareName == null ? null : wareName.trim();
    }

    public Long getWareId() {
        return wareId;
    }

    public void setWareId(Long wareId) {
        this.wareId = wareId;
    }

}
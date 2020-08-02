package com.topideal.supplychain.ocp.order.dto;

import com.topideal.supplychain.ocp.order.model.OrderBig;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 标题：大订单页面查询DTO
 * 模块：ocp-parent
 * 版权: Copyright © 2019 topideal
 * 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.order.dto
 * 作者：songping
 * 创建日期：2020/1/2 11:52
 *
 * @version 1.0
 */
public class OrderBigPageRequestDto extends OrderBig {

    /**
     * 支付时间（查询条件）
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payDateStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payDateEnd;
    /**
     * 系统创建时间（查询条件）
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeEnd;
    /**
     * 下单时间（查询条件）
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderDateStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderDateEnd;

    public Date getPayDateStart() {
        return payDateStart;
    }

    public void setPayDateStart(Date payDateStart) {
        this.payDateStart = payDateStart;
    }

    public Date getPayDateEnd() {
        return payDateEnd;
    }

    public void setPayDateEnd(Date payDateEnd) {
        this.payDateEnd = payDateEnd;
    }

    public Date getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(Date createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public Date getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Date createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public Date getOrderDateStart() {
        return orderDateStart;
    }

    public void setOrderDateStart(Date orderDateStart) {
        this.orderDateStart = orderDateStart;
    }

    public Date getOrderDateEnd() {
        return orderDateEnd;
    }

    public void setOrderDateEnd(Date orderDateEnd) {
        this.orderDateEnd = orderDateEnd;
    }
}

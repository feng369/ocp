package com.topideal.supplychain.ocp.order.dto;

import com.topideal.supplychain.enumeration.YesOrNoEnum;
import com.topideal.supplychain.ocp.order.model.OrderJd;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class OrderJdDto extends OrderJd {
    /**
     * 创建开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeStart;

    /**
     * 创建结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeEnd;

    /**
     * 订单创建开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderCreateTimeStart;


    /**
     * 订单创建结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderCreateTimeEnd;

    public Date getOrderCreateTimeStart() {
        return orderCreateTimeStart;
    }

    public void setOrderCreateTimeStart(Date orderCreateTimeStart) {
        this.orderCreateTimeStart = orderCreateTimeStart;
    }

    public Date getOrderCreateTimeEnd() {
        return orderCreateTimeEnd;
    }

    public void setOrderCreateTimeEnd(Date orderCreateTimeEnd) {
        this.orderCreateTimeEnd = orderCreateTimeEnd;
    }

    private YesOrNoEnum isMc;



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

    public YesOrNoEnum getIsMc() {
        return isMc;
    }

    public void setIsMc(YesOrNoEnum isMc) {
        this.isMc = isMc;
    }
}

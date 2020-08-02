package com.topideal.supplychain.ocp.order.dto;

import com.topideal.supplychain.ocp.order.model.OrderAmway;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class OrderAmwayDto extends OrderAmway {
    /**
     * 制单时间（查询条件）
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeEnd;

    /**
     * 下单时间（查询条件）
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDateStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDateEnd;


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


    public Date getCreateDateStart() {
        return createDateStart;
    }


    public void setCreateDateStart(Date createDateStart) {
        this.createDateStart = createDateStart;
    }


    public Date getCreateDateEnd() {
        return createDateEnd;
    }

    public void setCreateDateEnd(Date createDateEnd) {
        this.createDateEnd = createDateEnd;
    }
}

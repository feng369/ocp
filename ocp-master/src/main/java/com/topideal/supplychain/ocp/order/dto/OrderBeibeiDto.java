package com.topideal.supplychain.ocp.order.dto;

import com.topideal.supplychain.ocp.order.model.OrderBeibei;
import com.topideal.supplychain.ocp.order.model.OrderBeibeiItem;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author wanzhaozhang
 * @date 2020/3/19
 * @description
 **/
public class OrderBeibeiDto extends OrderBeibei {
    //开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;
    //结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    private List<OrderBeibeiItem> item;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<OrderBeibeiItem> getItem() {
        return item;
    }

    public void setItem(List<OrderBeibeiItem> item) {
        this.item = item;
    }
}

package com.topideal.supplychain.ocp.order.dto;

import com.topideal.supplychain.ocp.order.model.OrderHipac;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 海拍客页面转换类
 *
 * @author xuxiaoyan
 * @date 2019-12-16 15:57
 */
public class OrderHipacDto extends OrderHipac {

    /**
     * 制单时间（查询条件）
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeEnd;

    /**
     * 订单生成时间（查询条件）
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderDateStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderDateEnd;

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

package com.topideal.supplychain.ocp.order.dto;

import com.topideal.supplychain.ocp.order.model.OrderDxy;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @ClassName OrderDxyDto
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2020/7/1 14:02
 * @Version 1.0
 **/
public class OrderDxyDto extends OrderDxy {

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
    private Date orderCreateTimeStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderCreateTimeEnd;

    /**
     * 平台订单号 页面批量查询
     */
    private String orderIds;

    private String codes;

    private String[] orderIdList;

    private String[] codeList;


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

    public String getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(String orderIds) {
        this.orderIds = orderIds;
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    public String[] getOrderIdList() {
        return orderIdList;
    }

    public void setOrderIdList(String[] orderIdList) {
        this.orderIdList = orderIdList;
    }

    public String[] getCodeList() {
        return codeList;
    }

    public void setCodeList(String[] codeList) {
        this.codeList = codeList;
    }
}

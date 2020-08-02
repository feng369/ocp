package com.topideal.supplychain.ocp.ymatou.dto;

import java.util.Date;

/**
 * @author wanzhaozhang
 * @date 2019/12/10
 * @description
 **/
public class YmatouGrabReq {
    //2,17
    private String order_status;
    //时间筛选和排序类型  2.订单付款时间 默认2
    private String date_type;
    //0.倒序 1.升序 默认1
    private String sort_type;
    //查询开始时间 yyyy-MM-dd HH:mm
    private Date start_date;
    //查询结束时间
    private Date end_date;
    //请求分页页码(大于0)
    private int page_no;
    //每页记录数量(大于0且小于等于100 )
    private Long page_rows;

    public String getOrder_status() {
        return "2,17";
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getDate_type() {
        return "2";
    }

    public void setDate_type(String date_type) {
        this.date_type = date_type;
    }

    public String getSort_type() {
        return "1";
    }

    public void setSort_type(String sort_type) {
        this.sort_type = sort_type;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public int getPage_no() {
        return page_no;
    }

    public void setPage_no(int page_no) {
        this.page_no = page_no;
    }

    public Long getPage_rows() {
        return page_rows;
    }

    public void setPage_rows(Long page_rows) {
        this.page_rows = page_rows;
    }
}

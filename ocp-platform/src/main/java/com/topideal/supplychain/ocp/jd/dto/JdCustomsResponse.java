package com.topideal.supplychain.ocp.jd.dto;

import java.io.Serializable;

/**
 * @ClassName JdCustomsResponse
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2020/2/26 17:00
 * @Version 1.0
 **/
public class JdCustomsResponse implements Serializable {

    private String code;

    private JdOrderListResult orderListResult;

    private JdOrderResult orderResult;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public JdOrderListResult getOrderListResult() {
        return orderListResult;
    }

    public void setOrderListResult(JdOrderListResult orderListResult) {
        this.orderListResult = orderListResult;
    }

    public JdOrderResult getOrderResult() {
        return orderResult;
    }

    public void setOrderResult(JdOrderResult orderResult) {
        this.orderResult = orderResult;
    }
}

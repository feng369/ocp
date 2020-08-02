package com.topideal.supplychain.ocp.jd.dto;

/**
 * @ClassName JdYxResponse
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2020/3/4 16:37
 * @Version 1.0
 **/
public class JdYxResponse {

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

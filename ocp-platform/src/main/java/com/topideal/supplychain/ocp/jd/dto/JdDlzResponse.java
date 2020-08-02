package com.topideal.supplychain.ocp.jd.dto;

import java.io.Serializable;

/**
 * @ClassName JdDlzResponse
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2020/3/10 16:18
 * @Version 1.0
 **/
public class JdDlzResponse implements Serializable {

    private JdOrderListResult queryperiodorder_result;

    private String code;

    private JdOrderResult queryorder_result;

    public JdOrderListResult getQueryperiodorder_result() {
        return queryperiodorder_result;
    }

    public void setQueryperiodorder_result(
            JdOrderListResult queryperiodorder_result) {
        this.queryperiodorder_result = queryperiodorder_result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public JdOrderResult getQueryorder_result() {
        return queryorder_result;
    }

    public void setQueryorder_result(JdOrderResult queryorder_result) {
        this.queryorder_result = queryorder_result;
    }
}

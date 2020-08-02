package com.topideal.supplychain.ocp.gs.dto;

import java.io.Serializable;

/**
 * @ClassName GsResponse
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2020/4/27 13:49
 * @Version 1.0
 **/
public class GsResponse implements Serializable {

    /**
     * 状态码
     */
    private String code;
    /**
     * 回执信息
     */
    private String message;
    /**
     * 订单信息
     */
    private ResultData data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultData getData() {
        return data;
    }

    public void setData(ResultData data) {
        this.data = data;
    }
}

package com.topideal.supplychain.ocp.beibei.dto;

import java.io.Serializable;

/**
 * @author wanzhaozhang
 * @date 2020/4/14
 * @description
 **/
public class BeibeiDetailResponseDto implements Serializable {
    private boolean success;
    private String message;
    private BeibeiOrderDetailDto data;
    private Integer err_code;
    private String err_msg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BeibeiOrderDetailDto getData() {
        return data;
    }

    public void setData(BeibeiOrderDetailDto data) {
        this.data = data;
    }

    public Integer getErr_code() {
        return err_code;
    }

    public void setErr_code(Integer err_code) {
        this.err_code = err_code;
    }

    public String getErr_msg() {
        return err_msg;
    }

    public void setErr_msg(String err_msg) {
        this.err_msg = err_msg;
    }
}

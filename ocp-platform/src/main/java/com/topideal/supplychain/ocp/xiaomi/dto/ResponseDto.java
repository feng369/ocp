package com.topideal.supplychain.ocp.xiaomi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * @author wanzhaozhang
 * @date 2019/12/30
 * @description
 **/
public class ResponseDto implements Serializable {
    private Integer code;
    private String message;
    private ResultDto result;

    @JsonIgnore
    public Boolean isSuccess() {
        return code == 0;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultDto getResult() {
        return result;
    }

    public void setResult(ResultDto result) {
        this.result = result;
    }
}

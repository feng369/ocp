package com.topideal.supplychain.ocp.esd.dto;

import java.io.Serializable;

/**
 * @ClassName EsdResponse
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2019/12/11 14:18
 * @Version 1.0
 **/
public class EsdResponse implements Serializable {

    private String code; //返回码
    private Integer status; //返回状态枚举1（成功），-1（异常）
    private String sub_msg; //返回码描述
    //private String body; //返回参数集合

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSub_msg() {
        return sub_msg;
    }

    public void setSub_msg(String sub_msg) {
        this.sub_msg = sub_msg;
    }

    /*public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }*/
}

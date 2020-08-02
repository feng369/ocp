package com.topideal.supplychain.ocp.kjb.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @description: 跨境宝接单回执信息dto
 * @author: syq
 * @create: 2019-12-19 16:15
 **/
public class KjbReceiveRespDto implements Serializable {

    private static final long serialVersionUID = -807035170184052525L;

    private String code;

    private Integer status;

    @JsonProperty("sub_msg")
    private String subMsg;

    private String body;

    public static class Body{
        public Body() {
        }

        public Body(String errMsg) {
            this.errMsg = errMsg;
        }

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String errMsg;

        public String getErrMsg() {
            return errMsg;
        }

        public void setErrMsg(String errMsg) {
            this.errMsg = errMsg;
        }
    }

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

    public String getSubMsg() {
        return subMsg;
    }

    public void setSubMsg(String subMsg) {
        this.subMsg = subMsg;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

package com.topideal.supplychain.ocp.pub.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


public class PubResponseDto implements Serializable {

    private static final long serialVersionUID = 8131438190993872017L;

    private String code;

    private Integer status;

    @JsonProperty("sub_msg")
    private String subMsg;

    private String body;

    public static class Body{
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String errMsg;

        public Body() {
        }

        public Body(String errMsg) {
            this.errMsg = errMsg;
        }

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

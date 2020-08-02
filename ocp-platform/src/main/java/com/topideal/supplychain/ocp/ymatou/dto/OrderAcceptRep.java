package com.topideal.supplychain.ocp.ymatou.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author wanzhaozhang
 * @date 2020/4/20
 * @description 订单接单回执
 **/
public class OrderAcceptRep implements Serializable {
    //返回响应代码 0-成功
    private String code;
    //message
    private String message;
    //JSON Object
    private Content content;

    public static class Content implements Serializable{
      private List<Result> results;

        public List<Result> getResults() {
            return results;
        }

        public void setResults(List<Result> results) {
            this.results = results;
        }

        public static class Result implements Serializable{
          private String msg;
          private String exec_success;
          private String order_id;

            public String getMsg() {
                return msg;
            }

            public void setMsg(String msg) {
                this.msg = msg;
            }

            public String getExec_success() {
                return exec_success;
            }

            public void setExec_success(String exec_success) {
                this.exec_success = exec_success;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }
        }
    }

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

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
}

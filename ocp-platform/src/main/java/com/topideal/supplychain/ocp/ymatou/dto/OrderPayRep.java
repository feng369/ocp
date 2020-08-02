package com.topideal.supplychain.ocp.ymatou.dto;

/**
 * @author wanzhaozhang
 * @date 2020/4/20
 * @description 订单支付回执
 **/
public class OrderPayRep {
    //返回响应代码 0-成功
    private String code;
    //message
    private String message;
    //JSON Object
    private Content content;

    public class Content{
        private Result result;

        public Result getResult() {
            return result;
        }

        public void setResult(Result result) {
            this.result = result;
        }

        public class Result{
           private String msg;
           private String exec_success;
           private String order_id;
           private String declare_no;
           private String order_no;
           private String pay_transaction_id;
           private String identity_check;

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

           public String getDeclare_no() {
               return declare_no;
           }

           public void setDeclare_no(String declare_no) {
               this.declare_no = declare_no;
           }

           public String getOrder_no() {
               return order_no;
           }

           public void setOrder_no(String order_no) {
               this.order_no = order_no;
           }

           public String getPay_transaction_id() {
               return pay_transaction_id;
           }

           public void setPay_transaction_id(String pay_transaction_id) {
               this.pay_transaction_id = pay_transaction_id;
           }

           public String getIdentity_check() {
               return identity_check;
           }

           public void setIdentity_check(String identity_check) {
               this.identity_check = identity_check;
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

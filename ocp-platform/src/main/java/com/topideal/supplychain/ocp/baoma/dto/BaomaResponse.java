package com.topideal.supplychain.ocp.baoma.dto;

import com.topideal.supplychain.ocp.order.model.OrderBaoma;

import java.io.Serializable;
import java.util.List;

/**
 * @author wanzhaozhang
 * @date 2019/12/13
 * @description
 **/
public class BaomaResponse implements Serializable {
    //200-299为成功，其余为失败
    private String code;
   //true false
    private Boolean success;
    private String msg;
    private List<OrderBaoma> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<OrderBaoma> getData() {
        return data;
    }

    public void setData(List<OrderBaoma> data) {
        this.data = data;
    }
}

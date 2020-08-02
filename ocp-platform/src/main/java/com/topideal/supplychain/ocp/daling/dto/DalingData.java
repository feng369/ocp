package com.topideal.supplychain.ocp.daling.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author xuxiaoyan
 * @date 2019-12-16 10:11
 */
public class DalingData implements Serializable{

    private static final long serialVersionUID = -863137041260203942L;

    private String retBool;

    private Integer total;

    private String message;

    private Integer code;

    private DalingDataInfo t;

    private String other;

    private Integer totalNum;

    public String getRetBool() {
        return retBool;
    }

    public void setRetBool(String retBool) {
        this.retBool = retBool;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public DalingDataInfo getT() {
        return t;
    }

    public void setT(DalingDataInfo t) {
        this.t = t;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }
}

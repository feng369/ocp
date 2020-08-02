package com.topideal.supplychain.ocp.beibei.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author wanzhaozhang
 * @date 2020/3/23
 * @description 贝贝订单抓取回执实体类
 **/
public class BeibeiResponseDto implements Serializable {
    private boolean success;
    private String message;
    private List<BeibeiOrderDto> data;
    private Integer count;
    private List<String> oids;

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

    public List<BeibeiOrderDto> getData() {
        return data;
    }

    public void setData(List<BeibeiOrderDto> data) {
        this.data = data;
    }

    public List<String> getOids() {
        return oids;
    }

    public void setOids(List<String> oids) {
        this.oids = oids;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}

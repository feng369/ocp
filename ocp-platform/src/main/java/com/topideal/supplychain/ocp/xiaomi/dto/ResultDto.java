package com.topideal.supplychain.ocp.xiaomi.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author wanzhaozhang
 * @date 2019/12/30
 * @description
 **/
public class ResultDto implements Serializable {
    // 查询总数
    private Integer total;

    private List<DataDto> data;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<DataDto> getData() {
        return data;
    }

    public void setData(List<DataDto> data) {
        this.data = data;
    }
}

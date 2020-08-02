package com.topideal.supplychain.ocp.gs.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName ResultData
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2020/4/26 18:12
 * @Version 1.0
 **/
public class ResultData implements Serializable {

    private Integer total;

    private List<OrderGsReqDto> rows;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<OrderGsReqDto> getRows() {
        return rows;
    }

    public void setRows(List<OrderGsReqDto> rows) {
        this.rows = rows;
    }
}

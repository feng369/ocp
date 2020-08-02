package com.topideal.supplychain.ocp.dxy.dto;

import java.io.Serializable;

/**
 * @ClassName DxyPageBean
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2020/6/30 15:57
 * @Version 1.0
 **/
public class DxyPageBean implements Serializable {

    private int pageNo;
    private int pageSize;
    private int totalCount;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}

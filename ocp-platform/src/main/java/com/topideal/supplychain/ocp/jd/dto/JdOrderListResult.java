package com.topideal.supplychain.ocp.jd.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName JdOrderListResult
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2020/2/26 15:51
 * @Version 1.0
 **/
public class JdOrderListResult implements Serializable {

    private int pageSize;

    private int totalCount;

    private int page;

    private JdHeader header;

    private List<String> body;

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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public JdHeader getHeader() {
        return header;
    }

    public void setHeader(JdHeader header) {
        this.header = header;
    }

    public List<String> getBody() {
        return body;
    }

    public void setBody(List<String> body) {
        this.body = body;
    }
}

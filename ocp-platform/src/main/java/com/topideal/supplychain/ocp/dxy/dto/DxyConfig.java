package com.topideal.supplychain.ocp.dxy.dto;

import java.io.Serializable;

/**
 * @ClassName DxyConfig
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2020/6/30 16:20
 * @Version 1.0
 **/
public class DxyConfig implements Serializable {

    private String supplierId;

    private String token;

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

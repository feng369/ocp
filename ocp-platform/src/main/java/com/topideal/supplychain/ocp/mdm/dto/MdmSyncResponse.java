package com.topideal.supplychain.ocp.mdm.dto;

import java.util.List;

/**
 * @description: 主数据同步商品回执
 * @author: syq
 * @create: 2019-12-10 10:17
 **/
public class MdmSyncResponse {

    private String flag; //结果SUCCESS,FAILURE

    private String code; //错误代码

    private String message; //错误信息

    private String errInfo; //错误数据

    private List<MdmSyncResultInfo> syncResultInfo; //新增信息

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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

    public String getErrInfo() {
        return errInfo;
    }

    public void setErrInfo(String errInfo) {
        this.errInfo = errInfo;
    }

    public List<MdmSyncResultInfo> getSyncResultInfo() {
        return syncResultInfo;
    }

    public void setSyncResultInfo(List<MdmSyncResultInfo> syncResultInfo) {
        this.syncResultInfo = syncResultInfo;
    }
}

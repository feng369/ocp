package com.topideal.supplychain.ocp.jd.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.io.Serializable;

/**
 * @ClassName QingDanInfo
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2020/2/26 16:07
 * @Version 1.0
 **/
public class QingDanInfo implements Serializable {

    @JsonAlias({"V_COMP_PLATFORM_NO","ebcCode"})
    private String vCompPlatformNo;
    @JsonAlias({"EbpCode","ebpCode"})
    private String ebpCode;
    @JsonAlias({"V_IEPC","voIepc"})
    private String vIepc;
    @JsonAlias({"V_QY_STATE","voQyState"})
    private String vQyState;
    @JsonAlias({"CiqbCode","ciqbCode"})
    private String ciqbCode;
    @JsonAlias({"PayCode","payCode"})
    private String payCode;
    @JsonAlias({"PayName","payName"})
    private String payName;
    @JsonAlias({"PayTransactionId","paymentNo"})
    private String payTransactionId;
    @JsonAlias({"ReceiveNo","receiveNo"})
    private String receiveNo;

    public String getvCompPlatformNo() {
        return vCompPlatformNo;
    }

    public void setvCompPlatformNo(String vCompPlatformNo) {
        this.vCompPlatformNo = vCompPlatformNo;
    }

    public String getEbpCode() {
        return ebpCode;
    }

    public void setEbpCode(String ebpCode) {
        this.ebpCode = ebpCode;
    }

    public String getvIepc() {
        return vIepc;
    }

    public void setvIepc(String vIepc) {
        this.vIepc = vIepc;
    }

    public String getvQyState() {
        return vQyState;
    }

    public void setvQyState(String vQyState) {
        this.vQyState = vQyState;
    }

    public String getCiqbCode() {
        return ciqbCode;
    }

    public void setCiqbCode(String ciqbCode) {
        this.ciqbCode = ciqbCode;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public String getPayTransactionId() {
        return payTransactionId;
    }

    public void setPayTransactionId(String payTransactionId) {
        this.payTransactionId = payTransactionId;
    }

    public String getReceiveNo() {
        return receiveNo;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }
}

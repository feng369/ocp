package com.topideal.supplychain.ocp.mdm.dto;

/**
 * @description: 主数据同步信息
 * @author: syq
 * @create: 2019-12-10 10:19
 **/
public class MdmSyncResultInfo {

    private String opgcode; //商品业务ID
    private String ccode; //客户Id
    private String gcode; //商品编码
    private String prdcode; //产品ID

    public String getOpgcode() {
        return opgcode;
    }

    public void setOpgcode(String opgcode) {
        this.opgcode = opgcode;
    }

    public String getCcode() {
        return ccode;
    }

    public void setCcode(String ccode) {
        this.ccode = ccode;
    }

    public String getGcode() {
        return gcode;
    }

    public void setGcode(String gcode) {
        this.gcode = gcode;
    }

    public String getPrdcode() {
        return prdcode;
    }

    public void setPrdcode(String prdcode) {
        this.prdcode = prdcode;
    }
}

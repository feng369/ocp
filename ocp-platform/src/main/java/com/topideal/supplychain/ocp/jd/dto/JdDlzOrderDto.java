package com.topideal.supplychain.ocp.jd.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName jdOrder
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2020/2/26 15:56
 * @Version 1.0
 **/
public class JdDlzOrderDto implements Serializable {

    /**自营非自营订单主体***/
    private DlzWaybillInfo waybillInfo;

    private QingDanInfo qingDanInfo;

    private List<JdOrderItemDto> listGoods;

    public DlzWaybillInfo getWaybillInfo() {
        return waybillInfo;
    }

    public void setWaybillInfo(DlzWaybillInfo waybillInfo) {
        this.waybillInfo = waybillInfo;
    }

    public QingDanInfo getQingDanInfo() {
        return qingDanInfo;
    }

    public void setQingDanInfo(QingDanInfo qingDanInfo) {
        this.qingDanInfo = qingDanInfo;
    }

    public List<JdOrderItemDto> getListGoods() {
        return listGoods;
    }

    public void setListGoods(List<JdOrderItemDto> listGoods) {
        this.listGoods = listGoods;
    }

}

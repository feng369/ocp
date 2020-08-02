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
public class JdOrderDto implements Serializable {

    /**自营非自营订单主体***/
    private WaybillInfo waybillInfo;

    private QingDanInfo qingDanInfo;

    private List<JdOrderItemDto> listGoods;

    /**云霄购订单主体***/
    private GuangZhouYunXiaoOrderEntity guangZhouYunXiaoOrderEntity;

    private List<GuangZhouYunXiaoItemEntities> guangZhouYunXiaoItemEntities;

    public WaybillInfo getWaybillInfo() {
        return waybillInfo;
    }

    public void setWaybillInfo(WaybillInfo waybillInfo) {
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

    public GuangZhouYunXiaoOrderEntity getGuangZhouYunXiaoOrderEntity() {
        return guangZhouYunXiaoOrderEntity;
    }

    public void setGuangZhouYunXiaoOrderEntity(
            GuangZhouYunXiaoOrderEntity guangZhouYunXiaoOrderEntity) {
        this.guangZhouYunXiaoOrderEntity = guangZhouYunXiaoOrderEntity;
    }

    public List<GuangZhouYunXiaoItemEntities> getGuangZhouYunXiaoItemEntities() {
        return guangZhouYunXiaoItemEntities;
    }

    public void setGuangZhouYunXiaoItemEntities(
            List<GuangZhouYunXiaoItemEntities> guangZhouYunXiaoItemEntities) {
        this.guangZhouYunXiaoItemEntities = guangZhouYunXiaoItemEntities;
    }
}

package com.topideal.supplychain.ocp.pdd.dto;

import java.util.List;

/**
 * @author klw
 * @date 2019/12/13 16:49
 * @description:
 */
public class CatchOrderPddDepotDto {

    private List<CatchOrderPddWareDto> wareSubInfoList;

    private String wareSn;

    private Integer wareType;

    private String wareName;

    private String wareId;

    private String depotCode;

    private String depotName;

    private String depotId;

    private Integer depotType;

    public List<CatchOrderPddWareDto> getWareSubInfoList() {
        return wareSubInfoList;
    }

    public void setWareSubInfoList(List<CatchOrderPddWareDto> wareSubInfoList) {
        this.wareSubInfoList = wareSubInfoList;
    }

    public String getWareSn() {
        return wareSn;
    }

    public void setWareSn(String wareSn) {
        this.wareSn = wareSn;
    }

    public Integer getWareType() {
        return wareType;
    }

    public void setWareType(Integer wareType) {
        this.wareType = wareType;
    }

    public String getWareName() {
        return wareName;
    }

    public void setWareName(String wareName) {
        this.wareName = wareName;
    }

    public String getWareId() {
        return wareId;
    }

    public void setWareId(String wareId) {
        this.wareId = wareId;
    }

    public String getDepotCode() {
        return depotCode;
    }

    public void setDepotCode(String depotCode) {
        this.depotCode = depotCode;
    }

    public String getDepotName() {
        return depotName;
    }

    public void setDepotName(String depotName) {
        this.depotName = depotName;
    }

    public String getDepotId() {
        return depotId;
    }

    public void setDepotId(String depotId) {
        this.depotId = depotId;
    }

    public Integer getDepotType() {
        return depotType;
    }

    public void setDepotType(Integer depotType) {
        this.depotType = depotType;
    }
}

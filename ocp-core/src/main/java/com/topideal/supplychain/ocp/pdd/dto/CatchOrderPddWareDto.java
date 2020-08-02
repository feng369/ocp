package com.topideal.supplychain.ocp.pdd.dto;

/**
 * @author klw
 * @date 2019/12/13 16:48
 * @description:
 */
public class CatchOrderPddWareDto {
    private String wareSn;

    private Long wareQuantity;

    private String wareName;

    private Long wareId;

    public String getWareSn() {
        return wareSn;
    }

    public void setWareSn(String wareSn) {
        this.wareSn = wareSn;
    }

    public Long getWareQuantity() {
        return wareQuantity;
    }

    public void setWareQuantity(Long wareQuantity) {
        this.wareQuantity = wareQuantity;
    }

    public String getWareName() {
        return wareName;
    }

    public void setWareName(String wareName) {
        this.wareName = wareName;
    }

    public Long getWareId() {
        return wareId;
    }

    public void setWareId(Long wareId) {
        this.wareId = wareId;
    }
}

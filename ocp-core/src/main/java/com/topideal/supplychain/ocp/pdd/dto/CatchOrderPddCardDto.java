package com.topideal.supplychain.ocp.pdd.dto;

/**
 * @author klw
 * @date 2019/12/13 16:45
 * @description:
 */
public class CatchOrderPddCardDto {
    private String cardNo;

    private String maskPassword;

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getMaskPassword() {
        return maskPassword;
    }

    public void setMaskPassword(String maskPassword) {
        this.maskPassword = maskPassword;
    }
}

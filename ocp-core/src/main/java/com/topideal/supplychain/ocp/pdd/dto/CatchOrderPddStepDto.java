package com.topideal.supplychain.ocp.pdd.dto;

import java.math.BigDecimal;

/**
 * @author klw
 * @date 2019/12/13 16:47
 * @description:
 */
public class CatchOrderPddStepDto {

    private BigDecimal advancedPaidFee;
    private BigDecimal stepPaidFee;
    private BigDecimal stepDiscountAmount;
    private Integer stepTradeStatus;

    public BigDecimal getAdvancedPaidFee() {
        return advancedPaidFee;
    }

    public void setAdvancedPaidFee(BigDecimal advancedPaidFee) {
        this.advancedPaidFee = advancedPaidFee;
    }

    public BigDecimal getStepPaidFee() {
        return stepPaidFee;
    }

    public void setStepPaidFee(BigDecimal stepPaidFee) {
        this.stepPaidFee = stepPaidFee;
    }

    public BigDecimal getStepDiscountAmount() {
        return stepDiscountAmount;
    }

    public void setStepDiscountAmount(BigDecimal stepDiscountAmount) {
        this.stepDiscountAmount = stepDiscountAmount;
    }

    public Integer getStepTradeStatus() {
        return stepTradeStatus;
    }

    public void setStepTradeStatus(Integer stepTradeStatus) {
        this.stepTradeStatus = stepTradeStatus;
    }
}

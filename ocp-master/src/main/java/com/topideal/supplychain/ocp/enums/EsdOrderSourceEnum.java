package com.topideal.supplychain.ocp.enums;

/**
 * @ClassName: OrderSourceEnum
 * @Description: 推ESD订单来源
 * @Author: huyu
 * @Date: 2019/1/14 9:25
 */
public enum EsdOrderSourceEnum {
    STD_ORDER(10),
    KJB_ORDER(20),
    QM_ORDER(30),
    TPL_ORDER(40),
    JD_ORDER(50),
    YZ_ORDER(60),
    VP_ORDER(100),
    ;

    EsdOrderSourceEnum(Integer value) {
        this.value = value;
    }

    private Integer value;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}

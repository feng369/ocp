package com.topideal.supplychain.ocp.fx.dto;

import com.topideal.supplychain.enumeration.IntegerEnum;

/**
 * 标题：大订单退单请求参数校验状态枚举
 * 模块：ocp-parent
 * 版权: Copyright © 2019 topideal
 * 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.big.dto
 * 作者：songping
 * 创建日期：2019/12/31 17:14
 *
 * @version 1.0
 */
public enum FxValidateStatusEnum implements IntegerEnum {

    SUCCESS(1,"校验成功"),
    FAILURE(2,"校验失败")
    ;

    private Integer value;
    private String desc;

    FxValidateStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static FxValidateStatusEnum getValueEnum(Integer value) {
        for (FxValidateStatusEnum bigValidateStatusEnum : FxValidateStatusEnum
                .values()) {
            if (bigValidateStatusEnum.value.equals(value)) {
                return bigValidateStatusEnum;
            }
        }
        return null;
    }
}

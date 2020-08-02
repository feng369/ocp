package com.topideal.supplychain.ocp.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.topideal.supplychain.enumeration.IntegerEnum;

/**
 * 标题：大订单业务类型枚举
 * 模块：ocp-parent
 * 版权: Copyright © 2019 topideal
 * 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.enums
 * 作者：songping
 * 创建日期：2020/1/6 10:14
 *
 * @version 1.0
 */
public enum BigBusiTypeEnum implements IntegerEnum {

    BBC(10, "BBC"),
    BC(20, "BC"),
    BN(30, "保留"),
    CC(40, "CC"),
    BBC_OUT(50, "BBC出口"),
    EXP(60, "BC出口"),
    ;

    private Integer value;
    private String desc;

    BigBusiTypeEnum(Integer value, String desc) {
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
    @JsonValue
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static BigBusiTypeEnum getValueEnum(Integer value) {
        for (BigBusiTypeEnum bigBusiTypeEnum : BigBusiTypeEnum.values()) {
            if (bigBusiTypeEnum.value.equals(value)) {
                return bigBusiTypeEnum;
            }
        }
        return null;
    }
}

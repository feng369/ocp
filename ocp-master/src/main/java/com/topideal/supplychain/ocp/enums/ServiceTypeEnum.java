package com.topideal.supplychain.ocp.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.topideal.supplychain.enumeration.IntegerEnum;

/**
 * @author syq
 * @date 2019/12/16 13:43
 * @description: 订单推ESD服务类型枚举
 */
public enum ServiceTypeEnum implements IntegerEnum {
    ALL(0, "全部"),
    OVERSEA_LOCATION(1, "海外仓"),
    GLOBAL_LOGISTICS(2, "国际物流"),
    CLEARANCE(3, "清关"),
    DISPATCH(4, "配送"),
    ;

    private Integer value;

    private String desc;

    ServiceTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
    @JsonValue
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

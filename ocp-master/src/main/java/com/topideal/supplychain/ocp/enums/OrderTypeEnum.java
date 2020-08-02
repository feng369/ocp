package com.topideal.supplychain.ocp.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.topideal.supplychain.enumeration.IntegerEnum;

/**
 * @author klw
 * @date 2019/12/12 13:43
 * @description: 订单类型枚举
 */
public enum OrderTypeEnum implements IntegerEnum {
    RECEIVE(1, "接单"),
    CATCH(2, "抓单"),
    ;

    private Integer value;

    private String desc;

    OrderTypeEnum(Integer value, String desc) {
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

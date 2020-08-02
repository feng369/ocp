package com.topideal.supplychain.ocp.enums;


import com.fasterxml.jackson.annotation.JsonValue;
import com.topideal.supplychain.enumeration.IntegerEnum;

/**
 * @ClassName: StatusEnum
 * @Description: 订单状态枚举
 */
public enum SyncStatusEnum implements IntegerEnum {
    NOT_SYNC(10, "未同步"),
    HAVE_SYNC(20, "已同步"),
    SYNC_FAIL(30, "同步失败")
    ;

    private Integer value;
    private String desc;

    SyncStatusEnum(Integer value, String desc) {
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

}

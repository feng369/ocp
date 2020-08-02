package com.topideal.supplychain.ocp.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.topideal.supplychain.enumeration.IntegerEnum;
import com.topideal.supplychain.enumeration.StringEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 标题：大订单订单类型枚举
 * 模块：ocp-parent
 * 版权: Copyright © 2019 topideal
 * 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.enums
 * 作者：songping
 * 创建日期：2019/12/18 14:57
 *
 * @version 1.0
 */
public enum BigOrderTypeEnum implements IntegerEnum {

    //1:非自营;2:自营;3:ECLP;4:其他;5:多渠道;6:独立站;7:云霄
    FZY(1,"非自营"),
    ZY(2,"自营"),
    ECLP(3,"ECLP"),
    QT(4,"其他"),
    DQD(5,"多渠道"),
    DLZ(6,"独立站"),
    YX(7,"云霄"),
    ;

    private Integer value;
    private String desc;

    BigOrderTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * 获取枚举的value集合
     * @return
     */
    public static List<Integer> getEnumValues(){
        List<Integer> list = new ArrayList<>();
        for (BigOrderTypeEnum o :BigOrderTypeEnum.values()) {
            list.add(o.value);
        }
        return list;
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

    public static BigOrderTypeEnum getValueEnum(Integer value) {
        for (BigOrderTypeEnum bigOrderTypeEnum : BigOrderTypeEnum.values()) {
            if (bigOrderTypeEnum.value.equals(value)) {
                return bigOrderTypeEnum;
            }
        }
        return null;
    }
}

package com.topideal.supplychain.ocp.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.topideal.supplychain.enumeration.IntegerEnum;
import com.topideal.supplychain.enumeration.StringEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 标题：海关类型枚举
 * 模块：ocp-parent
 * 版权: Copyright © 2019 topideal
 * 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.enums
 * 作者：songping
 * 创建日期：2019/12/18 14:42
 *
 * @version 1.0
 */
public enum CustomsTypeEnum implements IntegerEnum {

    //1:总署版;2:2.0版
    V_ZS(1, "总署版"),
    V_J2(2, "2.0版"),
    ;

    CustomsTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * 获取枚举的value集合
     *
     * @return
     */
    public static List<Integer> getEnumValues() {
        List<Integer> list = new ArrayList<>();
        for (CustomsTypeEnum o : CustomsTypeEnum.values()) {
            list.add(o.getValue());
        }
        return list;
    }

    public static String getEnumInfo() {
        List<Map<Integer, String>> list = new ArrayList<>();
        for (CustomsTypeEnum o : CustomsTypeEnum.values()) {
            Map<Integer, String> map = new HashMap<>();
            map.put(o.value, o.desc);
            list.add(map);
        }
        return list.toString();
    }

    private Integer value;
    private String desc;

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

    public static CustomsTypeEnum getValueEnum(Integer value) {
        for (CustomsTypeEnum customsTypeEnum : CustomsTypeEnum.values()) {
            if (customsTypeEnum.value.equals(value)) {
                return customsTypeEnum;
            }
        }
        return null;
    }
}

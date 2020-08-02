package com.topideal.supplychain.ocp.enums;

import com.topideal.supplychain.enumeration.StringEnum;

/**
 * 标题：换单标志枚举
 * 模块：ocp-parent
 * 版权: Copyright © 2019 topideal
 * 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.enums
 * 作者：songping
 * 创建日期：2020/1/7 15:50
 *
 * @version 1.0
 */
public enum BigChangeFlagEnum implements StringEnum {

    NO("0", "运单不需要换成国内落地配运单"),
    YES("1", "运单需要换成国内落地配运单"),
    ;

    private String value;
    private String desc;

    BigChangeFlagEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static BigChangeFlagEnum getValueEnum(String value) {
        for (BigChangeFlagEnum bigChangeFlagEnum : BigChangeFlagEnum.values()) {
            if (bigChangeFlagEnum.value.equals(value)){
                return bigChangeFlagEnum;
            }
        }
        return null;
    }
}

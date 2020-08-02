package com.topideal.supplychain.ocp.enums;

import com.topideal.supplychain.enumeration.StringEnum;

/**
 * <p>标题: TODO</p>
 * <p>功能: TODO</p>
 * <p>模块: ocp2</p>
 * <p>版权: Copyright © 2020 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.enums</p>
 * <p>作者: topIdeal</p>
 * <p>创建日期: 2020-01-15 14:55</p>
 *
 * @version 1.0
 */
public enum GsIsSendKjbEnum implements StringEnum {
    OLD("0","原始数据"),
    NEW("1","调用后的数据")
    ;

    private String value;
    private String desc;

    GsIsSendKjbEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}

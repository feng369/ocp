package com.topideal.supplychain.ocp.enums;

import com.topideal.supplychain.enumeration.IntegerEnum;
import com.topideal.supplychain.enumeration.StringEnum;

/**
 * <p>标题: TODO</p>
 * <p>功能: TODO</p>
 * <p>模块: ocp2</p>
 * <p>版权: Copyright © 2020 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.enums</p>
 * <p>作者: topIdeal</p>
 * <p>创建日期: 2020-01-10 17:42</p>
 *
 * @version 1.0
 */
public enum GsFreezeStatusEnum implements IntegerEnum {
    FREEZE_0(0,"未冻结"),
    FREEZE_1(1,"已冻结"),
    FREEZE_2(2,"已解冻"),
    FREEZE_3(3,"永久冻结"),
    ;

    private Integer value;
    private String desc;

    GsFreezeStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}

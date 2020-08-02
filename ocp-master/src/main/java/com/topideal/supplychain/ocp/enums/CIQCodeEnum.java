package com.topideal.supplychain.ocp.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.topideal.supplychain.enumeration.StringEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 标题：国检码表枚举
 * 模块：ocp-parent
 * 版权: Copyright © 2019 topideal
 * 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.enums
 * 作者：songping
 * 创建日期：2019/12/18 15:17
 *
 * @version 1.0
 */
public enum CIQCodeEnum implements StringEnum {

    NSJ_BB("000069","南沙局本部"),
    HPJ_BB("000072","黄埔局本部"),
    NSJ_JZ_BSC("000094","南沙局金洲办事处"),
    ZYG("443433","状元谷"),
    //XZ1("442100","442100"),
    ;

    CIQCodeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    private String value;
    private String desc;

    @Override
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
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

    public static CIQCodeEnum getValueEnum(String value) {
        for (CIQCodeEnum ciqCodeEnum : CIQCodeEnum.values()) {
            if (ciqCodeEnum.value.equals(value)) {
                return ciqCodeEnum;
            }
        }
        return null;
    }

    /**
     * 获取value
     * @return
     */
    public static List<String> getValues(){
        List<String> list = new ArrayList<>();
        for (CIQCodeEnum o : CIQCodeEnum.values()) {
            list.add(o.value);
        }
        return list;
    }

    /**
     * 获取枚举详情
     * @return
     */
    public static String getEnumInfo() {
        List<Map<String, String>> list = new ArrayList<>();
        for (CIQCodeEnum o : CIQCodeEnum.values()) {
            Map<String, String> map = new HashMap<>();
            map.put(o.value, o.desc);
            list.add(map);
        }
        return list.toString();
    }
}

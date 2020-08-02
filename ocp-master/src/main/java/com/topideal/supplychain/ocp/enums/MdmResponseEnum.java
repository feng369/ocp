package com.topideal.supplychain.ocp.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.topideal.supplychain.enumeration.StringEnum;

/**
 * @description: 奇门商品推主数据回执枚举
 * @author: syq
 * @create: 2019-12-09 15:53
 **/
public enum MdmResponseEnum implements StringEnum {

    SUCCESS("0", "成功"),
    SERVICE_CLOSE("100", "服务已关闭"),
    MAX_WAIT_NUM("101", "超出最大等待数"),
    WAIT_TIMEOUT("102", "等待超时"),
    ILLEGAL("103", "非法接入"),
    FORMAT_ERROR("200", "数据格式错误"),
    REQUIRE_PARAM_ERROR("201", "请求参数错误"),
    DATA_CONFLICT("202", "同步数据与现有数据冲突"),
    NO_PRIMARY_KEY("203", "更新数据找不到主键"),
    FAILURE("300", "处理失败"),
    VERSION_ERROR("301", "版本号错误"),
    REQUIRE_TIMEOUT("302", "请求超时"),
    DB_ERROR("901", "数据库异常请重试"),
    SYS_ERROR("902", "系统异常请重试"),
    ;

    private String value;
    private String desc;

    MdmResponseEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    @JsonValue
    public String getDesc() {
        return desc;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


}

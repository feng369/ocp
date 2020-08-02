package com.topideal.supplychain.ocp.master.model;

import com.topideal.supplychain.common.model.BaseEntity;

/**
 * @author hhl
 * @date 2018/12/27
 */
public class Platform extends BaseEntity {


    private String code;

    private String virtualCode;

    private String name;

    private String shortName;

    private String arguments;

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArguments() {
        return arguments;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments;
    }

    public String getVirtualCode() {
        return virtualCode;
    }

    public void setVirtualCode(String virtualCode) {
        this.virtualCode = virtualCode;
    }
}

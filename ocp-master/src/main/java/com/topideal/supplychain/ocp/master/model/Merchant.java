package com.topideal.supplychain.ocp.master.model;

import com.topideal.supplychain.common.model.BaseEntity;
import com.topideal.supplychain.enumeration.YesOrNoEnum;

/**
 * @author hhl
 * @date 2018/12/27
 */
public class Merchant extends BaseEntity {

    private String code;

    private String name;

    private String shortName;

    private String arguments;

    /**
     * 是否税金分离：1-是，0-否，默认0
     */
    private YesOrNoEnum taxFlag;
    private String taxFlagStr;

    public String getTaxFlagStr() {
        return taxFlagStr;
    }

    public void setTaxFlagStr(String taxFlagStr) {
        this.taxFlagStr = taxFlagStr;
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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getArguments() {
        return arguments;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments;
    }

    public YesOrNoEnum getTaxFlag() {
        return taxFlag;
    }

    public void setTaxFlag(YesOrNoEnum taxFlag) {
        this.taxFlag = taxFlag;
    }
}

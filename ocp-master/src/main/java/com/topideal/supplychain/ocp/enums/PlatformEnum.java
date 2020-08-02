package com.topideal.supplychain.ocp.enums;

/**
 * @ClassName PlatformEnum
 * @TODO 平台编码
 * @Author zhangzhihao
 * @DATE 2019/11/28 11:26
 * @Version 1.0
 **/
public enum PlatformEnum {

    VP("VP00000001","全平台"),
    YOUZAN("youzan","有赞"),
    PDD("pdd","拼多多"),
    YMATOU("ymatou","洋码头"),
    VIP("vip","唯品会"),
    PUB("pub","标准"),
    GLOBAL("global","全球仓"),
    BAOMA("baoma","宝妈时光"),
    HIPAC("hipac","海拍客"),
    KJB("kjb","跨境宝"),
    XIAOMI("xiaomi","小米"),
    JD("jd","京东"),
    JD_YX("jd_yx","京东云霄"),
    JD_DLZ("jd_dlz","京东多渠道独立站"),
    GS("gs","环球捕手"),
    DALING("daling","达令家"),
    BEIBEI("beibei","贝贝"),
    DXY("dxy","丁香医生"),
    AMWAY("amway", "安利"),
    ;

    private String code;

    private String name;

    PlatformEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}

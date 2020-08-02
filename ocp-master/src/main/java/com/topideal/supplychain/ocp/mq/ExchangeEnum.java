package com.topideal.supplychain.ocp.mq;

import com.topideal.supplychain.mq.BasicExchangeEnum;

/**
 * @author zzh 功能相同的队列使用同一个交换机 交换机常量
 */
public enum ExchangeEnum implements BasicExchangeEnum{

    MQ_EXCHANGE_YOUZAN("ocp.exchange.youzan", "有赞交换机"),
    /**
     * 唯品会订单交换机
     **/
    MQ_EXCHANGE_VIP("ocp.exchange.vip", "唯品会交换机"),
    /**
     * 唯品会订单交换机
     **/
    MQ_EXCHANGE_PDD("ocp.exchange.pdd", "拼多多交换机"),
    /**
     * ymatou订单交换机
     **/
    MQ_EXCHANGE_YMATOU("ocp.exchange.ymatou", "洋码头交换机"),

    /**
     * 税金分离交换机
     */
    MQ_EXCHANGE_GEMINI("ocp.exchange.gemini", "税金分离交换机"),

    /**
     * 全球仓订单交换机
     **/
    MQ_EXCHANGE_GLOBAL("ocp.exchange.global", "全球仓交换机"),
    /**
     * mdm主数据交换机
     **/
    MQ_EXCHANGE_MDM("ocp.exchange.mdm", "主数据交换机"),
    /**
     * 达令家订单交换机
     **/
    MQ_EXCHANGE_DALING("ocp.exchange.daling", "达令家交换机"),
    /**
     * 海拍客交换机
     */
    MQ_EXCHANGE_HIPAC("ocp.exchange.hipac", "海拍客交换机"),
    /**
     * op交换机
     **/
    MQ_EXCHANGE_OP("ocp.exchange.op", "OP交换机"),
    /**
     * OFC交换机
     **/
    MQ_EXCHANGE_OFC("ocp.exchange.ofc", "OFC交换机"),
    /**
     * OFC-BC交换机
     **/
    MQ_EXCHANGE_OFCBC("ocp.exchange.ofc-bc", "OFC-BC交换机"),
    /**
     * op交换机
     **/
    MQ_EXCHANGE_ESD("ocp.exchange.esd", "ESD交换机"),
    /**
     * op交换机
     **/
    MQ_EXCHANGE_KJB("ocp.exchange.kjb", "跨境宝交换机"),
    /**
     * baoma订单交换机
     **/
    MQ_EXCHANGE_BAOMA("ocp.exchange.baoma", "宝妈时光交换机"),
    /**
     * xiaomi订单交换机
     **/
    MQ_EXCHANGE_XIAOMI("ocp.exchange.xiaomi", "小米交换机"),
    /**
     * 环球捕手订单交换机
     **/
    MQ_EXCHANGE_GS("ocp.exchange.gs", "环球捕手交换机"),

    /**
     * 大订单交换机
     **/
    MQ_EXCHANGE_BIG("ocp.exchange.big", "大订单交换机"),

    /**
     * 安利交换机
     **/
    MQ_EXCHANGE_AMWAY("ocp.exchange.amway", "安利交换机"),

    /**
     * 京东交换机
     **/
    MQ_EXCHANGE_JD("ocp.exchange.jd", "京东交换机"),

    /**
     * 贝贝订单交换机
     **/
    MQ_EXCHANGE_BEIBEI("ocp.exchange.beibei", "贝贝交换机"),

    /**
     * 分销订单交换机
     */
    MQ_EXCHANGE_FX("ocp.exchange.fx", "分销订单交换机"),
    /**
     * 丁香医生交换机
     */
    MQ_EXCHANGE_DXY("ocp.exchange.dxy", "丁香医生订单交换机"),
    /**
     * E仓交换机
     **/
    MQ_EXCHANGE_EW("ocp.exchange.ew", "E仓交换机"),
    ;

    ExchangeEnum(String exchangeName, String exchangeShowName) {
        this.exchangeName = exchangeName;
        this.exchangeShowName = exchangeShowName;
    }

    private String exchangeName;

    private String exchangeShowName;

    public String getExchangeShowName() {
        return exchangeShowName;
    }

    public String getExchangeName() {
        return exchangeName;
    }


}

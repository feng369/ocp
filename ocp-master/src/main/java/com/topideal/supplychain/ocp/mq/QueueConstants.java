package com.topideal.supplychain.ocp.mq;

import com.topideal.supplychain.mq.BasicQueueEnum;

public class QueueConstants {

    public static final String TEST = "test1";


    /**
     * 有赞订单处理
     */
    public static final String OCP_YOUZAN_ORDER_PROCESS = "ocp.youzan.order.process";
    public static final String OCP_YOUZAN_GET_ORDER = "ocp.youzan.get.order";
    public static final String OCP_YOUZAN_GET_MISS_ORDER = "ocp.youzan.get.miss.order";
    public static final String OCP_YOUZAN_ORDER_TO_KJB = "ocp.youzan.order.send.kjb";
    public static final String OCP_YOUZAN_ORDER_TO_OP = "ocp.youzan.order.send.op";
    public static final String OCP_YOUZAN_ORDER_TO_OFC = "ocp.youzan.order.send.ofc";
    public static final String OCP_YOUZAN_ORDER_TO_ESD = "ocp.youzan.order.send.esd";

    /**
     * 拼多多订单处理
     */
    public static final String PDD_GET_ORDER = "ocp.pdd.get.order";
    public static final String PDD_GET_MISS_ORDER = "ocp.pdd.get.miss.order";
    public static final String PDD_CATCH_ORDER_PROCESS = "ocp.pdd.catch.order.process";
    public static final String PDD_RECEIVE_ORDER_PROCESS = "ocp.pdd.receive.order.process";
    public static final String PDD_ORDER_TO_KJB = "ocp.pdd.order.send.kjb";
    public static final String PDD_ORDER_TO_GEMINI = "ocp.pdd.order.send.gemini";
    public static final String PDD_ORDER_PUSH_ROUTER = "ocp.pdd.order.push.router";
    public static final String PDD_ORDER_TO_OFC = "ocp.pdd.order.send.ofc";
    public static final String PDD_ORDER_TO_OP = "ocp.pdd.order.send.op";
    public static final String PDD_ORDER_TO_ESD = "ocp.pdd.order.send.esd";
    /**
     * 唯品会订单
     */
    public static final String OCP_VIP_GET_ORDER = "ocp.vip.get.order";
    public static final String OCP_VIP_ORDER_PUSH_OFC = "ocp.vip.order.push.ofc";
    public static final String OCP_VIP_ORDER_PUSH_ESD = "ocp.vip.order.push.esd";
    public static final String OCP_VIP_ORDER_PROCESS = "ocp.vip.order.process";

    /**
     * 全球仓订单
     */
    public static final String OCP_GLOBAL_GET_ORDER = "ocp.global.get.order";
    public static final String OCP_GLOBAL_GET_MISS_ORDER = "ocp.global.get.miss.order";
    public static final String OCP_GLOBAL_ORDER_PUSH_OFC = "ocp.global.order.push.ofc";

    /**
     * 洋码头订单 推送db 推送组套 kjb 推送OFC 推送税金 推送OP 抓取订单 订单接单 订单支付
     */
    public static final String OCP_YMATOU_ORDER_PROCESS = "ocp.ymatou.order.process";
    public static final String OCP_YMATOU_ORDER_PUSH_KJB = "ocp.ymatou.order.push.kjb";
    public static final String OCP_YMATOU_ORDER_PUSH_OFC = "ocp.ymatou.order.push.ofc";
    public static final String OCP_YMATOU_ORDER_PUSH_GEMINI = "ocp.ymatou.order.push.gemini";
    public static final String OCP_YMATOU_ORDER_PUSH_OP = "ocp.ymatou.order.push.op";
    public static final String OCP_YMATOU_GRAB_ORDER = "ocp.ymatou.order.grab";
    public static final String OCP_YMATOU_TAKE_ORDER = "ocp.ymatou.order.take";
    public static final String OCP_YMATOU_PAY_ORDER = "ocp.ymatou.order.pay";

    /**
     * 主数据同步商品信息
     */
    public static final String OCP_MDM_SYNC_GOODS_INFO = "ocp.mdm.sync.goods.info";


    /**
     * 达令家订单
     */
    public static final String OCP_DALING_ORDER_PUSH_OP = "ocp.daling.order.push.op";
    public static final String OCP_DALING_ORDER_PUSH_OFC = "ocp.daling.order.push.ofc";
    public static final String OCP_DALING_ORDER_INFO_GRAB = "ocp.daling.order.info.grab";
    public static final String OCP_DALING_GET_ORDER = "ocp.daling.get.order";
    public static final String OCP_DALING_GET_MISS_ORDER = "ocp.daling.get.miss.order";
    public static final String OCP_DALING_ORDER_PROCESS = "ocp.daling.order.process";

    /**
     * 海拍客订单
     */
    public static final String OCP_HIPAC_ORDER_PUSH_OP = "ocp.hipac.order.push.op";
    public static final String OCP_HIPAC_ORDER_PUSH_OFC = "ocp.hipac.order.push.ofc";

    /**
     * 宝妈时光订单
     */
    public static final String OCP_BAOMA_GET_ORDER = "ocp.baoma.get.order";
    public static final String OCP_BAOMA_SAVE_ORDER = "ocp.baoma.save.order";
    public static final String OCP_BAOMA_ORDER_PUSH_OFC = "ocp.baoma.order.push.ofc";
    public static final String OCP_BAOMA_ORDER_PUSH_OP = "ocp.baoma.order.push.op";

    /**
     * 标准订单
     */
    public static final String OCP_PUB_ORDER_PUSH_ESD = "ocp.pub.order.push.esd";

    /**
     * 跨境宝订单
     */
    public static final String OCP_KJB_ORDER_PUSH_ESD = "ocp.kjb.order.push.esd";

    /**
     * 小米订单
     */
    public static final String OCP_XIAOMI_GET_ORDER = "ocp.xiaomi.get.order";
    public static final String OCP_XIAOMI_SAVE_ORDER = "ocp.xiaomi.save.order";
    public static final String OCP_XIAOMI_ORDER_PUSH_KJB = "ocp.xiaomi.order.push.kjb";
    public static final String OCP_XIAOMI_GET_MISS_ORDER = "ocp.xiaomi.get.miss.order";

    /**
     * 大订单接单
     */
    public static final String OCP_BIG_ORDER_PUSH_OFCBC = "ocp.big.order.push.ofc-bc";
    public static final String OCP_BIG_ORDER_PUSH_OFC = "ocp.big.order.push.ofc";
    public static final String OCP_BIG_ORDER_PUSH_OP = "ocp.big.order.push.op";
    public static final String OCP_BIG_ORDER_PROCESS = "ocp.big.order.process";

    /**
     * 安利接单
     */
    public static final String OCP_AMWAY_ORDER_PUSH_OFC = "ocp.amway.order.push.ofc";
    public static final String OCP_AMWAY_ORDER_PROCESS = "ocp.amway.order.process";

    /**
     * 环球捕手订单处理
     */
    public static final String OCP_GS_ORDER_PROCESS = "ocp.gs.order.process";
    public static final String OCP_GS_GET_ORDER = "ocp.gs.get.order";
    public static final String OCP_GS_GET_MISS_ORDER = "ocp.gs.get.miss.order";
    public static final String OCP_GS_ORDER_TO_KJB = "ocp.gs.order.send.kjb";
    public static final String OCP_GS_ORDER_TO_OP = "ocp.gs.order.send.op";
    public static final String OCP_GS_ORDER_TO_OFC = "ocp.gs.order.send.ofc";

    /**
     * 京东订单
     */
    public static final String OCP_JD_ORDER_TO_OFC = "ocp.jd.order.send.ofc";
    public static final String OCP_JD_GET_ORDER = "ocp.jd.get.order";
    public static final String OCP_JD_ORDER_PROCESS = "ocp.jd.process.order";
    public static final String OCP_JD_YX_GET_ORDER = "ocp.jd.yx.get.order";
    public static final String OCP_JD_YX_ORDER_PROCESS = "ocp.jd.yx.process.order";
    public static final String OCP_JD_DLZ_GET_ORDER = "ocp.jd.dlz.get.order";
    public static final String OCP_JD_DLZ_ORDER_PROCESS = "ocp.jd.dlz.process.order";

    /**
     * 贝贝订单
     */
    public static final String OCP_BEIBEI_GET_ORDER = "ocp.beibei.get.order";
    public static final String OCP_BEIBEI_SAVE_ORDER = "ocp.beibei.save.order";
    public static final String OCP_BEIBEI_ORDER_TO_OFC = "ocp.beibei.order.send.ofc";

    /**
     * 分销订单处理
     */
    public static final String OCP_FX_ORDER_PROCESS = "ocp.fx.process.order";
    public static final String OCP_FX_ORDER_TO_KJB = "ocp.fx.order.to.kjb";
    public static final String OCP_FX_ORDER_TO_OFC = "ocp.fx.order.to.ofc";
    public static final String OCP_FX_ORDER_TO_EW = "ocp.fx.order.to.ew";

    /**
     * 丁香医生队列
     */
    public static final String OCP_DXY_GET_ORDER = "ocp.dxy.get.order";
    public static final String OCP_DXY_PROCESS_ORDER = "ocp.dxy.process.order";
    public static final String OCP_DXY_ORDER_TO_OFC = "ocp.dxy.order.to.ofc";
    public static final String OCP_DXY_GET_MISS_ORDER = "ocp.dxy.get.miss.order";



    /**
     * 队列名称枚举
     */
    public enum QueueEnum implements BasicQueueEnum {
        TEST("有赞订单处理",QueueConstants.TEST,
                ExchangeEnum.MQ_EXCHANGE_YOUZAN),

        /**
         * 有赞订单处理
         */
        OCP_YOUZAN_ORDER_PROCESS("有赞订单处理",QueueConstants.OCP_YOUZAN_ORDER_PROCESS,
                             ExchangeEnum.MQ_EXCHANGE_YOUZAN),
        OCP_YOUZAN_GET_ORDER("有赞抓取订单",QueueConstants.OCP_YOUZAN_GET_ORDER,
                             ExchangeEnum.MQ_EXCHANGE_YOUZAN,Boolean.FALSE),
        OCP_YOUZAN_GET_MISS_ORDER("有赞抓取漏掉订单",QueueConstants.OCP_YOUZAN_GET_MISS_ORDER,
                                  ExchangeEnum.MQ_EXCHANGE_YOUZAN,Boolean.FALSE),
        OCP_YOUZAN_ORDER_TO_KJB("有赞订单推送跨境宝",QueueConstants.OCP_YOUZAN_ORDER_TO_KJB,ExchangeEnum.MQ_EXCHANGE_KJB),
        OCP_YOUZAN_ORDER_TO_OFC("有赞订单推送OFC",QueueConstants.OCP_YOUZAN_ORDER_TO_OFC,ExchangeEnum.MQ_EXCHANGE_OFC),
        OCP_YOUZAN_ORDER_TO_ESD("有赞订单推送ESD",QueueConstants.OCP_YOUZAN_ORDER_TO_ESD,ExchangeEnum.MQ_EXCHANGE_ESD),
        OCP_YOUZAN_ORDER_TO_OP("有赞订单推送OP",QueueConstants.OCP_YOUZAN_ORDER_TO_OP,ExchangeEnum.MQ_EXCHANGE_OP),
        /**
         * 拼多多
         */
        PDD_GET_ORDER("拼多多抓取订单",QueueConstants.PDD_GET_ORDER, ExchangeEnum.MQ_EXCHANGE_PDD, Boolean.FALSE),
        PDD_GET_MISS_ORDER("拼多多漏单抓取订单",QueueConstants.PDD_GET_MISS_ORDER, ExchangeEnum.MQ_EXCHANGE_PDD, Boolean.FALSE),
        PDD_CATCH_ORDER_PROCESS("拼多多抓单订单处理",QueueConstants.PDD_CATCH_ORDER_PROCESS, ExchangeEnum.MQ_EXCHANGE_PDD, Boolean.FALSE),
        PDD_RECEIVE_ORDER_PROCESS("拼多多接单订单处理",QueueConstants.PDD_RECEIVE_ORDER_PROCESS, ExchangeEnum.MQ_EXCHANGE_PDD, Boolean.FALSE),
        PDD_ORDER_TO_KJB("拼多多订单推送跨境宝",QueueConstants.PDD_ORDER_TO_KJB, ExchangeEnum.MQ_EXCHANGE_PDD, Boolean.FALSE),
        PDD_ORDER_TO_GEMINI("拼多多订单推送税价分离",QueueConstants.PDD_ORDER_TO_GEMINI, ExchangeEnum.MQ_EXCHANGE_PDD, Boolean.FALSE),
        PDD_ORDER_PUSH_ROUTER("拼多多订单下发",QueueConstants.PDD_ORDER_PUSH_ROUTER, ExchangeEnum.MQ_EXCHANGE_PDD, Boolean.FALSE),
        PDD_ORDER_TO_OFC("拼多多订单下发OFC",QueueConstants.PDD_ORDER_TO_OFC, ExchangeEnum.MQ_EXCHANGE_PDD, Boolean.FALSE),
        PDD_ORDER_TO_OP("拼多多订单下发OP",QueueConstants.PDD_ORDER_TO_OP, ExchangeEnum.MQ_EXCHANGE_PDD, Boolean.FALSE),
        PDD_ORDER_TO_ESD("拼多多订单下发ESD",QueueConstants.PDD_ORDER_TO_ESD, ExchangeEnum.MQ_EXCHANGE_PDD, Boolean.FALSE),

        /**
         * 唯品会订单队列枚举
         */
        OCP_VIP_GET_ORDER("唯品会抓取订单", QueueConstants.OCP_VIP_GET_ORDER,
                ExchangeEnum.MQ_EXCHANGE_VIP, Boolean.TRUE),
        OCP_VIP_ORDER_PUSH_OFC("唯品会订单推OFC", QueueConstants.OCP_VIP_ORDER_PUSH_OFC,
                ExchangeEnum.MQ_EXCHANGE_OFC, Boolean.TRUE),
        OCP_VIP_ORDER_PUSH_ESD("唯品会订单推ESD", QueueConstants.OCP_VIP_ORDER_PUSH_ESD,
                ExchangeEnum.MQ_EXCHANGE_ESD, Boolean.TRUE),
        OCP_VIP_ORDER_PROCESS("唯品会抓单订单处理", QueueConstants.OCP_VIP_ORDER_PROCESS,
                ExchangeEnum.MQ_EXCHANGE_VIP, Boolean.TRUE),

        /**
         * 全球仓订单队列枚举
         */
        OCP_GLOBAL_GET_ORDER("全球仓抓取订单", QueueConstants.OCP_GLOBAL_GET_ORDER,
                ExchangeEnum.MQ_EXCHANGE_GLOBAL, Boolean.TRUE),
        OCP_GLOBAL_GET_MISS_ORDER("全球仓漏单抓取", QueueConstants.OCP_GLOBAL_GET_MISS_ORDER,
                ExchangeEnum.MQ_EXCHANGE_GLOBAL, Boolean.TRUE),
        OCP_GLOBAL_ORDER_PUSH_OFC("全球仓订单推OFC", QueueConstants.OCP_GLOBAL_ORDER_PUSH_OFC,
                ExchangeEnum.MQ_EXCHANGE_OFC, Boolean.TRUE),

        /**
         * 洋码头订单队列
         */
        OCP_YMATOU_ORDER_PROCESS("洋码头订单保存消息队列", QueueConstants.OCP_YMATOU_ORDER_PROCESS,
                ExchangeEnum.MQ_EXCHANGE_YMATOU, Boolean.TRUE),
        OCP_YMATOU_ORDER_PUSH_KJB("洋码头订单推送组套消息队列", QueueConstants.OCP_YMATOU_ORDER_PUSH_KJB,
                ExchangeEnum.MQ_EXCHANGE_KJB, Boolean.TRUE),
        OCP_YMATOU_ORDER_PUSH_OFC("洋码头订单推送OFC消息队列", QueueConstants.OCP_YMATOU_ORDER_PUSH_OFC,
                ExchangeEnum.MQ_EXCHANGE_OFC, Boolean.TRUE),
        OCP_YMATOU_ORDER_PUSH_GEMINI("洋码头订单推送税金分离消息队列", QueueConstants.OCP_YMATOU_ORDER_PUSH_GEMINI,
                ExchangeEnum.MQ_EXCHANGE_GEMINI, Boolean.TRUE),
        OCP_YMATOU_ORDER_PUSB_OP("洋码头订单推送OP消息队列", QueueConstants.OCP_YMATOU_ORDER_PUSH_OP,
                ExchangeEnum.MQ_EXCHANGE_OP, Boolean.TRUE),
        OCP_YMATOU_GRAB_ORDER("洋码头订单抓取消息队列", QueueConstants.OCP_YMATOU_GRAB_ORDER,
                ExchangeEnum.MQ_EXCHANGE_YMATOU, Boolean.TRUE),
        OCP_YMATOU_TAKE_ORDER("洋码头订单接单消息队列", QueueConstants.OCP_YMATOU_TAKE_ORDER,
                ExchangeEnum.MQ_EXCHANGE_YMATOU, Boolean.TRUE),
        OCP_YMATOU_PAY_ORDER("洋码头订单支付消息队列", QueueConstants.OCP_YMATOU_PAY_ORDER,
                ExchangeEnum.MQ_EXCHANGE_YMATOU, Boolean.TRUE),

        /**
         * 主数据队列
         */
        OCP_MDM_SYNC_GOODS_INFO("主数据同步商品信息队列", QueueConstants.OCP_MDM_SYNC_GOODS_INFO,
                ExchangeEnum.MQ_EXCHANGE_MDM, Boolean.TRUE),

        /**
         * 达令家订单队列
         */
        OCP_DALING_ORDER_PUSH_OP("达令家订单推送OP消息队列", QueueConstants.OCP_DALING_ORDER_PUSH_OP,
                ExchangeEnum.MQ_EXCHANGE_OP, Boolean.TRUE),
        OCP_DALING_ORDER_INFO_GRAB("达令家订单明细抓取队列", QueueConstants.OCP_DALING_ORDER_INFO_GRAB,
                ExchangeEnum.MQ_EXCHANGE_DALING, Boolean.TRUE),
        OCP_DALING_GET_ORDER("达令家抓取订单",QueueConstants.OCP_DALING_GET_ORDER,
                ExchangeEnum.MQ_EXCHANGE_DALING,Boolean.TRUE),
        OCP_DALING_GET_MISS_ORDER("达令家抓取漏掉订单",QueueConstants.OCP_DALING_GET_MISS_ORDER,
                ExchangeEnum.MQ_EXCHANGE_DALING,Boolean.TRUE),
        OCP_DALING_ORDER_PUSH_OFC("达令家订单推送OFC消息队列", QueueConstants.OCP_DALING_ORDER_PUSH_OFC,
                ExchangeEnum.MQ_EXCHANGE_OFC, Boolean.TRUE),
        OCP_DALING_ORDER_PROCESS("达令家处理订单", QueueConstants.OCP_DALING_ORDER_PROCESS,
                ExchangeEnum.MQ_EXCHANGE_DALING, Boolean.TRUE),

        /**
         * 海拍客订单队列
         */
        OCP_HIPAC_ORDER_PUSH_OP("海拍客订单推OP队列", QueueConstants.OCP_HIPAC_ORDER_PUSH_OP,
                ExchangeEnum.MQ_EXCHANGE_HIPAC, Boolean.TRUE),
        OCP_HIPAC_ORDER_PUSH_OFC("海拍客订单推OFC队列", QueueConstants.OCP_HIPAC_ORDER_PUSH_OFC,
                ExchangeEnum.MQ_EXCHANGE_HIPAC, Boolean.TRUE),

        /**
         * 宝妈时光订单队列
         */
        OCP_BAOMA_GET_ORDER("宝妈时光抓取订单", QueueConstants.OCP_BAOMA_GET_ORDER,
                ExchangeEnum.MQ_EXCHANGE_BAOMA, Boolean.TRUE),
        OCP_BAOMA_SAVE_ORDER("宝妈时光保存订单", QueueConstants.OCP_BAOMA_SAVE_ORDER,
                ExchangeEnum.MQ_EXCHANGE_BAOMA, Boolean.TRUE),
        OCP_BAOMA_ORDER_PUSH_OP("宝妈时光订单推送OP消息队列", QueueConstants.OCP_BAOMA_ORDER_PUSH_OP,
                ExchangeEnum.MQ_EXCHANGE_OP, Boolean.TRUE),
        OCP_BAOMA_ORDER_PUSH_OFC("宝妈时光订单推送OFC消息队列", QueueConstants.OCP_BAOMA_ORDER_PUSH_OFC,
                ExchangeEnum.MQ_EXCHANGE_OFC, Boolean.TRUE),

        /**
         * 标准订单队列
         */
        OCP_PUB_ORDER_PUSH_ESD("标准订单推ESD", QueueConstants.OCP_PUB_ORDER_PUSH_ESD,
                ExchangeEnum.MQ_EXCHANGE_ESD, Boolean.TRUE),

        OCP_KJB_ORDER_PUSH_ESD("跨境宝订单推ESD", QueueConstants.OCP_KJB_ORDER_PUSH_ESD,
                ExchangeEnum.MQ_EXCHANGE_KJB, Boolean.TRUE),


        /**
         * 小米订单队列
         */
        OCP_XIAOMI_GET_ORDER("小米抓取订单", QueueConstants.OCP_XIAOMI_GET_ORDER,
                            ExchangeEnum.MQ_EXCHANGE_XIAOMI, Boolean.TRUE),
        OCP_XIAOMI_SAVE_ORDER("小米保存订单", QueueConstants.OCP_XIAOMI_SAVE_ORDER,
                             ExchangeEnum.MQ_EXCHANGE_XIAOMI, Boolean.TRUE),
        OCP_XIAOMI_ORDER_PUSH_KJB("小米订单推送KJB", QueueConstants.OCP_XIAOMI_ORDER_PUSH_KJB,
                ExchangeEnum.MQ_EXCHANGE_KJB, Boolean.TRUE),
        OCP_XIAOMI_GET_MISS_ORDER("小米漏单抓单", QueueConstants.OCP_XIAOMI_GET_MISS_ORDER,
                ExchangeEnum.MQ_EXCHANGE_XIAOMI, Boolean.TRUE),

        /**
         * 大订单队列枚举
         */
        OCP_BIG_ORDER_PUSH_OFCBC("大订单推OFC-BC", QueueConstants.OCP_BIG_ORDER_PUSH_OFCBC,
                ExchangeEnum.MQ_EXCHANGE_OFCBC, Boolean.TRUE),
        OCP_BIG_ORDER_PUSH_OFC("大订单推OFC", QueueConstants.OCP_BIG_ORDER_PUSH_OFC,
                ExchangeEnum.MQ_EXCHANGE_OFC, Boolean.TRUE),
        OCP_BIG_ORDER_PUSH_OP("大订单推OP", QueueConstants.OCP_BIG_ORDER_PUSH_OP,
                ExchangeEnum.MQ_EXCHANGE_OP, Boolean.TRUE),
        OCP_BIG_ORDER_PROCESS("大订单订单处理",QueueConstants.OCP_BIG_ORDER_PROCESS,
                ExchangeEnum.MQ_EXCHANGE_BIG),

        /**
         * 安利订单队列枚举
         */
        OCP_AMWAY_ORDER_PROCESS("安利订单处理",QueueConstants.OCP_AMWAY_ORDER_PROCESS,
                ExchangeEnum.MQ_EXCHANGE_AMWAY),
        OCP_AMWAY_ORDER_PUSH_OFC("安利订单推OFC", QueueConstants.OCP_AMWAY_ORDER_PUSH_OFC,
                ExchangeEnum.MQ_EXCHANGE_AMWAY, Boolean.TRUE),

        /**
         * 环球捕手订单处理
         */
        OCP_GS_ORDER_PROCESS("环球捕手订单处理",QueueConstants.OCP_GS_ORDER_PROCESS,
                ExchangeEnum.MQ_EXCHANGE_GS),
        OCP_GS_GET_ORDER("环球捕手抓取订单", QueueConstants.OCP_GS_GET_ORDER,
                ExchangeEnum.MQ_EXCHANGE_GS, Boolean.FALSE),
        OCP_GS_GET_MISS_ORDER("环球捕手抓取漏掉订单", QueueConstants.OCP_GS_GET_MISS_ORDER,
                ExchangeEnum.MQ_EXCHANGE_GS, Boolean.FALSE),
        OCP_GS_ORDER_TO_KJB("环球捕手订单推送跨境宝", QueueConstants.OCP_GS_ORDER_TO_KJB, ExchangeEnum.MQ_EXCHANGE_KJB),
        OCP_GS_ORDER_TO_OFC("环球捕手订单推送OFC", QueueConstants.OCP_GS_ORDER_TO_OFC, ExchangeEnum.MQ_EXCHANGE_OFC),
        OCP_GS_ORDER_TO_OP("环球捕手订单推送OP", QueueConstants.OCP_GS_ORDER_TO_OP, ExchangeEnum.MQ_EXCHANGE_OP),



        /**
         * 京东订单队列
         */
        OCP_JD_ORDER_TO_OFC("京东订单推送OFC",QueueConstants.OCP_JD_ORDER_TO_OFC,ExchangeEnum.MQ_EXCHANGE_OFC),

        OCP_JD_GET_ORDER("京东抓取订单", QueueConstants.OCP_JD_GET_ORDER,
                ExchangeEnum.MQ_EXCHANGE_JD, Boolean.TRUE),
        OCP_JD_ORDER_PROCESS("京东处理订单", QueueConstants.OCP_JD_ORDER_PROCESS,
                ExchangeEnum.MQ_EXCHANGE_JD, Boolean.TRUE),
        OCP_JD_YX_GET_ORDER("京东云霄抓取订单", QueueConstants.OCP_JD_YX_GET_ORDER,
                ExchangeEnum.MQ_EXCHANGE_JD, Boolean.TRUE),
        OCP_JD_YX_ORDER_PROCESS("京东云霄处理订单", QueueConstants.OCP_JD_YX_ORDER_PROCESS,
                ExchangeEnum.MQ_EXCHANGE_JD, Boolean.TRUE),
        OCP_JD_DLZ_GET_ORDER("京东多渠道独立站抓取订单", QueueConstants.OCP_JD_DLZ_GET_ORDER,
                ExchangeEnum.MQ_EXCHANGE_JD, Boolean.TRUE),
        OCP_JD_DLZ_ORDER_PROCESS("京东多渠道独立站处理订单", QueueConstants.OCP_JD_DLZ_ORDER_PROCESS,
                ExchangeEnum.MQ_EXCHANGE_JD, Boolean.TRUE),

        /**
         * 贝贝订单队列
         */
        OCP_BEIBEI_GET_ORDER("贝贝抓取订单", QueueConstants.OCP_BEIBEI_GET_ORDER, ExchangeEnum.MQ_EXCHANGE_BEIBEI, Boolean.TRUE),
        OCP_BEIBEI_SAVE_ORDER("贝贝保存订单", QueueConstants.OCP_BEIBEI_SAVE_ORDER, ExchangeEnum.MQ_EXCHANGE_BEIBEI, Boolean.TRUE),
        OCP_BEIBEI_ORDER_TO_OFC("贝贝订单推送OFC", QueueConstants.OCP_BEIBEI_ORDER_TO_OFC, ExchangeEnum.MQ_EXCHANGE_OFC, Boolean.TRUE),

        /**
         * 分销订单队列
         */
        OCP_FX_ORDER_PROCESS("分销订单处理", QueueConstants.OCP_FX_ORDER_PROCESS,
                ExchangeEnum.MQ_EXCHANGE_FX, Boolean.TRUE),
        OCP_FX_ORDER_TO_KJB("分销订单下发跨境宝", QueueConstants.OCP_FX_ORDER_TO_KJB,
                ExchangeEnum.MQ_EXCHANGE_KJB, Boolean.FALSE),
        OCP_FX_ORDER_TO_OFC("分销订单下发OFC", QueueConstants.OCP_FX_ORDER_TO_OFC,
                ExchangeEnum.MQ_EXCHANGE_OFC, Boolean.FALSE),
        OCP_FX_ORDER_TO_EW("分销订单下发E仓", QueueConstants.OCP_FX_ORDER_TO_EW,
                ExchangeEnum.MQ_EXCHANGE_EW, Boolean.FALSE),

        /**
         * 丁香医生
         */
        OCP_DXY_GET_ORDER("丁香医生抓取订单", QueueConstants.OCP_DXY_GET_ORDER, ExchangeEnum.MQ_EXCHANGE_DXY, Boolean.TRUE),
        OCP_DXY_PROCESS_ORDER("丁香医生处理订单", QueueConstants.OCP_DXY_PROCESS_ORDER, ExchangeEnum.MQ_EXCHANGE_DXY, Boolean.TRUE),
        OCP_DXY_ORDER_TO_OFC("丁香医生订单推送OFC", QueueConstants.OCP_DXY_ORDER_TO_OFC, ExchangeEnum.MQ_EXCHANGE_OFC, Boolean.FALSE),
        OCP_DXY_GET_MISS_ORDER("丁香医生抓取漏掉订单",QueueConstants.OCP_DXY_GET_MISS_ORDER,
                ExchangeEnum.MQ_EXCHANGE_OFC,Boolean.FALSE),
        ;

        QueueEnum(String queueShowName, String queueName, ExchangeEnum exchangeEnum) {
            this.queueName = queueName;
            this.routingKey = queueName;
            this.exchangeEnum = exchangeEnum;
            this.queueShowName = queueShowName;
            this.needLock = Boolean.TRUE;
        }

        QueueEnum(String queueShowName, String queueName, ExchangeEnum exchangeEnum,
                Boolean needLock) {
            this.queueName = queueName;
            this.routingKey = queueName;
            this.exchangeEnum = exchangeEnum;
            this.queueShowName = queueShowName;
            this.needLock = needLock;
        }

        public static QueueEnum getQueueEnumByName(String routingKey) {
            QueueEnum[] queueEnums = QueueEnum.values();
            for (QueueEnum queueEnum : queueEnums) {
                if (queueEnum.getRoutingKey().equals(routingKey)) {
                    return queueEnum;
                }
            }
            return null;
        }

        private String queueName;

        private String queueShowName;

        private String routingKey;

        private ExchangeEnum exchangeEnum;

        private Boolean needLock = Boolean.TRUE;

        public final String getQueueName() {
            return queueName;
        }

        public String getRoutingKey() {
            return routingKey;
        }

        public ExchangeEnum getExchangeEnum() {
            return exchangeEnum;
        }

        //队列是否需要加锁
        public Boolean getNeedLock() {
            return needLock;
        }

        @Override
        public String getQueueShowName() {
            return queueShowName;
        }
    }
}

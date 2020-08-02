package com.topideal.supplychain.ocp.daling.consumer;

import com.topideal.supplychain.mq.BasicMessage;

/**
 * 达令家
 * @author xuxiaoyan
 * @date 2019-12-11 11:06
 */
public interface DalingConsumerService {

    /**
     * 订单推op
     * @param basicMessage
     */
    void sendOrderOp(BasicMessage basicMessage);

    /**
     * 获取订单
     * @param basicMessage
     */
    void getOrder(BasicMessage basicMessage);
    /**
     * 获取漏掉订单
     * @param basicMessage
     */
    void getMissOrder(BasicMessage basicMessage);
    /**
     * 获取订单明细详情
     * @param basicMessage
     */
    void grabDetail(BasicMessage basicMessage);

    /**
     * 订单推ofc
     * @param basicMessage
     */
    void sendOrderOfc(BasicMessage basicMessage);

    /**
     * 达令家订单处理
     * @param basicMessage
     */
    void processOrder(BasicMessage basicMessage);

}

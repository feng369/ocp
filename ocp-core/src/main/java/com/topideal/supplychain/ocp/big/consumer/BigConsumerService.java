package com.topideal.supplychain.ocp.big.consumer;

import com.topideal.supplychain.mq.BasicMessage;

/**
 * 标题：大订单队列service
 * 模块：ocp-parent
 * 版权: Copyright © 2019 topideal
 * 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.big.consumer
 * 作者：songping
 * 创建日期：2019/12/26 18:09
 *
 * @version 1.0
 */
public interface BigConsumerService {

    /**
     * 大订单推OFC
     * @param basicMessage
     */
    void orderSendToOFC(BasicMessage basicMessage);

    /**
     * 大订单推OFC-BC
     * @param basicMessage
     */
    void orderSendToOFCBC(BasicMessage basicMessage);

    /**
     * 大订单推OP
     * @param basicMessage
     */
    void orderSendToOP(BasicMessage basicMessage);

    /**
     * 大订单接单处理
     * @param basicMessage
     */
    void processOrder(BasicMessage basicMessage);
}

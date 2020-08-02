package com.topideal.supplychain.ocp.kjb.consumer;

import com.topideal.supplychain.mq.BasicMessage;

/**
 * 跨境宝订单消费者
 */
public interface KjbConsumerService {

    void sendOrderEsd(BasicMessage basicMessage);

}

package com.topideal.supplychain.ocp.amway.consumer;

import com.topideal.supplychain.mq.BasicMessage;

public interface AmwayConsumerService {

    /**
     * 安利订单推OFC
     */
    void sendOrderOfc(BasicMessage basicMessage);


    /**
     * 安利接单处理
     */
    void processOrder(BasicMessage basicMessage);
}

package com.topideal.supplychain.ocp.jd.consumer;

import com.topideal.supplychain.mq.BasicMessage;

public interface JdConsumerService {

    void sendOrderOfc(BasicMessage basicMessage);

    void processOrder(BasicMessage basicMessage);

    void getOrder(BasicMessage basicMessage);

    void processYxOrder(BasicMessage basicMessage);

    void getYxOrder(BasicMessage basicMessage);

    void processDlzOrder(BasicMessage basicMessage);

    void getDlzOrder(BasicMessage basicMessage);
}

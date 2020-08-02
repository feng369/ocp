package com.topideal.supplychain.ocp.dxy.consumer;

import com.topideal.supplychain.mq.BasicMessage;

public interface DxyConsumerService {

    void sendOrderOfc(BasicMessage basicMessage);

    void processOrder(BasicMessage basicMessage);

    void getOrder(BasicMessage basicMessage);

    void getMissOrder(BasicMessage basicMessage);

}

package com.topideal.supplychain.ocp.global.consumer;

import com.topideal.supplychain.mq.BasicMessage;

/**
 * @ClassName GlobalConsumerService
 * @TODO TODO
 * @Author zhangli
 * @DATE 2019/12/10 15:55
 * @Version 1.0
 **/
public interface GlobalConsumerService {

    void getOrder(BasicMessage message);

    void getMissOrder(BasicMessage message);

    void pushOrder(BasicMessage message);
}

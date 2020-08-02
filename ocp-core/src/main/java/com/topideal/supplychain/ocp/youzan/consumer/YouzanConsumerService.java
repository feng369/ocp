package com.topideal.supplychain.ocp.youzan.consumer;

import com.topideal.supplychain.mq.BasicMessage;

/**
 * @ClassName YouzanConsumerService
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2019/11/28 10:53
 * @Version 1.0
 **/
public interface YouzanConsumerService {

    void getOrder(BasicMessage basicMessage);

    void processOrder(BasicMessage basicMessage);

    void getMissOrder(BasicMessage basicMessage);

    void sendOrderOfc(BasicMessage basicMessage);

    void sendOrderKjb(BasicMessage basicMessage);

    void sendOrderOp(BasicMessage basicMessage);

    void sendOrderEsd(BasicMessage basicMessage);
}

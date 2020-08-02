package com.topideal.supplychain.ocp.vip.consumer;

import com.topideal.supplychain.mq.BasicMessage;

/**
 * @ClassName VipConsumerService
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2019/12/3 15:55
 * @Version 1.0
 **/
public interface VipConsumerService {

    void getOrder(BasicMessage message);

    void pushOrderToOfc(BasicMessage message);

    void pushOrderToEsd(BasicMessage message);

    /**
     * 唯品抓单订单处理
     * @param basicMessage
     */
    void processOrder(BasicMessage basicMessage);
}

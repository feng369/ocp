package com.topideal.supplychain.ocp.xiaomi.consumer;

import com.topideal.supplychain.mq.BasicMessage;

/**
 * @author wanzhaozhang
 * @date 2019/12/3
 * @description
 **/
public interface XiaomiConsumerService {

    void processOrder(BasicMessage basicMessage);
    void pushKjb(BasicMessage basicMessage);
    void getOrder(BasicMessage basicMessage);

    /**
     * 小米漏单抓单
     * @param basicMessage
     */
    void getMissOrder(BasicMessage basicMessage);
}

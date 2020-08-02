package com.topideal.supplychain.ocp.ymatou.consumer;

import com.topideal.supplychain.mq.BasicMessage;

/**
 * @author wanzhaozhang
 * @date 2019/12/3
 * @description
 **/
public interface YmatouConsumerService {

    void processOrder(BasicMessage basicMessage);
    void pushKjb(BasicMessage basicMessage);
    void pushGemini(BasicMessage basicMessage);
    void pushOp(BasicMessage basicMessage);
    void pushOfc(BasicMessage basicMessage);
    void grabOrder(BasicMessage basicMessage);
    void takeOrder(BasicMessage basicMessage);
    void payOrder(BasicMessage basicMessage);
}

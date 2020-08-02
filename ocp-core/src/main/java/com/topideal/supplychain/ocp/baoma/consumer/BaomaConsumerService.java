package com.topideal.supplychain.ocp.baoma.consumer;

import com.topideal.supplychain.mq.BasicMessage;

/**
 * @author wanzhaozhang
 * @date 2019/12/13
 * @description
 **/
public interface BaomaConsumerService {
    void grabOrder(BasicMessage basicMessage);
    void saveOrder(BasicMessage basicMessage);
    void pushOfc(BasicMessage basicMessage);
    void pushOp(BasicMessage basicMessage);
}

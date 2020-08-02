package com.topideal.supplychain.ocp.beibei.consumer;

import com.topideal.supplychain.mq.BasicMessage;

/**
 * @author wanzhaozhang
 * @date 2020/3/23
 * @description
 **/
public interface BeibeiConsumerService {
    void saveOrder(BasicMessage basicMessage);
    void pushOfc(BasicMessage basicMessage);
    void getOrder(BasicMessage basicMessage);
}

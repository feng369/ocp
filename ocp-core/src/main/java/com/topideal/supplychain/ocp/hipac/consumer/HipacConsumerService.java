package com.topideal.supplychain.ocp.hipac.consumer;

import com.topideal.supplychain.mq.BasicMessage;

/**
 * 海拍客消费
 *
 * @author xuxiaoyan
 * @date 2019-12-18 09:44
 */
public interface HipacConsumerService {

    /**
     * 订单推op
     * @param basicMessage
     */
    void sendOrderOp(BasicMessage basicMessage);

    /**
     * 订单推ofc
     * @param basicMessage
     */
    void sendOrderOfc(BasicMessage basicMessage);

}

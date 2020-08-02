package com.topideal.supplychain.ocp.distribution.consumer;

import com.topideal.supplychain.mq.BasicMessage;

/**
 * 分销订单转发系统
 *
 * @author xuxiaoyan
 * @date 2020-05-22 11:00
 */
public interface DistributionConsumerService {

    /**
     * 处理订单
     */
    void processOrder(BasicMessage basicMessage);

    void sendOrderKjb(BasicMessage basicMessage);

    void sendOrderOfc(BasicMessage basicMessage);

    void sendOrderEw(BasicMessage basicMessage);
}

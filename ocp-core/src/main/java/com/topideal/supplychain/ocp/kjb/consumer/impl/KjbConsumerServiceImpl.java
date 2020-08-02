package com.topideal.supplychain.ocp.kjb.consumer.impl;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.mq.NeedRetryException;
import com.topideal.supplychain.mq.NoDataReadyException;
import com.topideal.supplychain.ocp.kjb.consumer.KjbConsumerService;
import com.topideal.supplychain.ocp.kjb.service.KjbService;
import com.topideal.supplychain.ocp.mq.QueueConstants;
import com.topideal.supplychain.ocp.order.model.OrderKjb;
import com.topideal.supplychain.ocp.order.service.OrderKjbService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 跨境宝订单消费者
 */
@Service
public class KjbConsumerServiceImpl implements KjbConsumerService {

    @Autowired
    private OrderKjbService orderKjbService;
    @Autowired
    private KjbService kjbService;

    /**
     * 跨境宝订单推送esd
     * @param basicMessage
     */
    @RabbitListener(queues = QueueConstants.OCP_KJB_ORDER_PUSH_ESD)
    @RabbitHandler
    public void sendOrderEsd(BasicMessage basicMessage) {
        OrderKjb orderKjb = orderKjbService.selectById(basicMessage.getBusinessId());
        if (orderKjb == null) {
            throw new NoDataReadyException();
        }
        BaseResponse baseResponse = kjbService.sendOrderEsd(orderKjb);
        if (!baseResponse.isSuccess()) {
            throw new NeedRetryException(baseResponse.getMessage());
        }
    }

}

package com.topideal.supplychain.ocp.amway.consumer.impl;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.exception.BusinessException;
import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.ocp.amway.consumer.AmwayConsumerService;
import com.topideal.supplychain.ocp.amway.service.AmwayService;
import com.topideal.supplychain.ocp.mq.QueueConstants;
import com.topideal.supplychain.ocp.order.model.OrderAmway;
import com.topideal.supplychain.ocp.order.model.OrderTemp;
import com.topideal.supplychain.ocp.order.service.OrderAmwayService;
import com.topideal.supplychain.ocp.order.service.OrderTempService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmwayConsumerServiceImpl implements AmwayConsumerService {

    @Autowired
    private AmwayService amwayService;
    @Autowired
    private OrderAmwayService orderAmwayService;
    @Autowired
    private OrderTempService orderTempService;


    /**
     * 安利订单推OFC
     */
    @Override
    @RabbitListener(queues = QueueConstants.OCP_AMWAY_ORDER_PUSH_OFC)
    @RabbitHandler
    public void sendOrderOfc(BasicMessage basicMessage) {
        OrderAmway orderAmway = orderAmwayService.selectById(basicMessage.getBusinessId());
        if (null == orderAmway) {
            return;
        }
        BaseResponse baseResponse = amwayService.sendOrderOfc(orderAmway);
        BusinessAssert.assertIsTrue(baseResponse.isSuccess(), baseResponse.getMessage());
    }

    /**
     * 安利订单处理
     */
    @Override
    @RabbitListener(queues = QueueConstants.OCP_AMWAY_ORDER_PROCESS)
    @RabbitHandler
    public void processOrder(BasicMessage basicMessage) {
        OrderTemp orderTemp = orderTempService.selectById(basicMessage.getBusinessId());
        if (null == orderTemp) {
            return;
        }

        try {
            amwayService.processOrder(orderTemp);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }

    }


}

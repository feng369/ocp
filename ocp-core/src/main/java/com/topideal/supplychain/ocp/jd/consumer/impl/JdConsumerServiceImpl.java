package com.topideal.supplychain.ocp.jd.consumer.impl;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessException;
import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.mq.NeedRetryException;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.config.service.CatchOrderConfigService;
import com.topideal.supplychain.ocp.jd.consumer.JdConsumerService;
import com.topideal.supplychain.ocp.jd.service.JdService;
import com.topideal.supplychain.ocp.mq.QueueConstants;
import com.topideal.supplychain.ocp.order.model.OrderJd;
import com.topideal.supplychain.ocp.order.model.OrderTemp;
import com.topideal.supplychain.ocp.order.service.OrderJdService;
import com.topideal.supplychain.ocp.order.service.OrderTempService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JdConsumerServiceImpl implements JdConsumerService {

    @Autowired
    private CatchOrderConfigService catchOrderConfigService;
    @Autowired
    private OrderTempService orderTempService;
    @Autowired
    private JdService jdService;
    @Autowired
    private OrderJdService orderJdService;

    /**
     * 抓取订单
     *
     * @param basicMessage
     */

    @RabbitHandler
    @RabbitListener(queues = QueueConstants.OCP_JD_GET_ORDER)
    public void getOrder(BasicMessage basicMessage) {
        CatchOrderConfig catchOrderConfig = catchOrderConfigService.selectEnableById(basicMessage.getBusinessId());
        if (catchOrderConfig == null) {
            return;
        }
        BaseResponse baseResponse = jdService.getOrder(catchOrderConfig);
        if (baseResponse.isSuccess()) {
            return;
        }
        throw new NeedRetryException(baseResponse.getMessage());
    }



    @RabbitHandler
    @RabbitListener(queues = QueueConstants.OCP_JD_ORDER_PROCESS)
    public void processOrder(BasicMessage basicMessage) {
        OrderTemp orderTemp = orderTempService.selectById(basicMessage.getBusinessId());
        if (orderTemp == null) {
            return;
        }
        jdService.processOrder(orderTemp,basicMessage.getUdf1());
    }

    /**
     * 抓取订单
     *
     * @param basicMessage
     */

    @RabbitHandler
    @RabbitListener(queues = QueueConstants.OCP_JD_YX_GET_ORDER)
    @Override
    public void getYxOrder(BasicMessage basicMessage) {
        CatchOrderConfig catchOrderConfig = catchOrderConfigService.selectEnableById(basicMessage.getBusinessId());
        if (catchOrderConfig == null) {
            return;
        }
        BaseResponse baseResponse = jdService.getYxOrder(catchOrderConfig);
        if (baseResponse.isSuccess()) {
            return;
        }
        throw new NeedRetryException(baseResponse.getMessage());
    }

    @RabbitHandler
    @RabbitListener(queues = QueueConstants.OCP_JD_YX_ORDER_PROCESS)
    @Override
    public void processYxOrder(BasicMessage basicMessage) {
        OrderTemp orderTemp = orderTempService.selectById(basicMessage.getBusinessId());
        if (orderTemp == null) {
            return;
        }
        jdService.processYxOrder(orderTemp,basicMessage.getUdf1());
    }


    /**
     * 抓取订单
     *
     * @param basicMessage
     */

    @RabbitHandler
    @RabbitListener(queues = QueueConstants.OCP_JD_DLZ_GET_ORDER)
    @Override
    public void getDlzOrder(BasicMessage basicMessage) {
        CatchOrderConfig catchOrderConfig = catchOrderConfigService.selectEnableById(basicMessage.getBusinessId());
        if (catchOrderConfig == null) {
            return;
        }
        BaseResponse baseResponse = jdService.grabDlzOrder(catchOrderConfig);
        if (baseResponse.isSuccess()) {
            return;
        }
        throw new NeedRetryException(baseResponse.getMessage());
    }

    @RabbitHandler
    @RabbitListener(queues = QueueConstants.OCP_JD_DLZ_ORDER_PROCESS)
    @Override
    public void processDlzOrder(BasicMessage basicMessage) {
        OrderTemp orderTemp = orderTempService.selectById(basicMessage.getBusinessId());
        if (orderTemp == null) {
            return;
        }
        jdService.processDlzOrder(orderTemp,basicMessage.getUdf1());
    }


    @RabbitListener(queues = QueueConstants.OCP_JD_ORDER_TO_OFC)
    @RabbitHandler
    @Override
    public void sendOrderOfc(BasicMessage basicMessage) {
        OrderJd orderJd = orderJdService.selectById(basicMessage.getBusinessId());
        if (orderJd == null) {
            return;
        }

        BaseResponse baseResponse = jdService.sendOrderOfc(orderJd);
        if (baseResponse.isSuccess()) {
            return;
        }
        throw new BusinessException(baseResponse.getMessage());
    }
}

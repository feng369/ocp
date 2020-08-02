package com.topideal.supplychain.ocp.dxy.consumer.impl;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.exception.BusinessException;
import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.mq.NeedRetryException;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.config.service.CatchOrderConfigService;
import com.topideal.supplychain.ocp.dxy.consumer.DxyConsumerService;
import com.topideal.supplychain.ocp.dxy.service.DxyService;
import com.topideal.supplychain.ocp.jd.consumer.JdConsumerService;
import com.topideal.supplychain.ocp.jd.service.JdService;
import com.topideal.supplychain.ocp.mq.QueueConstants;
import com.topideal.supplychain.ocp.order.model.OrderDxy;
import com.topideal.supplychain.ocp.order.model.OrderJd;
import com.topideal.supplychain.ocp.order.model.OrderTemp;
import com.topideal.supplychain.ocp.order.service.OrderDxyService;
import com.topideal.supplychain.ocp.order.service.OrderJdService;
import com.topideal.supplychain.ocp.order.service.OrderTempService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DxyConsumerServiceImpl implements DxyConsumerService {

    @Autowired
    private CatchOrderConfigService catchOrderConfigService;
    @Autowired
    private OrderTempService orderTempService;
    @Autowired
    private DxyService dxyService;
    @Autowired
    private OrderDxyService orderDxyService;

    /**
     * 抓取订单
     *
     * @param basicMessage
     */

    @RabbitHandler
    @RabbitListener(queues = QueueConstants.OCP_DXY_GET_ORDER)
    public void getOrder(BasicMessage basicMessage) {
        CatchOrderConfig catchOrderConfig = catchOrderConfigService.selectEnableById(basicMessage.getBusinessId());
        if (catchOrderConfig == null) {
            return;
        }
        BaseResponse baseResponse = dxyService.getOrder(catchOrderConfig);
        if (baseResponse.isSuccess()) {
            return;
        }
        throw new NeedRetryException(baseResponse.getMessage());
    }



    @RabbitHandler
    @RabbitListener(queues = QueueConstants.OCP_DXY_PROCESS_ORDER)
    public void processOrder(BasicMessage basicMessage) {
        OrderTemp orderTemp = orderTempService.selectById(basicMessage.getBusinessId());
        if (orderTemp == null) {
            return;
        }
        CatchOrderConfig catchOrderConfig = catchOrderConfigService.selectByCode(basicMessage.getUdf1());
        BusinessAssert.assertNotNull(catchOrderConfig,String.format("[%s]抓单配置不存在",basicMessage.getUdf1()));
        dxyService.processOrder(orderTemp,catchOrderConfig);
    }



    @RabbitListener(queues = QueueConstants.OCP_DXY_ORDER_TO_OFC)
    @RabbitHandler
    @Override
    public void sendOrderOfc(BasicMessage basicMessage) {
        OrderDxy orderDxy = orderDxyService.selectById(basicMessage.getBusinessId());
        if (orderDxy == null) {
            return;
        }

        BaseResponse baseResponse = dxyService.sendOrderOfc(orderDxy);
        if (baseResponse.isSuccess()) {
            return;
        }
        throw new BusinessException(baseResponse.getMessage());
    }

    /**
     * 有赞抓取漏掉订单
     * @param basicMessage
     */
    @RabbitListener(queues = QueueConstants.OCP_DXY_GET_MISS_ORDER)
    @RabbitHandler
    public void getMissOrder(BasicMessage basicMessage) {
        CatchOrderConfig catchOrderConfig = catchOrderConfigService.selectEnableById(basicMessage.getBusinessId());
        if (catchOrderConfig == null) {
            return;
        }
        BaseResponse baseResponse = dxyService.getMissOrder(catchOrderConfig);
        if (baseResponse.isSuccess()) {
            return;
        }
        throw new NeedRetryException(baseResponse.getMessage());
    }
}

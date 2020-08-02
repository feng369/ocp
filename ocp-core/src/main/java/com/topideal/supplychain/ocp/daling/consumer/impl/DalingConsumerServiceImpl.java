package com.topideal.supplychain.ocp.daling.consumer.impl;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessException;
import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.config.service.CatchOrderConfigService;
import com.topideal.supplychain.ocp.daling.consumer.DalingConsumerService;
import com.topideal.supplychain.ocp.daling.service.DalingService;
import com.topideal.supplychain.ocp.enums.DaLingOrderStatusEnum;
import com.topideal.supplychain.ocp.enums.OrderStatusEnum;
import com.topideal.supplychain.ocp.mq.QueueConstants;
import com.topideal.supplychain.ocp.order.model.OrderDaling;
import com.topideal.supplychain.ocp.order.model.OrderTemp;
import com.topideal.supplychain.ocp.order.service.OrderDalingService;
import com.topideal.supplychain.ocp.order.service.OrderTempService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 达令家MQ消费队列
 *
 * @author xuxiaoyan
 * @date 2019-12-11 11:09
 */
@Service
public class DalingConsumerServiceImpl implements DalingConsumerService {

    @Autowired
    private OrderDalingService orderDalingService;
    @Autowired
    private DalingService dalingService;
    @Autowired
    private CatchOrderConfigService catchOrderConfigService;
    @Autowired
    private OrderTempService orderTempService;


    /**
     * 达令家订单推OP
     * @param basicMessage
     */
    @Override
    @RabbitListener(queues = QueueConstants.OCP_DALING_ORDER_PUSH_OP)
    @RabbitHandler
    public void sendOrderOp(BasicMessage basicMessage) {
        OrderDaling orderDaling = orderDalingService.selectById(basicMessage.getBusinessId());
        if (null == orderDaling) {
            return;
        }

        BaseResponse baseResponse = dalingService.sendOrderOp(orderDaling);
        if (baseResponse.isSuccess()) {
            return;
        }
        throw new BusinessException(baseResponse.getMessage());
    }


    @Override
    @RabbitListener(queues = QueueConstants.OCP_DALING_GET_ORDER)
    @RabbitHandler
    public void getOrder(BasicMessage basicMessage) {
        //判断抓单配置是否存在且有效
        CatchOrderConfig catchOrderConfig = catchOrderConfigService.selectEnableById(basicMessage.getBusinessId());
        if (catchOrderConfig == null) {
            return;
        }
        //开始抓单
        dalingService.getOrder(catchOrderConfig,false);
    }

    @Override
    @RabbitListener(queues = QueueConstants.OCP_DALING_GET_MISS_ORDER)
    @RabbitHandler
    public void getMissOrder(BasicMessage basicMessage) {
        //判断抓单配置是否存在且有效
        CatchOrderConfig catchOrderConfig = catchOrderConfigService.selectEnableById(basicMessage.getBusinessId());
        if (catchOrderConfig == null) {
            return;
        }
        //开始抓单
        dalingService.getMissOrder(catchOrderConfig);
    }

    @Override
    @RabbitListener(queues = QueueConstants.OCP_DALING_ORDER_INFO_GRAB)
    @RabbitHandler
    public void grabDetail(BasicMessage basicMessage) {
        OrderDaling orderDaling  = orderDalingService.selectById(basicMessage.getBusinessId());
        if(orderDaling == null){
            return;
        }
        boolean flag = OrderStatusEnum.INIT.equals(orderDaling.getOrderStatus())&& DaLingOrderStatusEnum.VALID
                .equals(orderDaling.getStatus());
        if (!flag) {
            return;
        }
        dalingService.grabDetail(orderDaling);
    }

    /**
     * 订单推ofc
     *
     * @param basicMessage
     */
    @Override
    @RabbitListener(queues = QueueConstants.OCP_DALING_ORDER_PUSH_OFC)
    @RabbitHandler
    public void sendOrderOfc(BasicMessage basicMessage) {
        OrderDaling orderDaling = orderDalingService.selectById(basicMessage.getBusinessId());
        if (null == orderDaling) {
            return;
        }

        BaseResponse baseResponse = dalingService.sendOrderOfc(orderDaling);
        if (baseResponse.isSuccess()) {
            return;
        }
        throw new BusinessException(baseResponse.getMessage());
    }

    /**
     * 达令家订单处理
     * @param basicMessage
     */
    @Override
    @RabbitListener(queues = QueueConstants.OCP_DALING_ORDER_PROCESS)
    @RabbitHandler
    public void processOrder(BasicMessage basicMessage) {
        OrderTemp orderTemp = orderTempService.selectById(basicMessage.getBusinessId());
        if (null == orderTemp) {
            return;
        }

        dalingService.processOrder(orderTemp);
    }
}

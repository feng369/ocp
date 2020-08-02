package com.topideal.supplychain.ocp.distribution.consumer.impl;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessException;
import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.ocp.distribution.consumer.DistributionConsumerService;
import com.topideal.supplychain.ocp.distribution.service.DistributionService;
import com.topideal.supplychain.ocp.enums.OrderStatusEnum;
import com.topideal.supplychain.ocp.mq.QueueConstants;
import com.topideal.supplychain.ocp.order.model.OrderDistribution;
import com.topideal.supplychain.ocp.order.model.OrderTemp;
import com.topideal.supplychain.ocp.order.service.OrderDistributionService;
import com.topideal.supplychain.ocp.order.service.OrderTempService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 分销订单
 *
 * @author xuxiaoyan
 * @date 2020-05-22 11:07
 */
@Service
public class DistributionConsumerServiceImpl implements DistributionConsumerService {

    @Autowired
    private OrderTempService orderTempService;
    @Autowired
    private DistributionService distributionService;
    @Autowired
    private OrderDistributionService orderDistributionService;

    /**
     * 处理分销订单数据，处理成功存入分销订单表，推送跨境宝分销接口
     * @param basicMessage
     */
    @Override
    @RabbitHandler
    @RabbitListener(queues = QueueConstants.OCP_FX_ORDER_PROCESS)
    public void processOrder(BasicMessage basicMessage) {
        OrderTemp orderTemp = orderTempService.selectById(basicMessage.getBusinessId());
        if (orderTemp == null) {
            return;
        }
        distributionService.processOrder(orderTemp);
    }


    /**
     * 推送订单到跨境宝
     * @param basicMessage
     */
    @RabbitListener(queues = QueueConstants.OCP_FX_ORDER_TO_KJB)
    @RabbitHandler
    public void sendOrderKjb(BasicMessage basicMessage) {
        OrderDistribution orderDistribution = orderDistributionService.selectById(basicMessage.getBusinessId());
        if (null == orderDistribution) {
            return;
        }

        BaseResponse baseResponse = distributionService.sendOrderKjb(orderDistribution);
        if (baseResponse.isSuccess()) {
            return;
        }
        throw new BusinessException(baseResponse.getMessage());
    }

    @RabbitListener(queues = QueueConstants.OCP_FX_ORDER_TO_OFC)
    @RabbitHandler
    public void sendOrderOfc(BasicMessage basicMessage) {
        OrderDistribution orderDistribution = orderDistributionService.selectById(basicMessage.getBusinessId());
        if (null == orderDistribution) {
            return;
        }

        BaseResponse baseResponse = distributionService.sendOrderOfc(orderDistribution);
        if (baseResponse.isSuccess()) {
            return;
        }
        throw new BusinessException(baseResponse.getMessage());
    }

    @RabbitListener(queues = QueueConstants.OCP_FX_ORDER_TO_EW)
    @RabbitHandler
    public void sendOrderEw(BasicMessage basicMessage) {
        OrderDistribution orderDistribution = orderDistributionService.selectById(basicMessage.getBusinessId());
        if (null == orderDistribution) {
            return;
        }

        BaseResponse baseResponse = distributionService.sendOrderEw(orderDistribution);
        if (baseResponse.isSuccess()) {
            return;
        }
        throw new BusinessException(baseResponse.getMessage());
    }
}

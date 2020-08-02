package com.topideal.supplychain.ocp.big.consumer.impl;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessException;
import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.ocp.big.consumer.BigConsumerService;
import com.topideal.supplychain.ocp.big.service.BigService;
import com.topideal.supplychain.ocp.mq.QueueConstants;
import com.topideal.supplychain.ocp.order.model.OrderBig;
import com.topideal.supplychain.ocp.order.model.OrderBigItem;
import com.topideal.supplychain.ocp.order.model.OrderTemp;
import com.topideal.supplychain.ocp.order.service.OrderBigItemService;
import com.topideal.supplychain.ocp.order.service.OrderBigService;
import com.topideal.supplychain.ocp.order.service.OrderTempService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 标题：大订单队列service
 * 模块：ocp-parent
 * 版权: Copyright © 2019 topideal
 * 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.big.consumer.impl
 * 作者：songping
 * 创建日期：2019/12/26 18:11
 *
 * @version 1.0
 */
@Service
public class BigConsumerServiceImpl implements BigConsumerService {

    @Autowired
    private BigService bigService;
    @Autowired
    private OrderBigService orderBigService;
    @Autowired
    private OrderBigItemService bigItemService;
    @Autowired
    private OrderTempService orderTempService;

    @Override
    @RabbitListener(queues = QueueConstants.OCP_BIG_ORDER_PUSH_OFC)
    @RabbitHandler
    public void orderSendToOFC(BasicMessage basicMessage) {
        OrderBig orderBig = orderBigService.selectById(basicMessage.getBusinessId());
        List<OrderBigItem> bigItems = bigItemService.selectByMainId(basicMessage.getBusinessId());
        orderBig.setOrderBigItemList(bigItems);
        if (null == orderBig){
            return;
        }
        BaseResponse response = bigService.forwardOFC(orderBig);
        if (response.isSuccess()){
            return;
        }
        throw new BusinessException(response.getMessage());
    }

    @Override
    @RabbitListener(queues = QueueConstants.OCP_BIG_ORDER_PUSH_OFCBC)
    @RabbitHandler
    public void orderSendToOFCBC(BasicMessage basicMessage) {
        OrderBig orderBig = orderBigService.selectById(basicMessage.getBusinessId());
        List<OrderBigItem> bigItems = bigItemService.selectByMainId(basicMessage.getBusinessId());
        orderBig.setOrderBigItemList(bigItems);
        if (null == orderBig){
            return;
        }
        BaseResponse response = bigService.forwardOFCBC(orderBig);
        if (response.isSuccess()){
            return;
        }
        throw new BusinessException(response.getMessage());
    }

    @Override
    @RabbitListener(queues = QueueConstants.OCP_BIG_ORDER_PUSH_OP)
    @RabbitHandler
    public void orderSendToOP(BasicMessage basicMessage) {
        OrderBig orderBig = orderBigService.selectById(basicMessage.getBusinessId());
        List<OrderBigItem> bigItems = bigItemService.selectByMainId(basicMessage.getBusinessId());
        orderBig.setOrderBigItemList(bigItems);
        if (null == orderBig){
            return;
        }
        BaseResponse response = bigService.forwardOP(orderBig);
        if (response.isSuccess()){
            return;
        }
        throw new BusinessException(response.getMessage());
    }

    /**
     * 大订单订单处理
     * @param basicMessage
     */
    @Override
    @RabbitListener(queues = QueueConstants.OCP_BIG_ORDER_PROCESS)
    @RabbitHandler
    public void processOrder(BasicMessage basicMessage) {
        OrderTemp orderTemp = orderTempService.selectById(basicMessage.getBusinessId());
        if (null == orderTemp) {
            return;
        }
        bigService.processOrder(orderTemp);
    }
}

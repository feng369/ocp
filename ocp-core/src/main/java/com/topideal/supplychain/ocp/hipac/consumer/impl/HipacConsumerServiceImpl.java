package com.topideal.supplychain.ocp.hipac.consumer.impl;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessException;
import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.ocp.hipac.consumer.HipacConsumerService;
import com.topideal.supplychain.ocp.hipac.service.HipacService;
import com.topideal.supplychain.ocp.mq.QueueConstants;
import com.topideal.supplychain.ocp.order.model.OrderHipac;
import com.topideal.supplychain.ocp.order.service.OrderHipacService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 海拍客
 *
 * @author xuxiaoyan
 * @date 2019-12-18 09:46
 */
@Service
public class HipacConsumerServiceImpl implements HipacConsumerService {

    @Autowired
    private HipacService hipacService;
    @Autowired
    private OrderHipacService orderHipacService;


    /**
     * 海拍客订单推OP
     * @param basicMessage
     */
    @Override
    @RabbitListener(queues = QueueConstants.OCP_HIPAC_ORDER_PUSH_OP)
    @RabbitHandler
    public void sendOrderOp(BasicMessage basicMessage) {
        OrderHipac orderHipac = orderHipacService.selectById(basicMessage.getBusinessId());
        if (null == orderHipac) {
            return;
        }
        BaseResponse baseResponse = hipacService.sendOrderOp(orderHipac);
        if (baseResponse.isSuccess()) {
            return;
        }
        throw new BusinessException(baseResponse.getMessage());
    }

    /**
     * 订单推ofc
     *
     * @param basicMessage
     */
    @Override
    @RabbitListener(queues = QueueConstants.OCP_HIPAC_ORDER_PUSH_OFC)
    @RabbitHandler
    public void sendOrderOfc(BasicMessage basicMessage) {
        OrderHipac orderHipac = orderHipacService.selectById(basicMessage.getBusinessId());
        if (null == orderHipac) {
            return;
        }
        BaseResponse baseResponse = hipacService.sendOrderOfc(orderHipac);
        if (baseResponse.isSuccess()) {
            return;
        }
        throw new BusinessException(baseResponse.getMessage());
    }
}

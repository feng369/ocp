package com.topideal.supplychain.ocp.gs.consumer.impl;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.exception.BusinessException;
import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.config.service.CatchOrderConfigService;
import com.topideal.supplychain.ocp.enums.OrderStatusEnum;
import com.topideal.supplychain.ocp.gs.consumer.GsConsumerService;
import com.topideal.supplychain.ocp.gs.service.GsService;
import com.topideal.supplychain.ocp.mq.QueueConstants;
import com.topideal.supplychain.ocp.order.model.OrderGs;
import com.topideal.supplychain.ocp.order.model.OrderTemp;
import com.topideal.supplychain.ocp.order.service.OrderGsService;
import com.topideal.supplychain.ocp.order.service.OrderTempService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>标题: TODO</p>
 * <p>功能: TODO</p>
 * <p>模块: ocp2</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.gs.consumer.impl</p>
 * <p>作者: topIdeal</p>
 * <p>创建日期: 2019-12-30 18:05</p>
 *
 * @version 1.0
 */
@Service
public class GsConsumerServiceImpl implements GsConsumerService {

    @Autowired
    private OrderTempService orderTempService;
    @Autowired
    private CatchOrderConfigService catchOrderConfigService;
    @Autowired
    private OrderGsService orderGsService;
    @Autowired
    private GsService gsService;

    /**
     * 环球捕手抓单
     *
     * @param basicMessage
     */
    @RabbitListener(queues = QueueConstants.OCP_GS_GET_ORDER)
    @RabbitHandler
    public void getOrder(BasicMessage basicMessage) {
        CatchOrderConfig catchOrderConfig = catchOrderConfigService.selectEnableById(basicMessage.getBusinessId());
        BusinessAssert.assertNotNull(catchOrderConfig,"抓单定义不存在");
        gsService.getOrder(catchOrderConfig);
    }

    /**
     * 处理环球捕手订单
     *
     * @param basicMessage
     */
    @RabbitListener(queues = QueueConstants.OCP_GS_ORDER_PROCESS)
    @RabbitHandler
    public void processOrder(BasicMessage basicMessage) {
        OrderTemp orderTemp = orderTempService.selectById(basicMessage.getBusinessId());
        if (orderTemp == null) {
            throw new BusinessException("临时订单不存在");
        }
        BusinessAssert.assertNotEmpty(basicMessage.getUdf1(),"抓单配置不存在");
        CatchOrderConfig catchOrderConfig = catchOrderConfigService.selectEnableById(Long.valueOf(basicMessage.getUdf1()));
        BusinessAssert.assertNotNull(catchOrderConfig,"抓单配置不存在");
        gsService.processOrder(orderTemp, catchOrderConfig);
    }

    /**
     * 商品拆分
     *
     * @param basicMessage
     */
    @RabbitListener(queues = QueueConstants.OCP_GS_ORDER_TO_KJB)
    @RabbitHandler
    public void sendOrderKjb(BasicMessage basicMessage) {
        OrderGs orderGs = orderGsService.selectById(basicMessage.getBusinessId());
        // 如果没有数据直接返回
        if (orderGs == null) {
            throw new BusinessException("订单不存在");
        }
        BaseResponse baseResponse = gsService.sendOrderKjb(orderGs);
        if (baseResponse.isSuccess()) {
            return;
        }
        throw new BusinessException(baseResponse.getMessage());
    }

    /**
     * 环球捕手订单推送
     * @param basicMessage
     */
    @RabbitListener(queues = QueueConstants.OCP_GS_ORDER_TO_OFC)
    @RabbitHandler
    public void sendOrderOfc(BasicMessage basicMessage) {
        OrderGs orderGs = orderGsService.selectById(basicMessage.getBusinessId());
        if (orderGs == null) {
            throw new BusinessException("订单不存在");
        }
        BaseResponse baseResponse = gsService.sendOrderOfc(orderGs);
        if (baseResponse.isSuccess()) {
            return;
        }
        throw new BusinessException(baseResponse.getMessage());
    }

    /**
     * 环球捕手订单推送
     * @param basicMessage
     */
    @RabbitListener(queues = QueueConstants.OCP_GS_ORDER_TO_OP)
    @RabbitHandler
    public void sendOrderOp(BasicMessage basicMessage) {
        OrderGs orderGs = orderGsService.selectById(basicMessage.getBusinessId());
        if (orderGs == null) {
            throw new BusinessException("订单不存在");
        }
        BaseResponse baseResponse = gsService.sendOrderOp(orderGs);
        if (baseResponse.isSuccess()) {
            return;
        }
        throw new BusinessException(baseResponse.getMessage());
    }
}

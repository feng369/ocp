package com.topideal.supplychain.ocp.baoma.consumer.impl;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessException;
import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.ocp.baoma.consumer.BaomaConsumerService;
import com.topideal.supplychain.ocp.baoma.service.BaomaService;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.config.service.CatchOrderConfigService;
import com.topideal.supplychain.ocp.mq.QueueConstants;
import com.topideal.supplychain.ocp.order.dto.OrderBaomaDto;
import com.topideal.supplychain.ocp.order.model.OrderTemp;
import com.topideal.supplychain.ocp.order.service.OrderBaomaService;
import com.topideal.supplychain.ocp.order.service.OrderTempService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wanzhaozhang
 * @date 2019/12/13
 * @description
 **/
@Component
public class BaomaConsumerServiceImpl implements BaomaConsumerService {

    @Autowired
    private BaomaService baomaService;
    @Autowired
    private CatchOrderConfigService catchOrderConfigService;
    @Autowired
    private OrderTempService orderTempService;
    @Autowired
    private OrderBaomaService orderBaomaService;

    /**
     * 抓取订单
     *
     * @param basicMessage
     */
    @RabbitListener(queues = QueueConstants.OCP_BAOMA_GET_ORDER)
    @RabbitHandler
    public void grabOrder(BasicMessage basicMessage) {
        CatchOrderConfig catchOrderConfig = catchOrderConfigService.selectEnableById(basicMessage.getBusinessId());
        if (catchOrderConfig == null || StringUtils.isEmpty(catchOrderConfig.getPlatformArguments())) {
            return;
        }
        BaseResponse response = baomaService.getOrder(catchOrderConfig);
        if (response.isSuccess()) {
            return;
        }
        throw new BusinessException(response.getMessage());
    }

    /**
     * 保存订单
     *
     * @param basicMessage
     */
    @RabbitListener(queues = QueueConstants.OCP_BAOMA_SAVE_ORDER)
    @RabbitHandler
    public void saveOrder(BasicMessage basicMessage) {
        OrderTemp orderTemp = orderTempService.selectById(basicMessage.getBusinessId());
        if (orderTemp == null) {
            return;
        }
        baomaService.saveOrder(orderTemp);

    }

    /**
     * 订单推送ofc
     *
     * @param basicMessage
     */
    @RabbitListener(queues = QueueConstants.OCP_BAOMA_ORDER_PUSH_OFC)
    @RabbitHandler
    public void pushOfc(BasicMessage basicMessage) {
        OrderBaomaDto orderBaoma = orderBaomaService.selectDtoById(basicMessage.getBusinessId());
        if (orderBaoma == null) {
            return;
        }
        BaseResponse baseResponse = baomaService.pushOfc(orderBaoma);
        if (baseResponse.isSuccess()) {
            return;
        }
        throw new BusinessException(baseResponse.getMessage());
    }

    /**
     * 订单推送op
     *
     * @param basicMessage
     */
    @RabbitListener(queues = QueueConstants.OCP_BAOMA_ORDER_PUSH_OP)
    @RabbitHandler
    public void pushOp(BasicMessage basicMessage) {
        OrderBaomaDto orderBaoma = orderBaomaService.selectDtoById(basicMessage.getBusinessId());
        if (orderBaoma == null) {
            return;
        }
        BaseResponse baseResponse = baomaService.pushOp(orderBaoma);
        if (baseResponse.isSuccess()) {
            return;
        }
        throw new BusinessException(baseResponse.getMessage());
    }


}

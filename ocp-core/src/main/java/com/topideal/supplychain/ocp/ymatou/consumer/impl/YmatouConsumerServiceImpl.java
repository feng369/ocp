package com.topideal.supplychain.ocp.ymatou.consumer.impl;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessException;
import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.config.service.CatchOrderConfigService;
import com.topideal.supplychain.ocp.enums.OrderStatusEnum;
import com.topideal.supplychain.ocp.master.model.Store;
import com.topideal.supplychain.ocp.master.service.StoreService;
import com.topideal.supplychain.ocp.mq.QueueConstants;
import com.topideal.supplychain.ocp.order.dto.OrderYmatouDto;
import com.topideal.supplychain.ocp.order.model.OrderTemp;
import com.topideal.supplychain.ocp.order.model.OrderYmatou;
import com.topideal.supplychain.ocp.order.service.OrderTempService;
import com.topideal.supplychain.ocp.order.service.OrderYmatouService;
import com.topideal.supplychain.ocp.ymatou.consumer.YmatouConsumerService;
import com.topideal.supplychain.ocp.ymatou.service.YmatouService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wanzhaozhang
 * @date 2019/12/3
 * @description
 **/
@Component
public class YmatouConsumerServiceImpl implements YmatouConsumerService {

    @Autowired
    private StoreService storeService;
    @Autowired
    private OrderYmatouService orderYmatouService;
    @Autowired
    private CatchOrderConfigService catchOrderConfigService;
    @Autowired
    private YmatouService ymatouService;
    @Autowired
    private OrderTempService orderTempService;


    /**
     * 处理保存订单[来源：单票抓单]
     *
     * @param basicMessage
     */
    @RabbitHandler
    @RabbitListener(queues = QueueConstants.OCP_YMATOU_ORDER_PROCESS)
    public void processOrder(BasicMessage basicMessage) {
        OrderTemp orderTemp = orderTempService.selectById(basicMessage.getBusinessId());
        if (orderTemp == null) {
            return;
        }
        Store store = storeService.selectById(Long.valueOf(basicMessage.getUdf1()));
        if (store == null) {
            throw new BusinessException("店铺信息不存在");
        }
        ymatouService.processOrder(store, orderTemp);

    }

    @RabbitHandler
    @RabbitListener(queues = QueueConstants.OCP_YMATOU_ORDER_PUSH_KJB)
    public void pushKjb(BasicMessage basicMessage) {
        OrderYmatou orderYmatou = orderYmatouService.selectById(basicMessage.getBusinessId());
        if (null == orderYmatou) {
            return;
        }
        BaseResponse baseResponse = ymatouService.toKjb(convert(orderYmatou));
        if (baseResponse.isSuccess()) {
            return;
        }
        throw new BusinessException(baseResponse.getMessage());
    }

    @RabbitHandler
    @RabbitListener(queues = QueueConstants.OCP_YMATOU_ORDER_PUSH_GEMINI)
    public void pushGemini(BasicMessage basicMessage) {
        OrderYmatou orderYmatou = orderYmatouService.selectById(basicMessage.getBusinessId());
        if (null == orderYmatou) {
            return;
        }
        //如果订单的状态为下发成功或请求税金分离接口成功则不进行调用
        if (OrderStatusEnum.SEND_SUCCESS.equals(orderYmatou.getSendStatus()) ||
                OrderStatusEnum.GEMINI_SUCCESS.equals(orderYmatou.getSendStatus())) {
            return;
        }
        BaseResponse baseResponse = ymatouService.toGemini(convert(orderYmatou));
        if (baseResponse.isSuccess()) {
            return;
        }
        throw new BusinessException(baseResponse.getMessage());
    }

    @RabbitHandler
    @RabbitListener(queues = QueueConstants.OCP_YMATOU_ORDER_PUSH_OP)
    public void pushOp(BasicMessage basicMessage) {
        OrderYmatou orderYmatou = orderYmatouService.selectById(basicMessage.getBusinessId());
        if (null == orderYmatou) {
            return;
        }
        BaseResponse baseResponse = ymatouService.toOp(convert(orderYmatou));
        if (baseResponse.isSuccess()) {
            return;
        }
        throw new BusinessException(baseResponse.getMessage());
    }

    @RabbitHandler
    @RabbitListener(queues = QueueConstants.OCP_YMATOU_ORDER_PUSH_OFC)
    public void pushOfc(BasicMessage basicMessage) {
        OrderYmatou orderYmatou = orderYmatouService.selectById(basicMessage.getBusinessId());
        if (null == orderYmatou) {
            return;
        }
        BaseResponse baseResponse = ymatouService.toOfc(convert(orderYmatou));
        if (baseResponse.isSuccess()) {
            return;
        }
        throw new BusinessException(baseResponse.getMessage());
    }

    private static OrderYmatouDto convert(OrderYmatou orderYmatou) {
        OrderYmatouDto dto = new OrderYmatouDto();
        BeanUtils.copyProperties(orderYmatou, dto);
        return dto;
    }

    /**
     * 抓取订单
     *
     * @param basicMessage
     */
    @RabbitListener(queues = QueueConstants.OCP_YMATOU_GRAB_ORDER)
    @RabbitHandler
    public void grabOrder(BasicMessage basicMessage) {
        CatchOrderConfig catchOrderConfig = catchOrderConfigService.selectEnableById(basicMessage.getBusinessId());
        if (catchOrderConfig == null) {
            return;
        }
        ymatouService.grabOrder(catchOrderConfig);
    }

    /**
     * 订单接单
     * @param basicMessage
     */
    @RabbitListener(queues = QueueConstants.OCP_YMATOU_TAKE_ORDER)
    @RabbitHandler
    public void takeOrder(BasicMessage basicMessage) {
        OrderYmatou orderYmatou = orderYmatouService.selectById(basicMessage.getBusinessId());
        if (null == orderYmatou) {
            return;
        }
        Store store = storeService.selectById(Long.valueOf(basicMessage.getUdf1()));
        if (store == null) {
            throw new BusinessException("店铺信息不存在");
        }
        BaseResponse baseResponse = ymatouService.takeOrder(convert(orderYmatou),store);
        if (baseResponse.isSuccess()) {
            return;
        }
        throw new BusinessException(baseResponse.getMessage());
    }

    /**
     * 订单支付
     * @param basicMessage
     */
    @RabbitListener(queues = QueueConstants.OCP_YMATOU_PAY_ORDER)
    @RabbitHandler
    public void payOrder(BasicMessage basicMessage) {
        OrderYmatou orderYmatou = orderYmatouService.selectById(basicMessage.getBusinessId());
        if (null == orderYmatou) {
            return;
        }
        Store store = storeService.selectById(Long.valueOf(basicMessage.getUdf1()));
        if (store == null) {
            throw new BusinessException("店铺信息不存在");
        }
        BaseResponse baseResponse = ymatouService.payOrder(convert(orderYmatou),store);
        if (baseResponse.isSuccess()) {
            return;
        }
        throw new BusinessException(baseResponse.getMessage());
    }
}

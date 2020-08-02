package com.topideal.supplychain.ocp.youzan.consumer.impl;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessException;
import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.config.service.CatchOrderConfigService;
import com.topideal.supplychain.ocp.enums.OrderStatusEnum;
import com.topideal.supplychain.ocp.master.model.Store;
import com.topideal.supplychain.ocp.master.service.StoreService;
import com.topideal.supplychain.ocp.mq.QueueConstants;
import com.topideal.supplychain.ocp.order.dto.OrderYouzanDto;
import com.topideal.supplychain.ocp.order.model.OrderTemp;
import com.topideal.supplychain.ocp.order.service.OrderTempService;
import com.topideal.supplychain.ocp.order.service.OrderYouzanService;
import com.topideal.supplychain.ocp.youzan.service.YouzanService;
import com.topideal.supplychain.ocp.youzan.consumer.YouzanConsumerService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName YouzanConsumerServiceImpl
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2019/11/28 9:38
 * @Version 1.0
 **/
@Service
public class YouzanConsumerServiceImpl implements YouzanConsumerService {

    @Autowired
    private OrderTempService orderTempService;
    @Autowired
    private YouzanService youzanService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private CatchOrderConfigService catchOrderConfigService;
    @Autowired
    private OrderYouzanService orderYouzanService;

    /**
     * 有赞抓取订单
     * @param basicMessage
     */
    @RabbitListener(queues = QueueConstants.OCP_YOUZAN_GET_ORDER)
    @RabbitHandler
    public void getOrder(BasicMessage basicMessage) {
        CatchOrderConfig catchOrderConfig = catchOrderConfigService.selectEnableById(basicMessage.getBusinessId());
        if (catchOrderConfig == null) {
            return;
        }
        youzanService.getOrder(catchOrderConfig);
    }

    /**
     * 处理有赞订单
     * @param basicMessage
     */
    @RabbitListener(queues = QueueConstants.OCP_YOUZAN_ORDER_PROCESS)
    @RabbitHandler
    public void processOrder(BasicMessage basicMessage){
        OrderTemp orderTemp = orderTempService.selectById(basicMessage.getBusinessId());
        if (orderTemp == null) {
            return;
        }
        Store store = storeService.selectById(Long.valueOf(basicMessage.getUdf1()));
        if (store == null) {
            throw new BusinessException("店铺信息不存在");
        }

        youzanService.processOrder(store,orderTemp);
    }


    /**
     * 有赞抓取漏掉订单
     * @param basicMessage
     */
    @RabbitListener(queues = QueueConstants.OCP_YOUZAN_GET_MISS_ORDER)
    @RabbitHandler
    public void getMissOrder(BasicMessage basicMessage) {
        CatchOrderConfig catchOrderConfig = catchOrderConfigService.selectEnableById(basicMessage.getBusinessId());
        if (catchOrderConfig == null) {
            return;
        }
        youzanService.getMissOrder(catchOrderConfig);
    }

    /**
     * 有赞订单推送
     * @param basicMessage
     */
    @RabbitListener(queues = QueueConstants.OCP_YOUZAN_ORDER_TO_OFC)
    @RabbitHandler
    public void sendOrderOfc(BasicMessage basicMessage) {
        OrderYouzanDto orderYouzan = orderYouzanService.selectDtoById(basicMessage.getBusinessId());
        if (orderYouzan == null) {
            return;
        }
        /*if (OrderStatusEnum.SUCCESS.equals(orderYouzan.getToStatus())) {
            return;
        }*/
        BaseResponse baseResponse = youzanService.sendOrderOfc(orderYouzan);
        if (baseResponse.isSuccess()) {
            return;
        }
        throw new BusinessException(baseResponse.getMessage());
    }

    /**
     * 推送订单到跨境宝
     * @param basicMessage
     */
    @RabbitListener(queues = QueueConstants.OCP_YOUZAN_ORDER_TO_KJB)
    @RabbitHandler
    public void sendOrderKjb(BasicMessage basicMessage) {
        OrderYouzanDto orderYouzan = orderYouzanService.selectDtoById(basicMessage.getBusinessId());
        if (orderYouzan == null) {
            return;
        }
        if (OrderStatusEnum.KJB_SUCCESS.equals(orderYouzan.getToStatus())) {
            return;
        }

        BaseResponse baseResponse = youzanService.sendOrderKjb(orderYouzan);
        if (baseResponse.isSuccess()) {
            return;
        }
        throw new BusinessException(baseResponse.getMessage());
    }

    /**
     * 有赞订单推送
     * @param basicMessage
     */
    @RabbitListener(queues = QueueConstants.OCP_YOUZAN_ORDER_TO_OP)
    @RabbitHandler
    public void sendOrderOp(BasicMessage basicMessage) {
        OrderYouzanDto orderYouzan = orderYouzanService.selectDtoById(basicMessage.getBusinessId());
        if (orderYouzan == null) {
            return;
        }
        /*if (OrderStatusEnum.SUCCESS.equals(orderYouzan.getToStatus())) {
            return;
        }*/
        BaseResponse baseResponse = youzanService.sendOrderOp(orderYouzan);
        if (baseResponse.isSuccess()) {
            return;
        }
        throw new BusinessException(baseResponse.getMessage());
    }

    /**
     * 有赞订单推送
     * @param basicMessage
     */
    @RabbitListener(queues = QueueConstants.OCP_YOUZAN_ORDER_TO_ESD)
    @RabbitHandler
    public void sendOrderEsd(BasicMessage basicMessage) {
        OrderYouzanDto orderYouzan = orderYouzanService.selectDtoById(basicMessage.getBusinessId());
        if (orderYouzan == null) {
            return;
        }
        /*if (OrderStatusEnum.SUCCESS.equals(orderYouzan.getToStatus())) {
            return;
        }*/
        BaseResponse baseResponse = youzanService.sendOrderEsd(orderYouzan);
        if (baseResponse.isSuccess()) {
            return;
        }
        throw new BusinessException(baseResponse.getMessage());
    }
}

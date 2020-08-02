package com.topideal.supplychain.ocp.xiaomi.consumer.impl;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessException;
import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.config.service.CatchOrderConfigService;
import com.topideal.supplychain.ocp.master.model.Store;
import com.topideal.supplychain.ocp.master.service.StoreService;
import com.topideal.supplychain.ocp.mq.QueueConstants;
import com.topideal.supplychain.ocp.order.dto.OrderXiaomiDto;
import com.topideal.supplychain.ocp.order.model.OrderTemp;
import com.topideal.supplychain.ocp.order.service.OrderTempService;
import com.topideal.supplychain.ocp.order.service.OrderXiaomiService;
import com.topideal.supplychain.ocp.xiaomi.consumer.XiaomiConsumerService;
import com.topideal.supplychain.ocp.xiaomi.service.XiaomiService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wanzhaozhang
 * @date 2019/12/3
 * @description
 **/
@Component
public class XiaomiConsumerServiceImpl implements XiaomiConsumerService {

    @Autowired
    private StoreService storeService;
    @Autowired
    private OrderXiaomiService orderXiaomiService;
    @Autowired
    private CatchOrderConfigService catchOrderConfigService;
    @Autowired
    private XiaomiService xiaomiService;
    @Autowired
    private OrderTempService orderTempService;


    /**
     * 处理并保存订单
     *
     * @param basicMessage
     */
    @Override
    @RabbitHandler
    @RabbitListener(queues = QueueConstants.OCP_XIAOMI_SAVE_ORDER)
    public void processOrder(BasicMessage basicMessage) {
        OrderTemp orderTemp = orderTempService.selectById(basicMessage.getBusinessId());
        if (orderTemp == null) {
            return;
        }
        Store store = storeService.selectById(Long.valueOf(basicMessage.getUdf1()));
        if (store == null) {
            throw new BusinessException("店铺信息不存在");
        }
        xiaomiService.processOrder(store, orderTemp);
    }

    @Override
    @RabbitHandler
    @RabbitListener(queues = QueueConstants.OCP_XIAOMI_ORDER_PUSH_KJB)
    public void pushKjb(BasicMessage basicMessage) {
        OrderXiaomiDto orderXiaomiDto= orderXiaomiService.selectDtoById(basicMessage.getBusinessId());
        if (null == orderXiaomiDto) {
            return;
        }
        BaseResponse baseResponse = xiaomiService.pushKjb(orderXiaomiDto);
        if (baseResponse.isSuccess()) {
            return;
        }
        throw new BusinessException(baseResponse.getMessage());
    }


    /**
     * 抓取订单
     *
     * @param basicMessage
     */
    @Override
    @RabbitListener(queues = QueueConstants.OCP_XIAOMI_GET_ORDER)
    @RabbitHandler
    public void getOrder(BasicMessage basicMessage) {
        CatchOrderConfig catchOrderConfig = catchOrderConfigService.selectEnableById(basicMessage.getBusinessId());
        if (catchOrderConfig == null) {
            return;
        }
        xiaomiService.getOrder(catchOrderConfig);
    }

    /**
     * 漏单抓单
     * @param basicMessage
     */
    @Override
    @RabbitListener(queues = QueueConstants.OCP_XIAOMI_GET_MISS_ORDER)
    @RabbitHandler
    public void getMissOrder(BasicMessage basicMessage) {
        CatchOrderConfig catchOrderConfig = catchOrderConfigService.selectEnableById(basicMessage.getBusinessId());
        if (null == catchOrderConfig) {
            return;
        }
        xiaomiService.getMissOrder(catchOrderConfig);
    }
}

package com.topideal.supplychain.ocp.beibei.consumer.impl;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessException;
import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.ocp.beibei.consumer.BeibeiConsumerService;
import com.topideal.supplychain.ocp.beibei.service.BeibeiService;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.config.service.CatchOrderConfigService;
import com.topideal.supplychain.ocp.master.model.Store;
import com.topideal.supplychain.ocp.master.service.StoreService;
import com.topideal.supplychain.ocp.mq.QueueConstants;
import com.topideal.supplychain.ocp.order.dto.OrderBeibeiDto;
import com.topideal.supplychain.ocp.order.model.OrderTemp;
import com.topideal.supplychain.ocp.order.service.OrderBeibeiService;
import com.topideal.supplychain.ocp.order.service.OrderTempService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wanzhaozhang
 * @date 2020/3/23
 * @description
 **/
@Component
public class BeibeiConsumerServiceImpl implements BeibeiConsumerService {
    @Autowired
    private StoreService storeService;
    @Autowired
    private OrderBeibeiService orderBeibeiService;
    @Autowired
    private CatchOrderConfigService catchOrderConfigService;
    @Autowired
    private BeibeiService beibeiService;
    @Autowired
    private OrderTempService orderTempService;

    /**
     * 保存訂單
     * @param basicMessage
     */
    @Override
    @RabbitHandler
    @RabbitListener(queues = QueueConstants.OCP_BEIBEI_SAVE_ORDER)
    public void saveOrder(BasicMessage basicMessage) {
        OrderTemp orderTemp = orderTempService.selectById(basicMessage.getBusinessId());
        if (orderTemp == null) {
            return;
        }
        Store store = storeService.selectById(Long.valueOf(basicMessage.getUdf1()));
        if (store == null) {
            throw new BusinessException("店铺信息不存在");
        }
        beibeiService.processOrder(orderTemp,store);
    }

    /**
     * 推送ofc
     * @param basicMessage
     */
    @Override
    @RabbitHandler
    @RabbitListener(queues = QueueConstants.OCP_BEIBEI_ORDER_TO_OFC)
    public void pushOfc(BasicMessage basicMessage) {
        OrderBeibeiDto orderBeibeiDto= orderBeibeiService.selectDtoById(basicMessage.getBusinessId());
        if (null == orderBeibeiDto) {
            return;
        }
        BaseResponse baseResponse = beibeiService.pushOfc(orderBeibeiDto);
        if (baseResponse.isSuccess()) {
            return;
        }
        throw new BusinessException(baseResponse.getMessage());
    }

    /**
     *抓单
     * @param basicMessage
     */
    @Override
    @RabbitHandler
    @RabbitListener(queues = QueueConstants.OCP_BEIBEI_GET_ORDER)
    public void getOrder(BasicMessage basicMessage) {
        CatchOrderConfig catchOrderConfig = catchOrderConfigService.selectEnableById(basicMessage.getBusinessId());
        if (catchOrderConfig == null) {
            return;
        }
        beibeiService.getOrder(catchOrderConfig);
    }
}

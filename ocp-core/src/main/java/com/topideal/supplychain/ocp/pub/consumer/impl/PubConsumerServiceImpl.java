package com.topideal.supplychain.ocp.pub.consumer.impl;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.ocp.config.mapper.TransferConfigMapper;
import com.topideal.supplychain.ocp.mq.QueueConstants;
import com.topideal.supplychain.ocp.order.model.OrderGlobal;
import com.topideal.supplychain.ocp.order.model.OrderPub;
import com.topideal.supplychain.ocp.order.service.OrderPubService;
import com.topideal.supplychain.ocp.pub.consumer.PubConsumerService;
import com.topideal.supplychain.ocp.pub.service.PubService;
import com.topideal.supplychain.ocp.vip.service.VipService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>标题: </p>
 * <p>模块: ocp-parent</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.pub.consumer.impl</p>
 * <p>作者: LeoZhang</p>
 * <p>创建日期: 2019/12/19 14:39</p>
 *
 * @version 1.0
 */
@Service
public class PubConsumerServiceImpl implements PubConsumerService {

    @Autowired
    private OrderPubService orderPubService;
    @Autowired
    private PubService pubService;

    /**
     * 标准订单推esd消费者
     *
     * @param message
     */
    @RabbitHandler
    @RabbitListener(queues = QueueConstants.OCP_PUB_ORDER_PUSH_ESD)
    public void pushOrder(BasicMessage message) {
        OrderPub orderPub = orderPubService.selectById(message.getBusinessId());
        if (orderPub == null) {
            return;
        }
        BaseResponse baseResponse = pubService.pushOrderToEsd(orderPub);
        //如果失败重试
        BusinessAssert.assertIsTrue(baseResponse.isSuccess(), baseResponse.getMessage());
    }
}

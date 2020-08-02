package com.topideal.supplychain.ocp.vip.consumer.impl;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.config.service.CatchOrderConfigService;
import com.topideal.supplychain.ocp.mq.QueueConstants;
import com.topideal.supplychain.ocp.order.model.OrderTemp;
import com.topideal.supplychain.ocp.order.model.OrderVip;
import com.topideal.supplychain.ocp.order.service.OrderTempService;
import com.topideal.supplychain.ocp.order.service.OrderVipService;
import com.topideal.supplychain.ocp.vip.consumer.VipConsumerService;
import com.topideal.supplychain.ocp.vip.dto.VipRequestArgsVo;
import com.topideal.supplychain.ocp.vip.service.VipService;
import com.topideal.supplychain.util.JacksonUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>标题: 唯品会订单推送OFC</p>
 * <p>模块: ocp-parent</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.vip.consumer</p>
 * <p>作者: LeoZhang</p>
 * <p>创建日期: 2019/11/28 17:03</p>
 *
 * @version 1.0
 */
@Component
public class VipConsumerServiceImpl implements VipConsumerService {

    @Autowired
    private OrderVipService orderVipService;
    @Autowired
    private VipService vipService;
    @Autowired
    private CatchOrderConfigService catchOrderConfigService;
    @Autowired
    private OrderTempService orderTempService;

    /**
     * 唯品会抓单消费者
     *
     * @param message
     */
    @Override
    @RabbitHandler
    @RabbitListener(queues = QueueConstants.OCP_VIP_GET_ORDER)
    public void getOrder(BasicMessage message) {
        CatchOrderConfig catchOrderConfig = catchOrderConfigService.selectEnableById(message.getBusinessId());
        if (catchOrderConfig == null) {
            return;
        }
        //解析唯品平台参数
        VipRequestArgsVo vipRequestArgsVo = JacksonUtils.readValue(catchOrderConfig.getPlatformArguments(), VipRequestArgsVo.class);
        //如果参数解析失败重试处理
        BusinessAssert.assertNotNull(vipRequestArgsVo, "唯品平台参数未配置");
        //按照关区和业务模式的维度抓单,每一次抓单都保证原子性。即使某一次抓单已经保存订单成功，但是后面存在异常也会保留订单信息
        vipRequestArgsVo.getArgsList().forEach(args -> vipService.getOrder(catchOrderConfig, vipRequestArgsVo, args));
    }

    /**
     * 唯品会订单推ofc消费者
     *
     * @param message
     */
    @Override
    @RabbitHandler
    @RabbitListener(queues = QueueConstants.OCP_VIP_ORDER_PUSH_OFC)
    public void pushOrderToOfc(BasicMessage message) {
        OrderVip orderVip = orderVipService.selectById(message.getBusinessId());
        //如果没有数据直接返回
        if (orderVip == null) {
            return;
        }
        BaseResponse baseResponse = vipService.pushOrderToOfc(orderVip);
        //不是成功重试
        BusinessAssert.assertIsTrue(baseResponse.isSuccess(),baseResponse.getMessage());
    }

    /**
     * 唯品会推送订单到ESD
     * @param message
     */
    @Override
    @RabbitHandler
    @RabbitListener(queues = QueueConstants.OCP_VIP_ORDER_PUSH_ESD)
    public void pushOrderToEsd(BasicMessage message) {
        OrderVip orderVip = orderVipService.selectById(message.getBusinessId());
        //如果没有数据直接返回
        if (orderVip == null) {
            return;
        }
        BaseResponse<T> baseResponse = vipService.pushOrderToEsd(orderVip);
        //不是成功重试
        BusinessAssert.assertIsTrue(baseResponse.isSuccess(),baseResponse.getMessage());
    }

    /**
     * 唯品抓单订单处理
     * @param basicMessage
     */
    @Override
    @RabbitHandler
    @RabbitListener(queues = QueueConstants.OCP_VIP_ORDER_PROCESS)
    public void processOrder(BasicMessage basicMessage) {
        OrderTemp orderTemp = orderTempService.selectById(basicMessage.getBusinessId());
        if (null == orderTemp) {
            return;
        }
        CatchOrderConfig config = catchOrderConfigService.selectById(Long.valueOf(basicMessage.getUdf1()));
        BusinessAssert.assertNotNull(config, "接单配置不存在,id为：" + basicMessage.getUdf1());

        vipService.processOrder(orderTemp, config);
    }
}

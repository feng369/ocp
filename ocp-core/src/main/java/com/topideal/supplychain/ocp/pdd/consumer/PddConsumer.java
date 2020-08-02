package com.topideal.supplychain.ocp.pdd.consumer;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.mq.NeedRetryException;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.config.model.TransferConfig;
import com.topideal.supplychain.ocp.config.service.CatchOrderConfigService;
import com.topideal.supplychain.ocp.config.service.TransferConfigService;
import com.topideal.supplychain.ocp.enums.LogisticsEnum;
import com.topideal.supplychain.ocp.enums.OrderStatusEnum;
import com.topideal.supplychain.ocp.enums.OrderTypeEnum;
import com.topideal.supplychain.ocp.mq.QueueConstants;
import com.topideal.supplychain.ocp.order.model.OrderPdd;
import com.topideal.supplychain.ocp.order.model.OrderTemp;
import com.topideal.supplychain.ocp.order.service.OrderPddService;
import com.topideal.supplychain.ocp.order.service.OrderTempService;
import com.topideal.supplychain.ocp.pdd.service.PddService;
import com.topideal.supplychain.util.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author klw
 * @date 2019/12/12 10:06
 * @description: 拼多多mq消费者
 */
@Component
public class PddConsumer {

    @Autowired
    private CatchOrderConfigService catchOrderConfigService;
    @Autowired
    private PddService pddService;
    @Autowired
    private OrderTempService orderTempService;
    @Autowired
    private OrderPddService orderPddService;
    @Autowired
    private TransferConfigService transferConfigService;

    /**
     * 抓取订单
     *
     * @param basicMessage
     */
    @RabbitHandler
    @RabbitListener(queues = QueueConstants.PDD_GET_ORDER)
    public void catchOrder(BasicMessage basicMessage) {
        CatchOrderConfig catchOrderConfig = catchOrderConfigService.selectById(basicMessage.getBusinessId());
        if (catchOrderConfig == null) {
            return;
        }
        pddService.catchOrder(catchOrderConfig, new Date(Long.valueOf(basicMessage.getUdf1())));
    }

    /**
     * 漏单抓取订单
     *
     * @param basicMessage
     */
    @RabbitHandler
    @RabbitListener(queues = QueueConstants.PDD_GET_MISS_ORDER)
    public void catchMissOrder(BasicMessage basicMessage) {
        CatchOrderConfig catchOrderConfig = catchOrderConfigService.selectById(basicMessage.getBusinessId());
        if (catchOrderConfig == null) {
            return;
        }
        //为什么要循环48次。。。因为拼多多最长抓单时长为半小时。漏单是要抓取一天的订单，所以分成了48份
        Date endTime = new Date(Long.valueOf(basicMessage.getUdf1()));
        for (int i = 0; i < 48; i++){
            pddService.catchOrder(catchOrderConfig, DateUtils.addMinute(endTime, -30 * i));
        }

    }

    /**
     * 抓取的订单处理
     *
     * @param basicMessage
     */
    @RabbitHandler
    @RabbitListener(queues = QueueConstants.PDD_CATCH_ORDER_PROCESS)
    public void processCatchOrder(BasicMessage basicMessage) {
        OrderTemp orderTemp = orderTempService.selectById(basicMessage.getBusinessId());
        if (orderTemp == null) {
            return;
        }
        CatchOrderConfig config = catchOrderConfigService.selectById(Long.valueOf(basicMessage.getUdf1()));
        BusinessAssert.assertNotNull(config, "接单配置不存在,id为：" + basicMessage.getUdf1());

        pddService.processCatchOrder(orderTemp, config, Boolean.valueOf(basicMessage.getUdf2()));
    }

    /**
     * 接单的订单处理
     *
     * @param basicMessage
     */
    @RabbitHandler
    @RabbitListener(queues = QueueConstants.PDD_RECEIVE_ORDER_PROCESS)
    public void processReceiveOrder(BasicMessage basicMessage) {
        OrderTemp orderTemp = orderTempService.selectById(basicMessage.getBusinessId());
        if (orderTemp == null) {
            return;
        }
        pddService.processReceiveOrder(orderTemp);
    }

    /**
     * 拼多多推送KJB做商品拆分
     *
     * @param basicMessage
     */
    @RabbitHandler
    @RabbitListener(queues = QueueConstants.PDD_ORDER_TO_KJB)
    public void pushKJB(BasicMessage basicMessage) {
        OrderPdd orderPdd = orderPddService.selectById(basicMessage.getBusinessId());
        if (orderPdd == null) {
            return;
        }
        //非抓单订单不处理
        if (!OrderTypeEnum.CATCH.equals(orderPdd.getType())) {
            return;
        }
        //非制单或者推送KJB失败则不处理
        if (!OrderStatusEnum.INIT.equals(orderPdd.getStatus()) && !OrderStatusEnum.KJB_FAILURE.equals(orderPdd.getStatus())) {
            return;
        }
        BaseResponse baseResponse = pddService.pushKJB(orderPdd);
        if (!baseResponse.isSuccess()) {
            throw new NeedRetryException(baseResponse.getMessage());
        }
    }

    /**
     * 拼多多推送GEMINI做税金分离
     *
     * @param basicMessage
     */
    @RabbitHandler
    @RabbitListener(queues = QueueConstants.PDD_ORDER_TO_GEMINI)
    public void pushGemini(BasicMessage basicMessage) {
        OrderPdd orderPdd = orderPddService.selectById(basicMessage.getBusinessId());
        if (orderPdd == null) {
            return;
        }
        //非抓单订单不处理
        if (!OrderTypeEnum.CATCH.equals(orderPdd.getType())) {
            return;
        }
        //非推送KJB成功或者推送GEMINI失败则不处理
        if (!OrderStatusEnum.KJB_SUCCESS.equals(orderPdd.getStatus()) && !OrderStatusEnum.GEMINI_FAILURE.equals(orderPdd.getStatus())) {
            return;
        }
        BaseResponse baseResponse = pddService.pushGemini(orderPdd);
        if (!baseResponse.isSuccess()) {
            throw new NeedRetryException(baseResponse.getMessage());
        }
    }

    /**
     * 拼多多订单下发路由
     *
     * @param basicMessage
     */
    @RabbitHandler
    @RabbitListener(queues = QueueConstants.PDD_ORDER_PUSH_ROUTER)
    public void pushRouter(BasicMessage basicMessage) {
        OrderPdd order = orderPddService.selectById(basicMessage.getBusinessId());
        if (order == null) {
            return;
        }
        TransferConfig transferConfig = transferConfigService.findByUnique(order.getElectricCode(), order.getCbepcomCode(),
                StringUtils.isEmpty(order.getLogistCode()) ? LogisticsEnum.VL.getCode() : order.getLogistCode(),
                order.getCustomsCode(), order.getBusiMode());
        if (transferConfig == null) {
            throw new NeedRetryException("转单配置不存在");
        }
        pddService.router(order, transferConfig);
    }

    /**
     * 拼多多订单下发ofc
     *
     * @param basicMessage
     */
    @RabbitHandler
    @RabbitListener(queues = QueueConstants.PDD_ORDER_TO_OFC)
    public void pushOfc(BasicMessage basicMessage) {
        OrderPdd order = orderPddService.selectById(basicMessage.getBusinessId());
        if (order == null) {
            return;
        }
        //
        if (!OrderStatusEnum.GEMINI_SUCCESS.equals(order.getStatus())
                && !OrderStatusEnum.SEND_SUCCESS.equals(order.getStatus())
                && !OrderStatusEnum.SEND_FAILURE.equals(order.getStatus())
                && !(OrderStatusEnum.INIT.equals(order.getStatus()) && OrderTypeEnum.RECEIVE.equals(order.getType()))) {
            return;
        }
        TransferConfig transferConfig = transferConfigService.selectById(Long.valueOf(basicMessage.getUdf1()));
        if (transferConfig == null) {
            throw new NeedRetryException("转单配置不存在");
        }
        BaseResponse baseResponse = pddService.pushOfc(order, transferConfig);
        if (!baseResponse.isSuccess()) {
            throw new NeedRetryException(baseResponse.getMessage());
        }
    }

    /**
     * 拼多多订单下发op
     *
     * @param basicMessage
     */
    @RabbitHandler
    @RabbitListener(queues = QueueConstants.PDD_ORDER_TO_OP)
    public void pushOp(BasicMessage basicMessage) {
        OrderPdd order = orderPddService.selectById(basicMessage.getBusinessId());
        if (order == null) {
            return;
        }
        //
        if (!OrderStatusEnum.GEMINI_SUCCESS.equals(order.getStatus())
                && !OrderStatusEnum.SEND_SUCCESS.equals(order.getStatus())
                && !OrderStatusEnum.SEND_FAILURE.equals(order.getStatus())
                && !(OrderStatusEnum.INIT.equals(order.getStatus()) && OrderTypeEnum.RECEIVE.equals(order.getType()))) {
            return;
        }
        TransferConfig transferConfig = transferConfigService.selectById(Long.valueOf(basicMessage.getUdf1()));
        if (transferConfig == null) {
            throw new NeedRetryException("转单配置不存在");
        }
        BaseResponse baseResponse = pddService.pushOp(order, transferConfig);
        if (!baseResponse.isSuccess()) {
            throw new NeedRetryException(baseResponse.getMessage());
        }
    }


    /**
     * 拼多多订单下发ESD
     *
     * @param basicMessage
     */
    @RabbitHandler
    @RabbitListener(queues = QueueConstants.PDD_ORDER_TO_ESD)
    public void pushEsd(BasicMessage basicMessage) {
        OrderPdd order = orderPddService.selectById(basicMessage.getBusinessId());
        if (order == null) {
            return;
        }
        //只有接单订单才有下发esd
        if (!OrderTypeEnum.RECEIVE.equals(order.getType())) {
            return;
        }
        //
        if (!OrderStatusEnum.GEMINI_SUCCESS.equals(order.getStatus())
                && !OrderStatusEnum.SEND_SUCCESS.equals(order.getStatus())
                && !OrderStatusEnum.SEND_FAILURE.equals(order.getStatus())
                && !OrderStatusEnum.INIT.equals(order.getStatus())) {
            return;
        }
        TransferConfig transferConfig = transferConfigService.selectById(Long.valueOf(basicMessage.getUdf1()));
        if (transferConfig == null) {
            throw new NeedRetryException("转单配置不存在");
        }
        BaseResponse baseResponse = pddService.pushEsd(order, transferConfig);
        if (!baseResponse.isSuccess()) {
            throw new NeedRetryException(baseResponse.getMessage());
        }
    }
}

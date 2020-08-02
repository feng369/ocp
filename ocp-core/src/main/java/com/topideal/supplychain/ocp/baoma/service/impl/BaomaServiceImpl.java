package com.topideal.supplychain.ocp.baoma.service.impl;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.exception.BusinessException;
import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.mq.MessageSender;
import com.topideal.supplychain.ocp.baoma.converter.BaomaDtoConverter;
import com.topideal.supplychain.ocp.baoma.dto.BaomaResponse;
import com.topideal.supplychain.ocp.baoma.service.BaomaApiService;
import com.topideal.supplychain.ocp.baoma.service.BaomaService;
import com.topideal.supplychain.ocp.config.dto.CatchDefaultConfig;
import com.topideal.supplychain.ocp.config.dto.TransferDefaultConfig;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.config.model.TransferConfig;
import com.topideal.supplychain.ocp.config.service.TransferConfigService;
import com.topideal.supplychain.ocp.enums.ForwardSystemEnum;
import com.topideal.supplychain.ocp.enums.LogisticsEnum;
import com.topideal.supplychain.ocp.enums.OrderStatusEnum;
import com.topideal.supplychain.ocp.master.model.Store;
import com.topideal.supplychain.ocp.master.service.StoreService;
import com.topideal.supplychain.ocp.mq.QueueConstants;
import com.topideal.supplychain.ocp.ofc.dto.OfcRequest;
import com.topideal.supplychain.ocp.ofc.service.OfcApiService;
import com.topideal.supplychain.ocp.op.dto.OpRequest;
import com.topideal.supplychain.ocp.op.service.OpApiService;
import com.topideal.supplychain.ocp.order.dto.OrderBaomaDto;
import com.topideal.supplychain.ocp.order.model.OrderBaoma;
import com.topideal.supplychain.ocp.order.model.OrderBaomaGoods;
import com.topideal.supplychain.ocp.order.model.OrderTemp;
import com.topideal.supplychain.ocp.order.service.OrderBaomaGoodsService;
import com.topideal.supplychain.ocp.order.service.OrderBaomaService;
import com.topideal.supplychain.ocp.order.service.OrderTempService;
import com.topideal.supplychain.util.JacksonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wanzhaozhang
 * @date 2019/12/13
 * @description
 **/
@Service
public class BaomaServiceImpl implements BaomaService {

    @Autowired
    private StoreService storeService;
    @Autowired
    private BaomaApiService baomaApiService;
    @Autowired
    protected MessageSender messageSender;
    @Autowired
    private OrderTempService orderTempService;
    @Autowired
    private OrderBaomaService orderBaomaService;
    @Autowired
    private OrderBaomaGoodsService orderBaomaGoodsService;
    @Autowired
    private TransferConfigService transferConfigService;
    @Autowired
    private OpApiService opApiService;
    @Autowired
    private OfcApiService ofcApiService;


    /**
     * 抓单
     *
     * @param catchOrderConfig
     */
    @Override
    public BaseResponse getOrder(CatchOrderConfig catchOrderConfig) {
        BaseResponse response = BaseResponse.responseFailure("宝妈时光抓单失败");
        Store store = storeService.selectById(catchOrderConfig.getStoreId());
        if (store == null || StringUtils.isEmpty(store.getArguments())) {
            throw new BusinessException("店铺信息有误");
        }
        BaomaResponse baomaResponse = baomaApiService.getOrder(catchOrderConfig, store);
        if (baomaResponse.getSuccess()) {
            response.setFlag("SUCCESS");
            //保存订单
            saveOrder(baomaResponse.getData(), store.getCode(), catchOrderConfig);

        }
        return response;
    }

    /**
     * 解析数据入库
     *
     * @param orderBaomas
     */
    private void saveOrder(List<OrderBaoma> orderBaomas, String storeCode, CatchOrderConfig catchOrderConfig) {
        //设置平台，店铺等参数,入库
        for (OrderBaoma order : orderBaomas) {
            order.setStoreCode(storeCode);
            order.setStoreId(String.valueOf(catchOrderConfig.getStoreId()));
            order.setEbpCode(catchOrderConfig.getPlatformCode());
            order.setEbpName(catchOrderConfig.getPlatformName());
            order.setEnterCode(catchOrderConfig.getMerchantCode());
            order.setSendState(OrderStatusEnum.INIT);
            //e.g. {"customsCode": "0910","busiMode": "10","ciqCode":"210100"}
            CatchDefaultConfig catchDefaultConfig = JacksonUtils.readValue(catchOrderConfig.getDefaultArguments(), CatchDefaultConfig.class);
            if (catchDefaultConfig != null) {
                order.setBusiMode(catchDefaultConfig.getBusiMode());
                order.setCustomsCode(catchDefaultConfig.getCustomsCode());
                order.setCustomsType("1");//海关类型，写死
                order.setCiqbCode(catchDefaultConfig.getCiqCode());
            }
            //插入临时表数据，并发送处理的mq
            OrderTemp orderTemp = new OrderTemp(JacksonUtils.toJSon(order));
            orderTempService.insertAndSendMq(orderTemp, QueueConstants.QueueEnum.OCP_BAOMA_SAVE_ORDER,
                    new BasicMessage(orderTemp.getId(), order.getOrderNo(), catchOrderConfig.getId().toString(), ""));
        }
    }

    @Override
    @Transactional
    public void saveOrder(OrderTemp orderTemp) {
        OrderBaoma orderBaoma = JacksonUtils.readValue(orderTemp.getJson(), OrderBaoma.class);
        //转单配置
        TransferConfig transferConfig = transferConfigService.findByUnique(orderBaoma.getEnterCode(), orderBaoma.getEbpCode(),
                LogisticsEnum.VL.getCode(), orderBaoma.getCustomsCode(), orderBaoma.getBusiMode());
        if (transferConfig == null) {
            throw new BusinessException("未找到转发配置");
        }
        //保存订单信息
        ForwardSystemEnum forwardSystem = transferConfig.getForwardSystem();
        orderBaoma.setSendSystem(forwardSystem);
        orderBaomaService.insert(orderBaoma);
        //保存商品信息
        for (OrderBaomaGoods goods : orderBaoma.getOrderDetail()) {
            goods.setOrderId(orderBaoma.getId());
            orderBaomaGoodsService.insert(goods);
        }
        QueueConstants.QueueEnum queueEnum = QueueConstants.QueueEnum.getQueueEnumByName("ocp.baoma.order.push." + forwardSystem.getQueue());
        //删除临时表的记录
        orderTempService.deleteById(orderTemp.getId());
        //发送转单mq
        messageSender.send(queueEnum, new BasicMessage(orderBaoma.getId(), orderBaoma.getCode()));

    }

    @Override
    @Transactional
    public BaseResponse pushOfc(OrderBaomaDto orderBaoma) {
        TransferConfig transferConfig = transferConfigService.findByUnique(orderBaoma.getEnterCode(), orderBaoma.getEbpCode(),
                LogisticsEnum.VL.getCode(), orderBaoma.getCustomsCode(), orderBaoma.getBusiMode());
        if (transferConfig == null) {
            orderBaomaService.updateOrderStatus(orderBaoma.getId(), OrderStatusEnum.SEND_FAILURE.getValue(), "订单推送OFC未找到对应转发配置");
            return BaseResponse.responseFailure("订单推送OFC未找到对应转发配置");
        }
        TransferDefaultConfig transferDefaultConfig = JacksonUtils.readValue(transferConfig.getConfiguration(), TransferDefaultConfig.class);
        setParamsOfOrder(orderBaoma, ForwardSystemEnum.OFC, transferConfig,transferDefaultConfig);
        OfcRequest ofcRequest = BaomaDtoConverter.convertOfc(orderBaoma, transferDefaultConfig);
        BaseResponse response = ofcApiService.sendOrder(ofcRequest, orderBaoma.getOrderNo(), orderBaoma.getCode());
        OrderStatusEnum orderStatus = response.isSuccess() ? OrderStatusEnum.SEND_SUCCESS : OrderStatusEnum.SEND_FAILURE;
        //更新发送状态
        orderBaomaService.updateOrderStatus(orderBaoma.getId(), orderStatus.getValue(), response.getMessage());
        return response;
    }

    @Override
    @Transactional
    public BaseResponse pushOp(OrderBaomaDto orderBaoma) {
        TransferConfig transferConfig = transferConfigService.findByUnique(orderBaoma.getEnterCode(), orderBaoma.getEbpCode(),
                LogisticsEnum.VL.getCode(), orderBaoma.getCustomsCode(), orderBaoma.getBusiMode());
        if (transferConfig == null) {
            orderBaomaService.updateOrderStatus(orderBaoma.getId(), OrderStatusEnum.SEND_FAILURE.getValue(), "订单推送OP未找到对应转发配置");
            return BaseResponse.responseFailure("订单推送OP未找到对应转发配置");
        }
        TransferDefaultConfig transferDefaultConfig = JacksonUtils.readValue(transferConfig.getConfiguration(), TransferDefaultConfig.class);
        setParamsOfOrder(orderBaoma, ForwardSystemEnum.OP, transferConfig,transferDefaultConfig);
        OpRequest opRequest = BaomaDtoConverter.convertOp(orderBaoma, transferDefaultConfig);
        BaseResponse baseResponse = opApiService.sendOrder(opRequest, orderBaoma.getOrderNo(), orderBaoma.getCode());
        OrderStatusEnum orderStatus = baseResponse.isSuccess() ? OrderStatusEnum.SEND_SUCCESS : OrderStatusEnum.SEND_FAILURE;
        //更新发送状态
        orderBaomaService.updateOrderStatus(orderBaoma.getId(), orderStatus.getValue(), baseResponse.getMessage());
        return baseResponse;
    }

    //设置订单的属性
    private void setParamsOfOrder(OrderBaomaDto order, ForwardSystemEnum systemEnum, TransferConfig transferConfig,TransferDefaultConfig config ) {
        List<OrderBaomaGoods> orderBaomaGoods = orderBaomaGoodsService.selectAllByOrderId(order.getId());
        order.setOrderDetail(orderBaomaGoods);
        if (config != null) {
            if (ForwardSystemEnum.OFC == systemEnum) {
                order.setOrderType(StringUtils.isNotEmpty(config.getOrderType()) ? Long.valueOf(config.getOrderType()) : null);
                order.setOrderSource(StringUtils.isNotEmpty(config.getOrderSource()) ? Long.valueOf(config.getOrderSource()) : null);
                order.setStoreCodeOfc(config.getWarehouseCode());
            } else if (ForwardSystemEnum.OP == systemEnum) {
                order.setConfigMode(transferConfig.getBusinessMode() != null ? transferConfig.getBusinessMode().getValue() : order.getBusiMode());
                order.setConfigCustoms(transferConfig.getCustomsCode() != null ? transferConfig.getCustomsCode().getValue() : order.getCustomsCode());
                order.setCiqbCode(StringUtils.isEmpty(config.getCiqCode()) ? order.getCiqbCode() : config.getCiqCode());
            }
        }

    }

    @Override
    public BaseResponse rePush(List<Long> ids) {
        List<OrderBaoma> orderBaomas = orderBaomaService.selectByIds(ids);
        BusinessAssert.assertNotEmpty(orderBaomas,"所选订单不存在");
        for (OrderBaoma order : orderBaomas) {
            //校验订单状态是否允许重推
            BusinessAssert.assertIsTrue(OrderStatusEnum.SEND_SUCCESS == order.getSendState(), String.format("订单[%s]当前状态不允许重推!", order.getCode()));
            //直接重新发送MQ
            QueueConstants.QueueEnum queueEnum = QueueConstants.QueueEnum.getQueueEnumByName("ocp.baoma.order.push." + order.getSendSystem().getQueue());
            messageSender.send(queueEnum, new BasicMessage(order.getId(), order.getCode()));
        }
        return BaseResponse.responseSuccess("重推成功");
    }
}

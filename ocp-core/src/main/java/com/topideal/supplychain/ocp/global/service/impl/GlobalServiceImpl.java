package com.topideal.supplychain.ocp.global.service.impl;

import com.google.common.collect.Lists;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.mq.MessageSender;
import com.topideal.supplychain.ocp.config.dto.CatchDefaultConfig;
import com.topideal.supplychain.ocp.config.dto.TransferDefaultConfig;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.config.model.TransferConfig;
import com.topideal.supplychain.ocp.config.service.TransferConfigService;
import com.topideal.supplychain.ocp.constants.PunctuateConstant;
import com.topideal.supplychain.ocp.enums.BusiModeEnum;
import com.topideal.supplychain.ocp.enums.ForwardSystemEnum;
import com.topideal.supplychain.ocp.enums.OrderStatusEnum;
import com.topideal.supplychain.ocp.global.converter.GlobalDtoConverter;
import com.topideal.supplychain.ocp.global.dto.GlobalParam;
import com.topideal.supplychain.ocp.global.dto.GlobalResponseDto;
import com.topideal.supplychain.ocp.global.service.GlobalApiService;
import com.topideal.supplychain.ocp.global.service.GlobalService;
import com.topideal.supplychain.ocp.mq.QueueConstants;
import com.topideal.supplychain.ocp.ofc.dto.OfcRequest;
import com.topideal.supplychain.ocp.ofc.service.OfcApiService;
import com.topideal.supplychain.ocp.order.model.OrderGlobal;
import com.topideal.supplychain.ocp.order.model.OrderGlobalItem;
import com.topideal.supplychain.ocp.order.service.OrderGlobalItemService;
import com.topideal.supplychain.ocp.order.service.OrderGlobalService;
import com.topideal.supplychain.util.JacksonUtils;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>标题: </p>
 * <p>模块: ocp-parent</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.vip.service.impl</p>
 * <p>作者: LeoZhang</p>
 * <p>创建日期: 2019/11/27 18:20</p>
 *
 * @version 1.0
 */
@Service
public class GlobalServiceImpl implements GlobalService {

    @Autowired
    private MessageSender messageSender;
    @Autowired
    private TransferConfigService transferConfigService;
    @Autowired
    private OfcApiService ofcApiService;
    @Autowired
    private OrderGlobalService orderGlobalService;
    @Autowired
    private OrderGlobalItemService globalItemService;
    @Autowired
    private GlobalApiService globalApiService;
    @Autowired
    private OrderGlobalItemService orderGlobalItemService;

    /**
     * 订单重推,如果状态不对的订单跳过并记录单号，状态正确的正常下发
     *
     * @param ids
     * @return
     */
    @Override
    @Transactional
    public String rePush(List<Long> ids) {
        StringBuilder msg = new StringBuilder();
        List<OrderGlobal> orderGlobals = orderGlobalService.selectByIds(ids);
        orderGlobals.forEach(orderGlobal -> {
            //下发成功的订单才允许重新推送
            if (!OrderStatusEnum.SEND_SUCCESS.equals(orderGlobal.getStatus())) {
                msg.append(orderGlobal.getCode()).append(PunctuateConstant.COMMA);
                return;
            }
            //直接重新发送MQ,全球仓目前只支持下发OFC
            messageSender.send(QueueConstants.QueueEnum.OCP_GLOBAL_ORDER_PUSH_OFC, new BasicMessage(orderGlobal.getId(), orderGlobal.getCode()));
        });
        return StringUtils.isEmpty(msg) ? msg.toString() : msg.append("订单当前状态不允许重推！").toString();
    }

    /**
     * 全球仓订单抓单
     *
     * @param catchOrderConfig
     * @param param
     */
    @Override
    @Transactional
    public BaseResponse<List<GlobalResponseDto.Content.OrderResult>> getOrder(CatchOrderConfig catchOrderConfig, GlobalParam param) {
        //调用全球仓抓单接口
        BaseResponse<List<GlobalResponseDto.Content.OrderResult>> baseResponse = globalApiService.grabOrder(param);
        List<GlobalResponseDto.Content.OrderResult> orderList = baseResponse.getData();
        //如果抓取订单为空则返回
        if (CollectionUtils.isEmpty(orderList)) {
            return baseResponse;
        }
        //保存订单
        orderList.forEach(orderResult -> {
            //如果请求的订单重要信息为空跳过
            if (orderResult == null || CollectionUtils.isEmpty(orderResult.getSkuList())) {
                return;
            }
            OrderGlobal orderGlobal = new OrderGlobal();
            //保存订单头信息
            BeanUtils.copyProperties(orderResult, orderGlobal);
            //保存收件人信息
            BeanUtils.copyProperties(orderResult.getConsignee(), orderGlobal);
            //保存支付人信息
            BeanUtils.copyProperties(orderResult.getPayerEntity(), orderGlobal);
            //保存支付信息
            BeanUtils.copyProperties(orderResult.getPaymentInfo(), orderGlobal);
            //填充订单信息
            enrichOrderInfo(orderGlobal, catchOrderConfig);
            //保存商品信息
            List<OrderGlobalItem> itemList = Lists.newArrayList();
            orderResult.getSkuList().forEach(orderItem -> {
                OrderGlobalItem item = new OrderGlobalItem();
                BeanUtils.copyProperties(orderItem, item);
                item.setDeclarePrice(item.getDeclarePrice() == null ? null : item.getDeclarePrice().divide(BigDecimal.valueOf(100), 5, BigDecimal.ROUND_HALF_UP));
                item.setDiscountPrice(item.getDiscountPrice() == null ? null : item.getDiscountPrice().divide(BigDecimal.valueOf(100), 5, BigDecimal.ROUND_HALF_UP));
                item.setPrice(item.getPrice() == null ? null : item.getPrice().divide(BigDecimal.valueOf(100), 5, BigDecimal.ROUND_HALF_UP));
                item.setTotalPrice(item.getDeclarePrice() == null || item.getCount() == null ? null : item.getDeclarePrice().multiply(BigDecimal.valueOf(item.getCount())));
                itemList.add(item);
            });
            //保存订单和商品信息,如果订单已存在则不插入
            int exist = orderGlobalService.insertNotExist(orderGlobal);
            if (exist != 1) return;
            globalItemService.batchInsert(orderGlobal.getId(), itemList);
            //目前全球仓只下发OFC
            messageSender.send(QueueConstants.QueueEnum.OCP_GLOBAL_ORDER_PUSH_OFC, new BasicMessage(orderGlobal.getId(), orderGlobal.getCode()));
        });
        return baseResponse;
    }

    /**
     * 填充订单信息
     *
     * @param orderGlobal
     * @param catchOrderConfig
     */
    private void enrichOrderInfo(OrderGlobal orderGlobal, CatchOrderConfig catchOrderConfig) {
        orderGlobal.setSendSystem(ForwardSystemEnum.OFC);//默认OFC
        orderGlobal.setStatus(OrderStatusEnum.INIT);
        //重新计算订单的金额,分换算成元
        orderGlobal.setDeclarePayAmount(orderGlobal.getDeclarePayAmount() != null ? orderGlobal.getDeclarePayAmount().divide(BigDecimal.valueOf(100), 5, BigDecimal.ROUND_HALF_UP) : null);
        orderGlobal.setTradeAmount(orderGlobal.getTradeAmount() != null ? orderGlobal.getTradeAmount().divide(BigDecimal.valueOf(100), 5, BigDecimal.ROUND_HALF_UP) : null);
        orderGlobal.setGoodsTotalAmount(orderGlobal.getGoodsTotalAmount() != null ? orderGlobal.getGoodsTotalAmount().divide(BigDecimal.valueOf(100), 5, BigDecimal.ROUND_HALF_UP) : null);
        orderGlobal.setDiscountAmount(orderGlobal.getDiscountAmount() != null ? orderGlobal.getDiscountAmount().divide(BigDecimal.valueOf(100), 5, BigDecimal.ROUND_HALF_UP) : null);
        orderGlobal.setFreightAmount(orderGlobal.getFreightAmount() != null ? orderGlobal.getFreightAmount().divide(BigDecimal.valueOf(100), 5, BigDecimal.ROUND_HALF_UP) : null);
        orderGlobal.setInsuranceAmount(orderGlobal.getInsuranceAmount() != null ? orderGlobal.getInsuranceAmount().divide(BigDecimal.valueOf(100), 5, BigDecimal.ROUND_HALF_UP) : null);
        orderGlobal.setTaxAmount(orderGlobal.getTaxAmount() != null ? orderGlobal.getTaxAmount().divide(BigDecimal.valueOf(100), 5, BigDecimal.ROUND_HALF_UP) : null);
        orderGlobal.setEbcCode(catchOrderConfig.getMerchantCode());
        orderGlobal.setStoreCode(catchOrderConfig.getStoreCode());
        //解析接单配置
        CatchDefaultConfig defaultConfig = JacksonUtils.readValue(catchOrderConfig.getDefaultArguments(), CatchDefaultConfig.class);
        if (defaultConfig == null) return;
        orderGlobal.setBusiMode(BusiModeEnum.getValueEnum(defaultConfig.getBusiMode()));
        orderGlobal.setCustomsCode(defaultConfig.getCustomsCode());
        //orderGlobal.setCiqCode(defaultConfig.getCiqCode());
        orderGlobal.setCustomsType(defaultConfig.getCustomsType());
    }

    /**
     * 推送订单信息到OFC
     *
     * @param orderGlobal
     */
    @Override
    @Transactional
    public BaseResponse pushOrderToOfc(OrderGlobal orderGlobal) {
        List<OrderGlobalItem> itemList = orderGlobalItemService.selectByOrderId(orderGlobal.getId());
        orderGlobal.setItemList(itemList);
        //解析转单配置
        TransferConfig transferConfig = transferConfigService.findByUnique(StringUtils.isNotEmpty(orderGlobal.getEbcCode()) ? orderGlobal.getEbcCode() : "", orderGlobal.getEbpCode(), StringUtils.isNotEmpty(orderGlobal.getLogisticsCode()) ? orderGlobal.getLogisticsCode() : "", orderGlobal.getCustomsCode(), orderGlobal.getBusiMode() == null ? "" : orderGlobal.getBusiMode().getValue());
        BusinessAssert.assertNotNull(transferConfig, "转单参数未配置");
        //解析转单参数
        TransferDefaultConfig configuration = JacksonUtils.readValue(transferConfig.getConfiguration(), TransferDefaultConfig.class);
        //转换请求报文,同时按照转单参数对字段进行转换
        OfcRequest req = GlobalDtoConverter.convertOfc(orderGlobal, configuration);
        OrderStatusEnum orderStatusEnum = OrderStatusEnum.SEND_FAILURE;
        //下发接口,如果有异常更新状态为下发失败
        BaseResponse response = ofcApiService.sendOrder(req, orderGlobal.getDeclareCode(), orderGlobal.getCode());
        if (response.isSuccess()) {
            orderStatusEnum = OrderStatusEnum.SEND_SUCCESS;
        }
        //更新订单状态,如果是业务异常则更改为下发失败，如果其他异常不更新为下发失败
        OrderGlobal updateEntity = new OrderGlobal();
        updateEntity.setId(orderGlobal.getId());
        updateEntity.setStatus(orderStatusEnum);
        updateEntity.setSendTime(new Date());
        orderGlobalService.update(updateEntity);
        return response;
    }

}

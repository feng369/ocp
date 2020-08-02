package com.topideal.supplychain.ocp.pdd.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.pdd.pop.sdk.http.api.request.PddOrderInformationGetRequest;
import com.pdd.pop.sdk.http.api.request.PddOrderNumberListIncrementGetRequest;
import com.pdd.pop.sdk.http.api.response.PddOrderInformationGetResponse;
import com.pdd.pop.sdk.http.api.response.PddOrderNumberListIncrementGetResponse;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.enumeration.YesOrNoEnum;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.exception.BusinessException;
import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.mq.MessageSender;
import com.topideal.supplychain.ocp.config.dto.CatchDefaultConfig;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.config.model.TransferConfig;
import com.topideal.supplychain.ocp.constants.CodeRuleConstants;
import com.topideal.supplychain.ocp.constants.DictConstant;
import com.topideal.supplychain.ocp.enums.*;
import com.topideal.supplychain.ocp.esd.dto.EsdRequest;
import com.topideal.supplychain.ocp.esd.service.EsdApiService;
import com.topideal.supplychain.ocp.gemini.dto.GeminiResponse;
import com.topideal.supplychain.ocp.gemini.service.GeminiApiService;
import com.topideal.supplychain.ocp.kjb.dto.KjbResponse;
import com.topideal.supplychain.ocp.kjb.service.KjbApiService;
import com.topideal.supplychain.ocp.master.dto.EsdStoreConfig;
import com.topideal.supplychain.ocp.master.dto.StoreDto;
import com.topideal.supplychain.ocp.master.model.GoodsCustomerConfig;
import com.topideal.supplychain.ocp.master.model.Platform;
import com.topideal.supplychain.ocp.master.model.Store;
import com.topideal.supplychain.ocp.master.service.GoodsCustomerService;
import com.topideal.supplychain.ocp.master.service.PlatformService;
import com.topideal.supplychain.ocp.master.service.StoreService;
import com.topideal.supplychain.ocp.mq.QueueConstants;
import com.topideal.supplychain.ocp.ofc.dto.OfcRequest;
import com.topideal.supplychain.ocp.ofc.service.OfcApiService;
import com.topideal.supplychain.ocp.op.dto.OpRequest;
import com.topideal.supplychain.ocp.op.service.OpApiService;
import com.topideal.supplychain.ocp.order.model.*;
import com.topideal.supplychain.ocp.order.service.*;
import com.topideal.supplychain.ocp.pdd.converter.PddDtoConverter;
import com.topideal.supplychain.ocp.pdd.dto.CatchOrderPddDto;
import com.topideal.supplychain.ocp.pdd.dto.CatchOrderPddItemDto;
import com.topideal.supplychain.ocp.pdd.dto.PddReceiveGoods;
import com.topideal.supplychain.ocp.pdd.dto.PddReceiveRequest;
import com.topideal.supplychain.ocp.pdd.model.PddStoreArg;
import com.topideal.supplychain.ocp.pdd.service.PddApiService;
import com.topideal.supplychain.ocp.pdd.service.PddService;
import com.topideal.supplychain.ocp.service.SystemConfigService;
import com.topideal.supplychain.util.AES256Util;
import com.topideal.supplychain.util.BeanCopy;
import com.topideal.supplychain.util.DateUtils;
import com.topideal.supplychain.util.JacksonUtils;
import com.topideal.supplychain.web.system.service.DictService;
import com.topideal.supplychain.web.system.service.impl.CodeGeneratorService;
import java.util.Map;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author klw
 * @date 2019/12/12 13:44
 * @description: 拼多多业务service
 */
@Service
public class PddServiceImpl implements PddService {

    @Autowired
    private OrderTempService orderTempService;
    @Autowired
    private OrderPddService orderPddService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private CodeGeneratorService codeGeneratorService;
    @Autowired
    private OrderPddGoodsService orderPddGoodsService;
    @Autowired
    private OrderPddCardService orderPddCardService;
    @Autowired
    private OrderPddItemService orderPddItemService;
    @Autowired
    private OrderPddWareService orderPddWareService;
    @Autowired
    private MessageSender messageSender;
    @Autowired
    private KjbApiService kjbApiService;
    @Autowired
    private GeminiApiService geminiApiService;
    @Autowired
    private PlatformService platformService;
    @Autowired
    private GoodsCustomerService goodsCustomerService;
    @Autowired
    private OfcApiService ofcApiService;
    @Autowired
    private OpApiService opApiService;
    @Autowired
    private EsdApiService esdApiService;
    @Autowired
    private PddApiService pddApiService;
    @Autowired
    private SystemConfigService systemConfigService;
    @Autowired
    private DictService dictService;

    /**
     * 抓单
     *
     * @param config
     */
    @Override
    public void catchOrder(CatchOrderConfig config, Date executeTime) {
        Store store = storeService.selectById(config.getStoreId());
        BusinessAssert.assertNotNull(store, "未找到店铺信息");
        PddStoreArg storeInfo = JacksonUtils.readValue(store.getArguments(), PddStoreArg.class);
        BusinessAssert.assertNotNull(storeInfo, "店铺未配置抓单信息");
        BusinessAssert.assertNotEmpty(storeInfo.getClientId(), "店铺未配置抓单clientId");
        BusinessAssert.assertNotEmpty(storeInfo.getAccessToken(), "店铺未配置抓单accessToken");
        BusinessAssert.assertNotEmpty(storeInfo.getClientSecret(), "店铺未配置抓单clientSecret");

        //获取拼多多抓取报文的请求
        PddOrderNumberListIncrementGetRequest pddCatchRequest = buildPddCatchRequest(config, executeTime);
        //分页从1开始
        int page = 1;
        List<PddOrderNumberListIncrementGetResponse.OrderSnIncrementGetResponseOrderSnListItem> orderList = Lists.newArrayList();
        do {
            //设置分页
            pddCatchRequest.setPage(page);
            //调接口抓单
            orderList = pddApiService.sendCatchRequest(pddCatchRequest, storeInfo);
            for (PddOrderNumberListIncrementGetResponse.OrderSnIncrementGetResponseOrderSnListItem order : orderList) {
                OrderTemp orderTemp = new OrderTemp(JacksonUtils.toJSon(order));
                //插入临时表数据，并发送处理的mq
                orderTempService.insertAndSendMq(orderTemp, QueueConstants.QueueEnum.PDD_CATCH_ORDER_PROCESS,
                        new BasicMessage(orderTemp.getId(), order.getInnerTransactionId(), config.getId().toString(), "false"));
            }
            page++;
        } while (CollectionUtils.isNotEmpty(orderList));

    }

    /**
     * 获取抓单请求体
     *
     * @param config
     * @param executeTime
     * @return
     */
    private PddOrderNumberListIncrementGetRequest buildPddCatchRequest(CatchOrderConfig config, Date executeTime) {
        PddOrderNumberListIncrementGetRequest request = new PddOrderNumberListIncrementGetRequest();
        request.setIsLuckyFlag(0);
        request.setOrderStatus(1);
        request.setStartUpdatedAt(DateUtils.addMinute(executeTime, -30).getTime() / 1000);
        request.setEndUpdatedAt(executeTime.getTime() / 1000);
        request.setPageSize(config.getPageSize());
        request.setRefundStatus(1);
        return request;
    }

    /**
     * 单票订单抓取
     *
     * @param config
     * @param orderSn
     */
    @Override
    @Transactional
    public void catchSingleOrder(CatchOrderConfig config, String orderSn) throws Exception {
        Store store = storeService.selectById(config.getStoreId());
        BusinessAssert.assertNotNull(store, "未找到店铺信息");
        PddStoreArg storeInfo = JacksonUtils.readValue(store.getArguments(), PddStoreArg.class);
        BusinessAssert.assertNotNull(storeInfo, "店铺未配置抓单信息");
        BusinessAssert.assertNotEmpty(storeInfo.getClientId(), "店铺未配置抓单clientId");
        BusinessAssert.assertNotEmpty(storeInfo.getAccessToken(), "店铺未配置抓单accessToken");
        BusinessAssert.assertNotEmpty(storeInfo.getClientSecret(), "店铺未配置抓单clientSecret");
        //请求报文
        PddOrderInformationGetRequest request = new PddOrderInformationGetRequest();
        request.setOrderSn(orderSn);
        BaseResponse<PddOrderInformationGetResponse.OrderInfoGetResponseOrderInfo> response = pddApiService.sendCatchSingRequest(request, storeInfo, orderSn, orderSn);
        if (!BaseResponse.SUCCESS.equals(response.getFlag())) {
            throw new BusinessException(response.getMessage());
        }
        OrderTemp orderTemp = new OrderTemp(JacksonUtils.toJSon(response.getData()));
        //插入临时表数据，并发送处理的mq
        orderTempService.insertAndSendMq(orderTemp, QueueConstants.QueueEnum.PDD_CATCH_ORDER_PROCESS,
                new BasicMessage(orderTemp.getId(), response.getData().getInnerTransactionId(), config.getId().toString(), "true"));
    }

    /**
     * 抓单订单处理
     *
     * @param orderTemp
     * @param config
     */
    @Override
    @Transactional
    public void processCatchOrder(OrderTemp orderTemp, CatchOrderConfig config, Boolean isSingleCatch) {
        CatchOrderPddDto catcher = JacksonUtils.readValue(orderTemp.getJson(), CatchOrderPddDto.class);

        //非单票抓取订单需要过滤重复单
        if (isFilter(isSingleCatch, catcher)) {
            orderTempService.deleteById(orderTemp.getId());
            return;
        }

        //订单过滤，商品配置在拼多多过滤字典中
        Map<String,String> filterMap = dictService.getByCode(DictConstant.PDD_FILTER_GOODS_CODE);
        for (CatchOrderPddItemDto itemDto : catcher.getItemList()) {
            if (filterMap.containsKey(itemDto.getOuterId())) {
                orderTempService.deleteById(orderTemp.getId());
                return;
            }
        }

        //转换报文为本地订单实体
        OrderPdd order = transferCatchOrder(catcher, config);
        //复制商品信息
        List<OrderPddGoods> goodsList = BeanCopy.copyProperties(catcher.getItemList(), OrderPddGoods.class);
        orderPddService.insert(order);
        orderPddGoodsService.insertBatch(goodsList, order.getId());
        //如果有账号信息就存着（线上应该木有）
        if (CollectionUtils.isNotEmpty(catcher.getCardInfoList())) {
            orderPddCardService.insertBatch(BeanCopy.copyProperties(catcher.getCardInfoList(), OrderPddCard.class), order.getId());
        }
        //如果有子货品信息就存着（线上应该木有）
        if (catcher.getOrderDepotInfo() != null && CollectionUtils.isNotEmpty(catcher.getOrderDepotInfo().getWareSubInfoList())) {
            orderPddWareService.insertBatch(BeanCopy.copyProperties(catcher.getOrderDepotInfo().getWareSubInfoList(), OrderPddWare.class), order.getId());
        }
        //推送跨境宝接口做商品拆分
        messageSender.send(QueueConstants.QueueEnum.PDD_ORDER_TO_KJB, new BasicMessage(order.getId(), order.getCode()));
        orderTempService.deleteById(orderTemp.getId());
    }

    private boolean isFilter(Boolean isSingleCatch, CatchOrderPddDto catcher) {
        //单票抓单的不需要过滤
        if (isSingleCatch) {
            return false;
        }
        //正常定时抓单的根据系统参数来判断是否需要过滤
        List<OrderPdd> existList = orderPddService.selectByOrderId(catcher.getInnerTransactionId());
        if (CollectionUtils.isEmpty(existList)) {
            return false;
        }
        return systemConfigService.getFilterPddDuplicateOrder();
    }

    private OrderPdd transferCatchOrder(CatchOrderPddDto catcher, CatchOrderConfig config) {
        OrderPdd order = new OrderPdd();
        //copy抓单特有字段
        BeanUtils.copyProperties(catcher, order);
        //与接单的字段同名单含义不同，已改名字段
        order.setDeliveryStatus(catcher.getOrderStatus());
        //扩展的仓库信息字段
        if (catcher.getOrderDepotInfo() != null) {
            BeanUtils.copyProperties(catcher.getOrderDepotInfo(), order);
        }
        //扩展的定金信息字段
        if (catcher.getStepOrderInfo() != null) {
            BeanUtils.copyProperties(catcher.getStepOrderInfo(), order);
        }
        //从接单配置中取订单默认值
        setOrderDefaultConfig(config, order);
        order.setElectricCode(config.getMerchantCode());
        order.setCbepcomCode(config.getPlatformCode());
        order.setSid(config.getStoreCode());

        order.setStatus(OrderStatusEnum.INIT);
        order.setType(OrderTypeEnum.CATCH);
        order.setCode(codeGeneratorService.generate(CodeRuleConstants.RULE_PDD));
        //与接单的公用字段塞值
        order.setCustomsType(1);
        order.setActuralPaid(catcher.getPayAmount());
        order.setBuyerIdNumber(AES256Util.encrypt(catcher.getIdCardNum()));
        order.setBuyerIdType("1");
        order.setBuyerName(catcher.getIdCardName());
        order.setBuyerRegNo(catcher.getReceiverPhone());
        order.setBuyerTelephone(AES256Util.encrypt(catcher.getReceiverPhone()));
        order.setCity(catcher.getCity());
        order.setName(catcher.getReceiverName());
        order.setAddress(catcher.getAddress());
        order.setMobilePhone(catcher.getReceiverPhone());
        order.setCountry(catcher.getCountry());
        order.setOrderDate(catcher.getCreatedTime());
        order.setDiscount(catcher.getDiscountAmount());
        order.setDistrict(catcher.getTown());
        order.setPostage(catcher.getPostage());
        order.setFreightFcode("CNY");
        order.setInsuredFee(BigDecimal.ZERO);
        order.setInsurCurr("CNY");
        order.setOrderSn(catcher.getOrderSn());
        order.setOrderId(catcher.getInnerTransactionId());
        order.setOrderStatus("S");
        order.setPackNo(1);
        order.setProvince(catcher.getProvince());
        order.setReceiveNo(catcher.getIdCardNum());
        order.setTaxFcode("CNY");
        order.setTradeMode("1");
        order.setOrderType("1");
        return order;
    }

    /**
     * 接单订单处理
     *
     * @param orderTemp
     */
    @Override
    @Transactional
    public void processReceiveOrder(OrderTemp orderTemp) {
        PddReceiveRequest requestOrder = JacksonUtils.readValue(orderTemp.getJson(), PddReceiveRequest.class);
        OrderPdd order = getReceiveOrder(requestOrder);
        List<OrderPddItem> itemList = getReceiveItemList(requestOrder);
        orderPddService.insert(order);
        orderPddItemService.insertBatch(itemList, order.getId());
        messageSender.send(QueueConstants.QueueEnum.PDD_ORDER_PUSH_ROUTER, new BasicMessage(order.getId(), order.getCode()));
        orderTempService.deleteById(orderTemp.getId());
    }

    private List<OrderPddItem> getReceiveItemList(PddReceiveRequest requestOrder) {
        List<OrderPddItem> itemList = Lists.newArrayList();
        for (PddReceiveGoods goods : requestOrder.getGoodList()) {
            OrderPddItem item = BeanCopy.copyProperties(goods, OrderPddItem.class);
            item.setPrice(getZeroIfNull(goods.getPrice()));
            item.setDecTotal(null);
            item.setGoodPrice(null);
            item.setTotalPrice(item.getPrice().multiply(BigDecimal.valueOf(item.getAmount())));
            item.setGrossWeight(getZeroIfNull(goods.getGrossWeight()));
            item.setNetWeight(getZeroIfNull(goods.getNetWeight()));
            itemList.add(item);
        }
        return itemList;
    }

    private OrderPdd getReceiveOrder(PddReceiveRequest requestOrder) {
        OrderPdd order = BeanCopy.copyProperties(requestOrder, OrderPdd.class);
        //将收件人信息copy出来
        BeanUtils.copyProperties(requestOrder.getRecipient(), order);
        order.setStatus(OrderStatusEnum.INIT);
        order.setType(OrderTypeEnum.RECEIVE);
        order.setCode(codeGeneratorService.generate(CodeRuleConstants.RULE_PDD));
        order.setLogistCode(requestOrder.getTpl());

        //加密订购人信息
        order.setBuyerIdNumber(AES256Util.encrypt(order.getBuyerIdNumber()));
        order.setBuyerTelephone(AES256Util.encrypt(order.getBuyerTelephone()));
        //空金额置0
        order.setFreightFcy(getZeroIfNull(order.getFreightFcy()));
        order.setInsuredFee(getZeroIfNull(order.getInsuredFee()));
        order.setTaxFcy(getZeroIfNull(order.getTaxFcy()));
        order.setOtherRate(getZeroIfNull(order.getOtherRate()));
        order.setOtherPayment(getZeroIfNull(order.getOtherPayment()));
        order.setDiscount(getZeroIfNull(order.getDiscount()));
        order.setTotalValue(getZeroIfNull(order.getTotalValue()));
        order.setTotalFavourable(getZeroIfNull(order.getTotalFavourable()));
        //邮费的计算
        order.setPostage(order.getFreightFcy().add(order.getOtherRate()));
        return order;
    }

    private BigDecimal getZeroIfNull(BigDecimal target) {
        if (target == null) {
            return BigDecimal.ZERO;
        }
        return target;
    }

    private void setOrderDefaultConfig(CatchOrderConfig catchOrderConfig, OrderPdd order) {
        if (StringUtils.isEmpty(catchOrderConfig.getDefaultArguments())) {
            return;
        }
        CatchDefaultConfig args = JacksonUtils.readValue(catchOrderConfig.getDefaultArguments(), CatchDefaultConfig.class);
        if (StringUtils.isNotEmpty(args.getBusiMode())) order.setBusiMode(args.getBusiMode());
        if (StringUtils.isNotEmpty(args.getCiqCode())) order.setCiqbCode(args.getCiqCode());
        if (StringUtils.isNotEmpty(args.getCustomsCode())) order.setCustomsCode(args.getCustomsCode());

        if (StringUtils.isNotEmpty(args.getLogisticsCode())) order.setLogistCode(args.getLogisticsCode());
    }

    /**
     * 推送订单信息至KJB做商品拆分
     *
     * @param order
     * @return
     */
    @Override
    @Transactional
    public BaseResponse pushKJB(OrderPdd order) {
        List<OrderPddGoods> goodsList = orderPddGoodsService.selectByOrderId(order.getId());
        BaseResponse<KjbResponse> response = kjbApiService.sendOrder(PddDtoConverter.buildKjbRequest(order, goodsList),
                order.getOrderId(), order.getCode());
        if (!BaseResponse.SUCCESS.equals(response.getFlag())) {
            orderPddService.updateOrderStatus(order.getId(), OrderStatusEnum.KJB_FAILURE.getValue(), response.getData().getNotes());
            return response;
        }
        List<OrderPddItem> itemList = buildItemList(response.getData(), order, goodsList);
        orderPddItemService.insertBatch(itemList, order.getId());
        orderPddService.updateOrderStatus(order.getId(), OrderStatusEnum.KJB_SUCCESS.getValue(), null);
        messageSender.send(QueueConstants.QueueEnum.PDD_ORDER_TO_GEMINI, new BasicMessage(order.getId(), order.getCode()));
        return response;
    }

    /**
     * 将kjb商品拆分回执报文转换成拼多多商品明细
     * 1.回执状态为不处理，则说明商品不需要拆分
     * 2.回执为处理成功，则根据回执明细去生成订单明细
     *
     * @param response
     * @param order
     * @return
     */
    private List<OrderPddItem> buildItemList(KjbResponse response, OrderPdd order, List<OrderPddGoods> goodsList) {
        List<OrderPddItem> itemlist = Lists.newArrayList();
        if (KjbSendStausEnum.NO_DEAL.equals(response.getStatus())) {
            for (OrderPddGoods goods : goodsList) {
                OrderPddItem item = new OrderPddItem();
                item.setGoodId(goods.getOuterId());
                item.setCopGName(goods.getGoodsName());
                item.setAmount(goods.getGoodsCount().intValue());
                item.setGoodPrice(goods.getGoodsPrice());
                item.setSpec(goods.getGoodsSpec());
                item.setDecTotal(item.getGoodPrice().multiply(BigDecimal.valueOf(item.getAmount())));
                itemlist.add(item);
            }
        } else {
            for (KjbResponse.KjbResponseGoods goods : response.getGood_list()) {
                OrderPddItem item = new OrderPddItem();
                item.setOrderId(order.getId());
                item.setGoodId(goods.getGoods_id());
                item.setCopGName(goods.getGoods_name());
                item.setAmount(Integer.valueOf(goods.getGoods_num()));
                item.setGoodPrice(goods.getGoods_price());
                item.setDecTotal(item.getGoodPrice().multiply(BigDecimal.valueOf(item.getAmount())));
                itemlist.add(item);
            }
        }
        return itemlist;
    }

    /**
     * 推送订单信息至GEMINI做税价分离
     *
     * @param order
     * @return
     */
    @Override
    @Transactional
    public BaseResponse pushGemini(OrderPdd order) {
        List<OrderPddItem> itemList = orderPddItemService.selectByOrderId(order.getId());

        //调用税价分离的接口
        BaseResponse<GeminiResponse> response = geminiApiService.sendOrder(PddDtoConverter.buildGeminiRequest(order, itemList),
                order.getOrderId(), order.getCode());
        //如果不成功，更新状态未税价分离失败
        if (!BaseResponse.SUCCESS.equals(response.getFlag())) {
            orderPddService.updateOrderStatus(order.getId(), OrderStatusEnum.GEMINI_FAILURE.getValue(), response.getData().getFailMessage());
            return response;
        }
        GeminiResponse geminiResponse = response.getData();
        //更新订单税价
        orderPddService.updateTax(order.getId(), new BigDecimal(geminiResponse.getMessage().getGoodsValue()),
                new BigDecimal(geminiResponse.getMessage().getTaxTotal()));
        //更新明细税价
        for (GeminiResponse.Message.Goods goods : geminiResponse.getMessage().getGoods()) {
            orderPddItemService.updateTax(Long.valueOf(goods.getId()), new BigDecimal(goods.getTotalPrice()), new BigDecimal(goods.getPrice()));
        }
        //更新订单状态为税价分离成功
        orderPddService.updateOrderStatus(order.getId(), OrderStatusEnum.GEMINI_SUCCESS.getValue(), null);
        messageSender.send(QueueConstants.QueueEnum.PDD_ORDER_PUSH_ROUTER, new BasicMessage(order.getId(), order.getCode()));
        return response;
    }

    /**
     * 下发系统路由分发
     *
     * @param order
     * @param transferConfig
     */
    @Override
    @Transactional
    public void router(OrderPdd order, TransferConfig transferConfig) {

        QueueConstants.QueueEnum sendQueue;
        //分发
        switch (transferConfig.getForwardSystem()) {
            case OFC:
                sendQueue = QueueConstants.QueueEnum.PDD_ORDER_TO_OFC;
                break;
            case OP:
                sendQueue = QueueConstants.QueueEnum.PDD_ORDER_TO_OP;
                break;
            case ESD:
                if (OrderTypeEnum.CATCH.equals(order.getType())) throw new BusinessException("抓取订单没有下发esd业务");
                sendQueue = QueueConstants.QueueEnum.PDD_ORDER_TO_ESD;
                break;
            default:
                throw new BusinessException("下发系统不存在");
        }
        //更新转发系统
        OrderPdd updateOrder = new OrderPdd();
        updateOrder.setId(order.getId());
        updateOrder.setSendSystem(transferConfig.getForwardSystem().getValue());
        orderPddService.update(updateOrder);
        messageSender.send(sendQueue, new BasicMessage(order.getId(), order.getCode(), transferConfig.getId().toString(), ""));
    }

    /**
     * 订单下发ofc
     *
     * @param order
     * @param transferConfig
     * @return
     */
    @Override
    @Transactional
    public BaseResponse pushOfc(OrderPdd order, TransferConfig transferConfig) {

        List<OrderPddItem> itemList = orderPddItemService.selectByOrderId(order.getId());
        //抓单下发需要根据转单配置解析一单多业主
        if (OrderTypeEnum.CATCH.equals(order.getType())) {
            setOwnerFlag(order, transferConfig, itemList);
        }
        OfcRequest ofcRequest = PddDtoConverter.buildOfcRequest(order, itemList, transferConfig);
        BaseResponse response = ofcApiService.sendOrder(ofcRequest, order.getOrderId(), order.getCode());
        if (BaseResponse.SUCCESS.equals(response.getFlag())) {
            orderPddService.updateOrderStatus(order.getId(), OrderStatusEnum.SEND_SUCCESS.getValue(), null);
        } else {
            orderPddService.updateOrderStatus(order.getId(), OrderStatusEnum.SEND_FAILURE.getValue(), response.getMessage());
        }
        return response;
    }

    private void setOwnerFlag(OrderPdd order, TransferConfig transferConfig, List<OrderPddItem> itemList) {
        if (YesOrNoEnum.YES.equals(transferConfig.getIsMultiple())) {
            Set<String> merchantSet = Sets.newHashSet();
            GoodsCustomerConfig condition = new GoodsCustomerConfig();
            for (OrderPddItem item : itemList) {
                condition.setGoodsCode(item.getGoodId());
                condition.setCustomerCode(CustomsCodeEnum.getValueEnum(order.getCustomsCode()));
                condition.setBusiMode(BusiModeEnum.getValueEnum(order.getBusiMode()));
                List<GoodsCustomerConfig> resultList = goodsCustomerService.selectByFilter(condition);
                if (CollectionUtils.isNotEmpty(resultList)) {
                    item.setEbcCode(resultList.get(0).getEnterpriseCode());
                    merchantSet.add(item.getEbcCode());
                } else {
                    merchantSet.add(null);
                }
            }
            if (merchantSet.size() > 1) {
                order.setOwnerFlag(YesOrNoEnum.YES.getValue());
            }
        } else {
            order.setOwnerFlag(YesOrNoEnum.NO.getValue());
        }
    }

    /**
     * 下发op
     *
     * @param order
     * @param transferConfig
     * @return
     */
    @Override
    @Transactional
    public BaseResponse pushOp(OrderPdd order, TransferConfig transferConfig) {
        List<OrderPddItem> itemList = orderPddItemService.selectByOrderId(order.getId());

        OpRequest opRequest = PddDtoConverter.buildOpRequest(order, itemList, transferConfig);
        BaseResponse response = opApiService.sendOrder(opRequest, order.getOrderId(), order.getCode());
        if (BaseResponse.SUCCESS.equals(response.getFlag())) {
            orderPddService.updateOrderStatus(order.getId(), OrderStatusEnum.SEND_SUCCESS.getValue(), null);
        } else {
            orderPddService.updateOrderStatus(order.getId(), OrderStatusEnum.SEND_FAILURE.getValue(), response.getMessage());
        }
        return response;
    }

    /**
     * 下发esd
     *
     * @param order
     * @param transferConfig
     * @return
     */
    @Override
    @Transactional
    public BaseResponse pushEsd(OrderPdd order, TransferConfig transferConfig) {
        List<OrderPddItem> itemList = orderPddItemService.selectByOrderId(order.getId());

        //查询对应的店铺信息
        //List<Platform> platforms = platformService.findByVirtualCode(PlatformEnum.PDD.getCode());
        //StoreDto condition = new StoreDto();
        //condition.setCode(order.getSid());
        //condition.setPlatformCodeList(platforms.stream().map(Platform::getCode).collect(Collectors.toList()));
        //condition.setMerchantCode(order.getElectricCode());
        Store store = storeService.selectEnableByCode(order.getSid());
        /*if (CollectionUtils.isEmpty(storeList)) {
            throw new BusinessException("未找到店铺信息");
        }*/
        BusinessAssert.assertNotNull(store,"未找到店铺信息");

        EsdStoreConfig esdStoreConfig = JacksonUtils.readValue(store.getEsdArguments(),EsdStoreConfig.class);
        BusinessAssert.assertNotNull(esdStoreConfig,"店铺未配置ESD参数信息");
        EsdRequest esdRequest = PddDtoConverter.buildEsdRequest(order, itemList, transferConfig, esdStoreConfig.getOverseaHouseCode());
        BaseResponse response = esdApiService.sendOrder(esdRequest, order.getOrderId(), order.getCode(), esdStoreConfig.getAppId(),esdStoreConfig.getAppKey());
        if (BaseResponse.SUCCESS.equals(response.getFlag())) {
            orderPddService.updateOrderStatus(order.getId(), OrderStatusEnum.SEND_SUCCESS.getValue(), null);
        } else {
            orderPddService.updateOrderStatus(order.getId(), OrderStatusEnum.SEND_FAILURE.getValue(), response.getMessage());
        }
        return response;
    }
}

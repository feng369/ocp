package com.topideal.supplychain.ocp.beibei.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.exception.BusinessException;
import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.mq.MessageSender;
import com.topideal.supplychain.ocp.beibei.converter.BeibeiDtoConverter;
import com.topideal.supplychain.ocp.beibei.dto.BeibeiDetailResponseDto;
import com.topideal.supplychain.ocp.beibei.dto.BeibeiOrderDetailDto;
import com.topideal.supplychain.ocp.beibei.dto.BeibeiOrderDto;
import com.topideal.supplychain.ocp.beibei.dto.BeibeiOrderItemDto;
import com.topideal.supplychain.ocp.beibei.dto.BeibeiResponseDto;
import com.topideal.supplychain.ocp.beibei.service.BeibeiApiService;
import com.topideal.supplychain.ocp.beibei.service.BeibeiService;
import com.topideal.supplychain.ocp.config.dto.TransferDefaultConfig;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.config.model.TransferConfig;
import com.topideal.supplychain.ocp.config.service.TransferConfigService;
import com.topideal.supplychain.ocp.constants.DictConstant;
import com.topideal.supplychain.ocp.enums.BusiModeEnum;
import com.topideal.supplychain.ocp.enums.ForwardSystemEnum;
import com.topideal.supplychain.ocp.enums.LogisticsEnum;
import com.topideal.supplychain.ocp.enums.OrderStatusEnum;
import com.topideal.supplychain.ocp.master.model.Store;
import com.topideal.supplychain.ocp.master.service.StoreService;
import com.topideal.supplychain.ocp.mq.QueueConstants;
import com.topideal.supplychain.ocp.ofc.dto.OfcRequest;
import com.topideal.supplychain.ocp.ofc.service.OfcApiService;
import com.topideal.supplychain.ocp.order.dto.OrderBeibeiDto;
import com.topideal.supplychain.ocp.order.model.OrderBeibei;
import com.topideal.supplychain.ocp.order.model.OrderBeibeiItem;
import com.topideal.supplychain.ocp.order.model.OrderTemp;
import com.topideal.supplychain.ocp.order.service.OrderBeibeiItemService;
import com.topideal.supplychain.ocp.order.service.OrderBeibeiService;
import com.topideal.supplychain.ocp.order.service.OrderTempService;
import com.topideal.supplychain.util.DateUtils;
import com.topideal.supplychain.util.JacksonUtils;
import com.topideal.supplychain.util.MD5Utils;
import com.topideal.supplychain.web.system.service.DictService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author wanzhaozhang
 * @date 2020/3/19
 * @description
 **/
@Service
public class BeibeiServiceImpl implements BeibeiService {

    @Autowired
    private StoreService storeService;
    @Autowired
    protected MessageSender messageSender;
    @Autowired
    private OrderTempService orderTempService;
    @Autowired
    private TransferConfigService transferConfigService;
    @Autowired
    private OfcApiService ofcApiService;
    @Autowired
    private OrderBeibeiService orderBeibeiService;
    @Autowired
    private OrderBeibeiItemService orderBeibeiItemService;
    @Autowired
    private BeibeiApiService beibeiApiService;
    @Autowired
    private DictService dictService;


    /**
     * 抓单
     *
     * @param catchOrderConfig
     */
    @Override
    public void getOrder(CatchOrderConfig catchOrderConfig) {
        Store store = storeService.selectById(catchOrderConfig.getStoreId());
        if (store == null || StringUtils.isEmpty(store.getArguments())) {
            throw new BusinessException("店铺信息有误");
        }
        Map<String, String> storeArgs = JacksonUtils.readValue(store.getArguments(), Map.class);

        Date nowTime = DateUtils.getNowTime();
        TreeMap<String, String> treeMap = generateParams(storeArgs, "beibei.outer.trade.order.get");
        treeMap.put("status", "1");
        treeMap.put("time_range", "modified_time");
        //付款后有30分钟延迟
        treeMap.put("start_time", String.valueOf(DateUtils.addMinutes(nowTime, -60).getTime() / 1000));
        treeMap.put("end_time", String.valueOf(nowTime.getTime() / 1000));
        //每次查询条数
        Integer pageSize = catchOrderConfig.getPageSize();
        int start = 1;
        do {
            // 开始页码
            treeMap.put("page_no", String.valueOf(start));
            //每页条数
            treeMap.put("page_size", String.valueOf(pageSize));
            treeMap.put("sign", getSign(treeMap, storeArgs.get("appKey")));
            //抓单
            BeibeiResponseDto responseDto = beibeiApiService.getOrder(treeMap);
            List<BeibeiOrderDto> dataDtos = responseDto.getData();
            double ceil = Math.ceil((double) responseDto.getCount() / pageSize);
            int intValue = new Double(ceil).intValue();
            //保存临时表并发送mq
            dataDtos.forEach(dataDto -> {
                OrderTemp orderTemp = new OrderTemp(JacksonUtils.toJSon(dataDto));
                BasicMessage basicMessage = new BasicMessage(orderTemp.getId(), dataDto.getOid(), store.getId().toString(), "");
                orderTempService.insertAndSendMq(orderTemp, QueueConstants.QueueEnum.OCP_BEIBEI_SAVE_ORDER, basicMessage);
            });
            if (intValue <= start) {
                break;
            }
            start = intValue;
        } while (true);
    }

    @Transactional
    public void processOrder(OrderTemp orderTemp, Store store) {
        saveOrder(orderTemp,store);
        //删除临时表的记录
        orderTempService.deleteById(orderTemp.getId());
    }

    private void saveOrder(OrderTemp orderTemp, Store store) {

        BeibeiOrderDto beibeiOrderDto = JacksonUtils.readValue(orderTemp.getJson(), BeibeiOrderDto.class);
        //重复订单剔除
        List<OrderBeibei> beibei = orderBeibeiService.selectByOId(beibeiOrderDto.getOid());
        if (CollectionUtils.isNotEmpty(beibei)) {
            return;
        }
        Map<String, String> storeArgs = JacksonUtils.readValue(store.getArguments(), Map.class);

        TreeMap<String, String> treeMap = generateParams(storeArgs, "beibei.outer.trade.order.detail.get");
        // 应用参数
        treeMap.put("oid", beibeiOrderDto.getOid());
        // 签名
        treeMap.put("sign", getSign(treeMap, storeArgs.get("appKey")));
        BeibeiDetailResponseDto orderDetailResponse = beibeiApiService.getOrderDetail(treeMap);
        BeibeiOrderDetailDto orderDetailDto = orderDetailResponse.getData();
        String cityCode = orderDetailDto.getItem().get(0).getShip_city_code();
        String customCode = dictService.getByCode(DictConstant.BEIBEI_CUSTOM_CODE).get(cityCode);
        //过滤掉没有关区映射关系的订单
        if (StringUtils.isEmpty(customCode)) {
            return;
        }
        OrderBeibeiDto orderBeibei = new OrderBeibeiDto();
        orderBeibei.setOid(orderDetailDto.getOid());
        orderBeibei.setType(orderDetailDto.getType());
        orderBeibei.setWarehousingType(orderDetailDto.getWarehousing_type());
        orderBeibei.setUser(orderDetailDto.getUser());
        orderBeibei.setNick(orderDetailDto.getNick());
        orderBeibei.setProvince(orderDetailDto.getProvince());
        orderBeibei.setCity(orderDetailDto.getCity());
        orderBeibei.setCounty(orderDetailDto.getCounty());
        orderBeibei.setAddress(orderDetailDto.getAddress());
        orderBeibei.setEventId(orderDetailDto.getEvent_id());
        orderBeibei.setItemNum(orderDetailDto.getItem_num());
        orderBeibei.setStatus(orderDetailDto.getStatus());
        orderBeibei.setTotalFee(StringUtils.isNotEmpty(orderDetailDto.getTotal_fee()) ? new BigDecimal(orderDetailDto.getTotal_fee()) : null);
        orderBeibei.setShippingFee(new BigDecimal(orderDetailDto.getShipping_fee()));
        orderBeibei.setPayment(new BigDecimal(orderDetailDto.getPayment()));
        orderBeibei.setInvoiceType(orderDetailDto.getInvoice_type());
        orderBeibei.setInvoiceName(orderDetailDto.getInvoice_name());
        orderBeibei.setDiscount(new BigDecimal(orderDetailDto.getDiscount()));
        orderBeibei.setMemberCard(orderDetailDto.getMember_card());
        orderBeibei.setMemberCardImgs(JacksonUtils.toJSon(orderDetailDto.getMember_card_imgs()));
        orderBeibei.setRemark(orderDetailDto.getRemark());
        orderBeibei.setSellerRemark(orderDetailDto.getSeller_remark());
        orderBeibei.setReceiverName(orderDetailDto.getReceiver_name());
        orderBeibei.setReceiverPhone(orderDetailDto.getReceiver_phone());
        orderBeibei.setReceiverAddress(orderDetailDto.getReceiver_address());
        orderBeibei.setCompany(orderDetailDto.getCompany());
        orderBeibei.setOutSid(orderDetailDto.getOut_sid());
        orderBeibei.setChannelName(orderDetailDto.getChannel_name());
        orderBeibei.setChannelInfo(orderDetailDto.getChannel_info());
        orderBeibei.setOrderTime(DateUtils.formatStringToDate(orderDetailDto.getCreate_time(), DateUtils.Y_M_D_HMS));
        orderBeibei.setPayTime(DateUtils.formatStringToDate(orderDetailDto.getPay_time(), DateUtils.Y_M_D_HMS));
        orderBeibei.setShipTime(DateUtils.formatStringToDate(orderDetailDto.getShip_time(), DateUtils.Y_M_D_HMS));
        orderBeibei.setEndTime(DateUtils.formatStringToDate(orderDetailDto.getEnd_time(), DateUtils.Y_M_D_HMS));
        orderBeibei.setModifiedTime(DateUtils.formatStringToDate(orderDetailDto.getModified_time(), DateUtils.Y_M_D_HMS));
        orderBeibei.setTaxFee(StringUtils.isNotEmpty(orderDetailDto.getTax_fee()) ? new BigDecimal(orderDetailDto.getTax_fee()) : BigDecimal.ZERO);
        orderBeibei.setTariffFee(StringUtils.isNotEmpty(orderDetailDto.getTariff_fee()) ? new BigDecimal(orderDetailDto.getTariff_fee()) : null);
        orderBeibei.setAddedvalueFee(StringUtils.isNotEmpty(orderDetailDto.getAddedvalue_fee()) ? new BigDecimal(orderDetailDto.getAddedvalue_fee()) : null);
        orderBeibei.setConsumpFee(StringUtils.isNotEmpty(orderDetailDto.getConsump_fee()) ? new BigDecimal(orderDetailDto.getConsump_fee()) : null);
        orderBeibei.setUserName(orderDetailDto.getUser_name());
        orderBeibei.setUserMemberCard(orderDetailDto.getUser_member_card());
        //转单默认值
        orderBeibei.setBusiMode(BusiModeEnum.BBC);
        orderBeibei.setCustomsCode(customCode);
        //保存订单信息,转发系统为ofc
        orderBeibei.setSendSystem(ForwardSystemEnum.OFC);
        orderBeibei.setSendStatus(OrderStatusEnum.INIT);
        orderBeibei.setStoreCode(store.getCode());
        orderBeibei.setEbcCode(store.getMerchantCode());
        orderBeibei.setEbpCode(store.getPlatformCode());
        orderBeibeiService.insert(orderBeibei);

        Map<String, BeibeiOrderItemDto> items = Maps.newHashMap();
        for (BeibeiOrderItemDto itemDto : orderDetailDto.getItem()) {
            items.put(itemDto.getSku_id(), itemDto);
        }
        //保存商品信息
        List<BeibeiOrderItemDto> orderDtoItemList = orderDetailDto.getItem();
        for (int i = 0; i < orderDtoItemList.size(); i++) {
            BeibeiOrderItemDto beibeiOrderItemDto = orderDtoItemList.get(i);
            BeibeiOrderItemDto newItem = items.get(beibeiOrderItemDto.getSku_id());
            OrderBeibeiItem item = new OrderBeibeiItem();
            item.setOrderId(orderBeibei.getId());
            item.setGnum(i + 1);
            item.setSkuId(beibeiOrderItemDto.getSku_id());
            item.setIid(beibeiOrderItemDto.getIid());
            item.setUrl(beibeiOrderItemDto.getUrl());
            item.setOuterId(beibeiOrderItemDto.getOuter_id());
            item.setGoodsNum(beibeiOrderItemDto.getGoods_num());
            item.setTitle(beibeiOrderItemDto.getTitle());
            item.setPrice(new BigDecimal(beibeiOrderItemDto.getPrice()));
            item.setDeclareAmount(new BigDecimal(newItem.getDeclare_amount()));
            item.setOriginPrice(new BigDecimal(beibeiOrderItemDto.getOrigin_price()));
            item.setNum(Integer.valueOf(newItem.getNum()));
            item.setRefundStatus(beibeiOrderItemDto.getRefund_status());
            item.setSubtotal(StringUtils.isNotEmpty(beibeiOrderItemDto.getSubtotal()) ? new BigDecimal(beibeiOrderItemDto.getSubtotal()) : null);
            item.setTotalFee(StringUtils.isNotEmpty(beibeiOrderItemDto.getTotal_fee()) ? new BigDecimal(beibeiOrderItemDto.getTotal_fee()) : null);
            item.setSkuProperties(beibeiOrderItemDto.getSku_properties());
            item.setShipCityProperty(beibeiOrderItemDto.getShip_city_property());
            item.setImg(beibeiOrderItemDto.getImg());
            item.setBrand(beibeiOrderItemDto.getBrand());
            orderBeibeiItemService.insert(item);
        }
        //发送转单mq
        messageSender.send(QueueConstants.QueueEnum.OCP_BEIBEI_ORDER_TO_OFC, new BasicMessage(orderBeibei.getId(), orderBeibei.getCode()));

    }

    @Override
    @Transactional
    public BaseResponse pushOfc(OrderBeibeiDto orderBeibei) {
        TransferConfig transferConfig = transferConfigService.findByUnique(orderBeibei.getEbcCode(), orderBeibei.getEbpCode(), LogisticsEnum.VL.getCode(), orderBeibei.getCustomsCode(), orderBeibei.getBusiMode().getValue());
        if (transferConfig == null) {
            orderBeibeiService.updateOrderStatus(orderBeibei.getId(), OrderStatusEnum.SEND_FAILURE.getValue(), "订单未找到对应转发配置!");
            return BaseResponse.responseFailure("订单未找到对应转发配置!");
        }
        TransferDefaultConfig transferDefaultConfig = JacksonUtils.readValue(transferConfig.getConfiguration(), TransferDefaultConfig.class);
        List<OrderBeibeiItem> orderBeibeiItems = orderBeibeiItemService.selectAllByOrderId(orderBeibei.getId());
        orderBeibei.setItem(orderBeibeiItems);
        //转为下发ofc实体
        OfcRequest ofcRequest = BeibeiDtoConverter.convertOfc(orderBeibei, transferDefaultConfig);
        BaseResponse response = ofcApiService.sendOrder(ofcRequest, orderBeibei.getOid(), orderBeibei.getCode());
        OrderStatusEnum orderStatus = response.isSuccess() ? OrderStatusEnum.SEND_SUCCESS : OrderStatusEnum.SEND_FAILURE;
        //更新发送状态
        orderBeibeiService.updateOrderStatus(orderBeibei.getId(), orderStatus.getValue(), response.getMessage());
        return response;
    }


    @Override
    public BaseResponse rePush(List<Long> ids) {
        List<OrderBeibei> orderBeibeis = orderBeibeiService.selectByIds(ids);
        BusinessAssert.assertNotEmpty(orderBeibeis, "所选订单不存在");
        for (OrderBeibei order : orderBeibeis) {
            //校验订单状态是否允许重推
            BusinessAssert.assertIsTrue(OrderStatusEnum.SEND_SUCCESS == order.getSendStatus(), String.format("订单[%s]当前状态不允许重推!", order.getCode()));
            //直接重新发送MQ
            QueueConstants.QueueEnum queueEnum = QueueConstants.QueueEnum.getQueueEnumByName("ocp.beibei.order.send." + order.getSendSystem().getQueue());
            messageSender.send(queueEnum, new BasicMessage(order.getId(), order.getCode()));
        }
        return BaseResponse.responseSuccess("重推成功");
    }

    /**
     * 根据orderId抓取订单
     *
     * @param orderId 订单id，多个用逗号[,]隔开
     * @param storeId 店铺id
     * @return
     */
    @Override
    @Transactional
    public BaseResponse grabOnlyOrder(String orderId, Long storeId) {
        Store store = storeService.selectById(storeId);
        Map<String, String> storeArgs = JacksonUtils.readValue(store.getArguments(), Map.class);
        //贝贝平台对接参数
        TreeMap<String, String> treeMap = generateParams(storeArgs, "beibei.outer.trade.order.batch.get");
        // 应用参数
        treeMap.put("oids", orderId);
        // 签名
        treeMap.put("sign", getSign(treeMap, storeArgs.get("appKey")));
        //根绝订单id抓取订单
        BeibeiResponseDto responseDto = beibeiApiService.getOrder(treeMap);
        List<BeibeiOrderDto> dataDtos = responseDto.getData();
        if (CollectionUtils.isEmpty(dataDtos)) {
            return BaseResponse.responseFailure("抓单失败");
        }
        List list = Lists.newArrayList(Arrays.asList(orderId.split(",")));
        List<String> orderIdList = Lists.newArrayList();
        dataDtos.forEach(dataDto -> {
            orderIdList.add(dataDto.getOid());
            OrderTemp orderTemp = new OrderTemp(JacksonUtils.toJSon(dataDto));
            BasicMessage basicMessage = new BasicMessage(orderTemp.getId(), dataDto.getOid(), store.getId().toString(), "");
            orderTempService.insertAndSendMq(orderTemp, QueueConstants.QueueEnum.OCP_BEIBEI_SAVE_ORDER, basicMessage);
        });
        list.removeAll(orderIdList);
        if (!CollectionUtils.isEmpty(list)) {
            return BaseResponse.responseFailure(String.format("订单%s抓单失败", JacksonUtils.toJSon(list)));
        }
        return BaseResponse.responseSuccess("抓单成功");
    }

    /**
     * 贝贝订单共同参数构造
     *
     * @param storeArgs  店铺信息
     * @param method 方法
     * @return
     */
    private TreeMap<String, String> generateParams(Map<String, String> storeArgs, String method) {
        TreeMap<String, String> params = new TreeMap();
        BusinessAssert.assertNotNull(storeArgs, "店铺参数不存在");
        BusinessAssert.assertNotEmpty(storeArgs.get("appId"), "店铺[appId]为空");
        BusinessAssert.assertNotEmpty(storeArgs.get("appKey"), "店铺[appKey]为空");
        //String accessToken = JacksonUtils.readValue(store.getArguments()).get("accessToken").asText();
        BusinessAssert.assertNotEmpty(storeArgs.get("accessToken"), "店铺参数[accessToken]为空");
        params.put("app_id", storeArgs.get("appId"));
        params.put("method", method);
        params.put("session", storeArgs.get("accessToken"));
        params.put("format", "json");
        params.put("timestamp", String.valueOf(System.currentTimeMillis()).substring(0, 10));
        return params;
    }

    //加签算法
    public String getSign(TreeMap<String, String> params, String secret) {
        String sign = secret;
        for (String key : params.keySet()) {
            sign += key + params.get(key);
        }
        sign += secret;
        return MD5Utils.md5Hex(sign);
    }

}

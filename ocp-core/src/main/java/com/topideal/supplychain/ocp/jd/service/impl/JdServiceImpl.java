package com.topideal.supplychain.ocp.jd.service.impl;

import com.google.common.collect.Lists;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.exception.BusinessException;
import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.mq.MessageSender;
import com.topideal.supplychain.ocp.baoma.service.BaomaApiService;
import com.topideal.supplychain.ocp.config.dto.TransferDefaultConfig;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.config.model.TransferConfig;
import com.topideal.supplychain.ocp.config.service.CatchOrderConfigService;
import com.topideal.supplychain.ocp.config.service.TransferConfigService;
import com.topideal.supplychain.ocp.enums.BuyeridTypeEnum;
import com.topideal.supplychain.ocp.enums.CustomModelEnum;
import com.topideal.supplychain.ocp.enums.CustomsTypeEnum;
import com.topideal.supplychain.ocp.enums.GrabIdEnum;
import com.topideal.supplychain.ocp.enums.JdOrderEnum;
import com.topideal.supplychain.ocp.enums.OrderStatusEnum;
import com.topideal.supplychain.ocp.jd.converter.JdDtoConverter;
import com.topideal.supplychain.ocp.jd.dto.DlzWaybillInfo;
import com.topideal.supplychain.ocp.jd.dto.GuangZhouYunXiaoItemEntities;
import com.topideal.supplychain.ocp.jd.dto.GuangZhouYunXiaoOrderEntity;
import com.topideal.supplychain.ocp.jd.dto.JdDlzOrderDto;
import com.topideal.supplychain.ocp.jd.dto.JdOrderDto;
import com.topideal.supplychain.ocp.jd.dto.JdOrderItemDto;
import com.topideal.supplychain.ocp.jd.dto.JdRequest;
import com.topideal.supplychain.ocp.jd.dto.QingDanInfo;
import com.topideal.supplychain.ocp.jd.dto.WaybillInfo;
import com.topideal.supplychain.ocp.jd.service.JdApiService;
import com.topideal.supplychain.ocp.jd.service.JdService;
import com.topideal.supplychain.ocp.mq.QueueConstants;
import com.topideal.supplychain.ocp.mq.QueueConstants.QueueEnum;
import com.topideal.supplychain.ocp.ofc.dto.OfcRequest;
import com.topideal.supplychain.ocp.ofc.service.OfcApiService;
import com.topideal.supplychain.ocp.order.model.OrderJd;
import com.topideal.supplychain.ocp.order.model.OrderJdItem;
import com.topideal.supplychain.ocp.order.model.OrderTemp;
import com.topideal.supplychain.ocp.order.service.OrderJdItemService;
import com.topideal.supplychain.ocp.order.service.OrderJdService;
import com.topideal.supplychain.ocp.order.service.OrderTempService;
import com.topideal.supplychain.util.AES256Util;
import com.topideal.supplychain.util.DateUtils;
import com.topideal.supplychain.util.JacksonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class JdServiceImpl implements JdService {


    @Autowired
    private JdApiService jdApiService;

    @Autowired
    private OrderJdService orderJdService;

    @Autowired
    private OrderJdItemService orderJdItemService;

    @Autowired
    private OrderTempService orderTempService;

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private TransferConfigService transferConfigService;

    @Autowired
    private OfcApiService ofcApiService;

    @Autowired
    private CatchOrderConfigService catchOrderConfigService;

    /***京东自营非自营***/

    @Override
    @Transactional
    public BaseResponse getOrder(CatchOrderConfig catchOrderConfig) {
        BaseResponse<List<String>> baseResponse = new BaseResponse<>(BaseResponse.SUCCESS);
        BusinessAssert.assertNotEmpty(catchOrderConfig.getGrabId(),"抓单配置的抓单id【grabId】未配置");
        List<String> list = null;
        JdRequest.Builder jdRequestBuilder= JdRequest.newBuilder().catchOrderConfig(catchOrderConfig).method("jingdong.guangzhou.customs.queryOrderByParam");
        //调用京东抓单接口
        boolean flag = getQueryTime(jdRequestBuilder,catchOrderConfig);
        if (!flag) {
            return baseResponse;
        }
        JdRequest jdRequest = jdRequestBuilder.build();
        do {
            baseResponse = jdApiService.grabOrder(jdRequest, catchOrderConfig.getCode());
            list = baseResponse.getData();
            if (CollectionUtils.isEmpty(list) || !baseResponse.isSuccess()) {
                break;
            }
            for (String order : list) {
                OrderTemp orderTemp = new OrderTemp(order);
                JdOrderDto jdOrder = JacksonUtils.readValue(order, JdOrderDto.class);
                String orderNo = catchOrderConfig.getCode();
                if (jdOrder != null) {
                    orderNo = jdOrder.getWaybillInfo().getOrderId();
                }
                //插入临时表数据，并发送处理的mq
                orderTempService.insertAndSendMq(orderTemp, QueueEnum.OCP_JD_ORDER_PROCESS,
                        new BasicMessage(orderTemp.getId(), orderNo, catchOrderConfig.getGrabId(),
                                ""));
            }
            jdRequest.setPage(jdRequest.getPage() + 1);
        } while (!CollectionUtils.isEmpty(list));
        if (!baseResponse.isSuccess()) {
            return baseResponse;
        }
        updateQuery(catchOrderConfig);
        //判断是否要进行下个时间段的查询
        if(isNeedNext(catchOrderConfig)) {
            messageSender.send(QueueConstants.QueueEnum.OCP_JD_GET_ORDER, new BasicMessage(catchOrderConfig.getId(), catchOrderConfig.getCode()));
        }
        return baseResponse;
    }

    @Override
    @Transactional
    public void processOrder(OrderTemp orderTemp,String grabId) {
        JdOrderDto jdOrderDto = JacksonUtils.readValue(orderTemp.getJson(),JdOrderDto.class);
        if (jdOrderDto == null) {
           throw new BusinessException("订单解析失败");
        }
        /*boolean isExist = orderJdService.isExist(jdOrderDto.getWaybillInfo().getOrderId(),OrderStatusEnum.SEND_SUCCESS);
        if (isExist) {
            orderTempService.deleteById(orderTemp.getId());
            return ;
        }*/
        OrderJd orderJd = converterOrderInfo(jdOrderDto,grabId);
        orderJdService.insert(orderJd);

        List<OrderJdItem> itemList = converterOrderItemInfo(jdOrderDto,orderJd.getId());
        orderJdItemService.insertList(itemList);
        orderTempService.deleteById(orderTemp.getId());
        messageSender.send(QueueEnum.OCP_JD_ORDER_TO_OFC,
                new BasicMessage(orderJd.getId(), orderJd.getCode()));
    }

    private OrderJd converterOrderInfo(JdOrderDto dto,String grabId){
        WaybillInfo waybillInfo = dto.getWaybillInfo();
        QingDanInfo qingDanInfo = dto.getQingDanInfo();
        OrderJd orderM = new OrderJd();
        orderM.setGrabKey(grabId);
        orderM.setOrderId(waybillInfo.getOrderId());
        orderM.setOrderCreateTime(waybillInfo.getCreateTime());
        orderM.setIeFlag(waybillInfo.getIeFlag());
        orderM.setCustomsType(CustomsTypeEnum.getValueEnum(Integer.valueOf(waybillInfo.getCustomsType())));
        orderM.setLogisticsCode(waybillInfo.getEntRecordNo());
        orderM.setLogisticsNo(waybillInfo.getWayBillNo());
        orderM.setFreight(StringUtils.isEmpty(waybillInfo.getFreight())?BigDecimal.ZERO:new BigDecimal(waybillInfo.getFreight()));
        orderM.setFreightCurr(waybillInfo.getFreightCurr());
        orderM.setTax(StringUtils.isEmpty(waybillInfo.getTax())?BigDecimal.ZERO:new BigDecimal(waybillInfo.getTax()));
        orderM.setTaxCurr(waybillInfo.getTaxCurr());
        if (StringUtils.isNotEmpty(waybillInfo.getRecipientProvincesName())) {
            String[] address = waybillInfo.getRecipientProvincesName().split(" ");
            orderM.setConsigneeProvince(address[0]);
            orderM.setConsigneeCity(address.length > 1 ? address[1]:"");
            orderM.setConsigneeCounty(address.length > 2 ? address[2]:"");
        }

        BigDecimal num =StringUtils.isEmpty(waybillInfo.getNum())?BigDecimal.ZERO:new BigDecimal(waybillInfo.getNum());
        orderM.setPackNo(num);
        /*if (num.compareTo(BigDecimal.ZERO) == 1) {

        }*/
        orderM.setGoodInfo(waybillInfo.getGoodInfo());
        orderM.setInsuredFee(StringUtils.isBlank(waybillInfo.getInsuredFee())?BigDecimal.ZERO:new BigDecimal(waybillInfo.getInsuredFee()));
        orderM.setBuyerPhone(AES256Util.encrypt(waybillInfo.getBuyerPhone()));
        orderM.setBuyerName(waybillInfo.getBuyerName());
        if (StringUtils.isBlank(waybillInfo.getBuyerIdType())){
            orderM.setBuyerIdType(BuyeridTypeEnum.IDCARD);
        } else {
            orderM.setBuyerIdType(BuyeridTypeEnum.getValueEnum(waybillInfo.getBuyerIdType()));
        }
        orderM.setBuyerIdNumber(AES256Util.encrypt(waybillInfo.getBuyerIdNumber()));
        orderM.setConsigneeName(waybillInfo.getRecipientName());
        //orderM.setre(waybillInfo.getRecipientProvincesName());
        orderM.setRecipientProvincesCode(waybillInfo.getRecipientProvincesCode());
        orderM.setConsigneeAddress(waybillInfo.getRecipientDetailedAddress());
        orderM.setConsigneePhone(AES256Util.encrypt(waybillInfo.getRecipientPhone()));
        orderM.setShipperCountryCode(waybillInfo.getShipperCountryCode());
        orderM.setVenderId(waybillInfo.getVenderId());
        orderM.setEclpCode(waybillInfo.getEclpCode());
        orderM.setThirdPlatformCode(waybillInfo.getPlatformId());
        orderM.setThirdPlatformName(waybillInfo.getPlatformName());
        orderM.setShouldPay(waybillInfo.getOrderSum());
        orderM.setDiscount(waybillInfo.getDiscount());
        orderM.setDiscountNote(waybillInfo.getDiscountNote());
        CustomModelEnum customModelEnum = CustomModelEnum.getValueEnum(waybillInfo.getModelId());
        orderM.setCustomModel(customModelEnum);
        /*if (customModelEnum != null) {
            orderM.setBusinessMode(customModelEnum.getBusiModeEnum().getValue());
        }*/
        orderM.setStoreId(waybillInfo.getStoreId());
        JdOrderEnum orderType = JdOrderEnum.getValueEnum( waybillInfo.getOrderType());
        orderM.setOrderType(orderType);
        orderM.setEbcCode(qingDanInfo.getvCompPlatformNo());
        orderM.setEbpCode(qingDanInfo.getEbpCode());
        orderM.setCustomsCode(qingDanInfo.getvIepc());
        orderM.setvQyState(qingDanInfo.getvQyState());
        orderM.setCiqbCode(qingDanInfo.getCiqbCode());
        orderM.setPayCode(qingDanInfo.getPayCode());
        orderM.setPayName(qingDanInfo.getPayName());
        orderM.setPayTransactionId(qingDanInfo.getPayTransactionId());
        orderM.setConsigneeIdNumber(AES256Util.encrypt(qingDanInfo.getReceiveNo()));
        //设置订单状态
        orderM.setStatus(OrderStatusEnum.INIT);
        return orderM;
    }

    private List<OrderJdItem> converterOrderItemInfo(JdOrderDto dto, Long orderId) {
        List<OrderJdItem> orderJdItems = Lists.newArrayList();
        List<JdOrderItemDto> orderItemDtos = dto.getListGoods();
        for (JdOrderItemDto itemDto : orderItemDtos) {
            OrderJdItem item = new OrderJdItem();
            item.setUnit1(itemDto.getUnit1());
            item.setUnit2(itemDto.getUnit2());
            item.setQty1(itemDto.getAmount1());
            item.setQty2(itemDto.getAmount2());
            item.setgNum(itemDto.getgIndex());
            item.setQuantity(StringUtils.isEmpty(itemDto.getQty()) ? BigDecimal.ZERO
                    : new BigDecimal(itemDto.getQty()));
            item.setPrice(StringUtils.isEmpty(itemDto.getDecPrice()) ? BigDecimal.ZERO
                    : new BigDecimal(itemDto.getDecPrice()));
            item.setTotalPrice(StringUtils.isEmpty(itemDto.getDecTotal()) ? BigDecimal.ZERO
                    : new BigDecimal(itemDto.getDecTotal()));
            item.setCurr(itemDto.getCurr());
            item.setItemNo(itemDto.getCopGNo());
            item.setCustomRecord(itemDto.getvGoodsRegistNo());
            item.setUnit(itemDto.getvUnit());
            item.setgNo(itemDto.getgNo());
            item.setItemName(itemDto.getvGoodsName());
            item.setSpe(itemDto.getvSpMod());
            item.setCountry(itemDto.getCountry());
            item.setNetWeight(StringUtils.isEmpty(itemDto.getNetWeight()) ? BigDecimal.ZERO
                    : new BigDecimal(itemDto.getNetWeight()));
            item.setSkuId(itemDto.getSkuId());
            item.setBrand(itemDto.getBrand());
            item.setOrderId(orderId);
            orderJdItems.add(item);
        }
        return orderJdItems;
    }

    /******京东云霄购*******/
    @Override
    @Transactional
    public BaseResponse getYxOrder(CatchOrderConfig catchOrderConfig) {
        BaseResponse<List<String>> baseResponse = BaseResponse.responseSuccess("订单抓取成功");
        BusinessAssert.assertNotEmpty(catchOrderConfig.getGrabId(),"抓单配置的抓单id【grabId】未配置");
        JdRequest.Builder jdRequestBuilder = JdRequest.newBuilder().catchOrderConfig(catchOrderConfig)
                .method("jingdong.YunXiaoServiceProviderService.queryOrderByParam")
                .isYx(true);
        //调用京东抓单接口
        boolean flag = getQueryTime(jdRequestBuilder,catchOrderConfig);
        if (!flag) {
            return baseResponse;
        }
        JdRequest jdRequest = jdRequestBuilder.build();

        List<String> list = null;
        do {
            //调用京东抓单接口
            baseResponse = jdApiService.grabYxOrder(jdRequest,catchOrderConfig.getCode());
            list = baseResponse.getData();
            if (CollectionUtils.isEmpty(list) || !baseResponse.isSuccess()) {
                break;
            }
            for (String order : list) {
                OrderTemp orderTemp = new OrderTemp(order);
                String orderNo = catchOrderConfig.getCode();
                JdOrderDto jdOrder = JacksonUtils.readValue(order,JdOrderDto.class);
                if (jdOrder != null) {
                    orderNo = jdOrder.getGuangZhouYunXiaoOrderEntity().getSpSoNo();
                }
                //插入临时表数据，并发送处理的mq
                orderTempService.insertAndSendMq(orderTemp, QueueEnum.OCP_JD_YX_ORDER_PROCESS,
                        new BasicMessage(orderTemp.getId(), orderNo,catchOrderConfig.getGrabId(),""));
            }
            jdRequest.setPage(jdRequest.getPage()+1);
        }while (!CollectionUtils.isEmpty(list));
        //如果接口调用失败，直接返回重试
        if (!baseResponse.isSuccess()) {
            return baseResponse;
        }
        updateQuery(catchOrderConfig);
        //判断是否要进行下个时间段的查询
        if(isNeedNext(catchOrderConfig)) {
            messageSender.send(QueueEnum.OCP_JD_YX_GET_ORDER, new BasicMessage(catchOrderConfig.getId(), catchOrderConfig.getCode()));
        }
        return baseResponse;
    }


    @Override
    @Transactional
    public void processYxOrder(OrderTemp orderTemp,String grabId) {
        JdOrderDto jdOrderDto = JacksonUtils.readValue(orderTemp.getJson(),JdOrderDto.class);
        if (jdOrderDto == null) {
            throw new BusinessException("订单解析失败");
        }
        /*boolean isExist = orderJdService.isExist(jdOrderDto.getGuangZhouYunXiaoOrderEntity().getSpSoNo(),OrderStatusEnum.SEND_SUCCESS);
        if (isExist) {
            orderTempService.deleteById(orderTemp.getId());
            return ;
        }*/
        OrderJd orderJd = converterYxOrderInfo(jdOrderDto,grabId);
        orderJdService.insert(orderJd);

        List<OrderJdItem> itemList = converterYxOrderItemInfo(jdOrderDto,orderJd.getId());
        orderJdItemService.insertList(itemList);
        orderTempService.deleteById(orderTemp.getId());
        messageSender.send(QueueEnum.OCP_JD_ORDER_TO_OFC,
                new BasicMessage(orderJd.getId(), orderJd.getCode()));
    }

    private OrderJd converterYxOrderInfo(JdOrderDto dto,String grabId){

        GuangZhouYunXiaoOrderEntity entity = dto.getGuangZhouYunXiaoOrderEntity();
        OrderJd orderM = new OrderJd();
        orderM.setGrabKey(grabId);
        orderM.setOrderId(entity.getSpSoNo());
        orderM.setOrderCreateTime(entity.getSalesPlatformCreateTime());
        orderM.setCustomModel(CustomModelEnum.getValueEnum(entity.getCustomModel()));
        //orderM.setIeFlag(entity.getIeFlag());
        //orderM.setCustomsType(CustomsTypeEnum.getValueEnum(Integer.valueOf(entity.getCustomsType())));
        orderM.setEbcCode(entity.getEbcCode());
        orderM.setEbcName(entity.getEbcName());
        orderM.setEbpName(entity.getEcpName());
        orderM.setEbpCode(entity.getEcpCode());
        orderM.setLogisticsCode(entity.getLogisticsCode());
        orderM.setLogisticsName(entity.getLogisticsName());
        orderM.setLogisticsNo(entity.getLogisticsNo());
        orderM.setDpdm(entity.getDpdm());
        orderM.setInternetCode(entity.getInternetCode());
        orderM.setCustomsCode(entity.getCustomsCode());
        orderM.setCiqbCode(entity.getCiqOrgCode());
        orderM.setVenderId(entity.getVenderId());
        orderM.setWarehouseNo(entity.getWarehouseNo());

        orderM.setCurrency(entity.getCurrency());
        orderM.setFreight(entity.getFreight());
        orderM.setFreightCurr("CNY");
        orderM.setTax(entity.getTaxTotal());
        orderM.setTaxCurr("CNY");
        orderM.setPackNo(entity.getPackNo());
        //orderM.setGoodInfo(entity.getGoodInfo());
        orderM.setInsuredFee(entity.getInsuredFee());
        orderM.setBuyerPhone(AES256Util.encrypt(entity.getBuyerPhone()));
        orderM.setBuyerName(entity.getBuyerName());
        if (StringUtils.isBlank(entity.getBuyerIdType())){
            orderM.setBuyerIdType(BuyeridTypeEnum.IDCARD);
        } else {
            orderM.setBuyerIdType(BuyeridTypeEnum.getValueEnum(entity.getBuyerIdType()));
        }
        orderM.setBuyerIdNumber(AES256Util.encrypt(entity.getBuyerIdNumber()));
        orderM.setConsigneeAddress(entity.getConsigneeAddress());
        orderM.setConsigneeIdNumber(AES256Util.encrypt(entity.getConsigneeIdNumber()));
        orderM.setConsigneeCountry(entity.getConsigneecountry());
        orderM.setConsigneeEmail(entity.getConsigneeEmail());
        orderM.setConsigneePhone(AES256Util.encrypt(entity.getConsigneeMobile()));
        orderM.setConsigneeName(entity.getConsigneeName());
        orderM.setConsigneePostCode(entity.getConsigneePostcode());
        if (StringUtils.isBlank(entity.getConsigneeIdType())){
            orderM.setConsigneeIdType(BuyeridTypeEnum.IDCARD);
        } else {
            orderM.setConsigneeIdType(BuyeridTypeEnum.getValueEnum(entity.getConsigneeIdType()));
        }
        orderM.setConsigneeProvince(entity.getAddressProvince());
        orderM.setConsigneeCity(entity.getAddressCity());
        orderM.setConsigneeCounty(entity.getAddressCounty());
        orderM.setConsigneeTown(entity.getAddressTown());
        orderM.setPayCode(entity.getPayCode());
        orderM.setPayName(entity.getPayName());
        orderM.setPayTransactionId(entity.getPayTransactionId());
        orderM.setShouldPay(entity.getShouldPay());
        orderM.setGoodsValue(entity.getGoodsValue());
        orderM.setOtherPrice(entity.getOtherPrice());
        orderM.setDiscount(entity.getDiscount());
        orderM.setBuyerRegNo(entity.getBuyerRegNo());
        orderM.setIsTax(entity.getIstax());
        orderM.setCustomModel(CustomModelEnum.getValueEnum(entity.getCustomModel()));
        //JdOrderEnum orderType = JdOrderEnum.getValueEnum( entity.getOrderType());
        orderM.setOrderType(JdOrderEnum.SHIES);
        //设置订单状态
        orderM.setStatus(OrderStatusEnum.INIT);
        return orderM;
    }

    private List<OrderJdItem> converterYxOrderItemInfo(JdOrderDto dto, Long orderId) {
        List<OrderJdItem> orderJdItems = Lists.newArrayList();
        List<GuangZhouYunXiaoItemEntities> orderItemDtos = dto.getGuangZhouYunXiaoItemEntities();
        for (GuangZhouYunXiaoItemEntities itemDto : orderItemDtos) {
            OrderJdItem item = new OrderJdItem();
            item.setUnit1(itemDto.getUnit1());
            item.setUnit2(itemDto.getUnit2());
            item.setQty1(itemDto.getQty1());
            item.setQty2(itemDto.getQty2());
            item.setgNum(itemDto.getGnum());
            item.setQuantity(itemDto.getQuantity());
            item.setPrice(itemDto.getPrice());
            item.setTotalPrice(itemDto.getTotalPrice());
            item.setCurr("CNY");
            item.setItemNo(itemDto.getItemNo());
            item.setItemDescribe(itemDto.getItemDescribe());
            item.setCustomRecord(itemDto.getCustomRecord());
            item.setQiRecord(itemDto.getQiRecord());
            item.setHscode(itemDto.getHscode());
            //item.setUnit(itemDto.ge());
            //item.setgNo(itemDto.getgNo());
            item.setItemName(itemDto.getItemName());
            item.setSpe(itemDto.getSpe());
            item.setCountry(itemDto.getCountry());
            item.setNetWeight(StringUtils.isEmpty(itemDto.getNetWeight()) ? BigDecimal.ZERO
                    : new BigDecimal(itemDto.getNetWeight()));
            item.setWeight(StringUtils.isEmpty(itemDto.getWeight()) ? BigDecimal.ZERO
                    : new BigDecimal(itemDto.getWeight()));
            item.setBarCode(itemDto.getBarCode());
            item.setBatchNumbers(itemDto.getBatchNumbers());
            item.setNote(itemDto.getNote());
            item.setOrderId(orderId);
            orderJdItems.add(item);
        }
        return orderJdItems;
    }

    /****京东独立站多渠道****/
    @Override
    @Transactional
    public BaseResponse grabDlzOrder(CatchOrderConfig catchOrderConfig) {
        BaseResponse<List<String>> baseResponse = BaseResponse.responseSuccess("订单抓取成功");
        BusinessAssert.assertNotEmpty(catchOrderConfig.getGrabId(),"抓单配置的抓单id【grabId】未配置");
        List<String> list = null;
        JdRequest.Builder jdRequestBuilder = JdRequest.newBuilder().catchOrderConfig(catchOrderConfig)
                .method("jingdong.dlz.guangzhou.customs.queryPeriodOrder")
                .isDlz(true);

        //调用京东抓单接口
        boolean flag = getQueryTime(jdRequestBuilder,catchOrderConfig);
        if (!flag) {
            return baseResponse;
        }
        JdRequest jdRequest = jdRequestBuilder.build();
        do {
            baseResponse = jdApiService.grabDlzOrder(jdRequest,catchOrderConfig.getCode());
            list = baseResponse.getData();
            if (CollectionUtils.isEmpty(list) || !baseResponse.isSuccess()) {
                break;
            }
            for (String order : list) {
                OrderTemp orderTemp = new OrderTemp(order);
                String orderNo = catchOrderConfig.getCode();
                JdDlzOrderDto jdOrderDto = JacksonUtils.readValue(order,JdDlzOrderDto.class);
                if (jdOrderDto != null) {
                    orderNo = jdOrderDto.getWaybillInfo().getOrderId();
                }
                //插入临时表数据，并发送处理的mq
                orderTempService.insertAndSendMq(orderTemp, QueueEnum.OCP_JD_DLZ_ORDER_PROCESS,
                        new BasicMessage(orderTemp.getId(), orderNo,catchOrderConfig.getGrabId(),""));
            }
            jdRequest.setPage(jdRequest.getPage()+1);
        }while (!CollectionUtils.isEmpty(list));
        if (!baseResponse.isSuccess()) {
            return baseResponse;
        }
        updateQuery(catchOrderConfig);
        //判断是否要进行下个时间段的查询
        if(isNeedNext(catchOrderConfig)) {
            messageSender.send(QueueEnum.OCP_JD_DLZ_GET_ORDER, new BasicMessage(catchOrderConfig.getId(), catchOrderConfig.getCode()));
        }
        return baseResponse;
    }

    @Override
    @Transactional
    public void processDlzOrder(OrderTemp orderTemp,String grabId) {
        JdDlzOrderDto jdOrderDto = JacksonUtils.readValue(orderTemp.getJson(),JdDlzOrderDto.class);
        if (jdOrderDto == null) {
            throw new BusinessException("订单解析失败");
        }
        /*boolean isExist = orderJdService.isExist(jdOrderDto.getWaybillInfo().getOrderId(),OrderStatusEnum.SEND_SUCCESS);
        if (isExist) {
            orderTempService.deleteById(orderTemp.getId());
            return ;
        }*/
        OrderJd orderJd = converterDlzOrderInfo(jdOrderDto,grabId);
        orderJdService.insert(orderJd);

        List<OrderJdItem> itemList = converterDlzOrderItemInfo(jdOrderDto,orderJd.getId());
        orderJdItemService.insertList(itemList);
        orderTempService.deleteById(orderTemp.getId());
        messageSender.send(QueueEnum.OCP_JD_ORDER_TO_OFC,
                new BasicMessage(orderJd.getId(), orderJd.getCode()));
    }

    private OrderJd converterDlzOrderInfo(JdDlzOrderDto dto,String grabId){
        DlzWaybillInfo waybillInfo = dto.getWaybillInfo();
        QingDanInfo qingDanInfo = dto.getQingDanInfo();
        OrderJd orderM = new OrderJd();
        orderM.setGrabKey(grabId);
        orderM.setOrderId(waybillInfo.getOrderId());
        orderM.setOrderCreateTime(waybillInfo.getCreateTime());
        orderM.setIeFlag(waybillInfo.getIeFlag());
        orderM.setCustomsType(CustomsTypeEnum.getValueEnum(Integer.valueOf(waybillInfo.getCustomsType())));
        orderM.setLogisticsCode(waybillInfo.getEntRecordNo());
        orderM.setLogisticsNo(waybillInfo.getWayBillNo());
        orderM.setFreight(StringUtils.isEmpty(waybillInfo.getFreight())?BigDecimal.ZERO:new BigDecimal(waybillInfo.getFreight()));
        orderM.setFreightCurr(waybillInfo.getFreightCurr());
        orderM.setTax(StringUtils.isEmpty(waybillInfo.getTax())?BigDecimal.ZERO:new BigDecimal(waybillInfo.getTax()));
        orderM.setTaxCurr(waybillInfo.getTaxCurr());
        if (StringUtils.isNotEmpty(waybillInfo.getRecipientProvincesName())) {
            String[] address = waybillInfo.getRecipientProvincesName().split(" ");
            orderM.setConsigneeProvince(address[0]);
            orderM.setConsigneeCity(address.length > 1 ? address[1]:"");
            orderM.setConsigneeCounty(address.length > 2 ? address[2]:"");
        }

        BigDecimal num =StringUtils.isEmpty(waybillInfo.getNum())?BigDecimal.ZERO:new BigDecimal(waybillInfo.getNum());
        orderM.setPackNo(num);
        /*if (num.compareTo(BigDecimal.ZERO) == 1) {

        }*/
        orderM.setGoodInfo(waybillInfo.getGoodInfo());
        orderM.setInsuredFee(StringUtils.isBlank(waybillInfo.getInsuredFee())?BigDecimal.ZERO:new BigDecimal(waybillInfo.getInsuredFee()));
        orderM.setBuyerPhone(AES256Util.encrypt(waybillInfo.getBuyerPhone()));
        orderM.setBuyerName(waybillInfo.getBuyerName());
        if (StringUtils.isBlank(waybillInfo.getBuyerIdType())){
            orderM.setBuyerIdType(BuyeridTypeEnum.IDCARD);
        } else {
            orderM.setBuyerIdType(BuyeridTypeEnum.getValueEnum(waybillInfo.getBuyerIdType()));
        }
        orderM.setBuyerIdNumber(AES256Util.encrypt(waybillInfo.getBuyerIdNumber()));
        orderM.setConsigneeName(waybillInfo.getRecipientName());
        //orderM.setre(waybillInfo.getRecipientProvincesName());
        orderM.setRecipientProvincesCode(waybillInfo.getRecipientProvincesCode());
        orderM.setConsigneeAddress(waybillInfo.getRecipientDetailedAddress());
        orderM.setConsigneePhone(AES256Util.encrypt(waybillInfo.getRecipientPhone()));
        orderM.setShipperCountryCode(waybillInfo.getShipperCountryCode());
        orderM.setVenderId(waybillInfo.getVenderId());
        orderM.setEclpCode(waybillInfo.getEclpCode());
        orderM.setThirdPlatformCode(waybillInfo.getPlatformId());
        orderM.setThirdPlatformName(waybillInfo.getPlatformName());
        orderM.setShouldPay(waybillInfo.getOrderSum());
        orderM.setDiscount(waybillInfo.getDiscount());
        orderM.setDiscountNote(waybillInfo.getDiscountNote());
        CustomModelEnum customModelEnum = CustomModelEnum.getValueEnum(waybillInfo.getModelId());
        orderM.setCustomModel(customModelEnum);
        /*if (customModelEnum != null) {
            orderM.setBusinessMode(customModelEnum.getBusiModeEnum().getValue());
        }*/
        orderM.setStoreId(waybillInfo.getStoreId());
        JdOrderEnum orderType = JdOrderEnum.getValueEnum( waybillInfo.getOrderType());
        orderM.setOrderType(orderType);
        orderM.setEbcCode(qingDanInfo.getvCompPlatformNo());
        orderM.setEbpCode(qingDanInfo.getEbpCode());
        orderM.setCustomsCode(qingDanInfo.getvIepc());
        orderM.setvQyState(qingDanInfo.getvQyState());
        orderM.setCiqbCode(qingDanInfo.getCiqbCode());
        orderM.setPayCode(qingDanInfo.getPayCode());
        orderM.setPayName(qingDanInfo.getPayName());
        orderM.setPayTransactionId(qingDanInfo.getPayTransactionId());
        orderM.setConsigneeIdNumber(AES256Util.encrypt(qingDanInfo.getReceiveNo()));
        //设置订单状态
        orderM.setStatus(OrderStatusEnum.INIT);
        return orderM;
    }

    private List<OrderJdItem> converterDlzOrderItemInfo(JdDlzOrderDto dto, Long orderId) {
        List<OrderJdItem> orderJdItems = Lists.newArrayList();
        List<JdOrderItemDto> orderItemDtos = dto.getListGoods();
        for (JdOrderItemDto itemDto : orderItemDtos) {
            OrderJdItem item = new OrderJdItem();
            item.setUnit1(itemDto.getUnit1());
            item.setUnit2(itemDto.getUnit2());
            item.setQty1(itemDto.getAmount1());
            item.setQty2(itemDto.getAmount2());
            item.setgNum(itemDto.getgIndex());
            item.setQuantity(StringUtils.isEmpty(itemDto.getQty()) ? BigDecimal.ZERO
                    : new BigDecimal(itemDto.getQty()));
            item.setPrice(StringUtils.isEmpty(itemDto.getDecPrice()) ? BigDecimal.ZERO
                    : new BigDecimal(itemDto.getDecPrice()));
            item.setTotalPrice(StringUtils.isEmpty(itemDto.getDecTotal()) ? BigDecimal.ZERO
                    : new BigDecimal(itemDto.getDecTotal()));
            item.setCurr(itemDto.getCurr());
            item.setItemNo(itemDto.getCopGNo());
            item.setCustomRecord(itemDto.getvGoodsRegistNo());
            item.setUnit(itemDto.getvUnit());
            item.setgNo(itemDto.getgNo());
            item.setItemName(itemDto.getvGoodsName());
            item.setSpe(itemDto.getvSpMod());
            item.setCountry(itemDto.getCountry());
            item.setNetWeight(StringUtils.isEmpty(itemDto.getNetWeight()) ? BigDecimal.ZERO
                    : new BigDecimal(itemDto.getNetWeight()));
            item.setSkuId(itemDto.getSkuId());
            item.setBrand(itemDto.getBrand());
            item.setOrderId(orderId);
            orderJdItems.add(item);
        }
        return orderJdItems;
    }



    /***发送订单到ofc*****/
    @Override
    @Transactional
    public BaseResponse sendOrderOfc(OrderJd orderJd) {

        //查询默认下发OCP配置,获取默认值配置.如果默认值配置不为空则将报文中的值替换成配置，根据币值系数转换币值
        TransferConfig transferConfig = transferConfigService
                .findByUnique(orderJd.getEbcCode(), orderJd.getEbpCode(),
                        orderJd.getLogisticsCode(), orderJd.getCustomsCode(),
                        orderJd.getCustomModel().getBusiModeEnum().getValue());
        if (transferConfig == null) {
            orderJdService.updateOrderStatus(orderJd.getId(), OrderStatusEnum.SEND_FAILURE.getValue(),
                    "订单推送OFC未找到对应转发配置");
            return BaseResponse.responseFailure("订单推送OFC未找到对应转发配置");
        }
        //有赞订单商品明细表
        List<OrderJdItem> orderItems = orderJdItemService.selectByOrderId(orderJd.getId());

        //获取下发的配置
        TransferDefaultConfig defaultConfig = JacksonUtils.readValue(transferConfig.getConfiguration(), TransferDefaultConfig.class);
        //转换为订单请求
        OfcRequest req = JdDtoConverter.convertOfc(orderJd,orderItems, defaultConfig);

        BaseResponse response = ofcApiService.sendOrder(req, orderJd.getOrderId(), orderJd.getCode());

        OrderStatusEnum orderStatus = OrderStatusEnum.SEND_FAILURE;
        if (response.isSuccess()) {
            orderStatus = OrderStatusEnum.SEND_SUCCESS;
        }
        orderJdService
                .updateOrderStatus(orderJd.getId(), orderStatus.getValue(), response.getMessage());
        return response;
    }

    //获取查询时间并判断是否要进行抓取行为
    private boolean getQueryTime(JdRequest.Builder jdRequest,CatchOrderConfig catchOrderConfig) {
        Date beginDate = catchOrderConfig.getLastQueryTime();
        //如果第一次时间为空，则取当前时间往前（间隔时间+延迟1分钟）的时间
        if (beginDate == null) {
            beginDate = DateUtils.addMinute(DateUtils.getNowTime(), (catchOrderConfig.getIntervalCount()+1) * -1);
        }
        beginDate = DateUtils.dateFormatToDate(beginDate,DateUtils.Y_M_D_HM);
        Date endDate = DateUtils.addMinute(beginDate, catchOrderConfig.getIntervalCount());
        //如果当前时间比结束时间大于等于1分钟，则抓取，否则不进行抓取行为
        if (DateUtils.getTwoDatesDifOfMins(DateUtils.getNowTime(),endDate) < 1) {
            return false;
        }
        catchOrderConfig.setLastQueryTime(beginDate);
        jdRequest.beginDate(beginDate).endDate(endDate);
        return true;
    }

    //判断是否需要进行下一次时间段的查询
    private boolean isNeedNext(CatchOrderConfig catchOrderConfig) {

        Date endDate = DateUtils.addMinute(catchOrderConfig.getLastQueryTime(), catchOrderConfig.getIntervalCount()*2);
        //如果当前时间比结束时间大于等于1分钟，则抓取，否则不进行抓取行为
        return DateUtils.getTwoDatesDifOfMins(DateUtils.getNowTime(),endDate) >= 1;
    }

    /**
     * 更新抓单配置的查询时间
     * @param catchOrderConfig
     */
    private void updateQuery(CatchOrderConfig catchOrderConfig) {
        Date beginDate = catchOrderConfig.getLastQueryTime();
        Date query = DateUtils.addMinute(beginDate, catchOrderConfig.getIntervalCount());
        catchOrderConfigService.updateQueryTime(query,catchOrderConfig.getId());
    }

    /***
     * 抓取单票订单
     * 1.校验抓单id是否正确
     * 2.获取抓单配置信息catchOrderConfig
     * 3.调用不同的方法获取订单信息
     *     3.1 自营非自营的调用方法为 jingdong.guangzhou.customs.queryOrderByOrderId
     *     3.2 多渠道的方法  jingdong.dlz.guangzhou.customs.queryOrderByOrderId
     *     3.3 云霄购的方法 jingdong.YunXiaoServiceProviderService.queryOrderByOrderId
     *  4.获取订单，发送到相应的订单处理队列，自营非自营和多渠道独立站的订单结构一样，订单的处理逻辑是一样的，队列是两个
     * @param orderId
     * @param grabKey
     * @param thirdPlatformCode
     * @param isDlz
     */
    @Transactional
    @Override
    public void getOneOrder(String orderId, String grabKey, String thirdPlatformCode,boolean isDlz) {
        GrabIdEnum grabIdEnum = GrabIdEnum.getValueEnum(grabKey);
        BusinessAssert.assertNotNull(grabIdEnum,"抓单id错误");

        List<CatchOrderConfig> catchOrderConfigs = catchOrderConfigService.selectByGrabKey(grabKey);
        if (CollectionUtils.isEmpty(catchOrderConfigs)) {
            throw new BusinessException("找不到抓单配置") ;
        }
        QueueEnum queueEnum = isDlz ? QueueEnum.OCP_JD_DLZ_ORDER_PROCESS : QueueEnum.OCP_JD_ORDER_PROCESS;
        if (grabIdEnum.isYx()) {
            queueEnum = QueueEnum.OCP_JD_YX_ORDER_PROCESS;
        }
        for (CatchOrderConfig catchOrderConfig : catchOrderConfigs) {
            List<String> list = null;
            if(grabIdEnum.isYx()) {
                list = getOneYxOrder(orderId,catchOrderConfig);
            }else if(isDlz) {
                list = grabOneDlzOrder(orderId,thirdPlatformCode,catchOrderConfig);
            } else{
                list = getOneOrder(orderId,catchOrderConfig);
            }
            if (CollectionUtils.isEmpty(list)) {
                continue;
            }
            for (String order : list) {
                OrderTemp orderTemp = new OrderTemp(order);
                //插入临时表数据，并发送处理的mq
                orderTempService.insertAndSendMq(orderTemp, queueEnum,
                        new BasicMessage(orderTemp.getId(), orderId,catchOrderConfig.getGrabId(),""));
            }
        }
    }

    /**
     * 获取单票自营非自营订单
     * @return
     */
    private List<String> getOneOrder(String orderId,CatchOrderConfig catchOrderConfig) {

        JdRequest jdRequest = JdRequest.newBuilder()
                .method("jingdong.guangzhou.customs.queryOrderByOrderId")
                .catchOrderConfig(catchOrderConfig)
                .orderId(orderId)
                .isBatch(false)
                .build();
        List<String> list = jdApiService.grabOneOrder(jdRequest);

        return list;
    }

    /**
     * 获取单票订单独立站订单
     * @return
     */
    private List<String> grabOneDlzOrder(String orderId,String platformId,CatchOrderConfig catchOrderConfig) {

        JdRequest jdRequest = JdRequest.newBuilder()
                .method("jingdong.dlz.guangzhou.customs.queryOrderByOrderId")
                .isDlz(true)
                .catchOrderConfig(catchOrderConfig)
                .orderId(orderId)
                .platformId(platformId)
                .isBatch(false)
                .build();
        List<String> list = jdApiService.grabOneDlzOrder(jdRequest);

        return list;
    }

    /**
     * 单票抓取云霄购订单
     * @param orderId
     * @param catchOrderConfig
     * @return
     */
    private List<String> getOneYxOrder(String orderId,CatchOrderConfig catchOrderConfig) {
        JdRequest jdRequest = JdRequest.newBuilder()
                .method("jingdong.YunXiaoServiceProviderService.queryOrderByOrderId")
                .isYx(true)
                .catchOrderConfig(catchOrderConfig)
                .orderId(orderId)
                .isBatch(false)
                .build();
        List<String> list = jdApiService.grabOneYxOrder(jdRequest);
        return list;
    }
}

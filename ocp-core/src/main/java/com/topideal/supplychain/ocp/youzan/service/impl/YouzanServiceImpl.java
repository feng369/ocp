package com.topideal.supplychain.ocp.youzan.service.impl;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.enumeration.YesOrNoEnum;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.exception.BusinessException;
import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.mq.MessageSender;
import com.topideal.supplychain.ocp.common.ExpCodeEnum;
import com.topideal.supplychain.ocp.config.dto.TransferDefaultConfig;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.config.model.TransferConfig;
import com.topideal.supplychain.ocp.config.service.TransferConfigService;
import com.topideal.supplychain.ocp.constants.DictConstant;
import com.topideal.supplychain.ocp.enums.BusiModeEnum;
import com.topideal.supplychain.ocp.enums.KjbSendStausEnum;
import com.topideal.supplychain.ocp.enums.LogisticsEnum;
import com.topideal.supplychain.ocp.enums.OrderStatusEnum;
import com.topideal.supplychain.ocp.enums.YouZanOrderNewEnum;
import com.topideal.supplychain.ocp.esd.dto.EsdRequest;
import com.topideal.supplychain.ocp.esd.service.EsdApiService;
import com.topideal.supplychain.ocp.kjb.dto.KjbRequest;
import com.topideal.supplychain.ocp.kjb.dto.KjbResponse;
import com.topideal.supplychain.ocp.kjb.service.KjbApiService;
import com.topideal.supplychain.ocp.master.dto.EsdStoreConfig;
import com.topideal.supplychain.ocp.master.model.Store;
import com.topideal.supplychain.ocp.master.service.StoreService;
import com.topideal.supplychain.ocp.mq.QueueConstants.QueueEnum;
import com.topideal.supplychain.ocp.ofc.dto.OfcRequest;
import com.topideal.supplychain.ocp.ofc.service.OfcApiService;
import com.topideal.supplychain.ocp.op.dto.OpRequest;
import com.topideal.supplychain.ocp.op.service.OpApiService;
import com.topideal.supplychain.ocp.order.dto.OrderYouzanDto;
import com.topideal.supplychain.ocp.order.model.OrderTemp;
import com.topideal.supplychain.ocp.order.model.OrderYouzan;
import com.topideal.supplychain.ocp.order.model.OrderYouzanChild;
import com.topideal.supplychain.ocp.order.model.OrderYouzanEx;
import com.topideal.supplychain.ocp.order.model.OrderYouzanItem;
import com.topideal.supplychain.ocp.order.model.OrderYouzanPayments;
import com.topideal.supplychain.ocp.order.model.OrderYouzanTags;
import com.topideal.supplychain.ocp.order.service.OrderTempService;
import com.topideal.supplychain.ocp.order.service.OrderYouzanChildService;
import com.topideal.supplychain.ocp.order.service.OrderYouzanExService;
import com.topideal.supplychain.ocp.order.service.OrderYouzanItemService;
import com.topideal.supplychain.ocp.order.service.OrderYouzanPaymentsService;
import com.topideal.supplychain.ocp.order.service.OrderYouzanService;
import com.topideal.supplychain.ocp.order.service.OrderYouzanTagsService;
import com.topideal.supplychain.ocp.service.SystemConfigService;
import com.topideal.supplychain.ocp.youzan.converter.YouzanDtoConverter;
import com.topideal.supplychain.ocp.youzan.dto.YouZanConfig;
import com.topideal.supplychain.ocp.youzan.dto.YouZanConfig.CustomBusiness;
import com.topideal.supplychain.ocp.youzan.dto.YouzanToken.OAuthToken;
import com.topideal.supplychain.ocp.youzan.service.YouzanApiService;
import com.topideal.supplychain.ocp.youzan.service.YouzanService;
import com.topideal.supplychain.util.AES256Util;
import com.topideal.supplychain.util.DateUtils;
import com.topideal.supplychain.util.JacksonUtils;
import com.topideal.supplychain.web.system.service.DictService;
import com.youzan.cloud.open.sdk.gen.v4_0_0.model.YouzanTradesSoldGetParams;
import com.youzan.cloud.open.sdk.gen.v4_0_0.model.YouzanTradesSoldGetResult.YouzanTradesSoldGetResultAddressinfo;
import com.youzan.cloud.open.sdk.gen.v4_0_0.model.YouzanTradesSoldGetResult.YouzanTradesSoldGetResultBuyerinfo;
import com.youzan.cloud.open.sdk.gen.v4_0_0.model.YouzanTradesSoldGetResult.YouzanTradesSoldGetResultChildinfo;
import com.youzan.cloud.open.sdk.gen.v4_0_0.model.YouzanTradesSoldGetResult.YouzanTradesSoldGetResultChildorders;
import com.youzan.cloud.open.sdk.gen.v4_0_0.model.YouzanTradesSoldGetResult.YouzanTradesSoldGetResultFullorderinfo;
import com.youzan.cloud.open.sdk.gen.v4_0_0.model.YouzanTradesSoldGetResult.YouzanTradesSoldGetResultFullorderinfolist;
import com.youzan.cloud.open.sdk.gen.v4_0_0.model.YouzanTradesSoldGetResult.YouzanTradesSoldGetResultOrderinfo;
import com.youzan.cloud.open.sdk.gen.v4_0_0.model.YouzanTradesSoldGetResult.YouzanTradesSoldGetResultOrders;
import com.youzan.cloud.open.sdk.gen.v4_0_0.model.YouzanTradesSoldGetResult.YouzanTradesSoldGetResultPayinfo;
import com.youzan.cloud.open.sdk.gen.v4_0_0.model.YouzanTradesSoldGetResult.YouzanTradesSoldGetResultPhasepayments;
import com.youzan.cloud.open.sdk.gen.v4_0_0.model.YouzanTradesSoldGetResult.YouzanTradesSoldGetResultRemarkinfo;
import com.youzan.cloud.open.sdk.gen.v4_0_0.model.YouzanTradesSoldGetResult.YouzanTradesSoldGetResultSourceinfo;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * @ClassName YouzanServiceImpl
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2019/11/22 11:06
 * @Version 1.0
 **/
@Service
public class YouzanServiceImpl implements YouzanService {

    @Autowired
    private YouzanApiService youzanApiService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private DictService dictService;
    @Autowired
    private OrderYouzanService orderYouzanService;
    @Autowired
    private OrderYouzanItemService orderYouzanItemService;
    @Autowired
    private OrderYouzanTagsService orderYouzanTagsService;
    @Autowired
    private OrderYouzanExService orderYouzanExService;
    @Autowired
    private OrderYouzanChildService orderYouzanChildService;
    @Autowired
    private OrderYouzanPaymentsService orderYouzanPaymentsService;
    @Autowired
    private OrderTempService orderTempService;
    @Autowired
    private OfcApiService ofcApiService;
    @Autowired
    private TransferConfigService transferConfigService;
    @Autowired
    private MessageSender messageSender;
    @Autowired
    private KjbApiService kjbApiService;
    @Autowired
    private OpApiService opApiService;
    @Autowired
    private EsdApiService esdApiService;
    @Autowired
    private SystemConfigService systemConfigService;

    /**
     * 正常抓单
     */
    public void getOrder(CatchOrderConfig catchOrderConfig) {
        getOrder(catchOrderConfig, false);
    }


    /**
     * 抓取之前检查token是否过期 1:获取店铺信息，校验店铺参数中token是否过期 2：如果token过期,则进行重新获取token的值，并更新token的过期时间
     * 3：分页抓取有赞的订单信息 4
     */
    private void getOrder(CatchOrderConfig catchOrderConfig, boolean isMiss) {
        Store store = storeService.selectById(catchOrderConfig.getStoreId());
        if (store == null) {
            throw new BusinessException("未找到店铺信息");
        }
        YouZanConfig youZanConfig = JacksonUtils
                .readValue(store.getArguments(), YouZanConfig.class);
        if (youZanConfig == null) {
            throw new BusinessException("店铺未配置抓单信息");
        }
//        getToken(store, youZanConfig);

        int pageNo = 1;
        List<YouzanTradesSoldGetResultFullorderinfolist> list;

        YouzanTradesSoldGetParams params = new YouzanTradesSoldGetParams();
        params.setPageSize(catchOrderConfig.getPageSize());
        params.setStatus("WAIT_SELLER_SEND_GOODS");
        params.setEndUpdate(DateUtils.getNowTime());
        params.setStartUpdate(DateUtils.addMinute(params.getEndUpdate(), -30));
        if (isMiss) {
            int intervalTime = systemConfigService.getIntervalTimeForMiss() * -1;
            params.setStartUpdate(
                    DateUtils.addMinutes(DateUtils.addDays(params.getEndUpdate(), intervalTime), -30));
        }
        do {
            params.setPageNo(pageNo);

            list = youzanApiService.getOrder(params, store, youZanConfig, ExpCodeEnum.YOUZAN003);
            for (YouzanTradesSoldGetResultFullorderinfolist orderInfo : list) {
                OrderTemp orderTemp = new OrderTemp(JacksonUtils.toJSon(orderInfo));

                String orderNo = orderInfo.getFullOrderInfo().getOrderInfo()
                        .getTid();//主订单号
                YouzanTradesSoldGetResultOrders item = orderInfo.getFullOrderInfo()
                        .getOrders().get(0);
                String subOrderNo = item == null ? orderNo : item.getSubOrderNo();
                orderTemp.setCode(subOrderNo);
                //插入临时表数据，并发送处理的mq
                orderTempService.insertAndSendMq(orderTemp, QueueEnum.OCP_YOUZAN_ORDER_PROCESS,
                        new BasicMessage(orderTemp.getId(), subOrderNo,
                                store.getId().toString(), ""));
            }
            pageNo++;
        } while (!CollectionUtils.isEmpty(list));
    }

    /**
     * 获取店铺的token 1:校验店铺的token是否为空,有效期是否过期
     */
    private void getToken(Store store, YouZanConfig youZanConfig) {
        String tokenStr = youZanConfig.getToken();
        if (StringUtils.isEmpty(tokenStr) || youZanConfig.getExpiratioTime() == null ||
                DateUtils.compareDateTime(DateUtils.getNowTime(), youZanConfig.getExpiratioTime())
                        > 0) {
            OAuthToken token = youzanApiService.getToken(store, youZanConfig);
            //更新token到抓单配置
            if (token != null && !(token.getAccessToken().equals(youZanConfig.getToken()) &&
                    new Date(token.getExpires()).equals(youZanConfig.getExpiratioTime()))) {
                youZanConfig.setExpiratioTime(new Date(token.getExpires()));
                youZanConfig.setToken(token.getAccessToken());
                Store newStore = new Store();
                newStore.setId(store.getId());
                newStore.setArguments(JacksonUtils.toJSon(youZanConfig));
                storeService.update(newStore);
            } else {
                throw new BusinessException("有赞token刷新失败");
            }
        }
    }


    /**
     * 1:处理订单信息 2：删除订单临时表的数据
     */
    @Transactional
    @Override
    public void processOrder(Store store, OrderTemp orderTemp) {
        process(store, orderTemp.getJson());
        orderTempService.deleteById(orderTemp.getId());
    }

    /**
     * 处理订单信息 1：复制订单信息数据 3：校验订单是否存在（根据订单的tid跟subOrderNo），存在的订单不在处理
     *
     * 4：校验是否属于需要处理的业务类型和关区的订单，有赞关区的转换（数据字典：youzan.custom.code
     *
     * ），是否需要处理的关区跟业务类型的配置在店铺配置参数中，过掉未配置的订单
     *
     * 5：如果是分销的订单IsPurchaseOrder为ture，FenxiaoPrice商品优惠后总价
     *
     * FenxiaoPayment商品最终均摊价  FxOrderNo分销订单号 FxInnerTransactionNo分销内部支付流水号 FxKdtId分销店铺id 必须存在
     *
     * 6：校验商品的跨境标识IsCrossBorder，不为跨境的订单不保存
     *
     * 7：保存订单信息,扩展信息，支付信息，标记信息，订单明细信息，子订单信息
     */
    private void process(Store store, String json) {

        YouzanTradesSoldGetResultFullorderinfolist orderInfo = JacksonUtils
                .readValue(json, YouzanTradesSoldGetResultFullorderinfolist.class);
        YouZanConfig youZanConfig = JacksonUtils
                .readValue(store.getArguments(), YouZanConfig.class);
        EsdStoreConfig esdStoreConfig = JacksonUtils
                .readValue(store.getEsdArguments(), EsdStoreConfig.class);
        //BusinessAssert.assertNotNull(esdStoreConfig,"");
        //复制订单数据
        List<OrderYouzanDto> youZanOrderList = converterFullOrderInfo(orderInfo, youZanConfig);
        if (CollectionUtils.isEmpty(youZanOrderList)) {
            return;
        }
        Set<String> noSet = new HashSet<>();//没有分销字段的订单号
        for (OrderYouzanDto youZanOrder : youZanOrderList) {
            List<OrderYouzanItem> orderItems = youZanOrder.getOrderYouzanItemList();
            //设置订单默认值
            youZanOrder.setOldTid(youZanOrder.getTid());
            youZanOrder.setToStatus(OrderStatusEnum.INIT);//订单状态
            youZanOrder.setToken(youZanConfig.getToken());//店铺token
            youZanOrder.setAppId(esdStoreConfig != null ? esdStoreConfig.getAppId() : "");//店铺id
            youZanOrder.setElectricCode(store.getMerchantCode());//电商企业编码
            youZanOrder.setEbpCode(store.getPlatformCode());//电商平台编码
            youZanOrder.setSubOrderNo(orderItems.get(0).getSubOrderNo());//子订单号，取第一个商品的订单号

            youZanOrder.setAppName(store.getCode());//店铺名称
            youZanOrder.setStoreKey(youZanConfig.getKey());//key
            //如果为分销
            if (youZanOrder.getOrderYouzanTags() != null
                    && youZanOrder.getOrderYouzanTags().getIsPurchaseOrder() != null &&
                    youZanOrder.getOrderYouzanTags().getIsPurchaseOrder()) {
                if (StringUtils.isEmpty(youZanOrder.getOrderYouzanEx().getFxOrderNo())
                        || StringUtils
                        .isEmpty(youZanOrder.getOrderYouzanEx().getFxInnerTransactionNo())
                        || StringUtils.isEmpty(youZanOrder.getOrderYouzanEx().getFxKdtId())) {
                    noSet.add(youZanOrder.getSubOrderNo());
                }
                //主订单号order_extra.fx_order_no
                youZanOrder.setTid(youZanOrder.getOrderYouzanEx().getFxOrderNo());
                //支付流水号 order_extra .fx_inner_transaction_no
                youZanOrder
                        .setTransaction(youZanOrder.getOrderYouzanEx().getFxInnerTransactionNo());
                for (OrderYouzanItem youZanOrderItem : orderItems) {
                    if (youZanOrderItem.getFenxiaoPrice() == null
                            || youZanOrderItem.getFenxiaoPayment() == null) {
                        noSet.add(youZanOrder.getSubOrderNo());
                    }
                    //设置商品优惠后总价
                    youZanOrderItem.setTotalFee(youZanOrderItem.getFenxiaoPrice());
                    //设置商品最终均摊价
                    youZanOrderItem.setPayment(youZanOrderItem.getFenxiaoPayment());
                    //设置单商品原价 小数点精度问题
                    youZanOrderItem.setPrice(youZanOrderItem.getTotalFee()
                            .divide(youZanOrderItem.getNum(), 2,
                                    BigDecimal.ROUND_HALF_UP));
                }
            }

            youZanOrder.setDno(store.getCode());//店铺编码
            youZanOrder.setStorehouseId(esdStoreConfig != null ? esdStoreConfig.getOverseaHouseCode() : "");//海外仓编码
            youZanOrder.setAppKey(esdStoreConfig != null ? esdStoreConfig.getAppKey() : "");//appkey
        }

        //判断此订单为分销订单，缺少分销订单所需字段信息
        BusinessAssert.assertEmpty(noSet,
                noSet.stream().collect(Collectors.joining(",")) + "订单为分销订单，缺少分销订单所需字段信息！");

        List<OrderYouzan> mqOrderYouzanList = new ArrayList<>();
        for (OrderYouzanDto youZanOrder : youZanOrderList) {
            List<OrderYouzanItem> orderItems = youZanOrder.getOrderYouzanItemList();
            //校验商品明细为空，不保存
            if (CollectionUtils.isEmpty(orderItems)) {
                continue;
            }
            //当商品不为空时：校验商品的跨境标识，如果存在不为跨境的商品则不保存该订单
            if (orderItems.stream().filter(a -> a.getIsCrossBorder()
                    .equals(YesOrNoEnum.NO.getValue().toString())).collect(
                    Collectors.toList()).size() > 0) {
                continue;
            }

            mqOrderYouzanList.add(youZanOrder);
            saveInformation(youZanOrder, orderItems);

            // 解析转单配置，下发对应的系统
            TransferConfig transferConfig = transferConfigService
                    .findByUnique(youZanOrder.getElectricCode(), youZanOrder.getEbpCode(),
                            LogisticsEnum.VL.getCode(), youZanOrder.getCustomsCode(),
                            youZanOrder.getOrderTradeMode().getValue());
            BusinessAssert.assertNotNull(transferConfig, "订单未找到对应转发配置!");

            //更新转发系统
            orderYouzanService
                    .updateForwardSystem(youZanOrder.getId(), transferConfig.getForwardSystem());
            QueueEnum queueEnum = QueueEnum.getQueueEnumByName(
                    "ocp.youzan.order.send." + transferConfig.getForwardSystem().getQueue());
            messageSender.send(queueEnum, new BasicMessage(youZanOrder.getId(), youZanOrder.getCode()));

        }

        // 2020/05/18 之后废弃推送组合拆分接口，不再推跨境宝
//        //数据保存成功则发送推送至跨境宝
//        for (OrderYouzan youZanOrder : mqOrderYouzanList) {
//            messageSender.send(QueueEnum.OCP_YOUZAN_ORDER_TO_KJB,
//                    new BasicMessage(youZanOrder.getId(), youZanOrder.getCode()));
//        }
    }

    /**
     * 转化订单为内部订单信息（有赞一个订单里面会有子订单，以子订单的维度处理） 1：订单明细转为内部订单明细 2：根据子单号进行分组，拆分成不同的订单
     * 3：根据主单号跟每个子单号校验订单是否存在，存在的订单不在进行处理 4：过滤店铺里面配置了的关区跟业务模式的订单 4：复制订单的信息 5:复制地址信息 6:复制购买信息
     * 7:复制订单备注信息 8:复制订单详细信息 9:复制订单标记信息 10:复制订单扩展字段信息 11:复制多支付信息 12:复制订单来源信息 13:复制送礼子单信息
     */
    private List<OrderYouzanDto> converterFullOrderInfo(
            YouzanTradesSoldGetResultFullorderinfolist fullOrderInfo, YouZanConfig youZanConfig) {
        List<OrderYouzanDto> list = new ArrayList<>();
        if (fullOrderInfo == null) {
            return list;
        }
        //只处理参数配置中的关区 业务模式的订单

        YouzanTradesSoldGetResultFullorderinfo info = fullOrderInfo.getFullOrderInfo();
        YouzanTradesSoldGetResultAddressinfo addressInfo = info.getAddressInfo();//订单收货地址信息结构体
        YouzanTradesSoldGetResultBuyerinfo buyerInfo = info.getBuyerInfo();//订单买家信息结构体
        YouzanTradesSoldGetResultOrderinfo orderInfo = info.getOrderInfo();//交易明细详情
        YouzanTradesSoldGetResultPayinfo payInfo = info.getPayInfo();//交易支付信息结构体
        YouzanTradesSoldGetResultRemarkinfo remarkInfo = info.getRemarkInfo();//订单标记信息结构体
        YouzanTradesSoldGetResultSourceinfo sourceInfo = info.getSourceInfo();//订单来源
        YouzanTradesSoldGetResultChildinfo childInfo = info.getChildInfo();//交易送礼子单
        //复制商品信息
        if (CollectionUtils.isEmpty(info.getOrders())) {
            return list;
        }

        if (CollectionUtils.isEmpty(youZanConfig.getCustomBusinessList())) {
            return list;
        }
        //
        Map<String, CustomBusiness> customBusinessMap = youZanConfig.getCustomBusinessList()
                .stream().collect(Collectors
                        .toMap(a -> a.getCustomsCode() + "_" + a.getBusinessMode(), a -> a));

        /**复制订单明细*/
        List<OrderYouzanItem> youZanOrderItems = new ArrayList<>();

        for (YouzanTradesSoldGetResultOrders orders : info.getOrders()) {
            OrderYouzanItem orderItem = new OrderYouzanItem();
            BeanUtils.copyProperties(orders, orderItem);
            orderItem.setTotalFee(StringUtils.isNotEmpty(orders.getTotalFee()) ? new BigDecimal(orders.getTotalFee()) : null);
            orderItem.setTaxTotal(StringUtils.isNotEmpty(orders.getTaxTotal()) ? new BigDecimal(orders.getTaxTotal()) : null);
            orderItem.setDiscount(StringUtils.isNotEmpty(orders.getDiscount()) ? new BigDecimal(orders.getDiscount()) : null);
            orderItem.setDiscountPrice(StringUtils.isNotEmpty(orders.getDiscountPrice()) ? new BigDecimal(orders.getDiscountPrice()) : null);
            orderItem.setNum(new BigDecimal(orders.getNum()));
            orderItem.setPayment(StringUtils.isNotEmpty(orders.getPayment()) ? new BigDecimal(orders.getPayment()) : null);
            orderItem.setPrice(StringUtils.isNotEmpty(orders.getPrice()) ? new BigDecimal(orders.getPrice()) : null);
            orderItem.setFreight(StringUtils.isNotEmpty(orders.getFreight()) ? new BigDecimal(orders.getFreight()) : null);
            orderItem.setFenxiaoDiscountPrice(StringUtils.isNotEmpty(orders.getFenxiaoDiscountPrice()) ? new BigDecimal(orders.getFenxiaoDiscountPrice()) : null);
            orderItem.setFenxiaoFreight(StringUtils.isNotEmpty(orders.getFenxiaoFreight()) ? new BigDecimal(orders.getFenxiaoFreight()) : null);
            orderItem.setFenxiaoPayment(StringUtils.isNotEmpty(orders.getFenxiaoPayment()) ? new BigDecimal(orders.getFenxiaoPayment()) : null);
            orderItem.setFenxiaoPrice(StringUtils.isNotEmpty(orders.getFenxiaoPrice()) ? new BigDecimal(orders.getFenxiaoPrice()) : null);
            orderItem.setFenxiaoTaxTotal(StringUtils.isNotEmpty(orders.getFenxiaoTaxTotal()) ? new BigDecimal(orders.getFenxiaoTaxTotal()) : null);
            orderItem.setFenxiaoDiscount(StringUtils.isNotEmpty(orders.getFenxiaoDiscount()) ? new BigDecimal(orders.getFenxiaoDiscount()) : null);
            orderItem.setPointsPrice(StringUtils.isNotEmpty(orders.getPointsPrice()) ? new BigDecimal(orders.getPointsPrice()) : null);
            youZanOrderItems.add(orderItem);
        }
        //根据子订单号分组
        Map<String, List<OrderYouzanItem>> map = youZanOrderItems.stream()
                .filter(a -> (StringUtils.isNotBlank(a.getSubOrderNo()) && !a.getSubOrderNo()
                        .equals("null"))).collect(
                        Collectors.groupingBy(OrderYouzanItem::getSubOrderNo));

        for (Entry<String, List<OrderYouzanItem>> entry : map.entrySet()) {
            //过滤掉存在的订单
            if (orderYouzanService
                    .isExist(orderInfo.getTid(), entry.getKey(),OrderStatusEnum.SEND_SUCCESS.getValue(),OrderStatusEnum.KJB_FAILURE.getValue())) {
                continue;
            }
            OrderYouzanDto youZanOrder = new OrderYouzanDto();
            youZanOrder.setCustomsCode(dictService.getByCode(DictConstant.YOUZAN_CUSTOM_CODE)
                    .get(entry.getValue().get(0).getCustomsCode()));
            BusiModeEnum busiModeEnum = BusiModeEnum
                    .getYzEnum(entry.getValue().get(0).getCrossBorderTradeMode());
            youZanOrder.setOrderTradeMode(
                    busiModeEnum);//贸易模式，取第一个商品的贸易模式
            CustomBusiness customBusiness = customBusinessMap
                    .get(youZanOrder.getCustomsCode() + "_" + youZanOrder.getOrderTradeMode()
                            .getValue());
            //过滤掉业务模式跟关区不处理的订单（只处理关区跟业务模式配置了的订单）
            if (customBusiness == null) {
                continue;
            }
            youZanOrder.setCiqCode(customBusiness.getCiqCode());

            youZanOrder.setOrderYouzanItemList(entry.getValue());
            //复制地址信息
            BeanUtils.copyProperties(addressInfo, youZanOrder);
            //复制购买信息
            BeanUtils.copyProperties(buyerInfo, youZanOrder);
            //不纪录粉丝信息
            youZanOrder.setFansNickname("");
            //复制订单备注信息
            BeanUtils.copyProperties(remarkInfo, youZanOrder);
            //复制订单详细信息
            BeanUtils.copyProperties(orderInfo, youZanOrder);
            //复制订单标记信息
            OrderYouzanTags tags = new OrderYouzanTags();
            BeanUtils.copyProperties(orderInfo.getOrderTags(), tags);

            youZanOrder.setOrderYouzanTags(tags);
            //复制订单扩展字段信息
            OrderYouzanEx orderExtra = new OrderYouzanEx();
            BeanUtils.copyProperties(orderInfo.getOrderExtra(), orderExtra);
            //不记录购买人名字
            orderExtra.setBuyerName("");
            youZanOrder.setOrderYouzanEx(orderExtra);
            //复制多支付信息
            if (payInfo != null) {
                BeanUtils.copyProperties(payInfo, youZanOrder);
                youZanOrder.setPayment(StringUtils.isNotEmpty(payInfo.getPayment()) ? new BigDecimal(payInfo.getPayment()) : BigDecimal.ZERO);
                youZanOrder.setTotalFee(StringUtils.isNotEmpty(payInfo.getTotalFee()) ? new BigDecimal(payInfo.getTotalFee()) : BigDecimal.ZERO);
                youZanOrder.setPostFee(StringUtils.isNotEmpty(payInfo.getPostFee()) ? new BigDecimal(payInfo.getPostFee()) : BigDecimal.ZERO);
                youZanOrder.setOuterTransactions(payInfo.getOuterTransactions().stream()
                        .collect(Collectors.joining("/")));

                youZanOrder.setTransaction(payInfo.getTransaction().stream()
                        .collect(Collectors.joining("/")));
                List<OrderYouzanPayments> youZanPhasePayments = new ArrayList<>();
                for (YouzanTradesSoldGetResultPhasepayments phasepayments : payInfo
                        .getPhasePayments()) {
                    OrderYouzanPayments payments = new OrderYouzanPayments();
                    BeanUtils.copyProperties(phasepayments, payments);
                    payments.setRealPrice(new BigDecimal(phasepayments.getRealPrice()));
                    youZanPhasePayments.add(payments);
                }
                youZanOrder.setOrderYouzanPaymentsList(youZanPhasePayments);
            }
            //复制订单来源信息
            if (sourceInfo != null) {
                BeanUtils.copyProperties(sourceInfo, youZanOrder);
                youZanOrder.setPlatform(sourceInfo.getSource().getPlatform());
                youZanOrder.setWxEntrance(sourceInfo.getSource().getWxEntrance());
            }
            //复制送礼子单信息
            if (childInfo != null) {
                BeanUtils.copyProperties(childInfo, youZanOrder);
                List<OrderYouzanChild> youzanChildOrderList = new ArrayList<>();
                for (YouzanTradesSoldGetResultChildorders childorders : childInfo
                        .getChildOrders()) {
                    OrderYouzanChild youZanChildOrder = new OrderYouzanChild();
                    BeanUtils.copyProperties(childorders, youZanChildOrder);
                    youzanChildOrderList.add(youZanChildOrder);
                }
                youZanOrder.setOrderYouzanChildList(youzanChildOrderList);
            }
            list.add(youZanOrder);
        }
        return list;
    }

    /**
     * 保存订单信息
     */
    public void saveInformation(OrderYouzanDto youZanOrder, List<OrderYouzanItem> orderItems) {
        //加密存储隐私信息,先保存订单主体信息,获取返回自增主键
        youZanOrder.setBuyerPhone(AES256Util.encrypt(youZanOrder.getBuyerPhone()));
        youZanOrder.getOrderYouzanEx().setIdCardNumber(
                AES256Util.encrypt(youZanOrder.getOrderYouzanEx().getIdCardNumber()));
        youZanOrder.setUpdated(youZanOrder.getUpdateTime());
        youZanOrder.setUpdateTime(null);
        orderYouzanService.insert(youZanOrder);
        Long orderId = youZanOrder.getId();

        //保存商品数据
        for (OrderYouzanItem orderItem : orderItems) {
            orderItem.setOrderId(orderId);
            orderItem.setSendKjbFlag(YouZanOrderNewEnum.OLD);
            orderItem.setCreateBy(youZanOrder.getCreateBy());
            orderItem.setCreateTime(youZanOrder.getCreateTime());
            orderItem.setTenantId(youZanOrder.getTenantId());
        }
        orderYouzanItemService.batchInsert(orderItems);

        //保存订单多阶段支付信息
        List<OrderYouzanPayments> phasePayments = youZanOrder.getOrderYouzanPaymentsList();
        if (!CollectionUtils.isEmpty(phasePayments)) {
            for (OrderYouzanPayments phasePayment : phasePayments) {
                phasePayment.setOrderId(orderId);
                phasePayment.setCreateBy(youZanOrder.getCreateBy());
                phasePayment.setCreateTime(youZanOrder.getCreateTime());
            }
            orderYouzanPaymentsService.batchInsert(phasePayments);
        }

        //保存订单标记信息
        OrderYouzanTags orderTags = youZanOrder.getOrderYouzanTags();
        if (orderTags != null) {
            orderTags.setOrderId(orderId);
            orderYouzanTagsService.insert(orderTags);
        }

        //保存订单扩展信息
        OrderYouzanEx orderExtra = youZanOrder.getOrderYouzanEx();
        if (orderExtra != null) {
            orderExtra.setOrderId(orderId);
            orderYouzanExService.insert(orderExtra);
        }

        //保存订单送礼子单
        List<OrderYouzanChild> childOrders = youZanOrder.getOrderYouzanChildList();
        if (!CollectionUtils.isEmpty(childOrders)) {
            for (OrderYouzanChild childOrder : childOrders) {
                childOrder.setOrderId(orderId);
                childOrder.setCreateBy(youZanOrder.getCreateBy());
                childOrder.setCreateTime(youZanOrder.getCreateTime());
                childOrder.setTenantId(youZanOrder.getTenantId());
            }
            orderYouzanChildService.batchInsert(childOrders);
        }
    }

    /**
     * 根据订单号进行抓单
     */
    @Override
    @Transactional
    public BaseResponse getOrder(Store store, String tid) {
        YouZanConfig youZanConfig = JacksonUtils
                .readValue(store.getArguments(), YouZanConfig.class);
        if (youZanConfig == null) {
            throw new BusinessException("店铺未配置抓单信息");
        }
//        getToken(store, youZanConfig);
        YouzanTradesSoldGetParams params = new YouzanTradesSoldGetParams();
        params.setTid(tid);
        List<YouzanTradesSoldGetResultFullorderinfolist> list = youzanApiService
                .getOrder(params, store, youZanConfig, ExpCodeEnum.YOUZAN004);
        //BusinessAssert.assertNotEmpty(list, "未抓取到订单");
        if (CollectionUtils.isEmpty(list)) {
            return BaseResponse.responseFailure("未抓取到订单");
        }
        for (YouzanTradesSoldGetResultFullorderinfolist orderInfo : list) {
            OrderTemp orderTemp = new OrderTemp(JacksonUtils.toJSon(orderInfo));
            String orderNo = orderInfo.getFullOrderInfo().getOrderInfo()
                    .getTid();//主订单号
            YouzanTradesSoldGetResultOrders item = orderInfo.getFullOrderInfo()
                    .getOrders().get(0);
            String subOrderNo = item == null ? orderNo : item.getSubOrderNo();
            //插入临时表数据，并发送处理的mq
            orderTempService.insertAndSendMq(orderTemp, QueueEnum.OCP_YOUZAN_ORDER_PROCESS,
                    new BasicMessage(orderTemp.getId(), subOrderNo, store.getId().toString(),
                            ""));
        }
        return BaseResponse.responseSuccess("订单抓取成功");
    }


    /**
     * 抓取漏掉订单
     */
    @Override
    public void getMissOrder(CatchOrderConfig catchOrderConfig) {
        getOrder(catchOrderConfig, true);
    }

    /**
     * 推送有赞订单到ofc
     */
    @Override
    @Transactional
    public BaseResponse sendOrderOfc(OrderYouzanDto dto) {
        //有赞扩展字段
        OrderYouzanEx extra = orderYouzanExService.selectByOrderId(dto.getId());
        //有赞订单商品明细表
        List<OrderYouzanItem> orderItems = orderYouzanItemService
                .selectByOrderId(dto.getId(), YouZanOrderNewEnum.OLD);
        //有赞订单商品组套商品表
        /*List<OrderYouzanItem> orderItemsKjb = orderYouzanItemService
                .selectByOrderId(dto.getId(), YouZanOrderNewEnum.NEW);*/
        //有赞订单信息标记
        OrderYouzanTags tags = orderYouzanTagsService.selectByOrderId(dto.getId());
        //有赞订单多阶段支付信息
        /*List<OrderYouzanPayments> payments = orderYouzanPaymentsService
                .selectByOrderId(dto.getId());*/
        dto.setOrderYouzanEx(extra);
        dto.setOrderYouzanItemList(orderItems);
        //dto.setOrderItemKjb(orderItemsKjb);
        dto.setOrderYouzanTags(tags);
        //dto.setOrderYouzanPaymentsList(payments);
        dto.setIsPurchaseOrder(
                tags != null && tags.getIsPurchaseOrder() ? YesOrNoEnum.YES : YesOrNoEnum.NO);

        //解密订单数据
        dto.setBuyerPhone(AES256Util.decrypt(dto.getBuyerPhone()));
        extra.setIdCardNumber(AES256Util.decrypt(extra.getIdCardNumber()));
        //查询默认下发OCP配置,获取默认值配置.如果默认值配置不为空则将报文中的值替换成配置，根据币值系数转换币值
        TransferConfig transferConfig = transferConfigService
                .findByUnique(dto.getElectricCode(), dto.getEbpCode(),
                        LogisticsEnum.VL.getCode(), dto.getCustomsCode(),
                        dto.getOrderTradeMode().getValue());
        if (transferConfig == null) {
            orderYouzanService.updateOrderStatus(dto.getId(), OrderStatusEnum.SEND_FAILURE.getValue(),
                    "订单推送OFC未找到对应转发配置");
            return BaseResponse.responseFailure("订单推送OFC未找到对应转发配置");
        }
        //获取下发的配置
        TransferDefaultConfig defaultConfig = JacksonUtils.readValue(transferConfig.getConfiguration(), TransferDefaultConfig.class);
        //转换为订单请求
        OfcRequest req = YouzanDtoConverter.convertOfc(dto, defaultConfig);

        BaseResponse response = ofcApiService.sendOrder(req, dto.getSubOrderNo(), dto.getCode());

        OrderStatusEnum orderStatus = OrderStatusEnum.SEND_FAILURE;
        if (response.isSuccess()) {
            orderStatus = OrderStatusEnum.SEND_SUCCESS;
        }
        orderYouzanService
                .updateOrderStatus(dto.getId(), orderStatus.getValue(), response.getMessage());
        return response;
    }


    /**
     * 推送订单数据到跨境宝，处理
     * @param dto
     * @return
     */
    @Override
    @Transactional
    public BaseResponse sendOrderKjb(OrderYouzanDto dto) {
        OrderYouzanTags tags = orderYouzanTagsService.selectByOrderId(dto.getId());
        dto.setOrderYouzanTags(tags);
        //有赞订单商品明细表
        List<OrderYouzanItem> orderItems = orderYouzanItemService
                .selectByOrderId(dto.getId(), YouZanOrderNewEnum.OLD);

        //查询默认下发OCP配置,获取默认值配置.如果默认值配置不为空则将报文中的值替换成配置，根据币值系数转换币值
        TransferConfig transferConfig = transferConfigService
                .findByUnique(dto.getElectricCode(), dto.getEbpCode(),
                        LogisticsEnum.VL.getCode(), dto.getCustomsCode(),
                        dto.getOrderTradeMode().getValue());
        if (transferConfig == null) {
            throw new BusinessException("订单未找到对应转发配置!");
        }

        KjbRequest reqKjbDto = YouzanDtoConverter.convertKjb(dto, orderItems);

        BaseResponse<KjbResponse> response = kjbApiService
                .sendOrder(reqKjbDto, dto.getSubOrderNo(), dto.getCode());

        OrderStatusEnum orderStatus =
                response.isSuccess() ? OrderStatusEnum.KJB_SUCCESS : OrderStatusEnum.KJB_FAILURE;
        orderYouzanService.updateOrderStatus(dto.getId(), orderStatus.getValue(),
                response.getData().getNotes(), response.getData().getStatus().getValue());

        //如果没有成功，则直接返回
        if (!response.isSuccess()) {
            return response;
        }

        //更新转发系统
        orderYouzanService.updateForwardSystem(dto.getId(),transferConfig.getForwardSystem());
        //处理返回的数据
        List<OrderYouzanItem> newOrderItems = processKjbItem(dto, response.getData(), orderItems);
        if (!CollectionUtils.isEmpty(newOrderItems)) {
            orderYouzanItemService.batchInsert(newOrderItems);
        }
        QueueEnum queueEnum = QueueEnum.getQueueEnumByName("ocp.youzan.order.send."+transferConfig.getForwardSystem().getQueue());
        messageSender.send(queueEnum,new BasicMessage(dto.getId(),dto.getCode()));
        return response;
    }

    /**
     * 处理跨境宝返回的明细数据
     */
    private List<OrderYouzanItem> processKjbItem(OrderYouzanDto dto, KjbResponse kjbResponse,
            List<OrderYouzanItem> itemList) {
        List<OrderYouzanItem> youZanOrderItemList = new ArrayList<>();
        Boolean isp = dto.getOrderYouzanTags().getIsPurchaseOrder();

        //状态为不处理
        if (KjbSendStausEnum.NO_DEAL == kjbResponse.getStatus()) {
            for (OrderYouzanItem item : itemList) {
                item.setSendKjbFlag(YouZanOrderNewEnum.NEW);
                item.setId(null);
                if (isp) {
                    item.setDiscountPrice(item.getFenxiaoDiscountPrice());
                    item.setTotalFee(item.getFenxiaoDiscountPrice().multiply(item.getNum()));
                }
                youZanOrderItemList.add(item);
            }
        } else {//处理-成功
            for (KjbResponse.KjbResponseGoods item : kjbResponse.getGood_list()) {

                OrderYouzanItem youZanOrderItem = new OrderYouzanItem();
                youZanOrderItem.setOrderId(dto.getId());
                youZanOrderItem.setTitle(item.getGoods_name());
                youZanOrderItem.setOuterSkuId(item.getGoods_id());
                youZanOrderItem.setNum(new BigDecimal(item.getGoods_num()));
                youZanOrderItem.setTotalFee(item.getGoods_price().multiply(new BigDecimal(item.getGoods_num())));
                youZanOrderItem.setDiscountPrice(item.getGoods_price());
                youZanOrderItem.setGoodsUnit(item.getGoods_unit());
                youZanOrderItem.setSendKjbFlag(YouZanOrderNewEnum.NEW);
                youZanOrderItemList.add(youZanOrderItem);
            }
        }
        return youZanOrderItemList;
    }


    @Override
    @Transactional
    public BaseResponse sendOrderOp(OrderYouzanDto dto) {
        //有赞扩展字段
        OrderYouzanEx extra = orderYouzanExService.selectByOrderId(dto.getId());
        //有赞订单商品明细表
        List<OrderYouzanItem> orderItems = orderYouzanItemService
                .selectByOrderId(dto.getId(), YouZanOrderNewEnum.OLD);
        //有赞订单商品组套商品表
        List<OrderYouzanItem> orderItemsKjb = orderYouzanItemService
                .selectByOrderId(dto.getId(), YouZanOrderNewEnum.NEW);
        //有赞订单信息标记
        OrderYouzanTags tags = orderYouzanTagsService.selectByOrderId(dto.getId());

        dto.setOrderYouzanEx(extra);
        dto.setOrderItemKjb(orderItemsKjb);
        dto.setOrderYouzanItemList(orderItems);
        dto.setOrderYouzanTags(tags);
        //解密订单数据
        dto.setBuyerPhone(AES256Util.decrypt(dto.getBuyerPhone()));
        extra.setIdCardNumber(AES256Util.decrypt(extra.getIdCardNumber()));
        //查询默认下发OCP配置,获取默认值配置.如果默认值配置不为空则将报文中的值替换成配置，根据币值系数转换币值
        TransferConfig transferConfig = transferConfigService
                .findByUnique(dto.getElectricCode(), dto.getEbpCode(),
                        LogisticsEnum.VL.getCode(), dto.getCustomsCode(),
                        dto.getOrderTradeMode().getValue());
        if (transferConfig == null) {
            orderYouzanService.updateOrderStatus(dto.getId(), OrderStatusEnum.SEND_FAILURE.getValue(),
                    "订单推送OP未找到对应转发配置");
            return BaseResponse.responseFailure("订单推送OP未找到对应转发配置");
        }
        //获取下发的配置
        TransferDefaultConfig defaultConfig = JacksonUtils.readValue(transferConfig.getConfiguration(), TransferDefaultConfig.class);
        //转换为订单请求
        OpRequest req = YouzanDtoConverter.convertOp(dto, defaultConfig);

        BaseResponse response = opApiService.sendOrder(req, dto.getSubOrderNo(), dto.getCode());

        OrderStatusEnum orderStatus = OrderStatusEnum.SEND_FAILURE;
        if (response.isSuccess()) {
            orderStatus = OrderStatusEnum.SEND_SUCCESS;
        }
        orderYouzanService
                .updateOrderStatus(dto.getId(), orderStatus.getValue(), response.getMessage());
        return response;
    }

    @Override
    public BaseResponse sendOrderEsd(OrderYouzanDto dto) {
        //有赞扩展字段
        OrderYouzanEx extra = orderYouzanExService.selectByOrderId(dto.getId());
        //有赞订单商品组套商品表
        /*List<OrderYouzanItem> orderItemsKjb = orderYouzanItemService
                .selectByOrderId(dto.getId(), YouZanOrderNewEnum.NEW);*/
        //有赞订单商品明细表
        List<OrderYouzanItem> orderItems = orderYouzanItemService
                .selectByOrderId(dto.getId(), YouZanOrderNewEnum.OLD);
        //有赞订单信息标记
        OrderYouzanTags tags = orderYouzanTagsService.selectByOrderId(dto.getId());

        dto.setOrderYouzanEx(extra);
        //dto.setOrderItemKjb(orderItemsKjb);
        dto.setOrderYouzanItemList(orderItems);
        dto.setOrderYouzanTags(tags);
        //解密订单数据
        dto.setBuyerPhone(AES256Util.decrypt(dto.getBuyerPhone()));
        extra.setIdCardNumber(AES256Util.decrypt(extra.getIdCardNumber()));
        //查询默认下发OCP配置,获取默认值配置.如果默认值配置不为空则将报文中的值替换成配置，根据币值系数转换币值
        TransferConfig transferConfig = transferConfigService
                .findByUnique(dto.getElectricCode(), dto.getEbpCode(),
                        LogisticsEnum.VL.getCode(), dto.getCustomsCode(),
                        dto.getOrderTradeMode().getValue());
        if (transferConfig == null) {
            orderYouzanService.updateOrderStatus(dto.getId(), OrderStatusEnum.SEND_FAILURE.getValue(),
                    "订单推送ESD未找到对应转发配置");
            return BaseResponse.responseFailure("订单推送ESD未找到对应转发配置");
        }
        //获取下发的配置
        TransferDefaultConfig defaultConfig = JacksonUtils.readValue(transferConfig.getConfiguration(), TransferDefaultConfig.class);
        EsdRequest req = YouzanDtoConverter.convertEsd(dto, defaultConfig);

        //组装报文
        BaseResponse response = esdApiService.sendOrder(req, dto.getSubOrderNo(), dto.getCode(),dto.getAppId(),dto.getAppKey());

        OrderStatusEnum orderStatus = OrderStatusEnum.SEND_FAILURE;
        if (response.isSuccess()) {
            orderStatus = OrderStatusEnum.SEND_SUCCESS;
        }
        orderYouzanService
                .updateOrderStatus(dto.getId(), orderStatus.getValue(), response.getMessage());
        return response;
    }

}


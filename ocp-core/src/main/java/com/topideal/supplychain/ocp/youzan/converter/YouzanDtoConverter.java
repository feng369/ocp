package com.topideal.supplychain.ocp.youzan.converter;

import com.google.common.collect.Lists;
import com.topideal.supplychain.ocp.config.dto.TransferDefaultConfig;
import com.topideal.supplychain.ocp.enums.BusiModeEnum;
import com.topideal.supplychain.ocp.enums.EsdOrderSourceEnum;
import com.topideal.supplychain.ocp.esd.dto.*;
import com.topideal.supplychain.ocp.kjb.dto.KjbReqGoods;
import com.topideal.supplychain.ocp.kjb.dto.KjbRequest;
import com.topideal.supplychain.ocp.ofc.dto.OfcGoodsReqDto;
import com.topideal.supplychain.ocp.ofc.dto.OfcOrderReqDto;
import com.topideal.supplychain.ocp.ofc.dto.OfcOrderReqDto.Builder;
import com.topideal.supplychain.ocp.ofc.dto.OfcRequest;
import com.topideal.supplychain.ocp.op.dto.OpCommonFieldDto;
import com.topideal.supplychain.ocp.op.dto.OpGoodsItemDto;
import com.topideal.supplychain.ocp.op.dto.OpRecipientDto;
import com.topideal.supplychain.ocp.op.dto.OpRequest;
import com.topideal.supplychain.ocp.order.dto.OrderYouzanDto;
import com.topideal.supplychain.ocp.order.model.OrderYouzanEx;
import com.topideal.supplychain.ocp.order.model.OrderYouzanItem;
import com.topideal.supplychain.ocp.order.model.OrderYouzanTags;
import com.topideal.supplychain.util.JacksonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DtoConverter
 * @TODO dto转换器
 * @Author zhangzhihao
 * @DATE 2019/12/3 17:38
 * @Version 1.0
 **/

public class YouzanDtoConverter {


    public static OfcRequest convertOfc(OrderYouzanDto dto, TransferDefaultConfig transferConfig) {
        OrderYouzanTags tags = dto.getOrderYouzanTags();
        //List<OrderYouzanItem> orderItemKjbs = dto.getOrderItemKjb();
        List<OrderYouzanItem> orderItems = dto.getOrderYouzanItemList();
        OrderYouzanEx orderYouzanEx = dto.getOrderYouzanEx();
        /**计算商品价格*/
        BigDecimal goodValue = BigDecimal.ZERO;
        /**计算折扣金额**/
        BigDecimal amount = BigDecimal.ZERO;
        /***计算总的税金***/
        BigDecimal taxTotal = BigDecimal.ZERO;
        /****计算实际支付金额**/
        BigDecimal acturalPaid = BigDecimal.ZERO;
        /**计算运费**/
        BigDecimal freight = BigDecimal.ZERO;

        boolean isPurchaseOrder =
                tags != null && tags.getIsPurchaseOrder() != null && tags.getIsPurchaseOrder();

        if (isPurchaseOrder) {
            for (OrderYouzanItem item : orderItems) {
                BigDecimal totalDiscountPrice = item.getFenxiaoDiscountPrice()
                        .multiply(item.getNum());
                goodValue = goodValue.add(totalDiscountPrice);

                amount = amount.add(item.getFenxiaoDiscount());

                taxTotal = taxTotal.add(item.getFenxiaoTaxTotal());

                acturalPaid = acturalPaid.add(item.getFenxiaoPayment())
                        .add(item.getFenxiaoFreight());

                freight = freight.add(item.getFenxiaoFreight());
            }
        } else {
            BigDecimal td;
            for (OrderYouzanItem item : orderItems) {
                td = item.getDiscountPrice().multiply(item.getNum());
                goodValue = goodValue.add(td);

                amount = amount.add(item.getDiscount());

                taxTotal = taxTotal.add(item.getTaxTotal());

                acturalPaid = acturalPaid.add(item.getPayment()).add(item.getFreight());

                freight = freight.add(item.getFreight());
            }
        }

        Builder build = OfcOrderReqDto.newBuilder()
                .key(dto.getStoreKey())
                .orderNo(dto.getSubOrderNo())
                .declTime(dto.getCreated())
                .buyerRegNo(dto.getFansId() != null ? dto.getFansId().toString() : null)
                .buyerIdType("1")
                .buyerIdNumber(orderYouzanEx != null ? orderYouzanEx.getIdCardNumber() : null)
                .buyerName(orderYouzanEx != null ? orderYouzanEx.getIdCardName() : null)
                .buyerTelephone(dto.getReceiverTel())
                .receiveNo(orderYouzanEx != null ? orderYouzanEx.getIdCardNumber() : null)
                .consignee(dto.getReceiverName())
                .consigneeTelephone(dto.getReceiverTel())
                .pod(null)
                .consigneeAddress(dto.getDeliveryAddress())
                .country("中国")
                .province(dto.getDeliveryProvince())
                .city(dto.getDeliveryCity())
                .district(dto.getDeliveryDistrict())
                .postCode(dto.getDeliveryPostalCode())
                .goodsValue(goodValue)
                .freight(freight)
                .discount(amount)
                .taxTotal(taxTotal)
                .taxCurr("CNY")
                .acturalPaid(acturalPaid)
                .payCurr("CNY")
                .insuredFee(BigDecimal.ZERO)
                .insurCurr("CNY")
                .freightCurr("CNY")
                .fCode("CNY")
                .ebpCode(dto.getEbpCode())
                .ebpName(null)
                .ebcCode(dto.getElectricCode())
                .ebcName(null)
                .userId(dto.getDno())
                .userName(null)
                .logisticsCode(null)
                .logisticsName(null)
                .logisticsNo(null)
                .logisticsNote(null)
                .payCode(null)
                .payName(null)
                .payTransactionId(dto.getOuterTransactions())
                .payTime(dto.getPayTime())
                .payNote(null)
                .customsCode(dto.getCustomsCode())
                .ciqbCode(StringUtils.isEmpty(dto.getCiqCode()) ? "000069" : dto.getCiqCode())
                .ieFlag("I")
                .busiMode(BusiModeEnum.BBC.getValue())
                .orderType(StringUtils.isNotEmpty(transferConfig.getOrderType()) ? Long
                        .valueOf(transferConfig.getOrderType()) : 1L)
                .orderStatus("S")
                .customsType(1L)
                .statisticsFlag(null)
                .trafMode(null)
                .trafNo(null)
                .voyageNo(null)
                .billNo(null)
                .shippernCode(null)
                .wrapType(null)
                .shipDate(null)
                .inputDate(null)
                .shipperName(null)
                .shipperAddress(null)
                .shipperPhone(null)
                .packNo(1L)
                .grossWeight(null)
                .netWeight(null)
                .fromEplat(null)
                .printHeader(null)
                .changeFlag(null)
                .traceFlag(null)
                .reDeclare(null)
                .vmiFlag(null)
                .ownerFlag(null)
                .isStoreStrategy(null)
                .orderCode(dto.getTid())
                .storeCode(transferConfig.getWarehouseCode())
                .platformOrderType(null)
                .orderSource(StringUtils.isNotEmpty(transferConfig.getOrderSource()) ? Long
                        .valueOf(transferConfig.getOrderSource()) : 301L)
                .ownerUserId(null)
                .ownerUserName(null)
                .lpCode(dto.getTransaction())
                .deliveryTimePreference(null)
                .isPackage(null)
                .transportday(null)
                .note(null)
                .tradeMode("1")
                .commonField(null)
                .orderBatchNo(null)
                .businessType(isPurchaseOrder ? "FXB2B" : null)
                .cBCode(null)
                .declareFormNo(null)
                .udf1(isPurchaseOrder ? orderYouzanEx.getFxKdtId()
                        : null)
                .udf2(isPurchaseOrder ? dto.getOldTid() : null);

        if (transferConfig != null && StringUtils.isNotEmpty(transferConfig.getLogisticsCode())) {
            build.logisticsCode(transferConfig.getLogisticsCode());
        }
        if (transferConfig != null && StringUtils.isNotEmpty(transferConfig.getMerchantCode())) {
            build.ebcCode(transferConfig.getMerchantCode());
        }
        if (transferConfig != null && StringUtils.isNotEmpty(transferConfig.getPlatformCode())) {
            build.ebpCode(transferConfig.getPlatformCode());
        }
        if (transferConfig != null && StringUtils.isNotEmpty(transferConfig.getFromEplat())) {
            build.fromEplat(Long.valueOf(transferConfig.getFromEplat()));
        }

        OfcOrderReqDto ofcOrderReqDto = build.build();

        List<OfcGoodsReqDto> list = Lists.newArrayList();
        for (int i = 0; i < orderItems.size(); i++) {
            OrderYouzanItem orderYouzanItem = orderItems.get(i);

            OfcGoodsReqDto ofcGoodsReqDto = OfcGoodsReqDto.newBuilder()
                    .gnum(String.valueOf(i + 1))
                    .goodsId(orderYouzanItem.getOuterSkuId())
                    .goodsName(orderYouzanItem.getTitle())
                    .barCode(null)
                    .currency(null)
                    .qty(orderYouzanItem.getNum())
                    .qtyUnit(null)
                    .price(isPurchaseOrder ? orderYouzanItem.getFenxiaoDiscountPrice()
                            : orderYouzanItem.getDiscountPrice())
                    .totalPrice(isPurchaseOrder ? (orderYouzanItem.getFenxiaoDiscountPrice()
                            .multiply(orderYouzanItem.getNum()))
                            : (orderYouzanItem.getDiscountPrice().multiply(orderYouzanItem.getNum())))
                    .batchNo(null)
                    .storeStrategyId(null)
                    .productionTime(null)
                    .expDate(null)
                    .ebcCode(null)
                    .virtualOwner(null)
                    .inventoryType(null)
                    .itemVersion(null)
                    .note(null)
                    .hsCode(null)
                    .ciqGoodsNo(null)
                    .assemCountry(null)
                    .ciqAssemCountry(null)
                    .spec(null)
                    .brand(null)
                    .packageType(null)
                    .qty1(null)
                    .unit1(null)
                    .gQty1(null)
                    .qty2(null)
                    .gQty2(null)
                    .unit2(null)
                    .grossWeight(null)
                    .netWeight(null)
                    .isGoodsRecord(null)
                    .tradeUrl(null)
                    .billNo(null)
                    .declareCode(null)
                    .ciqDeclareCode(null)
                    .ciqInboundDeclareCode(null).build();
            list.add(ofcGoodsReqDto);
        }
        return new OfcRequest(ofcOrderReqDto, list);
    }

    //组装报文,下发到跨境宝
    public static KjbRequest convertKjb(OrderYouzanDto dto,
            List<OrderYouzanItem> youZanOrderItemList) {

        KjbRequest.Builder builder = KjbRequest.newBuilder();
        if (CollectionUtils.isEmpty(youZanOrderItemList)) {
            return builder.build();
        }

        builder.cbepcomCode(dto.getElectricCode())
                .order_id(dto.getSubOrderNo());
        List<KjbReqGoods> reqGoodsList = new ArrayList<>();
        //判断是否分销
        Boolean isp = dto.getOrderYouzanTags().getIsPurchaseOrder();

        BigDecimal fee = BigDecimal.ZERO;
        for (OrderYouzanItem item : youZanOrderItemList) {
            if (isp) {
                fee = fee.add(
                        item.getFenxiaoDiscountPrice())
                        .multiply(item.getNum());
            } else {
                fee = fee.add(item.getDiscountPrice().multiply(item.getNum()));
            }

            KjbReqGoods kjbReqGoods = KjbReqGoods.newBuilder().goods_id(item.getOuterSkuId())
                    .goods_name(item.getTitle())
                    .goods_num(item.getNum() == null ? "" : item.getNum().toString())
                    .goods_price(isp ? item.getFenxiaoDiscountPrice() : item.getDiscountPrice())
                    .build();
            reqGoodsList.add(kjbReqGoods);
        }
        return builder.good_list(reqGoodsList).payment_goods(fee).build();
    }

    /**
     * 转为op请求参数
     */
    public static OpRequest convertOp(OrderYouzanDto dto, TransferDefaultConfig transferConfig) {

        List<OrderYouzanItem> orderItemKjb = dto.getOrderItemKjb();
        List<OrderYouzanItem> orderYouzanItems = dto.getOrderYouzanItemList();
        OrderYouzanTags orderTags = dto.getOrderYouzanTags();
        OrderYouzanEx orderYouzanEx = dto.getOrderYouzanEx();
        boolean isPurchaseOrder =
                orderTags != null && orderTags.getIsPurchaseOrder() != null && orderTags
                        .getIsPurchaseOrder();

        String transaction = dto.getTransaction() == null ? "" : dto.getTransaction();
        if (isPurchaseOrder) {
            transaction = orderYouzanEx != null ? orderYouzanEx.getFxInnerTransactionNo() : null;
        }

        BigDecimal freight = BigDecimal.ZERO;
        BigDecimal taxTotal = BigDecimal.ZERO;
        BigDecimal discount = BigDecimal.ZERO;
        BigDecimal amount = BigDecimal.ZERO;
        List<OpGoodsItemDto> goodList = Lists.newArrayList();

        if (isPurchaseOrder) {
            for (OrderYouzanItem item : orderYouzanItems) {

                freight = freight.add(item.getFenxiaoFreight());
                taxTotal = taxTotal
                        .add(item.getFenxiaoTaxTotal());
                discount = discount.add(item.getFenxiaoDiscount());
                amount = amount
                        .add(item.getFenxiaoPayment().add(item.getFenxiaoFreight()));

            }
        } else {
            for (OrderYouzanItem item : orderYouzanItems) {
                freight = freight.add(item.getFreight());
                taxTotal = taxTotal.add(item.getTaxTotal());
                discount = discount.add(item.getDiscount());
                amount = amount.add(item.getPayment().add(item.getFreight()));
            }
        }

        for (int i = 0; i < orderItemKjb.size(); i++) {
            OrderYouzanItem item = orderItemKjb.get(i);
            OpGoodsItemDto itemDto = OpGoodsItemDto.newBuilder()
                    .gnum(String.valueOf(i + 1))
                    .goodId(item.getOuterSkuId())//商品货号
                    .amount(item.getNum().intValue())//数量
                    .price(item.getDiscountPrice())//售价（实际售价）
                    .goodPrice(null)//商品售价（展示售价）
                    .copGName(null)//商品名称
                    .hsCode(null)//海关编码
                    .codeTs(null)//行邮税号
                    .decTotal(null)//商品总价
                    .custGoodsNo(null)//商品海关备案号
                    .ciqGoodsNo(null)//国检商品备案号
                    .batchNo(null)//商品批次号
                    .assemCountry(null)//原厂国
                    .qtyUnit(null)//法定计量单位
                    .spec(null)//商品规格型号
                    .storeStrategyId(null)//指定库存策略号
                    .productionTime(null)//生产日期
                    .expDate(null)//到期日期
                    .ownerCode(null)//货主
                    .brand(null)//品牌
                    .packageType(null)//包装
                    .qty1(null)//第一法定单位数量
                    .unit1(null)//第一法定单位
                    .qty2(null)//第二法定单位数量
                    .unit2(null)//第二法定单位
                    .ggrossWt(null)//商品毛重
                    .qty1(null).build();//第一法定单位数量
            goodList.add(itemDto);
        }

        OpCommonFieldDto commonFieldDto = OpCommonFieldDto.newBuilder()
                .transaction(transaction)
                .tid(dto.getOldTid())
                .isfx(isPurchaseOrder ? 1 : 0)
                .fx_kdt_id(isPurchaseOrder ? orderYouzanEx != null ? orderYouzanEx.getFxKdtId() : ""
                        : "")
                .fx_order_no(
                        isPurchaseOrder ? orderYouzanEx != null ? orderYouzanEx.getFxOrderNo() : ""
                                : "")
                .platformNo(null).build();//平台物流商编码

        OpRecipientDto recipientDto = OpRecipientDto.newBuilder()
                .name(dto.getReceiverName())//收货人姓名
                .receiveType(1)//证件类型
                .receiveNo(orderYouzanEx != null ? orderYouzanEx.getIdCardNumber() : null)//收件人证件号
                .mobilePhone(dto.getReceiverTel())//手机号码
                .phone(null)//座机
                .country("中国")//国家
                .province(dto.getDeliveryProvince())//省份
                .city(dto.getDeliveryCity())//市
                .district(dto.getDeliveryDistrict())//区/县
                .address(dto.getDeliveryAddress())//地址
                .postCode(null)//邮编
                .totalFavourable(null)//订单优惠金额
                .sender(null)//送礼人
                .receiver(null)//收礼人
                .congratulations(null)//祝贺语
                .transportDay(null)//配送时间
                .recipientProvincesCode(null).build();//收货人省市代码

        return OpRequest.newBuilder()
                .orderId(dto.getSubOrderNo())//企业订单编号
                .orderDate(dto.getCreated())//订单生成时间
                .packingMaterial(null)//包材编码
                .warehouseId(null)//仓库ID，
                .tpl(transferConfig.getLogisticsCode())//第三方物流商编
                .orderType(1)//是否自运营订单
                .orderStatus("S")//订单状态
                .customsType(1)//海关类型
                .electricCode(
                        StringUtils.isNotEmpty(transferConfig.getMerchantCode()) ? transferConfig
                                .getMerchantCode() : dto.getElectricCode())//电商企业编码
                .cbepcomCode(
                        StringUtils.isNotEmpty(transferConfig.getPlatformCode()) ? transferConfig
                                .getPlatformCode() : dto.getEbpCode())//电商平台编码
                .busiMode(Integer.parseInt(BusiModeEnum.BBC.getValue()))//进口模式
                .customsCode(dto.getCustomsCode())//申报关区
                .ciqbCode(StringUtils.isEmpty(dto.getCiqCode()) ? "000069" : dto.getCiqCode())//申报国检
                .stationbCode(null)//申报场站
                .deliveryCode(null)//运单号
                .notes(dto.getDno())//备注 ESDOCP-357 有赞大连推OP订单信息新增下发字段  由于dno里面存的是店铺编码，现在需求是将店铺编码赋值给notes，传给op
                .freightFcy(freight)//运费
                .freightFcode("CNY")//运费币制
                .insuredFee(BigDecimal.ZERO)//保费
                .insurCurr(null)//保费币制
                .insurMark(null)//(预留字段)
                .taxFcy(taxTotal)//税费
                .taxFcode("CNY")//税费币种
                .otherRate(BigDecimal.ZERO)//杂费
                .otherCurr(null)//杂费币制
                .otherMark(null)//(预留字段)
                .otherPayment(BigDecimal.ZERO)//抵付金额
                .otherPaymentCurr(null)//抵付币制
                .fCode("CNY")//货款币制
                .discount(discount)//优惠减免金额
                .buyerName(dto.getOrderYouzanEx() != null ? dto.getOrderYouzanEx().getIdCardName()
                        : null)//订购人姓名
                .buyerIdType(1)//订购人证件类型
                .buyerIdNumber(
                        dto.getOrderYouzanEx() != null ? dto.getOrderYouzanEx().getIdCardNumber()
                                : null)//订购人证件号码
                .buyerTelephone(dto.getReceiverTel())//订购人电话
                .buyerRegNo(dto.getReceiverTel())//订购人注册号
                .grossWeight(null)//毛重（公斤）
                .netWeight(null)//净重（公斤）
                .bakbCode(null)//商品备案地国检
                .ordExcStatus(null)//执行状态
                .forSellComp(null)//国外卖方
                .forSellCompName(null)//国外卖方名称
                .tradeUnitCode(null)//外贸经营单位码
                .tradeUnitName(null)//外贸经营单位名称
                .shippernCode(null)//发货人所在国
                .shipDate(null)//发货日期
                .inputDate(null)//录入日期
                .logNots(null)//物流信息备注
                .blno(null)//提单号
                .trans(null)//运输方式
                .transNo(null)//运输工具名称
                .changeFlag(null)//换单标志
                .tradeMode(null)//贸易模式
                .shipperName(null)//发货人名称
                .shipperAddress(null)//发货人地址
                .shipperPhone(null)//发货人电话
                .agentCode(null)//报关企业代码
                .agentName(null)//报关企业名称
                .payNo(null)//支付流水号
                .payPcomName(null)//支付企业名称
                .payCopNo(null)//支付企业编码
                .opType(null)//申报类型
                .payType(null)//交易支付类型
                .payStatus(null)//支付交易状态
                .payorName(null)//付款人
                .activePayComp(null)//实际支付企业
                .acturalPaid(amount)//实际支付金额
                .payCurr(null)//支付币制
                .payNots(null)//支付信息备注
                .payDate(null)//支付日期
                .fromEplat(null)//来源第e仓
                .printHeader(null)//打印抬头
                .commonField(JacksonUtils.toJSon(commonFieldDto))//通用字段
                .isStoreStrategy(null)//是否校验库存策略
                .vmiFlag(null)//是否VMI模式
                .ownerFlag(null)//是否一单多业主
                .cutMode(null)//征免性质分类
                .transMode(null)//成交方式
                .packNo(null)//件数
                .wrapType(null)//包装种类
                .sendCity(null)//发件人城市
                .totalValue(null)//货值
                .goodsInfo(null)//主要货物名称
                .orderBatchNo(null)//发货订单批次
                .reDeclare(null)//重报标识
                .ebptpl(null)
                .recipient(recipientDto)
                .goodList(goodList).build();//平台物流商编码

    }

    /**
     * 转换为esd请求参数
     */
    public static EsdRequest convertEsd(OrderYouzanDto dto, TransferDefaultConfig defaultConfig) {
        //List<OrderYouzanItem> orderItemKjb = dto.getOrderItemKjb();
        List<OrderYouzanItem> orderYouzanItems = dto.getOrderYouzanItemList();
        OrderYouzanTags orderTags = dto.getOrderYouzanTags();
        OrderYouzanEx orderYouzanEx = dto.getOrderYouzanEx();
        boolean isPurchaseOrder =
                orderTags != null && orderTags.getIsPurchaseOrder() != null && orderTags
                        .getIsPurchaseOrder();

        String dno = dto.getDno();
        String tid = dto.getTid();
        String paymentTransaction = dto.getTransaction();

        if (isPurchaseOrder) {
            dno = orderYouzanEx.getFxKdtId();
            tid = orderYouzanEx.getFxOrderNo();
            paymentTransaction = orderYouzanEx.getFxInnerTransactionNo();
        }

        EsdSender sender = EsdSender.newBuilder()
                .name("Ison YI").phone("852-24724606").country("香港").city("新界")
                .address("香港新界元朗流浮山路D.D.129Lot no.2963").build();

        EsdReceiver receiver = EsdReceiver.newBuilder()
                .name(dto.getReceiverName())
                .mobile(dto.getReceiverTel())
                .identity_no(orderYouzanEx.getIdCardNumber())
                .country("中国")
                .province(dto.getDeliveryProvince())
                .city(dto.getDeliveryCity())
                .county(dto.getDeliveryDistrict())
                .address(dto.getDeliveryAddress())
                .zip_code(dto.getDeliveryPostalCode()).build();

        EsdBuyer buyer = EsdBuyer.newBuilder()
                .name(orderYouzanEx.getIdCardName())
                .phone(dto.getReceiverTel())
                .mobile(dto.getFansId() == null ? null : dto.getFansId().toString())
                .id_type("1")
                .identity_no(orderYouzanEx.getIdCardNumber()).build();

        List<EsdServiceType> serviceTypesList = new ArrayList<>();
        EsdServiceType serviceType = EsdServiceType.newBuilder().service_type(1).build();
        serviceTypesList.add(serviceType);

        List<EsdGood> itemList = new ArrayList<>();
        for (int i = 0; i < orderYouzanItems.size(); i++) {
            OrderYouzanItem item = orderYouzanItems.get(i);
            EsdGood good = EsdGood.newBuilder()
                    .index(String.valueOf(i + 1))
                    .item(item.getOuterSkuId())
                    .is_presente(2)
                    .item_name(item.getTitle())
                    .item_quantity(Integer.valueOf(item.getNum().toString()))
                    .price_declare(isPurchaseOrder? item.getFenxiaoDiscountPrice() : item.getDiscountPrice())
                    .price_code("CNY")
                    .item_tax(item.getTaxTotal() != null ? item.getTaxTotal().toString() : "0")
                    .build();
            itemList.add(good);
        }

        BigDecimal totalFeight = BigDecimal.ZERO;
        BigDecimal taxTotal = BigDecimal.ZERO;
        BigDecimal discount = BigDecimal.ZERO;
        BigDecimal amount = BigDecimal.ZERO;

        for (OrderYouzanItem item : orderYouzanItems) {

            if (isPurchaseOrder) {
                discount = discount.add(item.getFenxiaoDiscount());
                taxTotal = taxTotal.add(item.getFenxiaoTaxTotal());
                totalFeight = totalFeight
                        .add(item.getFenxiaoFreight() != null ? item.getFenxiaoFreight()
                                : BigDecimal.ZERO);
                amount = amount.add(
                        item.getFenxiaoPayment() != null ? item.getFenxiaoPayment()
                                .add(item.getFenxiaoFreight() != null ? item.getFenxiaoFreight()
                                        : BigDecimal.ZERO) : BigDecimal.ZERO
                );
            } else {
                discount = discount
                        .add(item.getDiscount() != null ? item.getDiscount() : BigDecimal.ZERO);
                taxTotal = taxTotal
                        .add(item.getTaxTotal() != null ? item.getTaxTotal() : BigDecimal.ZERO);
                totalFeight = totalFeight
                        .add(item.getFreight() != null ? item.getFreight() : BigDecimal.ZERO);
                amount = amount.add(item.getPayment() != null ? item.getPayment()
                        .add(item.getFreight() != null ? item.getFreight() : BigDecimal.ZERO)
                        : BigDecimal.ZERO
                );
            }

        }

        return EsdRequest.newBuilder()
                .source(EsdOrderSourceEnum.YZ_ORDER.getValue())
                .dno(dno)
                .order_create_time(dto.getCreated())
                .storehouse_id(dto.getStorehouseId())
                .trade_no(dto.getSubOrderNo())
                .tid(tid)
                .payment_transaction(paymentTransaction)
                .declare_scheme_sid("GZ-HP-BC")
                .total_code("CNY")
                .discount_fee(discount)
                .discount_code("CNY")
                .platform(defaultConfig != null && StringUtils
                        .isNotEmpty(defaultConfig.getPlatformCode()) ? defaultConfig.getPlatformCode()
                        : dto.getEbpCode())
                .is_trace_source(2)
                .totai_taxes_pay(amount)
                .totai_pay_code("CNY")
                .is_tran("2")
                .destion_code("HP01")
                .totai_taxes_reference(taxTotal)
                .total_freight(totalFeight)
                .totai_code("CNY")
                .premium_fee(BigDecimal.ZERO)
                .premium_code("CNY")
                .buyer(buyer)
                .receiver(receiver)
                .sender(sender)
                .serciveList(serviceTypesList)
                .itemList(itemList).build();
    }
}










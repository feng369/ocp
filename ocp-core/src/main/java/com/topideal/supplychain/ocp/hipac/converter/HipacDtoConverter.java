package com.topideal.supplychain.ocp.hipac.converter;

import com.google.common.collect.Lists;
import com.topideal.supplychain.ocp.config.dto.TransferDefaultConfig;
import com.topideal.supplychain.ocp.enums.BusiModeEnum;
import com.topideal.supplychain.ocp.ofc.dto.OfcGoodsReqDto;
import com.topideal.supplychain.ocp.ofc.dto.OfcOrderReqDto;
import com.topideal.supplychain.ocp.ofc.dto.OfcRequest;
import com.topideal.supplychain.ocp.op.dto.OpGoodsItemDto;
import com.topideal.supplychain.ocp.op.dto.OpRecipientDto;
import com.topideal.supplychain.ocp.op.dto.OpRequest;
import com.topideal.supplychain.ocp.order.model.OrderHipac;
import com.topideal.supplychain.ocp.order.model.OrderHipacItem;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * 海拍客订单转报文转换器
 *
 * @author xuxiaoyan
 * @date 2019-12-18 14:32
 */
public class HipacDtoConverter {

    /**
     * 订单转OP参数
     * @param orderHipac
     * @param transferConfig
     * @return
     */
    public static OpRequest convertOp(OrderHipac orderHipac, TransferDefaultConfig transferConfig) {
        List<OrderHipacItem> itemList = orderHipac.getOrderItemList();
        List<OpGoodsItemDto> goodList = Lists.newArrayList();
        // 收货人信息
        OpRecipientDto recipientDto = OpRecipientDto.newBuilder()
                .name(orderHipac.getCustName())//收货人姓名
                .receiveType(1)//证件类型
                .receiveNo(orderHipac.getCustIdNum())//收件人证件号
                .mobilePhone(orderHipac.getCustPhone())//手机号码
                .phone(null)//座机
                .country("中国")//国家
                .province(orderHipac.getCustProvice())//省份
                .city(orderHipac.getCustCity())//市
                .district(orderHipac.getCustArea())//区/县
                .address(orderHipac.getCustAddress())//地址
                .postCode(null)//邮编
                .totalFavourable(orderHipac.getDiscount())//订单优惠金额
                .sender(null)//送礼人
                .receiver(null)//收礼人
                .congratulations(null)//祝贺语
                .transportDay(null)//配送时间
                .recipientProvincesCode(null).build();//收货人省市代码

        // 商品信息
        for (int i = 0; i < itemList.size(); i++) {
            OrderHipacItem item = itemList.get(i);
            OpGoodsItemDto itemDto = OpGoodsItemDto.newBuilder()
                    .gnum(String.valueOf(i + 1))
                    .goodId(item.getItemSupplyNo())//商品货号
                    .amount(item.getItemQuantity().intValue())//数量
                    .price(item.getItemPrice())//售价（实际售价）
                    .goodPrice(null)//商品售价（展示售价）
                    .copGName(item.getItemName())//商品名称
                    .hsCode(null)//海关编码
                    .codeTs(null)//行邮税号
                    .decTotal(item.getItemTotal())//商品总价
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

        return OpRequest.newBuilder()
                .orderId(orderHipac.getOrderNum())//企业订单编号
                .orderDate(orderHipac.getOrderDate())//订单生成时间
                .packingMaterial(null)//包材编码
                .warehouseId(null)//仓库ID，
                .tpl(StringUtils.isNotEmpty(transferConfig.getLogisticsCode()) ? transferConfig
                        .getLogisticsCode() : orderHipac.getSupplierSenderID())//第三方物流商编
                .orderType(1)//是否自运营订单
                .orderStatus("S")//订单状态
                .customsType(1)//海关类型
                .electricCode(
                        StringUtils.isNotEmpty(transferConfig.getMerchantCode()) ? transferConfig
                                .getMerchantCode() : orderHipac.getSupplierSenderID())//电商企业编码
                .cbepcomCode(
                        StringUtils.isNotEmpty(transferConfig.getPlatformCode()) ? transferConfig
                                .getPlatformCode() : orderHipac.getCbePcomCode())//电商平台编码
                .busiMode(Integer.parseInt(BusiModeEnum.BBC.getValue()))//进口模式
                .customsCode(orderHipac.getCustomsCode().getValue())//申报关区
                .ciqbCode(StringUtils.isEmpty(orderHipac.getCiqbCode()) ? "000069" : orderHipac.getCiqbCode())//申报国检
                .stationbCode(null)//申报场站
                .deliveryCode(orderHipac.getDeliveryCode())//运单号
                .notes(null)//备注
                .freightFcy(orderHipac.getLogisticsAmount())//运费
                .freightFcode("CNY")//运费币制
                .insuredFee(BigDecimal.ZERO)//保费
                .insurCurr(null)//保费币制
                .insurMark(null)//(预留字段)
                .taxFcy(orderHipac.getTotalTaxAmount())//税费
                .taxFcode("CNY")//税费币种
                .otherRate(BigDecimal.ZERO)//杂费
                .otherCurr(null)//杂费币制
                .otherMark(null)//(预留字段)
                .otherPayment(BigDecimal.ZERO)//抵付金额
                .otherPaymentCurr(null)//抵付币制
                .fCode("CNY")//货款币制
                .discount(null == orderHipac.getDiscount()? BigDecimal.ZERO : orderHipac.getDiscount())//优惠减免金额
                .buyerName(orderHipac.getCustName())//订购人姓名
                .buyerIdType(1)//订购人证件类型
                .buyerIdNumber(orderHipac.getCustIdNum())//订购人证件号码
                .buyerTelephone(orderHipac.getCustPhone())//订购人电话
                .buyerRegNo(orderHipac.getCustPhone())//订购人注册号
                .grossWeight(null)//毛重（公斤）
                .netWeight(null)//净重（公斤）
                .bakbCode(null)//商品备案地国检
                .ordExcStatus("10")//执行状态
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
                .tradeMode("1")//贸易模式
                .shipperName(null)//发货人名称
                .shipperAddress(null)//发货人地址
                .shipperPhone(null)//发货人电话
                .agentCode(null)//报关企业代码
                .agentName(null)//报关企业名称
                .payNo(orderHipac.getPayNo())//支付流水号
                .payPcomName(orderHipac.getPayCompanyName())//支付企业名称
                .payCopNo(null)//支付企业编码
                .opType("1")//申报类型
                .payType(orderHipac.getPayType())//交易支付类型
                .payStatus("D")//支付交易状态
                .payorName(null)//付款人
                .activePayComp(null)//实际支付企业
                .acturalPaid(orderHipac.getTotalPayAmount())//实际支付金额
                .payCurr("CNY")//支付币制
                .payNots(null)//支付信息备注
                .payDate(orderHipac.getPayTime())//支付日期
                .fromEplat(0)//来源第e仓
                .printHeader(null)//打印抬头
                .commonField(null)//通用字段
                .isStoreStrategy(0)//是否校验库存策略
                .vmiFlag(0)//是否VMI模式
                .ownerFlag(0)//是否一单多业主
                .cutMode(null)//征免性质分类
                .transMode(null)//成交方式
                .packNo(null)//件数
                .wrapType(null)//包装种类
                .sendCity(null)//发件人城市
                .totalValue(orderHipac.getTotalOrderAmount())//货值
                .goodsInfo(null)//主要货物名称
                .orderBatchNo(null)//发货订单批次
                .reDeclare(null)//重报标识
                .ebptpl(null)//平台物流商编码
                .recipient(recipientDto)
                .goodList(goodList).build();
    }

    /**
     * 转ofc参数
     * @param dto
     * @param transferConfig
     * @return
     */
    public static OfcRequest convertOfc(OrderHipac dto, TransferDefaultConfig transferConfig) {
        List<OrderHipacItem> itemList = dto.getOrderItemList();
        OfcOrderReqDto.Builder build = OfcOrderReqDto.newBuilder()
                .key(null)
                .orderNo(dto.getOrderNum())
                .declTime(dto.getOrderDate())
                .buyerRegNo(dto.getCustPhone())
                .buyerIdType("1")
                .buyerIdNumber(dto.getCustIdNum())
                .buyerName(dto.getCustName())
                .buyerTelephone(dto.getCustPhone())
                .receiveNo(dto.getCustIdNum())
                .consignee(dto.getCustName())
                .consigneeTelephone(dto.getCustPhone())
                .pod(null)
                .consigneeAddress(dto.getCustAddress())
                .country("中国")
                .province(dto.getCustProvice())
                .city(dto.getCustCity())
                .district(dto.getCustArea())
                .postCode(null)
                .goodsValue(null)
                .freight(dto.getLogisticsAmount())
                .discount(BigDecimal.ZERO)
                .taxTotal(dto.getTotalTaxAmount())
                .taxCurr("CNY")
                .acturalPaid(dto.getTotalPayAmount())
                .payCurr(null)
                .insuredFee(BigDecimal.ZERO)
                .insurCurr(null)
                .freightCurr("CNY")
                .fCode(null)
                .ebpCode(dto.getSupplierSenderID())
                .ebpName(null)
                .ebcCode(dto.getCbePcomCode())
                .ebcName(null)
                .userId(dto.getShopNum())
                .userName(null)
                .logisticsCode(null)
                .logisticsName(null)
                .logisticsNo(null)
                .logisticsNote(null)
                .payCode(null)
                .payName(null)
                .payTransactionId(dto.getPayNo())
                .payTime(null)
                .payNote(null)
                .customsCode(dto.getCustomsCode().getValue())
                .ciqbCode("210100")
                .ieFlag("I")
                .busiMode(BusiModeEnum.BBC.getValue())
                .orderType(1L)
                .orderStatus(null)
                .customsType(null)
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
                .orderCode(null)
                .storeCode(null)
                .platformOrderType(null)
                .orderSource(null)
                .ownerUserId(null)
                .ownerUserName(null)
                .lpCode(null)
                .deliveryTimePreference(null)
                .isPackage(null)
                .transportday(null)
                .note(null)
                .tradeMode(null)
                .commonField(null)
                .orderBatchNo(null)
                .businessType(null)
                .cBCode(null)
                .declareFormNo(null)
                .udf1(null)
                .udf2(null);
        /**
         * 转单配置赋值
         */
        if (transferConfig != null){
            if (StringUtils.isNotEmpty(transferConfig.getPlatformCode())) {
                build.ebpCode(transferConfig.getPlatformCode());
            }
            if (StringUtils.isNotEmpty(transferConfig.getMerchantCode())) {
                build.ebcCode(transferConfig.getMerchantCode());
            }
            if (StringUtils.isNotEmpty(transferConfig.getLogisticsCode())) {
                build.logisticsCode(transferConfig.getLogisticsCode());
            }
            if (StringUtils.isNotEmpty(transferConfig.getWarehouseCode())) {
                build.storeCode(transferConfig.getWarehouseCode());
            }
            if (StringUtils.isNotEmpty(transferConfig.getOrderSource())) {
                build.orderSource(Long.valueOf(transferConfig.getOrderSource()));
            }
            if (StringUtils.isNotEmpty(transferConfig.getFromEplat())) {
                build.fromEplat(Long.valueOf(transferConfig.getFromEplat()));
            }
            if (StringUtils.isNotEmpty(transferConfig.getCiqCode())) {
                build.ciqbCode(transferConfig.getCiqCode());
            }
            if (StringUtils.isNotEmpty(transferConfig.getOrderType())) {
                build.orderType(Long.valueOf(transferConfig.getOrderType()));
            }
        }

        OfcOrderReqDto ofcOrderReqDto = build.build();

        List<OfcGoodsReqDto> list = Lists.newArrayList();
        for (int i = 0; i < itemList.size(); i++) {
            OrderHipacItem orderGoods = itemList.get(i);

            OfcGoodsReqDto ofcGoodsReqDto = OfcGoodsReqDto.newBuilder()
                    .gnum(String.valueOf(1+i))
                    .goodsId(orderGoods.getItemSupplyNo())
                    .goodsName(null)
                    .barCode(null)
                    .currency("CNY")
                    .qty(orderGoods.getItemQuantity())
                    .qtyUnit(null)
                    .price(orderGoods.getItemPrice())
                    .totalPrice(orderGoods.getItemTotal())
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

}

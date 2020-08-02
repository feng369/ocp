package com.topideal.supplychain.ocp.gs.converter;

import com.google.common.collect.Lists;
import com.topideal.supplychain.ocp.config.dto.TransferDefaultConfig;
import com.topideal.supplychain.ocp.enums.GsIsSendKjbEnum;
import com.topideal.supplychain.ocp.enums.KjbSendStausEnum;
import com.topideal.supplychain.ocp.kjb.dto.KjbReqGoods;
import com.topideal.supplychain.ocp.kjb.dto.KjbRequest;
import com.topideal.supplychain.ocp.kjb.dto.KjbResponse;
import com.topideal.supplychain.ocp.ofc.dto.OfcGoodsReqDto;
import com.topideal.supplychain.ocp.ofc.dto.OfcOrderReqDto;
import com.topideal.supplychain.ocp.ofc.dto.OfcRequest;
import com.topideal.supplychain.ocp.op.dto.OpGoodsItemDto;
import com.topideal.supplychain.ocp.op.dto.OpRecipientDto;
import com.topideal.supplychain.ocp.op.dto.OpRequest;
import com.topideal.supplychain.ocp.order.model.OrderGs;
import com.topideal.supplychain.ocp.order.model.OrderGsItem;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>标题: TODO</p>
 * <p>功能: TODO</p>
 * <p>模块: ocp2</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.gs.converter</p>
 * <p>作者: topIdeal</p>
 * <p>创建日期: 2019-12-30 18:04</p>
 *
 * @version 1.0
 */
public class GsDtoConverter {

    public static KjbRequest convertKjb(OrderGs orderGs, List<OrderGsItem> orderGsItemList) {
        KjbRequest.Builder builder = KjbRequest.newBuilder();
        List<KjbReqGoods> kjbReqGoodsList = Lists.newArrayList();
        for (OrderGsItem orderGsItem : orderGsItemList) {// 商品数量
            BigDecimal qty = orderGsItem.getQuantity();
            if (BigDecimal.ZERO.compareTo(qty) == 0) {
                continue;
            }
            // 商品单价
            BigDecimal price = orderGsItem.getBondedAreaGoodsPrice()
                    .divide(qty, 4, BigDecimal.ROUND_HALF_UP);
            KjbReqGoods kjbReqGoods = KjbReqGoods.newBuilder()
                    .goods_id(orderGsItem.getDeliverCode())
                    .goods_name(orderGsItem.getSkuTitle())
                    .goods_price(price)
                    .goods_num(qty.stripTrailingZeros().toString()).build();
            kjbReqGoodsList.add(kjbReqGoods);
        }
        // 商品总货款
        BigDecimal paymentGoods = orderGs.getBondedAreaGoodsPrice()
                .setScale(2, BigDecimal.ROUND_HALF_UP);
        return builder.order_id(orderGs.getConsignCode())
                .cbepcomCode(orderGs.getEbcCode())
                .payment_goods(paymentGoods)
                .good_list(kjbReqGoodsList).build();
    }


    public static OfcRequest convertOfc(OrderGs orderGs, List<OrderGsItem> gsItemList,
            TransferDefaultConfig config) {
        List<OfcGoodsReqDto> list = Lists.newArrayList();
        for (int i = 0; i < gsItemList.size(); i++) {
            OfcGoodsReqDto ofcGoodsReqDto = OfcGoodsReqDto.newBuilder()
                    .gnum(String.valueOf(i + 1))
                    .goodsId(gsItemList.get(i).getDeliverCode())
                    .currency("CNY")
                    .qty(gsItemList.get(i).getQuantity())
                    .price(gsItemList.get(i).getBondedAreaGoodsPrice()
                            .divide(gsItemList.get(i).getQuantity(), 2, BigDecimal.ROUND_HALF_UP))
                    .totalPrice(gsItemList.get(i).getBondedAreaGoodsPrice()
                            .divide(gsItemList.get(i).getQuantity(), 2, BigDecimal.ROUND_HALF_UP)
                            .multiply(gsItemList.get(i).getQuantity())).build();
            list.add(ofcGoodsReqDto);
        }

        OfcOrderReqDto.Builder build = OfcOrderReqDto.newBuilder()
                .orderNo(orderGs.getConsignCode())
                .declTime(orderGs.getOrderCreateTime())
                .buyerRegNo(orderGs.getShipToMobile())
                .buyerIdType("1")
                .buyerIdNumber(orderGs.getIdCardNo())
                .buyerName(orderGs.getShipToName())
                .buyerTelephone(orderGs.getShipToMobile())
                .receiveNo(orderGs.getIdCardNo())
                .consignee(orderGs.getShipToName())
                .consigneeTelephone(orderGs.getShipToMobile())
                .consigneeAddress(orderGs.getShipToAddress())
                .country("中国")
                .province(orderGs.getShipToProvince())
                .city(orderGs.getShipToCity())
                .district(orderGs.getShipToDistrict())
                .freight(orderGs.getBondedAreaShipExpense())
                .discount(orderGs.getBondedAreaNonCashDeduct())
                .taxTotal(orderGs.getBondedAreaTax())
                .taxCurr("CNY")
                .insuredFee(BigDecimal.ZERO)
                .freightCurr("CNY")
                .ebpCode(StringUtils.isNotEmpty(config.getPlatformCode()) ? config.getPlatformCode()
                        : orderGs.getEbpCode())
                .ebcCode(StringUtils.isNotEmpty(config.getMerchantCode()) ? config.getMerchantCode()
                        : orderGs.getEbcCode())
                .logisticsCode(StringUtils.isNotEmpty(config.getLogisticsCode()) ? config
                        .getLogisticsCode() : orderGs.getLogisticsCode())
                .customsCode(orderGs.getCustomsCode())
                .ciqbCode(StringUtils.isNotEmpty(config.getCiqCode()) ? config.getCiqCode()
                        : orderGs.getCiqCode())
                .ieFlag("I")
                .busiMode(orderGs.getBusiMode().getValue())
                .orderType(StringUtils.isNotEmpty(config.getOrderType()) ? Long
                        .valueOf(config.getOrderType()) : 1L)
                .payTime(orderGs.getPayTime())
                .packNo(1L)
                .grossWeight(null)
                .vmiFlag(0L)
                .ownerFlag(0L)
                .isStoreStrategy(0L)
                .orderSource(StringUtils.isNotEmpty(config.getOrderSource()) ? Long
                        .valueOf(config.getOrderSource()) : 101L)
                .key(orderGs.getGrabId())
                .userId(orderGs.getShopCode())
                .payTransactionId(orderGs.getTradeNo())
                .storeCode(StringUtils.isNotEmpty(config.getWarehouseCode()) ? config
                        .getWarehouseCode() : orderGs.getWarehouseCode())
                .fromEplat(StringUtils.isNotEmpty(config.getFromEplat()) ? Long
                        .valueOf(config.getFromEplat()) : null);
        //fixme 旧ocp逻辑，是否可改成从转单配置取？
        /*if ("1".equals(orderGs.getFromEplat())) {
            build.fromEplat(1L).orderSource(100L);
        }*/
        OfcOrderReqDto ofcOrderReqDto = build.build();
        return new OfcRequest(ofcOrderReqDto, list);
    }

    public static OpRequest convertOp(OrderGs orderGs, List<OrderGsItem> gsItemList,
            TransferDefaultConfig config) {
        BigDecimal hundred = new BigDecimal(100);

        List<OpGoodsItemDto> goodList = Lists.newArrayList();
        for (int i = 0; i < gsItemList.size(); i++) {
            OrderGsItem item = gsItemList.get(i);
            OpGoodsItemDto itemDto = OpGoodsItemDto.newBuilder()
                    .gnum(String.valueOf(i + 1))
                    .goodId(item.getDeliverCode())//商品货号
                    .amount(item.getQuantity().intValue())//数量
                    .price(item.getPrice())//售价（实际售价）
                    .goodPrice(null)//商品售价（展示售价）
                    .copGName(item.getSkuTitle())//商品名称
                    .hsCode(null)//海关编码
                    .codeTs(null)//行邮税号
                    .decTotal(item.getTotalPrice())//商品总价
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
                    .virtualOwner("1" .equals(config.getFromEplat()) ? "1000000581" : null)
                    .qty1(null).build();//第一法定单位数量
            goodList.add(itemDto);
        }

        OpRecipientDto recipientDto = OpRecipientDto.newBuilder()
                .name(orderGs.getShipToName())//收货人姓名
                .receiveType(1)//证件类型
                .receiveNo(orderGs.getIdCardNo())//收件人证件号
                .mobilePhone(orderGs.getShipToMobile())//手机号码
                .phone(null)//座机
                .country("中国")//国家
                .province(orderGs.getShipToProvince())//省份
                .city(orderGs.getShipToCity())//市
                .district(orderGs.getShipToDistrict())//区/县
                .address(orderGs.getShipToAddress())//地址
                .postCode(orderGs.getShipToZip())//邮编
                .totalFavourable(orderGs.getBondedAreaNonCashDeduct()
                        .divide(hundred, 5, BigDecimal.ROUND_HALF_UP))//订单优惠金额
                .sender(null)//送礼人
                .receiver(null)//收礼人
                .congratulations(null)//祝贺语
                .transportDay(null)//配送时间
                .recipientProvincesCode(null).build();//收货人省市代码

        return OpRequest.newBuilder()
                .orderId(orderGs.getConsignCode())//企业订单编号
                .orderDate(orderGs.getCreateTime())//订单生成时间
                .packingMaterial(null)//包材编码
                .warehouseId(null)//仓库ID，
                .tpl(StringUtils.isNotEmpty(config.getTpl()) ? config.getTpl()
                        : orderGs.getLogisticsCode())//第三方物流商编
                .orderType(1)//是否自运营订单
                .orderStatus("S")//订单状态
                .customsType(1)//海关类型
                .electricCode(
                        StringUtils.isNotEmpty(config.getPlatformCode()) ? config.getPlatformCode()
                                : orderGs.getEbcCode())//电商企业编码
                .cbepcomCode(
                        StringUtils.isNotEmpty(config.getMerchantCode()) ? config.getMerchantCode()
                                : orderGs.getEbpCode())//电商平台编码
                .busiMode(Integer.valueOf(orderGs.getBusiMode().getValue()))//进口模式
                .customsCode(orderGs.getCustomsCode())//申报关区
                .ciqbCode(StringUtils.isNotEmpty(config.getCiqCode()) ? config.getCiqCode()
                        : orderGs.getCiqCode())//申报国检
                .stationbCode(null)//申报场站
                .deliveryCode(null)//运单号
                .freightFcy(orderGs.getBondedAreaShipExpense()
                        .divide(hundred, 5, BigDecimal.ROUND_HALF_UP))//运费，分转为元，保留五位小数
                .freightFcode("CNY")//运费币制
                .insuredFee(BigDecimal.ZERO)//保费
                .insurCurr(null)//保费币制
                .insurMark(null)//(预留字段)
                .taxFcy(orderGs.getBondedAreaTax().divide(hundred, 5, BigDecimal.ROUND_HALF_UP))//税费
                .taxFcode("CNY")//税费币种
                .otherRate(BigDecimal.ZERO)//杂费
                .otherCurr(null)//杂费币制
                .otherMark(null)//(预留字段)
                .otherPayment(BigDecimal.ZERO)//抵付金额
                .otherPaymentCurr(null)//抵付币制
                .fCode("CNY")//货款币制
                .discount(orderGs.getBondedAreaNonCashDeduct()
                        .divide(hundred, 5, BigDecimal.ROUND_HALF_UP))//优惠减免金额
                .buyerName(orderGs.getShipToName())//订购人姓名
                .buyerIdType(1)//订购人证件类型
                .buyerIdNumber(orderGs.getIdCardNo())//订购人证件号码
                .buyerTelephone(orderGs.getShipToMobile())//订购人电话
                .buyerRegNo(orderGs.getShipToMobile())//订购人注册号
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
                .inputDate(orderGs.getCreateTime())//录入日期
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
                .payNo(orderGs.getTradeNo())//支付流水号
                .payPcomName(orderGs.getPayChannel())//支付企业名称
                .payCopNo(null)//支付企业编码
                .opType(null)//申报类型
                .payType(null)//交易支付类型
                .payStatus(null)//支付交易状态
                .payorName(null)//付款人
                .activePayComp(null)//实际支付企业
                .acturalPaid(orderGs.getBondedAreaPayCash()
                        .divide(hundred, 5, BigDecimal.ROUND_HALF_UP))//实际支付金额
                .payCurr(null)//支付币制
                .payNots(null)//支付信息备注
                .payDate(null)//支付日期
                .fromEplat("1" .equals(orderGs.getFromEplat()) ? 1 : 0)//来源第e仓
                .printHeader(null)//打印抬头
                .isStoreStrategy(null)//是否校验库存策略
                .vmiFlag(null)//是否VMI模式
                .ownerFlag(null)//是否一单多业主
                .cutMode(null)//征免性质分类
                .transMode(null)//成交方式
                .packNo(1)//件数
                .wrapType(null)//包装种类
                .sendCity(null)//发件人城市
                .totalValue(null)//货值
                .goodsInfo(null)//主要货物名称
                .orderBatchNo(null)//发货订单批次
                .reDeclare(null)//重报标识
                .ebptpl(null)
                .notes(orderGs.getShopCode())
                .recipient(recipientDto)
                .goodList(goodList).build();//平台物流商编码
    }
}

package com.topideal.supplychain.ocp.jd.converter;

import com.google.common.collect.Lists;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.ocp.config.dto.TransferDefaultConfig;
import com.topideal.supplychain.ocp.enums.JdOrderEnum;
import com.topideal.supplychain.ocp.ofc.dto.OfcGoodsReqDto;
import com.topideal.supplychain.ocp.ofc.dto.OfcOrderReqDto;
import com.topideal.supplychain.ocp.ofc.dto.OfcOrderReqDto.Builder;
import com.topideal.supplychain.ocp.ofc.dto.OfcRequest;
import com.topideal.supplychain.ocp.order.model.OrderJd;
import com.topideal.supplychain.ocp.order.model.OrderJdItem;
import com.topideal.supplychain.util.AES256Util;
import com.topideal.supplychain.util.JacksonUtils;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName DtoConverter
 * @TODO dto转换器
 * @Author zhangzhihao
 * @DATE 2019/12/3 17:38
 * @Version 1.0
 **/

public class JdDtoConverter {


    /**
     * 转换订单信息下发到ofc
     * 特殊字段备注：
     * 1.国检编码，如果国检编码存在，则取订单上的，如果国检编码不存在，则取转单配置的ciqCode国检编码
     * 2.业务模式，取值customModel，BEIHUO("备货","beihuo",BusiModeEnum.BBC),
     *                             ZHIYOU("直邮","zhiyou",BusiModeEnum.BC),
     *
     * 3折扣金额，除了云霄购传京东给的值，独立站多渠道自营非自营的折扣金额都传0
     * 4电商企业电商平台，除了自营非自营的传京东给的值EbpCode,EbcCode,独立站多渠道云霄购都需要转为转单配置中的电商企业编码电商平台编码
     * 5物流企业编码，除了自营非自营的传京东给的值logisticsCode,独立站多渠道云霄购都需要转为转单配置中的物流企业编码
     * 6.订单来源默认601 packNo默认1
     * 7.storeCode仓库编码取转单配置中的店铺编码
     * 8.如果是云霄购，则明细上的电商企业需要取转单配置的ItemMerchantCode
     * 9第三方平台编码跟平台名称
     * 自营非自营的为空
     * 独立站的为订单信息中的third_platform_code  third_platform_name
     * 云霄购的为ebp_code   ebc_code
     *
     * @param orderJd
     * @param orderItems
     * @param defaultConfig
     * @return
     */
    public static OfcRequest convertOfc(OrderJd orderJd, List<OrderJdItem> orderItems, TransferDefaultConfig defaultConfig) {

        Map<String,Object> jsonMap = new HashMap<>();

        Builder build = OfcOrderReqDto.newBuilder()
                .busiMode(orderJd.getCustomModel().getBusiModeEnum().getValue())
                .buyerIdNumber(AES256Util.decrypt(orderJd.getBuyerIdNumber()))
                .buyerIdType(orderJd.getBuyerIdType().getValue())
                .buyerName(orderJd.getBuyerName())
                .buyerTelephone(AES256Util.decrypt(orderJd.getBuyerPhone()))
                .buyerRegNo(orderJd.getBuyerRegNo())
                .ciqbCode(StringUtils.isNotEmpty(orderJd.getCiqbCode()) ? orderJd.getCiqbCode() : defaultConfig.getCiqCode())
                .province(orderJd.getConsigneeProvince())
                .city(orderJd.getConsigneeCity())
                .district(orderJd.getConsigneeCounty())
                .consignee(orderJd.getConsigneeName())
                .consigneeAddress(orderJd.getConsigneeAddress())
                .consigneeTelephone(AES256Util.decrypt(orderJd.getConsigneePhone()))
                .customsCode(orderJd.getCustomsCode())
                .discount(BigDecimal.ZERO)
                .ebpCode(orderJd.getEbpCode())
                .ebpName(orderJd.getEbpName())
                .ebcCode(orderJd.getEbcCode())
                .ebcName(orderJd.getEbcName())
                .freight(orderJd.getFreight() == null ? BigDecimal.ZERO : orderJd.getFreight())
                .freightCurr(orderJd.getFreightCurr())
                .ieFlag(StringUtils.isNotEmpty(orderJd.getIeFlag()) ? orderJd.getIeFlag() : "I")
                .insuredFee(orderJd.getInsuredFee() == null ? BigDecimal.ZERO : orderJd.getInsuredFee())
                .key(orderJd.getGrabKey())
                .declTime(orderJd.getOrderCreateTime())
                .logisticsCode(orderJd.getLogisticsCode())
                .logisticsName(orderJd.getLogisticsName())
                .logisticsNo(orderJd.getLogisticsNo())
                .logisticsNote(null)
                .orderNo(orderJd.getOrderId())
                .orderSource(601L)
                .orderType(orderJd.getOrderType().getOmsValue())
                .packNo(1L)
                .payCode(orderJd.getPayCode())
                .payName(orderJd.getPayName())
                .payTransactionId(orderJd.getPayTransactionId())
                .receiveNo(AES256Util.decrypt(orderJd.getConsigneeIdNumber()))
                .storeCode(defaultConfig.getWarehouseCode())
                .country("中国")
                .taxTotal(orderJd.getTax() == null ? BigDecimal.ZERO : orderJd.getTax())
                .taxCurr(orderJd.getTaxCurr())
                .userId(orderJd.getVenderId())
                //.features(JacksonUtils.toJSon(jsonMap))
                ;
        if (StringUtils.isNotEmpty(defaultConfig.getLogisticsCode())) {
            build.logisticsCode(defaultConfig.getLogisticsCode());
        }
        //如果是多渠道或者是独立站的，则需要转换电商企业，电商平台，物流企业
        if (orderJd.getOrderType() == JdOrderEnum.DLZ || orderJd.getOrderType() == JdOrderEnum.MULTILCHAN) {
            build.ebcCode(defaultConfig.getMerchantCode())//电商企业编码
            .ebpCode(defaultConfig.getPlatformCode())//电商平台编码
            .logisticsCode(defaultConfig.getLogisticsCode());//物流企业编码*/
            jsonMap.put("thirdPlatformCode", orderJd.getThirdPlatformCode());//三方平台编码
            jsonMap.put("thirdPlatformName", orderJd.getThirdPlatformName());//三方平台名称
        }

        //如果是云霄购
        if (orderJd.getOrderType() == JdOrderEnum.SHIES) {
            build.ebcCode(defaultConfig.getMerchantCode())//电商企业编码
            .ebpCode(defaultConfig.getPlatformCode())//电商平台编码
            .logisticsCode(defaultConfig.getLogisticsCode())//物流企业编码*/
            .discount(orderJd.getDiscount());
            jsonMap.put("thirdPlatformCode", orderJd.getEbpCode());//三方平台编码
            jsonMap.put("thirdPlatformName", orderJd.getEbpName());//三方平台名称
        }
        build.features(JacksonUtils.toJSon(jsonMap));


        OfcOrderReqDto ofcOrderReqDto = build.build();

        List<OfcGoodsReqDto> list = Lists.newArrayList();
        for (int i = 0; i < orderItems.size(); i++) {
            OrderJdItem orderJdItem = orderItems.get(i);
            String ebcCode = null;
            //如果是云霄购
            if (orderJd.getOrderType() == JdOrderEnum.SHIES) {
                ebcCode = defaultConfig.getItemMerchantCode();
                BusinessAssert.assertNotEmpty(ebcCode,"云霄购明细电商企业编码未配置");
            }
            OfcGoodsReqDto ofcGoodsReqDto = OfcGoodsReqDto.newBuilder()
                    .gnum(orderJdItem.getgNum())
                    .goodsId(orderJdItem.getItemNo())
                    .goodsName(orderJdItem.getItemName())
                    .barCode(null)
                    .currency(orderJdItem.getCurr())
                    .qty(orderJdItem.getQuantity())
                    .qtyUnit(orderJdItem.getUnit())
                    .price(orderJdItem.getPrice())
                    .totalPrice(orderJdItem.getTotalPrice())
                    .batchNo(null)
                    .storeStrategyId(null)
                    .productionTime(null)
                    .expDate(null)
                    .ebcCode(ebcCode)
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










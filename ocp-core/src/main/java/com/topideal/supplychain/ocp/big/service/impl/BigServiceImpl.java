package com.topideal.supplychain.ocp.big.service.impl;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.enumeration.YesOrNoEnum;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.mq.MessageSender;
import com.topideal.supplychain.ocp.big.converter.BigConvertConstant;
import com.topideal.supplychain.ocp.big.converter.BigDtoConverter;
import com.topideal.supplychain.ocp.big.dto.BigGoodsRequestDto;
import com.topideal.supplychain.ocp.big.dto.BigReceiveRequestDto;
import com.topideal.supplychain.ocp.big.dto.BigRecipientRequestDto;
import com.topideal.supplychain.ocp.big.service.BigService;
import com.topideal.supplychain.ocp.config.dto.TransferDefaultConfig;
import com.topideal.supplychain.ocp.config.model.BigJdChannelConfig;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.config.model.CombineGoodsConfig;
import com.topideal.supplychain.ocp.config.model.TransferConfig;
import com.topideal.supplychain.ocp.config.service.BigJdChannelConfigService;
import com.topideal.supplychain.ocp.config.service.CatchOrderConfigService;
import com.topideal.supplychain.ocp.config.service.CombineGoodsConfigService;
import com.topideal.supplychain.ocp.config.service.TransferConfigService;
import com.topideal.supplychain.ocp.constants.PunctuateConstant;
import com.topideal.supplychain.ocp.enums.BigBusiTypeEnum;
import com.topideal.supplychain.ocp.enums.BigChangeFlagEnum;
import com.topideal.supplychain.ocp.enums.BigOrderStatusEnum;
import com.topideal.supplychain.ocp.enums.BigOrderTypeEnum;
import com.topideal.supplychain.ocp.enums.CustomsCodeEnum;
import com.topideal.supplychain.ocp.enums.CustomsTypeEnum;
import com.topideal.supplychain.ocp.enums.ForwardSystemEnum;
import com.topideal.supplychain.ocp.enums.IsGoodsRecordEnum;
import com.topideal.supplychain.ocp.enums.OrderStatusEnum;
import com.topideal.supplychain.ocp.enums.ReceiveIdTypeEnum;
import com.topideal.supplychain.ocp.master.model.Platform;
import com.topideal.supplychain.ocp.master.service.PlatformService;
import com.topideal.supplychain.ocp.mq.QueueConstants;
import com.topideal.supplychain.ocp.ofc.dto.OfcRequest;
import com.topideal.supplychain.ocp.ofc.service.OfcApiService;
import com.topideal.supplychain.ocp.ofcbc.dto.OfcBcExpRequest;
import com.topideal.supplychain.ocp.ofcbc.dto.OfcBcRequest;
import com.topideal.supplychain.ocp.ofcbc.service.OfcBcApiService;
import com.topideal.supplychain.ocp.op.dto.OpRequest;
import com.topideal.supplychain.ocp.op.service.OpApiService;
import com.topideal.supplychain.ocp.order.model.OrderBig;
import com.topideal.supplychain.ocp.order.model.OrderBigItem;
import com.topideal.supplychain.ocp.order.model.OrderTemp;
import com.topideal.supplychain.ocp.order.service.OrderBigItemService;
import com.topideal.supplychain.ocp.order.service.OrderBigService;
import com.topideal.supplychain.ocp.order.service.OrderTempService;
import com.topideal.supplychain.util.AES256Util;
import com.topideal.supplychain.util.JacksonUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * 标题：大订单业务service
 * 模块：ocp-parent
 * 版权: Copyright © 2019 topideal
 * 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.big.service.impl
 * 作者：songping
 * 创建日期：2019/12/26 18:21
 *
 * @version 1.0
 */
@Service
public class BigServiceImpl implements BigService {

    private static final String QUEUE_PREFIX = "ocp.big.order.push.";//队列名前缀

    @Autowired
    private OrderBigService orderBigService;
    @Autowired
    private OrderBigItemService goodsBigService;
    @Autowired
    private BigJdChannelConfigService bigJdChannelConfigService;
    @Autowired
    private TransferConfigService transferConfigService;
    @Autowired
    private MessageSender messageSender;
    @Autowired
    private CombineGoodsConfigService combineGoodsConfigService;
    @Autowired
    private OpApiService opApiService;
    @Autowired
    private OfcApiService ofcApiService;
    @Autowired
    private OfcBcApiService ofcBcApiService;
    @Autowired
    private OrderTempService orderTempService;
    @Autowired
    private PlatformService platformService;
    @Autowired
    private CatchOrderConfigService catchOrderConfigService;

    @Override
    @Transactional
    public void receiveOrder(BigReceiveRequestDto requestDto, String requestJson) {
        //1.校验数据
        validateOrderRequest(requestDto);
        // 校验通过存入临时表,发送订单处理MQ
        OrderTemp orderTemp = new OrderTemp(requestJson);
        BasicMessage basicMessage = new BasicMessage(orderTemp.getId(), requestDto.getOrderId(), "",
                "");
        orderTempService
                .insertAndSendMq(orderTemp, QueueConstants.QueueEnum.OCP_BIG_ORDER_PROCESS,
                        basicMessage);

    }

    /**
     * 校验请求数据
     *
     * @param request
     */
    private void validateOrderRequest(BigReceiveRequestDto request) {
        BusinessAssert.assertNotEmpty(request.getGoodList(), "货品信息【goodList】不能为空！");
        BusinessAssert.assertNotNull(request.getCustomsType(), "海关类型【customsType】不能为空！");
        BusinessAssert.assertIsFalse(!CollectionUtils.containsInstance(CustomsTypeEnum.getEnumValues(), request.getCustomsType()), "海关类型【" + request.getCustomsType() + "】不在码表中：" + CustomsTypeEnum.getEnumInfo());
        //总署版的非空校验
        if (CustomsTypeEnum.V_ZS.getValue().equals(request.getCustomsType())) {
            BusinessAssert.assertNotEmpty(request.getBuyerName(), "海关类型【customsType】为 1-总署版，订购人姓名【buyerName】不能为空！");
            BusinessAssert.assertNotEmpty(request.getBuyerIdNumber(), "海关类型【customsType】为 1-总署版，订购人证件号码【buyerIdNumber】不能为空！");
            BusinessAssert.assertNotEmpty(request.getBuyerTelephone(), "海关类型【customsType】为 1-总署版，订购人电话【buyerTelephone】不能为空！");
        }
        //进出口公共校验
        BusinessAssert.assertNotEmpty(request.getOrderId(), "企业订单编号【orderId】不能为空！");
        BusinessAssert.assertNotEmpty(request.getOrderDate(), "订单生成时间【orderDate】不能为空！");
        BusinessAssert.assertNotEmpty(request.getTpl(), "第三方物流商编码【tpl】不能为空！");
        BusinessAssert.assertNotEmpty(request.getElectricCode(), "电商企业编码【electricCode】不能为空！");
        BusinessAssert.assertNotEmpty(request.getOrderStatus(), "订单状态【orderStatus】不能为空！");
        BusinessAssert.assertNotNull(request.getBusiMode(), "进口模式【busiMode】不能为空！");
        //进出口分开校验
        if (BigBusiTypeEnum.EXP.getValue().equals(request.getBusiMode()) || BigBusiTypeEnum.BBC_OUT.getValue().equals(request.getBusiMode())) {//出口验证
            checkExpOrderData(request);
        } else {//进口验证
            checkImportOrderData(request);
        }

//        // 企业+平台+业务模式+订单号做幂等
//        OrderBig orderBig = orderBigService.selectByConditon(request.getElectricCode(), request.getCbepcomCode(),
//                String.valueOf(request.getBusiMode()), request.getOrderId());
//        BusinessAssert.assertIsNull(orderBig, "已存在相同企业+平台+业务模式+订单号的订单！");
    }

    /**
     * 出口数据校验
     *
     * @param request
     */
    private void checkExpOrderData(BigReceiveRequestDto request) {
        BusinessAssert.assertNotEmpty(request.getCbepcomCode(), "电商平台编码【cbepcomCode】不能为空！");
        BusinessAssert.assertNotNull(request.getFreightFcy(), "运费【freightFcy】不能为空！");
        BusinessAssert.assertNotEmpty(request.getFreightFcode(), "运费币制【freightFcode】不能为空！");
        BusinessAssert.assertNotNull(request.getInsuredFee(), "保费【insuredFee】不能为空！");
        BusinessAssert.assertNotEmpty(request.getInsurCurr(), "保费币制【insurCurr】不能为空！");
        BusinessAssert.assertNotEmpty(request.getCustomsCode(), "申报关区【customsCode】不能为空！");
        BusinessAssert.assertNotEmpty(request.getfCode(), "货款币制【fCode】不能为空！");
        BusinessAssert.assertNotEmpty(request.getChangeFlag(), "换单标志【changeFlag】不能为空！");
        BusinessAssert.assertNotEmpty(request.getPayCurr(), "支付币制【payCurr】不能为空！");
        BusinessAssert.assertNotNull(request.getActuralPaid(), "实际支付金额【acturalPaid】不能为空！");
        BusinessAssert.assertNotEmpty(request.getStatisticsFlag(), "申报业务类型【statisticsFlag】不能为空！");
        BusinessAssert.assertNotNull(request.getPackNo(), "件数【packNo】不能为空！");
        BusinessAssert.assertNotEmpty(request.getOrderBatchNo(), "发货订单批次【orderBatchNo】不能为空！");
        BusinessAssert.assertNotNull(request.getActuralPaid(), "实际支付金额【acturalPaid】不能为空！");
        if(request.getChangeFlag().equals("0")) BusinessAssert.assertNotEmpty(request.getLogisticsNo(), "国际运单号【logisticsNo】不能为空！");
        //收货信息
        BigRecipientRequestDto recipient = request.getRecipient();
        if (BigChangeFlagEnum.YES.getValue().equals(request.getChangeFlag())) {//换单标志=1
            BusinessAssert.assertNotNull(recipient, "换单标志=1，收货信息【recipient】不能为空！");
            BusinessAssert.assertNotEmpty(recipient.getName(), "换单标志=1，收货信息-收货人姓名【name】不能为空！");
            BusinessAssert.assertNotEmpty(recipient.getMobilePhone(), "换单标志=1，收货信息-手机号码【mobilePhone】不能为空！");
            BusinessAssert.assertNotEmpty(recipient.getAddress(), "换单标志=1，收货信息-地址【address】不能为空！");
        }
        //货品信息
        List<BigGoodsRequestDto> goodList = request.getGoodList();
        for (BigGoodsRequestDto good : goodList) {
            BusinessAssert.assertNotNull(good.getGnum(), "货品信息-序号【gnum】不能为空！");
            BusinessAssert.assertNotNull(good.getAmount(), "货品信息-数量【amount】不能为空！");
            BusinessAssert.assertNotNull(good.getPrice(), "货品信息-售价（实际售价)【price】不能为空！");
            BusinessAssert.assertNotNull(good.getDecTotal(), "货品信息-商品总价【decTotal】不能为空！");
            BusinessAssert.assertNotEmpty(good.getIsGoodsRecord(), "货品信息-是否指定备案信息【isGoodsRecord】不能为空！");
            BusinessAssert.assertNotEmpty(good.getBarCode(), "货品信息-条形码【barCode】不能为空！");
            BusinessAssert.assertNotEmpty(good.getCopGName(), "货品信息-商品名称【copGName】不能为空！");
            BusinessAssert.assertNotEmpty(good.getQtyUnit(), "货品信息-法定计量单位【qtyUnit】不能为空！");
            if (IsGoodsRecordEnum.YES.getValue().equals(good.getIsGoodsRecord())) {//商品备案
                BusinessAssert.assertNotEmpty(good.getHsCode(), "货品信息-海关编码【hsCode】不能为空！");
                BusinessAssert.assertNotEmpty(good.getSpec(), "货品信息-商品规格型号【spec】不能为空！");
                BusinessAssert.assertNotEmpty(good.getUnit1(), "货品信息-第一法定单位【unit1】不能为空！");
                BusinessAssert.assertIsFalse((null == good.getGrossWeight() || good.getGrossWeight().compareTo(BigDecimal.ZERO) == 0), "货品信息-毛重（Kg）【grossWeight】不能为空或者为0！");
                BusinessAssert.assertIsFalse((null == good.getNetWeight() || good.getNetWeight().compareTo(BigDecimal.ZERO) == 0), "货品信息-净重（Kg）【netWeight】不能为空或者为0！");
                BusinessAssert.assertIsFalse((null == good.getQty1() || good.getQty1().compareTo(BigDecimal.ZERO) == 0), "货品信息-第一法定单位数量【qty1】不能为空或者为0！");
                BusinessAssert.assertIsFalse((StringUtils.isBlank(good.getgQty1()) || new BigDecimal(good.getgQty1()).compareTo(BigDecimal.ZERO) == 0), "货品信息-商品第一数量【gQty1】不能为空或者为0！");
                //第二法定单位不为空则第二法定数量不为空
                if (StringUtils.isNotBlank(good.getUnit2())) {
                    BusinessAssert.assertIsFalse((null == good.getQty2() || good.getQty2().compareTo(BigDecimal.ZERO) == 0), "货品信息-第二法定单位数量【qty2】不能为空或者为0！");
                    BusinessAssert.assertIsFalse((StringUtils.isBlank(good.getgQty2()) || new BigDecimal(good.getgQty2()).compareTo(BigDecimal.ZERO) == 0), "货品信息-商品第二数量【gQty2】不能为空或者为0！");
                }
            }
        }
    }

    /**
     * 进口数据校验
     *
     * @param request
     */
    private void checkImportOrderData(BigReceiveRequestDto request) {
        BigRecipientRequestDto recipient = request.getRecipient();
        List<BigGoodsRequestDto> goodList = request.getGoodList();
        //收货信息
        BusinessAssert.assertNotNull(recipient, "收货信息【recipient】不能为空！");
        BusinessAssert.assertNotEmpty(recipient.getName(), "收货信息-收货人姓名【name】不能为空！");
        BusinessAssert.assertNotNull(recipient.getReceiveType(), "收货信息-证件类型【receiveType】不能为空！");
        //2020.4.9 取消大订单接口对receiveNo字段的必填校验
//        BusinessAssert.assertNotEmpty(recipient.getReceiveNo(), "收货信息-收件人证件号【receiveNo】不能为空！");
        BusinessAssert.assertNotEmpty(recipient.getMobilePhone(), "收货信息-手机号码【mobilePhone】不能为空！");
        BusinessAssert.assertNotEmpty(recipient.getCountry(), "收货信息-国家【country】不能为空！");
        BusinessAssert.assertNotEmpty(recipient.getProvince(), "收货信息-省份【province】不能为空！");
        BusinessAssert.assertNotEmpty(recipient.getCity(), "收货信息-市【city】不能为空！");
        BusinessAssert.assertNotEmpty(recipient.getAddress(), "收货信息-地址【address】不能为空！");
        //货品信息

        for (BigGoodsRequestDto good : goodList) {
            BusinessAssert.assertNotNull(good.getGnum(), "货品信息-序号【gnum】不能为空！");
            BusinessAssert.assertNotNull(good.getAmount(), "货品信息-数量【amount】不能为空！");
            BusinessAssert.assertNotNull(good.getPrice(), "货品信息-售价（实际售价)【price】不能为空！");
        }
        //业务模式分开校验
        if (BigBusiTypeEnum.BBC.getValue().equals(request.getBusiMode())) {//BBC
            BusinessAssert.assertNotNull(request.getOrderType(), "是否自运营订单【orderType】不能为空！");
            BusinessAssert.assertNotEmpty(request.getCbepcomCode(), "电商平台编码【cbepcomCode】不能为空！");
            BusinessAssert.assertNotEmpty(request.getCiqbCode(), "申报国检【ciqbCode】不能为空！");
            //BusinessAssert.assertIsFalse(!CIQCodeEnum.getValues().contains(request.getCiqbCode()), "申报国检编码【" + request.getCiqbCode() + "】不在码表中：" + CIQCodeEnum.getEnumInfo());
            BusinessAssert.assertNotEmpty(request.getCustomsCode(), "申报关区【customsCode】不能为空！");
            BusinessAssert.assertNotEmpty(request.getFreightFcode(), "运费币制【freightFcode】不能为空！");
            BusinessAssert.assertNotEmpty(request.getBuyerName(), "订购人姓名【buyerName】不能为空！");
            BusinessAssert.assertNotEmpty(request.getBuyerIdNumber(), "订购人证件号码【buyerIdNumber】不能为空！");
            BusinessAssert.assertNotEmpty(request.getBuyerTelephone(), "订购人电话【buyerTelephone】不能为空！");
            //货品信息
            StringBuilder warns = new StringBuilder();
            for (BigGoodsRequestDto good : goodList) {
                BusinessAssert.assertIsFalse((checkZhStringLength(good.getSpec(), 255)), "商品规格型号【spec】长度超出限制255！");
                //BusinessAssert.assertNotEmpty(good.getGoodId(), "商品货号【goodId】不能为空！");
                if (StringUtils.isEmpty(good.getGoodId())) {
                    warns.append(good.getGnum()).append(":").append(good.getNotes()).append("货号为空|");
                    continue;
                }
                if (good.getGoodId().length() > 32) {
                    warns.append(good.getGnum()).append(":").append(good.getNotes()).append("货号超长|");
                }
            }
            BusinessAssert.assertEmpty(warns.toString(), warns.toString());
        }

        if (BigBusiTypeEnum.BC.getValue().equals(request.getBusiMode())) {//BC
            BusinessAssert.assertNotNull(request.getOrderType(), "是否自运营订单【orderType】不能为空！");
            BusinessAssert.assertNotEmpty(request.getCbepcomCode(), "电商平台编码【cbepcomCode】不能为空！");
            BusinessAssert.assertNotEmpty(request.getCiqbCode(), "申报国检【ciqbCode】不能为空！");
            //BusinessAssert.assertIsFalse(!CIQCodeEnum.getValues().contains(request.getCiqbCode()), "申报国检编码【" + request.getCiqbCode() + "】不在码表中：" + CIQCodeEnum.getEnumInfo());
            BusinessAssert.assertNotEmpty(request.getCustomsCode(), "申报关区【customsCode】不能为空！");
            BusinessAssert.assertNotEmpty(request.getFreightFcode(), "运费币制【freightFcode】不能为空！");
            BusinessAssert.assertNotEmpty(request.getInsurCurr(), "保费币制【insurCurr】不能为空！");
            BusinessAssert.assertNotEmpty(request.getBuyerName(), "订购人姓名【buyerName】不能为空！");
            BusinessAssert.assertNotEmpty(request.getBuyerIdNumber(), "订购人证件号码【buyerIdNumber】不能为空！");
            BusinessAssert.assertNotEmpty(request.getBuyerTelephone(), "订购人电话【buyerTelephone】不能为空！");
            BusinessAssert.assertNotEmpty(request.getShippernCode(), "发货人所在国【shippernCode】不能为空！");
            BusinessAssert.assertNotEmpty(request.getShipDate(), "发货日期【shipDate】不能为空！");
            BusinessAssert.assertNotEmpty(request.getInputDate(), "录入日期【inputDate】不能为空！");
            BusinessAssert.assertNotEmpty(request.getTrans(), "运输方式【trans】不能为空！");
            BusinessAssert.assertNotEmpty(request.getChangeFlag(), "换单标志【changeFlag】不能为空！");
            BusinessAssert.assertNotEmpty(request.getTradeMode(), "贸易模式【tradeMode】不能为空！");
            BusinessAssert.assertNotEmpty(request.getShipperName(), "发货人名称【shipperName】不能为空！");
            BusinessAssert.assertNotEmpty(request.getShipperAddress(), "发货人地址【shipperAddress】不能为空！");
            BusinessAssert.assertNotEmpty(request.getShipperPhone(), "发货人电话【shipperPhone】不能为空！");
            BusinessAssert.assertNotNull(request.getActuralPaid(), "实际支付金额【acturalPaid】不能为空！");
            if(request.getChangeFlag().equals("0")) BusinessAssert.assertNotEmpty(request.getDeliveryCode(), "运单号【deliveryCode】不能为空！");
            //货品信息
            for (BigGoodsRequestDto good : goodList) {
                BusinessAssert.assertNotEmpty(good.getIsGoodsRecord(), "货品信息-是否指定备案信息【isGoodsRecord】不能为空！");
                if (IsGoodsRecordEnum.NO.getValue().equals(good.getIsGoodsRecord())) {
                    BusinessAssert.assertNotEmpty(good.getGoodId(), "商品货号【goodId】不能为空！");
                } else if (IsGoodsRecordEnum.YES.getValue().equals(good.getIsGoodsRecord())) {
                    BusinessAssert.assertNotEmpty(good.getCopGName(), "货品信息-商品名称【copGName】不能为空！");
                    BusinessAssert.assertNotEmpty(good.getHsCode(), "货品信息-海关编码【hsCode】不能为空！");
                    BusinessAssert.assertNotEmpty(good.getSpec(), "货品信息-商品规格型号【spec】不能为空！");
                    BusinessAssert.assertNotEmpty(good.getUnit1(), "货品信息-第一法定单位【unit1】不能为空！");
                    BusinessAssert.assertNotEmpty(good.getCiqGoodsNo(), "货品信息-国检商品备案号【ciqGoodsNo】不能为空！");
                    BusinessAssert.assertNotEmpty(good.getBrand(), "货品信息-品牌【brand】不能为空！");
                    BusinessAssert.assertNotEmpty(good.getCurrency(), "货品信息-币种【currency】不能为空！");
                    BusinessAssert.assertNotEmpty(good.getQtyUnit(), "货品信息-法定计量单位【qtyUnit】不能为空！");
                    BusinessAssert.assertNotEmpty(good.getCiqAssemCountry(), "货品信息-国检原产地【ciqAssemCountry】不能为空！");
                    BusinessAssert.assertIsFalse((null == good.getDecTotal() || good.getDecTotal().compareTo(BigDecimal.ZERO) == 0), "货品信息-商品总价【decTotal】不能为空或者为0！");
                    BusinessAssert.assertIsFalse((null == good.getGrossWeight() || good.getGrossWeight().compareTo(BigDecimal.ZERO) == 0), "货品信息-毛重（Kg）【grossWeight】不能为空或者为0！");
                    BusinessAssert.assertIsFalse((null == good.getNetWeight() || good.getNetWeight().compareTo(BigDecimal.ZERO) == 0), "货品信息-净重（Kg）【netWeight】不能为空或者为0！");
                    BusinessAssert.assertIsFalse((null == good.getQty1() || good.getQty1().compareTo(BigDecimal.ZERO) == 0), "货品信息-第一法定单位数量【qty1】不能为空或者为0！");
                    BusinessAssert.assertIsFalse((StringUtils.isBlank(good.getgQty1()) || new BigDecimal(good.getgQty1()).compareTo(BigDecimal.ZERO) == 0), "货品信息-商品第一数量【gQty1】不能为空或者为0！");
                    //第二法定单位不为空则第二法定数量不为空
                    if (StringUtils.isNotBlank(good.getUnit2())) {
                        BusinessAssert.assertIsFalse((null == good.getQty2() || good.getQty2().compareTo(BigDecimal.ZERO) == 0), "货品信息-第二法定单位数量【qty2】不能为空或者为0！");
                        BusinessAssert.assertIsFalse((StringUtils.isBlank(good.getgQty2()) || new BigDecimal(good.getgQty2()).compareTo(BigDecimal.ZERO) == 0), "货品信息-商品第二数量【gQty2】不能为空或者为0！");
                    }
                }
            }
        }

        if (BigBusiTypeEnum.CC.getValue().equals(request.getBusiMode())) {//CC
            BusinessAssert.assertNotEmpty(request.getDeliveryCode(), "运单号【deliveryCode】不能为空！");
            BusinessAssert.assertNotEmpty(request.getTradeMode(), "贸易模式【tradeMode】不能为空！");
            BusinessAssert.assertNotEmpty(request.getShipperName(), "发货人名称【shipperName】不能为空！");
            BusinessAssert.assertNotEmpty(request.getCutMode(), "征免性质分类【cutMode】不能为空！");
            BusinessAssert.assertNotEmpty(request.getTransMode(), "成交方式【transMode】不能为空！");
            BusinessAssert.assertNotEmpty(request.getWrapType(), "包装种类【wrapType】不能为空！");
            BusinessAssert.assertNotEmpty(request.getGoodsInfo(), "主要货物名称【goodsInfo】不能为空！");
            for (BigGoodsRequestDto good : goodList) {
                BusinessAssert.assertNotEmpty(good.getGoodId(), "商品货号【goodId】不能为空！");
                BusinessAssert.assertNotEmpty(good.getCopGName(), "货品信息-商品名称【copGName】不能为空！");
                BusinessAssert.assertNotEmpty(good.getHsCode(), "货品信息-海关编码【hsCode】不能为空！");
                BusinessAssert.assertNotEmpty(good.getCodeTs(), "货品信息-行邮税号【codeTs】不能为空！");
                BusinessAssert.assertNotEmpty(good.getAssemCountry(), "货品信息-原厂国【assemCountry】不能为空！");
                BusinessAssert.assertNotEmpty(good.getQtyUnit(), "货品信息-法定计量单位【qtyUnit】不能为空！");
                BusinessAssert.assertNotEmpty(good.getSpec(), "货品信息-商品规格型号【spec】不能为空！");
                BusinessAssert.assertNotEmpty(good.getBrand(), "货品信息-品牌【brand】不能为空！");
                BusinessAssert.assertNotEmpty(good.getUnit1(), "货品信息-第一法定单位【unit1】不能为空！");
                BusinessAssert.assertNotEmpty(good.getUnit2(), "货品信息-第一法定单位【unit2】不能为空！");
                BusinessAssert.assertNotEmpty(good.getPackageType(), "货品信息-包装类型【packageType】不能为空！");
            }
        }
    }

    /**
     * 校验中文长度
     *
     * @param str   字符串
     * @param limit 限制长度
     * @return 是否超长
     */
    private boolean checkZhStringLength(String str, int limit) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        int length = str.getBytes().length;
        if (length > (limit * 3)) {
            return true;
        }
        return false;
    }

    /**
     * 保存订单数据
     *
     * @param request
     */
    private void saveOrderData(BigReceiveRequestDto request, OrderBig orderBig) {
        BeanUtils.copyProperties(request, orderBig);
        orderBig.setEbcCode(request.getElectricCode());
        orderBig.setEbpCode(request.getCbepcomCode());
        orderBig.setFreight(request.getFreightFcy() == null ? BigDecimal.ZERO : request.getFreightFcy());
        orderBig.setTax(request.getTaxFcy() == null ? BigDecimal.ZERO : request.getTaxFcy());
        orderBig.setDiscount(request.getDiscount() == null ? BigDecimal.ZERO : request.getDiscount());
        orderBig.setInsuredFee(request.getInsuredFee() == null ? BigDecimal.ZERO : request.getInsuredFee());
        orderBig.setOtherRate(request.getOtherRate() == null ? BigDecimal.ZERO : request.getOtherRate());
        orderBig.setOtherPayment(request.getOtherPayment() == null ? BigDecimal.ZERO : request.getOtherPayment());
        orderBig.setFcyCurrency(StringUtils.isNotBlank(request.getfCode()) ? request.getfCode() : BigConvertConstant.CURRENCY);
        orderBig.setFreightCurrency(StringUtils.isNotBlank(request.getFreightFcode()) ? request.getFreightFcode() : BigConvertConstant.CURRENCY);
        orderBig.setTaxCurrency(StringUtils.isNotBlank(request.getTaxFcode()) ? request.getTaxFcode() : BigConvertConstant.CURRENCY);
        orderBig.setOrdExcStatus(StringUtils.isNotBlank(request.getOrdExcStatus()) ? request.getOrdExcStatus() : BigConvertConstant.EXCSTATUS);
        orderBig.setOtherPaymentCurr(StringUtils.isNotBlank(request.getOtherPaymentCurr()) ? request.getOtherPaymentCurr() : BigConvertConstant.CURRENCY);
        orderBig.setOpType(StringUtils.isNotBlank(request.getOpType()) ? request.getOpType() : BigConvertConstant.VERSION);
        orderBig.setPayType(StringUtils.isNotBlank(request.getPayType()) ? request.getPayType() : BigConvertConstant.PAYTYPE);
        orderBig.setPayStatus(StringUtils.isNotBlank(request.getPayStatus()) ? request.getPayStatus() : BigConvertConstant.PAYSTATUS);
        orderBig.setPayCurr(StringUtils.isNotBlank(request.getPayCurr()) ? request.getPayCurr() : BigConvertConstant.CURRENCY);
        orderBig.setOrderDate(request.getOrderDateFmt());
        orderBig.setInputDate(request.getInputDateFmt());
        orderBig.setPayDate(request.getPayDateFmt());
        orderBig.setShipDate(request.getShipDateFmt());
        orderBig.setShopId(request.getShopId());
        //枚举转换
        orderBig.setOrderType(BigOrderTypeEnum.getValueEnum(request.getOrderType()));
        orderBig.setOrderStatus(BigOrderStatusEnum.getValueEnum(request.getOrderStatus()));
        orderBig.setBuyerIdType(ReceiveIdTypeEnum.getValueEnum(request.getBuyerIdType().toString()));
        orderBig.setCustomsType(CustomsTypeEnum.getValueEnum(request.getCustomsType()));
        orderBig.setCustomsCode(CustomsCodeEnum.getValueEnum(request.getCustomsCode()));
        //orderBig.setCiqbCode(CIQCodeEnum.getValueEnum(request.getCiqbCode()));
        orderBig.setCiqbCode(request.getCiqbCode());
        orderBig.setBusiMode(String.valueOf(request.getBusiMode()));
        orderBig.setFromEplat(request.getFromEplat() == null ? null : YesOrNoEnum.getItem(request.getFromEplat().toString()));
        orderBig.setIsStoreStrategy(request.getIsStoreStrategy() == null ? null : YesOrNoEnum.getItem(request.getIsStoreStrategy().toString()));
        orderBig.setOwnerFlag(request.getOwnerFlag() == null ? null : YesOrNoEnum.getItem(request.getOwnerFlag().toString()));
        orderBig.setVmiFlag(request.getVmiFlag() == null ? null : YesOrNoEnum.getItem(request.getVmiFlag().toString()));
        //京东多渠道的单处理
        BigJdChannelConfig channelConfig = bigJdChannelConfigService.getBigJdChannelConfigModel(request.getCbepcomCode(), request.getElectricCode(), request.getTpl());
        if (channelConfig != null) {
            //京东多渠道的单处理字段:三方平台编码+店铺编码
            orderBig.setPlatformId(channelConfig.getPlatformCode());
            orderBig.setShopCode(channelConfig.getShopCode());
            orderBig.setOrderType(BigOrderTypeEnum.DQD);//默认5-京东多渠道
        }
        //加密手机和身份证
        orderBig.setBuyerIdNumber(StringUtils.isNotBlank(request.getBuyerIdNumber()) ? AES256Util.encrypt(request.getBuyerIdNumber()) : null);
        orderBig.setBuyerTelephone(StringUtils.isNotBlank(request.getBuyerTelephone()) ? AES256Util.encrypt(request.getBuyerTelephone()) : null);
        orderBig.setShipperPhone(StringUtils.isNotBlank(request.getShipperPhone()) ? AES256Util.encrypt(request.getShipperPhone()) : null);
        //收货信息
        BigRecipientRequestDto recipient = request.getRecipient();
        orderBig.setReceiverName(recipient.getName());
        orderBig.setReceiverIdType(ReceiveIdTypeEnum.getValueEnum(recipient.getReceiveType().toString()));
        //未收到receiveNo，将buyerIdNumber赋值给receiveNo
        orderBig.setReceiverIdNumber(StringUtils.isNotBlank(recipient.getReceiveNo()) ? AES256Util.encrypt(recipient.getReceiveNo()) : AES256Util.encrypt(request.getBuyerIdNumber()));
        orderBig.setReceiverMobile(StringUtils.isNotBlank(recipient.getMobilePhone()) ? AES256Util.encrypt(recipient.getMobilePhone()) : null);
        orderBig.setReceiverPhone(recipient.getPhone());
        orderBig.setCountry(recipient.getCountry());
        orderBig.setProvince(recipient.getProvince());
        orderBig.setCity(recipient.getCity());
        orderBig.setDistrict(recipient.getDistrict());
        orderBig.setAddress(recipient.getAddress());
        orderBig.setPostCode(recipient.getPostCode());
        orderBig.setTotalFavourable(recipient.getTotalFavourable());
        orderBig.setSender(recipient.getSender());
        orderBig.setReceiver(recipient.getReceiver());
        orderBig.setCongratulations(recipient.getCongratulations());
        orderBig.setTransportDay(recipient.getTransportDay());
        orderBig.setRecipientProvincesCode(recipient.getRecipientProvincesCode());
        orderBig.setPod(recipient.getPod());
        //商品详情
        List<BigGoodsRequestDto> goodList = request.getGoodList();
        List<OrderBigItem> itemList = new ArrayList<>();
        BigDecimal grossWeightTotal = BigDecimal.ZERO;
        BigDecimal netWeightTotal = BigDecimal.ZERO;
        for (BigGoodsRequestDto good : goodList) {
            OrderBigItem bigItem = new OrderBigItem();
            BeanUtils.copyProperties(good, bigItem);
            bigItem.setProductionTime(good.getProductionTimeFmt());
            bigItem.setExpDate(good.getExpDateFmt());
            bigItem.setgQty1(StringUtils.isNotBlank(good.getgQty1()) ? new BigDecimal(good.getgQty1()) : null);
            bigItem.setgQty2(StringUtils.isNotBlank(good.getgQty2()) ? new BigDecimal(good.getgQty2()) : null);
            bigItem.setDecTotal(good.getDecTotal() == null ? BigDecimal.ZERO : good.getDecTotal());
            if (null != good.getGrossWeight()) {
                grossWeightTotal = good.getGrossWeight().add(grossWeightTotal);
            }
            if (null != good.getNetWeight()) {
                netWeightTotal = good.getNetWeight().add(netWeightTotal);
            }
            itemList.add(bigItem);
        }
        orderBig.setNetWeight(netWeightTotal);
        orderBig.setGrossWeight(grossWeightTotal);
        //保存主表
        orderBigService.insert(orderBig);
        for (OrderBigItem item : itemList) {
            item.setMainId(orderBig.getId());
            item.setOrderId(orderBig.getOrderId());
        }
        //保存商品明细
        goodsBigService.insertOrderBigItems(itemList);
    }

    @Override
    @Transactional
    public BaseResponse forwardOFC(OrderBig orderBig) {
        //报文转换
        String msg = apartCombineGoods(orderBig);//聚美组合商品拆分
        if (null != msg) {
            return BaseResponse.responseFailure(msg);
        }
        // 获取下发的配置
        TransferConfig transferConfig = transferConfigService.findByUnique(orderBig.getEbcCode(), orderBig.getEbpCode(), orderBig.getTpl(), orderBig.getCustomsCode().getValue(), orderBig.getBusiMode());
        //解析转单参数
        if (transferConfig == null  || StringUtils.isBlank(transferConfig.getConfiguration())) {
            return BaseResponse.responseFailure("订单推送OFC未找到对应转发配置");
        }
        TransferDefaultConfig defaultConfig = JacksonUtils
                .readValue(transferConfig.getConfiguration(), TransferDefaultConfig.class);
        BaseResponse response = BaseResponse.responseFailure("转单失败");
        OrderStatusEnum orderStatus = OrderStatusEnum.SEND_FAILURE;
        //转发OFC
        if (BigBusiTypeEnum.BBC.getValue().equals(Integer.parseInt(orderBig.getBusiMode()))) {
            OfcRequest ofcRequest = BigDtoConverter.convertOfc(orderBig, defaultConfig);
            response = ofcApiService.sendOrder(ofcRequest, orderBig.getOrderId(), orderBig.getCode());
        }
        if (response.isSuccess()) {
            orderStatus = OrderStatusEnum.SEND_SUCCESS;
        }
        //更新转单状态
        orderBigService.updatePushStatus(orderBig.getId(), ForwardSystemEnum.OFC.getValue(), orderStatus, response.getMessage());
        return response;
    }

    @Override
    public BaseResponse forwardOFCBC(OrderBig orderBig) {
        //报文转换
        String msg = apartCombineGoods(orderBig);//聚美组合商品拆分
        if (null != msg) {
            return BaseResponse.responseFailure(msg);
        }
        BaseResponse response = BaseResponse.responseFailure("转单失败");
        OrderStatusEnum orderStatus = OrderStatusEnum.SEND_FAILURE;
        //转发OFC-BC
        if (BigBusiTypeEnum.BC.getValue().equals(Integer.parseInt(orderBig.getBusiMode()))) {
            OfcBcRequest ofcBcRequest = BigDtoConverter.convertBcOfc(orderBig);
            response = ofcBcApiService.sendBcOrder(ofcBcRequest, orderBig.getOrderId(), orderBig.getCode());
        } else if (BigBusiTypeEnum.EXP.getValue().equals(Integer.parseInt(orderBig.getBusiMode()))) {
            OfcBcExpRequest ofcBcExpRequest = BigDtoConverter.convertBcExpOfc(orderBig);
            response = ofcBcApiService.sendBcExpOrder(ofcBcExpRequest, orderBig.getOrderId(), orderBig.getCode());
        }
        if (response.isSuccess()) {
            orderStatus = OrderStatusEnum.SEND_SUCCESS;
        }
        //更新转单状态
        orderBigService.updatePushStatus(orderBig.getId(), ForwardSystemEnum.OFC_BC.getValue(), orderStatus, response.getMessage());
        return response;
    }

    @Override
    @Transactional
    public BaseResponse forwardOP(OrderBig orderBig) {
        //报文转换
        String msg = apartCombineGoods(orderBig);//聚美组合商品拆分
        if (null != msg) {
            return BaseResponse.responseFailure(msg);
        }
        // 获取下发的配置
        TransferConfig transferConfig = transferConfigService.findByUnique(orderBig.getEbcCode(), orderBig.getEbpCode(), orderBig.getTpl(), orderBig.getCustomsCode().getValue(), orderBig.getBusiMode());
        TransferDefaultConfig defaultConfig = JacksonUtils
                .readValue(transferConfig.getConfiguration(), TransferDefaultConfig.class);
        OpRequest opRequest = BigDtoConverter.convertOP(orderBig, defaultConfig);
        //转发OP
        BaseResponse response = opApiService.sendOrder(opRequest, orderBig.getOrderId(), orderBig.getCode());
        OrderStatusEnum orderStatus = OrderStatusEnum.SEND_FAILURE;
        if (response.isSuccess()) {
            orderStatus = OrderStatusEnum.SEND_SUCCESS;
        }
        //更新转单状态
        orderBigService.updatePushStatus(orderBig.getId(), ForwardSystemEnum.OP.getValue(), orderStatus, response.getMessage());
        return response;
    }

    /**
     * 聚美组合商品拆分
     *
     * @param orderBig
     */
    private String apartCombineGoods(OrderBig orderBig) {
        String msg = null;
        for (OrderBigItem item : orderBig.getOrderBigItemList()) {
            String goodId = item.getGoodId();
            if (StringUtils.isBlank(goodId)) {
                continue;
            }
            List<CombineGoodsConfig> configList = combineGoodsConfigService.selectByCombineCode(goodId);
            if (configList.size() == 0) {
                //不需拆分
            } else if (configList.size() == 1) {
                item.setGoodId(configList.get(0).getItemCode());
                item.setAmount(item.getAmount() * (configList.get(0).getApartQty()));
                item.setPrice(item.getDecTotal().divide(new BigDecimal(item.getAmount()), 4, BigDecimal.ROUND_HALF_UP));
            } else if (configList.size() > 1) {
                msg = "组合商品编码：" + goodId + " 在配置表中对应多个商品编码！";
                break;
            }
        }
        return msg;
    }

    @Override
    public String rePush(List<Long> ids) {
        StringBuilder msg = new StringBuilder();
        List<OrderBig> orderBigList = orderBigService.selectByIds(ids);
        orderBigList.forEach(orderBig -> {
            //转发成功的订单才允许重新推送
            if (!OrderStatusEnum.SEND_SUCCESS.equals(orderBig.getPushStatus())) {
                msg.append(orderBig.getOrderId()).append(PunctuateConstant.COMMA);
                return;
            }
            //重新发送转单MQ
            QueueConstants.QueueEnum queueEnum = QueueConstants.QueueEnum.OCP_BIG_ORDER_PUSH_OFC;
            if (BigBusiTypeEnum.BC.getValue().equals(Integer.parseInt(orderBig.getBusiMode())) || BigBusiTypeEnum.EXP.getValue().equals(Integer.parseInt(orderBig.getBusiMode()))) {
                queueEnum = QueueConstants.QueueEnum.OCP_BIG_ORDER_PUSH_OFCBC;
            }
            TransferConfig transferConfig = transferConfigService.findByUnique(orderBig.getEbcCode(), orderBig.getEbpCode(), orderBig.getTpl(), orderBig.getCustomsCode().getValue(), String.valueOf(orderBig.getBusiMode()));
            if (null != transferConfig) {
                queueEnum = QueueConstants.QueueEnum.getQueueEnumByName(QUEUE_PREFIX + transferConfig.getForwardSystem().getQueue());
            }
            messageSender.send(queueEnum, new BasicMessage(orderBig.getId(), orderBig.getCode()));
        });
        return StringUtils.isEmpty(msg) ? msg.toString() : msg.append("订单当前状态不允许重推！").toString();
    }

    /**
     * 大订单处理
     * @param orderTemp
     */
    @Override
    @Transactional
    public void processOrder(OrderTemp orderTemp) {
        BigReceiveRequestDto bigDto = JacksonUtils
                .readValue(orderTemp.getJson(), BigReceiveRequestDto.class);
        BusinessAssert.assertNotNull(bigDto, "订单解析失败");
        Platform platform = platformService.findByCode(bigDto.getCbepcomCode());
        BusinessAssert.assertNotNull(platform, "该平台接单配置未配置！");
        List<Platform> platformList = new ArrayList<>();
        platformList.add(platform);
        List<CatchOrderConfig> catchOrderConfigs = catchOrderConfigService
                .selectByPlatform(platformList);
        BusinessAssert.assertNotEmpty(catchOrderConfigs, "该平台接单配置未配置！");

        //删除临时表的记录
        orderTempService.deleteById(orderTemp.getId());

        OrderBig orderBig = new OrderBig();
        saveOrderData(bigDto, orderBig);

        //发送转单MQ
        QueueConstants.QueueEnum queueEnum = QueueConstants.QueueEnum.OCP_BIG_ORDER_PUSH_OFC;
        if (BigBusiTypeEnum.BC.getValue().equals(Integer.parseInt(orderBig.getBusiMode())) || BigBusiTypeEnum.EXP.getValue().equals(Integer.parseInt(orderBig.getBusiMode()))) {
            queueEnum = QueueConstants.QueueEnum.OCP_BIG_ORDER_PUSH_OFCBC;
        }
        TransferConfig transferConfig = transferConfigService.findByUnique(orderBig.getEbcCode(), orderBig.getEbpCode(), orderBig.getTpl(), orderBig.getCustomsCode().getValue(), String.valueOf(orderBig.getBusiMode()));
        BusinessAssert.assertNotNull(transferConfig,"订单找不到转单配置！");
        queueEnum = QueueConstants.QueueEnum.getQueueEnumByName(QUEUE_PREFIX + transferConfig.getForwardSystem().getQueue());
        messageSender.send(queueEnum, new BasicMessage(orderBig.getId(), orderBig.getCode()));

    }

}

package com.topideal.supplychain.ocp.order.model;

import com.topideal.supplychain.common.model.BaseEntity;
import com.topideal.supplychain.dict.DataDict;
import com.topideal.supplychain.enumeration.YesOrNoEnum;
import com.topideal.supplychain.ocp.enums.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 标题：大订单 实体Bean
 * 模块：ocp-parent
 * 版权: Copyright © 2019 topideal
 * 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.order.model
 * 作者：songping
 * 创建日期：2019/12/19 13:45
 *
 * @version 1.0
 */
public class OrderBig extends BaseEntity {

    /**
     * id集合
     */
    private List<Long> ids;

    /**
     * 推单状态
     */
    private OrderStatusEnum pushStatus;

    /**
     * 推单目标系统
     */
    private String pushSystem;

    /**
     * 推单备注
     */
    private String pushNotes;

    /**
     * 订单内码
     */
    private String code;

    /**
     * 企业订单编号
     */
    private String orderId;

    /**
     * 订单生成时间
     */
    private Date orderDate;

    /**
     * 包材编码
     */
    private String packingMaterial;

    /**
     * 仓库ID
     */
    private String warehouseId;

    /**
     * 第三方物流商编码
     */
    private String tpl;

    /**
     * 是否自运营订单（1:非自营;2:自营;3:ECLP;4:其他;5:多渠道;6:独立站;7:云霄）
     */
    private BigOrderTypeEnum orderType;

    /**
     * 订单状态（S-新增）
     */
    private BigOrderStatusEnum orderStatus;

    /**
     * 海关类型（1-总署版;2-2.0版）
     */
    private CustomsTypeEnum customsType;

    /**
     * 电商企业编码
     */
    private String ebcCode;

    /**
     * 电商平台编码
     */
    private String ebpCode;

    /**
     * 运单号
     */
    private String deliveryCode;

    /**
     * 备注
     */
    private String notes;

    /**
     * 运费
     */
    private BigDecimal freight;

    /**
     * 运费币种
     */
    private String freightCurrency;

    /**
     * 保费
     */
    private BigDecimal insuredFee;

    /**
     * 税费
     */
    private BigDecimal tax;

    /**
     * 税费币种
     */
    private String taxCurrency;

    /**
     * 货款币制
     */
    private String fcyCurrency;

    /**
     * 优惠减免金额
     */
    private BigDecimal discount;

    /**
     * 订购人姓名
     */
    private String buyerName;

    /**
     * 订购人证件类型
     */
    private ReceiveIdTypeEnum buyerIdType;

    /**
     * 订购人证件号
     */
    private String buyerIdNumber;

    /**
     * 订购人电话
     */
    private String buyerTelephone;

    /**
     * 订购人注册号
     */
    private String buyerRegNo;

    /**
     * 毛重（公斤）
     */
    private BigDecimal grossWeight;

    /**
     * 净重（公斤）
     */
    private BigDecimal netWeight;

    /**
     * 运营模式
     */
    /**
     * 运营模式
     */
    @DataDict(dict = "business.mode",source = "busiMode", split = ",")
    private String busiModeName;
    private String busiMode;

    /**
     * 申报关区
     */
    private CustomsCodeEnum customsCode;

    /**
     * 申报国检
     */
    private String ciqbCode;

    /**
     * 申报场站
     */
    private String stationbCode;

    /**
     * 申报业务类型
     */
    private String statisticsFlag;

    /**
     * 商品备案地国检
     */
    private String bakbCode;

    /**
     * 国际运单号
     */
    private String logisticsNo;

    /**
     * 执行状态
     */
    private String ordExcStatus;

    /**
     * 国外卖方
     */
    private String forSellComp;

    /**
     * 国外卖方名称
     */
    private String forSellCompName;

    /**
     * 外贸经营单位码
     */
    private String tradeUnitCode;

    /**
     * 外贸经营单位名称
     */
    private String tradeUnitName;

    /**
     * 发货人所在国
     */
    private String shippernCode;

    /**
     * 支付日期
     */
    private Date payDate;

    /**
     * 发货日期
     */
    private Date shipDate;

    /**
     * 录入日期
     */
    private Date inputDate;

    /**
     * 支付信息备注
     */
    private String payNots;

    /**
     * 物流信息备注
     */
    private String logNots;

    /**
     * 提单号
     */
    private String blno;

    /**
     * 运输方式
     */
    private String trans;

    /**
     * 运输工具名称
     */
    private String transNo;

    /**
     * 换单标志（换成国内落地配运单）：1-需要；0-不需要
     */
    private String changeFlag;

    /**
     * 重报标识：0-否;1-是
     */
    private Integer reDeclare;

    /**
     * 贸易模式：1：跨境模式；2：一般贸易
     */
    private String tradeMode;

    /**
     * 发货人名称
     */
    private String shipperName;

    /**
     * 发货人地址
     */
    private String shipperAddress;

    /**
     * 发货人电话
     */
    private String shipperPhone;

    /**
     * 保费币制
     */
    private String insurCurr;

    /**
     * 预留字段
     */
    private String insurMark;

    /**
     * 杂费币制
     */
    private String otherCurr;

    /**
     * 杂费
     */
    private BigDecimal otherRate;

    /**
     * 预留字段
     */
    private String otherMark;

    /**
     * 抵付币制
     */
    private String otherPaymentCurr;

    /**
     * 抵付金额
     */
    private BigDecimal otherPayment;

    /**
     * 支付流水号
     */
    private String payNo;

    /**
     * 支付企业名称
     */
    private String payPcomName;

    /**
     * 支付企业编码
     */
    private String payCopNo;

    /**
     * 报关企业代码
     */
    private String agentCode;

    /**
     * 报关企业名称
     */
    private String agentName;

    /**
     * 申报类型
     */
    private String opType;

    /**
     * 交易支付类型
     */
    private String payType;

    /**
     * 支付交易状态
     */
    private String payStatus;

    /**
     * 支付币制
     */
    private String payCurr;

    /**
     * 付款人
     */
    private String payorName;

    /**
     * 实际支付企业
     */
    private String activePayComp;

    /**
     * 实际支付金额
     */
    private BigDecimal acturalPaid;

    /**
     * 来源第e仓：0-否;1-是
     */
    private YesOrNoEnum fromEplat;

    /**
     * 打印抬头
     */
    private String printHeader;

    /**
     * 通用字段
     */
    private String commonField;

    /**
     * 是否校验库存策略
     */
    private YesOrNoEnum isStoreStrategy;

    /**
     * 是否VMI模式
     */
    private YesOrNoEnum vmiFlag;

    /**
     * 是否一单多业主
     */
    private YesOrNoEnum ownerFlag;

    /**
     * 征免性质分类
     */
    private String cutMode;

    /**
     * 成交方式
     */
    private String transMode;

    /**
     * 件数
     */
    private Integer packNo;

    /**
     * 发货订单批次
     */
    private String orderBatchNo;

    /**
     * 包装种类
     */
    private String wrapType;

    /**
     * 发件人城市
     */
    private String sendCity;

    /**
     * 总货值
     */
    private BigDecimal totalValue;

    /**
     * 主要货物名称
     */
    private String goodsInfo;

    /**
     * 店铺ID
     */
    private String shopId;

    /**
     * 是否商品备案
     */
    private String isGoodsRecord;

    /**
     * 第三方平台ID
     */
    private String platformId;

    /**
     * 店铺编码
     */
    private String shopCode;

    /**
     * 收货人姓名
     */
    private String receiverName;

    /**
     * 收货人证件类型
     */
    private ReceiveIdTypeEnum receiverIdType;

    /**
     * 收货人证件号
     */
    private String receiverIdNumber;

    /**
     * 收货人手机
     */
    private String receiverMobile;

    /**
     * 收货人座机
     */
    private String receiverPhone;

    /**
     * 国家
     */
    private String country;

    /**
     * 指运港代码
     */
    private String pod;

    /**
     * 省份
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区/县
     */
    private String district;

    /**
     * 地址
     */
    private String address;

    /**
     * 邮编
     */
    private String postCode;

    /**
     * 订单优惠金额
     */
    private BigDecimal totalFavourable;

    /**
     * 送礼人
     */
    private String sender;

    /**
     * 收礼人
     */
    private String receiver;

    /**
     * 祝贺语
     */
    private String congratulations;

    /**
     * 配送时间
     */
    private String transportDay;

    /**
     * 收货人省市代码
     */
    private String recipientProvincesCode;

    /**
     * 商品明细
     */
    private List<OrderBigItem> orderBigItemList;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public OrderStatusEnum getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(OrderStatusEnum pushStatus) {
        this.pushStatus = pushStatus;
    }

    public String getPushSystem() {
        return pushSystem;
    }

    public void setPushSystem(String pushSystem) {
        this.pushSystem = pushSystem;
    }

    public String getPushNotes() {
        return pushNotes;
    }

    public void setPushNotes(String pushNotes) {
        this.pushNotes = pushNotes;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getPackingMaterial() {
        return packingMaterial;
    }

    public void setPackingMaterial(String packingMaterial) {
        this.packingMaterial = packingMaterial;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getTpl() {
        return tpl;
    }

    public void setTpl(String tpl) {
        this.tpl = tpl;
    }

    public BigOrderTypeEnum getOrderType() {
        return orderType;
    }

    public void setOrderType(BigOrderTypeEnum orderType) {
        this.orderType = orderType;
    }

    public BigOrderStatusEnum getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(BigOrderStatusEnum orderStatus) {
        this.orderStatus = orderStatus;
    }

    public CustomsTypeEnum getCustomsType() {
        return customsType;
    }

    public void setCustomsType(CustomsTypeEnum customsType) {
        this.customsType = customsType;
    }

    public String getEbcCode() {
        return ebcCode;
    }

    public void setEbcCode(String ebcCode) {
        this.ebcCode = ebcCode;
    }

    public String getEbpCode() {
        return ebpCode;
    }

    public void setEbpCode(String ebpCode) {
        this.ebpCode = ebpCode;
    }

    public String getDeliveryCode() {
        return deliveryCode;
    }

    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public String getFreightCurrency() {
        return freightCurrency;
    }

    public void setFreightCurrency(String freightCurrency) {
        this.freightCurrency = freightCurrency;
    }

    public BigDecimal getInsuredFee() {
        return insuredFee;
    }

    public void setInsuredFee(BigDecimal insuredFee) {
        this.insuredFee = insuredFee;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public String getTaxCurrency() {
        return taxCurrency;
    }

    public void setTaxCurrency(String taxCurrency) {
        this.taxCurrency = taxCurrency;
    }

    public String getFcyCurrency() {
        return fcyCurrency;
    }

    public void setFcyCurrency(String fcyCurrency) {
        this.fcyCurrency = fcyCurrency;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public ReceiveIdTypeEnum getBuyerIdType() {
        return buyerIdType;
    }

    public void setBuyerIdType(ReceiveIdTypeEnum buyerIdType) {
        this.buyerIdType = buyerIdType;
    }

    public String getBuyerIdNumber() {
        return buyerIdNumber;
    }

    public void setBuyerIdNumber(String buyerIdNumber) {
        this.buyerIdNumber = buyerIdNumber;
    }

    public String getBuyerTelephone() {
        return buyerTelephone;
    }

    public void setBuyerTelephone(String buyerTelephone) {
        this.buyerTelephone = buyerTelephone;
    }

    public String getBuyerRegNo() {
        return buyerRegNo;
    }

    public void setBuyerRegNo(String buyerRegNo) {
        this.buyerRegNo = buyerRegNo;
    }

    public BigDecimal getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(BigDecimal grossWeight) {
        this.grossWeight = grossWeight;
    }

    public BigDecimal getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(BigDecimal netWeight) {
        this.netWeight = netWeight;
    }

    public String getBusiModeName() {
        return busiModeName;
    }

    public void setBusiModeName(String busiModeName) {
        this.busiModeName = busiModeName;
    }

    public String getBusiMode() {
        return busiMode;
    }

    public void setBusiMode(String busiMode) {
        this.busiMode = busiMode;
    }

    public CustomsCodeEnum getCustomsCode() {
        return customsCode;
    }

    public void setCustomsCode(CustomsCodeEnum customsCode) {
        this.customsCode = customsCode;
    }

    public String getCiqbCode() {
        return ciqbCode;
    }

    public void setCiqbCode(String ciqbCode) {
        this.ciqbCode = ciqbCode;
    }

    public String getStationbCode() {
        return stationbCode;
    }

    public void setStationbCode(String stationbCode) {
        this.stationbCode = stationbCode;
    }

    public String getStatisticsFlag() {
        return statisticsFlag;
    }

    public void setStatisticsFlag(String statisticsFlag) {
        this.statisticsFlag = statisticsFlag;
    }

    public String getBakbCode() {
        return bakbCode;
    }

    public void setBakbCode(String bakbCode) {
        this.bakbCode = bakbCode;
    }

    public String getLogisticsNo() {
        return logisticsNo;
    }

    public void setLogisticsNo(String logisticsNo) {
        this.logisticsNo = logisticsNo;
    }

    public String getOrdExcStatus() {
        return ordExcStatus;
    }

    public void setOrdExcStatus(String ordExcStatus) {
        this.ordExcStatus = ordExcStatus;
    }

    public String getForSellComp() {
        return forSellComp;
    }

    public void setForSellComp(String forSellComp) {
        this.forSellComp = forSellComp;
    }

    public String getForSellCompName() {
        return forSellCompName;
    }

    public void setForSellCompName(String forSellCompName) {
        this.forSellCompName = forSellCompName;
    }

    public String getTradeUnitCode() {
        return tradeUnitCode;
    }

    public void setTradeUnitCode(String tradeUnitCode) {
        this.tradeUnitCode = tradeUnitCode;
    }

    public String getTradeUnitName() {
        return tradeUnitName;
    }

    public void setTradeUnitName(String tradeUnitName) {
        this.tradeUnitName = tradeUnitName;
    }

    public String getShippernCode() {
        return shippernCode;
    }

    public void setShippernCode(String shippernCode) {
        this.shippernCode = shippernCode;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    public String getPayNots() {
        return payNots;
    }

    public void setPayNots(String payNots) {
        this.payNots = payNots;
    }

    public String getLogNots() {
        return logNots;
    }

    public void setLogNots(String logNots) {
        this.logNots = logNots;
    }

    public String getBlno() {
        return blno;
    }

    public void setBlno(String blno) {
        this.blno = blno;
    }

    public String getTrans() {
        return trans;
    }

    public void setTrans(String trans) {
        this.trans = trans;
    }

    public String getTransNo() {
        return transNo;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo;
    }

    public String getChangeFlag() {
        return changeFlag;
    }

    public void setChangeFlag(String changeFlag) {
        this.changeFlag = changeFlag;
    }

    public Integer getReDeclare() {
        return reDeclare;
    }

    public void setReDeclare(Integer reDeclare) {
        this.reDeclare = reDeclare;
    }

    public String getTradeMode() {
        return tradeMode;
    }

    public void setTradeMode(String tradeMode) {
        this.tradeMode = tradeMode;
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public String getShipperAddress() {
        return shipperAddress;
    }

    public void setShipperAddress(String shipperAddress) {
        this.shipperAddress = shipperAddress;
    }

    public String getShipperPhone() {
        return shipperPhone;
    }

    public void setShipperPhone(String shipperPhone) {
        this.shipperPhone = shipperPhone;
    }

    public String getInsurCurr() {
        return insurCurr;
    }

    public void setInsurCurr(String insurCurr) {
        this.insurCurr = insurCurr;
    }

    public String getInsurMark() {
        return insurMark;
    }

    public void setInsurMark(String insurMark) {
        this.insurMark = insurMark;
    }

    public String getOtherCurr() {
        return otherCurr;
    }

    public void setOtherCurr(String otherCurr) {
        this.otherCurr = otherCurr;
    }

    public BigDecimal getOtherRate() {
        return otherRate;
    }

    public void setOtherRate(BigDecimal otherRate) {
        this.otherRate = otherRate;
    }

    public String getOtherMark() {
        return otherMark;
    }

    public void setOtherMark(String otherMark) {
        this.otherMark = otherMark;
    }

    public String getOtherPaymentCurr() {
        return otherPaymentCurr;
    }

    public void setOtherPaymentCurr(String otherPaymentCurr) {
        this.otherPaymentCurr = otherPaymentCurr;
    }

    public BigDecimal getOtherPayment() {
        return otherPayment;
    }

    public void setOtherPayment(BigDecimal otherPayment) {
        this.otherPayment = otherPayment;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public String getPayPcomName() {
        return payPcomName;
    }

    public void setPayPcomName(String payPcomName) {
        this.payPcomName = payPcomName;
    }

    public String getPayCopNo() {
        return payCopNo;
    }

    public void setPayCopNo(String payCopNo) {
        this.payCopNo = payCopNo;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getPayCurr() {
        return payCurr;
    }

    public void setPayCurr(String payCurr) {
        this.payCurr = payCurr;
    }

    public String getPayorName() {
        return payorName;
    }

    public void setPayorName(String payorName) {
        this.payorName = payorName;
    }

    public String getActivePayComp() {
        return activePayComp;
    }

    public void setActivePayComp(String activePayComp) {
        this.activePayComp = activePayComp;
    }

    public BigDecimal getActuralPaid() {
        return acturalPaid;
    }

    public void setActuralPaid(BigDecimal acturalPaid) {
        this.acturalPaid = acturalPaid;
    }

    public YesOrNoEnum getFromEplat() {
        return fromEplat;
    }

    public void setFromEplat(YesOrNoEnum fromEplat) {
        this.fromEplat = fromEplat;
    }

    public String getPrintHeader() {
        return printHeader;
    }

    public void setPrintHeader(String printHeader) {
        this.printHeader = printHeader;
    }

    public String getCommonField() {
        return commonField;
    }

    public void setCommonField(String commonField) {
        this.commonField = commonField;
    }

    public YesOrNoEnum getIsStoreStrategy() {
        return isStoreStrategy;
    }

    public void setIsStoreStrategy(YesOrNoEnum isStoreStrategy) {
        this.isStoreStrategy = isStoreStrategy;
    }

    public YesOrNoEnum getVmiFlag() {
        return vmiFlag;
    }

    public void setVmiFlag(YesOrNoEnum vmiFlag) {
        this.vmiFlag = vmiFlag;
    }

    public YesOrNoEnum getOwnerFlag() {
        return ownerFlag;
    }

    public void setOwnerFlag(YesOrNoEnum ownerFlag) {
        this.ownerFlag = ownerFlag;
    }

    public String getCutMode() {
        return cutMode;
    }

    public void setCutMode(String cutMode) {
        this.cutMode = cutMode;
    }

    public String getTransMode() {
        return transMode;
    }

    public void setTransMode(String transMode) {
        this.transMode = transMode;
    }

    public Integer getPackNo() {
        return packNo;
    }

    public void setPackNo(Integer packNo) {
        this.packNo = packNo;
    }

    public String getOrderBatchNo() {
        return orderBatchNo;
    }

    public void setOrderBatchNo(String orderBatchNo) {
        this.orderBatchNo = orderBatchNo;
    }

    public String getWrapType() {
        return wrapType;
    }

    public void setWrapType(String wrapType) {
        this.wrapType = wrapType;
    }

    public String getSendCity() {
        return sendCity;
    }

    public void setSendCity(String sendCity) {
        this.sendCity = sendCity;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public String getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(String goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getIsGoodsRecord() {
        return isGoodsRecord;
    }

    public void setIsGoodsRecord(String isGoodsRecord) {
        this.isGoodsRecord = isGoodsRecord;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public ReceiveIdTypeEnum getReceiverIdType() {
        return receiverIdType;
    }

    public void setReceiverIdType(ReceiveIdTypeEnum receiverIdType) {
        this.receiverIdType = receiverIdType;
    }

    public String getReceiverIdNumber() {
        return receiverIdNumber;
    }

    public void setReceiverIdNumber(String receiverIdNumber) {
        this.receiverIdNumber = receiverIdNumber;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public BigDecimal getTotalFavourable() {
        return totalFavourable;
    }

    public void setTotalFavourable(BigDecimal totalFavourable) {
        this.totalFavourable = totalFavourable;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getCongratulations() {
        return congratulations;
    }

    public void setCongratulations(String congratulations) {
        this.congratulations = congratulations;
    }

    public String getTransportDay() {
        return transportDay;
    }

    public void setTransportDay(String transportDay) {
        this.transportDay = transportDay;
    }

    public String getRecipientProvincesCode() {
        return recipientProvincesCode;
    }

    public void setRecipientProvincesCode(String recipientProvincesCode) {
        this.recipientProvincesCode = recipientProvincesCode;
    }

    public List<OrderBigItem> getOrderBigItemList() {
        return orderBigItemList;
    }

    public void setOrderBigItemList(List<OrderBigItem> orderBigItemList) {
        this.orderBigItemList = orderBigItemList;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
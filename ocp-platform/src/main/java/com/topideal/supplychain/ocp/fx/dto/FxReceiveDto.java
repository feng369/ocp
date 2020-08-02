package com.topideal.supplychain.ocp.fx.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.topideal.supplychain.util.DateUtils;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 标题：大订单接单请求DTO 模块：ocp-parent 版权: Copyright © 2019 topideal 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.big.dto 作者：songping 创建日期：2019/12/26 17:23
 *
 * @version 1.0
 */
public class FxReceiveDto implements Serializable {

    /*private static DateFormat   df1	= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static DateFormat	df2	= new SimpleDateFormat("yyyy-MM-dd");*/
    /**
     * 企业订单编号
     */
    private String orderId;
    /**
     * 订单生成时间
     */
    private String orderDate;
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
     * 是否自运营订单
     */
    private Integer orderType;
    /**
     * 海关类型
     */
    private Integer customsType;
    /**
     * 电商企业编码
     */
    private String electricCode;
    /**
     * 电商平台编码
     */
    private String cbepcomCode;
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
    private BigDecimal freightFcy;
    /**
     * 运费币制
     */
    private String freightFcode;
    /**
     * 保费
     */
    private BigDecimal insuredFee;
    /**
     * 税费
     */
    private BigDecimal taxFcy;
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
    private Integer buyerIdType;
    /**
     * 订购人证件号码
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
     * 订单状态
     */
    private String orderStatus;
    /**
     * 进口模式
     */
    private Integer busiMode;
    /**
     * 申报关区
     */
    private String customsCode;
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
     * 货款币制
     */
    private String fCode;
    /**
     * 税费币种
     */
    private String taxFcode;
    /**
     * 支付日期
     */
    private String payDate;
    /**
     * 发货日期
     */
    private String shipDate;
    /**
     * 录入日期
     */
    private String inputDate;
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
     * 换单标志
     */
    private String changeFlag;
    /**
     * 重报标识
     */
    private Integer reDeclare;
    /**
     * 贸易模式
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
     * (预留字段)
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
     * (预留字段)
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
     * 支付类型
     */
    private String payType;
    /**
     * 支付状态
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
     * 来源第e仓
     */
    private Integer fromEplat;
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
    private Integer isStoreStrategy;
    /**
     * 是否VMI模式
     */
    private Integer vmiFlag;
    /**
     * 是否一单多业主
     */
    private Integer ownerFlag;
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
     * 包装种类
     */
    private String wrapType;
    /**
     * 发件人城市
     */
    private String sendCity;
    /**
     * 货值
     */
    private BigDecimal totalValue;
    /**
     * 主要货物名称
     */
    private String goodsInfo;
    /**
     * 发货订单批次
     */
    private String orderBatchNo;
    /**
     * 店铺ID
     */
    @JsonAlias({"shipNo","shopId","shipid"})
    private String shopId;

    private FxRecipientDto recipient;
    private List<FxGoodsDto> goodList;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    @JsonIgnore
    public Date getOrderDateFmt() {
        if (orderDate != null && orderDate.trim().length() > 0) {
                return DateUtils.formatStringToDate(orderDate,"");

        }
        return null;
    }

    public void setOrderDate(String orderDate) {
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

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getCustomsType() {
        return customsType;
    }

    public void setCustomsType(Integer customsType) {
        this.customsType = customsType;
    }

    public String getElectricCode() {
        return electricCode;
    }

    public void setElectricCode(String electricCode) {
        this.electricCode = electricCode;
    }

    public String getCbepcomCode() {
        return cbepcomCode;
    }

    public void setCbepcomCode(String cbepcomCode) {
        this.cbepcomCode = cbepcomCode;
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

    public BigDecimal getFreightFcy() {
        return freightFcy;
    }

    public void setFreightFcy(BigDecimal freightFcy) {
        this.freightFcy = freightFcy;
    }

    public String getFreightFcode() {
        return freightFcode;
    }

    public void setFreightFcode(String freightFcode) {
        this.freightFcode = freightFcode;
    }

    public BigDecimal getInsuredFee() {
        return insuredFee;
    }

    public void setInsuredFee(BigDecimal insuredFee) {
        this.insuredFee = insuredFee;
    }

    public BigDecimal getTaxFcy() {
        return taxFcy;
    }

    public void setTaxFcy(BigDecimal taxFcy) {
        this.taxFcy = taxFcy;
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

    public Integer getBuyerIdType() {
        return buyerIdType;
    }

    public void setBuyerIdType(Integer buyerIdType) {
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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getBusiMode() {
        return busiMode;
    }

    public void setBusiMode(Integer busiMode) {
        this.busiMode = busiMode;
    }

    public String getCustomsCode() {
        return customsCode;
    }

    public void setCustomsCode(String customsCode) {
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

    public String getfCode() {
        return fCode;
    }

    public void setfCode(String fCode) {
        this.fCode = fCode;
    }

    public String getTaxFcode() {
        return taxFcode;
    }

    public void setTaxFcode(String taxFcode) {
        this.taxFcode = taxFcode;
    }

    public String getPayDate() {
        return payDate;
    }

    @JsonIgnore
    public Date getPayDateFmt() {
        if (payDate != null && payDate.trim().length() > 0) {
            return DateUtils.formatStringToDate(payDate, "");

        }
        return null;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getShipDate() {
        return shipDate;
    }

    @JsonIgnore
    public Date getShipDateFmt() {
        if (shipDate != null && shipDate.trim().length() > 0) {
            return DateUtils.formatStringToDate(shipDate, "");

        }
        return null;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public String getInputDate() {
        return inputDate;
    }

    @JsonIgnore
    public Date getInputDateFmt() {
        if (inputDate != null && inputDate.trim().length() > 0) {
            return DateUtils.formatStringToDate(inputDate, "");

        }
        return null;
    }

    public void setInputDate(String inputDate) {
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

    public Integer getFromEplat() {
        return fromEplat;
    }

    public void setFromEplat(Integer fromEplat) {
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

    public Integer getIsStoreStrategy() {
        return isStoreStrategy;
    }

    public void setIsStoreStrategy(Integer isStoreStrategy) {
        this.isStoreStrategy = isStoreStrategy;
    }

    public Integer getVmiFlag() {
        return vmiFlag;
    }

    public void setVmiFlag(Integer vmiFlag) {
        this.vmiFlag = vmiFlag;
    }

    public Integer getOwnerFlag() {
        return ownerFlag;
    }

    public void setOwnerFlag(Integer ownerFlag) {
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

    public String getOrderBatchNo() {
        return orderBatchNo;
    }

    public void setOrderBatchNo(String orderBatchNo) {
        this.orderBatchNo = orderBatchNo;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public FxRecipientDto getRecipient() {
        return recipient;
    }

    public void setRecipient(FxRecipientDto recipient) {
        this.recipient = recipient;
    }

    public List<FxGoodsDto> getGoodList() {
        return goodList;
    }

    public void setGoodList(List<FxGoodsDto> goodList) {
        this.goodList = goodList;
    }
}

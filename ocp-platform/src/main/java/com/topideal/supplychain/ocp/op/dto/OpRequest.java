package com.topideal.supplychain.ocp.op.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @ClassName OpRequest
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2019/12/9 14:14
 * @Version 1.0
 **/
@JsonInclude(Include.NON_NULL)
public class OpRequest implements Serializable {

    private String orderId;//企业订单编号
    private Date orderDate;//订单生成时间
    private String packingMaterial;//包材编码
    private String warehouseId;//仓库ID，
    private String tpl;//第三方物流商编码
    private Integer orderType;//是否自运营订单
    private String orderStatus;//订单状态
    private Integer customsType;//海关类型
    private String electricCode;//电商企业编码
    private String cbepcomCode;//电商平台编码
    private Integer busiMode;//进口模式
    private String customsCode;//申报关区
    private String ciqbCode;//申报国检
    private String stationbCode;//申报场站
    private String deliveryCode;//运单号
    private String notes;//备注
    private BigDecimal freightFcy;//运费
    private String freightFcode;//运费币制
    private BigDecimal insuredFee;//保费
    private String insurCurr;//保费币制
    private String insurMark;//(预留字段)
    private BigDecimal taxFcy;//税费
    private String taxFcode;//税费币种
    private BigDecimal otherRate;//杂费
    private String otherCurr;//杂费币制
    private String otherMark;//(预留字段)
    private BigDecimal otherPayment;//抵付金额
    private String otherPaymentCurr;//抵付币制
    private String fCode;//货款币制
    private BigDecimal discount;//优惠减免金额
    private String buyerName;//订购人姓名
    private Integer buyerIdType;//订购人证件类型
    private String buyerIdNumber;//订购人证件号码
    private String buyerTelephone;//订购人电话
    private String buyerRegNo;//订购人注册号
    private BigDecimal grossWeight;//毛重（公斤）
    private BigDecimal netWeight;//净重（公斤）
    private String bakbCode;//商品备案地国检
    private String ordExcStatus;//执行状态
    private String forSellComp;//国外卖方
    private String forSellCompName;//国外卖方名称
    private String tradeUnitCode;//外贸经营单位码
    private String tradeUnitName;//外贸经营单位名称
    private String shippernCode;//发货人所在国
    private Date shipDate;//发货日期
    private Date inputDate;//录入日期
    private String logNots;//物流信息备注
    private String blno;//提单号
    private String trans;//运输方式
    private String transNo;//运输工具名称
    private String changeFlag;//换单标志
    private String tradeMode;//贸易模式
    private String shipperName;//发货人名称
    private String shipperAddress;//发货人地址
    private String shipperPhone;//发货人电话
    private String agentCode;//报关企业代码
    private String agentName;//报关企业名称
    private String payNo;//支付流水号
    private String payPcomName;//支付企业名称
    private String payCopNo;//支付企业编码
    private String opType;//申报类型
    private String payType;//交易支付类型
    private String payStatus;//支付交易状态
    private String payorName;//付款人
    private String activePayComp;//实际支付企业
    private BigDecimal acturalPaid;//实际支付金额
    private String payCurr;//支付币制
    private String payNots;//支付信息备注
    private Date payDate;//支付日期
    private Integer fromEplat;//来源第e仓
    private String printHeader;//打印抬头
    private String commonField;//通用字段
    private Integer isStoreStrategy;//是否校验库存策略
    private Integer vmiFlag;//是否VMI模式
    private Integer ownerFlag;//是否一单多业主
    private String cutMode;//征免性质分类
    private String transMode;//成交方式
    private Integer packNo;//件数
    private String wrapType;//包装种类
    private String sendCity;//发件人城市
    private BigDecimal totalValue;//货值
    private String goodsInfo;//主要货物名称
    private String orderBatchNo;//发货订单批次
    private String reDeclare;//重报标识
    private String ebptpl;//平台物流商编码
    private OpRecipientDto recipient;
    private List<OpGoodsItemDto> goodList;

    private OpRequest(Builder builder) {
        orderId = builder.orderId;
        orderDate = builder.orderDate;
        packingMaterial = builder.packingMaterial;
        warehouseId = builder.warehouseId;
        tpl = builder.tpl;
        orderType = builder.orderType;
        orderStatus = builder.orderStatus;
        customsType = builder.customsType;
        electricCode = builder.electricCode;
        cbepcomCode = builder.cbepcomCode;
        busiMode = builder.busiMode;
        customsCode = builder.customsCode;
        ciqbCode = builder.ciqbCode;
        stationbCode = builder.stationbCode;
        deliveryCode = builder.deliveryCode;
        notes = builder.notes;
        freightFcy = builder.freightFcy;
        freightFcode = builder.freightFcode;
        insuredFee = builder.insuredFee;
        insurCurr = builder.insurCurr;
        insurMark = builder.insurMark;
        taxFcy = builder.taxFcy;
        taxFcode = builder.taxFcode;
        otherRate = builder.otherRate;
        otherCurr = builder.otherCurr;
        otherMark = builder.otherMark;
        otherPayment = builder.otherPayment;
        otherPaymentCurr = builder.otherPaymentCurr;
        fCode = builder.fCode;
        discount = builder.discount;
        buyerName = builder.buyerName;
        buyerIdType = builder.buyerIdType;
        buyerIdNumber = builder.buyerIdNumber;
        buyerTelephone = builder.buyerTelephone;
        buyerRegNo = builder.buyerRegNo;
        grossWeight = builder.grossWeight;
        netWeight = builder.netWeight;
        bakbCode = builder.bakbCode;
        ordExcStatus = builder.ordExcStatus;
        forSellComp = builder.forSellComp;
        forSellCompName = builder.forSellCompName;
        tradeUnitCode = builder.tradeUnitCode;
        tradeUnitName = builder.tradeUnitName;
        shippernCode = builder.shippernCode;
        shipDate = builder.shipDate;
        inputDate = builder.inputDate;
        logNots = builder.logNots;
        blno = builder.blno;
        trans = builder.trans;
        transNo = builder.transNo;
        changeFlag = builder.changeFlag;
        tradeMode = builder.tradeMode;
        shipperName = builder.shipperName;
        shipperAddress = builder.shipperAddress;
        shipperPhone = builder.shipperPhone;
        agentCode = builder.agentCode;
        agentName = builder.agentName;
        payNo = builder.payNo;
        payPcomName = builder.payPcomName;
        payCopNo = builder.payCopNo;
        opType = builder.opType;
        payType = builder.payType;
        payStatus = builder.payStatus;
        payorName = builder.payorName;
        activePayComp = builder.activePayComp;
        acturalPaid = builder.acturalPaid;
        payCurr = builder.payCurr;
        payNots = builder.payNots;
        payDate = builder.payDate;
        fromEplat = builder.fromEplat;
        printHeader = builder.printHeader;
        commonField = builder.commonField;
        isStoreStrategy = builder.isStoreStrategy;
        vmiFlag = builder.vmiFlag;
        ownerFlag = builder.ownerFlag;
        cutMode = builder.cutMode;
        transMode = builder.transMode;
        packNo = builder.packNo;
        wrapType = builder.wrapType;
        sendCity = builder.sendCity;
        totalValue = builder.totalValue;
        goodsInfo = builder.goodsInfo;
        orderBatchNo = builder.orderBatchNo;
        reDeclare = builder.reDeclare;
        ebptpl = builder.ebptpl;
        recipient = builder.recipient;
        goodList = builder.goodList;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {

        private String orderId;
        private Date orderDate;
        private String packingMaterial;
        private String warehouseId;
        private String tpl;
        private Integer orderType;
        private String orderStatus;
        private Integer customsType;
        private String electricCode;
        private String cbepcomCode;
        private Integer busiMode;
        private String customsCode;
        private String ciqbCode;
        private String stationbCode;
        private String deliveryCode;
        private String notes;
        private BigDecimal freightFcy;
        private String freightFcode;
        private BigDecimal insuredFee;
        private String insurCurr;
        private String insurMark;
        private BigDecimal taxFcy;
        private String taxFcode;
        private BigDecimal otherRate;
        private String otherCurr;
        private String otherMark;
        private BigDecimal otherPayment;
        private String otherPaymentCurr;
        private String fCode;
        private BigDecimal discount;
        private String buyerName;
        private Integer buyerIdType;
        private String buyerIdNumber;
        private String buyerTelephone;
        private String buyerRegNo;
        private BigDecimal grossWeight;
        private BigDecimal netWeight;
        private String bakbCode;
        private String ordExcStatus;
        private String forSellComp;
        private String forSellCompName;
        private String tradeUnitCode;
        private String tradeUnitName;
        private String shippernCode;
        private Date shipDate;
        private Date inputDate;
        private String logNots;
        private String blno;
        private String trans;
        private String transNo;
        private String changeFlag;
        private String tradeMode;
        private String shipperName;
        private String shipperAddress;
        private String shipperPhone;
        private String agentCode;
        private String agentName;
        private String payNo;
        private String payPcomName;
        private String payCopNo;
        private String opType;
        private String payType;
        private String payStatus;
        private String payorName;
        private String activePayComp;
        private BigDecimal acturalPaid;
        private String payCurr;
        private String payNots;
        private Date payDate;
        private Integer fromEplat;
        private String printHeader;
        private String commonField;
        private Integer isStoreStrategy;
        private Integer vmiFlag;
        private Integer ownerFlag;
        private String cutMode;
        private String transMode;
        private Integer packNo;
        private String wrapType;
        private String sendCity;
        private BigDecimal totalValue;
        private String goodsInfo;
        private String orderBatchNo;
        private String reDeclare;
        private String ebptpl;
        private OpRecipientDto recipient;
        private List<OpGoodsItemDto> goodList;

        private Builder() {
        }

        public Builder orderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder orderDate(Date orderDate) {
            this.orderDate = orderDate;
            return this;
        }

        public Builder packingMaterial(String packingMaterial) {
            this.packingMaterial = packingMaterial;
            return this;
        }

        public Builder warehouseId(String warehouseId) {
            this.warehouseId = warehouseId;
            return this;
        }

        public Builder tpl(String tpl) {
            this.tpl = tpl;
            return this;
        }

        public Builder orderType(Integer orderType) {
            this.orderType = orderType;
            return this;
        }

        public Builder orderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        public Builder customsType(Integer customsType) {
            this.customsType = customsType;
            return this;
        }

        public Builder electricCode(String electricCode) {
            this.electricCode = electricCode;
            return this;
        }

        public Builder cbepcomCode(String cbepcomCode) {
            this.cbepcomCode = cbepcomCode;
            return this;
        }

        public Builder busiMode(Integer busiMode) {
            this.busiMode = busiMode;
            return this;
        }

        public Builder customsCode(String customsCode) {
            this.customsCode = customsCode;
            return this;
        }

        public Builder ciqbCode(String ciqbCode) {
            this.ciqbCode = ciqbCode;
            return this;
        }

        public Builder stationbCode(String stationbCode) {
            this.stationbCode = stationbCode;
            return this;
        }

        public Builder deliveryCode(String deliveryCode) {
            this.deliveryCode = deliveryCode;
            return this;
        }

        public Builder notes(String notes) {
            this.notes = notes;
            return this;
        }

        public Builder freightFcy(BigDecimal freightFcy) {
            this.freightFcy = freightFcy;
            return this;
        }

        public Builder freightFcode(String freightFcode) {
            this.freightFcode = freightFcode;
            return this;
        }

        public Builder insuredFee(BigDecimal insuredFee) {
            this.insuredFee = insuredFee;
            return this;
        }

        public Builder insurCurr(String insurCurr) {
            this.insurCurr = insurCurr;
            return this;
        }

        public Builder insurMark(String insurMark) {
            this.insurMark = insurMark;
            return this;
        }

        public Builder taxFcy(BigDecimal taxFcy) {
            this.taxFcy = taxFcy;
            return this;
        }

        public Builder taxFcode(String taxFcode) {
            this.taxFcode = taxFcode;
            return this;
        }

        public Builder otherRate(BigDecimal otherRate) {
            this.otherRate = otherRate;
            return this;
        }

        public Builder otherCurr(String otherCurr) {
            this.otherCurr = otherCurr;
            return this;
        }

        public Builder otherMark(String otherMark) {
            this.otherMark = otherMark;
            return this;
        }

        public Builder otherPayment(BigDecimal otherPayment) {
            this.otherPayment = otherPayment;
            return this;
        }

        public Builder otherPaymentCurr(String otherPaymentCurr) {
            this.otherPaymentCurr = otherPaymentCurr;
            return this;
        }

        public Builder fCode(String fCode) {
            this.fCode = fCode;
            return this;
        }

        public Builder discount(BigDecimal discount) {
            this.discount = discount;
            return this;
        }

        public Builder buyerName(String buyerName) {
            this.buyerName = buyerName;
            return this;
        }

        public Builder buyerIdType(Integer buyerIdType) {
            this.buyerIdType = buyerIdType;
            return this;
        }

        public Builder buyerIdNumber(String buyerIdNumber) {
            this.buyerIdNumber = buyerIdNumber;
            return this;
        }

        public Builder buyerTelephone(String buyerTelephone) {
            this.buyerTelephone = buyerTelephone;
            return this;
        }

        public Builder buyerRegNo(String buyerRegNo) {
            this.buyerRegNo = buyerRegNo;
            return this;
        }

        public Builder grossWeight(BigDecimal grossWeight) {
            this.grossWeight = grossWeight;
            return this;
        }

        public Builder netWeight(BigDecimal netWeight) {
            this.netWeight = netWeight;
            return this;
        }

        public Builder bakbCode(String bakbCode) {
            this.bakbCode = bakbCode;
            return this;
        }

        public Builder ordExcStatus(String ordExcStatus) {
            this.ordExcStatus = ordExcStatus;
            return this;
        }

        public Builder forSellComp(String forSellComp) {
            this.forSellComp = forSellComp;
            return this;
        }

        public Builder forSellCompName(String forSellCompName) {
            this.forSellCompName = forSellCompName;
            return this;
        }

        public Builder tradeUnitCode(String tradeUnitCode) {
            this.tradeUnitCode = tradeUnitCode;
            return this;
        }

        public Builder tradeUnitName(String tradeUnitName) {
            this.tradeUnitName = tradeUnitName;
            return this;
        }

        public Builder shippernCode(String shippernCode) {
            this.shippernCode = shippernCode;
            return this;
        }

        public Builder shipDate(Date shipDate) {
            this.shipDate = shipDate;
            return this;
        }

        public Builder inputDate(Date inputDate) {
            this.inputDate = inputDate;
            return this;
        }

        public Builder logNots(String logNots) {
            this.logNots = logNots;
            return this;
        }

        public Builder blno(String blno) {
            this.blno = blno;
            return this;
        }

        public Builder trans(String trans) {
            this.trans = trans;
            return this;
        }

        public Builder transNo(String transNo) {
            this.transNo = transNo;
            return this;
        }

        public Builder changeFlag(String changeFlag) {
            this.changeFlag = changeFlag;
            return this;
        }

        public Builder tradeMode(String tradeMode) {
            this.tradeMode = tradeMode;
            return this;
        }

        public Builder shipperName(String shipperName) {
            this.shipperName = shipperName;
            return this;
        }

        public Builder shipperAddress(String shipperAddress) {
            this.shipperAddress = shipperAddress;
            return this;
        }

        public Builder shipperPhone(String shipperPhone) {
            this.shipperPhone = shipperPhone;
            return this;
        }

        public Builder agentCode(String agentCode) {
            this.agentCode = agentCode;
            return this;
        }

        public Builder agentName(String agentName) {
            this.agentName = agentName;
            return this;
        }

        public Builder payNo(String payNo) {
            this.payNo = payNo;
            return this;
        }

        public Builder payPcomName(String payPcomName) {
            this.payPcomName = payPcomName;
            return this;
        }

        public Builder payCopNo(String payCopNo) {
            this.payCopNo = payCopNo;
            return this;
        }

        public Builder opType(String opType) {
            this.opType = opType;
            return this;
        }

        public Builder payType(String payType) {
            this.payType = payType;
            return this;
        }

        public Builder payStatus(String payStatus) {
            this.payStatus = payStatus;
            return this;
        }

        public Builder payorName(String payorName) {
            this.payorName = payorName;
            return this;
        }

        public Builder activePayComp(String activePayComp) {
            this.activePayComp = activePayComp;
            return this;
        }

        public Builder acturalPaid(BigDecimal acturalPaid) {
            this.acturalPaid = acturalPaid;
            return this;
        }

        public Builder payCurr(String payCurr) {
            this.payCurr = payCurr;
            return this;
        }

        public Builder payNots(String payNots) {
            this.payNots = payNots;
            return this;
        }

        public Builder payDate(Date payDate) {
            this.payDate = payDate;
            return this;
        }

        public Builder fromEplat(Integer fromEplat) {
            this.fromEplat = fromEplat;
            return this;
        }

        public Builder printHeader(String printHeader) {
            this.printHeader = printHeader;
            return this;
        }

        public Builder commonField(String commonField) {
            this.commonField = commonField;
            return this;
        }

        public Builder isStoreStrategy(Integer isStoreStrategy) {
            this.isStoreStrategy = isStoreStrategy;
            return this;
        }

        public Builder vmiFlag(Integer vmiFlag) {
            this.vmiFlag = vmiFlag;
            return this;
        }

        public Builder ownerFlag(Integer ownerFlag) {
            this.ownerFlag = ownerFlag;
            return this;
        }

        public Builder cutMode(String cutMode) {
            this.cutMode = cutMode;
            return this;
        }

        public Builder transMode(String transMode) {
            this.transMode = transMode;
            return this;
        }

        public Builder packNo(Integer packNo) {
            this.packNo = packNo;
            return this;
        }

        public Builder wrapType(String wrapType) {
            this.wrapType = wrapType;
            return this;
        }

        public Builder sendCity(String sendCity) {
            this.sendCity = sendCity;
            return this;
        }

        public Builder totalValue(BigDecimal totalValue) {
            this.totalValue = totalValue;
            return this;
        }

        public Builder goodsInfo(String goodsInfo) {
            this.goodsInfo = goodsInfo;
            return this;
        }

        public Builder orderBatchNo(String orderBatchNo) {
            this.orderBatchNo = orderBatchNo;
            return this;
        }

        public Builder reDeclare(String reDeclare) {
            this.reDeclare = reDeclare;
            return this;
        }

        public Builder ebptpl(String ebptpl) {
            this.ebptpl = ebptpl;
            return this;
        }

        public Builder recipient(OpRecipientDto recipient) {
            this.recipient = recipient;
            return this;
        }

        public Builder goodList(List<OpGoodsItemDto> goodList) {
            this.goodList = goodList;
            return this;
        }

        public OpRequest build() {
            return new OpRequest(this);
        }
    }

    public String getOrderId() {
        return orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public String getPackingMaterial() {
        return packingMaterial;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public String getTpl() {
        return tpl;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public Integer getCustomsType() {
        return customsType;
    }

    public String getElectricCode() {
        return electricCode;
    }

    public String getCbepcomCode() {
        return cbepcomCode;
    }

    public Integer getBusiMode() {
        return busiMode;
    }

    public String getCustomsCode() {
        return customsCode;
    }

    public String getCiqbCode() {
        return ciqbCode;
    }

    public String getStationbCode() {
        return stationbCode;
    }

    public String getDeliveryCode() {
        return deliveryCode;
    }

    public String getNotes() {
        return notes;
    }

    public BigDecimal getFreightFcy() {
        return freightFcy;
    }

    public String getFreightFcode() {
        return freightFcode;
    }

    public BigDecimal getInsuredFee() {
        return insuredFee;
    }

    public String getInsurCurr() {
        return insurCurr;
    }

    public String getInsurMark() {
        return insurMark;
    }

    public BigDecimal getTaxFcy() {
        return taxFcy;
    }

    public String getTaxFcode() {
        return taxFcode;
    }

    public BigDecimal getOtherRate() {
        return otherRate;
    }

    public String getOtherCurr() {
        return otherCurr;
    }

    public String getOtherMark() {
        return otherMark;
    }

    public BigDecimal getOtherPayment() {
        return otherPayment;
    }

    public String getOtherPaymentCurr() {
        return otherPaymentCurr;
    }

    public String getfCode() {
        return fCode;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public Integer getBuyerIdType() {
        return buyerIdType;
    }

    public String getBuyerIdNumber() {
        return buyerIdNumber;
    }

    public String getBuyerTelephone() {
        return buyerTelephone;
    }

    public String getBuyerRegNo() {
        return buyerRegNo;
    }

    public BigDecimal getGrossWeight() {
        return grossWeight;
    }

    public BigDecimal getNetWeight() {
        return netWeight;
    }

    public String getBakbCode() {
        return bakbCode;
    }

    public String getOrdExcStatus() {
        return ordExcStatus;
    }

    public String getForSellComp() {
        return forSellComp;
    }

    public String getForSellCompName() {
        return forSellCompName;
    }

    public String getTradeUnitCode() {
        return tradeUnitCode;
    }

    public String getTradeUnitName() {
        return tradeUnitName;
    }

    public String getShippernCode() {
        return shippernCode;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public String getLogNots() {
        return logNots;
    }

    public String getBlno() {
        return blno;
    }

    public String getTrans() {
        return trans;
    }

    public String getTransNo() {
        return transNo;
    }

    public String getChangeFlag() {
        return changeFlag;
    }

    public String getTradeMode() {
        return tradeMode;
    }

    public String getShipperName() {
        return shipperName;
    }

    public String getShipperAddress() {
        return shipperAddress;
    }

    public String getShipperPhone() {
        return shipperPhone;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public String getAgentName() {
        return agentName;
    }

    public String getPayNo() {
        return payNo;
    }

    public String getPayPcomName() {
        return payPcomName;
    }

    public String getPayCopNo() {
        return payCopNo;
    }

    public String getOpType() {
        return opType;
    }

    public String getPayType() {
        return payType;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public String getPayorName() {
        return payorName;
    }

    public String getActivePayComp() {
        return activePayComp;
    }

    public BigDecimal getActuralPaid() {
        return acturalPaid;
    }

    public String getPayCurr() {
        return payCurr;
    }

    public String getPayNots() {
        return payNots;
    }

    public Date getPayDate() {
        return payDate;
    }

    public Integer getFromEplat() {
        return fromEplat;
    }

    public String getPrintHeader() {
        return printHeader;
    }

    public String getCommonField() {
        return commonField;
    }

    public Integer getIsStoreStrategy() {
        return isStoreStrategy;
    }

    public Integer getVmiFlag() {
        return vmiFlag;
    }

    public Integer getOwnerFlag() {
        return ownerFlag;
    }

    public String getCutMode() {
        return cutMode;
    }

    public String getTransMode() {
        return transMode;
    }

    public Integer getPackNo() {
        return packNo;
    }

    public String getWrapType() {
        return wrapType;
    }

    public String getSendCity() {
        return sendCity;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public String getGoodsInfo() {
        return goodsInfo;
    }

    public String getOrderBatchNo() {
        return orderBatchNo;
    }

    public String getReDeclare() {
        return reDeclare;
    }

    public String getEbptpl() {
        return ebptpl;
    }

    public OpRecipientDto getRecipient() {
        return recipient;
    }

    public List<OpGoodsItemDto> getGoodList() {
        return goodList;
    }
}
package com.topideal.supplychain.ocp.kjb.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName KjbFxRequest
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2020/6/30 13:40
 * @Version 1.0
 **/
public class KjbFxRequest implements Serializable {

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

    private String shopId;

    private KjbFxRecipient recipient;
    private List<KjbFxGoods> goodList;

    public String getOrderId() {
        return orderId;
    }

    public String getOrderDate() {
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

    public Integer getCustomsType() {
        return customsType;
    }

    public String getElectricCode() {
        return electricCode;
    }

    public String getCbepcomCode() {
        return cbepcomCode;
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

    public BigDecimal getTaxFcy() {
        return taxFcy;
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

    public String getOrderStatus() {
        return orderStatus;
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

    public String getStatisticsFlag() {
        return statisticsFlag;
    }

    public String getBakbCode() {
        return bakbCode;
    }

    public String getLogisticsNo() {
        return logisticsNo;
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

    public String getfCode() {
        return fCode;
    }

    public String getTaxFcode() {
        return taxFcode;
    }

    public String getPayDate() {
        return payDate;
    }

    public String getShipDate() {
        return shipDate;
    }

    public String getInputDate() {
        return inputDate;
    }

    public String getPayNots() {
        return payNots;
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

    public Integer getReDeclare() {
        return reDeclare;
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

    public String getInsurCurr() {
        return insurCurr;
    }

    public String getInsurMark() {
        return insurMark;
    }

    public String getOtherCurr() {
        return otherCurr;
    }

    public BigDecimal getOtherRate() {
        return otherRate;
    }

    public String getOtherMark() {
        return otherMark;
    }

    public String getOtherPaymentCurr() {
        return otherPaymentCurr;
    }

    public BigDecimal getOtherPayment() {
        return otherPayment;
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

    public String getAgentCode() {
        return agentCode;
    }

    public String getAgentName() {
        return agentName;
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

    public String getPayCurr() {
        return payCurr;
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

    public String getShopId() {
        return shopId;
    }

    public KjbFxRecipient getRecipient() {
        return recipient;
    }

    public List<KjbFxGoods> getGoodList() {
        return goodList;
    }

    private KjbFxRequest(Builder builder) {
        orderId = builder.orderId;
        orderDate = builder.orderDate;
        packingMaterial = builder.packingMaterial;
        warehouseId = builder.warehouseId;
        tpl = builder.tpl;
        orderType = builder.orderType;
        customsType = builder.customsType;
        electricCode = builder.electricCode;
        cbepcomCode = builder.cbepcomCode;
        deliveryCode = builder.deliveryCode;
        notes = builder.notes;
        freightFcy = builder.freightFcy;
        freightFcode = builder.freightFcode;
        insuredFee = builder.insuredFee;
        taxFcy = builder.taxFcy;
        discount = builder.discount;
        buyerName = builder.buyerName;
        buyerIdType = builder.buyerIdType;
        buyerIdNumber = builder.buyerIdNumber;
        buyerTelephone = builder.buyerTelephone;
        buyerRegNo = builder.buyerRegNo;
        grossWeight = builder.grossWeight;
        netWeight = builder.netWeight;
        orderStatus = builder.orderStatus;
        busiMode = builder.busiMode;
        customsCode = builder.customsCode;
        ciqbCode = builder.ciqbCode;
        stationbCode = builder.stationbCode;
        statisticsFlag = builder.statisticsFlag;
        bakbCode = builder.bakbCode;
        logisticsNo = builder.logisticsNo;
        ordExcStatus = builder.ordExcStatus;
        forSellComp = builder.forSellComp;
        forSellCompName = builder.forSellCompName;
        tradeUnitCode = builder.tradeUnitCode;
        tradeUnitName = builder.tradeUnitName;
        shippernCode = builder.shippernCode;
        fCode = builder.fCode;
        taxFcode = builder.taxFcode;
        payDate = builder.payDate;
        shipDate = builder.shipDate;
        inputDate = builder.inputDate;
        payNots = builder.payNots;
        logNots = builder.logNots;
        blno = builder.blno;
        trans = builder.trans;
        transNo = builder.transNo;
        changeFlag = builder.changeFlag;
        reDeclare = builder.reDeclare;
        tradeMode = builder.tradeMode;
        shipperName = builder.shipperName;
        shipperAddress = builder.shipperAddress;
        shipperPhone = builder.shipperPhone;
        insurCurr = builder.insurCurr;
        insurMark = builder.insurMark;
        otherCurr = builder.otherCurr;
        otherRate = builder.otherRate;
        otherMark = builder.otherMark;
        otherPaymentCurr = builder.otherPaymentCurr;
        otherPayment = builder.otherPayment;
        payNo = builder.payNo;
        payPcomName = builder.payPcomName;
        payCopNo = builder.payCopNo;
        agentCode = builder.agentCode;
        agentName = builder.agentName;
        opType = builder.opType;
        payType = builder.payType;
        payStatus = builder.payStatus;
        payCurr = builder.payCurr;
        payorName = builder.payorName;
        activePayComp = builder.activePayComp;
        acturalPaid = builder.acturalPaid;
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
        shopId = builder.shopId;
        recipient = builder.recipient;
        goodList = builder.goodList;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {

        private String orderId;
        private String orderDate;
        private String packingMaterial;
        private String warehouseId;
        private String tpl;
        private Integer orderType;
        private Integer customsType;
        private String electricCode;
        private String cbepcomCode;
        private String deliveryCode;
        private String notes;
        private BigDecimal freightFcy;
        private String freightFcode;
        private BigDecimal insuredFee;
        private BigDecimal taxFcy;
        private BigDecimal discount;
        private String buyerName;
        private Integer buyerIdType;
        private String buyerIdNumber;
        private String buyerTelephone;
        private String buyerRegNo;
        private BigDecimal grossWeight;
        private BigDecimal netWeight;
        private String orderStatus;
        private Integer busiMode;
        private String customsCode;
        private String ciqbCode;
        private String stationbCode;
        private String statisticsFlag;
        private String bakbCode;
        private String logisticsNo;
        private String ordExcStatus;
        private String forSellComp;
        private String forSellCompName;
        private String tradeUnitCode;
        private String tradeUnitName;
        private String shippernCode;
        private String fCode;
        private String taxFcode;
        private String payDate;
        private String shipDate;
        private String inputDate;
        private String payNots;
        private String logNots;
        private String blno;
        private String trans;
        private String transNo;
        private String changeFlag;
        private Integer reDeclare;
        private String tradeMode;
        private String shipperName;
        private String shipperAddress;
        private String shipperPhone;
        private String insurCurr;
        private String insurMark;
        private String otherCurr;
        private BigDecimal otherRate;
        private String otherMark;
        private String otherPaymentCurr;
        private BigDecimal otherPayment;
        private String payNo;
        private String payPcomName;
        private String payCopNo;
        private String agentCode;
        private String agentName;
        private String opType;
        private String payType;
        private String payStatus;
        private String payCurr;
        private String payorName;
        private String activePayComp;
        private BigDecimal acturalPaid;
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
        private String shopId;
        private KjbFxRecipient recipient;
        private List<KjbFxGoods> goodList;

        private Builder() {
        }

        public Builder orderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder orderDate(String orderDate) {
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

        public Builder taxFcy(BigDecimal taxFcy) {
            this.taxFcy = taxFcy;
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

        public Builder orderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
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

        public Builder statisticsFlag(String statisticsFlag) {
            this.statisticsFlag = statisticsFlag;
            return this;
        }

        public Builder bakbCode(String bakbCode) {
            this.bakbCode = bakbCode;
            return this;
        }

        public Builder logisticsNo(String logisticsNo) {
            this.logisticsNo = logisticsNo;
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

        public Builder fCode(String fCode) {
            this.fCode = fCode;
            return this;
        }

        public Builder taxFcode(String taxFcode) {
            this.taxFcode = taxFcode;
            return this;
        }

        public Builder payDate(String payDate) {
            this.payDate = payDate;
            return this;
        }

        public Builder shipDate(String shipDate) {
            this.shipDate = shipDate;
            return this;
        }

        public Builder inputDate(String inputDate) {
            this.inputDate = inputDate;
            return this;
        }

        public Builder payNots(String payNots) {
            this.payNots = payNots;
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

        public Builder reDeclare(Integer reDeclare) {
            this.reDeclare = reDeclare;
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

        public Builder insurCurr(String insurCurr) {
            this.insurCurr = insurCurr;
            return this;
        }

        public Builder insurMark(String insurMark) {
            this.insurMark = insurMark;
            return this;
        }

        public Builder otherCurr(String otherCurr) {
            this.otherCurr = otherCurr;
            return this;
        }

        public Builder otherRate(BigDecimal otherRate) {
            this.otherRate = otherRate;
            return this;
        }

        public Builder otherMark(String otherMark) {
            this.otherMark = otherMark;
            return this;
        }

        public Builder otherPaymentCurr(String otherPaymentCurr) {
            this.otherPaymentCurr = otherPaymentCurr;
            return this;
        }

        public Builder otherPayment(BigDecimal otherPayment) {
            this.otherPayment = otherPayment;
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

        public Builder agentCode(String agentCode) {
            this.agentCode = agentCode;
            return this;
        }

        public Builder agentName(String agentName) {
            this.agentName = agentName;
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

        public Builder payCurr(String payCurr) {
            this.payCurr = payCurr;
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

        public Builder shopId(String shopId) {
            this.shopId = shopId;
            return this;
        }

        public Builder recipient(KjbFxRecipient recipient) {
            this.recipient = recipient;
            return this;
        }

        public Builder goodList(List<KjbFxGoods> goodList) {
            this.goodList = goodList;
            return this;
        }

        public KjbFxRequest build() {
            return new KjbFxRequest(this);
        }
    }

}

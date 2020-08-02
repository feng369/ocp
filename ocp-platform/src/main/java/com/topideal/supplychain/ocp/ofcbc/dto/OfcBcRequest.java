package com.topideal.supplychain.ocp.ofcbc.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 标题：BC-OFC转单请求
 * 模块：ocp-parent
 * 版权: Copyright © 2019 topideal
 * 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.ofc.dto
 * 作者：songping
 * 创建日期：2020/1/9 16:43
 *
 * @version 1.0
 */
public class OfcBcRequest implements Serializable {

    //订单号
    private String orderNo;
    private Date declTime;
    private String buyerRegNo;
    private String buyerIdType;
    private String buyerIdNumber;
    private String buyerName;
    private String buyerTelephone;
    private String receiveNo;
    private String consignee;
    private String consigneeTelephone;
    private String consigneeAddress;
    private String country;
    private String province;
    private String city;
    private String district;
    private String postCode;
    private BigDecimal goodsValue;
    private BigDecimal freight;
    private BigDecimal discount;
    private BigDecimal taxTotal;
    private String taxCurr;
    private BigDecimal acturalPaid;
    private BigDecimal insuredFee;
    private String insurCurr;
    private String freightCurr;
    private String ebpCode;
    private String ebpName;
    private String ebcCode;
    private String ebcName;
    private String userId;
    private String userName;
    private String logisticsCode;
    private String logisticsName;
    private String logisticsNo;
    private String logisticsNote;
    private String payCode;
    private String payName;
    private String payTransactionId;
    private Date payTime;
    private String payNote;
    private String customsCode;
    private String ciqbCode;
    private String ieFlag;
    private String busiMode;
    private String orderType;
    private String trafMode;
    private String trafNo;
    private String voyageNo;
    private String billNo;
    private String shippernCode;
    private String wrapType;
    private Long packNo;
    private BigDecimal grossWeight;
    private BigDecimal netWeight;
    private Integer fromEplat;
    private String printHeader;
    private Integer changeFlag;
    private Integer reDeclare;
    private Integer traceFlag;
    private String traceSourceCode;
    private Integer vmiFlag;
    private Integer ownerFlag;
    private Date shipDate;
    private Date inputDate;
    private String shipperName;
    private String shipperAddress;
    private String shipperPhone;
    private String note;
    private String orderCode;
    private String storeCode;
    private String platformOrderType;
    private Integer orderSource;
    private String ownerUserId;
    private String ownerUserName;
    private Integer isStoreStrategy;
    private String lpCode;
    private Date deliveryTimePreference;
    private String tradeMode;
    private String commonField;
    private String payCurr;
    private String statisticsFlag;
    private String orderBatchNo;
    private String pod;
    private String fCode;
    private String orderStatus;
    private Integer customsType;
    private List<OfcBcGoodsReqDto> bcTempSoItemVos;

    public String getOrderNo() {
        return orderNo;
    }

    public Date getDeclTime() {
        return declTime;
    }

    public String getBuyerRegNo() {
        return buyerRegNo;
    }

    public String getBuyerIdType() {
        return buyerIdType;
    }

    public String getBuyerIdNumber() {
        return buyerIdNumber;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public String getBuyerTelephone() {
        return buyerTelephone;
    }

    public String getReceiveNo() {
        return receiveNo;
    }

    public String getConsignee() {
        return consignee;
    }

    public String getConsigneeTelephone() {
        return consigneeTelephone;
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public String getCountry() {
        return country;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

    public String getPostCode() {
        return postCode;
    }

    public BigDecimal getGoodsValue() {
        return goodsValue;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public BigDecimal getTaxTotal() {
        return taxTotal;
    }

    public String getTaxCurr() {
        return taxCurr;
    }

    public BigDecimal getActuralPaid() {
        return acturalPaid;
    }

    public BigDecimal getInsuredFee() {
        return insuredFee;
    }

    public String getInsurCurr() {
        return insurCurr;
    }

    public String getFreightCurr() {
        return freightCurr;
    }

    public String getEbpCode() {
        return ebpCode;
    }

    public String getEbpName() {
        return ebpName;
    }

    public String getEbcCode() {
        return ebcCode;
    }

    public String getEbcName() {
        return ebcName;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getLogisticsCode() {
        return logisticsCode;
    }

    public String getLogisticsName() {
        return logisticsName;
    }

    public String getLogisticsNo() {
        return logisticsNo;
    }

    public String getLogisticsNote() {
        return logisticsNote;
    }

    public String getPayCode() {
        return payCode;
    }

    public String getPayName() {
        return payName;
    }

    public String getPayTransactionId() {
        return payTransactionId;
    }

    public Date getPayTime() {
        return payTime;
    }

    public String getPayNote() {
        return payNote;
    }

    public String getCustomsCode() {
        return customsCode;
    }

    public String getCiqbCode() {
        return ciqbCode;
    }

    public String getIeFlag() {
        return ieFlag;
    }

    public String getBusiMode() {
        return busiMode;
    }

    public String getOrderType() {
        return orderType;
    }

    public String getTrafMode() {
        return trafMode;
    }

    public String getTrafNo() {
        return trafNo;
    }

    public String getVoyageNo() {
        return voyageNo;
    }

    public String getBillNo() {
        return billNo;
    }

    public String getShippernCode() {
        return shippernCode;
    }

    public String getWrapType() {
        return wrapType;
    }

    public Long getPackNo() {
        return packNo;
    }

    public BigDecimal getGrossWeight() {
        return grossWeight;
    }

    public BigDecimal getNetWeight() {
        return netWeight;
    }

    public Integer getFromEplat() {
        return fromEplat;
    }

    public String getPrintHeader() {
        return printHeader;
    }

    public Integer getChangeFlag() {
        return changeFlag;
    }

    public Integer getReDeclare() {
        return reDeclare;
    }

    public Integer getTraceFlag() {
        return traceFlag;
    }

    public String getTraceSourceCode() {
        return traceSourceCode;
    }

    public Integer getVmiFlag() {
        return vmiFlag;
    }

    public Integer getOwnerFlag() {
        return ownerFlag;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public Date getInputDate() {
        return inputDate;
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

    public String getNote() {
        return note;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public String getPlatformOrderType() {
        return platformOrderType;
    }

    public Integer getOrderSource() {
        return orderSource;
    }

    public String getOwnerUserId() {
        return ownerUserId;
    }

    public String getOwnerUserName() {
        return ownerUserName;
    }

    public Integer getIsStoreStrategy() {
        return isStoreStrategy;
    }

    public String getLpCode() {
        return lpCode;
    }

    public Date getDeliveryTimePreference() {
        return deliveryTimePreference;
    }

    public String getTradeMode() {
        return tradeMode;
    }

    public String getCommonField() {
        return commonField;
    }

    public String getPayCurr() {
        return payCurr;
    }

    public String getStatisticsFlag() {
        return statisticsFlag;
    }

    public String getOrderBatchNo() {
        return orderBatchNo;
    }

    public String getPod() {
        return pod;
    }

    public String getfCode() {
        return fCode;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public Integer getCustomsType() {
        return customsType;
    }

    public List<OfcBcGoodsReqDto> getBcTempSoItemVos() {
        return bcTempSoItemVos;
    }

    private OfcBcRequest(Builder builder) {
        orderNo = builder.orderNo;
        declTime = builder.declTime;
        buyerRegNo = builder.buyerRegNo;
        buyerIdType = builder.buyerIdType;
        buyerIdNumber = builder.buyerIdNumber;
        buyerName = builder.buyerName;
        buyerTelephone = builder.buyerTelephone;
        receiveNo = builder.receiveNo;
        consignee = builder.consignee;
        consigneeTelephone = builder.consigneeTelephone;
        consigneeAddress = builder.consigneeAddress;
        country = builder.country;
        province = builder.province;
        city = builder.city;
        district = builder.district;
        postCode = builder.postCode;
        goodsValue = builder.goodsValue;
        freight = builder.freight;
        discount = builder.discount;
        taxTotal = builder.taxTotal;
        taxCurr = builder.taxCurr;
        acturalPaid = builder.acturalPaid;
        insuredFee = builder.insuredFee;
        insurCurr = builder.insurCurr;
        freightCurr = builder.freightCurr;
        ebpCode = builder.ebpCode;
        ebpName = builder.ebpName;
        ebcCode = builder.ebcCode;
        ebcName = builder.ebcName;
        userId = builder.userId;
        userName = builder.userName;
        logisticsCode = builder.logisticsCode;
        logisticsName = builder.logisticsName;
        logisticsNo = builder.logisticsNo;
        logisticsNote = builder.logisticsNote;
        payCode = builder.payCode;
        payName = builder.payName;
        payTransactionId = builder.payTransactionId;
        payTime = builder.payTime;
        payNote = builder.payNote;
        customsCode = builder.customsCode;
        ciqbCode = builder.ciqbCode;
        ieFlag = builder.ieFlag;
        busiMode = builder.busiMode;
        orderType = builder.orderType;
        trafMode = builder.trafMode;
        trafNo = builder.trafNo;
        voyageNo = builder.voyageNo;
        billNo = builder.billNo;
        shippernCode = builder.shippernCode;
        wrapType = builder.wrapType;
        packNo = builder.packNo;
        grossWeight = builder.grossWeight;
        netWeight = builder.netWeight;
        fromEplat = builder.fromEplat;
        printHeader = builder.printHeader;
        changeFlag = builder.changeFlag;
        reDeclare = builder.reDeclare;
        traceFlag = builder.traceFlag;
        traceSourceCode = builder.traceSourceCode;
        vmiFlag = builder.vmiFlag;
        ownerFlag = builder.ownerFlag;
        shipDate = builder.shipDate;
        inputDate = builder.inputDate;
        shipperName = builder.shipperName;
        shipperAddress = builder.shipperAddress;
        shipperPhone = builder.shipperPhone;
        note = builder.note;
        orderCode = builder.orderCode;
        storeCode = builder.storeCode;
        platformOrderType = builder.platformOrderType;
        orderSource = builder.orderSource;
        ownerUserId = builder.ownerUserId;
        ownerUserName = builder.ownerUserName;
        isStoreStrategy = builder.isStoreStrategy;
        lpCode = builder.lpCode;
        deliveryTimePreference = builder.deliveryTimePreference;
        tradeMode = builder.tradeMode;
        commonField = builder.commonField;
        payCurr = builder.payCurr;
        statisticsFlag = builder.statisticsFlag;
        orderBatchNo = builder.orderBatchNo;
        pod = builder.pod;
        fCode = builder.fCode;
        orderStatus = builder.orderStatus;
        customsType = builder.customsType;
        bcTempSoItemVos = builder.bcTempSoItemVos;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {

        private String orderNo;
        private Date declTime;
        private String buyerRegNo;
        private String buyerIdType;
        private String buyerIdNumber;
        private String buyerName;
        private String buyerTelephone;
        private String receiveNo;
        private String consignee;
        private String consigneeTelephone;
        private String consigneeAddress;
        private String country;
        private String province;
        private String city;
        private String district;
        private String postCode;
        private BigDecimal goodsValue;
        private BigDecimal freight;
        private BigDecimal discount;
        private BigDecimal taxTotal;
        private String taxCurr;
        private BigDecimal acturalPaid;
        private BigDecimal insuredFee;
        private String insurCurr;
        private String freightCurr;
        private String ebpCode;
        private String ebpName;
        private String ebcCode;
        private String ebcName;
        private String userId;
        private String userName;
        private String logisticsCode;
        private String logisticsName;
        private String logisticsNo;
        private String logisticsNote;
        private String payCode;
        private String payName;
        private String payTransactionId;
        private Date payTime;
        private String payNote;
        private String customsCode;
        private String ciqbCode;
        private String ieFlag;
        private String busiMode;
        private String orderType;
        private String trafMode;
        private String trafNo;
        private String voyageNo;
        private String billNo;
        private String shippernCode;
        private String wrapType;
        private Long packNo;
        private BigDecimal grossWeight;
        private BigDecimal netWeight;
        private Integer fromEplat;
        private String printHeader;
        private Integer changeFlag;
        private Integer reDeclare;
        private Integer traceFlag;
        private String traceSourceCode;
        private Integer vmiFlag;
        private Integer ownerFlag;
        private Date shipDate;
        private Date inputDate;
        private String shipperName;
        private String shipperAddress;
        private String shipperPhone;
        private String note;
        private String orderCode;
        private String storeCode;
        private String platformOrderType;
        private Integer orderSource;
        private String ownerUserId;
        private String ownerUserName;
        private Integer isStoreStrategy;
        private String lpCode;
        private Date deliveryTimePreference;
        private String tradeMode;
        private String commonField;
        private String payCurr;
        private String statisticsFlag;
        private String orderBatchNo;
        private String pod;
        private String fCode;
        private String orderStatus;
        private Integer customsType;
        private List<OfcBcGoodsReqDto> bcTempSoItemVos;

        private Builder() {
        }

        public Builder orderNo(String orderNo) {
            this.orderNo = orderNo;
            return this;
        }

        public Builder declTime(Date declTime) {
            this.declTime = declTime;
            return this;
        }

        public Builder buyerRegNo(String buyerRegNo) {
            this.buyerRegNo = buyerRegNo;
            return this;
        }

        public Builder buyerIdType(String buyerIdType) {
            this.buyerIdType = buyerIdType;
            return this;
        }

        public Builder buyerIdNumber(String buyerIdNumber) {
            this.buyerIdNumber = buyerIdNumber;
            return this;
        }

        public Builder buyerName(String buyerName) {
            this.buyerName = buyerName;
            return this;
        }

        public Builder buyerTelephone(String buyerTelephone) {
            this.buyerTelephone = buyerTelephone;
            return this;
        }

        public Builder receiveNo(String receiveNo) {
            this.receiveNo = receiveNo;
            return this;
        }

        public Builder consignee(String consignee) {
            this.consignee = consignee;
            return this;
        }

        public Builder consigneeTelephone(String consigneeTelephone) {
            this.consigneeTelephone = consigneeTelephone;
            return this;
        }

        public Builder consigneeAddress(String consigneeAddress) {
            this.consigneeAddress = consigneeAddress;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder province(String province) {
            this.province = province;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder district(String district) {
            this.district = district;
            return this;
        }

        public Builder postCode(String postCode) {
            this.postCode = postCode;
            return this;
        }

        public Builder goodsValue(BigDecimal goodsValue) {
            this.goodsValue = goodsValue == null ? null : goodsValue.multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP);
            return this;
        }

        public Builder freight(BigDecimal freight) {
            this.freight = freight == null ? null : freight.multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP);
            return this;
        }

        public Builder discount(BigDecimal discount) {
            this.discount = discount == null ? null : discount.multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP);
            return this;
        }

        public Builder taxTotal(BigDecimal taxTotal) {
            this.taxTotal = taxTotal == null ? null : taxTotal.multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP);
            return this;
        }

        public Builder taxCurr(String taxCurr) {
            this.taxCurr = taxCurr;
            return this;
        }

        public Builder acturalPaid(BigDecimal acturalPaid) {
            this.acturalPaid = acturalPaid == null ? null : acturalPaid.multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP);
            return this;
        }

        public Builder insuredFee(BigDecimal insuredFee) {
            this.insuredFee = insuredFee == null ? null : insuredFee.multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP);
            return this;
        }

        public Builder insurCurr(String insurCurr) {
            this.insurCurr = insurCurr;
            return this;
        }

        public Builder freightCurr(String freightCurr) {
            this.freightCurr = freightCurr;
            return this;
        }

        public Builder ebpCode(String ebpCode) {
            this.ebpCode = ebpCode;
            return this;
        }

        public Builder ebpName(String ebpName) {
            this.ebpName = ebpName;
            return this;
        }

        public Builder ebcCode(String ebcCode) {
            this.ebcCode = ebcCode;
            return this;
        }

        public Builder ebcName(String ebcName) {
            this.ebcName = ebcName;
            return this;
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder logisticsCode(String logisticsCode) {
            this.logisticsCode = logisticsCode;
            return this;
        }

        public Builder logisticsName(String logisticsName) {
            this.logisticsName = logisticsName;
            return this;
        }

        public Builder logisticsNo(String logisticsNo) {
            this.logisticsNo = logisticsNo;
            return this;
        }

        public Builder logisticsNote(String logisticsNote) {
            this.logisticsNote = logisticsNote;
            return this;
        }

        public Builder payCode(String payCode) {
            this.payCode = payCode;
            return this;
        }

        public Builder payName(String payName) {
            this.payName = payName;
            return this;
        }

        public Builder payTransactionId(String payTransactionId) {
            this.payTransactionId = payTransactionId;
            return this;
        }

        public Builder payTime(Date payTime) {
            this.payTime = payTime;
            return this;
        }

        public Builder payNote(String payNote) {
            this.payNote = payNote;
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

        public Builder ieFlag(String ieFlag) {
            this.ieFlag = ieFlag;
            return this;
        }

        public Builder busiMode(String busiMode) {
            this.busiMode = busiMode;
            return this;
        }

        public Builder orderType(String orderType) {
            this.orderType = orderType;
            return this;
        }

        public Builder trafMode(String trafMode) {
            this.trafMode = trafMode;
            return this;
        }

        public Builder trafNo(String trafNo) {
            this.trafNo = trafNo;
            return this;
        }

        public Builder voyageNo(String voyageNo) {
            this.voyageNo = voyageNo;
            return this;
        }

        public Builder billNo(String billNo) {
            this.billNo = billNo;
            return this;
        }

        public Builder shippernCode(String shippernCode) {
            this.shippernCode = shippernCode;
            return this;
        }

        public Builder wrapType(String wrapType) {
            this.wrapType = wrapType;
            return this;
        }

        public Builder packNo(Long packNo) {
            this.packNo = packNo;
            return this;
        }

        public Builder grossWeight(BigDecimal grossWeight) {
            this.grossWeight = grossWeight == null ? null : grossWeight.multiply(new BigDecimal(1000)).setScale(0, BigDecimal.ROUND_HALF_UP);
            return this;
        }

        public Builder netWeight(BigDecimal netWeight) {
            this.netWeight = netWeight == null ? null : netWeight.multiply(new BigDecimal(1000)).setScale(0, BigDecimal.ROUND_HALF_UP);
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

        public Builder changeFlag(Integer changeFlag) {
            this.changeFlag = changeFlag;
            return this;
        }

        public Builder reDeclare(Integer reDeclare) {
            this.reDeclare = reDeclare;
            return this;
        }

        public Builder traceFlag(Integer traceFlag) {
            this.traceFlag = traceFlag;
            return this;
        }

        public Builder traceSourceCode(String traceSourceCode) {
            this.traceSourceCode = traceSourceCode;
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

        public Builder shipDate(Date shipDate) {
            this.shipDate = shipDate;
            return this;
        }

        public Builder inputDate(Date inputDate) {
            this.inputDate = inputDate;
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

        public Builder note(String note) {
            this.note = note;
            return this;
        }

        public Builder orderCode(String orderCode) {
            this.orderCode = orderCode;
            return this;
        }

        public Builder storeCode(String storeCode) {
            this.storeCode = storeCode;
            return this;
        }

        public Builder platformOrderType(String platformOrderType) {
            this.platformOrderType = platformOrderType;
            return this;
        }

        public Builder orderSource(Integer orderSource) {
            this.orderSource = orderSource;
            return this;
        }

        public Builder ownerUserId(String ownerUserId) {
            this.ownerUserId = ownerUserId;
            return this;
        }

        public Builder ownerUserName(String ownerUserName) {
            this.ownerUserName = ownerUserName;
            return this;
        }

        public Builder isStoreStrategy(Integer isStoreStrategy) {
            this.isStoreStrategy = isStoreStrategy;
            return this;
        }

        public Builder lpCode(String lpCode) {
            this.lpCode = lpCode;
            return this;
        }

        public Builder deliveryTimePreference(Date deliveryTimePreference) {
            this.deliveryTimePreference = deliveryTimePreference;
            return this;
        }

        public Builder tradeMode(String tradeMode) {
            this.tradeMode = tradeMode;
            return this;
        }

        public Builder commonField(String commonField) {
            this.commonField = commonField;
            return this;
        }

        public Builder payCurr(String payCurr) {
            this.payCurr = payCurr;
            return this;
        }

        public Builder statisticsFlag(String statisticsFlag) {
            this.statisticsFlag = statisticsFlag;
            return this;
        }

        public Builder orderBatchNo(String orderBatchNo) {
            this.orderBatchNo = orderBatchNo;
            return this;
        }

        public Builder pod(String pod) {
            this.pod = pod;
            return this;
        }

        public Builder fCode(String fCode) {
            this.fCode = fCode;
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

        public Builder bcTempSoItemVos(List<OfcBcGoodsReqDto> bcTempSoItemVos) {
            this.bcTempSoItemVos = bcTempSoItemVos;
            return this;
        }

        public OfcBcRequest build() {
            return new OfcBcRequest(this);
        }
    }
}

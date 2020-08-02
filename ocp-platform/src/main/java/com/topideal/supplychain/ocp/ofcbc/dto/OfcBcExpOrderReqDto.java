package com.topideal.supplychain.ocp.ofcbc.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 标题：OFC-EXPBC订单
 * 模块：ocp-parent
 * 版权: Copyright © 2019 topideal
 * 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.ofc.dto
 * 作者：songping
 * 创建日期：2020/1/9 17:24
 *
 * @version 1.0
 */
public class OfcBcExpOrderReqDto implements Serializable {

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

    private List<OfcBcExpGoodsReqDto> ofcBcExpGoodsReqDtoList;

    private OfcBcExpOrderReqDto(Builder builder) {
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
        ofcBcExpGoodsReqDtoList = builder.ofcBcExpGoodsReqDtoList;
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
        private List<OfcBcExpGoodsReqDto> ofcBcExpGoodsReqDtoList;

        private Builder() {
        }

        public Builder orderNo(String val) {
            orderNo = val;
            return this;
        }

        public Builder declTime(Date val) {
            declTime = val;
            return this;
        }

        public Builder buyerRegNo(String val) {
            buyerRegNo = val;
            return this;
        }

        public Builder buyerIdType(String val) {
            buyerIdType = val;
            return this;
        }

        public Builder buyerIdNumber(String val) {
            buyerIdNumber = val;
            return this;
        }

        public Builder buyerName(String val) {
            buyerName = val;
            return this;
        }

        public Builder buyerTelephone(String val) {
            buyerTelephone = val;
            return this;
        }

        public Builder receiveNo(String val) {
            receiveNo = val;
            return this;
        }

        public Builder consignee(String val) {
            consignee = val;
            return this;
        }

        public Builder consigneeTelephone(String val) {
            consigneeTelephone = val;
            return this;
        }

        public Builder consigneeAddress(String val) {
            consigneeAddress = val;
            return this;
        }

        public Builder country(String val) {
            country = val;
            return this;
        }

        public Builder province(String val) {
            province = val;
            return this;
        }

        public Builder city(String val) {
            city = val;
            return this;
        }

        public Builder district(String val) {
            district = val;
            return this;
        }

        public Builder postCode(String val) {
            postCode = val;
            return this;
        }

        public Builder goodsValue(BigDecimal val) {
            goodsValue = val;
            return this;
        }

        public Builder freight(BigDecimal val) {
            freight = val;
            return this;
        }

        public Builder discount(BigDecimal val) {
            discount = val;
            return this;
        }

        public Builder taxTotal(BigDecimal val) {
            taxTotal = val;
            return this;
        }

        public Builder taxCurr(String val) {
            taxCurr = val;
            return this;
        }

        public Builder acturalPaid(BigDecimal val) {
            acturalPaid = val;
            return this;
        }

        public Builder insuredFee(BigDecimal val) {
            insuredFee = val;
            return this;
        }

        public Builder insurCurr(String val) {
            insurCurr = val;
            return this;
        }

        public Builder freightCurr(String val) {
            freightCurr = val;
            return this;
        }

        public Builder ebpCode(String val) {
            ebpCode = val;
            return this;
        }

        public Builder ebpName(String val) {
            ebpName = val;
            return this;
        }

        public Builder ebcCode(String val) {
            ebcCode = val;
            return this;
        }

        public Builder ebcName(String val) {
            ebcName = val;
            return this;
        }

        public Builder userId(String val) {
            userId = val;
            return this;
        }

        public Builder userName(String val) {
            userName = val;
            return this;
        }

        public Builder logisticsCode(String val) {
            logisticsCode = val;
            return this;
        }

        public Builder logisticsName(String val) {
            logisticsName = val;
            return this;
        }

        public Builder logisticsNo(String val) {
            logisticsNo = val;
            return this;
        }

        public Builder logisticsNote(String val) {
            logisticsNote = val;
            return this;
        }

        public Builder payCode(String val) {
            payCode = val;
            return this;
        }

        public Builder payName(String val) {
            payName = val;
            return this;
        }

        public Builder payTransactionId(String val) {
            payTransactionId = val;
            return this;
        }

        public Builder payTime(Date val) {
            payTime = val;
            return this;
        }

        public Builder payNote(String val) {
            payNote = val;
            return this;
        }

        public Builder customsCode(String val) {
            customsCode = val;
            return this;
        }

        public Builder ciqbCode(String val) {
            ciqbCode = val;
            return this;
        }

        public Builder ieFlag(String val) {
            ieFlag = val;
            return this;
        }

        public Builder busiMode(String val) {
            busiMode = val;
            return this;
        }

        public Builder orderType(String val) {
            orderType = val;
            return this;
        }

        public Builder trafMode(String val) {
            trafMode = val;
            return this;
        }

        public Builder trafNo(String val) {
            trafNo = val;
            return this;
        }

        public Builder voyageNo(String val) {
            voyageNo = val;
            return this;
        }

        public Builder billNo(String val) {
            billNo = val;
            return this;
        }

        public Builder shippernCode(String val) {
            shippernCode = val;
            return this;
        }

        public Builder wrapType(String val) {
            wrapType = val;
            return this;
        }

        public Builder packNo(Long val) {
            packNo = val;
            return this;
        }

        public Builder grossWeight(BigDecimal val) {
            grossWeight = val;
            return this;
        }

        public Builder netWeight(BigDecimal val) {
            netWeight = val;
            return this;
        }

        public Builder fromEplat(Integer val) {
            fromEplat = val;
            return this;
        }

        public Builder printHeader(String val) {
            printHeader = val;
            return this;
        }

        public Builder changeFlag(Integer val) {
            changeFlag = val;
            return this;
        }

        public Builder reDeclare(Integer val) {
            reDeclare = val;
            return this;
        }

        public Builder traceFlag(Integer val) {
            traceFlag = val;
            return this;
        }

        public Builder traceSourceCode(String val) {
            traceSourceCode = val;
            return this;
        }

        public Builder vmiFlag(Integer val) {
            vmiFlag = val;
            return this;
        }

        public Builder ownerFlag(Integer val) {
            ownerFlag = val;
            return this;
        }

        public Builder shipDate(Date val) {
            shipDate = val;
            return this;
        }

        public Builder inputDate(Date val) {
            inputDate = val;
            return this;
        }

        public Builder shipperName(String val) {
            shipperName = val;
            return this;
        }

        public Builder shipperAddress(String val) {
            shipperAddress = val;
            return this;
        }

        public Builder shipperPhone(String val) {
            shipperPhone = val;
            return this;
        }

        public Builder note(String val) {
            note = val;
            return this;
        }

        public Builder orderCode(String val) {
            orderCode = val;
            return this;
        }

        public Builder storeCode(String val) {
            storeCode = val;
            return this;
        }

        public Builder platformOrderType(String val) {
            platformOrderType = val;
            return this;
        }

        public Builder orderSource(Integer val) {
            orderSource = val;
            return this;
        }

        public Builder ownerUserId(String val) {
            ownerUserId = val;
            return this;
        }

        public Builder ownerUserName(String val) {
            ownerUserName = val;
            return this;
        }

        public Builder isStoreStrategy(Integer val) {
            isStoreStrategy = val;
            return this;
        }

        public Builder lpCode(String val) {
            lpCode = val;
            return this;
        }

        public Builder deliveryTimePreference(Date val) {
            deliveryTimePreference = val;
            return this;
        }

        public Builder tradeMode(String val) {
            tradeMode = val;
            return this;
        }

        public Builder commonField(String val) {
            commonField = val;
            return this;
        }

        public Builder payCurr(String val) {
            payCurr = val;
            return this;
        }

        public Builder statisticsFlag(String val) {
            statisticsFlag = val;
            return this;
        }

        public Builder orderBatchNo(String val) {
            orderBatchNo = val;
            return this;
        }

        public Builder pod(String val) {
            pod = val;
            return this;
        }

        public Builder fCode(String val) {
            fCode = val;
            return this;
        }

        public Builder orderStatus(String val) {
            orderStatus = val;
            return this;
        }

        public Builder customsType(Integer val) {
            customsType = val;
            return this;
        }

        public Builder ofcBcExpGoodsReqDtoList(List<OfcBcExpGoodsReqDto> val) {
            ofcBcExpGoodsReqDtoList = val;
            return this;
        }

        public OfcBcExpOrderReqDto build() {
            return new OfcBcExpOrderReqDto(this);
        }
    }

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

    public List<OfcBcExpGoodsReqDto> getOfcBcExpGoodsReqDtoList() {
        return ofcBcExpGoodsReqDtoList;
    }
}

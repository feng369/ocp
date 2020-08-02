package com.topideal.supplychain.ocp.order.model;

import com.topideal.supplychain.common.model.BaseEntity;
import com.topideal.supplychain.ocp.enums.BusiModeEnum;
import com.topideal.supplychain.ocp.enums.CustomsCodeEnum;
import com.topideal.supplychain.ocp.enums.ForwardSystemEnum;
import com.topideal.supplychain.ocp.enums.OrderStatusEnum;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderVip extends BaseEntity {

    /**
     * id集合
     */
    private List<Long> ids;
    /**
     * 订单类型 1-新增，2-修改
     */
    private String appType;

    /**
     * 内部订单
     */
    private String code;

    /**
     * 订单下单时间
     */
    private Date receiptTime;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 店铺Id
     */
    private String storeId;

    /**
     * 电商平台代码
     */
    private String ebpCode;

    /**
     * 电商平台名称
     */
    private String ebpName;

    /**
     * 电商企业代码
     */
    private String ebcCode;

    /**
     * 电商企业名称
     */
    private String ebcName;

    /**
     * 物流运单编号
     */
    private String logisticsNo;

    /**
     * 物流企业代码
     */
    private String logisticsCode;

    /**
     * 物流企业名称
     */
    private String logisticsName;

    /**
     * 检验检疫电商平台代码
     */
    private String ciqEbpCode;

    /**
     * 检验检疫电商企业代码
     */
    private String ciqEbccode;

    /**
     * 检验检疫物流企业代码
     */
    private String ciqLogisticsCode;

    /**
     * 检验检疫物流企业代码
     */
    private String checkOrgCode;

    /**
     * 进出口标记
     */
    private String ieFlag;

    /**
     * 申报海关代码
     */
    private String customsCode;

    /**
     * 订购人证件类型
     */
    private String buyerIdType;

    /**
     * 订购人证件号码
     */
    private String buyerIdNumber;

    /**
     * 订购人姓名
     */
    private String buyerName;

    /**
     * 订购人电话
     */
    private String buyerTelephone;

    /**
     * 进出口类型
     */
    private String tradeMode;

    /**
     * 运输方式
     */
    private String trafMode;

    /**
     * 运输工具编号
     */
    private String trafNo;

    /**
     * 航班航次号
     */
    private String voyageNo;

    /**
     * 提运单号
     */
    private String billNo;

    /**
     * 起运国（地区）
     */
    private String country;

    /**
     * 支付交易号
     */
    private String paymentNo;

    /**
     * 总货值
     */
    private BigDecimal totalValues;

    /**
     * 运费
     */
    private BigDecimal freight;

    /**
     * 保费
     */
    private BigDecimal insuredFee;

    /**
     * 币制
     */
    private String currency;

    /**
     * 包装种类代码
     */
    private String wrapType;

    /**
     * 件数
     */
    private String packNo;

    /**
     * 毛重（千克）
     */
    private BigDecimal grossWeight;

    /**
     * 净重（千克）
     */
    private BigDecimal netWeight;

    /**
     * 收货人
     */
    private String consigneeCname;

    /**
     * 收货人电话
     */
    private String consigneeTel;

    /**
     * 收件地址
     */
    private String consigneeAddress;

    /**
     * 收件国家
     */
    private String consigneeCountry;

    /**
     * 收件地址省份
     */
    private String consigneeProvince;

    /**
     * 收件地址市
     */
    private String consigneeCity;

    /**
     * 收件地址区县
     */
    private String consigneeDistrict;

    /**
     * 发货人证件类型
     */
    private String idType;

    /**
     * 发货人
     */
    private String consignorCname;

    /**
     * 发货人证件号码
     */
    private String idCard;

    /**
     * 发货人电话
     */
    private String consignorTel;

    /**
     * 发货人地址
     */
    private String consignorAddress;

    /**
     * 非现金抵扣金额
     */
    private BigDecimal favourableMoney;

    /**
     * 代扣税款
     */
    private BigDecimal taxFee;

    /**
     * 实际支付金额
     */
    private BigDecimal payMount;

    /**
     * 备注
     */
    private String note;

    /**
     * 订单状态:10 未同步；60下发失败；70下发成功；100回抛失败；110回抛成功
     */
    private OrderStatusEnum sendFlag;

    /**
     * 转发系统
     */
    private ForwardSystemEnum sendSystem;

    /**
     * 失败原因
     */
    private String failReason;

    /**
     * 唯品会商品
     */
    private List<OrderVipItem> mpOrderGoodsList;


    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType == null ? null : appType.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId == null ? null : storeId.trim();
    }

    public String getEbpCode() {
        return ebpCode;
    }

    public void setEbpCode(String ebpCode) {
        this.ebpCode = ebpCode == null ? null : ebpCode.trim();
    }

    public String getEbpName() {
        return ebpName;
    }

    public void setEbpName(String ebpName) {
        this.ebpName = ebpName == null ? null : ebpName.trim();
    }

    public String getEbcCode() {
        return ebcCode;
    }

    public void setEbcCode(String ebcCode) {
        this.ebcCode = ebcCode == null ? null : ebcCode.trim();
    }

    public String getEbcName() {
        return ebcName;
    }

    public void setEbcName(String ebcName) {
        this.ebcName = ebcName == null ? null : ebcName.trim();
    }

    public String getLogisticsNo() {
        return logisticsNo;
    }

    public void setLogisticsNo(String logisticsNo) {
        this.logisticsNo = logisticsNo == null ? null : logisticsNo.trim();
    }

    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode == null ? null : logisticsCode.trim();
    }

    public String getLogisticsName() {
        return logisticsName;
    }

    public void setLogisticsName(String logisticsName) {
        this.logisticsName = logisticsName == null ? null : logisticsName.trim();
    }

    public String getCiqEbpCode() {
        return ciqEbpCode;
    }

    public void setCiqEbpCode(String ciqEbpCode) {
        this.ciqEbpCode = ciqEbpCode == null ? null : ciqEbpCode.trim();
    }

    public String getCiqEbccode() {
        return ciqEbccode;
    }

    public void setCiqEbccode(String ciqEbccode) {
        this.ciqEbccode = ciqEbccode;
    }

    public String getCiqLogisticsCode() {
        return ciqLogisticsCode;
    }

    public void setCiqLogisticsCode(String ciqLogisticsCode) {
        this.ciqLogisticsCode = ciqLogisticsCode == null ? null : ciqLogisticsCode.trim();
    }

    public String getCheckOrgCode() {
        return checkOrgCode;
    }

    public void setCheckOrgCode(String checkOrgCode) {
        this.checkOrgCode = checkOrgCode == null ? null : checkOrgCode.trim();
    }

    public String getIeFlag() {
        return ieFlag;
    }

    public void setIeFlag(String ieFlag) {
        this.ieFlag = ieFlag == null ? null : ieFlag.trim();
    }

    public CustomsCodeEnum getCustomsCode() {
        return CustomsCodeEnum.getValueEnum(customsCode);
    }

    public void setCustomsCode(String customsCode) {
        this.customsCode = customsCode;
    }

    public String getBuyerIdType() {
        return buyerIdType;
    }

    public void setBuyerIdType(String buyerIdType) {
        this.buyerIdType = buyerIdType == null ? null : buyerIdType.trim();
    }

    public String getBuyerIdNumber() {
        return buyerIdNumber;
    }

    public void setBuyerIdNumber(String buyerIdNumber) {
        this.buyerIdNumber = buyerIdNumber == null ? null : buyerIdNumber.trim();
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName == null ? null : buyerName.trim();
    }

    public String getBuyerTelephone() {
        return buyerTelephone;
    }

    public void setBuyerTelephone(String buyerTelephone) {
        this.buyerTelephone = buyerTelephone == null ? null : buyerTelephone.trim();
    }

    public BusiModeEnum getTradeMode() {
        return BusiModeEnum.getValueEnum(tradeMode);
    }

    /**
     * 解析唯品业务类型
     * @return
     */
    public BusiModeEnum getVipTradeMode() {
        return BusiModeEnum.getVpEnum(tradeMode);
    }

    public void setTradeMode(String tradeMode) {
        this.tradeMode = tradeMode;
    }

    public String getTrafMode() {
        return trafMode;
    }

    public void setTrafMode(String trafMode) {
        this.trafMode = trafMode == null ? null : trafMode.trim();
    }

    public String getTrafNo() {
        return trafNo;
    }

    public void setTrafNo(String trafNo) {
        this.trafNo = trafNo == null ? null : trafNo.trim();
    }

    public String getVoyageNo() {
        return voyageNo;
    }

    public void setVoyageNo(String voyageNo) {
        this.voyageNo = voyageNo == null ? null : voyageNo.trim();
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo == null ? null : billNo.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo == null ? null : paymentNo.trim();
    }

    public BigDecimal getTotalValues() {
        return totalValues;
    }

    public void setTotalValues(BigDecimal totalValues) {
        this.totalValues = totalValues;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public BigDecimal getInsuredFee() {
        return insuredFee;
    }

    public void setInsuredFee(BigDecimal insuredFee) {
        this.insuredFee = insuredFee;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public String getWrapType() {
        return wrapType;
    }

    public void setWrapType(String wrapType) {
        this.wrapType = wrapType == null ? null : wrapType.trim();
    }

    public String getPackNo() {
        return packNo;
    }

    public void setPackNo(String packNo) {
        this.packNo = packNo == null ? null : packNo.trim();
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

    public String getConsigneeCname() {
        return consigneeCname;
    }

    public void setConsigneeCname(String consigneeCname) {
        this.consigneeCname = consigneeCname == null ? null : consigneeCname.trim();
    }

    public String getConsigneeTel() {
        return consigneeTel;
    }

    public void setConsigneeTel(String consigneeTel) {
        this.consigneeTel = consigneeTel == null ? null : consigneeTel.trim();
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress == null ? null : consigneeAddress.trim();
    }

    public String getConsigneeCountry() {
        return consigneeCountry;
    }

    public void setConsigneeCountry(String consigneeCountry) {
        this.consigneeCountry = consigneeCountry == null ? null : consigneeCountry.trim();
    }

    public String getConsigneeProvince() {
        return consigneeProvince;
    }

    public void setConsigneeProvince(String consigneeProvince) {
        this.consigneeProvince = consigneeProvince == null ? null : consigneeProvince.trim();
    }

    public String getConsigneeCity() {
        return consigneeCity;
    }

    public void setConsigneeCity(String consigneeCity) {
        this.consigneeCity = consigneeCity == null ? null : consigneeCity.trim();
    }

    public String getConsigneeDistrict() {
        return consigneeDistrict;
    }

    public void setConsigneeDistrict(String consigneeDistrict) {
        this.consigneeDistrict = consigneeDistrict == null ? null : consigneeDistrict.trim();
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType == null ? null : idType.trim();
    }

    public String getConsignorCname() {
        return consignorCname;
    }

    public void setConsignorCname(String consignorCname) {
        this.consignorCname = consignorCname == null ? null : consignorCname.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getConsignorTel() {
        return consignorTel;
    }

    public void setConsignorTel(String consignorTel) {
        this.consignorTel = consignorTel == null ? null : consignorTel.trim();
    }

    public String getConsignorAddress() {
        return consignorAddress;
    }

    public void setConsignorAddress(String consignorAddress) {
        this.consignorAddress = consignorAddress == null ? null : consignorAddress.trim();
    }

    public BigDecimal getFavourableMoney() {
        return favourableMoney;
    }

    public void setFavourableMoney(BigDecimal favourableMoney) {
        this.favourableMoney = favourableMoney;
    }

    public BigDecimal getTaxFee() {
        return taxFee;
    }

    public void setTaxFee(BigDecimal taxFee) {
        this.taxFee = taxFee;
    }

    public BigDecimal getPayMount() {
        return payMount;
    }

    public void setPayMount(BigDecimal payMount) {
        this.payMount = payMount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public OrderStatusEnum getSendFlag() {
        return sendFlag;
    }

    public void setSendFlag(OrderStatusEnum sendFlag) {
        this.sendFlag = sendFlag;
    }

    public ForwardSystemEnum getSendSystem() {
        return sendSystem;
    }

    public void setSendSystem(ForwardSystemEnum sendSystem) {
        this.sendSystem = sendSystem;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason == null ? null : failReason.trim();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    public List<OrderVipItem> getMpOrderGoodsList() {
        return mpOrderGoodsList;
    }

    public void setMpOrderGoodsList(List<OrderVipItem> mpOrderGoodsList) {
        this.mpOrderGoodsList = mpOrderGoodsList;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getReceiptTime() {
        return receiptTime;
    }

    public void setReceiptTime(Date receiptTime) {
        this.receiptTime = receiptTime;
    }
}
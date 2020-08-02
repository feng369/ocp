package com.topideal.supplychain.ocp.order.model;

import com.topideal.supplychain.common.model.BaseEntity;
import com.topideal.supplychain.ocp.enums.BusiModeEnum;
import com.topideal.supplychain.ocp.enums.ForwardSystemEnum;
import com.topideal.supplychain.ocp.enums.OrderStatusEnum;

import java.math.BigDecimal;
import java.util.Date;
import org.omg.PortableInterceptor.INACTIVE;

/**
 * <p>标题: TODO</p>
 * <p>功能: TODO</p>
 * <p>模块: ocp2</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.order.model</p>
 * <p>作者: topIdeal</p>
 * <p>创建日期: 2019-12-17 17:14</p>
 *
 * @version 1.0
 */
public class OrderGs extends BaseEntity {
    /**
     * 内部订单号
     */
    private String code;
    /**
     * 抓单定义ID
     */
    private String grabId;
    /**
     * 电商平台编码
     */
    private String ebpCode;
    /**
     * 电商企业编码
     */
    private String ebcCode;
    /**
     * 海关关区代码
     */
    private String customsCode;
    /**
     * 国检代码
     */
    private String ciqCode;
    /**
     * 物流企业
     */
    private String logisticsCode;
    /**
     * 业务模式
     */
    private BusiModeEnum busiMode;
    /**
     * 店铺订单id
     */
    private String shopOrderId;
    /**
     * 履约单号
     */
    private String consignCode;
    /**
     * 发货单状态 100：待发货 ，200：已发货，300：无需发货
     */
    private Integer status;
    /**
     * 发货单状态描述
     */
    private String statusDesc;
    /**
     * 冻结状态；0：未冻结，1：已冻结，2：已解冻，3：永久冻结
     */
    private Integer freezeStatus;
    /**
     * 冻结状态描述
     */
    private String freezeStatusDesc;
    /**
     * 支付时间
     */
    private Date payTime;
    /**
     * 创建时间(环球捕手)
     */
    private Date gsCreateTime;
    /**
     * 支付渠道
     */
    private String payChannel;
    /**
     * 支付交易号
     */
    private String tradeNo;
    /**
     * 发货单总运费
     */
    private BigDecimal bondedAreaShipExpense;
    /**
     * 发货单总货款（不含税）
     */
    private BigDecimal bondedAreaGoodsPrice;
    /**
     * 发货单总非现金抵扣金额
     */
    private BigDecimal bondedAreaNonCashDeduct;
    /**
     * 发货单总税款金额
     */
    private BigDecimal bondedAreaTax;
    /**
     * 发货单总实付金额
     */
    private BigDecimal bondedAreaPayCash;

    /**
     * 收货方姓名
     */
    private String shipToName;
    /**
     * 收货方手机号
     */
    private String shipToMobile;
    /**
     * 收货方地址邮编
     */
    private String shipToZip;
    /**
     * 收货方地址省份,直辖市省市相同
     */
    private String shipToProvince;
    /**
     * 收货方地址市,直辖市省市相同
     */
    private String shipToCity;
    /**
     * 收货方地址行政区,市/县级行政区
     */
    private String shipToDistrict;
    /**
     * 收货方地址镇,市级行政区对应街道/县级行政区对应城镇收货城市编码
     */
    private String shipToTown;
    /**
     * 收货方详细收货地址,街道、小区、门牌号收货地区
     */
    private String shipToAddress;
    /**
     * 身份证号码
     */
    private String idCardNo;
    /**
     * 身份证姓名
     */
    private String idCardName;
    /**
     * 身份证正面照片(图片url)
     */
    private String positiveUrl;
    /**
     * 身份证反面照片(图片url)
     */
    private String otherSideUrl;
    /**
     * 物流公司
     */
    private String logisticsChannel;
    /**
     * 物流编号
     */
    private String logisticsNumber;
    /**
     * 包裹状态
     */
    private String logisticsStatus;
    /**
     * 包裹状态描述
     */
    private String logisticsStatusDesc;
    /**
     * 店铺编码
     */
    private String shopCode;
    /**
     * 第e仓
     */
    private String fromEplat;
    /**
     * 失败原因
     */
    private String failReason;
    /**
     *
     */
    private OrderStatusEnum toStatus;
    /**
     *
     */
    private String sendKjbStatus;
    /**
     * 转发系统
     */
    private ForwardSystemEnum toSystem;
    /**
     * 外部仓库
     */
    private String warehouseCode;
    /**
     * 备注
     */
    private String note;

    //TODO gs单方新增字段
    private String buyerComment;
    private String sellerComment;
    private Date orderCreateTime;

    public String getGrabId() {
        return grabId;
    }

    public void setGrabId(String grabId) {
        this.grabId = grabId;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public OrderStatusEnum getToStatus() {
        return toStatus;
    }

    public String getSendKjbStatus() {
        return sendKjbStatus;
    }

    public void setSendKjbStatus(String sendKjbStatus) {
        this.sendKjbStatus = sendKjbStatus;
    }

    public void setToStatus(OrderStatusEnum toStatus) {
        this.toStatus = toStatus;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEbpCode() {
        return ebpCode;
    }

    public void setEbpCode(String ebpCode) {
        this.ebpCode = ebpCode;
    }

    public String getEbcCode() {
        return ebcCode;
    }

    public void setEbcCode(String ebcCode) {
        this.ebcCode = ebcCode;
    }

    public String getCustomsCode() {
        return customsCode;
    }

    public void setCustomsCode(String customsCode) {
        this.customsCode = customsCode;
    }

    public String getCiqCode() {
        return ciqCode;
    }

    public void setCiqCode(String ciqCode) {
        this.ciqCode = ciqCode;
    }

    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode;
    }

    public BusiModeEnum getBusiMode() {
        return busiMode;
    }

    public void setBusiMode(BusiModeEnum busiMode) {
        this.busiMode = busiMode;
    }

    public String getShipToName() {
        return shipToName;
    }

    public void setShipToName(String shipToName) {
        this.shipToName = shipToName;
    }

    public String getShipToMobile() {
        return shipToMobile;
    }

    public void setShipToMobile(String shipToMobile) {
        this.shipToMobile = shipToMobile;
    }

    public String getShipToZip() {
        return shipToZip;
    }

    public void setShipToZip(String shipToZip) {
        this.shipToZip = shipToZip;
    }

    public String getShipToProvince() {
        return shipToProvince;
    }

    public void setShipToProvince(String shipToProvince) {
        this.shipToProvince = shipToProvince;
    }

    public String getShipToCity() {
        return shipToCity;
    }

    public void setShipToCity(String shipToCity) {
        this.shipToCity = shipToCity;
    }

    public String getShipToDistrict() {
        return shipToDistrict;
    }

    public void setShipToDistrict(String shipToDistrict) {
        this.shipToDistrict = shipToDistrict;
    }

    public String getShipToTown() {
        return shipToTown;
    }

    public void setShipToTown(String shipToTown) {
        this.shipToTown = shipToTown;
    }

    public String getShipToAddress() {
        return shipToAddress;
    }

    public void setShipToAddress(String shipToAddress) {
        this.shipToAddress = shipToAddress;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getIdCardName() {
        return idCardName;
    }

    public void setIdCardName(String idCardName) {
        this.idCardName = idCardName;
    }

    public String getPositiveUrl() {
        return positiveUrl;
    }

    public void setPositiveUrl(String positiveUrl) {
        this.positiveUrl = positiveUrl;
    }

    public String getOtherSideUrl() {
        return otherSideUrl;
    }

    public void setOtherSideUrl(String otherSideUrl) {
        this.otherSideUrl = otherSideUrl;
    }

    public String getLogisticsChannel() {
        return logisticsChannel;
    }

    public void setLogisticsChannel(String logisticsChannel) {
        this.logisticsChannel = logisticsChannel;
    }

    public String getLogisticsNumber() {
        return logisticsNumber;
    }

    public void setLogisticsNumber(String logisticsNumber) {
        this.logisticsNumber = logisticsNumber;
    }

    public String getLogisticsStatus() {
        return logisticsStatus;
    }

    public void setLogisticsStatus(String logisticsStatus) {
        this.logisticsStatus = logisticsStatus;
    }

    public String getLogisticsStatusDesc() {
        return logisticsStatusDesc;
    }

    public void setLogisticsStatusDesc(String logisticsStatusDesc) {
        this.logisticsStatusDesc = logisticsStatusDesc;
    }

    public String getShopOrderId() {
        return shopOrderId;
    }

    public void setShopOrderId(String shopOrderId) {
        this.shopOrderId = shopOrderId;
    }

    public String getConsignCode() {
        return consignCode;
    }

    public void setConsignCode(String consignCode) {
        this.consignCode = consignCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public Integer getFreezeStatus() {
        return freezeStatus;
    }

    public void setFreezeStatus(Integer freezeStatus) {
        this.freezeStatus = freezeStatus;
    }

    public String getFreezeStatusDesc() {
        return freezeStatusDesc;
    }

    public void setFreezeStatusDesc(String freezeStatusDesc) {
        this.freezeStatusDesc = freezeStatusDesc;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getGsCreateTime() {
        return gsCreateTime;
    }

    public void setGsCreateTime(Date gsCreateTime) {
        this.gsCreateTime = gsCreateTime;
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public BigDecimal getBondedAreaShipExpense() {
        return bondedAreaShipExpense;
    }

    public void setBondedAreaShipExpense(BigDecimal bondedAreaShipExpense) {
        this.bondedAreaShipExpense = bondedAreaShipExpense;
    }

    public BigDecimal getBondedAreaGoodsPrice() {
        return bondedAreaGoodsPrice;
    }

    public void setBondedAreaGoodsPrice(BigDecimal bondedAreaGoodsPrice) {
        this.bondedAreaGoodsPrice = bondedAreaGoodsPrice;
    }

    public BigDecimal getBondedAreaNonCashDeduct() {
        return bondedAreaNonCashDeduct;
    }

    public void setBondedAreaNonCashDeduct(BigDecimal bondedAreaNonCashDeduct) {
        this.bondedAreaNonCashDeduct = bondedAreaNonCashDeduct;
    }

    public BigDecimal getBondedAreaTax() {
        return bondedAreaTax;
    }

    public void setBondedAreaTax(BigDecimal bondedAreaTax) {
        this.bondedAreaTax = bondedAreaTax;
    }

    public BigDecimal getBondedAreaPayCash() {
        return bondedAreaPayCash;
    }

    public void setBondedAreaPayCash(BigDecimal bondedAreaPayCash) {
        this.bondedAreaPayCash = bondedAreaPayCash;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getFromEplat() {
        return fromEplat;
    }

    public void setFromEplat(String fromEplat) {
        this.fromEplat = fromEplat;
    }

    public ForwardSystemEnum getToSystem() {
        return toSystem;
    }

    public void setToSystem(ForwardSystemEnum toSystem) {
        this.toSystem = toSystem;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getBuyerComment() {
        return buyerComment;
    }

    public void setBuyerComment(String buyerComment) {
        this.buyerComment = buyerComment;
    }

    public String getSellerComment() {
        return sellerComment;
    }

    public void setSellerComment(String sellerComment) {
        this.sellerComment = sellerComment;
    }

    public Date getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(Date orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }




}

package com.topideal.supplychain.ocp.order.model;

import com.topideal.supplychain.common.model.BaseEntity;
import com.topideal.supplychain.ocp.enums.ForwardSystemEnum;
import com.topideal.supplychain.ocp.enums.OrderStatusEnum;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author wanzhaozhang
 * @date 2019/11/28
 * @description 宝妈订单
 **/
public class OrderBaoma extends BaseEntity {
    /**
     * ocp内部订单号
     */
    private String code;

    /**
     * 平台订单号 渝欧订单号，一个订单只能对应一个包裹
     */
    private String orderNo;

    /**
     * 收货人姓名
     */
    private String consigneeName;

    /**
     * 收货人电话
     */
    private String consigneeTel;

    /**
     * 收货人身份证
     */
    private String consigneeIdCard;

    /**
     * 收货地址省
     */
    private String provinceName;

    /**
     * 收货地址市
     */
    private String cityName;

    /**
     * 收货地址区
     */
    private String districtName;

    /**
     * 收货详细地址 不含省市县
     */
    private String address;

    /**
     * 支付人姓名
     */
    private String payerName;

    /**
     * 支付人身份证
     */
    private String payerIdCard;

    /**
     * 支付人电话
     */
    private String payerTel;

    /**
     * 订单总金额 货税分离模式下传送 ，所有费用的总金额(税金+商品金额[含运费]）
     */
    private BigDecimal totalFee;

    /**
     * 支付时间
     */
    private String payTime;

    /**
     * 下单时间
     */
    private String orderTime;

    /**
     * 运费 订单总运费
     */
    private BigDecimal freight;

    /**
     * 支付方式 请参照支付方式数据字典
     */
    private String payType;

    /**
     * 支付流水号
     */
    private String payStreamNo;


    /**
     * 发送系统
     */
    private ForwardSystemEnum sendSystem;

    /**
     * 发送状态:10-制单,20-已抓取详细,60-下发失败,70-下发成功
     */
    private OrderStatusEnum sendState;

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
     * 业务模式
     */
    private String busiMode;

    /**
     * 企业编码
     */
    private String enterCode;

    /**
     * 失败原因
     */
    private String failReason;

    /**
     * 海关类型
     */
    private String customsType;

    /**
     * 申报关区
     */
    private String customsCode;

    /**
     * 申报国检
     */
    private String ciqbCode;

    /**
     * 备注
     */
    private String remark;


    /**
     * 店铺编码/名称
     */
    private String storeCode;

    private List<OrderBaomaGoods>  orderDetail;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getConsigneeTel() {
        return consigneeTel;
    }

    public void setConsigneeTel(String consigneeTel) {
        this.consigneeTel = consigneeTel;
    }

    public String getConsigneeIdCard() {
        return consigneeIdCard;
    }

    public void setConsigneeIdCard(String consigneeIdCard) {
        this.consigneeIdCard = consigneeIdCard;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getPayerIdCard() {
        return payerIdCard;
    }

    public void setPayerIdCard(String payerIdCard) {
        this.payerIdCard = payerIdCard;
    }

    public String getPayerTel() {
        return payerTel;
    }

    public void setPayerTel(String payerTel) {
        this.payerTel = payerTel;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayStreamNo() {
        return payStreamNo;
    }

    public void setPayStreamNo(String payStreamNo) {
        this.payStreamNo = payStreamNo;
    }

    public ForwardSystemEnum getSendSystem() {
        return sendSystem;
    }

    public void setSendSystem(ForwardSystemEnum sendSystem) {
        this.sendSystem = sendSystem;
    }

    public OrderStatusEnum getSendState() {
        return sendState;
    }

    public void setSendState(OrderStatusEnum sendState) {
        this.sendState = sendState;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getEbpCode() {
        return ebpCode;
    }

    public void setEbpCode(String ebpCode) {
        this.ebpCode = ebpCode;
    }

    public String getEbpName() {
        return ebpName;
    }

    public void setEbpName(String ebpName) {
        this.ebpName = ebpName;
    }

    public String getBusiMode() {
        return busiMode;
    }

    public void setBusiMode(String busiMode) {
        this.busiMode = busiMode;
    }

    public String getEnterCode() {
        return enterCode;
    }

    public void setEnterCode(String enterCode) {
        this.enterCode = enterCode;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public String getCustomsType() {
        return customsType;
    }

    public void setCustomsType(String customsType) {
        this.customsType = customsType;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public List<OrderBaomaGoods> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderBaomaGoods> orderDetail) {
        this.orderDetail = orderDetail;
    }
}

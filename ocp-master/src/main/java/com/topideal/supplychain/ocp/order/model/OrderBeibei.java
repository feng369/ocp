package com.topideal.supplychain.ocp.order.model;

import com.topideal.supplychain.common.model.BaseEntity;
import com.topideal.supplychain.ocp.enums.BusiModeEnum;
import com.topideal.supplychain.ocp.enums.ForwardSystemEnum;
import com.topideal.supplychain.ocp.enums.OrderStatusEnum;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 贝贝订单 实体类
 */
public class OrderBeibei extends BaseEntity {
    /**
     * ocp内部订单号
     */
    private String code;

    /**
     * 订单号/清关报关号
     */
    private String oid;

    /**
     * 订单类型
     */
    private String type;

    /**
     * 入仓类型,仅对联营入仓商家有效.(0:决策中, 1:平台发货，2:商家发货)
     */
    private String warehousingType;

    /**
     * 【全球购】用户标识/贝贝仓平台用户的唯一标识
     */
    private String user;

    /**
     * 用户昵称
     */
    private String nick;

    /**
     * 店铺编码
     */
    private String storeCode;

    /**
     * 电商平台编码
     */
    private String ebpCode;

    /**
     * 电商企业编码
     */
    private String ebcCode;

    /**
     * 关区
     */
    private String customsCode;

    /**
     * 业务模式
     */
    private BusiModeEnum busiMode;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区县
     */
    private String county;

    /**
     * 地址
     */
    private String address;

    /**
     * 专场id
     */
    private String eventId;

    /**
     * 该订单购买的商品总数量
     */
    private String itemNum;

    /**
     * 状态(1:待发货,2:已发货,3:已完成,4:已关闭)
     */
    private String status;

    /**
     * 订单总价(包含贝贝承担的现金券和积分等费用，为商家实际所得金额 含运费)
     */
    private BigDecimal totalFee;

    /**
     * 运费
     */
    private BigDecimal shippingFee;

    /**
     * 最终价格
     */
    private BigDecimal payment;

    /**
     * 发票类型(个人/企业) 为空则不需要开发票
     */
    private String invoiceType;

    /**
     * 发票抬头
     */
    private String invoiceName;

    /**
     * 折扣
     */
    private BigDecimal discount;

    /**
     * 【全球购】收件人身份证号
     */
    private String memberCard;

    /**
     * 【全球购】收件人身份图片，有数据该字段是Object,front_img表示正面，back_img表示反面
     */
    private String memberCardImgs;

    /**
     * 商家备注
     */
    private String sellerRemark;

    /**
     * 买家备注
     */
    private String remark;

    /**
     * 收件人姓名
     */
    private String receiverName;

    /**
     * 收件人手机号
     */
    private String receiverPhone;

    /**
     * 收件地址
     */
    private String receiverAddress;

    /**
     * 没发货为空,快递公司code，如 圆通对应的code为:yuantong
     */
    private String company;

    /**
     * 快递单号
     */
    private String outSid;

    /**
     * 支付渠道
     */
    private String channelName;

    /**
     * 渠道交易流水
     */
    private String channelInfo;

    /**
     * 下单时间
     */
    private Date orderTime;

    /**
     * 付款时间
     */
    private Date payTime;

    /**
     * 发货时间
     */
    private Date shipTime;

    /**
     * 完成时间
     */
    private Date endTime;

    /**
     * 修改时间
     */
    private Date modifiedTime;

    /**
     * 【全球购】税费
     */
    private BigDecimal taxFee;

    /**
     * 【全球购】税费.关税
     */
    private BigDecimal tariffFee;

    /**
     * 【全球购】税费.增值税
     */
    private BigDecimal addedvalueFee;

    /**
     * 【全球购】税费.消费税
     */
    private BigDecimal consumpFee;

    /**
     * 订购人姓名
     */
    private String userName;

    /**
     * 订购人身份证号码
     */
    private String userMemberCard;

    /**
     * 发送系统
     */
    private ForwardSystemEnum sendSystem;

    /**
     * 发送状态
     */
    private OrderStatusEnum sendStatus;

    /**
     * 发送原因
     */
    private String sendReason;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWarehousingType() {
        return warehousingType;
    }

    public void setWarehousingType(String warehousingType) {
        this.warehousingType = warehousingType;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
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

    public BusiModeEnum getBusiMode() {
        return busiMode;
    }

    public void setBusiMode(BusiModeEnum busiMode) {
        this.busiMode = busiMode;
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

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getItemNum() {
        return itemNum;
    }

    public void setItemNum(String itemNum) {
        this.itemNum = itemNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public BigDecimal getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(BigDecimal shippingFee) {
        this.shippingFee = shippingFee;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceName() {
        return invoiceName;
    }

    public void setInvoiceName(String invoiceName) {
        this.invoiceName = invoiceName;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getMemberCard() {
        return memberCard;
    }

    public void setMemberCard(String memberCard) {
        this.memberCard = memberCard;
    }

    public String getMemberCardImgs() {
        return memberCardImgs;
    }

    public void setMemberCardImgs(String memberCardImgs) {
        this.memberCardImgs = memberCardImgs;
    }

    public String getSellerRemark() {
        return sellerRemark;
    }

    public void setSellerRemark(String sellerRemark) {
        this.sellerRemark = sellerRemark;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getOutSid() {
        return outSid;
    }

    public void setOutSid(String outSid) {
        this.outSid = outSid;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelInfo() {
        return channelInfo;
    }

    public void setChannelInfo(String channelInfo) {
        this.channelInfo = channelInfo;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getShipTime() {
        return shipTime;
    }

    public void setShipTime(Date shipTime) {
        this.shipTime = shipTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public BigDecimal getTaxFee() {
        return taxFee;
    }

    public void setTaxFee(BigDecimal taxFee) {
        this.taxFee = taxFee;
    }

    public BigDecimal getTariffFee() {
        return tariffFee;
    }

    public void setTariffFee(BigDecimal tariffFee) {
        this.tariffFee = tariffFee;
    }

    public BigDecimal getAddedvalueFee() {
        return addedvalueFee;
    }

    public void setAddedvalueFee(BigDecimal addedvalueFee) {
        this.addedvalueFee = addedvalueFee;
    }

    public BigDecimal getConsumpFee() {
        return consumpFee;
    }

    public void setConsumpFee(BigDecimal consumpFee) {
        this.consumpFee = consumpFee;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMemberCard() {
        return userMemberCard;
    }

    public void setUserMemberCard(String userMemberCard) {
        this.userMemberCard = userMemberCard;
    }

    public ForwardSystemEnum getSendSystem() {
        return sendSystem;
    }

    public void setSendSystem(ForwardSystemEnum sendSystem) {
        this.sendSystem = sendSystem;
    }

    public OrderStatusEnum getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(OrderStatusEnum sendStatus) {
        this.sendStatus = sendStatus;
    }

    public String getSendReason() {
        return sendReason;
    }

    public void setSendReason(String sendReason) {
        this.sendReason = sendReason;
    }
}
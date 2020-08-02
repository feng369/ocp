package com.topideal.supplychain.ocp.pub.dto;

import com.alibaba.fastjson.annotation.JSONPOJOBuilder;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * <p>标题: </p>
 * <p>模块: ocp-parent</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.pub</p>
 * <p>作者: LeoZhang</p>
 * <p>创建日期: 2019/12/18 09:52</p>
 *
 * @version 1.0
 */
public class OrderContent implements Serializable {

    private static final long serialVersionUID = 7145542074767398081L;

    @JsonProperty("dno")
    private String storeCode; //店铺编码

    @JsonProperty("cp_code")
    private String logisticsCode; //物流公司编码

    @JsonProperty("mail_no")
    private String logisticsNo; //物流运单号

    @JsonProperty("tran_no")
    private String transportNo; //国际快递单号

    @JsonProperty("logistics_id")
    private String logisticsId; //菜鸟物流单号

    @JsonProperty("seller_id")
    private String sellerCode; //淘宝店铺编码

    @JsonProperty("order_create_time")
    private Date orderCreateTime; //订单时间

    @JsonProperty("storehouse_id")
    private String warehouseCode; //海外仓编码

    @JsonProperty("esdno")
    private String esdNo; //卓志速运订单号

    @JsonProperty("trade_no")
    private String orderNo; //交易订单号（平台）

    @JsonProperty("tid")
    private String tid;//存为订单号,部分平台用来申报

    @JsonProperty("payment_enterprise")
    private String paymentEnterprise; //支付企业编号

    @JsonProperty("payment_enterprise_name")
    private String paymentEnterpriseName; //支付企业名称

    @JsonProperty("payment_transaction")
    private String paymentTransaction; //支付流水号

    @JsonProperty("payment_remark")
    private String paymentRemark; //支付信息备注

    @JsonProperty("payment_time")
    private Date paymentTime;//支付时间

    @JsonProperty("out_way_bill_url")
    private String wayBillUrl; //面单url

    @JsonProperty("declare_scheme_sid")
    private String declareSchemeSid; //申报方案编码

    @JsonProperty("product_code")
    private String productCode; //产品代码

    @JsonProperty("total_freight")
    private BigDecimal totalFreight; //运费

    @JsonProperty("total_code")
    private String totalCode; //运费币制

    @JsonProperty("premium_fee")
    private BigDecimal premiumFee; //保费

    @JsonProperty("premium_code")
    private String premiumCurrency; //保费币制

    @JsonProperty("totai_taxes_reference")
    private BigDecimal totalTaxes; //税费

    @JsonProperty("totai_code")
    private String taxCurrency; //税费币制

    @JsonProperty("discount_fee")
    private BigDecimal discountFee; //优惠减免金额

    @JsonProperty("discount_code")
    private String discountCurrency; //优惠减免金额币制

    @JsonProperty("net_weight")
    private BigDecimal netWeight; //包裹总净重，单位：克

    @JsonProperty("itemsum_weight")
    private BigDecimal grossWeight; //包裹总毛重，单位：克

    @JsonProperty("bill")
    private String billNo; //提单号

    @JsonProperty("platform")
    private String platformCode; //电商平台编码

    @JsonProperty("is_trace_source")
    private Integer isTraceSource; //是否推溯源(1是，2否)

    private String zcode; //溯源码值

    @JsonProperty("totai_taxes_pay")
    private BigDecimal totalTaxesPay; //实际支付金额

    @JsonProperty("totai_pay_code")
    private String totalPayCode; //支付币制

    @JsonProperty("is_tran")
    private Integer isTransfer; //换单标志（1是；2否）是否需要换成国内落地配送单号

    @JsonProperty("destion_code")
    private String destinationCode;//目的地代码

    private String remark; //备注

    @JsonProperty("ieflag")
    private String ieFlag;//进出口标记

    @JsonProperty("package_type")
    private String packageType;//包裹类型

    @JsonProperty("parcel_count")
    private Integer parcelCount;//总箱数

    @JsonProperty("order_code")
    private String orderCode;//第三方订单号

    private BigDecimal length;//包裹长度

    private BigDecimal width;//包裹宽度

    private BigDecimal height;//包裹高度

    private String taxId;//中邮税号

    @JsonProperty("total_package_no")
    private String totalPackageNo;//总包号

    @JsonProperty("package_no")
    private String packageNo;//包裹号	部分平台用

    @JsonProperty("package_kind")
    private String packageKind;//包裹属性

    @JsonProperty("isreturn")
    private String isReturn;//是否可退回

    @JsonProperty("delayed_time")
    private Date delayedTime;//延迟发货时间

    @JsonProperty("source")
    private Integer source;//订单来源

    private Sender sender; //发件人信息

    private Receiver receiver; //收件人信息

    private Buyer buyer; //订购人信息

    @JsonAlias({"serciveList","serviceList","serviceTypeList"})
    private List<ServiceType> serviceList; //服务类型集合

    private List<Good> itemList; //商品列表

    private List<Box> boxList;//箱信息

    public static class Sender {
        @JsonProperty("name")
        private String senderName; //姓名
        @JsonProperty("phone")
        private String senderPhone; //电话
        @JsonProperty("mobile")
        private String senderMobile; //手机
        @JsonProperty("country")
        private String senderCountry; //国家
        @JsonProperty("province")
        private String senderProvince; //省
        @JsonProperty("city")
        private String senderCity; //市
        @JsonProperty("county")
        private String senderCounty; //区
        @JsonProperty("address")
        private String senderAddress; //详细地址
        @JsonProperty("zip_code")
        private String senderZipCode; //邮编
        private String street;//发件人街道
        @JsonProperty("send_num")
        private String sendNum;//发件人门牌号


        public String getSenderName() {
            return senderName;
        }

        public void setSenderName(String senderName) {
            this.senderName = senderName;
        }

        public String getSenderPhone() {
            return senderPhone;
        }

        public void setSenderPhone(String senderPhone) {
            this.senderPhone = senderPhone;
        }

        public String getSenderMobile() {
            return senderMobile;
        }

        public void setSenderMobile(String senderMobile) {
            this.senderMobile = senderMobile;
        }

        public String getSenderCountry() {
            return senderCountry;
        }

        public void setSenderCountry(String senderCountry) {
            this.senderCountry = senderCountry;
        }

        public String getSenderProvince() {
            return senderProvince;
        }

        public void setSenderProvince(String senderProvince) {
            this.senderProvince = senderProvince;
        }

        public String getSenderCity() {
            return senderCity;
        }

        public void setSenderCity(String senderCity) {
            this.senderCity = senderCity;
        }

        public String getSenderCounty() {
            return senderCounty;
        }

        public void setSenderCounty(String senderCounty) {
            this.senderCounty = senderCounty;
        }

        public String getSenderAddress() {
            return senderAddress;
        }

        public void setSenderAddress(String senderAddress) {
            this.senderAddress = senderAddress;
        }

        public String getSenderZipCode() {
            return senderZipCode;
        }

        public void setSenderZipCode(String senderZipCode) {
            this.senderZipCode = senderZipCode;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getSendNum() {
            return sendNum;
        }

        public void setSendNum(String sendNum) {
            this.sendNum = sendNum;
        }
    }

    public static class Receiver {
        @JsonProperty("name")
        private String receiverName; //姓名

        @JsonProperty("phone")
        private String receiverPhone; //电话

        @JsonProperty("mobile")
        private String receiverMobile; //手机

        @JsonProperty("identity_no")
        private String receiverIdentityNo; //身份证号码

        @JsonProperty("identity_no_front")
        private String receiverIdentityNoFront; //身份证正面url，pdf，图片

        @JsonProperty("identity_no_back")
        private String receiverIdentityNoBack; //身份证反面url，pdf，图片

        @JsonProperty("country")
        private String receiverCountry; //国家

        @JsonProperty("province")
        private String receiverProvince; //省

        @JsonProperty("city")
        private String receiverCity; //市

        @JsonProperty("county")
        private String receiverCounty; //区

        @JsonProperty("address")
        private String receiverAddress; //详细地址

        @JsonProperty("zip_code")
        private String receiverZipCode; //邮编

        @JsonProperty("identity_type")
        private String identityType;//证件类型，1:身份证；2:护照，默认1

        @JsonProperty("province_code")
        private String provinceCode;//收件人省份/州编码

        private String street;//收件人所在街道
        private String address2;//收件人详细地址2
        private String address3;//收件人详细地址3
        private String houseno;//收件人门牌号
        private String email;//收件人邮箱



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

        public String getReceiverMobile() {
            return receiverMobile;
        }

        public void setReceiverMobile(String receiverMobile) {
            this.receiverMobile = receiverMobile;
        }

        public String getReceiverIdentityNo() {
            return receiverIdentityNo;
        }

        public void setReceiverIdentityNo(String receiverIdentityNo) {
            this.receiverIdentityNo = receiverIdentityNo;
        }

        public String getReceiverIdentityNoFront() {
            return receiverIdentityNoFront;
        }

        public void setReceiverIdentityNoFront(String receiverIdentityNoFront) {
            this.receiverIdentityNoFront = receiverIdentityNoFront;
        }

        public String getReceiverIdentityNoBack() {
            return receiverIdentityNoBack;
        }

        public void setReceiverIdentityNoBack(String receiverIdentityNoBack) {
            this.receiverIdentityNoBack = receiverIdentityNoBack;
        }

        public String getReceiverCountry() {
            return receiverCountry;
        }

        public void setReceiverCountry(String receiverCountry) {
            this.receiverCountry = receiverCountry;
        }

        public String getReceiverProvince() {
            return receiverProvince;
        }

        public void setReceiverProvince(String receiverProvince) {
            this.receiverProvince = receiverProvince;
        }

        public String getReceiverCity() {
            return receiverCity;
        }

        public void setReceiverCity(String receiverCity) {
            this.receiverCity = receiverCity;
        }

        public String getReceiverCounty() {
            return receiverCounty;
        }

        public void setReceiverCounty(String receiverCounty) {
            this.receiverCounty = receiverCounty;
        }

        public String getReceiverAddress() {
            return receiverAddress;
        }

        public void setReceiverAddress(String receiverAddress) {
            this.receiverAddress = receiverAddress;
        }

        public String getReceiverZipCode() {
            return receiverZipCode;
        }

        public void setReceiverZipCode(String receiverZipCode) {
            this.receiverZipCode = receiverZipCode;
        }

        public String getIdentityType() {
            return identityType;
        }

        public void setIdentityType(String identityType) {
            this.identityType = identityType;
        }

        public String getProvinceCode() {
            return provinceCode;
        }

        public void setProvinceCode(String provinceCode) {
            this.provinceCode = provinceCode;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getAddress2() {
            return address2;
        }

        public void setAddress2(String address2) {
            this.address2 = address2;
        }

        public String getAddress3() {
            return address3;
        }

        public void setAddress3(String address3) {
            this.address3 = address3;
        }

        public String getHouseno() {
            return houseno;
        }

        public void setHouseno(String houseno) {
            this.houseno = houseno;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    public static class Buyer {
        @JsonProperty("name")
        private String buyerName; //姓名

        @JsonProperty("phone")
        private String buyerPhone; //联系电话

        @JsonProperty("mobile")
        private String buyerMobile; //注册号

        @JsonProperty("id_type")
        private String buyerIdType; //证件类型

        @JsonProperty("identity_no")
        private String buyerIdentityNo; //证件号码

        public String getBuyerName() {
            return buyerName;
        }

        public void setBuyerName(String buyerName) {
            this.buyerName = buyerName;
        }

        public String getBuyerPhone() {
            return buyerPhone;
        }

        public void setBuyerPhone(String buyerPhone) {
            this.buyerPhone = buyerPhone;
        }

        public String getBuyerMobile() {
            return buyerMobile;
        }

        public void setBuyerMobile(String buyerMobile) {
            this.buyerMobile = buyerMobile;
        }

        public String getBuyerIdType() {
            return buyerIdType;
        }

        public void setBuyerIdType(String buyerIdType) {
            this.buyerIdType = buyerIdType;
        }

        public String getBuyerIdentityNo() {
            return buyerIdentityNo;
        }

        public void setBuyerIdentityNo(String buyerIdentityNo) {
            this.buyerIdentityNo = buyerIdentityNo;
        }


    }

    public static class ServiceType {
        @JsonProperty("service_type")
        private String serviceType; //服务类型

        public String getServiceType() {
            return serviceType;
        }

        public void setServiceType(String serviceType) {
            this.serviceType = serviceType;
        }
    }

    public static class Good {
        @JsonProperty("index")
        private String itemNo; //序号

        @JsonProperty("item")
        private String goodsCode; //商品货号

        @JsonProperty("is_presente")
        private Integer isPresente; //是否商品备案(1是，2否)

        @JsonProperty("item_name")
        private String goodsName; //商品名称

        @JsonProperty("item_category")
        private String itemCategory; //商品行邮税号

        @JsonAlias({"HScode","hScode","Hscode","hscode"})
        private String hsCode; //HS海关编码

        @JsonProperty("cust_no")
        private String custNo; //商品海关备案号

        @JsonProperty("ciq_no")
        private String ciqNo; //国检商品备案号

        @JsonProperty("item_quantity")
        private Integer qty; //商品数量

        @JsonProperty("price_declare")
        private BigDecimal unitPrice; //商品单价

        @JsonProperty("price_code")
        private String currency; //单价币制

        private BigDecimal qty1; //第一法定计量单位

        private String unit1; //第一法定计量单位数量

        private BigDecimal qty2; //第二法定计量单位

        private String unit2; //第二法定计量单位数量

        private String brand; //品牌

        private String spec; //规格型号

        private String unit; //计量单位

        @JsonProperty("item_weight")
        private BigDecimal grossWeight; //商品毛重，g

        @JsonProperty("item_net_weight")
        private BigDecimal netWeight; //商品净重，g

        @JsonProperty("item_tax")
        private String tax; //商品税金

        @JsonProperty("assem_country")
        private String oriCountry; //原产国

        @JsonProperty("assem_area")
        private String oriArea; //国检原产地

        @JsonProperty("bar_code")
        private String barCode; //条形码

        @JsonProperty("nots")
        private String remark; //商品备注

        @JsonProperty("item_enname")
        private String itemEnname;//申报英文名称

        @JsonProperty("prod_use")
        private String prodUse;

        @JsonProperty("prod_material")
        private String prodMaterial;

        public String getItemNo() {
            return itemNo;
        }

        public void setItemNo(String itemNo) {
            this.itemNo = itemNo;
        }

        public String getGoodsCode() {
            return goodsCode;
        }

        public void setGoodsCode(String goodsCode) {
            this.goodsCode = goodsCode;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public BigDecimal getQty1() {
            return qty1;
        }

        public void setQty1(BigDecimal qty1) {
            this.qty1 = qty1;
        }

        public String getUnit1() {
            return unit1;
        }

        public void setUnit1(String unit1) {
            this.unit1 = unit1;
        }

        public BigDecimal getQty2() {
            return qty2;
        }

        public void setQty2(BigDecimal qty2) {
            this.qty2 = qty2;
        }

        public String getUnit2() {
            return unit2;
        }

        public void setUnit2(String unit2) {
            this.unit2 = unit2;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getSpec() {
            return spec;
        }

        public void setSpec(String spec) {
            this.spec = spec;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public Integer getIsPresente() {
            return isPresente;
        }

        public void setIsPresente(Integer isPresente) {
            this.isPresente = isPresente;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getItemCategory() {
            return itemCategory;
        }

        public void setItemCategory(String itemCategory) {
            this.itemCategory = itemCategory;
        }

        public String getHsCode() {
            return hsCode;
        }

        public void setHsCode(String hsCode) {
            this.hsCode = hsCode;
        }

        public String getCustNo() {
            return custNo;
        }

        public void setCustNo(String custNo) {
            this.custNo = custNo;
        }

        public String getCiqNo() {
            return ciqNo;
        }

        public void setCiqNo(String ciqNo) {
            this.ciqNo = ciqNo;
        }

        public Integer getQty() {
            return qty;
        }

        public void setQty(Integer qty) {
            this.qty = qty;
        }

        public BigDecimal getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(BigDecimal unitPrice) {
            this.unitPrice = unitPrice;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
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

        public String getTax() {
            return tax;
        }

        public void setTax(String tax) {
            this.tax = tax;
        }

        public String getOriCountry() {
            return oriCountry;
        }

        public void setOriCountry(String oriCountry) {
            this.oriCountry = oriCountry;
        }

        public String getOriArea() {
            return oriArea;
        }

        public void setOriArea(String oriArea) {
            this.oriArea = oriArea;
        }

        public String getBarCode() {
            return barCode;
        }

        public void setBarCode(String barCode) {
            this.barCode = barCode;
        }

        public String getItemEnname() {
            return itemEnname;
        }

        public void setItemEnname(String itemEnname) {
            this.itemEnname = itemEnname;
        }

        public String getProdUse() {
            return prodUse;
        }

        public void setProdUse(String prodUse) {
            this.prodUse = prodUse;
        }

        public String getProdMaterial() {
            return prodMaterial;
        }

        public void setProdMaterial(String prodMaterial) {
            this.prodMaterial = prodMaterial;
        }
    }

    public static class Box {
        private String number;//箱号
        @JsonProperty("client_weight")
        private BigDecimal clientWeight;//箱子重量
        @JsonProperty("client_length")
        private BigDecimal clientLength;//箱子长度
        @JsonProperty("client_width")
        private BigDecimal clientWidth;//箱子宽度
        @JsonProperty("client_height")
        private BigDecimal clientHeight;//箱子高度

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public BigDecimal getClientWeight() {
            return clientWeight;
        }

        public void setClientWeight(BigDecimal clientWeight) {
            this.clientWeight = clientWeight;
        }

        public BigDecimal getClientLength() {
            return clientLength;
        }

        public void setClientLength(BigDecimal clientLength) {
            this.clientLength = clientLength;
        }

        public BigDecimal getClientWidth() {
            return clientWidth;
        }

        public void setClientWidth(BigDecimal clientWidth) {
            this.clientWidth = clientWidth;
        }

        public BigDecimal getClientHeight() {
            return clientHeight;
        }

        public void setClientHeight(BigDecimal clientHeight) {
            this.clientHeight = clientHeight;
        }
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode;
    }

    public String getLogisticsNo() {
        return logisticsNo;
    }

    public void setLogisticsNo(String logisticsNo) {
        this.logisticsNo = logisticsNo;
    }

    public String getTransportNo() {
        return transportNo;
    }

    public void setTransportNo(String transportNo) {
        this.transportNo = transportNo;
    }

    public String getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(String logisticsId) {
        this.logisticsId = logisticsId;
    }

    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    public Date getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(Date orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getEsdNo() {
        return esdNo;
    }

    public void setEsdNo(String esdNo) {
        this.esdNo = esdNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPaymentEnterprise() {
        return paymentEnterprise;
    }

    public void setPaymentEnterprise(String paymentEnterprise) {
        this.paymentEnterprise = paymentEnterprise;
    }

    public String getPaymentEnterpriseName() {
        return paymentEnterpriseName;
    }

    public void setPaymentEnterpriseName(String paymentEnterpriseName) {
        this.paymentEnterpriseName = paymentEnterpriseName;
    }

    public String getPaymentTransaction() {
        return paymentTransaction;
    }

    public void setPaymentTransaction(String paymentTransaction) {
        this.paymentTransaction = paymentTransaction;
    }

    public String getPaymentRemark() {
        return paymentRemark;
    }

    public void setPaymentRemark(String paymentRemark) {
        this.paymentRemark = paymentRemark;
    }

    public String getWayBillUrl() {
        return wayBillUrl;
    }

    public void setWayBillUrl(String wayBillUrl) {
        this.wayBillUrl = wayBillUrl;
    }

    public String getDeclareSchemeSid() {
        return declareSchemeSid;
    }

    public void setDeclareSchemeSid(String declareSchemeSid) {
        this.declareSchemeSid = declareSchemeSid;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public BigDecimal getTotalFreight() {
        return totalFreight;
    }

    public void setTotalFreight(BigDecimal totalFreight) {
        this.totalFreight = totalFreight;
    }

    public String getTotalCode() {
        return totalCode;
    }

    public void setTotalCode(String totalCode) {
        this.totalCode = totalCode;
    }

    public BigDecimal getPremiumFee() {
        return premiumFee;
    }

    public void setPremiumFee(BigDecimal premiumFee) {
        this.premiumFee = premiumFee;
    }

    public String getPremiumCurrency() {
        return premiumCurrency;
    }

    public void setPremiumCurrency(String premiumCurrency) {
        this.premiumCurrency = premiumCurrency;
    }

    public BigDecimal getTotalTaxes() {
        return totalTaxes;
    }

    public void setTotalTaxes(BigDecimal totalTaxes) {
        this.totalTaxes = totalTaxes;
    }

    public String getTaxCurrency() {
        return taxCurrency;
    }

    public void setTaxCurrency(String taxCurrency) {
        this.taxCurrency = taxCurrency;
    }

    public BigDecimal getDiscountFee() {
        return discountFee;
    }

    public void setDiscountFee(BigDecimal discountFee) {
        this.discountFee = discountFee;
    }

    public String getDiscountCurrency() {
        return discountCurrency;
    }

    public void setDiscountCurrency(String discountCurrency) {
        this.discountCurrency = discountCurrency;
    }

    public BigDecimal getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(BigDecimal netWeight) {
        this.netWeight = netWeight;
    }

    public BigDecimal getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(BigDecimal grossWeight) {
        this.grossWeight = grossWeight;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    public Integer getIsTraceSource() {
        return isTraceSource;
    }

    public void setIsTraceSource(Integer isTraceSource) {
        this.isTraceSource = isTraceSource;
    }

    public String getZcode() {
        return zcode;
    }

    public void setZcode(String zcode) {
        this.zcode = zcode;
    }

    public BigDecimal getTotalTaxesPay() {
        return totalTaxesPay;
    }

    public void setTotalTaxesPay(BigDecimal totalTaxesPay) {
        this.totalTaxesPay = totalTaxesPay;
    }

    public String getTotalPayCode() {
        return totalPayCode;
    }

    public void setTotalPayCode(String totalPayCode) {
        this.totalPayCode = totalPayCode;
    }

    public Integer getIsTransfer() {
        return isTransfer;
    }

    public void setIsTransfer(Integer isTransfer) {
        this.isTransfer = isTransfer;
    }

    public String getDestinationCode() {
        return destinationCode;
    }

    public void setDestinationCode(String destinationCode) {
        this.destinationCode = destinationCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public List<ServiceType> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<ServiceType> serviceList) {
        this.serviceList = serviceList;
    }

    public List<Good> getItemList() {
        return itemList;
    }

    public void setItemList(List<Good> itemList) {
        this.itemList = itemList;
    }

    public String getIeFlag() {
        return ieFlag;
    }

    public void setIeFlag(String ieFlag) {
        this.ieFlag = ieFlag;
    }

    public String getPackageKind() {
        return packageKind;
    }

    public void setPackageKind(String packageKind) {
        this.packageKind = packageKind;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public Integer getParcelCount() {
        return parcelCount;
    }

    public void setParcelCount(Integer parcelCount) {
        this.parcelCount = parcelCount;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getTotalPackageNo() {
        return totalPackageNo;
    }

    public void setTotalPackageNo(String totalPackageNo) {
        this.totalPackageNo = totalPackageNo;
    }



    public Date getDelayedTime() {
        return delayedTime;
    }

    public void setDelayedTime(Date delayedTime) {
        this.delayedTime = delayedTime;
    }

    public List<Box> getBoxList() {
        return boxList;
    }

    public void setBoxList(List<Box> boxList) {
        this.boxList = boxList;
    }

    public String getIsReturn() {
        return isReturn;
    }

    public void setIsReturn(String isReturn) {
        this.isReturn = isReturn;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getPackageNo() {
        return packageNo;
    }

    public void setPackageNo(String packageNo) {
        this.packageNo = packageNo;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }
}

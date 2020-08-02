package com.topideal.supplychain.ocp.kjb.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @description: 跨境宝接单报文dto
 * @author: syq
 * @create: 2019-12-19 14:38
 **/
public class KjbReceiveReqDto implements Serializable {

    private static final long serialVersionUID = 4212473074131355014L;

    @JsonProperty("dno")
    private String storeCode; //店铺编码

    @JsonProperty("cp_code")
    private String logisticsCode; //物流公司编码

    @JsonProperty("mail_no")
    private String logisticsNo; //物流运单号

    @JsonProperty("tran_no")
    private String interLogisticsNo; //国际快递单号

    @JsonProperty("logistics_id")
    private String cainiaoLogisticsNo; //菜鸟物流单号

    @JsonProperty("seller_id")
    private String tbStoreCode; //淘宝店铺编码

    @JsonProperty("order_create_time")
    private Date orderCreateTime; //订单时间

    @JsonProperty("storehouse_id")
    private String overseaHouseCode; //海外仓编码

    @JsonProperty("esdno")
    private String esdNo; //卓志速运订单号

    @JsonProperty("trade_no")
    private String tradeNo; //交易订单号（平台）

    @JsonProperty("payment_enterprise")
    private String payMerchantCode; //支付企业编号

    @JsonProperty("payment_enterprise_name")
    private String payMerchantName; //支付企业名称

    @JsonProperty("payment_transaction")
    private String payNo; //支付流水号

    @JsonProperty("payment_remark")
    private String payRemark; //支付信息备注

    @JsonProperty("out_way_bill_url")
    private String waybillUrl; //面单url

    @JsonProperty("declare_scheme_sid")
    private String declareSchemeSid; //申报方案编码

    @JsonProperty("product_code")
    private String productCode; //产品代码

    @JsonProperty("total_freight")
    private BigDecimal freight; //运费

    @JsonProperty("total_code")
    private String freightCurrency; //运费币制

    @JsonProperty("premium_fee")
    private BigDecimal premium; //保费

    @JsonProperty("premium_code")
    private String premiumCurrency; //保费币制

    @JsonProperty("totai_taxes_reference")
    private BigDecimal tax; //税费

    @JsonProperty("totai_code")
    private String taxCurrency; //税费币制

    @JsonProperty("discount_fee")
    private BigDecimal discount; //优惠减免金额

    @JsonProperty("discount_code")
    private String discountCurrency; //优惠减免金额币制

    @JsonProperty("net_weight")
    private Integer netWeight; //包裹总净重，单位：克

    @JsonProperty("itemsum_weight")
    private Integer grossWeight; //包裹总毛重，单位：克

    @JsonProperty("bill")
    private String billNo; //提单号

    @JsonProperty("platform")
    private String platformCode; //电商平台编码

    @JsonProperty("is_trace_source")
    private Integer isTraceSource; //是否推溯源(1是，2否)

    private String zcode; //溯源码值

    @JsonProperty("totai_taxes_pay")
    private BigDecimal actualPaid; //实际支付金额

    @JsonProperty("totai_pay_code")
    private String actualPaidCurrency; //支付币制

    @JsonProperty("is_tran")
    private Integer isTran; //换单标志（1是；2否）是否需要换成国内落地配送单号

    @JsonProperty("destion_code")
    private String destinationCode;//目的地代码

    private String remark; //备注

    private Sender sender; //发件人信息

    private Receiver receiver; //收件人信息

    private Buyer buyer; //订购人信息

    private List<ServiceType> serciveList; //服务类型集合

    private List<Good> itemList; //商品列表

    public static class Sender {
        private String name; //姓名

        private String phone; //电话

        private String mobile; //手机

        private String country; //国家

        private String province; //省

        private String city; //市

        private String county; //区

        private String address; //详细地址

        @JsonProperty("zip_code")
        private String zipCode; //邮编

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
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

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }
    }

    public static class Receiver {
        private String name; //姓名

        private String phone; //电话

        private String mobile; //手机

        @JsonProperty("identity_no")
        private String identityNo; //身份证号码

        @JsonProperty("identity_no_front")
        private String identityNoFront; //身份证正面url，pdf，图片

        @JsonProperty("identity_no_back")
        private String identityNoBack; //身份证反面url，pdf，图片

        private String country; //国家

        private String province; //省

        private String city; //市

        private String county; //区

        private String address; //详细地址

        @JsonProperty("zip_code")
        private String zipCode; //邮编

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getIdentityNo() {
            return identityNo;
        }

        public void setIdentityNo(String identityNo) {
            this.identityNo = identityNo;
        }

        public String getIdentityNoFront() {
            return identityNoFront;
        }

        public void setIdentityNoFront(String identityNoFront) {
            this.identityNoFront = identityNoFront;
        }

        public String getIdentityNoBack() {
            return identityNoBack;
        }

        public void setIdentityNoBack(String identityNoBack) {
            this.identityNoBack = identityNoBack;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
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

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }
    }

    public static class Buyer {
        private String name; //姓名

        private String phone; //联系电话

        private String mobile; //注册号

        @JsonProperty("id_type")
        private String idType; //证件类型

        @JsonProperty("identity_no")
        private String identityNo; //证件号码

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getIdType() {
            return idType;
        }

        public void setIdType(String idType) {
            this.idType = idType;
        }

        public String getIdentityNo() {
            return identityNo;
        }

        public void setIdentityNo(String identityNo) {
            this.identityNo = identityNo;
        }
    }

    public static class ServiceType {
        @JsonProperty("service_type")
        private Integer serviceType; //服务类型

        public Integer getServiceType() {
            return serviceType;
        }

        public void setServiceType(Integer serviceType) {
            this.serviceType = serviceType;
        }
    }

    public static class Good {
        private String index; //序号

        private String item; //商品货号

        @JsonProperty("is_presente")
        private Integer isPresente; //是否商品备案(1是，2否)

        @JsonProperty("item_name")
        private String goodsName; //商品名称

        @JsonProperty("item_category")
        private String categoryNo; //商品行邮税号

        @JsonAlias({"HScode","hScode","Hscode","hscode"})
        private String hscode; //HS海关编码

        @JsonProperty("cust_no")
        private String custNo; //商品海关备案号

        @JsonProperty("ciq_no")
        private String ciqNo; //国检商品备案号

        @JsonProperty("item_quantity")
        private Integer qty; //商品数量

        @JsonProperty("price_declare")
        private BigDecimal price; //商品单价

        @JsonProperty("price_code")
        private String priceCurrency; //单价币制

        private BigDecimal qty1; //第一法定计量单位

        private String unit1; //第一法定计量单位数量

        private BigDecimal qty2; //第二法定计量单位

        private String unit2; //第二法定计量单位数量

        private String brand; //品牌

        private String spec; //规格型号

        private String unit; //计量单位

        @JsonProperty("item_weight")
        private Integer grossWeight; //商品毛重，g

        @JsonProperty("item_net_weight")
        private Integer netWeight; //商品净重，g

        @JsonProperty("item_tax")
        private String tax; //商品税金

        @JsonProperty("assem_country")
        private String originCountry; //原产国

        @JsonProperty("assem_area")
        private String ciqOriginArea; //国检原产地

        @JsonProperty("bar_code")
        private String barCode; //条形码

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
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

        public String getCategoryNo() {
            return categoryNo;
        }

        public void setCategoryNo(String categoryNo) {
            this.categoryNo = categoryNo;
        }

        public String getHscode() {
            return hscode;
        }

        public void setHscode(String hscode) {
            this.hscode = hscode;
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

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public String getPriceCurrency() {
            return priceCurrency;
        }

        public void setPriceCurrency(String priceCurrency) {
            this.priceCurrency = priceCurrency;
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

        public Integer getGrossWeight() {
            return grossWeight;
        }

        public void setGrossWeight(Integer grossWeight) {
            this.grossWeight = grossWeight;
        }

        public Integer getNetWeight() {
            return netWeight;
        }

        public void setNetWeight(Integer netWeight) {
            this.netWeight = netWeight;
        }

        public String getTax() {
            return tax;
        }

        public void setTax(String tax) {
            this.tax = tax;
        }

        public String getOriginCountry() {
            return originCountry;
        }

        public void setOriginCountry(String originCountry) {
            this.originCountry = originCountry;
        }

        public String getCiqOriginArea() {
            return ciqOriginArea;
        }

        public void setCiqOriginArea(String ciqOriginArea) {
            this.ciqOriginArea = ciqOriginArea;
        }

        public String getBarCode() {
            return barCode;
        }

        public void setBarCode(String barCode) {
            this.barCode = barCode;
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

    public String getInterLogisticsNo() {
        return interLogisticsNo;
    }

    public void setInterLogisticsNo(String interLogisticsNo) {
        this.interLogisticsNo = interLogisticsNo;
    }

    public String getCainiaoLogisticsNo() {
        return cainiaoLogisticsNo;
    }

    public void setCainiaoLogisticsNo(String cainiaoLogisticsNo) {
        this.cainiaoLogisticsNo = cainiaoLogisticsNo;
    }

    public String getTbStoreCode() {
        return tbStoreCode;
    }

    public void setTbStoreCode(String tbStoreCode) {
        this.tbStoreCode = tbStoreCode;
    }

    public Date getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(Date orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public String getOverseaHouseCode() {
        return overseaHouseCode;
    }

    public void setOverseaHouseCode(String overseaHouseCode) {
        this.overseaHouseCode = overseaHouseCode;
    }

    public String getEsdNo() {
        return esdNo;
    }

    public void setEsdNo(String esdNo) {
        this.esdNo = esdNo;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getPayMerchantCode() {
        return payMerchantCode;
    }

    public void setPayMerchantCode(String payMerchantCode) {
        this.payMerchantCode = payMerchantCode;
    }

    public String getPayMerchantName() {
        return payMerchantName;
    }

    public void setPayMerchantName(String payMerchantName) {
        this.payMerchantName = payMerchantName;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public String getPayRemark() {
        return payRemark;
    }

    public void setPayRemark(String payRemark) {
        this.payRemark = payRemark;
    }

    public String getWaybillUrl() {
        return waybillUrl;
    }

    public void setWaybillUrl(String waybillUrl) {
        this.waybillUrl = waybillUrl;
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

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public String getFreightCurrency() {
        return freightCurrency;
    }

    public void setFreightCurrency(String freightCurrency) {
        this.freightCurrency = freightCurrency;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }

    public String getPremiumCurrency() {
        return premiumCurrency;
    }

    public void setPremiumCurrency(String premiumCurrency) {
        this.premiumCurrency = premiumCurrency;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public String getTaxCurrency() {
        return taxCurrency;
    }

    public void setTaxCurrency(String taxCurrency) {
        this.taxCurrency = taxCurrency;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getDiscountCurrency() {
        return discountCurrency;
    }

    public void setDiscountCurrency(String discountCurrency) {
        this.discountCurrency = discountCurrency;
    }

    public Integer getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(Integer netWeight) {
        this.netWeight = netWeight;
    }

    public Integer getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(Integer grossWeight) {
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

    public BigDecimal getActualPaid() {
        return actualPaid;
    }

    public void setActualPaid(BigDecimal actualPaid) {
        this.actualPaid = actualPaid;
    }

    public String getActualPaidCurrency() {
        return actualPaidCurrency;
    }

    public void setActualPaidCurrency(String actualPaidCurrency) {
        this.actualPaidCurrency = actualPaidCurrency;
    }

    public Integer getIsTran() {
        return isTran;
    }

    public void setIsTran(Integer isTran) {
        this.isTran = isTran;
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

    public List<ServiceType> getSerciveList() {
        return serciveList;
    }

    public void setSerciveList(List<ServiceType> serciveList) {
        this.serciveList = serciveList;
    }

    public List<Good> getItemList() {
        return itemList;
    }

    public void setItemList(List<Good> itemList) {
        this.itemList = itemList;
    }

    public String getDestinationCode() {
        return destinationCode;
    }

    public void setDestinationCode(String destinationCode) {
        this.destinationCode = destinationCode;
    }
}

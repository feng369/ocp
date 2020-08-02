package com.topideal.supplychain.ocp.global.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>标题: 全球仓响应实体</p>
 * <p>模块: ocp-parent</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.global</p>
 * <p>作者: LeoZhang</p>
 * <p>创建日期: 2019/12/9 16:16</p>
 *
 * @version 1.0
 */
public class GlobalResponseDto {

    private Status stat;

    private List<Content> content;

    public Status getStat() {
        return stat;
    }

    public void setStat(Status stat) {
        this.stat = stat;
    }

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }

    /**
     * 状态
     */
    public static class Status{
        //回执编码,0为成功，其他为失败
        private String code;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

    /**
     * 订单实体
     */
    public static class Content{
        private PageItem page;

        private List<OrderResult> orders;

        public PageItem getPage() {
            return page;
        }

        public void setPage(PageItem page) {
            this.page = page;
        }

        public List<OrderResult> getOrders() {
            return orders;
        }

        public void setOrders(List<OrderResult> orders) {
            this.orders = orders;
        }


        /**
         * 全球仓订单信息
         */
        public static class OrderResult {

            private Long id;    //出库单ID 必填

            @JsonProperty("orderNo")
            private String orderCode;    //子订单号 必填

            private String scanCode;    //扫描码 必填

            private String routeCode;    //线路编码 必填

            private String mailNo;    //运单号 必填

            private Date orderDate;    //订单日期 必填

            private List<OrderItem> skuList;    //商品列表 必填

            private Consignor consignor;    //寄件方 必填

            private Consignee consignee;    //收件方 必填

            private Payer payerEntity;    //支付方 必填

            private PaymentInfo paymentInfo;    //支付信息 必填

            private Supplier supplier;    //供应商信息 必填

            @JsonInclude(JsonInclude.Include.NON_NULL)
            private Extension extention;    //扩展信息 选填

            private String portCode;    //口岸编码(NBPORT:宁波口岸、CQPORT:重庆口岸、GZPORT:广州口岸、HZPORT:杭州口岸、BJPORT:北京口岸、CDPORT:成都口岸...)

            private String ebpName;    //电商平台名称

            private String ebpCode;    //电商平台编码

            @JsonProperty("declareBizId")
            private String declareCode;    //申报订单号

            private String declarePayType;    //申报支付方式(ALIPAY：支付宝，WEIXINPAY：微信)

            private BigDecimal declarePayAmount;    //支付单申报的支付金额(单位:分)

            private String declarePayNo;    //申报支付流水

            public Long getId() {
                return id;
            }

            public void setId(Long id) {
                this.id = id;
            }

            public String getScanCode() {
                return scanCode;
            }

            public void setScanCode(String scanCode) {
                this.scanCode = scanCode;
            }

            public String getRouteCode() {
                return routeCode;
            }

            public void setRouteCode(String routeCode) {
                this.routeCode = routeCode;
            }

            public String getMailNo() {
                return mailNo;
            }

            public void setMailNo(String mailNo) {
                this.mailNo = mailNo;
            }

            public Date getOrderDate() {
                return orderDate;
            }

            public void setOrderDate(Date orderDate) {
                this.orderDate = orderDate;
            }

            public String getDeclareCode() {
                return declareCode;
            }

            public void setDeclareCode(String declareCode) {
                this.declareCode = declareCode;
            }

            public List<OrderItem> getSkuList() {
                return skuList;
            }

            public void setSkuList(List<OrderItem> skuList) {
                this.skuList = skuList;
            }

            public Consignor getConsignor() {
                return consignor;
            }

            public void setConsignor(Consignor consignor) {
                this.consignor = consignor;
            }

            public Consignee getConsignee() {
                return consignee;
            }

            public void setConsignee(Consignee consignee) {
                this.consignee = consignee;
            }

            public Payer getPayerEntity() {
                return payerEntity;
            }

            public void setPayerEntity(Payer payerEntity) {
                this.payerEntity = payerEntity;
            }

            public PaymentInfo getPaymentInfo() {
                return paymentInfo;
            }

            public void setPaymentInfo(PaymentInfo paymentInfo) {
                this.paymentInfo = paymentInfo;
            }

            public Supplier getSupplier() {
                return supplier;
            }

            public void setSupplier(Supplier supplier) {
                this.supplier = supplier;
            }

            public Extension getExtention() {
                return extention;
            }

            public void setExtention(Extension extention) {
                this.extention = extention;
            }

            public String getPortCode() {
                return portCode;
            }

            public void setPortCode(String portCode) {
                this.portCode = portCode;
            }

            public String getEbpName() {
                return ebpName;
            }

            public void setEbpName(String ebpName) {
                this.ebpName = ebpName;
            }

            public String getEbpCode() {
                return ebpCode;
            }

            public void setEbpCode(String ebpCode) {
                this.ebpCode = ebpCode;
            }

            public String getOrderCode() {
                return orderCode;
            }

            public void setOrderCode(String orderCode) {
                this.orderCode = orderCode;
            }

            public String getDeclarePayType() {
                return declarePayType;
            }

            public void setDeclarePayType(String declarePayType) {
                this.declarePayType = declarePayType;
            }

            public BigDecimal getDeclarePayAmount() {
                return declarePayAmount;
            }

            public void setDeclarePayAmount(BigDecimal declarePayAmount) {
                this.declarePayAmount = declarePayAmount;
            }

            public String getDeclarePayNo() {
                return declarePayNo;
            }

            public void setDeclarePayNo(String declarePayNo) {
                this.declarePayNo = declarePayNo;
            }

            /**
             * 寄件方信息
             */
            public static class Consignor {

                private String name;    //姓名 必填
                private String phone;    //手机 必填

                private String province;    //省 必填

                private String city;    //市 必填

                private String district;    //区 必填

                private String street;    //街道地址 必填

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

                public String getDistrict() {
                    return district;
                }

                public void setDistrict(String district) {
                    this.district = district;
                }

                public String getStreet() {
                    return street;
                }

                public void setStreet(String street) {
                    this.street = street;
                }
            }

            /**
             * 收件方信息
             */
            public static class Consignee {

                private String name;    //姓名 必填

                private String phone;    //手机 必填

                private String province;    //省 必填

                private String city;    //市 必填

                private String district;    //区 必填

                private String street;    //街道地址 必填

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

                public String getDistrict() {
                    return district;
                }

                public void setDistrict(String district) {
                    this.district = district;
                }

                public String getStreet() {
                    return street;
                }

                public void setStreet(String street) {
                    this.street = street;
                }
            }

            /**
             * 支付方信息
             */
            public static class Payer {

                private Integer payerIdType;    //支付人证件类型(1：身份证，2：护照，3，其他)

                private String payerIdNo;    //支付人证件号码

                private String payerName;    //支付人姓名

                public Integer getPayerIdType() {
                    return payerIdType;
                }

                public void setPayerIdType(Integer payerIdType) {
                    this.payerIdType = payerIdType;
                }

                public String getPayerIdNo() {
                    return payerIdNo;
                }

                public void setPayerIdNo(String payerIdNo) {
                    this.payerIdNo = payerIdNo;
                }

                public String getPayerName() {
                    return payerName;
                }

                public void setPayerName(String payerName) {
                    this.payerName = payerName;
                }
            }

            /**
             * 支付信息
             */
            public static class PaymentInfo {

                private BigDecimal tradeAmount;    //实际交易金额，单位分

                private BigDecimal goodsTotalAmount;    //商品总金额，单位分

                private BigDecimal discountAmount;    //折扣金额，单位分

                private BigDecimal freightAmount;    //运费，单位分

                private BigDecimal insuranceAmount;    //保险费用，单位分

                private BigDecimal taxAmount;    //总税金，单位分

                public BigDecimal getTradeAmount() {
                    return tradeAmount;
                }

                public void setTradeAmount(BigDecimal tradeAmount) {
                    this.tradeAmount = tradeAmount;
                }

                public BigDecimal getGoodsTotalAmount() {
                    return goodsTotalAmount;
                }

                public void setGoodsTotalAmount(BigDecimal goodsTotalAmount) {
                    this.goodsTotalAmount = goodsTotalAmount;
                }

                public BigDecimal getDiscountAmount() {
                    return discountAmount;
                }

                public void setDiscountAmount(BigDecimal discountAmount) {
                    this.discountAmount = discountAmount;
                }

                public BigDecimal getFreightAmount() {
                    return freightAmount;
                }

                public void setFreightAmount(BigDecimal freightAmount) {
                    this.freightAmount = freightAmount;
                }

                public BigDecimal getInsuranceAmount() {
                    return insuranceAmount;
                }

                public void setInsuranceAmount(BigDecimal insuranceAmount) {
                    this.insuranceAmount = insuranceAmount;
                }

                public BigDecimal getTaxAmount() {
                    return taxAmount;
                }

                public void setTaxAmount(BigDecimal taxAmount) {
                    this.taxAmount = taxAmount;
                }
            }

            /**
             * 供应商信息
             */
            public static class Supplier {

                private Long id;    //供应商编码（仓库ID） 必填

                private String name;    //供应商名称 必填

                public Long getId() {
                    return id;
                }

                public void setId(Long id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }

            /**
             * 扩展信息
             */
            public static class Extension {

                private String key;    //键

                private String value;    //值

                public String getKey() {
                    return key;
                }

                public void setKey(String key) {
                    this.key = key;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }

            /**
             * 商品信息
             */
            public static class OrderItem {

                private Long id;    //skuID 必填

                @JsonProperty("thirdSkuID")
                private String skuId;    //thirdSkuID 必填

                private String name;    //SKU名称 必填

                private String foreignName;    //SKU外文名称 必填

                private String billName;    //SKU面单名称 必填

                private String barcode;    //商品条码 必填

                private Long count;    //SKU数量 必填

                private BigDecimal declarePrice;    //商户申报价格 必填

                private BigDecimal discountTotalPrice;    //单种商品总折扣金额 必填

                private BigDecimal price;    //SKU供货价 必填

                private String currencyUnit;    //货币单位 必填

                public Long getId() {
                    return id;
                }

                public void setId(Long id) {
                    this.id = id;
                }

                public String getSkuId() {
                    return skuId;
                }

                public void setSkuId(String skuId) {
                    this.skuId = skuId;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getForeignName() {
                    return foreignName;
                }

                public void setForeignName(String foreignName) {
                    this.foreignName = foreignName;
                }

                public String getBillName() {
                    return billName;
                }

                public void setBillName(String billName) {
                    this.billName = billName;
                }

                public String getBarcode() {
                    return barcode;
                }

                public void setBarcode(String barcode) {
                    this.barcode = barcode;
                }

                public Long getCount() {
                    return count;
                }

                public void setCount(Long count) {
                    this.count = count;
                }

                public BigDecimal getDeclarePrice() {
                    return declarePrice;
                }

                public void setDeclarePrice(BigDecimal declarePrice) {
                    this.declarePrice = declarePrice;
                }

                public BigDecimal getDiscountTotalPrice() {
                    return discountTotalPrice;
                }

                public void setDiscountTotalPrice(BigDecimal discountTotalPrice) {
                    this.discountTotalPrice = discountTotalPrice;
                }

                public BigDecimal getPrice() {
                    return price;
                }

                public void setPrice(BigDecimal price) {
                    this.price = price;
                }

                public String getCurrencyUnit() {
                    return currencyUnit;
                }

                public void setCurrencyUnit(String currencyUnit) {
                    this.currencyUnit = currencyUnit;
                }
            }
        }


        public static class PageItem {

            private Integer pageIndex;    //分页查询的页码，默认从第一页开始

            private Integer pageSize;    //分页查询的每页查询最大数量，最大100

            private Integer totalCount;    //搜索结果总数

            public Integer getPageIndex() {
                return pageIndex;
            }

            public void setPageIndex(Integer pageIndex) {
                this.pageIndex = pageIndex;
            }

            public Integer getPageSize() {
                return pageSize;
            }

            public void setPageSize(Integer pageSize) {
                this.pageSize = pageSize;
            }

            public Integer getTotalCount() {
                return totalCount;
            }

            public void setTotalCount(Integer totalCount) {
                this.totalCount = totalCount;
            }
        }
    }



}

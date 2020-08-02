package com.topideal.supplychain.ocp.ofc.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>标题: 订单下发OFC的请求实体</p>
 * <p>模块: ocp-parent</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.ofc.dto</p>
 * <p>作者: LeoZhang</p>
 * <p>创建日期: 2019/11/28 17:19</p>
 *
 * @version 1.0
 */
public class OfcOrderReqDto implements Serializable {
    private static final long serialVersionUID = -6149709696115266910L;
    //数据库ID
    private Long id;
    //key: token
    private String key;
    //订单编号
    private String orderNo;
    //下单时间
    private Date declTime;
    //订购人注册号
    private String buyerRegNo;
    //订购人证件类型
    private String buyerIdType;
    //订购人证件号码
    private String buyerIdNumber;
    //订购人姓名
    private String buyerName;
    //订购人电话
    private String buyerTelephone;
    //收件人证件号
    private String receiveNo;
    //收货人姓名
    private String consignee;
    //收货人电话
    private String consigneeTelephone;
    //指运港代码
    private String pod;
    //收货地址
    private String consigneeAddress;
    //国家sheng
    private String country;
    //省
    private String province;
    //市
    private String city;
    //区
    private String district;
    //邮编
    private String postCode;
    //商品价格
    private BigDecimal goodsValue;
    //运杂费
    private BigDecimal freight;
    //非现金抵扣金额
    private BigDecimal discount;
    //代扣税款
    private BigDecimal taxTotal;
    //税费币种
    private String taxCurr;
    //实际支付金额
    private BigDecimal acturalPaid;
    //支付币制
    private String payCurr;
    //保价费
    private BigDecimal insuredFee;
    //保费币制
    private String insurCurr;
    //运费币种
    private String freightCurr;
    //货款币制
    private String fCode;
    //电商平台编码
    private String ebpCode;
    //电商平台名称
    private String ebpName;
    //电商企业编码
    private String ebcCode;
    //电商企业名称
    private String ebcName;
    //店铺
    private String userId;
    //店铺名称
    private String userName;
    //物流企业ID
    private String logisticsCode;
    //物流企业名称
    private String logisticsName;
    //物流运单编号
    private String logisticsNo;
    //物流备注
    private String logisticsNote;
    //支付企业编码
    private String payCode;
    //支付企业名称
    private String payName;
    //支付交易编号
    private String payTransactionId;
    //支付时间
    private Date payTime;
    //支付备注
    private String payNote;
    //海关关区代码
    private String customsCode;
    //国检检区代码
    private String ciqbCode;
    //进出口标记
    private String ieFlag;
    //业务模式
    private String busiMode;
    //运营
    private Long orderType;
    //订单状态
    private String orderStatus;
    //海关类型
    private Long customsType;
    //申报业务类型
    private String statisticsFlag;
    //运输方式
    private String trafMode;
    //运输工具编号
    private String trafNo;
    //航班航次号
    private String voyageNo;
    //起运国（地区）
    private String shippernCode;
    //包装种类代码
    private String wrapType;
    //发货日期
    private Date shipDate;
    //录入日期
    private Date inputDate;
    //发货人名称
    private String shipperName;
    //发货人地址
    private String shipperAddress;
    //发货人电话
    private String shipperPhone;
    //提运单号
    private String billNo;
    //件数
    private Long packNo;
    //毛重（克）
    private BigDecimal grossWeight;
    //净重（克）
    private BigDecimal netWeight;
    //来源第e仓
    private Long fromEplat;
    //打印抬头
    private String printHeader;
    //换单标志
    private Long changeFlag;
    //是否溯源
    private Long traceFlag;
    //是否重报
    private Long reDeclare;
    //是否VMI模式
    private Long vmiFlag;
    //是否一单多业主
    private Long ownerFlag;
    //是否校验库存策略
    private Long isStoreStrategy;
    //仓储中心订单编码
    private String orderCode;
    //仓储编码
    private String storeCode;
    //单据类型
    private String platformOrderType;
    //订单来源
    private Long orderSource;
    //货主ID
    private String ownerUserId;
    //货主名称
    private String ownerUserName;
    //lp号
    private String lpCode;
    //国内送货时间
    private Date deliveryTimePreference;
    //是否需要打包
    private Long isPackage;
    //送货方式
    private String transportday;
    //备注
    private String note;
    //贸易模式
    private String tradeMode;
    //通用字段
    private String commonField;
    //发货订单批次
    private String orderBatchNo;
    //业务类型
    private String businessType;
    //CB单号
    private String cBCode;
    //海关清单编号
    private String declareFormNo;
    //扩展字段
    private String features;

    /**
     * 为了和默认配置对碰特添加字段
     */
    //电商企业编码
    private String electricCodeConf;
    //电商平台编码
    private String cbePcomCodeConf;
    //物流企业id
    private String logisticsCodeConf;
    //物流企业编码
    private String customsCodeConf;
    //业务模式
    private String busiModeConf;
    //自定义字段1
    private String udf1;
    //自定义字段2
    private String udf2;

    private String udf3;

    private String udf4;
    //商品信息
    private List<OfcGoodsReqDto> goods;



    private OfcOrderReqDto(Builder builder) {
        id = builder.id;
        key = builder.key;
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
        pod = builder.pod;
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
        payCurr = builder.payCurr;
        insuredFee = builder.insuredFee;
        insurCurr = builder.insurCurr;
        freightCurr = builder.freightCurr;
        fCode = builder.fCode;
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
        orderStatus = builder.orderStatus;
        customsType = builder.customsType;
        statisticsFlag = builder.statisticsFlag;
        trafMode = builder.trafMode;
        trafNo = builder.trafNo;
        voyageNo = builder.voyageNo;
        shippernCode = builder.shippernCode;
        wrapType = builder.wrapType;
        shipDate = builder.shipDate;
        inputDate = builder.inputDate;
        shipperName = builder.shipperName;
        shipperAddress = builder.shipperAddress;
        shipperPhone = builder.shipperPhone;
        billNo = builder.billNo;
        packNo = builder.packNo;
        grossWeight = builder.grossWeight;
        netWeight = builder.netWeight;
        fromEplat = builder.fromEplat;
        printHeader = builder.printHeader;
        changeFlag = builder.changeFlag;
        traceFlag = builder.traceFlag;
        reDeclare = builder.reDeclare;
        vmiFlag = builder.vmiFlag;
        ownerFlag = builder.ownerFlag;
        isStoreStrategy = builder.isStoreStrategy;
        orderCode = builder.orderCode;
        storeCode = builder.storeCode;
        platformOrderType = builder.platformOrderType;
        orderSource = builder.orderSource;
        ownerUserId = builder.ownerUserId;
        ownerUserName = builder.ownerUserName;
        lpCode = builder.lpCode;
        deliveryTimePreference = builder.deliveryTimePreference;
        isPackage = builder.isPackage;
        transportday = builder.transportday;
        note = builder.note;
        tradeMode = builder.tradeMode;
        commonField = builder.commonField;
        orderBatchNo = builder.orderBatchNo;
        businessType = builder.businessType;
        cBCode = builder.cBCode;
        declareFormNo = builder.declareFormNo;
        electricCodeConf = builder.electricCodeConf;
        cbePcomCodeConf = builder.cbePcomCodeConf;
        logisticsCodeConf = builder.logisticsCodeConf;
        customsCodeConf = builder.customsCodeConf;
        busiModeConf = builder.busiModeConf;
        udf1 = builder.udf1;
        udf2 = builder.udf2;
        udf3 = builder.udf3;
        udf4 = builder.udf4;
        features = builder.features;
        goods = builder.goods;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * OFC订单报文实体
     */

    public static final class Builder {

        private Long id;
        private String key;
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
        private String pod;
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
        private String payCurr;
        private BigDecimal insuredFee;
        private String insurCurr;
        private String freightCurr;
        private String fCode;
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
        private Long orderType;
        private String orderStatus;
        private Long customsType;
        private String statisticsFlag;
        private String trafMode;
        private String trafNo;
        private String voyageNo;
        private String shippernCode;
        private String wrapType;
        private Date shipDate;
        private Date inputDate;
        private String shipperName;
        private String shipperAddress;
        private String shipperPhone;
        private String billNo;
        private Long packNo;
        private BigDecimal grossWeight;
        private BigDecimal netWeight;
        private Long fromEplat;
        private String printHeader;
        private Long changeFlag;
        private Long traceFlag;
        private Long reDeclare;
        private Long vmiFlag;
        private Long ownerFlag;
        private Long isStoreStrategy;
        private String orderCode;
        private String storeCode;
        private String platformOrderType;
        private Long orderSource;
        private String ownerUserId;
        private String ownerUserName;
        private String lpCode;
        private Date deliveryTimePreference;
        private Long isPackage;
        private String transportday;
        private String note;
        private String tradeMode;
        private String commonField;
        private String orderBatchNo;
        private String businessType;
        private String cBCode;
        private String declareFormNo;
        private String electricCodeConf;
        private String cbePcomCodeConf;
        private String logisticsCodeConf;
        private String customsCodeConf;
        private String busiModeConf;
        private String udf1;
        private String udf2;
        private String udf3;
        private String udf4;
        private String features;

        private List<OfcGoodsReqDto> goods;

        private Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder key(String val) {
            key = val;
            return this;
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

        public Builder pod(String val) {
            pod = val;
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
            goodsValue = val == null ? null : val.multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP);
            return this;
        }

        public Builder freight(BigDecimal val) {
            freight = val == null ? null : val.multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP);
            return this;
        }

        public Builder discount(BigDecimal val) {
            discount = val == null ? null : val.multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP);
            return this;
        }

        public Builder taxTotal(BigDecimal val) {
            taxTotal = val == null ? null : val.multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP);
            return this;
        }

        public Builder taxCurr(String val) {
            taxCurr = val;
            return this;
        }

        public Builder acturalPaid(BigDecimal val) {
            acturalPaid = val == null ? null : val.multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP);
            return this;
        }

        public Builder payCurr(String val) {
            payCurr = val;
            return this;
        }

        public Builder insuredFee(BigDecimal val) {
            insuredFee = val == null ? null : val.multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP);
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

        public Builder fCode(String val) {
            fCode = val;
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

        public Builder orderType(Long val) {
            orderType = val;
            return this;
        }

        public Builder orderStatus(String val) {
            orderStatus = val;
            return this;
        }

        public Builder customsType(Long val) {
            customsType = val;
            return this;
        }

        public Builder statisticsFlag(String val) {
            statisticsFlag = val;
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

        public Builder shippernCode(String val) {
            shippernCode = val;
            return this;
        }

        public Builder wrapType(String val) {
            wrapType = val;
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

        public Builder billNo(String val) {
            billNo = val;
            return this;
        }

        public Builder packNo(Long val) {
            packNo = val;
            return this;
        }

        public Builder grossWeight(BigDecimal val) {
            grossWeight = val == null ? null : val.multiply(BigDecimal.valueOf(1000));
            return this;
        }

        public Builder netWeight(BigDecimal val) {
            netWeight = val == null ? null : val.multiply(BigDecimal.valueOf(1000));
            return this;
        }

        public Builder fromEplat(Long val) {
            fromEplat = val;
            return this;
        }

        public Builder printHeader(String val) {
            printHeader = val;
            return this;
        }

        public Builder changeFlag(Long val) {
            changeFlag = val;
            return this;
        }

        public Builder traceFlag(Long val) {
            traceFlag = val;
            return this;
        }

        public Builder reDeclare(Long val) {
            reDeclare = val;
            return this;
        }

        public Builder vmiFlag(Long val) {
            vmiFlag = val;
            return this;
        }

        public Builder ownerFlag(Long val) {
            ownerFlag = val;
            return this;
        }

        public Builder isStoreStrategy(Long val) {
            isStoreStrategy = val;
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

        public Builder orderSource(Long val) {
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

        public Builder lpCode(String val) {
            lpCode = val;
            return this;
        }

        public Builder deliveryTimePreference(Date val) {
            deliveryTimePreference = val;
            return this;
        }

        public Builder isPackage(Long val) {
            isPackage = val;
            return this;
        }

        public Builder transportday(String val) {
            transportday = val;
            return this;
        }

        public Builder note(String val) {
            note = val;
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

        public Builder orderBatchNo(String val) {
            orderBatchNo = val;
            return this;
        }

        public Builder businessType(String val) {
            businessType = val;
            return this;
        }

        public Builder cBCode(String val) {
            cBCode = val;
            return this;
        }

        public Builder declareFormNo(String val) {
            declareFormNo = val;
            return this;
        }

        public Builder electricCodeConf(String val) {
            electricCodeConf = val;
            return this;
        }

        public Builder cbePcomCodeConf(String val) {
            cbePcomCodeConf = val;
            return this;
        }

        public Builder logisticsCodeConf(String val) {
            logisticsCodeConf = val;
            return this;
        }

        public Builder customsCodeConf(String val) {
            customsCodeConf = val;
            return this;
        }

        public Builder busiModeConf(String val) {
            busiModeConf = val;
            return this;
        }

        public Builder udf1(String val) {
            udf1 = val;
            return this;
        }

        public Builder udf2(String val) {
            udf2 = val;
            return this;
        }

        public Builder udf3(String val) {
            udf3 = val;
            return this;
        }

        public Builder udf4(String val) {
            udf4 = val;
            return this;
        }

        public Builder features(String val){
            features = val;
            return this;
        }

        public Builder goods(List<OfcGoodsReqDto> val) {
            goods = val;
            return this;
        }

        public OfcOrderReqDto build() {
            return new OfcOrderReqDto(this);
        }
    }

    public Long getId() {
        return id;
    }

    public String getKey() {
        return key;
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

    public String getPod() {
        return pod;
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

    public String getPayCurr() {
        return payCurr;
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

    public String getfCode() {
        return fCode;
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

    public Long getOrderType() {
        return orderType;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public Long getCustomsType() {
        return customsType;
    }

    public String getStatisticsFlag() {
        return statisticsFlag;
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

    public String getShippernCode() {
        return shippernCode;
    }

    public String getWrapType() {
        return wrapType;
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

    public String getBillNo() {
        return billNo;
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

    public Long getFromEplat() {
        return fromEplat;
    }

    public String getPrintHeader() {
        return printHeader;
    }

    public Long getChangeFlag() {
        return changeFlag;
    }

    public Long getTraceFlag() {
        return traceFlag;
    }

    public Long getReDeclare() {
        return reDeclare;
    }

    public Long getVmiFlag() {
        return vmiFlag;
    }

    public Long getOwnerFlag() {
        return ownerFlag;
    }

    public Long getIsStoreStrategy() {
        return isStoreStrategy;
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

    public Long getOrderSource() {
        return orderSource;
    }

    public String getOwnerUserId() {
        return ownerUserId;
    }

    public String getOwnerUserName() {
        return ownerUserName;
    }

    public String getLpCode() {
        return lpCode;
    }

    public Date getDeliveryTimePreference() {
        return deliveryTimePreference;
    }

    public Long getIsPackage() {
        return isPackage;
    }

    public String getTransportday() {
        return transportday;
    }

    public String getNote() {
        return note;
    }

    public String getTradeMode() {
        return tradeMode;
    }

    public String getCommonField() {
        return commonField;
    }

    public String getOrderBatchNo() {
        return orderBatchNo;
    }

    public String getBusinessType() {
        return businessType;
    }

    public String getcBCode() {
        return cBCode;
    }

    public String getDeclareFormNo() {
        return declareFormNo;
    }

    public String getElectricCodeConf() {
        return electricCodeConf;
    }

    public String getCbePcomCodeConf() {
        return cbePcomCodeConf;
    }

    public String getLogisticsCodeConf() {
        return logisticsCodeConf;
    }

    public String getCustomsCodeConf() {
        return customsCodeConf;
    }

    public String getBusiModeConf() {
        return busiModeConf;
    }

    public String getUdf1() {
        return udf1;
    }

    public String getUdf2() {
        return udf2;
    }

    public String getUdf3() {
        return udf3;
    }

    public String getUdf4() {
        return udf4;
    }

    public String getFeatures() {
        return features;
    }

    public List<OfcGoodsReqDto> getGoods() {
        return goods;
    }
}

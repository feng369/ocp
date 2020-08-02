package com.topideal.supplychain.ocp.esd.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @ClassName EsdRequest
 * @TODO 金额为元，重量为G
 * @Author zhangzhihao
 * @DATE 2019/12/10 19:13
 * @Version 1.0
 **/
@JsonInclude(Include.NON_NULL)
public class EsdRequest implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer source; //来源系统
    private String dno; //店铺编码
    private String cp_code; //物流公司编码
    private String mail_no; //物流运单号
    private String tran_no; //国际快递单号
    private String logistics_id; //菜鸟物流单号
    private String seller_id; //淘宝店铺编码
    private Date order_create_time; //订单创建时间
    private String storehouse_id; //海外仓编码
    private String esdno; //卓志速运订单号
    private String trade_no; //交易订单号（平台）
    private String tid;//交易子订单号
    private String payment_enterprise; //支付企业编号
    private String payment_enterprise_name; //支付企业名称
    private String payment_transaction; //支付流水号
    private String payment_remark; //支付信息备注
    private String out_way_bill_url; //面单url
    private String declare_scheme_sid; //申报方案编码
    private String product_code; //产品代码
    private BigDecimal total_freight; //运费，RMB
    private String total_code; //运费币制
    private BigDecimal premium_fee; //保费，RMB
    private String premium_code; //保费币制
    private BigDecimal totai_taxes_reference; //税费，RMB
    private String totai_code; //税费币制
    private BigDecimal discount_fee; //优惠减免金额，RMB
    private String discount_code; //优惠减免金额币制
    private BigDecimal net_weight; //包裹总净重，单位：g
    private BigDecimal itemsum_weight; //包裹总毛重，单位：g
    private String bill; //提单号
    private String platform; //电商平台编码
    private Integer is_trace_source; //是否推溯源(1是，2否)
    private String zcode; //溯源码值
    private BigDecimal totai_taxes_pay; //实际支付金额，与支付保持一致
    private String totai_pay_code; //支付币制
    private String is_tran; //换单标志（1是；2否）是否需要换成国内落地配送单号
    private String destion_code; //目的地代码
    private String remark; //备注
    private String ieflag;//进出口标记

    private String package_type;//包裹类型

    private Integer parcel_count;//总箱数

    private String order_code;//第三方订单号

    private BigDecimal length;//包裹长度

    private BigDecimal width;//包裹宽度

    private BigDecimal height;//包裹高度

    private String taxId;//中邮税号

    private String total_package_no;//总包号

    private String package_kind;//包裹属性

    private String isreturn;//是否可退回
    private String package_no;
    private Date payment_time;

    private Date delayed_time;//延迟发货时间
    private EsdSender sender;
    private EsdReceiver receiver;
    private EsdBuyer buyer;
    private List<EsdServiceType> serciveList;
    private List<EsdGood> itemList;
    private List<EsdBox> boxList;

    private EsdRequest(Builder builder) {
        source = builder.source;
        dno = builder.dno;
        cp_code = builder.cp_code;
        mail_no = builder.mail_no;
        tran_no = builder.tran_no;
        logistics_id = builder.logistics_id;
        seller_id = builder.seller_id;
        order_create_time = builder.order_create_time;
        storehouse_id = builder.storehouse_id;
        esdno = builder.esdno;
        trade_no = builder.trade_no;
        tid = builder.tid;
        payment_enterprise = builder.payment_enterprise;
        payment_enterprise_name = builder.payment_enterprise_name;
        payment_transaction = builder.payment_transaction;
        payment_remark = builder.payment_remark;
        out_way_bill_url = builder.out_way_bill_url;
        declare_scheme_sid = builder.declare_scheme_sid;
        product_code = builder.product_code;
        total_freight = builder.total_freight;
        total_code = builder.total_code;
        premium_fee = builder.premium_fee;
        premium_code = builder.premium_code;
        totai_taxes_reference = builder.totai_taxes_reference;
        totai_code = builder.totai_code;
        discount_fee = builder.discount_fee;
        discount_code = builder.discount_code;
        net_weight = builder.net_weight;
        itemsum_weight = builder.itemsum_weight;
        bill = builder.bill;
        platform = builder.platform;
        is_trace_source = builder.is_trace_source;
        zcode = builder.zcode;
        totai_taxes_pay = builder.totai_taxes_pay;
        totai_pay_code = builder.totai_pay_code;
        is_tran = builder.is_tran;
        destion_code = builder.destion_code;
        remark = builder.remark;
        ieflag = builder.ieflag;
        package_type = builder.package_type;
        parcel_count = builder.parcel_count;
        order_code = builder.order_code;
        length = builder.length;
        width = builder.width;
        height = builder.height;
        taxId = builder.taxId;
        total_package_no = builder.total_package_no;
        package_kind = builder.package_kind;
        isreturn = builder.isreturn;
        delayed_time = builder.delayed_time;
        sender = builder.sender;
        receiver = builder.receiver;
        buyer = builder.buyer;
        serciveList = builder.serciveList;
        itemList = builder.itemList;
        boxList = builder.boxList;
        package_no = builder.package_no;
        payment_time = builder.payment_time;

    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Integer getSource() {
        return source;
    }

    public String getDno() {
        return dno;
    }

    public String getCp_code() {
        return cp_code;
    }

    public String getMail_no() {
        return mail_no;
    }

    public String getTran_no() {
        return tran_no;
    }

    public String getLogistics_id() {
        return logistics_id;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public Date getOrder_create_time() {
        return order_create_time;
    }

    public String getStorehouse_id() {
        return storehouse_id;
    }

    public String getEsdno() {
        return esdno;
    }

    public String getTrade_no() {
        return trade_no;
    }

    public String getTid() {
        return tid;
    }

    public String getPayment_enterprise() {
        return payment_enterprise;
    }

    public String getPayment_enterprise_name() {
        return payment_enterprise_name;
    }

    public String getPayment_transaction() {
        return payment_transaction;
    }

    public String getPayment_remark() {
        return payment_remark;
    }

    public String getOut_way_bill_url() {
        return out_way_bill_url;
    }

    public String getDeclare_scheme_sid() {
        return declare_scheme_sid;
    }

    public String getProduct_code() {
        return product_code;
    }

    public BigDecimal getTotal_freight() {
        return total_freight;
    }

    public String getTotal_code() {
        return total_code;
    }

    public BigDecimal getPremium_fee() {
        return premium_fee;
    }

    public String getPremium_code() {
        return premium_code;
    }

    public BigDecimal getTotai_taxes_reference() {
        return totai_taxes_reference;
    }

    public String getTotai_code() {
        return totai_code;
    }

    public BigDecimal getDiscount_fee() {
        return discount_fee;
    }

    public String getDiscount_code() {
        return discount_code;
    }

    public BigDecimal getNet_weight() {
        return net_weight;
    }

    public BigDecimal getItemsum_weight() {
        return itemsum_weight;
    }

    public String getBill() {
        return bill;
    }

    public String getPlatform() {
        return platform;
    }

    public Integer getIs_trace_source() {
        return is_trace_source;
    }

    public String getZcode() {
        return zcode;
    }

    public BigDecimal getTotai_taxes_pay() {
        return totai_taxes_pay;
    }

    public String getTotai_pay_code() {
        return totai_pay_code;
    }

    public String getIs_tran() {
        return is_tran;
    }

    public String getDestion_code() {
        return destion_code;
    }

    public String getRemark() {
        return remark;
    }

    public String getIeflag() {
        return ieflag;
    }

    public String getPackage_type() {
        return package_type;
    }

    public Integer getParcel_count() {
        return parcel_count;
    }

    public String getOrder_code() {
        return order_code;
    }

    public BigDecimal getLength() {
        return length;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public String getTaxId() {
        return taxId;
    }

    public String getTotal_package_no() {
        return total_package_no;
    }

    public String getPackage_kind() {
        return package_kind;
    }

    public String getIsreturn() {
        return isreturn;
    }

    public Date getDelayed_time() {
        return delayed_time;
    }

    public String getPackage_no() {
        return package_no;
    }

    public Date getPayment_time() {
        return payment_time;
    }

    public EsdSender getSender() {
        return sender;
    }

    public EsdReceiver getReceiver() {
        return receiver;
    }

    public EsdBuyer getBuyer() {
        return buyer;
    }

    public List<EsdServiceType> getSerciveList() {
        return serciveList;
    }

    public List<EsdGood> getItemList() {
        return itemList;
    }

    public List<EsdBox> getBoxList() {
        return boxList;
    }


    public static final class Builder {

        private Integer source;
        private String dno;
        private String cp_code;
        private String mail_no;
        private String tran_no;
        private String logistics_id;
        private String seller_id;
        private Date order_create_time;
        private String storehouse_id;
        private String esdno;
        private String trade_no;
        private String tid;
        private String payment_enterprise;
        private String payment_enterprise_name;
        private String payment_transaction;
        private String payment_remark;
        private String out_way_bill_url;
        private String declare_scheme_sid;
        private String product_code;
        private BigDecimal total_freight;
        private String total_code;
        private BigDecimal premium_fee;
        private String premium_code;
        private BigDecimal totai_taxes_reference;
        private String totai_code;
        private BigDecimal discount_fee;
        private String discount_code;
        private BigDecimal net_weight;
        private BigDecimal itemsum_weight;
        private String bill;
        private String platform;
        private Integer is_trace_source;
        private String zcode;
        private BigDecimal totai_taxes_pay;
        private String totai_pay_code;
        private String is_tran;
        private String destion_code;
        private String remark;
        private String ieflag;
        private String package_type;
        private Integer parcel_count;
        private String order_code;
        private BigDecimal length;
        private BigDecimal width;
        private BigDecimal height;
        private String taxId;
        private String total_package_no;
        private String package_kind;
        private String isreturn;
        private Date delayed_time;
        private EsdSender sender;
        private EsdReceiver receiver;
        private EsdBuyer buyer;
        private String package_no;
        private Date payment_time;
        private List<EsdServiceType> serciveList;
        private List<EsdGood> itemList;
        private List<EsdBox> boxList;

        private Builder() {
        }

        public Builder source(Integer source) {
            this.source = source;
            return this;
        }

        public Builder dno(String dno) {
            this.dno = dno;
            return this;
        }

        public Builder cp_code(String cp_code) {
            this.cp_code = cp_code;
            return this;
        }

        public Builder mail_no(String mail_no) {
            this.mail_no = mail_no;
            return this;
        }

        public Builder tran_no(String tran_no) {
            this.tran_no = tran_no;
            return this;
        }

        public Builder logistics_id(String logistics_id) {
            this.logistics_id = logistics_id;
            return this;
        }

        public Builder seller_id(String seller_id) {
            this.seller_id = seller_id;
            return this;
        }

        public Builder order_create_time(Date order_create_time) {
            this.order_create_time = order_create_time;
            return this;
        }

        public Builder storehouse_id(String storehouse_id) {
            this.storehouse_id = storehouse_id;
            return this;
        }

        public Builder esdno(String esdno) {
            this.esdno = esdno;
            return this;
        }

        public Builder trade_no(String trade_no) {
            this.trade_no = trade_no;
            return this;
        }

        public Builder tid(String tid) {
            this.tid = tid;
            return this;
        }

        public Builder payment_enterprise(String payment_enterprise) {
            this.payment_enterprise = payment_enterprise;
            return this;
        }

        public Builder payment_enterprise_name(String payment_enterprise_name) {
            this.payment_enterprise_name = payment_enterprise_name;
            return this;
        }

        public Builder payment_transaction(String payment_transaction) {
            this.payment_transaction = payment_transaction;
            return this;
        }

        public Builder payment_remark(String payment_remark) {
            this.payment_remark = payment_remark;
            return this;
        }

        public Builder out_way_bill_url(String out_way_bill_url) {
            this.out_way_bill_url = out_way_bill_url;
            return this;
        }

        public Builder declare_scheme_sid(String declare_scheme_sid) {
            this.declare_scheme_sid = declare_scheme_sid;
            return this;
        }

        public Builder product_code(String product_code) {
            this.product_code = product_code;
            return this;
        }

        public Builder total_freight(BigDecimal total_freight) {
            this.total_freight = total_freight;
            return this;
        }

        public Builder total_code(String total_code) {
            this.total_code = total_code;
            return this;
        }

        public Builder premium_fee(BigDecimal premium_fee) {
            this.premium_fee = premium_fee;
            return this;
        }

        public Builder premium_code(String premium_code) {
            this.premium_code = premium_code;
            return this;
        }

        public Builder totai_taxes_reference(BigDecimal totai_taxes_reference) {
            this.totai_taxes_reference = totai_taxes_reference;
            return this;
        }

        public Builder totai_code(String totai_code) {
            this.totai_code = totai_code;
            return this;
        }

        public Builder discount_fee(BigDecimal discount_fee) {
            this.discount_fee = discount_fee;
            return this;
        }

        public Builder discount_code(String discount_code) {
            this.discount_code = discount_code;
            return this;
        }

        public Builder net_weight(BigDecimal net_weight) {
            if (net_weight != null) {
                this.net_weight = net_weight.multiply(new BigDecimal(1000));
            }
            return this;
        }

        public Builder itemsum_weight(BigDecimal itemsum_weight) {
            if (itemsum_weight != null) {
                this.itemsum_weight = itemsum_weight.multiply(new BigDecimal(1000));
            }
            return this;
        }

        public Builder bill(String bill) {
            this.bill = bill;
            return this;
        }

        public Builder platform(String platform) {
            this.platform = platform;
            return this;
        }

        public Builder is_trace_source(Integer is_trace_source) {
            this.is_trace_source = is_trace_source;
            return this;
        }

        public Builder zcode(String zcode) {
            this.zcode = zcode;
            return this;
        }

        public Builder totai_taxes_pay(BigDecimal totai_taxes_pay) {
            this.totai_taxes_pay = totai_taxes_pay;
            return this;
        }

        public Builder totai_pay_code(String totai_pay_code) {
            this.totai_pay_code = totai_pay_code;
            return this;
        }

        public Builder is_tran(String is_tran) {
            this.is_tran = is_tran;
            return this;
        }

        public Builder destion_code(String destion_code) {
            this.destion_code = destion_code;
            return this;
        }

        public Builder remark(String remark) {
            this.remark = remark;
            return this;
        }

        public Builder ieflag(String ieflag) {
            this.ieflag = ieflag;
            return this;
        }

        public Builder package_type(String package_type) {
            this.package_type = package_type;
            return this;
        }

        public Builder parcel_count(Integer parcel_count) {
            this.parcel_count = parcel_count;
            return this;
        }

        public Builder order_code(String order_code) {
            this.order_code = order_code;
            return this;
        }

        public Builder length(BigDecimal length) {
            this.length = length;
            return this;
        }

        public Builder width(BigDecimal width) {
            this.width = width;
            return this;
        }

        public Builder height(BigDecimal height) {
            this.height = height;
            return this;
        }

        public Builder taxId(String taxId) {
            this.taxId = taxId;
            return this;
        }

        public Builder total_package_no(String total_package_no) {
            this.total_package_no = total_package_no;
            return this;
        }

        public Builder package_kind(String package_kind) {
            this.package_kind = package_kind;
            return this;
        }

        public Builder isreturn(String isreturn) {
            this.isreturn = isreturn;
            return this;
        }

        public Builder delayed_time(Date delayed_time) {
            this.delayed_time = delayed_time;
            return this;
        }

        public Builder package_no(String package_no) {
            this.package_no = package_no;
            return this;
        }

        public Builder payment_time(Date payment_time) {
            this.payment_time = payment_time;
            return this;
        }

        public Builder sender(EsdSender sender) {
            this.sender = sender;
            return this;
        }

        public Builder receiver(EsdReceiver receiver) {
            this.receiver = receiver;
            return this;
        }

        public Builder buyer(EsdBuyer buyer) {
            this.buyer = buyer;
            return this;
        }

        public Builder serciveList(List<EsdServiceType> serciveList) {
            this.serciveList = serciveList;
            return this;
        }

        public Builder itemList(List<EsdGood> itemList) {
            this.itemList = itemList;
            return this;
        }

        public Builder boxList(List<EsdBox> boxList) {
            this.boxList = boxList;
            return this;
        }

        public EsdRequest build() {
            return new EsdRequest(this);
        }
    }
}

package com.topideal.supplychain.ocp.esd.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 重量为G 金额为元
 */
@JsonInclude(Include.NON_NULL)
public class EsdGood implements Serializable {

    private String index; //序号
    private String item; //商品货号
    private Integer is_presente; //是否商品备案(1是，2否)
    private String item_name; //商品名称
    private String item_category; //商品行邮税号
    private String hscode; //HS海关编码
    private String cust_no; //商品海关备案号
    private String ciq_no; //国检商品备案号
    private Integer item_quantity; //商品数量
    private BigDecimal item_net_weight; //商品净重，g
    private BigDecimal item_weight; //商品毛重，g
    private BigDecimal price_declare; //商品单价，RMB
    private String price_code; //单价币制
    private String unit; //计量单位
    private String unit1; //第一法定计量单位
    private BigDecimal qty1; //第一法定计量单位数量
    private String unit2; //第二法定计量单位
    private BigDecimal qty2; //第二法定计量单位数量
    private String brand; //品牌
    private String item_tax; //商品税金
    private String assem_country; //原产国
    private String assem_area; //国检原产地
    private String spec; //规格型号
    private String bar_code; //条形码
    private String nots; //商品备注
    private String item_enname;//申报英文名称

    private String prod_use;

    private String prod_material;

    private EsdGood(Builder builder) {
        setIndex(builder.index);
        setItem(builder.item);
        setIs_presente(builder.is_presente);
        setItem_name(builder.item_name);
        setItem_category(builder.item_category);
        setHscode(builder.hscode);
        setCust_no(builder.cust_no);
        setCiq_no(builder.ciq_no);
        setItem_quantity(builder.item_quantity);
        setItem_net_weight(builder.item_net_weight);
        setItem_weight(builder.item_weight);
        setPrice_declare(builder.price_declare);
        setPrice_code(builder.price_code);
        setUnit(builder.unit);
        setUnit1(builder.unit1);
        setQty1(builder.qty1);
        setUnit2(builder.unit2);
        setQty2(builder.qty2);
        setBrand(builder.brand);
        setItem_tax(builder.item_tax);
        setAssem_country(builder.assem_country);
        setAssem_area(builder.assem_area);
        setSpec(builder.spec);
        setBar_code(builder.bar_code);
        setNots(builder.nots);
        setItem_enname(builder.item_enname);
        setProd_use(builder.prod_use);
        setProd_material(builder.prod_material);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

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

    public Integer getIs_presente() {
        return is_presente;
    }

    public void setIs_presente(Integer is_presente) {
        this.is_presente = is_presente;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_category() {
        return item_category;
    }

    public void setItem_category(String item_category) {
        this.item_category = item_category;
    }

    public String getHscode() {
        return hscode;
    }

    public void setHscode(String hscode) {
        this.hscode = hscode;
    }

    public String getCust_no() {
        return cust_no;
    }

    public void setCust_no(String cust_no) {
        this.cust_no = cust_no;
    }

    public String getCiq_no() {
        return ciq_no;
    }

    public void setCiq_no(String ciq_no) {
        this.ciq_no = ciq_no;
    }

    public Integer getItem_quantity() {
        return item_quantity;
    }

    public void setItem_quantity(Integer item_quantity) {
        this.item_quantity = item_quantity;
    }

    public BigDecimal getItem_net_weight() {
        return item_net_weight;
    }

    public void setItem_net_weight(BigDecimal item_net_weight) {
        this.item_net_weight = item_net_weight;
    }

    public BigDecimal getItem_weight() {
        return item_weight;
    }

    public void setItem_weight(BigDecimal item_weight) {
        this.item_weight = item_weight;
    }

    public BigDecimal getPrice_declare() {
        return price_declare;
    }

    public void setPrice_declare(BigDecimal price_declare) {
        this.price_declare = price_declare;
    }

    public String getPrice_code() {
        return price_code;
    }

    public void setPrice_code(String price_code) {
        this.price_code = price_code;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit1() {
        return unit1;
    }

    public void setUnit1(String unit1) {
        this.unit1 = unit1;
    }

    public BigDecimal getQty1() {
        return qty1;
    }

    public void setQty1(BigDecimal qty1) {
        this.qty1 = qty1;
    }

    public String getUnit2() {
        return unit2;
    }

    public void setUnit2(String unit2) {
        this.unit2 = unit2;
    }

    public BigDecimal getQty2() {
        return qty2;
    }

    public void setQty2(BigDecimal qty2) {
        this.qty2 = qty2;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getItem_tax() {
        return item_tax;
    }

    public void setItem_tax(String item_tax) {
        this.item_tax = item_tax;
    }

    public String getAssem_country() {
        return assem_country;
    }

    public void setAssem_country(String assem_country) {
        this.assem_country = assem_country;
    }

    public String getAssem_area() {
        return assem_area;
    }

    public void setAssem_area(String assem_area) {
        this.assem_area = assem_area;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getBar_code() {
        return bar_code;
    }

    public void setBar_code(String bar_code) {
        this.bar_code = bar_code;
    }

    public String getNots() {
        return nots;
    }

    public void setNots(String nots) {
        this.nots = nots;
    }

    public String getItem_enname() {
        return item_enname;
    }

    public void setItem_enname(String item_enname) {
        this.item_enname = item_enname;
    }

    public String getProd_use() {
        return prod_use;
    }

    public void setProd_use(String prod_use) {
        this.prod_use = prod_use;
    }

    public String getProd_material() {
        return prod_material;
    }

    public void setProd_material(String prod_material) {
        this.prod_material = prod_material;
    }


    public static final class Builder {

        private String index;
        private String item;
        private Integer is_presente;
        private String item_name;
        private String item_category;
        private String hscode;
        private String cust_no;
        private String ciq_no;
        private Integer item_quantity;
        private BigDecimal item_net_weight;
        private BigDecimal item_weight;
        private BigDecimal price_declare;
        private String price_code;
        private String unit;
        private String unit1;
        private BigDecimal qty1;
        private String unit2;
        private BigDecimal qty2;
        private String brand;
        private String item_tax;
        private String assem_country;
        private String assem_area;
        private String spec;
        private String bar_code;
        private String nots;
        private String item_enname;
        private String prod_use;
        private String prod_material;

        private Builder() {
        }

        public Builder index(String index) {
            this.index = index;
            return this;
        }

        public Builder item(String item) {
            this.item = item;
            return this;
        }

        public Builder is_presente(Integer is_presente) {
            this.is_presente = is_presente;
            return this;
        }

        public Builder item_name(String item_name) {
            this.item_name = item_name;
            return this;
        }

        public Builder item_category(String item_category) {
            this.item_category = item_category;
            return this;
        }

        public Builder hscode(String hscode) {
            this.hscode = hscode;
            return this;
        }

        public Builder cust_no(String cust_no) {
            this.cust_no = cust_no;
            return this;
        }

        public Builder ciq_no(String ciq_no) {
            this.ciq_no = ciq_no;
            return this;
        }

        public Builder item_quantity(Integer item_quantity) {
            this.item_quantity = item_quantity;
            return this;
        }

        public Builder item_net_weight(BigDecimal item_net_weight) {
            if (item_net_weight != null) {
                this.item_net_weight = item_net_weight.multiply(new BigDecimal(1000));
            }
            return this;
        }

        public Builder item_weight(BigDecimal item_weight) {
            if (item_weight != null) {
                this.item_weight = item_weight.multiply(new BigDecimal(1000));
            }
            return this;
        }

        public Builder price_declare(BigDecimal price_declare) {
            this.price_declare = price_declare;
            return this;
        }

        public Builder price_code(String price_code) {
            this.price_code = price_code;
            return this;
        }

        public Builder unit(String unit) {
            this.unit = unit;
            return this;
        }

        public Builder unit1(String unit1) {
            this.unit1 = unit1;
            return this;
        }

        public Builder qty1(BigDecimal qty1) {
            this.qty1 = qty1;
            return this;
        }

        public Builder unit2(String unit2) {
            this.unit2 = unit2;
            return this;
        }

        public Builder qty2(BigDecimal qty2) {
            this.qty2 = qty2;
            return this;
        }

        public Builder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder item_tax(String item_tax) {
            this.item_tax = item_tax;
            return this;
        }

        public Builder assem_country(String assem_country) {
            this.assem_country = assem_country;
            return this;
        }

        public Builder assem_area(String assem_area) {
            this.assem_area = assem_area;
            return this;
        }

        public Builder spec(String spec) {
            this.spec = spec;
            return this;
        }

        public Builder bar_code(String bar_code) {
            this.bar_code = bar_code;
            return this;
        }

        public Builder nots(String nots) {
            this.nots = nots;
            return this;
        }

        public Builder item_enname(String item_enname) {
            this.item_enname = item_enname;
            return this;
        }

        public Builder prod_use(String prod_use) {
            this.prod_use = prod_use;
            return this;
        }

        public Builder prod_material(String prod_material) {
            this.prod_material = prod_material;
            return this;
        }

        public EsdGood build() {
            return new EsdGood(this);
        }
    }
}
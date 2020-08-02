package com.topideal.supplychain.ocp.mdm.dto;

import java.math.BigDecimal;

/**
 * @description: 主数据商品信息请求dto
 * @author: syq
 * @create: 2019-12-09 15:11
 **/
public class MdmGoodsReqDto {

    private String notUpdatePrd; //0:更新,1:不更新，不传默认0更新

    private String prdcode; //产品ID

    private String prdName; //产品名称

    private String barcode; //条形码

    private String gType; //Hscode编码

    private String spec; //规格型号

    private BigDecimal length; //长

    private BigDecimal width; //宽

    private BigDecimal height; //高

    private BigDecimal volume; //体积

    private BigDecimal kgs; //毛重

    private BigDecimal net; //净重

    private BigDecimal cartonLength; //外箱长

    private BigDecimal cartonWidth; //外箱宽

    private BigDecimal cartonHeight; //外箱高

    private BigDecimal cartonVolume; //外箱体积

    private BigDecimal cartonWeight; //外箱重量

    private Long lifeDays; //保质期天数

    private Integer useLifeFlag; //是否启用保质期管理

    private String qtyUnit; //产品计量单位

    private String brand; //品牌名称

    private String produceComp; //生产厂家

    private String manufacturerAddr; //生产厂家地址

    private String originRegion; //原产地

    private String originCountry; //原产国

    private String color; //颜色

    private String prdSize; //尺码

    private String ingredient; //成分

    private Integer fragileFlag; //是否易碎品

    private Integer dangerousFlag; //是否危险品

    private Integer valuablesFlag; //是否鬼品

    private Integer hasBatteryFlag; //是否包含电池

    private String keepTemperature; //温度要求

    private String keepHumidity; //湿度要求

    private String prdRemark; //备注

    private String opgcode; //商品业务ID

    private String gcode; //商品编码

    private String ccode; //客户ID

    private String gName; //商品名称

    private String goodsbarcode; //商品条形码

    public String getNotUpdatePrd() {
        return notUpdatePrd;
    }

    public void setNotUpdatePrd(String notUpdatePrd) {
        this.notUpdatePrd = notUpdatePrd;
    }

    public String getPrdcode() {
        return prdcode;
    }

    public void setPrdcode(String prdcode) {
        this.prdcode = prdcode;
    }

    public String getPrdName() {
        return prdName;
    }

    public void setPrdName(String prdName) {
        this.prdName = prdName;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getgType() {
        return gType;
    }

    public void setgType(String gType) {
        this.gType = gType;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
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

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public BigDecimal getKgs() {
        return kgs;
    }

    public void setKgs(BigDecimal kgs) {
        this.kgs = kgs;
    }

    public BigDecimal getNet() {
        return net;
    }

    public void setNet(BigDecimal net) {
        this.net = net;
    }

    public BigDecimal getCartonLength() {
        return cartonLength;
    }

    public void setCartonLength(BigDecimal cartonLength) {
        this.cartonLength = cartonLength;
    }

    public BigDecimal getCartonWidth() {
        return cartonWidth;
    }

    public void setCartonWidth(BigDecimal cartonWidth) {
        this.cartonWidth = cartonWidth;
    }

    public BigDecimal getCartonHeight() {
        return cartonHeight;
    }

    public void setCartonHeight(BigDecimal cartonHeight) {
        this.cartonHeight = cartonHeight;
    }

    public BigDecimal getCartonVolume() {
        return cartonVolume;
    }

    public void setCartonVolume(BigDecimal cartonVolume) {
        this.cartonVolume = cartonVolume;
    }

    public BigDecimal getCartonWeight() {
        return cartonWeight;
    }

    public void setCartonWeight(BigDecimal cartonWeight) {
        this.cartonWeight = cartonWeight;
    }

    public Long getLifeDays() {
        return lifeDays;
    }

    public void setLifeDays(Long lifeDays) {
        this.lifeDays = lifeDays;
    }

    public Integer getUseLifeFlag() {
        return useLifeFlag;
    }

    public void setUseLifeFlag(Integer useLifeFlag) {
        this.useLifeFlag = useLifeFlag;
    }

    public String getQtyUnit() {
        return qtyUnit;
    }

    public void setQtyUnit(String qtyUnit) {
        this.qtyUnit = qtyUnit;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProduceComp() {
        return produceComp;
    }

    public void setProduceComp(String produceComp) {
        this.produceComp = produceComp;
    }

    public String getManufacturerAddr() {
        return manufacturerAddr;
    }

    public void setManufacturerAddr(String manufacturerAddr) {
        this.manufacturerAddr = manufacturerAddr;
    }

    public String getOriginRegion() {
        return originRegion;
    }

    public void setOriginRegion(String originRegion) {
        this.originRegion = originRegion;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPrdSize() {
        return prdSize;
    }

    public void setPrdSize(String prdSize) {
        this.prdSize = prdSize;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public Integer getFragileFlag() {
        return fragileFlag;
    }

    public void setFragileFlag(Integer fragileFlag) {
        this.fragileFlag = fragileFlag;
    }

    public Integer getDangerousFlag() {
        return dangerousFlag;
    }

    public void setDangerousFlag(Integer dangerousFlag) {
        this.dangerousFlag = dangerousFlag;
    }

    public Integer getValuablesFlag() {
        return valuablesFlag;
    }

    public void setValuablesFlag(Integer valuablesFlag) {
        this.valuablesFlag = valuablesFlag;
    }

    public Integer getHasBatteryFlag() {
        return hasBatteryFlag;
    }

    public void setHasBatteryFlag(Integer hasBatteryFlag) {
        this.hasBatteryFlag = hasBatteryFlag;
    }

    public String getKeepTemperature() {
        return keepTemperature;
    }

    public void setKeepTemperature(String keepTemperature) {
        this.keepTemperature = keepTemperature;
    }

    public String getKeepHumidity() {
        return keepHumidity;
    }

    public void setKeepHumidity(String keepHumidity) {
        this.keepHumidity = keepHumidity;
    }

    public String getPrdRemark() {
        return prdRemark;
    }

    public void setPrdRemark(String prdRemark) {
        this.prdRemark = prdRemark;
    }

    public String getOpgcode() {
        return opgcode;
    }

    public void setOpgcode(String opgcode) {
        this.opgcode = opgcode;
    }

    public String getGcode() {
        return gcode;
    }

    public void setGcode(String gcode) {
        this.gcode = gcode;
    }

    public String getCcode() {
        return ccode;
    }

    public void setCcode(String ccode) {
        this.ccode = ccode;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public String getGoodsbarcode() {
        return goodsbarcode;
    }

    public void setGoodsbarcode(String goodsbarcode) {
        this.goodsbarcode = goodsbarcode;
    }
}

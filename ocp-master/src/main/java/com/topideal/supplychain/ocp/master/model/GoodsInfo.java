package com.topideal.supplychain.ocp.master.model;

import com.topideal.supplychain.common.model.BaseEntity;
import com.topideal.supplychain.enumeration.YesOrNoEnum;
import com.topideal.supplychain.ocp.enums.SyncStatusEnum;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 商品信息登记
 * @author: syq
 * @create: 2019-12-04 14:41
 **/
public class GoodsInfo extends BaseEntity {

    private String code;//ocp系统内部单号

    private String opgCode;//opg号

    private String actionType;//操作类型add/update

    private String warehouseCode;//仓库编码

    private String ownerCode;//货主编码

    private String supplierCode;//供应商编码

    private String supplierName;//供应商名称

    private String goodsNo;//商品编码

    private String wmsGoodsNo;//仓库系统商品编码

    private String goodsCode;//商品货号

    private String goodsName;//商品名称

    private String shortName;//商品简称

    private String englishName;//英文名

    private String barCode;//条形码

    private String skuProperty;//商品属性

    private String unit;//计量单位

    private BigDecimal length;//长

    private BigDecimal width;//宽

    private BigDecimal height;//高

    private BigDecimal volume;//体积

    private BigDecimal grossWeight;//毛重

    private BigDecimal netWeight;//净重

    private String color;//颜色

    private String size;//尺寸

    private String title;//渠道中的商品标题

    private String categoryId;//商品类别id

    private String categoryName;//商品类别名称

    private String pricingCategory;//计价货类

    private Integer safetyStock;//安全库存

    private String goodsType;//商品类型

    private BigDecimal tagPrice;//吊牌价

    private BigDecimal retailPrice;//零售价

    private BigDecimal costPrice;//成本价

    private BigDecimal purchasePrice;//采购价

    private String seasonCode;//季节编码

    private String seasonName;//季节名称

    private String brandCode;//品牌代码

    private String brandName;//品牌名称

    private YesOrNoEnum isSerialNumber;//是否需要串号管理

    private Date productDate;//生产日期

    private Date expireDate;//过期日期

    private YesOrNoEnum isShelfLife;//是否需要保质期管理

    private Integer shelfLife;//保质期

    private Integer rejectLifecycle;//保质期禁收天数

    private Integer lockupLifecycle;//保质期禁售天数

    private Integer adventLifecycle;//保质期临近预警天数

    private YesOrNoEnum isBatch;//是否需要批次管理

    private String batchCode;//批次代码

    private String batchRemark;//批次备注

    private String packCode;//包装代码

    private String pcs;//箱规

    private String originAddress;//商品原产地

    private String approvalNo;//批准文号

    private YesOrNoEnum isFragile;//是否易碎品

    private YesOrNoEnum isDangerous;//是否危险品

    private String remark;//配置

    private YesOrNoEnum isValid;//是否有效

    private YesOrNoEnum isSku;//是否sku

    private String packageMaterial;//商品包装材料类型

    private String arguments;//扩展属性

    private SyncStatusEnum status;//状态

    private String createBeginTime;
    private String createEndTime;

    private Date requestTimestamp;//请求参数timestamp

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOpgCode() {
        return opgCode;
    }

    public void setOpgCode(String opgCode) {
        this.opgCode = opgCode;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public String getWmsGoodsNo() {
        return wmsGoodsNo;
    }

    public void setWmsGoodsNo(String wmsGoodsNo) {
        this.wmsGoodsNo = wmsGoodsNo;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getSkuProperty() {
        return skuProperty;
    }

    public void setSkuProperty(String skuProperty) {
        this.skuProperty = skuProperty;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getPricingCategory() {
        return pricingCategory;
    }

    public void setPricingCategory(String pricingCategory) {
        this.pricingCategory = pricingCategory;
    }

    public Integer getSafetyStock() {
        return safetyStock;
    }

    public void setSafetyStock(Integer safetyStock) {
        this.safetyStock = safetyStock;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public BigDecimal getTagPrice() {
        return tagPrice;
    }

    public void setTagPrice(BigDecimal tagPrice) {
        this.tagPrice = tagPrice;
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getSeasonCode() {
        return seasonCode;
    }

    public void setSeasonCode(String seasonCode) {
        this.seasonCode = seasonCode;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Integer getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(Integer shelfLife) {
        this.shelfLife = shelfLife;
    }

    public Integer getRejectLifecycle() {
        return rejectLifecycle;
    }

    public void setRejectLifecycle(Integer rejectLifecycle) {
        this.rejectLifecycle = rejectLifecycle;
    }

    public Integer getLockupLifecycle() {
        return lockupLifecycle;
    }

    public void setLockupLifecycle(Integer lockupLifecycle) {
        this.lockupLifecycle = lockupLifecycle;
    }

    public Integer getAdventLifecycle() {
        return adventLifecycle;
    }

    public void setAdventLifecycle(Integer adventLifecycle) {
        this.adventLifecycle = adventLifecycle;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public String getBatchRemark() {
        return batchRemark;
    }

    public void setBatchRemark(String batchRemark) {
        this.batchRemark = batchRemark;
    }

    public String getPackCode() {
        return packCode;
    }

    public void setPackCode(String packCode) {
        this.packCode = packCode;
    }

    public String getPcs() {
        return pcs;
    }

    public void setPcs(String pcs) {
        this.pcs = pcs;
    }

    public String getOriginAddress() {
        return originAddress;
    }

    public void setOriginAddress(String originAddress) {
        this.originAddress = originAddress;
    }

    public String getApprovalNo() {
        return approvalNo;
    }

    public void setApprovalNo(String approvalNo) {
        this.approvalNo = approvalNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPackageMaterial() {
        return packageMaterial;
    }

    public void setPackageMaterial(String packageMaterial) {
        this.packageMaterial = packageMaterial;
    }

    public String getArguments() {
        return arguments;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments;
    }

    public SyncStatusEnum getStatus() {
        return status;
    }

    public void setStatus(SyncStatusEnum status) {
        this.status = status;
    }

    public Date getRequestTimestamp() {
        return requestTimestamp;
    }

    public void setRequestTimestamp(Date requestTimestamp) {
        this.requestTimestamp = requestTimestamp;
    }

    public String getCreateBeginTime() {
        return createBeginTime;
    }

    public void setCreateBeginTime(String createBeginTime) {
        this.createBeginTime = createBeginTime;
    }

    public String getCreateEndTime() {
        return createEndTime;
    }

    public void setCreateEndTime(String createEndTime) {
        this.createEndTime = createEndTime;
    }

    public YesOrNoEnum getIsSerialNumber() {
        return isSerialNumber;
    }

    public void setIsSerialNumber(YesOrNoEnum isSerialNumber) {
        this.isSerialNumber = isSerialNumber;
    }

    public YesOrNoEnum getIsBatch() {
        return isBatch;
    }

    public void setIsBatch(YesOrNoEnum isBatch) {
        this.isBatch = isBatch;
    }

    public YesOrNoEnum getIsFragile() {
        return isFragile;
    }

    public void setIsFragile(YesOrNoEnum isFragile) {
        this.isFragile = isFragile;
    }

    public YesOrNoEnum getIsDangerous() {
        return isDangerous;
    }

    public void setIsDangerous(YesOrNoEnum isDangerous) {
        this.isDangerous = isDangerous;
    }

    public YesOrNoEnum getIsValid() {
        return isValid;
    }

    public void setIsValid(YesOrNoEnum isValid) {
        this.isValid = isValid;
    }

    public YesOrNoEnum getIsSku() {
        return isSku;
    }

    public void setIsSku(YesOrNoEnum isSku) {
        this.isSku = isSku;
    }

    public YesOrNoEnum getIsShelfLife() {
        return isShelfLife;
    }

    public void setIsShelfLife(YesOrNoEnum isShelfLife) {
        this.isShelfLife = isShelfLife;
    }
}

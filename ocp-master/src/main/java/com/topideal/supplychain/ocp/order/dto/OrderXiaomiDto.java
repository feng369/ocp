package com.topideal.supplychain.ocp.order.dto;

import com.topideal.supplychain.ocp.enums.ForwardSystemEnum;
import com.topideal.supplychain.ocp.enums.OrderStatusEnum;
import com.topideal.supplychain.ocp.order.model.OrderXiaomi;
import com.topideal.supplychain.ocp.order.model.OrderXiaomiGoods;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author wanzhaozhang
 * @date 2019/12/16
 * @description
 **/
public class OrderXiaomiDto extends OrderXiaomi {
   private List<OrderXiaomiGoods> products;

   private String ebcName;

   //开始时间
   @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
   private Date startDate;
   //结束时间
   @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
   private Date endDate;

   public OrderXiaomiDto() {
   }

   private OrderXiaomiDto(Builder builder) {
      setCode(builder.code);
      setOrderId(builder.orderId);
      setStoreCode(builder.storeCode);
      setEbcCode(builder.ebcCode);
      setProvinceName(builder.provinceName);
      setCityName(builder.cityName);
      setDistrictName(builder.districtName);
      setAreaName(builder.areaName);
      setAddress(builder.address);
      setTel(builder.tel);
      setConsignee(builder.consignee);
      setZipcode(builder.zipcode);
      setCardId(builder.cardId);
      setCardName(builder.cardName);
      setImportationType(builder.importationType);
      setCouponAmount(builder.couponAmount);
      setShipFee(builder.shipFee);
      setTotalPrice(builder.totalPrice);
      setCtime(builder.ctime);
      setFtime(builder.ftime);
      setSendSystem(builder.sendSystem);
      setSendStatus(builder.sendStatus);
      setFailReason(builder.failReason);
      setProducts(builder.products);
   }

   public static Builder newBuilder() {
      return new Builder();
   }


   public List<OrderXiaomiGoods> getProducts() {
      return products;
   }

   public void setProducts(List<OrderXiaomiGoods> products) {
      this.products = products;
   }

   public String getEbcName() {
      return ebcName;
   }

   public void setEbcName(String ebcName) {
      this.ebcName = ebcName;
   }

   public Date getStartDate() {
      return startDate;
   }

   public void setStartDate(Date startDate) {
      this.startDate = startDate;
   }

   public Date getEndDate() {
      return endDate;
   }

   public void setEndDate(Date endDate) {
      this.endDate = endDate;
   }

   public static final class Builder {
      private String code;
      private String orderId;
      private String storeCode;
      private String ebcCode;
      private String provinceName;
      private String cityName;
      private String districtName;
      private String areaName;
      private String address;
      private String tel;
      private String consignee;
      private String zipcode;
      private String cardId;
      private String cardName;
      private String importationType;
      private BigDecimal couponAmount;
      private BigDecimal shipFee;
      private BigDecimal totalPrice;
      private Date ctime;
      private Date ftime;
      private ForwardSystemEnum sendSystem;
      private OrderStatusEnum sendStatus;
      private String failReason;
      private List<OrderXiaomiGoods> products;

      private Builder() {
      }

      public Builder code(String code) {
         this.code = code;
         return this;
      }

      public Builder orderId(String orderId) {
         this.orderId = orderId;
         return this;
      }

      public Builder storeCode(String storeCode) {
         this.storeCode = storeCode;
         return this;
      }

      public Builder ebcCode(String ebcCode) {
         this.ebcCode = ebcCode;
         return this;
      }

      public Builder provinceName(String provinceName) {
         this.provinceName = provinceName;
         return this;
      }

      public Builder cityName(String cityName) {
         this.cityName = cityName;
         return this;
      }

      public Builder districtName(String districtName) {
         this.districtName = districtName;
         return this;
      }

      public Builder areaName(String areaName) {
         this.areaName = areaName;
         return this;
      }

      public Builder address(String address) {
         this.address = address;
         return this;
      }

      public Builder tel(String tel) {
         this.tel = tel;
         return this;
      }

      public Builder consignee(String consignee) {
         this.consignee = consignee;
         return this;
      }

      public Builder zipcode(String zipcode) {
         this.zipcode = zipcode;
         return this;
      }

      public Builder cardId(String cardId) {
         this.cardId = cardId;
         return this;
      }

      public Builder cardName(String cardName) {
         this.cardName = cardName;
         return this;
      }

      public Builder importationType(String importationType) {
         this.importationType = importationType;
         return this;
      }

      public Builder couponAmount(BigDecimal couponAmount) {
         this.couponAmount = couponAmount;
         return this;
      }

      public Builder shipFee(BigDecimal shipFee) {
         this.shipFee = shipFee;
         return this;
      }

      public Builder totalPrice(BigDecimal totalPrice) {
         this.totalPrice = totalPrice;
         return this;
      }

      public Builder ctime(Date ctime) {
         this.ctime = ctime;
         return this;
      }

      public Builder ftime(Date ftime) {
         this.ftime = ftime;
         return this;
      }

      public Builder sendSystem(ForwardSystemEnum sendSystem) {
         this.sendSystem = sendSystem;
         return this;
      }

      public Builder sendStatus(OrderStatusEnum sendStatus) {
         this.sendStatus = sendStatus;
         return this;
      }

      public Builder failReason(String failReason) {
         this.failReason = failReason;
         return this;
      }

      public Builder products(List<OrderXiaomiGoods> products) {
         this.products = products;
         return this;
      }

      public OrderXiaomiDto build() {
         return new OrderXiaomiDto(this);
      }
   }


}

package com.topideal.supplychain.ocp.order.dto;

import com.topideal.supplychain.ocp.order.model.OrderYmatou;
import com.topideal.supplychain.ocp.order.model.OrderYmatouDelivery;
import com.topideal.supplychain.ocp.order.model.OrderYmatouIndentity;
import com.topideal.supplychain.ocp.order.model.OrderYmatouItem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author wanzhaozhang
 * @date 2019/11/28
 * @description 洋码头订单 实体bean
 **/
public class OrderYmatouDto extends OrderYmatou {

    private List<OrderYmatouItem> orderItemsInfo;
    private List<OrderYmatouIndentity> idCards;
    private List<OrderYmatouDelivery> deliveryInfo;

    /**
     * 申报国检
     */
    private String ciqbCode;
    private String orderType;
    private String orderSource;
    private String storeCode;

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public List<OrderYmatouItem> getOrderItemsInfo() {
        return orderItemsInfo;
    }

    public void setOrderItemsInfo(List<OrderYmatouItem> orderItemsInfo) {
        this.orderItemsInfo = orderItemsInfo;
    }

    public List<OrderYmatouIndentity> getIdCards() {
        return idCards;
    }

    public void setIdCards(List<OrderYmatouIndentity> idCards) {
        this.idCards = idCards;
    }

    public List<OrderYmatouDelivery> getDeliveryInfo() {
        return deliveryInfo;
    }

    public void setDeliveryInfo(List<OrderYmatouDelivery> deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
    }

    public String getCiqbCode() {
        return ciqbCode;
    }

    public void setCiqbCode(String ciqbCode) {
        this.ciqbCode = ciqbCode;
    }

    /**
     * toOp  /  toOfc
     */

    public String getBuyerRegNo() {
        return StringUtils.isEmpty(getReceiverMobile()) ? getReceiverPhone() : getReceiverMobile();
    }


    public String getBuyerIdNumber() {
        if (ObjectUtils.isEmpty(getIdCards())) {
            return "";
        }
        return getIdCards().get(0).getReceiverIdNo();
    }


    public String getReceiveNo() {
        if (ObjectUtils.isEmpty(getIdCards())) {
            return "";
        }
        return getIdCards().get(0).getReceiverIdNo();
    }


    public String getProvince() {
        String provice = "";
        try {
            String[] str = getReceiverAddress().split(",");
            provice = str[0];
        } catch (Exception e) {
            provice = "";
        }
        return provice;
    }


    public String getCity() {
        String provice = "";
        try {
            String[] str = getReceiverAddress().split(",");
            provice = str[1];
        } catch (Exception e) {
            provice = "";
        }
        return provice;
    }

    public String getDistrict() {
        String provice = "";
        try {
            String[] str = getReceiverAddress().split(",");
            provice = str[2];
        } catch (Exception e) {
            provice = "";
        }
        return provice;
    }

    public BigDecimal getGoodsValue() {
        return getGoodsValuePrice() == null ? null : new BigDecimal(getGoodsValuePrice());
    }

    public BigDecimal getFreight() {
        return getShippingFee() == null ? BigDecimal.ZERO : getShippingFee();
    }

    public BigDecimal getTaxTotal() {
        return getTaxTotalPrice() == null ? null : new BigDecimal(getTaxTotalPrice());
    }

    public BigDecimal getInsuredFee() {
        return BigDecimal.ZERO;
    }
}

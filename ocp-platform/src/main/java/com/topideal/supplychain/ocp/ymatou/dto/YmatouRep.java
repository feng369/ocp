package com.topideal.supplychain.ocp.ymatou.dto;

import com.topideal.supplychain.ocp.order.dto.OrderYmatouDto;

import java.util.List;

/**
 * @author xiao yu qiang
 * @time 2019/7/9 14:14
 * @description 洋码头返回对象
 * @table
 */
public class YmatouRep {
    //返回响应代码 0-成功
    private String code;
    //message
    private String message;
    //JSON Object
    private Content content;

    public class Content{
        //总记录数
        private Long total;
        private List<OrderYmatouDto> ordersInfo;
        private OrderYmatouDto orderInfo;

        public OrderYmatouDto getOrderInfo() {
            return orderInfo;
        }

        public void setOrderInfo(OrderYmatouDto orderInfo) {
            this.orderInfo = orderInfo;
        }

        public Long getTotal() {
            return total;
        }

        public void setTotal(Long total) {
            this.total = total;
        }

        public List<OrderYmatouDto> getOrdersInfo() {
            return ordersInfo;
        }

        public void setOrdersInfo(
            List<OrderYmatouDto> ordersInfo) {
            this.ordersInfo = ordersInfo;
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
}

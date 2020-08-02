package com.topideal.supplychain.ocp.order.mapper;

import com.topideal.supplychain.ocp.order.model.OrderYmatouDelivery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderYmatouDeliveryMapper {

    int insert(OrderYmatouDelivery record);

    OrderYmatouDelivery selectById(Long id);

    int update(OrderYmatouDelivery record);

    List<OrderYmatouDelivery> selectByMsgOrderId(Long msgOrderId);
}
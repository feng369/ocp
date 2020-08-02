package com.topideal.supplychain.ocp.order.mapper;

import com.topideal.supplychain.ocp.order.model.OrderYmatouItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderYmatouItemMapper {


    int insert(OrderYmatouItem record);


    OrderYmatouItem selectById(Long id);


    int update(OrderYmatouItem record);

    List<OrderYmatouItem> selectByOrderIdAndStatus(@Param("orderId") Long orderId, @Param("status") String status);
}
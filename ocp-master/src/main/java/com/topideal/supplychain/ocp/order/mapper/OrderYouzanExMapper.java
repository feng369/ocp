package com.topideal.supplychain.ocp.order.mapper;

import com.topideal.supplychain.ocp.order.model.OrderYouzanEx;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderYouzanExMapper {
    int deleteById(Long id);

    int insert(OrderYouzanEx record);

    OrderYouzanEx selectById(Long id);

    int updateById(OrderYouzanEx record);

    OrderYouzanEx selectByOrderId(Long orderId);
}
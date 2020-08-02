package com.topideal.supplychain.ocp.order.mapper;


import com.topideal.supplychain.ocp.order.model.OrderQmItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface OrderQmItemMapper {
    int deleteById(Long id);

    int insert(OrderQmItem record);

    OrderQmItem selectById(Long id);

    int update(OrderQmItem record);

    List<OrderQmItem> selectByOrderId(Long orderId);
}
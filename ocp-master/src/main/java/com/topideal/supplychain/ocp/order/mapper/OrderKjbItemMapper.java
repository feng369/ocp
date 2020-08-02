package com.topideal.supplychain.ocp.order.mapper;


import com.topideal.supplychain.ocp.order.model.OrderKjbItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface OrderKjbItemMapper {
    int deleteById(Long id);

    int insert(OrderKjbItem record);

    OrderKjbItem selectById(Long id);

    int update(OrderKjbItem record);

    List<OrderKjbItem> selectByOrderId(Long orderId);

    int batchInsert(List<OrderKjbItem> list);
}
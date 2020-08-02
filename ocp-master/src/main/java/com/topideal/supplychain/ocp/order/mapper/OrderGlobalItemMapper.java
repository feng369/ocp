package com.topideal.supplychain.ocp.order.mapper;


import com.topideal.supplychain.ocp.order.model.OrderGlobalItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderGlobalItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderGlobalItem record);

    int batchInsert(@Param("orderId") Long orderId, @Param("itemList") List<OrderGlobalItem> itemList);

    OrderGlobalItem selectByPrimaryKey(Long id);

    List<OrderGlobalItem> selectByOrderId(Long orderId);
}
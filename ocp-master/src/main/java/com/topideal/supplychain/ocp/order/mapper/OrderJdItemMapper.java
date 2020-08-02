package com.topideal.supplychain.ocp.order.mapper;

import com.topideal.supplychain.ocp.order.model.OrderJdItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderJdItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderJdItem record);

    void insertList(@Param("itemList") List<OrderJdItem> itemList);

    List<OrderJdItem> selectByOrderId(Long orderId);
}
package com.topideal.supplychain.ocp.order.mapper;

import com.topideal.supplychain.ocp.order.model.OrderDxyItem;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDxyItemMapper {

    int deleteById(Long id);

    int insert(OrderDxyItem record);

    OrderDxyItem selectById(Long id);

    int updateById(OrderDxyItem record);

    void insertList(@Param("itemList") List<OrderDxyItem> itemList);

    List<OrderDxyItem> selectByOrderId(Long orderId);
}
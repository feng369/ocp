package com.topideal.supplychain.ocp.order.mapper;

import com.topideal.supplychain.ocp.order.model.OrderAmwayItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderAmwayItemMapper {
    int deleteByPrimaryKey(Integer id);

    void insertList(@Param("itemList") List<OrderAmwayItem> itemList);

    List<OrderAmwayItem> selectByOrderId(Long orderId);

}
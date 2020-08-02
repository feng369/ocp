package com.topideal.supplychain.ocp.order.mapper;

import com.topideal.supplychain.ocp.order.model.OrderBeibeiItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderBeibeiItemMapper {

    int insert(OrderBeibeiItem record);

    List<OrderBeibeiItem> selectByOrderId(Long orderId);

}
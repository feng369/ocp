package com.topideal.supplychain.ocp.order.mapper;

import com.topideal.supplychain.ocp.order.model.OrderAmwayPaymentLine;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderAmwayPaymentLineMapper {

    int insert(OrderAmwayPaymentLine record);

    void insertList(@Param("itemList") List<OrderAmwayPaymentLine> itemList);

    List<OrderAmwayPaymentLine> selectByOrderId(Long orderId);
}
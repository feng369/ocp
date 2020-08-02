package com.topideal.supplychain.ocp.order.mapper;

import com.topideal.supplychain.ocp.order.model.OrderYouzanPayments;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderYouzanPaymentsMapper {
    int deleteById(Long id);

    int insert(OrderYouzanPayments record);

    OrderYouzanPayments selectById(Long id);

    int updateById(OrderYouzanPayments record);

    int batchInsert(List<OrderYouzanPayments> phasePayments);

    List<OrderYouzanPayments> selectByOrderId(Long orderId);
}
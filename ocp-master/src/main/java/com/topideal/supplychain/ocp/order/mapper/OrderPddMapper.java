package com.topideal.supplychain.ocp.order.mapper;

import com.topideal.supplychain.ocp.order.dto.OrderPddDto;
import com.topideal.supplychain.ocp.order.model.OrderPdd;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface OrderPddMapper {

    int insert(OrderPdd record);

    OrderPdd selectById(Long id);

    List<OrderPdd> selectByIds(@Param("ids") Long[] ids);

    OrderPdd selectByCode(String code);

    List<OrderPdd> selectByOrderId(String orderId);

    List<OrderPddDto> pageList(OrderPddDto condition);

    int update(OrderPdd record);

    int updateOrderStatus(@Param("id") Long id,@Param("status") String status,@Param("reason") String reason);

    int updateTax(@Param("id") Long id, @Param("geminiTotalPrice") BigDecimal geminiTotalPrice, @Param("taxFcy") BigDecimal taxFcy);
}
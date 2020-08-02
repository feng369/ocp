package com.topideal.supplychain.ocp.order.mapper;

import com.topideal.supplychain.ocp.order.dto.OrderXiaomiDto;
import com.topideal.supplychain.ocp.order.model.OrderXiaomi;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderXiaomiMapper {

    List<OrderXiaomiDto> pageList(OrderXiaomiDto order);

    int insert(OrderXiaomi record);

    OrderXiaomi selectById(Long id);

    OrderXiaomi selectByOrderId(String id);

    OrderXiaomiDto selectDtoById(Long id);

    void updateOrderStatus(@Param("id") Long id, @Param("status") String status, @Param("reason") String reason);

    List<OrderXiaomi> selectByIds(List<Long> ids);

}
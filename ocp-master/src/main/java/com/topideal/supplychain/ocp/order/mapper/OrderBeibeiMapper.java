package com.topideal.supplychain.ocp.order.mapper;

import com.topideal.supplychain.ocp.order.dto.OrderBeibeiDto;
import com.topideal.supplychain.ocp.order.model.OrderBeibei;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderBeibeiMapper {

    List<OrderBeibeiDto> pageList(OrderBeibeiDto order);

    int insert(OrderBeibei record);

    OrderBeibei selectById(Long id);

    List<OrderBeibei> selectByOId(String id);

    OrderBeibeiDto selectDtoById(Long id);

    void updateOrderStatus(@Param("id") Long id, @Param("status") String status, @Param("reason") String reason);

    List<OrderBeibei> selectByIds(List<Long> ids);
}
package com.topideal.supplychain.ocp.order.mapper;

import com.topideal.supplychain.ocp.enums.ForwardSystemEnum;
import com.topideal.supplychain.ocp.order.dto.OrderAmwayDto;
import com.topideal.supplychain.ocp.order.model.OrderAmway;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderAmwayMapper {


    int insert(OrderAmway record);

    OrderAmway selectById(Long id);

    List<OrderAmwayDto> pageList(OrderAmwayDto filter);

    List<OrderAmway> selectByIds(@Param("ids") Long[] ids);

    void updateOrderStatus(@Param("id")Long id, @Param("status")String status);

    void updateForwardSystem(@Param("id") Long id, @Param("forwardSystem") ForwardSystemEnum forwardSystem);

}
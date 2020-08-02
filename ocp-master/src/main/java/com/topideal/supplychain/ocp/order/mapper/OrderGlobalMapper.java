package com.topideal.supplychain.ocp.order.mapper;

import com.topideal.supplychain.ocp.order.model.OrderGlobal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderGlobalMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderGlobal record);

    int update(OrderGlobal record);

    List<OrderGlobal> selectByIds(@Param("ids") List<Long> ids);

    List<OrderGlobal> pageList(OrderGlobal filter);

    OrderGlobal selectById(Long id);

    int insertNotExist(OrderGlobal orderGlobal);
}
package com.topideal.supplychain.ocp.order.mapper;


import com.topideal.supplychain.ocp.order.model.OrderVip;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderVipMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderVip record);

    OrderVip selectById(Long id);

    int updateByPrimaryKey(OrderVip record);

    List<OrderVip> pageList(OrderVip filter);

    List<OrderVip> selectByIds(@Param("ids") List<Long> ids);

    void update(OrderVip updateEntity);

    void updateByIds(OrderVip orderVip);
}
package com.topideal.supplychain.ocp.order.mapper;


import com.topideal.supplychain.ocp.order.model.OrderQm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface OrderQmMapper {
    int deleteById(Long id);

    int insert(OrderQm record);

    OrderQm selectById(Long id);

    int update(OrderQm record);

    /**
     * 分页查询
     * @param orderQm
     * @return
     */
    List<OrderQm> pageList(OrderQm orderQm);
}
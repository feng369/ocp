package com.topideal.supplychain.ocp.order.mapper;


import com.topideal.supplychain.ocp.order.model.OrderKjb;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface OrderKjbMapper {
    int deleteById(Long id);

    int insert(OrderKjb record);

    OrderKjb selectById(Long id);

    int update(OrderKjb record);

    /**
     * 分页查询
     * @param orderKjb
     * @return
     */
    List<OrderKjb> pageList(OrderKjb orderKjb);

    OrderKjb selectExist(@Param("tradeNo") String tradeNo, @Param("storeCode") String storeCode,
                         @Param("requestTime") String requestTime);

    List<OrderKjb> selectByIds(@Param("ids") List<Long> ids);
}
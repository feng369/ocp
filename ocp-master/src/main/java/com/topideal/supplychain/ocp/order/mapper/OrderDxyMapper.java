package com.topideal.supplychain.ocp.order.mapper;

import com.topideal.supplychain.ocp.order.dto.OrderDxyDto;
import com.topideal.supplychain.ocp.order.model.OrderDxy;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDxyMapper {

    int deleteById(Long id);

    int insert(OrderDxy record);

    OrderDxy selectById(Long id);

    int updateById(OrderDxy record);

    boolean isExist(String orderId);

    void updateOrderStatus(@Param("id")Long id, @Param("status")String status);

    List<OrderDxy> pageList(OrderDxyDto filter);

    List<OrderDxy> selectByIds(@Param("ids")List<Long> ids);
}
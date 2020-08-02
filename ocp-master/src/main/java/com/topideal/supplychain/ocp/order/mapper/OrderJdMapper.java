package com.topideal.supplychain.ocp.order.mapper;

import com.topideal.supplychain.ocp.order.dto.OrderJdDto;
import com.topideal.supplychain.ocp.order.model.OrderJd;
import com.topideal.supplychain.ocp.order.model.OrderYouzan;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderJdMapper {

    int insert(OrderJd record);

    List<OrderJd> pageList(OrderJdDto filter);

    OrderJd selectById(Long id);

    void updateOrderStatus(@Param("id")Long id, @Param("status")String status, @Param("reason")String reason);

    List<OrderJd> selectListByIds(@Param("ids")Long[] ids);

    boolean isExist(@Param("orderId")String orderId, @Param("status")String status);
}
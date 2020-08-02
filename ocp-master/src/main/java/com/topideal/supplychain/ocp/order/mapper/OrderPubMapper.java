package com.topideal.supplychain.ocp.order.mapper;


import com.topideal.supplychain.ocp.order.model.OrderPub;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Mapper
public interface OrderPubMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderPub record);

    int update(OrderPub record);

    int updateByPrimaryKey(OrderPub record);

    List<OrderPub> pageList(OrderPub filter);

    OrderPub selectById(Long id);

    OrderPub selectByStoreAndCode(@Param("storeCode") String storeCode, @Param("orderNo") String orderNo);

    List<OrderPub> selectByIds(@Param("ids") List<Long> ids);
}
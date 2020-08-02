package com.topideal.supplychain.ocp.order.mapper;

import com.topideal.supplychain.ocp.order.model.OrderHipacItem;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 海拍客订单明细
 *
 * @author xuxiaoyan
 * @date 2019-12-16 15:21
 */
@Mapper
public interface OrderHipacItemMapper {

    List<OrderHipacItem> selectByOrderId(@Param("orderId") Long orderId);

    void insertList(@Param("itemList") List<OrderHipacItem> itemList);
}

package com.topideal.supplychain.ocp.order.mapper;

import com.topideal.supplychain.ocp.enums.GsIsSendKjbEnum;
import com.topideal.supplychain.ocp.order.model.OrderGsItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>标题: TODO</p>
 * <p>功能: TODO</p>
 * <p>模块: ocp2</p>
 * <p>版权: Copyright © 2020 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.order.mapper</p>
 * <p>作者: topIdeal</p>
 * <p>创建日期: 2020-01-14 12:04</p>
 *
 * @version 1.0
 */
@Mapper
public interface OrderGsItemMapper {

    List<OrderGsItem> selectByOrderId(@Param("orderId") Long id, @Param("orderNewEnum") GsIsSendKjbEnum old);

    int batchInsert(@Param("list") List<OrderGsItem> goodsList);
}

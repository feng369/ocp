package com.topideal.supplychain.ocp.order.mapper;

import com.topideal.supplychain.ocp.enums.ForwardSystemEnum;
import com.topideal.supplychain.ocp.order.dto.OrderGsDto;
import com.topideal.supplychain.ocp.order.model.OrderGs;
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
 * <p>创建日期: 2020-01-13 17:51</p>
 *
 * @version 1.0
 */
@Mapper
public interface OrderGsMapper {

    OrderGs selectById(Long id);

    List<OrderGs> selectByIds(@Param("ids") List<Long> ids);

    List<OrderGs> pageList(OrderGsDto orderGsDto);

    List<OrderGs> selectByConsignCode(@Param("consignCode") String consignCode);

    void updateOrderStatus(@Param("id") Long id, @Param("status") String status, @Param("reason") String reason, @Param("kjbStatus") String kjbStatus);

    void updateForwardSystem(@Param("id") Long id, @Param("forwardSystem") ForwardSystemEnum forwardSystem);

    int insert(OrderGs record);
}

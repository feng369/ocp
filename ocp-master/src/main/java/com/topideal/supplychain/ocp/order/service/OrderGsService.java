package com.topideal.supplychain.ocp.order.service;

import com.topideal.supplychain.ocp.enums.ForwardSystemEnum;
import com.topideal.supplychain.ocp.order.dto.OrderGsDto;
import com.topideal.supplychain.ocp.order.model.OrderGs;

import java.util.List;

/**
 * <p>标题: TODO</p>
 * <p>功能: TODO</p>
 * <p>模块: ocp2</p>
 * <p>版权: Copyright © 2020 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.order.service</p>
 * <p>作者: topIdeal</p>
 * <p>创建日期: 2020-01-13 17:46</p>
 *
 * @version 1.0
 */
public interface OrderGsService {

    void insert(OrderGs orderGs);

    List<OrderGs> pageList(OrderGsDto orderGsDto);

    void updateOrderStatus(Long id, String status, String reason, String kjbStatus);

    void updateOrderStatus(Long id, String status, String reason);

    void updateForwardSystem(Long id, ForwardSystemEnum forwardSystem);

    OrderGs selectById(Long id);

    List<OrderGs> selectByIds(List<Long> ids);

    List<OrderGs> selectByConsignCode(String consignCode);
}

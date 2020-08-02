package com.topideal.supplychain.ocp.order.service;

import com.topideal.supplychain.ocp.enums.OrderStatusEnum;
import com.topideal.supplychain.ocp.order.dto.OrderBigPageRequestDto;
import com.topideal.supplychain.ocp.order.model.OrderBig;

import java.util.List;

/**
 * 标题：大订单service
 * 模块：ocp-parent
 * 版权: Copyright © 2019 topideal
 * 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.order.service
 * 作者：songping
 * 创建日期：2019/12/16 14:16
 *
 * @version 1.0
 */
public interface OrderBigService {

    /**
     * 分页查询订单
     * @param filter
     * @return
     */
    List<OrderBig> pageList(OrderBigPageRequestDto filter);

    /**
     * 查询订单
     * @param id
     * @return
     */
    OrderBig selectById(Long id);

    /**
     * 查询订单
     * @param code
     * @return
     */
    OrderBig selectByCode(String code);

    /**
     * 批量查询订单
     * @param ids
     * @return
     */
    List<OrderBig> selectByIds(List<Long> ids);

    /**
     * 查询敏感信息
     * @param propertyName
     * @param id
     * @return
     */
    String selectSensitiveData(String propertyName, Long id);

    /**
     * 更新订单
     * @param updateOrder
     */
    void update(OrderBig updateOrder);

    /**
     * 新增订单
     * @param order
     */
    void insert(OrderBig order);

    /**
     * 删除订单
     * @param id
     */
    void deleteById(Long id);

    /**
     * 更新转单状态
     * @param id
     * @param pushSystem
     * @param pushStatus
     * @param notes
     */
    void updatePushStatus(Long id, String pushSystem, OrderStatusEnum pushStatus, String notes);

    /**
     * 企业+平台+业务模式+订单号做幂等
     * @param electricCode
     * @param cbepcomCode
     * @param s
     * @param orderId
     * @return
     */
    OrderBig selectByConditon(String electricCode, String cbepcomCode, String s, String orderId);
}

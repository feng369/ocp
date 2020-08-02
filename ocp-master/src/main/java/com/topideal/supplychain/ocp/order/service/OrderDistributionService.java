package com.topideal.supplychain.ocp.order.service;

import com.topideal.supplychain.ocp.order.dto.OrderDistributionPageRequestDto;
import com.topideal.supplychain.ocp.order.model.OrderDistribution;
import java.util.List;

/**
 * 分销订单
 *
 * @author xuxiaoyan
 * @date 2020-05-21 15:13
 */
public interface OrderDistributionService {

    /**
     * 主页面查询
     */
    List<OrderDistribution> pageList(OrderDistributionPageRequestDto filter);

    void insert(OrderDistribution distribution);

    OrderDistribution selectById(Long businessId);

    void update(OrderDistribution updateOrder);

    List<OrderDistribution> selectByIds(List<Long> ids);

    /**
     * 企业+平台+业务模式+订单号做幂等
     * @param electricCode
     * @param cbepcomCode
     * @param s
     * @param orderId
     * @return
     */
    OrderDistribution selectByConditon(String electricCode, String cbepcomCode, String s, String orderId);

    void updateOrderStatus(Long id, String status,String msg);
}

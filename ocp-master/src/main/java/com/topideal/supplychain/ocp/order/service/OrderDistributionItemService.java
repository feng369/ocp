package com.topideal.supplychain.ocp.order.service;

import com.topideal.supplychain.ocp.order.model.OrderDistributionItem;
import java.util.List; /**
 * 分销订单明细
 *
 * @author xuxiaoyan
 * @date 2020-05-21 15:15
 */
public interface OrderDistributionItemService {

    void insertItems(List<OrderDistributionItem> itemList);

    List<OrderDistributionItem> selectByOrder(Long id);
}

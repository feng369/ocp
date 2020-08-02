package com.topideal.supplychain.ocp.order.service;

import com.topideal.supplychain.ocp.order.model.OrderHipacItem;
import java.util.List;

/**
 * 海拍客订单商品明细
 *
 * @author xuxiaoyan
 * @date 2019-12-16 15:17
 */
public interface OrderHipacItemService {

    List<OrderHipacItem> selectByOrderId(Long id);

    void insertList(List<OrderHipacItem> itemList);
}

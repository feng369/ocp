package com.topideal.supplychain.ocp.order.service;

import com.topideal.supplychain.ocp.order.model.OrderDxyItem;
import java.util.List;

/**
 * @ClassName OrderDxyItemService
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2020/6/30 18:24
 * @Version 1.0
 **/
public interface OrderDxyItemService {

    void insertList(List<OrderDxyItem> itemList);

    List<OrderDxyItem> selectByOrderId(Long id);
}

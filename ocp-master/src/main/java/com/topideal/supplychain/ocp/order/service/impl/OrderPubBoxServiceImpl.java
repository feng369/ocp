package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.order.mapper.OrderPubBoxMapper;
import com.topideal.supplychain.ocp.order.mapper.OrderPubItemMapper;
import com.topideal.supplychain.ocp.order.model.OrderPubBox;
import com.topideal.supplychain.ocp.order.model.OrderPubItem;
import com.topideal.supplychain.ocp.order.service.OrderPubBoxService;
import com.topideal.supplychain.ocp.order.service.OrderPubItemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>标题: </p>
 * <p>模块: ocp-parent</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.order.service.impl</p>
 * <p>作者: LeoZhang</p>
 * <p>创建日期: 2019/12/2 17:42</p>
 *
 * @version 1.0
 */
@Service
public class OrderPubBoxServiceImpl implements OrderPubBoxService {

    @Autowired
    private OrderPubBoxMapper orderPubBoxMapper;

    @Override
    public List<OrderPubBox> selectByOrderId(Long orderId) {
        return orderPubBoxMapper.selectByOrderId(orderId);
    }

    @Override
    @Transactional
    public void batchInsert(Long orderId, List<OrderPubBox> boxList) {
        orderPubBoxMapper.batchInsert(orderId, boxList);
    }
}

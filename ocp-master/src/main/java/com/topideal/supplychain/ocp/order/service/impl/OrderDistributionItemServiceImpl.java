package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.order.mapper.OrderDistributionItemMapper;
import com.topideal.supplychain.ocp.order.model.OrderDistributionItem;
import com.topideal.supplychain.ocp.order.service.OrderDistributionItemService;
import com.topideal.supplychain.security.authorization.Authentication;
import com.topideal.supplychain.util.DateUtils;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * 分销订单明细
 *
 * @author xuxiaoyan
 * @date 2020-05-21 15:15
 */
@Service
public class OrderDistributionItemServiceImpl implements OrderDistributionItemService {

    @Autowired
    private OrderDistributionItemMapper orderDistributionItemMapper;

    @Override
    @Transactional
    public void insertItems(List<OrderDistributionItem> itemList) {
        if (CollectionUtils.isEmpty(itemList)){
            return;
        }
        Date nowTime = DateUtils.getNowTime();
        Long userId = Authentication.getUserId();
        for (OrderDistributionItem item : itemList) {
            item.setCreateBy(userId);
            item.setCreateTime(nowTime);
        }
        orderDistributionItemMapper.insertBatch(itemList);
    }

    @Override
    public List<OrderDistributionItem> selectByOrder(Long id) {
        return orderDistributionItemMapper.selectByOrder(id);
    }
}

package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.order.mapper.OrderAmwayPaymentLineMapper;
import com.topideal.supplychain.ocp.order.model.OrderAmwayPaymentLine;
import com.topideal.supplychain.ocp.order.service.OrderAmwayPaymentLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class OrderAmwayPaymentLineServiceImpl implements OrderAmwayPaymentLineService {
    @Autowired
    private OrderAmwayPaymentLineMapper mapper;

    @Override
    @Transactional
    public void insertList(List<OrderAmwayPaymentLine> itemList) {
        mapper.insertList(itemList);
    }

    @Override
    public List<OrderAmwayPaymentLine> selectByOrderId(Long orderId) {
        return mapper.selectByOrderId(orderId);
    }
}

package com.topideal.supplychain.ocp.kjb.service;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.kjb.dto.KjbReceiveReqDto;
import com.topideal.supplychain.ocp.order.model.OrderKjb;

import java.util.List;

/**
 * @description: 跨境宝订单
 * @author: syq
 * @create: 2019-12-17 16:35
 **/
public interface KjbService {

    BaseResponse sendOrderEsd(OrderKjb orderKjb);

    void receiveOrder(KjbReceiveReqDto kjbReceiveReqDto, String timestamp);

    void repushEsd(List<Long> ids);
}

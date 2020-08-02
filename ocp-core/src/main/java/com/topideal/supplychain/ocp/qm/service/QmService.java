package com.topideal.supplychain.ocp.qm.service;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.order.model.OrderQm;

/**
 * @description: 奇门订单
 * @author: syq
 * @create: 2019-12-16 10:18
 **/
public interface QmService {

    BaseResponse sendOrderEsd(OrderQm orderQm);

}

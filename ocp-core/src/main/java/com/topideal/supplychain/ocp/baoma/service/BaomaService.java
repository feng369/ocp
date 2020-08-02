package com.topideal.supplychain.ocp.baoma.service;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.order.dto.OrderBaomaDto;
import com.topideal.supplychain.ocp.order.model.OrderTemp;

import java.util.List;

/**
 * @author wanzhaozhang
 * @date 2019/12/13
 * @description
 **/
public interface BaomaService {

    //抓取订的
    BaseResponse getOrder(CatchOrderConfig catchOrderConfig);
    //保存订单
    void saveOrder(OrderTemp orderTemp);
    //推送ofc
    BaseResponse pushOfc(OrderBaomaDto orderBaoma);
    //推送op
    BaseResponse pushOp(OrderBaomaDto orderBaoma);

    //重推订单
    BaseResponse rePush(List<Long> ids);
}

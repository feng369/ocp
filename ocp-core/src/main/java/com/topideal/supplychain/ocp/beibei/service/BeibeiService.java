package com.topideal.supplychain.ocp.beibei.service;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.master.model.Store;
import com.topideal.supplychain.ocp.order.dto.OrderBeibeiDto;
import com.topideal.supplychain.ocp.order.model.OrderTemp;

import java.util.List;

/**
 * @author wanzhaozhang
 * @date 2020/3/19
 * @description
 **/
public interface BeibeiService {

    //抓取订单
    void getOrder(CatchOrderConfig catchOrderConfig);
    //保存订单
    void processOrder(OrderTemp orderTemp, Store store);
    //推送ofc
    BaseResponse pushOfc(OrderBeibeiDto orderBeibei);
    //重推订单
    BaseResponse rePush(List<Long> ids);
    /**
     * 根据orderId抓取订单
     * @param orderId 订单id，多个用逗号[,]隔开
     * @param storeId 店铺id
     * @return
     */
    BaseResponse grabOnlyOrder(String orderId,Long storeId);
}

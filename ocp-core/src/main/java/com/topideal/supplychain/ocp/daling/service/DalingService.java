package com.topideal.supplychain.ocp.daling.service;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.order.model.OrderDaling;
import com.topideal.supplychain.ocp.order.model.OrderTemp;

/**
 * 达令家
 *
 * @author xuxiaoyan
 * @date 2019-12-11 10:13
 */
public interface DalingService {

    /**
     * 重推订单
     * @param ids
     */
    String rePush(Long[] ids);

    /**
     * 推订单到OP
     * @param orderDaling
     * @return
     */
    BaseResponse sendOrderOp(OrderDaling orderDaling);

    /**
     * 抓取详情订单
     * @param orderDaling
     * @return
     */
    void grabDetail(OrderDaling orderDaling);

    /**
     * 抓取订单
     * @param catchOrderConfig
     * @return
     */
    void getOrder(CatchOrderConfig catchOrderConfig, boolean isMiss);

    /**
     * 抓取订单
     * @param catchOrderConfig
     * @return
     */
    void getMissOrder(CatchOrderConfig catchOrderConfig);

    /**
     * 推订单到OFC
     * @param orderDaling
     * @return
     */
    BaseResponse sendOrderOfc(OrderDaling orderDaling);

    /**
     * 处理达令家临时表
     * @param orderTemp
     */
    void processOrder(OrderTemp orderTemp);
}

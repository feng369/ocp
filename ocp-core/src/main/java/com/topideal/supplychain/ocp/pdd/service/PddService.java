package com.topideal.supplychain.ocp.pdd.service;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.config.model.TransferConfig;
import com.topideal.supplychain.ocp.order.model.OrderPdd;
import com.topideal.supplychain.ocp.order.model.OrderTemp;

import java.util.Date;

/**
 * @author klw
 * @date 2019/12/12 13:43
 * @description: 拼多多业务service
 */
public interface PddService {
    /**
     * 抓取订单
     * @param config
     */
    void catchOrder(CatchOrderConfig config, Date executeTime);

    /**
     * 单票订单抓取
     * @param config
     * @param orderSn
     */
    void catchSingleOrder(CatchOrderConfig config, String orderSn) throws Exception;

    /**
     * 抓单订单处理
     * @param orderTemp
     * @param config
     */
    void processCatchOrder(OrderTemp orderTemp, CatchOrderConfig config, Boolean isSingleCatch);

    /**
     * 接单订单处理
     * @param orderTemp
     */
    void processReceiveOrder(OrderTemp orderTemp);

    /**
     * 推送订单信息至KJB做商品拆分
     * @param order
     * @return
     */
    BaseResponse pushKJB(OrderPdd order);

    /**
     * 推送订单信息至GEMINI做税价分离
     * @param order
     * @return
     */
    BaseResponse pushGemini(OrderPdd order);

    /**
     * 下发系统路由分发
     * @param orderPdd
     * @param transferConfig
     */
    void router(OrderPdd orderPdd, TransferConfig transferConfig);

    /**
     * 下发ofc
     * @param order
     * @param transferConfig
     * @return
     */
    BaseResponse pushOfc(OrderPdd order, TransferConfig transferConfig);
    /**
     * 下发op
     * @param order
     * @param transferConfig
     * @return
     */
    BaseResponse pushOp(OrderPdd order, TransferConfig transferConfig);

    /**
     * 下发esd
     * @param order
     * @param transferConfig
     * @return
     */
    BaseResponse pushEsd(OrderPdd order, TransferConfig transferConfig);
}

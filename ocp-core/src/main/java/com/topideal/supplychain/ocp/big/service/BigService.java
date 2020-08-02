package com.topideal.supplychain.ocp.big.service;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.big.dto.BigReceiveRequestDto;
import com.topideal.supplychain.ocp.order.model.OrderBig;

import com.topideal.supplychain.ocp.order.model.OrderTemp;
import java.util.List;

/**
 * 标题：大订单业务service
 * 模块：ocp-parent
 * 版权: Copyright © 2019 topideal
 * 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.big.service
 * 作者：songping
 * 创建日期：2019/12/26 18:21
 *
 * @version 1.0
 */
public interface BigService {

    /**
     * 校验保存订单
     */
    void receiveOrder(BigReceiveRequestDto requestDto, String requestJson);

    /**
     * 转发OFC
     */
    BaseResponse forwardOFC(OrderBig orderBig);

    /**
     * 转发OFC-BC
     */
    BaseResponse forwardOFCBC(OrderBig orderBig);

    /**
     * 转发OP
     */
    BaseResponse forwardOP(OrderBig orderBig);

    /**
     * 订单重推
     * @param ids
     * @return
     */
    String rePush(List<Long> ids);

    /**
     * 大订单处理
     * @param orderTemp
     */
    void processOrder(OrderTemp orderTemp);
}

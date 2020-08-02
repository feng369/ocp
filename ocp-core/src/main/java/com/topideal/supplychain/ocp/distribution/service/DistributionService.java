package com.topideal.supplychain.ocp.distribution.service;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.big.dto.BigReceiveRequestDto;
import com.topideal.supplychain.ocp.fx.dto.FxReceiveDto;
import com.topideal.supplychain.ocp.order.model.OrderDistribution;
import com.topideal.supplychain.ocp.order.model.OrderTemp;
import java.util.List;

/**
 * 分销订单 业务逻辑
 *
 * @author xuxiaoyan
 * @date 2020-05-22 10:31
 */
public interface DistributionService {

    /**
     * 校验保存订单
     */
    void receiveOrder(FxReceiveDto requestDto, String requestJson);

    /**
     * 处理临时数据 保存订单
     * @param orderTemp
     */
    void processOrder(OrderTemp orderTemp);

    /**
     * 下发跨境宝分销接口
     * @param orderDistribution
     * @return
     */
    BaseResponse sendOrderKjb(OrderDistribution orderDistribution);

    /**
     * 重推订单
     * @param ids
     * @return
     */
    String rePush(List<Long> ids);

    BaseResponse sendOrderOfc(OrderDistribution orderDistribution);

    BaseResponse sendOrderEw(OrderDistribution orderDistribution);
}

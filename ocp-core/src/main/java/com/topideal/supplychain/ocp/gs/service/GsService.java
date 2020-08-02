package com.topideal.supplychain.ocp.gs.service;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.order.model.OrderGs;
import com.topideal.supplychain.ocp.order.model.OrderTemp;

import java.util.List;

/**
 * <p>标题: TODO</p>
 * <p>功能: TODO</p>
 * <p>模块: ocp2</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.gs.service.impl</p>
 * <p>作者: topIdeal</p>
 * <p>创建日期: 2019-12-30 18:03</p>
 *
 * @version 1.0
 */
public interface GsService {

    void getOrder(CatchOrderConfig catchOrderConfig);

    BaseResponse getOrder(Long id, String consignCode);

    void processOrder(OrderTemp orderTemp, CatchOrderConfig catchOrderConfig);

    BaseResponse sendOrderKjb(OrderGs orderGs);

    BaseResponse sendOrderOfc(OrderGs orderGs);

    BaseResponse sendOrderOp(OrderGs orderGs);

    String rePush(List<Long> ids);
}

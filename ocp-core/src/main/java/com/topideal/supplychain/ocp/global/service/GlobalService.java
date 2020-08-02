package com.topideal.supplychain.ocp.global.service;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.global.dto.GlobalParam;
import com.topideal.supplychain.ocp.global.dto.GlobalResponseDto;
import com.topideal.supplychain.ocp.order.model.OrderGlobal;

import java.util.List;

/**
 * <p>标题: </p>
 * <p>模块: ocp-parent</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.vip</p>
 * <p>作者: LeoZhang</p>
 * <p>创建日期: 2019/11/27 18:19</p>
 *
 * @version 1.0
 */
public interface GlobalService {
    
    String rePush(List<Long> ids);

    BaseResponse<List<GlobalResponseDto.Content.OrderResult>> getOrder(CatchOrderConfig catchOrderConfig, GlobalParam params);

    BaseResponse pushOrderToOfc(OrderGlobal orderGlobal);

}

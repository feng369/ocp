package com.topideal.supplychain.ocp.gs.service;

import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.gs.dto.GsGrabReqDto.GsGrabArgs;
import com.topideal.supplychain.ocp.gs.dto.OrderGsReqDto;
import java.util.List;

/**
 * <p>标题: TODO</p>
 * <p>功能: TODO</p>
 * <p>模块: ocp2</p>
 * <p>版权: Copyright © 2020 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.gs.service</p>
 * <p>作者: topIdeal</p>
 * <p>创建日期: 2020-01-07 16:55</p>
 *
 * @version 1.0
 */
public interface GsApiService {

    List<OrderGsReqDto> grabOrders(GsGrabArgs gsGrabArgs,CatchOrderConfig catchOrderConfig);

    List<OrderGsReqDto> grabOrder(GsGrabArgs gsGrabArgs,CatchOrderConfig catchOrderConfig, String consignCode);

}

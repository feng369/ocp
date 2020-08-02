package com.topideal.supplychain.ocp.global.service;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.global.dto.GlobalParam;
import com.topideal.supplychain.ocp.global.dto.GlobalResponseDto;
import com.topideal.supplychain.ocp.master.model.Store;

import java.util.List;

/**
 * <p>标题: </p>
 * <p>模块: ocp-parent</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.global.service</p>
 * <p>作者: LeoZhang</p>
 * <p>创建日期: 2019/12/10 14:12</p>
 *
 * @version 1.0
 */
public interface GlobalApiService {

    BaseResponse<List<GlobalResponseDto.Content.OrderResult>> grabOrder(GlobalParam param);
}

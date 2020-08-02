package com.topideal.supplychain.ocp.vip.service;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.order.model.OrderTemp;
import com.topideal.supplychain.ocp.order.model.OrderVip;
import com.topideal.supplychain.ocp.vip.dto.VipRequestArgsVo;
import java.util.List;
import org.apache.poi.ss.formula.functions.T;

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
public interface VipService {
    String rePush(List<Long> ids);

    BaseResponse pushOrderToOfc(OrderVip orderVip);

    void getOrder(CatchOrderConfig catchOrderConfig, VipRequestArgsVo vipRequestArgsVo, VipRequestArgsVo.Args args);

    BaseResponse<T> pushOrderToEsd(OrderVip orderVip);

    /**
     * 订单处理
     * @param orderTemp
     * @param config
     */
    void processOrder(OrderTemp orderTemp, CatchOrderConfig config);
}

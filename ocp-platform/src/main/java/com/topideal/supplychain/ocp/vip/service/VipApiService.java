package com.topideal.supplychain.ocp.vip.service;

import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.order.model.OrderVip;
import com.topideal.supplychain.ocp.vip.dto.VipRequestArgsVo;
import java.util.List;

/**
 * <p>标题: </p>
 * <p>模块: ocp-parent</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.vip</p>
 * <p>作者: LeoZhang</p>
 * <p>创建日期: 2019/12/4 16:13</p>
 *
 * @version 1.0
 */
public interface VipApiService {

    List<OrderVip> grabOrders(CatchOrderConfig catchOrderConfig, VipRequestArgsVo vipRequestArgsVo, VipRequestArgsVo.Args args);

    boolean feedBack(List<OrderVip> orderVips, VipRequestArgsVo vipRequestArgsVo);
}

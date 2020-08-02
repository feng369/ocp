package com.topideal.supplychain.ocp.pub.service;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.order.model.OrderPub;
import com.topideal.supplychain.ocp.pub.dto.OrderContent;

import java.util.List;

/**
 * <p>标题: </p>
 * <p>模块: ocp-parent</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.pub.consumer.service</p>
 * <p>作者: LeoZhang</p>
 * <p>创建日期: 2019/12/2 17:32</p>
 *
 * @version 1.0
 */
public interface PubService {
    void saveOrder(OrderContent orderReq);

    BaseResponse pushOrderToEsd(OrderPub orderPub);

    String rePush(List<Long> ids);
}

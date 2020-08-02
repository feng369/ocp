package com.topideal.supplychain.ocp.hipac.service;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.hipac.dto.HipacReceiveRequest;
import com.topideal.supplychain.ocp.order.model.OrderHipac;
import java.util.List;

/**
 * 海拍客
 *
 * @author xuxiaoyan
 * @date 2019-12-18 09:48
 */
public interface HipacService {

    /**
     * 海拍客接单
     * @param req
     * @return
     */
    void receiveOrder(HipacReceiveRequest req, String requestStr);

    /**
     * 订单重推
     * @param ids
     * @return
     */
    String rePush(List<Long> ids);

    /**
     * 订单重推=》OP
     * @param orderHipac
     * @return
     */
    BaseResponse sendOrderOp(OrderHipac orderHipac);

    /**
     * 订单重推=》OFC
     * @param orderHipac
     * @return
     */
    BaseResponse sendOrderOfc(OrderHipac orderHipac);


}

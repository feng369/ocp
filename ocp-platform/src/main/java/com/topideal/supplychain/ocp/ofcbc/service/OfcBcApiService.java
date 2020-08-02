package com.topideal.supplychain.ocp.ofcbc.service;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.ofcbc.dto.OfcBcExpRequest;
import com.topideal.supplychain.ocp.ofcbc.dto.OfcBcRequest;

/**
 * 标题：OfcBcApiService
 * 模块：ocp-parent
 * 版权: Copyright © 2019 topideal
 * 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.ofcbc.service
 * 作者：songping
 * 创建日期：2020/1/20 14:30
 *
 * @version 1.0
 */
public interface OfcBcApiService {

    /**
     * BC
     * @param req
     * @param businessCode
     * @param orderCode
     * @return
     */
    BaseResponse sendBcOrder(OfcBcRequest req, String businessCode, String orderCode);

    /**
     * BC-EXP
     * @param req
     * @param businessCode
     * @param orderCode
     * @return
     */
    BaseResponse sendBcExpOrder(OfcBcExpRequest req, String businessCode, String orderCode);
}

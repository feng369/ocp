package com.topideal.supplychain.ocp.gemini.service;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.gemini.dto.GeminiRequest;
import com.topideal.supplychain.ocp.gemini.dto.GeminiResponse;

/**
 * @author wanzhaozhang
 * @date 2019/12/9
 * @description
 **/
public interface GeminiApiService {

    /**
     * 发送订单到Gemini 调用税金接口
     *
     * @param dto
     * @param businessCode
     * @param orderCode
     * @return
     */
    BaseResponse<GeminiResponse> sendOrder(GeminiRequest dto, String businessCode, String orderCode);
}

package com.topideal.supplychain.ocp.dxy.service;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.dxy.dto.DxyOrderResponse;
import com.topideal.supplychain.ocp.dxy.dto.DxyRequest;
import java.util.List;

/**
 * @ClassName DxyApiService
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2020/6/30 15:45
 * @Version 1.0
 **/
public interface DxyApiService {

    BaseResponse<List<DxyOrderResponse>> getOrder(DxyRequest dxyRequest,
            CatchOrderConfig catchOrderConfig);
}

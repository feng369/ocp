package com.topideal.supplychain.ocp.mdm.service;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.mdm.dto.MdmSyncRequest;
import com.topideal.supplychain.ocp.mdm.dto.MdmSyncResponse;

/**
 * @description: 对接主数据接口
 * @author: syq
 * @create: 2019-12-05 17:53
 **/
public interface MdmApiService {

    BaseResponse<MdmSyncResponse> syncGoodsInfo(MdmSyncRequest request, String ocpCode);

}

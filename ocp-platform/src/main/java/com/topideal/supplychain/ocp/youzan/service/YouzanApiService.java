package com.topideal.supplychain.ocp.youzan.service;

import com.topideal.supplychain.ocp.common.ExpCodeEnum;
import com.topideal.supplychain.ocp.master.model.Store;
import com.topideal.supplychain.ocp.youzan.dto.YouZanConfig;
import com.topideal.supplychain.ocp.youzan.dto.YouzanToken.OAuthToken;
import com.youzan.cloud.open.sdk.gen.v4_0_0.model.YouzanTradesSoldGetParams;
import java.util.List;

/**
 * @ClassName YouzanApiService
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2019/11/21 17:28
 * @Version 1.0
 **/
public interface YouzanApiService {

    /**
     * 获取订单信息
     * @return
     */
    List getOrder(YouzanTradesSoldGetParams params,Store store,YouZanConfig youZanConfig, ExpCodeEnum expCodeEnum);

    /**
     * 获取店铺token信息
     * @return
     */
    OAuthToken getToken(Store store,YouZanConfig youZanConfig);
}

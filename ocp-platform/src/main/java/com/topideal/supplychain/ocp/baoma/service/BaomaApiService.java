package com.topideal.supplychain.ocp.baoma.service;

import com.topideal.supplychain.ocp.baoma.dto.BaomaResponse;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.master.model.Store;

/**
 * @author wanzhaozhang
 * @date 2019/12/13
 * @description
 **/
public interface BaomaApiService {

    BaomaResponse getOrder(CatchOrderConfig catchOrderConfig, Store store);
}

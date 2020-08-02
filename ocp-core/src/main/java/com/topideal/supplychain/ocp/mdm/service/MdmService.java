package com.topideal.supplychain.ocp.mdm.service;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.master.model.GoodsInfo;

/**
 * @description: 对接主数据
 * @author: syq
 * @create: 2019-12-09 14:50
 **/
public interface MdmService {

    BaseResponse syncGoodsInfo(GoodsInfo goodsInfo);

}

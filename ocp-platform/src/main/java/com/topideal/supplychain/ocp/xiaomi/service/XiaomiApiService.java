package com.topideal.supplychain.ocp.xiaomi.service;

import com.topideal.supplychain.ocp.xiaomi.dto.DataDto;
import com.topideal.supplychain.ocp.xiaomi.dto.PartnerInfo;

import java.util.List;
import java.util.Map;

/**
 * @author wanzhaozhang
 * @date 2019/12/27
 * @description
 **/
public interface XiaomiApiService {
    /**
     * 抓单
     * @param mapParam 请求参数
     * @param info 小米平台对接参数
     * @param isBatch true 批量抓单  false 根据id集合抓单
     * @return
     */
    List<DataDto> grab(Map<String,String> mapParam, PartnerInfo info,Boolean isBatch);


}

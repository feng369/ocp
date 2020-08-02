package com.topideal.supplychain.ocp.beibei.service;

import com.topideal.supplychain.ocp.beibei.dto.BeibeiDetailResponseDto;
import com.topideal.supplychain.ocp.beibei.dto.BeibeiResponseDto;

import java.util.TreeMap;

/**
 * @author wanzhaozhang
 * @date 2020/3/23
 * @description
 **/
public interface BeibeiApiService {

    BeibeiResponseDto getOrder(TreeMap<String, String> treeMap);

    BeibeiDetailResponseDto getOrderDetail(TreeMap<String,String> treeMap);

}

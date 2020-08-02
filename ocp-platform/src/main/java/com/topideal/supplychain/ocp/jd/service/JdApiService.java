package com.topideal.supplychain.ocp.jd.service;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.jd.dto.JdRequest;
import java.util.List;

public interface JdApiService {

    ///批量抓取自营非自营订单
    BaseResponse<List<String>> grabOrder(JdRequest jdRequest,String code);
    //批量抓单云霄购的订单
    BaseResponse<List<String>> grabYxOrder(JdRequest jdRequest,String code);
    //批量抓取独立站的订单
    BaseResponse<List<String>> grabDlzOrder(JdRequest jdRequest,String code);
    //单票抓取自营非自营
    List<String> grabOneOrder(JdRequest jdRequest);
    //单票抓云霄购
    List<String> grabOneYxOrder(JdRequest jdRequest);
    //单票抓独立站
    List<String> grabOneDlzOrder(JdRequest jdRequest);
}

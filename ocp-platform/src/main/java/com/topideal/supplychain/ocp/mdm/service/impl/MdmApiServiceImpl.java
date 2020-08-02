package com.topideal.supplychain.ocp.mdm.service.impl;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.enumeration.SuccessFailureEnum;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.log.HttpLog;
import com.topideal.supplychain.log.dto.ExpInvokeLogDto;
import com.topideal.supplychain.ocp.common.ExpCodeEnum;
import com.topideal.supplychain.ocp.common.SourceEnum;
import com.topideal.supplychain.ocp.enums.MdmResponseEnum;
import com.topideal.supplychain.ocp.mdm.dto.MdmSyncRequest;
import com.topideal.supplychain.ocp.mdm.dto.MdmSyncResponse;
import com.topideal.supplychain.ocp.mdm.service.MdmApiService;
import com.topideal.supplychain.util.DateUtils;
import com.topideal.supplychain.util.JacksonUtils;
import com.topideal.supplychain.web.config.service.InterfaceUrlService;
import com.topideal.supplychain.web.log.model.ExpInvokeLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @description: 对接主数据接口
 * @author: syq
 * @create: 2019-12-05 17:54
 **/
@Service
public class MdmApiServiceImpl implements MdmApiService {

    @Autowired
    private InterfaceUrlService interfaceUrlService;
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 主数据同步商品信息接口
     * @param mdmSyncRequest
     * @return
     */
    @Override
    @HttpLog
    public BaseResponse<MdmSyncResponse> syncGoodsInfo(MdmSyncRequest mdmSyncRequest, String ocpCode) {
        String requestStr = JacksonUtils.toJSon(mdmSyncRequest);
        //接口日志
        ExpInvokeLog expInvokeLog = getInvokeLog(ExpCodeEnum.MDM107, SourceEnum.OCP, SourceEnum.MDM, requestStr, ocpCode);

        String url = interfaceUrlService.getUrl("mdm.goods.sync.url");
        BusinessAssert.assertNotEmpty(url, "接口地址未配置：mdm.goods.sync.url");
        expInvokeLog.setInterfaceUrl(url);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        String responseStr = restTemplate.postForObject(url, new HttpEntity(requestStr, headers), String.class);
        expInvokeLog.setResponseData(responseStr);
        MdmSyncResponse response = JacksonUtils.readValue(responseStr, MdmSyncResponse.class);
        if (response == null) {
            return BaseResponse.responseFailure("接口异常：回执报文为空");
        }
        if (MdmResponseEnum.SUCCESS.getValue().equals(response.getCode())) {
            expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS);
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setFlag(BaseResponse.SUCCESS);
            baseResponse.setData(response);
            return baseResponse;
        }

        return BaseResponse.responseFailure(response.getMessage());
    }

    /**
     * 获取接口日志
     * @param expCodeEnum
     * @param source
     * @param target
     * @return
     */
    private ExpInvokeLog getInvokeLog(ExpCodeEnum expCodeEnum, SourceEnum source, SourceEnum target, String requestStr, String ocpCode) {
        ExpInvokeLog expInvokeLog = ExpInvokeLogDto.get()
                .setInterfaceEnum(expCodeEnum)
                .setCode(expCodeEnum.getCode())
                .setName(expCodeEnum.getName())
                .setSource(source.getCode())
                .setTarget(target.getCode())
                .setStartTime(DateUtils.getNowTime())
                .setFlag(SuccessFailureEnum.SUCCESS)
                .setRequestData(requestStr)
                .setBusinessCode(ocpCode)
                .setOrderCode(ocpCode);
        return expInvokeLog;
    }
}

package com.topideal.supplychain.ocp.restful;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.enumeration.EnableOrDisableEnum;
import com.topideal.supplychain.enumeration.SuccessFailureEnum;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.log.HttpLog;
import com.topideal.supplychain.log.HttpLogRequestBody;
import com.topideal.supplychain.log.dto.ExpInvokeLogDto;
import com.topideal.supplychain.ocp.common.ExpCodeEnum;
import com.topideal.supplychain.ocp.common.ResponseCodeEnum;
import com.topideal.supplychain.ocp.common.SourceEnum;
import com.topideal.supplychain.ocp.enums.BusiModeEnum;
import com.topideal.supplychain.ocp.enums.MerchantEnum;
import com.topideal.supplychain.ocp.enums.PlatformEnum;
import com.topideal.supplychain.ocp.master.dto.EsdStoreConfig;
import com.topideal.supplychain.ocp.master.dto.StoreInfoRequest;
import com.topideal.supplychain.ocp.master.dto.StoreRequestDto;
import com.topideal.supplychain.ocp.master.dto.SyncStoreDto;
import com.topideal.supplychain.ocp.master.model.Store;
import com.topideal.supplychain.ocp.master.service.MerchantService;
import com.topideal.supplychain.ocp.master.service.PlatformService;
import com.topideal.supplychain.ocp.master.service.StoreService;
import com.topideal.supplychain.util.JacksonUtils;
import com.topideal.supplychain.web.log.model.ExpInvokeLog;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wanzhaozhang
 * @date 2019/11/22
 * @description ocp 店铺 对外提供rest接口类
 **/
@Controller
@RequestMapping("/rest/store")
public class StoreApiController {

    private static final Logger logger = LoggerFactory.getLogger(StoreApiController.class);

    @Autowired
    private StoreService storeService;

    @Autowired
    private PlatformService platformService;

    @Autowired
    private MerchantService merchantService;


    /**
     * 店铺同步 来源mdm
     * @param syncStoreDto 参数实体
     * @return 结果
     * 测试路径： ~/ocp/rest/router/syncStore
     */
    @ResponseBody
    @PostMapping("syncStore")
    @HttpLog
    public BaseResponse syncStore(@RequestBody SyncStoreDto syncStoreDto, HttpServletRequest request) {
        ExpInvokeLog expInvokeLog = ExpInvokeLogDto.get().setInterfaceEnum(ExpCodeEnum.MDM120)
                .setSource(SourceEnum.MDM.getCode())
                .setTarget(SourceEnum.OCP.getCode())
                .setInterfaceUrl(request.getRequestURI())
                .setRequestData(JacksonUtils.toJSon(syncStoreDto))
                .setBusinessCode(syncStoreDto.getShopCode())
                .setOrderCode(syncStoreDto.getShopCode())
                ;
        //参数校验
        //电商平台code
        String ebpCode = syncStoreDto.getEbpCode();
        //电商企业code
        String ebcCode = syncStoreDto.getEbcCode();
        BusinessAssert.assertNotEmpty(ebpCode,"电商平台code为空!");
        BusinessAssert.assertNotEmpty(ebcCode,"电商企业code为空!");
        BusinessAssert.assertNotNull(platformService.findByCode(ebpCode),"电商平台code不存在!");
        BusinessAssert.assertNotNull(merchantService.findByCode(ebcCode),"电商企业code不存在!");
        //根据平台，企业，店铺code确定唯一值
       /* Store filter = new Store();
        filter.setPlatformCode(ebpCode);
        filter.setMerchantCode(ebcCode);
        filter.setCode(syncStoreDto.getShopCode());*/
        Store store = storeService.selectByCode(syncStoreDto.getShopCode());
        EnableOrDisableEnum isDeleted = syncStoreDto.getStatus().equals("20") ? EnableOrDisableEnum.ENABLE : EnableOrDisableEnum.DISABLE;
        String appKey = syncStoreDto.getAppKey();
        String appSecret = syncStoreDto.getAppSecret();
        String token = syncStoreDto.getToken();
        if(null != store){
            //update
            /*for(Store store : stores){*/
                String storeArguments = store.getArguments();
                Map storeMap = Maps.newHashMap();
                if(StringUtils.isNotEmpty(storeArguments)){
                    storeMap = JSONObject.parseObject(storeArguments,Map.class);
                }
                if (StringUtils.isNotBlank(syncStoreDto.getAppId())) {
                    storeMap.put("appId", syncStoreDto.getAppId());
                }
                if(StringUtils.isNotBlank(appKey)){
                    storeMap.put("clientId", appKey);
                    storeMap.put("appKey",appKey);
                }
                if(StringUtils.isNotBlank(appSecret)){
                    storeMap.put("clientSecret", appSecret);
                }
                if(StringUtils.isNotBlank(token)){
                    storeMap.put("accessToken", token);
                }
                String jsonString = JSONObject.toJSONString(storeMap);
                store.setArguments(jsonString);
                store.setIsDeleted(isDeleted);
                store.setMerchantCode(ebcCode);
                store.setPlatformCode(ebpCode);
                storeService.update(store);
            /*}*/
        }else{
            //insert
            Store bean  = new Store();
            bean.setPlatformCode(ebpCode);
            bean.setMerchantCode(ebcCode);
            bean.setCode(syncStoreDto.getShopCode());
            bean.setBusiType(BusiModeEnum.BBC.getValue());
            bean.setIsDeleted(isDeleted);
            /*bean.setAppId(syncStoreDto.getAppId());
            bean.setAppKey(appKey);
            bean.setOverseaHouseCode(syncStoreDto.getStorehouseId());*/

            Map<String, String> storeMap = Maps.newHashMap();
            if (StringUtils.isNotBlank(syncStoreDto.getAppId())) {
                storeMap.put("appId", syncStoreDto.getAppId());
            }
            if (StringUtils.isNotBlank(appKey)) {
                storeMap.put("appKey",appKey);
                storeMap.put("clientId", appKey);
            }
            if (StringUtils.isNotBlank(appSecret)) {
                storeMap.put("clientSecret", appSecret);
            }
            if (StringUtils.isNotBlank(token)) {
                storeMap.put("accessToken", token);
            }
            String jsonString = JSONObject.toJSONString(storeMap);
            bean.setArguments(jsonString);
            storeService.insert(bean);
        }
        expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS)
                    .setResponseData(JacksonUtils.toJSon(BaseResponse.responseSuccess("店铺信息同步成功!")));
        return BaseResponse.responseSuccess("店铺信息同步成功!");
    }

    /**
     * 接受KJB店铺 新增或更新
     * @param request
     * @return
     *  测试路径： ~/ocp/rest/router/receiveStore
     */
    @PostMapping("receiveStore")
    @ResponseBody
    @HttpLog
    public String receiveStore(@HttpLogRequestBody StoreRequestDto request){

        ExpInvokeLog expInvokeLog = ExpInvokeLogDto.get().setInterfaceEnum(ExpCodeEnum.OCP101)
                .setSource(SourceEnum.ESD.getCode())
                .setTarget(SourceEnum.OCP.getCode())
                .setFlag(SuccessFailureEnum.FAILURE)
                ;
        String response = "";
        String businessCode = "";
        try{
            //校验请求参数
            String errStr = checkReceiveStoreParams(request);
            StoreInfoRequest storeRequest = JacksonUtils.readValue(request.getBiz_content(), StoreInfoRequest.class);
            if(StringUtils.isNotEmpty(errStr)){
                response = storeInfoResponse(ResponseCodeEnum.PARAMETER_MISS.getCode(), -1, errStr, null);
                expInvokeLog.setResponseData(response);
                return response;
            }
            businessCode = storeRequest.getShopId();
            expInvokeLog.setBusinessCode(businessCode).setOrderCode(businessCode);
            //校验信息是否完整
            String errMsg = checkStoreInfo(storeRequest);
            if (StringUtils.isNotEmpty(errMsg)) {
                response = storeInfoResponse(ResponseCodeEnum.PARAMETER_MISS.getCode(), -1, errMsg, null);
                expInvokeLog.setResponseData(response);
                return response;
            }
            //根据店铺code查询，有则更新，没有则新增
            Store store = new Store();
            EsdStoreConfig esdStoreConfig = EsdStoreConfig.newBuilder().appId(storeRequest.getApp_id())
                    .appKey(storeRequest.getApp_key())
                    .overseaHouseCode(storeRequest.getStorehouse_id())
                    .dno(storeRequest.getDno()).build();
            /*store.setOverseaHouseCode(storeRequest.getStorehouse_id());
            store.setAppId(storeRequest.getApp_id());
            store.setAppKey(storeRequest.getApp_key());*/
            store.setEsdArguments(JacksonUtils.toJSon(esdStoreConfig));
            /*Store filter = new Store();
            filter.setCode(storeRequest.getShopId());*/
            Store oldStore = storeService.selectByCode(storeRequest.getShopId());
            //如果不存在，则插入
            if(oldStore == null){
                store.setCode(storeRequest.getShopId());
                store.setIsDeleted(EnableOrDisableEnum.ENABLE);
                store.setPlatformCode(PlatformEnum.KJB.getCode());
                store.setMerchantCode(MerchantEnum.KJB.getCode());
                storeService.insert(store);
            }else {
                //查找电商平台code是KJB的店铺
                /*List<Store> codeList = storeList.stream().filter(a -> a.getPlatformCode().equals(PlatformEnum.KJB.getCode())).collect(Collectors.toList());
                if(ObjectUtils.isEmpty(codeList)){
                    store.setId(storeList.get(0).getId());
                    storeService.update(store);
                }else{
                    store.setId(codeList.get(0).getId());
                    storeService.update(store);
                }*/

                store.setId(oldStore.getId());
                storeService.update(store);
            }
            response = storeInfoResponse(ResponseCodeEnum.SUCCESS.getCode(), 1, null, null);
            expInvokeLog.setResponseData(response);
            expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS);
            return response;
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = storeInfoResponse(ResponseCodeEnum.BUSINESS_ERROR.getCode(), -1, "系统异常", null);
            expInvokeLog.setResponseData(response);
        }
        return response;
    }

    /**
     * 公共输入参数 校验
     * @param request
     * @return
     */
    private String checkReceiveStoreParams(StoreRequestDto request) {
        if (StringUtils.isEmpty(request.getMethod())) {
            return "缺少参数method";
        }
        if (StringUtils.isEmpty(request.getFormat())) {
            return "缺少参数format";
        }
        if (StringUtils.isEmpty(request.getCharset())) {
            return "缺少参数charset";
        }
        if (StringUtils.isEmpty(request.getTimestamp())) {
            return "缺少参数timestamp";
        }
        if (StringUtils.isEmpty(request.getVersion())) {
            return "缺少参数version";
        }
        if (StringUtils.isEmpty(request.getBiz_content())) {
            return "缺少参数biz_content";
        }
        return "";
    }

    /**
     * 构建店铺信息接口返回
     *
     * @param code
     * @param status
     * @param subMsg
     * @param body
     * @return
     */
    private String storeInfoResponse(String code, Integer status, String subMsg, Object body) {
        HashMap<String, Object> res = new HashMap<>();
        res.put("code", code);
        res.put("status", status);
        res.put("sub_msg", subMsg == null ? "" : subMsg);
        res.put("body", body == null ? (new HashMap<String, Object>()) : body);
        return JSON.toJSONString(res);
    }

    /**
     * 校验店铺信息必填性
     *
     * @param storeInfoRequest
     * @return
     */
    private String checkStoreInfo(StoreInfoRequest storeInfoRequest) {
        if (storeInfoRequest.getDno() == null || storeInfoRequest.getDno().trim().equals("")) {
            return "缺少esd店铺编码dno";
        }
        if (storeInfoRequest.getStorehouse_id() == null || storeInfoRequest.getStorehouse_id().trim().equals("")) {
            return "缺少海外仓编码storehouse_id";
        }
        if (storeInfoRequest.getApp_id() == null || storeInfoRequest.getApp_id().trim().equals("")) {
            return "缺少app_id";
        }
        if (storeInfoRequest.getApp_key() == null || storeInfoRequest.getApp_key().trim().equals("")) {
            return "缺少app_key";
        }
        if (storeInfoRequest.getShopId() == null || storeInfoRequest.getShopId().trim().equals("")) {
            return "缺少店铺编码shopId";
        }
        return "";
    }

}

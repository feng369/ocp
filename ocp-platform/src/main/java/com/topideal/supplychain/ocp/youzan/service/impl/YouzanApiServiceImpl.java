package com.topideal.supplychain.ocp.youzan.service.impl;

import com.google.common.base.Throwables;
import com.topideal.supplychain.enumeration.SuccessFailureEnum;
import com.topideal.supplychain.exception.BusinessException;
import com.topideal.supplychain.log.HttpLog;
import com.topideal.supplychain.log.dto.ExpInvokeLogDto;
import com.topideal.supplychain.ocp.common.ExpCodeEnum;
import com.topideal.supplychain.ocp.common.SourceEnum;
import com.topideal.supplychain.ocp.master.model.Store;
import com.topideal.supplychain.ocp.youzan.dto.TokenParameter;
import com.topideal.supplychain.ocp.youzan.dto.YouZanConfig;
import com.topideal.supplychain.ocp.youzan.dto.YouzanToken;
import com.topideal.supplychain.ocp.youzan.dto.YouzanToken.OAuthToken;
import com.topideal.supplychain.ocp.youzan.service.YouzanApiService;
import com.topideal.supplychain.util.DateUtils;
import com.topideal.supplychain.util.JacksonUtils;
import com.topideal.supplychain.web.config.service.InterfaceUrlService;
import com.topideal.supplychain.web.log.model.ExpInvokeLog;
import com.topideal.supplychain.web.log.service.ExpInvokeLogService;
import com.youzan.cloud.open.sdk.common.exception.SDKException;
import com.youzan.cloud.open.sdk.core.client.auth.Token;
import com.youzan.cloud.open.sdk.core.client.core.DefaultYZClient;
import com.youzan.cloud.open.sdk.gen.v4_0_0.api.YouzanTradesSoldGet;
import com.youzan.cloud.open.sdk.gen.v4_0_0.model.YouzanTradesSoldGetParams;
import com.youzan.cloud.open.sdk.gen.v4_0_0.model.YouzanTradesSoldGetResult;
import com.youzan.cloud.open.sdk.gen.v4_0_0.model.YouzanTradesSoldGetResult.YouzanTradesSoldGetResultFullorderinfolist;
import com.youzan.cloud.open.sdk.gen.v4_0_0.model.YouzanTradesSoldGetResult.YouzanTradesSoldGetResultOrders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName YouzanApiServiceImpl
 * @TODO 有赞接口api
 * @Author zhangzhihao
 * @DATE 2019/11/21 17:28
 * @Version 1.0
 **/
@Service
public class YouzanApiServiceImpl implements YouzanApiService {

    private final static Logger LOGGER = LoggerFactory.getLogger(YouzanApiServiceImpl.class);

    @Autowired
    private InterfaceUrlService interfaceUrlService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ExpInvokeLogService expInvokeLogService;

    /**
     * 调用有赞接口获取订单信息
     */
    //@HttpLog
    public List<YouzanTradesSoldGetResultFullorderinfolist> getOrder(
            YouzanTradesSoldGetParams params, Store store, YouZanConfig youZanConfig, ExpCodeEnum expCodeEnum) {
        ExpInvokeLog expInvokeLog = new ExpInvokeLog()
                .setInterfaceEnum(expCodeEnum)
                .setCode(expCodeEnum.getCode())
                .setName(expCodeEnum.getName())
                .setSource(SourceEnum.OCP.getCode())
                .setTarget(SourceEnum.YOUZAN.getCode())
                .setStartTime(DateUtils.getNowTime())
                .setFlag(SuccessFailureEnum.FAILURE);
        List<YouzanTradesSoldGetResultFullorderinfolist> list = new ArrayList<>();
        try {
            //1.创建 YZClient
            DefaultYZClient yzClient = new DefaultYZClient();
            //2.创建 api 对象
            YouzanTradesSoldGet soldGet = new YouzanTradesSoldGet();

            String gateWay = interfaceUrlService.getUrl("youzan.get.order");
            //3.为 api 设置参数
            if (StringUtils.isNotEmpty(gateWay)) {
                soldGet.setGateway(gateWay);
            }
            soldGet.setAPIParams(params);
            String url = String.format("%s/api/%s/%s", soldGet.getGateway(), soldGet.getName(),
                    soldGet.getVersion());
            expInvokeLog.setInterfaceUrl(url);
            expInvokeLog.setRequestData(JacksonUtils.toJSon(new Object[]{soldGet,store}));
            //TOKEN验证
            Token token = new Token(youZanConfig.getAccessToken());
            //SDK调用
            YouzanTradesSoldGetResult result = yzClient.invoke(soldGet, token,
                    YouzanTradesSoldGetResult.class);
            expInvokeLog.setResponseData(JacksonUtils.toJSon(result));
            if (result != null && result.getSuccess()) {
                expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS);
            }
            if (result != null && result.getData() != null
                    && result.getData().getFullOrderInfoList() != null) {
                list = result.getData().getFullOrderInfoList();
            }
        } catch (Exception e) {
            LOGGER.error("有赞抓单接口调用失败", e);
            expInvokeLog.setFlag(SuccessFailureEnum.FAILURE);
            expInvokeLog.setResponseData(expInvokeLog.getResponseData() + Throwables.getStackTraceAsString(e));
        } finally {
            Date end = DateUtils.getNowTime();
            /**补齐接口信息*/
            expInvokeLog.setEndTime(end).setResponseTime(end.getTime() - expInvokeLog.getStartTime().getTime());
            if (CollectionUtils.isEmpty(list)) {
                expInvokeLog.setOrderCode(params.getTid()).setBusinessCode(params.getTid());
                expInvokeLogService.insert(expInvokeLog);
            } else {
                for (YouzanTradesSoldGetResultFullorderinfolist orderInfo : list) {
                    String orderNo = orderInfo.getFullOrderInfo().getOrderInfo()
                            .getTid();//主订单号
                    YouzanTradesSoldGetResultOrders item = orderInfo.getFullOrderInfo()
                            .getOrders().get(0);
                    String subOrderNo = item == null ? orderNo : item.getSubOrderNo();
                    expInvokeLog.setOrderCode(orderNo).setBusinessCode(subOrderNo);
                    expInvokeLogService.insert(expInvokeLog);
                }
            }
        }

        return list;
    }

    /**
     * 获取token信息
     * @param
     * @param
     * @return
     */
    @Override
    @HttpLog
    public OAuthToken getToken(Store store,YouZanConfig youZanConfig){

        //获取请求url
        String tokenUrl =  interfaceUrlService.getUrl("youzan.get.token");

        ExpInvokeLog expInvokeLog = ExpInvokeLogDto.get().setInterfaceEnum(ExpCodeEnum.YOUZAN001)
                .setSource(SourceEnum.OCP.getCode())
                .setTarget(SourceEnum.YOUZAN.getCode())
                .setInterfaceUrl(tokenUrl)
                .setBusinessCode(store.getCode())
                .setOrderCode(store.getCode())
                ;

        if (StringUtils.isEmpty(tokenUrl)){
            throw new BusinessException("有赞店铺获取TOKEN请求地址youzan.get.token为空!");
        }

        TokenParameter tokenParameter = null;
        try {
            tokenParameter = TokenParameter.self().clientId(youZanConfig.getClientId())
                    .clientSecret(youZanConfig.getSecret())
                    .grantId(store.getCode())
                    .build();
        }catch (SDKException e) {
            LOGGER.error(e.getMessage(),e);
            throw new BusinessException("参数构建失败",e);
        }
        String req = JacksonUtils.toJSon(tokenParameter);
        expInvokeLog.setRequestData(req);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        String responseStr = restTemplate.postForObject(tokenUrl,new HttpEntity(req,headers),String.class,
                new Object[0]);


        expInvokeLog.setResponseData(responseStr);
        YouzanToken youzanToken = JacksonUtils.readValue(responseStr,YouzanToken.class);
        if (youzanToken == null || !youzanToken.isSuccess()) {
            throw new BusinessException("有赞token刷新失败："+responseStr);
        }
        expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS);
        return youzanToken.getData();
    }

}

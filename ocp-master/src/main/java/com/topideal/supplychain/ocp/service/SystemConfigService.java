package com.topideal.supplychain.ocp.service;

import com.topideal.supplychain.web.system.service.impl.BaseSystemConfigService;
import org.springframework.stereotype.Service;

@Service
public class SystemConfigService extends BaseSystemConfigService {

    /**
     * 海拍客接单校验签名配置:控制是否验签
     *
     * @return
     */
    public Boolean getHipacOrderSign() {
        return configService.getValueByKey("hipac.order.sign", true);
    }

    /**
     * 过滤拼多多抓单的重复订单
     *
     * @return
     */
    public Boolean getFilterPddDuplicateOrder() {
        return configService.getValueByKey("is.pdd.duplicateOrder.filter", true);
    }

    public int getIntervalTimeForMiss() {
        return configService.getValueByKey("interval.time.for.miss.order", 1);
    }

    /**
     *AMWAY_APP_SECRET("ymatou_list_method","安利接口appSecret")
     */
    public String getAmwayAppSecret() {
        return configService.getValueByKey("amway.appsecret.method", "");
    }
    /**
     *安利电商企业编码
     */
    public String getAmwayEbcCode() {
        return configService.getValueByKey("amway.ebccode.method", "");
    }
    /**
     *安利电商平台编码
     */
    public String getAmwayEbpCode() {
        return configService.getValueByKey("amway.ebpcode.method", "");
    }
    /**
     *安利配送商
     */
    public String getAmwayDeliveryProvider() {
        return configService.getValueByKey("amway.delivery.method", "");
    }
    /**
     *安利 安利--安利接口报文对象名
     */
    public String getAmWayMessageName() {
        return configService.getValueByKey("amway.amwayMessageName", "");
    }
    /**
     *安利 安利--安利接口报文对象名
     */
    public String getAmWayZZMessageName() {
        return configService.getValueByKey("amway.amwayZZMessageName", "");
    }
}

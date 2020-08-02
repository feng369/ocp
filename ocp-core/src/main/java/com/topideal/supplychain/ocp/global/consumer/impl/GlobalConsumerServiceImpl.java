package com.topideal.supplychain.ocp.global.consumer.impl;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.mq.NeedRetryException;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.config.service.CatchOrderConfigService;
import com.topideal.supplychain.ocp.global.consumer.GlobalConsumerService;
import com.topideal.supplychain.ocp.global.dto.GlobalParam;
import com.topideal.supplychain.ocp.global.dto.GlobalResponseDto;
import com.topideal.supplychain.ocp.global.service.GlobalService;
import com.topideal.supplychain.ocp.master.model.Store;
import com.topideal.supplychain.ocp.master.service.StoreService;
import com.topideal.supplychain.ocp.mq.QueueConstants;
import com.topideal.supplychain.ocp.order.model.OrderGlobal;
import com.topideal.supplychain.ocp.order.service.OrderGlobalService;
import com.topideal.supplychain.ocp.service.SystemConfigService;
import com.topideal.supplychain.util.DateUtils;
import com.topideal.supplychain.util.JacksonUtils;
import java.util.Map;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * <p>标题: 全球仓订单推送OFC</p>
 * <p>模块: ocp-parent</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.global.consumer.impl.GlobalConsumerServiceImpl</p>
 * <p>作者: LeoZhang</p>
 * <p>创建日期: 2019/12/10 10:03</p>
 *
 * @version 1.0
 */
@Component
public class GlobalConsumerServiceImpl implements GlobalConsumerService {


    @Autowired
    private GlobalService globalService;
    @Autowired
    private CatchOrderConfigService catchOrderConfigService;
    @Autowired
    private OrderGlobalService orderGlobalService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private SystemConfigService systemConfigService;

    /**
     * 全球仓抓单消费者
     *
     * @param message
     */
    @Override
    @RabbitHandler
    @RabbitListener(queues = QueueConstants.OCP_GLOBAL_GET_ORDER)
    public void getOrder(BasicMessage message) {
        CatchOrderConfig catchOrderConfig = catchOrderConfigService.selectEnableById(message.getBusinessId());
        if (catchOrderConfig == null) {
            return;
        }
        BusinessAssert.assertNotNull(catchOrderConfig.getStoreId(), "抓单配置的店铺id为空");
        Store store = storeService.selectEnableById(catchOrderConfig.getStoreId());
        BusinessAssert.assertNotNull(store, "店铺信息为空");
        Map<String, String> storeArgs = JacksonUtils.readValue(store.getArguments(), Map.class);
        BusinessAssert.assertNotNull(storeArgs, "店铺参数信息为空");

        //生成全球仓的请求配置
        Date nowTime = DateUtils.getNowTime();
        GlobalParam param = GlobalParam.Builder.newBuilder()
                .storeCode(store.getCode())
                .appId(storeArgs.get("appId"))
                .pageSize(catchOrderConfig.getPageSize())
                .secretKey(storeArgs.get("appKey"))
                .startTime(DateUtils.addMinute(nowTime, -30).getTime())
                .endTime(nowTime.getTime())
                .pageNo(1).build();
        List<GlobalResponseDto.Content.OrderResult> orderResults;
        //循环抓单,如果抓取的订单数量小于需要抓单的数量就停止循环(可能单量为0但是其实抓单是成功的)
        do {
            //传入页码
            BaseResponse<List<GlobalResponseDto.Content.OrderResult>> baseResponse = globalService.getOrder(catchOrderConfig, param);
            orderResults = baseResponse.getData();
            if (!baseResponse.isSuccess()) {
                throw new NeedRetryException(baseResponse.getMessage());
            }
            //BusinessAssert.assertIsTrue(baseResponse.isSuccess(), baseResponse.getMessage());
            param.setPageNo(param.getPageNo() + 1);
        } while (CollectionUtils.isNotEmpty(orderResults) && orderResults.size() == catchOrderConfig.getPageSize());
    }

    /**
     * 全球仓漏单抓取队列
     *
     * @param message
     */
    @Override
    @RabbitHandler
    @RabbitListener(queues = QueueConstants.OCP_GLOBAL_GET_MISS_ORDER)
    public void getMissOrder(BasicMessage message) {
        CatchOrderConfig catchOrderConfig = catchOrderConfigService.selectEnableById(message.getBusinessId());
        if (catchOrderConfig == null) {
            return;
        }
        BusinessAssert.assertNotNull(catchOrderConfig.getStoreId(), "抓单配置的店铺id为空");
        Store store = storeService.selectEnableById(catchOrderConfig.getStoreId());
        BusinessAssert.assertNotNull(store, "店铺信息为空");
        Map<String, String> storeArgs = JacksonUtils.readValue(store.getArguments(), Map.class);
        BusinessAssert.assertNotNull(storeArgs, "店铺参数信息为空");
        //生成全球仓的请求配置
        Date nowTime = DateUtils.getNowTime();
        //全球仓漏单系统参数
        int intervalTime = systemConfigService.getIntervalTimeForMiss() * -1;
        GlobalParam param = GlobalParam.Builder.newBuilder()
                .storeCode(store.getCode())
                .appId(storeArgs.get("appId"))
                .pageSize(catchOrderConfig.getPageSize())
                .secretKey(storeArgs.get("appKey"))
                .startTime(DateUtils.addMinute(DateUtils.addDay(nowTime, intervalTime), -30).getTime())
                .endTime(nowTime.getTime())
                .pageNo(1).build();
        List<GlobalResponseDto.Content.OrderResult> orderResults;
        //循环抓单,如果抓取的订单数量小于需要抓单的数量就停止循环(可能单量为0但是其实抓单是成功的)
        do {
            //传入页码
            BaseResponse<List<GlobalResponseDto.Content.OrderResult>> baseResponse = globalService.getOrder(catchOrderConfig, param);
            orderResults = baseResponse.getData();
            BusinessAssert.assertIsTrue(baseResponse.isSuccess(), baseResponse.getMessage());
            param.setPageNo(param.getPageNo() + 1);
        } while (CollectionUtils.isNotEmpty(orderResults) && orderResults.size() == catchOrderConfig.getPageSize());
    }

    /**
     * 全球仓订单推ofc消费者
     *
     * @param message
     */
    @RabbitHandler
    @RabbitListener(queues = QueueConstants.OCP_GLOBAL_ORDER_PUSH_OFC)
    public void pushOrder(BasicMessage message) {
        OrderGlobal orderGlobal = orderGlobalService.selectById(message.getBusinessId());
        if (orderGlobal == null) {
            return;
        }
        BaseResponse baseResponse = globalService.pushOrderToOfc(orderGlobal);
        //如果失败重试
        BusinessAssert.assertIsTrue(baseResponse.isSuccess(), baseResponse.getMessage());
    }
}

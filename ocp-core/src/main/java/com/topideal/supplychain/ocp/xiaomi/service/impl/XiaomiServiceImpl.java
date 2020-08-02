package com.topideal.supplychain.ocp.xiaomi.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.mq.MessageSender;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.enums.OrderStatusEnum;
import com.topideal.supplychain.ocp.kjb.dto.OrderKjbReq;
import com.topideal.supplychain.ocp.kjb.service.KjbApiService;
import com.topideal.supplychain.ocp.master.model.Store;
import com.topideal.supplychain.ocp.master.service.StoreService;
import com.topideal.supplychain.ocp.mq.QueueConstants;
import com.topideal.supplychain.ocp.order.dto.OrderXiaomiDto;
import com.topideal.supplychain.ocp.order.model.OrderTemp;
import com.topideal.supplychain.ocp.order.model.OrderXiaomi;
import com.topideal.supplychain.ocp.order.model.OrderXiaomiGoods;
import com.topideal.supplychain.ocp.order.service.OrderTempService;
import com.topideal.supplychain.ocp.order.service.OrderXiaomiGoodsService;
import com.topideal.supplychain.ocp.order.service.OrderXiaomiService;
import com.topideal.supplychain.ocp.service.SystemConfigService;
import com.topideal.supplychain.ocp.xiaomi.converter.XiaomiDtoConverter;
import com.topideal.supplychain.ocp.xiaomi.dto.DataDto;
import com.topideal.supplychain.ocp.xiaomi.dto.PartnerInfo;
import com.topideal.supplychain.ocp.xiaomi.service.XiaomiApiService;
import com.topideal.supplychain.ocp.xiaomi.service.XiaomiService;
import com.topideal.supplychain.util.DateUtils;
import com.topideal.supplychain.util.JacksonUtils;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * @author wanzhaozhang
 * @date 2019/12/13
 * @description
 **/
@Service
public class XiaomiServiceImpl implements XiaomiService {

    @Autowired
    private StoreService storeService;
    @Autowired
    private XiaomiApiService xiaomiApiService;
    @Autowired
    protected MessageSender messageSender;
    @Autowired
    private OrderTempService orderTempService;
    @Autowired
    private OrderXiaomiService orderXiaomiService;
    @Autowired
    private OrderXiaomiGoodsService orderXiaomiGoodsService;
    @Autowired
    private KjbApiService kjbApiService;
    @Autowired
    private SystemConfigService systemConfigService;


    /**
     * 正常抓单
     *
     * @param catchOrderConfig 抓单配置
     */
    @Override
    @Transactional
    public void getOrder(CatchOrderConfig catchOrderConfig) {
        getOrder(catchOrderConfig, false);
    }

    /**
     * 根据电批信息得到小米平台对接参数
     *
     * @param store 店铺信息
     * @return
     */
    private PartnerInfo getPartnerInfo(Store store) {
        BusinessAssert.assertNotNull(store, "店铺不存在");
        Map<String, String> storeArgs = JacksonUtils.readValue(store.getArguments(), Map.class);
        String aesKey = storeArgs.get("aesKey");
        BusinessAssert.assertNotEmpty(aesKey, "店铺参数[aesKey]为空");
        String appKey = storeArgs.get("appKey");
        BusinessAssert.assertNotEmpty(appKey, "店铺[appKey]为空");
        return new PartnerInfo(store.getCode(), appKey, aesKey);
    }

    /**
     * 根据orderId抓取订单
     *
     * @param orderId 订单id，多个用逗号[,]隔开
     * @param storeId 店铺id
     * @return
     */
    @Override
    public BaseResponse grabOnlyOrder(String orderId, Long storeId) {
        Store store = storeService.selectById(storeId);
        //小米平台对接参数
        PartnerInfo info = getPartnerInfo(store);
        Map<String, String> orderIdMap = Maps.newHashMap();
        orderIdMap.put("order_ids", "[" + orderId + "]");
        //根绝订单id抓取订单
        List<DataDto> dataDtos = xiaomiApiService.grab(orderIdMap, info, false);
        if (CollectionUtils.isEmpty(dataDtos)) {
            return BaseResponse.responseFailure("抓单失败");
        }
        List list = Lists.newArrayList(Arrays.asList(orderId.split(",")));
        List<String> orderIdList = Lists.newArrayList();
        dataDtos.forEach(dataDto -> {
            orderIdList.add(dataDto.getOrder_id().toString());
            OrderTemp orderTemp = new OrderTemp(JacksonUtils.toJSon(dataDto));
            BasicMessage basicMessage = new BasicMessage(orderTemp.getId(), dataDto.getOrder_id().toString(), store.getId().toString(), "");
            orderTempService.insertAndSendMq(orderTemp, QueueConstants.QueueEnum.OCP_XIAOMI_SAVE_ORDER, basicMessage);
        });
        list.removeAll(orderIdList);
        if (!CollectionUtils.isEmpty(list)) {
            return BaseResponse.responseFailure(String.format("订单%s抓单失败", JacksonUtils.toJSon(list)));
        }
        return BaseResponse.responseSuccess("抓单成功");
    }

    @Override
    @Transactional
    public BaseResponse pushKjb(OrderXiaomiDto order) {
        List<OrderXiaomiGoods> orderXiaomiGoods = orderXiaomiGoodsService.selectAllByOrderId(order.getId());
        order.setProducts(orderXiaomiGoods);

        //组织请求对象
        OrderKjbReq orderKjbReq = XiaomiDtoConverter.convertKjbOrder(order);
        //调用接口
        BaseResponse baseResponse = kjbApiService.sendOrder2Kjb(orderKjbReq, order.getOrderId(), order.getCode());
        OrderStatusEnum statusEnum = baseResponse.isSuccess() ? OrderStatusEnum.SEND_SUCCESS : OrderStatusEnum.SEND_FAILURE;
        //更新订单状态
        orderXiaomiService.updateOrderStatus(order.getId(), statusEnum.getValue(),baseResponse.getMessage());
        return baseResponse;
    }

    @Override
    public BaseResponse rePush(List<Long> ids) {
        List<OrderXiaomi> orderXiaomis = orderXiaomiService.selectByIds(ids);
        BusinessAssert.assertNotEmpty(orderXiaomis, "所选订单不存在");
        for (OrderXiaomi order : orderXiaomis) {
            //校验订单状态是否允许重推
            BusinessAssert.assertIsTrue(OrderStatusEnum.SEND_SUCCESS == order.getSendStatus(), String.format("订单[%s]当前状态不允许重推!", order.getCode()));
            //直接重新发送MQ
            QueueConstants.QueueEnum queueEnum = QueueConstants.QueueEnum.getQueueEnumByName("ocp.xiaomi.order.push." + order.getSendSystem().getQueue());
            messageSender.send(queueEnum, new BasicMessage(order.getId(), order.getCode()));
        }
        return BaseResponse.responseSuccess("重推成功");
    }

    /**
     * 保存订单信息
     *
     * @param store     店铺
     * @param orderTemp 临时订单
     */
    @Override
    @Transactional
    public void processOrder(Store store, OrderTemp orderTemp) {
        DataDto orderData = JacksonUtils.readValue(orderTemp.getJson(), DataDto.class);
        //删除临时数据
        orderTempService.deleteById(orderTemp.getId());
        //重复订单剔除
        OrderXiaomi orderXiaomi = orderXiaomiService.selectByOrderId(orderData.getOrder_id().toString());
        if(orderXiaomi !=null){
            return;
        }
        OrderXiaomiDto orderXiaomiDto = XiaomiDtoConverter.convertOrder(orderData, store);
        //保存订单
        orderXiaomiService.insert(orderXiaomiDto);
        //保存订单产品信息
        if (!CollectionUtils.isEmpty(orderXiaomiDto.getProducts())) {
            for (OrderXiaomiGoods goods : orderXiaomiDto.getProducts()) {
                goods.setOrderId(orderXiaomiDto.getId());
                orderXiaomiGoodsService.insert(goods);
            }
        }
        //推送至组套MQ
        BasicMessage basicMessage = new BasicMessage(orderXiaomiDto.getId(), orderXiaomiDto.getCode());
        messageSender.send(QueueConstants.QueueEnum.OCP_XIAOMI_ORDER_PUSH_KJB, basicMessage);
    }

    /**
     * 漏单抓单
     * @param catchOrderConfig
     */
    @Override
    @Transactional
    public void getMissOrder(CatchOrderConfig catchOrderConfig) {
        getOrder(catchOrderConfig, true);
    }


    /**
     * 小米抓单
     */
    private void getOrder(CatchOrderConfig catchOrderConfig, boolean isMiss) {
        Store store = storeService.selectById(catchOrderConfig.getStoreId());
        //小米平台对接参数
        PartnerInfo info = getPartnerInfo(store);
        Map<String, String> map = Maps.newHashMap();
        Date nowTime = DateUtils.getNowTime();
        //订单状态，查询多个状态用","  默认"4"，已支付
        map.put("status", "4");
        // 查询开始时间，订单支付时间，unix timestamp 秒 小米支付后两个小时才能抓到订单
        map.put("beginTime", String.valueOf(DateUtils.addMinutes(nowTime, -150).getTime() / 1000));
        if (isMiss) {
            int intervalTime = systemConfigService.getIntervalTimeForMiss() * -1;
            Date missDate = DateUtils.addDays(nowTime, intervalTime);
            map.put("beginTime", String.valueOf(DateUtils.addMinutes(missDate, -150).getTime() / 1000));
        }
        //// 查询结束时间，endTime-beginTime 不超过30天
        map.put("endTime", String.valueOf(nowTime.getTime() / 1000));
        //每次查询条数
        Integer pageSize = catchOrderConfig.getPageSize();
        int start = 0;
        do {
            // 开始位置
            map.put("start", String.valueOf(start * pageSize));
            start++;
            // 结束位置
            map.put("end", String.valueOf(start * pageSize));
            //抓单
            List<DataDto> dataDtos = xiaomiApiService.grab(map, info, true);
            if (CollectionUtils.isEmpty(dataDtos)) {
                break;
            }
            //保存临时表并发送mq
            dataDtos.forEach(dataDto -> {
                OrderTemp orderTemp = new OrderTemp(JacksonUtils.toJSon(dataDto));
                BasicMessage basicMessage = new BasicMessage(orderTemp.getId(),
                        dataDto.getOrder_id().toString(), store.getId().toString(), "");
                orderTempService
                        .insertAndSendMq(orderTemp, QueueConstants.QueueEnum.OCP_XIAOMI_SAVE_ORDER,
                                basicMessage);
            });

        } while (true);
    }
}

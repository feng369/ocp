package com.topideal.supplychain.ocp.ymatou.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.exception.BusinessException;
import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.mq.MessageSender;
import com.topideal.supplychain.ocp.config.dto.TransferDefaultConfig;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.config.model.TransferConfig;
import com.topideal.supplychain.ocp.config.service.TransferConfigService;
import com.topideal.supplychain.ocp.enums.BusiModeEnum;
import com.topideal.supplychain.ocp.enums.ForwardSystemEnum;
import com.topideal.supplychain.ocp.enums.KjbSendStausEnum;
import com.topideal.supplychain.ocp.enums.LogisticsEnum;
import com.topideal.supplychain.ocp.enums.OrderStatusEnum;
import com.topideal.supplychain.ocp.enums.YouZanOrderNewEnum;
import com.topideal.supplychain.ocp.gemini.dto.GeminiRequest;
import com.topideal.supplychain.ocp.gemini.dto.GeminiResponse;
import com.topideal.supplychain.ocp.gemini.service.GeminiApiService;
import com.topideal.supplychain.ocp.kjb.dto.KjbRequest;
import com.topideal.supplychain.ocp.kjb.dto.KjbResponse;
import com.topideal.supplychain.ocp.kjb.service.KjbApiService;
import com.topideal.supplychain.ocp.master.model.Store;
import com.topideal.supplychain.ocp.master.service.StoreService;
import com.topideal.supplychain.ocp.mq.QueueConstants;
import com.topideal.supplychain.ocp.ofc.dto.OfcRequest;
import com.topideal.supplychain.ocp.ofc.service.OfcApiService;
import com.topideal.supplychain.ocp.op.dto.OpRequest;
import com.topideal.supplychain.ocp.op.service.OpApiService;
import com.topideal.supplychain.ocp.order.dto.OrderYmatouDto;
import com.topideal.supplychain.ocp.order.model.OrderTemp;
import com.topideal.supplychain.ocp.order.model.OrderYmatou;
import com.topideal.supplychain.ocp.order.model.OrderYmatouDelivery;
import com.topideal.supplychain.ocp.order.model.OrderYmatouIndentity;
import com.topideal.supplychain.ocp.order.model.OrderYmatouItem;
import com.topideal.supplychain.ocp.order.service.OrderTempService;
import com.topideal.supplychain.ocp.order.service.OrderYmatouDeliveryService;
import com.topideal.supplychain.ocp.order.service.OrderYmatouIndentityService;
import com.topideal.supplychain.ocp.order.service.OrderYmatouItemService;
import com.topideal.supplychain.ocp.order.service.OrderYmatouService;
import com.topideal.supplychain.ocp.ymatou.converter.YmatouDtoConverter;
import com.topideal.supplychain.ocp.ymatou.dto.OrderPayRep;
import com.topideal.supplychain.ocp.ymatou.dto.YmatouGrabReq;
import com.topideal.supplychain.ocp.ymatou.service.YmatouApiService;
import com.topideal.supplychain.ocp.ymatou.service.YmatouService;
import com.topideal.supplychain.util.DateUtils;
import com.topideal.supplychain.util.JacksonUtils;
import com.topideal.supplychain.util.MD5Utils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wanzhaozhang
 * @date 2019/12/2
 * @description
 **/
@Service
public class YmatouServiceImpl implements YmatouService {

    @Autowired
    private StoreService storeService;
    @Autowired
    private YmatouApiService ymatouApiService;
    @Autowired
    private OrderYmatouService orderYmatouService;
    @Autowired
    private OrderYmatouItemService orderYmatouItemService;
    @Autowired
    private OrderYmatouIndentityService orderYmatouIndentityService;
    @Autowired
    private OrderYmatouDeliveryService orderYmatouDeliveryService;
    @Autowired
    protected MessageSender messageSender;
    @Autowired
    private TransferConfigService transferConfigService;
    @Autowired
    private KjbApiService kjbApiService;
    @Autowired
    private GeminiApiService geminiApiService;
    @Autowired
    private OfcApiService ofcApiService;
    @Autowired
    private OpApiService opApiService;
    @Autowired
    private OrderTempService orderTempService;

    @Override
    @Transactional
    public void processOrder(Store store, OrderTemp orderTemp) {
        saveOrder(store,orderTemp);
        //删除临时数据
        orderTempService.deleteById(orderTemp.getId());
    }
    /**
     * 保存订单信息
     *
     * @param store     店铺
     * @param orderTemp 洋码头临时订单
     */
    private void saveOrder(Store store, OrderTemp orderTemp) {
        OrderYmatouDto orderYmatou = JacksonUtils.readValue(orderTemp.getJson(), OrderYmatouDto.class);
        //验证订单状态
        Long orderStatus = orderYmatou.getOrderStatus();
        if (orderStatus != 2 && orderStatus != 17) {
          return ;
        }
        //设置默认值-从店铺上面拿到默认参数并进行赋值
        JsonNode argNode = JacksonUtils.readValue(store.getArguments());
        JsonNode listNode = argNode.get("list");
        for (JsonNode node : listNode) {
            //判断是否为bbc的单
            if (orderYmatou.getOrderItemsInfo().get(0).getDeliveryType()
                    .equals(node.get("busiType").asLong())) {
                orderYmatou.setBusiType(BusiModeEnum.BBC);
                orderYmatou.setEbpCode(node.get("ebpCode").asText());
                orderYmatou.setEbcCode(node.get("ebcCode").asText());
                orderYmatou.setCustomerCode(node.get("customsCode").asText());
                break;
            }
        }
        orderYmatou.setSendStatus(OrderStatusEnum.INIT);
        orderYmatou.setCreateTime(new Date());
        orderYmatou.setStoreId(store.getCode());
        //保存订单
        saveOrder(orderYmatou);

        // 根据状态判断调用订单支付接口还是订单接单接口
        BasicMessage basicMessage = new BasicMessage(orderYmatou.getId(), orderYmatou.getCode(), store.getId().toString(), "");
        if (2 == orderStatus) {
            messageSender.send(QueueConstants.QueueEnum.OCP_YMATOU_TAKE_ORDER, basicMessage);
        } else if (17 == orderStatus) {
            messageSender.send(QueueConstants.QueueEnum.OCP_YMATOU_PAY_ORDER, basicMessage);
        }
    }


    private void saveOrder(OrderYmatouDto order) {
        //保存主订单
        orderYmatouService.insert(order);
        //保存item
        if (!org.springframework.util.CollectionUtils.isEmpty(order.getOrderItemsInfo())) {
            for (int i = 1; i <= order.getOrderItemsInfo().size(); i++) {
                OrderYmatouItem item = order.getOrderItemsInfo().get(i - 1);
                item.setMsgOrderId(order.getId());
                item.setSendKjbFlag(YouZanOrderNewEnum.OLD.getValue());
                item.setGnum(Long.valueOf(i));
                orderYmatouItemService.insert(item);
            }
        }
        //保存id_card
        if (!org.springframework.util.CollectionUtils.isEmpty(order.getIdCards())) {
            for (OrderYmatouIndentity indentity : order.getIdCards()) {
                indentity.setMsgOrderId(order.getId());
                orderYmatouIndentityService.insert(indentity);
            }
        }
        //保存delivery
        if (!org.springframework.util.CollectionUtils.isEmpty(order.getDeliveryInfo())) {
            for (OrderYmatouDelivery delivery : order.getDeliveryInfo()) {
                delivery.setMsgOrderId(order.getId());
                orderYmatouDeliveryService.insert(delivery);
            }
        }
    }

    /**
     * 洋码头订单 调用组套接口
     *
     * @param order 订单
     */
    @Override
    @Transactional
    public BaseResponse toKjb(OrderYmatouDto order) {
        List<OrderYmatouItem> itemList = orderYmatouItemService.selectByOrderIdAndStatus(order.getId(), YouZanOrderNewEnum.OLD.getValue());
        order.setOrderItemsInfo(itemList);
        //组织请求对象
        KjbRequest reqKjb = YmatouDtoConverter.convertKjb(order);

        //调用接口
        BaseResponse<KjbResponse> response = kjbApiService.sendOrder(reqKjb, order.getOrderNo(), order.getCode());
        OrderStatusEnum orderStatus = response.isSuccess() ? OrderStatusEnum.KJB_SUCCESS : OrderStatusEnum.KJB_FAILURE;

        //更新状态
        updateKjbStatus(order, orderStatus.getValue(), response.getData().getNotes(), response.getData().getStatus().getValue());

        //将接口返回内容进行处理
        if (OrderStatusEnum.KJB_FAILURE == orderStatus) {
            return response;
        }
        //将调用后的数据进行处理item
        buildRepItemList(order.getId(), response.getData(), itemList);

        //发送mq到税金分离接口
        messageSender.send(QueueConstants.QueueEnum.OCP_YMATOU_ORDER_PUSH_GEMINI,
                new BasicMessage(order.getId(), order.getCode()));
        return response;
    }

    //更新订单状态
    private void updateKjbStatus(OrderYmatou order, String status, String reason, String sendStaus) {
        //状态处于下发成功，不执行更新
        if (order.getSendStatus() != OrderStatusEnum.SEND_SUCCESS) {
            OrderYmatou updateOrder = new OrderYmatou();
            updateOrder.setId(order.getId());
            updateOrder.setSendStatus(OrderStatusEnum.getByValue(status));
            updateOrder.setSendReason(reason);
            updateOrder.setSendKjbStatus(sendStaus);
            orderYmatouService.updateById(updateOrder);
        }
    }


    /**
     * 返回回写的item
     *
     * @param orderId
     * @param repKjb
     * @param itemList
     */
    private void buildRepItemList(Long orderId, KjbResponse repKjb, List<OrderYmatouItem> itemList) {
        List<OrderYmatouItem> pendingList = new ArrayList<>();
        if (KjbSendStausEnum.NO_DEAL == repKjb.getStatus()) {
            //状态为不处理
            for (OrderYmatouItem item : itemList) {
                item.setSendKjbFlag(YouZanOrderNewEnum.NEW.getValue());
                item.setId(null);
                pendingList.add(item);
            }
        } else {
            //处理成功
            for (KjbResponse.KjbResponseGoods item : repKjb.getGood_list()) {
                OrderYmatouItem orderItem = new OrderYmatouItem();
                orderItem.setMsgOrderId(orderId);
                orderItem.setProductTitle(item.getGoods_name());
                orderItem.setOuterSkuId(item.getGoods_id());
                orderItem.setNum(Long.valueOf(item.getGoods_num()));
                orderItem.setPrice(item.getGoods_price());
                orderItem.setGoodsUnit(item.getGoods_unit());

                orderItem.setSendKjbFlag(YouZanOrderNewEnum.NEW.getValue());
                pendingList.add(orderItem);
            }
        }
        orderYmatouItemService.batchInsert(pendingList);
    }

    /**
     * 洋码头订单 调用税金接口
     *
     * @param order 订单
     */
    @Override
    @Transactional
    public BaseResponse toGemini(OrderYmatouDto order) {

        //根据有赞订单的商品贸易模式判断推送OP还是OFC
        TransferConfig transferConfig = findConfig(order);
        if (transferConfig == null) {
            updateKjbStatus(order, OrderStatusEnum.GEMINI_FAILURE.getValue(), "订单未找到对应转发配置!", null);
            return BaseResponse.responseFailure("订单未找到对应转发配置!");
        }

        List<OrderYmatouItem> itemList = orderYmatouItemService.selectByOrderIdAndStatus(order.getId(), YouZanOrderNewEnum.NEW.getValue());
        order.setOrderItemsInfo(itemList);
        //封装JSON请求与报文
        GeminiRequest geminiRequest = YmatouDtoConverter.convertGemini(order);
        //发送请求
        BaseResponse<GeminiResponse> response = geminiApiService.sendOrder(geminiRequest, order.getOrderNo(), order.getCode());
        //更新订单状态
        OrderStatusEnum orderStatus = response.isSuccess() ? OrderStatusEnum.GEMINI_SUCCESS : OrderStatusEnum.GEMINI_FAILURE;
        updateKjbStatus(order, orderStatus.getValue(), response.getData().getFailMessage(), null);
        if (!response.isSuccess()) {
            return response;
        }
        GeminiResponse responseGemini = response.getData();
        ForwardSystemEnum forwardSystem = transferConfig.getForwardSystem();
        //更新订单完税价格和发送系统
        updateGeminiPrice(order.getId(), responseGemini, forwardSystem);
        QueueConstants.QueueEnum queueEnum = QueueConstants.QueueEnum.getQueueEnumByName("ocp.ymatou.order.push." + forwardSystem.getQueue());
        messageSender.send(queueEnum, new BasicMessage(order.getId(), order.getCode()));
        return response;
    }

    /**
     * 查找配置 ymatou toOp toGemini toOfc三个方法用到
     * 传入findOne各参数：5b8fa415e9823,固定值-待定,null（代表全部）,5158,10
     *
     * @param order
     * @return
     */
    private TransferConfig findConfig(OrderYmatou order) {
        return transferConfigService.findByUnique(order.getEbcCode(), order.getEbpCode(), LogisticsEnum.VL.getCode(), order.getCustomerCode(), order.getBusiType().getValue());
    }


    //更新税金接口回传字段和发送系统
    private void updateGeminiPrice(Long orderId, GeminiResponse responseGemini, ForwardSystemEnum forwardSystem) {
        //更新主表完税价格
        OrderYmatou updateOrder = new OrderYmatou();
        updateOrder.setId(orderId);
        updateOrder.setGoodsValuePrice(responseGemini.getMessage().getGoodsValue());
        updateOrder.setTaxTotalPrice(responseGemini.getMessage().getTaxTotal());
        updateOrder.setSendSystem(forwardSystem);
        orderYmatouService.updateById(updateOrder);
        //更新商品表完税价格
        for (GeminiResponse.Message.Goods goods : responseGemini.getMessage().getGoods()) {
            OrderYmatouItem item = new OrderYmatouItem();
            item.setId(Long.valueOf(goods.getId()));
            item.setRePrice(goods.getPrice());
            item.setReTotalPrice(goods.getTotalPrice());
            orderYmatouItemService.updateById(item);
        }
    }

    /**
     * 洋码头订单 调用OP接口
     *
     * @param order 订单
     */
    @Override
    @Transactional
    public BaseResponse toOp(OrderYmatouDto order) {
        defineOrderYmatou(order);
        //匹配转单配置
        TransferConfig transferConfig = findConfig(order);
        if (transferConfig == null) {
            updateKjbStatus(order, OrderStatusEnum.SEND_FAILURE.getValue(), "订单推送OP未找到对应转发配置!", null);
            return BaseResponse.responseFailure("订单推送OP未找到对应转发配置!");
        }
        TransferDefaultConfig transferDefaultConfig = JacksonUtils.readValue(transferConfig.getConfiguration(), TransferDefaultConfig.class);
        OpRequest opRequest = YmatouDtoConverter.convertOp(order, transferDefaultConfig);
        //推送订单至OP
        BaseResponse response = opApiService.sendOrder(opRequest, order.getPaymentOrderNo(), order.getCode());
        //根据推送结果更新订单状态
        OrderStatusEnum orderStatus = response.isSuccess() ? OrderStatusEnum.SEND_SUCCESS : OrderStatusEnum.SEND_FAILURE;
        updateKjbStatus(order, orderStatus.getValue(), response.getMessage(), null);
        return response;
    }

    /**
     * 赋值订单信息
     *
     * @param order
     */
    private void defineOrderYmatou(OrderYmatouDto order) {
        order.setIdCards(orderYmatouIndentityService.selectByMsgOrderId(order.getId()));
        order.setDeliveryInfo(orderYmatouDeliveryService.selectByMsgOrderId(order.getId()));
        List<OrderYmatouItem> itemList = orderYmatouItemService.selectByOrderIdAndStatus(order.getId(), YouZanOrderNewEnum.NEW.getValue());
        order.setOrderItemsInfo(itemList);
    }

    /**
     * 洋码头订单 调用ofc接口
     *
     * @param order 订单
     */
    @Override
    @Transactional
    public BaseResponse toOfc(OrderYmatouDto order) {
        defineOrderYmatou(order);
        //匹配转单配置
        TransferConfig transferConfig = findConfig(order);
        if (transferConfig == null) {
            updateKjbStatus(order, OrderStatusEnum.SEND_FAILURE.getValue(), "订单推送OFC未找到对应转发配置!", null);
            return BaseResponse.responseFailure("订单推送OFC未找到对应转发配置");
        }
        TransferDefaultConfig transferDefaultConfig = JacksonUtils.readValue(transferConfig.getConfiguration(), TransferDefaultConfig.class);
        List<OrderYmatouItem> oldItemList = orderYmatouItemService.selectByOrderIdAndStatus(order.getId(), YouZanOrderNewEnum.OLD.getValue());
        //构建请求参数
        OfcRequest ofcRequest = YmatouDtoConverter.convertOfc(order,oldItemList, transferDefaultConfig);
        //推送OFC
        BaseResponse response = ofcApiService.sendOrder(ofcRequest, order.getOrderNo(), order.getCode());
        OrderStatusEnum orderStatus = response.isSuccess() ? OrderStatusEnum.SEND_SUCCESS : OrderStatusEnum.SEND_FAILURE;
        //更新发送状态
        updateKjbStatus(order, orderStatus.getValue(), response.getMessage(), null);
        return response;
    }


    /**
     * 单票订单抓取
     *
     * @param orderId 订单号
     * @param storeId 店铺id
     * @return
     */
    @Override
    public BaseResponse grabOnlyOrder(String orderId, Long storeId) {
        BaseResponse response = new BaseResponse();
        Store store = storeService.selectById(storeId);
        if (store == null || StringUtils.isEmpty(store.getArguments())) {
            return BaseResponse.responseFailure("店铺信息有误");
        }
        //组装业务报文
        Map map = Maps.newHashMap();
        map.put("order_id", orderId);
        map.put("needs_delivery_info", "true");
        //单票抓单
        Map<String, String> reqMap = buildRequestPargs(store, JacksonUtils.toJSon(map), "ymatou.order.detail.get");
        OrderYmatouDto orderYmatouDto = ymatouApiService.oneGrab(reqMap, orderId);
        if (orderYmatouDto != null) {
            OrderTemp orderTemp = new OrderTemp(JacksonUtils.toJSon(orderYmatouDto));
            BasicMessage basicMessage = new BasicMessage(orderTemp.getId(), orderId, store.getId().toString(), "");
            orderTempService.insertAndSendMq(orderTemp, QueueConstants.QueueEnum.OCP_YMATOU_ORDER_PROCESS, basicMessage);
            response.setFlag(BaseResponse.SUCCESS);
        } else {
            response.setFlag(BaseResponse.FAILURE);
        }
        return response;
    }


    /**
     * 组装请求参数
     *
     * @param store   店铺信息
     * @param content 请求参数
     * @param method  请求方法名
     * @return
     */
    private Map<String, String> buildRequestPargs(Store store, String content, String method) {
        Map<String, String> reqMap = new HashMap<>();
        Map<String, String> storeArgs = JacksonUtils.readValue(store.getArguments(), Map.class);
        String timestamp = DateUtils.dateToString(new Date(), DateUtils.Y_M_D_HMS);
        reqMap.put("app_id", storeArgs.get("appId"));
        reqMap.put("method", method);
        reqMap.put("sign_method", storeArgs.get("sign_method"));
        reqMap.put("timestamp", timestamp);
        reqMap.put("nonce_str", MD5Utils.md5Hex(timestamp));
        reqMap.put("auth_code", storeArgs.get("auth_code"));
        reqMap.put("biz_content", content);
        reqMap.put("sign", toSign(reqMap, storeArgs.get("app_secret")));
        return reqMap;
    }

    /**
     * 加密算法
     *
     * @param map
     * @return
     */
    private String toSign(Map<String, String> map, String secret) {
        String[] keys = map.keySet().toArray(new String[0]);
        Arrays.sort(keys);
        StringBuffer reqSf = new StringBuffer();
        for (String key : keys) {
            String value = map.get(key);
            if (!"sign".equals(key) && StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
                reqSf.append(key).append("=").append(value).append("&");
            }
        }
        reqSf.append("app_secret=").append(secret);

        return MD5Utils.md5Hex(reqSf.toString()).toUpperCase();
    }


    /**
     * 获取业务参数
     *
     * @param num    页数
     * @param config 抓单配置
     * @return
     */
    private String buildBizContent(int num, CatchOrderConfig config) {
        Date date = new Date();
        YmatouGrabReq req = new YmatouGrabReq();
        req.setStart_date(DateUtils.addMinutes(date, -30));
        req.setEnd_date(date);
        req.setPage_no(num);
        req.setPage_rows(Long.valueOf(config.getPageSize()));
        return JacksonUtils.toJSon(req);
    }

    /**
     * 抓取洋码头订单
     *
     * @param catchOrderConfig 抓单配置
     */
    @Override
    public void grabOrder(CatchOrderConfig catchOrderConfig) {
        Store store = storeService.selectById(catchOrderConfig.getStoreId());
        if (store == null || StringUtils.isEmpty(store.getArguments())) {
            throw new BusinessException("店铺信息有误");
        }
        int pageNo = 1;
        do {
            //获取批量抓单请求报文
            Map<String, String> reqMap = buildRequestPargs(store, buildBizContent(pageNo, catchOrderConfig), "ymatou.order.list.get");
            //发送请求
            List<OrderYmatouDto> orderYmatouDtos = ymatouApiService.batchGrab(reqMap);
            if (CollectionUtils.isEmpty(orderYmatouDtos)) {
                break;
            }
            //发送MQ
            orderYmatouDtos.forEach(order -> {
                if (order.getOrderItemsInfo().get(0).getDeliveryType().equals(new Long(4))
                        || order.getOrderItemsInfo().get(0).getDeliveryType().equals(new Long(2))) {
                    OrderTemp orderTemp = new OrderTemp(JacksonUtils.toJSon(order));
                    BasicMessage basicMessage = new BasicMessage(orderTemp.getId(), "",
                            store.getId().toString(), "");
                    orderTempService.insertAndSendMq(orderTemp,
                            QueueConstants.QueueEnum.OCP_YMATOU_ORDER_PROCESS, basicMessage);
                }
            });
            pageNo++;

        } while (true);


    }


    /**
     * 重推订单
     *
     * @param ids
     * @return
     */
    @Override
    public BaseResponse rePush(List<Long> ids) {
        List<OrderYmatou> orderYmatous = orderYmatouService.selectByIds(ids);
        BusinessAssert.assertNotEmpty(orderYmatous, "所选订单不存在");
        for (OrderYmatou order : orderYmatous) {
            //校验订单状态是否允许重推
            BusinessAssert.assertIsTrue(OrderStatusEnum.SEND_SUCCESS == order.getSendStatus(), String.format("订单[%s]当前状态不允许重推!", order.getCode()));
            //直接重新发送MQ
            QueueConstants.QueueEnum queueEnum = QueueConstants.QueueEnum.getQueueEnumByName("ocp.ymatou.order.push." + order.getSendSystem().getQueue());
            messageSender.send(queueEnum, new BasicMessage(order.getId(), order.getCode()));
        }

        return BaseResponse.responseSuccess("重推成功");
    }

    /**
     * 订单接单接口 订单状态为2时触发。
     * 该接口调用后，再调用订单支付接口
     *
     * @param order
     * @return
     */
    @Override
    public BaseResponse takeOrder(OrderYmatouDto order, Store store) {
        //组装业务报文
        Map map = Maps.newHashMap();
        ArrayList<Object> newArrayList = Lists.newArrayList();
        newArrayList.add(order.getOrderId());
        map.put("order_ids", newArrayList);
        //订单接单
        Map<String, String> reqMap = buildRequestPargs(store, JacksonUtils.toJSon(map), "ymatou.order.accept");
        BaseResponse response = ymatouApiService.takeOrder(reqMap, order.getOrderId());
        if (!response.isSuccess()) {
            return response;
        }
        //推送订单支付MQ
        BasicMessage basicMessage = new BasicMessage(order.getId(), order.getCode(), store.getId().toString(), "");
        messageSender.send(QueueConstants.QueueEnum.OCP_YMATOU_PAY_ORDER, basicMessage);
        return response;
    }

    /**
     * 订单支付接口  状态为17时触发
     *
     * @param order
     * @return
     */
    @Override
    public BaseResponse payOrder(OrderYmatouDto order, Store store) {
        //组装业务报文
        Map map = Maps.newHashMap();
        map.put("order_id", order.getOrderId());
        map.put("customs", "OTHER");
        //订单接单
        Map<String, String> reqMap = buildRequestPargs(store, JacksonUtils.toJSon(map), "ymatou.payment.push");
        BaseResponse<OrderPayRep> response = ymatouApiService.payOrder(reqMap, order.getOrderId());
        if (!response.isSuccess()) {
            return response;
        }
        OrderPayRep.Content.Result result = response.getData().getContent().getResult();
        OrderYmatou orderYmatou = new OrderYmatou();
        orderYmatou.setId(order.getId());
        orderYmatou.setOrderNo(result.getOrder_no());
        orderYmatou.setPayTransactionId(result.getPay_transaction_id());
        orderYmatouService.updateById(orderYmatou);
        //推送至组套MQ
        BasicMessage basicMessage = new BasicMessage(order.getId(), order.getCode());
        messageSender.send(QueueConstants.QueueEnum.OCP_YMATOU_ORDER_PUSH_KJB, basicMessage);
        return response;
    }
}

package com.topideal.supplychain.ocp.daling.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.exception.BusinessException;
import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.mq.MessageSender;
import com.topideal.supplychain.ocp.config.dto.CatchDefaultConfig;
import com.topideal.supplychain.ocp.config.dto.TransferDefaultConfig;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.config.model.TransferConfig;
import com.topideal.supplychain.ocp.config.service.CatchOrderConfigService;
import com.topideal.supplychain.ocp.config.service.TransferConfigService;
import com.topideal.supplychain.ocp.constants.CodeRuleConstants;
import com.topideal.supplychain.ocp.constants.PunctuateConstant;
import com.topideal.supplychain.ocp.daling.converter.DalingDtoConverter;
import com.topideal.supplychain.ocp.daling.dto.DalingArgsDto;
import com.topideal.supplychain.ocp.daling.dto.DalingDataInfo;
import com.topideal.supplychain.ocp.daling.dto.DalingGrabInfoData;
import com.topideal.supplychain.ocp.daling.dto.DalingInfoResponseDto;
import com.topideal.supplychain.ocp.daling.dto.DalingOrderDataInfo;
import com.topideal.supplychain.ocp.daling.service.DaLingApiService;
import com.topideal.supplychain.ocp.daling.service.DalingService;
import com.topideal.supplychain.ocp.enums.BusiModeEnum;
import com.topideal.supplychain.ocp.enums.CustomsCodeEnum;
import com.topideal.supplychain.ocp.enums.DaLingOrderStatusEnum;
import com.topideal.supplychain.ocp.enums.ForwardSystemEnum;
import com.topideal.supplychain.ocp.enums.OrderStatusEnum;
import com.topideal.supplychain.ocp.mq.QueueConstants;
import com.topideal.supplychain.ocp.mq.QueueConstants.QueueEnum;
import com.topideal.supplychain.ocp.ofc.dto.OfcRequest;
import com.topideal.supplychain.ocp.ofc.service.OfcApiService;
import com.topideal.supplychain.ocp.op.dto.OpRequest;
import com.topideal.supplychain.ocp.op.service.OpApiService;
import com.topideal.supplychain.ocp.order.dto.OrderDalingDto;
import com.topideal.supplychain.ocp.order.model.OrderDaling;
import com.topideal.supplychain.ocp.order.model.OrderDetailDaling;
import com.topideal.supplychain.ocp.order.model.OrderTemp;
import com.topideal.supplychain.ocp.order.service.OrderDalingService;
import com.topideal.supplychain.ocp.order.service.OrderTempService;
import com.topideal.supplychain.ocp.service.SystemConfigService;
import com.topideal.supplychain.util.DateUtils;
import com.topideal.supplychain.util.JacksonUtils;
import com.topideal.supplychain.util.MD5Utils;
import com.topideal.supplychain.web.config.service.InterfaceUrlService;
import com.topideal.supplychain.web.system.service.impl.CodeGeneratorService;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 达令家
 *
 * @author xuxiaoyan
 * @date 2019-12-11 10:14
 */
@Service
public class DalingServiceImpl implements DalingService {

    @Autowired
    private OrderDalingService orderDalingService;
    @Autowired
    private MessageSender messageSender;
    @Autowired
    private TransferConfigService transferConfigService;
    @Autowired
    private OpApiService opApiService;
    @Autowired
    private CatchOrderConfigService catchOrderConfigService;
    @Autowired
    private InterfaceUrlService interfaceUrlService;
    @Autowired
    private DaLingApiService daLingApiService;
    @Autowired
    private CodeGeneratorService codeGeneratorService;
    @Autowired
    private SystemConfigService systemConfigService;
    @Autowired
    private OfcApiService ofcApiService;
    @Autowired
    private OrderTempService orderTempService;

    /**
     * 重推订单:
     * 1.支持批量
     * 2.如果状态不对的订单跳过并记录单号，状态正确的正常下发
     *
     * @param ids
     */
    @Override
    @Transactional
    public String rePush(Long[] ids) {
        StringBuilder msg = new StringBuilder();
        List<OrderDaling> orderDalingList = orderDalingService.selectByIds(ids);
        orderDalingList.forEach(orderDaling -> {
            //只允许重推下发成功的订单
            if (!OrderStatusEnum.SEND_SUCCESS.equals(orderDaling.getOrderStatus())) {
                msg.append(orderDaling.getOrderNo()).append(PunctuateConstant.COMMA);
                return;
            }
            // 发送队列
            QueueConstants.QueueEnum queueEnum = QueueConstants.QueueEnum.getQueueEnumByName("ocp.daling.order.push." + orderDaling.getSendSystem().getQueue());
            messageSender.send(queueEnum,
                    new BasicMessage(orderDaling.getId(), orderDaling.getCode()));
        });
        return StringUtils.isEmpty(msg) ? msg.toString()
                : msg.append("订单状态不为下发成功!不允许重推!").toString();
    }

    /**
     * 达令订单推OP
     *
     * @param orderDaling
     * @return
     */
    @Override
    @Transactional
    public BaseResponse sendOrderOp(OrderDaling orderDaling) {
        // 转单配置
        TransferConfig transferConfig = transferConfigService.findByUnique(orderDaling.getElectricCode(), orderDaling.getCbePcomCode(), orderDaling.getTpl(), orderDaling.getCustomsCode() == null ? "" : orderDaling.getCustomsCode().getValue(), orderDaling.getBusiMode().getValue());
        if (null == transferConfig) {
            return BaseResponse.responseFailure("订单推送OP未找到对应转发配置");
        }
        // 订单明细
        OrderDetailDaling orderDetailDaling = JacksonUtils
                .readValue(orderDaling.getInfoData(), OrderDetailDaling.class);
        orderDaling.setOrderDetail(orderDetailDaling);
        // 解析转单参数
        //TransferConfig.Configuration configuration = TransferConfig.getConfig(transferConfig.getConfiguration());
        if (transferConfig == null || StringUtils.isBlank(transferConfig.getConfiguration())) {
            return BaseResponse.responseFailure("订单推送OP未找到对应转发配置");
        }
        TransferDefaultConfig transferDefaultConfig = JacksonUtils.readValue(transferConfig.getConfiguration(), TransferDefaultConfig.class);
        //转换请求报文,同时按照转单参数对字段进行转换
        OpRequest req;
        try {
            req = DalingDtoConverter.convertOp(orderDaling, transferDefaultConfig);
        } catch (ParseException e) {
            return BaseResponse.responseFailure("字段类型异常！");
        }
        BaseResponse response = opApiService.sendOrder(req, orderDaling.getOrderNo(), orderDaling.getCode());
        OrderStatusEnum orderStatus = OrderStatusEnum.SEND_FAILURE;
        if (response.isSuccess()) {
            orderStatus = OrderStatusEnum.SEND_SUCCESS;
        }
        orderDalingService
                .updateOrderStatus(orderDaling.getId(), orderStatus.getValue(), response.getMessage());
        return response;
    }

    /**
     * 抓取详情订单
     *
     * @param orderDaling
     * @return
     */
    @Override
    @Transactional
    public void grabDetail(OrderDaling orderDaling) {
        //根据抓单配置id查询配置
        CatchOrderConfig config = catchOrderConfigService.selectEnableById(Long.valueOf(orderDaling.getTaskId()));
        if (null == config) {
            throw new BusinessException("达令家订单抓取配置为空！");
        }
        DalingArgsDto dalingArgsDto = JacksonUtils
                .readValue(config.getPlatformArguments(), DalingArgsDto.class);
        boolean flag = (null == dalingArgsDto) || StringUtils.isEmpty(dalingArgsDto.getAppKey())
                || StringUtils.isEmpty(dalingArgsDto.getSecret());
        if (flag) {
            throw new BusinessException("获取达令家订单抓取配置或appKey或secret失败!");
        }
        // 接口地址
        String url = interfaceUrlService.getUrl("daling.get.order.single");
        if (StringUtils.isEmpty(url)) {
            throw new BusinessException("获取达令家订单接口地址失败!");
        }
        // 请求报文map
        String req = buildGrabReq(dalingArgsDto, orderDaling.getOrderNo());
        BaseResponse response = daLingApiService.grabInfo(req, url, orderDaling);
        if (response.isSuccess()) {
            DalingDataInfo t = ((DalingInfoResponseDto) response.getData()).getData().getT();
            TransferConfig transferConfig = transferConfigService.findByUnique(orderDaling.getElectricCode(), orderDaling.getCbePcomCode(), orderDaling.getTpl(), orderDaling.getCustomsCode() == null ? "" : orderDaling.getCustomsCode().getValue(), orderDaling.getBusiMode().getValue());
            BusinessAssert.assertNotNull(transferConfig,"没有找到对应的转单配置");
            ForwardSystemEnum forwardSystem = transferConfig.getForwardSystem();
            //将订单详细信息更新至数据库
            orderDalingService.updateInfoData(orderDaling.getId(), OrderStatusEnum.GRAB_INFO, JacksonUtils.toJSon(t),
                    t.getLogisticsNumber(),forwardSystem.getValue());
            QueueConstants.QueueEnum queueEnum = QueueConstants.QueueEnum.getQueueEnumByName("ocp.daling.order.push." + forwardSystem.getQueue());
            messageSender.send(queueEnum,
                    new BasicMessage(orderDaling.getId(), orderDaling.getCode()));
        } else {
            throw new BusinessException(response.getMessage());
        }
    }

    /**
     * 组装达令家抓单明细信息请求报文
     *
     * @param dalingArgsDto
     * @return
     */
    private String buildGrabReq(DalingArgsDto dalingArgsDto, String orderNo) {
        DalingGrabInfoData data = new DalingGrabInfoData();
        data.setOrderNo(orderNo);
        JSONObject jsonObject = JSONObject.parseObject(JacksonUtils.toJSon(data));
        return getDefaultRequest(dalingArgsDto,dalingArgsDto.getSingleMethod(),jsonObject);
    }

    /**
     * 加签   // 加签 treeMap默认升序 拼接参数名跟参数值
     *
     * @param req
     * @param secret
     * @return
     */
    private String getSign(TreeMap<String, Object> req, String secret) {
        // 密钥加于首尾
        StringBuilder sb = new StringBuilder(secret);
        for (Map.Entry<String, Object> entry : req.entrySet()) {
            sb.append(entry.getKey()).append(entry.getValue());
        }
        String params = sb.append(secret).toString();
        // MD5加密 小写
        return MD5Utils.md5Hex(params).toLowerCase();
    }

    @Override
    public void getMissOrder(CatchOrderConfig catchOrderConfig) {
        getOrder(catchOrderConfig,true);
    }

    @Override
    @Transactional
    public void getOrder(CatchOrderConfig catchOrderConfig, boolean isMiss) {
        //默认抓单配置
        DalingArgsDto dalingArgsDto = JacksonUtils.readValue(catchOrderConfig.getPlatformArguments(), DalingArgsDto.class);
        boolean flag = (null == dalingArgsDto) || StringUtils.isEmpty(dalingArgsDto.getAppKey())
                || StringUtils.isEmpty(dalingArgsDto.getSecret());
        if (flag) {
            throw new BusinessException("获取达令家订单抓取配置或appKey或secret失败!");
        }
        CatchDefaultConfig catchDefaultConfig = JacksonUtils.readValue(catchOrderConfig.getDefaultArguments(), CatchDefaultConfig.class);
        if (catchDefaultConfig == null) {
            throw new BusinessException("达令家接单配置默认值配置无效");
        }
        // 接口地址
        String url = interfaceUrlService.getUrl("daling.get.order.multiple");
        if (StringUtils.isEmpty(url)) {
            throw new BusinessException("获取达令家订单接口地址失败!");
        }
        //获取请求报文
        String request = getOrderBasicDataRequest(catchOrderConfig, dalingArgsDto,isMiss);
        //调接口抓取订单
        BaseResponse baseResponse = daLingApiService.grabOrder(request, url);
        if (baseResponse.isSuccess()) {
            //将抓取订单存数据库
            List<DalingOrderDataInfo> dalingDataInfos = (List<DalingOrderDataInfo>) baseResponse.getData();
            //此处应该需要过滤已重复的订单
            List<String> orderNoList = new ArrayList<>();
            for (DalingOrderDataInfo dalingOrderDataInfo :dalingDataInfos) {
                orderNoList.add(dalingOrderDataInfo.getOrderNo());
            }
            List<String> existOrderNoList = orderDalingService.selectExistOrderNo(orderNoList);

            //转换为daling实体
            for (DalingOrderDataInfo dalingOrderDataInfo : dalingDataInfos) {
                if(existOrderNoList.contains(dalingOrderDataInfo.getOrderNo())){
                    continue;
                }
                // 保存临时表处理
                OrderDalingDto orderDalingDto = new OrderDalingDto();
                orderDalingDto.setCbePcomCode(catchOrderConfig.getPlatformCode());
                orderDalingDto.setElectricCode(catchOrderConfig.getMerchantCode());
                orderDalingDto.setTpl(catchDefaultConfig.getTpl());
                orderDalingDto.setCustomsCodeStr(catchDefaultConfig.getCustomsCode());
                orderDalingDto.setCiqbCode(catchDefaultConfig.getCiqCode());
                orderDalingDto.setBusiModeStr(catchDefaultConfig.getBusiMode());
                orderDalingDto.setOrderNo(dalingOrderDataInfo.getOrderNo());
                orderDalingDto.setLogisticsNumber(dalingOrderDataInfo.getLogisticsNumber());
                orderDalingDto.setCreatedDate(DateUtils.formatStringToDate(dalingOrderDataInfo.getCreatedDate(), DateUtils.DATETIME_PATTERN));
                orderDalingDto.setStatusStr(dalingOrderDataInfo.getStatus());
                orderDalingDto.setTaskId(String.valueOf(catchOrderConfig.getId()));

                OrderTemp orderTemp = new OrderTemp(JacksonUtils.toJSon(orderDalingDto));
                //插入临时表数据，并发送处理的mq
                orderTempService.insertAndSendMq(orderTemp, QueueEnum.OCP_DALING_ORDER_PROCESS,
                        new BasicMessage(orderTemp.getId(), dalingOrderDataInfo.getOrderNo()));

            }
        }
    }

    private void sendInfoMqMessage(OrderDaling order) {
        messageSender.send(QueueConstants.QueueEnum.OCP_DALING_ORDER_INFO_GRAB,
                new BasicMessage(order.getId(), order.getCode()));
    }

    private String getOrderBasicDataRequest(CatchOrderConfig catchOrderConfig, DalingArgsDto dalingArgsDto, boolean isMiss) {
        JSONObject data = new JSONObject();
        data.put("create_start_time", DateUtils.dateToString(DateUtils.addMinute(DateUtils.getNowTime(), -30), DateUtils.DATETIME_PATTERN));//达令家订单系统内创建时间开始
        if (isMiss) {
            int intervalTime = systemConfigService.getIntervalTimeForMiss() * -1;
            data.put("create_start_time", DateUtils.dateToString(DateUtils.addMinutes(DateUtils.addDays(DateUtils.getNowTime(), intervalTime), -30), DateUtils.DATETIME_PATTERN));//达令家订单系统内创建时间开始
        }
        data.put("create_end_time", DateUtils.dateToString(DateUtils.getNowTime(), DateUtils.DATETIME_PATTERN));//达令家订单系统内创建时间结束
        data.put("page_size", catchOrderConfig.getPageSize().toString());
        data.put("page_no", "1");
        return getDefaultRequest(dalingArgsDto,dalingArgsDto.getMultipleMethod(),data);
    }

    //获取默认的请求参数
    private String getDefaultRequest(DalingArgsDto dalingArgsDto,String method,Object data) {
        TreeMap<String, Object> request = new TreeMap<>();
        request.put("v", dalingArgsDto.getVersion());
        request.put("format", dalingArgsDto.getFormat());
        request.put("sign_method", dalingArgsDto.getSignMethod());
        request.put("app_id", dalingArgsDto.getAppKey());
        request.put("timestamp", DateUtils.dateToString(new Date(), DateUtils.DATETIME_PATTERN));
        request.put("method", method);
        request.put("data", data);
        String sign = getSign(request, dalingArgsDto.getSecret());
        request.put("sign", sign);
        return JacksonUtils.toJSon(request);
    }


    /**
     * 推订单到OFC
     *
     * @param orderDaling
     * @return
     */
    @Override
    @Transactional
    public BaseResponse sendOrderOfc(OrderDaling orderDaling) {
        // 转单配置
        TransferConfig transferConfig = transferConfigService.findByUnique(orderDaling.getElectricCode(), orderDaling.getCbePcomCode(), orderDaling.getTpl(), orderDaling.getCustomsCode() == null ? "" : orderDaling.getCustomsCode().getValue(), orderDaling.getBusiMode().getValue());
        if (null == transferConfig) {
            orderDalingService.updateOrderStatus(orderDaling.getId(), OrderStatusEnum.SEND_FAILURE.getValue(), "订单推送OFC未找到对应转发配置");
            return BaseResponse.responseFailure("订单推送OFC未找到对应转发配置");
        }
        // 订单明细
        OrderDetailDaling orderDetailDaling = JacksonUtils.readValue(orderDaling.getInfoData(), OrderDetailDaling.class);
        orderDaling.setOrderDetail(orderDetailDaling);

        TransferDefaultConfig transferDefaultConfig = JacksonUtils.readValue(transferConfig.getConfiguration(), TransferDefaultConfig.class);
        //转换请求报文,同时按照转单参数对字段进行转换
        OfcRequest req;
        try {
            req = DalingDtoConverter.convertOfc(orderDaling, transferDefaultConfig);
        } catch (ParseException e) {
            return BaseResponse.responseFailure("字段类型异常！");
        }
        BaseResponse response = ofcApiService.sendOrder(req, orderDaling.getOrderNo(), orderDaling.getCode());
        OrderStatusEnum orderStatus = OrderStatusEnum.SEND_FAILURE;
        if (response.isSuccess()) {
            orderStatus = OrderStatusEnum.SEND_SUCCESS;
        }
        orderDalingService
                .updateOrderStatus(orderDaling.getId(), orderStatus.getValue(), response.getMessage());
        return response;
    }

    /**
     * 解析临时表；保存订单；发送抓取详情mq
     * @param orderTemp
     */
    @Override
    @Transactional
    public void processOrder(OrderTemp orderTemp) {
        OrderDalingDto dto = JacksonUtils.readValue(orderTemp.getJson(), OrderDalingDto.class);
        OrderDaling orderDaling = new OrderDaling();
        BeanUtils.copyProperties(dto, orderDaling);
        orderDaling.setCustomsCode(CustomsCodeEnum.getValueEnum(dto.getCustomsCodeStr()));
        orderDaling.setBusiMode(BusiModeEnum.getValueEnum(dto.getBusiModeStr()));
        orderDaling.setStatus(DaLingOrderStatusEnum.getByValue(dto.getStatusStr()));
        orderDaling.setOrderStatus(OrderStatusEnum.INIT);
        orderDaling.setCode(codeGeneratorService.generate(CodeRuleConstants.RULR_DL));

        // 插入订单
        orderDalingService.insert(orderDaling);

        // 抓取详情
        sendInfoMqMessage(orderDaling);

        // 保存成功删除临时表数据
        orderTempService.deleteById(orderTemp.getId());
    }
}

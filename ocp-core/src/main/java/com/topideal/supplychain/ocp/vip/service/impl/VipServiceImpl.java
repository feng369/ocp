package com.topideal.supplychain.ocp.vip.service.impl;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.mq.MessageSender;
import com.topideal.supplychain.ocp.config.dto.CatchDefaultConfig;
import com.topideal.supplychain.ocp.config.dto.TransferDefaultConfig;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.config.model.TransferConfig;
import com.topideal.supplychain.ocp.config.service.TransferConfigService;
import com.topideal.supplychain.ocp.constants.PunctuateConstant;
import com.topideal.supplychain.ocp.enums.BusiModeEnum;
import com.topideal.supplychain.ocp.enums.ForwardSystemEnum;
import com.topideal.supplychain.ocp.enums.OrderStatusEnum;
import com.topideal.supplychain.ocp.esd.dto.EsdRequest;
import com.topideal.supplychain.ocp.esd.service.EsdApiService;
import com.topideal.supplychain.ocp.master.model.Store;
import com.topideal.supplychain.ocp.master.service.StoreService;
import com.topideal.supplychain.ocp.mq.QueueConstants;
import com.topideal.supplychain.ocp.ofc.dto.OfcRequest;
import com.topideal.supplychain.ocp.ofc.service.OfcApiService;
import com.topideal.supplychain.ocp.order.dto.OrderVipDto;
import com.topideal.supplychain.ocp.order.model.OrderTemp;
import com.topideal.supplychain.ocp.order.model.OrderVip;
import com.topideal.supplychain.ocp.order.model.OrderVipItem;
import com.topideal.supplychain.ocp.order.service.OrderTempService;
import com.topideal.supplychain.ocp.order.service.OrderVipItemService;
import com.topideal.supplychain.ocp.order.service.OrderVipService;
import com.topideal.supplychain.ocp.vip.converter.VipDtoConverter;
import com.topideal.supplychain.ocp.vip.dto.VipRequestArgsVo;
import com.topideal.supplychain.ocp.vip.service.VipApiService;
import com.topideal.supplychain.ocp.vip.service.VipService;
import com.topideal.supplychain.util.JacksonUtils;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>标题: </p>
 * <p>模块: ocp-parent</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.vip.service.impl</p>
 * <p>作者: LeoZhang</p>
 * <p>创建日期: 2019/11/27 18:20</p>
 *
 * @version 1.0
 */
@Service
public class VipServiceImpl implements VipService {

    @Autowired
    private OrderVipService orderVipService;
    @Autowired
    private MessageSender messageSender;
    @Autowired
    private OrderVipItemService orderVipItemService;
    @Autowired
    private VipApiService vipApiService;
    @Autowired
    private TransferConfigService transferConfigService;
    @Autowired
    private OfcApiService ofcApiService;
    @Autowired
    private EsdApiService esdApiService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private OrderTempService orderTempService;

    /**
     * 订单重推,如果状态不对的订单跳过并记录单号，状态正确的正常下发
     *
     * @param ids
     * @return
     */
    @Override
    @Transactional
    public String rePush(List<Long> ids) {
        StringBuilder msg = new StringBuilder();
        List<OrderVip> orderVips = orderVipService.selectByIds(ids);
        orderVips.forEach(orderVip -> {
            //下发成功的订单才允许重新推送
            if (!OrderStatusEnum.SEND_SUCCESS.equals(orderVip.getSendFlag())) {
                msg.append(orderVip.getOrderNo()).append(PunctuateConstant.COMMA);
                return;
            }
            //直接重新发送MQ
            messageSender.send(BusiModeEnum.BBC.equals(orderVip.getTradeMode()) ? QueueConstants.QueueEnum.OCP_VIP_ORDER_PUSH_OFC :
                    QueueConstants.QueueEnum.OCP_VIP_ORDER_PUSH_ESD, new BasicMessage(orderVip.getId(), orderVip.getOrderNo()));
        });
        return StringUtils.isEmpty(msg) ? msg.toString() : msg.append("订单当前状态不允许重推！").toString();
    }

    /**
     * 订单推送唯品会
     * 1.解析转单配置
     * 2.下发ofc
     * 3.更新订单下发状态
     *
     * @param orderVip
     */
    @Override
    @Transactional
    public BaseResponse pushOrderToOfc(OrderVip orderVip) {
        List<OrderVipItem> goods = orderVipItemService.selectByOrderId(orderVip.getId());
        orderVip.setMpOrderGoodsList(goods);
        TransferConfig transferConfig = transferConfigService.findByUnique(orderVip.getEbcCode(), orderVip.getEbpCode(), orderVip.getLogisticsCode(), orderVip.getCustomsCode() == null ? "" : orderVip.getCustomsCode().getValue(), orderVip.getTradeMode().getValue());
        BusinessAssert.assertNotNull(transferConfig, "转单参数未配置");
        //解析转单参数
        if (transferConfig == null || StringUtils.isBlank(transferConfig.getConfiguration())) {
            return BaseResponse.responseFailure("订单推送OFC未找到对应转发配置");
        }
        TransferDefaultConfig transferDefaultConfig = JacksonUtils
                .readValue(transferConfig.getConfiguration(), TransferDefaultConfig.class);
        //转换请求报文,同时按照转单参数对字段进行转换
        OfcRequest req = VipDtoConverter.convertOfc(orderVip, transferDefaultConfig);
        OrderStatusEnum orderStatusEnum = OrderStatusEnum.SEND_FAILURE;
        //下发接口,如果有异常更新状态为下发失败
        BaseResponse response = ofcApiService.sendOrder(req, orderVip.getOrderNo(), orderVip.getCode());
        if (response.isSuccess()) {
            orderStatusEnum = OrderStatusEnum.SEND_SUCCESS;
        }
        //更新订单状态,如果是业务异常则更改为下发失败，如果其他异常不更新为下发失败
        OrderVip updateEntity = new OrderVip();
        updateEntity.setId(orderVip.getId());
        updateEntity.setSendFlag(orderStatusEnum);
        orderVipService.update(updateEntity);
        return response;
    }

    /**
     * 唯品会抓单
     * 1.抓取订单
     * 2.保存订单
     * 3.订单抓单结果回抛
     *
     * @param catchOrderConfig
     * @param vipRequestArgsVo
     * @param args
     */
    @Override
    @Transactional
    public void getOrder(CatchOrderConfig catchOrderConfig, VipRequestArgsVo vipRequestArgsVo, VipRequestArgsVo.Args args) {
        //通过配置，调用抓单接口，记录调用日志获取返回值
        List<OrderVip> orderVips = vipApiService.grabOrders(catchOrderConfig, vipRequestArgsVo, args);
        //如果抓取订单为空则开始下一次抓单循环
        if (CollectionUtils.isEmpty(orderVips)) {
            return;
        }
        // 将订单存入临时表 并发送订单处理的mq
        orderVips.forEach(orderVip -> {
            OrderVipDto orderVipDto = new OrderVipDto();
            BeanUtils.copyProperties(orderVip, orderVipDto);
            orderVipDto.setCustomsCodeStr(orderVip.getCustomsCode().getValue());
            orderVipDto.setTradeModeStr(orderVip.getVipTradeMode().getVpCode());
            OrderTemp orderTemp = new OrderTemp(JacksonUtils.toJSon(orderVipDto));
            //插入临时表数据，并发送处理的mq
            orderTempService.insertAndSendMq(orderTemp, QueueConstants.QueueEnum.OCP_VIP_ORDER_PROCESS,
                    new BasicMessage(orderTemp.getId(), orderVip.getOrderNo(), catchOrderConfig.getId().toString(), ""));
        });


//        saveOrder(orderVips, catchOrderConfig);
//        //如果保存成功返回唯品通知接口
//        boolean success = vipApiService.feedBack(orderVips, catchOrderConfig, vipRequestArgsVo, args);
//        //按照回抛状态更新订单状态
//        OrderVip updateEntity = new OrderVip();
//        updateEntity.setIds(orderVips.stream().map(OrderVip::getId).collect(Collectors.toList()));
//        updateEntity.setSendFlag(success ? OrderStatusEnum.BACK_SUCCESS : OrderStatusEnum.BACK_FAILURE);
//        orderVipService.updateByIds(updateEntity);
    }

    /**
     * 唯品会订单下发ESD
     *
     * @param orderVip
     * @return
     * @ TODO
     */
    @Override
    @Transactional
    public BaseResponse<T> pushOrderToEsd(OrderVip orderVip) {
        List<OrderVipItem> goods = orderVipItemService.selectByOrderId(orderVip.getId());
        orderVip.setMpOrderGoodsList(goods);
        TransferConfig transferConfig = transferConfigService.findByUnique(orderVip.getEbcCode(), orderVip.getEbpCode(), orderVip.getLogisticsCode(), orderVip.getCustomsCode() == null ? "" : orderVip.getCustomsCode().getValue(), orderVip.getTradeMode().getValue());
        BusinessAssert.assertIsFalse(transferConfig == null || StringUtils.isEmpty(transferConfig.getConfiguration()), "转单参数未配置");
        //解析转单参数
        TransferDefaultConfig transferDefaultConfig = JacksonUtils.readValue(transferConfig.getConfiguration(), TransferDefaultConfig.class);
        //转换请求报文,同时按照转单参数对字段进行转换
        EsdRequest req = VipDtoConverter.convertEsd(orderVip, transferDefaultConfig);
        //查询店铺信息
        Store store = storeService.selectEnableByCode(orderVip.getStoreId());
        BusinessAssert.assertNotNull(store, "店铺信息为空");
        //下发接口,如果有异常更新状态为下发失败
        BaseResponse<T> response = esdApiService.sendOrder(req, orderVip.getOrderNo(), orderVip.getCode(), store);
        //更新订单状态,如果是业务异常则更改为下发失败，如果其他异常不更新为下发失败
        OrderVip updateEntity = new OrderVip();
        updateEntity.setId(orderVip.getId());
        updateEntity.setSendFlag(response.isSuccess() ? OrderStatusEnum.SEND_SUCCESS : OrderStatusEnum.SEND_FAILURE);
        orderVipService.update(updateEntity);
        return response;
    }

    /**
     * 抓单订单处理
     * @param orderTemp
     * @param config
     */
    @Override
    @Transactional
    public void processOrder(OrderTemp orderTemp, CatchOrderConfig config) {
        OrderVipDto orderVipDto = JacksonUtils.readValue(orderTemp.getJson(), OrderVipDto.class);
        OrderVip orderVip = new OrderVip();
        BeanUtils.copyProperties(orderVipDto, orderVip);
        // 删除临时表数据
        orderTempService.deleteById(orderTemp.getId());
        // 保存订单
        saveOrder(orderVip, config, orderVipDto);
        //解析转单配置并更新订单信息
        TransferConfig transferConfig = transferConfigService
                .findByUnique(orderVip.getEbcCode(), orderVip.getEbpCode(),
                        orderVip.getLogisticsCode(), orderVip.getCustomsCode() == null ? ""
                                : orderVip.getCustomsCode().getValue(),
                        orderVip.getTradeMode() == null ? "" : orderVip.getTradeMode().getValue());
        BusinessAssert.assertNotNull(transferConfig, "转单配置解析失败");

        //解析唯品平台参数
        VipRequestArgsVo vipRequestArgsVo = JacksonUtils.readValue(config.getPlatformArguments(), VipRequestArgsVo.class);
        List<OrderVip> orderVips = new ArrayList<>();
        orderVips.add(orderVip);
        boolean success = vipApiService.feedBack(orderVips, vipRequestArgsVo);

        //更新下发系统
        OrderVip updateEntity = new OrderVip();
        updateEntity.setId(orderVip.getId());
        updateEntity.setSendSystem(transferConfig.getForwardSystem());
        //按照回抛状态更新订单状态
        updateEntity.setSendFlag(success ? OrderStatusEnum.BACK_SUCCESS : OrderStatusEnum.BACK_FAILURE);
        orderVipService.update(updateEntity);
        //按照下发系统转发，唯品目前只有OFC和ESD
        messageSender.send(ForwardSystemEnum.OFC.equals(transferConfig.getForwardSystem())
                        ? QueueConstants.QueueEnum.OCP_VIP_ORDER_PUSH_OFC
                        : QueueConstants.QueueEnum.OCP_VIP_ORDER_PUSH_ESD,
                new BasicMessage(orderVip.getId(), orderVip.getCode()));
    }


    /**
     * 保存订单
     */
    private void saveOrder(OrderVip orderVip, CatchOrderConfig catchOrderConfig, OrderVipDto orderVipDto) {
        orderVip.setCustomsCode(orderVipDto.getCustomsCodeStr());
        orderVip.setTradeMode(orderVipDto.getTradeModeStr());
        //初始化订单信息
        BusiModeEnum busiModeEnum = orderVip.getVipTradeMode();
        if (busiModeEnum != null) {
            orderVip.setTradeMode(busiModeEnum.getValue());
        }
        orderVip.setSendFlag(OrderStatusEnum.INIT);
        orderVip.setReceiptTime(orderVip.getUpdateTime());
        orderVip.setEbpCode(catchOrderConfig.getPlatformCode());
        orderVip.setEbcCode(catchOrderConfig.getMerchantCode());
        if (StringUtils.isEmpty(catchOrderConfig.getDefaultArguments())) {
            return;
        }
        CatchDefaultConfig defaultConfig = JacksonUtils
                .readValue(catchOrderConfig.getDefaultArguments(), CatchDefaultConfig.class);
        orderVip.setLogisticsCode(defaultConfig.getLogisticsCode());
        //保存订单和商品信息
        orderVipService.insert(orderVip);
        orderVipItemService.batchInsert(orderVip.getId(), orderVip.getMpOrderGoodsList());

    }


}

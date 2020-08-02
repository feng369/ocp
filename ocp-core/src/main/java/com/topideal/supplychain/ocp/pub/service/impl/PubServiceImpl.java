package com.topideal.supplychain.ocp.pub.service.impl;

import com.google.common.collect.Lists;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.mq.MessageSender;
import com.topideal.supplychain.ocp.constants.PunctuateConstant;
import com.topideal.supplychain.ocp.enums.*;
import com.topideal.supplychain.ocp.esd.dto.EsdRequest;
import com.topideal.supplychain.ocp.esd.service.EsdApiService;
import com.topideal.supplychain.ocp.master.model.Store;
import com.topideal.supplychain.ocp.master.service.StoreService;
import com.topideal.supplychain.ocp.mq.QueueConstants;
import com.topideal.supplychain.ocp.order.model.OrderPubBox;
import com.topideal.supplychain.ocp.order.model.OrderPubItem;
import com.topideal.supplychain.ocp.order.model.OrderPub;
import com.topideal.supplychain.ocp.order.service.OrderPubBoxService;
import com.topideal.supplychain.ocp.order.service.OrderPubItemService;
import com.topideal.supplychain.ocp.order.service.OrderPubService;
import com.topideal.supplychain.ocp.pub.converter.PubDtoConverter;
import com.topideal.supplychain.ocp.pub.dto.OrderContent;
import com.topideal.supplychain.ocp.pub.dto.OrderContent.Box;
import com.topideal.supplychain.ocp.pub.service.PubService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>标题: </p>
 * <p>模块: ocp-parent</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.pub.service.impl</p>
 * <p>作者: LeoZhang</p>
 * <p>创建日期: 2019/12/2 17:33</p>
 *
 * @version 1.0
 */
@Service
public class PubServiceImpl implements PubService {

    @Autowired
    private OrderPubService orderPubService;
    @Autowired
    private OrderPubItemService orderPubItemService;
    @Autowired
    private OrderPubBoxService orderPubBoxService;
    @Autowired
    private MessageSender messageSender;
    @Autowired
    private StoreService storeService;
    @Autowired
    private EsdApiService esdApiService;


    /**
     * 保存标准平台的订单
     *
     * @param orderReq
     */
    @Override
    @Transactional
    public void saveOrder(OrderContent orderReq) {
        OrderPub orderPub = new OrderPub();
        //保存订单和商品信息
        BeanUtils.copyProperties(orderReq, orderPub);
        if (StringUtils.isEmpty(orderPub.getIeFlag())) {
            orderPub.setIeFlag(IeFlagEnum.I.getValue());
        }
        //保存发货人信息
        BeanUtils.copyProperties(orderReq.getSender(), orderPub);
        orderPub.setSenderStreet(orderReq.getSender().getStreet());
        //保存收货人
        BeanUtils.copyProperties(orderReq.getReceiver(), orderPub);
        orderPub.setReceiverEmail(orderReq.getReceiver().getEmail());
        orderPub.setReceiverHouseNo(orderReq.getReceiver().getHouseno());
        orderPub.setReceiverStreet(orderReq.getReceiver().getStreet());
        orderPub.setReceiverProvinceCode(orderReq.getReceiver().getProvinceCode());
        if (StringUtils.isEmpty(orderReq.getReceiver().getIdentityType())) {
            orderPub.setIdentityType("1");
        }
        //保存购买人,购买人未校验必填
        if (orderReq.getBuyer() != null) {
            BeanUtils.copyProperties(orderReq.getBuyer(), orderPub);
        }
        //保存服务类型
        List<OrderContent.ServiceType> servicesList = orderReq.getServiceList();
        orderPub.setServiceType(CollectionUtils.isNotEmpty(servicesList) ? servicesList.stream()
                .filter(serviceType -> StringUtils.isNotEmpty(serviceType.getServiceType())).map(OrderContent.ServiceType::getServiceType)
                .collect(Collectors.joining(PunctuateConstant.COMMA)) : null);
        if(CollectionUtils.isNotEmpty(servicesList)) {
            BeanUtils.copyProperties(orderReq.getServiceList(), orderPub);
        }
        orderPub.setStatus(SyncStatusEnum.NOT_SYNC);
        //接单是g，数据库传kg
        orderPub.setGrossWeight(orderPub.getGrossWeight() == null ? null : orderPub.getGrossWeight().divide(BigDecimal.valueOf(1000), 5, BigDecimal.ROUND_HALF_UP));
        orderPub.setNetWeight(orderPub.getNetWeight() == null ? null : orderPub.getNetWeight().divide(BigDecimal.valueOf(1000), 5, BigDecimal.ROUND_HALF_UP));
        List<OrderPubItem> goodsList = Lists.newArrayList();
        orderReq.getItemList().forEach(good -> {
            OrderPubItem orderPubItem = new OrderPubItem();
            BeanUtils.copyProperties(good, orderPubItem);
            //接单是g，数据库传kg
            orderPubItem.setGrossWeight(orderPubItem.getGrossWeight() == null ? null : orderPubItem.getGrossWeight().divide(BigDecimal.valueOf(1000), 5, BigDecimal.ROUND_HALF_UP));
            orderPubItem.setNetWeight(orderPubItem.getNetWeight() == null ? null : orderPubItem.getNetWeight().divide(BigDecimal.valueOf(1000), 5, BigDecimal.ROUND_HALF_UP));
            goodsList.add(orderPubItem);
        });



        orderPubService.insert(orderPub);
        orderPubItemService.batchInsert(orderPub.getId(), goodsList);

        if (CollectionUtils.isNotEmpty(orderReq.getBoxList())) {
            List<OrderPubBox> boxList = Lists.newArrayList();
            orderReq.getBoxList().forEach(box -> {
                OrderPubBox orderPubBox = new OrderPubBox();
                BeanUtils.copyProperties(box, orderPubBox);
                //接单是g，数据库传kg
                orderPubBox.setClientWeight(orderPubBox.getClientWeight() == null ? null : orderPubBox.getClientWeight().divide(BigDecimal.valueOf(1000), 5, BigDecimal.ROUND_HALF_UP));
                boxList.add(orderPubBox);
            });
            orderPubBoxService.batchInsert(orderPub.getId(), boxList);

        }
        //发送下发ESD的MQ
        messageSender.send(QueueConstants.QueueEnum.OCP_PUB_ORDER_PUSH_ESD, new BasicMessage(orderPub.getId(), orderPub.getCode()));
    }

    /**
     * 推送订单到ESD
     *
     * @param orderPub
     * @return
     */
    @Override
    @Transactional
    public BaseResponse<T> pushOrderToEsd(OrderPub orderPub) {
        List<OrderPubItem> goods = orderPubItemService.selectByOrderId(orderPub.getId());
        orderPub.setGoodsList(goods);
        List<OrderPubBox> boxList = orderPubBoxService.selectByOrderId(orderPub.getId());
        orderPub.setBoxList(boxList);

        //转换请求报文,同时按照转单参数对字段进行转换(标准平台不解析转单配值)
        //查询店铺信息
        Store store = storeService.selectEnableByCode(orderPub.getStoreCode());
        BusinessAssert.assertNotNull(store, "店铺信息为空");
        BaseResponse<T> response = null;
        if (IeFlagEnum.E.getValue().equals(orderPub.getIeFlag())) {
            EsdRequest req = PubDtoConverter.convertEsdOut(orderPub);
            response = esdApiService.sendOutOrder(req, orderPub.getOrderNo(), orderPub.getCode(), store);

        }else {
            //下发接口,如果有异常更新状态为下发失败
            EsdRequest req = PubDtoConverter.convertEsd(orderPub);
            response = esdApiService.sendOrder(req, orderPub.getOrderNo(), orderPub.getCode(), store);
        }
        //更新订单状态,如果是业务异常则更改为下发失败，如果其他异常不更新为下发失败
        OrderPub updateEntity = new OrderPub();
        updateEntity.setId(orderPub.getId());
        updateEntity.setStatus(response.isSuccess() ? SyncStatusEnum.HAVE_SYNC : SyncStatusEnum.SYNC_FAIL);
        orderPubService.update(updateEntity);
        return response;
    }

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
        List<OrderPub> orderPubs = orderPubService.selectByIds(ids);
        orderPubs.forEach(orderPub -> {
            //下发成功的订单才允许重新推送
            if (!SyncStatusEnum.HAVE_SYNC.equals(orderPub.getStatus())) {
                msg.append(orderPub.getOrderNo()).append(PunctuateConstant.COMMA);
                return;
            }
            //直接重新发送MQ
            messageSender.send(QueueConstants.QueueEnum.OCP_PUB_ORDER_PUSH_ESD, new BasicMessage(orderPub.getId(), orderPub.getCode()));
        });
        return StringUtils.isEmpty(msg) ? msg.toString() : msg.append("订单当前状态不允许重推！").toString();
    }
}

package com.topideal.supplychain.ocp.kjb.service.impl;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.mq.MessageSender;
import com.topideal.supplychain.ocp.enums.KjbYesNoEnum;
import com.topideal.supplychain.ocp.enums.MerchantEnum;
import com.topideal.supplychain.ocp.enums.PlatformEnum;
import com.topideal.supplychain.ocp.enums.SyncStatusEnum;
import com.topideal.supplychain.ocp.esd.dto.EsdRequest;
import com.topideal.supplychain.ocp.esd.service.EsdApiService;
import com.topideal.supplychain.ocp.kjb.converter.KjbReqConverter;
import com.topideal.supplychain.ocp.kjb.dto.KjbReceiveReqDto;
import com.topideal.supplychain.ocp.kjb.service.KjbService;
import com.topideal.supplychain.ocp.master.model.Store;
import com.topideal.supplychain.ocp.master.service.StoreService;
import com.topideal.supplychain.ocp.mq.QueueConstants;
import com.topideal.supplychain.ocp.order.model.OrderKjb;
import com.topideal.supplychain.ocp.order.model.OrderKjbItem;
import com.topideal.supplychain.ocp.order.service.OrderKjbItemService;
import com.topideal.supplychain.ocp.order.service.OrderKjbService;
import com.topideal.supplychain.util.AES256Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 跨境宝订单
 * @author: syq
 * @create: 2019-12-17 16:36
 **/
@Service
public class KjbServiceImpl implements KjbService {


    @Autowired
    private OrderKjbItemService orderKjbItemService;
    @Autowired
    private EsdApiService esdApiService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private OrderKjbService orderKjbService;
    @Autowired
    private MessageSender messageSender;

    /**
     * 发送订单信息至esd
     * @param orderKjb
     * @return
     */
    @Override
    @Transactional
    public BaseResponse sendOrderEsd(OrderKjb orderKjb) {

        List<OrderKjbItem> orderKjbItemList = orderKjbItemService.selectByOrderId(orderKjb.getId());
        //获取报文
        EsdRequest esdRequest = KjbReqConverter.convertEsd(orderKjb, orderKjbItemList);
        //根据电商平台、电商企业、店铺编码、海外仓编码查询店铺信息获取appId、appKey
        Store store = storeService.selectEnableByCode(orderKjb.getStoreCode());
        BusinessAssert.assertNotNull(store,"店铺信息不存在");
        //推单至esd
        BaseResponse baseResponse = esdApiService.sendOrder(esdRequest, orderKjb.getTradeNo(),
                                    orderKjb.getCode(), store);
        //更新订单状态
        OrderKjb update = new OrderKjb();
        update.setId(orderKjb.getId());
        //回执成功则更新状态为同步成功，否则更新状态为同步失败并且记录失败原因
        if (baseResponse.isSuccess()) {
            update.setStatus(SyncStatusEnum.HAVE_SYNC);
        }else {
            update.setStatus(SyncStatusEnum.SYNC_FAIL);
            update.setFailReason(baseResponse.getMessage());
        }
        orderKjbService.update(update);
        return baseResponse;
    }

    /**
     * 跨境宝接单
     * 保存订单及明细信息，并发送推送esd的mq消息
     * @param kjbReceiveReqDto
     */
    @Override
    @Transactional
    public void receiveOrder(KjbReceiveReqDto kjbReceiveReqDto, String timestamp) {
        //保存订单及明细信息
        OrderKjb orderKjb = buildOrder(kjbReceiveReqDto, timestamp);
        orderKjbService.insert(orderKjb);
        List<OrderKjbItem> itemList = buildItems(kjbReceiveReqDto, orderKjb);
        orderKjbItemService.batchInsert(itemList);
        //发送跨境宝订单下发ESD的MQ
        messageSender.send(QueueConstants.QueueEnum.OCP_KJB_ORDER_PUSH_ESD, new BasicMessage(orderKjb.getId(), orderKjb.getCode()));
    }

    /**
     * 转换订单信息
     * @param kjbReceiveReqDto
     * @param timestamp
     * @return
     */
    private OrderKjb buildOrder(KjbReceiveReqDto kjbReceiveReqDto, String timestamp) {
        OrderKjb orderKjb = new OrderKjb();
        BeanUtils.copyProperties(kjbReceiveReqDto, orderKjb);
        orderKjb.setStatus(SyncStatusEnum.NOT_SYNC);
        orderKjb.setRequestTimestamp(timestamp);
        orderKjb.setIsTraceSource(KjbYesNoEnum.getValueEnum(kjbReceiveReqDto.getIsTraceSource()));
        orderKjb.setIsTran(KjbYesNoEnum.getValueEnum(kjbReceiveReqDto.getIsTran()));
        if (kjbReceiveReqDto.getGrossWeight() != null) {
            orderKjb.setGrossWeight(new BigDecimal(kjbReceiveReqDto.getGrossWeight()).divide(new BigDecimal(1000)).setScale(5, BigDecimal.ROUND_HALF_UP));
        }
        if (kjbReceiveReqDto.getNetWeight() != null) {
            orderKjb.setNetWeight(new BigDecimal(kjbReceiveReqDto.getNetWeight()).divide(new BigDecimal(1000)).setScale(5, BigDecimal.ROUND_HALF_UP));
        }

        //发货人信息
        orderKjb.setSenderName(AES256Util.encrypt(kjbReceiveReqDto.getSender().getName()));
        if (StringUtils.isNotEmpty(kjbReceiveReqDto.getSender().getPhone())) {
            orderKjb.setSenderPhone(AES256Util.encrypt(kjbReceiveReqDto.getSender().getPhone()));
        }
        if (StringUtils.isNotEmpty(kjbReceiveReqDto.getSender().getMobile())) {
            orderKjb.setSenderMobile(AES256Util.encrypt(kjbReceiveReqDto.getSender().getMobile()));
        }
        orderKjb.setSenderCountry(kjbReceiveReqDto.getSender().getCountry());
        orderKjb.setSenderProvince(kjbReceiveReqDto.getSender().getProvince());
        orderKjb.setSenderCity(kjbReceiveReqDto.getSender().getCity());
        orderKjb.setSenderDistrict(kjbReceiveReqDto.getSender().getCounty());
        orderKjb.setSenderAddress(AES256Util.encrypt(kjbReceiveReqDto.getSender().getAddress()));
        orderKjb.setSenderZip(kjbReceiveReqDto.getSender().getZipCode());

        //收货人信息
        orderKjb.setReceiverName(AES256Util.encrypt(kjbReceiveReqDto.getReceiver().getName()));
        if (StringUtils.isNotEmpty(kjbReceiveReqDto.getReceiver().getPhone())) {
            orderKjb.setReceiverPhone(AES256Util.encrypt(kjbReceiveReqDto.getReceiver().getPhone()));
        }
        if (StringUtils.isNotEmpty(kjbReceiveReqDto.getReceiver().getMobile())) {
            orderKjb.setReceiverMobile(AES256Util.encrypt(kjbReceiveReqDto.getReceiver().getMobile()));
        }
        if (StringUtils.isNotEmpty(kjbReceiveReqDto.getReceiver().getIdentityNo())) {
            orderKjb.setReceiverIdentityNo(AES256Util.encrypt(kjbReceiveReqDto.getReceiver().getIdentityNo()));
        }
        orderKjb.setReceiverIdentityFrontUrl(kjbReceiveReqDto.getReceiver().getIdentityNoFront());
        orderKjb.setReceiverIdentityBackUrl(kjbReceiveReqDto.getReceiver().getIdentityNoBack());
        orderKjb.setReceiverCountry(kjbReceiveReqDto.getReceiver().getCountry());
        orderKjb.setReceiverProvince(kjbReceiveReqDto.getReceiver().getProvince());
        orderKjb.setReceiverCity(kjbReceiveReqDto.getReceiver().getCity());
        orderKjb.setReceiverDistrict(kjbReceiveReqDto.getReceiver().getCounty());
        orderKjb.setReceiverAddress(AES256Util.encrypt(kjbReceiveReqDto.getReceiver().getAddress()));
        orderKjb.setReceiverZip(kjbReceiveReqDto.getReceiver().getZipCode());

        //购买人信息
        if (kjbReceiveReqDto.getBuyer() != null) {
            if (StringUtils.isNotEmpty(kjbReceiveReqDto.getBuyer().getName())) {
                orderKjb.setBuyerName(AES256Util.encrypt(kjbReceiveReqDto.getBuyer().getName()));
            }
            if (StringUtils.isNotEmpty(kjbReceiveReqDto.getBuyer().getPhone())) {
                orderKjb.setBuyerPhone(AES256Util.encrypt(kjbReceiveReqDto.getBuyer().getPhone()));
            }
            orderKjb.setBuyerMobile(kjbReceiveReqDto.getBuyer().getMobile());
            orderKjb.setBuyerIdType(kjbReceiveReqDto.getBuyer().getIdType());
            if (StringUtils.isNotEmpty(kjbReceiveReqDto.getBuyer().getIdentityNo())) {
                orderKjb.setBuyerIdentityNo(AES256Util.encrypt(kjbReceiveReqDto.getBuyer().getIdentityNo()));
            }
        }

        //服务类型
        List<Integer> serviceTypeStr = kjbReceiveReqDto.getSerciveList().stream().map(KjbReceiveReqDto.ServiceType :: getServiceType).collect(Collectors.toList());
        String serviceType = StringUtils.join(serviceTypeStr, ",");
        orderKjb.setServiceType(serviceType);

        return orderKjb;
    }

    /**
     * 转换订单明细信息
     * @param kjbReceiveReqDto
     * @param orderKjb
     * @return
     */
    private List<OrderKjbItem> buildItems(KjbReceiveReqDto kjbReceiveReqDto, OrderKjb orderKjb) {
        List<OrderKjbItem> itemList = new ArrayList<>();
        //订单明细
        for (KjbReceiveReqDto.Good good : kjbReceiveReqDto.getItemList()) {
            OrderKjbItem item = new OrderKjbItem();
            item.setOrderId(orderKjb.getId());
            item.setTradeNo(orderKjb.getTradeNo());
            item.setItemNo(good.getIndex());
            item.setGoodsCode(good.getItem());
            item.setIsPresente(KjbYesNoEnum.getValueEnum(good.getIsPresente()));
            item.setGoodsName(good.getGoodsName());
            item.setCategoryNo(good.getCategoryNo());
            item.setHscode(good.getHscode());
            item.setCustNo(good.getCustNo());
            item.setCiqNo(good.getCiqNo());
            item.setQty(good.getQty());
            item.setPrice(good.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP));
            item.setPriceCurrency(good.getPriceCurrency());
            item.setUnit1(good.getUnit1());
            if (good.getQty1() != null) {
                item.setQty1(good.getQty1().setScale(5, BigDecimal.ROUND_HALF_UP));
            }
            item.setUnit2(good.getUnit2());
            if (good.getQty2() != null) {
                item.setQty2(good.getQty2().setScale(5, BigDecimal.ROUND_HALF_UP));
            }
            item.setBrand(good.getBrand());
            item.setSpec(good.getSpec());
            item.setUnit(good.getUnit());
            if (good.getGrossWeight() != null) {
                item.setGrossWeight(new BigDecimal(good.getGrossWeight()).divide(new BigDecimal(1000)).setScale(5, BigDecimal.ROUND_HALF_UP));
            }
            if (good.getNetWeight() != null) {
                item.setNetWeight(new BigDecimal(good.getNetWeight()).divide(new BigDecimal(1000)).setScale(5, BigDecimal.ROUND_HALF_UP));
            }
            item.setTax(good.getTax());
            item.setOriginCountry(good.getOriginCountry());
            item.setCiqOriginArea(good.getCiqOriginArea());
            item.setBarCode(good.getBarCode());
            itemList.add(item);
        }
        return itemList;
    }

    /**
     * 重推订单至esd
     * @param ids
     */
    @Override
    @Transactional
    public void repushEsd(List<Long> ids) {
        List<OrderKjb> orderKjbList = orderKjbService.selectByIds(ids);
        for (OrderKjb orderKjb : orderKjbList) {
            //只有状态为已同步订单才能重推
            BusinessAssert.assertIsTrue(SyncStatusEnum.HAVE_SYNC.equals(orderKjb.getStatus()) ,String.format("订单%s不是已同步状态",orderKjb.getCode()));
            //发送订单下发esd的mq消息
            messageSender.send(QueueConstants.QueueEnum.OCP_KJB_ORDER_PUSH_ESD, new BasicMessage(orderKjb.getId(), orderKjb.getCode()));
        }
    }
}

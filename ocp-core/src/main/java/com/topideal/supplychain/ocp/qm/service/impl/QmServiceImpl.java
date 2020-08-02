package com.topideal.supplychain.ocp.qm.service.impl;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.enums.EsdOrderSourceEnum;
import com.topideal.supplychain.ocp.enums.QmYesOrNoEnum;
import com.topideal.supplychain.ocp.enums.ReceiveIdTypeEnum;
import com.topideal.supplychain.ocp.enums.ServiceTypeEnum;
import com.topideal.supplychain.ocp.esd.dto.*;
import com.topideal.supplychain.ocp.esd.service.EsdApiService;
import com.topideal.supplychain.ocp.master.model.GoodsInfo;
import com.topideal.supplychain.ocp.master.service.GoodsInfoService;
import com.topideal.supplychain.ocp.master.service.StoreService;
import com.topideal.supplychain.ocp.order.model.OrderQm;
import com.topideal.supplychain.ocp.order.model.OrderQmItem;
import com.topideal.supplychain.ocp.order.service.OrderQmItemService;
import com.topideal.supplychain.ocp.qm.service.QmService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.OrderComparator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 奇门订单
 * @author: syq
 * @create: 2019-12-16 10:19
 **/
@Service
public class QmServiceImpl implements QmService {

    @Autowired
    private OrderQmItemService orderQmItemService;
    @Autowired
    private GoodsInfoService goodsInfoService;
    @Autowired
    private EsdApiService esdApiService;
    @Autowired
    private StoreService storeService;

    @Override
    public BaseResponse sendOrderEsd(OrderQm orderQm) {


        List<OrderQmItem> orderQmItemList = orderQmItemService.selectByOrderId(orderQm.getId());
        EsdRequest request = buildEsdReq(orderQm, orderQmItemList);


        return null;
    }

    private EsdRequest buildEsdReq(OrderQm orderQm, List<OrderQmItem> orderQmItemList) {

        List<EsdServiceType> serviceTypeList = new ArrayList<>();
        EsdServiceType serviceType = EsdServiceType.newBuilder()
                .service_type(ServiceTypeEnum.ALL.getValue())
                .build();
        serviceTypeList.add(serviceType);

        EsdSender sender = EsdSender.newBuilder()
                .name(orderQm.getSenderName())
                .phone(orderQm.getSenderTel())
                .mobile(orderQm.getSenderMobile())
                .country(orderQm.getSenderCountryCode())
                .province(orderQm.getSenderProvince())
                .city(orderQm.getSenderCity())
                .county(orderQm.getSenderArea())
                .address(orderQm.getSenderDetailAddress())
                .zip_code(orderQm.getSenderZip())
                .build();

        EsdReceiver receiver = EsdReceiver.newBuilder()
                .name(orderQm.getReceiverName())
                .phone(orderQm.getReceiverTel())
                .mobile(orderQm.getReceiverMobile())
                .identity_no(StringUtils.isNotEmpty(orderQm.getReceiverIdType())
                        && orderQm.getReceiverIdType().equals(ReceiveIdTypeEnum.ID_CARD.getValue())
                        ? orderQm.getReceiverIdNumber() : null )
                .country(orderQm.getReceiverCountryCode())
                .province(orderQm.getReceiverProvince())
                .city(orderQm.getReceiverCity())
                .county(orderQm.getReceiverArea())
                .address(orderQm.getReceiverDetailAddress())
                .zip_code(orderQm.getReceiverZip())
                .build();

        EsdBuyer buyer = EsdBuyer.newBuilder()
                .name(orderQm.getReceiverName())
                .phone(orderQm.getReceiverTel())
                .mobile(orderQm.getReceiverMobile())
                .id_type(StringUtils.isNotEmpty(orderQm.getReceiverIdType())
                        && ReceiveIdTypeEnum.ID_CARD.getValue().equals(orderQm.getReceiverIdType())
                        ? ReceiveIdTypeEnum.ID_CARD.getValue() : ReceiveIdTypeEnum.OFFICER_CARD.getValue())
                .identity_no(orderQm.getReceiverIdNumber())
                .build();

        List<EsdGood> esdGoodList = new ArrayList<>();
        for (OrderQmItem item : orderQmItemList) {
            //查询商品信息获取条形码
            List<GoodsInfo> goodsInfoList = goodsInfoService.selectByOwnerAndGoodsNo(item.getOwnerCode(), item.getGoodsCode());
            EsdGood esdGood = EsdGood.newBuilder()
                    .index(item.getItemNo())
                    .is_presente(Integer.valueOf(QmYesOrNoEnum.NO.getValue()))
                    .item_name(item.getGoodsName())
                    .item_quantity(item.getPlanQty())
                    .price_declare(item.getRetailPrice())
                    .price_code("CNY")
                    .item(item.getGoodsCode())
                    .bar_code(goodsInfoList.get(0).getBarCode())
                    .build();
            esdGoodList.add(esdGood);
        }


        return EsdRequest.newBuilder()
                .source(EsdOrderSourceEnum.QM_ORDER.getValue())
                .dno(orderQm.getRequestAppKey())
                .trade_no(orderQm.getOrderCode())
                .cp_code(orderQm.getLogisticsCode())
                .mail_no(orderQm.getLogisticsNo())
                .order_create_time(orderQm.getCreateTime())
                .storehouse_id(orderQm.getWarehouseCode())
                .payment_transaction(orderQm.getPayNo())
                .total_freight(orderQm.getFreight())
                .total_code("CNY")
                .discount_fee(orderQm.getDiscountAmount())
                .discount_code("CNY")
                .platform(orderQm.getSourcePlatformCode())
                .is_trace_source(Integer.valueOf(QmYesOrNoEnum.NO.getValue()))
                .is_tran(QmYesOrNoEnum.NO.getValue())
                .remark(orderQm.getRemark())
                .buyer(buyer)
                .receiver(receiver)
                .sender(sender)
                .serciveList(serviceTypeList)
                .itemList(esdGoodList)
                .build();
    }
}

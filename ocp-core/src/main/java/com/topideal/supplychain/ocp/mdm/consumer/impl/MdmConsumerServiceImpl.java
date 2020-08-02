package com.topideal.supplychain.ocp.mdm.consumer.impl;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.mq.NeedRetryException;
import com.topideal.supplychain.mq.NoDataReadyException;
import com.topideal.supplychain.ocp.master.model.GoodsInfo;
import com.topideal.supplychain.ocp.master.service.GoodsInfoService;
import com.topideal.supplychain.ocp.mdm.consumer.MdmConsumerService;
import com.topideal.supplychain.ocp.mdm.service.MdmService;
import com.topideal.supplychain.ocp.mq.QueueConstants;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 主数据消费者
 * @author: syq
 * @create: 2019-12-10 11:37
 **/
@Component
public class MdmConsumerServiceImpl implements MdmConsumerService {

    @Autowired
    private GoodsInfoService goodsInfoService;
    @Autowired
    private MdmService mdmService;

    /**
     * 同步商品信息消费者
     * @param basicMessage
     */
    @Override
    @RabbitHandler
    @RabbitListener(queues = QueueConstants.OCP_MDM_SYNC_GOODS_INFO)
    public void syncGoodsInfo(BasicMessage basicMessage) {
        GoodsInfo goodsInfo = goodsInfoService.selectById(basicMessage.getBusinessId());
        if (goodsInfo == null) {
            throw new NoDataReadyException();
        }
        BaseResponse baseResponse = mdmService.syncGoodsInfo(goodsInfo);
        if (baseResponse.isSuccess()) {
            return;
        }
        throw new NeedRetryException(baseResponse.getMessage());
    }
}

package com.topideal.supplychain.ocp.mdm.consumer;

import com.topideal.supplychain.mq.BasicMessage;

/**
 * @description: 主数据同步商品信息消费者
 * @author: syq
 * @create: 2019-12-10 11:35
 **/
public interface MdmConsumerService {

    void syncGoodsInfo(BasicMessage basicMessage);

}

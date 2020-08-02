package com.topideal.supplychain.ocp.gs.consumer;

import com.topideal.supplychain.mq.BasicMessage;

/**
 * <p>标题: TODO</p>
 * <p>功能: TODO</p>
 * <p>模块: ocp2</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.gs.consumer.impl</p>
 * <p>作者: topIdeal</p>
 * <p>创建日期: 2019-12-30 18:04</p>
 *
 * @version 1.0
 */
public interface GsConsumerService {

    void getOrder(BasicMessage basicMessage);

    void processOrder(BasicMessage basicMessage);

    void sendOrderKjb(BasicMessage basicMessage);

    void sendOrderOfc(BasicMessage basicMessage);

    void sendOrderOp(BasicMessage basicMessage);
}

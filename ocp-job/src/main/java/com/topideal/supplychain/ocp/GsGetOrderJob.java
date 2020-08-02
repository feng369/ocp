package com.topideal.supplychain.ocp;

import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.mq.MessageSender;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.config.service.CatchOrderConfigService;
import com.topideal.supplychain.ocp.enums.PlatformEnum;
import com.topideal.supplychain.ocp.master.model.Platform;
import com.topideal.supplychain.ocp.master.service.PlatformService;
import com.topideal.supplychain.ocp.mq.QueueConstants;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>标题: TODO</p>
 * <p>功能: TODO</p>
 * <p>模块: ocp2</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp</p>
 * <p>作者: topIdeal</p>
 * <p>创建日期: 2019-12-17 17:43</p>
 *
 * @version 1.0
 */
@Component
public class GsGetOrderJob implements Job {

    private Logger logger = LoggerFactory.getLogger(GsGetOrderJob.class);

    @Autowired
    private PlatformService platformService;
    @Autowired
    private CatchOrderConfigService catchOrderConfigService;
    @Autowired
    private MessageSender messageSender;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // 1.查询环球捕手电商平台编码
        String gs = PlatformEnum.GS.getCode();
        List<Platform> platforms = platformService.findByVirtualCode(gs);
        if (CollectionUtils.isEmpty(platforms)) {
            return;
        }
        // 2.通过电商平台编码查询所有启用的抓单配置
        List<CatchOrderConfig> orderGrabConfigList = catchOrderConfigService.selectByPlatform(platforms);
        if (CollectionUtils.isEmpty(orderGrabConfigList)) {
            return;
        }
        // 3.循环抓单配置，发送MQ(抓单定义ID)
        orderGrabConfigList.forEach(grabConfig -> messageSender.send(QueueConstants.QueueEnum.OCP_GS_GET_ORDER, new BasicMessage(grabConfig.getId(), grabConfig.getCode())));
    }
}

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @description: 达令家抓单定时任务
 * @author: wanwei
 * @create: 2020-02-27 17:26
 **/
@Component
public class DaLingGetOrderJob implements Job {

    private Logger logger = LoggerFactory.getLogger(DaLingGetOrderJob.class);

    @Autowired
    private PlatformService platformService;

    @Autowired
    private CatchOrderConfigService catchOrderConfigService;

    @Autowired
    private MessageSender messageSender;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        String daLing = PlatformEnum.DALING.getCode();
        List<Platform> platforms = platformService.findByVirtualCode(daLing);
        if (CollectionUtils.isEmpty(platforms)) {
            return;
        }
        //通过电商平台编码拿到所有启用的抓单配置
        List<CatchOrderConfig> orderGrabConfigList = catchOrderConfigService.selectByPlatform(platforms);
        if (CollectionUtils.isEmpty(orderGrabConfigList)) {
            return;
        }

        //循环抓单配置
        for (CatchOrderConfig grabConfig : orderGrabConfigList) {
            try {
                messageSender.send(QueueConstants.QueueEnum.OCP_DALING_GET_ORDER, new BasicMessage(grabConfig.getId(), grabConfig.getStoreCode()));
            } catch (Exception e) {
                logger.error(String
                        .format("达令家抓单异常!抓单配置 %s，异常信息:%s", grabConfig.getName(), e.getMessage()), e);
            }

        }

    }
}

package com.topideal.supplychain.ocp;

import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.mq.MessageSender;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.config.service.CatchOrderConfigService;
import com.topideal.supplychain.ocp.enums.PlatformEnum;
import com.topideal.supplychain.ocp.master.model.Platform;
import com.topideal.supplychain.ocp.master.service.PlatformService;
import com.topideal.supplychain.ocp.mq.QueueConstants;
import java.util.List;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

public class DxyGetOrderJob implements Job {
    private Logger LOGGER = LoggerFactory.getLogger(DxyGetOrderJob.class);

    @Autowired
    private PlatformService platformService;
    @Autowired
    private CatchOrderConfigService catchOrderConfigService;
    @Autowired
    private MessageSender messageSender;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {

        List<Platform> platforms = platformService.findByVirtualCode(PlatformEnum.DXY.getCode());
        if (CollectionUtils.isEmpty(platforms)) {
            return;
        }
        List<CatchOrderConfig> orderGrabConfigList = catchOrderConfigService.selectByPlatform(platforms);
        //循环抓单配置
        for (CatchOrderConfig grabConfig : orderGrabConfigList) {
            try {
                messageSender.send(QueueConstants.QueueEnum.OCP_DXY_GET_ORDER, BasicMessage.newBuilder().businessId(grabConfig.getId()).businessCode(grabConfig.getCode()).build());
            }catch (Exception e) {
                LOGGER.error(String
                        .format("丁香医生抓单异常!抓单配置 %s，异常信息:%s", grabConfig.getCode(), e.getMessage()),e);
            }
        }

    }

}


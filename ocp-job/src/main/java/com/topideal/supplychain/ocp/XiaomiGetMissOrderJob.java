package com.topideal.supplychain.ocp;

import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.mq.MessageSender;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.config.service.CatchOrderConfigService;
import com.topideal.supplychain.ocp.enums.PlatformEnum;
import com.topideal.supplychain.ocp.master.model.Platform;
import com.topideal.supplychain.ocp.master.service.PlatformService;
import com.topideal.supplychain.ocp.mq.QueueConstants.QueueEnum;
import java.util.List;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * 小米漏单抓单定时任务
 * @author xuxiaoyan
 * @date 2020-05-27 14:54
 */
@Component
public class XiaomiGetMissOrderJob implements Job{

    private Logger logger = LoggerFactory.getLogger(XiaomiGetMissOrderJob.class);

    @Autowired
    private PlatformService platformService;
    @Autowired
    private CatchOrderConfigService catchOrderConfigService;
    @Autowired
    private MessageSender messageSender;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        //拿到小米电商平台编码
        String xiaomi = PlatformEnum.XIAOMI.getCode();
        List<Platform> platforms = platformService.findByVirtualCode(xiaomi);
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
                if (grabConfig.getStoreId() != null) {
                    messageSender.send(QueueEnum.OCP_XIAOMI_GET_MISS_ORDER,new BasicMessage(grabConfig.getId(),grabConfig.getStoreCode()));
                }
            } catch (Exception e) {
                logger.error(String
                        .format("小米漏单抓单异常!抓单配置 %s，异常信息:%s", grabConfig.getStoreId(), e.getMessage()),e);
            }

        }
    }
}

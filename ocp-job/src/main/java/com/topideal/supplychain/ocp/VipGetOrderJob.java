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
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * @Author: Zhangli
 * @Description: 唯品会订单抓取定时任务
 * @Date: Created In 2019/12/4
 * @Modified By:
 */
@Component
public class VipGetOrderJob implements Job {

    private Logger logger = LoggerFactory.getLogger(VipGetOrderJob.class);

    @Autowired
    private PlatformService platformService;
    @Autowired
    private CatchOrderConfigService catchOrderConfigService;
    @Autowired
    private MessageSender messageSender;


    /**
     * 定时任务抓取订单
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        //获取唯品会平台编码
        List<Platform> platforms = platformService.findByVirtualCode(PlatformEnum.VIP.getCode());
        if (CollectionUtils.isEmpty(platforms)) {
            return;
        }
        List<CatchOrderConfig> catchOrderConfigs = catchOrderConfigService
                .selectByPlatform(platforms);
        //唯品订单没有店铺的维度,唯品会是按照平台参数配置的关区维度区抓取订单
        for (CatchOrderConfig catchOrderConfig : catchOrderConfigs) {
            try {
                messageSender.send(QueueConstants.QueueEnum.OCP_VIP_GET_ORDER,
                        new BasicMessage(catchOrderConfig.getId(), catchOrderConfig.getCode()));
            } catch (Exception e) {
                logger.error(String
                        .format("唯品抓单异常!抓单配置 %s，异常信息:%s", catchOrderConfig.getCode(),
                                e.getMessage()), e);
            }

        }
    }

}

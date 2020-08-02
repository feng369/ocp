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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>标题: 全球订单抓单</p>
 * <p>模块: ocp-parent</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp</p>
 * <p>作者: LeoZhang</p>
 * <p>创建日期: 2019/12/10 11:21</p>
 *
 * @version 1.0
 */
@Component
public class GlobalGetOrderJob implements Job {

    @Autowired
    private PlatformService platformService;
    @Autowired
    private CatchOrderConfigService catchOrderConfigService;
    @Autowired
    private MessageSender messageSender;

    /**
     * 全球仓抓单
     * 1.抓取订单
     * 2.下发抓取订单的MQ
     * @param jobExecutionContext
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        //获取全球仓平台编码
        List<Platform> platforms = platformService.findByVirtualCode(PlatformEnum.GLOBAL.getCode());
        if (CollectionUtils.isEmpty(platforms)) {
            return;
        }
        List<CatchOrderConfig> catchOrderConfigs = catchOrderConfigService.selectByPlatform(platforms);
        //按照转单配置的数量进行抓单
        catchOrderConfigs.forEach(catchOrderConfig -> messageSender.send(QueueConstants.QueueEnum.OCP_GLOBAL_GET_ORDER, new BasicMessage(catchOrderConfig.getId(), catchOrderConfig.getCode())));
    }
}

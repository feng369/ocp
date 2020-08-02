package com.topideal.supplychain.ocp;


import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.mq.MessageSender;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.config.service.CatchOrderConfigService;
import com.topideal.supplychain.ocp.enums.PlatformEnum;
import com.topideal.supplychain.ocp.master.model.Platform;
import com.topideal.supplychain.ocp.master.service.PlatformService;
import com.topideal.supplychain.ocp.mq.QueueConstants;
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
 * @Author:
 * @Description: 丁香医生抓取漏掉订单
 * @Date: Created In 2019/5/5 10:33
 * @Modified By:
 */
@Component
public class DxyGetMissOrderJob implements Job {
	
	private Logger logger = LoggerFactory.getLogger(DxyGetMissOrderJob.class);

	@Autowired
	private PlatformService platformService;
	@Autowired
	private CatchOrderConfigService catchOrderConfigService;
	@Autowired
	private MessageSender messageSender;

	@Override
	public void execute(JobExecutionContext jobExecutionContext) {
		//拿到有赞电商平台编码
		List<Platform> platforms = platformService.findByVirtualCode(PlatformEnum.DXY.getCode());

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
				messageSender.send(
						QueueEnum.OCP_DXY_GET_MISS_ORDER, BasicMessage.newBuilder().businessId(grabConfig.getId()).businessCode(grabConfig.getCode()).build());
			}catch (Exception e) {
				logger.error(String
						.format("丁香医生漏单抓单异常!抓单配置 %s，异常信息:%s", grabConfig.getCode(), e.getMessage()),e);
			}

		}
	}
}

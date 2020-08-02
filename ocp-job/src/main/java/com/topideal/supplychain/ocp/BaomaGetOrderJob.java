package com.topideal.supplychain.ocp;


import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.mq.MessageSender;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.config.service.CatchOrderConfigService;
import com.topideal.supplychain.ocp.enums.PlatformEnum;
import com.topideal.supplychain.ocp.master.model.Platform;
import com.topideal.supplychain.ocp.master.service.PlatformService;
import com.topideal.supplychain.ocp.mq.QueueConstants.QueueEnum;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
  宝妈时光抓单
 */
@Component
public class BaomaGetOrderJob implements Job {
	
	private Logger logger = LoggerFactory.getLogger(BaomaGetOrderJob.class);

	@Autowired
	private PlatformService platformService;
	@Autowired
	private CatchOrderConfigService catchOrderConfigService;
	@Autowired
	private MessageSender messageSender;

	@Override
	public void execute(JobExecutionContext jobExecutionContext) {
		//拿到baoma电商平台编码
		String baoma = PlatformEnum.BAOMA.getCode();
		List<Platform> platforms = platformService.findByVirtualCode(baoma);
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
					messageSender.send(QueueEnum.OCP_BAOMA_GET_ORDER,new BasicMessage(grabConfig.getId(),grabConfig.getStoreCode()));
				}
			} catch (Exception e) {
				logger.error(String
					.format("宝妈时光抓单异常!抓单配置 %s，异常信息:%s", grabConfig.getStoreId(), e.getMessage()),e);
			}

		}
	}
}

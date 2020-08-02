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
 * @Author: Hu Yong
 * @Description: 有赞抓单
 * @Date: Created In 2019/5/5 10:33
 * @Modified By:
 */
@Component
public class YouZanGetOrderJob implements Job {
	
	private Logger logger = LoggerFactory.getLogger(YouZanGetOrderJob.class);

	@Autowired
	private PlatformService platformService;
	@Autowired
	private CatchOrderConfigService catchOrderConfigService;
	@Autowired
	private MessageSender messageSender;

	@Override
	public void execute(JobExecutionContext jobExecutionContext) {
		//拿到有赞电商平台编码
		String youzan = PlatformEnum.YOUZAN.getCode();
		List<Platform> platforms = platformService.findByVirtualCode(youzan);
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
					//youzanService.getOrder(grabConfig);
					messageSender.send(QueueEnum.OCP_YOUZAN_GET_ORDER,new BasicMessage(grabConfig.getId(),grabConfig.getStoreCode()));
				}
			} catch (Exception e) {
				logger.error(String
					.format("有赞抓单异常!抓单配置 %s，异常信息:%s", grabConfig.getStoreId(), e.getMessage()),e);
			}

		}
	}
}

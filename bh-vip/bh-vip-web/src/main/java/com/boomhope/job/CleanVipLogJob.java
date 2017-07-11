package com.boomhope.job;

import javax.annotation.Resource;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.boomhope.model.PushRuleVo;
import com.boomhope.service.IPushRuleService;
import com.boomhope.service.IVipLogService;

public class CleanVipLogJob extends QuartzJobBean {

	private final static Logger logger = LoggerFactory.getLogger(CleanVipLogJob.class);

	private IVipLogService vipLogService;

	@Resource(name = "vipLogService")
	public void setVipLogService(IVipLogService vipLogService) {
		this.vipLogService = vipLogService;
	}

	// 推送规则Service
	@Resource(name = "pushRuleService")
	IPushRuleService pushRuleService;

	public void setPushRuleService(IPushRuleService pushRuleService) {
		this.pushRuleService = pushRuleService;
	}

	/**
	 * 定时任务要调度的具体任务:定时清除vip_log表中数据
	 *author:zhangxx
	 */
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

		logger.info("==================开始定时清除vip_log表的信息！==================");
		// 查询logSaveTime
		String logSaveTime = null;
		try {
			PushRuleVo pushRuleVo = pushRuleService.selectByPrimaryKey("1");
			logSaveTime = pushRuleVo.getLogSaveTime();
			vipLogService.deleteByLogSaveTime(logSaveTime);
		} catch (Exception e) {
			logger.error("==================定时清除vip_log表的信息失败！==================");
		}

		logger.info("==================定时清除vip_log表的信息成功！==================");

	}

}

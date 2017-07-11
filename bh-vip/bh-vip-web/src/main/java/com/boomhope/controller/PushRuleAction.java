package com.boomhope.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boomhope.model.PushRuleVo;
import com.boomhope.service.IPushRuleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
 * 推送规则模块控制器
 * 
 * @version 1.0 2017-06-20
 * @author zhangxx
 *
 */
@Controller
@Scope("prototype")
public class PushRuleAction extends BaseAction {
	/**
	 * 日志类
	 */
	private final static Logger logger = LoggerFactory.getLogger(PushRuleAction.class);

	// 推送规则Service
	@Resource(name = "pushRuleService")
	IPushRuleService pushRuleService;

	public void setPushRuleService(IPushRuleService pushRuleService) {
		this.pushRuleService = pushRuleService;
	}

	/**
	 * 查询推送规则
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/vip/queryPushRule")
	public @ResponseBody Object queryPushRule(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		JSONObject json = getReqData(request);
		
		if (logger.isDebugEnabled()) {
			logger.debug("接入参数：" + json.toString());
		}
		// 1.获取入参
		Page<PushRuleVo> paged = PageHelper.startPage(1, 1, null);
		try {
			PushRuleVo pushRuleVo = pushRuleService.selectByPrimaryKey("1");
		} catch (Exception e) {
			logger.error("查询出错",e);
			return returnFail("查询出错");
		}
		PageInfo<PushRuleVo> pageInfo  = new PageInfo<PushRuleVo> (paged);
		
		return returnResult(pageInfo, pageInfo.getList());
	}
	/**
	 * 更新推送规则
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/vip/updatePushRule")
	public @ResponseBody Object updatePushRule(HttpServletRequest request, HttpServletResponse response) throws Exception {

		JSONObject json = getReqData(request);

		if (logger.isDebugEnabled()) {
			logger.debug("接入参数：" + json.toString());
		}
		// 1.获取入参
		PushRuleVo pushRuleVo = pushRuleService.selectByPrimaryKey("1");
		String threshold = (String) json.get("threshold");
		String timeInterval = (String) json.get("timeInterval");
		String logSaveTime = (String) json.get("logSaveTime");
		pushRuleVo.setRuleId("1");
		pushRuleVo.setThreshold(threshold);
		pushRuleVo.setTimeInterval(timeInterval);
		pushRuleVo.setLogSaveTime(logSaveTime);
		pushRuleService.updateByPrimaryKeySelective(pushRuleVo);

		return returnSucess();
	}

}
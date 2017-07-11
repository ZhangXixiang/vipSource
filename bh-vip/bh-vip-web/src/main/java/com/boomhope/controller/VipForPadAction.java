package com.boomhope.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boomhope.model.CommonConfig;
import com.boomhope.model.MacMachineVo;
import com.boomhope.model.PushRuleVo;
import com.boomhope.model.VipLogExtendVo;
import com.boomhope.service.IBaseService;
import com.boomhope.service.IMacService;
import com.boomhope.service.IPushRuleService;
import com.boomhope.service.IVipLogService;
import com.boomhope.util.DateUtil;
import com.boomhope.util.FileUtil;

/**
 * pad对外接口
 * 
 * @version 1.0 2017-03-15
 * @author zy
 *
 */
@Controller
@Scope("prototype")
public class VipForPadAction extends BaseAction {
	// 日志类
	private final static Logger logger = LoggerFactory.getLogger(VipForPadAction.class);

	private IBaseService baseService;

	@Resource(name = "baseService")
	public void setBaseService(IBaseService baseService) {
		this.baseService = baseService;
	}

	// vip识别日志
	private IVipLogService vipLogService;

	@Resource(name = "vipLogService")
	public void setVipLogService(IVipLogService vipLogService) {
		this.vipLogService = vipLogService;
	}

	// 设备服务类
	private IMacService macService;

	@Resource(name = "macService")
	public void setMacService(IMacService macService) {
		this.macService = macService;
	}

	// 推送规则Service
	@Resource(name = "pushRuleService")
	IPushRuleService pushRuleService;

	public void setPushRuleService(IPushRuleService pushRuleService) {
		this.pushRuleService = pushRuleService;
	}

	// 配置文件
	@Autowired
	CommonConfig commonConfig;

	/**
	 * 提供给pad端调用的接口：如果是第一次使用则新增一条数据进行登记，如果已有数据，则更新相关信息；
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/pad/uploadPadStatus")
	public @ResponseBody Object uploadPadStatus(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = getReqData(request);
		if (logger.isDebugEnabled()) {
			logger.debug("接入参数" + json.toString());
		}

		// 1.获取入参
		String unitCode = (String) json.get("unitCode");
		String macAddress = (String) json.get("macAddress");
		String ipAddress = (String) json.get("ipAddress");
		String port = (String) json.get("port");
		String status = (String) json.get("status");
		String createDate = DateUtil.getNowDate("yyyyMMddHHmss");
		String creater = (String) json.get("creater");

		// 这里根据mac_address查询，如果mac_machine中没有数据，则说明是第一次登陆，我们做一次插入操作，日后，如果设备下线，我们用下面的方法来更新设备的状态；
		Map<String, String> map = new HashMap<String, String>();
		map.put("macAddress", macAddress);
		List<MacMachineVo> macMachineList = macService.selectByMacAddress(map);
		// 已经注册，更新状态
		if (macMachineList != null && macMachineList.size() > 0) {
			try {
				MacMachineVo dm = new MacMachineVo();
				dm.setMacId(macMachineList.get(0).getMacId());
				dm.setCreateDate(createDate);
				dm.setCreater(creater);
				dm.setIpAddress(ipAddress);
				dm.setMacAddress(macAddress);

				dm.setPort(port);
				dm.setStatus(status);
				dm.setUnitCode(unitCode);

				macService.updateMacMachineVo(dm);
			} catch (Exception e) {
				logger.error("新增mac_machine出错", e);
				return returnFail("新增mac_machine失败");
			}
			// 新增mac_machine数据
		} else {
			try {
				MacMachineVo dm = new MacMachineVo();
				dm.setMacId(baseService.getSeq("mac_machine"));
				dm.setMacAddress(macAddress);
				dm.setIpAddress(ipAddress);
				dm.setPort(port);
				dm.setUnitCode(unitCode);

				dm.setStatus(status);
				dm.setCreateDate(createDate);
				dm.setCreater(creater);

				macService.addMacMachineVo(dm);
			} catch (Exception e) {
				logger.error("新增mac_machine出错", e);
				return returnFail("新增mac_machine失败");
			}
		}

		return returnSucess();
	}

	/**
	 * 给pad推送日志信息接口
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/pad/sendFaceRec")
	public @ResponseBody Object sendFaceRec(HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject json = getReqData(request);
			if (logger.isDebugEnabled()) {
				logger.debug("接入参数" + json.toString());
			}
			// 日志编号
			// String faceLogId = (String) json.get("faceLogId");
			// 根据查询条件查询
			Map<String, String> parMap = new HashMap<String, String>();

			// parMap.put("faceLogId", faceLogId);
			// 时间间隔
			String timeInterval = "60";
			// 相似度
			int sameScore = 80;
			// 查询push_rule
			PushRuleVo pushRuleVo = pushRuleService.selectByPrimaryKey("1");
			if (pushRuleVo.getThreshold() != null) {
				sameScore = Integer.valueOf(pushRuleVo.getThreshold());
			}
			// 时间间隔，这个值要传到查询SQL中：
			if (pushRuleVo.getTimeInterval() != null) {
				timeInterval = pushRuleVo.getTimeInterval();
			}
			parMap.put("timeInterval", timeInterval);
			List<VipLogExtendVo> vipLogExtendVoList = null;
			List<VipLogExtendVo> voList = new ArrayList<VipLogExtendVo>();
			try {
				// 这里更新为新的添加参数的查询方法
				vipLogExtendVoList = vipLogService.queryVipLogForPadList(parMap);
			} catch (Exception e) {
				logger.error("查询出错:" + e.toString());
				return returnFail("查询出错");
			}

			if (vipLogExtendVoList != null && vipLogExtendVoList.size() > 0) {
				for (int i = 0; i < vipLogExtendVoList.size(); i++) {
					VipLogExtendVo oldVo = vipLogExtendVoList.get(i);

					if (pushRuleVo.getThreshold() != null && !pushRuleVo.getThreshold().equals(""))
						// 添加阀值，从vip平台中读取；
						if (oldVo != null && oldVo.getSameScore() != null && Integer.valueOf(oldVo.getSameScore()) > sameScore) {
							VipLogExtendVo vo = new VipLogExtendVo();
							vo.setComputeTime(oldVo.getComputeTime());
							vo.setCreateDate(oldVo.getCreateDate());
							vo.setCustName(oldVo.getCustName());
							vo.setCustNo(oldVo.getCustNo());
							vo.setErrorCode(oldVo.getErrorCode());
							vo.setErrorDesc(oldVo.getErrorDesc());
							vo.setFaceLogId(oldVo.getFaceLogId());
							vo.setResult(oldVo.getResult());
							vo.setSameScore(oldVo.getSameScore());
							// 获上送图片base64
							String sendImg = oldVo.getSendImg();
							String sendImg1 = FileUtil.readFileByBytes(commonConfig.logImagePath + sendImg);
							// 获取命中图片base64
							String result = oldVo.getResult();
							String result1 = FileUtil.readFileByBytes(commonConfig.logImagePath + result);
							vo.setResult(result1);
							vo.setSendImg(sendImg1);
							vo.setStatus(oldVo.getStatus());
							vo.setUnitCode(oldVo.getUnitCode());
							vo.setUnitName(oldVo.getUnitName());
							vo.setVipCusId(oldVo.getVipCusId());
							vo.setCustLevel(oldVo.getCustLevel());
							vo.setCustSex(oldVo.getCustSex());
							
							voList.add(vo);
						}
				}
			}
			Map<Object, Object> myMap = (Map<Object, Object>) new HashMap<Object, Object>();
			myMap.put("voList", voList);
			return returnSucess(myMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

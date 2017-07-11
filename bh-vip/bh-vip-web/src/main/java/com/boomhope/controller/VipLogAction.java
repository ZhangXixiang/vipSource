package com.boomhope.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boomhope.model.CommonConfig;
import com.boomhope.model.VipLogExtendVo;
import com.boomhope.model.VipLogVo;
import com.boomhope.service.IVipLogService;
import com.boomhope.util.FileUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@Scope("prototype")
public class VipLogAction extends BaseAction {

	/**
	 * 日志类
	 */
	private final static Logger logger = LoggerFactory.getLogger(VipLogAction.class);

	private IVipLogService vipLogService;

	@Resource(name = "vipLogService")
	public void setVipLogService(IVipLogService vipLogService) {
		this.vipLogService = vipLogService;
	}

	@Autowired
	CommonConfig commonConfig;

	/**
	 * 查询日志信息
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/vip/queryVipLog")
	public @ResponseBody Object queryVipLog(@RequestBody Map<String, String> map) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("接入参数" + map.toString());
			}
			// 日志编号
			String faceLogId = null;
			if (map.get("faceLogId") != null) {
				faceLogId = (String) map.get("faceLogId");
			}
			// 错误编号
			String errorCode = null;
			if (map.get("errorCode") != null) {
				errorCode = (String) map.get("errorCode");
			}
			// 识别用户
			String custNo = null;
			if (map.get("custNo") != null) {
				custNo = (String) map.get("custNo");
			}
			// 状态
			String status = null;
			if (map.get("status") != null) {
				status = (String) map.get("status");
			}

			// 增加一个入参：processStatus,0是未处理的，如果是处理界面则传0,查询界面不传此参数，查询全部；
			String processStatus = null;
			if (map.get("processStatus") != null) {
				processStatus = (String) map.get("processStatus");
			}

			Integer offset = Integer.parseInt(map.get("pageNumber").toString());
			Integer limit = Integer.parseInt(map.get("pageSize").toString());
			
			String orderBy = null;
			orderBy = "f.create_date desc";

			// 根据查询条件查询
			Map<String, String> parMap = new HashMap<String, String>();

			parMap.put("faceLogId", faceLogId);
			parMap.put("errorCode", errorCode);
			parMap.put("custNo", custNo);
			parMap.put("status", status);
			parMap.put("processStatus", processStatus);

			// 先分页再查询
			Page<VipLogExtendVo> page = PageHelper.startPage(offset, limit, orderBy);
			try {
				vipLogService.queryVipLogList(parMap);
			} catch (Exception e) {
				logger.error("查询出错:"+e.toString());
				return returnFail("查询出错");
			}
			PageInfo<VipLogExtendVo> pageInfo = new PageInfo<VipLogExtendVo>(page);

			List<VipLogExtendVo> list = pageInfo.getList();
			logger.info("查询结束：size:" + list.size() + "  list.toString:" + list.toString());
			return returnResult(pageInfo, pageInfo.getList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 查询详情图片
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/vip/vipImg")
	public @ResponseBody Object queryImg(HttpServletRequest request, HttpServletResponse response) {
		JSONObject reqJson = getReqData(request);
		if (logger.isDebugEnabled()) {
			logger.debug("接入参数" + reqJson.toString());
		}
		// 根据设备编号查询图片路径
		String faceLogId = null;
		if (reqJson.getString("faceLogId") != null) {
			faceLogId = (String) reqJson.get("faceLogId");
		}
		VipLogVo vipLogVo = null;
		try {
			vipLogVo = vipLogService.selectByFaceLogId(faceLogId);
		} catch (Exception e) {
			logger.error("查询出错");
			return returnFail("查询出错");
		}
		if (vipLogVo == null) {
			return returnFail("展示没有命中图片");
		}

		VipLogVo vipLogVo1 = new VipLogVo();
		// 获上送图片base64
		String sendImg = vipLogVo.getSendImg();
		String sendImg1 = FileUtil.readFileByBytes(commonConfig.logImagePath + sendImg);

		// 获取命中图片base64
		String result = vipLogVo.getResult();
		String result1 = FileUtil.readFileByBytes(commonConfig.logImagePath + result);
		vipLogVo.setResult(result1);

		vipLogVo1.setSendImg(sendImg1);
		vipLogVo1.setResult(result1);
		vipLogVo1.setSameScore(vipLogVo.getSameScore());
		vipLogVo1.setComputeTime(vipLogVo.getComputeTime());

		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("list", vipLogVo1);
		return returnSucess(map);
	}
	
	
	/**
	 * 查询命中图片
	 * @param request
	 * @param response
	 */
	@RequestMapping("/vip/queryFaceResultImg")
	public @ResponseBody Object queryFaceResultImg(HttpServletRequest request,HttpServletResponse response){/*
		JSONObject reqJson = getReqData(request);
		if (logger.isDebugEnabled()) {
			logger.debug("接入参数" + reqJson.toString());
		}
		//根据设备编号查询图片路径
		String deployCode = null;
		if(reqJson.getString("deployCode") != null ){
			deployCode = (String)reqJson.get("deployCode");
		}
		List<VipLogVo> resultlList = new ArrayList<VipLogVo>();
		@SuppressWarnings("unchecked")
//		Page<FaceLogVo> paged = PageHelper.startPage(1, 6, "create_date desc");
		List<VipLogVo> faceLogVolist = null;
		try {
			faceLogVolist = vipLogService.selectResultImgByDeployCode(deployCode);
		} catch (Exception e) {
			logger.error("查询失败",e);
			return returnFail("查询失败");
		}
		
//		PageInfo<FaceLogVo> pageInfo = new PageInfo<FaceLogVo>(paged);
		if(faceLogVolist == null || faceLogVolist.size() <= 0){
			return returnFail("暂时没有命中图片");
		}
		for(int i=0;i<faceLogVolist.size();i++){
			VipLogVo vipLogVo = faceLogVolist.get(i);
			VipLogVo vipLogVo1 = new VipLogVo();
			// 获上送图片base64
			String sendImg = vipLogVo.getSendImg();
			String sendImg1 = FileUtil.readFileByBytes(commonConfig.logImagePath+sendImg);
			vipLogVo.setSendImg(sendImg1);
			
			// 获取命中图片base64
			String result = vipLogVo.getResult();
			if(result != null && result != ""){
				String result1 = FileUtil.readFileByBytes(commonConfig.logImagePath+result);
				vipLogVo.setResult(result1);
				vipLogVo1.setResult(result1);
				vipLogVo1.setSendImg(sendImg1);
				vipLogVo1.setSameScore(vipLogVo.getSameScore());
				resultlList.add(vipLogVo1);
			}else{
				vipLogVo1.setSendImg(sendImg1);
				vipLogVo1.setSameScore(vipLogVo.getSameScore());
				resultlList.add(vipLogVo1);
			}
			
		}
		Map<Object,Object> map = new HashMap<Object, Object>();
		map.put("list",resultlList);
		return returnSucess(map);
	*/
		return null;
		}
	
	
	

}

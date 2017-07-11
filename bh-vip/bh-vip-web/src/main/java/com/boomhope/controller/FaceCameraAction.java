package com.boomhope.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boomhope.check.InputCheckHand;
import com.boomhope.model.CommonConfig;
import com.boomhope.model.VipCustomerVo;
import com.boomhope.model.VipLogVo;
import com.boomhope.service.IBaseService;
import com.boomhope.service.ICustomerManagementService;
import com.boomhope.service.IVipLogService;
import com.boomhope.util.CheckUtil;
import com.boomhope.util.CommonDict;
import com.boomhope.util.DateUtil;
import com.boomhope.util.FcException;
import com.boomhope.util.FileUtil;
import com.boomhope.util.HttpClientUtil;

/**
 * 摄像头对外接口
 * 
 * @version 1.0 2017-03-15
 * @author zy
 *
 */
@Controller
@Scope("prototype")
public class FaceCameraAction extends BaseAction {

	/**
	 * 日志类
	 */
	private final static Logger logger = LoggerFactory.getLogger(FaceCameraAction.class);

	@Autowired
	private InputCheckHand inputCheckHand;

	/**
	 * vip_customer服务类
	 */
	private ICustomerManagementService customerManagementService;
	@Resource(name = "customerManagementService")
	public void setCustomerManagementService(
			ICustomerManagementService customerManagementService) {
		this.customerManagementService = customerManagementService;
	}

	/**
	 * vip_log 服务类
	 */
	private IVipLogService vipLogService;

	@Resource(name = "vipLogService")
	public void setvipLogService(IVipLogService vipLogService) {
		this.vipLogService = vipLogService;
	}

	private IBaseService baseService;

	@Resource(name = "baseService")
	public void setBaseService(IBaseService baseService) {
		this.baseService = baseService;
	}

	/**
	 * 设备服务类
	 */
//	private IMacService macService;
//
//	@Resource(name = "macService")
//	public void setMacService(IMacService macService) {
//		this.macService = macService;
//	}

	@Autowired
	CommonConfig commonConfig;

	/**
	 * 照相机抓拍到照片后，先调用布控平台根据ipAddress查找到unitCode，再VIP推送平台保存识别日志，（VIP平台调用生物认证进行1vsN保存图片）
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/camera/sendFaceImgVip")
	public @ResponseBody Object saveFaceImgVip(HttpServletRequest request, HttpServletResponse response){
		// 记录开始时间
		Long start = System.currentTimeMillis();
		JSONObject reqJson = getReqData(request);
		try {
			// 检查请求报文
			reqJson = inputCheckHand.saveFaceImgBeforeCheck(reqJson);
		} catch (FcException e) {
			logger.error(e.getMessage(), e);
			returnFaceInterFail(CheckUtil.ERROR_MUST_INPUT, e.getMessage(), response);
			return null;
		}
		// 获取参数
		String sendImg = reqJson.getString("img1");
		String unitCode = reqJson.getString("unitCode");

		// 调用人脸识别系统
		String ip = commonConfig.faceIp;
		String port = commonConfig.facePort;
		String requestUrl = "http://" + ip + ":" + port + "/bioauth-face-ws/face/ ";
		JSONObject paramMap = new JSONObject();
		paramMap.put("img1", sendImg);
		String httpResult = null ;
		try{
			httpResult = new HttpClientUtil().post(requestUrl, paramMap);
		}catch(Exception e){
			logger.error(CommonDict.ERROR_REC_PLATEFORM + "请求生物认证平台失败异常");
			return null;
		}
		logger.info("======" + httpResult + "======");
		// 获取返回该图片的命中信息
		if (httpResult == null || "".equals(httpResult)) {
			logger.error(CommonDict.ERROR_UNKNOW + "调用接口人脸1比N接口异常");
			return null;
		}

		JSONObject obj = JSONObject.fromObject(httpResult);
		// 保存拍摄照片
//		FaceImageVo vo = new FaceImageVo();
//		vo.setCreateDate(DateUtil.getNowDate("yyyyMMddHHmmss"));
//		vo.setDeployCode(deployCode);
//		vo.setFaceImageId(baseService.getSeq("face_image"));
		String logImagePath = commonConfig.logImagePath;
		logger.info("图片保存的路径，logImagePath:======="+logImagePath);
		FileUtil fu = new FileUtil();
		String fileNameSendImg = fu.writeFile(sendImg, logImagePath);
//		vo.setSendImg(fileNameSendImg);
//		faceService.insertFaceImage(vo);

		// 识别成功，保存返回的base64命中照片，
		if (obj.get("result").equals(1)) {
			JSONArray jsonArr = JSONArray.fromObject(obj.get("userInfos"));
			String resultImg = "";
			String sameScore = "";
			// 比对是否是vip的id
			String userId = "";
			String userName = "";
			Iterator<Object> it = jsonArr.iterator();
			while (it.hasNext()) {
				JSONObject ob = (JSONObject) it.next();
				resultImg = ob.getString("img");// 返回命中的base64
				sameScore = ob.getString("sim");// 返回的相似度
				userId = ob.getString("userId");
				userName = ob.getString("userName");
				break;
			}
			if (resultImg == null) {
				returnFaceInterFail(CommonDict.ERROR_UNKNOW, "人脸识别系统没有返回数据", response);
			}
			// 把命中照片存本地
			fu = new FileUtil();
			logImagePath = commonConfig.logImagePath;
			String fileNameResultImg = fu.writeFile(resultImg, logImagePath);
			fileNameSendImg = fu.writeFile(sendImg, logImagePath);
			if (userId == null) {
				returnFaceInterFail(CommonDict.ERROR_NULL_USERID, "人脸识别系统返回的用户id是null数据", response);
			} else {

				Map<String, String> parMap = new HashMap<String, String>();
				parMap.put("custNo", userId);
				List<VipCustomerVo> vipCustomerVoList = null;
				VipCustomerVo vipCustomerVo = new VipCustomerVo();
				try {
//					把返回的custNo去vip用户表中查，如果存在，说明是vip，需要登记
					vipCustomerVoList = customerManagementService.findVipCustomerList(parMap);
				} catch (Exception e) {
					logger.error("查询vip_customer表出错");
					return returnFail("查询vip_customer表出错");
				}

				VipLogVo vipLogVo = new VipLogVo();
				// 不是vip用户
				if (vipCustomerVoList == null || vipCustomerVoList.size() == 0) {
//					vipLogVo.setProStatus("1");
					vipLogVo.setStatus("1");
					// vip用户
				} else {
//					vipLogVo.setProStatus("0");
					vipLogVo.setStatus("0");
					vipCustomerVo = vipCustomerVoList.get(0);
				}
				// 记录结束时间
				Long end = System.currentTimeMillis();
				Long computeTime = end - start;
				vipLogVo.setComputeTime(computeTime+"");
				vipLogVo.setCreateDate(DateUtil.getNowDate("yyyyMMddHHmmss")+"");
//				姓名取值方式用custNo查出来的vip用户信息中去取custName值
				vipLogVo.setCustNo(vipCustomerVo.getCreditNo());
//				证件号
				vipLogVo.setCustNo(userId);
				vipLogVo.setUnitCode(unitCode);
				vipLogVo.setFaceLogId(baseService.getSeq("vip_log"));
				// 这个字段待定:0成功、1失败；
				 vipLogVo.setStatus("0");
				vipLogVo.setResult(fileNameResultImg);
				vipLogVo.setSameScore(String.valueOf((Math.round(Float.valueOf(sameScore)))));
				vipLogVo.setSendImg(fileNameSendImg);
				vipLogVo.setVipCusId(userId);
				try {
					// 新增vip识别日志
					vipLogService.insert(vipLogVo);
				} catch (Exception e) {
					logger.error("插入数据库出错"+e.toString());
					return returnFail("插入数据库出错！");
				}
			}
			return resultImg;
			// 没有命中图片的情况
		} else {
			VipLogVo vipLogVo = new VipLogVo();
			// 记录结束时间
			Long end = System.currentTimeMillis();
			Long computeTime = end - start;
			vipLogVo.setComputeTime(computeTime+"");
			vipLogVo.setCreateDate(DateUtil.getNowDate("yyyyMMddHHmmss"));
			vipLogVo.setCustNo("");
			vipLogVo.setUnitCode(unitCode);
			vipLogVo.setFaceLogId(baseService.getSeq("vip_log"));
			// 这个字段待定:0成功、1失败；
			vipLogVo.setStatus("1");
			vipLogVo.setResult("");
			vipLogVo.setSameScore(String.valueOf((Math.round(Float.valueOf(0)))));
			vipLogVo.setSendImg(fileNameSendImg);
			vipLogVo.setVipCusId("");
			try {
				// 新增vip识别日志
				vipLogService.insert(vipLogVo);
			} catch (Exception e) {
				logger.error("插入数据库出错");
				return returnFail("插入数据库出错！");
			}
			logger.error(CommonDict.ERROR_UNKNOW + "调用接口人脸1比N接口搜索失败");
			return sendImg;
		}
	}

}

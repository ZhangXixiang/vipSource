package com.boomhope.check;


import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.boomhope.util.CheckUtil;
import com.boomhope.util.FcException;


@Component
@Lazy
public class InputCheckHand {

	/**
	 * 日志类
	 */
	private final static Logger logger = LoggerFactory.getLogger(InputCheckHand.class);
	
	/**
	 * 检查
	 * @param reqJson
	 * @throws FcException
	 * @return
	 */
	public JSONObject queyUseCameraBeforeCheck(JSONObject reqJson) throws FcException{
		JSONObject json = CheckUtil.checkJsonFormat(reqJson);
		if(reqJson == null){
			throw new FcException(CheckUtil.ERROR_FORMAT);
		}
		reqJson = json;
		// 必填项IP校验
		boolean ip = CheckUtil.checkIsNull(reqJson, "ip");
		if(ip){
			throw new FcException("必填项IP不可为空");
		}
		return json;
	}
	
	/**
	 * 调用VIP推送平台HTTP入参校验
	 * @param reqJson
	 * @return
	 * @throws FcException
	 */
	public JSONObject saveFaceImgBeforeCheck(JSONObject reqJson) throws FcException{
		JSONObject json = CheckUtil.checkJsonFormat(reqJson);
		if(reqJson == null){
			throw new FcException(CheckUtil.ERROR_FORMAT);
		}
		reqJson = json;
		boolean base64 = CheckUtil.checkIsNull(reqJson, "img1");
		boolean ipAddress = CheckUtil.checkIsNull(reqJson, "unitCode");
		if(base64){
			throw new FcException("必填项img1不可为空");
		}
		if(ipAddress){
			throw new FcException("必填项unitCode不可为空");
		}
		return json;
	}
}

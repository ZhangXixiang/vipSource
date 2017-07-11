package com.boomhope.util;

import net.sf.json.JSONObject;

/**
 * 参数检查类
 * @version 1.0 
 * @author zy
 *
 */
public class CheckUtil {
	
	/**
	 * XX必填项未填写
	 */
	public static String ERROR_MUST_INPUT = "101";
	
	/**
	 * 报文格式非法
	 */
	public static String ERROR_FORMAT = "100";

	/**
	 * 检查是否为空
	 * @param json
	 * @param ck
	 * @return
	 */
	public static boolean checkIsNull(JSONObject json,String ck){
		if(json.get(ck) == null){
			return true;
		}
		String ip = String.valueOf(json.get(ck));
		if("".equals(ip.trim())){
			return true;
		}
		return false;
	}
	
	/**
	 * json格式转化
	 * @param json
	 * @return
	 */
	public static JSONObject checkJsonFormat(JSONObject json){
		Object  obj =  json.get("params");
		if(obj == null){
			return null;
		}
		String params = String.valueOf(obj);
		return JSONObject.fromObject(params);
	}
}

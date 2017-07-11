package com.boomhope.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.github.pagehelper.PageInfo;


/**
 * 
 * 控制器父类,一些公共方法的定义
 * 
 * @version 1.0 2017-03-06 
 * 
 * @author zy
 * 
 *
 */
public class BaseAction {
	
	/**
	 * 日志类
	 */
	private final static Logger logger = LoggerFactory.getLogger(BaseAction.class);
	
	/**
	 * 返回成功（带参数）
	 * @return
	 */
	public JSONObject returnSucess(Map<Object,Object> map){
		Map<Object,Object> result = new HashMap<Object,Object>();
		result.put("success", true);
		result.putAll(map);
		return JSONObject.fromObject(result);
	}
	
	/**
	 * 返回成功
	 * @return
	 */
	public JSONObject returnSucess(){
		Map<Object,Object> result = new HashMap<Object,Object>();
		result.put("success", true);
		return JSONObject.fromObject(result);
	}
	
	/**
	 * 返回失败
	 * @return
	 */
	public JSONObject returnFail(String errmsg){
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("success", false);
		map.put("errmsg", errmsg);
		return JSONObject.fromObject(map);
	}
	
	/**
	 * 获取request中的数据，返回json格式
	 * @param request
	 * @return
	 */
	public JSONObject getReqData(HttpServletRequest request) {
		JSONObject jsonObj = new JSONObject();
		Enumeration<?> enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String paraName = (String) enu.nextElement();
			jsonObj.put(paraName, request.getParameter(paraName));
		}
		return jsonObj;
	}
	
	/**
	 * 分页返回查询记录
	 * @param pageInfo
	 * @param list
	 * @return
	 */
	public JSONObject returnResult(PageInfo pageInfo,List<?> list){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("rows", JSONArray.fromObject(list));
		result.put("total", pageInfo.getTotal());
		result.put("success", true);
		return JSONObject.fromObject(result); 
	}
	
	/**
	 * 分页返回查询记录
	 * @param pageInfo
	 * @param list
	 * @return
	 */
	public JSONObject returnResultM(JSONArray json){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("rows", json);
//		result.put("total", pageInfo.getTotal());
		result.put("success", true);
		return JSONObject.fromObject(result); 
	}
	
	/**
	 * 返回成功
	 * @param response
	 * @param resultMap
	 */
	public void returnFaceInterSucess(HttpServletResponse response,JSONObject resultMap){
		JSONObject map = new JSONObject();
		map.put("exCode", "0");
		map.put("exMsg", "操作成功");
		if(resultMap != null){
			map.putAll(resultMap);
		}
		responseMsg(response,map.toString());
	}
	
	/**
	 * 返回成功
	 * @param response
	 */
	public void returnFaceInterSucess(HttpServletResponse response){
		returnFaceInterSucess( response, null);
	}
	
	/**
	 * 返回成功
	 * @param code
	 * @param msg
	 * @param response
	 */
	public void returnFaceInterFail(String code,String msg,HttpServletResponse response){
		JSONObject map = new JSONObject();
		map.put("exCode", code);
		map.put("exMsg", msg);
		responseMsg(response,map.toString());
	}
	
	/**
	 * 返回httpclient客户端值
	 * @param response
	 * @param msg
	 */
	protected void responseMsg(HttpServletResponse response,String msg){
	    response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
	    PrintWriter out = null;  
	    try {  
	        out = response.getWriter();  
	        out.append(msg);  
	    } catch (IOException e) {  
	    	logger.error("error...", e);
	    } finally {  
	        if (out != null) {  
	            out.close();  
	        }  
	    } 
	}
	
}

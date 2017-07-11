package com.boomhope.service;

import com.boomhope.model.PushRuleVo;


/**
 * 
 * 监控服务层
 * @version 1.0 2017-03-09
 * @author zy
 *
 */
public interface IPushRuleService {
	
	public void insert(PushRuleVo pushRuleVo);
	
	public PushRuleVo selectByPrimaryKey(String str);
	
	public void updateByPrimaryKeySelective(PushRuleVo pushRuleVo);
	
//	
//	/**
//	 * 查询监控信息
//	 * @param map
//	 * @return
//	 */
////	public ControlInfoVo queryControlInfos(Map<String,String>  map);
//	
//	/**
//	 * 更新监控信息
//	 * @param controlInfoVo
//	 * @throws Exception 
//	 */
////	public void updateControlInfo(ControlInfoVo controlInfoVo) throws Exception;
//
//	/**
//	 * 查询设备部署信息列表       sql 查询
//	 * @param parMap
//	 * @return
//	 */
////	public List<TerminalMonitorVo> queryControlInfoVoList(Map<String, String> parMap);
//	
//	
//	/**
//	 * 插入control_info,添加测试数据
//	 */
////	public void insert(ControlInfoVo controlInfoVo) throws Exception;
//	
//	/**
//	 * 
//	 * @Description: TODO()   
//	 * @param: @param deployCode
//	 * @param: @return      
//	 * @return: List<ControlInfoVo>      
//	 * @throws   
//	 * @author:zhangxx
//	 */
////	public List<ControlInfoVo> selectByDeployCode(String deployCode);
//	
}

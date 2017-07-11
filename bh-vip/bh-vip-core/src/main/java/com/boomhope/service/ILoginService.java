package com.boomhope.service;

import com.boomhope.model.SysAdminVo;

/**
 * 登录相关业务流程处理层
 * @author zy
 *
 */
public interface ILoginService {
	
	/**
	 * 
	 * 查询用户是否存在
	 * @param loginName
	 * @return
	 */
	public SysAdminVo findSysAdmin(String loginName);
	
	
	
}

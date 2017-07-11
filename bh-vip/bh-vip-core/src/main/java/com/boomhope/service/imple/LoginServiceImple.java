package com.boomhope.service.imple;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boomhope.dao.SysAdminMapper;
import com.boomhope.model.SysAdminVo;
import com.boomhope.po.SysAdmin;
import com.boomhope.service.ILoginService;
@Transactional
@Service(value="loginService")
public class LoginServiceImple  implements ILoginService{
	
	@SuppressWarnings("unused")
	private final static Logger log = LoggerFactory.getLogger(SysServiceImple.class); 
	
	//用户
	@Resource(name="sysAdminMapper")
	SysAdminMapper sysAdminMapper;
	
	public void setSysAdminMapper(SysAdminMapper sysAdminMapper) {
		this.sysAdminMapper = sysAdminMapper;
	}
	
	@Override
	public SysAdminVo findSysAdmin(String loginName) {
		SysAdmin sa = sysAdminMapper.selectByPrimaryKey(loginName);
		if(sa == null ){
			return null;
		}
		SysAdminVo vo = new SysAdminVo();
		vo.setLoginName(sa.getLoginName());
		vo.setCreater(sa.getCreater());
		vo.setCreateDate(sa.getCreateDate());
		vo.setLoginStatus(sa.getLoginStatus());
		vo.setPassWord(sa.getPassWord());
		vo.setUserName(sa.getUserName());
		return vo;
	}
	

}

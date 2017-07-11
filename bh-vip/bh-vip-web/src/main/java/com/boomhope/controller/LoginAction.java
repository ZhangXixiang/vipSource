package com.boomhope.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boomhope.model.SysAdminVo;
import com.boomhope.service.ILoginService;
import com.boomhope.service.ISysService;
import com.boomhope.util.MD5Util;


/**
 * 登录控制器，登录相关操作由此方法实现
 * 
 * @version 1.0 2017-03-06
 * 
 * @author zy
 *
 */
@Controller
@Scope("prototype")
public class LoginAction extends BaseAction {
	/**
	 * 日志类
	 */
	private final static Logger logger = LoggerFactory.getLogger(LoginAction.class); 
	/**
	 * 登录服务类
	 */
	private ILoginService loginService;
	
	@Resource(name = "loginService")
	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}
	
	private ISysService sysService;
	
	@Resource(name = "sysService")
	public void setSysService(ISysService sysService) {
		this.sysService = sysService;
	}

	/**
	 * 登录验证
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login")
    public @ResponseBody Object login(HttpServletRequest request,HttpServletResponse response) throws Exception {
		// 将请求参数转换为json
		JSONObject json = getReqData(request);
		// 登录名
		String loginName = json.getString("loginName");
		// 登录密码
		String passWord = json.getString("passWord");
		// 获取登录密码
		SysAdminVo vo = null;
		try {
			vo = loginService.findSysAdmin(loginName);
		} catch (Exception e) {
			logger.error("查询出错",e);
			return returnFail("登录失败");
		}
		// 判断是否正确
		if(vo == null){
			return returnFail("登录用户不存在！");
		}
		String md5Pass = MD5Util.string2MD5(passWord);
		if(!vo.getPassWord().equals(md5Pass)){
			return returnFail("密码错误！");
		}
		if(vo.getLoginStatus().equals("0")){
			return returnFail("用户已被禁用！");
		}
		request.getSession().setAttribute("loginUser", vo);
		request.getSession().setAttribute("userName", vo.getUserName());
    	return returnSucess();
    	
    }
	
	/**
	 * 注销登陆
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/loginOut")
    public @ResponseBody Object loginOut(HttpServletRequest request,HttpServletResponse response) throws Exception {
		//防止创建Session  
		HttpSession session = request.getSession(false);
        if(session == null){  
            return returnSucess();
        }  
        session.removeAttribute("loginUser");  
        return returnSucess();
    }
	
	/**
	 * 修改密码
	 * @param request
	 * @return
	 */
	@RequestMapping("/modifyPwd")
	public @ResponseBody Object modifyPwd(HttpServletRequest request){
		SysAdminVo sysAdmin = (SysAdminVo) request.getSession().getAttribute("loginUser");
		JSONObject json = getReqData(request);
		String loginName = sysAdmin.getLoginName();
		String userName = sysAdmin.getUserName();
		String pwd = (String) json.get("pwd");
		String newpwd1 = MD5Util.string2MD5((String) json.get("newpwd"));
		if( !sysAdmin.getPassWord().equals(MD5Util.string2MD5(pwd)) ) {
			return this.returnFail("原密码错误！");
		}
		SysAdminVo sysAdminVo = new SysAdminVo();
		sysAdminVo.setLoginName(loginName);
		sysAdminVo.setPassWord(newpwd1);
		sysAdminVo.setUserName(userName);
		sysAdminVo.setToken("admin");
		sysAdminVo.setLoginStatus("1");
		sysAdminVo.setCreater(sysAdmin.getCreater());
		try {
			sysService.updatePassWordByPrimaryKey(sysAdminVo);
		} catch (Exception e) {
			logger.error("查询出错",e);
			return returnFail("查询失败");
		}
		return returnSucess();
			
	}

}

package com.boomhope.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boomhope.model.SysAdminVo;
import com.boomhope.model.SysMenuVo;
import com.boomhope.model.SysRoleVo;
import com.boomhope.model.SysUserRoleMapVo;
import com.boomhope.model.Tree;
import com.boomhope.service.IBaseService;
import com.boomhope.service.ISysService;
import com.boomhope.util.DateUtil;
import com.boomhope.util.MD5Util;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 系统管理模块功能控制器
 * 
 * @version 1.0 2017-03-06
 * 
 * @author zy
 *
 */
@Controller
@Scope("prototype")
public class SystemAction extends BaseAction {

	/**
	 * 日志类
	 */
	private final static Logger logger = LoggerFactory.getLogger(SystemAction.class);

	/**
	 * 系统服务类
	 */
	private ISysService sysService;

	@Resource(name = "sysService")
	
	public void setSysService(ISysService sysService) {
		this.sysService = sysService;
	}

	private IBaseService baseService;

	@Resource(name = "baseService")
	public void setBaseService(IBaseService baseService) {
		this.baseService = baseService;
	}

	/**
	 * 查询用户信息列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/sys/queryUsers")
	public @ResponseBody Object queryUserInfoList(@RequestBody Map map) {
		String userName = null;
		if (map.get("userName") != null) {
			userName = (String) map.get("userName");
		}

		Integer offset = Integer.parseInt(map.get("pageNumber").toString());
		Integer limit = Integer.parseInt(map.get("pageSize").toString());
		String orderBy = null;
		orderBy = "create_date desc";

		Map<String, String> parMap = new HashMap<String, String>();
		parMap.put("userName", userName);
		Page<SysAdminVo> paged = PageHelper.startPage(offset, limit, orderBy);
		try {
			sysService.findAllList(parMap);
		} catch (Exception e) {
			logger.error("查询失败",e);
			return returnFail("查询失败");
		}
		
		PageInfo<SysAdminVo> pageInfo = new PageInfo<SysAdminVo>(paged);
		return returnResult(pageInfo, pageInfo.getList());
		
	}

	/**
	 * 添加新用户
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/sys/addUser")
	public @ResponseBody Object addUser(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = getReqData(request);
		if (logger.isDebugEnabled()) {
			logger.debug("接入参数" + json.toString());
		}
		// 1.获取入参
		String loginName = (String) json.get("loginName");
		String passWord = (String) json.get("passWord");
		String userName = (String) json.get("userName");
		// String roleName = (String) json.get("roleName");
		// 2.查询当前登录名是否存在，存在则提示：用户名已存在，不可重复创建
		try {
			if (loginName != null && !"".equals(loginName) || userName != null  && !"".equals(userName) || passWord != null && !"".equals(passWord) ) {
				SysAdminVo sysAdmin = sysService.findSysAdminByPrimaryKey(loginName);
				if (sysAdmin == null) {

					// 3.保存
					SysAdminVo sa = new SysAdminVo();
					sa.setLoginName(loginName);
					sa.setUserName(userName);
					sa.setPassWord(MD5Util.string2MD5(passWord));
					sa.setLoginStatus("1");
					sa.setToken("admin");
					sa.setCreateDate(DateUtil.getNowDate("yyyyMMddHHmmss"));
					
					SysAdminVo sysAdmins = (SysAdminVo) request.getSession().getAttribute("loginUser");
					if (sysAdmins == null) {
						return returnFail("未知异常");
					}
					sa.setCreater(sysAdmins.getCreater());
					sysService.addSysAdmin(sa);
					
				} else {
					return returnFail("对不起,该用户已经存在了！");
				}
			} else {
				return returnFail("对不起,必填项必须不能为空");
			}
		} catch (Exception e) {
			logger.error("查询失败",e);
			return returnFail("查询失败");
		}
		
		return returnSucess();
	}

	/**
	 * 更新用户信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/sys/updateUser")
	public @ResponseBody Object updateUser(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = getReqData(request);
		if (logger.isDebugEnabled()) {
			logger.debug("接入参数" + json.toString());
		}

		// 1.获取入参
		String loginName = (String)json.get("loginName");
		String passWord = (String)json.get("passWord");
		String userName = (String)json.get("userName");
		// 2.查询当前登录名是否存在，存在则提示：用户名已存在，不可重复创建
		try {
			if ( loginName != null  && !"".equals(loginName) || userName != null &&  !"".equals(userName)  || passWord != null &&  !"".equals(passWord) ) {
				SysAdminVo sysAdmin = sysService.findSysAdminByPrimaryKey(loginName);
				if (sysAdmin == null) {
					return returnFail("对不起，用户名不对！");
				} else {

					SysAdminVo sys = new SysAdminVo();
					sys.setLoginName(loginName);
					sys.setUserName(userName);
					sys.setPassWord(MD5Util.string2MD5(passWord));
					sys.setLoginStatus(sysAdmin.getLoginStatus());
					sys.setToken(sysAdmin.getToken());
					sys.setCreateDate(DateUtil.getNowDate("yyyyMMddHHmmss"));

					SysAdminVo sysAdmins = (SysAdminVo) request.getSession().getAttribute("loginUser");
					sys.setCreater(sysAdmins.getCreater());
					sysService.updateSysAdmin(sys);
					logger.info("更新成功");
				}
			} else {
				returnFail("必填项不能为空！");
			}
		} catch (Exception e) {
			logger.error("查询失败",e);
			return returnFail("查询失败");
		}
		
		
		return returnSucess();
	}

	/**
	 * 删除指定选中用户
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/sys/deleteUser")
	public @ResponseBody Object deleteUser(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = getReqData(request);
		if (logger.isDebugEnabled()) {
			logger.debug("接入参数" + json.toString());
		}

		// 1.获取入参
		String userIds = (String)json.get("loginName");
		try {
			sysService.deleteAdminlist(userIds);
		} catch (Exception e) {
			logger.error("查询失败",e);
			return returnFail("查询失败");
		}
		return returnSucess();
		
	}

	//
	/**
	 * 启用、停用操作处理
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/sys/updateUserStatus")
	public @ResponseBody Object updateUserStatus(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = getReqData(request);
		if (logger.isDebugEnabled()) {
			logger.debug("接入参数" + json.toString());
		}
		// 1.获取入参
		String loginStatus = (String)json.get("loginStatus");
		String loginName = (String)json.get("loginName");
		SysAdminVo sysAdmin = null;
		try {
			sysAdmin = sysService.findSysAdminByPrimaryKey(loginName);
			// 2.更新用户状态（启用或停用）
			if ("0".equals(loginStatus)) {
				// 启用
				loginStatus = "1";
				sysAdmin.setLoginStatus(loginStatus);
			} else {
				// 停用
				loginStatus = "0";
				sysAdmin.setLoginStatus(loginStatus);
			}
			sysAdmin.setLoginStatus(loginStatus);
			sysService.updateLoginStatusByPrimaryKey(sysAdmin);
		} catch (Exception e) {
			logger.error("查询失败",e);
			return returnFail("查询失败");
		}
		
		return returnSucess();
	}

	/**
	 * 重置登录密码
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/sys/updateUserPass")
	public @ResponseBody Object updateUserPass(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = getReqData(request);
		if (logger.isDebugEnabled()) {
			logger.debug("接入参数" + json.toString());
		}
		// 1.获取入参
		String loginName = (String)json.get("loginName");
		String passWord = (String)json.get("passWord");
		SysAdminVo sysAdmin =null;
		try {
			sysAdmin = sysService.findSysAdminByPrimaryKey(loginName);
			if (!"".equals(sysAdmin.getPassWord()) && sysAdmin.getPassWord() != null) {
				if (!sysAdmin.getPassWord().equals(passWord)) {
					
					// 2.更新用户密码（采用md5加密密码）
					sysAdmin.setLoginName(loginName);
					sysAdmin.setUserName(sysService.findSysAdminByPrimaryKey(loginName).getUserName());
					sysAdmin.setPassWord(MD5Util.string2MD5(passWord));
					sysAdmin.setToken(sysService.findSysAdminByPrimaryKey(loginName).getToken());
					sysAdmin.setLoginStatus(sysService.findSysAdminByPrimaryKey(loginName).getLoginStatus());
					sysAdmin.setCreateDate(sysService.findSysAdminByPrimaryKey(loginName).getCreateDate());
					sysAdmin.setCreater(sysService.findSysAdminByPrimaryKey(loginName).getCreater());
					sysService.updateSysAdmin(sysAdmin);
					
				} else {
					return returnFail("不能与原始密码一样，请重置");
				}
			} else {
				return returnFail("密码不能为空,请重置");
			}
		} catch (Exception e) {
			logger.error("查询失败",e);
			return returnFail("查询失败");
		}
		
		return returnSucess();
	}

	/**
	 * 获取用户对应的角色
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/sys/GetUserRole")
	public @ResponseBody Object setUserRole(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = getReqData(request);
		if (logger.isDebugEnabled()) {
			logger.debug("接入参数" + json.toString());
		}
		// 1.获取入参
		String loginName = (String)json.get("loginName");
		// 获取全部角色
		List<SysRoleVo> list = null;
		List<SysRoleVo> sysRoles = null;
		try {
			// 获取对应用户的角色
			list = sysService.findAll();
			sysRoles = sysService.findUserRoleList(loginName);
			
		} catch (Exception e) {
			logger.error("查询失败",e);
			return returnFail("查询失败");
		}
		List<Tree> treeList = new ArrayList<Tree>();
		if (null != sysRoles && sysRoles.size() > 0) {
			for (SysRoleVo sysRole : list) {
				Tree tree = new Tree();
				tree.setId(sysRole.getRoleCode());
				tree.setName(sysRole.getRoleName());
				tree.setParentId("0");
				for (SysRoleVo sysRole1 : sysRoles) {
					// 如果角色的roleCode与用户对应的角色的roleCode相同 check为true
					if (sysRole.getRoleCode().equals(sysRole1.getRoleCode())) {
						tree.setChecked(true);
						break;
					} else {
						tree.setChecked(false);
					}
				}
				treeList.add(tree);
			}
		} else {
			for (SysRoleVo sysRole : list) {
				Tree tree = new Tree();
				tree.setId(sysRole.getRoleCode());
				tree.setName(sysRole.getRoleName());
				tree.setParentId("0");
				tree.setChecked(false);
				treeList.add(tree);
			}
		}
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("treeList", JSONArray.fromObject(treeList));
		return returnSucess(map);
	}

	/**
	 * 保存用户角色
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/sys/addUserRole")
	public @ResponseBody Object saveUserRole(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = getReqData(request);
		if (logger.isDebugEnabled()) {
			logger.debug("接入参数" + json.toString());
		}
		String loginName = (String)json.get("loginName");
		String roleCodes = String.valueOf(json.get("id"));
		if ("".equals(roleCodes) || roleCodes == null) {
			return returnFail("请选择设置的角色。");
		}
		String[] str = roleCodes.split(",");
		List<String> roleCodeList = new ArrayList<String>();
		for (int i = 0; i < str.length; i++) {
			roleCodeList.add(str[i]);
		}
		try {
			
			sysService.addSysUserRoleMap(loginName, roleCodeList);
		} catch (Exception e) {
			logger.error("查询失败",e);
			return returnFail("查询失败");
		}
		return returnSucess();
	}

	/**
	 * 删除角色信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/mac/deleteSysRole")
	public @ResponseBody Object deleteRoleInfo(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = getReqData(request);
		if (logger.isDebugEnabled()) {
			logger.debug("接入参数" + json.toString());
		}
		// 1.获取入参
		String roleCodes = (String)json.get("roleCode");
		if(roleCodes == null ){
			return returnFail("对不起,没有您没有选中的删除的行");
		}
		try {
			sysService.deleteSysRole(roleCodes);
		} catch (Exception e) {
			logger.error("查询失败",e);
			return returnFail("查询失败");
		}
		return returnSucess();
	}

	/**
	 * 编辑角色信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/mac/updateRole")
	public @ResponseBody Object updateRoleInfo(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = getReqData(request);
		if (logger.isDebugEnabled()) {
			logger.debug("接入参数" + json.toString());
		}
		// 1.获取入参
		String roleCode = (String) json.get("roleCode");
		String roleDesc = (String) json.get("roleDesc");
		String roleName = (String) json.get("roleName");
		if (roleCode != null && !"".equals(roleCode) ) {
			// 2.查询当前角色否存在，存在则提示错误
			SysRoleVo sysRole = null;
			try {
				sysRole = sysService.selectByPrimaryKey(roleCode);
			} catch (Exception e) {
				logger.error("查询失败",e);
				return returnFail("查询失败");
			}
			if (sysRole == null) {
				return returnFail("编辑对象不存在!");
			} else {
				// 3.保存
				SysRoleVo sys = new SysRoleVo();
				sys.setRoleCode(roleCode);
				sys.setRoleName(roleName);
				sys.setRoleDesc(roleDesc);
				sys.setCreateDate(DateUtil.getNowDate("yyyyMMddHHmmss"));
				SysAdminVo sysAdmin = (SysAdminVo) request.getSession().getAttribute("loginUser");
				if (sysAdmin == null) {
					return returnFail("未知异常");
				}
				sys.setCreater(sysAdmin.getCreater());
				try {
					sysService.updateSysRole(sys);
				} catch (Exception e) {
					logger.error("查询失败",e);
					return returnFail("查询失败");
				}
			}
		} else {
			return returnFail("对不起,必填项必须不能为空");
		}
		return returnSucess();
	}

	/**
	 * 查询角色信息列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/sys/queryRoles")
	public @ResponseBody Object queryRoleInfoList(@RequestBody Map map) {
		String roleName = null;
		if (map.get("roleName") != null) {
			roleName = (String) map.get("roleName");
		}
		Integer offset = Integer.parseInt(map.get("pageNumber").toString());
		Integer limit = Integer.parseInt(map.get("pageSize").toString());
		String orderBy = null;
		orderBy = "create_date desc";
		
		Map<String, String> parMap = new HashMap<String, String>();
		parMap.put("roleName", roleName);
		Page<SysRoleVo> paged = PageHelper.startPage(offset, limit, orderBy);
		try {
			sysService.findSysRoleList(parMap);
		} catch (Exception e) {
			logger.error("查询失败",e);
			return returnFail("查询失败");
		}
		PageInfo<SysRoleVo> pageInfo = new PageInfo<SysRoleVo>(paged);
		return returnResult(pageInfo, pageInfo.getList());

	}

	/**
	 * 添加角色信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/sys/addRole")
	public @ResponseBody Object addRole(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = getReqData(request);
		if (logger.isDebugEnabled()) {
			logger.debug("接入参数" + json.toString());
		}
		// 1.获取入参
		String roleName = (String)json.get("roleName");
		String roleDesc = (String)json.get("roleDesc");
		if (!"".equals(roleName) && roleName != null) {
			// 2.判断当角色名称是否存在，存在则提示：角色已存在，不可重复创建
			// modified by zhangxx,这里添加角色不需要模糊查询应该使用精确查询；
			List<SysRoleVo> sysRoleObject = null;
			try {
				sysRoleObject = sysService.selectByLikeRoleName(roleName);
			} catch (Exception e) {
				logger.error("查询失败",e);
				return returnFail("查询失败");
			}
			if (sysRoleObject != null && sysRoleObject.size() > 0) {
				return returnFail("对不起,角色已经存在,且不能重复添加!");
			} else {
				SysRoleVo sysRole = new SysRoleVo();
				
				sysRole.setRoleCode(baseService.getSeq("sys_role"));
				sysRole.setRoleName(roleName);
				sysRole.setRoleDesc(roleDesc);
				sysRole.setCreateDate(DateUtil.getNowDate("yyyyMMddHHmmss"));
				SysAdminVo sysAdmin = (SysAdminVo) request.getSession().getAttribute("loginUser");
				if (sysAdmin == null) {
					return returnFail("未知异常");
				}
				
				sysRole.setCreater(sysAdmin.getCreater());
				// 3.添加
				try {
					sysService.addSysRole(sysRole);
				} catch (Exception e) {
					logger.error("查询出错",e);
					return returnFail("查询失败");
				}
			}
		} else {
			returnFail("对不起,必填项不得为空！");
		}
		return returnSucess();
	}

	/**
	 * 设置角色菜单
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/sys/setRoleMenu")
	public @ResponseBody Object setRoleMenu(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = getReqData(request);
		if (logger.isDebugEnabled()) {
			logger.debug("接入参数" + json.toString());
		}
		// 1.获取入参
		String roleCode = (String)json.get("roleCode");
		String menuIds = (String)json.get("menuIds");
		// 2.设置角色对应的菜单权限
		List<String> menuIdList = new ArrayList<String>();
		String[] str = menuIds.split(",");
		for (int i = 0; i < str.length; i++) {
			menuIdList.add(str[i]);
		}
		try {
			sysService.addSysRoleMenu(roleCode, menuIdList);
		} catch (Exception e) {
			logger.error("查询失败",e);
			return returnFail("查询失败");
		}
		return returnSucess();
	}

	/**
	 * 获取角色权限
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/sys/getRolePrivilege")
	public @ResponseBody Object getRolePrivilege(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = getReqData(request);
		if (logger.isDebugEnabled()) {
			logger.debug("接入参数" + json.toString());
		}
		String roleCodes = (String)json.get("roleCode");
		// 获取所有的菜单
		List<SysMenuVo> list = null;
		// 获取对应角色对应的菜单
		List<SysMenuVo> roleMenuList = null;
		try {
			
		list = sysService.findAllMenu();
			// 获取对应角色对应的菜单
		roleMenuList = sysService.findRoleMenuList(roleCodes);
		} catch (Exception e) {
			logger.error("查询失败",e);
			return returnFail("查询失败");
		}
		List<Tree> treeList = new ArrayList<Tree>();
		if (null != roleMenuList && roleMenuList.size() > 0) {
			for (SysMenuVo sysMenu : list) {
				Tree tree = new Tree();
				tree.setId(sysMenu.getMenuId());
				tree.setName(sysMenu.getMenuName());
				tree.setParentId(sysMenu.getParentId());
				tree.setMenuPath(sysMenu.getMenuPath());
				for (SysMenuVo sysRoleMenu : roleMenuList) {
					if (sysRoleMenu.getMenuId().equals(sysMenu.getMenuId())) {
						tree.setChecked(true);
						break;
					} else {
						tree.setChecked(false);
					}
				}
				treeList.add(tree);
			}
		} else {
			for (SysMenuVo sysMenu : list) {
				Tree tree = new Tree();
				tree.setId(sysMenu.getMenuId());
				tree.setName(sysMenu.getMenuName());
				tree.setParentId(sysMenu.getParentId());
				tree.setChecked(false);
				tree.setMenuPath(sysMenu.getMenuPath());
				treeList.add(tree);
			}
		}
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("treeList", JSONArray.fromObject(treeList));
		return returnSucess(map);
	}

	/**
	 * 保存角色权限
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping("/sys/addRolePrivilege")
	public @ResponseBody Object addRoleMenu(HttpServletRequest request) {
		JSONObject json = getReqData(request);
		if (logger.isDebugEnabled()) {
			logger.debug("接入参数" + json.toString());
		}
		String roleCode = (String)json.get("roleCode");
		String menuIds = (String)json.get("id");

		if ("".equals(menuIds) || menuIds == null) {
			return returnFail("请选择设置权限项。");
		}
		List<String> list = new ArrayList<String>();
		String[] str = menuIds.split(",");
		for (int i = 0; i < str.length; i++) {
			list.add(str[i]);
		}
		try {
			sysService.addSysRoleMenu(roleCode, list);
		} catch (Exception e) {
			logger.error("查询失败",e);
			return returnFail("查询失败");
		}
		return returnSucess();
	}
	
	
	
	
	/**
	 * 获取当前登录用户的角色权限父节点获取；
	 * added by zhangxx
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping("/sys/getLogRolePrivilege")
	public @ResponseBody Object getLogUserPrivilege(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = getReqData(request);
		if (logger.isDebugEnabled()) {
			logger.debug("接入参数" + json.toString());
		}
		
//		从session中获取当前登陆的用户
		SysAdminVo sysAdmin = (SysAdminVo) request.getSession().getAttribute("loginUser");
		if(sysAdmin == null){
			return returnFail("未知异常");
		}
		
        //获取到loginName到SYS_USER_ROLE_MAP表中查出roleCode（这里是一对一）
		String loginName = sysAdmin.getLoginName();
		
        //调用zhangxx写的loginName查询roleCode
		List<SysUserRoleMapVo> sysUserRoleMapVoList = null;
		try {
			sysUserRoleMapVoList = sysService.selectByLoginName(loginName);
		} catch (Exception e) {
			logger.error("查询失败",e);
			return returnFail("查询失败");
		}
		if(sysUserRoleMapVoList == null && sysUserRoleMapVoList.size() <=0){
			returnFail("用户没有任何权限");
		}
		List<String> roleCodes = new ArrayList<String>();
			for (SysUserRoleMapVo sysUserRoleMapVo : sysUserRoleMapVoList) {
				roleCodes.add(sysUserRoleMapVo.getRoleCode());
		    }
		// 获取所有的菜单
			List<SysMenuVo> list = null;
		try {
			list = sysService.findAllMenu();
		} catch (Exception e) {
			logger.error("查询失败",e);
			return returnFail("查询失败");
		}
//		//当前用户可能有多个角色
//		String roleCode = roleCodes.get(0);
//		
		List<Tree> treeEnd = new ArrayList<Tree>();
		List<Tree> treeEnd1 = new ArrayList<Tree>();
		for(int i=0;i<roleCodes.size();i++){
			//当前用户可能有多个角色
			String roleCode = roleCodes.get(i);
			// 获取对应角色对应的菜单
			List<SysMenuVo> roleMenuList =null;
			try {
				roleMenuList = sysService.findRoleMenuList(roleCode);
			} catch (Exception e) {
				logger.error("查询失败",e);
				return returnFail("查询失败");
			}
			List<Tree> treeList = new ArrayList<Tree>();
			if (null != roleMenuList && roleMenuList.size() > 0) {
				for (SysMenuVo sysMenu : list) {
					Tree tree = new Tree();
					tree.setId(sysMenu.getMenuId());
					tree.setName(sysMenu.getMenuName());
					tree.setParentId(sysMenu.getParentId());
					tree.setMenuPath(sysMenu.getMenuPath());
					for (SysMenuVo sysRoleMenu : roleMenuList) {
						if (sysRoleMenu.getMenuId().equals(sysMenu.getMenuId())) {
							tree.setChecked(true);
							break;
						} else {
							tree.setChecked(false);
						}
					}
					treeList.add(tree);
				}
			} else {
				for (SysMenuVo sysMenu : list) {
					Tree tree = new Tree();
					tree.setId(sysMenu.getMenuId());
					tree.setName(sysMenu.getMenuName());
					tree.setParentId(sysMenu.getParentId());
					tree.setChecked(false);
					tree.setMenuPath(sysMenu.getMenuPath());
					treeList.add(tree);
				}
			}
			
            //过滤掉checked字段是false的
			for (Tree tree : treeList) {
				if(tree.isChecked() == true && tree.getParentId().equals("0")){
					treeEnd.add(tree);
				}
			}
			
			//去重menu_id
			for (int h = 0; h < treeEnd.size()-1; h++) {
	            for (int j = treeEnd.size()-1; j > h; j--) {
	                if (treeEnd.get(j).getId() == treeEnd.get(h).getId()) {
	                	treeEnd.remove(j);
	                }
	            }
	        }
		}
		
//		list排序：在一个用户拥有多个角色，此时需要对几个角色汇总的菜单id进行排序；采用Collections提供的第二种排序方法sort(List<T> list, Comparator<? super T> c) added by zhangxx
        Collections.sort(treeEnd, new Comparator<Tree>() {  
        	  @Override
            public int compare(Tree tree1, Tree tree2) {  
                int i = Integer.valueOf(tree1.getId()) - Integer.valueOf(tree2.getId());  
                return i;  
            }  
        }); 
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("treeList", JSONArray.fromObject(treeEnd));
		return returnSucess(map);
	}
	
	
	/**
	 * 获取当前登录用户的角色权限对应父节点的子节点获取；
	 * added by zhangxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/sys/getLogRolePrivilegeSon")
	public @ResponseBody Object getLogUserPrivilegeSon(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = getReqData(request);
		if (logger.isDebugEnabled()) {
			logger.debug("接入参数" + json.toString());
		}
		
		String id = (String)json.get("id");
		
//		从session中获取当前登陆的用户
		SysAdminVo sysAdmin = (SysAdminVo) request.getSession().getAttribute("loginUser");
		if(sysAdmin == null){
			return returnFail("未知异常");
		}
		
//		获取到loginName到SYS_USER_ROLE_MAP表中查出roleCode（这里是一对一）
		String loginName = sysAdmin.getLoginName();
//		调用zhangxx写的loginName查询roleCode
		List<SysUserRoleMapVo> sysUserRoleMapVoList = null;
		try {
			sysUserRoleMapVoList = sysService.selectByLoginName(loginName);
		} catch (Exception e) {
			logger.error("查询失败",e);
			return returnFail("查询失败");
		}
		List<String> roleCodes = new ArrayList<String>();
		for (SysUserRoleMapVo sysUserRoleMapVo : sysUserRoleMapVoList) {
			roleCodes.add(sysUserRoleMapVo.getRoleCode());
		}
		
        //这个地方需要调查一下，当前用户可能有多个角色
//		String roleCode = roleCodes.get(0);
		
		// 获取所有的菜单
		List<SysMenuVo> list = null;
		try {
			list = sysService.findAllMenu();
		} catch (Exception e) {
			logger.error("查询失败",e);
			return returnFail("查询失败");
		}
		List<Tree> treeEnd = new ArrayList<Tree>();
		List<Tree> treeEnd1 = new ArrayList<Tree>();
		// 获取对应角色对应的菜单
		for(int i=0;i<roleCodes.size();i++){
			String roleCode = roleCodes.get(i);
			List<SysMenuVo> roleMenuList = null;
			try {
				roleMenuList = sysService.findRoleMenuList(roleCode);
			} catch (Exception e) {
				logger.error("查询失败",e);
				return returnFail("查询失败");
			}
			List<Tree> treeList = new ArrayList<Tree>();
			if (null != roleMenuList && roleMenuList.size() > 0) {
				for (SysMenuVo sysMenu : list) {
					Tree tree = new Tree();
					tree.setId(sysMenu.getMenuId());
					tree.setName(sysMenu.getMenuName());
					tree.setParentId(sysMenu.getParentId());
					tree.setMenuPath(sysMenu.getMenuPath());
					for (SysMenuVo sysRoleMenu : roleMenuList) {
						if (sysRoleMenu.getMenuId().equals(sysMenu.getMenuId())) {
							tree.setChecked(true);
							break;
						} else {
							tree.setChecked(false);
						}
					}
					treeList.add(tree);
				}
			} else {
				for (SysMenuVo sysMenu : list) {
					Tree tree = new Tree();
					tree.setId(sysMenu.getMenuId());
					tree.setName(sysMenu.getMenuName());
					tree.setParentId(sysMenu.getParentId());
					tree.setChecked(false);
					tree.setMenuPath(sysMenu.getMenuPath());
					treeList.add(tree);
				}
			}
			
            //过滤掉checked字段是false的,且根据前台传的id搜索对应的子节点是true的；
			for (Tree tree : treeList) {
				if(tree.isChecked() == true && tree.getParentId().equals(id)){
					treeEnd.add(tree);
				}
			}
			
			//去掉子节重复的
			for (int h = 0; h < treeEnd.size()-1; h++) {
	            for (int j = treeEnd.size()-1; j > h; j--) {
	                if (treeEnd.get(j).getId() == treeEnd.get(h).getId()) {
	                	treeEnd.remove(j);
	                }
	            }
	        }
		}
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("treeList", JSONArray.fromObject(treeEnd));
		return returnSucess(map);
	}
	
}

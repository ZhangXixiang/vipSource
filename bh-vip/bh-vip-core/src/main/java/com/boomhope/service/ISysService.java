package com.boomhope.service;

import java.util.List;
import java.util.Map;

import com.boomhope.model.SysAdminVo;
import com.boomhope.model.SysMenuVo;
import com.boomhope.model.SysRoleVo;
import com.boomhope.model.SysUserRoleMapVo;
import com.boomhope.po.SysMenu;

/**
 * 系统管理相关服务,包括：添加用户角色、添加角色、添加角色菜单、删除角色、配置角色菜单；
 * 
 * @version 1.0 2017-03-07
 * @author zy
 *
 */
public interface ISysService extends IBaseService {

	/**
	 * 查询用户
	 * 
	 * @param loginName
	 */
	public SysAdminVo findSysAdminByPrimaryKey(String loginName);

	/**
	 * 添加用户
	 * 
	 * @param sa
	 */
	public void addSysAdmin(SysAdminVo sa);

	/**
	 * 删除多个用户
	 * 
	 * @param loginName
	 */
	public void deleteAdminlist(String userIds);

	/**
	 * 查询用户状态
	 * 
	 * @param loginName
	 */
	public List<String> selectStatusByloginName(String userIds);

	/**
	 * 停用
	 * 
	 * @param sysAdmin
	 */
	public void updateLoginStatusByPrimaryKey(SysAdminVo sysAdmin);

	/**
	 * 用户分页查询
	 * 
	 * @param map
	 * @return
	 */
	public List<SysAdminVo> findAllList(Map<String, String> map);

	/**
	 * 更新用户
	 * 
	 * @param sysAdmin
	 */
	public void updateSysAdmin(SysAdminVo sysAdmin);

	/**
	 * 重置密码
	 * 
	 * @param sysAdmin
	 */
	public void updatePassWordByPrimaryKey(SysAdminVo sysAdminVo);

	/**
	 * 设置用户角色
	 * 
	 * @param loginName
	 * @param roleCodeList
	 */
	public void addSysUserRoleMap(String loginName, List<String> roleCodeList);

	/**
	 * 删除用户角色
	 * 
	 * @param loginName
	 */
	public void deleteSysUserRoleMapBy(String loginName);

	/**
	 * 增加角色
	 * 
	 * @param sysRole
	 */
	public void addSysRole(SysRoleVo sysRole);

	/**
	 * 删除角色
	 * 
	 * @param roleCode
	 */
	public void deleteSysRole(String roleCodes);

	/**
	 * 修改角色
	 * 
	 * @param sysRole
	 */
	public void updateSysRole(SysRoleVo sysRole);

	/**
	 * 获取菜单
	 * 
	 * @param sysRole
	 */
	public List<SysMenuVo> findAllMenu();

	/**
	 * 获取角色菜单
	 * 
	 * @param roleCode
	 * @param menuId
	 */
	public List<SysMenuVo> findRoleMenuList(String roleCodes);

	/**
	 * 设置角色菜单
	 * 
	 * @param roleCode
	 * @param menuId
	 */
	public void addSysRoleMenu(String roleCode, List<String> menuIdList);

	/**
	 * 添加菜单
	 * 
	 * @param roleCode
	 * @param menuId
	 */
	public void addSysMenu(String roleCode, List<String> menuIdList);

	/**
	 * 删除角色菜单
	 * 
	 * @param roleCode
	 */
	public void deleteSysRoleMenuByRoleCode(String roleCode);

	/**
	 * 角色分页查询
	 * 
	 * @param map
	 * @return
	 */
	public List<SysRoleVo> findSysRoleList(Map<String, String> map);

	/**
	 * 角色查询
	 * 
	 * @param
	 * @return list
	 */
	public List<SysRoleVo> findAll();

	/**
	 * 指定用户角色查询
	 * 
	 * @param
	 * @return list
	 */
	public List<SysRoleVo> findUserRoleList(String string);

	/**
	 * 根据roleCode查询角色信息
	 * 
	 * @param roleCode
	 * @return
	 */
	public SysRoleVo selectByPrimaryKey(String roleCode);

	/**
	 * 根据角色名获取角色信息
	 * 
	 * @param roleName
	 * @return
	 */
	public List<SysRoleVo> selectByLikeRoleName(String roleName);

	/**
	 * 通过登录名查询角色Code
	 * @param loginName
	 * @return
	 */
	List<SysUserRoleMapVo> selectByLoginName(String loginName);

}

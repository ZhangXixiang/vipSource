package com.boomhope.service.imple;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boomhope.dao.SysAdminMapper;
import com.boomhope.dao.SysMenuMapper;
import com.boomhope.dao.SysRoleMapper;
import com.boomhope.dao.SysRoleMenuMapper;
import com.boomhope.dao.SysUserRoleMapMapper;
import com.boomhope.model.SysAdminVo;
import com.boomhope.model.SysMenuVo;
import com.boomhope.model.SysRoleVo;
import com.boomhope.model.SysUserRoleMapVo;
import com.boomhope.po.SysAdmin;
import com.boomhope.po.SysMenu;
import com.boomhope.po.SysRole;
import com.boomhope.po.SysRoleMenu;
import com.boomhope.po.SysUserRoleMap;
import com.boomhope.service.IBaseService;
import com.boomhope.service.ISysService;

/**
 * 系统管理相关服务,包括：添加用户角色、添加角色、添加角色菜单、删除角色、配置角色菜单；
 * 
 * @author Mr.wei
 */
@Transactional
@Service(value = "sysService")
public class SysServiceImple extends BaseServiceImple implements ISysService {

	@SuppressWarnings("unused")
	private final static Logger log = LoggerFactory
			.getLogger(SysServiceImple.class);

	// 角色Mapper
	@Resource(name = "sysRoleMapper")
	private SysRoleMapper sysRoleMapper;

	// 角色菜单Mapper
	@Resource(name = "sysRoleMenuMapper")
	private SysRoleMenuMapper sysRoleMenuMapper;

	// 用户角色Mapper
	@Resource(name = "sysUserRoleMapMapper")
	private SysUserRoleMapMapper sysUserRoleMapMapper;

	// 用户
	@Resource(name = "sysAdminMapper")
	SysAdminMapper sysAdminMapper;

	// 菜单
	@Resource(name = "sysMenuMapper")
	SysMenuMapper sysMenuMapper;

	private IBaseService baseService;

	@Resource(name = "baseService")
	public void setBaseService(IBaseService baseService) {
		this.baseService = baseService;
	}

	/**
	 * 根据登陆名查询
	 * 
	 */
	@Override
	public SysAdminVo findSysAdminByPrimaryKey(String loginName) {
		SysAdmin sysAdmin = sysAdminMapper.selectByPrimaryKey(loginName);
		if (sysAdmin == null) {
			return null;
		}
		log.info("sysAdmin" + sysAdmin.getUserName());
		SysAdminVo sysAdminVo = new SysAdminVo();
		sysAdminVo.setLoginName(loginName);
		sysAdminVo.setUserName(sysAdmin.getUserName());
		sysAdminVo.setPassWord(sysAdmin.getPassWord());
		sysAdminVo.setToken(sysAdmin.getToken());
		sysAdminVo.setLoginStatus(sysAdmin.getLoginStatus());
		sysAdminVo.setCreateDate(sysAdmin.getCreateDate());
		sysAdminVo.setCreater(sysAdmin.getCreater());
		return sysAdminVo;

	}

	/**
	 * 添加用户
	 */
	@Override
	public void addSysAdmin(SysAdminVo sysAdminVo) {
		SysAdmin sysAdmin = new SysAdmin();
		sysAdmin.setLoginName(sysAdminVo.getLoginName());
		sysAdmin.setUserName(sysAdminVo.getUserName());
		sysAdmin.setPassWord(sysAdminVo.getPassWord());
		sysAdmin.setLoginStatus(sysAdminVo.getLoginStatus());
		sysAdmin.setToken(sysAdminVo.getToken());
		sysAdmin.setCreateDate(sysAdminVo.getCreateDate());
		sysAdmin.setCreater(sysAdminVo.getCreater());
		sysAdminMapper.insert(sysAdmin);
	}

	/**
	 * 停用
	 */
	@Override
	public void updateLoginStatusByPrimaryKey(SysAdminVo sysAdminVo) {
		SysAdmin sysAdmin = new SysAdmin();
		sysAdmin.setLoginName(sysAdminVo.getLoginName());
		sysAdmin.setUserName(sysAdminVo.getUserName());
		sysAdmin.setPassWord(sysAdminVo.getPassWord());
		sysAdmin.setToken(sysAdminVo.getToken());
		sysAdmin.setLoginStatus(sysAdminVo.getLoginStatus());
		sysAdmin.setCreateDate(sysAdminVo.getCreateDate());
		sysAdmin.setCreater(sysAdminVo.getCreater());
		sysAdminMapper.updateByPrimaryKey(sysAdmin);
	}

	/**
	 * 用户状态
	 */
	@Override
	public List<String> selectStatusByloginName(String userIds) {
		String[] ids = userIds.split(",");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < ids.length; i++) {
			list.add(ids[i]);
		}
		return sysAdminMapper.selectStatusByloginName(list);
	}

	/**
	 * 删除多个用户
	 */
	@Override
	public void deleteAdminlist(String userIds) {
		String[] ids = userIds.split(",");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < ids.length; i++) {
			list.add(ids[i]);
			// 删除用户角色表
			sysUserRoleMapMapper.deleteByLoginName(ids[i]);
		}
		sysAdminMapper.deleteAdminList(list);
	}

	/**
	 * 更新用户
	 */
	@Override
	public void updateSysAdmin(SysAdminVo sysAdminVo) {

		SysAdmin sysAdmin = new SysAdmin();

		sysAdmin.setLoginName(sysAdminVo.getLoginName());
		sysAdmin.setUserName(sysAdminVo.getUserName());
		sysAdmin.setPassWord(sysAdminVo.getPassWord());
		sysAdmin.setLoginStatus(sysAdminVo.getLoginStatus());
		sysAdmin.setToken(sysAdminVo.getToken());
		sysAdmin.setCreateDate(sysAdminVo.getCreateDate());
		sysAdmin.setCreater(sysAdminVo.getCreater());

		sysAdminMapper.updateByPrimaryKey(sysAdmin);

	}

	/**
	 * 增加角色
	 */
	@Override
	public void addSysRole(SysRoleVo sysRoleVo) {
		SysRole sysRole = new SysRole();
		sysRole.setRoleCode(sysRoleVo.getRoleCode());
		sysRole.setRoleName(sysRoleVo.getRoleName());
		sysRole.setRoleDesc(sysRoleVo.getRoleDesc());
		sysRole.setCreateDate(sysRoleVo.getCreateDate());
		sysRole.setCreater(sysRoleVo.getCreater());
		sysRoleMapper.insert(sysRole);
	}

	/**
	 * 删除角色 roleCode 主键
	 */
	@Override
	public void deleteSysRole(String roleCodes) {
		String[] str = roleCodes.split(",");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < str.length; i++) {
			list.add(str[i]);
			// 1、先删除字表-角色菜单表 sys_role_menu
			sysRoleMenuMapper.deleteByRoleCode(str[i]);
		}
		// 2、再删除父表-角色表 sys_role
		sysRoleMapper.deleteByPrimaryKey(list);
	}

	/**
	 * 修改角色
	 */
	@Override
	public void updateSysRole(SysRoleVo sysRoleVo) {
		SysRole sysRole = new SysRole();
		sysRole.setRoleCode(sysRoleVo.getRoleCode());
		sysRole.setRoleName(sysRoleVo.getRoleName());
		sysRole.setRoleDesc(sysRoleVo.getRoleDesc());
		sysRole.setCreateDate(sysRoleVo.getCreateDate());
		sysRole.setCreater(sysRoleVo.getCreater());
		sysRoleMapper.updateByPrimaryKey(sysRole);
	}

	/**
	 * 删除用户角色
	 */
	@Override
	public void deleteSysUserRoleMapBy(String loginNames) {
		// 根据登陆名获取该名下所有的用户角色
		String[] str = loginNames.split(",");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < str.length; i++) {
			list.add(str[i]);
			sysUserRoleMapMapper.deleteByLoginName(str[i]);
		}

	}

	/**
	 * 重置密码
	 * modified by zhangxx
	 */
	@Override
	public void updatePassWordByPrimaryKey(SysAdminVo sysAdminVo) {
		
		SysAdmin sysAdmin = new SysAdmin();

		sysAdmin.setLoginName(sysAdminVo.getLoginName());
		sysAdmin.setUserName(sysAdminVo.getUserName());
		sysAdmin.setPassWord(sysAdminVo.getPassWord());
		sysAdmin.setLoginStatus(sysAdminVo.getLoginStatus());
		sysAdmin.setToken(sysAdminVo.getToken());
		sysAdmin.setCreateDate(sysAdminVo.getCreateDate());
		sysAdmin.setCreater(sysAdminVo.getCreater());
		
		sysAdminMapper.updateByPrimaryKey(sysAdmin);
	}

	/**
	 * 删除角色菜单 roleCode 主键
	 */
	@Override
	public void deleteSysRoleMenuByRoleCode(String roleCode) {
		sysRoleMenuMapper.deleteByPrimaryKey(roleCode);
	}

	/**
	 * 设置用户角色
	 * 需要设置Vo来处理Service层；
	 * 
	 */
	@Override
	public void addSysUserRoleMap(String loginName, List<String> roleCodeList) {
		// 1、先删除该登陆名下的角色
		sysUserRoleMapMapper.deleteByLoginName(loginName);
		// 2、再重新给用户分配角色
		List<SysUserRoleMap> sysUserRoleMapList = new ArrayList<SysUserRoleMap>();
		for (int i = 0; i < roleCodeList.size(); i++) {
			SysUserRoleMap sysUserRole = new SysUserRoleMap();
			sysUserRole.setId(baseService.getSeq("sys_user_role_map"));
			sysUserRole.setLoginName(loginName);
			sysUserRole.setRoleCode(roleCodeList.get(i));
			sysUserRoleMapList.add(sysUserRole);
		}
		sysUserRoleMapMapper.saveUserRoleList(sysUserRoleMapList);

	}

	/**
	 * 设置角色菜单 roleCode 角色表 主键 menuIdList
	 */
	@Override
	public void addSysRoleMenu(String roleCode, List<String> menuIdList) {
		// 1、删除角色下的菜单
		sysRoleMenuMapper.deleteByPrimaryKey(roleCode);
		// 2、重新添加角色菜单
		List<SysRoleMenu> sysRoleMenuList = new ArrayList<SysRoleMenu>();
		for (int i = 0; i < menuIdList.size(); i++) {
			SysRoleMenu record = new SysRoleMenu();
			record.setId(baseService.getSeq("sys_role_menu"));
			record.setMenuId(menuIdList.get(i));
			record.setRoleCode(roleCode);
			sysRoleMenuList.add(record);
		}
		sysRoleMenuMapper.saveRoleMenuList(sysRoleMenuList);
	}

	/**
	 * 查询角色信息
	 */
	@Override
	public List<SysRoleVo> findSysRoleList(Map<String, String> map) {
		List<SysRole> list = sysRoleMapper.findSysRoleList(map);
		if (list == null || list.size() == 0) {
			return null;
		}
		List<SysRoleVo> voList = new ArrayList<SysRoleVo>();
		for (int i = 0; i < list.size(); i++) {
			SysRole sysRole = list.get(i);
			SysRoleVo sysRoleVo = new SysRoleVo();
			sysRoleVo.setCreateDate(sysRole.getCreateDate());
			sysRoleVo.setRoleCode(sysRole.getRoleCode());
			sysRoleVo.setRoleDesc(sysRole.getRoleDesc());

			sysRoleVo.setCreater(sysRole.getCreater());
			sysRoleVo.setRoleName(sysRole.getRoleName());
			voList.add(sysRoleVo);
		}
		return voList;
	}

	/**
	 * 根据主键获取角色信息 roleCode 角色表 主键
	 */
	@Override
	public SysRoleVo selectByPrimaryKey(String roleCode) {
		Integer roleNumber = Integer.parseInt(roleCode);
		SysRole sysRole = sysRoleMapper.selectByPrimaryKey(roleNumber);
		SysRoleVo sysRoleVo = new SysRoleVo();
		sysRoleVo.setRoleCode(sysRole.getRoleCode());
		sysRoleVo.setRoleName(sysRole.getRoleName());
		sysRoleVo.setRoleDesc(sysRole.getRoleDesc());
		sysRoleVo.setCreateDate(sysRole.getUpdateDate());
		sysRoleVo.setCreater(sysRole.getCreater());
		return sysRoleVo;
	}

	/**
	 * 根据角色名称获取角色信息
	 */
	@Override
	public List<SysRoleVo> selectByLikeRoleName(String roleName) {
		List<SysRole> sysRole = sysRoleMapper.selectByLikeRoleName(roleName);
		if (sysRole == null || sysRole.size() == 0) {
			return null;
		}
		
		List<SysRoleVo> sysRoleVoList = new ArrayList<SysRoleVo>();
		for (int i = 0; i < sysRole.size(); i++) {
			SysRoleVo sysRoleVo = new SysRoleVo();
			sysRoleVo.setRoleCode(sysRole.get(i).getRoleCode());
			sysRoleVo.setRoleName(sysRole.get(i).getRoleName());
			sysRoleVo.setRoleDesc(sysRole.get(i).getRoleDesc());
			sysRoleVo.setCreateDate(sysRole.get(i).getCreateDate());
			sysRoleVo.setCreater(sysRole.get(i).getCreater());
			sysRoleVoList.add(sysRoleVo);
		}
		return sysRoleVoList;
	}

	/**
	 * 用户分页查询
	 */
	@Override
	public List<SysAdminVo> findAllList(Map<String, String> Map) {
		List<SysAdmin> list = sysAdminMapper.selectAllList(Map);
		if (list == null || list.size() == 0) {
			return null;
		}
		List<SysAdminVo> voList = new ArrayList<SysAdminVo>();
		for (int i = 0; i < list.size(); i++) {
			SysAdmin sr = list.get(i);
			SysAdminVo vo = new SysAdminVo();
			vo.setCreateDate(sr.getCreateDate());
			vo.setCreater(sr.getCreater());
			vo.setLoginName(sr.getLoginName());
			vo.setPassWord(sr.getPassWord());
			vo.setUserName(sr.getUserName());
			vo.setLoginStatus(sr.getLoginStatus());
			vo.setToken(sr.getToken());
			voList.add(vo);
		}
		return voList;
	}

	/**
	 * 角色查询
	 */
	@Override
	public List<SysRoleVo> findAll() {
		List<SysRole> list = sysRoleMapper.selectAll();
		if (list == null || list.size() == 0) {
			return null;
		}
		List<SysRoleVo> list1 = new ArrayList<SysRoleVo>();
		for (int i = 0; i < list.size(); i++) {
			SysRoleVo sysRole = new SysRoleVo();
			sysRole.setRoleCode(list.get(i).getRoleCode());
			sysRole.setRoleName(list.get(i).getRoleName());
			sysRole.setRoleDesc(list.get(i).getRoleDesc());
			sysRole.setCreateDate(list.get(i).getCreateDate());
			sysRole.setCreater(list.get(i).getCreater());
			list1.add(sysRole);
		}
		return list1;
	}

	/**
	 * 指定用户角色查询
	 */
	@Override
	public List<SysRoleVo> findUserRoleList(String loginName) {
		
		if(loginName == null || loginName.equals("")){
			return null;
		}
		
		// 查询用户角色表的id
		String str[] = loginName.split(",");
		List<String> ss = new ArrayList<String>();
		for (int i = 0; i < str.length; i++) {
			ss.add(str[i]);
		}
		List<SysUserRoleMap> list = sysUserRoleMapMapper.selectByLoginName(ss);
		if (list == null || list.size() == 0) {
			return null;
		}
		
		List<String> list1 = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			list1.add(list.get(i).getRoleCode());
		}
		// 获取对应用户的角色
		if (list1 == null || list1.size() <= 0) {
			return null;
		}
		List<SysRole> list2 = sysRoleMapper.findUserRoleList(list1);
		List<SysRoleVo> list3 = new ArrayList<SysRoleVo>();
		for (int i = 0; i < list2.size(); i++) {
			SysRoleVo sysRole = new SysRoleVo();
			sysRole.setRoleCode(list2.get(i).getRoleCode());
			sysRole.setRoleDesc(list2.get(i).getRoleDesc());
			sysRole.setRoleName(list2.get(i).getRoleName());
			sysRole.setCreateDate(list2.get(i).getCreateDate());
			sysRole.setCreater(list2.get(i).getCreater());
			list3.add(sysRole);
		}
		return list3;
	}

	/**
	 * 角色菜单查询 roleCode
	 * 
	 */
	@Override
	public List<SysMenuVo> findRoleMenuList(String roleCodes) {
		// 通过角色查找角色菜单的menu_id;
		String str[] = roleCodes.split(",");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < str.length; i++) {
			list.add(str[i]);
		}
		List<SysRoleMenu> sysRoleMenu = sysRoleMenuMapper.selectByRoleCode(list);
		if (sysRoleMenu == null || sysRoleMenu.size() <= 0) {
			return null;
		}
		// 根据角色菜单menu_id去查找菜单
		List<SysMenuVo> menuList = new ArrayList<SysMenuVo>();
		for (SysRoleMenu srm : sysRoleMenu) {
			SysMenuVo vo = new SysMenuVo();
			SysMenu sysMenu = sysMenuMapper.selectByPrimaryKey(srm.getMenuId());
			vo.setMenuId(sysMenu.getMenuId());
			vo.setMenuName(sysMenu.getMenuName());
			vo.setMenuPath(sysMenu.getMenuPath());
			vo.setOrdBy(sysMenu.getOrdBy());
			vo.setParentId(sysMenu.getParentId());
			menuList.add(vo);
		}
		return menuList;
	}

	/**
	 * 查询全部菜单
	 * 
	 */
	@Override
	public List<SysMenuVo> findAllMenu() {
		List<SysMenuVo> sysMenuVoList = new ArrayList<SysMenuVo>(); 
		List<SysMenu> sysMenuList = sysMenuMapper.selectAll();
		if(sysMenuList == null || sysMenuList.size() <= 0){
		return null;	
		}
		
		for(int i=0;i<sysMenuList.size();i++){
			SysMenuVo vo = new SysMenuVo();
			vo.setMenuId(sysMenuList.get(i).getMenuId());
			vo.setMenuName(sysMenuList.get(i).getMenuName());
			vo.setMenuPath(sysMenuList.get(i).getMenuPath());
			vo.setOrdBy(sysMenuList.get(i).getOrdBy());
			vo.setParentId(sysMenuList.get(i).getParentId());
			sysMenuVoList.add(vo);
		}
		return sysMenuVoList;
	}

	/**
	 * 添加菜单
	 */
	@Override
	public void addSysMenu(String roleCode, List<String> menuIdList) {
		

	}
	
	/**
	 * 指定用户角色查询
	 * added by zhangxx
	 */
	@Override
	public List<SysUserRoleMapVo> selectByLoginName(String loginName) {
		
		if(loginName == null || loginName.equals("")){
			return null;
		}
		
//		根据LoginName查询出用户角色关系
		List<SysUserRoleMap> sysUserRoleMapList = sysUserRoleMapMapper.selectByLoginName1(loginName);
		if (sysUserRoleMapList == null || sysUserRoleMapList.size() <= 0) {
			return null;
		}
		
		List<SysUserRoleMapVo> SysUserRoleMapVoList = new ArrayList<SysUserRoleMapVo>();
		for (int i = 0; i < sysUserRoleMapList.size(); i++) {
			
			SysUserRoleMapVo sysUserRoleMapVo = new SysUserRoleMapVo();
			sysUserRoleMapVo.setId(sysUserRoleMapList.get(i).getId());
			sysUserRoleMapVo.setLoginName(sysUserRoleMapList.get(i).getLoginName());
			sysUserRoleMapVo.setRoleCode(sysUserRoleMapList.get(i).getRoleCode());
			SysUserRoleMapVoList.add(sysUserRoleMapVo);
			
		}
		
		return SysUserRoleMapVoList;
	}

}

package com.boomhope.dao;

import com.boomhope.po.SysRoleMenu;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface SysRoleMenuMapper {
	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table sys_role_menu
	 *
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(String string);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table sys_role_menu
	 *
	 * @mbg.generated
	 */
	int deleteByRoleCode(String string);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table sys_role_menu
	 *
	 * @mbg.generated
	 */
	int insert(SysRoleMenu record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table sys_role_menu
	 *
	 * @mbg.generated
	 */
	SysRoleMenu selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table sys_role_menu
	 *
	 * @mbg.generated
	 */
	List<SysRoleMenu> selectByRoleCode(List<String> list);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table sys_role_menu
	 *
	 * @mbg.generated
	 */
	List<SysRoleMenu> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table sys_role_menu
	 *
	 * @mbg.generated
	 */
	int updateByPrimaryKey(SysRoleMenu record);

	/**
	 * 添加角色菜单
	 * 
	 * @param list
	 */
	void saveRoleMenuList(List<SysRoleMenu> list);
}
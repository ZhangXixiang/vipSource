package com.boomhope.service;

import java.util.List;
import java.util.Map;

import com.boomhope.model.BussinessUserExtendVo;
import com.boomhope.model.BussinessUserVo;

/**
 * 营业厅员工管理模块业务逻辑层
 * @version 1.0 2017-06-19
 * @author renxl
 *
 */
public interface IBussinessUserService {
	/**
	 * 根据营业厅员工代码查询员工信息
	 * @return
	 */
	public BussinessUserVo selectByPrimaryKey(String userCode);
	
	/**
	 * 查询营业厅员工信息
	 * @return
	 */
	public List<BussinessUserExtendVo> selectAll(Map<String, String> map);
	
	/**
	 * 新增营业厅员工信息
	 * @return
	 */
	public void insert(BussinessUserVo bussinessUserVo); 
	
	/**
	 * 删除营业厅员工信息
	 * @return
	 */
	public void deleteByPrimaryKey(String string);
	
	/**
	 * 编辑营业厅员工信息
	 * @param bussinessUserVo
	 */
	public void updateByPrimaryKeySelective(BussinessUserVo bussinessUserVo);
	
	/**
	 * 编辑营业厅员工信息
	 * @param status
	 */
	public void enableBan(BussinessUserVo bussinessUserVo);
	
	/**
	 * 增加营业厅员工信息
	 * @param bussinessUserVo
	 */
	public void insertSelective(BussinessUserVo bussinessUserVo);
}

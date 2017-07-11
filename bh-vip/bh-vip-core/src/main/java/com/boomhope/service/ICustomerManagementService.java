package com.boomhope.service;

import java.util.List;
import java.util.Map;

import com.boomhope.model.VipCustomerVo;

public interface ICustomerManagementService {
	
	public List<VipCustomerVo> findVipCustomerList(Map<String, String> parMap);
	/**
	 * 根据客户证件号来查询VIP客户维护信息
	 * @param custNo
	 * @return
	 */
	public List<VipCustomerVo> selectByCustNolList(Map<String, String> parMap);
	/**
	 * 添加VIP客户维护信息----添加添加
	 * @param deployInfoVo
	 */
	public void addVipCustomer(VipCustomerVo VipCustomerVo);
	/**
	 * 删除多个用户
	 * 
	 * @param loginName
	 */
	public void deleteCustlist(String vipcustIds);
	/**
	 * 根据主键查询设备信息
	 */
	public VipCustomerVo selectByPrimaryKey(String vipCusId);
	/**
	 * 编辑客户信息
	 */
	public void updateVipCustomerVo(VipCustomerVo vipCustomerVo);
	/**
	 * 导入Excel
	 */
	public int  importExcell(VipCustomerVo vipCustomerVo);
	
	/**
	 * 导入excel
	 * @param string 
	 * @param unitCode
	 */
	public void importExcel(List<List<Object>> list);

}

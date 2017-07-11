package com.boomhope.service;

import java.util.List;
import java.util.Map;

import com.boomhope.model.DeployUnitVo;


public interface IBusinessManagentService {
	public List<DeployUnitVo> findBusinessManagentList(Map<String, String> parMap);
	public void addBusinessManagent(DeployUnitVo deployUnitVo);
	/**
	 * 根据主键查询营业厅信息
	 */
	public DeployUnitVo selectByPrimaryKey(String unitCode);
	/**
	 * 编辑营业厅信息
	 */
	public void updateBusinessManagent(DeployUnitVo deployUnitVo);
	/**
	 * 更新营业厅信息
	 * @param deployCode
	 */
	public void updateDeployUnitStatus(DeployUnitVo deployUnitVo);
	/**
	 * @param unitCode
	 */
	public void deleteBusinessManagent(String unitCodes);
	/**
	 * 导入excel
	 * @param string 
	 * @param unitCode
	 */
	public void importExcel(List<List<Object>> list, String string);
}

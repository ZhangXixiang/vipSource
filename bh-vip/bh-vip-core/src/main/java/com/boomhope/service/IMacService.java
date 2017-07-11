package com.boomhope.service;

import java.util.List;
import java.util.Map;

import com.boomhope.model.MacMachineVo;
import com.boomhope.po.MacMachine;

/**
 * 设备管理模块相关服务
 * @version 1.0 2017-03-08
 * @author zy
 *
 */
public interface IMacService {

	/**
	 * 根据条件查询设备维护信息
	 * @param parMap
	 * @return
	 */
	public List<MacMachineVo> queryMacMachine(Map<String,String> map);
	
	/**
	 * 根据mac_address查询设备维护信息
	 * @param parMap
	 * @return
	 */
	public List<MacMachineVo> selectByMacAddress(Map<String,String> map);
	
	
	
	/**
	 * 添加设备信息
	 * @param macMachineVo
	 */
	public void addMacMachineVo(MacMachineVo macMachineVo);
	/**
	 * 添加设备信息
	 * @param macMachineVo
	 */
	public void insert(MacMachineVo macMachineVo);
	
	/**
	 * 编辑设备信息
	 * @param macMachineVo
	 */
	public void updateMacMachineVo(MacMachineVo macMachineVo);
	
	/**
	 * 删除指定设备
	 * @param ids
	 */
	public void deleteMacMachineVo(String ids);
	
	/**
	 * 根据主键查询设备信息
	 */
	public MacMachineVo selectByPrimaryKey(String macId);
	
	/**
	 * 根据设备型号查询设备信息List
	 */
	List<MacMachineVo> selectByMacModelList(String macModel);

	/**
	 * 查询所有mac_modal
	 * @return
	 */
	List<String> selectMacModel();
	
	/**
	 * 查询对应mac_brand
	 * @return
	 */
	String selectMacBrand(String macModel);
	
	/**
	 * selectMacAll
	 * 
	 */
	List<MacMachineVo> selectAll();
	
}

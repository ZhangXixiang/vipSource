package com.boomhope.service.imple;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boomhope.dao.BaseMapper;
import com.boomhope.dao.MacMachineMapper;
import com.boomhope.model.MacMachineVo;
import com.boomhope.po.MacMachine;
import com.boomhope.service.IMacService;
@Transactional
@Service(value="macService")
public class MacServiceImple extends BaseServiceImple implements IMacService {
	/**
	 * 日志
	 */
	private final static Logger logger = LoggerFactory.getLogger(MacServiceImple.class); 
	@Resource(name="macMachineMapper")
	private MacMachineMapper macMachineMapper;
	
	@Resource(name="baseMapper")
	private BaseMapper baseMapper;
	
	/**
	 * 分页查询所有设备信息
	 */
	@Override
	public List<MacMachineVo> queryMacMachine(Map<String, String> map) {
		List<MacMachine> list =  macMachineMapper.findMacMachineList(map);
		if(list == null || list.size() == 0){
			return null;
		}
		List<MacMachineVo> voList =  new ArrayList<MacMachineVo>();
		for (int i = 0; i < list.size(); i++) {
			MacMachine mm = list.get(i);
			MacMachineVo vo = new MacMachineVo();
			vo.setMacId(mm.getMacId());
			vo.setUnitCode(mm.getUnitCode());
			vo.setMacAddress(mm.getMacAddress());
			vo.setIpAddress(mm.getIpAddress());
			vo.setPort(mm.getPort());
			vo.setStatus(mm.getStatus());
			vo.setCreateDate(mm.getCreateDate());
			vo.setCreater(mm.getCreater());
			voList.add(vo);
		}
		return voList;
	}

	/**
	 * 添加设备
	 */
	@Override
	public void addMacMachineVo(MacMachineVo macMachineVo) {
		MacMachine dm = new MacMachine();
		dm.setMacId(macMachineVo.getMacId());
		dm.setIpAddress(macMachineVo.getIpAddress());
		dm.setPort(macMachineVo.getPort());
		dm.setMacAddress(macMachineVo.getMacAddress());
		dm.setStatus(macMachineVo.getStatus());
		
		dm.setUnitCode(macMachineVo.getUnitCode());
		dm.setCreateDate(macMachineVo.getCreateDate());
		dm.setCreater(macMachineVo.getCreater());
		macMachineMapper.insert(dm);
		
	}

	/**
	 * 更新设备
	 */
	@Override
	public void updateMacMachineVo(MacMachineVo macMachineVo) {
		
		MacMachine dm = new MacMachine();
		dm.setMacId(macMachineVo.getMacId());
		dm.setIpAddress(macMachineVo.getIpAddress());
		dm.setPort(macMachineVo.getPort());
		dm.setMacAddress(macMachineVo.getMacAddress());
		dm.setStatus(macMachineVo.getStatus());
		
		dm.setUnitCode(macMachineVo.getUnitCode());
		dm.setCreateDate(macMachineVo.getCreateDate());
		dm.setCreater(macMachineVo.getCreater());
		macMachineMapper.updateByPrimaryKey(dm);
	}

	/**
	 * 删除设备
	 */
	@Override
	public void deleteMacMachineVo(String ids) {
		String [] ssss = ids.split(",");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < ssss.length; i++) {
			list.add(ssss[i]);
		}
		macMachineMapper.deleteByPrimaryKey(list);
	}
	
	/**
	 * 根据主键查询设备信息
	 * 在这里将数据库Mapper查询出来的PO对象转换为Vo，不能使用类强转
	 */
	@Override
	public MacMachineVo selectByPrimaryKey(String macId) {
		
		MacMachine macMachine = macMachineMapper.selectByPrimaryKey(macId);
		
		if(macMachine == null){
			return null;
		}
		
		MacMachineVo macMachineVo = new MacMachineVo();
		macMachineVo.setCreateDate(macMachine.getCreateDate());
		macMachineVo.setCreater(macMachine.getCreater());
		macMachineVo.setMacId(macMachine.getMacId());
		return macMachineVo;
	}

	/**
	 * 根据设备型号查询设备信息
	 */
	@Override
	public List<MacMachineVo> selectByMacModelList(String macModel) {
		
		List<MacMachine> list = macMachineMapper.selectByMacModelList(macModel);

		
		if(list == null || list.size() == 0){
			return null;
		}
		List<MacMachineVo> voList =  new ArrayList<MacMachineVo>();
		for (int i = 0; i < list.size(); i++) {
			MacMachine sr = list.get(i);
			MacMachineVo vo = new MacMachineVo();
			vo.setCreateDate(sr.getCreateDate());
			vo.setCreater(sr.getCreater());
			vo.setMacId(sr.getMacId());
			voList.add(vo);
		}
		
		return voList;
	}
	
	
	/**
	 * 添加设备信息
	 * @param macMachineVo
	 */

	@Override
	public void insert(MacMachineVo macMachineVo) {
		
		MacMachine dm = new MacMachine();
		
		dm.setMacId(macMachineVo.getMacId());
		dm.setCreateDate(macMachineVo.getCreateDate());
		dm.setCreater(macMachineVo.getCreater());
		macMachineMapper.insert(dm);
	}

	@Override
	public List<String> selectMacModel() {

		List<String> tmp = macMachineMapper.selectMacModel();
		List<String> tmpVo = tmp;
		return tmpVo;
	}

	@Override
	public String selectMacBrand(String macModel) {

		List<MacMachine> macModelList = macMachineMapper.selectByMacModelList(macModel);
		
		if(macModelList != null && macModelList.size() == 1){
			MacMachine macMachine = macModelList.get(0);
			
			MacMachineVo macMachineVo =  new MacMachineVo();
			macMachineVo.setCreateDate(macMachine.getCreateDate());
			macMachineVo.setCreater(macMachine.getCreater());
			macMachineVo.setMacId(macMachine.getMacId());
			return macMachineVo.getMacId();
		}
		else{
			logger.info("异常！");
			return null;
		}
		
		
	}

	@Override
	public List<MacMachineVo> selectAll() {

		List<MacMachine> macMachineList = macMachineMapper.selectAll();
		List<MacMachineVo> macMachineVoList = new ArrayList<MacMachineVo>();
		if(macMachineList != null){
			for (int i = 0; i < macMachineList.size(); i++) {
				MacMachine macMachine = macMachineList.get(i);
			
			MacMachineVo macMachineVo =  new MacMachineVo();
			macMachineVo.setCreateDate(macMachine.getCreateDate());
			macMachineVo.setCreater(macMachine.getCreater());
			macMachineVo.setMacId(macMachine.getMacId());
			macMachineVoList.add(macMachineVo);
		}
			return macMachineVoList;
	}
		else{
			return null;
		}
	}

	@Override
	public List<MacMachineVo> selectByMacAddress(Map<String, String> map) {
		List<MacMachine> list =  macMachineMapper.selectByMacAddress(map);
		if(list == null || list.size() == 0){
			return null;
		}
		List<MacMachineVo> voList =  new ArrayList<MacMachineVo>();
		for (int i = 0; i < list.size(); i++) {
			MacMachine mm = list.get(i);
			MacMachineVo vo = new MacMachineVo();
			vo.setMacId(mm.getMacId());
			vo.setUnitCode(mm.getUnitCode());
			vo.setMacAddress(mm.getMacAddress());
			vo.setIpAddress(mm.getIpAddress());
			vo.setPort(mm.getPort());
			vo.setStatus(mm.getStatus());
			vo.setCreateDate(mm.getCreateDate());
			vo.setCreater(mm.getCreater());
			voList.add(vo);
		}
		return voList;
	}
}

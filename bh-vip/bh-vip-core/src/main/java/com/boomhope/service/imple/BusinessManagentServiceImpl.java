package com.boomhope.service.imple;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boomhope.dao.DeployUnitMapper;
import com.boomhope.model.DeployUnitVo;
import com.boomhope.po.DeployUnit;
import com.boomhope.service.IBusinessManagentService;
import com.boomhope.util.DateUtil;

/**
 * 
 * 营业厅管理Service层实现
 * @author yuzw
 *
 */
@Transactional
@Service(value = "businessManagentService")
public class BusinessManagentServiceImpl extends BaseServiceImple implements IBusinessManagentService{
	/**
	 * 日志
	 */
	private final static Logger logger = LoggerFactory.getLogger(BusinessManagentServiceImpl.class); 
	
	@Resource(name="deployUnitMapper")
	private DeployUnitMapper deployUnitMapper;
	
	@Override
	public List<DeployUnitVo> findBusinessManagentList(Map<String, String> parMap) {
		List<DeployUnit> list =  deployUnitMapper.findBusinessManagentList(parMap);
		if(list == null || list.size() == 0){
			return null;
		}
		List<DeployUnitVo> voList =  new ArrayList<DeployUnitVo>();
		for (int i = 0; i < list.size(); i++) {
			DeployUnit sr = list.get(i);
			DeployUnitVo vo = new DeployUnitVo();
			vo.setAddress(sr.getAddress());
			vo.setCreateDate(sr.getCreateDate());
			vo.setCreater(sr.getCreater());
			vo.setEmail(sr.getEmail());
			vo.setManager(sr.getManager());
			vo.setParentCode(sr.getParentCode());
			vo.setPhone(sr.getPhone());
			vo.setStatus(sr.getStatus());
			vo.setUnitCode(sr.getUnitCode());
			vo.setUnitName(sr.getUnitName());
			vo.setUnitTel(sr.getUnitTel());
			voList.add(vo);
		}
		return voList;
	}

	@Override
	public void addBusinessManagent(DeployUnitVo deployUnitVo) {
		DeployUnit deployUnit=new DeployUnit();	
		deployUnit.setAddress(deployUnitVo.getAddress());
		deployUnit.setCreateDate(deployUnitVo.getCreateDate());
		deployUnit.setCreater(deployUnitVo.getCreater());
		deployUnit.setEmail(deployUnitVo.getEmail());
		deployUnit.setManager(deployUnitVo.getManager());
		deployUnit.setParentCode(deployUnitVo.getParentCode());
		deployUnit.setPhone(deployUnitVo.getPhone());
		deployUnit.setStatus(deployUnitVo.getStatus());
		deployUnit.setUnitCode(deployUnitVo.getUnitCode());
		deployUnit.setUnitName(deployUnitVo.getUnitName());
		deployUnit.setUnitTel(deployUnitVo.getUnitTel());
		deployUnitMapper.insert(deployUnit);
	}

	@Override
	public DeployUnitVo selectByPrimaryKey(String unitCode) {
		DeployUnit deployUnit=deployUnitMapper.selectByPrimaryKey(unitCode);
		if(deployUnit==null){
			return null;
		}
		DeployUnitVo deployUnitVo=new DeployUnitVo();
		deployUnitVo.setAddress(deployUnit.getAddress());
		deployUnitVo.setCreateDate(deployUnit.getCreateDate());
		deployUnitVo.setCreater(deployUnit.getCreater());
		deployUnitVo.setEmail(deployUnit.getEmail());
		deployUnitVo.setManager(deployUnit.getManager());
		deployUnitVo.setParentCode(deployUnit.getParentCode());
		deployUnitVo.setPhone(deployUnit.getPhone());
		deployUnitVo.setStatus(deployUnit.getStatus());
		deployUnitVo.setUnitCode(deployUnit.getUnitCode());
		deployUnitVo.setUnitName(deployUnit.getUnitName());
		deployUnitVo.setUnitTel(deployUnit.getUnitTel());
		return deployUnitVo;
	}

	@Override
	public void updateBusinessManagent(DeployUnitVo deployUnitVo) {
		DeployUnit deployUnit=new DeployUnit();	
		deployUnit.setAddress(deployUnitVo.getAddress());
		deployUnit.setCreateDate(deployUnitVo.getCreateDate());
		deployUnit.setCreater(deployUnitVo.getCreater());
		deployUnit.setEmail(deployUnitVo.getEmail());
		deployUnit.setManager(deployUnitVo.getManager());
		deployUnit.setParentCode(deployUnitVo.getParentCode());
		deployUnit.setPhone(deployUnitVo.getPhone());
		deployUnit.setStatus(deployUnitVo.getStatus());
		deployUnit.setUnitCode(deployUnitVo.getUnitCode());
		deployUnit.setUnitName(deployUnitVo.getUnitName());
		deployUnit.setUnitTel(deployUnitVo.getUnitTel());	
		deployUnitMapper.updateByPrimaryKey(deployUnit);
	}

	
	@Override
	public void deleteBusinessManagent(String unitCodes) {
		String[] ids = unitCodes.split(",");
		List<String> list = new ArrayList<String>();
		for (int i =0;i<ids.length;i++){
			list.add(ids[i]);
		}
		deployUnitMapper.deleteByPrimaryKey(list);
	}
	
	@Override
	public void updateDeployUnitStatus(DeployUnitVo deployUnitVo) {
		
		DeployUnit deployUnit = new DeployUnit();
		deployUnit.setAddress(deployUnitVo.getAddress());
		deployUnit.setCreateDate(deployUnitVo.getCreateDate());
		deployUnit.setCreater(deployUnitVo.getCreater());
		deployUnit.setEmail(deployUnitVo.getEmail());
		deployUnit.setManager(deployUnitVo.getManager());
		
		deployUnit.setParentCode(deployUnitVo.getParentCode());
		deployUnit.setPhone(deployUnitVo.getPhone());
		deployUnit.setStatus(deployUnitVo.getStatus());
		deployUnit.setUnitCode(deployUnitVo.getUnitCode());
		deployUnit.setUnitName(deployUnitVo.getUnitName());

		deployUnit.setUnitTel(deployUnitVo.getUnitTel());
		
		deployUnitMapper.updateByPrimaryKeySelective(deployUnit);
		
	}

	@Override
	public void importExcel(List<List<Object>> list,String creater) {
		List<DeployUnit> vo = new ArrayList<DeployUnit>();
		for (int i = 0; i < list.size(); i++){
			List<Object> every = list.get(i);
			if(every!=null && every.size()!=0 && !every.get(0).toString().equals("")){
				DeployUnit deployUnit = new DeployUnit();
				deployUnit.setUnitCode(every.get(0).toString());
				deployUnit.setUnitName(every.get(1).toString());
				deployUnit.setManager(every.get(2).toString());
				deployUnit.setPhone(every.get(3).toString());
				deployUnit.setUnitTel(every.get(4).toString());
				deployUnit.setAddress(every.get(5).toString());
				deployUnit.setCreater(creater);
				deployUnit.setCreateDate(DateUtil.getNowDate("yyyyMMddHHmmss"));
				deployUnit.setStatus("1");
				deployUnit.setEmail(null);
				deployUnit.setParentCode(null);
				vo.add(deployUnit);
			}
		}
		deployUnitMapper.importInsert(vo);
	}  
}

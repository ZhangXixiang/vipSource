package com.boomhope.service.imple;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boomhope.dao.BussinessUserMapper;
import com.boomhope.model.BussinessUserExtendVo;
import com.boomhope.model.BussinessUserVo;
import com.boomhope.po.BussinessUser;
import com.boomhope.po.BussinessUserExtend;
import com.boomhope.service.IBussinessUserService;
@Transactional
@Service(value="bussinessUserService")
public class BussinessUserServiceImpl implements IBussinessUserService{
	
	//日志
	private final static Logger logger = LoggerFactory.getLogger(BussinessUserServiceImpl.class); 
	
	//营业厅员工mapper
	@Resource(name="bussinessUserMapper")
	private BussinessUserMapper bussinessUserMapper;
	public void setBussinessUserMapper(BussinessUserMapper bussinessUserMapper) {
		this.bussinessUserMapper = bussinessUserMapper;
	}
	/**
	 * 根据营业厅员工代码查询员工信息
	 * @return BussinessUserVo
	 */
	@Override
	public BussinessUserVo selectByPrimaryKey(String userCode) {
		BussinessUser bussinessUser = bussinessUserMapper.selectByPrimaryKey(userCode);
		if(bussinessUser == null){
			return null;
		}
		logger.info("");
		BussinessUserVo bussinessUserVo = new BussinessUserVo();
		bussinessUserVo.setUserCode(bussinessUser.getUserCode());
		bussinessUserVo.setUnitCode(bussinessUser.getUnitCode());
		bussinessUserVo.setUserName(bussinessUser.getUserName());
		bussinessUserVo.setLoginName(bussinessUser.getLoginName());
		bussinessUserVo.setCreditId(bussinessUser.getCreditId());
		bussinessUserVo.setSex(bussinessUser.getSex());
		bussinessUserVo.setPhone(bussinessUser.getPhone());
		bussinessUserVo.setTelephone(bussinessUser.getTelephone());
		bussinessUserVo.setCreateDate(bussinessUser.getCreateDate());
		bussinessUserVo.setCreater(bussinessUser.getCreater());
		bussinessUserVo.setStatus(bussinessUser.getStatus());
		return bussinessUserVo;
	}
	
	/**
	 * 查询营业厅员工信息
	 * @return
	 */
	@Override
	public List<BussinessUserExtendVo> selectAll(Map<String, String> map) {
		List<BussinessUserExtend> bussinessUserList = bussinessUserMapper.slectAll(map);
		if(bussinessUserList == null || bussinessUserList.size() == 0){
			return null;
		}
		List<BussinessUserExtendVo> bussinessUserVoList = new ArrayList<>();
		for(int i=0;i<bussinessUserList.size();i++){
			BussinessUserExtend bussinessUser = bussinessUserList.get(i);
			BussinessUserExtendVo bussinessUserVo = new BussinessUserExtendVo();
			bussinessUserVo.setUserCode(bussinessUser.getUserCode());
			bussinessUserVo.setUnitCode(bussinessUser.getUnitCode());
			bussinessUserVo.setUserName(bussinessUser.getUserName());
			bussinessUserVo.setLoginName(bussinessUser.getLoginName());
			bussinessUserVo.setCreditId(bussinessUser.getCreditId());
			bussinessUserVo.setSex(bussinessUser.getSex());
			bussinessUserVo.setPhone(bussinessUser.getPhone());
			bussinessUserVo.setTelephone(bussinessUser.getTelephone());
			bussinessUserVo.setCreateDate(bussinessUser.getCreateDate());
			bussinessUserVo.setCreater(bussinessUser.getCreater());
			bussinessUserVo.setStatus(bussinessUser.getStatus());
			bussinessUserVo.setUnitName(bussinessUser.getUnitName());
			bussinessUserVoList.add(bussinessUserVo);
		}
		return bussinessUserVoList;
	}

	/**
	 * 营业厅员工新增
	 */
	@Override
	public void insertSelective(BussinessUserVo bussinessUserVo) {
		BussinessUser bussinessUser = new BussinessUser();
		bussinessUser.setUserCode(bussinessUserVo.getUserCode());
		bussinessUser.setUnitCode(bussinessUserVo.getUnitCode());
		bussinessUser.setUserName(bussinessUserVo.getUserName());
		bussinessUser.setLoginName(bussinessUserVo.getLoginName());
		bussinessUser.setCreditId(bussinessUserVo.getCreditId());
		bussinessUser.setSex(bussinessUserVo.getSex());
		bussinessUser.setPhone(bussinessUserVo.getPhone());
		bussinessUser.setTelephone(bussinessUserVo.getTelephone());
		bussinessUser.setCreateDate(bussinessUserVo.getCreateDate());
		bussinessUser.setCreater(bussinessUserVo.getCreater());
		bussinessUser.setStatus(bussinessUserVo.getStatus());
		bussinessUserMapper.insertSelective(bussinessUser);
	}

	/**
	 * 营业厅员工删除(多条)
	 */
	@Override
	public void deleteByPrimaryKey(String userCode) {
		String uc[] = userCode.split(",");
		List<String> list = new ArrayList<String>();
		for(int i=0;i<uc.length;i++){
			list.add(uc[i]);
		}
		bussinessUserMapper.deleteByPrimaryKey(list);
	}

	/**
	 * 营业厅员工编辑
	 */
	@Override
	public void updateByPrimaryKeySelective(BussinessUserVo bussinessUserVo) {
		BussinessUser bussinessUser = new BussinessUser();
		bussinessUser.setUserCode(bussinessUserVo.getUserCode());
		bussinessUser.setUnitCode(bussinessUserVo.getUnitCode());
		bussinessUser.setUserName(bussinessUserVo.getUserName());
		bussinessUser.setLoginName(bussinessUserVo.getLoginName());
		bussinessUser.setCreditId(bussinessUserVo.getCreditId());
		bussinessUser.setSex(bussinessUserVo.getSex());
		bussinessUser.setPhone(bussinessUserVo.getPhone());
		bussinessUser.setTelephone(bussinessUserVo.getTelephone());
		bussinessUser.setCreateDate(bussinessUserVo.getCreateDate());
		bussinessUser.setCreater(bussinessUserVo.getCreater());
		bussinessUser.setStatus(bussinessUserVo.getStatus());
		bussinessUserMapper.updateByPrimaryKeySelective(bussinessUser);
	}

	/**
	 * 启用禁用
	 */
	@Override
	public void enableBan(BussinessUserVo bussinessUserVo) {
		BussinessUser bussinessUser = new BussinessUser();
		bussinessUser.setUserCode(bussinessUserVo.getUserCode());
		bussinessUser.setStatus(bussinessUserVo.getStatus());
		bussinessUserMapper.enableBan(bussinessUser);
	}
	@Override
	public void insert(BussinessUserVo bussinessUserVo) {
		BussinessUser bussinessUser = new BussinessUser();
		bussinessUser.setUserCode(bussinessUserVo.getUserCode());
		bussinessUser.setUnitCode(bussinessUserVo.getUnitCode());
		bussinessUser.setUserName(bussinessUserVo.getUserName());
		bussinessUser.setLoginName(bussinessUserVo.getLoginName());
		bussinessUser.setCreditId(bussinessUserVo.getCreditId());
		bussinessUser.setSex(bussinessUserVo.getSex());
		bussinessUser.setPhone(bussinessUserVo.getPhone());
		bussinessUser.setTelephone(bussinessUserVo.getTelephone());
		bussinessUser.setCreateDate(bussinessUserVo.getCreateDate());
		bussinessUser.setCreater(bussinessUserVo.getCreater());
		bussinessUser.setStatus(bussinessUserVo.getStatus());
		bussinessUserMapper.insert(bussinessUser);
	}
}

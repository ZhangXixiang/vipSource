package com.boomhope.service.imple;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boomhope.dao.VipCustomerMapper;
import com.boomhope.model.VipCustomerVo;
import com.boomhope.po.DeployUnit;
import com.boomhope.po.VipCustomer;
import com.boomhope.service.IBaseService;
import com.boomhope.service.ICustomerManagementService;
import com.boomhope.util.DateUtil;
@Transactional
@Service(value="customerManagementService")
public class CustomerManagementServiceImple extends BaseServiceImple implements ICustomerManagementService{
	/**
	 * 日志
	 */
	private final static Logger logger = LoggerFactory.getLogger(CustomerManagementServiceImple.class); 
	
	@Resource(name="vipCustomerMapper")
	private VipCustomerMapper  vipCustomerMapper;
	private IBaseService baseService;
	@Resource(name = "baseService")
	public void setBaseService(IBaseService baseService) {
		this.baseService = baseService;
	}
	@Override
	public List<VipCustomerVo> findVipCustomerList(Map<String, String> parMap) {
		List<VipCustomer> list =  vipCustomerMapper.findVipCustomerList(parMap);
		if(list == null || list.size() == 0){
			return null;
		}
		List<VipCustomerVo> voList =  new ArrayList<VipCustomerVo>();
		for (int i = 0; i < list.size(); i++) {
			VipCustomer sr = list.get(i);
			VipCustomerVo vo = new VipCustomerVo();
			vo.setCustNo(sr.getCustNo());
			vo.setCustName(sr.getCustName());
			vo.setCustSex(sr.getCustSex());
			vo.setCreditType(sr.getCreditType());
			vo.setCreditNo(sr.getCreditNo());
			vo.setCustBir(sr.getCustBir());
			vo.setCreateDate(sr.getCreateDate());
			vo.setCustLevel(sr.getCustLevel());
			voList.add(vo);
		}
		return voList;
	}
	
	@Override
	public List<VipCustomerVo> selectByCustNolList(Map<String, String> parMap) {
    List<VipCustomer> list = vipCustomerMapper.selectByCustNoList(parMap);
		if(list == null || list.size() == 0){
			return null;
		}
		List<VipCustomerVo> voList =  new ArrayList<VipCustomerVo>();
		for (int i = 0; i < list.size(); i++) {
			VipCustomer sr = list.get(i);
			VipCustomerVo vo = new VipCustomerVo();
			vo.setCustNo(sr.getCustNo());
			vo.setCustName(sr.getCustName());
			vo.setCustSex(sr.getCustSex());
			vo.setCreditType(sr.getCreditType());
			vo.setCreditNo(sr.getCreditNo());
			vo.setCustBir(sr.getCustBir());
			vo.setCreateDate(sr.getCreateDate());
			vo.setCustLevel(sr.getCustLevel());
			voList.add(vo);
		}
		
		return voList;
	}
	
	public void addVipCustomer(VipCustomerVo vipCustomerVo) {
		VipCustomer dm = new VipCustomer();
		dm.setCreditNo(vipCustomerVo.getCreditNo());
		dm.setCustName(vipCustomerVo.getCustName());
		dm.setCustLevel(vipCustomerVo.getCustLevel().trim());
		dm.setCreateDate(vipCustomerVo.getCreateDate());
		dm.setCreditType(vipCustomerVo.getCreditType());
		dm.setCreateDate(vipCustomerVo.getCreateDate());
		dm.setCustSex(vipCustomerVo.getCustSex().trim());
		dm.setCustNo(vipCustomerVo.getCustNo());
		dm.setVipCusId(vipCustomerVo.getVipCusId());
		dm.setCustBir(vipCustomerVo.getCustBir());
		vipCustomerMapper.insert(dm);
	}
	
	/**
	 * 删除多条客户维护信息
	 */
	@Override
	public void deleteCustlist(String vipcustIds) {
		String[] ids = vipcustIds.split(",");
		List<String> list = new ArrayList<String>();
		for (int i =0;i<ids.length;i++){
			list.add(ids[i]);
		}
		vipCustomerMapper.deleteCustlist(list);
	}
	
	@Override
	public VipCustomerVo selectByPrimaryKey(String vipCusId) {
		VipCustomer vipCustomer = vipCustomerMapper.selectByPrimaryKey(vipCusId);
		if(vipCustomer == null){
			return null;
		}
		VipCustomerVo vipCustomervo = new VipCustomerVo();
		vipCustomervo.setVipCusId(vipCustomer.getVipCusId());
		vipCustomervo.setCreditNo(vipCustomer.getCreditNo());
		vipCustomervo.setCreditType(vipCustomer.getCreditType());
		vipCustomervo.setCustBir(vipCustomer.getCustBir());
		vipCustomervo.setCustLevel(vipCustomer.getCustLevel());
		vipCustomervo.setCustName(vipCustomer.getCustName());
		vipCustomervo.setCustSex(vipCustomer.getCustSex());
		vipCustomervo.setCreateDate(vipCustomer.getCreateDate());
		return vipCustomervo;
	}
	/**
	 * 编辑用户信息
	 */
	@Override
	public void updateVipCustomerVo(VipCustomerVo vipCustomerVo) {
		VipCustomer vc = new VipCustomer();
		vc.setCustName(vipCustomerVo.getCustName());
		vc.setCustNo(vipCustomerVo.getCustNo());
		vc.setCustSex(vipCustomerVo.getCustSex());
		vc.setVipCusId(vipCustomerVo.getVipCusId());
		vc.setCreditNo(vipCustomerVo.getCreditNo());
		vc.setCreditType(vipCustomerVo.getCreditType());
		vc.setCustBir(vipCustomerVo.getCustBir());
		vc.setCustLevel(vipCustomerVo.getCustLevel());
		vc.setCreateDate(vipCustomerVo.getCreateDate());
		vipCustomerMapper.updateByPrimaryKey(vc);
	}

	@Override
	public int importExcell(VipCustomerVo vipCustomerVo) {
		VipCustomer vc = new VipCustomer();
		vc.setCustName(vipCustomerVo.getCustName());
		vc.setCustNo(vipCustomerVo.getCustNo());
		vc.setCustSex(vipCustomerVo.getCustSex());
		vc.setVipCusId(vipCustomerVo.getVipCusId());
		vc.setCreditNo(vipCustomerVo.getCreditNo());
		vc.setCreditType(vipCustomerVo.getCreditType());
		vc.setCustBir(vipCustomerVo.getCustBir());
		vc.setCustLevel(vipCustomerVo.getCustLevel());
		vc.setCreateDate(vipCustomerVo.getCreateDate());
		vipCustomerMapper.insert(vc);
		return 1;
	}

	@Override
	public void importExcel(List<List<Object>> list) {
		List<VipCustomer> vo = new ArrayList<VipCustomer>();
		for (int i = 0; i < list.size(); i++){
			List<Object> every = list.get(i);
			if(every!=null && every.size()!=0 && !every.get(0).toString().equals("")){
			VipCustomer VipCustomer = new VipCustomer();
			VipCustomer.setVipCusId(every.get(0).toString());
			VipCustomer.setCustNo(every.get(0).toString());
			VipCustomer.setCustName(every.get(1).toString());
			VipCustomer.setCustSex(every.get(2).toString());
			VipCustomer.setCreditType(every.get(3).toString());
			VipCustomer.setCreditNo(every.get(4).toString());
			VipCustomer.setCustBir(every.get(5).toString());
			VipCustomer.setCustLevel(every.get(6).toString());
			VipCustomer.setCreateDate(DateUtil.getNowDate("yyyyMMddHHmmss"));
			vo.add(VipCustomer);
			}
		}
		vipCustomerMapper.importInsert(vo);
		
	}
}

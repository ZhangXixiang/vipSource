package com.boomhope.service.imple;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boomhope.dao.PushRuleMapper;
import com.boomhope.model.BussinessUserVo;
import com.boomhope.model.PushRuleVo;
import com.boomhope.po.PushRule;
import com.boomhope.service.IBaseService;
import com.boomhope.service.IBussinessUserService;
import com.boomhope.service.IPushRuleService;
import com.boomhope.util.DateUtil;
@Transactional
@Service(value = "pushRuleService")
public class PushRuleServiceImple implements IPushRuleService{
	
	/**
	 * test
	 */
	/*private IBaseService baseService;
	@Resource(name = "baseService")
	public void setBaseService(IBaseService baseService) {
		this.baseService = baseService;
	}

	private IPushRuleService pushRuleService;
	@Resource(name = "pushRuleService")
	public void setPushRuleService(IPushRuleService pushRuleService) {
		this.pushRuleService = pushRuleService;
	}

	private IBussinessUserService bussinessUserService;
	@Resource(name="bussinessUserService")
	public void setBussinessUserService(IBussinessUserService bussinessUserService) {
		this.bussinessUserService = bussinessUserService;
	}*/
	/**
	 * test
	 */
	

	
	
	/**
	 * 日志
	 */
	private final static Logger logger = LoggerFactory.getLogger(PushRuleServiceImple.class);

	@Resource(name = "pushRuleMapper")
	private PushRuleMapper pushRuleMapper;

	@Override
	public void insert(PushRuleVo vo) {
		PushRule po = new PushRule();
		po.setRuleId(vo.getRuleId());
		po.setThreshold(vo.getThreshold());
		po.setTimeInterval(vo.getTimeInterval());
		po.setLogSaveTime(vo.getLogSaveTime());
		pushRuleMapper.insert(po);
	}

	@Override
	public PushRuleVo selectByPrimaryKey(String str) {
		
		PushRule po = pushRuleMapper.selectByPrimaryKey(str);
		PushRuleVo vo = new PushRuleVo();
		vo.setRuleId(po.getRuleId());
		vo.setThreshold(po.getThreshold());
		vo.setTimeInterval(po.getTimeInterval());
		vo.setLogSaveTime(po.getLogSaveTime());
		return vo;
	}

	@Override
	public void updateByPrimaryKeySelective(PushRuleVo vo) {
		
		

		
		/**
		 * test
		 */
		/*String threshold = "85";
		String timeInterval = "85";
		String logSaveTime = "85";
		PushRuleVo pushRuleVo = new PushRuleVo();
		pushRuleVo.setRuleId(baseService.getSeq("push_rule"));
		pushRuleVo.setThreshold(threshold);
		pushRuleVo.setTimeInterval(timeInterval);
		pushRuleVo.setLogSaveTime(logSaveTime);
		pushRuleService.insert(pushRuleVo);
		
		
		String userCode = "U002";
		String unitCode = "U002";
		String userName = "zhangxxTest";
		String loginName ="zhangxx";		
		String creditId = "320124";
		String phone = "177";
		String telephone = "136";
		
		BussinessUserVo UserVo = new BussinessUserVo();
		
		UserVo.setUserCode(userCode);
		UserVo.setUnitCode(unitCode);
		UserVo.setUserName(userName);
		UserVo.setLoginName(loginName);
		UserVo.setCreditId(creditId);
		//UserVo.setSex(sex);
		UserVo.setPhone(phone);
		UserVo.setTelephone(telephone);
		UserVo.setCreateDate(DateUtil.getNowDate("yyyyMMddHHmmss"));
		UserVo.setCreater("zhangxx");
		UserVo.setStatus("1");
		try{
		bussinessUserService.insertSelective(UserVo);
		}
		catch(Exception e){
		logger.error(e.toString());	
		}*/
		/**
		 * test
		 */
		
		
		
		PushRule po = new PushRule();
		po.setRuleId(vo.getRuleId());
		po.setThreshold(vo.getThreshold());
		po.setTimeInterval(vo.getTimeInterval());
		po.setLogSaveTime(vo.getLogSaveTime());
		pushRuleMapper.updateByPrimaryKey(po);
	}

}

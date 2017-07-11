import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.boomhope.model.BussinessUserVo;
import com.boomhope.model.PushRuleVo;
import com.boomhope.model.VipCustomerVo;
import com.boomhope.model.VipLogExtendVo;
import com.boomhope.po.VipLogExtend;
import com.boomhope.service.IBaseService;
import com.boomhope.service.IBussinessUserService;
import com.boomhope.service.ICustomerManagementService;
import com.boomhope.service.IPushRuleService;
import com.boomhope.service.IVipLogService;
import com.boomhope.util.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
// 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration("classpath:applicationContext.xml")
public class PushRuleTest {

	private final static Logger logger = LoggerFactory.getLogger(PushRuleTest.class);

	private IBaseService baseService;

	@Resource(name = "baseService")
	public void setBaseService(IBaseService baseService) {
		this.baseService = baseService;
	}

	private IPushRuleService pushRuleService;

	@Resource(name = "pushRuleService")
	public void setPushRuleService(IPushRuleService pushRuleService) {
		this.pushRuleService = pushRuleService;
	}

	private ICustomerManagementService customerManagementService;

	@Resource(name = "customerManagementService")
	public void setCustomerManagementService(ICustomerManagementService customerManagementService) {
		this.customerManagementService = customerManagementService;
	}
	
	private IVipLogService vipLogService;

	@Resource(name = "vipLogService")
	public void setVipLogService(IVipLogService vipLogService) {
		this.vipLogService = vipLogService;
	}

	private IBussinessUserService bussinessUserService;
	@Resource(name="bussinessUserService")
	public void setBussinessUserService(IBussinessUserService bussinessUserService) {
		this.bussinessUserService = bussinessUserService;
	}
	
	
	@Test
	public void queryVipLogForPad(){
		
		Map<String,String> parMap = new HashMap<String,String>();
		parMap.put("timeInterval", "7000000");
		
		List<VipLogExtendVo> list = vipLogService.queryVipLogForPadList(parMap);		
	}
	
//	测试spring的事务管理
	@Transactional
	@Test
	public void TestTransaction(){
		
		String threshold = "85";
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
		}
		
		
	}
	
	
	
	
	
	@Test
	public void deleteByLogSaveTime(){
		
		String logSaveTime ="60";
		vipLogService.deleteByLogSaveTime(logSaveTime);
	}
	
	@Test
	public void queryVipCustomer() {
		Map<String, String> parMap = new HashMap<String, String>();
		parMap.put("custNo", "fyl");
		List<VipCustomerVo> vipCustomerVoList = customerManagementService.findVipCustomerList(parMap);
	}

	// 测试页面功能：推送规则管理页面；可以先默认在表中添加一条数据， 后面的保存操作，调用先查询再更新有变化的字段的方法；
	@Test
	public void queryPushRule() throws Exception {
		PushRuleVo pushRuleVo = pushRuleService.selectByPrimaryKey("3");
		String threshold = "1";
		// String timeInterval = "86";
		// String logSaveTime = "86";
		// PushRuleVo pushRuleVo = new PushRuleVo();
		pushRuleVo.setRuleId("2");
		pushRuleVo.setThreshold(threshold);
		// pushRuleVo.setTimeInterval(timeInterval);
		// pushRuleVo.setLogSaveTime(logSaveTime);
		pushRuleService.updateByPrimaryKeySelective(pushRuleVo);
		logger.info("更新成功！");
	}

	// 增加
	@Test
	public void addRule() throws Exception {
		String threshold = "85";
		String timeInterval = "85";
		String logSaveTime = "85";
		PushRuleVo pushRuleVo = new PushRuleVo();
		pushRuleVo.setRuleId(baseService.getSeq("push_rule"));
		pushRuleVo.setThreshold(threshold);
		pushRuleVo.setTimeInterval(timeInterval);
		pushRuleVo.setLogSaveTime(logSaveTime);
		pushRuleService.insert(pushRuleVo);
	}

	@Test
	public void updateControlInfo() throws Exception {
		String threshold = "86";
		String timeInterval = "86";
		String logSaveTime = "86";
		PushRuleVo pushRuleVo = new PushRuleVo();
		pushRuleVo.setRuleId("5");
		pushRuleVo.setThreshold(threshold);
		pushRuleVo.setTimeInterval(timeInterval);
		pushRuleVo.setLogSaveTime(logSaveTime);
		pushRuleService.updateByPrimaryKeySelective(pushRuleVo);
	}

 }

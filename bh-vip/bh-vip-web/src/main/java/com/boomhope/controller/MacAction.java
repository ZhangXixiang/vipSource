package com.boomhope.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boomhope.model.MacMachineVo;
import com.boomhope.service.IBaseService;
import com.boomhope.service.IMacService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


/**
 * 营业厅管理模块功能控制器
 * 
 * @version 1.0 2017-03-08
 * 
 * @author zy
 *
 */
@Controller
@Scope("prototype")
public class MacAction extends BaseAction {
	
	/**
	 * 日志类
	 */
	private final static Logger logger = LoggerFactory.getLogger(MacAction.class); 
	
	/**
	 * 设备服务类
	 */
	private IMacService macService;
	
	@Resource(name = "macService")
	public void setMacService(IMacService macService) {
		this.macService = macService;
	}
	
	
	/**
	 * 部署服务类
	 */
//	private IDeployService deployService;
//	
//	@Resource(name = "deployService")
//	public void setDeployService(IDeployService deployService) {
//		this.deployService = deployService;
//	}
	
	private IBaseService baseService;
	
	@Resource(name = "baseService")
	public void setBaseService(IBaseService baseService) {
		this.baseService = baseService;
	}

	/**
	 * 查询设备信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/mac/queryMacInfo")
    public @ResponseBody Object queryMacInfo(@RequestBody Map map)  {
		String unitCode = null;
		if(map.get("unitCode") != null && !"".equals(map.get(unitCode))){
			unitCode = (String)map.get("unitCode");
		}
		Integer offset = Integer.parseInt(map.get("pageNumber").toString());
		Integer limit = Integer.parseInt(map.get("pageSize").toString());
	    String orderBy = "create_date desc";
		// 根据查询条件查询
		Map<String,String> parMap = new HashMap<String,String>();
		parMap.put("unitCode", unitCode);
		Page<MacMachineVo> paged = PageHelper.startPage(offset, limit, orderBy);
		
		try {
			macService.queryMacMachine(parMap);
		} catch (Exception e) {
			logger.error("查询出错",e);
			return returnFail("查询失败");
		}
		
		PageInfo<MacMachineVo> pageInfo  = new PageInfo<MacMachineVo> (paged);
		return returnResult(pageInfo, pageInfo.getList());
	}
	
	/**
	 * 删除设备信息
	 * @param request
	 * @param response
	 * @return
	 */
//	@RequestMapping("/mac/deleteMacInfo")
//	public @ResponseBody Object deleteMacInfo(HttpServletRequest request,HttpServletResponse response)  {
//		JSONObject json = getReqData(request);
//		if(logger.isDebugEnabled()){
//			logger.debug("接入参数"+json.toString());
//		}
//		// 1.获取入参
//		String ids = json.getString("macId");
//		List list = null;
//		try {
//			//根据机器的ID查询出来机器的状态
//			list = deployService.selectStatusByMacIds(ids);
//			if(list.size() > 0){
//				return returnFail("删除的设备记录已在部署维护使用，不可删除！");
//			}
//			// 删除选中记录
//			macService.deleteMacMachineVo(ids);
//		} catch (Exception e) {
//			logger.error("查询失败",e);
//			return returnFail("删除失败");
//		}
//		
//		
//		return returnSucess();
//	}
}

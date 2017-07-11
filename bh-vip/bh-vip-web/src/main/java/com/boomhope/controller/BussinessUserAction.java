package com.boomhope.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boomhope.model.BussinessUserExtendVo;
import com.boomhope.model.BussinessUserVo;
import com.boomhope.model.SysAdminVo;
import com.boomhope.service.IBussinessUserService;
import com.boomhope.util.DateUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 营业厅员工管理模块
 * @author renxl
 *@version 1.0 2017-06-19
 */
@Controller
@Scope("prototype")
public class BussinessUserAction extends BaseAction{
	
	//日志
	private final static Logger logger = LoggerFactory.getLogger(BussinessUserAction.class);
	
	private IBussinessUserService bussinessUserService;
	@Resource(name="bussinessUserService")
	public void setBussinessUserService(IBussinessUserService bussinessUserService) {
		this.bussinessUserService = bussinessUserService;
	}
	
	/**
	 * 根据营业厅员工代码查询营业厅员工信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/bussinessUser/qryBussinessUserByPrimaryKey")
	public @ResponseBody Object qryBussinessUserByPrimaryKey(HttpServletRequest request,HttpServletResponse response){
		JSONObject json = getReqData(request);
		String userCode = (String)json.get("userCode");
		BussinessUserVo bussinessUserVo= null;
		try{
			bussinessUserVo = bussinessUserService.selectByPrimaryKey(userCode);
		}catch(Exception e){
			logger.error("查询出错！",e);
			return returnFail("查询失败！");
		}
		return bussinessUserVo;
	}
	
	/**
	 * 查询营业厅员工信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/bussinessUser/qryBussinessUserList")
	public @ResponseBody Object qryBussinessUserList(@RequestBody Map map){
		String creditId = null;
		String userName = null;
		String unitCode = null;
		if(map.get("creditId") != null && !"".equals(map.get(creditId))){
			creditId = (String)map.get("creditId");
		}
		if(map.get("userName") != null && !"".equals(map.get(userName))){
			userName = (String)map.get("userName");
		}
		if(map.get("unitCode") != null && !"".equals(map.get(unitCode))){
			unitCode = (String)map.get("unitCode");
		}
		Integer offset = Integer.parseInt(map.get("pageNumber").toString());
		Integer limit = Integer.parseInt(map.get("pageSize").toString());
		String orderBy = null;
		orderBy = "bu.create_date desc";
		Map<String,String> userMap = new HashMap<String,String>();
		userMap.put("creditId", creditId);
		userMap.put("userName", userName);
		userMap.put("unitCode", unitCode);
		Page<BussinessUserExtendVo> page = PageHelper.startPage(offset,limit,orderBy);
		try{
			bussinessUserService.selectAll(userMap);
		}catch(Exception e){
			logger.error("查询出错！",e);
			return returnFail("查询失败！");
		}
		PageInfo<BussinessUserExtendVo> pageInfo = new PageInfo<BussinessUserExtendVo>(page);
		return returnResult(pageInfo, pageInfo.getList());
	}
	
	/**
	 * 营业厅员工信息添加
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/bussinessUser/insert")
	public @ResponseBody Object insert(HttpServletRequest request,HttpServletResponse response){
		JSONObject json = getReqData(request);
		if(logger.isDebugEnabled()){
			logger.debug("接入参数" + json.toString());
		}
		// 1.获取入参
		String userCode = (String)json.get("userCode");
		//String unitCode = (String)json.get("unitCode");
		String userName = (String)json.get("userName");
		String loginName = (String)json.get("loginName");
		//String creditId = (String)json.get("creditId");
		//String sex = (String)json.get("sex");
		String phone = (String)json.get("phone");
		String telephone = (String)json.get("telephone");
		//String status = (String)json.get("status");
		try{
		// 2.查询当前营业厅员工是否存在，存在则提示：机构已存在，不可重复创建
		if(null != userCode && "".equals(userCode)){
			BussinessUserVo bussinessUserVo = null;
			
				bussinessUserVo = bussinessUserService.selectByPrimaryKey(userCode);
				if(null != bussinessUserVo && "".equals(bussinessUserVo)){
					return returnFail("对不起，已有该员工信息，不能重复添加！");
				}else{
					//添加
					bussinessUserVo.setUserCode(userCode);
					//bussinessUserVo.setUnitCode(unitCode);
					bussinessUserVo.setUserName(userName);
					bussinessUserVo.setLoginName(loginName);
					//bussinessUserVo.setCreditId(creditId);
					//bussinessUserVo.setSex(sex);
					bussinessUserVo.setPhone(phone);
					bussinessUserVo.setTelephone(telephone);
					bussinessUserVo.setCreateDate(DateUtil.getNowDate("yyyymmddHHMMss"));
					SysAdminVo sysAdmin = (SysAdminVo) request.getSession().getAttribute("loginUser");
					if(sysAdmin == null){
						return returnFail("未知异常");
					}
					//bussinessUserVo.setCreater(sysAdmin.getCreater());
					//bussinessUserVo.setStatus(status);
					bussinessUserService.insert(bussinessUserVo);
				}
			}else{
				return returnFail("对不起,必填项不得为空！");
			}
		}catch(Exception e){
			logger.error("查询出错！",e);
			return returnFail("查询失败！");
		}
		return returnSucess();
	}
	
	/**
	 * 营业厅员工信息删除
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/bussinessUser/deleteBussinessUser")
	public @ResponseBody Object deleteBussinessUser(HttpServletRequest request,HttpServletResponse response){
		JSONObject json = getReqData(request);
		if(logger.isDebugEnabled()){
			logger.debug("接入参数"+json.toString());
		}
		//获取参数
		String userCode = (String)json.get("userCode");
		try{
			bussinessUserService.deleteByPrimaryKey(userCode);
		}catch(Exception e){
			logger.error("删除失败！");
			return returnFail("删除失败");
		}
		return returnSucess();
	}
	
	/**
	 * 营业厅员工信息编辑
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/bussinessUser/editBussinessUser")
	public @ResponseBody Object editBussinessUser(HttpServletRequest request,HttpServletResponse response){
		JSONObject json = getReqData(request);
		if(logger.isDebugEnabled()){
			logger.debug("接入参数"+json.toString());
		}
		//1获取参数
		String userCode = (String)json.get("userCode");
		String unitCode = (String)json.get("unitCode");
		String userName = (String)json.get("userName");
		String loginName = (String)json.get("loginName");
		String creditId = (String)json.get("creditId");
		//String sex = (String)json.get("sex");
		String phone = (String)json.get("phone");
		String telephone = (String)json.get("telephone");
		//String status = (String)json.get("status");
		if(userCode != null && !"".equals(userCode)){
			BussinessUserVo bussinessUserVo = null;
			try{
				//2查询待修改记录
				bussinessUserVo = bussinessUserService.selectByPrimaryKey(userCode);
			}catch(Exception e){
				logger.debug("查询待修改记录失败！",e);
				return returnFail("查询待修改记录失败！");
			}
			if(bussinessUserVo == null){
				return returnFail("编辑对象不存在！");
			}else{
				//3保存
				bussinessUserVo.setUserCode(userCode);
				bussinessUserVo.setUnitCode(unitCode);
				bussinessUserVo.setUserName(userName);
				bussinessUserVo.setLoginName(loginName);
				bussinessUserVo.setCreditId(creditId);
				//bussinessUserVo.setSex(sex);
				bussinessUserVo.setPhone(phone);
				bussinessUserVo.setTelephone(telephone);
				bussinessUserVo.setCreateDate(DateUtil.getNowDate("yyyyMMddHHmmss"));
				//SysAdminVo sysAdminVo = (SysAdminVo)request.getSession().getAttribute(loginName);
				//if(sysAdminVo == null){
				//	return returnFail("未知异常！");
				//}
				//bussinessUserVo.setStatus(status);
				bussinessUserVo.setCreater(loginName);
				bussinessUserService.updateByPrimaryKeySelective(bussinessUserVo);
			}
		}else{
			return returnFail("必填项不能为空！");
		}
		return returnSucess();
	}
	
	/**
	 * 启用禁用
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/bussinessUser/enableBanBussinessUser")
	public @ResponseBody Object enableBanBussinessUser(HttpServletRequest request,HttpServletResponse response){
		JSONObject json = getReqData(request);
		if(logger.isDebugEnabled()){
			logger.debug("接入参数" + json.toString());
		}
		//获取参数
		String userCode = (String)json.get("userCode");
		String status = (String)json.get("status");
		BussinessUserVo bussinessUserVo = null;
		try{
			bussinessUserVo = bussinessUserService.selectByPrimaryKey(userCode);
			if("0".equals(status)){
				//禁用
				status = "1";
				bussinessUserVo.setStatus(status);
			}else{
				//启用
				status = "0";
				bussinessUserVo.setStatus(status);
			}
			bussinessUserVo.setStatus(status);
			bussinessUserService.enableBan(bussinessUserVo);
		}catch(Exception e){
			logger.error("修改状态失败！");
			return returnFail("修改状态失败！");
		}
		
		return returnSucess();
		
	}
	
	/**
	 * 营业厅员工信息添加
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/bussinessUser/insertSelective")
	public @ResponseBody Object insertSelective(HttpServletRequest request,HttpServletResponse response){
		JSONObject json = getReqData(request);
		if(logger.isDebugEnabled()){
			logger.debug("接入参数" + json.toString());
		}
		// 1.获取入参
		String userCode = (String)json.get("userCode");
		String unitCode = (String)json.get("unitCode");
		String userName = (String)json.get("userName");
		String loginName = (String)json.get("loginName");
		String creditId = (String)json.get("creditId");
		//String sex = (String)json.get("sex");
		String phone = (String)json.get("phone");
		String telephone = (String)json.get("telephone");
		//String status = (String)json.get("status");
		
		// 2.查询当前营业厅员工是否存在，存在则提示：机构已存在，不可重复创建
		if(null != userCode && !"".equals(userCode)){
			BussinessUserVo bussinessUserVo = null;
			try{
				bussinessUserVo = bussinessUserService.selectByPrimaryKey(userCode);
			}catch(Exception e){
					logger.error("查询出错！",e);
					return returnFail("查询失败！");
				}
				if(null == bussinessUserVo ){
					//添加
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
					SysAdminVo sysAdmin = (SysAdminVo) request.getSession().getAttribute("loginUser");
					if(sysAdmin == null){
						return returnFail("未知异常");
					}
					UserVo.setCreater(sysAdmin.getCreater());
					UserVo.setStatus("1");
					try{
					bussinessUserService.insertSelective(UserVo);
					}catch(Exception e){
						logger.error("添加失败",e);
						return returnFail("添加失败");
					}
					return returnSucess();
				}else{
					logger.error("该用户已经存在！");
					return returnFail("该用户已经存在！");
				}
			}else{
				return returnFail("对不起,必填项不得为空！");
			}
	}
}

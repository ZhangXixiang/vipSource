package com.boomhope.controller;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.boomhope.excelUtil.ExportExcelUtil;
import com.boomhope.excelUtil.ImportExcelUtil;
import com.boomhope.model.BussinessUserExtendVo;
import com.boomhope.model.DeployUnitVo;
import com.boomhope.model.SysAdminVo;
import com.boomhope.service.IBusinessManagentService;
import com.boomhope.service.IBussinessUserService;
import com.boomhope.util.DateUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 营业厅管理功能控制器
 * 
 * @version 1.0 2017-03-08
 * 
 * @author yzw
 *
 */
@Controller
@Scope("prototype")
public class BusinessManagentAction extends BaseAction {
	/**
	 * 日志类
	 */
	private final static Logger logger = LoggerFactory.getLogger(BusinessManagentAction.class);

	/**
	 * 客户服务类
	 */
	private IBusinessManagentService businessManagentService;

	@Resource(name = "businessManagentService")
	public void setBusinessManagentService(IBusinessManagentService businessManagentService) {
		this.businessManagentService = businessManagentService;
	}

	private IBussinessUserService bussinessUserService;

	@Resource(name = "bussinessUserService")
	public void setBussinessUserService(IBussinessUserService bussinessUserService) {
		this.bussinessUserService = bussinessUserService;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/findBusinessManagentList")
	public @ResponseBody Object queryBusinessManagentInfo(@RequestBody Map map) {
		String unitCode = null;
		if (map.get("unitCode") != null && !map.get("unitCode").equals("")) {
			unitCode = (String) map.get("unitCode");
		}
		String unitName = null;
		if (map.get("unitName") != null && !map.get("unitName").equals("")) {
			unitName = (String) map.get("unitName");
		}
		Integer offset = Integer.parseInt(map.get("pageNumber").toString());
		Integer limit = Integer.parseInt(map.get("pageSize").toString());
		String orderBy = "create_date desc";
		// 根据查询条件查询
		Map<String, String> parMap = new HashMap<String, String>();
		parMap.put("unitCode", unitCode);
		parMap.put("unitName", unitName);
		Page<DeployUnitVo> paged = PageHelper.startPage(offset, limit, orderBy);
		try {
			businessManagentService.findBusinessManagentList(parMap);
		} catch (Exception e) {
			logger.error("查询出错", e);
			return returnFail("查询出错");
		}
		PageInfo<DeployUnitVo> pageInfo = new PageInfo<DeployUnitVo>(paged);
		return returnResult(pageInfo, pageInfo.getList());
	}

	/**
	 * 添加营业厅信息
	 */
	@Transactional
	@RequestMapping("/addBusinessManagent")
	public @ResponseBody Object addMacInfo(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = getReqData(request);
		if (logger.isDebugEnabled()) {
			logger.debug("接入参数" + json.toString());
		}
		// 1.获取入参
		String unitCode = (String) json.get("unitCode");
		String unitName = (String) json.get("unitName");
		Map<String, String> parMap = new HashMap<String, String>();
		parMap.put("unitCode", unitCode);
		parMap.put("unitName", unitName);
		// 判断证件号 和名字 是否为空
		if (unitCode != null && !"".equals(unitCode) && unitName != null && !"".equals(unitName)) {
			// 2.根据macModel查询 规格型号是否存在,存在则提示错误
			List<DeployUnitVo> deployUnitList = null;
			try {
				deployUnitList = businessManagentService.findBusinessManagentList(parMap);
			} catch (Exception e) {
				logger.error("查询出错", e);
				return returnFail("查询出错");
			}
			if (deployUnitList != null && deployUnitList.size() > 0) {
				return returnFail("对不起，营业厅代码或者营业厅名称不能重复！");
			} else {
				// 3.保存
				DeployUnitVo du = new DeployUnitVo();
				du.setAddress((String) json.get("address"));
				SysAdminVo sysAdmins = (SysAdminVo) request.getSession().getAttribute("loginUser");
				if (sysAdmins == null) {
					return returnFail("未知异常");
				}
				du.setCreater(sysAdmins.getCreater());
				du.setEmail((String) json.get("email"));
				du.setManager((String) json.get("manager"));
				du.setParentCode((String) json.get("parentCode"));
				du.setPhone((String) json.get("phone"));
				du.setStatus("1");// 1：启用；0：禁用
				du.setUnitCode(unitCode);
				du.setUnitName(unitName);
				// StringBuilder sb=new StringBuilder((String)
				// json.get("unitTel"));
				// du.setUnitTel(sb.insert(4, "-").toString());
				String unitTel = (String) json.get("unitTel");
				du.setUnitTel(unitTel);
				du.setCreateDate(DateUtil.getNowDate("yyyyMMddHHmmss"));
				try {
					businessManagentService.addBusinessManagent(du);
				} catch (Exception e) {
					logger.error("添加失败", e);
					return returnFail("添加失败");
				}
			}
		} else {
			return returnFail("对不起,必填项不得为空！");
		}
		return returnSucess();
	}

	/**
	 * 编辑信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/updateBusinessManagent")
	public @ResponseBody Object updateMacInfo(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = getReqData(request);
		if (logger.isDebugEnabled()) {
			logger.debug("接入参数" + json.toString());
		}
		// 1.获取入参
		String unitCode = (String) json.get("unitCode");
		String status = (String) json.get("status");

		// 2.
		if (unitCode != null && !"".equals(unitCode)) {
			DeployUnitVo du = null;
			try {
				du = businessManagentService.selectByPrimaryKey(unitCode);
			} catch (Exception e) {
				logger.error("查询失败", e);
				return returnFail("编辑失败");
			}

			if (du == null) {
				return returnFail("对不起,未查询到记录！");
			} else if (status != null && !"".equals(status)) {
				if ("1".equals(status)) {
					du.setStatus("0");
				}
				if ("0".equals(status)) {
					du.setStatus("1");
				}
				businessManagentService.updateDeployUnitStatus(du);
				logger.info("状态更新成功");
			} else {
				try {
					Map<String, String> parMap = new HashMap<String, String>();
					// parMap.put("unitCode", unitCode);
					parMap.put("unitName", (String) json.get("unitName"));
					// 2.根据macModel查询 规格型号是否存在,存在则提示错误
					List<DeployUnitVo> deployUnitList = null;
					deployUnitList = businessManagentService.findBusinessManagentList(parMap);
					if (deployUnitList != null && deployUnitList.size() > 0 && !deployUnitList.get(0).getUnitCode().equals(unitCode)) {
						logger.error("修改后的营业厅名称重复！");
						return returnFail("修改后的营业厅名称重复！");
					}
					businessManagentService.updateBusinessManagent(du);
				} catch (Exception e) {
					logger.error("状态修改失败", e);
					return returnFail("状态修改失败");
				}
				du.setAddress((String) json.get("address"));
				du.setEmail((String) json.get("email"));
				du.setManager((String) json.get("manager"));
				du.setParentCode((String) json.get("parentCode"));
				du.setPhone((String) json.get("phone"));
				du.setUnitName((String) json.get("unitName"));
				StringBuilder sb = new StringBuilder((String) json.get("unitTel"));
				du.setUnitTel(sb.insert(4, "-").toString());
				try {
					businessManagentService.updateBusinessManagent(du);
				} catch (Exception e) {
					logger.error("编辑失败", e);
					return returnFail("编辑失败");
				}

				logger.info("更新成功");
			}
		} else {
			return returnFail("对不起,必填项不得为空！");
		}
		return returnSucess();
	}

	/**
	 * 删除信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/deleteBusinessManagent")
	public @ResponseBody Object deleteMacInfo(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = getReqData(request);
		if (logger.isDebugEnabled()) {
			logger.debug("接入参数" + json.toString());
		}
		// 1.获取入参
		String unitCodes = (String) json.get("unitCode");
		// 添加逻辑，如果该营业厅已经有员工属于该营业厅，则提示不能删除；
		Map<String, String> map = new HashMap<String, String>();
		map.put("unitCode", unitCodes);

		List<BussinessUserExtendVo> voList = bussinessUserService.selectAll(map);
		if (voList != null && voList.size() > 0) {
			logger.info("您删除的营业厅已存在员工信息，不能删除!");
			return returnFail("您删除的营业厅已存在员工信息，不能删除!");
		}
		try {
			businessManagentService.deleteBusinessManagent(unitCodes);
		} catch (Exception e) {
			logger.error("删除失败", e);
			return returnFail("删除失败");
		}
		return returnSucess();

	}

	/**
	 * 导入excel
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("business/importExcel")
	public @ResponseBody Object importExcel(HttpServletRequest request, HttpServletResponse response) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		System.out.println("通过传统方式form表单提交方式导入excel文件！");

		InputStream in = null;
		List<List<Object>> listob = null;
		MultipartFile file = multipartRequest.getFile("file");
		if (file.isEmpty()) {
			return returnFail("文件不存在！");
		}

		// 读取表格数据
		try {
			in = file.getInputStream();
			listob = new ImportExcelUtil().getBankListByExcel(in, file.getOriginalFilename());
			in.close();
		} catch (Exception e) {
			e.getMessage();
			if (e.getMessage().equals("1001")) {
				logger.info("数据条数超过1000");
				return returnFail("单次导入条数不能超过1000条");
			}
			logger.info("读取表格内容失败:" + e.toString());
			return returnFail("读取表格内容失败");
		}

		// 对读取的数据进行处理，并存入数据库
		SysAdminVo sysAdmins = (SysAdminVo) request.getSession().getAttribute("loginUser");
		if (sysAdmins == null) {
			return returnFail("用户未登录");
		}
		try {
			businessManagentService.importExcel(listob, sysAdmins.getCreater());
		} catch (Exception e) {
			logger.error("插入数据库失败", e);
			return returnFail("插入数据库失败");
		}
		return returnSucess();
	}

	/**
	 * 描述：通过 jquery.form.js 插件提供的ajax方式导出Excel
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/business/exportExcel")
	public String ajaxUploadExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("通过 jquery.form.js 提供的ajax方式导出文件！");
		OutputStream os = null;
		Workbook wb = null; // 工作薄

		try {
			// 导出项目中的Excel
			logger.info("进入到/business/exportExcel查询方法中，开始生成excel！");
			JSONObject json = getReqData(request);
			String reportDate = (String) json.get("reportDate");
			if (reportDate != null) {
				reportDate = reportDate.replace("-", "");
			}
			Map<String, String> parMap = new HashMap<String, String>();
			parMap.put("reportDate", reportDate);
			ExportExcelUtil util = new ExportExcelUtil();
			File file = util.getExcelDemoFile("/ExcelDemoFile/bussinessHallExcelDemo.xlsx");
			String sheetName = "sheet1";
			wb = util.writeNewExcel(file, sheetName);

			String fileName = "营业厅信息Excel.xlsx";
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
			os = response.getOutputStream();
			wb.write(os);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			os.flush();
			os.close();
			wb.close();
		}
		return null;
	}

}

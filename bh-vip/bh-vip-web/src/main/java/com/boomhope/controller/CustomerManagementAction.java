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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.boomhope.excelUtil.ExportExcelUtil;
import com.boomhope.excelUtil.ImportExcelUtil;
import com.boomhope.model.SysAdminVo;
import com.boomhope.model.VipCustomerVo;
import com.boomhope.service.IBaseService;
import com.boomhope.service.ICustomerManagementService;
import com.boomhope.util.DateUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 客户管理功能控制器
 * 
 * @version 1.0 2017-03-08
 * 
 * @author yzw
 *
 */
@Controller
@Scope("prototype")
public class CustomerManagementAction extends BaseAction {
	/**
	 * 日志类
	 */
	private final static Logger logger = LoggerFactory.getLogger(CustomerManagementAction.class);

	/**
	 * 客户管理服务类
	 */
	private ICustomerManagementService customerManagementService;

	@Resource(name = "customerManagementService")
	public void setCustomerManagementService(ICustomerManagementService customerManagementService) {
		this.customerManagementService = customerManagementService;
	}

	private IBaseService baseService;

	@Resource(name = "baseService")
	public void setBaseService(IBaseService baseService) {
		this.baseService = baseService;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/findVipCustomerList")
	public @ResponseBody Object queryVipCustomerInfo(@RequestBody Map map) {
		String custName = null;
		if (map.get("custName") != null && !map.get("custName").equals("")) {
			custName = (String) map.get("custName");
		}
		String creditNo = null;
		if (map.get("creditNo") != null && !map.get("creditNo").equals("")) {
			creditNo = (String) map.get("creditNo");
		}
		Integer offset = Integer.parseInt(map.get("pageNumber").toString());
		Integer limit = Integer.parseInt(map.get("pageSize").toString());
		String orderBy = "create_date desc";
		// 根据查询条件查询
		Map<String, String> parMap = new HashMap<String, String>();
		parMap.put("custName", custName);
		parMap.put("creditNo", creditNo);
		Page<VipCustomerVo> paged = PageHelper.startPage(offset, limit, orderBy);
		try {
			customerManagementService.findVipCustomerList(parMap);
		} catch (Exception e) {
			logger.error("查询出错", e);
			return returnFail("查询出错");
		}
		PageInfo<VipCustomerVo> pageInfo = new PageInfo<VipCustomerVo>(paged);
		return returnResult(pageInfo, pageInfo.getList());
	}

	/**
	 * 添加客户信息
	 */
	@RequestMapping("/addVipCustomer")
	public @ResponseBody Object addMacInfo(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = getReqData(request);
		if (logger.isDebugEnabled()) {
			logger.debug("接入参数" + json.toString());
		}
		// 1.获取入参
		String creditNo = (String) json.get("creditNo");
		String creditType = (String) json.get("creditType");
		String custLevel = (String) json.get("custLevel");
		String custName = (String) json.get("custName");
		String custBir = (String) json.get("custBir");
		String custSex = (String) json.get("custSex");
		String custNo = (String) json.get("custNo");
		Map<String, String> parMap = new HashMap<String, String>();
		// parMap.put("creditType", creditType);
		parMap.put("custNo", custNo);
		// 判断证件号 和名字 是否为空
		if (custNo != null && !"".equals(custNo)) {
			List<VipCustomerVo> vipCustomerObjList = null;
			try {
				vipCustomerObjList = customerManagementService.selectByCustNolList(parMap);
			} catch (Exception e) {
				logger.error("查询出错", e);
				return returnFail("查询出错");
			}
			if (vipCustomerObjList != null && vipCustomerObjList.size() > 0) {
				return returnFail("对不起，客户号不能重复，客户已经存在！");
			} else {
				// 3.保存
				VipCustomerVo dm = new VipCustomerVo();
				dm.setVipCusId(baseService.getSeq("vip_customer"));
				dm.setCreditNo(creditNo);
				dm.setCustLevel(custLevel);
				dm.setCustName(custName);
				dm.setCreditType(creditType);
				dm.setCustBir(custBir);
				dm.setCustSex(custSex);
				dm.setCustNo(custNo);
				dm.setCreateDate(DateUtil.getNowDate("yyyyMMddHHmmss"));
				try {
					customerManagementService.addVipCustomer(dm);
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
	@RequestMapping("/updateVipCustomer")
	public @ResponseBody Object updateMacInfo(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = getReqData(request);
		if (logger.isDebugEnabled()) {
			logger.debug("接入参数" + json.toString());
		}
		// 1.获取入参
		String vipCusId = (String) json.get("vipCusId");
		if (vipCusId != null && !"".equals(vipCusId)) {
			VipCustomerVo custommerVo = null;
			try {
				custommerVo = customerManagementService.selectByPrimaryKey(vipCusId);
			} catch (Exception e) {
				logger.error("查询失败", e);
				return returnFail("编辑失败");
			}

			if (custommerVo == null) {
				return returnFail("对不起,未查询到记录！");
			} else {

				custommerVo.setCustName((String) json.get("custName"));
				custommerVo.setCustSex((String) json.get("custSex"));
				custommerVo.setCustNo((String) json.get("custNo"));
				custommerVo.setCreditType((String) json.get("creditType"));
				custommerVo.setCustBir((String) json.get("custBir"));
				custommerVo.setCustLevel((String) json.get("custLevel"));
				custommerVo.setCreditNo((String) json.get("creditNo"));
				try {
					customerManagementService.updateVipCustomerVo(custommerVo);
				} catch (Exception e) {
					logger.error("查询失败", e);
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
	 * 删除客户维护信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/deleteCustName")
	public @ResponseBody Object deleteMacInfo(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = getReqData(request);
		if (logger.isDebugEnabled()) {
			logger.debug("接入参数" + json.toString());
		}
		// 1.获取入参
		String vipcustIds = (String) json.get("custNo");
		try {
			customerManagementService.deleteCustlist(vipcustIds);
		} catch (Exception e) {
			logger.error("删除失败", e);
			return returnFail("删除失败");
		}
		return returnSucess();

	}

	/**
	 * 描述：通过传统方式form表单提交方式导入excel文件
	 * 
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping("uploadExcel/upload")
	public @ResponseBody Object upload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// MultipartResolver resolver = new
		// CommonsMultipartResolver(request.getSession().getServletContext());
		// MultipartHttpServletRequest multipartRequest =
		// resolver.resolveMultipart(request);
		logger.info("通过传统方式form表单提交方式导入excel文件！");

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
			customerManagementService.importExcel(listob);
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
	@RequestMapping("/vip/exportExcel")
	public String ajaxUploadExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("通过 jquery.form.js 提供的ajax方式导出文件！");
		OutputStream os = null;
		Workbook wb = null; // 工作薄

		try {
			// 导出项目中的Excel
			logger.info("进入到/vip/exportExcel查询方法中，开始生成excel！");
			JSONObject json = getReqData(request);
			String reportDate = (String) json.get("reportDate");
			if (reportDate != null) {
				reportDate = reportDate.replace("-", "");
			}
			Map<String, String> parMap = new HashMap<String, String>();
			parMap.put("reportDate", reportDate);
			ExportExcelUtil util = new ExportExcelUtil();
			File file = util.getExcelDemoFile("/ExcelDemoFile/vipCustomerExcelDemo.xlsx");
			String sheetName = "sheet1";
			wb = util.writeNewExcel(file, sheetName);

			String fileName = "VIP客户信息Excel.xlsx";
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

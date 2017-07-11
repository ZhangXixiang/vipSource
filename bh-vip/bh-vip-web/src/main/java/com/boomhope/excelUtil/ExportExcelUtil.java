package com.boomhope.excelUtil;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExportExcelUtil {

	/**
	 * 描述：根据文件路径获取项目中的文件
	 * 
	 * @param fileDir
	 *            文件路径
	 * @return
	 * @throws Exception
	 */
	public File getExcelDemoFile(String fileDir) throws Exception {
		String classDir = null;
		String fileBaseDir = null;
		File file = null;
		classDir = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
		fileBaseDir = classDir.substring(0, classDir.lastIndexOf("classes"));

		file = new File(fileBaseDir + fileDir);
		if (!file.exists()) {
			throw new Exception("模板文件不存在！");
		}
		return file;
	}

	public Workbook writeNewExcel(File file, String sheetName) throws Exception {
		Workbook wb = null;
		Row row = null;
		Cell cell = null;

		FileInputStream fis = new FileInputStream(file);
		wb = new ImportExcelUtil().getWorkbook(fis, file.getName()); // 获取工作薄
		Sheet sheet = wb.getSheet(sheetName);

		// 循环插入数据
		int lastRow = sheet.getLastRowNum() + 1; // 插入数据的数据ROW
		CellStyle cs = setSimpleCellStyle(wb); // Excel单元格样式

		row = sheet.createRow(lastRow); // 创建新的ROW，用于数据插入

		cell = row.createCell(0);
		return wb;
	}

	/**
	 * 描述：设置简单的Cell样式
	 * 
	 * @return
	 */
	public CellStyle setSimpleCellStyle(Workbook wb) {
		CellStyle cs = wb.createCellStyle();

		cs.setBorderBottom(CellStyle.BORDER_THIN); // 下边框
		cs.setBorderLeft(CellStyle.BORDER_THIN);// 左边框
		cs.setBorderTop(CellStyle.BORDER_THIN);// 上边框
		cs.setBorderRight(CellStyle.BORDER_THIN);// 右边框

		cs.setAlignment(CellStyle.ALIGN_CENTER); // 居中

		return cs;
	}

}

package com.obs.datahandler;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataHandler {

	static XSSFWorkbook workbook; 
	static XSSFSheet sheet;
	
	public static final String currentDir = System.getProperty("user.dir");
	public static final String filePath = currentDir + "/src/test/resources/";
	
	public ExcelDataHandler(String fileName, String sheetName) throws IOException {
		String excelPath = filePath+fileName;
		workbook = new XSSFWorkbook(excelPath);
		sheet = workbook.getSheet(sheetName);
		
	}
	
	/**
	 * method to get rowsCount
	 */
	public static int getRowCount() {
		int rowCount = 0;
		try {
			rowCount = sheet.getPhysicalNumberOfRows();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rowCount;
	}

	/**
	 * method to get columnCount
	 */
	public static int getColCount() {
		int colCount = 0;
		try {
			colCount = sheet.getRow(0).getLastCellNum();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return colCount;
	}
	
	/**
	 * method to get cell data (String)
	 */
	public String getCellDataString(int rowNum, int colNum) {
		String cellValue = null;
		try {
			cellValue = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return cellValue;
	}
}

package com.framework.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelDataProvider {

	XSSFWorkbook wb;
	DataFormatter df;

	public ExcelDataProvider() {

		File file = new File(System.getProperty("user.dir") + "/TestData/TestData.xlsx");

		try {
			FileInputStream fis = new FileInputStream(file);
			wb = new XSSFWorkbook(fis);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to read the TestData sheet" + e.getMessage());
		}

	}

	public String getTestDataByIndex(int sheetNumber, int row, int col) {

		return wb.getSheetAt(sheetNumber).getRow(row).getCell(col).getStringCellValue();
	}

	public String getTestDataBySheetName(String sheetName, int row, int col) {

		return wb.getSheet(sheetName).getRow(row).getCell(col).getStringCellValue();
	}

	public double getNumericTestData(int sheetNumber, int row, int col) {

		return wb.getSheetAt(sheetNumber).getRow(row).getCell(col).getNumericCellValue();
	}
	
	
	public List<Map<String,String>> getTestDataInMap(){
		
		List<Map<String,String>> allRows=null;
		Map<String,String> testData = null;
		XSSFSheet sheet = wb.getSheetAt(0);
		try {
			wb.close();
		} catch (IOException e) {
			System.out.println("Unable to close the workbook");
			e.printStackTrace();
		}
		
		int lastRowNumber = sheet.getLastRowNum();
		int lastColNumber = sheet.getRow(0).getLastCellNum();
		
		List list = new ArrayList();
		df = new DataFormatter();
		
		for (int i=0; i<lastColNumber;i++) {
			Row row = sheet.getRow(0);
			Cell cell = row.getCell(i);		
			String rowHeader = df.formatCellValue(cell).trim();
			list.add(rowHeader);
		}
		
		allRows= new ArrayList<Map<String,String>>();
		
		for(int j=1; j<=lastRowNumber;j++) {
			Row row = sheet.getRow(j);
			testData = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
			for (int k = 0; k < lastColNumber; k++) {
				
				Cell cell = row.getCell(k);		
				String colValue = df.formatCellValue(cell).trim();
				
				testData.put((String)list.get(k), colValue);
			}
			
			allRows.add(testData);
		}
		
		return allRows;
	}
	
	@DataProvider(name="testData2")
	public Object[][] dataProvider(Method m){
		
		String testMethodName = m.getName();
		String[] allSplit = testMethodName.split("_");
		String sheetname = allSplit[0];
		XSSFSheet sheet = wb.getSheet(sheetname);
		try {
			wb.close();
		} catch (IOException e) {
			System.out.println("Unable to close the workbook");
			e.printStackTrace();
		}
		
		int lastRowNumber = sheet.getLastRowNum();
		int lastColNumber = sheet.getRow(0).getLastCellNum();
		
		Object[][] obj = new Object[lastRowNumber][1];
		df = new DataFormatter();
		for(int i=0; i<lastRowNumber;i++) {
			
			Map<Object,Object> datamap = new HashMap<Object, Object>();
			
			for(int j=0; j<lastColNumber;j++) {
				
				Cell cell1 = sheet.getRow(0).getCell(j);
				Cell cell2 = sheet.getRow(i+1).getCell(j);
				datamap.put(df.formatCellValue(cell1), df.formatCellValue(cell2));
			}
			
			obj[i][0]=datamap;
		}
		
		return obj;
	}
	
	@DataProvider(name="testData")
	public Object[][] dataProviderToFetchSingleValue(Method m){
		
		String testMethodName = m.getName();
		String[] allSplit = testMethodName.split("_");
		String sheetname = allSplit[0];
		XSSFSheet sheet = wb.getSheet(sheetname);
		try {
			wb.close();
		} catch (IOException e) {
			System.out.println("Unable to close the workbook");
			e.printStackTrace();
		}
		
		int lastRowNumber = sheet.getLastRowNum();
		int lastColNumber = sheet.getRow(0).getLastCellNum();
		
		Object[][] obj = new Object[1][1];
		df = new DataFormatter();
		for(int i=0; i<lastRowNumber;i++) {
			Row row = sheet.getRow(i);
			Cell cell = row.getCell(0);
			
			if(df.formatCellValue(cell).equals(m.getName())) {
			Map<Object,Object> datamap = new HashMap<Object, Object>();
			
			for(int j=0; j<lastColNumber;j++) {
				
				Cell cell1 = sheet.getRow(0).getCell(j);
				Cell cell2 = sheet.getRow(i).getCell(j);
				datamap.put(df.formatCellValue(cell1), df.formatCellValue(cell2));
			}
			
			obj[0][0]=datamap;
		}
		}
		
		return obj;
	}

}

package com.tt.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtil {

	public String filePath = "";
	public String currentSheet="UserData";
	public int rowCount=0;
	XSSFWorkbook wb;
	XSSFSheet sheet;
	InputStream is;

	HashMap<String,Integer> column;

	// Enable the filePath...
	public XLUtil(String filePath) {
		this(filePath,"UserData");
	}

	// Different data sheet name...
	public XLUtil(String filePath,String currentSheet) {
		this.filePath=filePath;
		this.currentSheet = currentSheet;
		load();
	}

	public void load() {
		try {
			InputStream is = new FileInputStream(this.filePath);
			wb = new XSSFWorkbook(is);
			loadSheet(this.currentSheet);

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void loadSheet(String sheetName) {
		sheet = wb.getSheet(sheetName);
		getRowCount();
		loadColumns();

	}

	public void close() {
		try {
			if(is!=null) {
				is.close();
			}

		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void loadColumns() {

		column = new HashMap();
		Row headerRow = sheet.getRow(0);
		int currentCellIndex =0;
		Iterator<Cell> cells = headerRow.iterator();
		while(cells.hasNext()) 
		{
			Cell cell = cells.next();
			String colName = getCellValue(cell);
			column.put(colName,currentCellIndex);
			currentCellIndex++;
		}
	}

	public void printRow(int rowNum) {

		Row r = sheet.getRow(rowNum);
		Iterator<Cell> cells = r.iterator();
		int cellNum=0;
		while(cells.hasNext()) 
		{
			Cell cell = cells.next();
			System.out.println("Value at["+rowNum+","+cellNum+"] =>" + getCellValue(cell));
			cellNum++;
		}
	}

	public String getCellValue(String columnName,int rowNum) {
		String ret="";
		Row r= sheet.getRow(rowNum);
		Iterator<Cell> cells = r.iterator();

		int colIndex = column.get(columnName);
		int cellNum=0;

		while(cells.hasNext()) {
			Cell cell=cells.next();
			if(colIndex==cellNum) {
				ret=getCellValue(cell);
				System.out.println("Value at["+rowNum+","+cellNum+"] =>"+getCellValue(cell));
				break;
			}

			cellNum++;
		}

		return ret;		
	}

	// Type of cell value in excel sheet...
	public String getCellValue(Cell cell) {

		String ret = "";		
		if(cell.getCellTypeEnum()==CellType.STRING) {
			ret = cell.getStringCellValue();
		}
		else if(cell.getCellTypeEnum()==CellType.NUMERIC) {
			ret=" "+ cell.getNumericCellValue();
		}
		else if(cell.getCellTypeEnum()==CellType.BOOLEAN) {
			ret=" "+ cell.getBooleanCellValue();

		}
		return ret;
	}

	// Count the total no. of row in sheet...
	public int getRowCount() {
		rowCount = sheet.getLastRowNum();
		return rowCount;
	}

	public static void main(String []args ) {

		XLUtil xl = new XLUtil("D:\\Selenium-Testing\\Selenium File Generate\\resources\\TestData.xlsx");

		int rowCount = xl.getRowCount();
		System.out.println("Number of Rows Present in Excel file: "+ rowCount);
		
//		String firstName = xl.getCellValue("FirstName", 1);
//		String lastName = xl.getCellValue("LastName", 1);
//		String pinCode = xl.getCellValue("Postal Code", 1);
		
		String registration_no = xl.getCellValue("Registration No", 1);
		String phone = xl.getCellValue("Phone", 1);
		String fax = xl.getCellValue("Fax", 1);
		String Email = xl.getCellValue("Email", 1);
		String addressStreet1 = xl.getCellValue("AddressStreet1", 1);
		String addressStreet2 = xl.getCellValue("AddressStreet2", 1);
		String city = xl.getCellValue("City", 1);
		String state = xl.getCellValue("State", 1);
		String note = xl.getCellValue("Note", 1);
		String postalCode = xl.getCellValue("PostalCode", 1);
		String country = xl.getCellValue("Country", 1);
		String employee_Name = xl.getCellValue("Employee Name", 1);
		String users_name = xl.getCellValue("UserName", 1);
		String password = xl.getCellValue("Password", 1);
		String confirm_Password = xl.getCellValue("Confirm Password", 1);
		String user_role = xl.getCellValue("UserRole", 1);
		String status = xl.getCellValue("Status", 1);
		
		xl.close();
	}
}

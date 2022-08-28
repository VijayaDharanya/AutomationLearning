package automate.test;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class SF34ReadExcel {
	
	
	public static String[][] dynamic4(String fileName) throws IOException {
		
	//setup the path for workbook
    XSSFWorkbook wb =new XSSFWorkbook("./DataProviderExcel/"+fileName+".xlsx");
    //step2:get into specific sheet
    XSSFSheet sheet = wb.getSheet("Sheet1");
    //to get the row count
    int rowcount = sheet.getLastRowNum();//row count without header
    //to get the column count
    short columnCount  = sheet.getRow(0).getLastCellNum();
    //Declare 2D String Array
    String[][] data=new String[rowcount][columnCount];    
    //loop
     for (int i = 1; i <=rowcount ; i++) {//row
        
         for (int j = 0; j <columnCount ; j++) {//cell 0 1 2
            String stringCellValue = sheet.getRow(i).getCell(j).getStringCellValue();
            data[i-1][j]=stringCellValue;//0,0
         }
}
//to close the workbook
     wb.close();
     return data;
}
}

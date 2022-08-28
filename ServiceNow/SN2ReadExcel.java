package serviceNow;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SN2ReadExcel {
	public static String[][] readexcel(String fileName) throws IOException {

		XSSFWorkbook wb = new XSSFWorkbook("./DataProviderExcel/" + fileName + ".xlsx");
		XSSFSheet sheet = wb.getSheet("Sheet1");
		int rownum = sheet.getLastRowNum();// row count without header
		System.out.println("No of Row " + rownum);
		short colnum = sheet.getRow(0).getLastCellNum();
		System.out.println("No of Column " + colnum);
		String[][] data = new String[rownum][colnum];
		for (int i = 1; i <= rownum; i++) {// row
			for (int j = 0; j < colnum; j++) {// cell 0 1 2
				String stringCellValue = sheet.getRow(i).getCell(j).getStringCellValue();
				data[i - 1][j] = stringCellValue;// 0,0
			}
		}
		wb.close();
		return data;
	}
}

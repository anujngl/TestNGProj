package actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ConnectExcel {

	public static String retrievedata(String testid){
	
	try {
		String filename = "Datasheet.xls";
	    String filePath = System.getProperty("user.dir") + "\\excelIO";
	    File file = new File(filePath + "\\" + filename);
	    FileInputStream inputStream = new FileInputStream(file);
	    Workbook newWorkbook = null;
	    String fileExtensionName = filename.substring(filename.indexOf("."));
	    if (fileExtensionName.equals(".xlsx")) {
	      newWorkbook = new XSSFWorkbook();
	    } else if (fileExtensionName.equals(".xls")) {
	      newWorkbook = new HSSFWorkbook(inputStream);
	    }
	    Sheet newSheet = newWorkbook.getSheet("DataSheet");
	    //double rowCountval = newSheet.getLastRowNum() - newSheet.getFirstRowNum();
	    
	    String testScriptId = "";
		int rowCount = newSheet.getLastRowNum() - newSheet.getFirstRowNum();
	    
		for (int i = 1; i < rowCount + 1; i++)
		{
			Row row = newSheet.getRow(i);
			testScriptId = row.getCell(211).getStringCellValue();
			
			if (testScriptId.equalsIgnoreCase(testid)) {
				
				String val = "";
				//String lastrowValflag = "false";
	
	
					int lastcolval = row.getLastCellNum();
					for (int j = 0; j < lastcolval; j++)
					{
						System.out.print(row.getCell(j) + "||");
		        
						val = val + row.getCell(j) + "@@";
		        	}    
					return val;
			}
			inputStream.close();
			FileOutputStream outputStream = new FileOutputStream(file);
		    newWorkbook.write(outputStream);
		    outputStream.close();
			
		}
	
		}catch(Exception e) {
			e.printStackTrace();
		}
	return null;
	
	}
}


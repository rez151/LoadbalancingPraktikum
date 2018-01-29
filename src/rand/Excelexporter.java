package rand;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class Excelexporter {
	
	public void createWorkbook(String wbName) {
		Workbook wb = new HSSFWorkbook();
		Sheet s1 = wb.createSheet();

		
		
		
	    FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(wbName+".xls");
		    wb.write(fileOut);
		    fileOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		

}

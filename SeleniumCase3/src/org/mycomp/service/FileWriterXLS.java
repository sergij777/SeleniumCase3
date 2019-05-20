package org.mycomp.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.mycomp.model.Good;

public class FileWriterXLS {
	String fileName = "goodsList";
	String fileNameFull = fileName + ".xls";		
	String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + fileNameFull;	   
	//create new file in the memory
	HSSFWorkbook workbook = new HSSFWorkbook();	    
    
	//create new sheet
    public void createSheet (String nameSheet, List<Good> goods) {    
    	HSSFSheet sheet = workbook.createSheet(nameSheet);   
    	int rowNum = 0;    
    	Row row = sheet.createRow(rowNum);
    	row.createCell(0).setCellValue("good name");
    	row.createCell(1).setCellValue("good price");    
    	for (Good good : goods) {
    		createSheetHeader(sheet, ++rowNum, good);
    	}
    }
    
    // filling line (rowNum) the particular sheet
    private static void createSheetHeader(HSSFSheet sheet, int rowNum, Good good) {
        Row row = sheet.createRow(rowNum); 
        row.createCell(0).setCellValue(good.getGoodName());  
        row.createCell(1).setCellValue(good.getGoodPrice());       
    }    
    
    // write file from the memory to particular file 
    public void writeDataToFile() {
    	try (FileOutputStream out = new FileOutputStream(new File(filePath))) {
    		workbook.write(out);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	System.out.println("Excel file was created.");
    }
}

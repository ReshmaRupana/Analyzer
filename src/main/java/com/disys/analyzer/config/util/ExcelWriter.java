package com.disys.analyzer.config.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 * @author Sajid Qureshi
 * @since Oct 23, 2020
 */

public class ExcelWriter {

	public static String writeExcelFile(List<Object> analyserList, String[] columnNames, String realPath,
			String fileName, String fileExt, String sheetName) throws IOException, InvalidFormatException {
		String returnVal = "FAIL";

		SXSSFWorkbook workbook = new SXSSFWorkbook(1000);
		SXSSFSheet sheet = workbook.createSheet(sheetName);
		sheet.trackAllColumnsForAutoSizing();

		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		// headerFont.setFontHeightInPoints((short) 14);
		// headerFont.setColor(IndexedColors.RED.getIndex());

		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		// Create a Row
		Row headerRow = sheet.createRow(0);

		for (int i = 0; i < columnNames.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columnNames[i]);
			cell.setCellStyle(headerCellStyle);
		}

		// Create Other rows and cells with contacts data
		int rowNum = 1;
		for (Object obj : analyserList) {
			Row row = sheet.createRow(rowNum++);
			Object[] striValues = (Object[]) obj;
			for (int j = 0; j < striValues.length; j++) {
				if (striValues[j] != null) {
					if (FacesUtils.isNumeric(striValues[j].toString())) {
						row.createCell(j).setCellValue(Double.parseDouble(striValues[j].toString()));
					} else {
						row.createCell(j).setCellValue(striValues[j].toString());
					}
					// row.createCell(j).setCellValue(striValues[j].toString());
				} else {
					row.createCell(j).setCellValue("");
				}
			}
		}

		// Resize all columns to fit the content size
		for (int i = 0; i < columnNames.length; i++) {
			sheet.autoSizeColumn(i);
		}

		String completeFilePath = realPath + fileName + fileExt;
		System.out.println("File for downloading is : " + completeFilePath);

		WriteToFile objWriteToFile = new WriteToFile();
		File file = objWriteToFile.createFile(completeFilePath);

		// Write the output to a file
		FileOutputStream fileOut = new FileOutputStream(file);
		workbook.write(fileOut);
		workbook.close();
		fileOut.flush();
		fileOut.close();

		returnVal = completeFilePath;
		return returnVal;
	}

}
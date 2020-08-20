package com.tokyo.supermix.config.export;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;

public class SupplierLayouter {
  public static void buildReport(HSSFSheet worksheet, int startRowIndex, int startColIndex) {
    // Set column widths
    worksheet.setColumnWidth(0, 5000);
    worksheet.setColumnWidth(1, 5000);
    worksheet.setColumnWidth(2, 5000);
    worksheet.setColumnWidth(3, 5000);
    worksheet.setColumnWidth(4, 5000);
    worksheet.setColumnWidth(5, 5000);
    // Build the title and date headers
    buildTitle(worksheet, startRowIndex, startColIndex);
    // Build the column headers
    buildHeaders(worksheet, startRowIndex, startColIndex);
  }

  /**
   * Builds the report title and the date header
   * 
   * @param worksheet
   * @param startRowIndex starting row offset
   * @param startColIndex starting column offset
   */
  public static void buildTitle(HSSFSheet worksheet, int startRowIndex, int startColIndex) {
    // Create font style for the report title
    Font fontTitle = worksheet.getWorkbook().createFont();
    fontTitle.setBoldweight(Font.BOLDWEIGHT_NORMAL);
    fontTitle.setFontHeight((short) 280);

    // Create cell style for the report title
    HSSFCellStyle cellStyleTitle = worksheet.getWorkbook().createCellStyle();
    cellStyleTitle.setAlignment(CellStyle.ALIGN_CENTER_SELECTION);
    cellStyleTitle.setWrapText(true);
    cellStyleTitle.setFont(fontTitle);

    // Create report title
    HSSFRow rowTitle = worksheet.createRow((short) startRowIndex);
    rowTitle.setHeight((short) 500);
    HSSFCell cellTitle = rowTitle.createCell(startColIndex);
    cellTitle.setCellValue("Supplier Details");
    cellTitle.setCellStyle(cellStyleTitle);
    // Create merged region for the report title
    worksheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
  }

  /**
   * Builds the column headers
   * 
   * @param worksheet
   * @param startRowIndex starting row offset
   * @param startColIndex starting column offset
   */
  public static void buildHeaders(HSSFSheet worksheet, int startRowIndex, int startColIndex) {
    // Create font style for the headers
    Font font = worksheet.getWorkbook().createFont();
    font.setBoldweight(Font.BOLDWEIGHT_BOLD);

    // Create cell style for the headers
    HSSFCellStyle headerCellStyle = worksheet.getWorkbook().createCellStyle();
    headerCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
    headerCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
    headerCellStyle.setWrapText(true);
    headerCellStyle.setFont(font);
    headerCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
    headerCellStyle.setBorderTop(CellStyle.BORDER_THIN);
    headerCellStyle.setBorderRight(CellStyle.BORDER_THIN);
    headerCellStyle.setBorderLeft(CellStyle.BORDER_THIN);

    // Create the column headers
    HSSFRow rowHeader = worksheet.createRow((short) startRowIndex + 1);
    rowHeader.setHeight((short) 500);
    // Create the column headers


    HSSFCell cell1 = rowHeader.createCell(startColIndex + 0);
    cell1.setCellValue("Email");
    cell1.setCellStyle(headerCellStyle);

    HSSFCell cell2 = rowHeader.createCell(startColIndex + 1);
    cell2.setCellValue("Name");
    cell2.setCellStyle(headerCellStyle);

    HSSFCell cell3 = rowHeader.createCell(startColIndex + 2);
    cell3.setCellValue("Address");
    cell3.setCellStyle(headerCellStyle);

    HSSFCell cell4 = rowHeader.createCell(startColIndex + 3);
    cell4.setCellValue("Phone No");
    cell4.setCellStyle(headerCellStyle);

    HSSFCell cell5 = rowHeader.createCell(startColIndex + 4);
    cell5.setCellValue("Plant");
    cell5.setCellStyle(headerCellStyle);

    HSSFCell cell6 = rowHeader.createCell(startColIndex + 5);
    cell6.setCellValue("Supplier Category");
    cell6.setCellStyle(headerCellStyle);
  }
}

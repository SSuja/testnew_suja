package com.tokyo.supermix.config.export;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import com.tokyo.supermix.data.entities.MixDesignProportion;

public class MixDesignFillManager {
  /**
   * Fills the report with content
   * 
   * @param worksheet
   * @param startRowIndex starting row offset
   * @param startColIndex starting column offset
   * @param list the data source
   */
  public static void fillReport(HSSFSheet worksheet, int startRowIndex, int startColIndex,
      List<MixDesignProportion> list) {
    // Row offset
    startRowIndex += 1;

    // Create cell style for the body
    HSSFCellStyle bodyCellStyle = worksheet.getWorkbook().createCellStyle();
    bodyCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
    bodyCellStyle.setWrapText(true);
    bodyCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
    bodyCellStyle.setBorderTop(CellStyle.BORDER_THIN);
    bodyCellStyle.setBorderRight(CellStyle.BORDER_THIN);
    bodyCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
    CellStyle cellStyle = worksheet.getWorkbook().createCellStyle();
    CreationHelper createHelper = worksheet.getWorkbook().getCreationHelper();
    short dateFormat = createHelper.createDataFormat().getFormat("yyyy-mm-dd");
    cellStyle.setDataFormat(dateFormat);
    cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
    cellStyle.setBorderTop(CellStyle.BORDER_THIN);
    cellStyle.setBorderRight(CellStyle.BORDER_THIN);
    cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
    // Create body
    for (int i = startRowIndex; i + startRowIndex - 1 < list.size() + 1; i++) {
      // Create a new row
      HSSFRow row = worksheet.createRow((short) i + 1);

      // Retrieve the id value
      HSSFCell cell1 = row.createCell(startColIndex + 0);
      cell1.setCellValue(list.get(i - 1).getMixDesign().getCode());
      cell1.setCellStyle(bodyCellStyle);

      // Retrieve the id value
      HSSFCell cell2 = row.createCell(startColIndex + 1);
      cell2.setCellValue(list.get(i - 1).getMixDesign().getDate());
      cell2.setCellStyle(cellStyle);

      // Retrieve the id value
      HSSFCell cell3 = row.createCell(startColIndex + 2);
      cell3.setCellValue(list.get(i - 1).getMixDesign().getTargetGrade());
      cell3.setCellStyle(bodyCellStyle);

      // Retrieve the id value
      HSSFCell cell4 = row.createCell(startColIndex + 3);
      cell4.setCellValue(list.get(i - 1).getMixDesign().getTargetSlump());
      cell4.setCellStyle(bodyCellStyle);

      // Retrieve the id value
      HSSFCell cell5 = row.createCell(startColIndex + 4);
      cell5.setCellValue(list.get(i - 1).getMixDesign().getWaterBinderRatio());
      cell5.setCellStyle(bodyCellStyle);

      // Retrieve the id value
      HSSFCell cell6 = row.createCell(startColIndex + 5);
      cell6.setCellValue(list.get(i - 1).getMixDesign().getWaterCementRatio());
      cell6.setCellStyle(bodyCellStyle);

      // Retrieve the id value
      HSSFCell cell7 = row.createCell(startColIndex + 6);
      cell7.setCellValue(list.get(i - 1).getMixDesign().getPlant().getCode());
      cell7.setCellStyle(bodyCellStyle);

      // Retrieve the id value
      HSSFCell cell8 = row.createCell(startColIndex + 7);
      cell8.setCellValue(String.valueOf(list.get(i-1).getMixDesign().getStatus().name()));
      cell8.setCellStyle(bodyCellStyle);
      
   // Retrieve the id value
      HSSFCell cell9 = row.createCell(startColIndex + 8);
      cell9.setCellValue(list.get(i - 1).getMixDesign().getMaterialCategory().getName());
      cell9.setCellStyle(bodyCellStyle);

      // Retrieve the id value
      HSSFCell cell10 = row.createCell(startColIndex + 9);
      cell10.setCellValue(list.get(i - 1).getRawMaterial().getName());
      cell10.setCellStyle(bodyCellStyle);

      // Retrieve the id value
      HSSFCell cell11 = row.createCell(startColIndex + 10);
      cell11.setCellValue(list.get(i - 1).getQuantity());
      cell11.setCellStyle(bodyCellStyle);

      // Retrieve the id value
      HSSFCell cell12 = row.createCell(startColIndex + 11);
      cell12.setCellValue(list.get(i - 1).getUnit().getUnit());
      cell12.setCellStyle(bodyCellStyle);
    }
  }
}


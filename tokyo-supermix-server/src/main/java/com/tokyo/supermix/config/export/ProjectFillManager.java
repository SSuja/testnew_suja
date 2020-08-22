package com.tokyo.supermix.config.export;

import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import com.tokyo.supermix.data.entities.Project;

public class ProjectFillManager {
  public static void fillReport(HSSFSheet worksheet, int startRowIndex, int startColIndex,
      List<Project> projects) {
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
    for (int i = startRowIndex; i + startRowIndex - 1 < projects.size() + 1; i++) {
      // Create a new row
      HSSFRow row = worksheet.createRow((short) i + 1);

      // Retrieve the id value
      HSSFCell cell1 = row.createCell(startColIndex + 0);
      cell1.setCellValue(projects.get(i - 1).getCode());
      cell1.setCellStyle(bodyCellStyle);

      // Retrieve the id value
      HSSFCell cell2 = row.createCell(startColIndex + 1);
      cell2.setCellValue(projects.get(i - 1).getName());
      cell2.setCellStyle(cellStyle);

      // Retrieve the id value
      HSSFCell cell3 = row.createCell(startColIndex + 2);
      cell3.setCellValue(projects.get(i - 1).getContactPerson());
      cell3.setCellStyle(bodyCellStyle);

      // Retrieve the id value
      HSSFCell cell4 = row.createCell(startColIndex + 3);
      cell4.setCellValue(projects.get(i - 1).getContactNumber());
      cell4.setCellStyle(bodyCellStyle);

      // Retrieve the id value
      HSSFCell cell5 = row.createCell(startColIndex + 4);
      cell5.setCellValue(projects.get(i - 1).getStartDate());
      cell5.setCellStyle(bodyCellStyle);

      // Retrieve the id value
      HSSFCell cell6 = row.createCell(startColIndex + 5);
      cell6.setCellValue(projects.get(i - 1).getCustomer().getName());
      cell6.setCellStyle(bodyCellStyle);

      // Retrieve the id value
      HSSFCell cell7 = row.createCell(startColIndex + 6);
      cell7.setCellValue(projects.get(i - 1).getPlant().getName());
      cell7.setCellStyle(bodyCellStyle);
    }
  }
}

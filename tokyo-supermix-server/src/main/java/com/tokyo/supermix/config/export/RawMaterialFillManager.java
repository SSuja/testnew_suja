package com.tokyo.supermix.config.export;

import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellStyle;
import com.tokyo.supermix.data.entities.RawMaterial;

public class RawMaterialFillManager {
  public static void fillReport(HSSFSheet worksheet, int startRowIndex, int startColIndex,
      List<RawMaterial> rawMaterials) {
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
    cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
    cellStyle.setBorderTop(CellStyle.BORDER_THIN);
    cellStyle.setBorderRight(CellStyle.BORDER_THIN);
    cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
    // Create body
    for (int i = startRowIndex; i + startRowIndex - 1 < rawMaterials.size() + 1; i++) {
      // Create a new row
      HSSFRow row = worksheet.createRow((short) i + 1);

      // Retrieve the id value
      HSSFCell cell1 = row.createCell(startColIndex + 0);
      cell1.setCellValue(rawMaterials.get(i - 1).getName());
      cell1.setCellStyle(bodyCellStyle);

      // Retrieve the id value
      HSSFCell cell2 = row.createCell(startColIndex + 1);
      cell2.setCellValue(rawMaterials.get(i - 1).getPrefix());
      cell2.setCellStyle(cellStyle);

      // Retrieve the id value
      HSSFCell cell3 = row.createCell(startColIndex + 2);
      cell3.setCellValue(rawMaterials.get(i - 1).getMaterialState().getMaterialState());
      cell3.setCellStyle(bodyCellStyle);

      // Retrieve the id value
      HSSFCell cell4 = row.createCell(startColIndex + 3);
      cell4.setCellValue(rawMaterials.get(i - 1).getMaterialSubCategory().getName());
      cell4.setCellStyle(bodyCellStyle);

      // Retrieve the id value
      HSSFCell cell5 = row.createCell(startColIndex + 4);
      if (rawMaterials.get(i - 1).getPlant() != null) {
        cell5.setCellValue(rawMaterials.get(i - 1).getPlant().getName());
        cell5.setCellStyle(bodyCellStyle);
      } else {
        cell5.setAsActiveCell();
        cell5.setCellStyle(bodyCellStyle);
      }

      // Retrieve the id value
      HSSFCell cell6 = row.createCell(startColIndex + 5);
      cell6.setCellValue(rawMaterials.get(i - 1).getDescription());
      cell6.setCellStyle(bodyCellStyle);
    }

  }
}

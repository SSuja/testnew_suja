package com.tokyo.supermix.config.export;

import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import com.tokyo.supermix.data.entities.PlantEquipment;

public class PlantEquipmentFillManager {
  public static void fillReport(HSSFSheet worksheet, int startRowIndex, int startColIndex,
      List<PlantEquipment> list) {
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

      // Retrieve the serial number value
      HSSFCell cell1 = row.createCell(startColIndex + 0);
      cell1.setCellValue(list.get(i - 1).getSerialNo());
      cell1.setCellStyle(bodyCellStyle);

      // Retrieve the brand name value
      HSSFCell cell2 = row.createCell(startColIndex + 1);
      cell2.setCellValue(list.get(i - 1).getBrandName());
      cell2.setCellStyle(cellStyle);

      // Retrieve the calibration exists value
      HSSFCell cell3 = row.createCell(startColIndex + 2);
      cell3.setCellValue(list.get(i - 1).isCalibrationExists());
      cell3.setCellStyle(bodyCellStyle);

      // Retrieve the description value
      HSSFCell cell4 = row.createCell(startColIndex + 3);
      cell4.setCellValue(list.get(i - 1).getDescription());
      cell4.setCellStyle(bodyCellStyle);

      // Retrieve the model name value
      HSSFCell cell5 = row.createCell(startColIndex + 4);
      cell5.setCellValue(list.get(i - 1).getModelName());
      cell5.setCellStyle(bodyCellStyle);

      // Retrieve the equipment name value
      HSSFCell cell6 = row.createCell(startColIndex + 5);
      cell6.setCellValue(list.get(i - 1).getEquipment().getName());
      cell6.setCellStyle(bodyCellStyle);

      // Retrieve the plant name value
      HSSFCell cell7 = row.createCell(startColIndex + 6);
      cell7.setCellValue(list.get(i - 1).getPlant().getName());
      cell7.setCellStyle(bodyCellStyle);
    }
  }
}

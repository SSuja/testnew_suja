package com.tokyo.supermix.server.controller;

import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.config.export.EnrollWriter;
import com.tokyo.supermix.config.export.MixDesignFillManager;
import com.tokyo.supermix.config.export.MixDesignLayouter;
import com.tokyo.supermix.data.repositories.MixDesignProportionRepository;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.server.services.FileStorageService;
import com.tokyo.supermix.util.FileStorageConstants;

@RestController
@CrossOrigin(origins = "*")
public class MixDesignFileStorageController {
  @Autowired
  private MixDesignProportionRepository mixDesignProportionRepository;
  @Autowired
  private FileStorageService fileStorageService;

  @GetMapping(value = EndpointURI.EXPORT_MIXDESIGN)
  public ResponseEntity<Object> downloadXLSProfile(HttpServletResponse response)
      throws ClassNotFoundException {
    HSSFWorkbook workbook = new HSSFWorkbook();
    HSSFSheet worksheet = workbook.createSheet(FileStorageConstants.WORK_SHEET);
    int startRowIndex = 0;
    int startColIndex = 0;
    MixDesignLayouter.buildReport(worksheet, startRowIndex, startColIndex);
    MixDesignFillManager.fillReport(worksheet, startRowIndex, startColIndex,
        mixDesignProportionRepository.getMixDesign());
    String fileName = FileStorageConstants.FILE_NAME;
    response.setHeader("Content-Disposition", "inline; filename=" + fileName);
    response.setContentType("application/vnd.ms-excel");
    EnrollWriter.write(response, worksheet);
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, FileStorageConstants.EXPORT_SUCCESS),
        HttpStatus.OK);
  }

  @PostMapping(value = EndpointURI.UPLOAD_MIXDESIGN)
  public ResponseEntity<Object> uploadMultipartFile(@RequestParam("file") MultipartFile file) {
    fileStorageService.uploadCsv(file);
    fileStorageService.importMixDesgin(file);
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, FileStorageConstants.UPLOAD_SUCCESS),
        HttpStatus.OK);
  }
}

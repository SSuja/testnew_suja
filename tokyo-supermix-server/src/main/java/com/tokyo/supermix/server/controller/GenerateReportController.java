package com.tokyo.supermix.server.controller;

import java.io.FileNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.server.services.GenerateReportService;
import net.sf.jasperreports.engine.JRException;

@RestController
@CrossOrigin
public class GenerateReportController {
  @Autowired
  private GenerateReportService generateReportService;
  private static final Logger logger = Logger.getLogger(GenerateReportController.class);

  @GetMapping(value = "summary-report/{code}")
  public ResponseEntity<Object> generateSummaryReport(@PathVariable String code) {
    try {
      generateReportService.generatePdfSummaryDetailReport(code);
    } catch (FileNotFoundException e) {
      logger.debug("FileNotFoundException");
      e.printStackTrace();
    } catch (JRException e) {
      logger.debug("JRException");
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new ResponseEntity<>("sucessfully mailed", HttpStatus.OK);
  }

  @GetMapping(value = "delivery-report/{code}")
  public ResponseEntity<Object> generateDeliveryReport(@PathVariable String code) {
    try {
      generateReportService.generatePdfDeliveryDetailReport(code);
    } catch (FileNotFoundException e) {
      logger.debug("FileNotFoundException");
      e.printStackTrace();
    } catch (JRException e) {
      logger.debug("JRException");
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new ResponseEntity<>("sucessfully mailed", HttpStatus.OK);
  }
}

package com.tokyo.supermix.server.controller;

import java.io.File;
import java.io.FileNotFoundException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.server.services.EmailService;
import com.tokyo.supermix.server.services.GenerateReportService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.MailConstants;

import net.sf.jasperreports.engine.JRException;

@RestController
@CrossOrigin
public class EmailNotificationController {
  @Autowired
  private GenerateReportService generateReportService;
  
  @GetMapping("/report/{incomingSampleCode}")
  public String generateSummaryReport(@PathVariable String incomingSampleCode)
      throws FileNotFoundException, JRException, MessagingException {
    return generateReportService.generatePdfSummaryDetailReport(incomingSampleCode);
  }
  }


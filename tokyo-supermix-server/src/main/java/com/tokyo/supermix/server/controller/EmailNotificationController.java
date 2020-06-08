package com.tokyo.supermix.server.controller;

import java.io.FileNotFoundException;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.server.services.EmailService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.MailConstants;

@RestController
@CrossOrigin
public class EmailNotificationController {
  @Autowired 
  private EmailService emailService;
  @Autowired
  private MailConstants mailConstants;
  @PostMapping(value = EndpointURI.MAIL_REPORT)
  public @ResponseBody ResponseEntity<Object> sendEmailAttachment(@RequestParam("file") MultipartFile file) {
      try {
          emailService.sendEmailWithAttachment(mailConstants.getMailReportUser(), Constants.SUBJECT_REPORT, Constants.BODY_FOR_REPORT,file);
      } catch (MessagingException | FileNotFoundException mailException) {
        return new ResponseEntity<>(
            new BasicResponse<>(RestApiResponseStatus.OK, Constants.REPORT_SEND_ERROR),
            HttpStatus.INTERNAL_SERVER_ERROR);
      }
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.REPORT_SUCESS),
          HttpStatus.OK);
  }

}


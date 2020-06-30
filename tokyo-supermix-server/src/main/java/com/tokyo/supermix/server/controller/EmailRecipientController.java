package com.tokyo.supermix.server.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.EmailRecipientDto;
import com.tokyo.supermix.data.dto.EmailRecipientRequestDto;
import com.tokyo.supermix.data.enums.RecipientType;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.EmailRecipientService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin
@RestController
public class EmailRecipientController {
  @Autowired
  private Mapper mapper;
  @Autowired
  private EmailRecipientService emailRecipientService;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  private static final Logger logger = Logger.getLogger(EmailRecipientController.class);


  @GetMapping(value = EndpointURI.EMAIL_RECIPIENTS)
  public ResponseEntity<Object> getAllEmailRecipient(@PathVariable Long emailGroupId,
      @PathVariable RecipientType recipientType) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.EMAIL_RECIPIENTS,
        mapper.map(emailRecipientService.getEmailRecipient(emailGroupId, recipientType),
            EmailRecipientRequestDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }


  @PostMapping(value = EndpointURI.EMAIL_RECIPIENT)
  public ResponseEntity<Object> createEmailRecipient(
      @RequestBody EmailRecipientDto emailRecipientDto) {
    if (emailRecipientService.isDuplicateDataExists(emailRecipientDto)) {
      logger.debug("email is already exists: createEmailRecipient(), isEmailRecipientExist: {}");
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.EMAIL_RECIPIENT,
              validationFailureStatusCodes.getEmailRecipientAlreadyExist()),
          HttpStatus.BAD_REQUEST);
    }
    emailRecipientService.createEmailRecipient(emailRecipientDto);
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_EMAIL_RECIPIENT_SUCCESS),
        HttpStatus.OK);
  }

  @DeleteMapping(value = EndpointURI.EMAIL_RECIPIENT_BY_ID)
  public ResponseEntity<Object> deleteEmailRecipient(@PathVariable Long id) {
    if (emailRecipientService.isEmailRecipientExist(id)) {
      logger.debug("delete email recipient by id");
      emailRecipientService.deleteEmailRecipient(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.EMAIL_RECIPIENT_DELETED),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMAIL_RECIPIENT_ID,
        validationFailureStatusCodes.getEmailRecipientnotExist()), HttpStatus.BAD_REQUEST);
  }
}

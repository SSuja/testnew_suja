package com.tokyo.supermix.server.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.EmailRecipientDto;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.EmailRecipientService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin
@RestController
public class EmailRecipientController {
	@Autowired
	private EmailRecipientService emailRecipientService;
	@Autowired
	private ValidationFailureStatusCodes validationFailureStatusCodes;
	private static final Logger logger = Logger.getLogger(EmailRecipientController.class);

	@PostMapping(value = EndpointURI.EMAIL_RECIPIENT)
	public ResponseEntity<Object> createEmailRecipient(@RequestBody EmailRecipientDto emailRecipientDto) {
		if (emailRecipientService.isDuplicateDataExists(emailRecipientDto)) {
			  logger.debug("email is already exists: createEmailRecipient(), isEmailRecipientExist: {}");
			return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMAIL_RECIPIENT,
					validationFailureStatusCodes.getEmailRecipientAlreadyExist()), HttpStatus.BAD_REQUEST);
		}
		emailRecipientService.createEmailRecipient(emailRecipientDto);
		return new ResponseEntity<>(
				new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_EMAIL_RECIPIENT_SUCCESS), HttpStatus.OK);
	}
}

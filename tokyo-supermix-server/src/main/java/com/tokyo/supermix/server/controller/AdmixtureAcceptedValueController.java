package com.tokyo.supermix.server.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.AdmixtureAcceptedValueResponseDto;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.server.services.AdmixtureAcceptedValueService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class AdmixtureAcceptedValueController {
	 @Autowired
	  private AdmixtureAcceptedValueService admixtureAcceptedValueService;
	  @Autowired
	  ValidationFailureStatusCodes validationFailureStatusCodes;
	  @Autowired
	  private Mapper mapper;
	  private static final Logger logger = Logger.getLogger(AcceptedValueController.class);
	  @GetMapping(value = EndpointURI.ADMIXTURE_ACCEPTED_VALUES)
	  public ResponseEntity<Object> getAllAdmixtureAcceptedValues() {
	    List<AdmixtureAcceptedValueResponseDto> admixtureAcceptedValueResponseDtoList =
	        mapper.map(admixtureAcceptedValueService.getAllAdmixtureAcceptedValues(), AdmixtureAcceptedValueResponseDto.class);
	    return new ResponseEntity<>(
	        new ContentResponse<>(Constants.ADMIXTURE_ACCEPTED_VALUES, admixtureAcceptedValueResponseDtoList, RestApiResponseStatus.OK), null,
	        HttpStatus.OK);
	  }
}

package com.tokyo.supermix.server.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.DesignationDto;
import com.tokyo.supermix.data.entities.Designation;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.validation.ValidationFailure;
import com.tokyo.supermix.server.services.DesignationService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
public class DesignationController {

	@Autowired
	DesignationService designationService;

	@Autowired
	ValidationFailureStatusCodes validationFailureStatusCodes;

	@Autowired
	private Mapper mapper;

	private static final Logger logger = Logger.getLogger(DesignationController.class);
// post API for designation
	@PostMapping(value = EndpointURI.DESIGNATION)
	public ResponseEntity<Object> createDesignation(@RequestBody DesignationDto designationDto) {
		if (designationService.isDesignationAlreadyExist(designationDto.getName())) {
			logger.debug("Designation already exists: createDesignation(), dsesignationName: {}");
			return new ResponseEntity<>(new ContentResponse<>(" ",
					new ValidationFailure(Constants.DESIGNATION_NAME,
							validationFailureStatusCodes.getDesignationAlreadyExist()),
					RestApiResponseStatus.VALIDATION_FAILURE), HttpStatus.BAD_REQUEST);
		}

		Designation designation = mapper.map(designationDto, Designation.class);
		designationService.createDesignation(designation);
		return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_DESIGNATION_SUCCESS),
				HttpStatus.OK);

	}
	
	}


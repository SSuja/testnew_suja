package com.tokyo.supermix.server.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.DesignationDto;
import com.tokyo.supermix.data.entities.Designation;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.server.services.DesignationService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationConstance;
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

	// get designation by id
	@GetMapping(value = EndpointURI.GET_DESIGNATION_BY_ID)
	public ResponseEntity<Object> getDesignationById(@PathVariable Long id) {
		if (designationService.isDesignationExist(id)) {
			logger.debug("Get Designation by id ");
			Designation designation = designationService.getDesignationById(id);
			return new ResponseEntity<>(new ContentResponse<>(Constants.DESIGNATION,
					mapper.map(designation, DesignationDto.class), RestApiResponseStatus.OK), HttpStatus.OK);
		} else {
			logger.debug("Designation doesn't exist ");
		}
		return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.VALIDATION_FAILURE,
				ValidationConstance.DESIGNATION_NOT_EXIST), HttpStatus.BAD_REQUEST);
	}

}

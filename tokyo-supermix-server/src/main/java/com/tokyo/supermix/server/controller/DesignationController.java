package com.tokyo.supermix.server.controller;

import org.springframework.web.bind.annotation.RestController;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.validation.ValidationFailure;
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

	// delete api for designation
	@DeleteMapping(value = EndpointURI.DELETE_DESIGNATION_BY_ID)
	public ResponseEntity<Object> deleteDesignation(@PathVariable(Constants.DESIGNATION_PATH_VARIABLE) Long id) {
		if (designationService.isDesignationIdAlreadyExist(id)) {
			designationService.deleteDesignation(id);
			return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK, Constants.DESIGNATION_DELETED),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(
				new BasicResponse<>(
						new ValidationFailure(Constants.DESIGNATION_NAME,
								validationFailureStatusCodes.getDesignationNotExist()),
						RestApiResponseStatus.VALIDATION_FAILURE, ValidationConstance.DESIGNATION_NOT_EXIST),
				HttpStatus.BAD_REQUEST);

	}

}

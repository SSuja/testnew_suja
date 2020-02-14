package com.tokyo.supermix.server.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.DesignationDto;
import com.tokyo.supermix.data.entities.Designation;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ListContentResponse;
import com.tokyo.supermix.server.services.DesignationService;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;
import com.tokyo.supermix.util.Constants;

@RestController
public class DesignationController {

	@Autowired
	DesignationService designationService;

	@Autowired
	ValidationFailureStatusCodes validationFailureStatusCodes;

	@Autowired
	private Mapper mapper;

	private static final Logger logger = Logger.getLogger(DesignationController.class);

	// get all designations
	@GetMapping(value = EndpointURI.GET_ALL_DESIGNATIONS)
	public ResponseEntity<Object> getAllDesignations() {
		List<Designation> designationList = designationService.getAllDesignations();
		if (designationList.isEmpty()) {
			logger.debug("Designation is Empty");
			return new ResponseEntity<>(
					new BasicResponse<>(RestApiResponseStatus.VALIDATION_FAILURE, Constants.NO_DATA_FOUND),
					HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(new ListContentResponse<>(mapper.map(designationList, DesignationDto.class)),
				HttpStatus.OK);
	}

}

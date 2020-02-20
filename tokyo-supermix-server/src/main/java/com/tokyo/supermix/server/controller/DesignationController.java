package com.tokyo.supermix.server.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.DesignationDto;
import com.tokyo.supermix.data.entities.Designation;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
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

	// get all designations
	@GetMapping(value = EndpointURI.DESIGNATIONS)
	public ResponseEntity<Object> getAllDesignations() {
		List<Designation> designationList = designationService.getAllDesignations();
		List<DesignationDto> designationDtoList = mapper.map(designationList, DesignationDto.class);
		return new ResponseEntity<>(
				new ContentResponse<>(Constants.DESIGNATIONS, designationDtoList, RestApiResponseStatus.OK), null,
				HttpStatus.OK);
	}

	// get designation by id
	@GetMapping(value = EndpointURI.GET_DESIGNATION_BY_ID)
	public ResponseEntity<Object> getDesignationById(@PathVariable Long id) {
		if (designationService.isDesignationExist(id)) {
			logger.debug("Get Designation by id ");
			Designation designation = designationService.getDesignationById(id);
			return new ResponseEntity<>(new ContentResponse<>(Constants.DESIGNATION,
					mapper.map(designation, DesignationDto.class), RestApiResponseStatus.OK), HttpStatus.OK);
		}
		return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.VALIDATION_FAILURE,
				ValidationConstance.DESIGNATION_NOT_EXIST), HttpStatus.BAD_REQUEST);

	}

	// delete api for designation
	@DeleteMapping(value = EndpointURI.DELETE_DESIGNATION_BY_ID)
	public ResponseEntity<Object> deleteDesignation(@PathVariable Long id) {
		if (designationService.isDesignationExist(id)) {
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

	// post API for designation
	@PostMapping(value = EndpointURI.DESIGNATION)
	public ResponseEntity<Object> createDesignation(@RequestBody DesignationDto designationDto) {
		if (designationService.isDesignationExist(designationDto.getName())) {
			logger.debug("Designation already exists: createDesignation(), designationName: {}");
			return new ResponseEntity<>(new ContentResponse<>(Constants.DESIGNATION,
					new ValidationFailure(Constants.DESIGNATION_NAME,
							validationFailureStatusCodes.getDesignationAlreadyExist()),
					RestApiResponseStatus.VALIDATION_FAILURE), HttpStatus.BAD_REQUEST);
		}

		Designation designation = mapper.map(designationDto, Designation.class);
		designationService.saveDesignation(designation);
		return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_DESIGNATION_SUCCESS),
				HttpStatus.OK);

	}

	// update API for designations
	@PutMapping(value = EndpointURI.DESIGNATION)
	public ResponseEntity<Object> updateDesignation(@Valid @RequestBody DesignationDto designationDto) {

		if (designationService.isDesignationExist(designationDto.getId())) {
			if (designationService.isUpdatedDesignationNameExist(designationDto.getId(), designationDto.getName())) {
				return new ResponseEntity<>(new ValidationFailureResponse(Constants.DESIGNATION_NAME,
						validationFailureStatusCodes.getDesignationAlreadyExist()), HttpStatus.BAD_REQUEST);
			}
			Designation designation = mapper.map(designationDto, Designation.class);
			designationService.saveDesignation(designation);
			return new ResponseEntity<>(
					new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_DESIGNATION_SUCCESS), HttpStatus.OK);
		}
		return new ResponseEntity<>(new ValidationFailureResponse(Constants.DESIGNATION_NAME,
				validationFailureStatusCodes.getDesignationNotExist()), HttpStatus.BAD_REQUEST);
	}
}

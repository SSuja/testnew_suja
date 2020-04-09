package com.tokyo.supermix.server.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.TestDto;
import com.tokyo.supermix.data.entities.Test;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.TestService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin(origins = "*")
public class TestController {

	@Autowired
	TestService testService;

	@Autowired
	ValidationFailureStatusCodes validationFailureStatusCodes;

	@Autowired
	Mapper mapper;

	private static final Logger logger = Logger.getLogger(UnitController.class);

	// create test api
	@PostMapping(value = EndpointURI.TEST)
	public ResponseEntity<Object> createTest(@Valid @RequestBody TestDto testDto) {
		if (testService.isTestExist(testDto.getName())) {
			logger.debug("Test already exists: createTest(), test: {}");
			return new ResponseEntity<>(
					new ValidationFailureResponse(Constants.TEST, validationFailureStatusCodes.getTestAlreadyExist()),
					HttpStatus.BAD_REQUEST);
		}
		testService.saveTest(mapper.map(testDto, Test.class));
		return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_TEST_SUCCESS),
				HttpStatus.OK);
	}

	// get all tests api
	@GetMapping(value = EndpointURI.TESTS)
	public ResponseEntity<Object> getAllTests() {
		List<TestDto> testDtoList = mapper.map(testService.getAllTests(), TestDto.class);
		return new ResponseEntity<>(new ContentResponse<>(Constants.TEST, testDtoList, RestApiResponseStatus.OK), null,
				HttpStatus.OK);
	}

	// delete test api
	@DeleteMapping(value = EndpointURI.GET_TEST_BY_ID)
	public ResponseEntity<Object> deleteTest(@PathVariable Long id) {
		if (testService.isTestExist(id)) {
			testService.deleteTest(id);
			return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK, Constants.DELETE_TEST_SCCESS),
					HttpStatus.OK);
		}
		logger.debug("Invalid Id");
		return new ResponseEntity<>(
				new ValidationFailureResponse(Constants.TEST, validationFailureStatusCodes.getTestNotExist()),
				HttpStatus.BAD_REQUEST);
	}

	// get test by id api
	@GetMapping(value = EndpointURI.GET_TEST_BY_ID)
	public ResponseEntity<Object> getTestById(@PathVariable Long id) {
		if (testService.isTestExist(id)) {
			logger.debug("Id found");
			return new ResponseEntity<>(new ContentResponse<>(Constants.TEST,
					mapper.map(testService.getTestById(id), TestDto.class), RestApiResponseStatus.OK), HttpStatus.OK);
		}
		logger.debug("Invalid id");
		return new ResponseEntity<>(
				new ValidationFailureResponse(Constants.TEST, validationFailureStatusCodes.getTestNotExist()),
				HttpStatus.BAD_REQUEST);
	}

	// update test api
	@PutMapping(value = EndpointURI.TEST)
	public ResponseEntity<Object> updateTest(@Valid @RequestBody TestDto testDto) {
		if (testService.isTestExist(testDto.getName())) {
			if (testService.isUpdatedTestExist(testDto.getId(), testDto.getName())) {
				return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST,
						validationFailureStatusCodes.getTestAlreadyExist()), HttpStatus.BAD_REQUEST);
			}
			testService.saveTest(mapper.map(testDto, Test.class));
			return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_TEST_SUCCESS),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(
				new ValidationFailureResponse(Constants.TEST, validationFailureStatusCodes.getTestNotExist()),
				HttpStatus.BAD_REQUEST);
	}
}

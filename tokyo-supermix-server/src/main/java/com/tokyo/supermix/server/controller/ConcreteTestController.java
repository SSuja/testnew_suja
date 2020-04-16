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
import com.tokyo.supermix.data.dto.ConcreteTestDto;
import com.tokyo.supermix.data.entities.ConcreteTest;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.ConcreteTestService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class ConcreteTestController {

	@Autowired
	private Mapper mapper;
	@Autowired
	private ConcreteTestService concreteTestService;
	@Autowired
	private ValidationFailureStatusCodes validationFailureStatusCodes;
	private static final Logger logger = Logger.getLogger(ConcreteTestController.class);

	// post API for ConcreteTest
	@PostMapping(value = EndpointURI.CONCRETE_TEST)
	public ResponseEntity<Object> createConcreteTest(@Valid @RequestBody ConcreteTestDto concreteTestDto) {
		if (concreteTestService.isNameExists(concreteTestDto.getName())) {

			return new ResponseEntity<>(new ValidationFailureResponse(Constants.CONCRETE_TEST_NAME,
					validationFailureStatusCodes.getConcreteTestAlreadyExists()), HttpStatus.BAD_REQUEST);

		}
		concreteTestService.saveConcreteTest(mapper.map(concreteTestDto, ConcreteTest.class));
		return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_CONCRETE_TEST_SUCCESS),
				HttpStatus.OK);
	}

	@GetMapping(value = EndpointURI.CONCRETE_TESTS)
	public ResponseEntity<Object> getAllConcreteTest() {
		List<ConcreteTest> concreteTestList = concreteTestService.getAllConcreteTests();
		return new ResponseEntity<Object>(new ContentResponse<>(Constants.CONCRETE_TESTS,
				mapper.map(concreteTestList, ConcreteTestDto.class), RestApiResponseStatus.OK), HttpStatus.OK);
	}

	// get ConcreteTest by id
	@GetMapping(value = EndpointURI.CONCRETE_TEST_BY_ID)
	public ResponseEntity<Object> getConcreteTestById(@PathVariable Long id) {
		if (concreteTestService.isConcreteTestExists(id)) {
			logger.debug("Get ConcreteTest by id ");
			return new ResponseEntity<>(new ContentResponse<>(Constants.CONCRETE_TEST,
					mapper.map(concreteTestService.getConcreteTestById(id), ConcreteTestDto.class),
					RestApiResponseStatus.OK), HttpStatus.OK);
		}
		logger.debug("Invalid Id");
		return new ResponseEntity<>(new ValidationFailureResponse(Constants.CONCRETE_TEST,
				validationFailureStatusCodes.getConcreteTestNotExist()), HttpStatus.BAD_REQUEST);
	}

	// get ConcreteTest Delete
	@DeleteMapping(value = EndpointURI.CONCRETE_TEST_BY_ID)
	public ResponseEntity<Object> deleteConcreteTest(@PathVariable Long id) {
		if (concreteTestService.isConcreteTestExists(id)) {
			concreteTestService.deleteConcreteTest(id);
			return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK, Constants.CONCRETE_TEST_DELETED),
					HttpStatus.OK);
		}
		logger.debug("invalid ConcreteTestId");
		return new ResponseEntity<>(new ValidationFailureResponse(Constants.CONCRETE_TEST,
				validationFailureStatusCodes.getConcreteTestNotExist()), HttpStatus.BAD_REQUEST);
	}

	@PutMapping(value = EndpointURI.CONCRETE_TEST)
	public ResponseEntity<Object> updateConcreteTest(@Valid @RequestBody ConcreteTestDto concreteTestDto) {
		if (concreteTestService.isConcreteTestExists(concreteTestDto.getId())) {
			concreteTestService.saveConcreteTest(mapper.map(concreteTestDto, ConcreteTest.class));
			return new ResponseEntity<>(
					new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_CONCRETE_TEST_SUCCESS),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ValidationFailureResponse(Constants.CONCRETE_TEST,
				validationFailureStatusCodes.getConcreteTestNotExist()), HttpStatus.BAD_REQUEST);
	}

}

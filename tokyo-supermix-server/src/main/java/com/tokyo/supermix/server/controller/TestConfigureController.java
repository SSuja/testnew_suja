package com.tokyo.supermix.server.controller;

import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.TestConfigureRequestDto;
import com.tokyo.supermix.data.dto.TestConfigureResponseDto;
import com.tokyo.supermix.data.entities.TestConfigure;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.TestConfigureService;
import com.tokyo.supermix.server.services.TestTypeService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin
public class TestConfigureController {
	@Autowired
	private TestConfigureService testConfigureService;
	@Autowired
	private TestTypeService testTypeService;
	@Autowired
	private ValidationFailureStatusCodes validationFailureStatusCodes;

	@Autowired
	private Mapper mapper;

	private static final Logger logger = Logger.getLogger(TestConfigureController.class);

	@PostMapping(value = EndpointURI.TEST_CONFIGURE)
//	@PreAuthorize("hasAuthority('add_test_configure')")
	public ResponseEntity<Object> createTestConfigure(
			@Valid @RequestBody TestConfigureRequestDto testConfigureRequestDto) {
		if (!testTypeService.getTestTypeById(testConfigureRequestDto.getTestTypeId()).getMaterialSubCategory()
				.getMaterialCategory().getName().equalsIgnoreCase("Admixture")) {
			if (!testConfigureService.isexistByTestTypeIdAndTestId(testConfigureRequestDto.getTestTypeId(),
					testConfigureRequestDto.getTestId())) {
				testConfigureService.saveTestConfigure(mapper.map(testConfigureRequestDto, TestConfigure.class));
				return new ResponseEntity<>(
						new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_TEST_CONFIGURE_SUCCESS),
						HttpStatus.OK);
			}

			return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_CONFIGURE,
					validationFailureStatusCodes.getTestConfigureAlreadyExist()), HttpStatus.BAD_REQUEST);
		} else {
			testConfigureService.saveTestConfigure(mapper.map(testConfigureRequestDto, TestConfigure.class));
			return new ResponseEntity<>(
					new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_TEST_CONFIGURE_SUCCESS), HttpStatus.OK);
		}
	}

	@GetMapping(value = EndpointURI.TEST_CONFIGURES)
	@PreAuthorize("hasAuthority('get_test_configure')")
	public ResponseEntity<Object> getAllTestConfigures() {
		return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_CONFIGURE,
				mapper.map(testConfigureService.getAllTestConfigures(), TestConfigureResponseDto.class),
				RestApiResponseStatus.OK), null, HttpStatus.OK);
	}

	@GetMapping(value = EndpointURI.GET_TEST_CONFIGURE_BY_ID)
	public ResponseEntity<Object> getTestById(@PathVariable Long id) {
		if (testConfigureService.isTestConfigureExist(id)) {
			return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_CONFIGURE,
					mapper.map(testConfigureService.getTestConfigureById(id), TestConfigureResponseDto.class),
					RestApiResponseStatus.OK), HttpStatus.OK);
		}
		logger.debug("No Test record exist for given id");
		return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_CONFIGURE_ID,
				validationFailureStatusCodes.getTestConfigureNotExist()), HttpStatus.BAD_REQUEST);
	}

	@PutMapping(value = EndpointURI.TEST_CONFIGURE)
	@PreAuthorize("hasAuthority('edit_test_configure')")
	public ResponseEntity<Object> updateTestConfigure(
			@Valid @RequestBody TestConfigureRequestDto testConfigureRequestDto) {
		if (testConfigureService.isTestConfigureExist(testConfigureRequestDto.getId())) {
			if (testConfigureService.isDuplicateEntryExist(testConfigureRequestDto.getTestId(),
					testConfigureRequestDto.getTestTypeId())) {
				logger.debug("Test already exists: createTst(), testName: {}");
				return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_CONFIGURE,
						validationFailureStatusCodes.getTestConfigureAlreadyExist()), HttpStatus.BAD_REQUEST);
			}
			testConfigureService.saveTestConfigure(mapper.map(testConfigureRequestDto, TestConfigure.class));
			return new ResponseEntity<>(
					new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_TEST_CONFIGURE_SUCCESS),
					HttpStatus.OK);
		}
		logger.debug("No Test Configure record exist for given id");
		return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_CONFIGURE_ID,
				validationFailureStatusCodes.getTestConfigureNotExist()), HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping(EndpointURI.DELETE_TEST_CONFIGURE)
	@PreAuthorize("hasAuthority('delete_test_configure')")
	public ResponseEntity<Object> deleteTestConfigure(@PathVariable Long id) {
		if (testConfigureService.isTestConfigureExist(id)) {
			testConfigureService.deleteTestConfigure(id);
			return new ResponseEntity<>(
					new BasicResponse<>(RestApiResponseStatus.OK, Constants.DELETE_TEST_CONFIGURE_SCCESS),
					HttpStatus.OK);
		}
		logger.debug("No Test record exist for given id");
		return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_CONFIGURE_ID,
				validationFailureStatusCodes.getTestConfigureNotExist()), HttpStatus.BAD_REQUEST);
	}

	@GetMapping(value = EndpointURI.GET_TEST_CONFIGURE_BY_TEST_TYPE_ID)
	public ResponseEntity<Object> getTestConfigureByTestTypeId(@PathVariable Long testTypeId) {
		if (testTypeService.isTestTypeIdExist(testTypeId)) {
			return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_TYPE,
					mapper.map(testConfigureService.getTestConfigureByTestType(
							testTypeService.getTestTypeById(testTypeId)), TestConfigureResponseDto.class),
					RestApiResponseStatus.OK), HttpStatus.OK);
		} else {
			logger.debug("No Test record exist for given Test type id");
			return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_TYPE_ID,
					validationFailureStatusCodes.getTestTypeNotExist()), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = EndpointURI.GET_TEST_CONFIGURE_BY_CORE_TEST)
	public ResponseEntity<Object> getTestByCoreTest(@PathVariable boolean coreTest) {
		return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_CONFIGURE,
				mapper.map(testConfigureService.findByCoreTest(coreTest), TestConfigureResponseDto.class),
				RestApiResponseStatus.OK), HttpStatus.OK);
	}

	@PutMapping(value = EndpointURI.TEST_CONFIGURE_BY_ID_AND_CORE_TEST)
	public ResponseEntity<Object> updateCoreTestTestConfigure(
			@Valid @RequestBody TestConfigureRequestDto testConfigureRequestDto) {
		if (testConfigureService.isTestConfigureExist(testConfigureRequestDto.getId())) {
			testConfigureService.updateCoreTestForTestConfigure(testConfigureRequestDto.getId(),
					testConfigureRequestDto.isCoreTest());
			return new ResponseEntity<>(
					new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_TEST_CONFIGURE_SUCCESS),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_CONFIGURE_ID,
				validationFailureStatusCodes.getTestConfigureNotExist()), HttpStatus.BAD_REQUEST);
	}

	@GetMapping(value = EndpointURI.TEST_CONFIGURE_SEARCH)
	public ResponseEntity<Object> getTestConfigureSearch(
			@QuerydslPredicate(root = TestConfigure.class) Predicate predicate, @RequestParam(name = "page") int page,
			@RequestParam(name = "size") int size) {
		return new ResponseEntity<>(
				new ContentResponse<>(Constants.TEST_CONFIGURE,
						testConfigureService.searchTestConfigure(predicate, page, size), RestApiResponseStatus.OK),
				null, HttpStatus.OK);
	}

	@GetMapping(value = EndpointURI.GET_TEST_DETAILS_BY_CONFIGURE_ID)
	public ResponseEntity<Object> getTestDetailsById(@PathVariable Long id) {
		if (testConfigureService.isTestConfigureExist(id)) {
			return new ResponseEntity<>(
					new ContentResponse<>(Constants.TEST_CONFIGURE,
							testConfigureService.getTestDetailsByConfigureId(id), RestApiResponseStatus.OK),
					HttpStatus.OK);
		}
		logger.debug("No Test record exist for given id");
		return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_CONFIGURE_ID,
				validationFailureStatusCodes.getTestConfigureNotExist()), HttpStatus.BAD_REQUEST);
	}
}

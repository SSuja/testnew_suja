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
import com.tokyo.supermix.data.dto.TestRequestDto;
import com.tokyo.supermix.data.dto.TestResponseDto;
import com.tokyo.supermix.data.entities.Test;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.TestService;
import com.tokyo.supermix.server.services.TestTypeService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin
public class TestController {
  @Autowired
  private TestService testService;
  @Autowired
  private TestTypeService testTypeService;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;

  @Autowired
  private Mapper mapper;

  private static final Logger logger = Logger.getLogger(TestController.class);

  @PostMapping(value = EndpointURI.TEST)
  public ResponseEntity<Object> createTest(@Valid @RequestBody TestRequestDto testRequestDto) {
    if (testService.isDuplicateEntryExist(testRequestDto.getName(),
        testRequestDto.getTestTypeId())) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST,
          validationFailureStatusCodes.getTestAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    testService.saveTest(mapper.map(testRequestDto, Test.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_TEST_SUCCESS), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.TESTS)
  public ResponseEntity<Object> getAllTests() {
    List<TestResponseDto> testResponseDtoList =
        mapper.map(testService.getAllTests(), TestResponseDto.class);
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.TEST, testResponseDtoList, RestApiResponseStatus.OK), null,
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.GET_TEST_BY_ID)
  public ResponseEntity<Object> getTestById(@PathVariable Long id) {
    if (testService.isTestExist(id)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.TEST,
          mapper.map(testService.getTestById(id), TestResponseDto.class), RestApiResponseStatus.OK),
          HttpStatus.OK);
    }
    logger.debug("No Test record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_ID,
        validationFailureStatusCodes.getTestNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.TEST)
  public ResponseEntity<Object> updateTest(@Valid @RequestBody TestRequestDto testRequestDto) {
    if (testService.isTestExist(testRequestDto.getId())) {
      if (testService.isDuplicateEntryExist(testRequestDto.getName(),
          testRequestDto.getTestTypeId())) {
        logger.debug("Test already exists: createTst(), testName: {}");
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST,
            validationFailureStatusCodes.getTestAlreadyExist()), HttpStatus.BAD_REQUEST);
      }
      testService.saveTest(mapper.map(testRequestDto, Test.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_TEST_SUCCESS),
          HttpStatus.OK);
    }
    logger.debug("No Test record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_ID,
        validationFailureStatusCodes.getTestNotExist()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(EndpointURI.DELETE_TEST)
  public ResponseEntity<Object> deleteTest(@PathVariable Long id) {
    if (testService.isTestExist(id)) {
      testService.deleteTest(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.DELETE_TEST_SCCESS),
          HttpStatus.OK);
    }
    logger.debug("No Test record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_ID,
        validationFailureStatusCodes.getTestNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.GET_TEST_BY_TEST_TYPE_ID)
  public ResponseEntity<Object> getTestByTestTypeId(@PathVariable Long testTypeId) {
    if (testTypeService.isTestTypeIdExist(testTypeId)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_TYPE,
          mapper.map(testService.getTestByTestType(testTypeService.getTestTypeById(testTypeId)),
              TestResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    } else {
      logger.debug("No Test record exist for given Test type id");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_TYPE_ID,
          validationFailureStatusCodes.getTestTypeNotExist()), HttpStatus.BAD_REQUEST);
    }
  }
}

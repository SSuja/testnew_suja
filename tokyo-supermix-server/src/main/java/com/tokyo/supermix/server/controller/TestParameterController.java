package com.tokyo.supermix.server.controller;

import java.util.List;
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
import com.tokyo.supermix.data.dto.TestParameterRequestDto;
import com.tokyo.supermix.data.dto.TestParameterResponseDto;
import com.tokyo.supermix.data.entities.TestParameter;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.TestParameterService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class TestParameterController {
  @Autowired
  private TestParameterService testParameterService;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  private static final Logger logger = Logger.getLogger(TestParameterController.class);

  @PostMapping(value = EndpointURI.TEST_PARAMETER)
  public ResponseEntity<Object> createTestParameter(
      @RequestBody List<TestParameterRequestDto> testParameterRequestDtoList) {
    for (TestParameterRequestDto testParameterRequestDto : testParameterRequestDtoList) {
      if ((testParameterService.isDuplicateRowExists(testParameterRequestDto.getParameterId(),
          testParameterRequestDto.getTestId(), testParameterRequestDto.getUnitId()))) {
        logger.debug("row is already exists: createTestParameter(), isUpdatedRowExists: {}");
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.TEST_PARAMETER,
                validationFailureStatusCodes.getTestParameterAlreadyExist()),
            HttpStatus.BAD_REQUEST);
      }
    }
    testParameterService
        .saveTestParameter(mapper.map(testParameterRequestDtoList, TestParameter.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_TEST_PARAMETER_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.TEST_PARAMETERS)
  public ResponseEntity<Object> getTestParameters() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_PARAMETERS,
        mapper.map(testParameterService.getAllTestParameters(), TestParameterResponseDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.TEST_PARAMETER_BY_ID)
  public ResponseEntity<Object> getTestParameterById(@PathVariable Long id) {
    if (testParameterService.isTestParameterExist(id)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_PARAMETER,
          mapper.map(testParameterService.getTestParameterById(id), TestParameterResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_PARAMETER_ID,
        validationFailureStatusCodes.getTestParameterNotExist()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(EndpointURI.TEST_PARAMETER_BY_ID)
  public ResponseEntity<Object> deleteTestParameter(@PathVariable Long id) {
    if (testParameterService.isTestParameterExist(id)) {
      testParameterService.deleteTestParameter(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.TEST_PARAMETER_DELETED),
          HttpStatus.OK);
    }
    logger.debug("No Test Parameter record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_PARAMETER_ID,
        validationFailureStatusCodes.getTestParameterNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.GET_TEST_PARAMETER_BY_TEST_ID)
  public ResponseEntity<Object> getAllParameterByTestId(@PathVariable Long testId) {
    if (testParameterService.isTestIdExist(testId)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_PARAMETERS,
          mapper.map(testParameterService.getTestParameterByTestId(testId),
              TestParameterResponseDto.class),
          RestApiResponseStatus.OK), null, HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_ID,
        validationFailureStatusCodes.getTestNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.TEST_PARAMETER)
  public ResponseEntity<Object> updateTestParameter(
      @RequestBody TestParameterRequestDto testParameterRequestDto) {
    if (testParameterService.isTestParameterExist(testParameterRequestDto.getId())) {
      if ((testParameterService.isDuplicateRowExists(testParameterRequestDto.getParameterId(),
          testParameterRequestDto.getTestId(), testParameterRequestDto.getUnitId()))) {
        logger.debug("row is already exists: createTestParameter(), isUpdatedRowExists: {}");
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.TEST_PARAMETER,
                validationFailureStatusCodes.getTestParameterAlreadyExist()),
            HttpStatus.BAD_REQUEST);
      }
      testParameterService
          .updateTestParameter(mapper.map(testParameterRequestDto, TestParameter.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_TEST_PARAMETER_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_PARAMETER_ID,
        validationFailureStatusCodes.getTestParameterNotExist()), HttpStatus.BAD_REQUEST);
  }
}

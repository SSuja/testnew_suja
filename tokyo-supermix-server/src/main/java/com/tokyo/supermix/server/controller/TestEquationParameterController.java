package com.tokyo.supermix.server.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.TestEquationParameterResponseDto;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.TestEquationParameterService;
import com.tokyo.supermix.server.services.TestEquationService;
import com.tokyo.supermix.server.services.TestParameterService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin
public class TestEquationParameterController {
  @Autowired
  private TestEquationParameterService testEquationParameterService;
  @Autowired
  private TestParameterService testParameterService;
  @Autowired
  private TestEquationService testEquationService;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  private static final Logger logger = Logger.getLogger(TestEquationParameterController.class);

  // Get all Test Equation Parameter API
  @GetMapping(value = EndpointURI.TEST_EQUATION_PARAMETERS)
  public ResponseEntity<Object> getAllTestEquationParameters() {
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.TEST_EQUATION_PARAMETERS,
            mapper.map(testEquationParameterService.getAllTestEquationParameters(),
                TestEquationParameterResponseDto.class),
            RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }

  // Get Test Equation Parameter by Test Parameter API
  @GetMapping(value = EndpointURI.TEST_EQUATION_PARAMETER_BY_TEST_PARAMETER_ID)
  public ResponseEntity<Object> getByTestParameterId(@PathVariable Long testParameterId) {
    if (testParameterService.isTestParameterExist(testParameterId)) {
      logger.debug("Test Parameter id is found");
      return new ResponseEntity<>(
          new ContentResponse<>(Constants.TEST_EQUATION_PARAMETERS,
              mapper.map(testEquationParameterService.getByTestParameter(testParameterId),
                  TestEquationParameterResponseDto.class),
              RestApiResponseStatus.OK),
          HttpStatus.OK);
    }
    logger.debug("Invalid id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_PARAMETER,
        validationFailureStatusCodes.getTestParameterNotExist()), HttpStatus.BAD_REQUEST);
  }

  // Get Test Equation Parameter by Test Equation API
  @GetMapping(value = EndpointURI.TEST_EQUATION_PARAMETER_BY_TEST_EQUATION_ID)
  public ResponseEntity<Object> getByTestEquationId(@PathVariable Long testEquationId) {
    if (testEquationService.isTestEquationExists(testEquationId)) {
      logger.debug("Test Equation id is found");
      return new ResponseEntity<>(
          new ContentResponse<>(Constants.TEST_EQUATION_PARAMETERS,
              mapper.map(testEquationParameterService.getByTestEquation(testEquationId),
                  TestEquationParameterResponseDto.class),
              RestApiResponseStatus.OK),
          HttpStatus.OK);
    }
    logger.debug("Invalid id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_EQUATION_ID,
        validationFailureStatusCodes.getTestEquationNotExist()), HttpStatus.BAD_REQUEST);
  }
}

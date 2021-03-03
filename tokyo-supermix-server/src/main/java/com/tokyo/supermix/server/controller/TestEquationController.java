package com.tokyo.supermix.server.controller;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.TestEquationDto;
import com.tokyo.supermix.data.dto.TestEquationResponseDto;
import com.tokyo.supermix.data.entities.TestEquation;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.EquationService;
import com.tokyo.supermix.server.services.TestConfigureService;
import com.tokyo.supermix.server.services.TestEquationService;
import com.tokyo.supermix.server.services.TestParameterService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin(origins = "*")
public class TestEquationController {
  @Autowired
  private TestEquationService testEquationService;
  @Autowired
  private TestConfigureService testConfigureService;
  @Autowired
  private EquationService equationService;
  @Autowired
  private TestParameterService testParameterService;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  private static final Logger logger = LoggerFactory.getLogger(TestEquationController.class);

  // Create Test Equation API
  @PostMapping(value = EndpointURI.TEST_EQUATION)
  public ResponseEntity<Object> createTestEquation(
      @Valid @RequestBody TestEquationDto testEquationDto) {
    if (testEquationService.isTestParaneterExists(testEquationDto.getTestParameterId())) {
      logger.info("Test Equation already exists: createTestEquation(), testEquation: {}");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_EQUATION,
          validationFailureStatusCodes.getTestEquationAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    testEquationService.saveTestEquationAndTestEquationParameter(testEquationDto);
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_TEST_EQUATION_SUCCESS),
        HttpStatus.OK);
  }

  // Get all Test Equation API
  @GetMapping(value = EndpointURI.TEST_EQUATIONS)
  public ResponseEntity<Object> getAllTestEquations() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_EQUATIONS,
        mapper.map(testEquationService.getAllTestEquations(), TestEquationResponseDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  // Get Test Equation by Id API
  @GetMapping(value = EndpointURI.TEST_EQUATION_BY_ID)
  public ResponseEntity<Object> getByTestEquationId(@PathVariable Long id) {
    if (testEquationService.isTestEquationExists(id)) {
      logger.info("Test Equation id is found");
      return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_EQUATION,
          mapper.map(testEquationService.getByTestEquationId(id), TestEquationResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.info("Invalid id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_EQUATION_ID,
        validationFailureStatusCodes.getTestEquationNotExist()), HttpStatus.BAD_REQUEST);
  }

  // Delete Test Equation API
  @DeleteMapping(value = EndpointURI.TEST_EQUATION_BY_ID)
  public ResponseEntity<Object> deleteTestEquation(@PathVariable Long id) {
    TestEquation testEquation = testEquationService.getByTestEquationId(id);
    if (testEquationService.isTestEquationExists(id)) {
      if (testConfigureService.isAlreadyDepended(testEquation.getTestConfigure().getId())) {
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.TEST_CONFIGURE,
                validationFailureStatusCodes.getTestConfigureAlreadyDepended()),
            HttpStatus.BAD_REQUEST);
      }
      testEquationService.deleteTestEquation(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.DELETE_TEST_EQUATION_SCCESS),
          HttpStatus.OK);
    }
    logger.info("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_EQUATION_ID,
        validationFailureStatusCodes.getTestEquationNotExist()), HttpStatus.BAD_REQUEST);
  }

  // Get Test Equation by Test Configure API
  @GetMapping(value = EndpointURI.TEST_EQUATION_BY_TEST_CONFIGURE_ID)
  public ResponseEntity<Object> getByTestConfigureId(@PathVariable Long testConfigureId) {
    if (testConfigureService.isTestConfigureExist(testConfigureId)) {
      logger.info("Test Configure id is found");
      return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_EQUATION,
          mapper.map(testEquationService.getByTestConfigure(testConfigureId),
              TestEquationResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.info("Invalid id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_CONFIGURE_ID,
        validationFailureStatusCodes.getTestConfigureNotExist()), HttpStatus.BAD_REQUEST);
  }

  // Get Test Equation by Equation API
  @GetMapping(value = EndpointURI.TEST_EQUATION_BY_EQUATION_ID)
  public ResponseEntity<Object> getByEquationId(@PathVariable Long equationId) {
    if (equationService.isEquationExist(equationId)) {
      logger.info("Equation id is found");
      return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_EQUATION,
          mapper.map(testEquationService.getByEquation(equationId), TestEquationResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.info("Invalid id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.EQUATION_ID,
        validationFailureStatusCodes.getEquationNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.TEST_EQUATION_BY_TEST_PARAMETER_ID)
  public ResponseEntity<Object> getByTestParameter(@PathVariable Long testParameterId) {
    if (testParameterService.isTestParameterExist(testParameterId)) {
      logger.info("Test Parameter id is found");
      return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_EQUATION,
          mapper.map(testEquationService.getByTestParameter(testParameterId),
              TestEquationResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.info("Invalid id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_PARAMETER_ID,
        validationFailureStatusCodes.getTestParameterNotExist()), HttpStatus.BAD_REQUEST);
  }
}

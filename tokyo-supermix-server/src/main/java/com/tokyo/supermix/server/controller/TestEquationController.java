package com.tokyo.supermix.server.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.TestEquationDto;
import com.tokyo.supermix.data.dto.TestEquationResponseDto;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.EquationService;
import com.tokyo.supermix.server.services.TestConfigureService;
import com.tokyo.supermix.server.services.TestEquationService;
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
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  private static final Logger logger = Logger.getLogger(TestEquationController.class);

  // Create Test Equation API
  @PostMapping(value = EndpointURI.TEST_EQUATION)
  public ResponseEntity<Object> createTestEquation(
      @Valid @RequestBody TestEquationDto testEquationDto) {
    if (testEquationService.isTestParaneterExists(testEquationDto.getTestParameterId())) {
      logger.debug("Test Equation already exists: createTestEquation(), testEquation: {}");
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
      logger.debug("Test Equation id is found");
      return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_EQUATION,
          mapper.map(testEquationService.getByTestEquationId(id), TestEquationResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("Invalid id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_EQUATION_ID,
        validationFailureStatusCodes.getTestEquationNotExist()), HttpStatus.BAD_REQUEST);
  }

  // Delete Test Equation API
  @DeleteMapping(value = EndpointURI.TEST_EQUATION_BY_ID)
  public ResponseEntity<Object> deleteTestEquation(@PathVariable Long id) {
    if (testEquationService.isTestEquationExists(id)) {
      testEquationService.deleteTestEquation(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.DELETE_TEST_EQUATION_SCCESS),
          HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_EQUATION_ID,
        validationFailureStatusCodes.getTestEquationNotExist()), HttpStatus.BAD_REQUEST);
  }

  // Get Test Equation by Test Configure API
  @GetMapping(value = EndpointURI.TEST_EQUATION_BY_TEST_CONFIGURE_ID)
  public ResponseEntity<Object> getByTestConfigureId(@PathVariable Long testConfigureId) {
    if (testConfigureService.isTestConfigureExist(testConfigureId)) {
      logger.debug("Test Configure id is found");
      return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_EQUATION,
          mapper.map(testEquationService.getByTestConfigure(testConfigureId),
              TestEquationResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("Invalid id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_CONFIGURE_ID,
        validationFailureStatusCodes.getTestConfigureNotExist()), HttpStatus.BAD_REQUEST);
  }

  // Get Test Equation by Equation API
  @GetMapping(value = EndpointURI.TEST_EQUATION_BY_EQUATION_ID)
  public ResponseEntity<Object> getByEquationId(@PathVariable Long equationId) {
    if (equationService.isEquationExist(equationId)) {
      logger.debug("Equation id is found");
      return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_EQUATION,
          mapper.map(testEquationService.getByEquation(equationId), TestEquationResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("Invalid id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.EQUATION_ID,
        validationFailureStatusCodes.getEquationNotExist()), HttpStatus.BAD_REQUEST);
  }
}

package com.tokyo.supermix.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.ParameterEquationElementResponseDto;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.ParameterEquationElementService;
import com.tokyo.supermix.server.services.ParameterEquationService;
import com.tokyo.supermix.server.services.TestParameterService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin
public class ParameterEquationElementController {
  @Autowired
  private ParameterEquationElementService parameterEquationElementService;
  @Autowired
  private TestParameterService testParameterService;
  @Autowired
  private ParameterEquationService parameterEquationService;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  private static final Logger logger =
      LoggerFactory.getLogger(ParameterEquationElementController.class);

  @GetMapping(value = EndpointURI.PARAMETER_EQUATION_ELEMENTS)
  public ResponseEntity<Object> getParameterEquationElements() {
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.PARAMETER_EQUATION_ELEMENTS,
            mapper.map(parameterEquationElementService.getAllParameterEquationElements(),
                ParameterEquationElementResponseDto.class),
            RestApiResponseStatus.OK),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.PARAMETER_EQUATION_ELEMENT_BY_TEST_PARAMETER_ID)
  public ResponseEntity<Object> getParameterEquationElementByTestParameter(
      @PathVariable Long testParameterId) {
    if (testParameterService.isTestParameterExist(testParameterId)) {
      return new ResponseEntity<>(
          new ContentResponse<>(Constants.PARAMETER_EQUATION_ELEMENT,
              mapper.map(parameterEquationElementService.getByTestParameterId(testParameterId),
                  ParameterEquationElementResponseDto.class),
              RestApiResponseStatus.OK),
          HttpStatus.OK);
    } else {
      logger.debug("No Parameter Equation Element record exist for given test parameter id");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_PARAMETER_ID,
          validationFailureStatusCodes.getTestParameterNotExist()), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(value = EndpointURI.PARAMETER_EQUATION_ELEMENT_BY_PARAMETER_EQUATION_ID)
  public ResponseEntity<Object> getParameterEquationElementByParameterEquation(
      @PathVariable Long parameterEquationId) {
    if (parameterEquationService.isParameterEquationExist(parameterEquationId)) {
      return new ResponseEntity<>(
          new ContentResponse<>(Constants.PARAMETER_EQUATION_ELEMENT,
              mapper.map(
                  parameterEquationElementService.getByParameterEquationId(parameterEquationId),
                  ParameterEquationElementResponseDto.class),
              RestApiResponseStatus.OK),
          HttpStatus.OK);
    } else {
      logger.debug("No Parameter Equation Element record exist for given parameter equation id");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.PARAMETER_EQUATION_ID,
          validationFailureStatusCodes.getParameterEquationNotExit()), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(value = EndpointURI.PARAMETER_EQUATION_ELEMENT_BY_TEST_PARAMETER)
  public ResponseEntity<Object> getByTestParameter(@PathVariable Long testParameterId) {
    if (testParameterService.isTestParameterExist(testParameterId)) {
      return new ResponseEntity<>(
          new ContentResponse<>(Constants.PARAMETER_EQUATION_ELEMENT,
              mapper.map(parameterEquationElementService.getByTestParameter(testParameterId),
                  ParameterEquationElementResponseDto.class),
              RestApiResponseStatus.OK),
          HttpStatus.OK);
    } else {
      logger.debug("No Parameter Equation Element record exist for given Test Parameter id");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_PARAMETER_ID,
          validationFailureStatusCodes.getTestParameterNotExist()), HttpStatus.BAD_REQUEST);
    }
  }
}

package com.tokyo.supermix.server.controller;

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
import com.tokyo.supermix.data.dto.ParameterEquationEleDto;
import com.tokyo.supermix.data.dto.ParameterEquationRequestDto;
import com.tokyo.supermix.data.dto.ParameterEquationResponseDto;
import com.tokyo.supermix.data.entities.ParameterEquation;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.ParameterEquationService;
import com.tokyo.supermix.server.services.TestConfigureService;
import com.tokyo.supermix.server.services.TestParameterService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin
public class ParameterEquationController {
  @Autowired
  private ParameterEquationService parameterEquationService;
  @Autowired
  private TestParameterService testParameterService;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  @Autowired
  private TestConfigureService testConfigureService;
  private static final Logger logger = Logger.getLogger(ParameterEquationController.class);

  @PostMapping(value = EndpointURI.PARAMETER_EQUATION)
  public ResponseEntity<Object> createParameterEquation(
      @RequestBody ParameterEquationEleDto parameterEquationEleDto) {
    if (parameterEquationService.isEquationIdAndTestParameterId(
        parameterEquationEleDto.getEquationId(), parameterEquationEleDto.getTestParameterId(),
        testParameterService.getTestParameterById(parameterEquationEleDto.getTestParameterId())
            .getTestConfigure().getId())) {
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.PARAMETER_EQUATION,
              validationFailureStatusCodes.getParameterEquationAlreadyExit()),
          HttpStatus.BAD_REQUEST);
    }
    parameterEquationService.saveParameterEquationAndElement(parameterEquationEleDto);
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_PARAMETER_EQUATION_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.PARAMETER_EQUATIONS)
  public ResponseEntity<Object> getParameterEquations() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.PARAMETER_EQUATIONS, mapper
        .map(parameterEquationService.getParameterEquations(), ParameterEquationResponseDto.class),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.PARAMETER_EQUATION_BY_ID)
  public ResponseEntity<Object> getParameterEquationById(@PathVariable Long id) {
    if (parameterEquationService.isParameterEquationExist(id)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.PARAMETER_EQUATIONS,
          mapper.map(parameterEquationService.getParameterEquationById(id),
              ParameterEquationResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("No Parameter Equation record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PARAMETER_EQUATION_ID,
        validationFailureStatusCodes.getParameterEquationNotExit()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(EndpointURI.PARAMETER_EQUATION_BY_ID)
  public ResponseEntity<Object> deleteParameterEquation(@PathVariable Long id) {
    if (parameterEquationService.isParameterEquationExist(id)) {
      parameterEquationService.deleteParameterEquation(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.DELETE_PARAMETER_EQUATION_SCCESS),
          HttpStatus.OK);
    }
    logger.debug("No Parameter Equation record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PARAMETER_EQUATION_ID,
        validationFailureStatusCodes.getParameterEquationNotExit()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.PARAMETER_EQUATION_BY_TEST_PARAMETER_ID)
  public ResponseEntity<Object> getParameterEquationByTestParameter(
      @PathVariable Long testParameterId) {
    if (testParameterService.isTestParameterExist(testParameterId)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.PARAMETER_EQUATION,
          mapper.map(parameterEquationService.getParameterEquationByTestParameter(testParameterId),
              ParameterEquationResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    } else {
      logger.debug("No Parameter Equation record exist for given test parameter id");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_PARAMETER_ID,
          validationFailureStatusCodes.getTestParameterNotExist()), HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping(value = EndpointURI.PARAMETER_EQUATION)
  public ResponseEntity<Object> updateParameterEquation(
      @RequestBody ParameterEquationRequestDto parameterEquationRequestDto) {
    if (parameterEquationService.isParameterEquationExist(parameterEquationRequestDto.getId())) {
//      if (parameterEquationService.isEquationIdAndTestParameterId(
//          parameterEquationRequestDto.getEquation().getId(),
//          parameterEquationRequestDto.getTestParameter().getId())) {
//        return new ResponseEntity<>(
//            new ValidationFailureResponse(Constants.PARAMETER_EQUATION,
//                validationFailureStatusCodes.getParameterEquationAlreadyExit()),
//            HttpStatus.BAD_REQUEST);
//      }
      parameterEquationService.updateParameterEquation(
          mapper.map(parameterEquationRequestDto, ParameterEquation.class));
      return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
          Constants.UPDATE_PARAMETER_EQUATION_SUCCESS), HttpStatus.OK);
    }
    logger.debug("No Parameter Equation record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PARAMETER_EQUATION_ID,
        validationFailureStatusCodes.getParameterEquationNotExit()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.PARAMETER_EQUATION_BY_TEST_CONFIGURE_ID)
  public ResponseEntity<Object> getParameterEquationByTestConfigureId(
      @PathVariable Long testConfigureId) {
    if (testConfigureService.isTestConfigureExist(testConfigureId)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.PARAMETER_EQUATION,
          mapper.map(
              parameterEquationService.getParameterEquationsByTestConfigureId(testConfigureId),
              ParameterEquationResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    } else {
      logger.debug("No Parameter Equation record exist for given testconfigureid");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_CONFIGURE_ID,
          validationFailureStatusCodes.getTestConfigureNotExist()), HttpStatus.BAD_REQUEST);
    }
  }
}

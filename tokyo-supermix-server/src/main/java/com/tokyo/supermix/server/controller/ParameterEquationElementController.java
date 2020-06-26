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
import com.tokyo.supermix.data.dto.ParameterEquationElementRequestDto;
import com.tokyo.supermix.data.dto.ParameterEquationElementResponseDto;
import com.tokyo.supermix.data.entities.ParameterEquationElement;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
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
  private static final Logger logger = Logger.getLogger(ParameterEquationElementController.class);

  @PostMapping(value = EndpointURI.PARAMETER_EQUATION_ELEMENT)
  public ResponseEntity<Object> createParameterEquationElement(
      @RequestBody List<ParameterEquationElementRequestDto> parameterEquationElementRequestDtoList) {
    for (ParameterEquationElementRequestDto parameterEquationElementRequestDto : parameterEquationElementRequestDtoList) {
      if (parameterEquationElementService.isDuplicateEntryExist(
          parameterEquationElementRequestDto.getParameterEquation().getId(),
          parameterEquationElementRequestDto.getTestParameterId())) {
        logger.debug(
            "Row is already exists: createParameterEquationElement(), isDuplicateRowExists: {}");
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.PARAMETER_EQUATION_ELEMENT,
                validationFailureStatusCodes.getParameterEquationElementAlreadyExit()),
            HttpStatus.BAD_REQUEST);
      }
    }
    parameterEquationElementService.saveParameterEquationElement(
        mapper.map(parameterEquationElementRequestDtoList, ParameterEquationElement.class));
    return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
        Constants.ADD_PARAMETER_EQUATION_ELEMENT_SUCCESS), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.PARAMETER_EQUATION_ELEMENTS)
  public ResponseEntity<Object> getParameterEquationElements() {
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.PARAMETER_EQUATION_ELEMENTS,
            mapper.map(parameterEquationElementService.getAllParameterEquationElements(),
                ParameterEquationElementResponseDto.class),
            RestApiResponseStatus.OK),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.PARAMETER_EQUATION_ELEMENT_BY_ID)
  public ResponseEntity<Object> getParameterEquationElementById(@PathVariable Long id) {
    if (parameterEquationElementService.isExistsById(id)) {
      return new ResponseEntity<>(
          new ContentResponse<>(Constants.PARAMETER_EQUATION_ELEMENTS,
              mapper.map(parameterEquationElementService.getByParameterEquationElement(id),
                  ParameterEquationElementResponseDto.class),
              RestApiResponseStatus.OK),
          HttpStatus.OK);
    }
    logger.debug("No Parameter Equation Element record exist for given id");
    return new ResponseEntity<>(
        new ValidationFailureResponse(Constants.PARAMETER_EQUATION_ELEMENT_ID,
            validationFailureStatusCodes.getParameterEquationElementNotExit()),
        HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(EndpointURI.PARAMETER_EQUATION_ELEMENT_BY_ID)
  public ResponseEntity<Object> deleteParameterEquationElement(@PathVariable Long id) {
    if (parameterEquationElementService.isExistsById(id)) {
      parameterEquationElementService.deleteParameterEquationElement(id);
      return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
          Constants.DELETE_PARAMETER_EQUATION_ELEMENT_SCCESS), HttpStatus.OK);
    }
    logger.debug("No Parameter Equation Element record exist for given id");
    return new ResponseEntity<>(
        new ValidationFailureResponse(Constants.PARAMETER_EQUATION_ELEMENT_ID,
            validationFailureStatusCodes.getParameterEquationElementNotExit()),
        HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.PARAMETER_EQUATION_ELEMENT)
  public ResponseEntity<Object> updateParameterEquationElement(
      @RequestBody ParameterEquationElementRequestDto parameterEquationElementRequestDto) {
    if (parameterEquationElementService.isExistsById(parameterEquationElementRequestDto.getId())) {
      if (parameterEquationElementService.isDuplicateEntryExist(
          parameterEquationElementRequestDto.getParameterEquation().getId(),
          parameterEquationElementRequestDto.getTestParameterId())) {
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.PARAMETER_EQUATION_ELEMENT,
                validationFailureStatusCodes.getParameterEquationElementAlreadyExit()),
            HttpStatus.BAD_REQUEST);
      }
      parameterEquationElementService.updateParameterEquationElement(
          mapper.map(parameterEquationElementRequestDto, ParameterEquationElement.class));
      return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
          Constants.UPDATE_PARAMETER_EQUATION_ELEMENT_SUCCESS), HttpStatus.OK);
    }
    logger.debug("No Parameter Equation Element record exist for given id");
    return new ResponseEntity<>(
        new ValidationFailureResponse(Constants.PARAMETER_EQUATION_ELEMENT_ID,
            validationFailureStatusCodes.getParameterEquationElementNotExit()),
        HttpStatus.BAD_REQUEST);
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
}

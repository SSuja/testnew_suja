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
import com.tokyo.supermix.data.dto.EquationParameterRequestDto;
import com.tokyo.supermix.data.dto.EquationParameterResponseDto;
import com.tokyo.supermix.data.entities.EquationParameter;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.EquationParameterService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin
public class EquationParameterController {
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  @Autowired
  private EquationParameterService equationParameterService;
  private static final Logger logger = Logger.getLogger(EquationParameterController.class);

  @PostMapping(value = EndpointURI.EQUATION_PARAMETER)
  public ResponseEntity<Object> createEquationParameter(
      @Valid @RequestBody EquationParameterRequestDto equationParameterRequestDto) {
    if (equationParameterService.isDuplicateRowExists(equationParameterRequestDto.getEquationId(),
        equationParameterRequestDto.getParameterId())) {
      logger.debug("row is already exists: createEquationParameter(), isDuplicateRowExists: {}");
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.EQUATION_PARAMETER,
              validationFailureStatusCodes.getEquationParameterAlreadyExist()),
          HttpStatus.BAD_REQUEST);
    }
    equationParameterService
        .saveEquationParameter(mapper.map(equationParameterRequestDto, EquationParameter.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_EQUATION_PARAMETER_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.EQUATION_PARAMETERS)
  public ResponseEntity<Object> getAllEquationParameters() {
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.EQUATION_PARAMETERS,
            mapper.map(equationParameterService.getAllEquationParameters(),
                EquationParameterResponseDto.class),
            RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.EQUATION_PARAMETER_BY_ID)
  public ResponseEntity<Object> getEquationParameterById(@PathVariable Long id) {
    if (equationParameterService.isEquationParameterExist(id)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.EQUATION_PARAMETER,
          mapper.map(equationParameterService.getEquationParameterById(id),
              EquationParameterResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("No Equation Parameter record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.EQUATION_PARAMETER_ID,
        validationFailureStatusCodes.getEquationParameterNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.GET_PARAMETERS_BY_EQUATION_ID)
  public ResponseEntity<Object> getAllParameterByEquationId(@PathVariable Long equationId) {
    if (equationParameterService.isEquationIdExist(equationId)) {
      System.out.println("good");
      return new ResponseEntity<>(
          new ContentResponse<>(Constants.EQUATION_PARAMETERS,
              mapper.map(equationParameterService.getEquationByEquationId(equationId),
                  EquationParameterResponseDto.class),
              RestApiResponseStatus.OK),
          null, HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.EQUATION_ID,
        validationFailureStatusCodes.getEquationParameterNotExist()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(EndpointURI.EQUATION_PARAMETER_BY_ID)
  public ResponseEntity<Object> deleteEquationParameterById(@PathVariable Long id) {
    if (equationParameterService.isEquationParameterExist(id)) {
      equationParameterService.deleteTestParameter(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.DELETED_EQUATION_PARAMETER),
          HttpStatus.OK);
    }
    logger.debug("No Equation Parameter record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.EQUATION_ID,
        validationFailureStatusCodes.getEquationParameterNotExist()), HttpStatus.BAD_REQUEST);
  }
}

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.EquationRequestDto;
import com.tokyo.supermix.data.dto.EquationResponseDto;
import com.tokyo.supermix.data.entities.Equation;
import com.tokyo.supermix.data.enums.EquationType;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.EquationService;
import com.tokyo.supermix.server.services.TestConfigureService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class EquationController {
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  @Autowired
  private EquationService equationService;
  @Autowired
  private TestConfigureService testConfigureService;

  private static final Logger logger = Logger.getLogger(EquationController.class);

  @PostMapping(value = EndpointURI.EQUATION)
  public ResponseEntity<Object> createEquation(
      @Valid @RequestBody EquationRequestDto equationRequestDto) {
    if (equationService.isEmptyFormula(equationRequestDto.getFormula())) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EQUATION_FORMULA,
          validationFailureStatusCodes.getFormulaIsNull()), HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(new ContentResponse<>(Constants.EQUATION,
        equationService.saveEquation(mapper.map(equationRequestDto, Equation.class)),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.EQUATIONS)
  public ResponseEntity<Object> getAllEquations() {
    logger.debug("get all equations");
    return new ResponseEntity<>(new ContentResponse<>(Constants.EQUATIONS,
        mapper.map(equationService.getAllEquations(), Equation.class), RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.EQUATION_BY_ID)
  public ResponseEntity<Object> getEquationById(@PathVariable Long id) {
    if (equationService.isEquationExist(id)) {
      logger.debug("Get equation By Id");
      return new ResponseEntity<>(new ContentResponse<>(Constants.EQUATION,
          mapper.map(equationService.getEquationById(id), EquationResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.EQUATION_ID,
        validationFailureStatusCodes.getEquationNotExist()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(value = EndpointURI.EQUATION_BY_ID)
  public ResponseEntity<Object> deleteEquation(@PathVariable Long id) {
    if (equationService.isEquationExist(id)) {
      logger.debug("delete equation by id");
      equationService.deleteEquation(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.DELETE_EQUATION_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.EQUATION_ID,
        validationFailureStatusCodes.getEquationNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.EQUATION)
  public ResponseEntity<Object> updateEquation(
      @Valid @RequestBody EquationRequestDto equationRequestDto) {
    if (equationService.isEquationExist(equationRequestDto.getId())) {
      if (testConfigureService.isAlreadyDepended(equationRequestDto.getTestConfigureId())) {
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.TEST_CONFIGURE,
                validationFailureStatusCodes.getTestConfigureAlreadyDepended()),
            HttpStatus.BAD_REQUEST);
      }
      equationService.saveEquation(mapper.map(equationRequestDto, Equation.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_EQUATION_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.EQUATION_ID,
        validationFailureStatusCodes.getEquationNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.EQUATIONS_BY_EQUATION_TYPE)
  public ResponseEntity<Object> getEquationsByEquationType(
      @PathVariable EquationType equationType) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.EQUATION, mapper
        .map(equationService.getEquationsByEquationType(equationType), EquationResponseDto.class),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }
}

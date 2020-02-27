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
import com.tokyo.supermix.data.dto.EquationDto;
import com.tokyo.supermix.data.entities.Equation;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.EquationService;
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
  private static final Logger logger = Logger.getLogger(EquationController.class);

  // Add Equation
  @PostMapping(value = EndpointURI.EQUATION)
  public ResponseEntity<Object> createEquation(@Valid @RequestBody EquationDto equationDto) {
    if (equationService.isFormulaExist(equationDto.getFormula())) {
      logger.debug("formula is already exists: createEquation(), isNameExist: {}");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EQUATION_FORMULA,
          validationFailureStatusCodes.getEquationAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    equationService.saveEquation(mapper.map(equationDto, Equation.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_EQUATION_SUCCESS),
        HttpStatus.OK);
  }

  // Get All Equations
  @GetMapping(value = EndpointURI.EQUATIONS)
  public ResponseEntity<Object> getAllEquations() {
    logger.debug("get all equations");
    List<EquationDto> equuationDtoList =
        mapper.map(equationService.getAllEquations(), EquationDto.class);
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.EQUATIONS, equuationDtoList, RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }

  // Get Equation By Id
  @GetMapping(value = EndpointURI.EQUATION_BY_ID)
  public ResponseEntity<Object> getEquationById(@PathVariable Long id) {
    if (equationService.isEquationExist(id)) {
      logger.debug("Get equation By Id");
      return new ResponseEntity<>(new ContentResponse<>(Constants.EQUATION,
          mapper.map(equationService.getEquationById(id), EquationDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.EQUATION_ID,
        validationFailureStatusCodes.getEquationNotExist()), HttpStatus.BAD_REQUEST);
  }

  // Delete Equation
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

  // Update Equation
  @PutMapping(value = EndpointURI.EQUATION)
  public ResponseEntity<Object> updateEquation(@Valid @RequestBody EquationDto equationDto) {
    if (equationService.isEquationExist(equationDto.getId())) {
      if (equationService.isUpdatedFormulaExist(equationDto.getId(), equationDto.getFormula())) {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.EQUATION_FORMULA,
            validationFailureStatusCodes.getEquationAlreadyExist()), HttpStatus.BAD_REQUEST);
      }
      equationService.saveEquation(mapper.map(equationDto, Equation.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_EQUATION_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.EQUATION_ID,
        validationFailureStatusCodes.getEquationNotExist()), HttpStatus.BAD_REQUEST);
  }
}

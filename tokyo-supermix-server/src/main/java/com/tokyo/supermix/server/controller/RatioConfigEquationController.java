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
import com.tokyo.supermix.data.dto.RatioConfigEquationRequestDto;
import com.tokyo.supermix.data.dto.RatioConfigEquationResponseDto;
import com.tokyo.supermix.data.entities.RatioConfigEquation;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.RatioConfigEquationService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin
public class RatioConfigEquationController {

  @Autowired
  RatioConfigEquationService ratioConfigEquationService;

  @Autowired
  ValidationFailureStatusCodes validationFailureStatusCodes;

  @Autowired
  private Mapper mapper;

  private static final Logger logger = Logger.getLogger(RatioConfigController.class);

  // get all Ratio Configuration
  @GetMapping(value = EndpointURI.RATIO_CONFIG_EQUATIONS)
  public ResponseEntity<Object> getAllRatioConfigEquation() {
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.RATIO_CONFIG_EQUATION,
            mapper.map(ratioConfigEquationService.getAllRatioConfigEquations(),
                RatioConfigEquationResponseDto.class),
            RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }

  // get RatioConfig by id
  @GetMapping(value = EndpointURI.RATIO_CONFIG_EQUATION_BY_ID)
  public ResponseEntity<Object> getRatioConfigEquationById(@PathVariable Long id) {
    if (ratioConfigEquationService.isRatioConfigEquationExistsById(id)) {
      logger.debug("Get Designation by id ");
      return new ResponseEntity<>(new ContentResponse<>(Constants.RATIO_CONFIG_EQUATION,
          mapper.map(ratioConfigEquationService.getRatioConfigEquationById(id),
              RatioConfigEquationResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.RATIO_CONFIG_EQUATION,
        validationFailureStatusCodes.getRatioConfigEquationNotExist()), HttpStatus.BAD_REQUEST);
  }

  // delete api for RatioConfig
  @DeleteMapping(value = EndpointURI.RATIO_CONFIG_EQUATION_BY_ID)
  public ResponseEntity<Object> deleteRatioConfigEquation(@PathVariable Long id) {
    if (ratioConfigEquationService.isRatioConfigEquationExistsById(id)) {
      ratioConfigEquationService.deleteRatioConfigEquation(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.RATIO_CONFIG_DELETED),
          HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.RATIO_CONFIG_EQUATION,
        validationFailureStatusCodes.getRatioConfigEquationNotExist()), HttpStatus.BAD_REQUEST);
  }

  // post API for RatioConfig Equation
  @PostMapping(value = EndpointURI.RATIO_CONFIG_EQUATION)
  public ResponseEntity<Object> createRatioConfigEquation(
      @Valid @RequestBody RatioConfigEquationRequestDto ratioConfigEquationRequestDto) {
    if (ratioConfigEquationService.isRatioExistsByRatioConfig(
        ratioConfigEquationRequestDto.getRatioConfigId(),
        ratioConfigEquationRequestDto.getRatio())) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.RATIO_CONFIG_EQUATION,
          validationFailureStatusCodes.getRatioConfigAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    if (!ratioConfigEquationService
        .getAllRatioConfigEquationsByRatioConfig(ratioConfigEquationRequestDto.getRatioConfigId())
        .isEmpty()) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.RATIO_CONFIG_EQUATION,
          validationFailureStatusCodes.getRatioConfigAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    ratioConfigEquationService.saveRatioConfigEquation(
        mapper.map(ratioConfigEquationRequestDto, RatioConfigEquation.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_RATIO_CONFIG__EQUATION_SUCCESS),
        HttpStatus.OK);
  }

  // update API for RatioConfig
  @PutMapping(value = EndpointURI.RATIO_CONFIG_EQUATION)
  public ResponseEntity<Object> updateRatioConfigEquation(
      @Valid @RequestBody RatioConfigEquationRequestDto ratioConfigEquationRequestDto) {
    if (ratioConfigEquationService
        .isRatioConfigEquationExistsById(ratioConfigEquationRequestDto.getId())) {
      ratioConfigEquationService.saveRatioConfigEquation(
          mapper.map(ratioConfigEquationRequestDto, RatioConfigEquation.class));
      return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
          Constants.UPDATE_RATIO_CONFIG__EQUATION_SUCCESS), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.RATIO_CONFIG_EQUATION,
        validationFailureStatusCodes.getRatioConfigEquationNotExist()), HttpStatus.BAD_REQUEST);
  }

  // get RatioConfig by id
  @GetMapping(value = EndpointURI.RATIO_CONFIG_EQUATION_BY_RATIO_CONFIG)
  public ResponseEntity<Object> getRatioConfigEquationByRatioConfigId(
      @PathVariable Long ratioConfigId) {
    if (ratioConfigEquationService.isRatioConfigEquationExistsByRatioConfigId(ratioConfigId)) {
      logger.debug("Get Ratio config equation by id ");
      return new ResponseEntity<>(new ContentResponse<>(Constants.RATIO_CONFIG_EQUATION,
          mapper.map(
              ratioConfigEquationService.getAllRatioConfigEquationsByRatioConfig(ratioConfigId),
              RatioConfigEquationResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.RATIO_CONFIG_EQUATION,
        validationFailureStatusCodes.getRatioConfigEquationNotExist()), HttpStatus.BAD_REQUEST);
  }
}

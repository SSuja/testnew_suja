package com.tokyo.supermix.server.controller;

import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.MixDesignParameterRequestDto;
import com.tokyo.supermix.data.dto.MixDesignParameterResponseDto;
import com.tokyo.supermix.data.entities.MixDesignParameter;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.EquationService;
import com.tokyo.supermix.server.services.MixDesignParameterService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin(origins = "*")
public class MixDesignParameterController {
  @Autowired
  private Mapper mapper;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private MixDesignParameterService mixDesignParameterService;
  @Autowired
  private EquationService equationService;
  private static final Logger logger = Logger.getLogger(MixDesignParameterController.class);

  @GetMapping(value = EndpointURI.MIX_DESIGN_PARAMETERS)
  public ResponseEntity<Object> getAllMixDesignParameters() {
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.MIX_DESIGN_PARAMETERS,
            mapper.map(mixDesignParameterService.getAllMixDesignParameters(),
                MixDesignParameterResponseDto.class),
            RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }

  @PostMapping(value = EndpointURI.MIX_DESIGN_PARAMETER)
  public ResponseEntity<Object> saveMixDesignParameter(
      @Valid @RequestBody MixDesignParameterRequestDto mixDesignParameterRequestDto) {
    mixDesignParameterService
        .saveMixDesignParameter(mapper.map(mixDesignParameterRequestDto, MixDesignParameter.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_MIX_DESIGN_PARAMETER_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.GET_MIX_DESIGN_PARAMETER_BY_ID)
  public ResponseEntity<Object> getMixDesignParameterById(@PathVariable Long id) {
    if (mixDesignParameterService.isMixDesignParameterExist(id)) {
      logger.debug("Get MixDesignParameter By Id");
      return new ResponseEntity<>(new ContentResponse<>(Constants.MIX_DESIGN_PARAMETER,
          mapper.map(mixDesignParameterService.getMixDesignParameterById(id),
              MixDesignParameterResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MIX_DESIGN_PARAMETER,
        validationFailureStatusCodes.getMixDesignParameterNotExist()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(value = EndpointURI.DELETE_MIX_DESIGN_PARAMETER)
  public ResponseEntity<Object> deleteMixDesignParameter(@PathVariable Long id) {
    if (mixDesignParameterService.isMixDesignParameterExist(id)) {
      logger.debug("delete mix design parameter by id");
      mixDesignParameterService.deleteMixDesignParameter(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.MIX_DESIGN_PARAMETER_DELETED),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MIX_DESIGN_PARAMETER,
        validationFailureStatusCodes.getMixDesignParameterNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.GET_MIX_DESIGN_PARAMETER_BY_EQUATION_ID)
  public ResponseEntity<Object> getMixDesignParameterByEquationId(@PathVariable Long equationId) {
    if (equationService.isEquationExist(equationId)) {
      logger.debug("Get MixDesignParameter By Id");
      return new ResponseEntity<>(new ContentResponse<>(Constants.MIX_DESIGN_PARAMETER,
          mapper.map(mixDesignParameterService.getMixDesignParameterByEquationId(equationId),
              MixDesignParameterResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.EQUATION,
        validationFailureStatusCodes.getEquationNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.MIX_DESIGN_PARAMETER)
  @PreAuthorize("hasAuthority('edit_customer')")
  public ResponseEntity<Object> updateMixDesignParameter(
      @Valid @RequestBody MixDesignParameterRequestDto mixDesignParameterRequestDto) {
    if (mixDesignParameterService.isMixDesignParameterExist(mixDesignParameterRequestDto.getId())) {
      mixDesignParameterService.saveMixDesignParameter(
          mapper.map(mixDesignParameterRequestDto, MixDesignParameter.class));
      return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
          Constants.UPDATE_MIX_DESIGN_PARAMETER_SUCCESS), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MIX_DESIGN_PARAMETER,
        validationFailureStatusCodes.getMixDesignParameterNotExist()), HttpStatus.BAD_REQUEST);
  }
}

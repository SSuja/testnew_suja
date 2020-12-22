package com.tokyo.supermix.server.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.RatioConfigParameterRequestDto;
import com.tokyo.supermix.data.dto.RatioConfigParameterResponseDto;
import com.tokyo.supermix.data.entities.RatioConfigParameter;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.RatioConfigParameterService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin
public class RatioConfigParameterController {

  @Autowired
  RatioConfigParameterService ratioConfigParameterService;

  @Autowired
  ValidationFailureStatusCodes validationFailureStatusCodes;

  @Autowired
  private Mapper mapper;

  private static final Logger logger = Logger.getLogger(RatioConfigParameterController.class);

  @GetMapping(value = EndpointURI.RATIO_CONFIG_PARAMETERS)
  public ResponseEntity<Object> getAllRatioConfigParameters() {
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.RATIO_CONFIG_PARAMETERS,
            mapper.map(ratioConfigParameterService.getAllRatioConfigParameters(),
                RatioConfigParameterResponseDto.class),
            RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.RATIO_CONFIG_PARAMETER_BY_RATIO_CONFIG)
  public ResponseEntity<Object> getRatioConfigParameterByRatioConfig(
      @PathVariable Long ratioConfigId) {
    if (ratioConfigParameterService.isRatioConfigParameterExistByRatioConfig(ratioConfigId)) {
      logger.debug("Get Designation by id ");
      return new ResponseEntity<>(new ContentResponse<>(Constants.RATIO_CONFIG_PARAMETER,
          mapper.map(ratioConfigParameterService.getAllRatioParametersByRatioConfig(ratioConfigId),
              RatioConfigParameterResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.RATIO_CONFIG_PARAMETER,
        validationFailureStatusCodes.getRatioConfigNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PostMapping(value = EndpointURI.RATIO_CONFIG_PARAMETER)
  public ResponseEntity<Object> createRatioConfigParameter(
      @Valid @RequestBody List<RatioConfigParameterRequestDto> ratioConfigParameterRequestDto) {
    if (ratioConfigParameterRequestDto.stream()
        .filter(para -> para.getAbbreviation() == null && para.getUnitId() == null)
        .collect(Collectors.toList()).isEmpty()) {
      ratioConfigParameterService.saveRatioConfigParameters(
          mapper.map(ratioConfigParameterRequestDto, RatioConfigParameter.class));
      return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
          Constants.ADD_RATIO_CONFIG_PARAMETER_SUCCESS), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.RATIO_CONFIG_PARAMETER,
        validationFailureStatusCodes.getRatioConfigNotExist()), HttpStatus.BAD_REQUEST);
  }
}

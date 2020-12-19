package com.tokyo.supermix.server.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.RatioConfigResponseDto;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.RatioConfigService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin
public class RatioConfigController {

  @Autowired
  RatioConfigService ratioConfigService;

  @Autowired
  ValidationFailureStatusCodes validationFailureStatusCodes;

  @Autowired
  private Mapper mapper;

  private static final Logger logger = Logger.getLogger(RatioConfigController.class);

  // get all Ratio Configs
  @GetMapping(value = EndpointURI.RATIO_CONFIGS)
  public ResponseEntity<Object> getAllRatioConfigs() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.RATIO_CONFIGS,
        mapper.map(ratioConfigService.getAllRatioConfigs(), RatioConfigResponseDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  // get RatioConfigService by id
  @GetMapping(value = EndpointURI.RATIO_CONFIG_BY_ID)
  public ResponseEntity<Object> getDesignationById(@PathVariable Long id) {
    if (ratioConfigService.isRatioConfigExist(id)) {
      logger.debug("Get Designation by id ");
      return new ResponseEntity<>(new ContentResponse<>(Constants.RATIO_CONFIG,
          mapper.map(ratioConfigService.getRatioConfigById(id), RatioConfigResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.RATIO_CONFIG,
        validationFailureStatusCodes.getRatioConfigNotExist()), HttpStatus.BAD_REQUEST);
  }
}

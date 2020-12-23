package com.tokyo.supermix.server.controller;

import java.util.List;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.MixDesignRatioConfigRequestDto;
import com.tokyo.supermix.data.dto.MixDesignRatioConfigResponseDto;
import com.tokyo.supermix.data.entities.MixDesignRatioConfig;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.server.services.MixDesignRatioConfigService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin(origins = "*")
public class MixDesignRatioConfigController {

  @Autowired
  private MixDesignRatioConfigService mixDesignRatioConfigService;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  private static final Logger logger = Logger.getLogger(MixDesignRatioConfigController.class);

  @PostMapping(value = EndpointURI.MIX_DESIGN_RATIO_CONFIG)
  public ResponseEntity<Object> createMixDesignRatioConfig(
      @Valid @RequestBody List<MixDesignRatioConfigRequestDto> mixDesignRatioConfigDtoList) {
    mixDesignRatioConfigService.saveMixDesignRatioConfig(
        mapper.map(mixDesignRatioConfigDtoList, MixDesignRatioConfig.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_MIX_DESIGN_RATIO_CONFIG),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.MIX_DESIGN_RATIO_CONFIGS)
  public ResponseEntity<Object> getAllMixDesignRatios() {
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.MIX_DESIGN_RATIO_CONFIG,
            mapper.map(mixDesignRatioConfigService.getAllMixDesignRatioConfigs(),
                MixDesignRatioConfigResponseDto.class),
            RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }

  // @GetMapping(value = EndpointURI.MIX_DESIGN_RATIO_CONFIG_BY_ID)
  // public ResponseEntity<Object> getDesignationById(@PathVariable Long id) {
  // if (designationService.isDesignationExist(id)) {
  // logger.debug("Get Designation by id ");
  // return new ResponseEntity<>(new ContentResponse<>(Constants.MIX_DESIGN_RATIO_CONFIG,
  // mapper.map(designationService.getDesignationById(id), DesignationDto.class),
  // RestApiResponseStatus.OK), HttpStatus.OK);
  // }
  // logger.debug("Invalid Id");
  // return new ResponseEntity<>(new ValidationFailureResponse(Constants.MIX_DESIGN_RATIO_CONFIG,
  // validationFailureStatusCodes.getDesignationNotExist()), HttpStatus.BAD_REQUEST);
  // }

  // @DeleteMapping(value = EndpointURI.DESIGNATION_BY_ID)
  // public ResponseEntity<Object> deleteDesignation(@PathVariable Long id) {
  // if (designationService.isDesignationExist(id)) {
  // designationService.deleteDesignation(id);
  // return new ResponseEntity<>(
  // new BasicResponse<>(RestApiResponseStatus.OK, Constants.DESIGNATION_DELETED),
  // HttpStatus.OK);
  // }
  // logger.debug("Invalid Id");
  // return new ResponseEntity<>(new ValidationFailureResponse(Constants.DESIGNATION,
  // validationFailureStatusCodes.getDesignationNotExist()), HttpStatus.BAD_REQUEST);
  // }
}

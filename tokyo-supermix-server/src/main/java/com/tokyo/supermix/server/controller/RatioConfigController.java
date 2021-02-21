package com.tokyo.supermix.server.controller;

import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.RatioConfigRequestDto;
import com.tokyo.supermix.data.dto.RatioConfigResponseDto;
import com.tokyo.supermix.data.entities.RatioConfig;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
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

  // get all Ratio Configuration
  @GetMapping(value = EndpointURI.RATIO_CONFIGS)
  public ResponseEntity<Object> getAllRatioConfigs() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.RATIO_CONFIGS,
        mapper.map(ratioConfigService.getAllRatioConfigs(), RatioConfigResponseDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  // get RatioConfig by id
  @GetMapping(value = EndpointURI.RATIO_CONFIG_BY_ID)
  public ResponseEntity<Object> getRatioConfigById(@PathVariable Long id) {
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

  // delete api for RatioConfig
  // @DeleteMapping(value = EndpointURI.RATIO_CONFIG_BY_ID)
  // public ResponseEntity<Object> deleteRatioConfig(@PathVariable Long id) {
  // if (ratioConfigService.isRatioConfigExist(id)) {
  // ratioConfigService.deleteRatioConfig(id);
  // return new ResponseEntity<>(
  // new BasicResponse<>(RestApiResponseStatus.OK, Constants.RATIO_CONFIG_DELETED),
  // HttpStatus.OK);
  // }
  // logger.debug("Invalid Id");
  // return new ResponseEntity<>(new ValidationFailureResponse(Constants.RATIO_CONFIG,
  // validationFailureStatusCodes.getRatioConfigNotExist()), HttpStatus.BAD_REQUEST);
  // }

  // post API for RatioConfig
  @PostMapping(value = EndpointURI.RATIO_CONFIG)
  public ResponseEntity<Object> createRatioConfig(
      @Valid @RequestBody RatioConfigRequestDto ratioConfigRequestDto) {
    if (ratioConfigService.isRatioConfigExist(ratioConfigRequestDto.getName())) {
      logger.debug("Name already exists");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.RATIO_CONFIG,
          validationFailureStatusCodes.getRatioConfigAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.RATIO_CONFIG,
            ratioConfigService.saveRatioConfig(
                mapper.map(ratioConfigRequestDto, RatioConfig.class)),
            RestApiResponseStatus.OK),
        HttpStatus.OK);
  }

  // update API for RatioConfig
  @PutMapping(value = EndpointURI.RATIO_CONFIG)
  public ResponseEntity<Object> updateRatioConfig(
      @Valid @RequestBody RatioConfigRequestDto ratioConfigRequestDto) {
    if (ratioConfigService.isRatioConfigExist(ratioConfigRequestDto.getId())) {
      if (ratioConfigService.isUpdatedRatioConfigNameExist(ratioConfigRequestDto.getId(),
          ratioConfigRequestDto.getName())) {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.RATIO_CONFIG,
            validationFailureStatusCodes.getRatioConfigAlreadyExist()), HttpStatus.BAD_REQUEST);
      }
      ratioConfigService.saveRatioConfig(mapper.map(ratioConfigRequestDto, RatioConfig.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_RATIO_CONFIG_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.RATIO_CONFIG,
        validationFailureStatusCodes.getRatioConfigNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.RATIO_CONFIG_DETAILS_CONFIG_ID)
  public ResponseEntity<Object> getAllRatioConfigDetails(@PathVariable Long ratioConfigId) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.RATIO_CONFIGS,
        ratioConfigService.getMixDesignTestConfigDetails(ratioConfigId), RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }

  @DeleteMapping(EndpointURI.RATIO_CONFIG_BY_ID)
  public ResponseEntity<Object> deleteRatioConfigureReset(@PathVariable Long id) {
    if (ratioConfigService.checkRatioConfigDepend(id)) {
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.TEST_CONFIGURE,
              validationFailureStatusCodes.getRatioConfigAlreadyDepended()),
          HttpStatus.BAD_REQUEST);
    }
    ratioConfigService.deleteRatioConfigReset(id);
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.RATIO_CONFIG_DELETED),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.RATIO_CONFIGS_PAGE)
  public ResponseEntity<Object> getRatioConfigsWithPagination(@RequestParam(name = "page") int page,
      @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(page, size, totalpage, 0l);
    pagination.setTotalRecords(ratioConfigService.getAllRatioConfigCount());
    return new ResponseEntity<>(
        new PaginatedContentResponse<>(Constants.RATIO_CONFIGS,
            mapper.map(ratioConfigService.findAllWithPagination(pageable),
                RatioConfigResponseDto.class),
            RestApiResponseStatus.OK, pagination),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.RATIO_CONFIGS_SERACH)
  public ResponseEntity<Object> searchRatioConfig(
      @RequestParam(name = "name", required = false) String name,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(0, 0, totalpage, 0l);
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    return new ResponseEntity<>(
        new PaginatedContentResponse<>(Constants.RATIO_CONFIGS,
            mapper.map(
                ratioConfigService.searchRatioConfig(name, booleanBuilder, pageable, pagination),
                RatioConfigResponseDto.class),
            RestApiResponseStatus.OK, pagination),
        HttpStatus.OK);
  }
}

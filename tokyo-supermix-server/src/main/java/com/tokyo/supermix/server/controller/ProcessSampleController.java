package com.tokyo.supermix.server.controller;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.tokyo.supermix.data.dto.ProcessSampleRequestDto;
import com.tokyo.supermix.data.dto.ProcessSampleResponseDto;
import com.tokyo.supermix.data.entities.ProcessSample;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.security.CurrentUser;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.PlantService;
import com.tokyo.supermix.server.services.ProcessSampleService;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;
import com.tokyo.supermix.util.privilege.PermissionConstants;

@CrossOrigin(origins = "*")
@RestController
public class ProcessSampleController {
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  @Autowired
  private ProcessSampleService processSampleService;
  @Autowired
  private PlantService plantService;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;

  private static final Logger logger = LoggerFactory.getLogger(ProcessSampleController.class);

  @PostMapping(value = EndpointURI.PROCESS_SAMPLE)
  public ResponseEntity<Object> createProcessSample(
      @Valid @RequestBody ProcessSampleRequestDto processSampleRequestDto) {
    processSampleService
        .saveProcessSample(mapper.map(processSampleRequestDto, ProcessSample.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_PROCESS_SAMPLE_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.PROCESS_SAMPLES)
  public ResponseEntity<Object> getProcessSamples() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.PROCESS_SAMPLES,
        mapper.map(processSampleService.getAllProcessSamples(), ProcessSampleResponseDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.PROCESS_SAMPLE_BY_PLANT)
  public ResponseEntity<Object> getAllProcessSamplesByCurrentUserPermission(
      @CurrentUser UserPrincipal currentUser, @PathVariable String plantCode,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(page, size, totalpage, 0l);

    if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
      pagination.setTotalRecords(processSampleService.getCountProcessSample());
      return new ResponseEntity<>(new PaginatedContentResponse<>(Constants.PROCESS_SAMPLES,
          mapper.map(processSampleService.getAllProcessSamplesByCurrentUser(currentUser, pageable),
              ProcessSampleResponseDto.class),
          RestApiResponseStatus.OK, pagination), null, HttpStatus.OK);
    }
    if (currentUserPermissionPlantService
        .getPermissionPlantCodeByCurrentUser(currentUser, PermissionConstants.VIEW_PROCESS_SAMPLE)
        .contains(plantCode)) {
      pagination.setTotalRecords(processSampleService.getCountProcessSampleByPlantCode(plantCode));
      return new ResponseEntity<>(
          new PaginatedContentResponse<>(Constants.PROCESS_SAMPLES,
              mapper.map(processSampleService.getProcessSampleByPlantCode(plantCode, pageable),
                  ProcessSampleResponseDto.class),
              RestApiResponseStatus.OK, pagination),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(value = EndpointURI.PROCESS_SAMPLE_BY_CODE)
  public ResponseEntity<Object> deleteProcessSample(@PathVariable String code) {
    if (processSampleService.isProcessSampleExist(code)) {
      logger.info("delete ProcessSample by code");
      processSampleService.deleteProcessSample(code);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.PROCESS_SAMPLE_DELETED),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PROCESS_SAMPLE_CODE,
        validationFailureStatusCodes.getProcessSampleNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.PROCESS_SAMPLE_BY_CODE)
  public ResponseEntity<Object> getProcessSampleByCode(@PathVariable String code) {
    if (processSampleService.isProcessSampleExist(code)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.PROCESS_SAMPLE, mapper
          .map(processSampleService.getProcessSampleByCode(code), ProcessSampleResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.info("No ProcessSample record exist for given code");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PROCESS_SAMPLE_CODE,
        validationFailureStatusCodes.getProcessSampleNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.PROCESS_SAMPLE)
  public ResponseEntity<Object> updateProcessSample(
      @Valid @RequestBody ProcessSampleRequestDto processSampleRequestDto) {
    if (processSampleService.isProcessSampleExist(processSampleRequestDto.getCode())) {
      processSampleService
          .saveProcessSample(mapper.map(processSampleRequestDto, ProcessSample.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_PROCESS_SAMPLE_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PROCESS_SAMPLE_CODE,
        validationFailureStatusCodes.getProcessSampleNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.PROCESS_SAMPLES_BY_PLANT_CODE)
  public ResponseEntity<Object> getProcessSampleByPlantCode(@PathVariable String plantCode) {
    if (plantService.isPlantExist(plantCode)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.PROCESS_SAMPLES,
          mapper.map(processSampleService.getProcessSampleByPlantCode(plantCode),
              ProcessSampleResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.info("No ProcessSample record exist for given plantCode");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.PROCESS_SAMPLE_SEARCH)
  public ResponseEntity<Object> getProcessSampleSearch(@PathVariable String plantCode,
      @RequestParam(name = "incomingSampleCode", required = false) String incomingSampleCode,
      @RequestParam(name = "rawMaterialName", required = false) String rawMaterialName,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(0, 0, totalpage, 0l);
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    return new ResponseEntity<>(new PaginatedContentResponse<>(Constants.PROCESS_SAMPLES,
        processSampleService.searchProcessSample(booleanBuilder, incomingSampleCode,
            rawMaterialName, plantCode, pageable, pagination),
        RestApiResponseStatus.OK, pagination), null, HttpStatus.OK);
  }
}

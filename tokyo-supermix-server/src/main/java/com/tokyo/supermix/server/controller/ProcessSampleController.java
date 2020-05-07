package com.tokyo.supermix.server.controller;

import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
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
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.ProcessSampleRequestDto;
import com.tokyo.supermix.data.dto.ProcessSampleResponseDto;
import com.tokyo.supermix.data.entities.ProcessSample;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.ProcessSampleService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class ProcessSampleController {
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  @Autowired
  private ProcessSampleService processSampleService;
  private static final Logger logger = Logger.getLogger(ProcessSampleController.class);

  @PostMapping(value = EndpointURI.PROCESS_SAMPLE)
  public ResponseEntity<Object> createProcessSample(
      @Valid @RequestBody ProcessSampleRequestDto processSampleRequestDto) {
    if (processSampleService.isProcessSampleExist(processSampleRequestDto.getCode())) {
      logger.debug("ProcessSample code already exists: ");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.PROCESS_SAMPLE_CODE,
          validationFailureStatusCodes.getProcessSampleAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
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

  @DeleteMapping(value = EndpointURI.PROCESS_SAMPLE_BY_CODE)
  public ResponseEntity<Object> deleteProcessSample(@PathVariable String code) {
    if (processSampleService.isProcessSampleExist(code)) {
      logger.debug("delete ProcessSample by code");
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
      return new ResponseEntity<>(new ContentResponse<>(Constants.PROCESS_SAMPLE_CODE, mapper
          .map(processSampleService.getProcessSampleByCode(code), ProcessSampleResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("No ProcessSample record exist for given code");
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

  @GetMapping(value = EndpointURI.PROCESS_SAMPLE_SEARCH)
  public ResponseEntity<Object> getProcessSampleSearch(
      @QuerydslPredicate(root = ProcessSample.class) Predicate predicate,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.PROCESS_SAMPLES,
        processSampleService.searchProcessSample(predicate, page, size), RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }
}

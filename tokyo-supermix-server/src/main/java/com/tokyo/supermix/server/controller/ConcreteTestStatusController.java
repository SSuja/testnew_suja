package com.tokyo.supermix.server.controller;

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
import com.tokyo.supermix.data.dto.ConcreteTestStatusRequestDto;
import com.tokyo.supermix.data.dto.ConcreteTestStatusResponseDto;
import com.tokyo.supermix.data.entities.ConcreteTestStatus;
import com.tokyo.supermix.data.enums.ConcreteStatus;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.ConcreteTestStatusService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class ConcreteTestStatusController {
  @Autowired
  private ConcreteTestStatusService concreteTestStatusService;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  private static final Logger logger = Logger.getLogger(ConcreteTestStatusController.class);

  @PostMapping(value = EndpointURI.CONCRETE_TEST_STATUS)
  public ResponseEntity<Object> createConcreteTestStatus(
      @RequestBody ConcreteTestStatusRequestDto concreteTestStatusRequestDto) {
    if (concreteTestStatusService.isDuplicateEntryExist(
        concreteTestStatusRequestDto.getConcreteTestTypeId(),
        concreteTestStatusRequestDto.getFinishProductSampleId())) {
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.CONCRETE_TEST_STATUS,
              validationFailureStatusCodes.getConcreteTestStatusAlreadyExist()),
          HttpStatus.BAD_REQUEST);
    }
    concreteTestStatusService
        .saveConcreteTestStatus(mapper.map(concreteTestStatusRequestDto, ConcreteTestStatus.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_CONCRETE_TEST_STATUS_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.CONCRETE_TEST_STATUSES)
  public ResponseEntity<Object> getConcreteTestStatuses() {
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.CONCRETE_TEST_STATUSES,
            mapper.map(concreteTestStatusService.getAllConcreteTestStatus(),
                ConcreteTestStatusResponseDto.class),
            RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.CONCRETE_TEST_STATUS_BY_ID)
  public ResponseEntity<Object> getConcreteTestStatusById(@PathVariable Long id) {
    if (concreteTestStatusService.isConcreteTestStatusExists(id)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.CONCRETE_TEST_STATUS,
          mapper.map(concreteTestStatusService.getConcreteTestStatusById(id),
              ConcreteTestStatusResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.CONCRETE_TEST_STATUS_ID,
        validationFailureStatusCodes.getConcreteTestStatusNotExist()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(EndpointURI.CONCRETE_TEST_STATUS_BY_ID)
  public ResponseEntity<Object> deleteConcreteTestStatus(@PathVariable Long id) {
    if (concreteTestStatusService.isConcreteTestStatusExists(id)) {
      concreteTestStatusService.deleteConcreteTestStatus(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.CONCRETE_TEST_STATUS_DELETED),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.CONCRETE_TEST_STATUS_ID,
        validationFailureStatusCodes.getConcreteTestStatusNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.CONCRETE_TEST_STATUS)
  public ResponseEntity<Object> updateConcreteTestStatus(
      @RequestBody ConcreteTestStatusRequestDto concreteTestStatusRequestDto) {
    if (concreteTestStatusService
        .isConcreteTestStatusExists(concreteTestStatusRequestDto.getId())) {
      if (concreteTestStatusService.isDuplicateEntryExist(
          concreteTestStatusRequestDto.getConcreteTestTypeId(),
          concreteTestStatusRequestDto.getFinishProductSampleId())) {
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.CONCRETE_TEST_STATUS,
                validationFailureStatusCodes.getConcreteTestStatusAlreadyExist()),
            HttpStatus.BAD_REQUEST);
      }
      concreteTestStatusService.saveConcreteTestStatus(
          mapper.map(concreteTestStatusRequestDto, ConcreteTestStatus.class));
      return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
          Constants.UPDATE_CONCRETE_TEST_STATUS_SUCCESS), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.CONCRETE_TEST_STATUS_ID,
        validationFailureStatusCodes.getConcreteTestStatusNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.CONCRETE_TEST_STATUS_BY_CONCRETE_STATUS)
  public ResponseEntity<Object> getConcreteTestStatusByConcreteStatus(
      @PathVariable ConcreteStatus concreteStatus) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.CONCRETE_TEST_STATUS,
        mapper.map(concreteTestStatusService.getConcreteTestStatusByConcreteStatus(concreteStatus),
            ConcreteTestStatusResponseDto.class),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.CONCRETE_TEST_STATUS_BY_CONCRETE_STATUS_AND_CONCRETE_TEST_TYPE)
  public ResponseEntity<Object> getConcreteTestStatusByConcreteStatusAndConcreteTestType(
      @PathVariable ConcreteStatus concreteStatus, @PathVariable String concreteTestType) {
    if (concreteTestStatusService.isConcreteTestTypeExits(concreteTestType)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.CONCRETE_TEST_STATUS,
          mapper.map(concreteTestStatusService
              .getConcreteTestStatusByConcreteStatusAndConcreteTestType(concreteStatus,
                  concreteTestType),
              ConcreteTestStatusResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.CONCRETE_TEST_TYPE,
        validationFailureStatusCodes.getConcreteTestStatusNotExist()), HttpStatus.BAD_REQUEST);
  }
}

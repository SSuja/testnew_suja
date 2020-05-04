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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.SieveTestRequestDto;
import com.tokyo.supermix.data.dto.SieveTestResponseDto;
import com.tokyo.supermix.data.entities.SieveTest;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.SieveTestService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin("*")
public class SieveTestController {
  @Autowired
  private Mapper mapper;
  @Autowired
  private SieveTestService sieveTestService;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  private static final Logger logger = Logger.getLogger(SieveTestController.class);

  // post API for
  @PostMapping(value = EndpointURI.SIEVE_TEST)
  public ResponseEntity<Object> createSieveTest(
      @Valid @RequestBody SieveTestRequestDto sieveTestRequestDto) {
    if (sieveTestService.isSieveTestExists(sieveTestRequestDto.getCode())) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.SIEVE_TEST_CODE,
          validationFailureStatusCodes.getSieveTestAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    sieveTestService.saveSieveTest(mapper.map(sieveTestRequestDto, SieveTest.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_SIEVE_TEST_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.SIEVE_TESTS)
  public ResponseEntity<Object> getAllSieveTests() {
    List<SieveTest> sieveTestTestList = sieveTestService.getAllSieveTests();
    return new ResponseEntity<Object>(
        new ContentResponse<>(Constants.SIEVE_TESTS,
            mapper.map(sieveTestTestList, SieveTestResponseDto.class), RestApiResponseStatus.OK),
        HttpStatus.OK);
  }

  // get SieveTest by code
  @GetMapping(value = EndpointURI.SIEVE_TEST_BY_CODE)
  public ResponseEntity<Object> getSieveTestByCode(@PathVariable String code) {
    if (sieveTestService.isSieveTestExists(code)) {
      logger.debug("Get SieveTest by code ");
      return new ResponseEntity<>(new ContentResponse<>(Constants.SIEVE_TEST,
          mapper.map(sieveTestService.getSieveTestByCode(code), SieveTestResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("Invalid Code");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.SIEVE_TEST_CODE,
        validationFailureStatusCodes.getSieveTestNotExist()), HttpStatus.BAD_REQUEST);
  }

  // get SieveTest Delete
  @DeleteMapping(value = EndpointURI.SIEVE_TEST_BY_CODE)
  public ResponseEntity<Object> deleteSieveTest(@PathVariable String code) {
    if (sieveTestService.isSieveTestExists(code)) {
      sieveTestService.deleteSieveTest(code);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.SIEVE_TEST_DELETED),
          HttpStatus.OK);
    }
    logger.debug("invalid SieveTestCode");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.SIEVE_TEST_CODE,
        validationFailureStatusCodes.getSieveTestNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.SIEVE_TEST)
  public ResponseEntity<Object> updateSieveTest(
      @Valid @RequestBody SieveTestRequestDto sieveTestRequestDto) {
    if (sieveTestService.isSieveTestExists(sieveTestRequestDto.getCode())) {
      sieveTestService.saveSieveTest(mapper.map(sieveTestRequestDto, SieveTest.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_SIEVE_TEST_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.SIEVE_TEST_CODE,
        validationFailureStatusCodes.getSieveTestNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.SEARCH_SIEVE_TEST)
  public ResponseEntity<Object> searchSieveTest(
      @RequestParam(name = "page", defaultValue = "0") int page,
      @RequestParam(name = "size", defaultValue = "500") int size,
      @RequestParam(name = "incomingSampleCode", required = false) String incomingSampleCode,
      @RequestParam(name = "plantCode", required = false) String plantCode,
      @RequestParam(name = "status", required = false) Status status,
      @RequestParam(name = "finenessModulusMax", required = false) Double finenessModulusMax,
      @RequestParam(name = "finenessModulusMin", required = false) Double finenessModulusMin,
      @RequestParam(name = "finenessModulus", required = false) Double finenessModulus) {
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    return new ResponseEntity<>(new ContentResponse<>(Constants.MATERIAL_TESTS,
        sieveTestService.searchSieveTest(incomingSampleCode, status, finenessModulus,
            finenessModulusMin, finenessModulusMax, plantCode, booleanBuilder, page, size),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }
}

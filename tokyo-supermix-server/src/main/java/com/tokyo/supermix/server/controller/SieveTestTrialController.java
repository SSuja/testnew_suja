package com.tokyo.supermix.server.controller;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.SieveTestTrialRequestDto;
import com.tokyo.supermix.data.dto.SieveTestTrialResponseDto;
import com.tokyo.supermix.data.entities.SieveTestTrial;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.SieveTestService;
import com.tokyo.supermix.server.services.SieveTestTrialService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin
public class SieveTestTrialController {
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  @Autowired
  private SieveTestTrialService sieveTestTrialService;
  @Autowired
  private SieveTestService sieveTestService;
  private static final Logger logger = Logger.getLogger(SieveTestTrialController.class);

  @PostMapping(value = EndpointURI.SIEVE_TEST_TRIAL)
  public ResponseEntity<Object> createSieveTestTrial(
      @RequestBody List<SieveTestTrialRequestDto> sieveTestTrialRequestDtoList) {
    sieveTestTrialService
        .saveSieveTestTrial(mapper.map(sieveTestTrialRequestDtoList, SieveTestTrial.class));
    sieveTestTrialService.updateSieveTestStatus(sieveTestService
        .getSieveTestByCode(sieveTestTrialRequestDtoList.get(0).getSieveTestCode()));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_SIEVE_TEST_TRIAL_SUCCEESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.SIEVE_TEST_TRIALS)
  public ResponseEntity<Object> getSieveTestTrials() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.SIEVE_TEST_TRIALS,
        mapper.map(sieveTestTrialService.getAllSieveTestTrials(), SieveTestTrialResponseDto.class),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.SIEVE_TEST_TRIAL_BY_ID)
  public ResponseEntity<Object> getSieveTestTrialById(@PathVariable Long id) {
    if (sieveTestTrialService.isSieveTestTrialExist(id)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.SIEVE_TEST_TRIALS, mapper
          .map(sieveTestTrialService.getSieveTestTrialById(id), SieveTestTrialResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("No Sieve Test Trial record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.SIEVE_TEST_TRIAL_ID,
        validationFailureStatusCodes.getSieveTestTrialNotExist()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(EndpointURI.SIEVE_TEST_TRIAL_BY_ID)
  public ResponseEntity<Object> deleteSieveTestTrial(@PathVariable Long id) {
    if (sieveTestTrialService.isSieveTestTrialExist(id)) {
      sieveTestTrialService.deleteSieveTestTrial(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.DELETE_SIEVE_TEST_TRIAL_SUCCESS),
          HttpStatus.OK);
    }
    logger.debug("No Sieve Test Trial record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.SIEVE_TEST_TRIAL_ID,
        validationFailureStatusCodes.getSieveTestTrialNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.SIEVE_TEST_TRIAL_BY_SIEVE_TEST_ID)
  public ResponseEntity<Object> getSieveSizeBySieveTestId(@PathVariable String sieveTestCode) {
    if (sieveTestService.isSieveTestExists(sieveTestCode)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.SIEVE_TEST_CODE,
          mapper.map(sieveTestTrialService.findSieveTestTrialBySieveTestCode(sieveTestCode),
              SieveTestTrialResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    } else {
      logger.debug("No Sieve Test Trial record exist for given Sieve Test id");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.SIEVE_TEST_CODE,
          validationFailureStatusCodes.getSieveTestNotExist()), HttpStatus.BAD_REQUEST);
    }
  }

}

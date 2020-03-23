package com.tokyo.supermix.server.controller;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.SieveTestTrialRequestDto;
import com.tokyo.supermix.data.entities.SieveTestTrial;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.server.services.SieveTestTrialService;
import com.tokyo.supermix.util.Constants;

@RestController
@CrossOrigin
public class SieveTestTrialController {
  @Autowired
  private Mapper mapper;
  @Autowired
  private SieveTestTrialService sieveTestTrialService;
  private static final Logger logger = Logger.getLogger(SieveTestTrialController.class);

  @PostMapping(value = EndpointURI.SIEVE_TEST_TRIAL)
  public ResponseEntity<Object> createSieveTestTrial(
      @RequestBody List<SieveTestTrialRequestDto> sieveTestTrialRequestDtoList) {
    logger.debug("Add Sieve_Test_Trial");
    sieveTestTrialService
        .saveSieveTestTrial(mapper.map(sieveTestTrialRequestDtoList, SieveTestTrial.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.SIEVE_TEST_TRIAL_ADD),
        HttpStatus.OK);
  }
}

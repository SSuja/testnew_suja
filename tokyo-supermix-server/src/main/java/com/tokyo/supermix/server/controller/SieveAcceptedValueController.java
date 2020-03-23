package com.tokyo.supermix.server.controller;

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
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.SieveAcceptedValueRequestDto;
import com.tokyo.supermix.data.dto.SieveAcceptedValueResponseDto;
import com.tokyo.supermix.data.entities.SieveAcceptedValue;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.SieveAcceptedValueService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class SieveAcceptedValueController {
  @Autowired
  private SieveAcceptedValueService sieveAcceptedValueService;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  private static final Logger logger = Logger.getLogger(SieveAcceptedValueController.class);

  @PostMapping(value = EndpointURI.SIEVE_ACCEPTED_VALUE)
  public ResponseEntity<Object> createSieveAcceptedValue(
      @Valid @RequestBody SieveAcceptedValueRequestDto sieveAcceptedValueRequestDto) {
    if (sieveAcceptedValueService.isSieveSizeExist(sieveAcceptedValueRequestDto.getSieveSizeId())) {
      logger.debug("Sieve Size already exists: createSieveAcceptedValue(), sieveSize: {}");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.SIEVE_SIZE,
          validationFailureStatusCodes.getSieveSizeAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    sieveAcceptedValueService
        .saveSieveAcceptedValue(mapper.map(sieveAcceptedValueRequestDto, SieveAcceptedValue.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_SIEVE_ACCEPTED_VALUE_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.SIEVE_ACCEPTED_VALUES)
  public ResponseEntity<Object> getSieveAcceptedValues() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.SIEVE_ACCEPTED_VALUES,
        mapper.map(sieveAcceptedValueService.getAllSieveAcceptedValues(),
            SieveAcceptedValueResponseDto.class),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.SIEVE_ACCEPTED_VALUE_BY_ID)
  public ResponseEntity<Object> getSieveAcceptedValueById(@PathVariable Long id) {
    if (sieveAcceptedValueService.isSieveAcceptedValueExist(id)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.SIEVE_ACCEPTED_VALUES,
          mapper.map(sieveAcceptedValueService.getSieveAcceptedValueById(id),
              SieveAcceptedValueResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("No Sieve Accepted Value record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.SIEVE_ACCEPTED_VALUE_ID,
        validationFailureStatusCodes.getSieveAcceptedValueNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.SIEVE_ACCEPTED_VALUE)
  public ResponseEntity<Object> updateSieveAcceptedValue(
      @Valid @RequestBody SieveAcceptedValueRequestDto sieveAcceptedValueRequestDto) {
    if (sieveAcceptedValueService.isSieveAcceptedValueExist(sieveAcceptedValueRequestDto.getId())) {
      if (sieveAcceptedValueService
          .isSieveSizeExist(sieveAcceptedValueRequestDto.getSieveSizeId())) {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.SIEVE_SIZE,
            validationFailureStatusCodes.getSieveSizeNotExist()), HttpStatus.BAD_REQUEST);
      }
      sieveAcceptedValueService.saveSieveAcceptedValue(
          mapper.map(sieveAcceptedValueRequestDto, SieveAcceptedValue.class));
      return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
          Constants.UPDATE_SIEVE_ACCEPTED_VALUE_SUCCESS), HttpStatus.OK);
    }
    logger.debug("No Sieve Accepted Value record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.SIEVE_ACCEPTED_VALUE_ID,
        validationFailureStatusCodes.getSieveAcceptedValueNotExist()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(EndpointURI.SIEVE_ACCEPTED_VALUE_BY_ID)
  public ResponseEntity<Object> deleteSieveAcceptedValue(@PathVariable Long id) {
    if (sieveAcceptedValueService.isSieveAcceptedValueExist(id)) {
      sieveAcceptedValueService.deleteSieveAcceptedValue(id);
      return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
          Constants.DELETE_SIEVE_ACCEPTED_VALUE_SUCCESS), HttpStatus.OK);
    }
    logger.debug("No Sieve Accepted Value record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.SIEVE_ACCEPTED_VALUE_ID,
        validationFailureStatusCodes.getSieveAcceptedValueNotExist()), HttpStatus.BAD_REQUEST);
  }

}

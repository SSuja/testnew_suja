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
import com.tokyo.supermix.data.dto.AdmixtureAcceptedValueRequestDto;
import com.tokyo.supermix.data.dto.AdmixtureAcceptedValueResponseDto;
import com.tokyo.supermix.data.entities.AdmixtureAcceptedValue;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.AdmixtureAcceptedValueService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class AdmixtureAcceptedValueController {
  @Autowired
  private AdmixtureAcceptedValueService admixtureAcceptedValueService;
  @Autowired
  ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;

  private static final Logger logger = Logger.getLogger(AdmixtureAcceptedValueController.class);

  // get all AdmixtureAcceptedValues
  @GetMapping(value = EndpointURI.ADMIXTURE_ACCEPTED_VALUES)
  public ResponseEntity<Object> getAllAdmixtureAcceptedValues() {
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.ADMIXTURE_ACCEPTED_VALUES,
            mapper.map(admixtureAcceptedValueService.getAllAdmixtureAcceptedValues(),
                AdmixtureAcceptedValueResponseDto.class),
            RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }

  @PostMapping(value = EndpointURI.ADMIXTURE_ACCEPTED_VALUE)
  public ResponseEntity<Object> createAdmixtureAcceptedValue(
      @Valid @RequestBody AdmixtureAcceptedValueRequestDto admixtureAcceptedValueRequestDto) {
    if (admixtureAcceptedValueService.isAdmixtureAcceptedValueExistsByTestConfigureId(
        admixtureAcceptedValueRequestDto.getTestConfigureId())) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_CONFIGURE_ID,
          validationFailureStatusCodes.getTestIdAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    admixtureAcceptedValueService.saveAdmixtureAcceptedValue(
        mapper.map(admixtureAcceptedValueRequestDto, AdmixtureAcceptedValue.class));
    return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
        Constants.ADD_ADMIXTURE_ACCEPTED_VALUE_SUCCESS), HttpStatus.OK);
  }

  // update API for AdmixtureAcceptedValue
  @PutMapping(value = EndpointURI.ADMIXTURE_ACCEPTED_VALUE)
  public ResponseEntity<Object> updateAdmixtureAcceptedValue(
      @Valid @RequestBody AdmixtureAcceptedValueRequestDto admixtureAcceptedValueRequestDto) {
    if (admixtureAcceptedValueService
        .isAdmixtureAcceptedValueExist(admixtureAcceptedValueRequestDto.getId())) {
      if (admixtureAcceptedValueService.isUpdatedTestConfigureIdExist(
          admixtureAcceptedValueRequestDto.getId(),
          admixtureAcceptedValueRequestDto.getTestConfigureId())) {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_CONFIGURE_ID,
            validationFailureStatusCodes.getTestIdAlreadyExist()), HttpStatus.BAD_REQUEST);
      }
      admixtureAcceptedValueService.saveAdmixtureAcceptedValue(
          mapper.map(admixtureAcceptedValueRequestDto, AdmixtureAcceptedValue.class));
      return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
          Constants.ADD_ADMIXTURE_ACCEPTED_VALUE_SUCCESS), HttpStatus.OK);
    }
    return new ResponseEntity<>(
        new ValidationFailureResponse(Constants.ADMIXTURE_ACCEPTED_VALUE_ID,
            validationFailureStatusCodes.getAdmixtureAcceptedValueNotExist()),
        HttpStatus.BAD_REQUEST);
  }

  // DELETE API for AdmixtureAcceptedValue
  @DeleteMapping(value = EndpointURI.ADMIXTURE_ACCEPTED_VALUE_BY_ID)
  public ResponseEntity<Object> deleteAdmixtureAcceptedValue(@PathVariable Long id) {
    if (admixtureAcceptedValueService.isAdmixtureAcceptedValueExist(id)) {
      admixtureAcceptedValueService.deleteAdmixtureAcceptedValue(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADMIXTURE_ACCEPTED_VALUE_DELETED),
          HttpStatus.OK);
    }
    logger.debug("invalid AdmixtureAcceptedValue");
    return new ResponseEntity<>(
        new ValidationFailureResponse(Constants.ADMIXTURE_ACCEPTED_VALUE_ID,
            validationFailureStatusCodes.getAdmixtureAcceptedValueNotExist()),
        HttpStatus.BAD_REQUEST);
  }

  // get AdmixtureAcceptedValue by id
  @GetMapping(value = EndpointURI.ADMIXTURE_ACCEPTED_VALUE_BY_ID)
  public ResponseEntity<Object> getAdmixtureAcceptedValueById(@PathVariable Long id) {
    if (admixtureAcceptedValueService.isAdmixtureAcceptedValueExist(id)) {
      logger.debug("Get AdmixtureAcceptedValue by id ");
      return new ResponseEntity<>(
          new ContentResponse<>(Constants.ADMIXTURE_ACCEPTED_VALUE,
              mapper.map(admixtureAcceptedValueService.getAdmixtureAcceptedValueById(id),
                  AdmixtureAcceptedValueResponseDto.class),
              RestApiResponseStatus.OK),
          HttpStatus.OK);
    }
    logger.debug("invalid");
    return new ResponseEntity<>(
        new ValidationFailureResponse(Constants.ADMIXTURE_ACCEPTED_VALUE_ID,
            validationFailureStatusCodes.getAdmixtureAcceptedValueNotExist()),
        HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.ADMIXTURE_ACCEPTED_VALUE_BY_TEST_CONFIGURE_ID)
  public ResponseEntity<Object> getAdmixtureAcceptedValueByTestId(
      @PathVariable Long testConfigureId) {
    if (admixtureAcceptedValueService
        .isAdmixtureAcceptedValueExistsByTestConfigureId(testConfigureId)) {
      return new ResponseEntity<Object>(new ContentResponse<>(Constants.TEST_CONFIGURE,
          mapper.map(admixtureAcceptedValueService.getAdmixtureAcceptedValueByTestConfigureId(
              testConfigureId), AdmixtureAcceptedValueResponseDto.class),
          RestApiResponseStatus.OK), null, HttpStatus.OK);
    }
    logger.debug("invalid");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.ADMIXTURE_ACCEPTED_VALUE,
        validationFailureStatusCodes.getTestConfigureNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.SEARCH_ADMIXTURE_ACCEPTED_VALUE)
  public ResponseEntity<Object> getAdmixtureAcceptedValueSearch(
      @QuerydslPredicate(root = AdmixtureAcceptedValue.class) Predicate predicate,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.ADMIXTURE_ACCEPTED_VALUES,
        admixtureAcceptedValueService.searchAdmixtureAcceptedValue(predicate, size, page),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }
}

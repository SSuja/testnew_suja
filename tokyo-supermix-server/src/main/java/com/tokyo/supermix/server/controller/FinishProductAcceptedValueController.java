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
import com.tokyo.supermix.data.dto.FinishProductAcceptedValueRequestDto;
import com.tokyo.supermix.data.dto.FinishProductAcceptedValueResponseDto;
import com.tokyo.supermix.data.entities.FinishProductAcceptedValue;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.FinishProductAcceptedValueService;
import com.tokyo.supermix.server.services.TestParameterService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class FinishProductAcceptedValueController {
  @Autowired
  private FinishProductAcceptedValueService finishProductAcceptedValueService;
  @Autowired
  private TestParameterService testParameterService;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  private static final Logger logger = Logger.getLogger(FinishProductAcceptedValueController.class);

  @PostMapping(value = EndpointURI.FINISH_PRODUCT_ACCEPTED_VALUE)
  public ResponseEntity<Object> createFinishProductAcceptedValue(
      @Valid @RequestBody FinishProductAcceptedValueRequestDto finishProductAcceptedValueRequestDto) {
    finishProductAcceptedValueService.saveFinishProductAcceptedValue(
        mapper.map(finishProductAcceptedValueRequestDto, FinishProductAcceptedValue.class));
    return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
        Constants.ADD_FINISH_PRODUCT_ACCEPTED_VALUE_SUCCESS), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.FINISH_PRODUCT_ACCEPTED_VALUES)
  public ResponseEntity<Object> getAllFinishProductAcceptedValues() {
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.FINISH_PRODUCT_ACCEPTED_VALUES,
            mapper.map(finishProductAcceptedValueService.getAllFinishProductAcceptedValues(),
                FinishProductAcceptedValueResponseDto.class),
            RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.FINISH_PRODUCT_ACCEPTED_VALUE_BY_ID)
  public ResponseEntity<Object> getFinishProductAcceptedValueById(@PathVariable Long id) {
    if (finishProductAcceptedValueService.isFinishProductAcceptedValueExist(id)) {
      return new ResponseEntity<>(
          new ContentResponse<>(Constants.FINISH_PRODUCT_ACCEPTED_VALUE,
              mapper.map(finishProductAcceptedValueService.getFinishProductAcceptedValueById(id),
                  FinishProductAcceptedValueResponseDto.class),
              RestApiResponseStatus.OK),
          HttpStatus.OK);
    }
    logger.debug("No Finish Product Accepted Value record exist for given id");
    return new ResponseEntity<>(
        new ValidationFailureResponse(Constants.FINISH_PRODUCT_ACCEPTED_VALUE_ID,
            validationFailureStatusCodes.getFinishProductAcceptedValueNotExist()),
        HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(value = EndpointURI.FINISH_PRODUCT_ACCEPTED_VALUE_BY_ID)
  public ResponseEntity<Object> deleteFinishProductAcceptedValue(@PathVariable Long id) {
    if (finishProductAcceptedValueService.isFinishProductAcceptedValueExist(id)) {
      finishProductAcceptedValueService.deleteFinishProductAcceptedValue(id);
      return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
          Constants.DELETE_FINISH_PRODUCT_ACCEPTED_VALUE), HttpStatus.OK);
    }
    logger.debug("No Finish Product Accepted Value record exist for given id");
    return new ResponseEntity<>(
        new ValidationFailureResponse(Constants.FINISH_PRODUCT_ACCEPTED_VALUE_ID,
            validationFailureStatusCodes.getFinishProductAcceptedValueNotExist()),
        HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.FINISH_PRODUCT_ACCEPTED_VALUE)
  public ResponseEntity<Object> updateFinishProductAcceptedValue(
      @Valid @RequestBody FinishProductAcceptedValueRequestDto finishProductAcceptedValueRequestDto) {
    if (finishProductAcceptedValueService
        .isFinishProductAcceptedValueExist(finishProductAcceptedValueRequestDto.getId())) {
      finishProductAcceptedValueService.saveFinishProductAcceptedValue(
          mapper.map(finishProductAcceptedValueRequestDto, FinishProductAcceptedValue.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.ACCEPTED_VALUE_UPDATE_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(
        new ValidationFailureResponse(Constants.FINISH_PRODUCT_ACCEPTED_VALUE,
            validationFailureStatusCodes.getFinishProductAcceptedValueNotExist()),
        HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.GET_FINISH_PRODUCT_ACCEPTED_VALUE_BY_TEST_PARAMETER)
  public ResponseEntity<Object> getFinishProductAcceptedValueByTestParameter(
      @PathVariable Long testParameterId) {
    if (testParameterService.isTestParameterExist(testParameterId)) {
      return new ResponseEntity<>(
          new ContentResponse<>(Constants.FINISH_PRODUCT_ACCEPTED_VALUE,
              mapper
                  .map(
                      finishProductAcceptedValueService
                          .getFinishProductAcceptedValueByTestParameter(testParameterId),
                      FinishProductAcceptedValueResponseDto.class),
              RestApiResponseStatus.OK),
          HttpStatus.OK);
    } else {
      logger.debug("No Finish Product Accepted Value record exist for given Test Parameter id");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_PARAMETER_ID,
          validationFailureStatusCodes.getTestParameterNotExist()), HttpStatus.BAD_REQUEST);
    }
  }

}

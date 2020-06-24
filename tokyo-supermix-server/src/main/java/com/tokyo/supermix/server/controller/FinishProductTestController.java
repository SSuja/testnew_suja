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
import com.tokyo.supermix.data.dto.FinishProductTestRequestDto;
import com.tokyo.supermix.data.dto.FinishProductTestResponseDto;
import com.tokyo.supermix.data.entities.FinishProductTest;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.FinishProductTestService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin
@RestController
public class FinishProductTestController {

  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  @Autowired
  private FinishProductTestService finishProductTestService;

  private static final Logger logger = Logger.getLogger(FinishProductTestController.class);


  @GetMapping(value = EndpointURI.FINISH_PRODUCT_TESTS)
  public ResponseEntity<Object> getAllFinishProductSampleTests() {
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.FINISH_PRODUCT_TESTS,
            mapper.map(finishProductTestService.getAllFinishProductTests(),
                FinishProductTestResponseDto.class),
            RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }

  @PostMapping(value = EndpointURI.FINISH_PRODUCT_TEST)
  public ResponseEntity<Object> saveFinishProductSampleTest(
      @Valid @RequestBody FinishProductTestRequestDto finishProductTestRequestDto) {
    finishProductTestService
        .createFinishProductTest(mapper.map(finishProductTestRequestDto, FinishProductTest.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_FINISH_PRODUCT_TEST_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.FINISH_PRODUCT_TEST_BY_ID)
  public ResponseEntity<Object> getFinishProductSampleTestById(@PathVariable Long id) {
    if (finishProductTestService.isFinishProductTestExists(id)) {
      logger.debug("Get By Id");
      return new ResponseEntity<>(new ContentResponse<>(Constants.FINISH_PRODUCT_TEST,
          mapper.map(finishProductTestService.getFinishProductTestById(id),
              FinishProductTestResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.FINISH_PRODUCT_TEST_ID,
        validationFailureStatusCodes.getFinishProductTestNotExit()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(value = EndpointURI.FINISH_PRODUCT_TEST_BY_ID)
  public ResponseEntity<Object> deleteFinishProductSampleTest(@PathVariable Long id) {
    if (finishProductTestService.isFinishProductTestExists(id)) {
      logger.debug("delete by id");
      finishProductTestService.deleteFinishProductTest(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.DELETED_FINISH_PRODUCT_TEST),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.FINISH_PRODUCT_TEST_ID,
        validationFailureStatusCodes.getFinishProductTestNotExit()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.FINISH_PRODUCT_TEST)
  public ResponseEntity<Object> updateFinishProductSampleTest(
      @Valid @RequestBody FinishProductTestRequestDto finishProductTestRequestDto) {
    if ((finishProductTestService.isFinishProductTestExists(finishProductTestRequestDto.getId()))) {
      finishProductTestService.createFinishProductTest(
          mapper.map(finishProductTestRequestDto, FinishProductTest.class));
      return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
          Constants.UPDATE_FINISH_PRODUCT_TEST_SUCCESS), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.FINISH_PRODUCT_TEST_ID,
        validationFailureStatusCodes.getFinishProductTestNotExit()), HttpStatus.BAD_REQUEST);
  }

}

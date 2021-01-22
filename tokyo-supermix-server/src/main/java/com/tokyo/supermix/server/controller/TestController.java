package com.tokyo.supermix.server.controller;

import javax.validation.Valid;
import org.apache.log4j.Logger;
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
import com.tokyo.supermix.data.dto.TestDto;
import com.tokyo.supermix.data.entities.Test;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.TestService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin(origins = "*")
public class TestController {

  @Autowired
  private TestService testService;

  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;

  @Autowired
  private Mapper mapper;

  private static final Logger logger = Logger.getLogger(TestController.class);

  // Create Test API
  @PostMapping(value = EndpointURI.TEST)
  public ResponseEntity<Object> createTest(@Valid @RequestBody TestDto testDto) {
    if (testService.isTestExist(testDto.getName())) {
      logger.debug("Test already exists: createTest(), test: {}");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST,
          validationFailureStatusCodes.getTestAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    testService.saveTest(mapper.map(testDto, Test.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_TEST_SUCCESS), HttpStatus.OK);
  }

  // Get all Test API
  @GetMapping(value = EndpointURI.TESTS)
  public ResponseEntity<Object> getAllTests(@RequestParam(name = "page") int page,
      @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(page, size, totalpage, 0l);
    pagination.setTotalRecords(testService.countTest());

    return new ResponseEntity<>(new PaginatedContentResponse<>(Constants.TEST,
        mapper.map(testService.getAllTests(pageable), TestDto.class), RestApiResponseStatus.OK,
        pagination), null, HttpStatus.OK);
  }

  // Delete Test API
  @DeleteMapping(value = EndpointURI.TEST_BY_ID)
  public ResponseEntity<Object> deleteTest(@PathVariable Long id) {
    if (testService.isTestExist(id)) {
      testService.deleteTest(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.DELETE_TEST_SCCESS),
          HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST,
        validationFailureStatusCodes.getTestNotExist()), HttpStatus.BAD_REQUEST);
  }

  // Get Test by Id API
  @GetMapping(value = EndpointURI.TEST_BY_ID)
  public ResponseEntity<Object> getTestById(@PathVariable Long id) {
    if (testService.isTestExist(id)) {
      logger.debug("Id found");
      return new ResponseEntity<>(
          new ContentResponse<>(Constants.TEST,
              mapper.map(testService.getTestById(id), TestDto.class), RestApiResponseStatus.OK),
          HttpStatus.OK);
    }
    logger.debug("Invalid id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST,
        validationFailureStatusCodes.getTestNotExist()), HttpStatus.BAD_REQUEST);
  }

  // Update Test API
  @PutMapping(value = EndpointURI.TEST)
  public ResponseEntity<Object> updateTest(@Valid @RequestBody TestDto testDto) {
    if (testService.isTestExist(testDto.getId())) {
      if (testService.isUpdatedTestExist(testDto.getId(), testDto.getName())) {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST,
            validationFailureStatusCodes.getTestAlreadyExist()), HttpStatus.BAD_REQUEST);
      }
      testService.saveTest(mapper.map(testDto, Test.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_TEST_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST,
        validationFailureStatusCodes.getTestNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.SEARCH_TEST)
  public ResponseEntity<Object> searchTests(@RequestParam(name = "page") int page,
      @RequestParam(name = "size") int size,
      @RequestParam(name = "name", required = false) String name) {
    Pageable pageable = PageRequest.of(page, size);
    Pagination pagination = new Pagination(0, 0, 0, 0l);
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    return new ResponseEntity<>(new PaginatedContentResponse<>(Constants.TEST,
        mapper.map(testService.searchTests(name, booleanBuilder, page, size, pageable, pagination),
            TestDto.class),
        RestApiResponseStatus.OK, pagination), HttpStatus.OK);
  }
}

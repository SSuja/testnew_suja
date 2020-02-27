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
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.TestTypeDto;
import com.tokyo.supermix.data.entities.TestType;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.TestTypeService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class TestTypeController {
  @Autowired
  private Mapper mapper;

  @Autowired
  ValidationFailureStatusCodes validationFailureStatusCodes;

  @Autowired
  TestTypeService testTypeService;
  private static final Logger logger = Logger.getLogger(TestTypeController.class);

  // get all TestTypes
  @GetMapping(value = EndpointURI.TEST_TYPES)
  public ResponseEntity<Object> getAllTestTypes() {
    List<TestTypeDto> testTypeDtoList =
        mapper.map(testTypeService.getAllTestTypes(), TestTypeDto.class);
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.TEST_TYPES, testTypeDtoList, RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }

  // Add TestType
  @PostMapping(value = EndpointURI.TEST_TYPE)
  public ResponseEntity<Object> saveTestType(@Valid @RequestBody TestTypeDto testTypeDto) {
    if (testTypeService.isTestTypeExist(testTypeDto.getType())) {
      logger.debug("Test type already exists: saveTestType(), testType: {}");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_TYPE,
          validationFailureStatusCodes.getTestTypealreadyExists()), HttpStatus.BAD_REQUEST);
    }
    TestType testType = mapper.map(testTypeDto, TestType.class);
    testTypeService.saveTestType(testType);
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_TEST_TYPE_SUCCESS),
        HttpStatus.OK);
  }

  // Get TestType By Id
  @GetMapping(value = EndpointURI.GET_TEST_TYPE_BY_ID)
  public ResponseEntity<Object> getTestTypeById(@PathVariable Long id) {
    if (testTypeService.isTestTypeIdExist(id)) {
      TestType testType = testTypeService.getTestTypeById(id);
      return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_TYPE,
          mapper.map(testType, TestTypeDto.class), RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_TYPE_ID,
        validationFailureStatusCodes.getTestTypeNotExist()), HttpStatus.BAD_REQUEST);
  }

  // Delete TestType
  @DeleteMapping(value = EndpointURI.DELETE_TEST_TYPE)
  public ResponseEntity<Object> deleteTestType(@PathVariable Long id) {
    if (testTypeService.isTestTypeIdExist(id)) {
      testTypeService.deleteTestType(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.TEST_TYPE_DELETED),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_TYPE_ID,
        validationFailureStatusCodes.getTestTypeNotExist()), HttpStatus.BAD_REQUEST);
  }

  // Update TestType
  @PutMapping(value = EndpointURI.TEST_TYPE)
  public ResponseEntity<Object> updateTestType(@Valid @RequestBody TestTypeDto testTypeDto) {
    if (testTypeService.isTestTypeIdExist(testTypeDto.getId())) {
      if (testTypeService.isTestTypeExist(testTypeDto.getType())) {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_TYPE,
            validationFailureStatusCodes.getTestTypealreadyExists()), HttpStatus.BAD_REQUEST);
      }
      testTypeService.saveTestType(mapper.map(testTypeDto, TestType.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_TEST_TYPE_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_TYPE_ID,
        validationFailureStatusCodes.getTestTypeNotExist()), HttpStatus.BAD_REQUEST);
  }


}

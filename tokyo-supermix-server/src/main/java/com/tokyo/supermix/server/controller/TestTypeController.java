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
import com.tokyo.supermix.data.dto.TestTypeRequestDto;
import com.tokyo.supermix.data.dto.TestTypeResponseDto;
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
    return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_TYPES,
        mapper.map(testTypeService.getAllTestTypes(), TestTypeResponseDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  // Add TestType
  @PostMapping(value = EndpointURI.TEST_TYPE)
  public ResponseEntity<Object> saveTestType(
      @Valid @RequestBody TestTypeRequestDto testTypeRequestDto) {
    if (testTypeService.isTestTypeExist(testTypeRequestDto.getType())) {
      logger.debug("Test type already exists: saveTestType(), testType: {}");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_TYPE,
          validationFailureStatusCodes.getTestTypealreadyExists()), HttpStatus.BAD_REQUEST);
    }
    testTypeService.saveTestType(mapper.map(testTypeRequestDto, TestType.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_TEST_TYPE_SUCCESS),
        HttpStatus.OK);
  }

  // Get TestType By Id
  @GetMapping(value = EndpointURI.GET_TEST_TYPE_BY_ID)
  public ResponseEntity<Object> getTestTypeById(@PathVariable Long id) {
    if (testTypeService.isTestTypeIdExist(id)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_TYPE,
          mapper.map(testTypeService.getTestTypeById(id), TestTypeResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
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
  public ResponseEntity<Object> updateTestType(
      @Valid @RequestBody TestTypeRequestDto testTypeRequestDto) {
    if (testTypeService.isTestTypeIdExist(testTypeRequestDto.getId())) {
      if (testTypeService.isUpdatedTestTypeExist(testTypeRequestDto.getId(),
          testTypeRequestDto.getType())) {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_TYPE,
            validationFailureStatusCodes.getTestTypealreadyExists()), HttpStatus.BAD_REQUEST);
      }
      testTypeService.saveTestType(mapper.map(testTypeRequestDto, TestType.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_TEST_TYPE_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_TYPE_ID,
        validationFailureStatusCodes.getTestTypeNotExist()), HttpStatus.BAD_REQUEST);
  }

  // Get TestType By Material sub category Id
  @GetMapping(value = EndpointURI.GET_TEST_TYPES_BY_MATERIAL_SUB_CATEGORY_ID)
  public ResponseEntity<Object> getAllTestTypesByMaterialSubCategoryId(
      @PathVariable Long materialSubCategoryId) {
    if (testTypeService.isMaterialSubCategoryIdExist(materialSubCategoryId)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_TYPES,
          mapper.map(testTypeService.getTestTypesByMaterialSubCategoryId(materialSubCategoryId),
              TestTypeResponseDto.class),
          RestApiResponseStatus.OK), null, HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_SUB_CATEGORY_ID,
        validationFailureStatusCodes.getMaterialSubCategoryNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.SEARCH_TEST_TYPE)
  public ResponseEntity<Object> getTestTypeBySearch(
      @QuerydslPredicate(root = TestType.class) Predicate predicate,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.TEST_TYPES,
            testTypeService.searchTestType(predicate, size, page), RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }
}

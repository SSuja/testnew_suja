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
import com.tokyo.supermix.data.dto.TestConfigureRequestDto;
import com.tokyo.supermix.data.dto.TestConfigureResponseDto;
import com.tokyo.supermix.data.entities.TestConfigure;
import com.tokyo.supermix.data.enums.AcceptedType;
import com.tokyo.supermix.data.enums.MainType;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.MaterialSubCategoryService;
import com.tokyo.supermix.server.services.TestConfigureService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationConstance;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin
public class TestConfigureController {
  @Autowired
  private TestConfigureService testConfigureService;
  @Autowired
  private MaterialSubCategoryService materialSubCategoryService;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;

  private static final Logger logger = Logger.getLogger(TestConfigureController.class);

  @PostMapping(value = EndpointURI.TEST_CONFIGURE)
  public ResponseEntity<Object> createTestConfigure(
      @Valid @RequestBody TestConfigureRequestDto testConfigureRequestDto) {
    if (testConfigureService.isExistByTestIdAndMaterialSubCategoryId(
        testConfigureRequestDto.getTestId(), testConfigureRequestDto.getMaterialSubCategoryId(),
        testConfigureRequestDto.getRawMaterialId())) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_CONFIGURE,
          validationFailureStatusCodes.getTestConfigureAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    if (testConfigureService.isPrefixAlreadyExists(testConfigureRequestDto.getPrefix())) {
      return new ResponseEntity<>(new ValidationFailureResponse(ValidationConstance.PREFIX,
          validationFailureStatusCodes.getPrefixAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_CONFIGURE,
        testConfigureService.saveTestConfigure(testConfigureRequestDto), RestApiResponseStatus.OK),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.TEST_CONFIGURES)
  public ResponseEntity<Object> getAllTestConfigures() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_CONFIGURE,
        mapper.map(testConfigureService.getAllTestConfigures(), TestConfigureResponseDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.TEST_CONFIGURE_BY_ID)
  public ResponseEntity<Object> getTestById(@PathVariable Long id) {
    if (testConfigureService.isTestConfigureExist(id)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_CONFIGURE,
          mapper.map(testConfigureService.getTestConfigureById(id), TestConfigureResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("No Test record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_CONFIGURE_ID,
        validationFailureStatusCodes.getTestConfigureNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.TEST_CONFIGURE)
  public ResponseEntity<Object> updateTestConfigure(
      @Valid @RequestBody TestConfigureRequestDto testConfigureRequestDto) {
    if (testConfigureService.isTestConfigureExist(testConfigureRequestDto.getId())) {
      if (testConfigureService.isUpdatedMaterialSubCategoryAndTest(testConfigureRequestDto.getId(),
          testConfigureRequestDto.getTestId(), testConfigureRequestDto.getMaterialSubCategoryId(),
          testConfigureRequestDto.getRawMaterialId())) {
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.TEST_CONFIGURE,
                validationFailureStatusCodes.getTestConfigureAlreadyExist()),
            HttpStatus.BAD_REQUEST);
      }
      if (testConfigureService.isPrefixAlreadyExistsUpdate(testConfigureRequestDto.getId(),
          testConfigureRequestDto.getPrefix())) {
        return new ResponseEntity<>(new ValidationFailureResponse(ValidationConstance.PREFIX,
            validationFailureStatusCodes.getPrefixAlreadyExist()), HttpStatus.BAD_REQUEST);
      }
      testConfigureService
          .updateTestConfigure(mapper.map(testConfigureRequestDto, TestConfigure.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_TEST_CONFIGURE_SUCCESS),
          HttpStatus.OK);
    }
    logger.debug("No Test Configure record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_CONFIGURE_ID,
        validationFailureStatusCodes.getTestConfigureNotExist()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(EndpointURI.TEST_CONFIGURE_BY_ID)
  public ResponseEntity<Object> deleteTestConfigure(@PathVariable Long id) {
    if (testConfigureService.isTestConfigureExist(id)) {
      testConfigureService.deleteTestConfigure(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.DELETE_TEST_CONFIGURE_SCCESS),
          HttpStatus.OK);
    }
    logger.debug("No Test record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_CONFIGURE_ID,
        validationFailureStatusCodes.getTestConfigureNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.GET_TEST_CONFIGURE_BY_CORE_TEST)
  public ResponseEntity<Object> getTestByCoreTest(@PathVariable boolean coreTest) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_CONFIGURE,
        mapper.map(testConfigureService.findByCoreTest(coreTest), TestConfigureResponseDto.class),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }

  @PutMapping(value = EndpointURI.TEST_CONFIGURE_BY_ID_AND_CORE_TEST)
  public ResponseEntity<Object> updateCoreTestTestConfigure(
      @Valid @RequestBody TestConfigureRequestDto testConfigureRequestDto) {
    if (testConfigureService.isTestConfigureExist(testConfigureRequestDto.getId())) {
      testConfigureService.updateCoreTestForTestConfigure(testConfigureRequestDto.getId(),
          testConfigureRequestDto.isCoreTest());
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_TEST_CONFIGURE_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_CONFIGURE_ID,
        validationFailureStatusCodes.getTestConfigureNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.GET_TEST_DETAILS_BY_CONFIGURE_ID)
  public ResponseEntity<Object> getTestConfigAllDetailsByTestConfigId(
      @PathVariable Long testConfigId) {
    if (testConfigureService.isTestConfigureExist(testConfigId)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_CONFIGURE,
          testConfigureService.getTestConfigureDetailsByConfigureId(testConfigId),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_CONFIGURE_ID,
        validationFailureStatusCodes.getTestConfigureNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.GET_TEST_CONFIGURE_BY_TEST_TYPE)
  public ResponseEntity<Object> getTestConfigureByTestType(@PathVariable MainType testType) {
    if (testConfigureService.isexistByTestType(testType)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_CONFIGURE,
          mapper.map(testConfigureService.findByTestType(testType), TestConfigureResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("No Test Configure record exist for given test type");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_TYPE,
        validationFailureStatusCodes.getTestConfigureNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.GET_TEST_CONFIGURE_BY_MATERIAL_SUB_CATEGORY)
  public ResponseEntity<Object> getTestConfigureByMaterialSubCategory(
      @PathVariable Long materialSubCategoryId) {
    if (materialSubCategoryService.isMaterialSubCategoryExist(materialSubCategoryId)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.MATERIAL_SUB_CATEGORY,
          mapper.map(testConfigureService.findByMaterialSubCategory(materialSubCategoryId),
              TestConfigureResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("No Test Configure record exist for given Material Sub Category");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_SUB_CATEGORY,
        validationFailureStatusCodes.getMaterialSubCategoryNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.GET_TEST_CONFIGURE_BY_MATERIAL_SUB_CATEGORY_AND_TEST_TYPE)
  public ResponseEntity<Object> getTestConfigureByMaterialSubCategoryAndTestType(
      @PathVariable Long materialSubCategoryId, @PathVariable MainType testType) {
    if (materialSubCategoryService.isMaterialSubCategoryExist(materialSubCategoryId)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.MATERIAL_SUB_CATEGORY,
          mapper.map(testConfigureService.getTestConfiguresByMaterialSubCategoryAndTestType(
              materialSubCategoryId, testType), TestConfigureResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("No Test Configure record exist for given Material Sub Category");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_SUB_CATEGORY,
        validationFailureStatusCodes.getMaterialSubCategoryNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.UPDATE_ACCEPTED_TYPE_TEST_CONFIGURE)
  public ResponseEntity<Object> updateAcceptedTypeTestConfigure(@PathVariable Long testConfigureId,
      @PathVariable AcceptedType acceptedType) {
    TestConfigure testConfigure = testConfigureService.getTestConfigureById(testConfigureId);
    testConfigure.setAcceptedType(acceptedType);
    testConfigureService.updateTestConfigure(testConfigure);
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_TEST_CONFIGURE_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.GET_TEST_CONFIGURE_BY_MATERIAL_CATEGORY)
  public ResponseEntity<Object> getTestConfigureByMaterialCategory(
      @PathVariable Long materialCategoryId) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_CONFIGURE,
        mapper.map(testConfigureService.findByMaterialCategory(materialCategoryId),
            TestConfigureResponseDto.class),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.TEST_CONFIGURE_SEARCH)
  public ResponseEntity<Object> searchTestConfigure(@RequestParam(name = "page") int page,
      @RequestParam(name = "size") int size,
      @RequestParam(name = "testName", required = false) String testName,
      @RequestParam(name = "mainType", required = false) MainType mainType,
      @RequestParam(name = "mainCategoryName", required = false) String mainCategoryName,
      @RequestParam(name = "subCategoryName", required = false) String subCategoryName,
      @RequestParam(name = "materialName", required = false) String materialName) {
    Pageable pageable = PageRequest.of(page, size);
    Pagination pagination = new Pagination(0, 0, 0, 0l);
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    return new ResponseEntity<>(new PaginatedContentResponse<>(Constants.TEST_CONFIGURE,
        mapper.map(
            testConfigureService.searchTestConfigure(testName, mainType, mainCategoryName,
                subCategoryName, materialName, booleanBuilder, page, size, pageable, pagination),
            TestConfigureResponseDto.class),
        RestApiResponseStatus.OK, pagination), HttpStatus.OK);
  }
}

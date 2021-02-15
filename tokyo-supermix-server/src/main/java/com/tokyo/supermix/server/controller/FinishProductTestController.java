package com.tokyo.supermix.server.controller;

import javax.servlet.http.HttpServletRequest;
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
import com.tokyo.supermix.data.dto.FinishProductTestRequestDto;
import com.tokyo.supermix.data.dto.FinishProductTestResponseDto;
import com.tokyo.supermix.data.entities.FinishProductTest;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.security.CurrentUser;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.FinishProductTestService;
import com.tokyo.supermix.server.services.FinishProductTrialService;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;
import com.tokyo.supermix.util.privilege.PermissionConstants;

@CrossOrigin
@RestController
public class FinishProductTestController {
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  @Autowired
  private FinishProductTestService finishProductTestService;
  @Autowired
  private FinishProductTrialService finishProductTrialService;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;
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
    // if (finishProductTestService.isDuplicateEntry(
    // finishProductTestRequestDto.getFinishProductSampleCode(),
    // finishProductTestRequestDto.getTestConfigureId())) {
    // return new ResponseEntity<>(
    // new ValidationFailureResponse(Constants.FINISH_PRODUCT_TEST,
    // validationFailureStatusCodes.getFinishProductTestAlreadyExists()),
    // HttpStatus.BAD_REQUEST);
    // }
    return new ResponseEntity<>(new ContentResponse<>(Constants.FINISH_PRODUCT_TEST,
        finishProductTestService.createFinishProductTest(
            mapper.map(finishProductTestRequestDto, FinishProductTest.class)),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.FINISH_PRODUCT_TEST_BY_CODE)
  public ResponseEntity<Object> getFinishProductSampleTestByCode(@PathVariable String code) {
    if (finishProductTestService.isFinishProductTestExists(code)) {
      logger.debug("Get By Id");
      return new ResponseEntity<>(new ContentResponse<>(Constants.FINISH_PRODUCT_TEST,
          mapper.map(finishProductTestService.getFinishProductTestByCode(code),
              FinishProductTestResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.FINISH_PRODUCT_TEST_ID,
        validationFailureStatusCodes.getFinishProductTestNotExit()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(value = EndpointURI.FINISH_PRODUCT_TEST_BY_CODE)
  public ResponseEntity<Object> deleteFinishProductSampleTest(@PathVariable String code) {
    if (finishProductTestService.isFinishProductTestExists(code)) {
      logger.debug("delete by id");
      finishProductTestService.deleteFinishProductTest(code);
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
    if ((finishProductTestService
        .isFinishProductTestExists(finishProductTestRequestDto.getCode()))) {
      finishProductTestService.createFinishProductTest(
          mapper.map(finishProductTestRequestDto, FinishProductTest.class));
      return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
          Constants.UPDATE_FINISH_PRODUCT_TEST_SUCCESS), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.FINISH_PRODUCT_TEST_ID,
        validationFailureStatusCodes.getFinishProductTestNotExit()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.GET_FINISH_PRODUCT_TESTS_BY_TESTCONFIGURE)
  public ResponseEntity<Object> getFinishProductSampleTestByTestConfigure(
      @PathVariable Long testConfigureId) {
    if (finishProductTestService.isFinishProductTestExistsByTestConfigure(testConfigureId)) {
      logger.debug("Get By Id");
      return new ResponseEntity<>(new ContentResponse<>(Constants.FINISH_PRODUCT_TEST,
          mapper.map(
              finishProductTestService.getAllFinishProductTestsByTestConfigure(testConfigureId),
              FinishProductTestResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.FINISH_PRODUCT_TEST_ID,
        validationFailureStatusCodes.getTestConfigureNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.GET_FINISH_PRODUCT_TESTS_BY_FINISH_PRODUCT_SAMPLE_TESTCONFIGURE)
  public ResponseEntity<Object> getFinishProductSampleTestByFinishProductSampleAndTestConfigure(
      @PathVariable String finishProductSampleCode, @PathVariable Long testConfigureId) {
    if (finishProductTestService.isFinishProductTestExistsByTestConfigure(testConfigureId)) {
      logger.debug("Get By Id");
      return new ResponseEntity<>(new ContentResponse<>(Constants.FINISH_PRODUCT_TEST,
          mapper.map(
              finishProductTestService
                  .getFinishProductTestByFinishProductSampleCodeAndTestConfigureId(
                      finishProductSampleCode, testConfigureId),
              FinishProductTestResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.FINISH_PRODUCT_TEST_ID,
        validationFailureStatusCodes.getTestConfigureNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.FINISH_PRODUCT_TEST_BY_PLANT)
  public ResponseEntity<Object> getAllFinishProductSampleTestsByPlant(
      @CurrentUser UserPrincipal currentUser, @PathVariable String plantCode,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(page, size, totalpage, 0l);
    if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
      pagination.setTotalRecords(finishProductTestService.getCountFinishProductTest());
      return new ResponseEntity<>(new PaginatedContentResponse<>(Constants.FINISH_PRODUCT_TESTS,
          mapper.map(finishProductTestService.getAllFinishProductTestByPlant(currentUser, pageable),
              FinishProductTestResponseDto.class),
          RestApiResponseStatus.OK, pagination), HttpStatus.OK);
    }
    if (currentUserPermissionPlantService.getPermissionPlantCodeByCurrentUser(currentUser,
        PermissionConstants.VIEW_FINISH_PRODUCT_TEST).contains(plantCode)) {
      pagination
          .setTotalRecords(finishProductTestService.getCountFinishProductTestByPlant(plantCode));
      return new ResponseEntity<>(new PaginatedContentResponse<>(Constants.FINISH_PRODUCT_TESTS,
          mapper.map(finishProductTestService.getAllFinishProductTestByPlant(plantCode, pageable),
              FinishProductTestResponseDto.class),
          RestApiResponseStatus.OK, pagination), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }


  @PutMapping(value = EndpointURI.FINISH_PRODUCT_TEST_COMMENT)
  public ResponseEntity<Object> updateFinishProductTestComment(
      @Valid @RequestBody FinishProductTestRequestDto finishProductTestRequestDto) {
    finishProductTestService.updateFinishProductTestComment(
        mapper.map(finishProductTestRequestDto, FinishProductTest.class));
    return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
        Constants.FINISH_PRODUCT_TEST_COMMENT_UPDATED), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.GET_FINISH_PRODUCT_TESTS_BY_FINISH_PRODUCT_SAMPLE)
  public ResponseEntity<Object> getFinishproductTestsByFinishProductSample(
      @PathVariable String finishProductSampleCode) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.FINISH_PRODUCT_TEST,
        finishProductTestService.getFinishProductTestByFinishProductSample(finishProductSampleCode),
        RestApiResponseStatus.OK), HttpStatus.OK);

  }

  @GetMapping(value = EndpointURI.FINISH_PRODUCT_TEST_SEARCH)
  public ResponseEntity<Object> getFinishProductTest(@PathVariable String plantCode,
      @RequestParam(name = "code", required = false) String code,
      @RequestParam(name = "specimenCode", required = false) String specimenCode,
      @RequestParam(name = "finishProductSampleCode",
          required = false) String finishProductSampleCode,
      @RequestParam(name = "mixDesignCode", required = false) String mixDesignCode,
      @RequestParam(name = "testName", required = false) String testName,
      @RequestParam(name = "materialName", required = false) String materialName,
      @RequestParam(name = "mainCategoryName", required = false) String mainCategoryName,
      @RequestParam(name = "subCategoryName", required = false) String subCategoryName,
      @RequestParam(name = "plantName", required = false) String plantName,
      @RequestParam(name = "status", required = false) Status status,
      @RequestParam(name = "date", required = false) String date,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(0, 0, totalpage, 0l);
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    return new ResponseEntity<>(
        new PaginatedContentResponse<>(Constants.FINISH_PRODUCT_TEST, mapper.map(
            finishProductTestService.searchFinishProductTest(booleanBuilder, code, specimenCode,
                finishProductSampleCode, mixDesignCode, testName, materialName, mainCategoryName,
                subCategoryName, status, date, plantName, plantCode, pageable, pagination),
            FinishProductTestResponseDto.class), RestApiResponseStatus.OK, pagination),
        null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.GET_FINISH_PRODUCT_TESTS_BY_FINISH_PRODUCT_SAMPLE_CODE)
  public ResponseEntity<Object> getFinishproductTestsByFinishProductSampleCode(
      @PathVariable String finishProductSampleCode) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.FINISH_PRODUCT_TEST,
        mapper.map(finishProductTestService.getFinishProductTestsByFinishProductSampleCode(
            finishProductSampleCode), FinishProductTestResponseDto.class),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.FINISH_PRODUCT_TEST_APROVE_BY_CODE)
  public ResponseEntity<Object> finishproductTestsAproveUpdate(
      @PathVariable String finishProductTestCode, HttpServletRequest request) {
    finishProductTrialService.aprovedUpdateStatus(finishProductTestCode, request);
    return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
        Constants.UPDATE_FINISH_PRODUCT_TEST_APROVE_SUCCESS), HttpStatus.OK);
  }
}

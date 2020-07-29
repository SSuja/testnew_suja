package com.tokyo.supermix.server.controller;

import javax.servlet.http.HttpSession;
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
import com.tokyo.supermix.security.CurrentUser;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.FinishProductTestService;
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
    if (finishProductTestService.isDuplicateEntry(
        finishProductTestRequestDto.getFinishProductSampleCode(),
        finishProductTestRequestDto.getTestConfigureId())) {
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.FINISH_PRODUCT_TEST,
              validationFailureStatusCodes.getFinishProductTestAlreadyExists()),
          HttpStatus.BAD_REQUEST);
    }
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
      @CurrentUser UserPrincipal currentUser,HttpSession session) {
    String plantCode = (String)session.getAttribute(Constants.SESSION_PLANT);
    if(plantCode == null) {
      return new ResponseEntity<>(
          new ContentResponse<>(Constants.FINISH_PRODUCT_TESTS,
              mapper.map(finishProductTestService.getAllFinishProductTests(),
                  FinishProductTestResponseDto.class),
              RestApiResponseStatus.OK),
          null, HttpStatus.OK);
    }
    if(currentUserPermissionPlantService.getPermissionPlantCodeByCurrentUser(currentUser, PermissionConstants.VIEW_FINISH_PRODUCT_TEST).contains(plantCode)) {
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.FINISH_PRODUCT_TESTS,
            mapper.map(finishProductTestService.getAllFinishProductTestByPlant(plantCode),
                FinishProductTestResponseDto.class),
            RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
}

}

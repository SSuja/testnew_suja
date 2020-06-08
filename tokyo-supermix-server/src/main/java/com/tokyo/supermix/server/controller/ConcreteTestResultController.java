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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.ConcreteTestResultRequestDto;
import com.tokyo.supermix.data.dto.ConcreteTestResultResponseDto;
import com.tokyo.supermix.data.entities.ConcreteTest;
import com.tokyo.supermix.data.entities.ConcreteTestResult;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.ConcreteTestResultService;
import com.tokyo.supermix.server.services.ConcreteTestService;
import com.tokyo.supermix.server.services.ConcreteTestTypeService;
import com.tokyo.supermix.server.services.FinishProductSampleService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class ConcreteTestResultController {
  @Autowired
  private Mapper mapper;
  @Autowired
  private ConcreteTestResultService concreteTestResultService;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private ConcreteTestService concreteTestService;
  @Autowired
  private ConcreteTestTypeService concreteTestTypeService;
  @Autowired
  private FinishProductSampleService finishProductSampleService;

  private static final Logger logger = Logger.getLogger(ConcreteTestResultController.class);

  @PostMapping(value = EndpointURI.CONCRETE_TEST_RESULT)
  public ResponseEntity<Object> createConcreteTestResult(
      @Valid @RequestBody ConcreteTestResultRequestDto concreteTestResultRequestDto) {
    ConcreteTest concreteTest =
        concreteTestService.getConcreteTestById(concreteTestResultRequestDto.getConcreteTestId());
    if (concreteTest.getConcreteTestType().getType().equalsIgnoreCase(Constants.SLUMP)) {
      concreteTestResultService
          .saveConcreteTestSlumpStatus(concreteTestResultRequestDto.getFinishProductSampleId());
      concreteTestResultService
          .saveConcreteTestStrengthStatus(concreteTestResultRequestDto.getFinishProductSampleId());
      concreteTestResultService
          .saveConcreteTestMoistureStatus(concreteTestResultRequestDto.getFinishProductSampleId());
      concreteTestResultService.saveConcreteSlumpTestSlumpResult(
          mapper.map(concreteTestResultRequestDto, ConcreteTestResult.class));
      concreteTestResultService.saveConcreteSlumpTestWaterCementRatioResult(
          mapper.map(concreteTestResultRequestDto, ConcreteTestResult.class));
      concreteTestResultService.saveConcreteTestWaterBinderRatioResult(
          mapper.map(concreteTestResultRequestDto, ConcreteTestResult.class));
      concreteTestResultService.saveConcreteSlumpTestSlumpGradeRatioResult(
          mapper.map(concreteTestResultRequestDto, ConcreteTestResult.class));
    } else if (concreteTest.getConcreteTestType().getType().equalsIgnoreCase(Constants.STRENGTH)) {
      concreteTestResultService.saveConcreteStrengthTestAverageStrengthResult(
          mapper.map(concreteTestResultRequestDto, ConcreteTestResult.class));
      concreteTestResultService.saveConcreteStrengthTestStrengthGradeRatioResult(
          mapper.map(concreteTestResultRequestDto, ConcreteTestResult.class));
    } else if (concreteTest.getConcreteTestType().getType().equalsIgnoreCase(Constants.MOISTURE)) {
      concreteTestResultService.saveConcreteTestResult(
          mapper.map(concreteTestResultRequestDto, ConcreteTestResult.class));
    } else {
      concreteTestResultService.saveConcreteTestResult(
          mapper.map(concreteTestResultRequestDto, ConcreteTestResult.class));
    }
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_CONCRETE_TEST_RESULT_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.CONCRETE_TEST_RESULTS)
  public ResponseEntity<Object> getAllConcreteTestResult() {
    return new ResponseEntity<Object>(new ContentResponse<>(Constants.CONCRETE_TEST_RESULTS,
        mapper.map(concreteTestResultService.getAllConcreteTestResults(),
            ConcreteTestResultResponseDto.class),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.CONCRETE_TEST_RESULT_BY_ID)
  public ResponseEntity<Object> getConcreteTestResultById(@PathVariable Long id) {
    if (concreteTestResultService.isConcreteTestResultExists(id)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.CONCRETE_TEST_RESULT,
          mapper.map(concreteTestResultService.getConcreteTestResultById(id),
              ConcreteTestResultResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.CONCRETE_TEST_RESULT,
        validationFailureStatusCodes.getConcreteTestNotExist()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(value = EndpointURI.CONCRETE_TEST_RESULT_BY_ID)
  public ResponseEntity<Object> deleteConcreteTestResult(@PathVariable Long id) {
    if (concreteTestResultService.isConcreteTestResultExists(id)) {
      concreteTestResultService.deleteConcreteTestResult(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.CONCRETE_TEST_RESULT_DELETED),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.CONCRETE_TEST_RESULT,
        validationFailureStatusCodes.getConcreteTestNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.CONCRETE_TEST_RESULT)
  public ResponseEntity<Object> updateConcreteTestResult(
      @Valid @RequestBody ConcreteTestResultRequestDto concreteTestResultRequestDto) {
    if (concreteTestResultService
        .isConcreteTestResultExists(concreteTestResultRequestDto.getId())) {
      ConcreteTest concreteTest =
          concreteTestService.getConcreteTestById(concreteTestResultRequestDto.getConcreteTestId());
      if (concreteTest.getConcreteTestType().getType().equalsIgnoreCase(Constants.SLUMP)) {
        concreteTestResultService.saveConcreteSlumpTestSlumpResult(
            mapper.map(concreteTestResultRequestDto, ConcreteTestResult.class));
        concreteTestResultService.saveConcreteSlumpTestWaterCementRatioResult(
            mapper.map(concreteTestResultRequestDto, ConcreteTestResult.class));
        concreteTestResultService.saveConcreteTestWaterBinderRatioResult(
            mapper.map(concreteTestResultRequestDto, ConcreteTestResult.class));
        concreteTestResultService.saveConcreteSlumpTestSlumpGradeRatioResult(
            mapper.map(concreteTestResultRequestDto, ConcreteTestResult.class));
      } else if (concreteTest.getConcreteTestType().getType()
          .equalsIgnoreCase(Constants.STRENGTH)) {
        concreteTestResultService.saveConcreteStrengthTestAverageStrengthResult(
            mapper.map(concreteTestResultRequestDto, ConcreteTestResult.class));
        concreteTestResultService.saveConcreteStrengthTestStrengthGradeRatioResult(
            mapper.map(concreteTestResultRequestDto, ConcreteTestResult.class));
      } else if (concreteTest.getConcreteTestType().getType()
          .equalsIgnoreCase(Constants.MOISTURE)) {
        concreteTestResultService.saveConcreteTestResult(
            mapper.map(concreteTestResultRequestDto, ConcreteTestResult.class));
      } else {
        concreteTestResultService.saveConcreteTestResult(
            mapper.map(concreteTestResultRequestDto, ConcreteTestResult.class));
      }
      return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
          Constants.UPDATE_CONCRETE_TEST_RESULT_SUCCESS), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.CONCRETE_TEST_RESULT,
        validationFailureStatusCodes.getConcreteTestNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.SEARCH_CONCRETE_TEST_RESULT)
  public ResponseEntity<Object> searchConcreteStrengthTest(@RequestParam(name = "page") int page,
      @RequestParam(name = "size") int size,
      @RequestParam(name = "finishProductSampleId", required = false) Long finishProductSampleId,
      @RequestParam(name = "ConcreteTestId", required = false) Long ConcreteTestId,
      @RequestParam(name = "status", required = false) Status status,
      @RequestParam(name = "result", required = false) Double result,
      @RequestParam(name = "resultMin", required = false) Double resultMin,
      @RequestParam(name = "resultMax", required = false) Double resultMax) {
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    return new ResponseEntity<>(new ContentResponse<>(Constants.CONCRETE_TEST_RESULTS,
        concreteTestResultService.searchConcreteTestResult(finishProductSampleId, ConcreteTestId,
            status, result, resultMin, resultMax, booleanBuilder, page, size),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.CONCRETE_TEST_RESULT_BY_CONCRETE_TEST_ID)
  public ResponseEntity<Object> getConcreteTestResultByConcreteTestId(
      @PathVariable Long concreteTestId) {
    if (concreteTestService.isConcreteTestExists(concreteTestId)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.CONCRETE_TEST_RESULT,
          mapper.map(concreteTestResultService.findByConcreteTestId(concreteTestId),
              ConcreteTestResultResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.CONCRETE_TEST_RESULT,
        validationFailureStatusCodes.getConcreteTestNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.CONCRETE_TEST_RESULT_BY_CONCRETE_TEST_TYPE_ID)
  public ResponseEntity<Object> getConcreteTestResultByConcreteTestTypeId(
      @PathVariable Long concreteTestTypeId) {
    if (concreteTestTypeService.isConcreteTestTypeExists(concreteTestTypeId)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.CONCRETE_TEST_RESULTS,
          mapper.map(concreteTestResultService.findByConcreteTestTypeId(concreteTestTypeId),
              ConcreteTestResultResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.CONCRETE_TEST_TYPE,
        validationFailureStatusCodes.getConcreteTestTypeNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.CONCRETE_TEST_RESULT_BY_FINISH_PRODUCT_SAMPLE_ID)
  public ResponseEntity<Object> getConcreteTestResultByFinishProductSampleId(
      @PathVariable Long finishProductSampleId) {
    if (finishProductSampleService.isFinishProductSampleExist(finishProductSampleId)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.CONCRETE_TEST_RESULTS,
          mapper.map(concreteTestResultService.findByFinishProductSampleId(finishProductSampleId),
              ConcreteTestResultResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.FINISH_PRODUCT_SAMPLE_ID,
        validationFailureStatusCodes.getFinishProductSampleNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(
      value = EndpointURI.CONCRETE_TEST_RESULT_BY_CONCRETE_TEST_TYPE_ID_AND_FINISH_PRODUCT_SAMPLE_ID)
  public ResponseEntity<Object> getConcreteTestResultByConcreteTestTypeIdAndFinishProductSampleId(
      @PathVariable Long concreteTestTypeId, @PathVariable Long finishProductSampleId) {
    if (finishProductSampleService.isFinishProductSampleExist(finishProductSampleId)
        && concreteTestTypeService.isConcreteTestTypeExists(concreteTestTypeId)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.CONCRETE_TEST_RESULTS,
          mapper.map(
              concreteTestResultService
                  .getConcreteTestResultByConcreteTestConcreteTestTypeIdAndFinishProductSampleId(
                      concreteTestTypeId, finishProductSampleId),
              ConcreteTestResultResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.FINISH_PRODUCT_SAMPLE_ID,
        validationFailureStatusCodes.getFinishProductSampleNotExist()), HttpStatus.BAD_REQUEST);

  }
}

package com.tokyo.supermix.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.ConcreteTestReportDto;
import com.tokyo.supermix.data.dto.MaterialTestTrialResultDto;
import com.tokyo.supermix.data.dto.report.ConcreteStrengthDto;
import com.tokyo.supermix.data.dto.report.IncomingSampleDeliveryReportDto;
import com.tokyo.supermix.data.dto.report.TestReportDetailDto;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.FinishProductTestService;
import com.tokyo.supermix.server.services.IncomingSampleService;
import com.tokyo.supermix.server.services.MaterialTestService;
import com.tokyo.supermix.server.services.TestReportService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class TestReportController {
  @Autowired
  MaterialTestService materialTestService;
  @Autowired
  private TestReportService testReportService;
  @Autowired
  private IncomingSampleService incomingSampleService;
  @Autowired
  private FinishProductTestService finishProductTestService;

  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;

  @GetMapping(value = EndpointURI.MATERIAL_TEST_REPORT_DETAIL)
  public ResponseEntity<Object> getMaterialTestReportDetails(@PathVariable String materialTestCode,
      @PathVariable String plantCode) {
    if (materialTestService.isMaterialTestExists(materialTestCode)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_REPORT,
          mapper.map(testReportService.getMaterialTestDetailReport(materialTestCode, plantCode),
              TestReportDetailDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_TEST,
        validationFailureStatusCodes.getMaterialTestNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.FINISH_PRODUCT_TEST_DETAIL_REPORT)
  public ResponseEntity<Object> getFinishProductTestReportDetails(
      @PathVariable String finishProductTestCode) {
    if (finishProductTestService.isFinishProductTestExists(finishProductTestCode)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_REPORT,
          mapper.map(testReportService.getFinishProductTestDetailReport(finishProductTestCode),
              TestReportDetailDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.FINISH_PRODUCT_TEST,
        validationFailureStatusCodes.getFinishProductTestNotExit()), HttpStatus.BAD_REQUEST);
  }


  @GetMapping(value = EndpointURI.INCOMING_SAMPLE_SUMMARY_REPORT)
  public ResponseEntity<Object> getIncomingSampleSummaryReportDetails(
      @PathVariable String incomingSampleCode, @PathVariable String plantCode) {
    if (incomingSampleService.isIncomingSampleExist(incomingSampleCode)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_REPORT,
          mapper.map(
              testReportService.getIncomingSampleSummaryReport(incomingSampleCode, plantCode),
              IncomingSampleDeliveryReportDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.INCOMING_SAMPLE,
        validationFailureStatusCodes.getIncomingSampleNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.INCOMING_SAMPLE_DELIVERY_REPORT)
  public ResponseEntity<Object> getIncomingSampleDeliveryReportDetails(
      @PathVariable String incomingSampleCode, @PathVariable String testName,
      @PathVariable String plantCode) {
    if (incomingSampleService.isIncomingSampleExist(incomingSampleCode)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_REPORT,
          mapper.map(testReportService.getIncomingSampleDeliveryReports(incomingSampleCode,
              testName, plantCode), IncomingSampleDeliveryReportDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.INCOMING_SAMPLE,
        validationFailureStatusCodes.getIncomingSampleNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.CONCRETE_TEST_REPORT)
  public ResponseEntity<Object> getConcreteTestReport(@PathVariable String finishProductTestCode,
      @PathVariable String plantCode) {
    if (finishProductTestService.isFinishProductTestExists(finishProductTestCode)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_REPORT,
          mapper.map(testReportService.getConcreteTestReport(finishProductTestCode, plantCode),
              ConcreteTestReportDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.FINISH_PRODUCT_TEST,
        validationFailureStatusCodes.getFinishProductTestNotExit()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.MATERIAL_TEST_TRIALS_WISE_BY_MATERIAL_TEST_CODE)
  public ResponseEntity<Object> getMaterialTestTrials(@PathVariable String materialTestCode) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_REPORT,
        mapper.map(testReportService.getMaterialTestTrailByMaterialTestCode(materialTestCode),
            MaterialTestTrialResultDto.class),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.CONCRETE_STRENGTHS_BY_PLANT)
  public ResponseEntity<Object> getConcreteResults(@PathVariable String plantCode) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.CONCRETE_STRENGTH,
        mapper.map(testReportService.getConcreteStrengths(plantCode), ConcreteStrengthDto.class),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.SIEVE_TRIALS_BY_MATERIAL_TEST_CODE_PLANT_CODE)
  public ResponseEntity<Object> getSeiveTest(@PathVariable String materialTestCode,
      @PathVariable String plantCode) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.SIEVE_TEST,
        testReportService.getSieveTestReport(materialTestCode, plantCode),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.SIEVE_TEST_GRAPH_BY_MATERIAL_TEST_CODE)
  public ResponseEntity<Object> getSeiveTestGraph(@PathVariable String materialTestCode) {
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.SIEVE_TEST,
            testReportService.getTrialResultGraph(materialTestCode), RestApiResponseStatus.OK),
        HttpStatus.OK);
  }
}

package com.tokyo.supermix.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.FinishProductParameterResultResponseDto;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.server.services.FinishProductParameterResultService;
import com.tokyo.supermix.server.services.FinishProductSampleService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin("*")
public class FinishProductParameterResultController {
  @Autowired
  private Mapper mapper;
  @Autowired
  private FinishProductParameterResultService finishProductParameterResultService;
  @Autowired
  private FinishProductSampleService finishProductSampleService;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;

  @GetMapping(value = EndpointURI.FINISH_PRODUCT_PARAMETER_RESULTS)
  public ResponseEntity<Object> getAllFinishProductParameterResults() {
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.FINISH_PRODUCT_PARAMETER_RESULTS,
            mapper.map(finishProductParameterResultService.getallFinishProductParameterResults(),
                FinishProductParameterResultResponseDto.class),
            RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.FINISH_PRODUCT_PARAMETER_RESULT_BY_FINISH_PRODUCT_TEST_CODE)
  public ResponseEntity<Object> getByFinishProductTestCode(
      @PathVariable String finishProductTestCode) {
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.FINISH_PRODUCT_SAMPLE_ID,
            mapper.map(
                finishProductParameterResultService.getFinishProductResult(finishProductTestCode),
                FinishProductParameterResultResponseDto.class),
            RestApiResponseStatus.OK),
        HttpStatus.OK);
  }
}

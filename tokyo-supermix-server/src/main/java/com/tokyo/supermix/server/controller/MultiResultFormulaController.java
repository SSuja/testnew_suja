package com.tokyo.supermix.server.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.MultiResultFormulaRequestDto;
import com.tokyo.supermix.data.dto.MultiResultFormulaResponseDto;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.MultiResultFormulaService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin
public class MultiResultFormulaController {
  @Autowired
  private MultiResultFormulaService multiResultFormulaService;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;

  @GetMapping(value = EndpointURI.MULTI_RESULT_FORMULA_BY_TEST_CONFIGURE_ID)
  public ResponseEntity<Object> getMultiResultFormulaByTestConfigureId(
      @PathVariable Long testConfigureId) {
    if (multiResultFormulaService.isExistsTestConfigureId(testConfigureId)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.MULTI_RESULT_FORMULA,
          mapper.map(multiResultFormulaService.getByTestConfigureId(testConfigureId),
              MultiResultFormulaResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_CONFIGURE_ID,
        validationFailureStatusCodes.getTestConfigureNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PostMapping(value = "")
  public ResponseEntity<Object> createMultiResultFormula(
      @Valid @RequestBody MultiResultFormulaRequestDto multiResultFormulaRequestDto) {
    return null;
  }
}

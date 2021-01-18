package com.tokyo.supermix.server.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.MultiResultFormulaRequestDto;
import com.tokyo.supermix.data.dto.MultiResultFormulaResponseDto;
import com.tokyo.supermix.data.entities.MultiResultFormula;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
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

  @PostMapping(value = EndpointURI.MULTI_RESULT_FORMULA)
  public ResponseEntity<Object> createMultiResultFormula(
      @Valid @RequestBody MultiResultFormulaRequestDto multiResultFormulaRequestDto) {
    if (multiResultFormulaService
        .isExistsTestConfigureId(multiResultFormulaRequestDto.getTestConfigureId())) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.MULTI_RESULT_FORMULA,
          validationFailureStatusCodes.getTestConfigureAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    multiResultFormulaService
        .saveMultiResultFormula(mapper.map(multiResultFormulaRequestDto, MultiResultFormula.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_MULTI_RESULT_FORMULA_SUCCESS),
        HttpStatus.OK);
  }

  @DeleteMapping(value = EndpointURI.MULTI_RESULT_FORMULA_BY_ID)
  public ResponseEntity<Object> deleteMultiResultFormula(@PathVariable Long id) {
    if (multiResultFormulaService.isExistById(id)) {
      multiResultFormulaService.deleteMultiResultFormula(id);;
      return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
          Constants.DELETE_MULTI_RESULT_FORMULA_SUCCESS), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MULTI_RESULT_FORMULA,
        validationFailureStatusCodes.getTestConfigureNotExist()), HttpStatus.BAD_REQUEST);
  }
}

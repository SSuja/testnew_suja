package com.tokyo.supermix.server.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.script.ScriptException;
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
import com.tokyo.supermix.data.dto.TestParameterEquationDto;
import com.tokyo.supermix.data.dto.TestParameterRequestDto;
import com.tokyo.supermix.data.dto.TestParameterResponseDto;
import com.tokyo.supermix.data.entities.TestParameter;
import com.tokyo.supermix.data.enums.InputMethod;
import com.tokyo.supermix.data.enums.ParameterDataType;
import com.tokyo.supermix.data.enums.TestParameterType;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.ParameterService;
import com.tokyo.supermix.server.services.TestConfigureService;
import com.tokyo.supermix.server.services.TestParameterService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class TestParameterController {
  @Autowired
  private TestParameterService testParameterService;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private TestConfigureService testConfigureService;
  @Autowired
  ParameterService parameterService;
  @Autowired
  private Mapper mapper;
  private static final Logger logger = Logger.getLogger(TestParameterController.class);

  @PostMapping(value = EndpointURI.TEST_PARAMETER)
  public ResponseEntity<Object> createTestParameter(
      @Valid @RequestBody List<TestParameterRequestDto> testParameterRequestDtoList)
      throws ScriptException {
    List<TestParameterRequestDto> listTestParameter = testParameterRequestDtoList.stream()
        .filter(testPara -> (testPara.getInputMethods() == null) || (testPara.getType() == null)
            || (testPara.getUnitId() == null)
            || ((testPara.getType().equals(TestParameterType.CONFIG))
                && parameterService.getParameterById(testPara.getParameterId())
                    .getParameterDataType().equals(ParameterDataType.NUMBER)
                && (testPara.getValue() == null))
            || (testPara.getType().equals(TestParameterType.CONFIG)
                && parameterService.getParameterById(testPara.getParameterId())
                    .getParameterDataType().equals(ParameterDataType.DATETIME)
                && (testPara.getDateValue() == null)))
        .collect(Collectors.toList());
    if (listTestParameter.isEmpty()) {
      if (testParameterService.checkAbbreviation(testParameterRequestDtoList)) {
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.ABBREVIATION,
                validationFailureStatusCodes.getAbbreviationAlreadyExist()),
            HttpStatus.BAD_REQUEST);
      }
      for (TestParameterRequestDto testParameterRequestDto : testParameterRequestDtoList) {
        if ((testParameterRequestDto.getParameterId() != null)
            && (testParameterRequestDto.getQualityParameterId() == null)) {
          if (testParameterService.isAbbreviationNull(testParameterRequestDto.getAbbreviation())) {
            return new ResponseEntity<>(new ValidationFailureResponse(Constants.ABBREVIATION,
                validationFailureStatusCodes.getAbbreviationIsNull()), HttpStatus.BAD_REQUEST);
          }
        }
        if (testParameterService.isTestConfigureAndAbbreviationExist(
            testParameterRequestDto.getTestConfigureId(),
            testParameterRequestDto.getAbbreviation())) {
          return new ResponseEntity<>(
              new ValidationFailureResponse(Constants.ABBREVIATION,
                  validationFailureStatusCodes.getAbbreviationAlreadyExist()),
              HttpStatus.BAD_REQUEST);
        }
        if (testParameterService.isDuplicateTestParameterEntryExist(
            testParameterRequestDto.getTestConfigureId(), testParameterRequestDto.getAbbreviation(),
            testParameterRequestDto.getParameterId())) {
          logger.debug("");
          return new ResponseEntity<>(
              new ValidationFailureResponse(Constants.TEST_PARAMETER,
                  validationFailureStatusCodes.getTestParameterAlreadyExist()),
              HttpStatus.BAD_REQUEST);
        }
        if (testConfigureService.isAlreadyDepended(testParameterRequestDto.getTestConfigureId())) {
          if (testParameterRequestDto.getType().equals(TestParameterType.RESULT)) {
            return new ResponseEntity<>(
                new ValidationFailureResponse(Constants.TEST_CONFIGURE,
                    validationFailureStatusCodes.getTestConfigureAlreadyDepended()),
                HttpStatus.BAD_REQUEST);
          }
        }
      }
      testParameterService
          .saveTestParameterAll(mapper.map(testParameterRequestDtoList, TestParameter.class));
      return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_CONFIGURE,
          testParameterService.checkEqutaionExistsForTest(
              testParameterRequestDtoList.get(0).getTestConfigureId()),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_PARAMETER_ID,
        validationFailureStatusCodes.getTestParameterNotExist()), HttpStatus.BAD_REQUEST);

  }

  @GetMapping(value = EndpointURI.TEST_PARAMETERS)
  public ResponseEntity<Object> getTestParameters() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_PARAMETERS, mapper
        .map(testParameterService.getAllParametersByDecending(), TestParameterResponseDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.TEST_PARAMETER_BY_ID)
  public ResponseEntity<Object> getTestParameterById(@PathVariable Long id) {
    if (testParameterService.isTestParameterExist(id)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_PARAMETER,
          mapper.map(testParameterService.getTestParameterById(id), TestParameterResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_PARAMETER_ID,
        validationFailureStatusCodes.getTestParameterNotExist()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(EndpointURI.TEST_PARAMETER_BY_ID)
  public ResponseEntity<Object> deleteTestParameter(@PathVariable Long id) {
    TestParameter testParameter = testParameterService.getTestParameterById(id);
    if (testParameterService.isTestParameterExist(id)) {
      if (testConfigureService.isAlreadyDepended(testParameter.getTestConfigure().getId())) {
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.TEST_CONFIGURE,
                validationFailureStatusCodes.getTestConfigureAlreadyDepended()),
            HttpStatus.BAD_REQUEST);
      }
      testParameterService.deleteTestParameter(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.TEST_PARAMETER_DELETED),
          HttpStatus.OK);
    }
    logger.debug("No Test Parameter record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_PARAMETER_ID,
        validationFailureStatusCodes.getTestParameterNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.TEST_PARAMETERS_BY_TEST_CONFIGURE_ID)
  public ResponseEntity<Object> getAllTestParametersByTestConfigureId(
      @PathVariable Long testConfigureId) {
    if (testParameterService.isTestConfigureIdExist(testConfigureId)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_PARAMETERS,
          mapper.map(testParameterService.getAllTestParametersByTestConfigureId(testConfigureId),
              TestParameterResponseDto.class),
          RestApiResponseStatus.OK), null, HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_CONFIGURE_ID,
        validationFailureStatusCodes.getTestConfigureNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.TEST_PARAMETER)
  public ResponseEntity<Object> updateTestParameter(
      @RequestBody TestParameterRequestDto testParameterRequestDto) {
    if (testParameterService.isTestParameterExist(testParameterRequestDto.getId())) {
      if (testConfigureService.isAlreadyDepended(testParameterRequestDto.getTestConfigureId())) {
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.TEST_CONFIGURE,
                validationFailureStatusCodes.getTestConfigureAlreadyDepended()),
            HttpStatus.BAD_REQUEST);
      }
      if (testParameterService.isAbbreviationNull(testParameterRequestDto.getAbbreviation())) {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.ABBREVIATION,
            validationFailureStatusCodes.getAbbreviationIsNull()), HttpStatus.BAD_REQUEST);
      }
      if (testParameterService.isUpdatedAbbreviationExists(testParameterRequestDto.getId(),
          testParameterRequestDto.getTestConfigureId(),
          testParameterRequestDto.getAbbreviation())) {
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.ABBREVIATION,
                validationFailureStatusCodes.getAbbreviationAlreadyExist()),
            HttpStatus.BAD_REQUEST);
      }
      if (testParameterService.isUpdatedParameterExists(testParameterRequestDto.getId(),
          testParameterRequestDto.getTestConfigureId(), testParameterRequestDto.getParameterId())) {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.PARAMETER,
            validationFailureStatusCodes.getParameterAlreadyExist()), HttpStatus.BAD_REQUEST);
      }
      if (testParameterRequestDto.getType().equals(TestParameterType.CONFIG)) {
        if (testParameterService.isValueNull(testParameterRequestDto.getValue(),
            testParameterRequestDto.getDateValue(), testParameterRequestDto.getParameterId())) {
          return new ResponseEntity<>(new ValidationFailureResponse(Constants.VALUE,
              validationFailureStatusCodes.getValueIsNull()), HttpStatus.BAD_REQUEST);
        }
      }
      if (testParameterService.checkDependForTestParameter(testParameterRequestDto.getId())) {
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.ABBREVIATION,
                validationFailureStatusCodes.getTestParameterAlreadyDepended()),
            HttpStatus.BAD_REQUEST);
      }
      testParameterService
          .saveTestParameter(mapper.map(testParameterRequestDto, TestParameter.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_TEST_PARAMETER_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_PARAMETER_ID,
        validationFailureStatusCodes.getTestParameterNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.SEARCH_TEST_PARAMETER)
  public ResponseEntity<Object> getTestParameterSearch(
      @QuerydslPredicate(root = TestParameter.class) Predicate predicate,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_PARAMETERS,
        testParameterService.searchTestParameter(predicate, size, page), RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.TEST_PARAMETER_BY_TEST_CONFIGURE)
  public ResponseEntity<Object> getTestParameterByTestConfigure(
      @PathVariable Long testConfigureId) {
    if (testParameterService.isTestConfigureIdExist(testConfigureId)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_PARAMETERS,
          mapper.map(testParameterService.getTestParameterEquation(testConfigureId),
              TestParameterEquationDto.class),
          RestApiResponseStatus.OK), null, HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_CONFIGURE_ID,
        validationFailureStatusCodes.getTestConfigureNotExist()), HttpStatus.BAD_REQUEST);
  }
}

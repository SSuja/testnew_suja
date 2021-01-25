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
import com.tokyo.supermix.data.dto.AcceptedValueRequestDto;
import com.tokyo.supermix.data.dto.AcceptedValueResponseDto;
import com.tokyo.supermix.data.entities.AcceptedValue;
import com.tokyo.supermix.data.enums.Condition;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.AcceptedValueService;
import com.tokyo.supermix.server.services.MaterialTestService;
import com.tokyo.supermix.server.services.TestConfigureService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class AcceptedValueController {
  @Autowired
  private AcceptedValueService acceptedValueService;
  @Autowired
  private TestConfigureService testService;
  @Autowired
  private MaterialTestService materialTestService;
  @Autowired
  ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  private static final Logger logger = Logger.getLogger(AcceptedValueController.class);

  @PostMapping(value = EndpointURI.ACCEPTED_VALUE)
  public ResponseEntity<Object> createAcceptedValue(
      @Valid @RequestBody AcceptedValueRequestDto acceptedValueRequestDto) {
    if (acceptedValueService.isAcceptedValueByTestConfigureIdAndTestParameter(
        acceptedValueRequestDto.getTestConfigureId(),
        acceptedValueRequestDto.getTestParameterId())) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.ACCEPTED_VALUE,
          validationFailureStatusCodes.getAcceptedValueAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    if (acceptedValueService
        .isCheckValidation(mapper.map(acceptedValueRequestDto, AcceptedValue.class))) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.ACCEPTED_VALUE,
          validationFailureStatusCodes.getAcceptedValueNotNull()), HttpStatus.BAD_REQUEST);
    }
    acceptedValueService
        .saveAcceptedValue(mapper.map(acceptedValueRequestDto, AcceptedValue.class));
    acceptedValueService.upDateTesConfigureType(acceptedValueRequestDto.getTestConfigureId());
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_ACCEPTED_VALUE_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.ACCEPTED_VALUES)
  public ResponseEntity<Object> getAllAcceptedValues() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.ACCEPTED_VALUES,
        mapper.map(acceptedValueService.getAllAcceptedValues(), AcceptedValueResponseDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.ACCEPTED_VALUE_BY_ID)
  public ResponseEntity<Object> getAcceptedValueById(@PathVariable Long id) {
    if (acceptedValueService.isAcceptedValueExist(id)) {
      logger.debug("Get AcceptedValue by id ");
      return new ResponseEntity<>(new ContentResponse<>(Constants.ACCEPTED_VALUE,
          mapper.map(acceptedValueService.getAcceptedValueById(id), AcceptedValueResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.ACCEPTED_VALUE_ID,
        validationFailureStatusCodes.getAcceptedValueNotExist()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(value = EndpointURI.ACCEPTED_VALUE_BY_ID)
  public ResponseEntity<Object> deleteAcceptedValue(@PathVariable Long id) {
    if (acceptedValueService.isAcceptedValueExist(id)) {
      acceptedValueService.deleteAcceptedValue(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.ACCEPTED_VALUE_DELETED),
          HttpStatus.OK);
    }
    logger.debug("invalid acceptedValueId");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.ACCEPTED_VALUE_ID,
        validationFailureStatusCodes.getAcceptedValueNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.ACCEPTED_VALUE)
  public ResponseEntity<Object> updateAcceptedValue(
      @Valid @RequestBody AcceptedValueRequestDto acceptedValueRequestDto) {
    if (acceptedValueService.isAcceptedValueExist(acceptedValueRequestDto.getId())) {
      if (materialTestService
          .isMaterialTestByTestConfigureExists(acceptedValueRequestDto.getTestConfigureId())) {
        return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
            Constants.ACCEPTED_VALUE_ALREADY_DEPENDED), HttpStatus.OK);
      } else {
        acceptedValueService
            .saveAcceptedValue(mapper.map(acceptedValueRequestDto, AcceptedValue.class));
        acceptedValueService.upDateTesConfigureType(acceptedValueRequestDto.getTestConfigureId());
        return new ResponseEntity<>(
            new BasicResponse<>(RestApiResponseStatus.OK, Constants.ACCEPTED_VALUE_UPDATE_SUCCESS),
            HttpStatus.OK);
      }
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.ACCEPTED_VALUE,
        validationFailureStatusCodes.getAcceptedValueNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.GET_ACCEPTED_VALUE_BY_TEST_CONFIGURE_ID)
  public ResponseEntity<Object> getAcceptedValueByTestId(@PathVariable Long testConfigureId) {
    if (testService.isTestConfigureExist(testConfigureId)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_CONFIGURE,
          mapper.map(acceptedValueService.findByTestConfigure(testConfigureId),
              AcceptedValueResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    } else {
      logger.debug("No AcceptedValue record exist for given Test type id");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_CONFIGURE_ID,
          validationFailureStatusCodes.getAcceptedValueNotExist()), HttpStatus.BAD_REQUEST);
    }
  }

  // @GetMapping(value = EndpointURI.SEARCH_ACCEPTED_VALUE)
  // public ResponseEntity<Object> getAcceptedValueSearch(
  // @QuerydslPredicate(root = AcceptedValue.class) Predicate predicate,
  // @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
  // return new ResponseEntity<>(new ContentResponse<>(Constants.ACCEPTED_VALUES,
  // acceptedValueService.searchAcceptedValue(predicate, size, page), RestApiResponseStatus.OK),
  // null, HttpStatus.OK);
  // }

  @GetMapping(value = EndpointURI.SEARCH_ACCEPTED_VALUE)
  public ResponseEntity<Object> searchAcceptedValues(@PathVariable Long testConfigId,
      @RequestParam(name = "testParamName", required = false) String testParamName,
      @RequestParam(name = "condition", required = false) Condition condition,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(0, 0, totalpage, 0l);
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    return new ResponseEntity<>(new PaginatedContentResponse<>(Constants.ACCEPTED_VALUES,
        acceptedValueService.searchAcceptedValue(testConfigId, testParamName, condition,
            booleanBuilder, totalpage, size, pageable, pagination),
        RestApiResponseStatus.OK, pagination), null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.GET_ACCEPTED_VALUE_DTO_BY_TEST_CONFIGURE_ID)
  public ResponseEntity<Object> getAcceptedValueByTestIdNew(@PathVariable Long testConfigureId) {
    if (acceptedValueService.existsAcceptedValueByTestConfigureId(testConfigureId)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_CONFIGURE,
          acceptedValueService.findByTestConfigureId(testConfigureId), RestApiResponseStatus.OK),
          HttpStatus.OK);
    } else {
      logger.debug("No AcceptedValue record exist for given Test type id");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_CONFIGURE_ID,
          validationFailureStatusCodes.getAcceptedValueNotExist()), HttpStatus.BAD_REQUEST);
    }
  }
}

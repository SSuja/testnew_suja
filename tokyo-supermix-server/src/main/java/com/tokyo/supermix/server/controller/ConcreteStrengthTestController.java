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
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.ConcreteStrengthTestRequestDto;
import com.tokyo.supermix.data.dto.ConcreteStrengthTestResponseDto;
import com.tokyo.supermix.data.entities.ConcreteStrengthTest;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.ConcreteStrengthTestService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class ConcreteStrengthTestController {
  @Autowired
  private Mapper mapper;
  @Autowired
  ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  ConcreteStrengthTestService concreteStrengthTestService;
  private static final Logger logger = Logger.getLogger(ConcreteStrengthTestController.class);

  @GetMapping(value = EndpointURI.CONCRETE_STRENGTH_TESTS)
  public ResponseEntity<Object> getAllConcreteStrengthTests() {
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.CONCRETE_STRENGTH_TESTS,
            mapper.map(concreteStrengthTestService.getAllConcreteStrengthTests(),
                ConcreteStrengthTestResponseDto.class),
            RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }

  @PostMapping(value = EndpointURI.CONCRETE_STRENGTH_TEST)
  public ResponseEntity<Object> saveConcreteStrengthTest(
      @Valid @RequestBody ConcreteStrengthTestRequestDto concreteStrengthTestRequestDto) {
    if (concreteStrengthTestService
        .checkConcreteAge(concreteStrengthTestRequestDto.getConcreteAge())) {
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.CONCRETE_STRENGTH_TESTS_CONCRETE_AGE,
              validationFailureStatusCodes.getConcreteStrengthTestConcreteAgeNotValid()),
          HttpStatus.BAD_REQUEST);
    }
    concreteStrengthTestService.saveConcreteStrengthTest(
        mapper.map(concreteStrengthTestRequestDto, ConcreteStrengthTest.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_CONCRETE_STRENGTH_TEST_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.CONCRETE_STRENGTH_TEST_BY_ID)
  public ResponseEntity<Object> getConcreteStrengthTestById(@PathVariable Long id) {
    if (concreteStrengthTestService.isConcreteStrengthTestExist(id)) {
      logger.debug("Get ConcreteStrengthTest By Id");
      return new ResponseEntity<>(new ContentResponse<>(Constants.CONCRETE_STRENGTH_TEST,
          mapper.map(concreteStrengthTestService.getConcreteStrengthTestById(id),
              ConcreteStrengthTestResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(
        new ValidationFailureResponse(Constants.CONCRETE_STRENGTH_TEST_ID,
            validationFailureStatusCodes.getConcreteStrengthTestNotExist()),
        HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(value = EndpointURI.CONCRETE_STRENGTH_TEST_BY_ID)
  public ResponseEntity<Object> deleteConcreteStrengthTest(@PathVariable Long id) {
    if (concreteStrengthTestService.isConcreteStrengthTestExist(id)) {
      concreteStrengthTestService.deleteConcreteStrengthTest(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.CONCRETE_STRENGTH_TEST_DELETED),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(
        new ValidationFailureResponse(Constants.CONCRETE_STRENGTH_TEST_ID,
            validationFailureStatusCodes.getConcreteStrengthTestNotExist()),
        HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.CONCRETE_STRENGTH_TEST)
  public ResponseEntity<Object> updateConcreteStrengthTest(
      @Valid @RequestBody ConcreteStrengthTestRequestDto concreteStrengthTestRequestDto) {
    if (concreteStrengthTestService
        .isConcreteStrengthTestExist(concreteStrengthTestRequestDto.getId())) {
      if (concreteStrengthTestService
          .checkConcreteAge(concreteStrengthTestRequestDto.getConcreteAge())) {
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.CONCRETE_STRENGTH_TESTS_CONCRETE_AGE,
                validationFailureStatusCodes.getConcreteStrengthTestConcreteAgeNotValid()),
            HttpStatus.BAD_REQUEST);
      }
      concreteStrengthTestService.saveConcreteStrengthTest(
          mapper.map(concreteStrengthTestRequestDto, ConcreteStrengthTest.class));
      return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
          Constants.UPDATE_CONCRETE_STRENGTH_TEST_SUCCESS), HttpStatus.OK);
    }
    return new ResponseEntity<>(
        new ValidationFailureResponse(Constants.CONCRETE_STRENGTH_TEST_ID,
            validationFailureStatusCodes.getConcreteStrengthTestNotExist()),
        HttpStatus.BAD_REQUEST);
  }
}

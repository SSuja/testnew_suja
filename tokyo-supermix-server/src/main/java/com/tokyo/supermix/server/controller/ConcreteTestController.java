package com.tokyo.supermix.server.controller;

import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.tokyo.supermix.data.dto.ConcreteTestRequestDto;
import com.tokyo.supermix.data.dto.ConcreteTestResponseDto;
import com.tokyo.supermix.data.entities.ConcreteTest;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.ConcreteTestService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class ConcreteTestController {
  @Autowired
  private Mapper mapper;
  @Autowired
  private ConcreteTestService concreteTestService;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  private static final Logger logger = Logger.getLogger(ConcreteTestController.class);

  @PostMapping(value = EndpointURI.CONCRETE_TEST)
  @PreAuthorize("hasAuthority('add_concrete_test')")
  public ResponseEntity<Object> createConcreteTest(
      @Valid @RequestBody ConcreteTestRequestDto ConcreteTestRequestDto) {
    if (concreteTestService.isNameExists(ConcreteTestRequestDto.getName())) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.CONCRETE_TEST_NAME,
          validationFailureStatusCodes.getConcreteTestAlreadyExists()), HttpStatus.BAD_REQUEST);
    }
    concreteTestService.saveConcreteTest(mapper.map(ConcreteTestRequestDto, ConcreteTest.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_CONCRETE_TEST_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.CONCRETE_TESTS)
  @PreAuthorize("hasAuthority('get_concrete_test')")
  public ResponseEntity<Object> getAllConcreteTest() {
    return new ResponseEntity<Object>(new ContentResponse<>(Constants.CONCRETE_TESTS,
        mapper.map(concreteTestService.getAllConcreteTests(), ConcreteTestResponseDto.class),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.CONCRETE_TEST_BY_ID)
  public ResponseEntity<Object> getConcreteTestById(@PathVariable Long id) {
    if (concreteTestService.isConcreteTestExists(id)) {
      logger.debug("Get ConcreteTest by id ");
      return new ResponseEntity<>(new ContentResponse<>(Constants.CONCRETE_TEST,
          mapper.map(concreteTestService.getConcreteTestById(id), ConcreteTestResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.CONCRETE_TEST,
        validationFailureStatusCodes.getConcreteTestNotExist()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(value = EndpointURI.CONCRETE_TEST_BY_ID)
  @PreAuthorize("hasAuthority('delete_concrete_test')")
  public ResponseEntity<Object> deleteConcreteTest(@PathVariable Long id) {
    if (concreteTestService.isConcreteTestExists(id)) {
      concreteTestService.deleteConcreteTest(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.CONCRETE_TEST_DELETED),
          HttpStatus.OK);
    }
    logger.debug("invalid ConcreteTestId");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.CONCRETE_TEST,
        validationFailureStatusCodes.getConcreteTestNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.CONCRETE_TEST)
  @PreAuthorize("hasAuthority('edit_concrete_test')")
  public ResponseEntity<Object> updateConcreteTest(
      @Valid @RequestBody ConcreteTestRequestDto concreteTestRequestDto) {
    if (concreteTestService.isConcreteTestExists(concreteTestRequestDto.getId())) {
      concreteTestService.saveConcreteTest(mapper.map(concreteTestRequestDto, ConcreteTest.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_CONCRETE_TEST_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.CONCRETE_TEST,
        validationFailureStatusCodes.getConcreteTestNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.SEARCH_CONCRETE_TEST)
  public ResponseEntity<Object> getConcreteTestSearch(
      @QuerydslPredicate(root = ConcreteTest.class) Predicate predicate,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.CONCRETE_TESTS,
        concreteTestService.searchConcreteTest(predicate, size, page), RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }
}

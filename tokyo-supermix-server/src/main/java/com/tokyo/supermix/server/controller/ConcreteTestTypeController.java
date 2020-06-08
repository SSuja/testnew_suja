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
import com.tokyo.supermix.data.dto.ConcreteTestTypeDto;
import com.tokyo.supermix.data.entities.ConcreteTestType;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.ConcreteTestTypeService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class ConcreteTestTypeController {
  @Autowired
  private Mapper mapper;
  @Autowired
  private ConcreteTestTypeService concreteTestTypeService;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  private static final Logger logger = Logger.getLogger(ConcreteTestTypeController.class);

  @PostMapping(value = EndpointURI.CONCRETE_TEST_TYPE)
  @PreAuthorize("hasAuthority('add_concrete_test_type')")
  public ResponseEntity<Object> createConcreteTestType(
      @Valid @RequestBody ConcreteTestTypeDto ConcreteTestTypeDto) {
    if (concreteTestTypeService.isTypeExists(ConcreteTestTypeDto.getType())) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.CONCRETE_TEST_TYPE_TYPE,
          validationFailureStatusCodes.getTestTypealreadyExists()), HttpStatus.BAD_REQUEST);
    }
    concreteTestTypeService
        .saveConcreteTestType(mapper.map(ConcreteTestTypeDto, ConcreteTestType.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_CONCRETE_TEST_TYPE_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.CONCRETE_TEST_TYPES)
  @PreAuthorize("hasAuthority('get_concrete_test_type')")
  public ResponseEntity<Object> getAllConcreteTestTypes() {
    return new ResponseEntity<Object>(new ContentResponse<>(Constants.CONCRETE_TEST_TYPES,
        mapper.map(concreteTestTypeService.getAllConcreteTestTypes(), ConcreteTestTypeDto.class),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.CONCRETE_TEST_TYPE_BY_ID)
  public ResponseEntity<Object> getConcreteTestTypeById(@PathVariable Long id) {
    if (concreteTestTypeService.isConcreteTestTypeExists(id)) {
      logger.debug("Get ConcreteTest by id ");
      return new ResponseEntity<>(new ContentResponse<>(
          Constants.CONCRETE_TEST_TYPE, mapper
              .map(concreteTestTypeService.getConcreteTestTypeById(id), ConcreteTestTypeDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.CONCRETE_TEST_TYPE,
        validationFailureStatusCodes.getConcreteTestTypeNotExist()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(value = EndpointURI.CONCRETE_TEST_TYPE_BY_ID)
  @PreAuthorize("hasAuthority('delete_concrete_test_type')")
  public ResponseEntity<Object> deleteConcreteTestType(@PathVariable Long id) {
    if (concreteTestTypeService.isConcreteTestTypeExists(id)) {
      concreteTestTypeService.deleteConcreteTestType(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.CONCRETE_TEST_TYPE_DELETED),
          HttpStatus.OK);
    }
    logger.debug("invalid ConcreteTestTypeId");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.CONCRETE_TEST_TYPE,
        validationFailureStatusCodes.getConcreteTestTypeNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.CONCRETE_TEST_TYPE)
  @PreAuthorize("hasAuthority('edit_concrete_test_type')")
  public ResponseEntity<Object> updateConcreteTestType(
      @Valid @RequestBody ConcreteTestTypeDto concreteTestTypeDto) {
    if (concreteTestTypeService.isConcreteTestTypeExists(concreteTestTypeDto.getId())) {
      concreteTestTypeService
          .saveConcreteTestType(mapper.map(concreteTestTypeDto, ConcreteTestType.class));
      return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
          Constants.UPDATE_CONCRETE_TEST_TYPE_SUCCESS), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.CONCRETE_TEST_TYPE,
        validationFailureStatusCodes.getConcreteTestTypeNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.SEARCH_CONCRETE_TEST_TYPE)
  public ResponseEntity<Object> getConcreteTestTypeSearch(
      @QuerydslPredicate(root = ConcreteTestType.class) Predicate predicate,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.CONCRETE_TEST_TYPES,
        concreteTestTypeService.searchConcreteTestType(predicate, size, page),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }
}

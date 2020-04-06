package com.tokyo.supermix.server.controller;

import java.util.List;
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
import com.tokyo.supermix.data.dto.ConcreteTestElementRequestDto;
import com.tokyo.supermix.data.dto.ConcreteTestElementResponseDto;
import com.tokyo.supermix.data.entities.ConcreteTestElement;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.ConcreteTestElementService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin("*")
public class ConcreteTestElementController {
  @Autowired
  private Mapper mapper;
  @Autowired
  private ConcreteTestElementService concreteTestElementService;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  private static final Logger logger = Logger.getLogger(ConcreteTestElementController.class);

  // post API for ConcreteTestElement
  @PostMapping(value = EndpointURI.CONCRETE_TEST_ELEMENT)
  public ResponseEntity<Object> createConcreteTestElement(
      @Valid @RequestBody ConcreteTestElementRequestDto ConcreteTestElementRequestDto) {
    if (concreteTestElementService
        .isConcreteTestElementNameExists(ConcreteTestElementRequestDto.getName())) {
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.CONCRETE_TEST_ELEMENT_NAME,
              validationFailureStatusCodes.getConcreteTestElementAlreadyExist()),
          HttpStatus.BAD_REQUEST);
    }
    if (concreteTestElementService
        .isConcreteTestElementAbbreviationExists(ConcreteTestElementRequestDto.getAbbreviation())) {
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.CONCRETE_TEST_ELEMENT_ABBREVIATION,
              validationFailureStatusCodes.getConcreteTestElementAlreadyExist()),
          HttpStatus.BAD_REQUEST);
    }
    concreteTestElementService.saveConcreteTestElement(
        mapper.map(ConcreteTestElementRequestDto, ConcreteTestElement.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_CONCRETE_TEST_ELEMENT_SUCCESS),
        HttpStatus.OK);

  }

  // get all ConcreteTestElement
  @GetMapping(value = EndpointURI.CONCRETE_TEST_ELEMENTS)
  public ResponseEntity<Object> getAllConcreteTestElements() {
    List<ConcreteTestElement> ConcreteTestElementList =
        concreteTestElementService.getAllConcreteTestElement();
    return new ResponseEntity<Object>(new ContentResponse<>(Constants.CONCRETE_TEST_ELEMENTS,
        mapper.map(ConcreteTestElementList, ConcreteTestElementResponseDto.class),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }

  // get ConcreteTestElement by id
  @GetMapping(value = EndpointURI.CONCRETE_TEST_ELEMENT_BY_ID)
  public ResponseEntity<Object> getConcreteTestElementById(@PathVariable Long id) {
    if (concreteTestElementService.isConcreteTestElementExists(id)) {
      logger.debug("Get ConcreteTestElement by id ");
      ConcreteTestElement ConcreteTestElement =
          concreteTestElementService.getConcreteTestElementById(id);
      return new ResponseEntity<>(new ContentResponse<>(Constants.CONCRETE_TEST_ELEMENT,
          mapper.map(ConcreteTestElement, ConcreteTestElementResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("invalid");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.CONCRETE_TEST_ELEMENT_ID,
        validationFailureStatusCodes.getConcreteTestElementNotExist()), HttpStatus.BAD_REQUEST);
  }

  // DELETE API for ConcreteTestElement
  @DeleteMapping(value = EndpointURI.CONCRETE_TEST_ELEMENT_BY_ID)
  public ResponseEntity<Object> deleteConcreteTestElement(@PathVariable Long id) {
    if (concreteTestElementService.isConcreteTestElementExists(id)) {
      concreteTestElementService.deleteConcreteTestElement(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.CONCRETE_TEST_ELEMENT_DELETED),
          HttpStatus.OK);
    }
    logger.debug("invalid ConcreteTestElement");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.CONCRETE_TEST_ELEMENT_ID,
        validationFailureStatusCodes.getConcreteTestElementNotExist()), HttpStatus.BAD_REQUEST);
  }

  // update API for ConcreteTestElement
  @PutMapping(value = EndpointURI.CONCRETE_TEST_ELEMENT)
  public ResponseEntity<Object> updateConcreteTestElement(
      @Valid @RequestBody ConcreteTestElementRequestDto ConcreteTestElementRequestDto) {
    if (concreteTestElementService
        .isConcreteTestElementExists(ConcreteTestElementRequestDto.getId())) {
      if (concreteTestElementService.isUpdateConcreteTestElementNameExists(
          ConcreteTestElementRequestDto.getId(), ConcreteTestElementRequestDto.getName())) {
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.CONCRETE_TEST_ELEMENT_NAME,
                validationFailureStatusCodes.getConcreteTestElementAlreadyExist()),
            HttpStatus.BAD_REQUEST);
      }
      if (concreteTestElementService.isUpdateConcreteTestElementAbbreviationExists(
          ConcreteTestElementRequestDto.getId(), ConcreteTestElementRequestDto.getAbbreviation())) {
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.CONCRETE_TEST_ELEMENT_ABBREVIATION,
                validationFailureStatusCodes.getConcreteTestElementAlreadyExist()),
            HttpStatus.BAD_REQUEST);
      }
      concreteTestElementService.saveConcreteTestElement(
          mapper.map(ConcreteTestElementRequestDto, ConcreteTestElement.class));
      return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
          Constants.UPDATE_CONCRETE_TEST_ELEMENT_SUCCESS), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.CONCRETE_TEST_ELEMENT_ID,
        validationFailureStatusCodes.getEmployeeNotExist()), HttpStatus.BAD_REQUEST);
  }
}

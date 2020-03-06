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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.AcceptedValueRequestDto;
import com.tokyo.supermix.data.dto.AcceptedValueResponseDto;
import com.tokyo.supermix.data.dto.DesignationDto;
import com.tokyo.supermix.data.entities.AcceptedValue;
import com.tokyo.supermix.data.entities.ConcreteTest;
import com.tokyo.supermix.data.entities.Designation;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.AcceptedValueService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class AcceptedValueController {
  @Autowired
  private AcceptedValueService acceptedValueService;
  @Autowired
  ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  private static final Logger logger = Logger.getLogger(AcceptedValueController.class);

  // get all acceptedValues
  @GetMapping(value = EndpointURI.ACCEPTED_VALUES)
  public ResponseEntity<Object> getAllAcceptedValues() {
    List<AcceptedValue> acceptedValueList = acceptedValueService.getAllAcceptedValues();
    List<AcceptedValueResponseDto> acceptedValueResponseDtoList =
        mapper.map(acceptedValueList, AcceptedValueResponseDto.class);
    return new ResponseEntity<>(new ContentResponse<>(Constants.ACCEPTED_VALUES,
        acceptedValueResponseDtoList, RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  // get acceptedValue by id
  @GetMapping(value = EndpointURI.ACCEPTED_VALUE_BY_ID)
  public ResponseEntity<Object> getAcceptedValueById(@PathVariable Long id) {
    if (acceptedValueService.isAcceptedValueExist(id)) {
      logger.debug("Get AcceptedValue by id ");
      AcceptedValue acceptedValue = acceptedValueService.getAcceptedValueById(id);
      return new ResponseEntity<>(
          new ContentResponse<>(Constants.ACCEPTED_VALUE,
              mapper.map(acceptedValue, DesignationDto.class), RestApiResponseStatus.OK),
          HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.ACCEPTED_VALUE_ID,
        validationFailureStatusCodes.getAcceptedValueNotExist()), HttpStatus.BAD_REQUEST);
  }

  // get acceptedValue Delete
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

  @PostMapping(value = EndpointURI.ACCEPTED_VALUE)
  public ResponseEntity<Object> createAcceptedValue(
      @Valid @RequestBody AcceptedValueRequestDto acceptedValueRequestDto) {
    acceptedValueService
        .saveAcceptedValue(mapper.map(acceptedValueRequestDto, AcceptedValue.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_ACCEPTED_VALUE_SUCCESS),
        HttpStatus.OK);
  }
}

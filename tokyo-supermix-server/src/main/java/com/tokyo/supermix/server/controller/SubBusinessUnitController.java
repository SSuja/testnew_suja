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
import com.tokyo.supermix.data.dto.SubBusinessUnitRequestDto;
import com.tokyo.supermix.data.dto.SubBusinessUnitResponseDto;
import com.tokyo.supermix.data.entities.SubBusinessUnit;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.SubBusinessUnitService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin
public class SubBusinessUnitController {

  @Autowired
  SubBusinessUnitService subBusinessUnitService;

  @Autowired
  ValidationFailureStatusCodes validationFailureStatusCodes;

  @Autowired
  private Mapper mapper;

  private static final Logger logger = Logger.getLogger(SubBusinessUnitController.class);

  @GetMapping(value = EndpointURI.SUB_BUSINESS_UNITS)
  public ResponseEntity<Object> getAllSubBusinessUnits() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.SUB_BUSINESS_UNITS, mapper
        .map(subBusinessUnitService.getAllSubBusinessUnits(), SubBusinessUnitResponseDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.SUB_BUSINESS_UNIT_BY_ID)
  public ResponseEntity<Object> getSubBusinessUnitById(@PathVariable Long id) {
    if (subBusinessUnitService.isSubBusinessUnitExist(id)) {
      logger.debug("Get SubBusinessUnit by id ");
      return new ResponseEntity<>(new ContentResponse<>(Constants.SUB_BUSINESS_UNIT, mapper
          .map(subBusinessUnitService.getSubBusinessUnitById(id), SubBusinessUnitResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.SUB_BUSINESS_UNIT,
        validationFailureStatusCodes.getSubBusinessUnitNotExist()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(value = EndpointURI.SUB_BUSINESS_UNIT_BY_ID)
  public ResponseEntity<Object> deleteSubBusinessUnit(@PathVariable Long id) {
    if (subBusinessUnitService.isSubBusinessUnitExist(id)) {
      subBusinessUnitService.deleteSubBusinessUnit(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.SUB_BUSINESS_UNIT_DELETED),
          HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.SUB_BUSINESS_UNIT,
        validationFailureStatusCodes.getSubBusinessUnitNotExist()), HttpStatus.BAD_REQUEST);

  }

  @PostMapping(value = EndpointURI.SUB_BUSINESS_UNIT)
  public ResponseEntity<Object> createSubBusinessUnit(
      @Valid @RequestBody SubBusinessUnitRequestDto subBusinessUnitDto) {
    if (subBusinessUnitService.isSubBusinessUnitExist(subBusinessUnitDto.getName())) {
      logger.debug("Already exists");
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.SUB_BUSINESS_UNIT_NAME,
              validationFailureStatusCodes.getSubBusinessUnitAlreadyExist()),
          HttpStatus.BAD_REQUEST);
    }
    subBusinessUnitService
        .saveSubBusinessUnit(mapper.map(subBusinessUnitDto, SubBusinessUnit.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_SUB_BUSINESS_UNIT_SUCCESS),
        HttpStatus.OK);
  }

  @PutMapping(value = EndpointURI.SUB_BUSINESS_UNIT)
  public ResponseEntity<Object> updateSubBusinessUnit(
      @Valid @RequestBody SubBusinessUnitRequestDto subBusinessUnitDto) {
    if (subBusinessUnitService.isSubBusinessUnitExist(subBusinessUnitDto.getId())) {
      if (subBusinessUnitService.isUpdatedSubBusinessUnitNameExist(subBusinessUnitDto.getId(),
          subBusinessUnitDto.getName())) {
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.SUB_BUSINESS_UNIT_NAME,
                validationFailureStatusCodes.getSubBusinessUnitAlreadyExist()),
            HttpStatus.BAD_REQUEST);
      }
      subBusinessUnitService
          .saveSubBusinessUnit(mapper.map(subBusinessUnitDto, SubBusinessUnit.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_SUB_BUSINESS_UNIT_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.SUB_BUSINESS_UNIT_NAME,
        validationFailureStatusCodes.getSubBusinessUnitNotExist()), HttpStatus.BAD_REQUEST);
  }
}

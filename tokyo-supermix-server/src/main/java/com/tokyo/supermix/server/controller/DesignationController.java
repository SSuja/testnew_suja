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
import org.springframework.security.access.prepost.PreAuthorize;


import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.DesignationDto;
import com.tokyo.supermix.data.entities.Designation;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.DesignationService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin
public class DesignationController {

  @Autowired
  DesignationService designationService;

  @Autowired
  ValidationFailureStatusCodes validationFailureStatusCodes;

  @Autowired
  private Mapper mapper;

  private static final Logger logger = Logger.getLogger(DesignationController.class);

  // get all designations
  @GetMapping(value = EndpointURI.DESIGNATIONS)
  @PreAuthorize("hasAuthority('get_designation')")
  public ResponseEntity<Object> getAllDesignations() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.DESIGNATIONS,
        mapper.map(designationService.getAllDesignations(), DesignationDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  // get designation by id
  @GetMapping(value = EndpointURI.GET_DESIGNATION_BY_ID)
  public ResponseEntity<Object> getDesignationById(@PathVariable Long id) {
    if (designationService.isDesignationExist(id)) {
      logger.debug("Get Designation by id ");
      return new ResponseEntity<>(new ContentResponse<>(Constants.DESIGNATION,
          mapper.map(designationService.getDesignationById(id), DesignationDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.DESIGNATION,
        validationFailureStatusCodes.getDesignationNotExist()), HttpStatus.BAD_REQUEST);

  }

  // delete api for designation
  @DeleteMapping(value = EndpointURI.DELETE_DESIGNATION_BY_ID)
  @PreAuthorize("hasAuthority('delete_designation')")
  public ResponseEntity<Object> deleteDesignation(@PathVariable Long id) {
    if (designationService.isDesignationExist(id)) {
      designationService.deleteDesignation(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.DESIGNATION_DELETED),
          HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.DESIGNATION,
        validationFailureStatusCodes.getDesignationNotExist()), HttpStatus.BAD_REQUEST);

  }

  // post API for designation
  @PostMapping(value = EndpointURI.DESIGNATION)
  @PreAuthorize("hasAuthority('add_designation')")
  public ResponseEntity<Object> createDesignation(
      @Valid @RequestBody DesignationDto designationDto) {
    if (designationService.isDesignationExist(designationDto.getName())) {
      logger.debug("Designation already exists: createDesignation(), designationName: {}");
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.DESIGNATION_NAME,
              validationFailureStatusCodes.getDesignationNameAlreadyExist()),
          HttpStatus.BAD_REQUEST);
    }
    designationService.saveDesignation(mapper.map(designationDto, Designation.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_DESIGNATION_SUCCESS),
        HttpStatus.OK);

  }

  // update API for designations
  @PutMapping(value = EndpointURI.DESIGNATION)
  @PreAuthorize("hasAuthority('edit_designation')")
  public ResponseEntity<Object> updateDesignation(
      @Valid @RequestBody DesignationDto designationDto) {

    if (designationService.isDesignationExist(designationDto.getId())) {
      if (designationService.isUpdatedDesignationNameExist(designationDto.getId(),
          designationDto.getName())) {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.DESIGNATION_NAME,
            validationFailureStatusCodes.getDesignationAlreadyExist()), HttpStatus.BAD_REQUEST);
      }
      designationService.saveDesignation(mapper.map(designationDto, Designation.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_DESIGNATION_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.DESIGNATION_NAME,
        validationFailureStatusCodes.getDesignationNotExist()), HttpStatus.BAD_REQUEST);
  }
}

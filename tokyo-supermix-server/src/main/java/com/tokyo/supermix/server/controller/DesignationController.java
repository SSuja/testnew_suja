package com.tokyo.supermix.server.controller;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.tokyo.supermix.data.dto.DesignationDto;
import com.tokyo.supermix.data.entities.Designation;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
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

  private static final Logger logger = LoggerFactory.getLogger(DesignationController.class);

  // get all designations
  @GetMapping(value = EndpointURI.DESIGNATIONS)
  public ResponseEntity<Object> getAllDesignations() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.DESIGNATIONS,
        mapper.map(designationService.getAllDesignations(), DesignationDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  // get designation by id
  @GetMapping(value = EndpointURI.DESIGNATION_BY_ID)
  public ResponseEntity<Object> getDesignationById(@PathVariable Long id) {
    if (designationService.isDesignationExist(id)) {
      logger.info("Get Designation by id ");
      return new ResponseEntity<>(new ContentResponse<>(Constants.DESIGNATION,
          mapper.map(designationService.getDesignationById(id), DesignationDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.info("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.DESIGNATION,
        validationFailureStatusCodes.getDesignationNotExist()), HttpStatus.BAD_REQUEST);
  }

  // delete api for designation
  @DeleteMapping(value = EndpointURI.DESIGNATION_BY_ID)
  public ResponseEntity<Object> deleteDesignation(@PathVariable Long id) {
    if (designationService.isDesignationExist(id)) {
      designationService.deleteDesignation(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.DESIGNATION_DELETED),
          HttpStatus.OK);
    }
    logger.info("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.DESIGNATION,
        validationFailureStatusCodes.getDesignationNotExist()), HttpStatus.BAD_REQUEST);
  }

  // post API for designation
  @PostMapping(value = EndpointURI.DESIGNATION)
  public ResponseEntity<Object> createDesignation(
      @Valid @RequestBody DesignationDto designationDto) {
    if (designationService.isDesignationExist(designationDto.getName())) {
      logger.info("Designation already exists: createDesignation(), designationName: {}");
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

  @GetMapping(value = EndpointURI.DESIGNATION_PAGEABLE)
  public ResponseEntity<Object> getAllDesignation(@RequestParam(name = "page") int page,
      @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(page, size, totalpage, 0l);
    pagination.setTotalRecords(designationService.getCountDesignation());
    return new ResponseEntity<>(new PaginatedContentResponse<>(Constants.DESIGNATIONS,
        mapper.map(designationService.getAllDesignation(pageable), DesignationDto.class),
        RestApiResponseStatus.OK, pagination), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.SEARCH_DESIGNATION)
  public ResponseEntity<Object> getDesignationSearch(
      @RequestParam(name = "name", required = false) String name,
      @RequestParam(name = "description", required = false) String description,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(0, 0, totalpage, 0l);
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    return new ResponseEntity<>(new PaginatedContentResponse<>(Constants.DESIGNATIONS,
        designationService.searchDesignation(booleanBuilder, name, description, pageable,
            pagination),
        RestApiResponseStatus.OK, pagination), HttpStatus.OK);
  }
}

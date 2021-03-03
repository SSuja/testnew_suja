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
import com.tokyo.supermix.data.dto.SubBusinessUnitRequestDto;
import com.tokyo.supermix.data.dto.SubBusinessUnitResponseDto;
import com.tokyo.supermix.data.entities.SubBusinessUnit;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
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
  private static final Logger logger = LoggerFactory.getLogger(SubBusinessUnitController.class);

  @GetMapping(value = EndpointURI.SUB_BUSINESS_UNITS)
  public ResponseEntity<Object> getAllSubBusinessUnits() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.SUB_BUSINESS_UNITS, mapper
        .map(subBusinessUnitService.getAllSubBusinessUnits(), SubBusinessUnitResponseDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.SUB_BUSINESS_UNIT_BY_ID)
  public ResponseEntity<Object> getSubBusinessUnitById(@PathVariable Long id) {
    if (subBusinessUnitService.isSubBusinessUnitExist(id)) {
      logger.info("Get SubBusinessUnit by id ");
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
    logger.info("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.SUB_BUSINESS_UNIT,
        validationFailureStatusCodes.getSubBusinessUnitNotExist()), HttpStatus.BAD_REQUEST);

  }

  @PostMapping(value = EndpointURI.SUB_BUSINESS_UNIT)
  public ResponseEntity<Object> createSubBusinessUnit(
      @Valid @RequestBody SubBusinessUnitRequestDto subBusinessUnitDto) {
    if (subBusinessUnitService.isSubBusinessUnitExist(subBusinessUnitDto.getName())) {
      logger.info("Already exists");
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

  @GetMapping(value = EndpointURI.SUB_BUSINESS_UNIT_SEARCH)
  public ResponseEntity<Object> getSubBusinessUnitSearch(
      @RequestParam(name = "name", required = false) String name,
      @RequestParam(name = "description", required = false) String description,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    Pagination pagination = new Pagination(0, 0, 0, 0l);
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    return new ResponseEntity<>(new PaginatedContentResponse<>(Constants.SUB_BUSINESS_UNITS,
        mapper.map(subBusinessUnitService.searchSubBusinessUnit(booleanBuilder, name, description,
            pageable, pagination), SubBusinessUnitResponseDto.class),
        RestApiResponseStatus.OK, pagination), null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.SUB_BUSINESS_UNITS_PAGINATION)
  public ResponseEntity<Object> getAllSubBusinessUnitsPagination(
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(page, size, totalpage, 0l);
    pagination.setTotalRecords(subBusinessUnitService.getCountsubBusinessUnit());
    return new ResponseEntity<>(
        new PaginatedContentResponse<>(Constants.SUB_BUSINESS_UNITS,
            mapper.map(subBusinessUnitService.getAllSubBusinessUnits(pageable),
                SubBusinessUnitResponseDto.class),
            RestApiResponseStatus.OK, pagination),
        HttpStatus.OK);
  }
}

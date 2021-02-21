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
import com.tokyo.supermix.data.dto.UnitDto;
import com.tokyo.supermix.data.entities.Unit;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.UnitService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin(origins = "*")
public class UnitController {
  @Autowired
  UnitService unitService;
  @Autowired
  ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  Mapper mapper;
  private static final Logger logger = Logger.getLogger(UnitController.class);

  @PostMapping(value = EndpointURI.UNIT)
  public ResponseEntity<Object> createUnit(@Valid @RequestBody UnitDto unitDto) {
    if (unitService.isUnitExist(unitDto.getUnit())) {
      logger.debug("Unit already exists: createUnit(), unit: {}");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.UNIT,
          validationFailureStatusCodes.getUnitAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    unitService.saveUnit(mapper.map(unitDto, Unit.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_UNIT_SUCCESS), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.UNITS)
  public ResponseEntity<Object> getAllUnits() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.UNITS,
        mapper.map(unitService.getAllUnits(), UnitDto.class), RestApiResponseStatus.OK), null,
        HttpStatus.OK);
  }

  @DeleteMapping(value = EndpointURI.UNIT_BY_ID)
  public ResponseEntity<Object> deleteUnit(@PathVariable Long id) {
    if (unitService.isUnitExist(id)) {
      unitService.deleteUnit(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UNIT_DELETED), HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.UNIT,
        validationFailureStatusCodes.getUnitNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.UNIT_BY_ID)
  public ResponseEntity<Object> getUnitById(@PathVariable Long id) {
    if (unitService.isUnitExist(id)) {
      logger.debug("Id found");
      return new ResponseEntity<>(
          new ContentResponse<>(Constants.UNIT,
              mapper.map(unitService.getUnitById(id), UnitDto.class), RestApiResponseStatus.OK),
          HttpStatus.OK);
    }
    logger.debug("Invalid id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.UNIT,
        validationFailureStatusCodes.getUnitNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.UNIT)
  public ResponseEntity<Object> updateUnit(@Valid @RequestBody UnitDto unitDto) {
    if (unitService.isUnitExist(unitDto.getId())) {
      if (unitService.isUpdatedUnitExist(unitDto.getId(), unitDto.getUnit())) {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.UNIT,
            validationFailureStatusCodes.getUnitAlreadyExist()), HttpStatus.BAD_REQUEST);
      }
      unitService.saveUnit(mapper.map(unitDto, Unit.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UNIT_UPDATED_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.UNIT,
        validationFailureStatusCodes.getUnitNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.SEARCH_UNIT)
  public ResponseEntity<Object> getUnitSearch(@PathVariable String plantCode,
      @RequestParam(name = "unit", required = false) String unit,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    Pagination pagination = new Pagination(0, 0, 0, 0l);
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    return new ResponseEntity<>(new PaginatedContentResponse<>(Constants.UNITS,
        mapper.map(unitService.searchUnit(booleanBuilder, unit, pageable, plantCode, pagination),
            UnitDto.class),
        RestApiResponseStatus.OK, pagination), null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.UNITS_PAGINATION)
  public ResponseEntity<Object> getAllUnitsPagination(@RequestParam(name = "page") int page,
      @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(page, size, totalpage, 0l);
    pagination.setTotalRecords(unitService.getCountUnit());
    return new ResponseEntity<>(new PaginatedContentResponse<>(Constants.UNITS,
        mapper.map(unitService.getAllUnits(pageable), UnitDto.class), RestApiResponseStatus.OK,
        pagination), HttpStatus.OK);
  }
}

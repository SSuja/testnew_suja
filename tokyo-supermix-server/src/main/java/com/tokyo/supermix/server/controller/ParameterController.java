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
import com.tokyo.supermix.data.dto.ParameterDto;
import com.tokyo.supermix.data.entities.Parameter;
import com.tokyo.supermix.data.enums.ParameterDataType;
import com.tokyo.supermix.data.enums.ParameterType;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.ParameterService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin(origins = "*")
public class ParameterController {
  @Autowired
  ParameterService parameterService;
  @Autowired
  ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  private static final Logger logger = Logger.getLogger(ParameterController.class);

  @GetMapping(value = EndpointURI.PARAMETERS)
  public ResponseEntity<Object> getAllParameters(@RequestParam(name = "page") int page,
      @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(page, size, totalpage, 0l);
    pagination.setTotalRecords(parameterService.getCountParameters());
    return new ResponseEntity<>(new PaginatedContentResponse<>(Constants.PARAMETERS,
        mapper.map(parameterService.getAllParametersByDecending(pageable), ParameterDto.class),
        RestApiResponseStatus.OK, pagination), null, HttpStatus.OK);
  }

  @PostMapping(value = EndpointURI.PARAMETER)
  public ResponseEntity<Object> createParameter(@Valid @RequestBody ParameterDto parameterDto) {
    if (parameterService.isExistsByParameterNameAndParameterTypeAndParameterDataType(
        parameterDto.getName(), parameterDto.getParameterType(),
        parameterDto.getParameterDataType())) {
      logger.debug("parameter already exists: createparameter(), parameterName: {}");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.PARAMETER_NAME,
          validationFailureStatusCodes.getParameterAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    parameterService.saveParameter(mapper.map(parameterDto, Parameter.class));
    return new ResponseEntity<Object>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_PARAMETER_SUCCESS),
        HttpStatus.OK);
  }

  @DeleteMapping(value = EndpointURI.PARAMETER_BY_ID)
  public ResponseEntity<Object> deleteParameter(@PathVariable Long id) {
    if (parameterService.isParameterExist(id)) {
      parameterService.deleteParameter(id);
      return new ResponseEntity<Object>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.PARAMETER_DELETED),
          HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PARAMETER,
        validationFailureStatusCodes.getParameterNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.PARAMETER_BY_ID)
  public ResponseEntity<Object> getByParameterId(@PathVariable Long id) {
    if (parameterService.isParameterExist(id)) {
      logger.debug("Get Parameter by id ");
      return new ResponseEntity<>(new ContentResponse<>(Constants.PARAMETER,
          mapper.map(parameterService.getParameterById(id), ParameterDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PARAMETER,
        validationFailureStatusCodes.getParameterNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.PARAMETER)
  public ResponseEntity<Object> UpdateParameter(@Valid @RequestBody ParameterDto parameterDto) {
    if (parameterService.isParameterExist(parameterDto.getId())) {
      if (parameterService.isUpdatedNameExist(parameterDto.getId(), parameterDto.getName())) {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.PARAMETER_NAME,
            validationFailureStatusCodes.getParameterAlreadyExist()), HttpStatus.BAD_REQUEST);
      }
      parameterService.saveParameter(mapper.map(parameterDto, Parameter.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_PARAMETER_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PARAMETER_ID,
        validationFailureStatusCodes.getParameterNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.PARAMETER_BY_PARAMETER_TYPE)
  public ResponseEntity<Object> getByParameterType(@PathVariable ParameterType parameterType) {
    if (parameterService.isParameterTypeExists(parameterType)) {
      logger.debug("Get Parameter by parameterType");
      return new ResponseEntity<>(new ContentResponse<>(
          Constants.PARAMETER, mapper
              .map(parameterService.getParameterByParameterType(parameterType), ParameterDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PARAMETER,
        validationFailureStatusCodes.getParameterNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.PARAMETER_SEARCH)
  public ResponseEntity<Object> searchParameters(@RequestParam(name = "page") int page,
      @RequestParam(name = "size") int size,
      @RequestParam(name = "name", required = false) String name,
      @RequestParam(name = "parameterType", required = false) ParameterType parameterType,
      @RequestParam(name = "parameterDataType",
          required = false) ParameterDataType parameterDataType) {
    Pageable pageable = PageRequest.of(page, size);
    Pagination pagination = new Pagination(0, 0, 0, 0l);
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    return new ResponseEntity<>(new PaginatedContentResponse<>(Constants.PARAMETER,
        mapper.map(parameterService.searchParameters(name, parameterType, parameterDataType,
            booleanBuilder, page, size, pageable, pagination), ParameterDto.class),
        RestApiResponseStatus.OK, pagination), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.PARAMETER_SEARCH_BY_TYPE)
  public ResponseEntity<Object> searchParametersByType(@PathVariable ParameterType parameterType,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size,
      @RequestParam(name = "name", required = false) String name,
      @RequestParam(name = "parameterDataType",
          required = false) ParameterDataType parameterDataType) {
    Pageable pageable = PageRequest.of(page, size);
    Pagination pagination = new Pagination(0, 0, 0, 0l);
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    return new ResponseEntity<>(new PaginatedContentResponse<>(Constants.PARAMETER,
        mapper.map(parameterService.searchParameters(name, parameterType, parameterDataType,
            booleanBuilder, page, size, pageable, pagination), ParameterDto.class),
        RestApiResponseStatus.OK, pagination), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.PARAMETER_PAGE_BY_TYPE)
  public ResponseEntity<Object> getAllParametersBYParameterType(
      @PathVariable ParameterType parameterType, @RequestParam(name = "page") int page,
      @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(page, size, totalpage, 0l);
    pagination.setTotalRecords(parameterService.getCountParametersByType(parameterType));
    return new ResponseEntity<>(
        new PaginatedContentResponse<>(Constants.PARAMETERS,
            mapper.map(parameterService.getAllParametersAndParameterTypeByDecending(parameterType,
                pageable), ParameterDto.class),
            RestApiResponseStatus.OK, pagination),
        null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.PARAMETER_COMMON_SEARCH)
  public ResponseEntity<Object> searchAllParameters(@PathVariable ParameterType parameterType,
      @RequestParam(name = "name", required = false) String name) {
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    return new ResponseEntity<>(new ContentResponse<>(Constants.PARAMETER,
        mapper.map(parameterService.searchCommonParameters(name, parameterType, booleanBuilder),
            ParameterDto.class),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }
}

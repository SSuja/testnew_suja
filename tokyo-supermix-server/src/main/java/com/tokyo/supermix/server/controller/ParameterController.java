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
import com.tokyo.supermix.data.dto.ParameterDto;
import com.tokyo.supermix.data.entities.Parameter;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
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
  @PreAuthorize("hasAuthority('get_parameter')")
  public ResponseEntity<Object> getAllParameters() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.PARAMETERS,
        mapper.map(parameterService.getAllParameters(), ParameterDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @PostMapping(value = EndpointURI.PARAMETER)
  @PreAuthorize("hasAuthority('add_parameter')")
  public ResponseEntity<Object> createParameter(@Valid @RequestBody ParameterDto parameterDto) {
    if (parameterService.isNameExist(parameterDto.getName())) {
      logger.debug("parameter already exists: createparameter(), parameterName: {}");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.PARAMETER_NAME,
          validationFailureStatusCodes.getParameterAlreadyExist()), HttpStatus.BAD_REQUEST);
    }

    parameterService.saveParameter(mapper.map(parameterDto, Parameter.class));
    return new ResponseEntity<Object>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_PARAMETER_SUCCESS),
        HttpStatus.OK);
  }

  @DeleteMapping(value = EndpointURI.DELETE_PARAMETER_BY_ID)
  @PreAuthorize("hasAuthority('delete_parameter')")
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

  @GetMapping(value = EndpointURI.GET_PARAMETER_BY_ID)
  public ResponseEntity<Object> getParameterByID(@PathVariable Long id) {
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
  @PreAuthorize("hasAuthority('edit_parameter')")
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

  @GetMapping(value = EndpointURI.PARAMETER_SEARCH)
  public ResponseEntity<Object> getParameterSearch(
      @QuerydslPredicate(root = Parameter.class) Predicate predicate,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.PARAMETERS,
            parameterService.searchParameter(predicate, page, size), RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }
}

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
import com.tokyo.supermix.data.dto.QualityParameterRequestDto;
import com.tokyo.supermix.data.dto.QualityParameterResponseDto;
import com.tokyo.supermix.data.entities.QualityParameter;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.QualityParameterService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin
public class QualityParameterController {
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  @Autowired
  private QualityParameterService qualityParameterService;
  private static final Logger logger = Logger.getLogger(QualityParameterController.class);

  @PostMapping(value = EndpointURI.QUALITY_PARAMETER)
  public ResponseEntity<Object> createQualityParameter(
      @Valid @RequestBody QualityParameterRequestDto qualityParameterRequestDto) {
    if (qualityParameterService.isNameExists(qualityParameterRequestDto.getName())) {
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.QUALITY_PARAMETER,
              validationFailureStatusCodes.getQualityParameterAlreadyExist()),
          HttpStatus.BAD_REQUEST);
    }
    qualityParameterService
        .saveQualityParameter(mapper.map(qualityParameterRequestDto, QualityParameter.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_QUALITY_PARAMETER_SUCCESS),
        HttpStatus.OK);


  }

  @GetMapping(value = EndpointURI.QUALITY_PARAMETERS)
  public ResponseEntity<Object> getAllQualityParameters() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.QUALITY_PARAMETERS, mapper
        .map(qualityParameterService.getAllQualityParameters(), QualityParameterResponseDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @DeleteMapping(EndpointURI.QUALITY_PARAMETER_BY_ID)
  public ResponseEntity<Object> deleteQualityParameterById(@PathVariable Long id) {
    if (qualityParameterService.isQualityParameterIdExist(id)) {
      qualityParameterService.deleteQualityParameter(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.DELETED_QUALITY_PARAMETER),
          HttpStatus.OK);
    }
    logger.debug("No quality Parameter record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.QUALITY_PARAMETER_ID,
        validationFailureStatusCodes.getQualityParameterNotExist()), HttpStatus.BAD_REQUEST);
  }


  @GetMapping(value = EndpointURI.QUALITY_PARAMETER_BY_ID)
  public ResponseEntity<Object> getQualityParameterById(@PathVariable Long id) {
    if (qualityParameterService.isQualityParameterIdExist(id)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.QUALITY_PARAMETER,
          mapper.map(qualityParameterService.getQualityParameterById(id),
              QualityParameterResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("No Quality Parameter record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.QUALITY_PARAMETER_ID,
        validationFailureStatusCodes.getQualityParameterNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.QUALITY_PARAMETER)
  public ResponseEntity<Object> updateQualityParameter(
      @RequestBody QualityParameterRequestDto qualityParameterRequestDto) {
    if (qualityParameterService.isQualityParameterIdExist(qualityParameterRequestDto.getId())) {
      if (qualityParameterService.isDuplicateEnty(qualityParameterRequestDto.getId(),
          qualityParameterRequestDto.getName())) {
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.QUALITY_PARAMETER,
                validationFailureStatusCodes.getQualityParameterAlreadyExist()),
            HttpStatus.BAD_REQUEST);
      }
      qualityParameterService
          .saveQualityParameter(mapper.map(qualityParameterRequestDto, QualityParameter.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_QUALITY_PARAMETER_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.QUALITY_PARAMETER_ID,
        validationFailureStatusCodes.getQualityParameterNotExist()), HttpStatus.BAD_REQUEST);
  }
}

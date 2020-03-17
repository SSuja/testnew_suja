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
import com.tokyo.supermix.data.dto.ParameterResultRequestDto;
import com.tokyo.supermix.data.dto.ParameterResultResponseDto;
import com.tokyo.supermix.data.entities.ParameterResult;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.MaterialTestTrialService;
import com.tokyo.supermix.server.services.ParameterResultService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class ParameterResultController {
  @Autowired
  private ParameterResultService parameterResultService;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;

  @Autowired
  private MaterialTestTrialService materialTestTrialService;
  @Autowired
  private Mapper mapper;
  private static final Logger logger = Logger.getLogger(ParameterResultController.class);

  @PostMapping(value = EndpointURI.PARAMETER_RESULT)
  public ResponseEntity<Object> createParameterResult(
      @Valid @RequestBody List<ParameterResultRequestDto> parameterResultRequestDtoList) {
    parameterResultRequestDtoList.forEach(parameterResultRequestDtoListObj -> parameterResultService
        .saveParameterResult(mapper.map(parameterResultRequestDtoListObj, ParameterResult.class)));
    return new ResponseEntity<Object>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_PARAMETER_RESULT_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.PARAMETER_RESULTS)
  public ResponseEntity<Object> getAllParameterResults() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.PARAMETER_RESULTS, mapper
        .map(parameterResultService.getAllParameterResults(), ParameterResultResponseDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.GET_PARAMETER_RESULT_BY_ID)
  public ResponseEntity<Object> getParameterResultByID(@PathVariable Long id) {
    if (parameterResultService.isParameterResultExist(id)) {
      logger.debug("Get ParameterResult by id ");
      return new ResponseEntity<>(new ContentResponse<>(Constants.PARAMETER_RESULT_ID, mapper
          .map(parameterResultService.getParameterResultById(id), ParameterResultResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PARAMETER_RESULT,
        validationFailureStatusCodes.getParameterResultNotExist()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(value = EndpointURI.DELETE_PARAMETER_RESULT_BY_ID)
  public ResponseEntity<Object> deleteParameterResult(@PathVariable Long id) {
    if (parameterResultService.isParameterResultExist(id)) {
      parameterResultService.deleteParameterResult(id);
      return new ResponseEntity<Object>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.PARAMETER_RESULT_DELETED),
          HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PARAMETER_RESULT_ID,
        validationFailureStatusCodes.getParameterResultNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.PARAMETER_RESULT_CALCULATION)
  public ResponseEntity<Object> calculateResultForTestTrial(
      @PathVariable String materialTestTrialCode) {
    parameterResultService
        .setResult(materialTestTrialService.getMaterialTestTrialByCode(materialTestTrialCode));
    return new ResponseEntity<Object>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.RESULT_SUCCESSFULLY_UPDATED),
        HttpStatus.OK);
  }
}

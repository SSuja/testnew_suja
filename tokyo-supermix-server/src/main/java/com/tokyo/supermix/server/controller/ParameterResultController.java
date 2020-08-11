package com.tokyo.supermix.server.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.script.ScriptException;
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
import com.tokyo.supermix.data.dto.MaterialParameterResultDto;
import com.tokyo.supermix.data.dto.ParameterResultDto;
import com.tokyo.supermix.data.dto.ParameterResultRequestDto;
import com.tokyo.supermix.data.dto.ParameterResultResponseDto;
import com.tokyo.supermix.data.entities.ParameterResult;
import com.tokyo.supermix.data.enums.InputMethod;
import com.tokyo.supermix.data.enums.TestParameterType;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.MaterialTestService;
import com.tokyo.supermix.server.services.MaterialTestTrialService;
import com.tokyo.supermix.server.services.ParameterResultService;
import com.tokyo.supermix.server.services.PlantService;
import com.tokyo.supermix.server.services.TestParameterService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class ParameterResultController {
  @Autowired
  private ParameterResultService parameterResultService;
  @Autowired
  private PlantService plantService;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private MaterialTestTrialService materialTestTrialService;
  @Autowired
  private MaterialTestService materialTestService;
  @Autowired
  TestParameterService testParameterService;
  @Autowired
  private Mapper mapper;
  private static final Logger logger = Logger.getLogger(ParameterResultController.class);

  @PostMapping(value = EndpointURI.PARAMETER_RESULT)
  public ResponseEntity<Object> createParameterResult(
      @Valid @RequestBody List<MaterialParameterResultDto> materialParameterResultDtolist)
      throws ScriptException {
    List<ParameterResultDto> li = materialParameterResultDtolist.get(0).parameterResults.stream()
        .filter(parameterResult -> (parameterResult.getValue() == 0
            && testParameterService.getTestParameterById(parameterResult.getTestParameterId())
                .getInputMethods().equals(InputMethod.OBSERVE)
            && testParameterService.getTestParameterById(parameterResult.getTestParameterId())
                .getType().equals(TestParameterType.INPUT)))
        .collect(Collectors.toList());
    if (li.isEmpty()) {
      parameterResultService.saveParameterResults(materialParameterResultDtolist);
      return new ResponseEntity<Object>(new BasicResponse<>(RestApiResponseStatus.OK,
          Constants.PARAMETER_VALUE_ADDED_AND_RESULT_UPDATED), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PARAMETER_RESULT,
        validationFailureStatusCodes.getParameterResultNotExist()), HttpStatus.BAD_REQUEST);
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

  @DeleteMapping(value = EndpointURI.PARAMETER_RESULT_BY_ID)
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

  @PutMapping(value = EndpointURI.PARAMETER_RESULT)
  public ResponseEntity<Object> UpdateParameterResult(
      @Valid @RequestBody ParameterResultRequestDto parameterResultRequestDto) {
    if (parameterResultService.isParameterResultExist(parameterResultRequestDto.getId())) {
      parameterResultService
          .saveParameterValue(mapper.map(parameterResultRequestDto, ParameterResult.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_PARAMETER_RESULT_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PARAMETER_RESULT_ID,
        validationFailureStatusCodes.getParameterResultNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.PARAMETER_RESULT_BY_MATERIAL_TEST_TRIAL_CODE)
  public ResponseEntity<Object> getParameterResultByMaterialTestTrialByCode(
      @PathVariable String materialTestTrialCode) {
    if (materialTestTrialService.isMaterialTestTrialExits(materialTestTrialCode)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.MATERIAL_TEST_TRIAL_CODE,
          mapper.map(parameterResultService.findByMaterialTestTrialCode(materialTestTrialCode),
              ParameterResultResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    } else {
      logger.debug("No Parameter Result record exist for given Material Test Trial code");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_TEST_TRIAL_CODE,
          validationFailureStatusCodes.getMaterialTestTrailNotExist()), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(value = EndpointURI.GET_PARAMETER_RESULT_BY_PLANT)
  public ResponseEntity<Object> getParameterResultByPlant(@PathVariable String plantCode) {
    if (plantService.isPlantExist(plantCode)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.PLANT_ID,
          mapper.map(parameterResultService.findParameterResultByPlantCode(plantCode),
              ParameterResultResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    } else {
      logger.debug("No Parameter Result record exist for given Plant Code");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT_ID,
          validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(
      value = EndpointURI.GET_PARAMETER_RESULTS_BY_MATERIAL_TEST_TRIAL_CODE_AND_MATERIAL_TEST_CODE)
  public ResponseEntity<Object> getParameterResultWithConfigValue(
      @PathVariable String materialTestTrialCode, @PathVariable String materialTestCode) {
    if (materialTestTrialService.isMaterialTestTrialExits(materialTestTrialCode)
        && materialTestService.isMaterialTestExists(materialTestCode)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.MATERIAL_TEST_TRIAL_CODE,
          mapper.map(parameterResultService.getParameterResultWithConfigValue(materialTestTrialCode,
              materialTestCode), ParameterResultResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_TEST_CODE,
          validationFailureStatusCodes.getMaterialTestNotExist()), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(value = EndpointURI.PARAMETER_RESULT_BY_MATERIAL_TEST_CODE)
  public ResponseEntity<Object> getParameterResultByMaterialTestCode(
      @PathVariable String materialTestCode) {
    if (materialTestService.isMaterialTestExists(materialTestCode)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.MATERIAL_TEST_CODE,
          mapper.map(parameterResultService.findByMaterialTestCode(materialTestCode),
              ParameterResultResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    } else {
      logger.debug("No Parameter Result record exist for given Material Test code");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_TEST_CODE,
          validationFailureStatusCodes.getMaterialTestNotExist()), HttpStatus.BAD_REQUEST);
    }
  }

  // @GetMapping(value = EndpointURI.SIEVETEST_PARAMETER_RESULT_BY_MATERIAL_TEST_CODE)
  // public ResponseEntity<Object> getSieveTestParameterResultByMaterialTestCode(
  // @PathVariable String materialTestCode) {
  // if (materialTestService.isMaterialTestExists(materialTestCode)) {
  // return new ResponseEntity<>(new ContentResponse<>(Constants.MATERIAL_TEST_CODE,
  // mapper.map(parameterResultService.getSieveTestResultsByMaterialTestCode(materialTestCode),
  // SieveTestResultsDto.class),
  // RestApiResponseStatus.OK), HttpStatus.OK);
  // } else {
  // logger.debug("No Parameter Result record exist for given Material Test code");
  // return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_TEST_CODE,
  // validationFailureStatusCodes.getMaterialTestNotExist()), HttpStatus.BAD_REQUEST);
  // }
  // }
}

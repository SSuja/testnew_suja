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
import com.tokyo.supermix.data.dto.FinishProductSampleIssueResponseDto;
import com.tokyo.supermix.data.dto.FinishProductTrialRequestDto;
import com.tokyo.supermix.data.dto.FinishProductTrialResponseDto;
import com.tokyo.supermix.data.dto.ParameterResultDto;
import com.tokyo.supermix.data.dto.FinishProductTestTrialDto;
import com.tokyo.supermix.data.entities.FinishProductTrial;
import com.tokyo.supermix.data.enums.InputMethod;
import com.tokyo.supermix.data.enums.TestParameterType;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.TestParameterRepository;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.security.CurrentUser;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.FinishProductTrialService;
import com.tokyo.supermix.server.services.TestParameterService;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;
import com.tokyo.supermix.util.privilege.PermissionConstants;

@RestController
@CrossOrigin
public class FinishProductTrialController {
  @Autowired
  private Mapper mapper;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private FinishProductTrialService finishProductTrialService;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;
  @Autowired
  private TestParameterService testParameterService;
  private static final Logger logger = Logger.getLogger(FinishProductTrialController.class);

  @GetMapping(value = EndpointURI.FINISH_PRODUCT_TRIALS)
  public ResponseEntity<Object> getAllFinishProductTrials() {
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.FINISH_PRODUCT_TRIALS,
            mapper.map(finishProductTrialService.getAllFinishProductTrials(),
                FinishProductTrialResponseDto.class),
            RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.FINISH_PRODUCT_TRIAL_BY_PLANT)
  public ResponseEntity<Object> getAllFinishProductTrialsByPlant(
      @CurrentUser UserPrincipal currentUser, @PathVariable String plantCode) {
    if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
      return new ResponseEntity<>(
          new ContentResponse<>(Constants.FINISH_PRODUCT_TRIALS,
              mapper.map(finishProductTrialService.getAllFinishProductTrialsByPlant(currentUser),
                  FinishProductSampleIssueResponseDto.class),
              RestApiResponseStatus.OK),
          HttpStatus.OK);
    }
    if (currentUserPermissionPlantService.getPermissionPlantCodeByCurrentUser(currentUser,
        PermissionConstants.VIEW_FINISH_PRODUCT_TRAIL).contains(plantCode)) {
      return new ResponseEntity<>(
          new ContentResponse<>(Constants.FINISH_PRODUCT_TRIALS,
              mapper.map(finishProductTrialService.getAllFinishProductTrialsByPlantCode(plantCode),
                  FinishProductSampleIssueResponseDto.class),
              RestApiResponseStatus.OK),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PostMapping(value = EndpointURI.FINISH_PRODUCT_TRIAL)
  public ResponseEntity<Object> saveFinishProductTrial(
      @Valid @RequestBody List<FinishProductTrialRequestDto> finishProductTrialRequestDtoList)
      throws ScriptException {
    List<FinishProductTrialRequestDto> li = finishProductTrialRequestDtoList.stream()
        .filter(finish -> (finish.getValue() == null
            && testParameterService.getTestParameterById(finish.getTestParameterId())
                .getInputMethods().equals(InputMethod.OBSERVE)
            && testParameterService.getTestParameterById(finish.getTestParameterId()).getType()
                .equals(TestParameterType.INPUT)))
        .collect(Collectors.toList());
    if ((li.isEmpty())) {
      finishProductTrialService.saveFinishProductTrial(
          mapper.map(finishProductTrialRequestDtoList, FinishProductTrial.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_FINISH_PRODUCT_TRIAL_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.FINISH_PRODUCT_TRIAL_ID,
        validationFailureStatusCodes.getFinishProductTrialNotExit()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.FINISH_PRODUCT_RESULT_BY_FINISH_PRODUCT_CODE)
  public ResponseEntity<Object> getResultByFinishProductCode(
      @PathVariable String finishProductCode) {
    if (finishProductTrialService.isFinishProductTestExists(finishProductCode)) {
      logger.debug("Get Finish Product Trial By Id");
      finishProductTrialService.saveFinishproductResult(finishProductCode);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.FINISH_PRODUCT_TRIAL_ID,
        validationFailureStatusCodes.getFinishProductTrialNotExit()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.FINISH_PRODUCT_TRIAL_BY_ID)
  public ResponseEntity<Object> getFinishProductTrialByCode(@PathVariable Long id) {
    if (finishProductTrialService.isFinishProductTrialExists(id)) {
      logger.debug("Get Finish Product Trial By Id");
      return new ResponseEntity<>(new ContentResponse<>(Constants.FINISH_PRODUCT_TRIAL,
          mapper.map(finishProductTrialService.getFinishProductTrialByCode(id),
              FinishProductTrialResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.FINISH_PRODUCT_TRIAL_ID,
        validationFailureStatusCodes.getFinishProductTrialNotExit()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(value = EndpointURI.FINISH_PRODUCT_TRIAL_BY_ID)
  public ResponseEntity<Object> deleteFinishProductTrial(@PathVariable Long id) {
    if (finishProductTrialService.isFinishProductTrialExists(id)) {
      logger.debug("delete finishProductTrial by id");
      finishProductTrialService.deleteFinishProductTrial(id);
      return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
          Constants.DELETE_FINISH_PRODUCT_TRIAL_SUCCESS), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.FINISH_PRODUCT_TRIAL_ID,
        validationFailureStatusCodes.getCustomerNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.FINISH_PRODUCT_TRIALS_BY_FINISH_PRODUCT_TEST_CODE)
  public ResponseEntity<Object> getFinishProductTrialsByFinishProductTestCode(
      @PathVariable String finishProductTestCode) {
    if (finishProductTrialService.isFinishProductTestExists(finishProductTestCode)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.FINISH_PRODUCT_TRIAL,
          mapper.map(finishProductTrialService.getFinishProductTrialsByFinishProductTestCode(
              finishProductTestCode), FinishProductTestTrialDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.FINISH_PRODUCT_TRIAL_ID,
        validationFailureStatusCodes.getFinishProductTrialNotExit()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.FINISH_PRODUCT_TRIAL)
  public ResponseEntity<Object> updateFinishProductTrial(
      @Valid @RequestBody FinishProductTrialRequestDto finishProductTrialRequestDto) {
    finishProductTrialService.updateFinishProductTestTrial(
        mapper.map(finishProductTrialRequestDto, FinishProductTrial.class));
    return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
        Constants.UPDATE_FINISH_PRODUCT_TRIAL_SUCCESS), HttpStatus.OK);
  }
}

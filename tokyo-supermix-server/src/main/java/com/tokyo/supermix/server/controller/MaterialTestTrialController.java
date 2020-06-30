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
import com.tokyo.supermix.data.dto.MaterialTestTrialRequestDto;
import com.tokyo.supermix.data.dto.MaterialTestTrialResponseDto;
import com.tokyo.supermix.data.entities.MaterialTest;
import com.tokyo.supermix.data.entities.MaterialTestTrial;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.MaterialTestRepository;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.security.CurrentUser;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.MaterialTestService;
import com.tokyo.supermix.server.services.MaterialTestTrialService;
import com.tokyo.supermix.server.services.PlantService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin(origins = "*")
public class MaterialTestTrialController {
  @Autowired
  private MaterialTestTrialService materialTestTrialService;
  @Autowired
  private PlantService plantService;
  @Autowired
  private Mapper mapper;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  private static final Logger logger = Logger.getLogger(MaterialTestTrialController.class);
  @Autowired
  private MaterialTestRepository materialTestRepository;
  @Autowired
  private MaterialTestService materialTestService;

  // get all MaterialTestTrial
  @GetMapping(value = EndpointURI.MATERIAL_TEST_TRIALS)
  public ResponseEntity<Object> getAllMaterialTestTrial() {
    return new ResponseEntity<Object>(new ContentResponse<>(Constants.MATERIAL_TEST_TRIAL,
        mapper.map(materialTestTrialService.getAllMaterialTestTrial(),
            MaterialTestTrialResponseDto.class),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }
  @GetMapping(value = EndpointURI.MATERIAL_TEST_TRIAL_BY_PLANT)
  public ResponseEntity<Object> getAllMaterialTestTrialByPlant(@CurrentUser UserPrincipal currentUser) {
    return new ResponseEntity<Object>(new ContentResponse<>(Constants.MATERIAL_TEST_TRIAL,
        mapper.map(materialTestTrialService.getAllMaterialTestTrialByplant(currentUser),
            MaterialTestTrialResponseDto.class),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }
  // post MaterialTestTrial
  @PostMapping(value = EndpointURI.MATERIAL_TEST_TRIAL)
   public String createMaterialTestTrial(
      @Valid @RequestBody MaterialTestTrialRequestDto materialTestTrialRequestDto) {
    return materialTestTrialService
        .saveMaterialTestTrial(mapper.map(materialTestTrialRequestDto, MaterialTestTrial.class));
  }

  // get MaterialTestTrial By Id
  @GetMapping(value = EndpointURI.MATERIAL_TEST_TRIAL_BY_CODE)
  public ResponseEntity<Object> getMaterialTestTrialByCode(@PathVariable String code) {
    if (materialTestTrialService.isMaterialTestTrialExits(code)) {
      logger.debug("Get MaterialTestTrial by code ");
      return new ResponseEntity<>(new ContentResponse<>(Constants.MATERIAL_TEST_TRIAL,
          mapper.map(materialTestTrialService.getMaterialTestTrialByCode(code),
              MaterialTestTrialResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("Invalid code");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_TEST_TRIAL_CODE,
        validationFailureStatusCodes.getMaterialTestTrailNotExist()), HttpStatus.BAD_REQUEST);
  }

  // get MaterialTestTrial Delete
  @DeleteMapping(value = EndpointURI.MATERIAL_TEST_TRIAL_BY_CODE)
  public ResponseEntity<Object> deleteMaterialTestTrial(@PathVariable String code) {
    if (materialTestTrialService.isMaterialTestTrialExits(code)) {
      materialTestTrialService.deleteMaterialTestTrial(code);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.MATERIAL_TEST_TRIAL_DELETED),
          HttpStatus.OK);
    }
    logger.debug("invalid MaterialTestTrialCode");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_TEST_TRIAL,
        validationFailureStatusCodes.getMaterialTestTrailNotExist()), HttpStatus.BAD_REQUEST);
  }

  // get MaterialTestTrial By MaterialTest
  @GetMapping(value = EndpointURI.MATERIAL_TEST_TRIAL_BY_MATERIAL_TEST_CODE)
  public ResponseEntity<Object> getAllMaterialTestTrialByMaterialTestCode(
      @PathVariable String materialTestCode) {
    if (materialTestTrialService.isMaterialTestIdExits(materialTestCode)) {
      logger.debug("Get MaterialTest by code ");
      return new ResponseEntity<>(new ContentResponse<>(Constants.MATERIAL_TEST,
          mapper.map(
              materialTestTrialService.getMaterialTestTrialByMaterialTestCode(materialTestCode),
              MaterialTestTrialResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_TEST,
        validationFailureStatusCodes.getMaterialTestNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.MATERIAL_TEST_TRIAL)
  public ResponseEntity<Object> updateMaterialTestTrial(
      @Valid @RequestBody MaterialTestTrialRequestDto materialTestTrialRequestDto) {
    // if (materialTestTrialService.isMaterialTestTrialExits(materialTestTrialRequestDto.getCode()))
    // {
    materialTestTrialService
        .saveMaterialTestTrial(mapper.map(materialTestTrialRequestDto, MaterialTestTrial.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_MATERIAL_TEST_TRIAL_SUCCESS),
        HttpStatus.OK);
    // }
    // return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_TEST,
    // validationFailureStatusCodes.getMaterialTestNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.GET_MATERIAL_TEST_TRIAL_BY_PLANT)
  public ResponseEntity<Object> getMaterialTestTrialByPlant(@PathVariable String plantCode) {
    if (plantService.isPlantExist(plantCode)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.PLANT_ID,
          mapper.map(materialTestTrialService.getMaterialTestTrialByPlantCode(plantCode),
              MaterialTestTrialResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    } else {
      logger.debug("No Material Test Trial record exist for given Plant Code");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT_ID,
          validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping(value = EndpointURI.MATERIAL_RESULT_BY_MATERIAL_TEST_CODE)
  public ResponseEntity<Object> getMaterialTestAverageBycode(
      @PathVariable String materialTestCode) {
    MaterialTest materialTest = materialTestRepository.findByCode(materialTestCode);
    if (!materialTest.getTestConfigure().isBulkTrial()){
//        .getTest().getName()
//        .equalsIgnoreCase(Constants.SIEVETEST)) {
      materialTestTrialService.getAverageAndStatus(materialTestCode);
      materialTestService.updateIncomingSampleStatusByIncomingSample(materialTest.getIncomingSample());
      return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
          Constants.UPDATE_MATERIAL_TEST_TRIAL_AVERAGE_SUCCESS), HttpStatus.OK);
    }
    else {
      materialTestTrialService.sieveavg(materialTestCode);
     // materialTestService.updateIncomingSampleStatusByIncomingSample(materialTest.getIncomingSample());
      return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
          Constants.UPDATE_MATERIAL_TEST_TRIAL_AVERAGE_SUCCESS), HttpStatus.OK);
    
//    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_TEST,
//        validationFailureStatusCodes.getMaterialTestNotExist()), HttpStatus.BAD_REQUEST);
  }

//    return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
//        Constants.UPDATE_MATERIAL_TEST_TRIAL_AVERAGE_SUCCESS), HttpStatus.OK);}
}}

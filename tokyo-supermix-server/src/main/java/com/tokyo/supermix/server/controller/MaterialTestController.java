package com.tokyo.supermix.server.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.MaterialTestRequestDto;
import com.tokyo.supermix.data.dto.MaterialTestResponseDto;
import com.tokyo.supermix.data.entities.MaterialTest;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.MaterialTestService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin
public class MaterialTestController {

  @Autowired
  MaterialTestService materialTestService;

  @Autowired
  ValidationFailureStatusCodes validationFailureStatusCodes;

  @Autowired
  Mapper mapper;

  private static final Logger logger = Logger.getLogger(FinishProductController.class);

  // create material tests
  @PostMapping(value = EndpointURI.MATERIAL_TEST)
  public ResponseEntity<Object> createMaterialTest(
      @Valid @RequestBody MaterialTestRequestDto materialTestDto) {

    if (materialTestService.isMaterialTestExists(materialTestDto.getCode())) {
      logger.debug("Code already exists");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_TEST,
          validationFailureStatusCodes.getMaterialTestAlreadyExists()), HttpStatus.BAD_REQUEST);
    }
    MaterialTest materialTest = mapper.map(materialTestDto, MaterialTest.class);
    materialTestService.saveMaterialTest(materialTest);
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_MATERIAL_TEST_SUCCESS),
        HttpStatus.OK);
  }

  // get all material tests
  @GetMapping(value = EndpointURI.MATERIAL_TESTS)
  public ResponseEntity<Object> getAllMaterialTests() {
    List<MaterialTestResponseDto> materialTestLists =
        mapper.map(materialTestService.getAllMaterialTests(), MaterialTestResponseDto.class);
    return new ResponseEntity<>(new ContentResponse<>(Constants.MATERIAL_TESTS, materialTestLists,
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  // get material test by id
  @GetMapping(value = EndpointURI.MATERIAL_TESTS_BY_CODE)
  public ResponseEntity<Object> getMaterialTestByCode(@PathVariable String code) {
    if (materialTestService.isMaterialTestExists(code)) {
      logger.debug("Id Exists");
      return new ResponseEntity<>(new ContentResponse<>(Constants.MATERIAL_TEST, mapper
          .map(materialTestService.getMaterialTestByCode(code), MaterialTestResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_TEST,
        validationFailureStatusCodes.getMaterialTestNotExist()), HttpStatus.BAD_REQUEST);
  }

  // delete material test by id
  @DeleteMapping(value = EndpointURI.MATERIAL_TESTS_BY_CODE)
  public ResponseEntity<Object> deleteMaterialTest(@PathVariable String code) {
    if (materialTestService.isMaterialTestExists(code)) {
      materialTestService.deleteMaterialTest(code);
      logger.debug("Material Test deleted");
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.MATERIAL_TEST_DELETED),
          HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_TEST,
        validationFailureStatusCodes.getMaterialTestNotExist()), HttpStatus.BAD_REQUEST);
  }

  // update material test
  @PutMapping(value = EndpointURI.MATERIAL_TEST)
  public ResponseEntity<Object> updateMaterialTest(
      @Valid @RequestBody MaterialTestRequestDto materialTestDto) {
    if (materialTestService.isMaterialTestExists(materialTestDto.getCode())) {
      logger.debug("Id exists");
      MaterialTest materialTest = mapper.map(materialTestDto, MaterialTest.class);
      materialTestService.saveMaterialTest(materialTest);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.MATERIAL_TEST_UPDATED),
          HttpStatus.OK);
    }
    logger.debug("Id not found");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_TEST,
        validationFailureStatusCodes.getMaterialTestNotExist()), HttpStatus.BAD_REQUEST);
  }

  @Scheduled(cron = "0 0/20 * * * ?")
  public void scheduleTaskWithFixedRate() {
    materialTestService.updateIncomingSampleStatusBySeheduler();
  }
}

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.MaterialTestRequestDto;
import com.tokyo.supermix.data.dto.MaterialTestResponseDto;
import com.tokyo.supermix.data.entities.MaterialTest;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.IncomingSampleService;
import com.tokyo.supermix.server.services.MaterialTestService;
import com.tokyo.supermix.server.services.PlantService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin
public class MaterialTestController {

  @Autowired
  MaterialTestService materialTestService;
  @Autowired
  private PlantService plantService;
  @Autowired
  private IncomingSampleService incomingSampleService;
  @Autowired
  ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  Mapper mapper;

  private static final Logger logger = Logger.getLogger(MaterialTestController.class);

  // create material tests
  @PostMapping(value = EndpointURI.MATERIAL_TEST)
  public ResponseEntity<Object> createMaterialTest(
      @Valid @RequestBody MaterialTestRequestDto materialTestDto) {

    if (materialTestService.isMaterialTestExists(materialTestDto.getCode())) {
      logger.debug("Code already exists");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_TEST,
          validationFailureStatusCodes.getMaterialTestAlreadyExists()), HttpStatus.BAD_REQUEST);
    }
    materialTestService.saveMaterialTest(mapper.map(materialTestDto, MaterialTest.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_MATERIAL_TEST_SUCCESS),
        HttpStatus.OK);
  }

  // get all material tests
  @GetMapping(value = EndpointURI.MATERIAL_TESTS)
  public ResponseEntity<Object> getAllMaterialTests() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.MATERIAL_TESTS,
        mapper.map(materialTestService.getAllMaterialTests(), MaterialTestResponseDto.class),
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
      MaterialTest materialTest = materialTestService.getMaterialTestByCode(code);
      if ((materialTest.getStatus()).equals(Status.NEW)) {
        materialTestService.deleteMaterialTest(code);
        logger.debug("Material Test deleted");
        return new ResponseEntity<>(
            new BasicResponse<>(RestApiResponseStatus.OK, Constants.MATERIAL_TEST_DELETED),
            HttpStatus.OK);
      } else {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_TEST_STATUS,
            validationFailureStatusCodes.getMaterialTestStatusValid()), HttpStatus.BAD_REQUEST);
      }
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
      materialTestService.saveMaterialTest(mapper.map(materialTestDto, MaterialTest.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.MATERIAL_TEST_UPDATED),
          HttpStatus.OK);
    }
    logger.debug("Id not found");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_TEST,
        validationFailureStatusCodes.getMaterialTestNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.MATERIAL_TEST_BY_INCOMING_SAMPLE_CODE)
  public ResponseEntity<Object> getMaterialTestByIncomingSampleCode(
      @PathVariable String incomingSampleCode) {
    if (incomingSampleService.isIncomingSampleExist(incomingSampleCode)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.INCOMING_SAMPLE_CODE,
          mapper.map(materialTestService.findByIncomingSampleCode(incomingSampleCode),
              MaterialTestResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    } else {
      logger.debug("No Material Test record exist for given Incoming Sample Code");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.INCOMING_SAMPLE_CODE,
          validationFailureStatusCodes.getIncomingSampleNotExist()), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(value = EndpointURI.SEARCH_MATERIAL_TEST)
  public ResponseEntity<Object> searchMaterialTest(@RequestParam(name = "page") int page,
      @RequestParam(name = "size") int size,
      @RequestParam(name = "incomingSampleCode", required = false) String incomingSampleCode,
      @RequestParam(name = "testName", required = false) String testName,
      @RequestParam(name = "status", required = false) Status status,
      @RequestParam(name = "averageMax", required = false) Double averageMax,
      @RequestParam(name = "averageMin", required = false) Double averageMin,
      @RequestParam(name = "average", required = false) Double average) {
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.MATERIAL_TESTS,
            materialTestService.searchMaterialTest(incomingSampleCode, status, average, testName,
                averageMin, averageMax, booleanBuilder, page, size),
            RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.GET_MATERIAL_TEST_BY_PLANT)
  public ResponseEntity<Object> getMaterialTestByPlant(@PathVariable String plantCode) {
    if (plantService.isPlantExist(plantCode)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.PLANT_ID,
          mapper.map(materialTestService.getMaterialTestByPlantCode(plantCode),
              MaterialTestResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    } else {
      logger.debug("No Material Test record exist for given Plant Code");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT_ID,
          validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
    }
  }
}

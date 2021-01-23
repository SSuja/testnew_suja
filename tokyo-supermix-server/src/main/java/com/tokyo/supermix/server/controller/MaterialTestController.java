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
import com.tokyo.supermix.data.dto.MaterialTestRequestDto;
import com.tokyo.supermix.data.dto.MaterialTestResponseDto;
import com.tokyo.supermix.data.entities.MaterialTest;
import com.tokyo.supermix.data.enums.MainType;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.PlantRepository;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.security.CurrentUser;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.IncomingSampleService;
import com.tokyo.supermix.server.services.MaterialTestService;
import com.tokyo.supermix.server.services.PlantService;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;
import com.tokyo.supermix.util.privilege.PermissionConstants;

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
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;
  @Autowired
  private PlantRepository plantRepository;
  private static final Logger logger = Logger.getLogger(MaterialTestController.class);

  // create material tests
  @PostMapping(value = EndpointURI.MATERIAL_TEST)
  public String createMaterialTest(@Valid @RequestBody MaterialTestRequestDto materialTestDto) {
    return materialTestService.saveMaterialTest(mapper.map(materialTestDto, MaterialTest.class));
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
      @PathVariable String incomingSampleCode, @PathVariable String plantCode) {
    if (incomingSampleService.isIncomingSampleExist(incomingSampleCode)) {
      if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
        return new ResponseEntity<>(new ContentResponse<>(Constants.PLANT_ID,
            mapper.map(materialTestService.findByIncomingSampleCode(incomingSampleCode),
                MaterialTestResponseDto.class),
            RestApiResponseStatus.OK), HttpStatus.OK);
      }
      if (plantRepository.existsByCode(plantCode)) {
        return new ResponseEntity<>(new ContentResponse<>(Constants.PLANT_ID,
            mapper.map(materialTestService.getMaterialTestsByincomingSampleCodeAndPlantCode(
                incomingSampleCode, plantCode), MaterialTestResponseDto.class),
            RestApiResponseStatus.OK), HttpStatus.OK);
      }
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANTS,
          validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
    }
    logger.debug("No Material Test record exist for given Incoming Sample Code");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.INCOMING_SAMPLE_CODE,
        validationFailureStatusCodes.getIncomingSampleNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.SEARCH_MATERIAL_TEST)
  public ResponseEntity<Object> searchMaterialTest(@PathVariable String plantCode,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size,
      @RequestParam(name = "code", required = false) String code,
      @RequestParam(name = "incomingSampleCode", required = false) String incomingSampleCode,
      @RequestParam(name = "date", required = false) String date,
      @RequestParam(name = "specimenCode", required = false) String specimenCode,
      @RequestParam(name = "testName", required = false) String testName,
      @RequestParam(name = "status", required = false) Status status,
      @RequestParam(name = "supplierName", required = false) String supplierName,
      @RequestParam(name = "materialCategory", required = false) String materialCategory,
      @RequestParam(name = "subCategoryName", required = false) String subCategoryName) {
    Pageable pageable = PageRequest.of(page, size);
    Pagination pagination = new Pagination(0, 0, 0, 0l);
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    if (plantCode.equalsIgnoreCase(Constants.ADMIN) || plantRepository.existsByCode(plantCode)) {
      return new ResponseEntity<>(
          new PaginatedContentResponse<>(Constants.INCOMING_SAMPLES,
              mapper.map(
                  materialTestService.searchMaterialTest(code, incomingSampleCode, date,
                      specimenCode, status, supplierName, testName, materialCategory,
                      subCategoryName, booleanBuilder, page, size, pageable, plantCode, pagination),
                  MaterialTestResponseDto.class),
              RestApiResponseStatus.OK, pagination),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANTS,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
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

  @GetMapping(value = EndpointURI.GET_MATERIAL_TEST_TRIAL_BY_TEST_CONFIGURE)
  public ResponseEntity<Object> getMaterialTestByTestConfigure(@PathVariable Long testConfigureId,
      @PathVariable String plantCode) {
    if (materialTestService.isMaterialTestByTestConfigureExists(testConfigureId)) {
      if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
        return new ResponseEntity<>(new ContentResponse<>(Constants.PLANT_ID,
            mapper.map(materialTestService.getMaterialTestByTestConfigureId(testConfigureId),
                MaterialTestResponseDto.class),
            RestApiResponseStatus.OK), HttpStatus.OK);
      }
      if (plantRepository.existsByCode(plantCode)) {
        return new ResponseEntity<>(new ContentResponse<>(Constants.PLANT_ID,
            mapper.map(materialTestService.getMaterialTestByTestConfigureIdByPlant(testConfigureId,
                plantCode), MaterialTestResponseDto.class),
            RestApiResponseStatus.OK), HttpStatus.OK);
      }
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANTS,
          validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_CONFIGURE,
        validationFailureStatusCodes.getTestConfigureNotExist()), HttpStatus.BAD_REQUEST);
  }

  // get material test by TestConfigureTestType
  @GetMapping(value = EndpointURI.MATERIAL_TESTS_BY_TESTCONFIGURE_TESTTYPE)
  public ResponseEntity<Object> getMaterialTestByTestConfigureTestType(
      @PathVariable MainType testType) {
    if (!materialTestService.getMaterialTestByTestConfigureTestType(testType).isEmpty()) {
      logger.debug("testType Exists");
      return new ResponseEntity<>(new ContentResponse<>(Constants.MATERIAL_TESTS,
          mapper.map(materialTestService.getMaterialTestByTestConfigureTestType(testType),
              MaterialTestResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("Invalid TestType");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_TEST,
        validationFailureStatusCodes.getMaterialTestNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.MATERIAL_TEST_BY_PLANT)
  public ResponseEntity<Object> getAllMaterialTestsByPlant(@CurrentUser UserPrincipal currentUser,
      @PathVariable String plantCode) {
    if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.MATERIAL_TESTS,
          mapper.map(materialTestService.getAllMaterialTestByPlant(currentUser),
              MaterialTestResponseDto.class),
          RestApiResponseStatus.OK), null, HttpStatus.OK);
    }
    if (currentUserPermissionPlantService
        .getPermissionPlantCodeByCurrentUser(currentUser, PermissionConstants.VIEW_MATERIAL_TEST)
        .contains(plantCode)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.MATERIAL_TESTS,
          mapper.map(materialTestService.getMaterialTestByPlantCode(plantCode),
              MaterialTestResponseDto.class),
          RestApiResponseStatus.OK), null, HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.MATERIAL_TEST_COMMENT)
  public ResponseEntity<Object> updateMaterialTestComment(
      @Valid @RequestBody MaterialTestRequestDto materialTestRequestDto) {
    materialTestService
        .updateMaterialTestComment(mapper.map(materialTestRequestDto, MaterialTest.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.MATERIAL_TEST_COMMENT_UPDATED),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.MATERIAL_TEST_BY_INCOMING_SAMPLE_CODE_AND_TEST)
  public ResponseEntity<Object> getMaterialTestByIncomingSampleCodeAndTest(
      @PathVariable String incomingSampleCode, @PathVariable Long testConfigId,
      @PathVariable String plantCode) {
    if (incomingSampleService.isIncomingSampleExist(incomingSampleCode)) {
      if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
        return new ResponseEntity<>(new ContentResponse<>(Constants.INCOMING_SAMPLES,
            mapper.map(materialTestService.getMaterialTestsByIncomingSampleCodeAndTestConfigId(
                incomingSampleCode, testConfigId), MaterialTestResponseDto.class),
            RestApiResponseStatus.OK), HttpStatus.OK);
      }
      if (plantRepository.existsByCode(plantCode)) {
        return new ResponseEntity<>(new ContentResponse<>(Constants.INCOMING_SAMPLES,
            mapper.map(
                materialTestService.getMaterialTestsByIncomingSampleCodeAndTestConfigIdAndPlantCode(
                    incomingSampleCode, testConfigId, plantCode),
                MaterialTestResponseDto.class),
            RestApiResponseStatus.OK), HttpStatus.OK);
      }
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANTS,
          validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.INCOMING_SAMPLE_CODE,
        validationFailureStatusCodes.getIncomingSampleNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.MATERIAL_TEST_BY_PLANT_CODE)
  public ResponseEntity<Object> getMaterialTestByPlantCode(@PathVariable String plantCode,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(page, size, totalpage, 0l);
    if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
      pagination.setTotalRecords(materialTestService.getCountMaterialTest());
      return new ResponseEntity<>(
          new PaginatedContentResponse<>(Constants.INCOMING_SAMPLES,
              mapper.map(materialTestService.getAllMaterialTests(pageable),
                  MaterialTestResponseDto.class),
              RestApiResponseStatus.OK, pagination),
          HttpStatus.OK);
    }
    if (plantRepository.existsByCode(plantCode)) {
      pagination.setTotalRecords(materialTestService.getCountMaterialTestByPlantCode(plantCode));
      return new ResponseEntity<>(
          new PaginatedContentResponse<>(Constants.INCOMING_SAMPLES,
              mapper.map(materialTestService.getMaterialTestByPlant(plantCode, pageable),
                  MaterialTestResponseDto.class),
              RestApiResponseStatus.OK, pagination),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANTS,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.GET_MATERIAL_TESTS_BY_INCOMING_SAMPLE)
  public ResponseEntity<Object> getMaterialTestsByIncomingSample(
      @PathVariable String incomingSampleCode) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.MATERIAL_TESTS,
        materialTestService.getMaterialTestsByIncomingSample(incomingSampleCode),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }

}

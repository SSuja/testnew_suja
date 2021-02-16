package com.tokyo.supermix.server.controller;

import java.util.List;
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
import com.tokyo.supermix.data.dto.MaterialAcceptedValueRequestDto;
import com.tokyo.supermix.data.dto.MaterialAcceptedValueResponseDto;
import com.tokyo.supermix.data.dto.MaterialSubCategoryResponseDto;
import com.tokyo.supermix.data.dto.RawMaterialResponseDto;
import com.tokyo.supermix.data.entities.MaterialAcceptedValue;
import com.tokyo.supermix.data.enums.CategoryAcceptedType;
import com.tokyo.supermix.data.enums.Condition;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.CoreTestConfigureService;
import com.tokyo.supermix.server.services.MaterialAcceptedValueService;
import com.tokyo.supermix.server.services.MaterialTestService;
import com.tokyo.supermix.server.services.TestConfigureService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class MaterialAcceptedValueController {

  @Autowired
  private MaterialAcceptedValueService materialAcceptedValueService;
  @Autowired
  private TestConfigureService testConfigureService;
  @Autowired
  private MaterialTestService materialTestService;
  @Autowired
  ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  @Autowired
  CoreTestConfigureService configureService;
  private static final Logger logger = Logger.getLogger(MaterialAcceptedValueController.class);

  @PostMapping(value = EndpointURI.MATERIAL_ACCEPTED_VALUE)
  public ResponseEntity<Object> createMaterialAcceptedValue(
      @Valid @RequestBody List<MaterialAcceptedValueRequestDto> materialAcceptedValueRequestDtoList) {
    for (MaterialAcceptedValueRequestDto materialAcceptedValueRequestDto : materialAcceptedValueRequestDtoList) {
      if (materialAcceptedValueRequestDto.getMaterialSubCategoryId() != null) {
        configureService.updateApplicableTestByMaterialSubCategoryId(
            materialAcceptedValueRequestDto.getTestConfigureId(),
            materialAcceptedValueRequestDto.getMaterialSubCategoryId(), true);
      }
      if (materialAcceptedValueRequestDto.getRawMaterialId() != null) {
        configureService.updateApplicableTestByRawMaterialId(
            materialAcceptedValueRequestDto.getTestConfigureId(),
            materialAcceptedValueRequestDto.getRawMaterialId(), true);
      }
    }
    if (materialAcceptedValueService.isCheckValidation(
        mapper.map(materialAcceptedValueRequestDtoList, MaterialAcceptedValue.class))) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.ACCEPTED_VALUE,
          validationFailureStatusCodes.getAcceptedValueNotExist()), HttpStatus.BAD_REQUEST);
    }

    materialAcceptedValueService.saveAcceptedValue(
        mapper.map(materialAcceptedValueRequestDtoList, MaterialAcceptedValue.class));
    materialAcceptedValueService
        .upDateTesConfigureType(materialAcceptedValueRequestDtoList.get(0).getTestConfigureId());
    return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
        Constants.ADD_MATERIAL_ACCEPTED_VALUE_SUCCESS), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.MATERIAL_ACCEPTED_VALUES)
  public ResponseEntity<Object> getAllMaterialAcceptedValues() {
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.MATERIAL_ACCEPTED_VALUES,
            mapper.map(materialAcceptedValueService.getAllMaterialAcceptedValues(),
                MaterialAcceptedValueResponseDto.class),
            RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.MATERIAL_ACCEPTED_VALUES_PAGINATION)
  public ResponseEntity<Object> getAllMaterialAcceptedValuesByPagination(
      @PathVariable Long testConfigureId, @PathVariable CategoryAcceptedType categoryAcceptedType,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(page, size, totalpage, 0l);
    pagination.setTotalRecords(materialAcceptedValueService
        .countMaterialAcceptedValues(testConfigureId, categoryAcceptedType));
    return new ResponseEntity<>(
        new PaginatedContentResponse<>(Constants.TEST_CONFIGURE,
            materialAcceptedValueService.getAllMaterialAcceptedValuesByPage(pageable,
                testConfigureId, categoryAcceptedType),
            RestApiResponseStatus.OK, pagination),
        null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.MATERIAL_ACCEPTED_VALUE_BY_ID)
  public ResponseEntity<Object> getMaterialAcceptedValueById(@PathVariable Long id) {
    if (materialAcceptedValueService.isMaterialAcceptedValueExist(id)) {
      logger.debug("Get AcceptedValue by id ");
      return new ResponseEntity<>(
          new ContentResponse<>(Constants.MATERIAL_ACCEPTED_VALUE,
              mapper.map(materialAcceptedValueService.getMaterialAcceptedValueById(id),
                  MaterialAcceptedValueResponseDto.class),
              RestApiResponseStatus.OK),
          HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_ACCEPTED_VALUE,
        validationFailureStatusCodes.getAcceptedValueNotExist()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(value = EndpointURI.MATERIAL_ACCEPTED_VALUE_BY_ID)
  public ResponseEntity<Object> deleteAcceptedValue(@PathVariable Long id) {
    if (materialAcceptedValueService.isMaterialAcceptedValueExist(id)) {
      MaterialAcceptedValue materialAcceptedValue =
          materialAcceptedValueService.getMaterialAcceptedValueById(id);
      if (materialAcceptedValue.getCategoryAcceptedType().equals(CategoryAcceptedType.MATERIAL)) {
        if (materialTestService.isMaterialTestByTestConfigureAndRawMaterialExists(
            materialAcceptedValue.getTestConfigure().getId(),
            materialAcceptedValue.getRawMaterial().getId())) {
          return new ResponseEntity<>(
              new ValidationFailureResponse(Constants.TEST_CONFIGURE,
                  validationFailureStatusCodes.getTestConfigureAlreadyDepended()),
              HttpStatus.BAD_REQUEST);
        }
      } else {
        if (materialTestService.isMaterialTestByTestConfigureAndMaterialSubCategoryExists(
            materialAcceptedValue.getTestConfigure().getId(),
            materialAcceptedValue.getMaterialSubCategory().getId())) {
          return new ResponseEntity<>(
              new ValidationFailureResponse(Constants.TEST_CONFIGURE,
                  validationFailureStatusCodes.getTestConfigureAlreadyDepended()),
              HttpStatus.BAD_REQUEST);
        }
      }
      if (materialAcceptedValue.getMaterialSubCategory() != null) {
        configureService.updateApplicableTestByMaterialSubCategoryId(
            materialAcceptedValue.getTestConfigure().getId(),
            materialAcceptedValue.getMaterialSubCategory().getId(), false);
      }
      if (materialAcceptedValue.getRawMaterial() != null) {
        configureService.updateApplicableTestByRawMaterialId(
            materialAcceptedValue.getTestConfigure().getId(),
            materialAcceptedValue.getRawMaterial().getId(), false);
      }
      materialAcceptedValueService.deleteMaterialAcceptedValue(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.MATERIAL_ACCEPTED_VALUE_DELETED),
          HttpStatus.OK);
    }
    logger.debug("invalid acceptedValueId");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_ACCEPTED_VALUE_ID,
        validationFailureStatusCodes.getAcceptedValueNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.MATERIAL_ACCEPTED_VALUE)
  public ResponseEntity<Object> updateMaterialAcceptedValue(
      @Valid @RequestBody MaterialAcceptedValueRequestDto materialAcceptedValueRequestDto) {
    if (materialAcceptedValueService
        .isMaterialAcceptedValueExist(materialAcceptedValueRequestDto.getId())) {
      if (materialAcceptedValueRequestDto.getCategoryAcceptedType()
          .equals(CategoryAcceptedType.MATERIAL)) {
        if (materialTestService.isMaterialTestByTestConfigureAndRawMaterialExists(
            materialAcceptedValueRequestDto.getTestConfigureId(),
            materialAcceptedValueRequestDto.getRawMaterialId())) {
          return new ResponseEntity<>(
              new ValidationFailureResponse(Constants.TEST_CONFIGURE,
                  validationFailureStatusCodes.getTestConfigureAlreadyDepended()),
              HttpStatus.BAD_REQUEST);
        }
      } else {
        if (materialTestService.isMaterialTestByTestConfigureAndMaterialSubCategoryExists(
            materialAcceptedValueRequestDto.getTestConfigureId(),
            materialAcceptedValueRequestDto.getMaterialSubCategoryId())) {
          return new ResponseEntity<>(
              new ValidationFailureResponse(Constants.TEST_CONFIGURE,
                  validationFailureStatusCodes.getTestConfigureAlreadyDepended()),
              HttpStatus.BAD_REQUEST);
        }
      }
      if (materialAcceptedValueRequestDto.getMaterialSubCategoryId() != null) {
        configureService.updateApplicableTestByMaterialSubCategoryId(
            materialAcceptedValueRequestDto.getTestConfigureId(),
            materialAcceptedValueRequestDto.getMaterialSubCategoryId(), true);
      }
      if (materialAcceptedValueRequestDto.getRawMaterialId() != null) {
        configureService.updateApplicableTestByRawMaterialId(
            materialAcceptedValueRequestDto.getTestConfigureId(),
            materialAcceptedValueRequestDto.getRawMaterialId(), true);
      }
      if (materialAcceptedValueService.isCheckValidations(
          mapper.map(materialAcceptedValueRequestDto, MaterialAcceptedValue.class))) {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.ACCEPTED_VALUE,
            validationFailureStatusCodes.getAcceptedValueNotExist()), HttpStatus.BAD_REQUEST);
      }
      materialAcceptedValueService.updateMaterialAcceptedValue(
          mapper.map(materialAcceptedValueRequestDto, MaterialAcceptedValue.class));
      materialAcceptedValueService
          .upDateTesConfigureType(materialAcceptedValueRequestDto.getTestConfigureId());
      return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
          Constants.UPDATE_MATERIAL_ACCEPTED_VALUE_SUCCESS), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_ACCEPTED_VALUE,
        validationFailureStatusCodes.getAcceptedValueNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.MATERIAL_ACCEPTED_VALUE_BY_TEST_CONFIGURE_ID)
  public ResponseEntity<Object> getMaterialAcceptedValueByTestConfigureId(
      @PathVariable Long testConfigureId) {
    if (testConfigureService.isTestConfigureExist(testConfigureId)) {
      return new ResponseEntity<>(
          new ContentResponse<>(Constants.TEST_CONFIGURE,
              mapper.map(
                  materialAcceptedValueService.getMaterialAcceptedValueByTestConfigure(
                      testConfigureService.getTestConfigureById(testConfigureId)),
                  MaterialAcceptedValueResponseDto.class),
              RestApiResponseStatus.OK),
          HttpStatus.OK);
    } else {
      logger.debug("No AcceptedValue record exist for given Test type id");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_CONFIGURE_ID,
          validationFailureStatusCodes.getAcceptedValueNotExist()), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(value = EndpointURI.MATERIAL_ACCEPTED_VALUE_DTO_BY_TEST_CONFIGURE_ID)
  public ResponseEntity<Object> getAcceptedValueByTestConfigureId(
      @PathVariable Long testConfigureId) {
    if (materialAcceptedValueService.ExistsTestConfigureId(testConfigureId)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_CONFIGURE,
          materialAcceptedValueService.findByTestConfigureId(testConfigureId),
          RestApiResponseStatus.OK), HttpStatus.OK);
    } else {
      logger.debug("No AcceptedValue record exist for given Test type id");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_CONFIGURE_ID,
          validationFailureStatusCodes.getAcceptedValueNotExist()), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(value = EndpointURI.MATERIAL_ACCEPTED_VALUE_BY_RAW_MATERIAL)
  public ResponseEntity<Object> getAcceptedValueByRawMaterial(@PathVariable Long testConfigureId) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_CONFIGURE,
        mapper.map(materialAcceptedValueService.findRawMaterialByTestConfigureId(testConfigureId),
            RawMaterialResponseDto.class),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.MATERIAL_SUB_CATEGORIES_BY_TEST_CONFIGURE_ID)
  public ResponseEntity<Object> getMaterialSubCategory(@PathVariable Long testConfigureId) {
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.MATERIAL_SUB_CATEGORIES,
            mapper.map(materialAcceptedValueService.getMaterialSubCategoryByTesConfigureId(
                testConfigureId), MaterialSubCategoryResponseDto.class),
            RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.RAW_MATERIALS_BY_TEST_CONFIGURE_ID)
  public ResponseEntity<Object> getRawMaterial(@PathVariable Long testConfigureId) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.RAW_MATERIAL,
        mapper.map(materialAcceptedValueService.getRawMaterialByTesConfigureId(testConfigureId),
            RawMaterialResponseDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.RAW_MATERIALS_BY_TEST_CONFIGURE_ID_AND_TEST_PARAMETERS)
  public ResponseEntity<Object> getRawMaterialByTestConfigureIdAndTestParameterId(
      @PathVariable Long testConfigureId, @PathVariable Long testParameterId) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.RAW_MATERIAL,
        mapper.map(materialAcceptedValueService.getRawMaterialByTesConfigureIdAndTestParameterId(
            testConfigureId, testParameterId), RawMaterialResponseDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.MATERIAL_SUB_CATEGORIES_BY_TEST_CONFIGURE_ID_AND_TEST_PARAMETERS)
  public ResponseEntity<Object> getMaterialSubCategoryByTestConfigureIdAndTestParameterId(
      @PathVariable Long testConfigureId, @PathVariable Long testParameterId) {
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.MATERIAL_SUB_CATEGORIES,
            mapper.map(materialAcceptedValueService
                .getMaterialSubCategoryByTesConfigureIdAndTestParameterId(testConfigureId,
                    testParameterId),
                MaterialSubCategoryResponseDto.class),
            RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.SEARCH_MATERIAL_ACCEPTED_VALUE)
  public ResponseEntity<Object> searchMaterialAcceptedValues(@PathVariable Long testConfigId,
      @PathVariable CategoryAcceptedType categoryAcceptedType,
      @RequestParam(name = "testParamName", required = false) String testParamName,
      @RequestParam(name = "condition", required = false) Condition condition,
      @RequestParam(name = "materialName", required = false) String materialName,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(0, 0, totalpage, 0l);
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    return new ResponseEntity<>(new PaginatedContentResponse<>(Constants.TEST_CONFIGURE,
        materialAcceptedValueService.searchAcceptedValue(testConfigId, categoryAcceptedType,
            testParamName, condition, materialName, booleanBuilder, totalpage, size, pageable,
            pagination),
        RestApiResponseStatus.OK, pagination), null, HttpStatus.OK);
  }
}

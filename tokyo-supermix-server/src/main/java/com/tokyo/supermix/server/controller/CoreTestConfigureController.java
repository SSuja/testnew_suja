package com.tokyo.supermix.server.controller;

import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.CoreTestConfigureDto;
import com.tokyo.supermix.data.dto.TestOriginRequestDto;
import com.tokyo.supermix.data.entities.CoreTestConfigure;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.server.services.CoreTestConfigureService;
import com.tokyo.supermix.util.Constants;

@RestController
@CrossOrigin
public class CoreTestConfigureController {
  @Autowired
  private CoreTestConfigureService coreTestConfigureService;
  @Autowired
  private Mapper mapper;
  private static final Logger logger = LoggerFactory.getLogger(CoreTestConfigureController.class);

  @PostMapping(value = EndpointURI.CORE_TEST_CONFIGURE)
  public ResponseEntity<Object> createCoreTestConfigure(
      @Valid @RequestBody List<CoreTestConfigureDto> coreTestConfigureDtoList) {
    coreTestConfigureService
        .saveCoreTestConfigure(mapper.map(coreTestConfigureDtoList, CoreTestConfigure.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_CORE_TEST_CONFIGURE_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.CORE_TEST_CONFIGURES)
  public ResponseEntity<Object> getAllCoreTestConfigure() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.CORE_TEST_CONFIGURES,
        mapper.map(coreTestConfigureService.getAllCoreTestConfigure(), CoreTestConfigureDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.CORE_TEST_CONFIGURE_BY_TEST_CONFIGURE_ID)
  public ResponseEntity<Object> getCoreTestConfigureByTestConfigureId(
      @PathVariable Long testConfigureId) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.CORE_TEST_CONFIGURES,
        mapper.map(coreTestConfigureService.getCoreTestConfigureByTestConfigureId(testConfigureId),
            CoreTestConfigureDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.CORE_TEST_CONFIGURE_BY_RAWMATERIAL_ID)
  public ResponseEntity<Object> getCoreTestConfigureByMaterialSubCategoryAndTestType(
      @PathVariable Long rawMaterialId) {
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.CORE_TEST_CONFIGURES,
            mapper.map(coreTestConfigureService
                .getCoreTestConfigureByRawMaterialIdAndApplicableTest(rawMaterialId),
                CoreTestConfigureDto.class),
            RestApiResponseStatus.OK),
        HttpStatus.OK);
  }

  @PutMapping(value = EndpointURI.CORE_TEST_CONFIGURE)
  public ResponseEntity<Object> updateAcceptedValue(
      @Valid @RequestBody List<CoreTestConfigureDto> coreTestConfigureDtoList) {

    coreTestConfigureService
        .saveCoreTestConfigure(mapper.map(coreTestConfigureDtoList, CoreTestConfigure.class));

    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_CORE_TEST_CONFIGURE_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.LIST_CORE_TEST_CONFIGURE_BY_TEST_CONFIGURE_ID)
  public ResponseEntity<Object> getCoreTestConfigureListByTestConfigureId(
      @PathVariable Long testConfigureId) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.CORE_TEST_CONFIGURES,
        coreTestConfigureService.getAllCoreTestConfigureByTestConfigureId(testConfigureId),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.LIST_ALL_CORE_TEST_CONFIGURE_BY_TEST_CONFIGURE_ID)
  public ResponseEntity<Object> getCoreTestConfigureListByTestId(@PathVariable Long testId) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.CORE_TEST_CONFIGURES,
        coreTestConfigureService.getAllCoreTestConfigureByTestId(testId), RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.CORE_TEST_CONFIGURE_BY_MATERIAL_CATEGORY_ID)
  public ResponseEntity<Object> getCoreTestConfigureListByMainCatId(
      @PathVariable Long mainCategoryId) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.CORE_TEST_CONFIGURES,
        coreTestConfigureService.getAllCoreTestConfigureByMainCategoryId(mainCategoryId),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.CORE_TEST_CONFIGURE_BY_MATERIAL_SUB_CATEGORY_ID)
  public ResponseEntity<Object> getCoreTestConfigureListByMaterialSubCategoryId(
      @PathVariable Long materialSubCategoryId) {
    return new ResponseEntity<>(new ContentResponse<>(
        Constants.CORE_TEST_CONFIGURES, coreTestConfigureService
            .getAllCoreTestConfigureByMaterialSubCategoryId(materialSubCategoryId),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.CORE_TEST_CONFIGURE_BY_RAW_MATERIAL_ID)
  public ResponseEntity<Object> getCoreTestConfigureListByRawMaterialId(
      @PathVariable Long rawMaterialId) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.CORE_TEST_CONFIGURES,
        coreTestConfigureService.getAllCoreTestConfigureByRawMaterialId(rawMaterialId),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @PutMapping(value = EndpointURI.CORE_TEST_CONFIGURE_TEST_ORIGIN)
  public ResponseEntity<Object> updateCoreTestOrigin(
      @Valid @RequestBody List<TestOriginRequestDto> testOriginRequestDtolist) {

    coreTestConfigureService.testOriginChangeStatus(testOriginRequestDtolist);
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_CORE_TEST_CONFIGURE_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.CORE_TEST_CONFIGURE_SEARCH)
  public ResponseEntity<Object> getRawMaterialSearch(@PathVariable Long testConfigureId,
      @RequestParam(name = "rawMaterialName", required = false) String rawMaterialName,
      @RequestParam(name = "materialSubCategoryId", required = false) Long materialSubCategoryId) {

    int totalpage = 0;
    Pagination pagination = new Pagination(0, 0, totalpage, 0l);
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    return new ResponseEntity<>(
        new PaginatedContentResponse<>(Constants.CORE_TEST_CONFIGURES,
            coreTestConfigureService.searchRawMaterial(booleanBuilder, testConfigureId,
                rawMaterialName, materialSubCategoryId),
            RestApiResponseStatus.OK, pagination),
        null, HttpStatus.OK);
  }

  @PutMapping(value = EndpointURI.CORE_TEST_CONFIGURE_UPDATE)
  public ResponseEntity<Object> updateCoreTestConfigure(
      @Valid @RequestBody List<CoreTestConfigureDto> coreTestConfigureDtoList) {
    coreTestConfigureService
        .updateCoreTestConfigure(mapper.map(coreTestConfigureDtoList, CoreTestConfigure.class));

    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_CORE_TEST_CONFIGURE_SUCCESS),
        HttpStatus.OK);
  }
}

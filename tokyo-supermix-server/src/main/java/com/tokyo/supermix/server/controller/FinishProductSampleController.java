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
import com.tokyo.supermix.data.dto.FinishProductSampleRequestDto;
import com.tokyo.supermix.data.dto.FinishProductSampleResponseDto;
import com.tokyo.supermix.data.entities.FinishProductSample;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.security.CurrentUser;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.FinishProductSampleService;
import com.tokyo.supermix.server.services.PlantService;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;
import com.tokyo.supermix.util.privilege.PermissionConstants;

@CrossOrigin
@RestController
public class FinishProductSampleController {
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  @Autowired
  private FinishProductSampleService finishProductSampleService;
  @Autowired
  private PlantService plantService;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;
  private static final Logger logger = Logger.getLogger(FinishProductSampleController.class);

  @PostMapping(value = EndpointURI.FINISH_PRODUCT_SAMPLE)
  public ResponseEntity<Object> createFinishProductSample(
      @Valid @RequestBody FinishProductSampleRequestDto finishProductSampleRequestDto) {
    if (finishProductSampleService
        .isFinishProductCodeExist(finishProductSampleRequestDto.getFinishProductCode())) {
      logger.debug(
          "finish product sample code is already exists: createFinishProductSample(), isFinishProductCodeExist: {}");
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.FINISH_PRODUCT_CODE,
              validationFailureStatusCodes.getFinishProductSampleAlreadyExist()),
          HttpStatus.BAD_REQUEST);
    }
    finishProductSampleService.saveFinishProductSample(
        mapper.map(finishProductSampleRequestDto, FinishProductSample.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_FINISH_PRODUCT_SAMPLE_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.FINISH_PRODUCT_SAMPLES)
  public ResponseEntity<Object> getAllFinishProductSamples() {
    logger.debug("get all finish product samples");
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.FINISH_PRODUCT_SAMPLES,
            mapper.map(finishProductSampleService.getAllFinishProductSamples(),
                FinishProductSampleResponseDto.class),
            RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.FINISH_PRODUCT_SAMPLE_BY_PLANT)
  public ResponseEntity<Object> getAllFinishProductSamplesByPlant(
      @CurrentUser UserPrincipal currentUser, @PathVariable String plantCode,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(page, size, totalpage, 0l);
    if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
      pagination.setTotalRecords(finishProductSampleService.getCountFinishProductSample());
      return new ResponseEntity<>(
          new PaginatedContentResponse<>(Constants.FINISH_PRODUCT_SAMPLES,
              mapper.map(finishProductSampleService.getAllFinishProductSamplesByPlant(currentUser,
                  pageable), FinishProductSampleResponseDto.class),
              RestApiResponseStatus.OK, pagination),
          HttpStatus.OK);
    }
    if (currentUserPermissionPlantService.getPermissionPlantCodeByCurrentUser(currentUser,
        PermissionConstants.VIEW_FINISH_PRODUCT_SAMPLE).contains(plantCode)) {
      pagination.setTotalRecords(
          finishProductSampleService.getCountFinishProductSampleByPlantCode(plantCode));
      return new ResponseEntity<>(
          new PaginatedContentResponse<>(Constants.FINISH_PRODUCT_SAMPLES,
              mapper.map(
                  finishProductSampleService.getFinishProductSampleByPlantCode(plantCode, pageable),
                  FinishProductSampleResponseDto.class),
              RestApiResponseStatus.OK, pagination),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.FINISH_PRODUCT_SAMPLE_BY_ID)
  public ResponseEntity<Object> getFinishProductSampleById(@PathVariable String code) {
    if (finishProductSampleService.isFinishProductSampleExist(code)) {
      logger.debug("Get Finish Product Sample By Id");
      return new ResponseEntity<>(new ContentResponse<>(Constants.FINISH_PRODUCT_SAMPLE,
          mapper.map(finishProductSampleService.getFinishProductSampleById(code),
              FinishProductSampleResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.FINISH_PRODUCT_SAMPLE_ID,
        validationFailureStatusCodes.getFinishProductSampleNotExist()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(value = EndpointURI.FINISH_PRODUCT_SAMPLE_BY_ID)
  public ResponseEntity<Object> deleteFinishProductSample(@PathVariable String code) {
    if (finishProductSampleService.isFinishProductSampleExist(code)) {
      logger.debug("delete Finish Product Sample by id");
      finishProductSampleService.deleteFinishProductSample(code);;
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.FINISH_PRODUCT_SAMPLE_DELETED),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.FINISH_PRODUCT_SAMPLE_ID,
        validationFailureStatusCodes.getFinishProductSampleNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.FINISH_PRODUCT_SAMPLE)
  public ResponseEntity<Object> updateFinishProductSample(
      @Valid @RequestBody FinishProductSampleRequestDto finishProductSampleRequestDto) {
    if (finishProductSampleService
        .isFinishProductSampleExist(finishProductSampleRequestDto.getCode())) {
      if (finishProductSampleService.isUpdatedFinishProductCodeExist(
          finishProductSampleRequestDto.getCode(),
          finishProductSampleRequestDto.getFinishProductCode())) {
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.FINISH_PRODUCT_CODE,
                validationFailureStatusCodes.getFinishProductSampleAlreadyExist()),
            HttpStatus.BAD_REQUEST);
      }
      finishProductSampleService.updateFinishProductSample(
          mapper.map(finishProductSampleRequestDto, FinishProductSample.class));
      return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
          Constants.UPDATE_FINISH_PRODUCT_SAMPLE_SUCCESS), HttpStatus.OK);
    }
    return new ResponseEntity<>(
        new ValidationFailureResponse(Constants.FINISH_PRODUCT_SAMPLE_ID,
            validationFailureStatusCodes.getFinishProductSampleAlreadyExist()),
        HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.FINISH_PRODUCT_SAMPLE_BY_MIX_DESIGN_CODE)
  public ResponseEntity<Object> getFinishProductSampleByMixDesignCode(
      @PathVariable String mixDesignCode) {
    if (finishProductSampleService.isMixDesignCodeExist(mixDesignCode)) {
      logger.debug("Get Finish Product Sample By Mix Design Code");
      return new ResponseEntity<>(new ContentResponse<>(Constants.FINISH_PRODUCT_SAMPLES,
          mapper.map(
              finishProductSampleService.getFinishProductSampleByMixDesignCode(mixDesignCode),
              FinishProductSampleResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MIX_DESIGN_CODE,
        validationFailureStatusCodes.getMixDesignNotExist()), HttpStatus.BAD_REQUEST);
  }

  // @GetMapping(value = EndpointURI.FINISH_PRODUCT_SAMPLE_SEARCH)
  // public ResponseEntity<Object> getFinishProductSampleSearch(
  // @QuerydslPredicate(root = FinishProductSample.class) Predicate predicate,
  // @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
  // return new ResponseEntity<>(new ContentResponse<>(Constants.FINISH_PRODUCT_SAMPLES,
  // finishProductSampleService.searchFinishProductSample(predicate, page, size),
  // RestApiResponseStatus.OK), null, HttpStatus.OK);
  // }

  @GetMapping(value = EndpointURI.FINISH_PRODUCT_SAMPLES_BY_PLANT_CODE)
  public ResponseEntity<Object> getFinishProductSampleByPlantCode(@PathVariable String plantCode,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(page, size, totalpage, 0l);
    if (plantService.isPlantExist(plantCode)) {
      pagination.setTotalRecords(
          finishProductSampleService.getCountFinishProductSampleByPlantCode(plantCode));
      return new ResponseEntity<>(
          new PaginatedContentResponse<>(Constants.FINISH_PRODUCT_SAMPLES,
              mapper.map(
                  finishProductSampleService.getFinishProductSampleByPlantCode(plantCode, pageable),
                  FinishProductSampleResponseDto.class),
              RestApiResponseStatus.OK, pagination),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.FINISH_PRODUCT_SAMPLE_BY_STATUS)
  public ResponseEntity<Object> getFinishProductSampleByStatus(@PathVariable Status status) {
    if (finishProductSampleService.isFinishProductSampleStatusExist(status)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.FINISH_PRODUCT_SAMPLES,
          mapper.map(finishProductSampleService.getFinishProductSampleByStatus(status),
              FinishProductSampleResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    } else {
      logger.debug("No Finish Product Sample record exist for given Status");
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.FINISH_PRODUCT_SAMPLES,
              validationFailureStatusCodes.getFinishProductSampleNotExist()),
          HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(value = EndpointURI.FINISH_PRODUCT_SAMPLE_SEARCH)
  public ResponseEntity<Object> getFinishProductSearch(@PathVariable String plantCode,
      @RequestParam(name = "finishProductCode", required = false) String finishProductCode,
      @RequestParam(name = "equipmentName", required = false) String equipmentName,
      @RequestParam(name = "plantName", required = false) String plantName,
      @RequestParam(name = "mixDesignCode", required = false) String mixDesignCode,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(0, 0, totalpage, 0l);
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    return new ResponseEntity<>(new PaginatedContentResponse<>(Constants.FINISH_PRODUCT_SAMPLES,
        finishProductSampleService.searchFinishProductSample(booleanBuilder, finishProductCode,
            equipmentName, mixDesignCode, plantName, plantCode, pageable, pagination),
        RestApiResponseStatus.OK, pagination), null, HttpStatus.OK);

  }

  @GetMapping(value = EndpointURI.RAW_FINISH_PRODUCT_SAMPLES_BY_MATERIAL_SUBCATORY_AND_PLANT)
  public ResponseEntity<Object> getFinishProductSamplesBySubCategoryAndCurrentUserPermission(
      @CurrentUser UserPrincipal currentUser, @PathVariable Long materialSubCategoryId,
      @PathVariable String plantCode, @RequestParam(name = "page") int page,
      @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(page, size, totalpage, 0l);
    if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
      pagination.setTotalRecords(
          finishProductSampleService.getSubCategoryCountFinishProductSample(materialSubCategoryId));
      return new ResponseEntity<>(new PaginatedContentResponse<>(Constants.FINISH_PRODUCT_SAMPLES,
          mapper.map(finishProductSampleService.getFinishProductSamplesBySubCategoryId(
              materialSubCategoryId, pageable), FinishProductSampleResponseDto.class),
          RestApiResponseStatus.OK, pagination), HttpStatus.OK);
    } else {
      if (currentUserPermissionPlantService.getPermissionPlantCodeByCurrentUser(currentUser,
          PermissionConstants.VIEW_FINISH_PRODUCT_SAMPLE).contains(plantCode)) {
        pagination.setTotalRecords(finishProductSampleService
            .getCountSubCategoryFinishProductSampleByPlantCode(plantCode, materialSubCategoryId));
        return new ResponseEntity<>(
            new PaginatedContentResponse<>(Constants.FINISH_PRODUCT_SAMPLES,
                mapper.map(
                    finishProductSampleService.getFinishProductSamplesBySubCategoryIdAndPlantCode(
                        materialSubCategoryId, plantCode, pageable),
                    FinishProductSampleResponseDto.class),
                RestApiResponseStatus.OK, pagination),
            HttpStatus.OK);
      }
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }


  @GetMapping(value = EndpointURI.RAW_FINISH_PRODUCT_SAMPLES_BY_MATERIAL_CATORY_AND_PLANT)
  public ResponseEntity<Object> getFinishProductSamplesByCategoryAndCurrentUserPermission(
      @CurrentUser UserPrincipal currentUser, @PathVariable Long materialCategoryId,
      @PathVariable String plantCode, @RequestParam(name = "page") int page,
      @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(page, size, totalpage, 0l);
    if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
      pagination.setTotalRecords(
          finishProductSampleService.getCategoryCountFinishProductSample(materialCategoryId));
      return new ResponseEntity<>(new PaginatedContentResponse<>(Constants.FINISH_PRODUCT_SAMPLES,
          mapper.map(finishProductSampleService.getFinishProductSamplesByCategoryId(
              materialCategoryId, pageable), FinishProductSampleResponseDto.class),
          RestApiResponseStatus.OK, pagination), HttpStatus.OK);
    } else {
      if (currentUserPermissionPlantService.getPermissionPlantCodeByCurrentUser(currentUser,
          PermissionConstants.VIEW_FINISH_PRODUCT_SAMPLE).contains(plantCode)) {
        pagination.setTotalRecords(finishProductSampleService
            .getCountCategoryFinishProductSampleByPlantCode(plantCode, materialCategoryId));
        return new ResponseEntity<>(
            new PaginatedContentResponse<>(Constants.FINISH_PRODUCT_SAMPLES,
                mapper.map(
                    finishProductSampleService.getFinishProductSamplesByCategoryIdAndPlantCode(
                        materialCategoryId, plantCode, pageable),
                    FinishProductSampleResponseDto.class),
                RestApiResponseStatus.OK, pagination),
            HttpStatus.OK);
      }
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }
  
  @GetMapping(value = EndpointURI.RAW_FINISH_PRODUCT_SAMPLES_BY_RAW_MATERIAL_AND_PLANT)
  public ResponseEntity<Object> getFinishProductSamplesByRawMaterialAndCurrentUserPermission(
      @CurrentUser UserPrincipal currentUser, @PathVariable Long rawMaterialId,
      @PathVariable String plantCode, @RequestParam(name = "page") int page,
      @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(page, size, totalpage, 0l);
    if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
      pagination.setTotalRecords(
          finishProductSampleService.getRawMaterialCountFinishProductSample(rawMaterialId));
      return new ResponseEntity<>(new PaginatedContentResponse<>(Constants.FINISH_PRODUCT_SAMPLES,
          mapper.map(finishProductSampleService.getFinishProductSamplesByRawMaterialId(
              rawMaterialId, pageable), FinishProductSampleResponseDto.class),
          RestApiResponseStatus.OK, pagination), HttpStatus.OK);
    } else {
      if (currentUserPermissionPlantService.getPermissionPlantCodeByCurrentUser(currentUser,
          PermissionConstants.VIEW_FINISH_PRODUCT_SAMPLE).contains(plantCode)) {
        pagination.setTotalRecords(finishProductSampleService
            .getCountRawMaterialFinishProductSampleByPlantCode(plantCode, rawMaterialId));
        return new ResponseEntity<>(
            new PaginatedContentResponse<>(Constants.FINISH_PRODUCT_SAMPLES,
                mapper.map(
                    finishProductSampleService.getFinishProductSamplesByRawMaterialIdAndPlantCode(
                        rawMaterialId, plantCode, pageable),
                    FinishProductSampleResponseDto.class),
                RestApiResponseStatus.OK, pagination),
            HttpStatus.OK);
      }
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }
}

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
import com.tokyo.supermix.data.dto.IncomingSampleRequestDto;
import com.tokyo.supermix.data.dto.IncomingSampleResponseDto;
import com.tokyo.supermix.data.entities.IncomingSample;
import com.tokyo.supermix.data.enums.RawMaterialSampleType;
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
import com.tokyo.supermix.server.services.IncomingSampleService;
import com.tokyo.supermix.server.services.MaterialSubCategoryService;
import com.tokyo.supermix.server.services.SupplierService;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;
import com.tokyo.supermix.util.privilege.PermissionConstants;

@CrossOrigin(origins = "*")
@RestController
public class IncomingSampleController {
  @Autowired
  private IncomingSampleService incomingSampleService;
  @Autowired
  ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  @Autowired
  private MaterialSubCategoryService materialSubCategoryService;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;
  @Autowired
  private SupplierService supplierService;
  private static final Logger logger = Logger.getLogger(IncomingSampleController.class);

  @GetMapping(value = EndpointURI.INCOMING_SAMPLES)
  public ResponseEntity<Object> getIncomingSamples() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.INCOMING_SAMPLES,
        mapper.map(incomingSampleService.getAllIncomingSamples(), IncomingSampleResponseDto.class),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }

  // @GetMapping(value = EndpointURI.INCOMING_SAMPLE_BY_PLANT)
  // public ResponseEntity<Object> getIncomingSamplesByUserPermission(
  // @CurrentUser UserPrincipal currentUser, @PathVariable String plantCode,
  // @RequestParam(name = "page") int page, @RequestParam(name = "size") int size)
  // {
  // Pageable pageable = PageRequest.of(page, size);
  // int totalpage = 0;
  // Pagination pagination = new Pagination(page, size, totalpage, 0l);
  // if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
  // pagination.setTotalRecords(incomingSampleService.getCountIncomingSample());
  // return new ResponseEntity<>(
  // new PaginatedContentResponse<>(Constants.INCOMING_SAMPLES,
  // mapper.map(
  // incomingSampleService.getAllIncomingSamplesByCurrentUser(currentUser,
  // pageable),
  // IncomingSampleResponseDto.class),
  // RestApiResponseStatus.OK, pagination),
  // HttpStatus.OK);
  // }
  // if (currentUserPermissionPlantService
  // .getPermissionPlantCodeByCurrentUser(currentUser,
  // PermissionConstants.VIEW_INCOMING_SAMPLE)
  // .contains(plantCode)) {
  // pagination
  // .setTotalRecords(incomingSampleService.getCountIncomingSampleByPlantCode(plantCode));
  // return new ResponseEntity<>(
  // new PaginatedContentResponse<>(Constants.INCOMING_SAMPLES,
  // mapper.map(incomingSampleService.getIncomingSampleByPlantCode(plantCode,
  // pageable),
  // IncomingSampleResponseDto.class),
  // RestApiResponseStatus.OK, pagination),
  // HttpStatus.OK);
  // }
  // return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
  // validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  // }

  @GetMapping(value = EndpointURI.INCOMING_SAMPLE_BY_PLANT)
  public ResponseEntity<Object> getIncomingSamplesByUserPermission(
      @CurrentUser UserPrincipal currentUser,
      @PathVariable RawMaterialSampleType rawMaterialSampleType, @PathVariable String plantCode,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(page, size, totalpage, 0l);
    if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
      pagination.setTotalRecords(
          incomingSampleService.countAllSampleByRawMaterialSampleType(rawMaterialSampleType));
      return new ResponseEntity<>(new PaginatedContentResponse<>(Constants.INCOMING_SAMPLES,
          mapper.map(incomingSampleService
              .findAllByPlantCodeAndRawMaterialSampleTypeInOrderByUpdatedAtDesc(currentUser,
                  rawMaterialSampleType, pageable),
              IncomingSampleResponseDto.class),
          RestApiResponseStatus.OK, pagination), HttpStatus.OK);
    }
    if (currentUserPermissionPlantService
        .getPermissionPlantCodeByCurrentUser(currentUser, PermissionConstants.VIEW_INCOMING_SAMPLE)
        .contains(plantCode)) {
      pagination.setTotalRecords(incomingSampleService
          .countByRawMaterialSampleTypeAndPlantCode(rawMaterialSampleType, plantCode));
      return new ResponseEntity<>(new PaginatedContentResponse<>(Constants.INCOMING_SAMPLES,
          mapper.map(incomingSampleService.findByRawMaterialSampleTypeAndPlantCodeByDecending(
              rawMaterialSampleType, plantCode, pageable), IncomingSampleResponseDto.class),
          RestApiResponseStatus.OK, pagination), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.INCOMING_SAMPLE_BY_CODE)
  public ResponseEntity<Object> getIncomingSampleByCode(@PathVariable String code) {
    if (incomingSampleService.isIncomingSampleExist(code)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.INCOMING_SAMPLE, mapper
          .map(incomingSampleService.getIncomingSampleById(code), IncomingSampleResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.INCOMING_SAMPLE_CODE,
        validationFailureStatusCodes.getIncomingSampleNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PostMapping(value = EndpointURI.INCOMING_SAMPLE)
  public ResponseEntity<Object> createIncomingSample(
      @Valid @RequestBody IncomingSampleRequestDto incomingSampleRequestDto) {
    if (incomingSampleService.isIncomingSampleExist(incomingSampleRequestDto.getCode())) {
      logger.debug(
          "IncomingSampleCode already exists: createIncomingSample(), IncomingSampleCode: {}");
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.INCOMING_SAMPLE_CODE,
              validationFailureStatusCodes.getIncomingSampleAlreadyExist()),
          HttpStatus.BAD_REQUEST);
    }
    incomingSampleService
        .createIncomingSample(mapper.map(incomingSampleRequestDto, IncomingSample.class));
    String successMesagge = "";
    if (incomingSampleRequestDto.getRawMaterialSampleType()
        .equals(RawMaterialSampleType.INCOMING_SAMPLE)) {
      successMesagge = Constants.ADD_INCOMING_SAMPLE_SUCCESS;
    } else {
      successMesagge = Constants.ADD_PROCESS_SAMPLE_SUCCESS;
    }
    return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK, successMesagge),
        HttpStatus.OK);
  }

  @PutMapping(value = EndpointURI.INCOMING_SAMPLE)
  public ResponseEntity<Object> updateIncomingSample(
      @Valid @RequestBody IncomingSampleRequestDto incomingSampleRequestDto) {
    if (incomingSampleService.isIncomingSampleExist(incomingSampleRequestDto.getCode())) {
      incomingSampleService
          .updateIncomingSample(mapper.map(incomingSampleRequestDto, IncomingSample.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_INCOMING_SAMPLE_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.INCOMING_SAMPLE_CODE,
        validationFailureStatusCodes.getIncomingSampleNotExist()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(value = EndpointURI.INCOMING_SAMPLE_BY_CODE)
  public ResponseEntity<Object> deleteIncomingSampleByCode(@PathVariable String code) {
    if (incomingSampleService.isIncomingSampleExist(code)) {
      incomingSampleService.deleteIncomingSample(code);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.INCOMING_SAMPLE_DELETED),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.INCOMING_SAMPLE_CODE,
        validationFailureStatusCodes.getIncomingSampleNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.INCOMING_SAMPLE_BY_CODE_AND_STATUS)
  public ResponseEntity<Object> updateStatusIncomingSample(@PathVariable String code,
      @PathVariable Status status) {
    if (incomingSampleService.isIncomingSampleExist(code)) {
      incomingSampleService.updateStatusForIncomingSample(code, status);
      return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
          Constants.UPDATE_STATUS_INCOMING_SAMPLE_SUCCESS), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.INCOMING_SAMPLE_CODE,
        validationFailureStatusCodes.getIncomingSampleNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.INCOMING_SAMPLE_BY_STATUS)
  public ResponseEntity<Object> getIncomingSampleByStatus(@PathVariable Status status) {
    if (incomingSampleService.isIncomingSampleStatusExist(status)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.INCOMING_SAMPLE,
          mapper.map(incomingSampleService.getIncomingSampleByStatus(status),
              IncomingSampleResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.INCOMING_SAMPLE_STATUS,
        validationFailureStatusCodes.getIncomingSampleNotExist()), HttpStatus.BAD_REQUEST);
  }

  // @GetMapping(value = EndpointURI.INCOMING_SAMPLES_BY_PLANT_CODE)
  // public ResponseEntity<Object> getIncomingSampleByPlantCode(@PathVariable
  // String plantCode) {
  // if (plantService.isPlantExist(plantCode)) {
  // return new ResponseEntity<>(new ContentResponse<>(Constants.INCOMING_SAMPLES,
  // mapper.map(incomingSampleService.getIncomingSampleByPlantCode(plantCode),
  // IncomingSampleResponseDto.class),
  // RestApiResponseStatus.OK), HttpStatus.OK);
  // }
  // return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
  // validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  // }

  @GetMapping(value = EndpointURI.GET_INCOMING_SAMPLES_BY_MATERIAL_SUB_CATEGORY)
  public ResponseEntity<Object> getIncomingSampleMaterialSubCategory(
      @PathVariable Long materialSubCategoryId, @PathVariable String plantCode,
      @RequestParam(name = "code") String code) {
    if (materialSubCategoryService.isMaterialSubCategoryExist(materialSubCategoryId)) {
      if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
        return new ResponseEntity<>(new ContentResponse<>(Constants.INCOMING_SAMPLES,
            mapper.map(incomingSampleService.getByMaterialSubCategory(materialSubCategoryId, code),
                IncomingSampleResponseDto.class),
            RestApiResponseStatus.OK), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(new ContentResponse<>(Constants.INCOMING_SAMPLES,
            mapper.map(incomingSampleService.getByMaterialSubCategoryPlantWise(
                materialSubCategoryId, plantCode, code), IncomingSampleResponseDto.class),
            RestApiResponseStatus.OK), HttpStatus.OK);
      }
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.INCOMING_SAMPLE_CODE,
        validationFailureStatusCodes.getIncomingSampleNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.GET_INCOMING_SAMPLE_BY_PLANT_CODE)
  public ResponseEntity<Object> getCustomerNameSearch(@PathVariable String plantCode,
      @RequestParam(name = "code") String code) {
    if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.INCOMING_SAMPLES, mapper
          .map(incomingSampleService.getIncomingSampleCode(code), IncomingSampleResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ContentResponse<>(Constants.INCOMING_SAMPLES,
        mapper.map(incomingSampleService.getIncomingSampleCodeByPlantCode(plantCode, code),
            IncomingSampleResponseDto.class),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.INCOMING_SAMPLE_SEARCH)
  public ResponseEntity<Object> getIncomingSampleSearch(@PathVariable String plantCode,
      @RequestParam(name = "code", required = false) String code,
      @RequestParam(name = "date", required = false) String date,
      @RequestParam(name = "status", required = false) Status status,
      @RequestParam(name = "rawMaterialName", required = false) String rawMaterialName,
      @RequestParam(name = "plantName", required = false) String plantName,
      @RequestParam(name = "supplierName", required = false) String supplierName,

      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    Pagination pagination = new Pagination(0, 0, 0, 0l);
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    return new ResponseEntity<>(
        new PaginatedContentResponse<>(Constants.INCOMING_SAMPLE,
            mapper.map(
                incomingSampleService.searchIncomingSample(code, date, status, rawMaterialName,
                    plantName, supplierName, booleanBuilder, pageable,
                    RawMaterialSampleType.INCOMING_SAMPLE, plantCode, pagination),
                IncomingSampleResponseDto.class),
            RestApiResponseStatus.OK, pagination),
        null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.PROCESS_SAMPLE_SEARCH_TYPE)
  public ResponseEntity<Object> getProcessSampleSearch(@PathVariable String plantCode,
      @RequestParam(name = "code", required = false) String code,
      @RequestParam(name = "date", required = false) String date,
      @RequestParam(name = "status", required = false) Status status,
      @RequestParam(name = "rawMaterialName", required = false) String rawMaterialName,
      @RequestParam(name = "plantName", required = false) String plantName,
      @RequestParam(name = "supplierName", required = false) String supplierName,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    Pagination pagination = new Pagination(0, 0, 0, 0l);
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    return new ResponseEntity<>(
        new PaginatedContentResponse<>(Constants.INCOMING_SAMPLE,
            mapper.map(
                incomingSampleService.searchIncomingSample(code, date, status, rawMaterialName,
                    plantName, supplierName, booleanBuilder, pageable,
                    RawMaterialSampleType.PROCESS_SAMPLE, plantCode, pagination),
                IncomingSampleResponseDto.class),
            RestApiResponseStatus.OK, pagination),
        null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.INCOMING_SAMPLES_BY_MATERIAL_SUB_CATEGORY)
  public ResponseEntity<Object> getIncomingSampleMaterialSubCategory(
      @PathVariable Long materialSubCategoryId, @PathVariable String plantCode) {
    if (materialSubCategoryService.isMaterialSubCategoryExist(materialSubCategoryId)) {
      if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
        return new ResponseEntity<>(new ContentResponse<>(Constants.INCOMING_SAMPLES,
            mapper.map(incomingSampleService.getByMaterialSubCategory(materialSubCategoryId),
                IncomingSampleResponseDto.class),
            RestApiResponseStatus.OK), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(new ContentResponse<>(Constants.INCOMING_SAMPLES,
            mapper.map(incomingSampleService.getByMaterialSubCategoryPlantWise(
                materialSubCategoryId, plantCode), IncomingSampleResponseDto.class),
            RestApiResponseStatus.OK), HttpStatus.OK);
      }
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.INCOMING_SAMPLE_CODE,
        validationFailureStatusCodes.getIncomingSampleNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.INCOMING_SAMPLES_BY_SUPPLIER_ID)
  public ResponseEntity<Object> getIncomingSampleBySupplierId(@PathVariable Long supplierId) {
    if (supplierService.isSupplierExist(supplierId)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.INCOMING_SAMPLES, mapper
          .map(incomingSampleService.getBySupplierId(supplierId), IncomingSampleResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.SUPPLIER,
        validationFailureStatusCodes.getSupplierNotExit()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.GET_SAMPLES_BY_TYPE)
  public ResponseEntity<Object> getSamplesByRawMaterialSample(
      @PathVariable RawMaterialSampleType rawMaterialSampleType,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(page, size, totalpage, 0l);
    if (incomingSampleService.isSampleExistsByRawMaterialSample(rawMaterialSampleType)) {
      pagination.setTotalRecords(
          incomingSampleService.countAllSampleByRawMaterialSampleType(rawMaterialSampleType));
      return new ResponseEntity<>(new PaginatedContentResponse<>(Constants.INCOMING_SAMPLES,
          incomingSampleService.findByRawMaterialSampleType(rawMaterialSampleType, pageable),
          RestApiResponseStatus.OK, pagination), HttpStatus.OK);
    }
    return new ResponseEntity<>(
        new ValidationFailureResponse(Constants.INCOMING_SAMPLE,
            validationFailureStatusCodes.getRawMaterialSampleTypeNotExists()),
        HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.INCOMING_SAMPLES_BY_MATERIAL_CATEGORY)
  public ResponseEntity<Object> getIncomingSampleMaterialCategory(
      @PathVariable Long materialCategoryId, @PathVariable String plantCode) {
    if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.INCOMING_SAMPLES,
          mapper.map(incomingSampleService.getByMaterialCategoryId(materialCategoryId),
              IncomingSampleResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(
          new ContentResponse<>(Constants.INCOMING_SAMPLES,
              mapper.map(incomingSampleService.getByMaterialCategoryPlantWise(materialCategoryId,
                  plantCode), IncomingSampleResponseDto.class),
              RestApiResponseStatus.OK),
          HttpStatus.OK);
    }
  }

  @GetMapping(value = EndpointURI.INCOMING_SAMPLES_BY_RAW_MATERIAL_ID)
  public ResponseEntity<Object> getIncomingSampleByRawMaterialId(@PathVariable Long rawMaterialId) {
    if (incomingSampleService.isRawMaterialExist(rawMaterialId)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.INCOMING_SAMPLES,
          mapper.map(incomingSampleService.getByRawMaterialId(rawMaterialId),
              IncomingSampleResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.RAW_MATERIAL,
        validationFailureStatusCodes.getRawMaterialNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.INCOMING_SAMPLES_BY_SAMPLE_TYPE)
  public ResponseEntity<Object> getIncomingSamplesBySampleType(
      @PathVariable RawMaterialSampleType rawMaterialSampleType, @PathVariable String plantCode) {
    if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.INCOMING_SAMPLES,
          mapper.map(incomingSampleService.getInComingSampleBySampleType(rawMaterialSampleType),
              IncomingSampleResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(new ContentResponse<>(Constants.INCOMING_SAMPLES,
          mapper.map(incomingSampleService.getInComingSampleBySampleTypeAndPlant(
              rawMaterialSampleType, plantCode), IncomingSampleResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
  }
}

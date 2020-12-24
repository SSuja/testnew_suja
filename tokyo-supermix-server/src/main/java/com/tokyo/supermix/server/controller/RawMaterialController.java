package com.tokyo.supermix.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import org.springframework.web.multipart.MultipartFile;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.config.export.EnrollWriter;
import com.tokyo.supermix.config.export.RawMaterialFillManager;
import com.tokyo.supermix.config.export.RawMaterialLayouter;
import com.tokyo.supermix.data.dto.RawMaterialRequestDto;
import com.tokyo.supermix.data.dto.RawMaterialResponseDto;
import com.tokyo.supermix.data.entities.MaterialSubCategory;
import com.tokyo.supermix.data.entities.RawMaterial;
import com.tokyo.supermix.data.enums.MainType;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.security.CurrentUser;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.FileStorageService;
import com.tokyo.supermix.server.services.MaterialSubCategoryService;
import com.tokyo.supermix.server.services.RawMaterialService;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.FileStorageConstants;
import com.tokyo.supermix.util.ValidationConstance;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;
import com.tokyo.supermix.util.privilege.PermissionConstants;

@RestController
@CrossOrigin
public class RawMaterialController {
  @Autowired
  private RawMaterialService rawMaterialService;
  @Autowired
  private MaterialSubCategoryService materialSubCategoryService;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private FileStorageService fileStorageService;
  @Autowired
  private Mapper mapper;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;
  private static final Logger logger = Logger.getLogger(RawMaterialController.class);

  @PostMapping(value = EndpointURI.RAW_MATERIAL)
  public ResponseEntity<Object> createRawMaterial(
      @Valid @RequestBody RawMaterialRequestDto rawMaterialRequestDto) {
    MaterialSubCategory materialSubCategory = materialSubCategoryService
        .getMaterialSubCategoryById(rawMaterialRequestDto.getMaterialSubCategoryId());
    if (rawMaterialService.isRawMaterialNameExist(rawMaterialRequestDto.getName())) {
      logger.debug("Material already exists: createMaterial(), materialName: {}");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.RAW_MATERIAL_NAME,
          validationFailureStatusCodes.getRawMaterialAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    if (materialSubCategory.getMaterialCategory().getMainType().equals(MainType.RAW_MATERIAL)) {
      if (rawMaterialService.isPrefixAlreadyExists(rawMaterialRequestDto.getPrefix())) {
        return new ResponseEntity<>(new ValidationFailureResponse(ValidationConstance.PREFIX,
            validationFailureStatusCodes.getPrefixAlreadyExist()), HttpStatus.BAD_REQUEST);
      }
    } else {
      if (rawMaterialService.isPrefixAndMaterialSubCategoryExists(rawMaterialRequestDto.getPrefix(),
          rawMaterialRequestDto.getMaterialSubCategoryId(), rawMaterialRequestDto.getPlantCode())) {
        return new ResponseEntity<>(new ValidationFailureResponse(ValidationConstance.PREFIX,
            validationFailureStatusCodes.getPrefixAlreadyExist()), HttpStatus.BAD_REQUEST);
      }
    }
    rawMaterialService.saveRawMaterial(mapper.map(rawMaterialRequestDto, RawMaterial.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_RAW_MATERIAL_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.RAW_MATERIALS)
  public ResponseEntity<Object> getAllRawMaterials() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.RAW_MATERIAL,
        mapper.map(rawMaterialService.getAllRawMaterials(), RawMaterialResponseDto.class),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.GET_RAW_MATERIAL_BY_ID)
  public ResponseEntity<Object> getRawMaterialById(@PathVariable Long id) {
    if (rawMaterialService.isRawMaterialExist(id)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.RAW_MATERIAL,
          mapper.map(rawMaterialService.getRawMaterialById(id), RawMaterialResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("No Raw Material record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.RAW_MATERIAL_ID,
        validationFailureStatusCodes.getRawMaterialNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.RAW_MATERIAL)
  public ResponseEntity<Object> updateRawMaterial(
      @Valid @RequestBody RawMaterialRequestDto rawMaterialRequestDto) {
    if (rawMaterialService.isRawMaterialExist(rawMaterialRequestDto.getId())) {
      if (rawMaterialService.isUpdatedNameExist(rawMaterialRequestDto.getId(),
          rawMaterialRequestDto.getName())) {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.RAW_MATERIAL_NAME,
            validationFailureStatusCodes.getRawMaterialAlreadyExist()), HttpStatus.BAD_REQUEST);
      }
      if (rawMaterialService.isPrefixAndMaterialSubCategoryExists(rawMaterialRequestDto.getPrefix(),
          rawMaterialRequestDto.getMaterialSubCategoryId(), rawMaterialRequestDto.getPlantCode())) {
        return new ResponseEntity<>(new ValidationFailureResponse(ValidationConstance.PREFIX,
            validationFailureStatusCodes.getPrefixAlreadyExist()), HttpStatus.BAD_REQUEST);
      }
      rawMaterialService.saveRawMaterial(mapper.map(rawMaterialRequestDto, RawMaterial.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_RAW_MATERIAL_SUCCESS),
          HttpStatus.OK);
    }
    logger.debug("No Raw Material record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.RAW_MATERIAL_ID,
        validationFailureStatusCodes.getRawMaterialNotExist()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(EndpointURI.DELETE_RAW_MATERIAL)
  public ResponseEntity<Object> deleteRawMaterial(@PathVariable Long id) {
    if (rawMaterialService.isRawMaterialExist(id)) {
      rawMaterialService.deleteRawMaterial(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.DELETE_RAW_MATERIAL_SCCESS),
          HttpStatus.OK);
    }
    logger.debug("No Raw Material record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.RAW_MATERIAL_ID,
        validationFailureStatusCodes.getRawMaterialNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.ACTIVE_RAW_MATERIALS)
  public ResponseEntity<Object> getAllActiveRawMaterials() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.RAW_MATERIAL,
        mapper.map(rawMaterialService.getAllActiveRawMaterials(), RawMaterialResponseDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.GET_BY_MATERIAL_SUB_CATEGORY)
  public ResponseEntity<Object> getByMaterialSubCategory(@PathVariable Long materialSubCategoryId) {
    if (materialSubCategoryService.isMaterialSubCategoryExist(materialSubCategoryId)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.MATERIAL_SUB_CATEGORY,
          mapper.map(rawMaterialService.getByMaterialSubCategoryId(materialSubCategoryId),
              RawMaterialResponseDto.class),
          RestApiResponseStatus.OK), null, HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_SUB_CATEGORY,
        validationFailureStatusCodes.getMaterialSubCategoryNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.RAW_MATERIALS_BY_PLANT)
  public ResponseEntity<Object> getRawMaterialsByCurrentUserPermission(
      @CurrentUser UserPrincipal currentUser, @PathVariable String plantCode,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(page, size, totalpage, 0l);
    if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
      pagination.setTotalRecords(rawMaterialService.countRawMaterials());
      return new ResponseEntity<>(
          new PaginatedContentResponse<>(Constants.RAW_MATERIAL,
              mapper.map(rawMaterialService.getAllRawMaterialsPage(pageable),
                  RawMaterialResponseDto.class),
              RestApiResponseStatus.OK, pagination),
          HttpStatus.OK);
    } else {
      if (currentUserPermissionPlantService
          .getPermissionPlantCodeByCurrentUser(currentUser, PermissionConstants.VIEW_RAW_MATERIAL)
          .contains(plantCode)) {
        pagination.setTotalRecords(rawMaterialService.countRawMaterialByPlant(plantCode));
        return new ResponseEntity<>(
            new PaginatedContentResponse<>(Constants.RAW_MATERIAL,
                mapper.map(rawMaterialService.getRawMaterialsByPlantCode(plantCode, pageable),
                    RawMaterialResponseDto.class),
                RestApiResponseStatus.OK, pagination),
            HttpStatus.OK);
      }
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.RAW_MATERIALS_BY_MATERIAL_SUBCATORY_AND_PLANT)
  public ResponseEntity<Object> getRawMaterialsBySubCategoryAndCurrentUserPermission(
      @CurrentUser UserPrincipal currentUser, @PathVariable Long materialSubCategoryId,
      @PathVariable String plantCode) {
    if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.RAW_MATERIAL,
          mapper.map(rawMaterialService.getRawMaterialsByMaterialSubCategoryAndPlantCode(
              materialSubCategoryId, plantCode), RawMaterialResponseDto.class),
          RestApiResponseStatus.OK), null, HttpStatus.OK);
    } else {
      if (currentUserPermissionPlantService
          .getPermissionPlantCodeByCurrentUser(currentUser, PermissionConstants.VIEW_RAW_MATERIAL)
          .contains(plantCode)) {
        return new ResponseEntity<>(new ContentResponse<>(Constants.RAW_MATERIAL,
            mapper.map(rawMaterialService.getRawMaterialsByMaterialSubCategoryAndPlantCode(
                materialSubCategoryId, plantCode), RawMaterialResponseDto.class),
            RestApiResponseStatus.OK), null, HttpStatus.OK);
      }
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.SEARCH_RAW_MATERIAL)
  public ResponseEntity<Object> getRawMaterialSearch(@PathVariable String plantCode,
      @RequestParam(name = "name", required = false) String name,
      @RequestParam(name = "materialSubCategoryName",
          required = false) String materialSubCategoryName,
      @RequestParam(name = "plantName", required = false) String plantName,
      @RequestParam(name = "prefix", required = false) String prefix,
      @RequestParam(name = "erpCode", required = false) String erpCode,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(0, 0, totalpage, 0l);
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    return new ResponseEntity<>(new PaginatedContentResponse<>(Constants.RAW_MATERIAL,
        rawMaterialService.searchRawMaterial(booleanBuilder, name, materialSubCategoryName,
            plantName, prefix, plantCode, erpCode, pageable, pagination),
        RestApiResponseStatus.OK, pagination), null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.GET_RAW_MATERIALS_BY_PLANT)
  public ResponseEntity<Object> getNameSearch(@PathVariable String plantCode,
      @RequestParam(name = "name") String name) {
    if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.RAW_MATERIAL,
          mapper.map(rawMaterialService.getName(name), RawMaterialResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ContentResponse<>(Constants.RAW_MATERIAL, mapper
        .map(rawMaterialService.getNameByPlantCode(plantCode, name), RawMaterialResponseDto.class),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.GET_RAW_MATERIALS_BY_MAIN_TYPE)
  public ResponseEntity<Object> getRawMaterialsByMainType(@PathVariable MainType mainType) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.RAW_MATERIAL, mapper
        .map(rawMaterialService.getRawMaterialsByMainType(mainType), RawMaterialResponseDto.class),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.EXPORT_RAW_MATERIAL)
  public ResponseEntity<Object> exportRawMaterial(HttpServletResponse response)
      throws ClassNotFoundException {
    HSSFWorkbook workbook = new HSSFWorkbook();
    HSSFSheet worksheet = workbook.createSheet(FileStorageConstants.RAW_MATERIAL_WORK_SHEET);
    int startRowIndex = 0;
    int startColIndex = 0;
    RawMaterialLayouter.buildReport(worksheet, startRowIndex, startColIndex);
    RawMaterialFillManager.fillReport(worksheet, startRowIndex, startColIndex,
        rawMaterialService.getAllActiveRawMaterials());
    String fileName = FileStorageConstants.RAW_MATERIAL_FILE_NAME;
    response.setHeader("Content-Disposition", "inline; filename=" + fileName);
    response.setContentType("application/vnd.ms-excel");
    EnrollWriter.write(response, worksheet);
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, FileStorageConstants.EXPORT_SUCCESS),
        HttpStatus.OK);
  }

  @PostMapping(value = EndpointURI.IMPORT_RAW_MATERIAL)
  public ResponseEntity<Object> uploadRawMaterial(@RequestParam("file") MultipartFile file,
      HttpServletRequest request) {
    fileStorageService.uploadCsv(file);
    fileStorageService.importRawMaterial(file, request);
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, FileStorageConstants.UPLOAD_SUCCESS),
        HttpStatus.OK);
  }
}

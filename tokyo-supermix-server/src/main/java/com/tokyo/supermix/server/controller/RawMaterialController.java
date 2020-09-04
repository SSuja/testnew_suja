package com.tokyo.supermix.server.controller;

import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
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
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.RawMaterialRequestDto;
import com.tokyo.supermix.data.dto.RawMaterialResponseDto;
import com.tokyo.supermix.data.entities.RawMaterial;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.security.CurrentUser;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.MaterialSubCategoryService;
import com.tokyo.supermix.server.services.RawMaterialService;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.Constants;
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
  private Mapper mapper;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;
  private static final Logger logger = Logger.getLogger(RawMaterialController.class);

  @PostMapping(value = EndpointURI.RAW_MATERIAL)
  public ResponseEntity<Object> createRawMaterial(
      @Valid @RequestBody RawMaterialRequestDto rawMaterialRequestDto) {
    if (rawMaterialService.isRawMaterialNameExist(rawMaterialRequestDto.getName())) {
      logger.debug("Material already exists: createMaterial(), materialName: {}");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.RAW_MATERIAL_NAME,
          validationFailureStatusCodes.getRawMaterialAlreadyExist()), HttpStatus.BAD_REQUEST);
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
        RestApiResponseStatus.OK), null, HttpStatus.OK);
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

  @GetMapping(value = EndpointURI.SEARCH_RAW_MATERIAL)
  public ResponseEntity<Object> getRawMaterialSearch(
      @QuerydslPredicate(root = RawMaterial.class) Predicate predicate,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.RAW_MATERIAL,
            rawMaterialService.searchRawMaterial(predicate, page, size), RestApiResponseStatus.OK),
        null, HttpStatus.OK);
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
      @CurrentUser UserPrincipal currentUser, @PathVariable String plantCode) {
    if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.RAW_MATERIAL,
          mapper.map(rawMaterialService.getAllRawMaterials(), RawMaterialResponseDto.class),
          RestApiResponseStatus.OK), null, HttpStatus.OK);
    } else {
      if (currentUserPermissionPlantService
          .getPermissionPlantCodeByCurrentUser(currentUser, PermissionConstants.VIEW_RAW_MATERIAL)
          .contains(plantCode)) {
        return new ResponseEntity<>(new ContentResponse<>(Constants.RAW_MATERIAL,
            mapper.map(rawMaterialService.getRawMaterialsByPlantCode(plantCode),
                RawMaterialResponseDto.class),
            RestApiResponseStatus.OK), null, HttpStatus.OK);
      }
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }
}

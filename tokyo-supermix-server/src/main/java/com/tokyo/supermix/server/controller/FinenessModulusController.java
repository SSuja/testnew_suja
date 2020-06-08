package com.tokyo.supermix.server.controller;

import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.tokyo.supermix.data.dto.FinenessModulusRequestDto;
import com.tokyo.supermix.data.dto.FinenessModulusResponseDto;
import com.tokyo.supermix.data.entities.FinenessModulus;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.FinenessModulusService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin("*")
public class FinenessModulusController {
  @Autowired
  private Mapper mapper;
  @Autowired
  private FinenessModulusService finenessModulusService;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  private static final Logger logger = Logger.getLogger(FinenessModulusController.class);

  // Create Fineness Modulus API
  @PostMapping(value = EndpointURI.FINENESS_MODULUS)
  @PreAuthorize("hasAuthority('add_finess_module')")
  public ResponseEntity<Object> createFinenessModulus(
      @Valid @RequestBody FinenessModulusRequestDto finenessModulusRequestDto) {
    if (finenessModulusService
        .isMaterialSubCategoryIdExists(finenessModulusRequestDto.getMaterialSubCategoryId())) {
      logger.debug(
          "Material Sub Category already exists: createFinenessModulus(), materialSubCategory: {}");
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.MATERIAL_SUB_CATEGORY,
              validationFailureStatusCodes.getMaterialSubCategoryAlreadyExist()),
          HttpStatus.BAD_REQUEST);
    }
    finenessModulusService
        .saveFinenessModulus(mapper.map(finenessModulusRequestDto, FinenessModulus.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_FINENESS_MODULUS_SUCCESS),
        HttpStatus.OK);
  }

  // Get all Fineness Modulus API
  @GetMapping(value = EndpointURI.FINENESS_MODULUS)
  @PreAuthorize("hasAuthority('get_finess_module')")
  public ResponseEntity<Object> getAllFinenessModuluss() {
    return new ResponseEntity<Object>(new ContentResponse<>(Constants.FINENESS_MODULUS, mapper
        .map(finenessModulusService.getAllFinenessModulus(), FinenessModulusResponseDto.class),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }

  // Get Fineness Modulus by Id API
  @GetMapping(value = EndpointURI.FINENESS_MODULUS_BY_ID)
  public ResponseEntity<Object> getFinenessModulusById(@PathVariable Long id) {
    if (finenessModulusService.isFinenessModulusExists(id)) {
      logger.debug("Get FinenessModulus by id ");
      return new ResponseEntity<>(new ContentResponse<>(Constants.FINENESS_MODULUS, mapper
          .map(finenessModulusService.getFinenessModulusById(id), FinenessModulusResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.FINENESS_MODULUS_ID,
        validationFailureStatusCodes.getFinenessModulusNotExist()), HttpStatus.BAD_REQUEST);
  }

  // Delete Fineness Modulus API
  @DeleteMapping(value = EndpointURI.FINENESS_MODULUS_BY_ID)
  @PreAuthorize("hasAuthority('delete_finess_module')")
  public ResponseEntity<Object> deleteFinenessModulus(@PathVariable Long id) {
    if (finenessModulusService.isFinenessModulusExists(id)) {
      finenessModulusService.deleteFinenessModulus(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.FINENESS_MODULUS_DELETED),
          HttpStatus.OK);
    }
    logger.debug("invalid FinenessModulusId");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.FINENESS_MODULUS_ID,
        validationFailureStatusCodes.getFinenessModulusNotExist()), HttpStatus.BAD_REQUEST);
  }

  // Update Fineness Modulus API
  @PutMapping(value = EndpointURI.FINENESS_MODULUS)
  @PreAuthorize("hasAuthority('edit_finess_module')")
  public ResponseEntity<Object> updateFinenessModulus(
      @Valid @RequestBody FinenessModulusRequestDto finenessModulusRequestDto) {
    if (finenessModulusService.isFinenessModulusExists(finenessModulusRequestDto.getId())) {
      if (finenessModulusService.isUpdatedMaterialSubCategoryExist(
          finenessModulusRequestDto.getId(),
          finenessModulusRequestDto.getMaterialSubCategoryId())) {
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.MATERIAL_SUB_CATEGORY,
                validationFailureStatusCodes.getMaterialSubCategoryAlreadyExist()),
            HttpStatus.BAD_REQUEST);
      }
    }
    finenessModulusService
        .saveFinenessModulus(mapper.map(finenessModulusRequestDto, FinenessModulus.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_FINENESS_MODULUS_SUCCESS),
        HttpStatus.OK);
  }

  // Get Fineness Modulus by Material SubCategory API
  @GetMapping(value = EndpointURI.FINENESS_MODULUS_BY_MATERIALSUBCATEGORY)
  public ResponseEntity<Object> getFinenessModulusByMaterialSubCategory(
      @PathVariable Long materialSubCategoryId) {
    if (finenessModulusService.isMaterialSubCategoryIdExists(materialSubCategoryId)) {
      logger.debug("Get FinenessModulus by MaterialSubCategoryId ");
      return new ResponseEntity<>(
          new ContentResponse<>(Constants.FINENESS_MODULUS,
              mapper.map(finenessModulusService.getFinenessModulusByMaterialSubCategory(
                  materialSubCategoryId), FinenessModulusResponseDto.class),
              RestApiResponseStatus.OK),
          HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_SUB_CATEGORY_ID,
        validationFailureStatusCodes.getMaterialSubCategoryNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.SEARCH_FINENESS_MODULUS)
  public ResponseEntity<Object> getFinenessModulusSearch(
      @QuerydslPredicate(root = FinenessModulus.class) Predicate predicate,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.FINENESS_MODULUS,
        finenessModulusService.searchFinenessModulus(predicate, size, page),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }
}

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
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.MaterialCategoryDto;
import com.tokyo.supermix.data.entities.MaterialCategory;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.MaterialCategoryService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;


@CrossOrigin(origins = "*")
@RestController
public class MaterialCategoryController {
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  @Autowired
  private MaterialCategoryService materialCategoryService;
  private static final Logger logger = Logger.getLogger(MaterialCategoryController.class);

  // Add Material Category
  @PostMapping(value = EndpointURI.MATERIAL_CATEGORY)
  public ResponseEntity<Object> createMaterialCategory(
      @Valid @RequestBody MaterialCategoryDto materialCategoryDto) {
    if (materialCategoryService.isNameExist(materialCategoryDto.getName())) {
      logger.debug("name is already exists: createMaterialCategory(), isNameExist: {}");
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.MATERIAL_CATEGORY_NAME,
              validationFailureStatusCodes.getMaterialCategoryAlreadyExist()),
          HttpStatus.BAD_REQUEST);
    }
    materialCategoryService
        .saveMaterialCategory(mapper.map(materialCategoryDto, MaterialCategory.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_MATERIAL_CATEGORY_SUCCESS),
        HttpStatus.OK);
  }

  // Get All Material Category
  @GetMapping(value = EndpointURI.MATERIAL_CATEGORIES)
  public ResponseEntity<Object> getAllMaterialCategory() {
    logger.debug("get all material categories");
    return new ResponseEntity<>(new ContentResponse<>(Constants.MATERIAL_CATEGORIES,
        mapper.map(materialCategoryService.getAllMainCategories(), MaterialCategoryDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  // Get Material_Category By Id
  @GetMapping(value = EndpointURI.MATERIAL_CATEGORY_BY_ID)
  public ResponseEntity<Object> getMaterialCategoryById(@PathVariable Long id) {
    if (materialCategoryService.isMaterialCategoryExist(id)) {
      logger.debug("Get Material Category By Id");
      return new ResponseEntity<>(new ContentResponse<>(
          Constants.MATERIAL_CATEGORY, mapper
              .map(materialCategoryService.getMaterialCategoryById(id), MaterialCategoryDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_CATEGORY_ID,
        validationFailureStatusCodes.getMaterialCategoryNotExist()), HttpStatus.BAD_REQUEST);
  }

  // Delete Material Category
  @DeleteMapping(value = EndpointURI.MATERIAL_CATEGORY_BY_ID)
  public ResponseEntity<Object> deleteMaterialCategory(@PathVariable Long id) {
    if (materialCategoryService.isMaterialCategoryExist(id)) {
      logger.debug("delete material by id");
      materialCategoryService.deleteMaterialCategory(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.MATERIAL_CATEGORY_DELETED),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_CATEGORY_ID,
        validationFailureStatusCodes.getMaterialCategoryNotExist()), HttpStatus.BAD_REQUEST);
  }

  // Update Material Category
  @PutMapping(value = EndpointURI.MATERIAL_CATEGORY)
  public ResponseEntity<Object> updateMaterialCategory(
      @Valid @RequestBody MaterialCategoryDto materialCategoryDto) {
    if (materialCategoryService.isMaterialCategoryExist(materialCategoryDto.getId())) {
      if (materialCategoryService.isUpdatedNameExist(materialCategoryDto.getId(),
          materialCategoryDto.getName())) {
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.MATERIAL_CATEGORY_NAME,
                validationFailureStatusCodes.getMaterialCategoryAlreadyExist()),
            HttpStatus.BAD_REQUEST);
      }
      materialCategoryService
          .saveMaterialCategory(mapper.map(materialCategoryDto, MaterialCategory.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_MATERIAL_CATEGORY_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_CATEGORY_ID,
        validationFailureStatusCodes.getMaterialCategoryNotExist()), HttpStatus.BAD_REQUEST);
  }
}

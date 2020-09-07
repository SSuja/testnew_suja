package com.tokyo.supermix.server.controller;

import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import com.tokyo.supermix.data.dto.MaterialSubCategoryRequestDto;
import com.tokyo.supermix.data.dto.MaterialSubCategoryResponseDto;
import com.tokyo.supermix.data.entities.MaterialCategory;
import com.tokyo.supermix.data.entities.MaterialSubCategory;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.MaterialCategoryService;
import com.tokyo.supermix.server.services.MaterialSubCategoryService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin(origins = "*")
public class MaterialSubCategoryController {
  @Autowired
  private MaterialSubCategoryService materialSubCategoryService;
  @Autowired
  private MaterialCategoryService materialCategoryService;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  private static final Logger logger = Logger.getLogger(MaterialSubCategoryController.class);

  @GetMapping(value = EndpointURI.MATERIAL_SUB_CATEGORIES)
  public ResponseEntity<Object> getMaterialSubCategory(@RequestParam(name = "page") int page,
      @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(page, size, totalpage, 0l);
    return new ResponseEntity<>(
        new PaginatedContentResponse<>(Constants.MATERIAL_SUB_CATEGORIES,
            mapper.map(materialSubCategoryService.getAllMaterialSubCategories(pageable),
                MaterialSubCategoryResponseDto.class),
            RestApiResponseStatus.OK, pagination),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.MATERIAL_SUB_CATEGORY_BY_ID)
  public ResponseEntity<Object> getMaterialSubCategoryById(@PathVariable Long id) {
    if (materialSubCategoryService.isMaterialSubCategoryExist(id)) {
      return new ResponseEntity<>(
          new ContentResponse<>(Constants.MATERIAL_SUB_CATEGORY,
              mapper.map(materialSubCategoryService.getMaterialSubCategoryById(id),
                  MaterialSubCategoryResponseDto.class),
              RestApiResponseStatus.OK),
          null, HttpStatus.OK);
    }
    logger.debug("MaterialSubCategory does not exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_SUB_CATEGORY_ID,
        validationFailureStatusCodes.getMaterialSubCategoryNotExist()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(value = EndpointURI.MATERIAL_SUB_CATEGORY_BY_ID)
  public ResponseEntity<Object> deleteMaterialSubCategoryById(@PathVariable Long id) {
    if (materialSubCategoryService.isMaterialSubCategoryExist(id)) {
      materialSubCategoryService.deleteMaterialSubCategory(id);;
      return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
          Constants.DELETE_MATERIAL_SUB_CATEGORY_SUCCESS), HttpStatus.OK);
    }
    logger.debug("MaterialSubCategory doesn't exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_SUB_CATEGORY_ID,
        validationFailureStatusCodes.getMaterialSubCategoryNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PostMapping(value = EndpointURI.MATERIAL_SUB_CATEGORY)
  public ResponseEntity<Object> createMaterialSubCategory(
      @Valid @RequestBody MaterialSubCategoryRequestDto materialSubCategoryRequestDto) {
    if (materialSubCategoryService.isMaterialCategoryExist(materialSubCategoryRequestDto.getName(),
        materialSubCategoryRequestDto.getMaterialCategoryId())) {
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.MATERIAL_SUB_CATEGORY_NAME,
              validationFailureStatusCodes.getMaterialSubCategoryAlreadyExist()),
          HttpStatus.BAD_REQUEST);
    }
    materialSubCategoryService.saveMaterialSubCategory(
        mapper.map(materialSubCategoryRequestDto, MaterialSubCategory.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_MATERIAL_SUB_CATEGORY_SUCCESS),
        HttpStatus.OK);
  }

  @PutMapping(value = EndpointURI.MATERIAL_SUB_CATEGORY)
  public ResponseEntity<Object> updateMaterialSubCategory(
      @Valid @RequestBody MaterialSubCategoryRequestDto materialSubCategoryRequestDto) {
    if (materialSubCategoryService
        .isMaterialSubCategoryExist(materialSubCategoryRequestDto.getId())) {
      if (materialSubCategoryService.isMaterialCategoryExist(
          materialSubCategoryRequestDto.getName(),
          materialSubCategoryRequestDto.getMaterialCategoryId())) {
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.MATERIAL_SUB_CATEGORY_NAME,
                validationFailureStatusCodes.getMaterialSubCategoryAlreadyExist()),
            HttpStatus.BAD_REQUEST);
      }
      materialSubCategoryService.saveMaterialSubCategory(
          mapper.map(materialSubCategoryRequestDto, MaterialSubCategory.class));
      return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
          Constants.UPDATE_MATERIAL_SUB_CATEGORY_SUCCESS), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_SUB_CATEGORY,
        validationFailureStatusCodes.getMaterialSubCategoryNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.GET_MATERIAL_SUB_CATEGORY_BY_MATERIAL_CATEGORY)
  public ResponseEntity<Object> getMaterialSubCategoryByCategory(
      @PathVariable Long materialCategoryId) {
    MaterialCategory materialCategory =
        materialCategoryService.getMaterialCategoryById(materialCategoryId);
    if (materialCategory != null) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.MATERIAL_SUB_CATEGORIES,
          mapper.map(materialSubCategoryService.getMaterialSubCategoryByCategory(materialCategory),
              MaterialSubCategoryResponseDto.class),
          RestApiResponseStatus.OK), null, HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_SUB_CATEGORY,
        validationFailureStatusCodes.getMaterialSubCategoryNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.GET_MATERIAL_SUB_CATEGORY_BY_MATERIAL_CATEGORY_NAME)
  public ResponseEntity<Object> getMaterialSubCategoryByCategory(
      @PathVariable String materialCategoryName) {
    if (materialCategoryService.isNameExist(materialCategoryName)) {
      if (materialSubCategoryService.isMaterialCategoryIdExist(
          materialCategoryService.getMaterialCategoryByName(materialCategoryName).getId())) {
        return new ResponseEntity<>(new ContentResponse<>(Constants.MATERIAL_SUB_CATEGORIES,
            mapper.map(
                materialSubCategoryService.getMaterialSubCategoryByCategory(
                    materialCategoryService.getMaterialCategoryById(materialCategoryService
                        .getMaterialCategoryByName(materialCategoryName).getId())),
                MaterialSubCategoryResponseDto.class),
            RestApiResponseStatus.OK), HttpStatus.OK);
      }
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.MATERIAL_CATEGORY_ID,
              validationFailureStatusCodes.getMaterialSubCategoryNotExist()),
          HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_CATEGORY_NAME,
        validationFailureStatusCodes.getMaterialCategoryNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.MATERIAL_SUB_CATEGORY_SEARCH)
  public ResponseEntity<Object> getMaterialSubCategoryBySearch(
      @QuerydslPredicate(root = MaterialSubCategory.class) Predicate predicate,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.MATERIAL_SUB_CATEGORIES,
        materialSubCategoryService.searchMaterialSubCategory(predicate, size, page),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }
}

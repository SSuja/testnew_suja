package com.tokyo.supermix.server.controller;

import java.util.List;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.SupplierCategoryDto;
import com.tokyo.supermix.data.entities.SupplierCategory;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.server.services.SupplierCategoryService;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;
import com.tokyo.supermix.rest.response.ValidationFailure;
import com.tokyo.supermix.util.Constants;

@RestController
public class SupplierCategoryController {

  @Autowired
  private SupplierCategoryService supplierCategoryService;

  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;

  @Autowired
  private Mapper mapper;

  private static final Logger logger = Logger.getLogger(SupplierCategoryController.class);

  @PostMapping(value = EndpointURI.SUPPLIER_CATEGORY)
  public ResponseEntity<Object> createSupplierCategory(
      @Valid @RequestBody SupplierCategoryDto supplierCategoryDto) {
    if (supplierCategoryService.isSupplierCategoryExist(supplierCategoryDto.getCategory())) {
      logger.debug("Supplier Category already exists: createSupplierCategory(), category: {}");
      return new ResponseEntity<>(new ContentResponse<>(Constants.SUPPLIER_CATEGORY,
          new ValidationFailure(Constants.SUPPLIER_CATEGORY_NAME,
              validationFailureStatusCodes.getSupplierCategoryAlreadyExist()),
          RestApiResponseStatus.VALIDATION_FAILURE), HttpStatus.BAD_REQUEST);
    }
    SupplierCategory supplierCategory = mapper.map(supplierCategoryDto, SupplierCategory.class);
    supplierCategoryService.createSupplierCategory(supplierCategory);
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.CREATED, Constants.ADD_SUPPLIER_CATEGORY_SUCCESS),
        HttpStatus.OK);

  }

  @GetMapping(value = EndpointURI.SUPPLIER_CATEGORIES)
  public ResponseEntity<Object> getAllSupplierCategories() {
    List<SupplierCategory> supplierCategoryList =
        supplierCategoryService.getAllSupplierCategories();
    List<SupplierCategoryDto> supplierCategoryDtoList =
        mapper.map(supplierCategoryList, SupplierCategoryDto.class);
    return new ResponseEntity<>(new ContentResponse<>(Constants.SUPPLIER_CATEGORY,
        supplierCategoryDtoList, RestApiResponseStatus.OK), null, HttpStatus.OK);
  }
}

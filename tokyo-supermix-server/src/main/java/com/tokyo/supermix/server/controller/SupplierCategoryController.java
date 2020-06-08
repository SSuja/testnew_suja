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
import org.springframework.security.access.prepost.PreAuthorize;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.SupplierCategoryDto;
import com.tokyo.supermix.data.entities.SupplierCategory;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.server.services.SupplierCategoryService;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.util.Constants;

@RestController
@CrossOrigin
public class SupplierCategoryController {

  @Autowired
  private SupplierCategoryService supplierCategoryService;

  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;

  @Autowired
  private Mapper mapper;

  private static final Logger logger = Logger.getLogger(SupplierCategoryController.class);

  @PostMapping(value = EndpointURI.SUPPLIER_CATEGORY)
  @PreAuthorize("hasAuthority('add_supplier_category')")
  public ResponseEntity<Object> createSupplierCategory(
      @Valid @RequestBody SupplierCategoryDto supplierCategoryDto) {
    if (supplierCategoryService.isSupplierCategoryExist(supplierCategoryDto.getCategory())) {
      logger.debug("Supplier Category already exists: createSupplierCategory(), category: {}");
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.SUPPLIER_CATEGORY_NAME,
              validationFailureStatusCodes.getSupplierCategoryAlreadyExist()),
          HttpStatus.BAD_REQUEST);
    }
    supplierCategoryService
        .createSupplierCategory(mapper.map(supplierCategoryDto, SupplierCategory.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_SUPPLIER_CATEGORY_SUCCESS),
        HttpStatus.OK);

  }

  @DeleteMapping(EndpointURI.DELETE_SUPPLIER_CATEGORY)
  @PreAuthorize("hasAuthority('delete_supplier_category')")
  public ResponseEntity<Object> deleteSupplierCategory(@PathVariable Long id) {
    if (supplierCategoryService.isSupplierCategoryExist(id)) {
      supplierCategoryService.deleteSupplierCategory(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.DELETE_SUPPLIER_CATEGORY_SCCESS),
          HttpStatus.OK);
    }
    logger.debug("No Supplier Category record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.SUPPLIER_CATEGORY,
        validationFailureStatusCodes.getSupplierCategoryNotExit()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.SUPPLIER_CATEGORIES)
  @PreAuthorize("hasAuthority('get_supplier_category')")
  public ResponseEntity<Object> getAllSupplierCategories() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.SUPPLIER_CATEGORY,
        mapper.map(supplierCategoryService.getAllSupplierCategories(), SupplierCategoryDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @PutMapping(value = EndpointURI.SUPPLIER_CATEGORY)
  @PreAuthorize("hasAuthority('edit_supplier_category')")
  public ResponseEntity<Object> updateSupplierCategory(
      @Valid @RequestBody SupplierCategoryDto supplierCategoryDto) {
    if (supplierCategoryService.isSupplierCategoryExist(supplierCategoryDto.getId())) {
      if (supplierCategoryService.isUpdatedCategoryExist(supplierCategoryDto.getId(),
          supplierCategoryDto.getCategory())) {
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.SUPPLIER_CATEGORY,
                validationFailureStatusCodes.getSupplierCategoryAlreadyExist()),
            HttpStatus.BAD_REQUEST);
      }
      supplierCategoryService
          .updateSupplierCategory(mapper.map(supplierCategoryDto, SupplierCategory.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_SUPPLIER_CATEGORY_SUCCESS),
          HttpStatus.OK);
    }
    logger.debug("No Supplier Category record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.SUPPLIER_CATEGORY,
        validationFailureStatusCodes.getSupplierCategoryNotExit()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.SUPPLIER_CATEGORY_BY_ID)
  public ResponseEntity<Object> getSupplierCategoryById(@PathVariable Long id) {
    if (supplierCategoryService.isSupplierCategoryExist(id)) {
      return new ResponseEntity<>(new ContentResponse<>(
          Constants.SUPPLIER_CATEGORY, mapper
              .map(supplierCategoryService.getSupplierCategoryById(id), SupplierCategoryDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("No Supplier Category record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.SUPPLIER_CATEGORY,
        validationFailureStatusCodes.getSupplierCategoryNotExit()), HttpStatus.BAD_REQUEST);
  }
}

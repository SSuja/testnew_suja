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
import com.tokyo.supermix.data.dto.SupplierCategoryDto;
import com.tokyo.supermix.data.entities.SupplierCategory;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.server.services.SupplierCategoryService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

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

  @DeleteMapping(EndpointURI.SUPPLIER_CATEGORY_BY_ID)
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
  public ResponseEntity<Object> getAllSupplierCategories() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.SUPPLIER_CATEGORY,
        mapper.map(supplierCategoryService.getAllSupplierCategories(), SupplierCategoryDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @PutMapping(value = EndpointURI.SUPPLIER_CATEGORY)
  public ResponseEntity<Object> updateSupplierCategory(
      @Valid @RequestBody SupplierCategoryDto supplierCategoryDto) {
    if (supplierCategoryService.isSupplierCategoryExist(supplierCategoryDto.getId())) {
      if (supplierCategoryService.isUpdatedCategoryExist(supplierCategoryDto.getId(),
          supplierCategoryDto.getCategory())) {
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.SUPPLIER_CATEGORY_NAME,
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

  @GetMapping(value = EndpointURI.SUPPLIER_CATEGORY_PAGINATION)
  public ResponseEntity<Object> getAllSupplierCategories(@RequestParam(name = "page") int page,
      @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(page, size, totalpage, 0l);
    pagination.setTotalRecords(supplierCategoryService.countSupplierCategory());
    return new ResponseEntity<>(
        new PaginatedContentResponse<>(Constants.SUPPLIER_CATEGORY,
            mapper.map(supplierCategoryService.getAllSupplierCategoryByPageable(pageable),
                SupplierCategoryDto.class),
            RestApiResponseStatus.OK, pagination),
        null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.SEARCH_SUPPLIER_CATEGORY)
  public ResponseEntity<Object> searchSupplierCategory(@RequestParam(name = "page") int page,
      @RequestParam(name = "size") int size,
      @RequestParam(name = "category", required = false) String category) {
    Pageable pageable = PageRequest.of(page, size);
    Pagination pagination = new Pagination(0, 0, 0, 0l);
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    return new ResponseEntity<>(new PaginatedContentResponse<>(Constants.SUPPLIER_CATEGORY,
        mapper.map(supplierCategoryService.searchSupplierCategory(category, booleanBuilder, page,
            size, pageable, pagination), SupplierCategoryDto.class),
        RestApiResponseStatus.OK, pagination), HttpStatus.OK);
  }
}

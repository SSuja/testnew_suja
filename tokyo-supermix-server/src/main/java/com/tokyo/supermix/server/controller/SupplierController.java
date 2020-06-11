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
import com.tokyo.supermix.data.dto.SupplierRequestDto;
import com.tokyo.supermix.data.dto.SupplierResponseDto;
import com.tokyo.supermix.data.entities.Supplier;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.PlantService;
import com.tokyo.supermix.server.services.SupplierCategoryService;
import com.tokyo.supermix.server.services.SupplierService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin(origins = "*")
public class SupplierController {
  @Autowired
  private SupplierService supplierService;
  @Autowired
  private SupplierCategoryService supplierCategoryService;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  @Autowired
  private PlantService plantService;

  private static final Logger logger = Logger.getLogger(SupplierController.class);

  @GetMapping(value = EndpointURI.SUPPLIERS)
  // @PreAuthorize("hasAuthority('get_supplier')")
  public ResponseEntity<Object> getSuppliers() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.SUPPLIER,
        mapper.map(supplierService.getSuppliers(), SupplierResponseDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @PostMapping(value = EndpointURI.SUPPLIER)
  // @PreAuthorize("hasAuthority('add_supplier')")
  public ResponseEntity<Object> createSupplier(@Valid @RequestBody SupplierRequestDto supplierDto) {
    if (supplierService.isEmailExist(supplierDto.getEmail())) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMAIL,
          validationFailureStatusCodes.getSupplierAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    if (supplierService.isPhoneNumberExist(supplierDto.getPhoneNumber())) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.PHONE_NUMBER,
          validationFailureStatusCodes.getSupplierAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    supplierService.createSupplier(mapper.map(supplierDto, Supplier.class),
        supplierDto.getSuppilerCategoryIds());
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_SUPPLIER_SUCCESS),
        HttpStatus.OK);
  }

  @PutMapping(value = EndpointURI.SUPPLIER)
  // @PreAuthorize("hasAuthority('edit_supplier')")
  public ResponseEntity<Object> updateSupplier(@Valid @RequestBody SupplierRequestDto supplierDto) {

    if (supplierService.isSupplierExist(supplierDto.getId())) {
      if (supplierService.isUpdatedEmailExist(supplierDto.getId(), supplierDto.getEmail())) {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMAIL,
            validationFailureStatusCodes.getSupplierAlreadyExist()), HttpStatus.BAD_REQUEST);
      }
      if (supplierService.isUpdatedPhoneNumberExist(supplierDto.getId(),
          supplierDto.getPhoneNumber())) {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.PHONE_NUMBER,
            validationFailureStatusCodes.getSupplierAlreadyExist()), HttpStatus.BAD_REQUEST);
      }
      supplierService.createSupplier(mapper.map(supplierDto, Supplier.class), supplierService
          .supplierCategoriesIds(supplierDto.getSuppilerCategoryIds(), supplierDto.getId()));

      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_SUPPLIER_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.SUPPLIER,
        validationFailureStatusCodes.getSupplierNotExit()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(value = EndpointURI.DELETE_SUPPLIER)
  // @PreAuthorize("hasAuthority('delete_supplier')")
  public ResponseEntity<Object> deleteSupplierById(@PathVariable Long id) {
    if (supplierService.isSupplierExist(id)) {
      supplierService.deleteSupplierById(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.DELETE_SUPPLIER_SUCCESS),
          HttpStatus.OK);
    }
    logger.debug("Supplier doesn't exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.SUPPLIER,
        validationFailureStatusCodes.getSupplierNotExit()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.GET_SUPPLIER_BY_ID)
  public ResponseEntity<Object> getSupplierById(@PathVariable Long id) {
    if (supplierService.isSupplierExist(id)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.SUPPLIER,
          mapper.map(supplierService.getSupplierById(id), SupplierResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("No Supplier record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.SUPPLIER,
        validationFailureStatusCodes.getSupplierNotExit()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.GET_SUPPLIER_BY_SUPPLIER_CATEGORY_ID)
  public ResponseEntity<Object> getSupplierBySupplierCategoryId(
      @PathVariable Long suppilerCategoryId) {
    if (supplierCategoryService.isSupplierCategoryExist(suppilerCategoryId)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.SUPPLIER_CATEGORY,
          mapper.map(supplierService.findBySupplierCategoryId(suppilerCategoryId),
              SupplierResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    } else {
      logger.debug("No Supplier record exist for given Supplier Category id");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.SUPPLIER_CATEGORY,
          validationFailureStatusCodes.getSupplierCategoryNotExit()), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(value = EndpointURI.SUPPLIER_SEARCH)
  public ResponseEntity<Object> getSupplierSearch(
      @QuerydslPredicate(root = Supplier.class) Predicate predicate,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.SUPPLIER,
            supplierService.searchSupplier(predicate, page, size), RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.GET_SUPPLIERS_BY_PLANT_CODE)
  public ResponseEntity<Object> getSupplierByPlantCode(@PathVariable String plantCode) {
    if (plantService.isPlantExist(plantCode)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.SUPPLIER,
          mapper.map(supplierService.getSupplierByPlantCode(plantCode), SupplierResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }
}

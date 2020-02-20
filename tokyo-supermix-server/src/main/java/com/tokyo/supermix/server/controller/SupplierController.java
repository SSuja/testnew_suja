package com.tokyo.supermix.server.controller;

import java.util.List;
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
import com.tokyo.supermix.data.dto.SupplierRequestDto;
import com.tokyo.supermix.data.dto.SupplierResponseDto;
import com.tokyo.supermix.data.entities.Supplier;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.SupplierService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin(origins = "*")
public class SupplierController {
  @Autowired
  private SupplierService supplierService;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;

  private static final Logger logger = Logger.getLogger(SupplierController.class);

  @GetMapping(value = EndpointURI.SUPPLIERS)
  public ResponseEntity<Object> getSuppliers() {
    List<Supplier> supplierList = supplierService.getSuppliers();
    List<SupplierResponseDto> supplierResponseDtoList =
        mapper.map(supplierList, SupplierResponseDto.class);
    return new ResponseEntity<>(new ContentResponse<>(Constants.SUPPLIER, supplierResponseDtoList,
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @PostMapping(value = EndpointURI.SUPPLIER)
  public ResponseEntity<Object> createSupplier(@Valid @RequestBody SupplierRequestDto supplierDto) {
    if (supplierService.isEmailExist(supplierDto.getEmail())) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMAIL,
          validationFailureStatusCodes.getSupplierAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    if (supplierService.isPhoneNumberExist(supplierDto.getPhoneNumber())) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.PHONE_NUMBER,
          validationFailureStatusCodes.getSupplierAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    Supplier supplier = mapper.map(supplierDto, Supplier.class);
    supplierService.createSupplier(supplier);
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_SUPPLIER_SUCCESS),
        HttpStatus.OK);
  }

  @PutMapping(value = EndpointURI.SUPPLIER)
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
      Supplier supplier = mapper.map(supplierDto, Supplier.class);
      supplierService.updateSupplier(supplier);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_SUPPLIER_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.SUPPLIER,
        validationFailureStatusCodes.getSupplierNotExit()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(value = EndpointURI.DELETE_SUPPLIER)
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
      Supplier supplier = supplierService.getSupplierById(id);
      return new ResponseEntity<>(new ContentResponse<>(Constants.SUPPLIER,
          mapper.map(supplier, SupplierResponseDto.class), RestApiResponseStatus.OK),
          HttpStatus.OK);
    }
    logger.debug("No Supplier record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.SUPPLIER,
        validationFailureStatusCodes.getSupplierNotExit()), HttpStatus.BAD_REQUEST);
  }
}

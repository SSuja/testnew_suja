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
import com.tokyo.supermix.data.dto.CustomerRequestDto;
import com.tokyo.supermix.data.dto.CustomerResponseDto;
import com.tokyo.supermix.data.entities.Customer;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.security.CurrentUser;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.CustomerService;
import com.tokyo.supermix.server.services.PlantService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin
public class CustomerController {
  @Autowired
  private Mapper mapper;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private CustomerService customerService;
  @Autowired
  private PlantService plantService;
  private static final Logger logger = Logger.getLogger(CustomerController.class);

  @GetMapping(value = EndpointURI.CUSTOMERS)
  public ResponseEntity<Object> getAllCustomers(@CurrentUser UserPrincipal currentUser) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.CUSTOMERS,
        mapper.map(customerService.getAllCustomers(currentUser), CustomerResponseDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @PostMapping(value = EndpointURI.CUSTOMER)
  public ResponseEntity<Object> saveCustomer(
      @Valid @RequestBody CustomerRequestDto customerRequestDto) {
    if (customerService.isNameExist(customerRequestDto.getName())) {
      logger.debug("Name is already exists:saveCustomer(), isNameAlreadyExist:{}");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.CUSTOMER,
          validationFailureStatusCodes.getCustomerAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    customerService.saveCustomer(mapper.map(customerRequestDto, Customer.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_CUSTOMER_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.CUSTOMER_BY_ID)
  public ResponseEntity<Object> getCustomerById(@PathVariable Long id) {
    if (customerService.isCustomerExist(id)) {
      logger.debug("Get Customer By Id");
      return new ResponseEntity<>(new ContentResponse<>(Constants.CUSTOMER,
          mapper.map(customerService.getCustomerById(id), CustomerResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.CUSTOMER_ID,
        validationFailureStatusCodes.getCustomerNotExist()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(value = EndpointURI.CUSTOMER_BY_ID)
  public ResponseEntity<Object> deleteCustomer(@PathVariable Long id) {
    if (customerService.isCustomerExist(id)) {
      logger.debug("delete customer by id");
      customerService.deleteCustomer(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.CUSTOMER_DELETED), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.CUSTOMER_ID,
        validationFailureStatusCodes.getCustomerNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.CUSTOMER)
  public ResponseEntity<Object> updateCustomer(
      @Valid @RequestBody CustomerRequestDto customerRequestDto) {
    if (customerService.isCustomerExist(customerRequestDto.getId())) {
      if (customerService.isUpdatedCustomerEmailExist(customerRequestDto.getId(),
          customerRequestDto.getEmail())) {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMAIL,
            validationFailureStatusCodes.getEmailAlreadyExist()), HttpStatus.BAD_REQUEST);
      }
      if (customerService.isUpdatedCustomerNameExist(customerRequestDto.getId(),
          customerRequestDto.getName())) {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.CUSTOMER,
            validationFailureStatusCodes.getCustomerAlreadyExist()), HttpStatus.BAD_REQUEST);
      }
      customerService.saveCustomer(mapper.map(customerRequestDto, Customer.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_CUSTOMER_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.CUSTOMER_ID,
        validationFailureStatusCodes.getCustomerNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.CUSTOMER_SEARCH)
  public ResponseEntity<Object> getCustomerSearch(
      @QuerydslPredicate(root = Customer.class) Predicate predicate,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.CUSTOMERS,
            customerService.searchCustomer(predicate, page, size), RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.GET_CUSTOMERS_BY_PLANT_CODE)
  public ResponseEntity<Object> getCustomerByPlantCode(@PathVariable String plantCode) {
    if (plantService.isPlantExist(plantCode)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.CUSTOMERS,
          mapper.map(customerService.getCustomerByPlantCode(plantCode), CustomerResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }
}

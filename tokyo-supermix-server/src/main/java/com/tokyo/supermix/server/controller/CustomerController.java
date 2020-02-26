package com.tokyo.supermix.server.controller;

import java.util.List;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.CustomerDto;
import com.tokyo.supermix.data.entities.Customer;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.CustomerService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
public class CustomerController {
  @Autowired
  private Mapper mapper;

  @Autowired
  ValidationFailureStatusCodes validationFailureStatusCodes;

  @Autowired
  CustomerService customerService;
  private static final Logger logger = Logger.getLogger(CustomerController.class);

  // get all customers
  @GetMapping(value = EndpointURI.CUSTOMERS)
  public ResponseEntity<Object> getAllCustomers() {
    List<CustomerDto> customerDtoList =
        mapper.map(customerService.getAllCustomers(), CustomerDto.class);
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.CUSTOMERS, customerDtoList, RestApiResponseStatus.OK), null,
        HttpStatus.OK);
  }

  // Add Customer
  @PostMapping(value = EndpointURI.CUSTOMER)
  public ResponseEntity<Object> saveCustomer(@Valid @RequestBody CustomerDto customerDto) {
    if (customerService.isEmailExist(customerDto.getEmail())) {
      logger.debug("email is already exists:saveCustomer(), isEmailAlreadyExist:{}");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMAIL,
          validationFailureStatusCodes.getCustomerAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    Customer customer = mapper.map(customerDto, Customer.class);
    customerService.saveCustomer(customer);
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_CUSTOMER_SUCCESS),
        HttpStatus.OK);
  }

  // Get Customer By Id
  @GetMapping(value = EndpointURI.GET_CUSTOMER_BY_ID)
  public ResponseEntity<Object> getCustomerById(@PathVariable Long id) {
    if (customerService.isCustomerExist(id)) {
      logger.debug("Get Customer By Id");
      Customer customer = customerService.getCustomerById(id);
      return new ResponseEntity<>(new ContentResponse<>(Constants.CUSTOMER,
          mapper.map(customer, CustomerDto.class), RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.CUSTOMER_ID,
        validationFailureStatusCodes.getCustomerNotExist()), HttpStatus.BAD_REQUEST);
  }

  // Delete Customer
  @DeleteMapping(value = EndpointURI.DELETE_CUSTOMER)
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

  // Update Customer
  @PutMapping(value = EndpointURI.CUSTOMER)
  public ResponseEntity<Object> updateCustomer(@Valid @RequestBody CustomerDto customerDto) {
    if (customerService.isCustomerExist(customerDto.getId())) {
      if (customerService.isUpdatedCustomerEmailExist(customerDto.getId(),
          customerDto.getEmail())) {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMAIL,
            validationFailureStatusCodes.getCustomerAlreadyExist()), HttpStatus.BAD_REQUEST);
      }
      customerService.saveCustomer(mapper.map(customerDto, Customer.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_CUSTOMER_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.CUSTOMER_ID,
        validationFailureStatusCodes.getCustomerNotExist()), HttpStatus.BAD_REQUEST);
  }

}

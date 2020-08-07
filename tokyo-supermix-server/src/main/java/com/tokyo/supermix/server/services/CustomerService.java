package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Page;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.dto.CustomerResponseDto;
import com.tokyo.supermix.data.entities.Customer;
import com.tokyo.supermix.security.UserPrincipal;

public interface CustomerService {
  public void saveCustomer(Customer customer, List<String> plantCodes);

  public List<CustomerResponseDto> getAllCustomersByCurrentUser(UserPrincipal currentUser);

  public List<Customer> getAllCustomers();
  
  public List<CustomerResponseDto> getAllCustomer();

  public boolean isEmailExist(String email);

  public  List<CustomerResponseDto> getCustomerByPlantCode(String plantCode);

  boolean isCustomerExist(Long id);

  public CustomerResponseDto getCustomerById(Long id);

  public boolean isUpdatedCustomerEmailExist(Long id, String email);

  public boolean isUpdatedCustomerNameExist(Long id, String name);

  public void deleteCustomer(Long id);

  public boolean isNameExist(String name);

  public Page<Customer> searchCustomer(Predicate predicate, int page, int size);
}

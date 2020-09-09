
package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.dto.CustomerResponseDto;
import com.tokyo.supermix.data.entities.Customer;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.security.UserPrincipal;

public interface CustomerService {
  public void saveCustomer(Customer customer, List<String> plantCodes);

  public List<CustomerResponseDto> getAllCustomersByCurrentUser(UserPrincipal currentUser);

  public List<Customer> getAllCustomers();

  // public List<CustomerResponseDto> getAllCustomer();

  public boolean isEmailExist(String email);

  // public List<CustomerResponseDto> getCustomerByPlantCode(String plantCode);

  boolean isCustomerExist(Long id);

  public CustomerResponseDto getCustomerById(Long id);

  public boolean isUpdatedCustomerEmailExist(Long id, String email);

  public boolean isUpdatedCustomerNameExist(Long id, String name);

  public void deleteCustomer(Long id);

  public boolean isNameExist(String name);

  public List<CustomerResponseDto> getAllCustomer(Pageable pageable);

  public List<CustomerResponseDto> getCustomerByPlantCode(String plantCode, Pageable pageable);

  public Long getCountCustomer();

  public Long getCountCustomerByPlantCode(String plantCode);

  public List<Customer> getCustomerNameByPlantCode(String plantCode, String name);

  public List<Customer> getCustomerName(String name);

  public List<CustomerResponseDto> searchCustomerByPlantCode(String name, String email,
      String phoneNumber, String address, BooleanBuilder booleanBuilder, String plantCode,
      Pageable pageable, Pagination pagination);
}

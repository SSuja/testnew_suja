package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.Customer;

public interface CustomerService {
  public void saveCustomer(Customer customer);

  public List<Customer> getAllCustomers();

  public boolean isEmailExist(String email);

  boolean isCustomerExist(Long id);

  public Customer getCustomerById(Long id);

  public boolean isUpdatedCustomerEmailExist(Long id, String email);

  public void deleteCustomer(Long id);
}

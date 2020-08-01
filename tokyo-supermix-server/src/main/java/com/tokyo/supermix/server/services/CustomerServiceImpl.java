package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.Customer;
import com.tokyo.supermix.data.entities.Plant;
import com.tokyo.supermix.data.entities.SupplierCategory;
import com.tokyo.supermix.data.repositories.CustomerRepository;
import com.tokyo.supermix.data.repositories.PlantRepository;
import com.tokyo.supermix.notification.EmailNotification;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.privilege.PermissionConstants;

@Service
public class CustomerServiceImpl implements CustomerService {
  @Autowired
  private CustomerRepository customerRepository;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;
  @Autowired
  private EmailNotification emailNotification;
  @Autowired
  private PlantRepository plantRepository;

  @Transactional(readOnly = true)
  public List<Customer> getAllCustomersByCurrentUser(UserPrincipal currentUser) {
    return customerRepository.findByPlantCodeIn(currentUserPermissionPlantService
        .getPermissionPlantCodeByCurrentUser(currentUser, PermissionConstants.VIEW_CUSTOMER));
  }

  @Transactional
  public void saveCustomer(Customer customer, List<String> plantCodes) {
    // Customer customerObj = customerRepository.save(customer);
    // if (customerObj != null)
    // emailNotification.sendCustomerCreationEmail(customerObj);

    List<Plant> plantList = new ArrayList<Plant>();
    plantCodes.forEach(code -> plantList.add(plantRepository.findById(code).get()));
    customer.setPlant(plantList);
    customerRepository.save(customer);


  }

  @Transactional(readOnly = true)
  public boolean isEmailExist(String email) {
    return customerRepository.existsByEmail(email);
  }

  @Transactional(readOnly = true)
  public boolean isCustomerExist(Long id) {
    return customerRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public Customer getCustomerById(Long id) {
    return customerRepository.findById(id).get();
  }

  public boolean isUpdatedCustomerEmailExist(Long id, String email) {
    if ((!getCustomerById(id).getEmail().equalsIgnoreCase(email)) && (isEmailExist(email))) {
      return true;
    }
    return false;
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteCustomer(Long id) {
    customerRepository.deleteById(id);

  }

  @Transactional(readOnly = true)
  public boolean isNameExist(String name) {
    return customerRepository.existsByName(name);
  }

  public boolean isUpdatedCustomerNameExist(Long id, String name) {
    if ((!getCustomerById(id).getName().equalsIgnoreCase(name)) && (isNameExist(name))) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public Page<Customer> searchCustomer(Predicate predicate, int page, int size) {
    return customerRepository.findAll(predicate,
        PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
  }

  @Transactional(readOnly = true)
  public List<Customer> getCustomerByPlantCode(String plantCode) {
    return customerRepository.findByPlantCode(plantCode);
  }

  @Transactional(readOnly = true)
  public List<Customer> getAllCustomers() {
    return customerRepository.findAll();
  }
}

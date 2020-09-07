package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.dto.CustomerResponseDto;
import com.tokyo.supermix.data.dto.PlantDto;
import com.tokyo.supermix.data.entities.Customer;
import com.tokyo.supermix.data.entities.Plant;
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
  public List<CustomerResponseDto> getAllCustomersByCurrentUser(UserPrincipal currentUser) {
    ArrayList<CustomerResponseDto> customerResponseDtoList = new ArrayList<CustomerResponseDto>();
    List<Customer> customerList =
        customerRepository.findByPlantCodeIn(currentUserPermissionPlantService
            .getPermissionPlantCodeByCurrentUser(currentUser, PermissionConstants.VIEW_CUSTOMER));
    for (Customer customer : customerList) {
      CustomerResponseDto customerResponseDto = new CustomerResponseDto();
      customerResponseDto.setId(customer.getId());
      customerResponseDto.setAddress(customer.getAddress());
      customerResponseDto.setCreatedAt(customer.getCreatedAt().toString());
      customerResponseDto.setUpdatedAt(customer.getUpdatedAt().toString());
      customerResponseDto.setName(customer.getName());
      customerResponseDto.setPhoneNumber(customer.getPhoneNumber());
      customerResponseDto.setEmail(customer.getEmail());
      customerResponseDto.setPlants(getAllPlant(customer.getId()));
      customerResponseDtoList.add(customerResponseDto);
    }
    return customerResponseDtoList;
  }

  @Transactional
  public void saveCustomer(Customer customer, List<String> plantCodes) {
    List<Plant> plantList = new ArrayList<Plant>();
    plantCodes.forEach(code -> plantList.add(plantRepository.findById(code).get()));
    customer.setPlant(plantList);
    customerRepository.save(customer);
    emailNotification.sendCustomerCreationEmail(customer);
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
  public CustomerResponseDto getCustomerById(Long id) {
    Customer customer = customerRepository.findById(id).get();
    CustomerResponseDto customerResponseDto = new CustomerResponseDto();
    customerResponseDto.setId(customer.getId());
    customerResponseDto.setAddress(customer.getAddress());
    customerResponseDto.setCreatedAt(customer.getCreatedAt().toString());
    customerResponseDto.setUpdatedAt(customer.getUpdatedAt().toString());
    customerResponseDto.setName(customer.getName());
    customerResponseDto.setPhoneNumber(customer.getPhoneNumber());
    customerResponseDto.setEmail(customer.getEmail());
    customerResponseDto.setPlants(getAllPlant(customer.getId()));
    return customerResponseDto;
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
  public List<CustomerResponseDto> getCustomerByPlantCode(String plantCode, Pageable pageable) {
    ArrayList<CustomerResponseDto> customerResponseDtoList = new ArrayList<CustomerResponseDto>();
    List<Customer> customerList = customerRepository.findAllByPlantCode(plantCode, pageable);
    for (Customer customer : customerList) {
      CustomerResponseDto customerResponseDto = new CustomerResponseDto();
      customerResponseDto.setId(customer.getId());
      customerResponseDto.setAddress(customer.getAddress());
      customerResponseDto.setCreatedAt(customer.getCreatedAt().toString());
      customerResponseDto.setUpdatedAt(customer.getUpdatedAt().toString());
      customerResponseDto.setName(customer.getName());
      customerResponseDto.setPhoneNumber(customer.getPhoneNumber());
      customerResponseDto.setEmail(customer.getEmail());
      customerResponseDto.setPlants(getAllPlant(customer.getId()));
      customerResponseDtoList.add(customerResponseDto);
    }
    return customerResponseDtoList;

  }

  public List<CustomerResponseDto> getAllCustomer(Pageable pageable) {
    ArrayList<CustomerResponseDto> customerResponseDtoList = new ArrayList<CustomerResponseDto>();
    List<Customer> customerList = customerRepository.findAll(pageable).toList();
    for (Customer customer : customerList) {
      CustomerResponseDto customerResponseDto = new CustomerResponseDto();
      customerResponseDto.setId(customer.getId());
      customerResponseDto.setAddress(customer.getAddress());
      customerResponseDto.setCreatedAt(customer.getCreatedAt().toString());
      customerResponseDto.setUpdatedAt(customer.getUpdatedAt().toString());
      customerResponseDto.setName(customer.getName());
      customerResponseDto.setPhoneNumber(customer.getPhoneNumber());
      customerResponseDto.setEmail(customer.getEmail());
      customerResponseDto.setPlants(getAllPlant(customer.getId()));
      customerResponseDtoList.add(customerResponseDto);
    }
    return customerResponseDtoList;
  }

  public List<PlantDto> getAllPlant(Long customerId) {
    List<PlantDto> plantDtoList = new ArrayList<PlantDto>();
    Customer Customer = customerRepository.findById(customerId).get();
    for (Plant plant : Customer.getPlant()) {
      PlantDto plantDto = new PlantDto();
      plantDto.setCode(plant.getCode());
      plantDto.setAddress(plant.getAddress());
      plantDto.setDescription(plant.getDescription());
      plantDto.setFaxNumber(plant.getFaxNumber());
      plantDto.setName(plant.getName());
      plantDto.setPhoneNumber(plant.getPhoneNumber());
      plantDtoList.add(plantDto);
    }
    return plantDtoList;
  }

  @Transactional(readOnly = true)
  public List<Customer> getAllCustomers() {

    return customerRepository.findAll();
  }

  @Transactional(readOnly = true)
  public Long getCountCustomer() {
    return customerRepository.count();
  }

  @Transactional(readOnly = true)
  public Long getCountCustomerByPlantCode(String plantCode) {
    return customerRepository.countByPlantCode(plantCode);
  }
}

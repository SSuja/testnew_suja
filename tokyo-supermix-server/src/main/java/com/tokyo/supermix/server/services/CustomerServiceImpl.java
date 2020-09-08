package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.dto.CustomerResponseDto;
import com.tokyo.supermix.data.dto.PlantDto;
import com.tokyo.supermix.data.entities.Customer;
import com.tokyo.supermix.data.entities.Plant;
import com.tokyo.supermix.data.entities.QCustomer;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.CustomerRepository;
import com.tokyo.supermix.data.repositories.PlantRepository;
import com.tokyo.supermix.notification.EmailNotification;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.Constants;
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
  @Autowired
  private Mapper mapper;

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

  // @Transactional(readOnly = true)
  // public Iterable<Customer> searchCustomer(Predicate predicate) {
  // return customerRepository.findAll(predicate, Sort.by(Sort.Direction.ASC, "id"));
  // }

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

  @Transactional(readOnly = true)
  public List<Customer> getCustomerNameByPlantCode(String plantCode, String name) {
    if (name.isEmpty()) {
      return null;
    }
    return customerRepository.findByPlantCodeAndNameStartsWith(plantCode, name);
  }

  @Transactional(readOnly = true)
  public List<Customer> getCustomerName(String name) {
    if (name.isEmpty()) {
      return null;
    }
    return customerRepository.findByNameStartsWith(name);
  }

  @Transactional(readOnly = true)
  public List<CustomerResponseDto> searchCustomerByPlantCode(String name, String email,
      String plantName, String phoneNumber, String address, BooleanBuilder booleanBuilder,
      String plantCode, Pageable pageable, Pagination pagination) {
    Plant plant = new Plant();
    if (name != null && !name.isEmpty()) {
      booleanBuilder.and(QCustomer.customer.name.contains(name));
    }
    if (email != null && !email.isEmpty()) {
      booleanBuilder.and(QCustomer.customer.email.contains(email));
    }
    if (plantName != null && !plantName.isEmpty()) {
      plant.setName(plantName);
      booleanBuilder.and(QCustomer.customer.plant.contains(plant));
    }
    if (phoneNumber != null && !phoneNumber.isEmpty()) {
      booleanBuilder.and(QCustomer.customer.phoneNumber.contains(phoneNumber));
    }
    if (address != null && !address.isEmpty()) {
      booleanBuilder.and(QCustomer.customer.address.contains(address));
    }
    if (plantCode != null && !plantCode.isEmpty()
        && !(plantCode.equalsIgnoreCase(Constants.ADMIN))) {
      plant.setCode(plantCode);
      booleanBuilder.and(QCustomer.customer.plant.contains(plant));
    }
    List<Customer> customerList = new ArrayList<>();
    customerRepository.findAll(booleanBuilder, pageable).stream()
        .filter(customers -> customerList.add(customers)).collect(Collectors.toList());
    pagination.setTotalRecords(
        ((Collection<Customer>) customerRepository.findAll(booleanBuilder)).stream().count());
    return mapper.map(customerList, CustomerResponseDto.class);
  }
}

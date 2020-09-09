package com.tokyo.supermix.server.services;

import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.dto.EmployeeResponseDto;
import com.tokyo.supermix.data.entities.ConfirmationToken;
import com.tokyo.supermix.data.entities.Employee;
import com.tokyo.supermix.data.entities.QEmployee;
import com.tokyo.supermix.data.enums.UserType;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.ConfirmationTokenRepository;
import com.tokyo.supermix.data.repositories.EmployeeRepository;
import com.tokyo.supermix.notification.EmailNotification;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.privilege.PermissionConstants;

@Service
public class EmployeeServiceImpl implements EmployeeService {
  @Autowired
  private EmployeeRepository employeeRepository;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;
  @Autowired
  private EmailNotification emailNotification;
  @Autowired
  private Mapper mapper;
  @Autowired
  private ConfirmationTokenRepository confirmationTokenRepository;

  @Transactional()
  public void createEmployee(Employee employee, HttpServletRequest request) {
    employee.setHasUser(false);
    Employee employeeObj = employeeRepository.save(employee);
    ConfirmationToken confirmationToken = new ConfirmationToken(employee);
    confirmationTokenRepository.save(confirmationToken);
    if (employeeObj != null) {
      emailNotification.sendEmployeeEmail(employeeObj);
      emailNotification.sendEmployeeConformation(employeeObj, confirmationToken, request);
    }
  }

  @Transactional(readOnly = true)
  public boolean isEmailExist(String email) {
    return employeeRepository.existsByEmail(email);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteEmployee(Long id) {
    if (confirmationTokenRepository.findByEmployeeId(id) != null) {
      confirmationTokenRepository
          .deleteById(confirmationTokenRepository.findByEmployeeId(id).getTokenid());
    }
    employeeRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public boolean isEmployeeExist(Long id) {
    return employeeRepository.existsById(id);
  }

  @Transactional()
  public Employee getEmployeeById(Long id) {
    return employeeRepository.findById(id).get();
  }

  @Transactional()
  public List<Employee> getAllEmployees() {
    return employeeRepository.findAll();
  }

  @Transactional()
  public List<Employee> getAllEmployeesByPlant(UserPrincipal currentUser, Pageable pageable) {
    return currentUser.getUserType().name().equalsIgnoreCase(UserType.NON_PLANT_USER.name())
        && currentUser.getRoles().contains(1L)
            ? employeeRepository.findAll()
            : employeeRepository.findByPlantCodeIn(
                currentUserPermissionPlantService.getPermissionPlantCodeByCurrentUser(currentUser,
                    PermissionConstants.VIEW_EMPLOYEE));
  }

  @Override
  public boolean isUpdatedEmployeeEmailExist(Long id, String email) {
    if ((!getEmployeeById(id).getEmail().equalsIgnoreCase(email)) && (isEmailExist(email))) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public Page<Employee> searchEmployee(Predicate predicate, int size, int page) {
    return employeeRepository.findAll(predicate,
        PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
  }

  @Transactional(readOnly = true)
  public List<Employee> getEmployeeByPlantCode(String plantCode, Pageable pageable) {
    return employeeRepository.findAllByPlantCode(plantCode, pageable);
  }

  @Transactional
  public void updateEmployee(Employee employee, HttpServletRequest request) {
    if (!employee.getEmail().equals(employeeRepository.findById(employee.getId()).get().getEmail())
        && employee.isEnabled() == false) {
      Employee employeeObj = employeeRepository.save(employee);
      ConfirmationToken confirmationToken = new ConfirmationToken(employee);
      confirmationToken
          .setTokenid(confirmationTokenRepository.findByEmployeeId(employee.getId()).getTokenid());
      confirmationTokenRepository.save(confirmationToken);
      emailNotification.sendEmployeeEmail(employeeObj);
      emailNotification.sendEmployeeConformation(employeeObj, confirmationToken, request);
    } else {
      employeeRepository.save(employee);
    }
  }

  @Override
  public void updateEmployeeWithConfirmation(String confirmationToken) {
    ConfirmationToken token =
        confirmationTokenRepository.findByConfirmationToken(confirmationToken);
    if (token != null) {
      Employee employee = employeeRepository.findByEmailIgnoreCase(token.getEmployee().getEmail());
      employee.setEnabled(true);
      employeeRepository.save(employee);
    }
  }

  @Transactional(readOnly = true)
  public Long getCountEmployee() {
    return employeeRepository.count();
  }

  @Transactional(readOnly = true)
  public Long getCountEmployeeByPlantCode(String plantCode) {
    return employeeRepository.countByPlantCode(plantCode);
  }

  @Transactional(readOnly = true)
  public List<Employee> getEmployeeByPlantCode(String plantCode) {
    return employeeRepository.findByPlantCode(plantCode);
  }

  @Transactional(readOnly = true)
  public List<EmployeeResponseDto> searchEmployee(BooleanBuilder booleanBuilder, String firstName,
      String email, String lastName, String address, String phoneNumber, String plantName,
      String designationName, String plantCode, Pageable pageable, Pagination pagination) {
    if (firstName != null && !firstName.isEmpty()) {
      booleanBuilder.and(QEmployee.employee.firstName.startsWithIgnoreCase(firstName));
    }
    if (email != null && !email.isEmpty()) {
      booleanBuilder.and(QEmployee.employee.email.startsWithIgnoreCase(email));
    }
    if (lastName != null && !lastName.isEmpty()) {
      booleanBuilder.and(QEmployee.employee.lastName.startsWithIgnoreCase(lastName));
    }
    if (address != null && !address.isEmpty()) {
      booleanBuilder.and(QEmployee.employee.address.startsWithIgnoreCase(address));
    }
    if (phoneNumber != null && !phoneNumber.isEmpty()) {
      booleanBuilder.and(QEmployee.employee.phoneNumber.startsWithIgnoreCase(phoneNumber));
    }
    if (plantName != null && !plantName.isEmpty()) {
      booleanBuilder.and(QEmployee.employee.plant.name.startsWithIgnoreCase(plantName));
    }
    if (designationName != null && !designationName.isEmpty()) {
      booleanBuilder.and(QEmployee.employee.designation.name.startsWithIgnoreCase(designationName));
    }
    if (plantCode != null && !plantCode.isEmpty()
        && !(plantCode.equalsIgnoreCase(Constants.ADMIN))) {
      booleanBuilder.and(QEmployee.employee.plant.code.startsWithIgnoreCase(plantCode));
    }
    pagination.setTotalRecords(
        ((Collection<Employee>) employeeRepository.findAll(booleanBuilder)).stream().count());
    return mapper.map(employeeRepository.findAll(booleanBuilder, pageable).toList(),
        EmployeeResponseDto.class);

  }

  @Transactional(readOnly = true)
  public List<Employee> getFirstNameByPlantCode(String plantCode, String firstName) {
    if (firstName.isEmpty()) {
      return null;
    }
    return employeeRepository.findByPlantCodeAndFirstNameStartsWith(plantCode, firstName);
  }

  @Transactional(readOnly = true)
  public List<Employee> getFirstName(String firstName) {
    if (firstName.isEmpty()) {
      return null;
    }
    return employeeRepository.findByFirstNameStartsWith(firstName);
  }
}

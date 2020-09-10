package com.tokyo.supermix.server.services;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.dto.EmployeeResponseDto;
import com.tokyo.supermix.data.entities.Employee;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.security.UserPrincipal;

public interface EmployeeService {
  public void createEmployee(Employee employee, HttpServletRequest request);

  public void updateEmployee(Employee employee, HttpServletRequest request);

  public boolean isEmailExist(String email);

  public void deleteEmployee(Long id);

  boolean isEmployeeExist(Long id);

  public Employee getEmployeeById(Long id);

  public boolean isUpdatedEmployeeEmailExist(Long id, String email);

  public List<Employee> getAllEmployees();

  public List<Employee> getAllEmployeesByPlant(UserPrincipal currentUser, Pageable pageable);

  public Page<Employee> searchEmployee(Predicate predicate, int size, int page);

  public List<Employee> getEmployeeByPlantCode(String plantCode);

  public void updateEmployeeWithConfirmation(String confirmationToken);

  public Long getCountEmployee();

  public Long getCountEmployeeByPlantCode(String plantCode);

  public List<Employee> getEmployeeByPlantCode(String plantCode, Pageable pageable);

  public List<EmployeeResponseDto> searchEmployee(BooleanBuilder booleanBuilder, String firstName,
      String email, String lastName, String address, String phoneNumber, String plantName,
      String designationName, String plantCode, Pageable pageable, Pagination pagination);

  public List<Employee> getFirstNameByPlantCode(String plantCode, String firstName);

  public List<Employee> getFirstName(String firstName);
}

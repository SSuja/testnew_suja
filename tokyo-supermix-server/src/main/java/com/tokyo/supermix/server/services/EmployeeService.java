package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Page;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.Employee;

public interface EmployeeService {
  /* Save Employee */
  public void saveEmployee(Employee employee);

  /* Check Existing email */
  public boolean isEmailExist(String email);

  /* Delete Employee */
  public void deleteEmployee(Long id);

  /* Check Existing id */
  boolean isEmployeeExist(Long id);

  /* Get Employee By Id */
  public Employee getEmployeeById(Long id);

  /* Check updated Email */
  public boolean isUpdatedEmployeeEmailExist(Long id, String email);

  /* Get All Employees */
  public List<Employee> getAllEmployees();

  public Page<Employee> searchEmployee(Predicate predicate, int size, int page);
}

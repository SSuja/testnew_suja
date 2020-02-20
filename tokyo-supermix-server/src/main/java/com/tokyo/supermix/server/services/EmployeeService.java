package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.dto.EmployeeRequestDto;
import com.tokyo.supermix.data.entities.Employee;

public interface EmployeeService {
  /* Add Employee */
  public void createEmployee(Employee employee);

  /* Check Existing email */
  public boolean isEmailExist(String email);

  /* Delete Employee */
  public void deleteEmployee(Long id);

  /* Check Existing id */
  boolean isEmployeeExist(Long id);

  /* Get Employee By Id */
  public Employee getEmployeeById(Long id);

  /* Update Employee */
  public Employee updateEmployee(Employee employee);

  /* Check Emails */
  boolean isNotSameEmailExists(Employee employeeData, EmployeeRequestDto employeeDto);

  /* Get All Employees */
  public List<Employee> getAllEmployees();
}

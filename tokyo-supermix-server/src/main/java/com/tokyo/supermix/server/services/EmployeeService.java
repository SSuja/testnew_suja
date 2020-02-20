package com.tokyo.supermix.server.services;

import java.util.List;
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

  /* Check updated Email */
  public boolean isUpdatedEmployeeEmailExist(Long id, String email);

  /* Get All Employees */
  public List<Employee> getAllEmployees();
}

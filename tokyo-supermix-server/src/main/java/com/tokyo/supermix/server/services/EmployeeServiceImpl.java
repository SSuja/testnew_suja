package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.EmployeeRequestDto;
import com.tokyo.supermix.data.entities.Employee;
import com.tokyo.supermix.data.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepository;

  @Transactional()
  public void createEmployee(Employee employee) {
    employeeRepository.save(employee);

  }

  @Transactional(readOnly = true)
  public boolean isEmailExist(String email) {
    return employeeRepository.existsByEmail(email);

  }

  @Transactional()
  public void deleteEmployee(Long id) {
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
  public Employee updateEmployee(Employee employee) {
    return employeeRepository.save(employee);

  }

  @Override
  public boolean isNotSameEmailExists(Employee employeeData, EmployeeRequestDto employeeDto) {
    return employeeData.getEmail().equalsIgnoreCase(employeeDto.getEmail());

  }
}

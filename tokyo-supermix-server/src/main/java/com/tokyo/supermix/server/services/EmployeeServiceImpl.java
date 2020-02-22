package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.Employee;
import com.tokyo.supermix.data.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
  @Autowired
  private EmployeeRepository employeeRepository;

  @Transactional()
  public void saveEmployee(Employee employee) {
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

  @Override
  public boolean isUpdatedEmployeeEmailExist(Long id, String email) {
    if ((!getEmployeeById(id).getEmail().equalsIgnoreCase(email)) && (isEmailExist(email))) {
      return true;
    }
    return false;
  }


}

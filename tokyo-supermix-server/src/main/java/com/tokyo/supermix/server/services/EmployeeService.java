package com.tokyo.supermix.server.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.Employee;
import com.tokyo.supermix.security.UserPrincipal;

public interface EmployeeService {
	public void createEmployee(Employee employee);
	public void updateEmployee(Employee employee);
	public boolean isEmailExist(String email);

	public void deleteEmployee(Long id);

	boolean isEmployeeExist(Long id);

	public Employee getEmployeeById(Long id);

	public boolean isUpdatedEmployeeEmailExist(Long id, String email);

	public List<Employee> getAllEmployees();
	
	public List<Employee> getAllEmployeesByPlant(UserPrincipal currentUser) ;

	public Page<Employee> searchEmployee(Predicate predicate, int size, int page);

	public List<Employee> getEmployeeByPlantCode(String plantCode);
}

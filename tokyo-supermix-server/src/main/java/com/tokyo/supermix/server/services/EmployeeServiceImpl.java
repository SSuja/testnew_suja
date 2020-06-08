package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.Employee;
import com.tokyo.supermix.data.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	@Transactional()
	public void createEmployee(Employee employee) {
		employee.setHasUser(false);
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

	@Transactional(readOnly = true)
	public Page<Employee> searchEmployee(Predicate predicate, int size, int page) {
		return employeeRepository.findAll(predicate, PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
	}

	@Transactional(readOnly = true)
	public List<Employee> getEmployeeByPlantCode(String plantCode) {
		return employeeRepository.findByPlantCode(plantCode);
	}

	@Override
	public void updateEmployee(Employee employee) {
		employeeRepository.save(employee);
	}
}

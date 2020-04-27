package com.tokyo.supermix.querydsl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.Employee;
import com.tokyo.supermix.data.entities.QEmployee;
import com.tokyo.supermix.data.repositories.EmployeeRepository;
import com.tokyo.supermix.server.services.EmployeeService;


@RestController
public class EmployeeSearchController {
  @Autowired
  private EmployeeService employeeService;
  @Autowired
  private EmployeeRepository employeeRepository;

  @GetMapping("/searchEmployee")
  public Page<Employee> getPersonsSimplified(
      @QuerydslPredicate(root = Employee.class) Predicate predicate,
      @RequestParam(name = "page", defaultValue = "0") int page,
      @RequestParam(name = "size", defaultValue = "500") int size) {
    return employeeService.searchEmployee(predicate, page, size);
  }

  @GetMapping("/search")
  public Page<Employee> getMixDesignInbetweenOperation(
      @RequestParam(name = "page", defaultValue = "0") int page,
      @RequestParam(name = "size", defaultValue = "500") int size,
      @RequestParam(name = "firstName", required = false) String firstName,
      @RequestParam(name = "lastName", required = false) String lastName,
      @RequestParam(name = "email", required = false) String email,
      @RequestParam(name = "phoneNumber", required = false) String phoneNumber,
      @RequestParam(name = "address", required = false) String address) {
    BooleanBuilder booleanBuilder = new BooleanBuilder();

    if (firstName != null && !firstName.isEmpty()) {
      booleanBuilder.and(QEmployee.employee.firstName.eq(firstName));
    }
    if (lastName != null && !lastName.isEmpty()) {
      booleanBuilder.and(QEmployee.employee.lastName.eq(lastName));
    }
    if (email != null && !email.isEmpty()) {
      booleanBuilder.and(QEmployee.employee.email.eq(email));
    }
    if (phoneNumber != null && !phoneNumber.isEmpty()) {
      booleanBuilder.and(QEmployee.employee.phoneNumber.eq(phoneNumber));
    }
    if (address != null && !address.isEmpty()) {
      booleanBuilder.and(QEmployee.employee.address.eq(address));
    }
    return employeeRepository.findAll(booleanBuilder.getValue(),
        PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
  }
}

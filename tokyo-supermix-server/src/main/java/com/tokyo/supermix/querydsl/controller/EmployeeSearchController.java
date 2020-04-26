package com.tokyo.supermix.querydsl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.Employee;
import com.tokyo.supermix.server.services.EmployeeService;


@RestController
public class EmployeeSearchController {
  @Autowired
  private EmployeeService employeeService;

  @GetMapping("/searchEmployee")
  public Page<Employee> getPersonsSimplified(
      @QuerydslPredicate(root = Employee.class) Predicate predicate,
      @RequestParam(name = "page", defaultValue = "0") int page,
      @RequestParam(name = "size", defaultValue = "500") int size) {
    return employeeService.searchEmployee(predicate, page, size);
  }
}

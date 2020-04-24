package com.tokyo.supermix.querydsl.service;

import java.util.List;
import com.tokyo.supermix.data.entities.Employee;
import com.tokyo.supermix.querydsl.common.SearchCriteria;

public interface EmployeeQuerydslService {
  List<Employee> searchUser(List<SearchCriteria> params);

  public List<Employee> findEmployeesByPlantCodeQueryDSL(String plantCode);

}

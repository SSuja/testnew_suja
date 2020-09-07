package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import com.tokyo.supermix.data.entities.Employee;

@Repository
public interface EmployeeRepository
    extends JpaRepository<Employee, Long>, QuerydslPredicateExecutor<Employee> {
  boolean existsByEmail(String mail);

  List<Employee> findByPlantCode(String plantCode);

  List<Employee> findByPlantCodeIn(List<String> plantCodes);

  Employee findByEmailIgnoreCase(String email);

  Employee findByEmail(String email);

  Long countByPlantCode(String plantCode);

  List<Employee> findAllByPlantCode(String plantCode, Pageable pageable);
}

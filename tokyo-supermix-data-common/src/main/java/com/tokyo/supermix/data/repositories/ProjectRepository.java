package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.tokyo.supermix.data.entities.Project;

public interface ProjectRepository
    extends JpaRepository<Project, String>, QuerydslPredicateExecutor<Project> {

  List<Project> findByPlantCode(String plantCode);

  List<Project> findByCodeContaining(String code);

  List<Project> findByPlantCodeIn(List<String> plantCodes);

  List<Project> findByCustomerId(Long customerId);

  boolean existsByCustomerId(Long customerId);

  List<Project> findByCustomerIdAndPlantCode(Long customerId, String plantCode);

  public boolean existsByNameAndCustomerIdAndPlantCode(String name, Long customerId,
      String plantCode);
}

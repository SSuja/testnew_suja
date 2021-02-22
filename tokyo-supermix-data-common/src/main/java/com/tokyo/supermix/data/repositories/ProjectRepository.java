package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.tokyo.supermix.data.entities.Project;

public interface ProjectRepository
    extends JpaRepository<Project, String>, QuerydslPredicateExecutor<Project> {

  List<Project> findByPlantCode(String plantCode);

  List<Project> findByCodeContaining(String code);

  List<Project> findByPlantCodeIn(List<String> plantCodes, Pageable pageable);

  List<Project> findByCustomerId(Long customerId);

  boolean existsByCustomerId(Long customerId);

  List<Project> findByCustomerIdAndPlantCode(Long customerId, String plantCode);

  public boolean existsByNameAndCustomerIdAndPlantCode(String name, Long customerId,
      String plantCode);

  Project findByCode(String code);

  Long countByPlantCode(String plantCode);

  List<Project> findAllByPlantCode(String plantCode, Pageable pageable);

  List<Project> findByNameContaining(String name);

  List<Project> findByPlantCodeAndNameContaining(String plantCode, String name);

  Project findByName(String name);

  List<Project> findBySentMail(boolean sentMail);

  Page<Project> findAllByOrderByUpdatedAtDesc(Pageable pageable);

  List<Project> findByPlantCodeOrderByUpdatedAtDesc(String plantCode, Pageable pageable);

  List<Project> findByPlantCodeInOrderByUpdatedAtDesc(List<String> plantCodes, Pageable pageable);
}

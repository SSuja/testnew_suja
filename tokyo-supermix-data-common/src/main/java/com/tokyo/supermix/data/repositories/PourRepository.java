package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.tokyo.supermix.data.entities.Pour;

public interface PourRepository extends JpaRepository<Pour, Long>, QuerydslPredicateExecutor<Pour> {
  boolean existsByNameAndProjectCode(String name, String projectCode);

  List<Pour> findByProjectPlantCode(String plantCode);

  List<Pour> findByProjectPlantCodeIn(List<String> plantCodes, Pageable pageable);

  Long countByProjectPlantCode(String plantCode);

  List<Pour> findAllByProjectPlantCode(String plantCode, Pageable pageable);

  List<Pour> findByProjectPlantCodeAndNameStartsWith(String plantCode, String name);

  List<Pour> findByNameStartsWith(String name);
}

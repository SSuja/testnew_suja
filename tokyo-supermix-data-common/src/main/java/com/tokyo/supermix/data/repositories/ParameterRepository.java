package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.tokyo.supermix.data.entities.Parameter;
import com.tokyo.supermix.data.enums.ParameterDataType;
import com.tokyo.supermix.data.enums.ParameterType;

public interface ParameterRepository extends JpaRepository<Parameter, Long>,
    QuerydslPredicateExecutor<Parameter>, PagingAndSortingRepository<Parameter, Long> {
  boolean existsByName(String name);

  List<Parameter> findByParameterType(ParameterType parameterType);

  boolean existsByParameterType(ParameterType parameterType);

  List<Parameter> findByOrderByIdDesc();

  boolean existsByNameAndParameterType(String name, ParameterType parameterType);

  boolean existsByNameAndParameterTypeAndParameterDataType(String name, ParameterType parameterType,
      ParameterDataType parameterDataType);

  List<Parameter> findAllByOrderByIdDesc(Pageable pageable);
}

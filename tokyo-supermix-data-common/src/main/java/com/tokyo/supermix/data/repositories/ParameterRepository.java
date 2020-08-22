package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.tokyo.supermix.data.entities.Parameter;
import com.tokyo.supermix.data.enums.ParameterType;

public interface ParameterRepository
    extends JpaRepository<Parameter, Long>, QuerydslPredicateExecutor<Parameter> {
  boolean existsByName(String name);

  List<Parameter> findByParameterType(ParameterType parameterType);

  boolean existsByParameterType(ParameterType parameterType);

  List<Parameter> findAllByOrderByIdDesc();
  
  boolean existsByNameAndParameterType(String name,ParameterType parameterType);
}

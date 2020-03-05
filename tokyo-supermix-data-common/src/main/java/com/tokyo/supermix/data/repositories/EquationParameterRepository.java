package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.tokyo.supermix.data.entities.EquationParameter;

public interface EquationParameterRepository extends JpaRepository<EquationParameter, Long> {
  List<EquationParameter> findByEquationId(Long equationId);
  @Query(value = "SELECT *  FROM equation_parameter WHERE equation_id= ?1 and parameter_id= ?2", nativeQuery = true)
  Long isDuplicateRow(Long equationId, Long parameterId);
}

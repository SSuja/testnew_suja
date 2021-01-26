package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.tokyo.supermix.data.entities.AcceptedValue;
import com.tokyo.supermix.data.entities.TestConfigure;

public interface AcceptedValueRepository extends JpaRepository<AcceptedValue, Long>,
    QuerydslPredicateExecutor<AcceptedValue>, PagingAndSortingRepository<AcceptedValue, Long> {
  List<AcceptedValue> findByTestConfigure(TestConfigure testConfigure);

  public boolean existsByTestConfigureId(Long testConfigureId);

  public boolean existsByTestConfigureIdAndTestEquationId(Long testConfigureId,
      Long testEquationId);

  List<AcceptedValue> findByTestConfigureId(Long testConfigureId);

  public AcceptedValue findByTestConfigureIdAndTestEquationId(Long testConfigureId,
      Long testEquationId);

  AcceptedValue findByTestParameterId(Long testParameterId);

  public boolean existsByTestConfigureIdAndTestParameterId(Long testConfigureId,
      Long testParameterId);

  AcceptedValue findByTestParameterIdAndTestConfigureId(Long testParameterId, Long testConfigureId);

  Long countByTestConfigureIdAndFinalResultTrue(Long testConfigureId);

  Long countByTestConfigureId(Long testConfigureId);

  List<AcceptedValue> findByTestConfigureIdAndFinalResultTrue(Long testConfigureId);

  Page<AcceptedValue> findByTestConfigureIdOrderByUpdatedAtDesc(Pageable pageable,
      Long testConfigureId);
}

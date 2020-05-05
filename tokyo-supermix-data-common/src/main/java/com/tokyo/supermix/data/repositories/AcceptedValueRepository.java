package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.tokyo.supermix.data.entities.AcceptedValue;
import com.tokyo.supermix.data.entities.TestConfigure;

public interface AcceptedValueRepository
    extends JpaRepository<AcceptedValue, Long>, QuerydslPredicateExecutor<AcceptedValue> {
  List<AcceptedValue> findByTestConfigure(TestConfigure testConfigure);

  public boolean existsAcceptedValueByTestConfigureId(Long testConfigureId);

  AcceptedValue findByTestConfigureId(Long testConfigureId);
}

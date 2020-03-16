package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.AcceptedValue;
import com.tokyo.supermix.data.entities.Test;

public interface AcceptedValueRepository extends JpaRepository<AcceptedValue, Long> {
  List<AcceptedValue> findByTest(Test test);

  public boolean existsAcceptedValueByTestId(Long testId);
  
  AcceptedValue findByTestId(Long testId);
}

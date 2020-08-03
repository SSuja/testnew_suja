package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.FinishProductAcceptedValue;

public interface FinishProductAcceptedValueRepository
    extends JpaRepository<FinishProductAcceptedValue, Long> {
  List<FinishProductAcceptedValue> findByTestParameterId(Long testParameterId);
}

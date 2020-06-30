package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.tokyo.supermix.data.entities.ProcessSample;

public interface ProcessSampleRepository
    extends JpaRepository<ProcessSample, String>, QuerydslPredicateExecutor<ProcessSample> {
  ProcessSample findProcessSampleByCode(String code);

  List<ProcessSample> findByIncomingSamplePlantCode(String plantCode);

  List<ProcessSample> findByIncomingSamplePlantCodeIn(List<String> plantCodes);
}

package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.tokyo.supermix.data.entities.ProcessSample;

public interface ProcessSampleRepository extends JpaRepository<ProcessSample, String>,
    QuerydslPredicateExecutor<ProcessSample>, PagingAndSortingRepository<ProcessSample, String> {
  ProcessSample findProcessSampleByCode(String code);

  List<ProcessSample> findByIncomingSamplePlantCode(String plantCode);

  Page<ProcessSample> findByIncomingSamplePlantCodeIn(List<String> plantCodes, Pageable pageable);

  List<ProcessSample> findByCodeContaining(String code);

  Page<ProcessSample> findAll(Pageable pageable);

  Page<ProcessSample> findAllByIncomingSamplePlantCode(String plantCode, Pageable pageable);

  Long countByIncomingSamplePlantCode(String plantCode);
}

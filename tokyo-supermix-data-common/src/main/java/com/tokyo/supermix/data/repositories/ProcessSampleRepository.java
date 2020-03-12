package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.ProcessSample;

public interface ProcessSampleRepository extends JpaRepository<ProcessSample, String> {
  ProcessSample findProcessSampleByCode(String code);
}

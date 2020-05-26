package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.ProcessSampleLoad;

public interface ProcessSampleLoadRepository extends JpaRepository<ProcessSampleLoad, Long> {
  List<ProcessSampleLoad> findByProcessSampleIncomingSamplePlantCode(String plantCode);
}

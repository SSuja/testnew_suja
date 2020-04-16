package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.StrengthCubeTestFinding;

public interface StrengthCubeTestFindingRepository
    extends JpaRepository<StrengthCubeTestFinding, Long> {
  List<StrengthCubeTestFinding> findByFinishProductSampleId(Long finishProductSampleId);

  boolean existsByFinishProductSampleId(Long finishProductSampleId);
}

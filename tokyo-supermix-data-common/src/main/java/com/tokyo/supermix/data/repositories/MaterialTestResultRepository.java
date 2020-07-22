package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.MaterialTestResult;

public interface MaterialTestResultRepository extends JpaRepository<MaterialTestResult, Long> {
  public List<MaterialTestResult> findByMaterialTestCode(String materialTestCode);
}

package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.MaterialTestResult;
import com.tokyo.supermix.data.entities.TestEquation;
import com.tokyo.supermix.data.entities.TestParameter;

public interface MaterialTestResultRepository extends JpaRepository<MaterialTestResult, Long> {
  MaterialTestResult findByTestEquationAndMaterialTestCode(TestEquation testEquation,
      String materialTestCode);

  public List<MaterialTestResult> findByMaterialTestCode(String materialTestCode);

  public MaterialTestResult findByTestParameterAndMaterialTestCode(
      TestParameter testParameter, String materialTestCode);
}

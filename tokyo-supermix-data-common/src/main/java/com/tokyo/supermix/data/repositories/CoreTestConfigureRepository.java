package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.CoreTestConfigure;

public interface CoreTestConfigureRepository extends JpaRepository<CoreTestConfigure, Long> {
  List<CoreTestConfigure> findByrawMaterialId(Long rawMaterialId);

  List<CoreTestConfigure> findByrawMaterialIdAndCoreTestTrue(Long rawMaterialId);

  List<CoreTestConfigure> findBytestConfigureId(Long testConfigureId);

  boolean existsBytestConfigureIdAndRawMaterialIdAndCoreTestTrue(Long testConfigureId,
      Long rawMaterialId);

  List<CoreTestConfigure> findByTestConfigureIdAndMaterialCategoryId(Long testConfigureId,
      Long materialCategoryId);

  List<CoreTestConfigure> findByTestConfigureIdAndMaterialSubCategoryId(Long testConfigureId,
      Long materialSubCategoryId);

  boolean existsBytestConfigureIdAndMaterialCategoryIdAndCoreTestTrue(Long testConfigureId,
      Long mainCategoryId);

  boolean existsBytestConfigureIdAndMaterialSubCategoryIdAndCoreTestTrue(Long testConfigureId,
      Long materialSubCategoryId);

  List<CoreTestConfigure> findByTestConfigureIdAndRawMaterialId(Long testConfigureId,
      Long rawMaterialId);
}

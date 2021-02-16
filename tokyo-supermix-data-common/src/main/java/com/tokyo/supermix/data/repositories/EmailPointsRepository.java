package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tokyo.supermix.data.entities.EmailPoints;

@Repository
public interface EmailPointsRepository extends JpaRepository<EmailPoints, Long> {

  boolean existsByMaterialSubCategoryIdAndTestId(Long materialSubCategoryId, Long testId);

  List<EmailPoints> findByActive(boolean status);

  EmailPoints findByMaterialSubCategoryIdAndTestId(Long materialSubCategoryId, Long testId);

  List<EmailPoints> findByActiveAndAdminLevelEmailConfiguration(boolean status,
      boolean adminStatus);

  void deleteByTestIdAndMaterialSubCategoryId(Long testId, Long materialSubCategoryId);

  void deleteByTestIdAndMaterialCategoryId(Long testId, Long materialCategoryId);

  EmailPoints findByMaterialCategoryIdAndTestId(Long materialCategoryId, Long testId);

  EmailPoints findByMaterialCategoryIdAndTestIdAndSchedule(Long materialCategoryId, Long testId,
      boolean status);

  EmailPoints findByTestIdAndMaterialCategoryId(Long testId, Long materialCategoryId);

  EmailPoints findByTestIdAndMaterialSubCategoryId(Long testId, Long materialSubCategoryId);

  EmailPoints findByTestConfigureIdAndSchedule(Long testConfigureId, boolean status);

  List<EmailPoints> findByTestConfigureId(Long testConfigureId);

  void deleteByTestConfigureId(Long testConfigureId);

  boolean existsByTestConfigureId(Long testConfigId);
}

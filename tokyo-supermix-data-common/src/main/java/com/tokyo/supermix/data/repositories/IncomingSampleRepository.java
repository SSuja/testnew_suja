package com.tokyo.supermix.data.repositories;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.tokyo.supermix.data.entities.IncomingSample;
import com.tokyo.supermix.data.enums.Status;

public interface IncomingSampleRepository
    extends JpaRepository<IncomingSample, String>, QuerydslPredicateExecutor<IncomingSample> {
  boolean existsByCode(String code);

  IncomingSample findIncomingSampleByCode(String code);

  List<IncomingSample> findIncomingSampleByStatus(Status status);

  boolean existsByStatus(Status status);

  List<IncomingSample> findByStatusAndRawMaterialIdAndDateAndPlantCodeIn(Status status, Long RawMaterialId,
      Date date, List<String> permissionPlantCodeByCurrentUser);

  List<IncomingSample> findByRawMaterialIdAndDate(Long RawMaterialId, Date date);

  List<IncomingSample> findByStatus(Status status);

  List<IncomingSample> findByRawMaterialMaterialSubCategoryIdAndDate(Long materialSubCategoryId,
      Date date);

  List<IncomingSample> findByRawMaterialMaterialSubCategoryIdAndDateAndStatus(
      Long materialSubCategoryId, Date date, Status status);

  List<IncomingSample> findByPlantCode(String plantCode);

  List<IncomingSample> findByCodeContaining(String code);

  List<IncomingSample> findByRawMaterialIdAndDateAndPlantCodeIn(Long id, Date date,
      List<String> permissionPlantCodeByCurrentUser);

  List<IncomingSample> findByStatusAndRawMaterialIdAndDate(Status new1, Long id,
      Date sqlDate);

  List<IncomingSample> findByPlantCodeIn(List<String> permissionPlantCodeByCurrentUser);

  List<IncomingSample> findByRawMaterialMaterialSubCategoryIdAndDateAndStatusAndPlantCodeIn(
      Long materialSubCategoryId, Date sqlDate, Status new1, List<String> plantCodes);

  List<IncomingSample> findByRawMaterialMaterialSubCategoryIdAndDateAndPlantCodeIn(
      Long materialSubCategoryId, Date sqlDate, List<String> plantCodes);
}

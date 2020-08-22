package com.tokyo.supermix.data.repositories;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.tokyo.supermix.data.dto.IncomingSampleResponseDto;
import com.tokyo.supermix.data.entities.IncomingSample;
import com.tokyo.supermix.data.enums.Status;

public interface IncomingSampleRepository
    extends JpaRepository<IncomingSample, String>, QuerydslPredicateExecutor<IncomingSample> {
  boolean existsByCode(String code);

  IncomingSample findIncomingSampleByCode(String code);

  List<IncomingSample> findIncomingSampleByStatus(Status status);

  boolean existsByStatus(Status status);

  List<IncomingSample> findByStatusAndRawMaterialIdAndDateAndPlantCode(Status status,
      Long RawMaterialId, Date date, String plantCode);

  List<IncomingSample> findByRawMaterialIdAndDate(Long RawMaterialId, Date date);

  List<IncomingSample> findByStatus(Status status);

  List<IncomingSample> findByRawMaterialMaterialSubCategoryIdAndDate(Long materialSubCategoryId,
      Date date);

  List<IncomingSample> findByRawMaterialMaterialSubCategoryIdAndDateAndStatus(
      Long materialSubCategoryId, Date date, Status status);

  List<IncomingSample> findByPlantCodeOrderByUpdatedAtDesc(String plantCode);

  List<IncomingSample> findByCodeContaining(String code);

  List<IncomingSample> findByRawMaterialIdAndDateAndPlantCode(Long RawMaterialId, Date date,
      String plantCode);

  List<IncomingSample> findByStatusAndRawMaterialIdAndDate(Status status, Long id, Date sqlDate);

  List<IncomingSample> findByPlantCodeInOrderByUpdatedAtDesc(List<String> permissionPlantCodeByCurrentUser);

  List<IncomingSample> findByRawMaterialMaterialSubCategoryIdAndDateAndStatusAndPlantCode(
      Long materialSubCategoryId, Date sqlDate, Status status, String plantCode);

  List<IncomingSample> findByRawMaterialMaterialSubCategoryIdAndDateAndPlantCode(
      Long materialSubCategoryId, Date sqlDate, String plantCode);

  List<IncomingSample> findByRawMaterialMaterialSubCategoryId(Long materialSubCategoryId);

  List<IncomingSample> findByRawMaterialMaterialSubCategoryIdAndPlantCode(
      Long materialSubCategoryId, String plantCode);
  
  List<IncomingSampleResponseDto> findByRawMaterialId(Long RawMaterialId);
}

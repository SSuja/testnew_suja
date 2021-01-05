package com.tokyo.supermix.data.repositories;

import java.sql.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.tokyo.supermix.data.entities.IncomingSample;
import com.tokyo.supermix.data.enums.RawMaterialSampleType;
import com.tokyo.supermix.data.enums.Status;

public interface IncomingSampleRepository extends JpaRepository<IncomingSample, String>,
    QuerydslPredicateExecutor<IncomingSample>, PagingAndSortingRepository<IncomingSample, String> {
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

  // List<IncomingSample> findByPlantCodeOrderByUpdatedAtDesc(String plantCode);

  List<IncomingSample> findByCodeContaining(String code);

  List<IncomingSample> findByRawMaterialIdAndDateAndPlantCode(Long RawMaterialId, Date date,
      String plantCode);

  List<IncomingSample> findByStatusAndRawMaterialIdAndDate(Status status, Long id, Date sqlDate);

  // List<IncomingSample> findByPlantCodeInOrderByUpdatedAtDesc(List<String>
  // permissionPlantCodeByCurrentUser);

  List<IncomingSample> findByRawMaterialMaterialSubCategoryIdAndDateAndStatusAndPlantCode(
      Long materialSubCategoryId, Date sqlDate, Status status, String plantCode);

  List<IncomingSample> findByRawMaterialMaterialSubCategoryIdAndDateAndPlantCode(
      Long materialSubCategoryId, Date sqlDate, String plantCode);

  // List<IncomingSample> findByRawMaterialMaterialSubCategoryId(Long materialSubCategoryId);

  // List<IncomingSample> findByRawMaterialMaterialSubCategoryIdAndPlantCode(
  // Long materialSubCategoryId, String plantCode);

  List<IncomingSample> findAllByPlantCodeInOrderByUpdatedAtDesc(
      List<String> permissionPlantCodeByCurrentUser, Pageable pageable);

  List<IncomingSample> findByPlantCodeOrderByUpdatedAtDesc(String plantCode, Pageable pageable);

  Page<IncomingSample> findAll(Pageable pageable);

  Long countByPlantCode(String plantCode);

  List<IncomingSample> findByPlantCodeAndCodeStartsWith(String plantCode, String code);

  List<IncomingSample> findByCodeStartsWith(String code);

  List<IncomingSample> findByRawMaterialMaterialSubCategoryIdAndCodeStartsWith(
      Long materialSubCategoryId, String code);

  List<IncomingSample> findByRawMaterialMaterialSubCategoryIdAndPlantCodeAndCodeStartsWith(
      Long materialSubCategoryId, String plantCode, String code);

  List<IncomingSample> findByRawMaterialMaterialSubCategoryIdAndPlantCode(
      Long materialSubCategoryId, String plantCode);

  List<IncomingSample> findByRawMaterialMaterialSubCategoryId(Long materialSubCategoryId);

  List<IncomingSample> findBySupplierId(Long supplierId);

  boolean existsByRawMaterialSampleType(RawMaterialSampleType rawMaterialSampleType);

  // new repo

  List<IncomingSample> findAllByRawMaterialSampleType(RawMaterialSampleType rawMaterialSampleType,
      Pageable pageable);

  List<IncomingSample> findAllByRawMaterialSampleTypeAndPlantCode(
      RawMaterialSampleType rawMaterialSampleType, String plantCode, Pageable pageable);

  List<IncomingSample> findByPlantCodeInAndRawMaterialSampleTypeOrderByUpdatedAtDesc(
      List<String> permissionPlantCodeByCurrentUser, RawMaterialSampleType rawMaterialSampleType,
      Pageable pageable);

  Long countByRawMaterialSampleType(RawMaterialSampleType rawMaterialSampleType);

  Long countByRawMaterialSampleTypeAndPlantCode(RawMaterialSampleType rawMaterialSampleType,
      String plantCode);


  List<IncomingSample> findByRawMaterialMaterialSubCategoryMaterialCategoryId(
      Long materialCategoryId);

  List<IncomingSample> findByRawMaterialMaterialSubCategoryMaterialCategoryIdAndPlantCode(
      Long materialCategoryId, String plantCode);
}

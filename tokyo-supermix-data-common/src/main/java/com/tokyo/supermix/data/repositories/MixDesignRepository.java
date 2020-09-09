package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.tokyo.supermix.data.entities.MixDesign;
import com.tokyo.supermix.data.enums.Status;

public interface MixDesignRepository extends JpaRepository<MixDesign, String>,
    QuerydslPredicateExecutor<MixDesign>, PagingAndSortingRepository<MixDesign, String> {
  boolean existsByCode(String code);

  List<MixDesign> findByPlantCode(String plantCode);

  MixDesign findByCode(String code);

  List<MixDesign> findByCodeContaining(String code);

  List<MixDesign> findByStatus(Status status);

  boolean existsByStatus(Status status);

  List<MixDesign> findByPlantCodeIn(List<String> plantCodes);

  List<MixDesign> findByPlantCodeOrderByUpdatedAtDesc(String plantCode);

  List<MixDesign> findByRawMaterialId(Long rawMaterialId);

  boolean existsByRawMaterialId(Long rawMaterialId);

  Page<MixDesign> findAllByOrderByUpdatedAtDesc(Pageable pageable);

  Page<MixDesign> findAllByPlantCodeOrderByUpdatedAtDesc(String plantCode, Pageable pageable);

  Long countByPlantCode(String plantCode);

  List<MixDesign> findByPlantCodeAndCodeStartsWith(String plantCode, String code);

  List<MixDesign> findByCodeStartsWith(String code);

  List<MixDesign> findByRawMaterialIdAndStatusAndCodeStartsWith(Long rawMaterialId, Status status,
      String code);
}

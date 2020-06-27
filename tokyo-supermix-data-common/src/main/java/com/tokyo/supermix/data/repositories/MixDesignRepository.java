package com.tokyo.supermix.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.tokyo.supermix.data.entities.MixDesign;
import com.tokyo.supermix.data.enums.Status;

public interface MixDesignRepository
    extends JpaRepository<MixDesign, String>, QuerydslPredicateExecutor<MixDesign> {
  boolean existsByCode(String code);

  List<MixDesign> findByPlantCode(String plantCode);

  MixDesign findByCode(String code);

  List<MixDesign> findByCodeContaining(String code);

  List<MixDesign> findByStatus(Status status);

  boolean existsByStatus(Status status);

  List<MixDesign> findByPlantCodeIn(List<String>plantCodes);
}

package com.tokyo.supermix.data.repositories;

import java.sql.Date;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.tokyo.supermix.data.entities.IncomingSample;
import com.tokyo.supermix.data.enums.Status;

public interface IncomingSampleRepository extends JpaRepository<IncomingSample, String> {
  boolean existsByCode(String code);

  IncomingSample findIncomingSampleByCode(String code);

  List<IncomingSample> findIncomingSampleByStatus(Status status);

  boolean existsByStatus(Status status);

  List<IncomingSample> findByStatusAndRawMaterialIdAndDate(Status status, Long RawMaterialId,
      Date date);

  List<IncomingSample> findByRawMaterialIdAndDate(Long RawMaterialId, Date date);

  List<IncomingSample> findByStatus(Status status);
  
  @Query(value = "SELECT COUNT(code)\r\n" + "FROM incoming_sample\r\n"
      + "inner join  raw_material on incoming_sample.raw_material_id = raw_material.id\r\n"
      + "inner join material_sub_category on raw_material.material_sub_category_id = material_sub_category.id\r\n"
      + "inner join material_category on material_sub_category.material_category_id = material_category.id\r\n"
      + "where incoming_sample.date=current_date() and\r\n" + "material_category.name=?1",
      nativeQuery = true)
  Long countByTotalMaterialCategoryIncomingSample(String materialCategoryName);
  @Query(value = "SELECT COUNT(code) FROM incoming_sample inner join raw_material on incoming_sample.raw_material_id = raw_material.id inner join material_sub_category on raw_material.material_sub_category_id = material_sub_category.id inner join material_category on material_sub_category.material_category_id = material_category.id where incoming_sample.date = current_date() and material_sub_category.name=?1", nativeQuery = true)
  Long calculateMaterialSubCategoryCount(String materialSubCategoryName);
}

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

	List<IncomingSample> findByStatusAndRawMaterialIdAndDate(Status status, Long RawMaterialId, Date date);

	List<IncomingSample> findByRawMaterialIdAndDate(Long RawMaterialId, Date date);

	List<IncomingSample> findByStatus(Status status);

	List<IncomingSample> findByRawMaterialMaterialSubCategoryIdAndDate(Long materialSubCategoryId, Date date);

	List<IncomingSample> findByRawMaterialMaterialSubCategoryIdAndDateAndStatus(Long materialSubCategoryId, Date date,
			Status status);

	List<IncomingSample> findByPlantCode(String plantCode);

	List<IncomingSample> findByCodeContaining(String code);
}

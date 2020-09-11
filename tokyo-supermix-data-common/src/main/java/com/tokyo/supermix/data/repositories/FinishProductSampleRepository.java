package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.tokyo.supermix.data.entities.FinishProductSample;
import com.tokyo.supermix.data.enums.Status;

@Repository
public interface FinishProductSampleRepository extends JpaRepository<FinishProductSample, String>,
		QuerydslPredicateExecutor<FinishProductSample>, PagingAndSortingRepository<FinishProductSample, String> {
	boolean existsByFinishProductCode(String code);

	boolean existsByMixDesignCode(String code);

	List<FinishProductSample> findByMixDesignCode(String mixDesignCode);

	boolean existsByEquipmentId(Long id);

	List<FinishProductSample> findByEquipmentId(Long id);

	Page<FinishProductSample> findByMixDesignPlantCodeOrderByUpdatedAtDesc(String plantCode, Pageable pageable);

	List<FinishProductSample> findByStatus(Status status);

	boolean existsByStatus(Status status);

	Page<FinishProductSample> findByMixDesignPlantCodeInOrderByUpdatedAtDesc(List<String> plantCodes,
			Pageable pageable);

	List<FinishProductSample> findByCodeContaining(String code);

	List<FinishProductSample> findByMixDesignPlantCode(String plantCode);

	List<FinishProductSample> findByMixDesignRawMaterialMaterialSubCategoryId(Long materialSubCategoryId,
			Pageable pageable);

	List<FinishProductSample> findByMixDesignRawMaterialMaterialSubCategoryIdAndMixDesignPlantCode(
			Long materialSubCategoryId, String plantCode, Pageable pageable);

	Long countByMixDesignPlantCode(String plantCode);

	Long countByMixDesignPlantCodeAndMixDesignRawMaterialMaterialSubCategoryId(String plantCode,
			Long materialSubCategoryId);

	Long countByMixDesignRawMaterialMaterialSubCategoryId(Long materialSubCategoryId);

	Long countByMixDesignRawMaterialMaterialSubCategoryMaterialCategoryId(Long materialCategoryId);

	List<FinishProductSample> findByMixDesignRawMaterialMaterialSubCategoryMaterialCategoryId(Long materialCategoryId,
			Pageable pageable);

	Long countByMixDesignPlantCodeAndMixDesignRawMaterialMaterialSubCategoryMaterialCategoryId(String plantCode,
			Long materialCategoryId);

	List<FinishProductSample> findByMixDesignRawMaterialMaterialSubCategoryMaterialCategoryIdAndMixDesignPlantCode(
			Long materialCategoryId, String plantCode, Pageable pageable);

	Long countByMixDesignRawMaterialId(Long rawMaterialId);

	List<FinishProductSample> findByMixDesignRawMaterialId(Long rawMaterialId, Pageable pageable);

	Long countByMixDesignPlantCodeAndMixDesignRawMaterialId(String plantCode, Long rawMaterialId);

	List<FinishProductSample> findByMixDesignRawMaterialIdAndMixDesignPlantCode(Long rawMaterialId, String plantCode,
			Pageable pageable);
}

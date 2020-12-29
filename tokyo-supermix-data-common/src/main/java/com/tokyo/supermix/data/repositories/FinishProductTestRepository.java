package com.tokyo.supermix.data.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.tokyo.supermix.data.entities.FinishProductTest;
import com.tokyo.supermix.data.enums.Status;

public interface FinishProductTestRepository extends JpaRepository<FinishProductTest, String>,
		PagingAndSortingRepository<FinishProductTest, String>, QuerydslPredicateExecutor<FinishProductTest> {

	List<FinishProductTest> findByTestConfigureId(Long testConfigureId);

	boolean existsByTestConfigureId(Long testConfigureId);

	boolean existsByCode(String code);

	FinishProductTest findFinishProductTestByCode(String code);

	List<FinishProductTest> findByCodeContaining(String code);

	List<FinishProductTest> findByFinishProductSampleCode(String finishProductSampleCode);

	List<FinishProductTest> findByFinishProductSampleCodeAndTestConfigureId(String finishProductSampleCode,
			Long testConfigureId);

	List<FinishProductTest> findByTestConfigureTestName(String testName);

	boolean existsByFinishProductSampleCodeAndTestConfigureId(String finishProductSampleCode, Long testConfigureId);

	Page<FinishProductTest> findByFinishProductSampleMixDesignPlantCodeInOrderByUpdatedAtDesc(List<String> plantCodes,
			Pageable pageable);

	boolean existsByFinishProductSampleCode(String finishProductSampleCode);

	Page<FinishProductTest> findByFinishProductSampleMixDesignPlantCodeOrderByUpdatedAtDesc(String plantCode,
			Pageable pageable);

	FinishProductTest findByCodeAndFinishProductSampleMixDesignPlantCode(String finishProductTestCode,
			String plantCode);

	boolean existsByFinishProductSampleMixDesignPlantCode(String plantCode);

	List<FinishProductTest> findByTestConfigureMaterialSubCategoryId(Long materialSubCategoryId);

	List<FinishProductTest> findByTestConfigureMaterialSubCategoryIdAndStatus(Long materialSubCategoryId,
			Status status);

	Long countByFinishProductSampleMixDesignPlantCode(String plantCode);

	List<FinishProductTest> findByFinishProductSampleMixDesignCodeAndTestConfigureCoreTestTrueAndTestConfigureMaterialSubCategoryIdAndStatus(
			String mixDesignCode, Long materialSubCategoryId, Status status);

	List<FinishProductTest> findByFinishProductSampleMixDesignCodeAndTestConfigureCoreTestTrueAndTestConfigureMaterialCategoryIdAndStatus(
			String mixDesignCode, Long materialCategoryId, Status status);

	List<FinishProductTest> findByFinishProductSampleMixDesignCodeAndTestConfigureCoreTestTrueAndTestConfigureRawMaterialIdAndStatus(
			String mixDesignCode, Long rawMaterialId, Status status);

	List<FinishProductTest> findByFinishProductSampleMixDesignCodeAndTestConfigureCoreTestTrueAndFinishProductSampleCodeAndStatus(
			String mixDesignCode, String finishProductSampleCode, Status status);

	Set<FinishProductTest> findByTestConfigureDueDayNotNull();
}

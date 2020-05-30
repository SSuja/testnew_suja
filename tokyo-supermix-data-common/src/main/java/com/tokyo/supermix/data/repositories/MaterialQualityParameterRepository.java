package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tokyo.supermix.data.entities.MaterialQualityParameter;

@Repository
public interface MaterialQualityParameterRepository extends JpaRepository<MaterialQualityParameter, Long> {
	boolean existsByQualityParameterIdAndRawMaterialIdAndValueAndUnitId(Long qualityParameterId, Long rawMaterialId,
			Double value, Long unitId);

	public MaterialQualityParameter findByQualityParameterIdAndRawMaterialId(Long qualityParameterId,
			Long rawMaterialId);

	public MaterialQualityParameter findByQualityParameterId(Long qualityParameterId);

}

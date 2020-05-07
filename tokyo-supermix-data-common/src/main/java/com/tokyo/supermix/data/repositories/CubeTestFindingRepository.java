package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.CubeTestFinding;

public interface CubeTestFindingRepository extends JpaRepository<CubeTestFinding, Long> {
	List<CubeTestFinding> findByFinishProductSampleId(Long finishProductSampleId);

	boolean existsByFinishProductSampleId(Long finishProductSampleId);
}

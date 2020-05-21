package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tokyo.supermix.data.entities.ConcreteTestStatus;

public interface ConcreteTestStatusRepository extends JpaRepository<ConcreteTestStatus, Long> {
	ConcreteTestStatus findByFinishProductSampleId(Long finishProductSampleId);

	ConcreteTestStatus findByConcreteTestTypeId(Long concreteTestTypeId);

	boolean existsByConcreteTestTypeIdAndFinishProductSampleId(Long concreteTestTypeId, Long finishProductSampleId);
}

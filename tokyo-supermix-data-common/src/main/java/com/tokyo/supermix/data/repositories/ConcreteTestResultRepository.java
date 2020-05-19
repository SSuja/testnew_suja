package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tokyo.supermix.data.entities.ConcreteTestResult;

public interface ConcreteTestResultRepository extends JpaRepository<ConcreteTestResult, Long> {
	ConcreteTestResult findByFinishProductSampleId(Long finishProductSampleId);
}

package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.tokyo.supermix.data.entities.ConcreteTestResult;

public interface ConcreteTestResultRepository
		extends JpaRepository<ConcreteTestResult, Long>, QuerydslPredicateExecutor<ConcreteTestResult> {
	ConcreteTestResult findByFinishProductSampleId(Long finishProductSampleId);
}

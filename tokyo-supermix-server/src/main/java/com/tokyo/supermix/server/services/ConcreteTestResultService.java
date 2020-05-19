package com.tokyo.supermix.server.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.entities.ConcreteTestResult;
import com.tokyo.supermix.data.enums.Status;

public interface ConcreteTestResultService {
	public ConcreteTestResult saveConcreteTestResult(ConcreteTestResult concreteTestResult);

	public List<ConcreteTestResult> getAllConcreteTestResults();

	public ConcreteTestResult getConcreteTestResultById(Long id);

	public void deleteConcreteTestResult(Long id);

	public boolean isConcreteTestResultExists(Long id);

	public Page<ConcreteTestResult> searchConcreteTestResult(Long finishProductSampleId, Long ConcreteTestId,
			Status status, Double result, Double resultMin, Double resultMax, Double strenghGradeRatio,
			Double strenghGradeRatioMin, Double strenghGradeRatioMax, Double slump, Double slumpMin, Double slumpMax,
			Double slumpGradeRatio, Double slumpGradeRatioMin, Double slumpGradeRatioMax, BooleanBuilder booleanBuilder,
			int page, int size);
}

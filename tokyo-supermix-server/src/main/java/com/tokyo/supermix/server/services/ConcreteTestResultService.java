package com.tokyo.supermix.server.services;

import java.util.List;

import com.tokyo.supermix.data.entities.ConcreteTestResult;

public interface ConcreteTestResultService {
	public ConcreteTestResult saveConcreteTestResult(ConcreteTestResult concreteTestResult);

	public List<ConcreteTestResult> getAllConcreteTestResults();

	public ConcreteTestResult getConcreteTestResultById(Long id);

	public void deleteConcreteTestResult(Long id);

	public boolean isConcreteTestResultExists(Long id);
}

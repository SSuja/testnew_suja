package com.tokyo.supermix.server.services;

import java.util.List;

import com.tokyo.supermix.data.entities.ConcreteTest;

public interface ConcreteTestService {
	public ConcreteTest saveConcreteTest(ConcreteTest concreteTest);

	public List<ConcreteTest> getAllConcreteTests();

	public ConcreteTest getConcreteTestById(Long id);

	public void deleteConcreteTest(Long id);

	public boolean isConcreteTestExists(Long id);

	public boolean isNameExists(String name);

}

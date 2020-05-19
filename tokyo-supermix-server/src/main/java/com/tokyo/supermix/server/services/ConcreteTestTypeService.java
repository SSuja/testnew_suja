package com.tokyo.supermix.server.services;

import java.util.List;

import com.tokyo.supermix.data.entities.ConcreteTestType;

public interface ConcreteTestTypeService {
	public ConcreteTestType saveConcreteTestType(ConcreteTestType concreteTestType);

	public List<ConcreteTestType> getAllConcreteTestTypes();

	public ConcreteTestType getConcreteTestTypeById(Long id);

	public void deleteConcreteTestType(Long id);

	public boolean isConcreteTestTypeExists(Long id);

	public boolean isTypeExists(String type);
}

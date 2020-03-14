package com.tokyo.supermix.server.services;

import java.util.List;

import com.tokyo.supermix.data.entities.MaterialTest;

public interface MaterialTestService {

	public void saveMaterialTest(MaterialTest materialTest);

	public boolean isMaterialTestExists(Long id);

	public MaterialTest getMaterialTestById(Long id);

	public List<MaterialTest> getAllMaterialTests();

	public void deleteMaterialTest(Long id);

	public List <MaterialTest> getMaterialTestByStatus(String status);
	
	public List <MaterialTest> getMaterialTestByTest(Long testId);

}

package com.tokyo.supermix.server.services;

import java.util.List;

import com.tokyo.supermix.data.entities.MaterialTest;

public interface MaterialTestService {
	
	public void saveMaterialTest (MaterialTest materialTest);
	
	public MaterialTest getMaterialTestById (Long id);
	
	public List<MaterialTest> getAllMaterialTests();
	
	public void deleteMaterialTest(Long id);

}

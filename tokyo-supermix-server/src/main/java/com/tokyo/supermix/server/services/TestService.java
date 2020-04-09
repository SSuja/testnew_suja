package com.tokyo.supermix.server.services;

import java.util.List;

import com.tokyo.supermix.data.entities.Test;

public interface TestService {
	
	public void saveTest(Test test);

	public boolean isTestExist(Long id);

	public boolean isTestExist(String name);

	public List<Test> getAllTests();

	public void deleteTest(Long id);

	public Test getTestById(Long id);

	public boolean isUpdatedTestExist(Long id, String name);

}

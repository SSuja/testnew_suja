package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.TestConfigure;
import com.tokyo.supermix.data.entities.TestType;

public interface TestConfigureService {
  public TestConfigure saveTest(TestConfigure test);

  public boolean isTestNameExist(String name);

  public boolean isTestExist(Long id);

  public List<TestConfigure> getAllTests();

  public TestConfigure getTestById(Long id);

  boolean existsByNameAndTestTypeId(String name, Long testTypeId);

  public boolean isDuplicateEntryExist(String name, Long testTypeId);

  public boolean isUpdatedTestExist(Long testTypeId, String name);

  public void deleteTest(Long id);

  public List<TestConfigure> getTestByTestType(TestType testType);

  public List<TestConfigure> findByTestTypeId(Long testTypeId);
}

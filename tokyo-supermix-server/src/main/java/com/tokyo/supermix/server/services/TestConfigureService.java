package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.TestConfigure;
import com.tokyo.supermix.data.entities.TestType;

public interface TestConfigureService {
  public TestConfigure saveTestConfigure(TestConfigure test);

  public boolean isTestConfigureExist(Long id);

  public List<TestConfigure> getAllTestConfigures();

  public TestConfigure getTestConfigureById(Long id);

  boolean existsByTestIdAndTestTypeId(Long testId, Long testTypeId);

  public boolean isDuplicateEntryExist(Long testId, Long testTypeId);

  public void deleteTestConfigure(Long id);

  public List<TestConfigure> getTestConfigureByTestType(TestType testType);

  public List<TestConfigure> findByTestTypeId(Long testTypeId);
}
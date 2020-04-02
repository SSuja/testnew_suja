package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.Test;
import com.tokyo.supermix.data.entities.TestType;

public interface TestService {
  public Test saveTest(Test test);

  public boolean isTestNameExist(String name);

  public boolean isTestExist(Long id);

  public List<Test> getAllTests();

  public Test getTestById(Long id);

  boolean existsByNameAndTestTypeId(String name, Long testTypeId);

  public boolean isDuplicateEntryExist(String name, Long testTypeId);

  public boolean isUpdatedTestExist(Long testTypeId, String name);

  public void deleteTest(Long id);

  public List<Test> getTestByTestType(TestType testType);

  public List<Test> findByTestTypeId(Long testTypeId);
}

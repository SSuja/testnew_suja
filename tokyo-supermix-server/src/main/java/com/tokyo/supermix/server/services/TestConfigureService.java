package com.tokyo.supermix.server.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.dto.TestConfigureDto;
import com.tokyo.supermix.data.entities.TestConfigure;
import com.tokyo.supermix.data.entities.TestType;

public interface TestConfigureService {
  public Long saveTestConfigure(TestConfigure testConfigure);

  public boolean isTestConfigureExist(Long id);

  public List<TestConfigure> getAllTestConfigures();

  public TestConfigure getTestConfigureById(Long id);

  public boolean isDuplicateEntryExist(Long testId, Long testTypeId);
  
  public boolean isexistByTestTypeIdAndTestId( Long testTypeId, Long testId);

  public void deleteTestConfigure(Long id);

  public List<TestConfigure> getTestConfigureByTestType(TestType testType);

  public List<TestConfigure> findByTestTypeId(Long testTypeId);

  public void updateCoreTestForTestConfigure(Long id, boolean coreTest);

  public List<TestConfigure> findByCoreTest(boolean coreTest);

  public Page<TestConfigure> searchTestConfigure(Predicate predicate, int page, int size);
  
  public TestConfigureDto getTestDetailsByConfigureId(Long id);
}

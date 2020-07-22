package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Page;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.dto.TestConfigureDto;
import com.tokyo.supermix.data.dto.TestConfigureRequestDto;
import com.tokyo.supermix.data.entities.TestConfigure;
import com.tokyo.supermix.data.enums.TestType;

public interface TestConfigureService {
  public Long saveTestConfigure(TestConfigureRequestDto testConfigureRequestDto);

  public boolean isTestConfigureExist(Long id);

  public List<TestConfigure> getAllTestConfigures();

  public TestConfigure getTestConfigureById(Long id);

  public void deleteTestConfigure(Long id);

  public void updateCoreTestForTestConfigure(Long id, boolean coreTest);

  public List<TestConfigure> findByCoreTest(boolean coreTest);

  public Page<TestConfigure> searchTestConfigure(Predicate predicate, int page, int size);

  public TestConfigureDto getTestDetailsByConfigureId(Long id);

  public TestConfigureDto getTestConfigureDetailsByConfigureId(Long id);

  public boolean isexistByTestIdAndMaterialCategoryIdAndMaterialSubCategoryId(Long testId,
      Long materialCategoryId, Long materialSubCategoryId);

  public List<TestConfigure> findByTestType(TestType testType);

  public boolean isexistByTestType(TestType testType);

  public List<TestConfigure> findByMaterialSubCategory(Long materialSubCategoryId);

  public List<TestConfigure> getTestConfiguresByMaterialSubCategoryAndTestType(
      Long materialSubCategoryId, TestType testType);

  public Long updateTestConfigure(TestConfigure testConfigure);
}

package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.dto.TestConfigureDto;
import com.tokyo.supermix.data.dto.TestConfigureRequestDto;
import com.tokyo.supermix.data.dto.TestConfigureResDto;
import com.tokyo.supermix.data.entities.TestConfigure;
import com.tokyo.supermix.data.enums.MainType;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;

public interface TestConfigureService {
  public Long saveTestConfigure(TestConfigureRequestDto testConfigureRequestDto);

  public boolean isTestConfigureExist(Long id);

  public List<TestConfigure> getAllTestConfigures();

  public TestConfigure getTestConfigureById(Long id);

  public void deleteTestConfigure(Long id);

  public void updateCoreTestForTestConfigure(Long id, boolean coreTest);

  public List<TestConfigure> findByCoreTest(boolean coreTest);

  public TestConfigureDto getTestConfigureDetailsByConfigureId(Long id);

  public boolean isExistByTestIdAndMaterialSubCategoryId(Long testId, Long materialSubCategoryId,
      Long rawMaterialId);

  public List<TestConfigure> findByTestType(MainType testType);

  public boolean isexistByTestType(MainType testType);

  public List<TestConfigure> findByMaterialSubCategory(Long materialSubCategoryId);

  public List<TestConfigure> getTestConfiguresByMaterialSubCategoryAndTestType(
      Long materialSubCategoryId, MainType testType);

  public Long updateTestConfigure(TestConfigure testConfigure);

  public boolean isUpdatedMaterialSubCategoryAndTest(Long id, Long testId,
      Long materialSubCategoryId, Long rawMaterialId);

  public TestConfigureResDto getTestConfigureForAcceptedValue(Long testConfigureId);

  public boolean isPrefixAlreadyExists(String prefix);

  public boolean isPrefixAlreadyExistsUpdate(Long id, String prefix);

  public List<TestConfigure> findByMaterialCategory(Long materialCategoryId);

  public List<TestConfigure> searchTestConfigure(String testName, MainType mainType,
      String mainCategoryName, String subCategoryName, String materialName,
      BooleanBuilder booleanBuilder, int page, int size, Pageable pageable, Pagination pagination);

  public List<TestConfigure> getAllTestConfigureByDecending(Pageable pageable);

  public Long countTestConfigure();

  public boolean isTestConfigureByRawMaterialId(Long rawMaterialId);

  public boolean isDuplicateEntry(Long testId, Long materialCategoryId, Long materialSubCategoryId,
      Long rawMaterialId);
}

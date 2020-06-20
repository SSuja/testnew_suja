package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Page;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.TestType;

public interface TestTypeService {

  public List<TestType> getAllTestTypes();

  public boolean isTestTypeExist(String testType);

  public boolean isTestTypeIdExist(Long id);

  public TestType getTestTypeById(Long id);

  public void deleteTestType(Long id);

  public void saveTestType(TestType testType);

  public boolean isMaterialSubCategoryIdExist(Long materialSubCategoryId);

  public List<TestType> getTestTypesByMaterialSubCategoryId(Long materialSubCategoryId);

  public boolean isUpdatedTestTypeExist(Long id, String type);

  public Page<TestType> searchTestType(Predicate predicate, int size, int page);
}
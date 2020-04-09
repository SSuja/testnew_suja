package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.TestType;
import com.tokyo.supermix.data.repositories.TestTypeRepository;

@Service
public class TestTypeServiceImpl implements TestTypeService {

  @Autowired
  private TestTypeRepository testTypeRepository;

  @Transactional(readOnly = true)
  public List<TestType> getAllTestTypes() {
    return testTypeRepository.findAll();
  }

  @Transactional(readOnly = true)
  public TestType getTestTypeById(Long id) {
    return testTypeRepository.findById(id).get();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteTestType(Long id) {
    testTypeRepository.deleteById(id);
  }

  @Transactional
  public void saveTestType(TestType testType) {
    testTypeRepository.save(testType);
  }

  @Transactional(readOnly = true)
  public boolean isTestTypeExist(String testType) {
    return testTypeRepository.existsByType(testType);
  }

  @Transactional(readOnly = true)
  public boolean isTestTypeIdExist(Long id) {
    return testTypeRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public boolean isMaterialSubCategoryIdExist(Long materialSubCategoryId) {
    return testTypeRepository.existsByMaterialSubCategoryId(materialSubCategoryId);
  }

  @Transactional(readOnly = true)
  public List<TestType> getTestTypesByMaterialSubCategoryId(Long materialSubCategoryId) {
    return testTypeRepository.findByMaterialSubCategoryId(materialSubCategoryId);
  }

  public boolean isUpdatedTestTypeExist(Long id, String type) {
    if ((!getTestTypeById(id).getType().equalsIgnoreCase(type)) && (isTestTypeExist(type))) {
      return true;
    }
    return false;
  }
}

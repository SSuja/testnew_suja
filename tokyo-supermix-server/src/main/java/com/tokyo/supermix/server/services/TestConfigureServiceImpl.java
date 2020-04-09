package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.TestConfigure;
import com.tokyo.supermix.data.entities.TestType;
import com.tokyo.supermix.data.repositories.TestConfigureRepository;

@Service
public class TestConfigureServiceImpl implements TestConfigureService {
  @Autowired
  private TestConfigureRepository testConfigureRepository;

  @Transactional
  public TestConfigure saveTestConfigure(TestConfigure testConfigure) {
    return testConfigureRepository.save(testConfigure);
  }

  @Transactional(readOnly = true)
  public boolean isTestNameExist(String name) {
    return testConfigureRepository.existsByName(name);
  }

  @Transactional(readOnly = true)
  public boolean isTestConfigureExist(Long id) {
    return testConfigureRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public List<TestConfigure> getAllTestConfigures() {
    return testConfigureRepository.findAll();
  }

  @Transactional(readOnly = true)
  public TestConfigure getTestConfigureById(Long id) {
    return testConfigureRepository.findById(id).get();
  }

  public boolean isUpdatedTestConfigureExist(Long id, String name) {
    if ((!getTestConfigureById(id).getName().equalsIgnoreCase(name)) && (isTestNameExist(name))) {
      return true;
    }
    return false;
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteTestConfigure(Long id) {
    testConfigureRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public List<TestConfigure> getTestConfigureByTestType(TestType testType) {
    return testConfigureRepository.findByTestType(testType);
  }

  @Transactional(readOnly = true)
  public List<TestConfigure> findByTestTypeId(Long testTypeId) {
    return testConfigureRepository.findByTestTypeId(testTypeId);
  }

  @Transactional(readOnly = true)
  public boolean existsByNameAndTestTypeId(String name, Long testTypeId) {
    return testConfigureRepository.existsByNameAndTestTypeId(name, testTypeId);
  }

  public boolean isDuplicateEntryExist(String name, Long testTypeId) {
    if ((!findByTestTypeId(testTypeId).equals(name))
        && (existsByNameAndTestTypeId(name, testTypeId))) {
      return true;
    }
    return false;
  }

}

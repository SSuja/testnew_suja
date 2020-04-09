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
  private TestConfigureRepository testRepository;

  @Transactional
  public TestConfigure saveTest(TestConfigure test) {
    return testRepository.save(test);
  }

  @Transactional(readOnly = true)
  public boolean isTestNameExist(String name) {
    return testRepository.existsByName(name);
  }

  @Transactional(readOnly = true)
  public boolean isTestExist(Long id) {
    return testRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public List<TestConfigure> getAllTests() {
    return testRepository.findAll();
  }

  @Transactional(readOnly = true)
  public TestConfigure getTestById(Long id) {
    return testRepository.findById(id).get();
  }

  public boolean isUpdatedTestExist(Long id, String name) {
    if ((!getTestById(id).getName().equalsIgnoreCase(name)) && (isTestNameExist(name))) {
      return true;
    }
    return false;
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteTest(Long id) {
    testRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public List<TestConfigure> getTestByTestType(TestType testType) {
    return testRepository.findByTestType(testType);
  }

  @Transactional(readOnly = true)
  public List<TestConfigure> findByTestTypeId(Long testTypeId) {
    return testRepository.findByTestTypeId(testTypeId);
  }

  @Transactional(readOnly = true)
  public boolean existsByNameAndTestTypeId(String name, Long testTypeId) {
    return testRepository.existsByNameAndTestTypeId(name, testTypeId);
  }

  public boolean isDuplicateEntryExist(String name, Long testTypeId) {
    if ((!findByTestTypeId(testTypeId).equals(name))
        && (existsByNameAndTestTypeId(name, testTypeId))) {
      return true;
    }
    return false;
  }
}

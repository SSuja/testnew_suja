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

  public boolean isDuplicateEntryExist(Long testId, Long testTypeId) {
    if (testConfigureRepository.existsByTestIdAndTestTypeId(testId, testTypeId)) {
      return true;
    }
    return false;
  }

  @Transactional
  public void updateCoreTestForTestConfigure(Long id, boolean coreTest) {
    TestConfigure testConfigure = testConfigureRepository.findById(id).get();
    testConfigure.setCoreTest(coreTest);
    testConfigureRepository.save(testConfigure);
  }

  @Transactional(readOnly = true)
  public List<TestConfigure> findByCoreTest(boolean coreTest) {
    return testConfigureRepository.findByCoreTest(coreTest);
  }
}

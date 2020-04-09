package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.TestParameter;
import com.tokyo.supermix.data.repositories.TestParameterRepository;

@Service
public class TestParameterServiceImpl implements TestParameterService {
  @Autowired
  private TestParameterRepository testParameterRepository;

  @Transactional
  public List<TestParameter> saveTestParameter(List<TestParameter> testParameter) {
    return testParameterRepository.saveAll(testParameter);
  }

  @Transactional(readOnly = true)
  public List<TestParameter> getAllTestParameters() {
    return testParameterRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isTestParameterExist(Long id) {
    return testParameterRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public TestParameter getTestParameterById(Long id) {
    return testParameterRepository.findById(id).get();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteTestParameter(Long id) {
    testParameterRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public List<TestParameter> getTestParameterByTestConfigureId(Long testConfigureId) {
    return testParameterRepository.findByTestConfigureId(testConfigureId);
  }

  @Transactional(readOnly = true)
  public boolean isTestConfigureIdExist(Long id) {
    return testParameterRepository.existsByTestConfigureId(id);
  }

  public boolean isDuplicateRowExists(Long parameterId, Long testConfigureId, Long unitId) {
    if (testParameterRepository.isDuplicateRow(parameterId, testConfigureId, unitId) != null) {
      return true;
    }
    return false;
  }

  @Transactional
  public TestParameter updateTestParameter(TestParameter testParameter) {
    return testParameterRepository.save(testParameter);
  }
}

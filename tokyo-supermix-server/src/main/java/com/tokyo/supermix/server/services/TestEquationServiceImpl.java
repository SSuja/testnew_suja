package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.TestEquation;
import com.tokyo.supermix.data.repositories.TestEquationRepository;

@Service
public class TestEquationServiceImpl implements TestEquationService {
  @Autowired
  private TestEquationRepository testEquationRepository;

  @Transactional
  public TestEquation saveTestEquation(TestEquation testEquation) {
    return testEquationRepository.save(testEquation);
  }

  @Transactional(readOnly = true)
  public List<TestEquation> getAllTestEquations() {
    return testEquationRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isDuplicateEntry(Long testConfigureId, Long equationId) {
    return testEquationRepository.existsByTestConfigureIdAndEquationId(testConfigureId, equationId);
  }

  @Transactional(readOnly = true)
  public TestEquation getByTestEquationId(Long id) {
    return testEquationRepository.findById(id).get();
  }

  @Transactional(readOnly = true)
  public boolean isTestEquationExists(Long id) {
    return testEquationRepository.existsById(id);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteTestEquation(Long id) {
    testEquationRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public List<TestEquation> getByTestConfigure(Long testConfigureId) {
    return testEquationRepository.findByTestConfigureId(testConfigureId);
  }

  @Transactional(readOnly = true)
  public List<TestEquation> getByEquation(Long equationId) {
    return testEquationRepository.findByEquationId(equationId);
  }

}

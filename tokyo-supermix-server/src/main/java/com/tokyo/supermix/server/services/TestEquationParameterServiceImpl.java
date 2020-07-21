package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.TestEquationParameter;
import com.tokyo.supermix.data.repositories.TestEquationParameterRepository;

@Service
public class TestEquationParameterServiceImpl implements TestEquationParameterService {
  @Autowired
  private TestEquationParameterRepository testEquationParameterRepository;

  @Transactional(readOnly = true)
  public List<TestEquationParameter> getAllTestEquationParameters() {
    return testEquationParameterRepository.findAll();
  }

  @Transactional(readOnly = true)
  public List<TestEquationParameter> getByTestParameter(Long testParameterId) {
    return testEquationParameterRepository.findByTestParameterId(testParameterId);
  }

  @Transactional(readOnly = true)
  public List<TestEquationParameter> getByTestEquation(Long testEquationId) {
    return testEquationParameterRepository.findByTestEquationId(testEquationId);
  }
}

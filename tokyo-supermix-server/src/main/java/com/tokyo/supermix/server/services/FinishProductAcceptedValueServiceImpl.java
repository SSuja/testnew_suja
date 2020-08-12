package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.FinishProductAcceptedValue;
import com.tokyo.supermix.data.repositories.FinishProductAcceptedValueRepository;

@Service
public class FinishProductAcceptedValueServiceImpl implements FinishProductAcceptedValueService {
  @Autowired
  private FinishProductAcceptedValueRepository finishProductAcceptedValueRepository;

  @Transactional
  public FinishProductAcceptedValue saveFinishProductAcceptedValue(
      FinishProductAcceptedValue finishProductAcceptedValue) {
    return finishProductAcceptedValueRepository.save(finishProductAcceptedValue);
  }

  @Transactional(readOnly = true)
  public List<FinishProductAcceptedValue> getAllFinishProductAcceptedValues() {
    return finishProductAcceptedValueRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isFinishProductAcceptedValueExist(Long id) {
    return finishProductAcceptedValueRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public FinishProductAcceptedValue getFinishProductAcceptedValueById(Long id) {
    return finishProductAcceptedValueRepository.findById(id).get();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteFinishProductAcceptedValue(Long id) {
    finishProductAcceptedValueRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public List<FinishProductAcceptedValue> getFinishProductAcceptedValueByTestParameter(
      Long testParameterId) {
    return finishProductAcceptedValueRepository.findByTestParameterId(testParameterId);
  }

  @Transactional(readOnly = true)
  public FinishProductAcceptedValue getByAcceptedValueByTestConfigure(Long testConfigureId) {
    return finishProductAcceptedValueRepository.findByTestParameterTestConfigureId(testConfigureId);
  }

  @Transactional(readOnly = true)
  public boolean isTestParameterAndTestconfigure(Long testConfigureId, Long testParameterId) {
    return finishProductAcceptedValueRepository
        .existsByTestParameterTestConfigureIdAndTestParameterId(testConfigureId, testParameterId);
  }
}

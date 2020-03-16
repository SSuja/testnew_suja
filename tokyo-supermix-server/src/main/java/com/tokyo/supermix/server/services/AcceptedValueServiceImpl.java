package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.AcceptedValue;
import com.tokyo.supermix.data.entities.Test;
import com.tokyo.supermix.data.repositories.AcceptedValueRepository;

@Service
public class AcceptedValueServiceImpl implements AcceptedValueService {

  @Autowired
  private AcceptedValueRepository acceptedValueRepository;

  @Transactional
  public void saveAcceptedValue(AcceptedValue acceptedValue) {
    acceptedValueRepository.save(acceptedValue);
  }

  @Transactional(readOnly = true)
  public List<AcceptedValue> getAllAcceptedValues() {
    return acceptedValueRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isAcceptedValueExist(Long id) {
    return acceptedValueRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public AcceptedValue getAcceptedValueById(Long id) {
    return acceptedValueRepository.findById(id).get();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteAcceptedValue(Long id) {
    acceptedValueRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public List<AcceptedValue> getAcceptedValueByTest(Test test) {
    return acceptedValueRepository.findByTest(test);
  }

  @Transactional(readOnly = true)
  public boolean isAcceptedValueByTestId(Long testId) {
    return acceptedValueRepository.existsAcceptedValueByTestId(testId);
  }

  public boolean isUpdatedAcceptedValueTestIdExist(Long id, Long testId) {
    if ((!getAcceptedValueById(id).getTest().getId().equals(testId))
        && (isAcceptedValueByTestId(testId))) {
      return true;
    }
    return false;
  }

}

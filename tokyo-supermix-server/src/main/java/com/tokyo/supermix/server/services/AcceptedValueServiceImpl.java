package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.AcceptedValue;
import com.tokyo.supermix.data.entities.TestConfigure;
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
  public List<AcceptedValue> getAcceptedValueByTestConfigure(TestConfigure testConfigure) {
    return acceptedValueRepository.findByTestConfigure(testConfigure);
  }

  @Transactional(readOnly = true)
  public boolean isAcceptedValueByTestConfigureId(Long testConfigureId) {
    return acceptedValueRepository.existsByTestConfigureId(testConfigureId);
  }

  @Transactional(readOnly = true)
  public boolean isAcceptedValueByTestConfigureIdAndTestEquation(Long testConfigureId,
      Long testEquationId) {
    if (acceptedValueRepository.existsByTestConfigureIdAndTestEquationId(testConfigureId,
        testEquationId)) {
      return true;
    }
    return false;
  }


  public boolean isUpdatedAcceptedValueTestConfigureIdExist(Long id, Long testConfigureId) {
    if ((!getAcceptedValueById(id).getTestConfigure().getId().equals(testConfigureId))
        && (isAcceptedValueByTestConfigureId(testConfigureId))) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public Page<AcceptedValue> searchAcceptedValue(Predicate predicate, int size, int page) {
    return acceptedValueRepository.findAll(predicate,
        PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
  }

  @Transactional(readOnly = true)
  public List<AcceptedValue> findByTestConfigure(Long testConfigureId) {
    return acceptedValueRepository.findByTestConfigureId(testConfigureId);
  }
}

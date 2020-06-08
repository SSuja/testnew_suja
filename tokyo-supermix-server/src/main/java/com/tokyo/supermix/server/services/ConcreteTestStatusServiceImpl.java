package com.tokyo.supermix.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tokyo.supermix.data.entities.ConcreteTestStatus;
import com.tokyo.supermix.data.enums.ConcreteStatus;
import com.tokyo.supermix.data.repositories.ConcreteTestStatusRepository;

@Service
public class ConcreteTestStatusServiceImpl implements ConcreteTestStatusService {

  @Autowired
  private ConcreteTestStatusRepository concreteTestStatusRepository;

  @Transactional
  public ConcreteTestStatus saveConcreteTestStatus(ConcreteTestStatus concreteTestStatus) {
    return concreteTestStatusRepository.save(concreteTestStatus);
  }

  @Transactional(readOnly = true)
  public List<ConcreteTestStatus> getAllConcreteTestStatus() {
    return concreteTestStatusRepository.findAll();
  }

  @Transactional(readOnly = true)
  public ConcreteTestStatus getConcreteTestStatusById(Long id) {
    return concreteTestStatusRepository.findById(id).get();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteConcreteTestStatus(Long id) {
    concreteTestStatusRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public boolean isConcreteTestStatusExists(Long id) {
    return concreteTestStatusRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public boolean isDuplicateEntryExist(Long concreteTestTypeId, Long finishProductSampleId) {
    if (concreteTestStatusRepository.existsByConcreteTestTypeIdAndFinishProductSampleId(
        concreteTestTypeId, finishProductSampleId)) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public List<ConcreteTestStatus> getConcreteTestStatusByConcreteStatus(
      ConcreteStatus concreteStatus) {
    return concreteTestStatusRepository.findByConcreteStatus(concreteStatus);
  }

  @Transactional(readOnly = true)
  public boolean isConcreteTestTypeExits(String concreteTestType) {
    return concreteTestStatusRepository.existsByConcreteTestTypeType(concreteTestType);
  }

  @Transactional(readOnly = true)
  public List<ConcreteTestStatus> getConcreteTestStatusByConcreteStatusAndConcreteTestType(
      ConcreteStatus concreteStatus, String concreteTestType) {
    return concreteTestStatusRepository.findByConcreteStatusAndConcreteTestTypeType(concreteStatus,
        concreteTestType);
  }

  @Transactional(readOnly = true)
  public List<ConcreteTestStatus> findByConcreteTestTypeType(String concreteTestType) {
    return concreteTestStatusRepository.findByConcreteTestTypeType(concreteTestType);
  }
}

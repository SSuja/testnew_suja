package com.tokyo.supermix.server.services;

import java.util.List;

import com.tokyo.supermix.data.entities.ConcreteTestStatus;
import com.tokyo.supermix.data.enums.ConcreteStatus;

public interface ConcreteTestStatusService {
  public ConcreteTestStatus saveConcreteTestStatus(ConcreteTestStatus concreteTestStatus);

  public List<ConcreteTestStatus> getAllConcreteTestStatus();

  public ConcreteTestStatus getConcreteTestStatusById(Long id);

  public void deleteConcreteTestStatus(Long id);

  public boolean isConcreteTestStatusExists(Long id);

  public boolean isDuplicateEntryExist(Long concreteTestTypeId, Long finishProductSampleId);

  public ConcreteTestStatus getConcreteTestStatusByConcreteStatus(ConcreteStatus concreteStatus);

}
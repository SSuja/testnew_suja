package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.FinishProductAcceptedValue;

public interface FinishProductAcceptedValueService {
  public FinishProductAcceptedValue saveFinishProductAcceptedValue(
      FinishProductAcceptedValue finishProductAcceptedValue);

  public List<FinishProductAcceptedValue> getAllFinishProductAcceptedValues();

  boolean isFinishProductAcceptedValueExist(Long id);

  public FinishProductAcceptedValue getFinishProductAcceptedValueById(Long id);

  public void deleteFinishProductAcceptedValue(Long id);

  public List<FinishProductAcceptedValue> getFinishProductAcceptedValueByTestParameter(
      Long testParameterId);
}
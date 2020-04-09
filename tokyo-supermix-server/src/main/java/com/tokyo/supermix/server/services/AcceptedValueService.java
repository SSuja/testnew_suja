package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.AcceptedValue;
import com.tokyo.supermix.data.entities.TestConfigure;

public interface AcceptedValueService {

  public void saveAcceptedValue(AcceptedValue acceptedValue);

  public List<AcceptedValue> getAllAcceptedValues();

  boolean isAcceptedValueExist(Long id);

  public AcceptedValue getAcceptedValueById(Long id);

  public void deleteAcceptedValue(Long id);

  public List<AcceptedValue> getAcceptedValueByTest(TestConfigure test);

  boolean isAcceptedValueByTestId(Long testId);

  public boolean isUpdatedAcceptedValueTestIdExist(Long id, Long testId);

}

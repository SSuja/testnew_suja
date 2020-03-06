package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.AcceptedValue;

public interface AcceptedValueService {

  public void saveAcceptedValue(AcceptedValue acceptedValue);

  public List<AcceptedValue> getAllAcceptedValues();

  boolean isAcceptedValueExist(Long id);

  public AcceptedValue getAcceptedValueById(Long id);

  public void deleteAcceptedValue(Long id);

}

package com.tokyo.supermix.server.services;

import java.util.List;

import com.tokyo.supermix.data.entities.AdmixtureAcceptedValue;

public interface AdmixtureAcceptedValueService {

  public void saveAdmixtureAcceptedValue(AdmixtureAcceptedValue admixtureAcceptedValue);

  public List<AdmixtureAcceptedValue> getAllAdmixtureAcceptedValues();

  boolean isAdmixtureAcceptedValueExist(Long id);

  public AdmixtureAcceptedValue getAdmixtureAcceptedValueById(Long id);

  public void deleteAdmixtureAcceptedValue(Long id);

  public boolean isUpdatedTestIdExist(Long id, Long testId);

  public boolean isAdmixtureAcceptedValueExistsByTestId(Long id);

  public AdmixtureAcceptedValue getAdmixtureAcceptedValueByTestId(Long testId);
}

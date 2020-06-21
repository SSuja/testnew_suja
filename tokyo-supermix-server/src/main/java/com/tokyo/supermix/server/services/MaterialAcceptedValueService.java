package com.tokyo.supermix.server.services;

import java.util.List;

import com.tokyo.supermix.data.entities.MaterialAcceptedValue;
import com.tokyo.supermix.data.entities.TestConfigure;

public interface MaterialAcceptedValueService {

  public List<MaterialAcceptedValue> saveAcceptedValue(
      List<MaterialAcceptedValue> materialAcceptedValue);

  public void updateMaterialAcceptedValue(MaterialAcceptedValue materialAcceptedValue);

  public List<MaterialAcceptedValue> getAllMaterialAcceptedValues();

  boolean isMaterialAcceptedValueExist(Long id);

  public MaterialAcceptedValue getMaterialAcceptedValueById(Long id);

  public void deleteMaterialAcceptedValue(Long id);

  public List<MaterialAcceptedValue> getMaterialAcceptedValueByTestConfigure(
      TestConfigure testConfigure);

  boolean isDuplicateEntryExist(Long testConfigureId, Long rawMaterialId);

  public boolean isUpdatedRawMaterialIdExist(Long id, Long rawMaterialId);

  boolean isRawMaterialIdExist(Long rawMaterialId);
}
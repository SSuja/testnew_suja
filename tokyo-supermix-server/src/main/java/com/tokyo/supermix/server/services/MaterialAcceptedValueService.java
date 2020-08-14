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

  public boolean isUpdatedRawMaterialIdExist(Long id, Long testConfigureId, Long rawMaterialId);

  boolean isRawMaterialIdExist(Long rawMaterialId);

  public boolean isTestConfigureIdAndRawMaterialIdAndTestParameterId(Long testConfigureId,
      Long rawMaterialId, Long testParameterId);

  public boolean isCheckValidation(List<MaterialAcceptedValue> materialAcceptedValueList);
}

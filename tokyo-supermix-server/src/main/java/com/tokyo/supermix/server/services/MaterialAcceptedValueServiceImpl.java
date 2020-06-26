package com.tokyo.supermix.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tokyo.supermix.data.entities.MaterialAcceptedValue;
import com.tokyo.supermix.data.entities.TestConfigure;
import com.tokyo.supermix.data.repositories.MaterialAcceptedValueRepository;

@Service
public class MaterialAcceptedValueServiceImpl implements MaterialAcceptedValueService {

  @Autowired
  private MaterialAcceptedValueRepository materialAcceptedValueRepository;

  @Transactional
  public List<MaterialAcceptedValue> saveAcceptedValue(
      List<MaterialAcceptedValue> materialAcceptedValue) {
    return materialAcceptedValueRepository.saveAll(materialAcceptedValue);
  }

  @Transactional
  public void updateMaterialAcceptedValue(MaterialAcceptedValue materialAcceptedValue) {
    materialAcceptedValueRepository.save(materialAcceptedValue);
  }

  @Transactional(readOnly = true)
  public List<MaterialAcceptedValue> getAllMaterialAcceptedValues() {
    return materialAcceptedValueRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isMaterialAcceptedValueExist(Long id) {
    return materialAcceptedValueRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public MaterialAcceptedValue getMaterialAcceptedValueById(Long id) {
    return materialAcceptedValueRepository.findById(id).get();
  }

  @Transactional
  public void deleteMaterialAcceptedValue(Long id) {
    materialAcceptedValueRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public List<MaterialAcceptedValue> getMaterialAcceptedValueByTestConfigure(
      TestConfigure testConfigure) {
    return materialAcceptedValueRepository.findByTestConfigure(testConfigure);
  }

  @Transactional(readOnly = true)
  public boolean isDuplicateEntryExist(Long testConfigureId, Long rawMaterialId) {
    return materialAcceptedValueRepository.existsByTestConfigureIdAndRawMaterialId(testConfigureId,
        rawMaterialId);
  }

  @Transactional(readOnly = true)
  public boolean isUpdatedRawMaterialIdExist(Long id, Long rawMaterialId) {
    if ((!getMaterialAcceptedValueById(id).getRawMaterial().equals(rawMaterialId))
        && (isRawMaterialIdExist(rawMaterialId))) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public boolean isRawMaterialIdExist(Long rawMaterialId) {
    return materialAcceptedValueRepository.existsByRawMaterialId(rawMaterialId);
  }
}
